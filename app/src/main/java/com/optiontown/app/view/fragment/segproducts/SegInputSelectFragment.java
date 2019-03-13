package com.optiontown.app.view.fragment.segproducts;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.format.DateFormat;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.optiontown.R;
import com.optiontown.app.interfaces.Communicator;
import com.optiontown.app.model.segproduct.JourneyChoiceArray;
import com.optiontown.app.model.segproduct.JourneyChoiceArraySecond;
import com.optiontown.app.model.segproduct.SegInputData;
import com.optiontown.app.model.segproduct.SegmentArray;
import com.optiontown.app.model.segproduct.SelectedDateData;
import com.optiontown.app.model.selectproduct.FragmentCommunicationData;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.baseui.MainActivity;
import com.optiontown.app.view.customview.OTRadioButton;
import com.optiontown.app.view.customview.OTTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by amit on 14-07-2017.
 */
public class SegInputSelectFragment extends Fragment
{
    public static final int VIEW_TYPE_OUTBOUND_DESIRE_DATE = 1;
    public static final int VIEW_TYPE_OUTBOUND_REBOOK_DATE = 2;
    private View view;
    private ImageView imgCalendar;
    private LinearLayout lytCalendar;
    //private OTTextView txtDate;
    private int viewType;
    private LinearLayout lytRadioDate;
    private ImageView imgDone;
    private Communicator communicator;
    private CustomRadioGroup radioGroupDate;
    private RadioButton rbSameDay;
    private RadioButton rbOneDayAfter;
    private SegmentArray segmentArray;
    private OTTextView txtSelectedItem;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_seg_input_select, container, false);

        segmentArray = (SegmentArray) getArguments().get(getActivity().getString(R.string.key_serializable));
        viewType = (int) getArguments().getInt(getString(R.string.key_view_type));

        getReferences();

        updateUI();

        return view;
    }

    private void updateUI()
    {
        if(viewType == VIEW_TYPE_OUTBOUND_DESIRE_DATE)
        {
            Utils.updateActionBarForFeatures(getActivity(), new SegInputSelectFragment().getClass().getName(), removeBrTag(segmentArray.getDesiredDateLabl()), segmentArray.getConvenienceLablPart1());

            OTTextView txtQuestion = (OTTextView) view.findViewById(R.id.txtQuestion);
            txtQuestion.setText(segmentArray.getConvenienceLablPart2().trim());

            ArrayList<JourneyChoiceArray> journeyChoiceArray = segmentArray.getJourneyChoiceArray();
            for (int index = 0; index < journeyChoiceArray.size(); index++)
            {
                RadioButton rb = new OTRadioButton(getActivity());
                rb.setText(journeyChoiceArray.get(index).getChoicLabel() + "        " + journeyChoiceArray.get(index).getChoiceDate());
                rb.setTextSize(Utils.convertPixelToDp(getActivity(), getActivity().getResources().getDimension(R.dimen.size_font_13)));
                rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        RadioButton radioButton = (RadioButton) buttonView;
                        if(isChecked)
                        {
                            txtSelectedItem.setText(radioButton.getText().toString());
                        }
                    }
                });
                radioGroupDate.addView(rb);
            }

            View view = getActivity().getLayoutInflater().inflate(R.layout.layout_desire_date, null);
            view.setTag(segmentArray.getSomeOtherDayLbl());
            final RadioButton rbOtherDate = (RadioButton) view.findViewById(R.id.rbOtherDate);
            final OTTextView txtDate = (OTTextView) view.findViewById(R.id.txtDate);

            rbOtherDate.setText(segmentArray.getSomeOtherDayLbl());
            rbOtherDate.setTag(segmentArray.getSomeOtherDayLbl());//same as view
            rbOtherDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    RadioButton radioButton = (RadioButton) buttonView;
                    if(isChecked)
                    {
                        txtSelectedItem.setText(getDisplayDate(txtDate.getText().toString()));
                    }
                }
            });



            txtDate.setText(segmentArray.getSomeOtherDayValue());

            ImageView imgCalendar = (ImageView) view.findViewById(R.id.imgCalendar);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datePicker(segmentArray.getSomeOtherDayValue(), txtDate, txtSelectedItem);
                }
            });

            radioGroupDate.addView(view);

        }
        else if(viewType == VIEW_TYPE_OUTBOUND_REBOOK_DATE)
        {
            Utils.updateActionBarForFeatures(getActivity(), new SegInputSelectFragment().getClass().getName(), removeBrTag(segmentArray.getDesiredDateLabl1()), segmentArray.getConvenienceLabl1Part1());

            OTTextView txtQuestion = (OTTextView) view.findViewById(R.id.txtQuestion);
            txtQuestion.setText(segmentArray.getConvenienceLabl1Part2().trim());

            ArrayList<JourneyChoiceArraySecond> journeyChoiceArraySecond = segmentArray.getJourneyChoiceArraySecond();
            for (int index = 0; index < journeyChoiceArraySecond.size(); index++)
            {
                RadioButton rb = new OTRadioButton(getActivity());
                rb.setText(journeyChoiceArraySecond.get(index).getChoicLabel() + " " + journeyChoiceArraySecond.get(index).getBeforeTravalLabl());
                rb.setTextSize(Utils.convertPixelToDp(getActivity(), getActivity().getResources().getDimension(R.dimen.size_font_12)));
                rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        RadioButton radioButton = (RadioButton) buttonView;
                        if(isChecked)
                        {
                            txtSelectedItem.setText(radioButton.getText().toString());
                        }
                    }
                });
                radioGroupDate.addView(rb);
            }
        }

    }

    private String getDisplayDate(String date)//MMM-dd-yyyy
    {
        try
        {
            Date d = Utils.convertToDate_MMMddyyyy(date);
            String format = new SimpleDateFormat("MMM dd").format(d.getTime());
            return format;
        }
        catch (Exception e)
        {
            return date;
        }
    }

    private String removeBrTag(String desiredDateLabl) {
        if(desiredDateLabl == null)
            return null;

        return desiredDateLabl.replace("<b>", "").replace("</b>", "");
    }

    private void getReferences()
    {
        txtSelectedItem = (OTTextView) view.findViewById(R.id.txtSelectedItem);
        txtSelectedItem.setText("");
        radioGroupDate = (CustomRadioGroup) view.findViewById(R.id.radioGroupDate);
        /*rbSameDay = (RadioButton) view.findViewById(R.id.rbSameDay);
        rbOneDayAfter = (RadioButton) view.findViewById(R.id.rbOneDayAfter);
        lytRadioDate = (LinearLayout) view.findViewById(R.id.lytRadioDate);
        lytCalendar = (LinearLayout) view.findViewById(R.id.lytCalendar);
        lytCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(txtDate);
            }
        });
        imgCalendar = (ImageView) view.findViewById(R.id.imgCalendar);
        txtDate = (OTTextView) view.findViewById(R.id.txtDate);*/

        imgDone = (ImageView) view.findViewById(R.id.imgDone);
        imgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!txtSelectedItem.getText().toString().trim().equals(""))
                {
                    communicateToFragment();
                    ((MainActivity)getActivity()).onBackPressed();
                }
            }
        });
    }

    private void communicateToFragment() {
        FragmentCommunicationData data = new FragmentCommunicationData();
        data.setFragmentName((new SegInputDetailsFragment()).getClass().getName());

        SelectedDateData d = new SelectedDateData();
        d.setDate("");
        d.setDateWithText(getCheckedRadioText(radioGroupDate));
        d.setViewId(viewType);
        d.setPosition(segmentArray.getPosition());

        data.setSelectedDateData(d);

        communicator.onResponse(data);
    }

    private String getCheckedRadioText(CustomRadioGroup radioGroupDate) {
        if(radioGroupDate == null)
            return "";

        for (int index = 0; index < radioGroupDate.getChilds().size(); index++) {
            Checkable checkable = (Checkable) radioGroupDate.getChilds().get(index);
            if(checkable.isChecked())
            {
                View v = (View) checkable;
                Object tag1 = v.getTag();
                if(tag1 != null && ((String) tag1).equals(segmentArray.getSomeOtherDayLbl()))
                {
                    for (int i = 0; i < radioGroupDate.getChildCount(); i++)
                    {
                        View childAt = radioGroupDate.getChildAt(i);
                        Object tag =  childAt.getTag();
                        if(tag != null && ((String)tag).equals(segmentArray.getSomeOtherDayLbl()))
                        {
                            OTTextView txtDate = (OTTextView) childAt.findViewById(R.id.txtDate);
                            return getDisplayDate(txtDate.getText().toString());
                        }
                    }
                }
                return ((RadioButton)radioGroupDate.getChilds().get(index)).getText().toString();
            }
        }
        return "";
    }

    @Override
    public void onAttach(Activity activity) {
        communicator = (Communicator) activity;
        super.onAttach(activity);
    }

    private void datePicker(String someOtherDayValue, final OTTextView txtDate, final OTTextView txtSelectedItem) {
        // Process to get Current Date
        final Calendar c = Calendar.getInstance(Locale.getDefault());
        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view,
                                          int yearSelected, int monthSelected,
                                          int daySelected) {
                        // Display Selected date in textbox
                        //String dob = checkDigit(dayOfMonth) + "-" + checkDigit(monthOfYear + 1) + "-" + year;
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String dateInString = (daySelected) + "/" + (monthSelected + 1) + "/" + yearSelected;;
                        Date date = null;
                        try {
                            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy", Utils.getLocalForCommunication());
                            date = formatter.parse(dateInString);
                            System.out.println(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String monthName = (String) DateFormat.format("MMM", date);
                        txtDate.setText(monthName + "-" + daySelected + "-" + yearSelected);
                        txtSelectedItem.setText(getDisplayDate(txtDate.getText().toString()));

                    }
                }, c.get(Calendar.YEAR),  c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dpd.show();
        dpd.getDatePicker().setMinDate(Utils.convertToDate_MMMddyyyy(someOtherDayValue).getTime());
        /*dpd.getDatePicker().setMaxDate(
                System.currentTimeMillis() - 1000 + getDaysBefore(mMBChangeFlightCurrentData.getBooking_Days_Value())
                        + getDaysBefore(mMBChangeFlightCurrentData.getMax_Range_Days()));*/
    }

    public void updateHelpLayout(FragmentCommunicationData message)
    {
        OTTextView txtHelp = (OTTextView) view.findViewById(R.id.txtHelp);
        txtHelp.setMovementMethod(new ScrollingMovementMethod());
        txtHelp.setText((Html.fromHtml(message.getHelp().trim())).toString());
        txtHelp.setVisibility(message.isShowHelp() ? View.VISIBLE : View.GONE);
    }
}
