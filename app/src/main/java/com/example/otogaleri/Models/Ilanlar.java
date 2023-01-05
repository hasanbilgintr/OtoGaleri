package com.example.otogaleri.models;

public class Ilanlar {
    private String uyeid;
    private String sehirid;
    private String sehiradi;
    private String ilce;
    private String mahalle;
    private String marka;
    private String seri;
    private String model;
    private String yil;
    private String km;
    private String ilantipi;
    private String kimden;
    private String baslik;
    private String aciklama;
    private String motortipi;
    private String motorhacmi;
    private String surat;
    private String yakittipi;
    private String ortalamayakit;
    private String depohacmi;
    private String ucret;
    private String ilanresimid;
    private String resimbaslik;
    private String yol;
    private String resultmessage;
    private String tf;
    private String ilanid;
    private String sayi;


    public String getilanid() {
        return ilanid;
    }

    public void setilanid(String ilanid) {
        this.ilanid = ilanid;
    }

    public String getuyeid() {
        return uyeid;
    }

    public void setuyeid(String uyeid) {
        this.uyeid = uyeid;
    }

    public String getSehirAdi() {
        return sehiradi;
    }

    public void setSehirAdi(String sehiradi) {
        this.sehiradi = sehiradi;
    }

    public String getsehirid() {
        return sehirid;
    }

    public void setsehirid(String sehirid) {
        this.sehirid = sehirid;
    }

    public String getilce() {
        return ilce;
    }

    public void setilce(String ilce) {
        this.ilce = ilce;
    }

    public String getmahalle() {
        return mahalle;
    }

    public void setmahalle(String mahalle) {
        this.mahalle = mahalle;
    }

    public String getmarka() {
        return marka;
    }

    public void setmarka(String marka) {
        this.marka = marka;
    }

    public String getseri() {
        return seri;
    }

    public void setseri(String seri) {
        this.seri = seri;
    }

    public String getmodel() {
        return model;
    }

    public void setmodel(String model) {
        this.model = model;
    }

    public String getyil() {
        return yil;
    }

    public void setyil(String yil) {
        this.yil = yil;
    }

    public String getkm() {
        return km;
    }

    public void setkm(String km) {
        this.km = km;
    }

    public String getilantipi() {
        return ilantipi;
    }

    public void setilantipi(String ilantipi) {
        this.ilantipi = ilantipi;
    }

    public String getkimden() {
        return kimden;
    }

    public void setkimden(String kimden) {
        this.kimden = kimden;
    }

    public String getbaslik() {
        return baslik;
    }

    public void setbaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getaciklama() {
        return aciklama;
    }

    public void setaciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getmotortipi() {
        return motortipi;
    }

    public void setmotortipi(String motortipi) {
        this.motortipi = motortipi;
    }

    public String getmotorhacmi() {
        return motorhacmi;
    }

    public void setmotorhacmi(String motorhacmi) {
        this.motorhacmi = motorhacmi;
    }

    public String getsurat() {
        return surat;
    }

    public void setsurat(String surat) {
        this.surat = surat;
    }

    public String getyakittipi() {
        return yakittipi;
    }

    public void setyakittipi(String yakittipi) {
        this.yakittipi = yakittipi;
    }

    public String getortalamayakit() {
        return ortalamayakit;
    }

    public void setortalamayakit(String ortalamayakit) {
        this.ortalamayakit = ortalamayakit;
    }

    public String getdepohacmi() {
        return depohacmi;
    }

    public void setdepohacmi(String depohacmi) {
        this.depohacmi = depohacmi;
    }

    public String getucret() {
        return ucret;
    }

    public void setucret(String ucret) {
        this.ucret = ucret;
    }

    public String getilanresimid() {
        return ilanresimid;
    }

    public void setilanresimid(String ilanresimid) {
        this.ilanresimid = ilanresimid;
    }


    public String getResimbaslik() {
        return resimbaslik;
    }

    public void setResimbaslik(String resimbaslik) {
        this.resimbaslik = resimbaslik;
    }

    public String getyol() {
        return yol;
    }

    public void setyol(String yol) {
        this.yol = yol;
    }

    public String getresultmessage() {
        return resultmessage;
    }

    public void setresultmessage(String resultmessage) {
        this.resultmessage = resultmessage;
    }

    public String gettf() {
        return tf;
    }

    public void settf(String tf) {
        this.tf = tf;
    }


    public String getSayi() {
        return sayi;
    }

    public void setSayi(String sayi) {
        this.sayi = sayi;
    }

    @Override
    public String toString() {
        return "Ilanlar{" +
                "uyeid='" + uyeid + '\'' +
                ", sehirid='" + sehirid + '\'' +
                ", sehiradi='" + sehiradi + '\'' +
                ", ilce='" + ilce + '\'' +
                ", mahalle='" + mahalle + '\'' +
                ", marka='" + marka + '\'' +
                ", seri='" + seri + '\'' +
                ", model='" + model + '\'' +
                ", yil='" + yil + '\'' +
                ", km='" + km + '\'' +
                ", ilantipi='" + ilantipi + '\'' +
                ", kimden='" + kimden + '\'' +
                ", baslik='" + baslik + '\'' +
                ", aciklama='" + aciklama + '\'' +
                ", motortipi='" + motortipi + '\'' +
                ", motorhacmi='" + motorhacmi + '\'' +
                ", surat='" + surat + '\'' +
                ", yakittipi='" + yakittipi + '\'' +
                ", ortalamayakit='" + ortalamayakit + '\'' +
                ", depohacmi='" + depohacmi + '\'' +
                ", ucret='" + ucret + '\'' +
                ", ilanresimid='" + ilanresimid + '\'' +
                ", resimbaslik='" + resimbaslik + '\'' +
                ", yol='" + yol + '\'' +
                ", resultmessage='" + resultmessage + '\'' +
                ", tf='" + tf + '\'' +
                ", ilanid='" + ilanid + '\'' +
                ", sayi='" + sayi + '\'' +
                '}';
    }


}

