package com.optiontown.app.view.fragment.segproducts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;
import com.optiontown.app.model.segproduct.SegInputData;
import com.optiontown.app.model.segproduct.SegmentArray;
import com.optiontown.app.model.segproduct.SelectedDateData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by amit on 14-07-2017.
 */
public class SegInputDetailsFragment extends BaseFragment
{
    private View view;
    private OTTextView txtSearch;
    private AppSharedPrefs sp;
    private RadioButton rbOutbounds;
    private RadioButton rbReturn;
    private RadioButton rbBoth;
    private SegInputData segInputData;
    private RadioGroup radioGroupDirection;
    private OTTextView txtAirline;
    private OTTextView txtPNR;
    private NetworkImageView imgAirlineLogo;
    private ImageLoader imageLoader;
    private LinearLayout lytRadio;
    private OTTextView txtQuestion;
    private LinearLayout lytInputDetails;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_seg_input_details, container, false);
        sp = AppSharedPrefs.getInstance(getActivity());

        segInputData = (SegInputData) getArguments().get(getActivity().getString(R.string.key_serializable));

        getReferences();

        updateUI();

        return view;
    }

    private void updateUI()
    {
        lytInputDetails.removeAllViews();
        imgAirlineLogo.setImageUrl(segInputData.getAirlineLogo(), imageLoader);
        txtAirline.setText(segInputData.getAirlineDisplayName());
        txtPNR.setText(segInputData.getPnr());
        txtQuestion.setText(segInputData.getSelectFlightLabel());

        ArrayList<SegmentArray> listSegment = segInputData.getSegmentArray();
        for (int index = 0; index < listSegment.size(); index++)
        {
            final SegmentArray segmentArray = listSegment.get(index);
            segmentArray.setPosition(index);
            View view = getActivity().getLayoutInflater().inflate(R.layout.layout_input_details_row, null);


            LinearLayout lytJourneyType = (LinearLayout) view.findViewById(R.id.lytJourneyType);
            OTTextView txtJourneyType = (OTTextView) view.findViewById(R.id.txtJourneyType);
            OTTextView txtOrigin = (OTTextView) view.findViewById(R.id.txtOrigin);
            OTTextView txtDestination = (OTTextView) view.findViewById(R.id.txtDestination);
            OTTextView txtTime = (OTTextView) view.findViewById(R.id.txtTime);
            OTTextView txtUnavailableMessage = (OTTextView) view.findViewById(R.id.txtUnavailableMessage);

            OTTextView txtDesireDate = (OTTextView) view.findViewById(R.id.txtDesireDate);
            txtDesireDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.moveToFragment(getActivity(), new SegInputSelectFragment(), segmentArray, SegInputSelectFragment.VIEW_TYPE_OUTBOUND_DESIRE_DATE);
                }
            });

            OTTextView txtNotificationDate = (OTTextView) view.findViewById(R.id.txtNotificationDate);
            txtNotificationDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.moveToFragment(getActivity(), new SegInputSelectFragment(), segmentArray, SegInputSelectFragment.VIEW_TYPE_OUTBOUND_REBOOK_DATE);
                }
            });


            if(segmentArray.getFlightAvailabilityMsg() != null)
            {
                txtUnavailableMessage.setVisibility(View.VISIBLE);
                txtUnavailableMessage.setText(segmentArray.getFlightAvailabilityMsg());

                txtDesireDate.setVisibility(View.GONE);
                txtNotificationDate.setVisibility(View.GONE);
            }
            else
            {
                txtUnavailableMessage.setVisibility(View.GONE);

                txtDesireDate.setVisibility(View.VISIBLE);
                txtNotificationDate.setVisibility(View.VISIBLE);

                txtDesireDate.setText("" + Html.fromHtml(segmentArray.getDesiredDateLabl()));
                txtNotificationDate.setText("" + Html.fromHtml(segmentArray.getDesiredDateLabl1()));
            }

            //assign values
            txtJourneyType.setText(segmentArray.getJourneytypeLbl());
            view.setTag(/*txtJourneyType.getText().toString()*/ (index));
            txtOrigin.setText(segmentArray.getFlightDepartureName() + "(" + segmentArray.getFlightDepartureCode() + ")");
            txtDestination.setText(segmentArray.getFlightArrivalName() + "(" + segmentArray.getFlightArrivalCode() + ")");
            txtTime.setText(segmentArray.getFlightDeparturedate());


            lytInputDetails.addView(view);

            //radio buttons
            setDirectionalRadioUI(listSegment);

        }
        txtSearch.setText(segInputData.getButtonText());
    }

    private void setDirectionalRadioUI(ArrayList<SegmentArray> listSegment)
    {
        if(segInputData.getJournyCount() != 2)
        {
            lytRadio.setVisibility(View.GONE);
        }
        else
        {
            for (SegmentArray sa: listSegment)
            {
                lytRadio.setVisibility(sa.getFlightAvailabilityMsg() == null ? View.GONE : View.VISIBLE);
            }
        }
    }


    private void getReferences()
    {
        imageLoader = AppController.getInstance().getImageLoader();
        imgAirlineLogo = (NetworkImageView) view.findViewById(R.id.imgAirlineLogo);
        txtAirline = (OTTextView) view.findViewById(R.id.txtAirline);
        txtPNR = (OTTextView) view.findViewById(R.id.txtPNR);
        lytRadio = (LinearLayout) view.findViewById(R.id.lytRadio);
        txtQuestion = (OTTextView) view.findViewById(R.id.txtQuestion);
        radioGroupDirection = (RadioGroup) view.findViewById(R.id.radioGroupDirection);

        lytInputDetails = (LinearLayout) view.findViewById(R.id.lytInputDetails);


        //
        /*
        lytReturn = (LinearLayout) view.findViewById(R.id.lytReturn);
        txtReturn = (OTTextView) view.findViewById(R.id.txtReturn);
        txtReturnOrigin = (OTTextView) view.findViewById(R.id.txtReturnOrigin);
        txtReturnDestination = (OTTextView) view.findViewById(R.id.txtReturnDestination);
        txtReturnTime = (OTTextView) view.findViewById(R.id.txtReturnTime);
        txtReturnDesireDate = (HtmlTextView) view.findViewById(R.id.txtReturnDesireDate);
        txtReturnDesireDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new SegInputSelectFragment(), null, SegInputSelectFragment.VIEW_TYPE_RETURN_DESIRE_DATE);
            }
        });
        */

        /*txtReturnNotificationDate = (HtmlTextView) view.findViewById(R.id.txtReturnNotificationDate);
        txtReturnNotificationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.moveToFragment(getActivity(), new SegInputSelectFragment(), null, SegInputSelectFragment.VIEW_TYPE_RETURN_REBOOK_DATE);
            }
        });*/



        setRadioListeners();

        txtSearch = (OTTextView) view.findViewById(R.id.txtSearch);
        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidateFields())
                {
                    Utils.moveToFragment(getActivity(), new SegSelectFlightFragment(), null, 0);
                }
            }
        });
    }



    private void setRadioListeners()
    {
        //assuming that lytRadio is only visible if outbound and return both journey type are available
        rbOutbounds = (RadioButton) view.findViewById(R.id.rbOutbounds);
        rbOutbounds.setText(segInputData.getOutboundLabel());
        rbOutbounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytInputDetails.getChildAt(0).setVisibility(View.VISIBLE);
                lytInputDetails.getChildAt(1).setVisibility(View.GONE);
            }
        });

        rbReturn = (RadioButton) view.findViewById(R.id.rbReturn);
        rbReturn.setText(segInputData.getReturnLabel());
        rbReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytInputDetails.getChildAt(0).setVisibility(View.GONE);
                lytInputDetails.getChildAt(1).setVisibility(View.VISIBLE);
            }
        });

        rbBoth = (RadioButton) view.findViewById(R.id.rbBoth);
        rbBoth.setText(segInputData.getBothLabel());
        rbBoth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytInputDetails.getChildAt(0).setVisibility(View.VISIBLE);
                lytInputDetails.getChildAt(1).setVisibility(View.VISIBLE);
            }
        });
    }

    public void updateUIFromSelect(FragmentCommunicationData data)
    {
        Utils.DEBUG("SegInputDetailsFragment >> updateUIFromSelect called");

        /*txtOutboundDesireDate.setText((String)sp.get(getString(R.string.KEY_OUTBOUND_DESIRE_DATE)));
        txtOutboundNotificationDate.setText((String)sp.get(getString(R.string.KEY_OUTBOUND_NOTIFICATION_DATE)));*/

        SelectedDateData selectedDateData = data.getSelectedDateData();

        for (int index = 0; index < lytInputDetails.getChildCount(); index++) {
            View view = lytInputDetails.getChildAt(index);
            if((int) view.getTag() == selectedDateData.getPosition())
            {
                switch (selectedDateData.getViewId())
                {
                    case SegInputSelectFragment.VIEW_TYPE_OUTBOUND_DESIRE_DATE:
                        OTTextView txtDesireDate = (OTTextView) view.findViewById(R.id.txtDesireDate);
                        txtDesireDate.setText(selectedDateData.getDateWithText());
                        txtDesireDate.setTag(selectedDateData.getDateWithText());

                        OTTextView txtDesireDateError = (OTTextView) view.findViewById(R.id.txtDesireDateError);
                        txtDesireDateError.setVisibility(View.GONE);//user came after selection, so hide corresponding error message if showing

                        return;

                    case SegInputSelectFragment.VIEW_TYPE_OUTBOUND_REBOOK_DATE:
                        OTTextView txtNotificationDate = (OTTextView) view.findViewById(R.id.txtNotificationDate);
                        txtNotificationDate.setText(selectedDateData.getDateWithText());
                        txtNotificationDate.setTag(selectedDateData.getDateWithText());

                        OTTextView txtNotificationDateError = (OTTextView) view.findViewById(R.id.txtNotificationDateError);
                        txtNotificationDateError.setVisibility(View.GONE);//user came after selection, so hide corresponding error message if showing
                        return;

                }
            }
        }
    }

    private boolean isValidateFields()
    {
        boolean flagValid = true;

        if(lytRadio.getVisibility() == View.VISIBLE)
        {
            //both journey type available
            if(rbOutbounds.isChecked())
            {
                flagValid = validateEachRowView(lytInputDetails.getChildAt(0));
            }
            else if(rbReturn.isChecked())
            {
                flagValid = validateEachRowView(lytInputDetails.getChildAt(1));
            }
            else if (rbBoth.isChecked())
            {
                for (int index = 0; index < lytInputDetails.getChildCount(); index++)
                {
                    boolean flag = validateEachRowView(lytInputDetails.getChildAt(index));
                    if(flag == false)
                    {
                        flagValid = false;
                    }


                }
            }
        }
        else
        {
            for (int index = 0; index < lytInputDetails.getChildCount(); index++)
            {
                boolean flag = validateEachRowView(lytInputDetails.getChildAt(index));
                if(flag == false)
                {
                    flagValid = false;
                }
            }
        }
        return flagValid;
    }


    private boolean validateEachRowView(View view)
    {
        boolean flagValid = true;
        OTTextView txtUnavailableMessage = (OTTextView) view.findViewById(R.id.txtUnavailableMessage);

        if(txtUnavailableMessage.getVisibility() == View.VISIBLE)
        {
            //don't check just bypass
            return true;
        }

        OTTextView txtDesireDateError = (OTTextView) view.findViewById(R.id.txtDesireDateError);
        OTTextView txtDesireDate = (OTTextView) view.findViewById(R.id.txtDesireDate);
        if(txtDesireDate.getTag() == null)
        {
            txtDesireDateError.setText(segInputData.getErrorMessage());
            txtDesireDateError.setVisibility(View.VISIBLE);
            flagValid = false;
        }

        OTTextView txtNotificationDateError = (OTTextView) view.findViewById(R.id.txtNotificationDateError);
        OTTextView txtNotificationDate = (OTTextView) view.findViewById(R.id.txtNotificationDate);
        if(txtNotificationDate.getTag() == null)
        {
            txtNotificationDateError.setText(segInputData.getErrorMessage());
            txtNotificationDateError.setVisibility(View.VISIBLE);
            flagValid = false;
        }

        return flagValid;
    }
}













