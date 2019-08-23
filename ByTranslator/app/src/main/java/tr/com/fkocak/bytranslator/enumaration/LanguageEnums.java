package tr.com.fkocak.bytranslator.enumaration;

import tr.com.fkocak.bytranslator.model.LanguageModel;

/**
 * Created by fatihkocak on 1.10.2018.
 */

public enum LanguageEnums {
    en("İngilizce","en"),
    tr("Türkçe","tr"),
    fr("Fransızca","fr"),
    ja("Japonca","ja"),
    de("Almanca","de"),
    pl("Lehçe","pl"),


    EN("EN","İngilizce"),
    TR("TR","Türkçe"),
    FR("FR","Fransızca"),
    JA("JA","Japonca"),
    DE("DE","Almanca"),
    PL("PL","Lehçe");


    private final String strOfLanguage;
    private final String keyOfLanguage;

    private LanguageEnums(String strOfLanguage, String keyOfLanguage){
        this.strOfLanguage = strOfLanguage;
        this.keyOfLanguage = keyOfLanguage;
    }

    public LanguageModel getLanguage(){
        return new LanguageModel(keyOfLanguage,strOfLanguage);
    }

}
