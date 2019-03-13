package com.optiontown.app.view.fragment.legproducts;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.optiontown.R;
import com.optiontown.app.adapter.BoostPriorityRecyclerViewAdapter;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.selectproduct.BoostMyPrioritySelectedData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.model.utosearchresult.BoostDetail;
import com.optiontown.app.model.utosearchresult.BoostNameList;
import com.optiontown.app.model.utosearchresult.LegListObj;
import com.optiontown.app.model.utosearchresult.PriceUpcabinDetail;
import com.optiontown.app.model.utosearchresult.UpcabinNameDetail;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.OTTextView;

import java.util.List;

/**
 * Created by Ravi.kumar on 06-08-2016.
 */
public class BoostMyPriorityFragment extends Fragment {
    View view;
    OTTextView txtTopBanner, txtFirst, txtConfirmButton, txtPriorityPM, txtUpgradPriorityTitle;
    private LinearLayout lytBMPheadTitle;
    //BoostMypriority boostMypriorities;
    String bpLabel, tgpDirectBtnLabel, ppMsg, psMsg, closeLabel, bpm;
    AppSharedPrefs sp;
    private RecyclerView recycleViewBoostPriority;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.Adapter adapter;
    private LegListObj legListObject;
    private Communicator communicator;
    private BoostMyPrioritySelectedData boostMyPrioritySelectedData;
    private String indexString;
    private AppDialogLoader loader;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_boost_my_priority, container, false);
        Init(view);
        sp = AppSharedPrefs.getInstance(getActivity());
        //boostMypriorities = ((BoostMypriority) getArguments().getSerializable(getString(R.string.key_serializable)));
        legListObject = ((LegListObj) getArguments().getSerializable(getString(R.string.key_serializable)));
        callBoostData(legListObject);
        loader = AppDialogLoader.getLoader(getActivity());

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        return view;
    }

    private void Init(View view) {
        recycleViewBoostPriority = (RecyclerView) view.findViewById(R.id.recycleViewBoostPriority);
        txtTopBanner = (OTTextView) view.findViewById(R.id.txtTopBanner);
      //  txtUpgradePriority = (OTTextView) view.findViewById(R.id.txtUpgradePriority);

        lytBMPheadTitle = (LinearLayout) view.findViewById(R.id.lytBMPheadTitle);

        txtFirst = (OTTextView) view.findViewById(R.id.txtFirst);
        txtPriorityPM = (OTTextView) view.findViewById(R.id.txtPriorityPM);
        txtConfirmButton = (OTTextView) view.findViewById(R.id.txtConfirmButton);
        txtUpgradPriorityTitle = (OTTextView) view.findViewById(R.id.txtUpgradPriorityTitle);

        txtConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showToast(getActivity(), "Confirmed");


                FragmentCommunicationData data = new FragmentCommunicationData();
                data.setFragmentName((new LegProductSearchResultFragment()).getClass().getName());
                data.setBoostMyPrioritySelectedData(boostMyPrioritySelectedData);

                communicator.onResponse(data);

                ((MainActivity)getActivity()).onBackPressed();
           }
        });

    }



    private void callBoostData(LegListObj legListObj) {

        //  List<BoostDetail> boostDetails = boostMypriorities.getBoostDetails();
        List<BoostDetail> boostDetails = legListObj.getBoostMypriority().getBoostDetails();

        bpLabel = boostDetails.get(0).getBoostPriorityLabel();
        tgpDirectBtnLabel = boostDetails.get(0).getLABLGetTGPDirectConfirmButtonLabel();
        ppMsg = boostDetails.get(0).getPriorityPricesMessage();
        psMsg = boostDetails.get(0).getPrioritySmallMessage();
        closeLabel = boostDetails.get(0).getLABLCloseLabel();

        bpm = boostDetails.get(0).getBoostMyPriority();
        sp.put(getActivity().getString(R.string.bpm_close_label), closeLabel);
        sp.put(getActivity().getString(R.string.bpm_actionbar_label), bpLabel);

        List<UpcabinNameDetail> upcabinNameDetails = boostDetails.get(0).getUpcabinNameDetails();
        List<PriceUpcabinDetail> priceUpcabinDetails = boostDetails.get(0).getPriceUpcabinDetails();
        List<BoostNameList> boostNameList = boostDetails.get(0).getBoostNameList();


        for (int i = 0; i < upcabinNameDetails.size(); i++) {

            TextView textView = new TextView(getActivity());
            textView.setText(upcabinNameDetails.get(i).getUpCabinNames());
            textView.setTextColor(Color.parseColor("#CD3301"));
            textView.setTextSize(Utils.convertPixelToDp(getActivity(), getActivity().getResources().getDimension(R.dimen.size_font_13)));
            textView.setPadding(10,0,0,0);
            lytBMPheadTitle.addView(textView);
        }

        //txtUpgradePriority.setText(upCabinName);
        txtConfirmButton.setText(tgpDirectBtnLabel);
        txtUpgradPriorityTitle.setText(psMsg);
        txtPriorityPM.setText(ppMsg);


        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
        recycleViewBoostPriority.setLayoutManager(mLinearLayoutManager);
        adapter = new BoostPriorityRecyclerViewAdapter(new BoostPriorityRecyclerViewAdapter.IBoostMyPriority() {
            @Override
            public void onClickBMP(String index, String price) {
                indexString = index;
                boostMyPrioritySelectedData = new BoostMyPrioritySelectedData();
                boostMyPrioritySelectedData.setSelectedIndex(index);
                boostMyPrioritySelectedData.setSelectedPrice(price);

                Utils.DEBUG("indexString :"+index+" selectedPrice :"+price);

            }
        }, getActivity(), priceUpcabinDetails, boostNameList, upcabinNameDetails);
        recycleViewBoostPriority.setAdapter(adapter);

    }

    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("BoostMyPriorityFragment >> onAttach(Activity) called");
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }

}
