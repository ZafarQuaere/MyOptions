package com.optiontown.app.utils;

import android.app.IntentService;
import android.content.Intent;

import com.android.volley.toolbox.ImageLoader;
import com.optiontown.R;
import com.optiontown.app.model.session.CountryList;
import com.optiontown.app.model.session.CountryListAPI;
import com.optiontown.app.parser.ParseManager;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amit on 10-08-2016.
 */
public class DownloadIntentService extends IntentService
{
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    public DownloadIntentService() {
        super("");
    }
    public DownloadIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Utils.DEBUG("DownloadIntentService >> onHandleIntent() called");

        if(intent == null)
        {
            return;
        }
        try
        {
            CountryListAPI countryListAPI = ParseManager.getInstance().fromJSON(new JSONObject(intent.getStringExtra(getString(R.string.key_country_data))), CountryListAPI.class);
            final ArrayList<CountryList> countryList = countryListAPI.getCountryList();

            for (int index = 0; index < countryList.size(); index++)
            {
                Utils.downloadAndSaveImage(countryList.get(index).getCountryLogo());
            }
        }
        catch (Exception e)
        {
            Utils.ERROR("DownloadIntentService >> onHandleIntent() > Error : " + e.toString());
        }

    }

}
