package com.example.recyclerviewwithwebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: EntriesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        entriesRecyclerView.setHasFixedSize(true)
        entriesRecyclerView.layoutManager = LinearLayoutManager(this)

        setEntriesData()
    }

    private fun setEntriesData() {
        setEntriesDataAdapter(addAllEntriesData())
    }

    private fun setEntriesDataAdapter(entriesData: MutableList<EntriesModel>) {
        mAdapter = EntriesRecyclerViewAdapter(applicationContext, entriesData) {

        }

        entriesRecyclerView.adapter = mAdapter

        articleDetailTitle.setOnClickListener{
            val newValue = "Updated Value"
            val updatedIndex = 0
            entriesData[updatedIndex] =
                EntriesModel("text","1", ImageOfArticle(""), EntriesData(newValue,null,null))
            mAdapter.notifyItemChanged(updatedIndex)
        }


    }


    private fun addAllEntriesData(): MutableList<EntriesModel> {
        val listOfEntriesData: MutableList<EntriesModel> = mutableListOf()


        listOfEntriesData.add(
            EntriesModel(
                "text",
                "1",
                ImageOfArticle(""),
                EntriesData("49 senelik ömrü içinde 30 senelik hükümdarlık sürerek Osmanlı İmparatorluğu’nun en büyük padişahı olmayı başarmış, Avrupa’da da Grand Turco \\(Büyük Türk\\) adıyla anılan Fatih Sultan Mehmed, son zamanlarda hakkında yanlış bilinenlerle gündeme geliyor\\. 1453 tarihinde dünyayı değiştiren bir Osmanlı padişahının Hıristiyanlığının araştırılması ve ’’Müslüman mı öldü?’’ sorularıyla anılması tarihin ilgilenmemiz gereken yüzü müdür? Biz, gerçeği kendine göre yazıp yorumlayanları bir kenara bırakıp itibar edilmesi gereken kaynakları konuşmaya, özellikle de Fatih’in bugünün dünyasında dahi benzeri bulunmaz entelektüel kişiliğini öğrenmeye çalışacağız",null,null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "image",
                "2",
                ImageOfArticle("https://img-s1.onedio.com/id-5a61983d837f472d108f78bc/rev-0/w-736/h-552/f-jpg/s-8f2ad6896d77f425640d8750086adfb6a5c48d22.jpg"),
                EntriesData("", null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "text",
                "3",
                ImageOfArticle(""),
                EntriesData("Tarihçi Prof. Dr. İlber Ortaylı’ya göre 'II. Mehmed tarihin en entelektüel ve en renkli şahsiyeti'", null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "video",
                "4",
                ImageOfArticle(""),
                EntriesData(null, "Prof. İlber Ortaylı anlatıyor - Fatih, döneminin en büyük entelektüellerinden biridir.", "<iframe class='video-embed' src='https://www.youtube.com/embed/yG--0AP4j_4' width='100%' height='360' frameborder='0'></iframe>")
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "text",
                "5",
                ImageOfArticle(""),
                EntriesData("Fatih, Arapça ve Farsça yazıp çizebiliyor, İtalya konuşabiliyor, Yunanca biliyor ve bizim bugün bile okumakta zorlandığımız Homeros’un İlyada’sını rahatlıkla okuyabiliyor", null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "image",
                "6",
                ImageOfArticle("https://img-s2.onedio.com/id-5a6199bf27c965770e0a8ba7/rev-0/w-580/h-338/f-jpg/s-c6b5d51dd568761219455a7da903c52fc239a797.jpg"),
                EntriesData("", null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "text",
                "7",
                ImageOfArticle(""),
                EntriesData("Büyük İskender tarihiyle ilgileniyor ve İskender’in fetihlerini anlatan kitaplara sahip", null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "image",
                "8",
                ImageOfArticle("https://img-s2.onedio.com/id-5a619a48de1e3ece0efec951/rev-0/w-936/h-387/f-jpg/s-0a5c8a3c3615e8b1eb4301b95b930a938899910c.jpg"),
                EntriesData("", null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "text",
                "9",
                ImageOfArticle(""),
                EntriesData("Batı dünyasında Doğu hakkında bilinen en ileri bilgi İbranice öğrenmek olabilirken, bizim söylemlerimizle değerini düşürdüğümüz tipik Müslüman portresi 15\\. yüzyılda ‘’Avni’’ mahlasıyla şiirler yazıyor\\", null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "image",
                "10",
                ImageOfArticle("https://img-s1.onedio.com/id-5a619a9f4422c7b70e0feb80/rev-0/w-845/h-404/f-gif/s-f1ea64eebd38d6ad39f243d9012153b2037e73a4.gif"),
                EntriesData("", null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "text",
                "11",
                ImageOfArticle(""),
                EntriesData("Jeoloji profesörü Celal Şengör’ün dediğine göre ise, yalnızca fethedeceği yerlerle ilgilenmeyen Fatih, zamanında ne biliniyorsa onu merak ediyor; coğrafyaya duyduğu ilgi global çapta\\", null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "video",
                "12",
                ImageOfArticle(""),
                EntriesData(null, "Fatih Sultan Mehmet'in büyüklüğünü İlber Ortaylı ve Celal Şengör'den dinleyin.", "<iframe class='video-embed' src='https://www.youtube.com/embed/DHbL1ONdj-o' width='100%' height='360' frameborder='0'></iframe>")
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "text",
                "13",
                ImageOfArticle(""),
                EntriesData("Mustafa Altıoklar’ın Twitter’da ‘’iki dandik taka’’ diyerek küçümsediği İstanbul’un fethi ve dış kaynakların da doğruladığı gemilerin karadan yürütülme tekniği Fatih Sultan Mehmed’in askeri dehasının kanıtı\\.", null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "image",
                "14",
                ImageOfArticle("https://img-s2.onedio.com/id-5a619ff09459e4ff0f0f7f77/rev-0/w-780/h-544/f-jpg/s-a38ffd407799be8c8268152ed0cd45ba55831367.jpg"),
                EntriesData(null, null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "text",
                "15",
                ImageOfArticle(""),
                EntriesData("Fetihler sonrası kitapları yağmalama karalamasının aksine bir kütüphane kurtarıcısı olan Fatih Sultan, sayılamaz bir kitap koleksiyonuna sahip\\! Bilim insanlarınca onaylanan kütüphanesi; öklit \\(geometri\\), ölçüler ve mühendislik, İncil lugatı, Zebur, Kristof Kolomb hayatı, astronomi ve aritmetik, Ezop fablları, tıp kitapları, hayvanlar ve zooloji, tarım üzerine 11 kitaplık Yunanca koleksiyon, Aristo’nun ‘’Oluş ve Bozuluş Üzerine’’ kitabı, askerlik ve taktik, hava tahminleri, tartışma kitapları ve İstanbul arkeolojisi hakkında kitaplardan oluşuyor\\", null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "image",
                "16",
                ImageOfArticle("https://img-s1.onedio.com/id-5a619bebd84162b30e169490/rev-0/w-800/h-600/f-jpg/s-cd91618c71a8152a1e9754ce3dcaff763e6933ea.jpg"),
                EntriesData("", null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "text",
                "17",
                ImageOfArticle(""),
                EntriesData("2018’de de olsak, 1481 yılında vefat etmiş Fatih gibi ileri görüşlü, bilgili ve sanatsever bir lidere dünya tarihinde çok az rastlanılır…", null, null)
            )
        )

        listOfEntriesData.add(
            EntriesModel(
                "image",
                "18",
                ImageOfArticle("https://img-s1.onedio.com/id-5a619c7ea0fda9b20e963ed9/rev-0/w-736/h-972/f-jpg/s-4da19979ec48045bee47919674b5c23ff93f5034.jpg"),
                EntriesData(null, null, null)
            )
        )



        return listOfEntriesData
    }
}
