package com.optiontown.app.view.fragment.testmonial;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.optiontown.R;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.session.SessionIdData;
import com.optiontown.app.model.session.Testimonial;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amit on 12-07-2016.
 */
public class TestimonialFragment extends BaseFragment
{
    private View view;
    private AppSharedPrefs sp;
    InternationalizeData localization;
    private OTTextView txtWriteTestimonial;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_testimonial, container, false);

        //initialise shared prefs manager
        sp = AppSharedPrefs.getInstance(getActivity());
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        try {
             localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        getUIReference();

        //updateUI();

        return view;

    }

    @Override
    public void setSharedElementReturnTransition(Object transition) {
        super.setSharedElementReturnTransition(transition);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void getUIReference()
    {
        //OTTextView txtTestimonialTitle = (OTTextView) view.findViewById(R.id.txtTestimonialTitle);


        txtWriteTestimonial = (OTTextView) view.findViewById(R.id.txtWriteTestimonial);
        txtWriteTestimonial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Utils.showToast(getActivity(), getString(R.string.string_write_a_testimonial));

                Utils.moveToFragment(getActivity(),new WriteTestimonialFragment(), null,0);
            }
        });

        WebView webView = (WebView) view.findViewById(R.id.webViewTestimonialDescription);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        float fontSize = getResources().getDimension(R.dimen.size_font_6);
        webSettings.setDefaultFontSize((int)fontSize);

        try
        {
            String baseURL = "";
            JSONObject json = new JSONObject(Utils.getSessionData(getActivity()));
            SessionIdData data = (ParseManager.getInstance().fromJSON(json, SessionIdData.class));
            Utils.DEBUG("Testimonial json : " + json.getString("Testimonial"));

            String title = getString(R.string.string_look_what_our_customers_have_to_say_about_us);
            try {
                title = localization.getLABLMTPTestimonialInstructionMessage();
            }catch(Exception e){

            }

            //add title of the testimonial
            StringBuilder buffer = new StringBuilder("<p align=\"center\" style=\"font-size:20px; font-weight: bold\">" + title + "</p>");
            ArrayList<Testimonial> testimonialList = data.getTestimonial();
            for (int index = 0; index < testimonialList.size(); index++)
            {
                /*//certificate issue with test server, so use 'http' instead 'https'
                if(getString(R.string.URL_BASE).contains("192.168.64.10"))
                {
                    baseURL = "http://192.168.64.10";
                }
                else
                {
                    baseURL = getString(R.string.URL_BASE);
                }*/
               // buffer.append("<p font-size:5px; font-weight: normal\">-" +testimonialList.get(index).getText().replace("../..", "https://www.optiontown.com"));
                buffer.append(testimonialList.get(index).getText().replace("../..", "https://www.optiontown.com"));

                buffer.append("<p align=\"right\">-" + testimonialList.get(index).getLastName() + ", " + testimonialList.get(index).getCountry() + "</p>");


                buffer.append("<hr>");

                Utils.DEBUG("testimonial : " + testimonialList.get(index).getText().replace("../..", baseURL));

            }

            webView.loadData(buffer.toString(), "text/html; charset=utf-8", "UTF-8");
        }
        catch (JSONException e)
        {
            Utils.DEBUG("Error while parsing json : " + e.toString());
        }

        localiseUI();
    }

    private void localiseUI() {

        ((OTTextView) view.findViewById(R.id.txtTestimonials)).setText(localization.getLABLMTPTestimonialPageHeadingLabel());
        txtWriteTestimonial.setText(localization.getLABLMTPWriteTestimonialLabel());
    }
}
