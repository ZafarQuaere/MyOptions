package com.optiontown.app.view.fragment.learnmore;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.session.FlightPass;
import com.optiontown.app.model.session.SessionIdData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amit on 25-07-2016.
 */
public class LearnMoreFragment extends BaseFragment {

    private View view;
    private AppSharedPrefs sp;
    ImageLoader imageLoader;
    private SessionIdData sessiondata;
    private OTTextView txt_mainheading_cheapest;
    private Boolean isBottomBarFromMmp;
    private LinearLayout bottom_lay_less;
    private LinearLayout bottom_lay_more;
    private OTTextView txtFlightHeadingg;
    private OTTextView txt_hyperlink_webview_optiontown, txt_secondline_howpurchase, txt_howpurchse_titlepara2;
    private LinearLayout lytHowPurchase;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_learn_more, container, false);
        imageLoader = AppController.getInstance().getImageLoader();

        //---update actionbar
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        updateBottomLayout();

        try {
            sessiondata = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getSessionData(getActivity())), SessionIdData.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        initUI();

        return view;

    }

    private void initUI() {

        //--Learn about Flight Pass
        final LinearLayout lytLearnAboutFlightPass = (LinearLayout) view.findViewById(R.id.lytLearnAboutFlightPass);
        lytLearnAboutFlightPass.setBackgroundColor(Color.parseColor("#ffffff"));
        lytLearnAboutFlightPass.findViewById(R.id.lytBottomToHide).setVisibility(View.VISIBLE);
        ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtTitle)).setVisibility(View.GONE);
        lytLearnAboutFlightPass.findViewById(R.id.imgExpand).setVisibility(View.GONE);
        lytLearnAboutFlightPass.findViewById(R.id.txtReadMore).setVisibility(View.GONE);
        lytLearnAboutFlightPass.findViewById(R.id.lytTitle).setVisibility(View.GONE);
        NetworkImageView imgLearnMore = (NetworkImageView)lytLearnAboutFlightPass.findViewById(R.id.imgLearnMore);
        imgLearnMore.setImageUrl(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getImage()+"", imageLoader);
        imgLearnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=-5QkZh4nHkc&list=UUSULxgi1JZanNRJWMbAq4SA"));
                    startActivity(browserIntent);
                } catch (ActivityNotFoundException e) {
                    Utils.ERROR("Error: " + "Error WebView");
                    e.printStackTrace();
                }
            }
        });


        ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtTitle)).setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getHeadingLearnAboutFlightPass());
        ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtLearnMoreTitle)).setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getTitleHeading());
        ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtLearnMoreDescription)).setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getTitlePara1());
        ((OTTextView) lytLearnAboutFlightPass.findViewById(R.id.txtFlightUpto50less)).setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getTitlePara2());

        final LinearLayout lytBottomToHide = (LinearLayout) lytLearnAboutFlightPass.findViewById(R.id.lytBottomToHide);
        final LinearLayout parent = Utils.layLoop(getActivity(),lytBottomToHide,sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(0).getInnerList());

        LinearLayout lay_loopParent = (LinearLayout) view.findViewById(R.id.lay_loopParent);

        ArrayList<FlightPass> dataForLearnMore = new ArrayList();
        for(int i = 0 ; i< sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().size(); i++)
        {
            dataForLearnMore.add(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(i));
        }


        txt_mainheading_cheapest = (OTTextView)view.findViewById(R.id.txt_mainheading_cheapest);
        txt_mainheading_cheapest.setText(sessiondata.getLearnAboutFlightPass().getLearnaboutFlightPass().getFlightPass().get(1).getTitleHeading());

        Utils.layLoopForLearnMoreFragment(getActivity(), lay_loopParent,dataForLearnMore);

        txtFlightHeadingg = (OTTextView) view.findViewById(R.id.txtFlightHeading);

        if(Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_fpo)))
        {
            txtFlightHeadingg.setVisibility(View.GONE);

        }else if(Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_utp) )
                || Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_esp))
                || Utils.getCurrentProductId(getActivity()) == (getResources().getInteger(R.integer.value_tgProductId_psp)))
        {
            txtFlightHeadingg.setVisibility(View.VISIBLE);
        }


        //--How can I book flights with my Flight Pass?

        final LinearLayout lytHowBook = (LinearLayout) view.findViewById(R.id.lytHowBook);
        final NetworkImageView img_how_can_i_book_flight = (NetworkImageView) lytHowBook.findViewById(R.id.img_how_can_i_book_flight);
        img_how_can_i_book_flight.setImageUrl(sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(1).getImage()+"", imageLoader);
        final LinearLayout lytBottomToHideHowBook = (LinearLayout) lytHowBook.findViewById(R.id.lytBottomToHide);
        lytBottomToHideHowBook.setVisibility(View.GONE);
        final LinearLayout parentlytHowBook = Utils.layLoopForlytHowBook(getActivity(),lytBottomToHideHowBook,sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(1).getInnerList());
        ((OTTextView) lytHowBook.findViewById(R.id.txtTitle)).setText(sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(1).getTitleHeading());
        ((OTTextView) lytHowBook.findViewById(R.id.txt_choos_flight_pass_you_want)).setText("• "+sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(1).getInnerList().get(1));

        final ImageView imgExpandHowBook = (ImageView) lytHowBook.findViewById(R.id.imgExpand);
        imgExpandHowBook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                imgExpandHowBook.setImageResource(lytBottomToHideHowBook.getVisibility() == View.GONE ? R.drawable.minus_icon : R.drawable.plus_icon);
                lytBottomToHideHowBook.setVisibility(lytBottomToHideHowBook.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        final OTTextView txt_how_book = (OTTextView) lytHowBook.findViewById(R.id.txt_webview_ot_how_book);
        txt_how_book.setText("• "+sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(1).getInnerList().get(0));
        txt_how_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.optiontown.com"));
                    startActivity(browserIntent);
                } catch (ActivityNotFoundException e) {
                    Utils.ERROR("Error: " + "Error WebView");
                    e.printStackTrace();
                }
            }
        });


        //--How Can I Purchase Flight Pass?
        lytHowPurchase = (LinearLayout) view.findViewById(R.id.lytHowPurchase);

        ((OTTextView) lytHowPurchase.findViewById(R.id.txtTitle)).setText(sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(0).getTitleHeading());
        final NetworkImageView img_howpurchase = (NetworkImageView) lytHowPurchase.findViewById(R.id.img_howpurchase);
        img_howpurchase.setImageUrl(sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(0).getImage() + "", imageLoader);

        final LinearLayout lytBottomToHideHowPurchase = (LinearLayout) lytHowPurchase.findViewById(R.id.lytBottomToHide);
        lytBottomToHideHowPurchase.setVisibility(View.GONE);
        final LinearLayout parenthowpurchase = Utils.layLoopForlytHowPurchase(getActivity(), lytBottomToHideHowPurchase, sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(0).getInnerList());
        final ImageView imgExpandHowPurchase = (ImageView) lytHowPurchase.findViewById(R.id.imgExpand);
        imgExpandHowPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgExpandHowPurchase.setImageResource(lytBottomToHideHowPurchase.getVisibility() == View.GONE ? R.drawable.minus_icon : R.drawable.plus_icon);
                lytBottomToHideHowPurchase.setVisibility(lytBottomToHideHowPurchase.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });

        txt_hyperlink_webview_optiontown = (OTTextView) lytHowPurchase.findViewById(R.id.txt_hyperlink_webview_optiontown);
        txt_hyperlink_webview_optiontown.setText("• "+sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(0).getInnerList().get(0));
        txt_secondline_howpurchase = (OTTextView) lytHowPurchase.findViewById(R.id.txt_secondline_howpurchase);
        txt_secondline_howpurchase.setText("• " + sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(0).getInnerList().get(1));
        txt_howpurchse_titlepara2 = (OTTextView) lytHowPurchase.findViewById(R.id.txt_howpurchse_titlepara2);
        txt_howpurchse_titlepara2.setText(sessiondata.getLearnAboutFlightPass().getHowToPurchase().get(0).getTitlePara2());
        txt_hyperlink_webview_optiontown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.optiontown.com"));
                    startActivity(browserIntent);
                } catch (ActivityNotFoundException e) {
                    Utils.ERROR("Error: " + "Error WebView");
                    e.printStackTrace();
                }
            }
        });

    }

    private void updateBottomLayout() {

        bottom_lay_less = (LinearLayout) view.findViewById(R.id.bottom_lay_less);
        bottom_lay_more = (LinearLayout) view.findViewById(R.id.bottom_lay_more);

        try {
            isBottomBarFromMmp = (Boolean) getArguments().getSerializable(getString(R.string.key_serializable));

        }catch (Exception e)
        {

        }

        if(isBottomBarFromMmp != null)
        {
            if (isBottomBarFromMmp) {
                bottom_lay_less.setVisibility(View.VISIBLE);
                bottom_lay_more.setVisibility(View.VISIBLE);
                Utils.updateBottomBarFpoRedeemMoreForFeatures(view, this.getClass().getName(), false);
            }else {
                Utils.updateBottomBarForFeatures(view, this.getClass().getName());
            }
        }else {

            bottom_lay_less.setVisibility(View.GONE);
            bottom_lay_more.setVisibility(View.GONE);
            Utils.updateBottomBarForFeatures(view, this.getClass().getName());
        }

    }
}
