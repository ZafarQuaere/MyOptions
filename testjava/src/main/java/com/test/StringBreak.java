package com.test;

/**
 * Created by zafar.imam on 24-08-2016.
 */
public class StringBreak {
    public static String[] steps;
    public static StringBuilder stepss;

    public static void main(String args[]) {
        String myStr = "Visit www.optiontown.com and select Extra Baggage Option (XBo).##Enter your booking details (airline, PNR or booking reference, passenger last name and email) to retrieve your booking. If you don't have your PNR handy, use the 'Advanced Search' to retrieve your booking with your flight details.##Select desired Extra Baggage on any one or more flights in your booking.##Pay a small Sign-up price and a nominal XBo Price (in this way enjoying up to 75% savings compared to regular Extra Baggage price).##Review and make the payment.##You will be notified via email about your Extra Baggage status within 12 hours. If you are not assigned Extra Baggage, the XBo price will be fully and automatically refunded to you within five business days";
        step2Upgrade(myStr);
        System.out.println("\n\n\n");
        System.out.print(stepss.toString());
    }

    private static void step2Upgrade(String label) {
        stepss = new StringBuilder();
        steps = label.split("##");

        for (int i = 0; i < steps.length; i++) {

            stepss.append("\n• ").append(steps[i]);
            if (i == 1) {
                System.out.print(stepss.toString());
            } else {

            }
        }


    }

}
