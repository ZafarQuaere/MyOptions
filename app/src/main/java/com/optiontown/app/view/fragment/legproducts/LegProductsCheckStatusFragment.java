package com.optiontown.app.view.fragment.legproducts;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.legproducthomepage.Home;
import com.optiontown.app.model.legproducthomepage.HomeBannerObject;
import com.optiontown.app.model.legproducthomepage.HomePageData;
import com.optiontown.app.model.legproducthomepage.SearchHelper;
import com.optiontown.app.model.legproducthomepage.StatusFormObject;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.AppVariables;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTEditText;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BFragment;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONObject;

/**
 * home UI
 *
 * @author ravi
 */
public class LegProductsCheckStatusFragment extends BaseFragment implements View.OnClickListener {


    private AppSharedPrefs sp;

    private NetworkImageView imgOTConfirmation, imgLastName;
    private OTEditText edtOTConfirmation, edtLastName;
    AppDialogLoader loader;
    String strOptiontownConfirmation, strLastName;
    View view;
    OTTextView txtSearch, txtCheckStatusTitle;
    private ImageLoader imageLoader;
    Drawable home, faqDrawable;
    LinearLayout lytError,lytErrorMessage;
    private AppSharedPrefs sharedPrefs;
    boolean searchError = false;
    private OTTextView txtOTConfirmationLable;
    private OTTextView txtLastNameLabel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_legproduct_status, container, false);
        Init(view);
        loader = AppDialogLoader.getLoader(getActivity());
        imageLoader = AppController.getInstance().getImageLoader();

        //productName = getArguments().getString(getActivity().getString(R.string.key_serializable));
        callSessionData();

        Utils.updateLegProductBottomBarForFeatures(getActivity(), view, this.getClass().getName());

        sharedPrefs = AppSharedPrefs.getInstance(getActivity());


       //callHomePassBannerAPI();
        return view;
    }

    private void callSessionData() {
        try {

            String session = Utils.getLegProductSessionData(getActivity());
            if (session == null) {
                return;
            }
            JSONObject json = new JSONObject(session);

            Home data = (ParseManager.getInstance().fromJSON(json, Home.class));
            HomePageData mHomePageData = data.getHomePageData();
            StatusFormObject statusFormObj = mHomePageData.getStatusFormObject();

            HomeBannerObject banner = mHomePageData.getHomeBannerObject();

            String statusLable = banner.getLABLTgpStatusTitleLabel();

            Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(),statusLable,null);
          //  Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

            edtOTConfirmation.setHint(statusFormObj.getLABLOptiontownCNLabel());
            edtLastName.setHint(statusFormObj.getLABLLastNameLabel());
            imgOTConfirmation.setImageUrl(statusFormObj.getOptiontown_CN_Image(), imageLoader);
            imgLastName.setImageUrl(statusFormObj.getLastNameImage(), imageLoader);

        } catch (Exception e) {
            Utils.DEBUG("Error while parsing json : " + e.toString());
        }
    }

    public void Init(View view) {
        sp = AppSharedPrefs.getInstance(getActivity());

        imgOTConfirmation = (NetworkImageView) view.findViewById(R.id.imgOTConfirmation);
        imgLastName = (NetworkImageView) view.findViewById(R.id.imgLastName);

        edtOTConfirmation = (OTEditText) view.findViewById(R.id.edtOTConfirmation);
        edtLastName = (OTEditText) view.findViewById(R.id.edtLastName);
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);

        txtOTConfirmationLable = (OTTextView) view.findViewById(R.id.txtOTConfirmationLable);
        txtLastNameLabel = (OTTextView) view.findViewById(R.id.txtLastNameLabel);

        txtSearch = (OTTextView) view.findViewById(R.id.txtSearch);
        txtCheckStatusTitle = (OTTextView) view.findViewById(R.id.txtCheckStatusTitle);
        txtSearch.setOnClickListener(this);

        addTextChangeListeners();
    }

    private void addTextChangeListeners() {
        edtOTConfirmation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtOTConfirmationLable.setVisibility(edtOTConfirmation.getText().length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        edtLastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtLastNameLabel.setVisibility(edtLastName.getText().length()>0?View.VISIBLE:View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public void onClickSearch() {

            SearchHelper mSearchHelper = new SearchHelper();
            mSearchHelper.setCountryId(Utils.getUserSelectedCountryId(getActivity()) +"");
            mSearchHelper.setIsSearchBy("2");
            mSearchHelper.setLanguageId( Utils.getUserSelectedLanguageId(getActivity()) + "");
            mSearchHelper.setTgProductId(sharedPrefs.get(getString(R.string.key_selected_productId)).toString());
            mSearchHelper.setOCN(edtOTConfirmation.getText().toString());
            mSearchHelper.setLastName(edtLastName.getText().toString());

            mSearchHelper.setMarketingAirlineId("");


            switch (sharedPrefs.get(getString(R.string.key_selected_productId)).toString()) {

                case "1":
                    // LegProductReviewFragment
                    Utils.moveToFragment(getActivity(), new LegProductSearchResultFragment(), mSearchHelper, 0);
                    break;
                default:
                    Utils.moveToFragment(getActivity(), new ESoSearchResultFragment(), mSearchHelper, 0);
                    break;

            }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        strOptiontownConfirmation = edtOTConfirmation.getText().toString().trim();
        strLastName = edtLastName.getText().toString().trim();
        switch (id) {
            case R.id.txtSearch:
                if (strOptiontownConfirmation.equalsIgnoreCase("")) {
                    edtOTConfirmation.requestFocus();
                    edtOTConfirmation.setError(getString(R.string.input_mandatory));

                } else if (strOptiontownConfirmation.length() < 6) {
                    edtOTConfirmation.requestFocus();
                    edtOTConfirmation.setError(getString(R.string.enter_valid_confirmation_number));

                } else if (strLastName.equalsIgnoreCase("")) {
                    edtLastName.requestFocus();
                    edtLastName.setError(getString(R.string.input_mandatory));

                } else {

                    onClickSearch();
                }
                break;

        }

    }


    private void callHomePassBannerAPI() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) +getString(R.string.URL_HOME_PASS_BANNER);


        JSONObject requestObject = new JSONObject();
        try {

            requestObject.put("TgProductId", sharedPrefs.get(getString(R.string.key_selected_productId)));
            requestObject.put("CountryId", "239");
            requestObject.put("LanguageId", "1");
            requestObject.put("AirlineId", "978");


        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }
        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response == null) {
                            return;
                        }
                        Utils.DEBUG("onResponse() called : " + response.toString());
                        loader.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    @Override
    public void onBackEventPre() {

    }

    @Override
    public void onFocusEvent() {
        try {
            searchError = (boolean) sharedPrefs.get(getString(R.string.is_search_error));
        }
        catch (Exception e){
            searchError = false;
        }
        if (searchError && AppVariables.searchResult) {
            lytError.setVisibility(View.VISIBLE);
            lytErrorMessage.removeAllViews();
            String errorMsg = (String) sharedPrefs.get(getString(R.string.search_error_msg));
            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(),errorMsg ));
            AppVariables.searchResult = false;
            //  Snackbar.make((RelativeLayout) view.findViewById(R.id.relSearchLayout), error,Snackbar.LENGTH_SHORT).show();
        }
        else {
            // lytError.setVisibility(View.GONE);
        }

    }
}
