package com.optiontown.app.view.fragment.fpo.flightpass;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.optiontown.R;
import com.optiontown.app.utils.Utils;
import com.optiontown.app.view.fragment.BaseFragment;

/**
 * Created by amit on 16-09-2016.
 */
public class DropDownFragment extends BaseFragment
{
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_drop_down, container, false);



        addSpinner();

        addAutoComTextView();

        return view;
    }

    private void addAutoComTextView() {
        String[] languages = { "C","C++","Java","C#","PHP","JavaScript","jQuery","AJAX","JSON", "AAor" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.select_dialog_item, languages);
        //Find TextView control
        AutoCompleteTextView acTextView = (AutoCompleteTextView) view.findViewById(R.id.languages);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
        //Set the adapter
        acTextView.setAdapter(adapter);
    }

    private void addSpinner() {
        Spinner staticSpinner = (Spinner) view.findViewById(R.id.static_spinner);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(getActivity(), R.array.brew_array,
                        android.R.layout.simple_spinner_dropdown_item);

        // Specify the layout to use when the list of choices appears
        /*staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
*/
        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);



        staticSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
