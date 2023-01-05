package com.example.otogaleri.models;

public class Mahalleler {

    private String id;
    private String mahalle;
    private String resultMessage;
    private boolean isSelected;
    private String sql;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMahalle() {
        return mahalle;
    }

    public void setMahalle(String mahalle) {
        this.mahalle = mahalle;
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
        return "Mahalleler{" +
                "id='" + id + '\'' +
                ", mahalle='" + mahalle + '\'' +
                ", resultMessage='" + resultMessage + '\'' +
                ", isSelected=" + isSelected +
                ", sql='" + sql + '\'' +
                '}';
    }



}
