package tr.com.fkocak.kotlinfunction

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Toast.makeText(applicationContext, standartFunc("Fatih Koçak"), Toast.LENGTH_SHORT).show()

        Toast.makeText(applicationContext, simplifyFuncWithIf(35), Toast.LENGTH_SHORT).show()

        Toast.makeText(applicationContext, simplifyFuncWithWhen(35), Toast.LENGTH_SHORT).show()

        Toast.makeText(applicationContext, valOfString("Fatih Koçak").toString(), Toast.LENGTH_SHORT).show()

        Toast.makeText(
            applicationContext,
            highOrderFunc("Fatih Koçak", ({ input -> input.length * 3 })).toString(),
            Toast.LENGTH_SHORT
        ).show()
    }


    ////////////////////////////////////
    /**
     * Standart Function Declaration
     */
    ////////////////////////////////////
    fun standartFunc(name: String): String {
        return name
    }

    ////////////////////////////////////
    /**
     * Simplify Function Declaration With If Statement
     */
    ////////////////////////////////////
    fun simplifyFuncWithIf(count: Int): String = if (count == 10) "Sayı 10' dur" else if (count == 5) "Sayı 10 Değildir.." else ""

    ////////////////////////////////////
    /**
     * Simplify Function Declaration With When Statement
     */
    ////////////////////////////////////
    fun simplifyFuncWithWhen(count: Int): String = when {
        count == 10 -> "Sayı 10' dur"
        else -> "Sayı 10 değildir.."
    }

    ////////////////////////////////////
    /**
     * Anonymus Function Declaration
     */
    ////////////////////////////////////
    val valOfString: (String) -> Int = { strOfValue ->

        strOfValue.length * 3
    }

    ////////////////////////////////////
    /**
     * High-Order Function Declaration
     */
    ////////////////////////////////////
    fun highOrderFunc(str: String, mapper: (String) -> Int): Int {
        val newStr = str + " Denem"
        return mapper(newStr)
    }
}
