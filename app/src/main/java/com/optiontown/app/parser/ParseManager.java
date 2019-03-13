package com.optiontown.app.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.Primitives;
import com.optiontown.app.utils.Utils;

import org.json.JSONObject;


/**
 * Class used to parse different json
 */
public class ParseManager
{
    private static ParseManager parseManager = null;

    public static ParseManager getInstance()
    {
        if(parseManager == null)
        {
            parseManager = new ParseManager();
        }

        return parseManager;
    }

    public <T> T fromJSON(JSONObject objJson, Class<T> classOfT)
    {
        if(objJson == null)
        {
            return null;
        }

        Gson gson = (new GsonBuilder()).create();
        try
        {
            Object object = gson.fromJson(objJson.toString(), classOfT);
            return Primitives.wrap(classOfT).cast(object);
        }
        catch (Exception e)
        {
            Utils.ERROR("ParseManager >> fromJSON >> Error while parsing JSON : " + e.toString());
            e.printStackTrace();
        }

        return null;
    }

    public String toJSON(Object obj)
    {
        if(obj == null)
        {
            return null;
        }

        Gson gson = (new GsonBuilder()).create();
        try
        {
            return gson.toJson(obj);
        }
        catch (Exception e)
        {
            Utils.ERROR("ParseManager >> toJSON >> Error while getting JSON from object : " + e.toString());
        }

        return null;
    }


}
