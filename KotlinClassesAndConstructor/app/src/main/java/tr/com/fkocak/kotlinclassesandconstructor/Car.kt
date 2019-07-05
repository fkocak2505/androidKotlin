package tr.com.fkocak.kotlinclassesandconstructor

class Car {

    private var modelName : String = ""
    private var modelYear : Int = 0

    constructor(modelName: String, modelYear: Int) {
        this.modelName = modelName
        this.modelYear = modelYear
    }

    constructor(modelName: String) {
        this.modelName = modelName
    }


    init {
        this.modelName = modelName
        this.modelYear = modelYear
    }

    fun getModelName() : String {
       return modelName
    }

    fun getModelYear() : Int {
        return modelYear
    }

    fun setModelName(strOfModelName: String) : Unit {
        this.modelName = strOfModelName
    }

    fun seteModeleYear(modelYearOfInt: Int) : Unit {
        this.modelYear = modelYearOfInt
    }




}