package com.string;

public class RemoveCharTest {

    public static void main(String arg[]){
        String mainUrl = "https://d2iisqli8fpdjs.cloudfront.net.mpd";
        System.out.println("mainUrl: "+mainUrl);
        String updatedUrl = mainUrl+"#"+getRandomNumber();
        String updatedUrl1 = mainUrl+"@"+getRandomNumber();
        System.out.println("updatedUrl: "+updatedUrl);
        System.out.println("updatedUrl1: "+updatedUrl1);
        String actualUrl = getActualUrl(updatedUrl);
        String actualUrl1 = getActualUrl(updatedUrl1);
        System.out.println("Actual url "+actualUrl);
        System.out.println("Actual url1 "+actualUrl1);
    }

    private static String getActualUrl(String updatedUrl) {
        String url = "";
        if (updatedUrl.contains("#")){
            url = updatedUrl.split("#")[0];
        } else if (updatedUrl.contains("@")){
            url = updatedUrl.split("@")[0];
        } else
            url = updatedUrl;
        return url;
    }

    public static int getRandomNumber(){
        return (int) (Math.random()*100);
    }
}
