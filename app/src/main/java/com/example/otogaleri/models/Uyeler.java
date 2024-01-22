package com.example.otogaleri.models;

public class Uyeler {
    private String id;
    private String kadi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKadi() {
        return kadi;
    }

    public void setKadi(String kadi) {
        this.kadi = kadi;
    }

    @Override
    public String toString() {
        return "Uyeler{" +
                "id='" + id + '\'' +
                ", kadi='" + kadi + '\'' +
                '}';
    }


}
