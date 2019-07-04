package tr.com.fkocak.kotlinexpressionstatemeent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val statment = 40

        //////////////////////////////////////////
        /**
         * If statement using..
         */
        //////////////////////////////////////////
        var newStatement : String = if (statment == 40) "40' tır" else "40 değildir."
        Toast.makeText(applicationContext, newStatement, Toast.LENGTH_SHORT).show()


        //////////////////////////////////////////
        /**
         * When statement using..
         */
        //////////////////////////////////////////
        var newStatement1 = when {
            statment == 40 -> "40' tır"
            else -> "40 değildir."
        }
        Toast.makeText(applicationContext, newStatement1, Toast.LENGTH_SHORT).show()

    }
}
