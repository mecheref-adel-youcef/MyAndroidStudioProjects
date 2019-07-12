package com.example.myfirstapp;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Music implements Serializable {

    private String Label;
    private int idImage;

    public Music(String label, int idImage) {
        this.Label = label;
        this.idImage = idImage;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    @Override
    public String toString() {
        return this.getLabel();
    }

}
