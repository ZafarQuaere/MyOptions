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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.MMPParameterAdapter;
import com.optiontown.app.adapter.NpaGridLayoutManager;
import com.optiontown.app.model.redeem.mmp.ChangeFlightParameter;
import com.optiontown.app.model.redeem.mmp.ChangeParameterData;
import com.optiontown.app.model.redeem.mmp.FlightsList;
import com.optiontown.app.model.redeem.mmp.MMPUserSelectedData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BFragment;

import org.json.JSONObject;

/**
 * Created by amit on 05-11-2016.
 */
public class MMPSelectParameterFragment extends BFragment
{
    private View view;
    private FlightsList passSelected;
    private ChangeParameterData changeParameterData;
    private OTTextView txtSelectParameterLabel;
    private OTTextView txtSelectedParameter;
    private LinearLayout lytChange;
    private OTTextView txtContinue;
    private RelativeLayout lytMain;
    private RecyclerView recyclerView;
    private LinearLayout lytError;
    private LinearLayout lytErrorMessage;
    private CardView cardView;
    private ChangeFlightParameter selectedChangeFlightParameter;
    private OTTextView txtSelectedPass;
    private ImageView imgEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Utils.DEBUG("onCreateView called");
        view = inflater.inflate(R.layout.fragment_mmp_select_parameter, container, false);

        passSelected = (FlightsList) getArguments().getSerializable(getString(R.string.key_serializable));

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), "", null);
        Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
        getUIReference();
        callManageYourPassRefreshAPI();
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
                //Utils.clearBackstackTillMMPSelectPassFragment("Update Users",getActivity());
            }
        });
        lytChange = (LinearLayout) view.findViewById(R.id.lytChange);
        lytChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView.setVisibility(cardView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
                txtContinue.setVisibility(cardView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        txtSelectParameterLabel = (OTTextView) view.findViewById(R.id.txtSelectParameterLabel);
        txtSelectedParameter = (OTTextView) view.findViewById(R.id.txtSelectedParameter);
        txtContinue = (OTTextView) view.findViewById(R.id.txtSave);
        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtSelectedParameter.getText().toString().equals(changeParameterData.getChangeFlightParameter().get(0).getPasslabelname()))
                {
                    //first clear all previos error message view
                    lytErrorMessage.removeAllViews();
                    lytError.setVisibility(View.VISIBLE);

                    //show error message
                    lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), changeParameterData.getLABLERRChangeTypeLabel()));
                }
                else
                {
                    lytErrorMessage.removeAllViews();
                    lytError.setVisibility(View.GONE);
                    //move to
                    MMPUserSelectedData data = new MMPUserSelectedData();
                    data.setSelectedPassLabel(passSelected.getLabel());
                    data.setSelectedpassid(passSelected.getId());
                    data.setPassChangeType(selectedChangeFlightParameter.getPasschangrtypeid());

                    Utils.moveToFragment(getActivity(), new MMPSelectNewValueFragment(), data, 0);
                }
            }
        });
        cardView = (CardView) view.findViewById(R.id.cardView);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

    private void updateUI()
    {
        try
        {
            txtSelectedPass.setText(passSelected.getLabel().replace("#", ":"));
            Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), changeParameterData.getLABLFPOChangeMyFlightPassLabel(), null);
            txtSelectParameterLabel.setText(changeParameterData.getLABLFPOSelectParameterToChangeLabel());
            txtSelectedParameter.setText(changeParameterData.getChangeFlightParameter().get(0).getPasslabelname());
            txtContinue.setText(changeParameterData.getLABL_Continue_Label());

            NpaGridLayoutManager gridLayoutManager = new NpaGridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);

            MMPParameterAdapter adapter = new MMPParameterAdapter(getActivity(), 0, changeParameterData.getChangeFlightParameter(), new MMPParameterAdapter.RecyclerViewHolderClicks()
            {
                @Override
                public void onClickRecyclerItemDetail(View v, Object data) {
                    selectedChangeFlightParameter = (ChangeFlightParameter)data;
                    cardView.setVisibility(View.GONE);
                    txtSelectedParameter.setText(selectedChangeFlightParameter.getPasslabelname());
                    txtContinue.setVisibility(View.VISIBLE);

                    if(txtSelectedParameter.getText().toString().equals(changeParameterData.getChangeFlightParameter().get(0).getPasslabelname()))
                    {
                        //first clear all previous error message view
                        lytErrorMessage.removeAllViews();
                        lytError.setVisibility(View.VISIBLE);

                        //show error message
                        lytErrorMessage.addView(Utils.getErrorOneRowView(getActivity(), changeParameterData.getLABLERRChangeTypeLabel()));
                    }
                    else
                    {
                        lytErrorMessage.removeAllViews();
                        lytError.setVisibility(View.GONE);
                    }
                }
            });
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);


            lytMain.setVisibility(View.VISIBLE);
        }catch (Exception e)
        {
            Utils.ERROR("Error while setting ui : " + e.toString());
        }

    }

    private void callManageYourPassRefreshAPI() {
        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_ManageYourPassRefresh);
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("selectedpassid", passSelected.getId());
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
                        changeParameterData = ParseManager.getInstance().fromJSON(response, ChangeParameterData.class);
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
