package com.optiontown.app.view.fragment.fpo.redeem;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.adapter.RedeemAddPassengerRecyclerViewAdapter;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.redeem.AddPaxIdentityData;
import com.optiontown.app.model.redeem.ListUsersDetail;
import com.optiontown.app.model.redeem.RedeemSearchResultData;
import com.optiontown.app.model.redeem.UserIdData;
import com.optiontown.app.model.redeem.UsersDetail;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.optiontown.app.utils.Utils.DEBUG;

/**
 * Created by amit on 22-09-2016.
 */
public class RedeemAddPassengerFragment extends BaseFragment
{
    private View view;
    private RedeemSearchResultData redeemSearchResultData;
    private OTTextView txtInformation1;
    private OTTextView txtInformation2;
    private RecyclerView recyclerView;
    private OTTextView txtContinue;
    private ArrayList<UsersDetail> listSelectedUsers = new ArrayList<UsersDetail>();
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private InternationalizeData localization;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Utils.DEBUG("onCreateView called");
        view = inflater.inflate(R.layout.fragment_redeem_add_passenger, container, false);

        redeemSearchResultData = (RedeemSearchResultData) getArguments().getSerializable(getString(R.string.key_serializable));
        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (Exception e) {
            Utils.ERROR("Error while parsing InternationalizeData from shared prefs : " + e.toString());
        }

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), redeemSearchResultData.getAdd_Passenger_Label(), null);
        getUIReference();
        updateUI();
        return view;
    }



    @Override
    public void onBackEventPost() {
        super.onBackEventPost();
        Utils.clearBackstackTillRedeemSearch(getActivity());
        Utils.DEBUG("onBackEventPost() called");
    }

    private void getUIReference()
    {
        lytError = (LinearLayout) view.findViewById(R.id.lytError);
        lytErrorMessage = (LinearLayout) view.findViewById(R.id.lytErrorMessage);
        txtInformation1 = (OTTextView) view.findViewById(R.id.txtInformation1);
        txtInformation2 = (OTTextView) view.findViewById(R.id.txtInformation2);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        txtContinue = (OTTextView) view.findViewById(R.id.txtSave);
        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callRedeemReivewBook();

             }
        });
    }

    private void updateUI()
    {
        Utils.DEBUG("Is_Error : " + redeemSearchResultData.is_Error());
        if(redeemSearchResultData.is_Error())
        {
            //first clear all previos error message view
            lytErrorMessage.removeAllViews();
            lytError.setVisibility(View.VISIBLE);

            //show error message
            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), redeemSearchResultData.getError_Message()));
        }
        else
        {
            lytErrorMessage.removeAllViews();
            lytError.setVisibility(View.GONE);
        }
        txtInformation1.setText(redeemSearchResultData.getSelect_Passengers_Label());
        txtInformation2.setText(redeemSearchResultData.getExisting_Passengers_Label());
        txtContinue.setText(redeemSearchResultData.getContinue_Label());

        NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        redeemSearchResultData.setAdd_New_Passenger_Label(localization.getUserAddNewPass());
        RedeemAddPassengerRecyclerViewAdapter adapter = new RedeemAddPassengerRecyclerViewAdapter(getActivity(), redeemSearchResultData, new RedeemAddPassengerRecyclerViewAdapter.IRecyclerViewHolderClicks() {
            @Override
            public void onClickRecyclerItemSelect(View v, ArrayList<UsersDetail> listUsersDetail, boolean isDefault) {


                if(listUsersDetail != null)
                {
                    for(int i = 0 ; i<listUsersDetail.size(); i++)
                    {
                        if((listUsersDetail.get(i).getUpgradeRequired() == true && !isDefault)
                                /*|| (redeemSearchResultData.getFFPNumberMandatory() == 1 && listUsersDetail.get(i).getFfpNumber().equals(""))*/)
                        {
                            AddPaxIdentityData param = new AddPaxIdentityData();
                            param.setUpgradRequired(listUsersDetail.get(i).getUpgradeRequired());

                            String[] arayPrefix = getResources().getStringArray(R.array.array_prefix_name);
                            int[] arayPrefixId = getResources().getIntArray(R.array.array_prefix_id);
                            String prefixId = null;
                            for (int j=0 ; j<arayPrefix.length; j++)
                            {
                                if(arayPrefix[j].equals(listUsersDetail.get(i).getPrefix()))
                                {
                                    prefixId = String.valueOf(arayPrefixId[j]);
                                    break;
                                }

                            }

                            String phoneExtValue = null;
                            for (int k=0 ; k<redeemSearchResultData.getPhoneExtArray().size(); k++)
                            {
                                if(redeemSearchResultData.getPhoneExtArray().get(k).getLabel().equals(listUsersDetail.get(i).getPhoneExt()))
                                {
                                     phoneExtValue = redeemSearchResultData.getPhoneExtArray().get(k).getLabel().toString();
                                    break;
                                }



                            }

                            if(phoneExtValue == null)//if wrong phnext coming from server
                            {
                                phoneExtValue = redeemSearchResultData.getPhoneExtArray().get(0).getLabel();
                            }

                            param.setPassUserId(String.valueOf(listUsersDetail.get(i).getUserId()));
                            param.setPrefix(prefixId);
                            param.setFName(listUsersDetail.get(i).getFName());
                            param.setMName(listUsersDetail.get(i).getMName());
                            param.setLName(listUsersDetail.get(i).getLName());
                            param.setEmail(listUsersDetail.get(i).getEmail());
                            param.setMobExt(phoneExtValue);
                            param.setMobNum(listUsersDetail.get(i).getTelephoneMainPart());
                            param.setDOBDay(listUsersDetail.get(i).getDOBDay());
                            param.setDOBMonth(listUsersDetail.get(i).getDOBMonth());
                            param.setDOBYear(listUsersDetail.get(i).getDOBYear());
                            param.setFFPnumber(listUsersDetail.get(i).getFfpNumber());
                            param.setCardTypeArray(redeemSearchResultData.getCardTypeArray());
                            param.setCountryListArray(redeemSearchResultData.getCountryListArray());

                            param.setIDCardType(listUsersDetail.get(i).getIdCardType());
                            param.setIDCardNumber(listUsersDetail.get(i).getIDnumber());
                            param.setIDCountry(listUsersDetail.get(i).getCountryName());
                            param.setEXPDay(listUsersDetail.get(i).getIDDocExpDay());
                            param.setEXPMonth(listUsersDetail.get(i).getIDDocExpMonth());
                            param.setEXPYear(listUsersDetail.get(i).getIDDocExpYear());

                            param.setIsDisplayFfpNumber(redeemSearchResultData.getIsDisplayFFPNumber());
                            param.setFFPnumberMandatory(redeemSearchResultData.getFFPNumberMandatory());
                            param.setFFpnumberHelpMessage(redeemSearchResultData.getFFpnumberHelpMessage());
                            param.setFFpnumberErrorMessage(redeemSearchResultData.getFFpnumberErrorMessage());

                            Utils.moveToFragment(getActivity(),new RedeemAddPaxIdentityFragment(), param, 0);

                            //clear error message if any
                            lytErrorMessage.removeAllViews();
                            lytError.setVisibility(View.GONE);
                        }
                    }
                    listSelectedUsers.clear();
                    listSelectedUsers.addAll(listUsersDetail);
                }
                else
                {
                    Utils.moveToFragment(getActivity(),new RedeemAddPaxInfoFragment(),redeemSearchResultData, 0 );
                    //clear error message if any
                    lytErrorMessage.removeAllViews();
                    lytError.setVisibility(View.GONE);
                }

            }
        });
        recyclerView.setAdapter(adapter);
    }


    public void updateLayout(FragmentCommunicationData message) {
        Utils.DEBUG("updateLayout() called : " + message.isFlagRollbackAddPaxUI());
        AddPaxIdentityData addPaxCompleteData = message.getAddPaxCompleteData();
        if(message.isFlagRollbackAddPaxUI())
        {
            for (int index = 0; index < listSelectedUsers.size(); index++) {
                if(listSelectedUsers.get(index).getUpgradeRequired())
                {
                    listSelectedUsers.remove(index);
                }
            }

            for (int index = 0; index < redeemSearchResultData.getUsersDetail().size(); index++)
            {
                boolean isEqual = false;
                for (int pos = 0; pos < listSelectedUsers.size(); pos++)
                {
                    if(redeemSearchResultData.getUsersDetail().get(index).getIndex() == listSelectedUsers.get(pos).getIndex())
                    {
                        isEqual = true;
                        break;
                    }
                }
                redeemSearchResultData.getUsersDetail().get(index).setSelectedPassUser(isEqual);
            }


            updateUI();
        }
        else
        {
            callAddPassengerAPI(addPaxCompleteData);
        }

    }

    private void callAddPassengerAPI(AddPaxIdentityData addPaxCompleteData)
    {
        String tag_json_obj = "json_obj_req";
        StringBuilder bufferURL = null;

        if(addPaxCompleteData.isUpgradRequired() == true)
        {
            bufferURL = new StringBuilder(getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_UpdatePassUserAjax));
            addPaxCompleteData.setUpgradRequired(false);
        }
        else
        {
            bufferURL = new StringBuilder(getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_SaveNewUserFPO));
        }
        for (int index = 0; index < listSelectedUsers.size(); index++) {
            bufferURL.append("&selectedIndex=" + listSelectedUsers.get(index).getIndex());
        }

        /*JSONObject requestObject = null;
        try {
            requestObject = new JSONObject(Utils.readFromAssets(getActivity(), "addNewPassengerTestData.txt"));
            //requestObject = new JSONObject(Utils.readFromAssets(getActivity(), addPaxCompleteData));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        addPaxCompleteData.setFFpnumberHelpMessage(null);
        addPaxCompleteData.setFFpnumberErrorMessage(null);

        String s = ParseManager.getInstance().toJSON(addPaxCompleteData);

        JSONObject requestObject = null;
        try {
            requestObject = new JSONObject(s);
            requestObject.remove("isUpgradRequired");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*try {
            requestObject.put("FName",       addPaxCompleteData.getFirstName()              );
            requestObject.put("MName",       addPaxCompleteData.getMiddleName()             );
            requestObject.put("LName",       addPaxCompleteData.getLastName()               );
            requestObject.put("MobExt",      addPaxCompleteData.getCountryCode()         +"");
            requestObject.put("MobNum",      addPaxCompleteData.getPhoneNumber()         +"");
            requestObject.put("Email",       addPaxCompleteData.getEmail()                  );
            requestObject.put("DOBDay",      addPaxCompleteData.getDate()                +"");
            requestObject.put("DOBMonth",    addPaxCompleteData.getMonth()               +"");
            requestObject.put("DOBYear",     addPaxCompleteData.getYear()                +"");
            requestObject.put("FFPnumber",   addPaxCompleteData.getBritishClubMemberNumber()+"");
            requestObject.put("IDCardType",  addPaxCompleteData.getCardTypeSelected()       );
            requestObject.put("IDCountry",   addPaxCompleteData.getCountryIssuance()        );
            requestObject.put("IDCardNumber",addPaxCompleteData.getIdCardNumber()        +"");
            requestObject.put("EXPDay",      addPaxCompleteData.getExpDay()              +"");
            requestObject.put("EXPMonth",    addPaxCompleteData.getExpMonth()            +"");
            requestObject.put("EXPYear",     addPaxCompleteData.getYear()                +"");
            requestObject.put("Prefix",      addPaxCompleteData.getPrefix()                 );
            requestObject.put("PIssuePlace", addPaxCompleteData.getCityIssuance()           );

        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }*/



        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                bufferURL.toString(),
                requestObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        if(response == null)
                        {
                            return;
                        }
                        DEBUG("onResponse() called : " + response.toString());
                        ListUsersDetail listUsersDetail = ParseManager.getInstance().fromJSON(response, ListUsersDetail.class);
                        Utils.DEBUG("Is_Error : " + listUsersDetail.is_Error());
                        redeemSearchResultData.setUsersDetail(listUsersDetail.getUsersDetail());
                        redeemSearchResultData.setIs_Error(listUsersDetail.is_Error());
                        Utils.DEBUG("Is_Error : " + redeemSearchResultData.is_Error());
                        redeemSearchResultData.setError_Message(listUsersDetail.getError_Message());
                        updateUI();
                        loader.dismiss();
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }


    private void callRedeemReivewBook()
    {
        String tag_json_obj = "json_obj_req";

        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_PassFlightSummary);

        if(listSelectedUsers.size() != Integer.parseInt(redeemSearchResultData.getPassengers_Count()))
        {
            //first clear all previos error message view
            lytErrorMessage.removeAllViews();
            lytError.setVisibility(View.VISIBLE);

            //show error message
            lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), redeemSearchResultData.getSelect_Passengers_Label()));

            return;
        }
        UserIdData data = new UserIdData();
        ArrayList<String> ids = new ArrayList<>();
        for (int index = 0; index < listSelectedUsers.size(); index++) {
            ids.add(Integer.toString(listSelectedUsers.get(index).getIndex()));
        }
        data.setUserId(ids);

        JSONObject requestObject = null;

        try {
            requestObject = new  JSONObject(ParseManager.getInstance().toJSON(data));
        } catch (JSONException e) {
            requestObject = new JSONObject();
        }


        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        if(response == null)
                        {
                            return;
                        }
                        DEBUG("onResponse() Redeem Search Result called : " + response.toString());
                        RedeemSearchResultData result = ParseManager.getInstance().fromJSON(response, RedeemSearchResultData.class);
                        Utils.moveToFragment(getActivity(), new RedeemReviewBookFragment(), result, 0);

                        loader.dismiss();
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }


}
