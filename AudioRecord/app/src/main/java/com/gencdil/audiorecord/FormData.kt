package com.gencdil.audiorecord

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gencdil.audiorecord.adapter.RecordModel
import com.gencdil.audiorecord.constant.RecordConstant
import com.gencdil.audiorecord.prefs.StringSharedPrefs
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_form_data.*
import java.io.File


class FormData : AppCompatActivity() {

    private lateinit var recordItem: RecordModel
    private lateinit var sharedPreferences: SharedPreferences

    private var mapOfFormData: HashMap<String, Any?> = HashMap()
    private var mapOfFirstSpeaker: HashMap<String, String> = HashMap()
    private var mapOfSecondSpeaker: HashMap<String, String> = HashMap()

    private var iliski: MutableList<String> = mutableListOf()

    private val longLiveAnkaraData =
        arrayOf("1 yıldan az", "1 Yıl", "2-5 Yıl", "6-10 Yıl", "10 Yıldan Fazla")
    private val edLevel =
        arrayOf("İlkokul", "Lise", "Üniversite(Lisans)", "Yüksek Lisans", "Doktora")
    private val neZamandanBeri =
        arrayOf("6 aydan kısa süredir", "1 Yıl", "2 Yıl", "3 Yıl", "4 Yıl", "5 Yıldan uzun süre")
    private val neSiklikla = arrayOf(
        "Her gün",
        "İki günde bir",
        "3-4 Günder bir",
        "Haftada bir",
        "İki haftada bir",
        "Ayda bir",
        "Daha seyrek"
    )

    private lateinit var mediaPlayer: MediaPlayer
    private var length: Int? = null

    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference
    //private val mountainImagesRef = storageRef.child("records/FKocak.aac")
    private lateinit var mountainImagesRef: StorageReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_data)

        changeTypeFace()

        val db = FirebaseFirestore.getInstance()

        sharedPreferences = getSharedPreferences(RecordConstant.FILE_NAME, Context.MODE_PRIVATE)
        recordItem = RecordSingleton.instance.getRecordItem()

        val pathString = "records/" + recordItem.fileName
        mountainImagesRef = storageRef.child(pathString)

        supportActionBar?.hide()

        mediaPlayer = MediaPlayer()
        playRecordFile.setOnClickListener{
            playAndPauseVideo(recordItem, playRecordFile)
        }

        val longLiveAnkaraDataAdapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, longLiveAnkaraData)

        val edLevelAdapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, edLevel)

        val neZamandanBeriAdapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, neZamandanBeri)

        val neSikliklaAdapter =
            ArrayAdapter<String>(this, R.layout.spinner_item, neSiklikla)

        spnFatherJob.adapter = edLevelAdapter
        spnFatherJob2.adapter = edLevelAdapter
        spnMotherJob.adapter = edLevelAdapter
        spnMotherJob2.adapter = edLevelAdapter

        spnLongLiveAnkara.adapter = longLiveAnkaraDataAdapter
        spnLongLiveAnkara2.adapter = longLiveAnkaraDataAdapter

        spnNeZamandanBeri.adapter = neZamandanBeriAdapter
        spnMeeting.adapter = neSikliklaAdapter



        sendData.setOnClickListener {

            if(mediaPlayer.isPlaying){
                mediaPlayer.stop()
                mediaPlayer.release()
                mediaPlayer = MediaPlayer()

                recordItem.isPlay = -1
                recordItem.playIcon = R.drawable.ic_play_button

                playRecordFile.setImageResource(R.drawable.ic_play_button)

            }


            val listOfValidator: MutableList<EmptyCheckModel> = mutableListOf()
            listOfValidator.add(
                EmptyCheckModel(
                    speakPlace.text.toString(),
                    true,
                    "Konuşma nerede gerçekleşti? (kafe, ev, vb.)*",
                    speakPlace
                )
            )
            listOfValidator.add(
                EmptyCheckModel(
                    firstWord.text.toString(),
                    true,
                    "Sesini ayırt edebilmek için, bu kayıtta SENİN söylediğin ilk sözcük/ifade nedir?",
                    firstWord
                )
            )
            listOfValidator.add(
                EmptyCheckModel(
                    nameSurname.text.toString(),
                    true,
                    "Ad Soyad",
                    nameSurname
                )
            )
            listOfValidator.add(
                EmptyCheckModel(
                    birthday.text.toString(),
                    true,
                    "Doğum Tarihi",
                    birthday
                )
            )
            listOfValidator.add(EmptyCheckModel(gender.text.toString(), true, "Cinsiyet", gender))
            listOfValidator.add(
                EmptyCheckModel(
                    mainLanguage.text.toString(),
                    true,
                    "Ana Dili",
                    mainLanguage
                )
            )
            listOfValidator.add(
                EmptyCheckModel(
                    otherLanguage.text.toString(),
                    true,
                    "Varsa bildiği diğer diller.",
                    otherLanguage
                )
            )
            listOfValidator.add(
                EmptyCheckModel(
                    fromCity.text.toString(),
                    true,
                    "Memleket",
                    fromCity
                )
            )
            listOfValidator.add(EmptyCheckModel(school.text.toString(), true, "Okulu", school))
            listOfValidator.add(EmptyCheckModel(classBy.text.toString(), true, "Sınıfı", classBy))
            listOfValidator.add(
                EmptyCheckModel(
                    motherJob.text.toString(),
                    true,
                    "Annesinin Mesleği",
                    motherJob
                )
            )
            listOfValidator.add(
                EmptyCheckModel(
                    fatherJob.text.toString(),
                    true,
                    "Babasının Mesleği",
                    fatherJob
                )
            )
            listOfValidator.add(
                EmptyCheckModel(
                    brotherCount.text.toString(),
                    true,
                    "Kardeş Sayısı",
                    brotherCount
                )
            )
            listOfValidator.add(
                EmptyCheckModel(
                    firstWordForSpeaker.text.toString(),
                    true,
                    "Konuşmacıyı ayırt etmek için kullandığı ilk sözcük?",
                    firstWordForSpeaker
                )
            )


            if (emptyCheck(listOfValidator).status != null) {
                Toast.makeText(
                    applicationContext,
                    emptyCheck(listOfValidator).errorMessage + " alanı boş bırakılamaz",
                    Toast.LENGTH_SHORT
                ).show()
                emptyCheck(listOfValidator).component?.requestFocus()
            } else {

                mapOfFirstSpeaker["name"] = nameSurname.text.toString()
                mapOfFirstSpeaker["birthDate"] = birthday.text.toString()
                mapOfFirstSpeaker["gender"] = gender.text.toString()
                mapOfFirstSpeaker["nativeLang"] = mainLanguage.text.toString()
                mapOfFirstSpeaker["otherLangs"] = otherLanguage.text.toString()
                mapOfFirstSpeaker["hometown"] = fromCity.text.toString()
                mapOfFirstSpeaker["howManyYearsInAnkara"] =
                    spnLongLiveAnkara.selectedItem.toString()
                mapOfFirstSpeaker["school"] = school.text.toString()
                mapOfFirstSpeaker["class"] = classBy.text.toString()
                mapOfFirstSpeaker["motherJob"] = motherJob.text.toString()
                mapOfFirstSpeaker["motherEducation"] = spnMotherJob.selectedItem.toString()
                mapOfFirstSpeaker["fatherJob"] = fatherJob.text.toString()
                mapOfFirstSpeaker["fatherEducation"] = spnFatherJob.selectedItem.toString()
                mapOfFirstSpeaker["brotherNumber"] = brotherCount.text.toString()
                mapOfFirstSpeaker["firstWord"] = firstWordForSpeaker.text.toString()

                if (isExistSecondSpeaker.isChecked) {
                    mapOfFirstSpeaker["name"] = nameSurname2.text.toString()
                    mapOfFirstSpeaker["birthDate"] = birthday2.text.toString()
                    mapOfFirstSpeaker["gender"] = gender2.text.toString()
                    mapOfFirstSpeaker["nativeLang"] = mainLanguage2.text.toString()
                    mapOfFirstSpeaker["otherLangs"] = otherLanguage2.text.toString()
                    mapOfFirstSpeaker["hometown"] = fromCity2.text.toString()
                    mapOfFirstSpeaker["howManyYearsInAnkara"] =
                        spnLongLiveAnkara2.selectedItem.toString()
                    mapOfFirstSpeaker["school"] = school2.text.toString()
                    mapOfFirstSpeaker["class"] = classBy2.text.toString()
                    mapOfFirstSpeaker["motherJob"] = motherJob2.text.toString()
                    mapOfFirstSpeaker["motherEducation"] = spnMotherJob2.selectedItem.toString()
                    mapOfFirstSpeaker["fatherJob"] = fatherJob2.text.toString()
                    mapOfFirstSpeaker["fatherEducation"] = spnFatherJob2.selectedItem.toString()
                    mapOfFirstSpeaker["brotherNumber"] = brotherCount2.text.toString()
                    mapOfFirstSpeaker["firstWord"] = firstWordForSpeaker2.text.toString()
                }

                if (closeFriend.isChecked)
                    iliski.add("Yakın Arkdaş")
                if (doorMate.isChecked)
                    iliski.add("Komşu")
                if (relative.isChecked)
                    iliski.add("Akraba")
                if (schoolFriend.isChecked)
                    iliski.add("Okul Arkadaşı")
                if (dershaneArkadasi.isChecked)
                    iliski.add("Dershane Arkadaşı")
                if (mahalleArkadasi.isChecked)
                    iliski.add("Mahalle Arkadaşı")

                mapOfFormData["inWhere"] = speakPlace.text.toString()
                mapOfFormData["docName"] = StringSharedPrefs(
                    sharedPreferences,
                    RecordConstant.USER,
                    ""
                ).getValue<String>() + recordItem.date
                mapOfFormData["firstWordUser"] = firstWord.text.toString()
                mapOfFormData["extraInfo"] = extraInfo.text.toString()
                mapOfFormData["friendLevel"] = iliski
                mapOfFormData["friendshipTime"] = spnNeZamandanBeri.selectedItem.toString()
                mapOfFormData["meetFrequency"] = spnMeeting.selectedItem.toString()
                mapOfFormData["firstSpeaker"] = mapOfFirstSpeaker
                mapOfFormData["recordTime"] = recordItem.date
                mapOfFormData["recordDuration"] = recordItem.time
                mapOfFormData["username"] =
                    StringSharedPrefs(sharedPreferences, RecordConstant.USER, "").getValue<String>()

                if (isExistSecondSpeaker.isChecked)
                    mapOfFormData["secondSpeaker"] = mapOfSecondSpeaker
                else
                    mapOfFormData["secondSpeaker"] = null

                var file = Uri.fromFile(File(recordItem.recordPath))

                showLoading()
                mountainImagesRef.putFile(file).addOnFailureListener {
                    Toast.makeText(applicationContext, "Ses kaydı sunucuya atılırken bir sorun oluştu.", Toast.LENGTH_SHORT).show()
                }.addOnSuccessListener {
                    mountainImagesRef.putFile(file).continueWithTask { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let {
                                throw it
                                hideLoading()
                            }
                        }
                        mountainImagesRef.downloadUrl
                    }.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val downloadUri = task.result
                            mapOfFormData["recordURL"] = downloadUri.toString()

                            db.collection("RECORDS")
                                .document(
                                    StringSharedPrefs(
                                        sharedPreferences,
                                        RecordConstant.USER,
                                        ""
                                    ).getValue<String>() + recordItem.date
                                )
                                .set(mapOfFormData)
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        applicationContext,
                                        "Formunuz Ses kaydınız ile birlikte başarılı bir şekilde kaydedilmiştir.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    hideLoading()

                                    startActivity(Intent(applicationContext, MainActivity::class.java))
                                }
                                .addOnFailureListener {
                                    hideLoading()
                                    Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT)
                                        .show()
                                }
                        } else {
                            hideLoading()
                            Toast.makeText(
                                applicationContext,
                                "Ses kaydı başarılıyla atıldı. Fakat data da bir sorun var..",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        }

        isExistSecondSpeaker.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                openVisibility4SecondSpeaker(View.VISIBLE)
            else
                openVisibility4SecondSpeaker(View.GONE)
        }
    }

    private fun openVisibility4SecondSpeaker(visibility: Int) {
        secondSpeaker.visibility = visibility
        nameSurname2.visibility = visibility
        birthday2.visibility = visibility
        gender2.visibility = visibility
        mainLanguage2.visibility = visibility
        otherLanguage2.visibility = visibility
        fromCity2.visibility = visibility
        longLiveAnkara2.visibility = visibility
        spnLongLiveAnkara2.visibility = visibility
        school2.visibility = visibility
        classBy2.visibility = visibility
        motherJob2.visibility = visibility
        infoMotherEdLevel.visibility = visibility
        spnMotherJob2.visibility = visibility
        fatherJob2.visibility = visibility
        textView.visibility = visibility
        spnFatherJob2.visibility = visibility
        brotherCount2.visibility = visibility
        firstWordForSpeaker2.visibility = visibility
        //deneme.visibility = visibility
    }

    fun emptyCheck(emptyCheckModel: MutableList<EmptyCheckModel>): EmptyCheckModel {
        var emptyObject = EmptyCheckModel(null, null, null, null)
        for (i in 0 until emptyCheckModel.size) {
            if (!stringCheck(emptyCheckModel.get(i).componentVal as String)) {
                emptyObject = EmptyCheckModel(
                    emptyCheckModel.get(i).componentVal,
                    false, emptyCheckModel[i].errorMessage + " alanı boş bırakılamaz",
                    emptyCheckModel[i].component
                )
                break
            }
        }
        return emptyObject
    }

    private fun stringCheck(stringVal: String): Boolean = when {
        stringVal.trim().equals("") -> false
        else -> true
    }

    private fun changeTypeFace(){
        bilgiler.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        speakPlace.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        firstWord.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        digerKonusmac.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        konusmacı1.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        nameSurname.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        birthday.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        gender.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        mainLanguage.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        otherLanguage.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        fromCity.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        longLiveAnkara.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        school.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        classBy.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        motherJob.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        annesininSeviyesi.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        fatherJob.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        babasininSeviyesi.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        brotherCount.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        firstWordForSpeaker.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        isExistSecondSpeaker.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        secondSpeaker.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        nameSurname2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        birthday2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        gender2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        mainLanguage2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        otherLanguage2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        fromCity2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        longLiveAnkara2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        school2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        classBy2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        motherJob2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        infoMotherEdLevel.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        fatherJob2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        textView.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        brotherCount2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        firstWordForSpeaker2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        ttt1.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        closeFriend.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        doorMate.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        relative.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        schoolFriend.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        dershaneArkadasi.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        mahalleArkadasi.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        other.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        ttt2.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        ttt3.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        ttt4.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        extraInfo.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
        sendData.typeface = Typeface.createFromAsset(applicationContext?.assets, "muli-regular.ttf")
    }

    private fun playAndPauseVideo(recordItem: RecordModel, imageView: ImageView) {
        if (!mediaPlayer.isPlaying) {
            if (recordItem.isPlay == -1) {
                try {
                    mediaPlayer.setDataSource(recordItem.recordPath + File.separator + "")
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                    imageView.setImageResource(R.drawable.ic_pause_button)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                mediaPlayer.seekTo(length!!)
                mediaPlayer.start()
                imageView.setImageResource(R.drawable.ic_pause_button)
            }
        } else {
            imageView.setImageResource(R.drawable.ic_play_button)
            /*mediaPlayer.pause()
            length = mediaPlayer.currentPosition
            recordItem.isPlay = 0*/

            mediaPlayer.stop()
            mediaPlayer.release()
            mediaPlayer = MediaPlayer()

            recordItem.isPlay = -1

        }

        mediaPlayer.setOnCompletionListener {
            imageView.setImageResource(R.drawable.ic_play_button)
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {

                if(mediaPlayer.isPlaying){
                    mediaPlayer.stop()
                    mediaPlayer.release()
                    mediaPlayer = MediaPlayer()

                    recordItem.isPlay = -1
                    recordItem.playIcon = R.drawable.ic_play_button

                    playRecordFile.setImageResource(R.drawable.ic_play_button)

                }


                startActivity(Intent(applicationContext, MainActivity::class.java))
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    fun showLoading() {
        avlIndicatorProgress.smoothToShow()
        window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }


    fun hideLoading() {
        avlIndicatorProgress.smoothToHide()
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

}