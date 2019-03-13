package com.optiontown.app.view.fragment.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.optiontown.R;
import com.optiontown.app.model.fpogetpass.PassObject;
import com.optiontown.app.parser.ParseManager;
import com.optiontown.app.utils.AppSharedPrefs;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.fragment.BaseFragment;

/**
 * Created by amit on 29-06-2016.
 */
public class MakePaymentFragment extends BaseFragment
{
    private View view;
    private AppSharedPrefs sp;
    private PassObject passObjectData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_make_payment, container, false);

        //initialise shared prefs manager
        sp = AppSharedPrefs.getInstance(getActivity());

        passObjectData = ((PassObject) getArguments().getSerializable(getString(R.string.key_serializable)));

        Utils.DEBUG("passObject json : " + ParseManager.getInstance().toJSON(passObjectData));

        Utils.updateActionBarForFeatures(getActivity(), this.getClass().getName());

        getUIReference();

        updateUI();

        return view;

    }

    private void updateUI() {

    }

    private void getUIReference() {
    }

}
