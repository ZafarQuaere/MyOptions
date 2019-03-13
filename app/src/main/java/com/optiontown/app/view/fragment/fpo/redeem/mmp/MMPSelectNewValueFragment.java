package com.optiontown.app.view.fragment.fpo.redeem.mmp;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.MMPParameterAdapter;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.model.redeem.mmp.MMPUserSelectedData;
import com.optiontown.app.model.redeem.mmp.PassSelectedParameterData;
import com.optiontown.app.model.redeem.mmp.SelectedParameterList;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BFragment;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amit on 08-11-2016.
 */
public class MMPSelectNewValueFragment extends BFragment
{
    private View view;
    private MMPUserSelectedData mMPUserSelectedData;
    private RelativeLayout lytMain;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private RelativeLayout lytNew;
    private CardView cardView;
    private RecyclerView recyclerView;
    private OTTextView txtCalculateFee;
    private OTTextView txtChangeType;
    private OTTextView txtExistingLabel;
    private OTTextView txtExisting;
    private OTTextView txtNewValueLabel;
    private OTTextView txtNewValue;
    private OTTextView txtHelpMeChoose;
    private PassSelectedParameterData passSelectedParameterData;
    private OTTextView txtTravelPeriodExisting;
    private OTTextView txtTravelPeriodNew;
    private RelativeLayout lytHelpAndNew;
    private ArrayList<SelectedParameterList> listSelectedUsers = new ArrayList<>();
    private ScrollView scrollView;
    private OTTextView txtSelectedPass;
    private ImageView imgEdit;
    private RelativeLayout lytExisting;
    private LinearLayout lytPassengerInfo;
    private OTTextView txtInfo1;
    private OTTextView txtInfo2;
    private OTTextView txtNoPassenger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Utils.DEBUG("onCreateView called");
        view = inflater.inflate(R.layout.fragment_mmp_select_new_value, container, false);

        mMPUserSelectedData = (MMPUserSelectedData) getArguments().getSerializable(getString(R.string.key_serializable));

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), "", null);
        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
        getUIReference();
        callPassSelectedParameterAPI();
        return view;
    }

    private void getUIReference()
    {
        lytMain = (RelativeLayout) view.findViewById(R.id.lytMain);
        lytMain.setVisibility(View.GONE);
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        txtSelectedPass = (OTTextView) view.findViewById(R.id.txtSelectedPass);
        imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.clearBackstackTillMMPSelectPassFragment(getActivity());
            }
        });
        lytPassengerInfo = (LinearLayout) view.findViewById(R.id.lytPassengerInfo);
        txtInfo1 = (OTTextView) view.findViewById(R.id.txtInfo1);
        txtInfo2 = (OTTextView) view.findViewById(R.id.txtInfo2);

        lytExisting = (RelativeLayout) view.findViewById(R.id.lytExisting);
        lytNew = (RelativeLayout) view.findViewById(R.id.lytNew);
        lytNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(passSelectedParameterData.getSelectedParameterList().size() == 1 &&
                        passSelectedParameterData.getSelectedParameterList().get(0).getErr_NotValid() != null)
                {
                    return;
                }

                cardView.setVisibility(cardView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                txtCalculateFee.setVisibility(cardView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        lytHelpAndNew = (RelativeLayout) view.findViewById(R.id.lytHelpAndNew);

        txtChangeType = (OTTextView) view.findViewById(R.id.txtChangeType);
        txtExistingLabel = (OTTextView) view.findViewById(R.id.txtExistingLabel);
        txtExisting = (OTTextView) view.findViewById(R.id.txtExisting);
        txtNewValueLabel = (OTTextView) view.findViewById(R.id.txtNewValueLabel);
        txtNewValue = (OTTextView) view.findViewById(R.id.txtNewValue);
        txtHelpMeChoose = (OTTextView) view.findViewById(R.id.txtHelpMeChoose);

        txtTravelPeriodExisting = (OTTextView) view.findViewById(R.id.txtTravelPeriodExisting);
        txtTravelPeriodNew = (OTTextView) view.findViewById(R.id.txtTravelPeriodNew);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);




        txtCalculateFee = (OTTextView) view.findViewById(R.id.txtCalculateFee);
        txtCalculateFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listSelectedUsers.isEmpty() && mMPUserSelectedData.getPassChangeType().equals("8"))
                {
                    //first clear all previous error message view
                    lytErrorMessage.removeAllViews();
                    lytError.setVisibility(View.VISIBLE);

                    //show error message
                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), "Please select replace User."));
                    scrollView.pageScroll(View.FOCUS_UP);

                }
                else
                {
                    lytErrorMessage.removeAllViews();
                    lytError.setVisibility(View.GONE);

                    Utils.moveToFragment(getActivity(), new MMPCalculateFeeFragment(), mMPUserSelectedData, 0);
                }
            }
        });
        cardView = (CardView) view.findViewById(R.id.cardView);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        txtNoPassenger = (OTTextView) view.findViewById(R.id.txtNoPassenger);
    }

    private void updateUI()
    {
        txtSelectedPass.setText(mMPUserSelectedData.getSelectedPassLabel().replace("#", ":"));
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), passSelectedParameterData.getMangemypassrefreshdata().getLABLFPOChangeMyFlightPassLabel(), null);


        if(passSelectedParameterData.getSelectedParameterList().size() == 1 &&
                passSelectedParameterData.getSelectedParameterList().get(0).getErr_NotValid() != null)
        {
            txtNewValue.setText(passSelectedParameterData.getSelectedParameterList().get(0).getErr_NotValid());
            txtNewValue.setTextColor(getActivity().getResources().getColor(R.color.color_font_red));
            txtCalculateFee.setVisibility(View.GONE);
        }
        else
        {
            if(mMPUserSelectedData.getSelectedZone() == null && !passSelectedParameterData.getSelectedParameterList().isEmpty())
            {
                mMPUserSelectedData.setSelectedZone(passSelectedParameterData.getSelectedParameterList().get(0).getID());
                txtNewValue.setText(passSelectedParameterData.getSelectedParameterList().get(0).getLabel());
                txtNewValue.setTextColor(getActivity().getResources().getColor(R.color.color_font_black));
            }
        }

        txtChangeType.setText(passSelectedParameterData.getSelectPassChangeLabel());
        txtExistingLabel.setText(passSelectedParameterData.getExistingValue());
        txtExisting.setText(passSelectedParameterData.getSelectedParameter());
        txtNewValueLabel.setText(passSelectedParameterData.getNewValue());


        txtCalculateFee.setText(passSelectedParameterData.getCalculateLabel());

        if(mMPUserSelectedData.getPassChangeType().equals("3"))//travel period
        {
            txtExisting.setText(passSelectedParameterData.getSelectedParameter() + " " + passSelectedParameterData.getMonthsLbl());
            txtTravelPeriodExisting.setText(passSelectedParameterData.getCurrentdateNow() + " to " + passSelectedParameterData.getCurrentdateAfter());
            txtTravelPeriodNew.setText(passSelectedParameterData.getNewdateNow() + " to " + passSelectedParameterData.getNewdateAfter());

            if(passSelectedParameterData.getSelectedParameterList().size() == 1 &&
                    passSelectedParameterData.getSelectedParameterList().get(0).getErr_NotValid() != null)
            {
                txtTravelPeriodNew.setText("");
            }
        }
        else
        {
            txtTravelPeriodExisting.setText("");
            txtTravelPeriodNew.setText("");
        }

        if(mMPUserSelectedData.getPassChangeType().equals("8"))//replace user
        {
            cardView.setVisibility(View.VISIBLE);
            lytHelpAndNew.setVisibility(View.GONE);
            lytExisting.setVisibility(View.GONE);
            lytPassengerInfo.setVisibility(View.VISIBLE);
            String[] split = ((passSelectedParameterData.getSelectedParameter().replace("<b>", "")).replace("</b>", "")).split("<br\\/>");
            txtInfo1.setText(split[0]);
            txtInfo2.setText(split[1]);

            if(passSelectedParameterData.getSelectedParameterList().isEmpty())
            {
                txtNoPassenger.setVisibility(View.VISIBLE);
                txtNoPassenger.setText(passSelectedParameterData.getLABL_No_Active_User_Label());
                recyclerView.setVisibility(View.GONE);
                txtCalculateFee.setVisibility(View.GONE);
            }


            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params1.addRule(RelativeLayout.BELOW, R.id.cardView);
            params1.setMargins((int) Utils.conertDpToPixel(getActivity(), 20), (int) Utils.conertDpToPixel(getActivity(), 50), (int) Utils.conertDpToPixel(getActivity(), 20), 0);
            txtCalculateFee.setLayoutParams(params1);
        }
        else
        {
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params2.addRule(RelativeLayout.BELOW, R.id.lytHelpAndNew);
            params2.setMargins((int) Utils.conertDpToPixel(getActivity(), 20), 0, (int) Utils.conertDpToPixel(getActivity(), 20), 0);
            cardView.setLayoutParams(params2);
        }

        NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        MMPParameterAdapter adapter = new MMPParameterAdapter(getActivity(), Integer.parseInt(mMPUserSelectedData.getPassChangeType()), passSelectedParameterData.getSelectedParameterList(), new MMPParameterAdapter.RecyclerViewHolderClicks()
        {
            @Override
            public void onClickRecyclerItemDetail(View v, Object data)
            {
                lytErrorMessage.removeAllViews();
                lytError.setVisibility(View.GONE);

                listSelectedUsers.clear();
                listSelectedUsers.addAll((ArrayList<SelectedParameterList>) data);

                if(mMPUserSelectedData.getPassChangeType().equals("8"))//replace user
                {
                    ArrayList<String> listUsers = new ArrayList<>();
                    for (int index = 0; index < listSelectedUsers.size(); index++) {
                        listUsers.add(listSelectedUsers.get(index).getID());
                    }

                    mMPUserSelectedData.setUserDetails(listUsers);
                }
                else
                {
                    cardView.setVisibility(View.GONE);
                    txtNewValue.setText(listSelectedUsers.get(0).getLabel());
                    mMPUserSelectedData.setSelectedZone(listSelectedUsers.get(0).getID());

                    if(mMPUserSelectedData.getPassChangeType().equals("3"))//travel period
                    {
                        callPassSelectedParameterAPI();
                    }
                }
                txtCalculateFee.setVisibility(View.VISIBLE);

            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        lytMain.setVisibility(View.VISIBLE);
    }

    private void callPassSelectedParameterAPI()
    {
        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_PassSelectedParameter);
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("selectedpassid", mMPUserSelectedData.getSelectedpassid());
            requestObject.put("PassChangeType", mMPUserSelectedData.getPassChangeType());
            requestObject.put("selectedZone", mMPUserSelectedData.getSelectedZone());
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
                        Utils.DEBUG("Response : " + response.toString());
                        passSelectedParameterData = ParseManager.getInstance().fromJSON(response, PassSelectedParameterData.class);
                        updateUI();
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

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }




    @Override
    public void onBackEventPre() {

    }

    @Override
    public void onBackEventPost() {

    }

    @Override
    public void onFocusEvent() {

    }
}
