package com.optiontown.app.view.fragment.legproducts;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.optiontown.R;
import com.optiontown.app.adapter.LegProductSearchSelectRecyclerViewAdapter;

import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.legproducthomepage.AirlineDropDownList;
import com.optiontown.app.model.legproducthomepage.Home;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by zafar.imam on 05-08-2016.
 */
public class LegProductSearchSelectFragment extends BaseFragment {
    View view;
    private Communicator communicator;
    private AppSharedPrefs sf;
    private Home flightPassDealData;

    private ImageView imgDone;
    private String title;
    private String tag = "";
    private int id = 0;
    OTTextView txtSelectedItem;
    ImageView imgSelectedItem;
    private String strHelp;
    private FragmentActivity fragmentActivity;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_leg_product_search_select, container, false);
        sf = AppSharedPrefs.getInstance(getActivity());

        Utils.DEBUG(this.getClass().getSimpleName() + " >> received : " + getArguments().toString());


        try {
            flightPassDealData = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getLegProductSessionData(getActivity())), Home.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtSelectedItem = (OTTextView) view.findViewById(R.id.txtSelectedItem);
        imgSelectedItem = (ImageView) view.findViewById(R.id.imgSelectedItem);
        txtSelectedItem.setText("Airline");

        RecyclerView  recyclerViewLPSelect = (RecyclerView) view.findViewById(R.id.recyclerViewLPSelect);
        recyclerViewLPSelect.setHasFixedSize(false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recyclerViewLPSelect.setLayoutManager(gridLayoutManager);

          List<AirlineDropDownList> arrayList =  flightPassDealData.getHomePageData().getAirlineDropDownList();

        for (int index = 0; index < arrayList.size(); index++) {
            if( sf.get(getActivity().getString(R.string.key_LP_selected_airline_tag)) != null && ((String) sf.get(getActivity().getString(R.string.key_LP_selected_airline_tag))).equals(arrayList.get(index).getAirlineName()))
            {
                arrayList.get(index).setSelectedAirline(true);
                txtSelectedItem.setText(arrayList.get(index).getAirlineName());
            }
            else
            {
                arrayList.get(index).setSelectedAirline(false);
            }
        }


        //  arrayList.get(0).setSelectedAirline(true);
         RecyclerView.Adapter adapter = new LegProductSearchSelectRecyclerViewAdapter(getActivity(), arrayList, new LegProductSearchSelectRecyclerViewAdapter.IRecyclerViewHolderClicks()
        {
            @Override
            public void onClickRecyclerItems(View v, int position, Object item)
            {
                if(item instanceof AirlineDropDownList)
                {
                    tag = (((AirlineDropDownList) item).getAirlineName());
                    id = Integer.parseInt((((AirlineDropDownList) item).getAirlineCode()));
                    ((AirlineDropDownList) item).setSelectedAirline(true);
                    txtSelectedItem.setText(tag);
                }

            }
        });
        recyclerViewLPSelect.setAdapter(adapter);


        imgDone = (ImageView) view.findViewById(R.id.imgDone);
        imgDone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                    Utils.DEBUG("selected airline id : " + id + ", selected airline name : " + tag);
                Utils.DEBUG("from sp : " + sf.get(getString(R.string.key_LP_selected_airline_tag)));

                if(id == 0 && sf.get(getString(R.string.key_LP_selected_airline_tag)) == null)
                {

                }
                else if(id == 0 && sf.get(getString(R.string.key_LP_selected_airline_tag)) != null)
                {
                    //update LegProductSearchFragment
                    communicateToFragment(false);
                    //back
                    ((MainActivity)getActivity()).onBackPressed();
                }
                else
                {
                    sf.put(getString(R.string.key_LP_selected_airline_id), id);
                    sf.put(getString(R.string.key_LP_selected_airline_tag), tag);


                    //update LegProductSearchFragment
                    communicateToFragment(false);

                    //back
                    ((MainActivity)getActivity()).onBackPressed();
                }

            }
        });

        Utils.updateLegProductBottomBarForFeatures(getActivity(), view, this.getClass().getName());



        return view;
    }


    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("LegProductSearchSelectFragment >> onAttach(Activity) called");
        fragmentActivity = (FragmentActivity) activity;
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }

    private void communicateToFragment(boolean isAirlineChanged)
    {
        FragmentCommunicationData data = new FragmentCommunicationData();
        data.setFragmentName((new LegProductSearchFragment()).getClass().getName());

        data.setSelectedAirline((String) sf.get(getString(R.string.key_LP_selected_airline_tag)));
        data.setAirlineChanged(isAirlineChanged);
        communicator.onResponse(data);
    }
}
