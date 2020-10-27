package com.gencdil.audiorecord

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Constraints
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.gencdil.audiorecord.adapter.RecordModel
import com.gencdil.audiorecord.adapter.RecordRecyclerViewAdapter
import com.gencdil.audiorecord.common.RecordCommon
import com.gencdil.audiorecord.constant.RecordConstant
import com.gencdil.audiorecord.prefs.StringSharedPrefs
import com.gencdil.audiorecord.record.AudioRecordView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {

    private lateinit var sharedPrefs: SharedPreferences

    private lateinit var mapOfRecordSharedPrefs: HashMap<String, RecordModel>

    private var mediaPlayer: MediaPlayer? = null

    private var length: Int? = null

    private lateinit var timerRecordd: Chronometer

    private var nameAndSurname: String = ""

    private var fileeName = ""

    private var playingPos = -1


    private val requiredPermissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.RECORD_AUDIO
    )

    private var timer: Timer? = null
    private var recorder: MediaRecorder? = null

    private var fileName: String = ""
    private lateinit var audioRecordView: AudioRecordView
    private lateinit var uuid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initMainActivityComponent()


        sesKayitlari.typeface =
            Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")


        fillRecordRecyclerView()

        mediaPlayer = MediaPlayer()

        startRecord.setOnClickListener {

            if(mediaPlayer?.isPlaying!!){
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer()

                val strOfRecord = StringSharedPrefs(sharedPrefs, RecordConstant.RECORD_FILES, "").getValue<String>()
                val mapOfRecord = RecordCommon.convertString2Map(strOfRecord)

                mapOfRecord.forEach{(key, value) ->
                    value.isPlay = -1
                    value.playIcon = R.drawable.ic_play_button
                }

                val newStrOfRecord = RecordCommon.convertMap2String(mapOfRecord)
                StringSharedPrefs(sharedPrefs, RecordConstant.RECORD_FILES,"").setValue(newStrOfRecord)

            }
            if (StringSharedPrefs(sharedPrefs, RecordConstant.USER, "").getValue<String>() == "")
                launchCustomDialog4User()

            if (StringSharedPrefs(sharedPrefs, RecordConstant.USER, "").getValue<String>() != "")
                launchCustomDialog()

        }

        infoApp.setOnClickListener {
            startActivity(Intent(applicationContext, AppInfoActivity::class.java))
        }
    }

    private fun initMainActivityComponent() {
        supportActionBar?.hide()
        sharedPrefs =
            getSharedPreferences(RecordConstant.FILE_NAME, Context.MODE_PRIVATE)

        recordRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun fillRecordRecyclerView() {
        if (getRecordFiles().size != 0) {
            recordRecyclerView.adapter =
                RecordRecyclerViewAdapter(
                    applicationContext,
                    sharedPrefs,
                    getRecordFiles()
                ) { position, recordItem, type, imageView ->
                    when (type) {
                        "FORM" -> {

                            if(mediaPlayer != null){
                                mediaPlayer?.stop()
                                mediaPlayer?.release()
                                mediaPlayer = MediaPlayer()

                                recordItem.isPlay = -1
                                recordItem.playIcon = R.drawable.ic_play_button

                                imageView.setImageResource(R.drawable.ic_play_button)

                            }
                            RecordSingleton.instance.setRecordItem(recordItem)
                            RecordSingleton.instance.setPosition(position)
                            startActivity(Intent(applicationContext, FormData::class.java))
                        }
                        "PLAY" -> {
                            if(playingPos == -1)
                                playingPos = position

                            if (!mediaPlayer?.isPlaying!! || playingPos == position){
                                playingPos = position
                                playAndPauseVideo(recordItem, imageView)
                            }

                        }
                    }
                }
        }
    }

    private fun playAndPauseVideo(recordItem: RecordModel, imageView: ImageView) {
        if (!mediaPlayer?.isPlaying!!) {
            if (recordItem.isPlay == -1) {
                try {
                    mediaPlayer?.setDataSource(recordItem.recordPath + File.separator + "")
                    mediaPlayer?.prepare()
                    mediaPlayer?.start()
                    imageView.setImageResource(R.drawable.ic_pause_button)
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "awfafaw", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            } else {
                mediaPlayer?.seekTo(length!!)
                mediaPlayer?.start()
                imageView.setImageResource(R.drawable.ic_pause_button)
            }
        } else {
            imageView.setImageResource(R.drawable.ic_play_button)
            /*mediaPlayer.pause()
            length = mediaPlayer.currentPosition
            recordItem.isPlay = 0*/

            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer()

            recordItem.isPlay = -1


        }

        mediaPlayer!!.setOnCompletionListener {
            imageView.setImageResource(R.drawable.ic_play_button)

            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer()

            recordItem.isPlay = -1
        }

    }


    private fun getRecordFiles(): MutableList<RecordModel> {
        val sRecordFiles =
            StringSharedPrefs(sharedPrefs, RecordConstant.RECORD_FILES, "").getValue<String>()

        return if (sRecordFiles == "")
            mutableListOf()
        else {
            mapOfRecordSharedPrefs =
                RecordCommon.convertString2Map(sRecordFiles) as HashMap<String, RecordModel>
            convertMapToList(mapOfRecordSharedPrefs)
        }
    }

    private fun convertMapToList(map: HashMap<String, RecordModel>): MutableList<RecordModel> {
        val recordsList: MutableList<RecordModel> = mutableListOf()

        map.forEach { (key, value) ->
            recordsList.add(
                RecordModel(
                    key,
                    value.date,
                    value.playIcon,
                    value.recordPath,
                    -1,
                    value.time,
                    fileeName
                )
            )
        }

        return recordsList
    }

    private fun launchCustomDialog4User() {
        val customLayout =
            LayoutInflater.from(this@MainActivity).inflate(R.layout.user_info, null)

        val nameAndSurName: EditText = customLayout.findViewById(R.id.nameAndSurnam)

        val builder = AlertDialog.Builder(this@MainActivity)
            .setView(customLayout)
            .setPositiveButton("Tamam") { dialogInterface, _ ->
                if (nameAndSurName.text.toString().trim() != "") {
                    nameAndSurname = nameAndSurName.text.toString()
                    StringSharedPrefs(sharedPrefs, RecordConstant.USER, "").setValue(nameAndSurname)
                    dialogInterface.dismiss()
                } else
                    Toast.makeText(
                        applicationContext,
                        "Ad ve Soyad alanı boş bırakılamaz..!",
                        Toast.LENGTH_LONG
                    ).show()
            }
            .setNegativeButton("İptal") { dialogInterface, _ ->
                Toast.makeText(
                    applicationContext,
                    "Ad ve Soyad girilmesi zorunludur. Lütfen adınızı ve soyadınızı giriniz.",
                    Toast.LENGTH_LONG
                ).show()
            }
            .setCancelable(false)
        builder.show()
    }

    private fun launchCustomDialog() {

        val dialog = Dialog(this@MainActivity)
        dialog.setContentView(R.layout.custom_record_layout)


        val startRecord: ImageView = dialog.findViewById(R.id.startRecord)
        val stopRecording: TextView = dialog.findViewById(R.id.stopRecording)
        val cancelButton: TextView = dialog.findViewById(R.id.cancelButton)
        val time: Chronometer = dialog.findViewById(R.id.timerRecord)
        audioRecordView = dialog.findViewById(R.id.audioRecordView)

        startRecord.setOnClickListener {

            stopRecording.visibility = View.VISIBLE

            uuid = UUID.randomUUID().toString()

            fileeName = "$uuid.aac"

            fileName = "${externalCacheDir?.absolutePath}/audiorecord_$uuid.aac"


            startRecord.visibility = View.INVISIBLE
            audioRecordView.visibility = View.VISIBLE

            startRecording(time)
        }

        stopRecording.setOnClickListener {
            stopRecording()

            time.stop()

            startRecord.visibility = View.VISIBLE
            audioRecordView.visibility = View.INVISIBLE

            saveSharedPref(uuid, fileName, time.text.toString())

            dialog.dismiss()

            Toast.makeText(
                applicationContext,
                "Tebrikler..! Kayıt başarılı bir şekilde yapılmıştır.",
                Toast.LENGTH_LONG
            ).show()
        }

        cancelButton.setOnClickListener {

            if (audioRecordView.visibility == View.VISIBLE) {
                stopRecording()
                startRecord.visibility = View.INVISIBLE
                audioRecordView.visibility = View.VISIBLE

            }

            fillRecordRecyclerView()
            dialog.dismiss()

        }

        dialog.setCancelable(false)
        dialog.show()
        val window = dialog.window
        window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            Constraints.LayoutParams.WRAP_CONTENT
        )

    }

    private fun startRecording(time: Chronometer) {
        if (!permissionsIsGranted(requiredPermissions)) {
            ActivityCompat.requestPermissions(this, requiredPermissions, 200)
            timerRecordd = time
            return
        }

        //Creating MediaRecorder and specifying audio source, output format, encoder & output format
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setAudioSamplingRate(44100)
            setAudioEncodingBitRate(96000)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            //
            setOutputFile(fileName)

            try {
                prepare()
            } catch (e: IOException) {

            }
            start()

            time.base = SystemClock.elapsedRealtime()
            time.start()
        }

        startDrawing()
    }

    private fun stopRecording() {
        recorder?.stop()
        recorder?.release()

        stopDrawing()
    }

    private fun startDrawing() {
        timer = Timer()

        timer?.schedule(object : TimerTask() {
            override fun run() {
                audioRecordView.update((0..5000).random())
            }
        }, 0, 100)
    }

    private fun stopDrawing() {
        timer?.cancel()
        audioRecordView.recreate()
    }

    private fun permissionsIsGranted(perms: Array<String>): Boolean {
        for (perm in perms) {
            val checkVal: Int = checkCallingOrSelfPermission(perm)
            if (checkVal != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun saveSharedPref(uuid: String, fileName: String, time: String) {
        val sRecordFiles =
            StringSharedPrefs(sharedPrefs, RecordConstant.RECORD_FILES, "").getValue<String>()

        mapOfRecordSharedPrefs = HashMap()

        if (sRecordFiles == "") {
            mapOfRecordSharedPrefs[uuid] =
                RecordModel(
                    uuid,
                    getCurrentDate(),
                    R.drawable.ic_play_button,
                    fileName,
                    -1,
                    time,
                    fileeName
                )
        } else {
            mapOfRecordSharedPrefs =
                RecordCommon.convertString2Map(sRecordFiles) as HashMap<String, RecordModel>
            mapOfRecordSharedPrefs[uuid] =
                RecordModel(
                    uuid,
                    getCurrentDate(),
                    R.drawable.ic_play_button,
                    fileName,
                    -1,
                    time,
                    fileeName
                )
        }


        StringSharedPrefs(
            sharedPrefs,
            RecordConstant.RECORD_FILES,
            ""
        ).setValue(RecordCommon.convertMap2String(mapOfRecordSharedPrefs))

        fillRecordRecyclerView()

    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(System.currentTimeMillis()))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return
            }
        }
        startRecording(timerRecordd)
    }

}
