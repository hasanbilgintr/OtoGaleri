package com.example.otogaleri.models;

public class IlanVer {
    private String resultmessage;
    private int uyeId;
    private String ilanId;

    public void setresultmessage(String resultmessage) {
        this.resultmessage = resultmessage;
    }

    public String getresultmessage() {
        return resultmessage;
    }

    public void setUyeId(int uyeId) {
        this.uyeId = uyeId;
    }

    public int getUyeId() {
        return uyeId;
    }

    public void setIlanId(String ilanId) {
        this.ilanId = ilanId;
    }

    public String getIlanId() {
        return ilanId;
    }

    @Override
    public String toString() {
        return
                "IlanVer{" +
                        "resultmessage = '" + resultmessage + '\'' +
                        ",uye_id = '" + uyeId + '\'' +
                        ",ilan_id = '" + ilanId + '\'' +
                        "}";
    }

}
