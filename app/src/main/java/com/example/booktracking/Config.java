package com.example.booktracking;

public class Config {
//    static String baseUrl = "http://192.168.1.248:8000/";
static String baseUrl = "http://143.89.130.156/";

    public static String getApiUrl() {
        return baseUrl + "api/";
    }

    public static String getImageUrl(String image) {
        if (image == null) {
            return baseUrl + "storage/uploads/default.png";
        }
        return baseUrl + "storage/" + image;
    }
}
