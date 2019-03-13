package com.optiontown.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.optiontown.R;
import com.optiontown.app.model.legproducthomepage.ArriveDropDownList;

import java.util.ArrayList;
import java.util.List;

public class AirlineArriveDepartAdapter extends ArrayAdapter<ArriveDropDownList> implements Filterable {
    private final Context mContext;
    private final int mLayoutResourceId;
    private final ArrayList<ArriveDropDownList> mDepartments_All;
    private final ArrayList<ArriveDropDownList> mDepartments_Suggestion;
    private List<ArriveDropDownList> mAirlineList = new ArrayList<>();

    public AirlineArriveDepartAdapter(Context context, int resource, List<ArriveDropDownList> departments) {
        super(context, resource, departments);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mAirlineList = departments;
        this.mDepartments_All = new ArrayList<>(departments);
        this.mDepartments_Suggestion = new ArrayList<>();

    }


    public int getCount() {
        return mAirlineList.size();
    }

    public ArriveDropDownList getItem(int position) {
        return mAirlineList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView name = null;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
            }
            ArriveDropDownList department = getItem(position);
            name = (TextView) convertView.findViewById(R.id.txt1);
            name.setText(department.getArriveName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public String convertResultToString(Object resultValue) {
                return ((ArriveDropDownList) resultValue).getArriveName();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    mDepartments_Suggestion.clear();
                    for (ArriveDropDownList department : mDepartments_All) {
                        if (department.getArriveName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            mDepartments_Suggestion.add(department);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mDepartments_Suggestion;
                    filterResults.count = mDepartments_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mAirlineList.clear();
                if (results != null && results.count > 0) {
                    // avoids unchecked cast warning when using mAirlineList.addAll((ArrayList<Department>) results.values);
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof ArriveDropDownList) {
                            mAirlineList.add((ArriveDropDownList) object);
                        }
                    }
                } else if (constraint == null) {
                    // no filter, add entire original list back in
                    mAirlineList.addAll(mDepartments_All);
                }
                notifyDataSetChanged();
            }
        };

    }

}