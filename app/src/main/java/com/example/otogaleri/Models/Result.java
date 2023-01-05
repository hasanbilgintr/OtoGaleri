package com.example.otogaleri.models;

public class Result {
    private String resultmessage;
    private Boolean resultdialog;
    private String resulterror;

    private int dogrulamakodu;

    public String getResultmessage() {
        return resultmessage;
    }

    public void setResultmessage(String resultmessage) {
        this.resultmessage = resultmessage;
    }

    public Boolean getResultdialog() {
        return resultdialog;
    }

    public void setResultdialog(Boolean resultdialog) {
        this.resultdialog = resultdialog;
    }

    public String getResulterror() {
        return resulterror;
    }

    public void setResulterror(String resulterror) {
        this.resulterror = resulterror;
    }

    public int getDogrulamakodu() {
        return dogrulamakodu;
    }

    public void setDogrulamakodu(int dogrulamakodu) {
        this.dogrulamakodu = dogrulamakodu;
    }


    public String toStringdogrulamakodu() {
        return "Result{" +
                "resultmessage='" + resultmessage + '\'' +
                ", resultdialog=" + resultdialog +
                ", resulterror='" + resulterror + '\'' +
                ", dogrulamakodu='" + dogrulamakodu + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultmessage='" + resultmessage + '\'' +
                ", resultdialog=" + resultdialog +
                ", resulterror='" + resulterror + '\'' +
                '}';
    }


}
