package com.example.otogaleri.models;


public class Activate {

    private String resultmessage;
    private String uyeid;
    private String uyekullaniciadi;


    public String getUyekullaniciadi() {
        return uyekullaniciadi;
    }

    public void setUyekullaniciadi(String uyekullaniciadi) {
        this.uyekullaniciadi = uyekullaniciadi;
    }

    public String getResultmessage() {
        return resultmessage;
    }

    public void setResultmessage(String resultmessage) {
        this.resultmessage = resultmessage;
    }

    public String getUyeid() {
        return uyeid;
    }

    public void setUyeid(String uyeid) {
        this.uyeid = uyeid;
    }

    @Override
    public String toString() {
        return "Activate{" +
                "resultmessage='" + resultmessage + '\'' +
                ", uyeid='" + uyeid + '\'' +
                ", uyekullaniciadi='" + uyekullaniciadi + '\'' +
                '}';
    }

}
