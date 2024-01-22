package com.example.otogaleri.models;

public class Iller {
    private String Id;
    private String il;
    private String resultMessage;
    private boolean isSelected;
    private String sql;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }


    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "Iller{" +
                "sehirId='" + Id + '\'' +
                ", sehirAdi='" + il + '\'' +
                ", resultMessage='" + resultMessage + '\'' +
                ", isSelected=" + isSelected +
                ", sql='" + sql + '\'' +
                '}';
    }


}
