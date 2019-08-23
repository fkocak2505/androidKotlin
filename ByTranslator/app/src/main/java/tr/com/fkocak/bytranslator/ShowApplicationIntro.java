package tr.com.fkocak.bytranslator;

import android.content.Intent;
import android.os.Bundle;


import com.vdurmont.emoji.EmojiParser;

import java.util.ArrayList;
import java.util.List;

import tr.com.fkocak.bytranslator.libintro.AhoyOnboarderActivityCustom;
import tr.com.fkocak.bytranslator.libintro.AhoyOnboarderCardCustom;

public class ShowApplicationIntro extends AhoyOnboarderActivityCustom {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String kaydettigimKelimeler = "Çevirilerde kullandığınız İNGİLİZCE \nkelimeleri kaydeder :floppy_disk: Tabi eğer Ayarlar ekranından gerekli izni verdiyseniz :relieved::relieved:";
        String ayarlar = "'Ben sadece çeviri yapıcam banane kelime kaydetten bildirimden :unamused::unamused:' \ndiyorsanız bizimde iyi sen bilirsin kapatırız o özellikleri dediğimiz yerdir kendileri :triumph::grimacing:";
        String kelimeBul = "İngilizcede nadir bulunan kelimelerle adam asmaca :hovering_man: oyunu oynayın. Unutmayın sadece :seven: Hakkınız var.";
        String ceviri = ":strawberry: Çilek yiyelim :banana: muz tadı gelsin' özelliğide mevcuttur :heart_eyes: Yani kısacası başka uygulamalarınızı kapatmayın çeviri ekran üzerinde dilediğiniz yerde açık kalsın :blush::sunglasses:";
        String snapTrans = ":camera: Resimdeki yazının ne anlama geldiğini mi merak ediyorsunuz? İşte size kameradan çeviri özelliği :sunglasses: ";

        AhoyOnboarderCardCustom ahoyOnboarderCard1 = new AhoyOnboarderCardCustom("Kaydettiğim Kelimeler", EmojiParser.parseToUnicode(kaydettigimKelimeler), R.mipmap.savewordsc);
        AhoyOnboarderCardCustom ahoyOnboarderCard2 = new AhoyOnboarderCardCustom("Ayarlar", EmojiParser.parseToUnicode(ayarlar), R.mipmap.settingsc);
        AhoyOnboarderCardCustom ahoyOnboarderCard3 = new AhoyOnboarderCardCustom("Kelime Bul", EmojiParser.parseToUnicode(kelimeBul), R.mipmap.gamesc);
        AhoyOnboarderCardCustom ahoyOnboarderCard4 = new AhoyOnboarderCardCustom("Çeviri", EmojiParser.parseToUnicode(ceviri), R.mipmap.translatesc);
        AhoyOnboarderCardCustom ahoyOnboarderCard5 = new AhoyOnboarderCardCustom("SnapTrans", EmojiParser.parseToUnicode(snapTrans), R.mipmap.snaptranssc);

        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard4.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard5.setBackgroundColor(R.color.black_transparent);

        List<AhoyOnboarderCardCustom> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        pages.add(ahoyOnboarderCard4);
        pages.add(ahoyOnboarderCard5);

        for (AhoyOnboarderCardCustom page : pages) {
            page.setTitleColor(R.color.introTitleAndDesc);
            page.setDescriptionColor(R.color.introTitleAndDesc);
        }

        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        setGradientBackground();
        //setGradientBackground(R.drawable.download);
        //setImageBackground(R.drawable.download);


        //Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        //setFont(face);

        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.white);

        setOnboardPages(pages);

    }

    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }


}

