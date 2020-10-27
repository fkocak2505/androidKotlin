package com.musicore.versioncomparing

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.apache.maven.artifact.versioning.DefaultArtifactVersion


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val minVersion = DefaultArtifactVersion("1.11.1")
        val maxVersion = DefaultArtifactVersion("1.10")

        val version = DefaultArtifactVersion("1.11")

        if (minVersion > version) {
            Toast.makeText(applicationContext, "Min Version küçük", Toast.LENGTH_SHORT).show()
        } else if(maxVersion > version){
            Toast.makeText(applicationContext, "Max Version büyük", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(applicationContext, "awfawf", Toast.LENGTH_SHORT).show()
        }
    }
}