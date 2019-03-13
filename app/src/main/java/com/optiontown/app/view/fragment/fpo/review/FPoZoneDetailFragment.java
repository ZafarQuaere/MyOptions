package com.optiontown.app.view.fragment.fpo.review;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.optiontown.R;
import com.optiontown.app.model.review.ArriveZoneArr;
import com.optiontown.app.model.review.ConnectingZoneArr;
import com.optiontown.app.model.review.DepArrvZoneArray;
import com.optiontown.app.model.review.DepartZoneArr;
import com.optiontown.app.model.review.ZonefeatureData;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;

/**
 * Created by amit on 19-06-2017.
 */
public class FPoZoneDetailFragment extends BaseFragment
{
    private View view;
    private OTTextView txtViewAllRoutes;
    private OTTextView txtTiny;
    private OTTextView txtSummary;
    private OTTextView txtDetail;
    private ZonefeatureData zonefeatureData;
    private OTTextView txtZoneName;
    private OTTextView txtZoneDescription;
    private OTTextView txtOriginLabel;
    private OTTextView txtDescriptionLabel;
    private OTTextView txtStopsLabel;
    private OTTextView txtDestinationLabel;
    private LinearLayout lytOriginDestination;

    private final int TYPE_TINY = 1;
    private final int TYPE_SUMMARY = 2;
    private final int TYPE_DETAIL = 3;

    private int type_current = 0;
    private boolean flagShowAll = false;

    private final int DEFAULT_LIMIT_TINY = 10;
    private final int DEFAULT_LIMIT_SUMMARY = 6;
    private final int DEFAULT_LIMIT_DETAIL = 6;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fpo_zone_detail, container, false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), "Travel Zone", "");

        zonefeatureData = ((ZonefeatureData)getArguments().getSerializable(getString(R.string.key_serializable)));

        getReferences();

        updateUI();

        return view;
    }

    private void getReferences()
    {
        lytOriginDestination = (LinearLayout) view.findViewById(R.id.lytOriginDestination);

        txtZoneName = (OTTextView) view.findViewById(R.id.txtZoneName);
        txtZoneDescription = (OTTextView) view.findViewById(R.id.txtZoneDescription);

        txtOriginLabel = (OTTextView) view.findViewById(R.id.txtOriginLabel);
        txtDestinationLabel = (OTTextView) view.findViewById(R.id.txtDestinationLabel);
        txtStopsLabel = (OTTextView) view.findViewById(R.id.txtStopsLabel);

        txtViewAllRoutes = (OTTextView) view.findViewById(R.id.txtViewAllRoutes);
        txtViewAllRoutes.setPaintFlags(txtViewAllRoutes.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txtViewAllRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewAllRoutes.setText(flagShowAll ? zonefeatureData.getViewAllRoutesLabel() : zonefeatureData.getViewLessLabel());
                createRunTimeUI(lytOriginDestination, type_current, !flagShowAll);

            }
        });

        txtTiny = (OTTextView) view.findViewById(R.id.txtTiny);
        txtTiny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorAndType(txtTiny, R.color.color_font_blue, Typeface.BOLD);
                setColorAndType(txtSummary, R.color.color_font_gray_dark, Typeface.NORMAL);
                setColorAndType(txtDetail, R.color.color_font_gray_dark, Typeface.NORMAL);
                createRunTimeUI(lytOriginDestination, TYPE_TINY, flagShowAll);
            }
        });
        txtSummary = (OTTextView) view.findViewById(R.id.txtSummary);
        txtSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorAndType(txtSummary, R.color.color_font_blue, Typeface.BOLD);
                setColorAndType(txtTiny, R.color.color_font_gray_dark, Typeface.NORMAL);
                setColorAndType(txtDetail, R.color.color_font_gray_dark, Typeface.NORMAL);
                createRunTimeUI(lytOriginDestination, TYPE_SUMMARY, flagShowAll);
            }
        });
        txtDetail = (OTTextView) view.findViewById(R.id.txtDetail);
        txtDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setColorAndType(txtDetail, R.color.color_font_blue, Typeface.BOLD);
                setColorAndType(txtTiny, R.color.color_font_gray_dark, Typeface.NORMAL);
                setColorAndType(txtSummary, R.color.color_font_gray_dark, Typeface.NORMAL);
                createRunTimeUI(lytOriginDestination, TYPE_DETAIL, flagShowAll);
            }
        });
    }

    private void setColorAndType(OTTextView textView, int color, int style)
    {
        textView.setTextColor(textView.getContext().getResources().getColor(color));
        textView.setTypeface(null, style);
    }


    private void updateUI()
    {
        if(zonefeatureData != null)
        {
            txtZoneName.setText(zonefeatureData.getShort_Decription());
            txtZoneName.setVisibility(View.GONE);//not in mobile website
            txtZoneDescription.setText(zonefeatureData.getLong_description());
            txtTiny.setText(zonefeatureData.getLABLTinyLabel());
            txtSummary.setText(zonefeatureData.getLABLSummaryLabel());
            txtDetail.setText(zonefeatureData.getLABLDetailLabel());

            txtOriginLabel.setText(zonefeatureData.getOriginLabel());
            txtDestinationLabel.setText(zonefeatureData.getDestinationLabel());
            txtStopsLabel.setText(zonefeatureData.getStopsLabel());

            txtViewAllRoutes.setText(zonefeatureData.getViewAllRoutesLabel());

            createRunTimeUI(lytOriginDestination, TYPE_SUMMARY, flagShowAll);
        }
    }

    private void createRunTimeUI(LinearLayout lytOriginDestination, int type, boolean flagShowAll)
    {
        if(lytOriginDestination != null)
        {
            this.type_current = type;
            this.flagShowAll = flagShowAll;



            lytOriginDestination.removeAllViews();;
            if(zonefeatureData != null)
            {
                ArrayList<DepArrvZoneArray> depArrvZoneArray = zonefeatureData.getDepArrvZoneArray();

                int count = 0;
                int runningCount = 0;
                boolean flagMaxRowReached = false;
                if(type == TYPE_TINY)
                {
                    count = (flagShowAll ? depArrvZoneArray.size() : (DEFAULT_LIMIT_TINY > depArrvZoneArray.size() ? depArrvZoneArray.size() : DEFAULT_LIMIT_TINY));
                    txtViewAllRoutes.setVisibility(depArrvZoneArray.size() <= DEFAULT_LIMIT_TINY ? View.GONE : View.VISIBLE);
                }
                else if(type == TYPE_SUMMARY)
                {
                    count = (flagShowAll ? getAllArrivingZoneArraySize(depArrvZoneArray) : (DEFAULT_LIMIT_SUMMARY > getAllArrivingZoneArraySize(depArrvZoneArray) ? getAllArrivingZoneArraySize(depArrvZoneArray) : DEFAULT_LIMIT_SUMMARY));
                    txtViewAllRoutes.setVisibility(getAllArrivingZoneArraySize(depArrvZoneArray) <= DEFAULT_LIMIT_SUMMARY ? View.GONE : View.VISIBLE);
                }
                else if(type == TYPE_DETAIL)
                {
                    count = (flagShowAll ? getAllArrivingZoneArraySize(depArrvZoneArray) : (DEFAULT_LIMIT_DETAIL > getAllArrivingZoneArraySize(depArrvZoneArray) ? getAllArrivingZoneArraySize(depArrvZoneArray) : DEFAULT_LIMIT_DETAIL));
                    txtViewAllRoutes.setVisibility(getAllArrivingZoneArraySize(depArrvZoneArray) <= DEFAULT_LIMIT_DETAIL ? View.GONE : View.VISIBLE);
                }

                for (int index = 0; index < depArrvZoneArray.size(); index++)
                {
                    if(!flagMaxRowReached)
                    {
                        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_review_zone_detail_row, null);

                        LinearLayout lytOrigin = (LinearLayout) view.findViewById(R.id.lytOrigin);
                        OTTextView txtDirectionalArrow = (OTTextView) view.findViewById(R.id.txtDirectionalArrow);
                        LinearLayout lytDestination = (LinearLayout) view.findViewById(R.id.lytDestination);
                        if(type == TYPE_TINY)
                        {
                            lytDestination.setOrientation(LinearLayout.HORIZONTAL);
                        }
                        LinearLayout lytStops = (LinearLayout) view.findViewById(R.id.lytStops);

                        //origin
                        StringBuilder bufferOrigin = new StringBuilder();
                        ArrayList<DepartZoneArr> departZoneArr = depArrvZoneArray.get(index).getDepartZoneArr();
                        for (int pos = 0; pos < departZoneArr.size(); pos++)
                        {
                            bufferOrigin.append(getDepartDisplayTextForStartOrigin(departZoneArr.get(pos), type));
                            if(pos != departZoneArr.size() - 1)
                            {
                                bufferOrigin.append(" / ");
                            }

                            HtmlTextView textView = new HtmlTextView(lytOrigin.getContext());
                            textView.setText(Html.fromHtml(getDepartDisplayText(zonefeatureData.getIsZoneAirportNameDisplay(), departZoneArr.get(pos), type)));
                            textView.setTextColor(textView.getContext().getResources().getColor(R.color.color_font_black));
                            textView.setTextSize(Utils.convertPixelToDp(textView.getContext(), textView.getContext().getResources().getDimension(R.dimen.size_font_11)));

                            lytOrigin.addView(textView);
                        }

                        //connecting
                        StringBuilder bufferConnecting = new StringBuilder();
                        ArrayList<ConnectingZoneArr> connectingZoneArr = depArrvZoneArray.get(index).getConnectingZoneArr();
                        for (int pos = 0; pos < connectingZoneArr.size(); pos++)
                        {
                            bufferConnecting.append(getDepartDisplayTextForConnecting(connectingZoneArr.get(pos), type));
                            if(pos != connectingZoneArr.size() - 1)
                            {
                                bufferConnecting.append(" / ");
                            }
                        }

                        //uni/bi directional
                        txtDirectionalArrow.setText(zonefeatureData.getIsBidirectional() == 1 ? zonefeatureData.getBiDirectionalArrow() : zonefeatureData.getUniDirectionalArrow());

                        //Destination
                        ArrayList<ArriveZoneArr> ArriveZoneArr = depArrvZoneArray.get(index).getArriveZoneArr();
                        StringBuilder buffer = new StringBuilder();
                        for (int pos = 0; pos < ArriveZoneArr.size(); pos++)
                        {
                            if(count == runningCount && type != TYPE_TINY)
                            {
                                //max row size,
                                flagMaxRowReached = true;
                                continue;
                            }
                            buffer.append(getArriveDisplayText(zonefeatureData.getIsZoneAirportNameDisplay(), ArriveZoneArr.get(pos), type));
                            if(type == TYPE_TINY)
                            {
                                if(pos != ArriveZoneArr.size() - 1)
                                {
                                    buffer.append(" / ");
                                }
                                else//last
                                {
                                    HtmlTextView textView = new HtmlTextView(lytOrigin.getContext());
                                    textView.setText(Html.fromHtml(buffer.toString()));
                                    textView.setTextColor(textView.getContext().getResources().getColor(R.color.color_font_black));
                                    textView.setTextSize(Utils.convertPixelToDp(textView.getContext(), textView.getContext().getResources().getDimension(R.dimen.size_font_11)));

                                    lytDestination.addView(textView);
                                }

                                if(pos % 2 == 0 && pos != 0)
                                {
                                    buffer.append("<br/>");
                                }

                            }
                            else
                            {
                                HtmlTextView textView = new HtmlTextView(lytOrigin.getContext());
                                textView.setText(Html.fromHtml(getArriveDisplayText(zonefeatureData.getIsZoneAirportNameDisplay(), ArriveZoneArr.get(pos), type)));
                                textView.setTextColor(textView.getContext().getResources().getColor(R.color.color_font_black));
                                textView.setTextSize(Utils.convertPixelToDp(textView.getContext(), textView.getContext().getResources().getDimension(R.dimen.size_font_11)));

                                lytDestination.addView(textView);
                            }


                            runningCount = runningCount + 1;
                        }

                        //stops
                        OTTextView txtStopsValue1 = (OTTextView) view.findViewById(R.id.txtStopsValue1);
                        OTTextView txtStopsValue2 = (OTTextView) view.findViewById(R.id.txtStopsValue2);
                        if(zonefeatureData.getMinStopsCount() == 0)
                        {
                            txtStopsValue1.setText(zonefeatureData.getNonStopLabel());
                            txtStopsValue2.setVisibility(View.GONE);
                        }

                        if(zonefeatureData.getMaxStopsCount() == 2)
                        {
                            //// TODO: 27-06-2017 required text from api to replace
                            if(!connectingZoneArr.isEmpty())
                            {
                                txtStopsValue2.setVisibility(View.VISIBLE);
                                txtStopsValue2.setText(connectingZoneArr.size() + " stop via " + bufferConnecting.toString());
                            }
                            else
                            {
                                txtStopsValue2.setVisibility(View.GONE);
                            }

                        }
                        else if(zonefeatureData.getMaxStopsCount() == 1)
                        {
                            //// TODO: 27-06-2017 required text from api to replace
                            if(!connectingZoneArr.isEmpty())
                            {
                                txtStopsValue2.setVisibility(View.VISIBLE);
                                txtStopsValue2.setText(connectingZoneArr.size() + " stop via " + bufferConnecting.toString());
                            }
                            else
                            {
                                txtStopsValue2.setVisibility(View.GONE);
                            }
                        }
                        else
                        {
                            txtStopsValue2.setText("");
                            txtStopsValue2.setVisibility(View.GONE);
                        }


                        //
                        OTTextView txtTravelMustStart = (OTTextView) view.findViewById(R.id.txtTravelMustStart);
                        if(zonefeatureData.getIsBidirectional() != 1)
                        {
                            txtTravelMustStart.setText(zonefeatureData.getUniDirectionalTitle().replace("{0}", bufferOrigin.toString()));

                            txtDirectionalArrow.setTextColor(Color.parseColor("#FF0000"));
                            txtTravelMustStart.setTextColor(Color.parseColor("#FF0000"));
                        }
                        else
                        {
                            txtTravelMustStart.setText("");
                            txtTravelMustStart.setVisibility(View.GONE);
                        }
                        lytOriginDestination.addView(view);
                    }
                }
            }
        }
    }

    private int getAllArrivingZoneArraySize(ArrayList<DepArrvZoneArray> depArrvZoneArray) {
        if(depArrvZoneArray == null)
            return 0;

        int count = 0;
        for (int index = 0; index < depArrvZoneArray.size(); index++) {
            count = count + depArrvZoneArray.get(index).getArriveZoneArr().size();
        }
        return count;
    }

    private String getArriveDisplayText(int isZoneAirportNameDisplay, ArriveZoneArr arriveZoneArr, int type)
    {
        switch (type)
        {
            case TYPE_TINY:
                return arriveZoneArr.getArriveCode();

            case TYPE_SUMMARY:
                return arriveZoneArr.getArriveCityname() + "(" + arriveZoneArr.getArriveCode() + ")";

            case TYPE_DETAIL:
                return arriveZoneArr.getArriveCityname() + "(" + arriveZoneArr.getArriveCode() + ")"
                        + (isZoneAirportNameDisplay == 1 ? "<br/><i>" + arriveZoneArr.getArriveAirportname() + "</i>" : "") + "";
        }
        return "";
    }


    private String getDepartDisplayText(int isZoneAirportNameDisplay, DepartZoneArr departZoneArr, int type)
    {
        switch (type)
        {
            case TYPE_TINY:
                return departZoneArr.getDepartCode();

            case TYPE_SUMMARY:
                return departZoneArr.getDepartCityName() + "(" + departZoneArr.getDepartCode() + ")";

            case TYPE_DETAIL:
                return departZoneArr.getDepartCityName() + "(" + departZoneArr.getDepartCode() + ")"
                        + (isZoneAirportNameDisplay == 1 ? "<br/><i>" + departZoneArr.getDepartAirportName() + "</i>": "") + "";
        }
        return "";
    }

    private String getDepartDisplayTextForStartOrigin(DepartZoneArr departZoneArr, int type)
    {
        switch (type)
        {
            case TYPE_TINY:
                return departZoneArr.getDepartCode();

            case TYPE_SUMMARY:
                return departZoneArr.getDepartCityName() + "(" + departZoneArr.getDepartCode() + ")";

            case TYPE_DETAIL:
                return departZoneArr.getDepartCityName() + "(" + departZoneArr.getDepartCode() + ")";
        }
        return "";
    }

    private String getDepartDisplayTextForConnecting(ConnectingZoneArr connectingZoneArr, int type)
    {
        switch (type)
        {
            case TYPE_TINY:
                return connectingZoneArr.getConnectingZoneCode();

            case TYPE_SUMMARY:
                return connectingZoneArr.getConnectingZoneCityName();

            case TYPE_DETAIL:
                return connectingZoneArr.getConnectingZoneCityName();
        }
        return "";
    }
}
