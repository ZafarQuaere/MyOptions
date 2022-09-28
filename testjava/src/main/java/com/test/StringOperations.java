package com.test;

/**
 * Created by zafar.imam on 29-07-2017.
 */

public class StringOperations {

    public static void main(String arg[]){
        String url = "https://rodmandev.streaming.mediaservices.windows.net/1a165454-8464-4338-a4bb-8c94fa56980e/f5f4b6ab-5024-4753-a70e-a6ed1d8e.ism/manifest(encryption=cbc,format=mpd-time-csf)";
        System.out.println("Existing url: "+url);
        updateUrl(url);
    }

    private static void updateUrl(String url) {
        String exactUrl = url.replace("format=mpd-time-csf","format=m3u8-aapl");
        System.out.println("Updated Url: "+exactUrl);
    }
}
