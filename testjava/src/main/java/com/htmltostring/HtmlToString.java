package com.htmltostring;

/**
 * Created by parasmani.sharma on 31/01/2017.
 */

public class HtmlToString {



    public static String htmldata = "<divstyle=\"width:100%;\"><spanstyle=\"font-weight:bold;\">PromoCode\"OPTIONTOWN10\"Terms&Conditions</span><tableclass=\"promotns\"border=\"0\"cellpadding=\"0\"cellspacing=\"0\"><tr><tdalign=\"left\"valign=\"top\"width=\"15\"><strong>a.</strong></td><tdalign=\"left\"valign=\"middle\">FlightPassmustbepurchasedbetween30thJanuary2017and3rdFebruary2017(bothdatesinclusive).</td></tr><tr><tdalign=\"left\"valign=\"top\"><strong>b.</strong></td><tdalign=\"left\"valign=\"middle\">TravelPeriodmustbeonlybetween1Feb2017to15Sep2017.</td></tr><tr><tdalign=\"left\"valign=\"top\"><strong>c.</strong></td><tdalign=\"left\"valign=\"middle\">BlackoutPeriodi.etravelnotvalidfrom15Apr2017to15Jun2017.</td></tr><tr><tdalign=\"left\"valign=\"top\"><strong>d.</strong></td><tdalign=\"left\"valign=\"middle\">TravelvalidonallroutesoperatedbyVistaraexceptDelhi-Mumbai(&viceversa).</td></tr><tr><tdalign=\"left\"valign=\"top\"><strong>e.</strong></td><tdalign=\"left\"valign=\"middle\">Flat10%discountonallcabin.</td></tr><tr><tdalign=\"left\"valign=\"top\"><strong>f.</strong></td><tdalign=\"left\"valign=\"middle\">BookingvalidthroughouttheperiodinaccordancetoFlightpassotherterms/Parameters.</td></tr><tr><tdalign=\"left\"valign=\"top\"><strong>g.</strong></td><tdalign=\"left\"valign=\"middle\">ThisPromotionaloffervalidonbothEconomyandBusinessClassFlightPasses.</td></tr><tr><tdalign=\"left\"valign=\"top\"><strong>h.</strong></td><tdalign=\"left\"valign=\"middle\">ThisPromotionaloffercannotbeclubbedwithanyotherexistingoffer.</td></tr></table></div>";

    public static void main(String argsp[])
    {

        String  data = convert(htmldata);
        System.out.print("String data >>> " +data);


    }

    private static String convert(String htmldata) {

        StringBuilder buffer = new StringBuilder("<divstyle=\"width:100%;\"><spanstyle=\"font-weight:bold;\">" + htmldata + "</span>");
        buffer.append(htmldata.replace("../..", "https://www.optiontown.com"));
        //buffer.append("<p align=\"right\">-" + data + ", " + data + "</p>");
        buffer.append("<hr>");

        System.out.println("DAta  >> > "  + buffer.toString());

        return null;
    }

}
