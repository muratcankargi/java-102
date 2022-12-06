package com.example.tourismagency.Models;

public enum RoomType {

    YATAK_SAYISI,
    TELEVIZYON("VAR"),
    MINIBAR("VAR"),
    OYUN_KONSOLU("YOK"),
    METREKARE,
    KASA,
    PROJEKSIYON;

    private String label;

    RoomType() { }

    RoomType(String label) {
        this.label = label;
    }


}