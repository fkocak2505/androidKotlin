package tr.com.fkocak.kotlinclassesandconstructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val car = Car("Mercedes", 2018)

        car.setModelName("Fatih")
        car.seteModeleYear(2020)

        Toast.makeText(applicationContext, car.getModelName() + " - " + car.getModelYear(), Toast.LENGTH_LONG).show()

        val person = Person("Fatih", "Koçak", 26)
        Toast.makeText(
            applicationContext,
            person.name + " - " + person.surName + " - " + person.age,
            Toast.LENGTH_SHORT
        ).show()
    }
}
