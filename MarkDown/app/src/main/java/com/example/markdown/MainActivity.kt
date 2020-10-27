package com.example.markdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.noties.markwon.Markwon
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Markwon.create(applicationContext).setMarkdown(aaaa, "49 senelik ömrü içinde 30 senelik hükümdarlık sürerek Osmanlı İmparatorluğu’nun en büyük padişahı olmayı başarmış, Avrupa’da da Grand Turco \\(Büyük Türk\\) adıyla anılan Fatih Sultan Mehmed, son zamanlarda hakkında yanlış bilinenlerle gündeme geliyor\\. 1453 tarihinde dünyayı değiştiren bir Osmanlı padişahının Hıristiyanlığının araştırılması ve ’’Müslüman mı öldü?’’ sorularıyla anılması tarihin ilgilenmemiz gereken yüzü müdür? Biz, gerçeği kendine göre yazıp yorumlayanları bir kenara bırakıp itibar edilmesi gereken kaynakları konuşmaya, özellikle de Fatih’in bugünün dünyasında dahi benzeri bulunmaz entelektüel kişiliğini öğrenmeye çalışacağız\\.")

    }
}
