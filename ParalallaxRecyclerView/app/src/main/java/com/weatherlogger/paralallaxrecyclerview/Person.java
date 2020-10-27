package com.weatherlogger.paralallaxrecyclerview;

public class Person {
    private String name;
    private final String address;
    private int photoId;

    public Person(String name,String address,  int photoId) {
        this.name = name;
        this.address = address;
        this.photoId = photoId;

    }

    public int getPhotoId() {
        return photoId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {return address;}

}