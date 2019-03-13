package com.optiontown.app.view.fragment;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.optiontown.R;
import com.optiontown.app.adapter.ProductBeneifitsRecyclerViewAdapter;
import com.optiontown.app.model.benifits.Benefit;
import com.optiontown.app.model.homepage.HomeStatic;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.AppVariables;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.fragment.fpo.flightpass.FlightPassFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductsHomeFragment;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * home UI
 *
 * @author amit
 */
public class HomeFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView.Adapter adapter;
    AppDialogLoader loader = null;
    private AppSharedPrefs sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sp = AppSharedPrefs.getInstance(getActivity());
        loader = AppDialogLoader.getLoader(getActivity());
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewHome);
        callSessionIdAPI();
        return view;
    }
    private void callSessionIdAPI() {
        String tag_json_obj = "json_obj_req";
        String url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_SESSION_ID);
        JSONObject requestObject = new JSONObject();
        try {
            requestObject.put("tgProductId", Integer.toString(getResources().getInteger(R.integer.value_tgProductId_fpo)));
            requestObject.put("CountryId", Utils.getUserSelectedCountryId(getActivity())+"");
            requestObject.put("LanguageId", Utils.getUserSelectedLanguageId(getActivity())+"");
        } catch (Exception e) {
            Utils.ERROR("Error while creating json request : " + e.toString());
        }

        loader.show();
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
                        HomeStatic mHome = ParseManager.getInstance().fromJSON(response, HomeStatic.class);
                        com.optiontown.app.model.homepage.StaticBanner mStaticBanner = mHome.getStaticBanner();
                        List<com.optiontown.app.model.homepage.FrontBanner> mFrontBanners = mStaticBanner.getFrontBanner();
                        try {
                             LoadProducts(mFrontBanners);
                        } catch (Exception ew) {
                            Utils.DEBUG(ew.toString());
                        }

                        loader.hide();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.ERROR("Error: " + error);
                loader.hide();

            }

        }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void LoadProducts(List<com.optiontown.app.model.homepage.FrontBanner> mFrontBanners) {
        recyclerView.setHasFixedSize(true);
        try {
            final ArrayList<Benefit> mBenefits = new ArrayList<>();
            for (int i = 0; i < mFrontBanners.size(); i++) {
                // JSONObject mJsonObjectBenefit = new JSONObject(jsonArrayProducts.get(i).toString());
                Benefit mBenefit = new Benefit();
                mBenefit.setBenefitName(mFrontBanners.get(i).getShortLabel());
                mBenefit.setImageURL(mFrontBanners.get(i).getButtonIcon());
                mBenefit.setId(Integer.parseInt(mFrontBanners.get(i).getProductID()));
                mBenefits.add(mBenefit);
            }
            gridLayoutManager = new GridLayoutManager(this.getActivity(), 2, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);

            int spanCount = 2; // 3 columns
            int spacing = 30; // 50px
            boolean includeEdge = true;
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));



            adapter = new ProductBeneifitsRecyclerViewAdapter(Color.parseColor("#ffffff"), getActivity(), mBenefits,
                    new ProductBeneifitsRecyclerViewAdapter.IRecyclerViewHolderClicks() {
                @Override
                public void onClickRecyclerItem(View v, int position) {

                    int id= mBenefits.get(position).getId();
                    Utils.setCurrentProductId(getActivity(),id);
                   /// Utils.showToast(getActivity(), id +" ");
                    if(id == getResources().getInteger(R.integer.value_tgProductId_fpo) || id == getResources().getInteger(R.integer.value_tgProductId_utp) ||
                            id == getResources().getInteger(R.integer.value_tgProductId_psp) || id == getResources().getInteger(R.integer.value_tgProductId_esp))
                    {

                        Utils.moveToFragment(getActivity(), new FlightPassFragment(), null, 0);
                    }
                    else
                    {
                        if(getResources().getBoolean(R.bool.bool_default_fpo) == false){

                            String productName= getResources().getStringArray(R.array.array_option_name)[position];

                            String benefitJSON = ParseManager.getInstance().toJSON(mBenefits.get(position));
                            Utils.setLegBenefitData(getActivity(), benefitJSON);

                            Utils.moveToFragment(getActivity(), new LegProductsHomeFragment(), mBenefits.get(position), 0);
                            AppVariables.ProductName= mBenefits.get(position).getBenefitName();

                        }else {
                            //Utils.showToast(getActivity(),getString(R.string.coming_soon));
                        }
                    }
                }
            });
            recyclerView.setAdapter(adapter);
        } catch (Exception exc) {
        }
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

}
