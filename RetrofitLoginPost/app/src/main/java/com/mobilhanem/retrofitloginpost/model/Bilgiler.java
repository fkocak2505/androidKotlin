package com.mobilhanem.retrofitloginpost.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by alper on 13/02/17.
 */

public class Bilgiler implements Serializable {
    @SerializedName("soforID")
    private String soforID;
    @SerializedName("firmaID")
    private String firmaID;
    @SerializedName("adSoyad")
    private String adSoyad;
    @SerializedName("tc")
    private String tc;
    @SerializedName("telefon")
    private String telefon;
    @SerializedName("tecrubeYil")
    private String tecrubeYil;
    @SerializedName("ehliyetSinifi")
    private String ehliyetSinifi;
    @SerializedName("kullaniciAdi")
    private String kullaniciAdi;
    @SerializedName("firmaUnvan")
    private String firmaUnvan;
    @SerializedName("isfirmaTelefonlem")
    private String firmaTelefon;
    @SerializedName("firmaFax")
    private String firmaFax;
    @SerializedName("islefirmaEpostam")
    private String firmaEposta;
    @SerializedName("firmaKullaniciAdi")
    private String firmaKullaniciAdi;
    @SerializedName("firmaYetkili")
    private String firmaYetkili;
    @SerializedName("hatID")
    private String hatID;
    @SerializedName("hatNo")
    private String hatNo;
    @SerializedName("hatAciklama")
    private String hatAciklama;
    @SerializedName("musteriEposta")
    private String musteriEposta;
    @SerializedName("musteriKullaniciAdi")
    private String musteriKullaniciAdi;

    public String getTecrubeYil() {
        return tecrubeYil;
    }

    public void setTecrubeYil(String tecrubeYil) {
        this.tecrubeYil = tecrubeYil;
    }

    public String getSoforID() {
        return soforID;
    }

    public void setSoforID(String soforID) {
        this.soforID = soforID;
    }

    public String getFirmaID() {
        return firmaID;
    }

    public void setFirmaID(String firmaID) {
        this.firmaID = firmaID;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEhliyetSinifi() {
        return ehliyetSinifi;
    }

    public void setEhliyetSinifi(String ehliyetSinifi) {
        this.ehliyetSinifi = ehliyetSinifi;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getFirmaUnvan() {
        return firmaUnvan;
    }

    public void setFirmaUnvan(String firmaUnvan) {
        this.firmaUnvan = firmaUnvan;
    }

    public String getFirmaTelefon() {
        return firmaTelefon;
    }

    public void setFirmaTelefon(String firmaTelefon) {
        this.firmaTelefon = firmaTelefon;
    }

    public String getFirmaFax() {
        return firmaFax;
    }

    public void setFirmaFax(String firmaFax) {
        this.firmaFax = firmaFax;
    }

    public String getFirmaEposta() {
        return firmaEposta;
    }

    public void setFirmaEposta(String firmaEposta) {
        this.firmaEposta = firmaEposta;
    }

    public String getFirmaKullaniciAdi() {
        return firmaKullaniciAdi;
    }

    public void setFirmaKullaniciAdi(String firmaKullaniciAdi) {
        this.firmaKullaniciAdi = firmaKullaniciAdi;
    }

    public String getFirmaYetkili() {
        return firmaYetkili;
    }

    public void setFirmaYetkili(String firmaYetkili) {
        this.firmaYetkili = firmaYetkili;
    }

    public String getHatID() {
        return hatID;
    }

    public void setHatID(String hatID) {
        this.hatID = hatID;
    }

    public String getHatNo() {
        return hatNo;
    }

    public void setHatNo(String hatNo) {
        this.hatNo = hatNo;
    }

    public String getHatAciklama() {
        return hatAciklama;
    }

    public void setHatAciklama(String hatAciklama) {
        this.hatAciklama = hatAciklama;
    }

    public void setMusteriEpostausteriEposta(String musteriEposta) {
        this.musteriEposta = musteriEposta;
    }
    public String getMusteriEposta() {
        return musteriEposta;
    }
    public String getMusteriKullaniciAdi() {
        return musteriKullaniciAdi;
    }

}
