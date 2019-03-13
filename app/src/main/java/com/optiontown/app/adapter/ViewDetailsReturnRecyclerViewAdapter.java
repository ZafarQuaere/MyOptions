package com.optiontown.app.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.benifits.Benefit;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.legproducts.LegProductViewDetailsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to attach item with data for recycler view at home fragment
 * @author Ravi Kumar
 */
public class ViewDetailsReturnRecyclerViewAdapter extends RecyclerView.Adapter<ViewDetailsReturnRecyclerViewAdapter.ViewHolder> {


    ArrayList<Benefit> benefits;
    private ImageLoader imageLoader;

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView.Adapter adapter;
    private Context ctx;
    LegProductViewDetailsFragment mViewMoreFrag;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private NetworkImageView imgOption;
        private OTTextView txtOutbound;


        public ViewHolder(View v)
        {
            super(v);




        }
    }

    public ViewDetailsReturnRecyclerViewAdapter(Context ctx, ArrayList<Benefit> benefits, LegProductViewDetailsFragment viewMoreFragment)
    {
        this.benefits= benefits;
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.ctx= ctx;
        mViewMoreFrag = viewMoreFragment;

    }

    @Override
    public ViewDetailsReturnRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_detail_return_rows, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

      /*  holder.txtOutbound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return benefits.size();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }



        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}