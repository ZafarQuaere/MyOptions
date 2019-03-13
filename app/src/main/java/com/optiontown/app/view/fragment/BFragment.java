package com.optiontown.app.view.fragment;



        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.View;

        import com.optiontown.app.utils.Utils;

/**
 * Created by amit on 16-09-2016.
 */
public abstract class BFragment extends Fragment
{
    public abstract void onBackEventPre();
    public abstract void onBackEventPost();
    public abstract void onFocusEvent();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Utils.hideKeyboard(getActivity(), view);
        super.onViewCreated(view, savedInstanceState);
    }
}
