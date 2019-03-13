package com.optiontown.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.benifits.Benefit;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.customview.OTTextViewHtml;
import com.optiontown.app.view.fragment.legproducts.LegProductLearnMoreFragment;
import com.optiontown.app.view.fragment.legproducts.LegProductsHomeFragment;

import java.util.ArrayList;

/**
 * Adapter to attach item with data for recycler view at home fragment
 *
 * @author amit
 */
public class BegifitsRecyclerViewAdapter extends RecyclerView.Adapter<BegifitsRecyclerViewAdapter.ViewHolder> {

    private final String className;
    private Context contextclass;
    ArrayList<Benefit> benefits;
    private ImageLoader imageLoader;

    private IRecyclerViewHolderClicks listener;

    public static interface IRecyclerViewHolderClicks {
        public void onClickRecyclerItem(View v, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private NetworkImageView imgLogo;
        private OTTextViewHtml txtLearnMoreTitle, txtLearnMoreDescription;

        public ViewHolder(View v) {
            super(v);
            this.imgLogo = (NetworkImageView) v.findViewById(R.id.imgLogo);
            this.txtLearnMoreTitle = (OTTextViewHtml) v.findViewById(R.id.txtLearnMoreTitle);
            this.txtLearnMoreDescription = (OTTextViewHtml) v.findViewById(R.id.txtLearnMoreDescription);
        }
    }

    public BegifitsRecyclerViewAdapter(Context ctx, ArrayList<Benefit> benefits, String className) {
        this.benefits = benefits;
        this.imageLoader = AppController.getInstance().getImageLoader();
        this.contextclass = ctx;
        this.className = className;

        Utils.DEBUG("Class BegifitsRecyclerViewAdapter >> list size : " + benefits.size());
    }
    // Create new views (invoked by the layout manager)
    @Override
    public BegifitsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.benefit_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    // Replace the contents of a view (invoked by the layout manager)
    //TODO: Heading Data for Index 0 separated with ## not coming(UTO). Update from server.
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Context context = holder.imgLogo.getContext();
        String name= benefits.get(position).getBenefitName();
        if (name != null) {
            if(name.indexOf("</font>")>0){
                holder.txtLearnMoreTitle.setTextColor(context.getResources().getColor(R.color.color_font_black));
                holder.txtLearnMoreTitle.setTypeface(null, Typeface.NORMAL);
            }
            String strForUto = "Upgrade to Business or First for up to 75% less!";

            if(Utils.getCurrentProductId((MainActivity)contextclass) == contextclass.getResources().getInteger(R.integer.value_tgProductId_uto))
            {
                holder.txtLearnMoreTitle.setHtml(benefits.get(position).getBenefitName().equals("")
                        || benefits.get(position).getBenefitName().isEmpty() ? strForUto : benefits.get(position).getBenefitName());
            }
            else
            {
                holder.txtLearnMoreTitle.setHtml(name);
            }

        }else {
            holder.txtLearnMoreTitle.setVisibility(View.GONE);
        }

        if(className.equals(new LegProductLearnMoreFragment().getClass().getName()))
        {
            if(position != 0 && position != 1)
            {
                if(Utils.getCurrentProductId((MainActivity)contextclass) == contextclass.getResources().getInteger(R.integer.value_tgProductId_pso))
                {
                    holder.txtLearnMoreTitle.setVisibility(View.VISIBLE);
                }else {
                    holder.txtLearnMoreTitle.setVisibility(View.GONE);
                }
            }else {
                holder.txtLearnMoreTitle.setVisibility(View.VISIBLE);
            }
        }




        if(benefits.get(position).getBenefitDescription()!=null)
        {
            holder.txtLearnMoreDescription.setHtml(getHTMLStringForBulletTag(benefits.get(position).getBenefitDescription()));
        }else{
            holder.txtLearnMoreDescription.setVisibility(View.GONE);
        }

        //change number of lines for txtLearnMoreDescription
        /*if(className.equals(new LegProductsHomeFragment().getClass().getName()))
        {

            //holder.txtLearnMoreDescription.setEllipsize(TextUtils.TruncateAt.END);
            //String str = setMaxStringDataForTextView(holder.txtLearnMoreDescription.getText().toString());
            String str = holder.txtLearnMoreDescription.getText().toString();
            if((position == 0) && benefits.size() > 1)
            {
                String requireStr = getHTMLString(benefits.get(position).getBenefitDescription());
                holder.txtLearnMoreDescription.setHtml(str + requireStr);
            }else
            {
                holder.txtLearnMoreDescription.setText(str);
            }

        }*/

        String imageURL = benefits.get(position).getImageURL().trim();
        if(imageURL != null && imageURL.length() > 0)
        {
            holder.imgLogo.setVisibility(View.VISIBLE);
            holder.imgLogo.setImageUrl(imageURL, imageLoader);
        }
        else
        {
            holder.imgLogo.setVisibility(View.GONE);
        }

    }

    private String setMaxStringDataForTextView(String stringTO) {

        int stringLength = stringTO.length();
        int max_size = stringLength > 250 ? 250 : stringLength;
        String substring = stringTO.substring(0, max_size);
        return substring + "....";

    }

    private String getHTMLString(String description)
    {
        if(description == null)
        {
            return "";
        }
        boolean flagShowBuller = false;
        StringBuilder buffer = new StringBuilder();

        String[] split = new String[0];
        try {
            split = description.split("##");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int index = 0; index < split.length; index++)
        {
            if (index == split.length - 1) {
                String[] $$s = split[index].split("%%%");
                for (int col = 0; col < $$s.length; col++) {
                    if(col != 0)
                    {
                        buffer.append("<br /><br />");
                        buffer.append(flagShowBuller ? "•" : "").append("<font color=\"#EE0000\">").append($$s[col]).append("</font>").append("<br /><br />");
                    }
                }
            }
        }
        return buffer.toString();
    }

    /**
     * Response array Learn_About[] at index 0 , we have response label data contain ## and %%% symbols.
     * Separate heading description using ## and red text descrption using %%% symbols.
     * @param description
     * @return
     */

    private String getHTMLStringForBulletTag(String description) {

        boolean flagShowBuller = false;
        StringBuilder buffer = new StringBuilder();

        String[] split = description.split("##");

        for (int index = 0; index < split.length; index++)
        {
            if (index == split.length - 1) {
                String[] $$s = split[index].split("%%%");

                for (int col = 0; col < $$s.length; col++) {
                    if(col == 0)
                    {
                        if(Utils.getCurrentProductId((MainActivity)contextclass) == contextclass.getResources().getInteger(R.integer.value_tgProductId_FlexibilityReward))
                        {
                            buffer.append(flagShowBuller ? "•" : "").append($$s[col].replace("<br />", ""));
                        }
                        else
                        {
                            buffer.append(flagShowBuller ? "•" : "").append($$s[col]).append("<br /><br />");
                        }
                    }
                    else
                    {
                        //if(!className.equals(new LegProductsHomeFragment().getClass().getName()))
                        {
                            buffer.append(flagShowBuller ? "•" : "").append("<font color=\"#EE0000\">").append($$s[col]).append("</font>").append("<br /><br />");
                        }
                    }
                }

            } else {

                if(className.equals(new LegProductsHomeFragment().getClass().getName()))
                {
                    if (index == 0)
                    {
                        String str = setMaxStringDataForTextView(split[index]);
                        if(Utils.getCurrentProductId((MainActivity)contextclass) == contextclass.getResources().getInteger(R.integer.value_tgProductId_FlexibilityReward))
                        {
                            buffer.append(flagShowBuller ? "•" : "").append(str.replace("<br />", ""));
                        }
                        else
                        {
                            buffer.append(flagShowBuller ? "•" : "").append(str).append("<br /><br />");
                        }
                    }

                }else {
                    buffer.append(flagShowBuller ? "•" : "").append(split[index]).append("<br /><br />");
                }
            }
            /*if(className.equals(new LegProductsHomeFragment().getClass().getName())) {
                buffer.append(flagShowBuller ? "•" : "").append(split[index]).append("<br /><br />");
            }
            else
            {

            }*/

        }


        return buffer.toString();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return benefits.size();
    }
}