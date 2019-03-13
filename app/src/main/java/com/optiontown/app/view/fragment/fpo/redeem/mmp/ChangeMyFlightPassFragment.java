package com.optiontown.app.view.fragment.fpo.redeem.mmp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.optiontown.R;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.customview.OTTextView;

/**
 * Created by zafar.imam on 11-10-2016.
 */
public class ChangeMyFlightPassFragment extends Fragment implements View.OnClickListener{

    private View view;
    private LinearLayout lytChangeMyPass,lytAddUsers,lytChangePassword,lytUpdateUsers,lytInvoice;
    private OTTextView txtContinue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_change_my_flightpass, container, false);
        Utils.updateBottomBarFpoRedeemForFeatures(view, this.getClass().getName(), false);
        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        txtContinue = (OTTextView) view.findViewById(R.id.txtSave);
        txtContinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.txtSave:
            Utils.showToast(getActivity(),"just clikeddddd ..?");
                break;
        }
    }
}
