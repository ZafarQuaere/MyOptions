package com.optiontown.app.view.fragment.fpo.review;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.optiontown.R;

import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.customize.AdvanceBooking1Array;
import com.optiontown.app.model.customize.AdvanceBooking2Array;
import com.optiontown.app.model.customize.AdvanceBookingData;
import com.optiontown.app.model.customize.AdvanceBookingDataRoot;
import com.optiontown.app.model.customize.CustomizeData;
import com.optiontown.app.model.customize.FlexibilityData;
import com.optiontown.app.model.customize.FlexibilityDataRoot;
import com.optiontown.app.model.customize.FlexibilitySetArray;
import com.optiontown.app.model.customize.NoFlexibilitySetArray;
import com.optiontown.app.model.customize.RestrictionData;
import com.optiontown.app.model.customize.RestrictionDataRoot;
import com.optiontown.app.model.customize.UsersArray;
import com.optiontown.app.model.customize.UsersDataRoot;
import com.optiontown.app.model.internationalizedata.InternationalizeData;
import com.optiontown.app.model.review.PassCMMIndexData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppController;
import com.optiontown.app.utils.AppDialogLoader;
import com.optiontown.app.utils.MyJsonObjectRequest;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTRadioButton;
import com.optiontown.app.view.customview.OTTextView;
import com.optiontown.app.view.fragment.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amit on 14-07-2016.
 */
public class CustomizeFragment extends BaseFragment
{
    private View view;
    private LinearLayout lytPassengers;
    private RadioGroup radioGroupPassengers;
    private LinearLayout lytAdvanceBooking;
    private RadioGroup radioGroupAdvanceBooking;
    private LinearLayout lytFlexibility;
    private RadioGroup radioGroupFliexibility;
    private OTTextView txtSelectedItem;
    private ImageView imgSelectedItem;
    private LinearLayout lytModifySearch;
    private LinearLayout lytExpressCheckout;
    private PassCMMIndexData passCMMIndexData;
    private CustomizeData customizeData;
    private LinearLayout lytTopCustomize;

    private final int VIEW_ADVANCE_BOOKING = 0;
    private final int VIEW_TRAVEL_FLEXIBILITY = 1;
    private final int VIEW_PASSENGER = 2;
    private final int VIEW_FM_MATRIX = 3;
    private final int VIEW_ALL = -1;
    private int view_selected = 0;
    private boolean isVisitedTravelFlexibility = false;
    private boolean isVisitedPassenger = false;
    private ImageView imgDone;
    private Communicator communicator;
    private int passIndex;
    private int cmmIndex;
    private LinearLayout lytHorizontalView;
    private LinearLayout lytFMMatrix;
    private RadioGroup radioGroupFMMatrix;
    private int aryFMMatrixIds[];
    private HorizontalScrollView svHorizontal;
    private InternationalizeData localization;
    private OTTextView txtCustomize;
    private OTTextView txtexpresscheckoutlabel;
    private OTTextView txtModifySearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_customize, container, false);

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        passCMMIndexData = ((PassCMMIndexData) getArguments().getSerializable(getString(R.string.key_serializable)));
        try {
            localization = ParseManager.getInstance().fromJSON(new JSONObject(Utils.getInternationalLanguage(getActivity())), InternationalizeData.class);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        getUIReference();

        passIndex = passCMMIndexData.getPassIndex();
        cmmIndex = passCMMIndexData.getCmmIndex();

        callAPI(VIEW_ALL, 0, 0);

        return view;
    }

    /**
     *
     * update UI
     */
    private void updateUI(int viewType, boolean flagForceDrawUI, int fId)
    {
        try
        {
            lytTopCustomize.setVisibility(View.VISIBLE);
            createHorizontalFeatureView();
            makeTextSelected(viewType, fId);

            if(viewType == VIEW_ADVANCE_BOOKING)
            {
                lytAdvanceBooking.setVisibility(View.VISIBLE);
                lytFlexibility.setVisibility(View.GONE);
                lytPassengers.setVisibility(View.GONE);
                lytFMMatrix.setVisibility(View.GONE);

                Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), customizeData.getAdvanceBookingLabel(), customizeData.getAdvanceBookingLongDesc());

                createUIForAdvanceBooking(flagForceDrawUI);

            }
            else if(viewType == VIEW_TRAVEL_FLEXIBILITY)
            {
                isVisitedTravelFlexibility = true;
                lytFlexibility.setVisibility(View.VISIBLE);
                lytAdvanceBooking.setVisibility(View.GONE);
                lytPassengers.setVisibility(View.GONE);
                lytFMMatrix.setVisibility(View.GONE);

                Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), customizeData.getDepartFlexibilityLabel(), customizeData.getDepartFlexibilityLongDesc());

                createUIForTravelFlexibility(flagForceDrawUI);
            }
            else if(viewType == VIEW_PASSENGER)
            {
                isVisitedPassenger = true;
                lytPassengers.setVisibility(View.VISIBLE);
                lytFlexibility.setVisibility(View.GONE);
                lytAdvanceBooking.setVisibility(View.GONE);
                lytFMMatrix.setVisibility(View.GONE);

                Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), customizeData.getPassengersHeadingLabel(), customizeData.getPassengersLongDescLabel());

                createUIForPassenger(flagForceDrawUI);
            }
            else if(viewType == VIEW_FM_MATRIX)
            {
                lytAdvanceBooking.setVisibility(View.GONE);
                lytFlexibility.setVisibility(View.GONE);
                lytPassengers.setVisibility(View.GONE);
                lytFMMatrix.setVisibility(View.VISIBLE);

                for (int index = 0; index < customizeData.getRestrictionDetails().size(); index++)
                {
                    if(customizeData.getRestrictionDetails().get(index).get(0).getFtId() == fId)
                    {
                        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName(), customizeData.getRestrictionDetails().get(index).get(0).getFmName(), customizeData.getRestrictionDetails().get(index).get(0).getFeature_LongTitle());
                        break;
                    }
                }
                createUIForFMMatrix(flagForceDrawUI, fId);
            }
        }
        catch (Exception e)
        {
            Utils.DEBUG("error : " + e.toString());
            e.printStackTrace();
        }
    }

    private void makeTextSelected(int viewType, int fId)
    {

        if(lytHorizontalView != null)
        {
            int scrollX = 30;
            int scrollY = 0;
            makeTextStyleNormal(lytHorizontalView);
            for (int index = 0; index < lytHorizontalView.getChildCount(); index++)
            {
                LinearLayout childAt = (LinearLayout)lytHorizontalView.getChildAt(index);
                OTTextView childAt0 = (OTTextView)childAt.getChildAt(0);
                OTTextView childAt1 = (OTTextView)childAt.getChildAt(1);
                svHorizontal.smoothScrollBy((index + 1)*scrollX, scrollY);

                if(viewType == VIEW_ADVANCE_BOOKING && childAt0.getText().toString().equals(customizeData.getAdvanceBookingLabel()))
                {
                    childAt0.setTypeface(null, Typeface.BOLD);
                    childAt0.setTextColor(Color.RED);
                    childAt1.setBackgroundColor(Color.parseColor("#ff0000"));
                    return;
                }
                else if(viewType == VIEW_TRAVEL_FLEXIBILITY && childAt0.getText().toString().equals(customizeData.getDepartFlexibilityLabel()))
                {
                    childAt0.setTypeface(null, Typeface.BOLD);
                    childAt0.setTextColor(Color.RED);
                    childAt1.setBackgroundColor(Color.parseColor("#ff0000"));
                    return;
                }
                else if(viewType == VIEW_PASSENGER && childAt0.getText().toString().equals(customizeData.getPassengersHeadingLabel()))
                {
                    childAt0.setTypeface(null, Typeface.BOLD);
                    childAt0.setTextColor(Color.RED);
                    childAt1.setBackgroundColor(Color.parseColor("#ff0000"));
                    return;
                }
                else if(viewType == VIEW_FM_MATRIX)
                {
                    for (int i = 0; i < customizeData.getRestrictionDetails().size(); i++) {
                        if(fId == customizeData.getRestrictionDetails().get(i).get(0).getFtId()
                                && childAt0.getText().toString().equals(customizeData.getRestrictionDetails().get(i).get(0).getFmName()))
                        {
                            childAt0.setTypeface(null, Typeface.BOLD);
                            childAt0.setTextColor(Color.RED);
                            childAt1.setBackgroundColor(Color.parseColor("#ff0000"));
                            return;
                        }
                    }
                }
            }
        }
    }

    private void createHorizontalFeatureView()
    {
        if(lytHorizontalView.getChildCount() == 0)
        {
            int baseFeatureCount = 3;//advance booking, flexibility and passenger
            baseFeatureCount = baseFeatureCount + (customizeData.getRestrictionDetails().size());


            for (int index = 0; index < baseFeatureCount; index++) {

                LinearLayout ll = new LinearLayout(getActivity());
                ll.setOrientation(LinearLayout.VERTICAL);


                final OTTextView textView = new OTTextView(getActivity());
                textView.setPadding(20, 20, 20, 10);
                textView.setTextColor(Color.BLACK);
                textView.setTag(false);

                //bottom selected bar in red color
                final OTTextView view = new OTTextView(getActivity());
                view.setBackgroundColor(Color.parseColor("#ffffff"));
                view.setTag(true);
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 10);
                view.setLayoutParams(param);

                //

                if(index == 0)
                {
                    textView.setText(customizeData.getAdvanceBookingLabel());
                    textView.setTypeface(null, Typeface.BOLD);//default bold
                    textView.setTextColor(Color.RED);

                    view.setBackgroundColor(Color.parseColor("#ff0000"));
                }
                else if(index == 1)
                {
                    if(isShowFlexibility(customizeData))
                    {
                        textView.setText(customizeData.getDepartFlexibilityLabel());
                    }
                    else
                    {
                        textView.setVisibility(View.GONE);
                    }
                }
                else if (index == 2)
                {
                    textView.setText(customizeData.getPassengersHeadingLabel());
                }
                else//fm matrix feature
                {
                    int val = index - customizeData.getRestrictionDetails().size();
                    textView.setText(customizeData.getRestrictionDetails().get(val).get(0).getFmName());
                }


                final int finalIndex = index;
                final int finalBaseFeatureCount = baseFeatureCount;
                ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        Utils.DEBUG("clicked : " + ((OTTextView) textView).getText().toString());

                        makeTextStyleNormal(lytHorizontalView);

                        ((OTTextView) textView).setTypeface(null, Typeface.BOLD);
                        ((OTTextView) textView).setTextColor(Color.RED);

                        view.setBackgroundColor(Color.parseColor("#ff0000"));

                        if(finalIndex == 0)
                        {
                            aryFMMatrixIds = getFMMatrixIds();
                            view_selected = VIEW_ADVANCE_BOOKING;
                            updateUI(VIEW_ADVANCE_BOOKING, false, 0);
                        }
                        else if(finalIndex == 1)
                        {
                            aryFMMatrixIds = getFMMatrixIds();
                            if(isShowFlexibility(customizeData))
                            {
                                view_selected = VIEW_TRAVEL_FLEXIBILITY;
                                updateUI(VIEW_TRAVEL_FLEXIBILITY, false, 0);
                            }
                            else
                            {
                                view_selected = VIEW_PASSENGER;
                                updateUI(VIEW_PASSENGER, false, 0);
                            }
                        }
                        else if (finalIndex == 2)
                        {
                            aryFMMatrixIds = getFMMatrixIds();
                            view_selected = VIEW_PASSENGER;
                            updateUI(VIEW_PASSENGER, false, 0);
                        }
                        else//fm matrix feature
                        {
                            aryFMMatrixIds = getFMMatrixIds();
                            int val = finalIndex - customizeData.getRestrictionDetails().size();
                            //rest
                            for (int index = 0; index < aryFMMatrixIds.length; index++) {

                                if(aryFMMatrixIds[index] == customizeData.getRestrictionDetails().get(val).get(0).getFtId())
                                {
                                    aryFMMatrixIds[index] = 0;
                                    break;
                                }
                                aryFMMatrixIds[index] = 0;
                            }
                            view_selected = VIEW_FM_MATRIX;

                            updateUI(VIEW_FM_MATRIX, false, customizeData.getRestrictionDetails().get(val).get(0).getFtId());
                        }
                    }
                });
                ll.addView(textView);
                ll.addView(view);


                lytHorizontalView.addView(ll);
            }
        }
    }

    private void createUIForFMMatrix(boolean flagForceDrawUI, int fId)
    {
        radioGroupFMMatrix.removeAllViews();
        imgSelectedItem.setImageResource(R.drawable.passenger_customize);
        imgSelectedItem.setVisibility(View.INVISIBLE);

        for (int loc = 0; loc < customizeData.getRestrictionDetails().size(); loc++)
        {
            final ArrayList<RestrictionData> restrictionData = customizeData.getRestrictionDetails().get(loc);
            if(restrictionData.get(0).getFtId() == fId)
            {
                for (int index = 0; index < restrictionData.size(); index++) {
                    View inflated = getActivity().getLayoutInflater().inflate(R.layout.layout_flight_pass_search_fm_matrix_inner_row, null);

                    LinearLayout lytHeading = (LinearLayout) inflated.findViewById(R.id.lytHeading);
                    lytHeading.setVisibility(index == 0 ? View.VISIBLE : View.GONE);

                    OTTextView txtSavingLabel = (OTTextView) inflated.findViewById(R.id.txtSavingLabel);
                    txtSavingLabel.setText(restrictionData.get(index).getSavings_Heading());

                    OTTextView txtFlexibilityLabel = (OTTextView) inflated.findViewById(R.id.txtFlexibilityLabel);
                    txtFlexibilityLabel.setText(restrictionData.get(index).getFlexibility_Heading());

                    RadioButton rbFeatureName = (RadioButton) inflated.findViewById(R.id.rbFeatureName);
                    rbFeatureName.setId(10000 + index);
                    rbFeatureName.setText(restrictionData.get(index).getFeatureName());

                    if(restrictionData.get(index).getFeature_isSelected() == 1)
                    {
                        //radioGroupFMMatrix.check(rbFeatureName.getId());
                        rbFeatureName.setChecked(true);
                        txtSelectedItem.setText(restrictionData.get(index).getFeatureName());
                    }
                    else
                    {
                        rbFeatureName.setChecked(false);
                    }
                    final int finalIndex = index;
                    rbFeatureName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked)
                            {
                                txtSelectedItem.setText(((CompoundButton) buttonView).getText().toString());
                                uncheckRestRadioButton(radioGroupFMMatrix, ((CompoundButton) buttonView).getId());

                                //call api
                                callAPI(VIEW_FM_MATRIX, restrictionData.get(finalIndex).getFtId(), restrictionData.get(finalIndex).getFvId());
                            }
                        }
                    });


                    OTTextView txtFeatureShortTitle = (OTTextView) inflated.findViewById(R.id.txtFeatureShortTitle);
                    txtFeatureShortTitle.setText(restrictionData.get(index).getFeatureShortTitle());

                    LinearLayout lytFeatureSaving = (LinearLayout) inflated.findViewById(R.id.lytFeatureSaving);
                    //change orientation as it is common layout
                    lytFeatureSaving.setOrientation(LinearLayout.VERTICAL);

                    OTTextView txtSaving = new OTTextView(lytFeatureSaving.getContext());
                    txtSaving.setText(restrictionData.get(index).getFeature_isSelected() == 1
                            ? restrictionData.get(index).getPriceLabel() : restrictionData.get(index).getFeaturePrice());
                    txtSaving.setTextColor(Color.parseColor(restrictionData.get(index).getFeature_Color()));
                    txtSaving.setTextSize(12);
                    lytFeatureSaving.addView(txtSaving);

                    OTTextView txtPerFlight = new OTTextView(lytFeatureSaving.getContext());
                    txtPerFlight.setText(restrictionData.get(index).getFeaturePerFlightLabel());
                    txtPerFlight.setTextColor(Color.BLACK);
                    txtPerFlight.setTextSize(10);
                    lytFeatureSaving.addView(txtPerFlight);




                    LinearLayout lytFeatureFlexibility = (LinearLayout) inflated.findViewById(R.id.lytFeatureFlexibility);
                    for (int i = 0; i < restrictionData.get(index).getFlexibilityIconDetails().size(); i++)
                    {
                        NetworkImageView img = new NetworkImageView(getActivity());

                        Utils.setBackground(img, getResources().getDrawable(R.drawable.flexibility));
                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(20, 20);

                        lytFeatureFlexibility.addView(img, param);
                    }
                    radioGroupFMMatrix.addView(inflated);
                }
                break;
            }
        }


    }

    private void uncheckRestRadioButton(ViewGroup radioGroupFMMatrix, int id) {
        for (int i = 0; i < radioGroupFMMatrix.getChildCount(); i++)
        {
            View child = radioGroupFMMatrix.getChildAt(i);
            if(child instanceof ViewGroup)
            {
                uncheckRestRadioButton((ViewGroup) child, id);
            }
            else if(child instanceof RadioButton)
            {
                if(child != null && id != child.getId())
                {
                    //Utils.DEBUG("child : " + child);
                    ((RadioButton)child).setChecked(false);
                }
            }
        }
    }

    private void makeTextStyleNormal(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++)
        {
            LinearLayout childAt = (LinearLayout) viewGroup.getChildAt(i);
            for (int j = 0; j < childAt.getChildCount(); j++)
            {
                View child = childAt.getChildAt(j);

                if(child instanceof ViewGroup)
                {
                    makeTextStyleNormal((ViewGroup) child);
                }

                else if(child instanceof TextView)
                {
                    boolean isBorder = (boolean)((TextView)child).getTag();
                    if(isBorder)
                    {
                        ((OTTextView)child).setBackgroundColor(Color.parseColor("#ffffff"));
                    }
                    else
                    {
                        //Utils.DEBUG("child : " + child);
                        ((OTTextView)child).setTypeface(null, Typeface.NORMAL);
                        ((OTTextView)child).setTextColor(Color.BLACK);
                    }

                }

            }
        }
    }

    /**
     * used to create UI for Passenger module
     */
    private void createUIForPassenger(boolean flagForceDrawUI)
    {
        if(radioGroupPassengers.getChildCount() > 0 && !flagForceDrawUI)
        {
            txtSelectedItem.setText(getCheckedRadioData(radioGroupPassengers));
            imgSelectedItem.setImageResource(R.drawable.passenger_customize);
            imgSelectedItem.setVisibility(View.VISIBLE);
            //already ui created don't draw
            return;
        }
        else
        {
            radioGroupPassengers.removeAllViews();
        }
        imgSelectedItem.setImageResource(R.drawable.passenger_customize);
        imgSelectedItem.setVisibility(View.VISIBLE);

        ArrayList<UsersArray> usersArray = customizeData.getUsersData().getUsersArray();

        OTTextView tv = new OTTextView(getActivity());
        radioGroupPassengers.addView(tv);
        tv.setText(customizeData.getLABLPassengersShortDescLabel());
        tv.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_13)));
        tv.setTextColor(getResources().getColor(R.color.color_font_black));
        tv.setTypeface(null, Typeface.NORMAL);

        boolean isThisSelectedData = false;
        for(final UsersArray data : usersArray)
        {
            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.HORIZONTAL);
            radioGroupPassengers.addView(ll);

            OTRadioButton radioButton = new OTRadioButton(getActivity());
            radioButton.setText(data.getUsersShowValue());
            radioButton.setTag(data.getUsersShowValue());
            radioButton.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_12)));
            radioButton.setTextColor(getResources().getColor(R.color.color_font_black));
            radioButton.setTypeface(null, Typeface.NORMAL);
            radioButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(((OTRadioButton)v).isChecked())
                    {
                        callAPI(VIEW_PASSENGER, data.getUsersShowId(), 0);
                    }
                }

            });

            ll.addView(radioButton);
            if(data.getUserChecked().equals("1"))
            {
                radioButton.setChecked(true);
                txtSelectedItem.setText(data.getUserSelectedLabel());
                isThisSelectedData = true;
            }

            //--
            addColoredTextView(ll, data.getUsersShowValueLabel(), data.getUserChecked(), isThisSelectedData);
            //--
        }
    }


    /**
     * used to create UI for Flexibility module
     */
    private void createUIForTravelFlexibility(boolean flagForceDrawUI)
    {
        if(radioGroupFliexibility.getChildCount() > 0 && !flagForceDrawUI)
        {
            //already ui created don't draw
            txtSelectedItem.setText(getCheckedRadioData(radioGroupFliexibility));
            imgSelectedItem.setImageResource(R.drawable.travel_flexibility_customize);
            imgSelectedItem.setVisibility(View.VISIBLE);
            return;
        }
        else
        {
            radioGroupFliexibility.removeAllViews();
        }
        imgSelectedItem.setImageResource(R.drawable.travel_flexibility_customize);
        imgSelectedItem.setVisibility(View.VISIBLE);

        FlexibilityData flexibilityData = customizeData.getFlexibilityData();
        ArrayList<FlexibilitySetArray> flexibilitySetArray = flexibilityData.getFlexibilitySetArray();
        ArrayList<NoFlexibilitySetArray> noFlexibilitySetArray = flexibilityData.getNoFlexibilitySetArray();

        //--Not Flexibility
        OTTextView tvNoFlexibility = new OTTextView(getActivity());
        radioGroupFliexibility.addView(tvNoFlexibility);
        tvNoFlexibility.setText(noFlexibilitySetArray.get(0).getFlexibilityNotExistInputLabel());
        tvNoFlexibility.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_14)));
        tvNoFlexibility.setTextColor(getResources().getColor(R.color.color_font_black));
        tvNoFlexibility.setTypeface(null, Typeface.NORMAL);

        boolean isThisSelectedData = false;
        for(final NoFlexibilitySetArray data : noFlexibilitySetArray)
        {
            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.HORIZONTAL);
            radioGroupFliexibility.addView(ll);

            OTRadioButton radioButton = new OTRadioButton(getActivity());
            radioButton.setText(data.getNotFlexibilitySetRangeData());
            radioButton.setTag(data.getNotFlexibilitySetRangeData());
            radioButton.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_12)));
            radioButton.setTextColor(getResources().getColor(R.color.color_font_black));
            radioButton.setTypeface(null, Typeface.BOLD);
            radioButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(((OTRadioButton)v).isChecked())
                    {
                        callAPI(VIEW_TRAVEL_FLEXIBILITY, data.getNotFlexibilitySetRangeId(), 0);
                    }
                }

            });


            if(data.getNotFlexibilitySetChecked().equals("1"))
            {
                radioButton.setChecked(true);
                txtSelectedItem.setText(data.getNotFlexibilitySetRangeData());
                isThisSelectedData = true;
            }
            ll.addView(radioButton);

            //--
            addColoredTextView(ll, data.getNotFlexibilitySetRangeValue(), data.getNotFlexibilitySetChecked(), isThisSelectedData);
            //--

            OTTextView tvBottom = new OTTextView(getActivity());
            tvBottom.setText(data.getNotFlexibilitySetRangeNote());
            tvBottom.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_10)));
            tvBottom.setTextColor(getResources().getColor(R.color.color_font_gray));
            tvBottom.setTypeface(null, Typeface.NORMAL);
            tvBottom.setPadding((int) Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.dp_64)), 0, 0, 0);
            radioGroupFliexibility.addView(tvBottom);
        }

        //--Flexibility
        OTTextView tvFlexibility = new OTTextView(getActivity());
        radioGroupFliexibility.addView(tvFlexibility);
        tvFlexibility.setText(flexibilitySetArray.get(0).getFlexibilitySetExistInputLabel());
        tvFlexibility.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_14)));
        tvFlexibility.setTextColor(getResources().getColor(R.color.color_font_black));
        tvFlexibility.setTypeface(null, Typeface.NORMAL);
        tvFlexibility.setPadding(0, (int) Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.dp_30)), 0, 0);

        for(final FlexibilitySetArray data : flexibilitySetArray)
        {
            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.HORIZONTAL);
            radioGroupFliexibility.addView(ll);

            OTRadioButton radioButton = new OTRadioButton(getActivity());
            radioButton.setText(data.getFlexibilitySetRangeData());
            radioButton.setTag(data.getFlexibilitySetRangeData());
            radioButton.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_12)));
            radioButton.setTextColor(getResources().getColor(R.color.color_font_black));
            radioButton.setTypeface(null, Typeface.BOLD);
            radioButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(((OTRadioButton)v).isChecked())
                    {
                        callAPI(VIEW_TRAVEL_FLEXIBILITY, data.getFlexibilitySetRangeId(), 0);
                    }
                }

            });


            if(data.getFlexibilitySetCheck().equals("1"))
            {
                radioButton.setChecked(true);
                txtSelectedItem.setText(data.getFlexibilitySetRangeData());
                isThisSelectedData = true;
            }
            ll.addView(radioButton);

            //--
            addColoredTextView(ll, data.getIncludedPriceLabel(), data.getFlexibilitySetCheck(), isThisSelectedData);
            //--

            OTTextView tvBottom = new OTTextView(getActivity());
            tvBottom.setText(data.getFlexibilitySetValue());
            tvBottom.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_10)));
            tvBottom.setTextColor(getResources().getColor(R.color.color_font_gray));
            tvBottom.setTypeface(null, Typeface.NORMAL);
            tvBottom.setPadding((int) Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.dp_64)), 0, 0, 0);
            radioGroupFliexibility.addView(tvBottom);


        }

    }


    /**
     * used to create UI for Advance Booking module
     */
    private void createUIForAdvanceBooking(boolean flagForceDrawUI)
    {
        if(radioGroupAdvanceBooking.getChildCount() > 0 && !flagForceDrawUI)
        {
            //already ui created don't draw
            txtSelectedItem.setText(getCheckedRadioData(radioGroupAdvanceBooking));
            imgSelectedItem.setImageResource(R.drawable.earlybook_icon);
            imgSelectedItem.setVisibility(View.VISIBLE);
            return;
        }
        else
        {
            radioGroupAdvanceBooking.removeAllViews();
        }
        imgSelectedItem.setImageResource(R.drawable.earlybook_icon);
        imgSelectedItem.setVisibility(View.VISIBLE);

        AdvanceBookingData advanceBookingData = customizeData.getAdvanceBookingData();
        ArrayList<AdvanceBooking1Array> advanceBooking1Array = advanceBookingData.getAdvanceBooking1Array();
        ArrayList<AdvanceBooking2Array> advanceBooking2Array = advanceBookingData.getAdvanceBooking2Array();


        OTTextView txtBookingMainLabel1 = new OTTextView(getActivity());
        radioGroupAdvanceBooking.addView(txtBookingMainLabel1);
        txtBookingMainLabel1.setText(advanceBooking1Array.get(0).getPassAdvanceBookCloserLabel());
        txtBookingMainLabel1.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_14)));
        txtBookingMainLabel1.setTextColor(getResources().getColor(R.color.color_font_black));
        txtBookingMainLabel1.setTypeface(null, Typeface.BOLD);

        boolean isThisSelectedData = false;
        for(final AdvanceBooking1Array data: advanceBooking1Array)
        {
            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.HORIZONTAL);
            radioGroupAdvanceBooking.addView(ll);

            OTRadioButton radioButton = new OTRadioButton(getActivity());
            radioButton.setText(data.getAdvanceBookingValue());
            radioButton.setTag(data.getAdvanceBookingValue());
            radioButton.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_12)));
            radioButton.setTextColor(getResources().getColor(R.color.color_font_black));
            radioButton.setTypeface(null, Typeface.NORMAL);
            radioButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(((OTRadioButton)v).isChecked())
                    {
                        callAPI(VIEW_ADVANCE_BOOKING, data.getAdvanceBookingId(), 0);
                    }
                }

            });


            if(data.getAdvanceBookingChecked().equals("1"))
            {
                radioButton.setChecked(true);
                txtSelectedItem.setText(data.getAdvanceBookingSelectedValueLabel());
                isThisSelectedData = true;
            }
            ll.addView(radioButton);

            //--
            addColoredTextView(ll, data.getAdvanceBookingPriceValue(), data.getAdvanceBookingChecked(), isThisSelectedData);
            //--


        }


        //--
        OTTextView txtBookingMainLabel2 = new OTTextView(getActivity());
        radioGroupAdvanceBooking.addView(txtBookingMainLabel2);
        txtBookingMainLabel2.setText(advanceBooking2Array.get(0).getPassAdvanceBookEarlyLabel());
        txtBookingMainLabel2.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_14)));
        txtBookingMainLabel2.setTextColor(getResources().getColor(R.color.color_font_black));
        txtBookingMainLabel2.setTypeface(null, Typeface.BOLD);
        txtBookingMainLabel2.setPadding(0, (int) Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.dp_30)), 0, 0);



        for(final AdvanceBooking2Array data: advanceBooking2Array)
        {
            LinearLayout ll = new LinearLayout(getActivity());
            ll.setOrientation(LinearLayout.HORIZONTAL);

            radioGroupAdvanceBooking.addView(ll);

            OTRadioButton radioButton = new OTRadioButton(getActivity());
            radioButton.setText(data.getAdvanceBookingValue());
            radioButton.setTag(data.getAdvanceBookingValue());
            radioButton.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_12)));
            radioButton.setTextColor(getResources().getColor(R.color.color_font_black));
            radioButton.setTypeface(null, Typeface.NORMAL);
            radioButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(((OTRadioButton)v).isChecked())
                    {
                        callAPI(VIEW_ADVANCE_BOOKING, data.getAdvanceBookingId(), 0);
                    }
                }

            });



            if(data.getAdvanceBookingChecked().equals("1"))
            {
                radioButton.setChecked(true);
                txtSelectedItem.setText(data.getAdvanceBookingValue());
                isThisSelectedData = true;
            }
            ll.addView(radioButton);

            //--
            addColoredTextView(ll, data.getAdvanceBookingPriceValue(), data.getAdvanceBookingChecked(), isThisSelectedData);
            //--


        }
    }

    /**
     * used to add colored textview
     * @param ll
     * @param text
     * @param strChecked
     * @param isThisSelectedData
     */
    private void addColoredTextView(LinearLayout ll, String text, String strChecked, boolean isThisSelectedData)
    {
        OTTextView tv = new OTTextView(getActivity());
        tv.setText(text);
        tv.setTextSize(Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.size_font_12)));
        tv.setPadding((int) Utils.convertPixelToDp(getActivity(), getResources().getDimension(R.dimen.dp_10)), 0, 0, 0);
        if(isThisSelectedData){
            if(strChecked.equals("1"))
            {
                tv.setTextColor(getResources().getColor(R.color.color_font_black));
            }
            else
            {
                tv.setTextColor(getResources().getColor(R.color.color_font_green));
            }
        }
        else
        {
            tv.setTextColor(getResources().getColor(R.color.color_font_pale_blue));
        }
        tv.setTypeface(null, Typeface.BOLD);
        ll.addView(tv);
    }


    /**
     * common api calling method for all type
     * @param viewType
     * @param id1
     */
    private void callAPI(final int viewType, final int id1, final int id2)
    {
        String tag_json_obj = "json_obj_req";
        String url = "";
        switch (viewType)
        {
            case VIEW_ADVANCE_BOOKING:
                url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_GET_LOGIN) + getString(R.string.URL_SEARCH_ADVANCE_BOOKING)
                        + "&passIndex=" + passIndex + "&CMMIndex=" + cmmIndex + "&adv=" + id1;
                break;

            case VIEW_TRAVEL_FLEXIBILITY:
                url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_GET_LOGIN) + getString(R.string.URL_TRAVEL_FLEXIBILITY)
                        + "&passIndex=" + passIndex + "&CMMIndex=" + cmmIndex + "&departWind=" + id1;
                break;

            case VIEW_PASSENGER:
                url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_CUSTOMIZE_PAX_CHANGES)
                        + "&passIndex=" + passIndex + "&CMMIndex=" + cmmIndex + "&noOfPax=" + id1;
                break;

            case VIEW_FM_MATRIX:
                url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_CustomizeFm)
                        + "&passIndex=" + passIndex + "&CMMIndex=" + cmmIndex + "&ftId=" + id1 + "&fvId=" + id2;
                break;

            case VIEW_ALL:
                url = getString(R.string.URL_BASE) + getString(R.string.URL_METHOD_SELLER_LIST) + getString(R.string.URL_FPO_MOBILE_CUSTOMIZE)
                        + "&passIndex=" + passCMMIndexData.getPassIndex() + "&CMMIndex=" + passCMMIndexData.getCmmIndex();
                break;
        }




        final JSONObject requestObject = new JSONObject();

        final AppDialogLoader loader = AppDialogLoader.getLoader(getActivity());
        loader.show();

        MyJsonObjectRequest jsonObjReq = new MyJsonObjectRequest(
                false,
                getActivity(),
                Request.Method.POST,
                url,
                requestObject,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        if(response == null)
                        {
                            return;
                        }
                        Utils.DEBUG("onResponse() called : " + response.toString());

                        switch (viewType)
                        {
                            case VIEW_ADVANCE_BOOKING:
                                AdvanceBookingDataRoot advanceBookingDataRoot = ParseManager.getInstance().fromJSON(response, AdvanceBookingDataRoot.class);
                                customizeData.setAdvanceBookingData(advanceBookingDataRoot.getAdvanceBookingData());
                                passCMMIndexData.setPassIndex(advanceBookingDataRoot.getPassIndex());
                                passCMMIndexData.setCmmIndex(advanceBookingDataRoot.getCmmIndex());
                                passIndex = passCMMIndexData.getPassIndex();
                                cmmIndex = passCMMIndexData.getCmmIndex();

                                updateUI(view_selected, true, 0);
                                break;

                            case VIEW_PASSENGER:
                                UsersDataRoot usersDataRoot = ParseManager.getInstance().fromJSON(response, UsersDataRoot.class);
                                customizeData.setUsersData(usersDataRoot.getUsersData());
                                passCMMIndexData.setPassIndex(usersDataRoot.getPassIndex());
                                passCMMIndexData.setCmmIndex(usersDataRoot.getCmmIndex());
                                passIndex = passCMMIndexData.getPassIndex();
                                cmmIndex = passCMMIndexData.getCmmIndex();
                                updateUI(view_selected, true, 0);
                                break;

                            case VIEW_TRAVEL_FLEXIBILITY:
                                FlexibilityDataRoot flexibilityDataRoot = ParseManager.getInstance().fromJSON(response, FlexibilityDataRoot.class);
                                customizeData.setFlexibilityData(flexibilityDataRoot.getFlexibilityData());
                                passCMMIndexData.setPassIndex(flexibilityDataRoot.getPassIndex());
                                passCMMIndexData.setCmmIndex(flexibilityDataRoot.getCmmIndex());
                                passIndex = passCMMIndexData.getPassIndex();
                                cmmIndex = passCMMIndexData.getCmmIndex();
                                updateUI(view_selected, true, 0);
                                break;

                            case VIEW_FM_MATRIX:
                                RestrictionDataRoot restrictionDataRoot = ParseManager.getInstance().fromJSON(response, RestrictionDataRoot.class);

                                ArrayList<ArrayList<RestrictionData>> restrictionDetails = customizeData.getRestrictionDetails();
                                for (int index = 0; index < restrictionDetails.size(); index++) {
                                    if(restrictionDetails.get(index).get(0).getFtId() == id1)
                                    {
                                        //only update selected fm feature
                                        restrictionDetails.set(index, restrictionDataRoot.getRestrictionDetails().get(0));
                                        break;
                                    }
                                }
                                passCMMIndexData.setPassIndex(restrictionDataRoot.getPassIndex());
                                passCMMIndexData.setCmmIndex(restrictionDataRoot.getCmmIndex());
                                passIndex = passCMMIndexData.getPassIndex();
                                cmmIndex = passCMMIndexData.getCmmIndex();

                                updateUI(VIEW_FM_MATRIX, true, id1);//fvId
                                break;

                            case VIEW_ALL:
                                customizeData = ParseManager.getInstance().fromJSON(response, CustomizeData.class);
                                aryFMMatrixIds = getFMMatrixIds();
                                updateUI(view_selected, false, 0);
                                break;
                        }

                        loader.dismiss();
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Utils.ERROR("Error: " + error);
                Utils.showToast(getActivity(), getString(R.string.warning_common_error_message));
                loader.dismiss();
            }
        }
        );

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }




    /**
     * used to get tag of checked radio button
     * @param radioGroup
     * @return
     */
    private String getCheckedRadioData(RadioGroup radioGroup)
    {
        for (int index = 0; index < radioGroup.getChildCount(); index++)
        {
            View childAt = radioGroup.getChildAt(index);

            if(childAt instanceof LinearLayout)
            {
                LinearLayout lyt = (LinearLayout) childAt;
                for (int i = 0; i < lyt.getChildCount(); i++)
                {
                    View radio = lyt.getChildAt(i);

                    if(radio instanceof OTRadioButton)
                    {
                        if(((OTRadioButton)radio).isChecked())
                        {
                            return (String)((OTRadioButton)radio).getTag();
                        }
                    }
                }
            }


        }
        return "";
    }

    public void updateHelpLayout(FragmentCommunicationData message)
    {
        OTTextView txtHelp = (OTTextView) view.findViewById(R.id.txtHelp);
        txtHelp.setMovementMethod(new ScrollingMovementMethod());
        txtHelp.setText((Html.fromHtml(message.getHelp().trim())).toString());
        txtHelp.setVisibility(message.isShowHelp() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onAttach(Activity activity) {

        Utils.DEBUG("CustomizeFragment >> onAttach(Activity) called");
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }

    /**
     * take references
     */
    private void getUIReference()
    {
        lytTopCustomize = (LinearLayout) view.findViewById(R.id.lytTopCustomize);
        lytTopCustomize.setVisibility(View.GONE);

        txtSelectedItem = (OTTextView) view.findViewById(R.id.txtSelectedItem);
        imgSelectedItem = (ImageView) view.findViewById(R.id.imgSelectedItem);
        imgDone = (ImageView) view.findViewById(R.id.imgDone);
        imgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view_selected + 1 >= VIEW_FM_MATRIX)
                {
                    for (int index = 0; index < aryFMMatrixIds.length; index++) {
                        if(aryFMMatrixIds[index] != 0)
                        {
                            updateUI(VIEW_FM_MATRIX, false, aryFMMatrixIds[index]);
                            aryFMMatrixIds[index] = 0;
                            return;
                        }
                    }
                    //all visited, time to checkout
                    checkout();
                }
                else
                {
                    if(view_selected + 1 == VIEW_TRAVEL_FLEXIBILITY)
                    {
                        if(isShowFlexibility(customizeData))
                        {
                            view_selected = VIEW_TRAVEL_FLEXIBILITY;
                            updateUI(view_selected, false, 0);
                        }
                        else
                        {
                            view_selected = VIEW_PASSENGER;
                            updateUI(view_selected, false, 0);
                        }
                    }
                    else if(view_selected + 1 == VIEW_PASSENGER)
                    {
                        view_selected = VIEW_PASSENGER;
                        updateUI(view_selected, false, 0);
                    }
                }
            }
        });

        lytPassengers = (LinearLayout) view.findViewById(R.id.lytPassengers);
        radioGroupPassengers = (RadioGroup) view.findViewById(R.id.radioGroupPassengers);

        lytAdvanceBooking = (LinearLayout) view.findViewById(R.id.lytAdvanceBooking);
        radioGroupAdvanceBooking = (RadioGroup) view.findViewById(R.id.radioGroupAdvanceBooking);

        lytFlexibility = (LinearLayout) view.findViewById(R.id.lytFlexibility);
        radioGroupFliexibility = (RadioGroup) view.findViewById(R.id.radioGroupFliexibility);

        lytModifySearch = (LinearLayout) view.findViewById(R.id.lytModifySearch);
        lytModifySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.clearBackstackTillFlightPassFragment(getActivity());


            }
        });

        /*lytCustomizeSecond = (LinearLayout) view.findViewById(R.id.lytCustomizeSecond);
        lytCustomizeSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });*/

        lytExpressCheckout = (LinearLayout) view.findViewById(R.id.lytExpressCheckout);
        lytExpressCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout();
            }
        });

        svHorizontal = (HorizontalScrollView) view.findViewById(R.id.svHorizontal);
        lytHorizontalView  = (LinearLayout) view.findViewById(R.id.lytHorizontalView);

        lytFMMatrix = (LinearLayout) view.findViewById(R.id.lytFMMatrix);
        radioGroupFMMatrix = (RadioGroup) view.findViewById(R.id.radioGroupFMMatrix);

        txtModifySearch = (OTTextView) view.findViewById(R.id.txtModifySearch);
        txtModifySearch.setText(localization.getLABL_Modify_Search_Label());
        txtexpresscheckoutlabel = (OTTextView) view.findViewById(R.id.txtexpresscheckoutlabel);
        txtexpresscheckoutlabel.setText(localization.getLABLGetTGPFPODirectConfirmButtonLabel());
        txtCustomize = (OTTextView) view.findViewById(R.id.txtCustomize);
        txtCustomize.setText(localization.getLABLProcessStatusBarFPOCustomizeLabel());
    }

    /**
     * call this method to checkout
     */
    private void checkout() {
        //finish this fragment, move to ReviewFragment and call its Buy method
        Utils.clearRecentBackStack(getActivity());

        FragmentCommunicationData data = new FragmentCommunicationData();
        data.setFragmentName(new ReviewFragment().getClass().getName());
        data.setCallBuy(true);
        data.setCmmIndex(cmmIndex);
        data.setPassIndex(passIndex);


        communicator.onResponse(data);
    }


    public int[] getFMMatrixIds()
    {
        int ary[] = new int[customizeData.getRestrictionDetails().size()];
        for (int index = 0; index < customizeData.getRestrictionDetails().size(); index++)
        {
            ary[index] = customizeData.getRestrictionDetails().get(index).get(0).getFtId();
        }
        return ary;
    }

    /**
     * getIsPassFlexibilityDisplay  is not returning valid output so need to apply check on flexibility data
     * @param customizeData
     * @return
     */
    private boolean isShowFlexibility(CustomizeData  customizeData){
        if (customizeData == null || customizeData.getFlexibilityData() == null)
            return  false;
        if (customizeData.getIsPassFlexibilityDisplay() == 1 && (!customizeData.getFlexibilityData().getFlexibilitySetArray().isEmpty()
                || !customizeData.getFlexibilityData().getNoFlexibilitySetArray().isEmpty())){
            return true;
        }
        return  false;
    }
}
