package com.musicore.downloadfileretrofit

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.*


class MainActivity : AppCompatActivity() {

    private val MY_PERMISSIONS_REQUEST = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST
            )
        }

        downloadVideo.setOnClickListener {
            downloadFile()
        }

        playVideo.setOnClickListener {
            //val uri = Uri.parse(getExternalFilesDir(null).toString() + File.separator.toString() + "deneme.mp4")
            val uri = FileProvider.getUriForFile(
                applicationContext, "$packageName.provider", File(
                    getExternalFilesDir(
                        null
                    ).toString() + File.separator.toString() + "deneme.mp4"
                )
            )

            val mediaController = MediaController(this@MainActivity)
            mediaController.setAnchorView(videoViewDeneme)

            videoViewDeneme.setMediaController(mediaController)
            videoViewDeneme.setVideoURI(uri)
            videoViewDeneme.requestFocus()
            videoViewDeneme.start()
        }

    }

    private fun downloadFile() {
        val builder = Retrofit.Builder()
            .baseUrl("http://commondatastorage.googleapis.com/")

        val retrofit = builder.build()

        val fileDownloadClient = retrofit.create(FileDownloadClient::class.java)

        val call = fileDownloadClient.downloadFile()

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                doAsync(applicationContext, progressBar, txtProgressPercent) {
                    val success = writeResponseBodyToDisk(response.body()!!)
                    Log.d("TAG", "file download finish")
                }.execute()

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(applicationContext, "No..", Toast.LENGTH_SHORT).show()
            }
        })
    }

    class doAsync(
        val context: Context,
        val progressBar: ProgressBar,
        val percentTextView: TextView,
        val handler: () -> Unit
    ) : AsyncTask<ResponseBody, Pair<Integer, Long>, String>() {
        /*override fun doInBackground(vararg params: Void?): Void? {
            handler()
            return null
        }*/
        override fun doInBackground(vararg params: ResponseBody?): String {
            handler()
            return ""
        }
    }

    @SuppressLint("SetTextI18n")
    private fun writeResponseBodyToDisk(body: ResponseBody): Boolean {
        return try {
            // todo change the file location/name according to your needs
            val futureStudioIconFile =
                File(getExternalFilesDir(null).toString() + File.separator.toString() + "deneme.mp4")
            var inputStream: InputStream? = null
            Log.d("FILE PATHHH", futureStudioIconFile.toString())
            var outputStream: OutputStream? = null
            try {
                val fileReader = ByteArray(4096)
                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0
                inputStream = body.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)
                while (true) {
                    val read: Int = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                    Log.d("TAG", "file download: $fileSizeDownloaded of $fileSize")

                    val currentProgress =
                        (fileSizeDownloaded * 100 / fileSize ).toInt()

                    runOnUiThread {
                        progressBar.progress = currentProgress
                        txtProgressPercent.text = "Progress $currentProgress %"

                    }

                }
                outputStream.flush()
                true
            } catch (e: IOException) {
                false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(applicationContext, "İzin verildi", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(applicationContext, "İzin verilmedi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}