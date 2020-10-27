package com.musicore.nestedrecyclerview

import java.util.*

object ParentDataFactory {
    private val random = Random()

    private val titles =
        arrayListOf("Now Playing", "Classic", "Comedy", "Thriller", "Action", "Horror", "TV Series")
    private val types = arrayListOf("item3", "googleAdsTop")

    private fun randomTitle(): String {
        /*val index = random.nextInt(titles.size)
        return titles[index]*/
        return "Fatih Koçak"
    }

    private fun randomType(): String {
        val index = random.nextInt(types.size)
        return types[index]
    }

    private fun randomChildren(): List<ChildModel> {
        return ChildDataFactory.getChildren(20)
    }

    fun getParents(count: Int): List<ParentModel> {
        val parents = mutableListOf<ParentModel>()

        val parent = ParentModel(randomTitle(), "googleAdsTop", randomChildren(), false)
        parents.add(parent)

        val parent1 = ParentModel(randomTitle(), "item3", randomChildren())
        parents.add(parent1)

        val parent2 = ParentModel(randomTitle(), "item1", randomChildren())
        parents.add(parent2)

        val parent3 = ParentModel(randomTitle(), "item2", randomChildren())
        parents.add(parent3)

        val parent4 = ParentModel(randomTitle(), "item3", randomChildren())
        parents.add(parent4)

        val parent5 = ParentModel(randomTitle(), "item1", randomChildren())
        parents.add(parent5)

        val parent6 = ParentModel(randomTitle(), "item3", randomChildren())
        parents.add(parent6)

        val parent7 = ParentModel(randomTitle(), "item3", randomChildren())
        parents.add(parent7)

        val parent8 = ParentModel(randomTitle(), "item1", randomChildren())
        parents.add(parent8)

        val parent9 = ParentModel(randomTitle(), "googleAdsTop", randomChildren(), false)
        parents.add(parent9)

        val parent10 = ParentModel(randomTitle(), "item2", randomChildren())
        parents.add(parent10)

        val parent11 = ParentModel(randomTitle(), "item3", randomChildren())
        parents.add(parent11)

        val aa = ParentModel(randomTitle(), "item2", randomChildren())
        parents.add(aa)

        val ww = ParentModel(randomTitle(), "item3", randomChildren())
        parents.add(ww)

        val ff = ParentModel(randomTitle(), "item2", randomChildren())
        parents.add(ff)

        val awf = ParentModel(randomTitle(), "item2", randomChildren())
        parents.add(awf)

        val ggg = ParentModel(randomTitle(), "item3", randomChildren())
        parents.add(ggg)

        val aaw = ParentModel(randomTitle(), "item2", randomChildren())
        parents.add(aaw)

        val fwaa = ParentModel(randomTitle(), "item2", randomChildren())
        parents.add(fwaa)

        val ggaaa = ParentModel(randomTitle(), "item3", randomChildren())
        parents.add(ggaaa)

        return parents
    }
}