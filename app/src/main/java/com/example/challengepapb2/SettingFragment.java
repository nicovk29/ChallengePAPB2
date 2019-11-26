package com.example.challengepapb2;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    private SharedPreferences myPreferences;

    private final String INTERVAL = "INTERVAL";
    private final String NUMBER = "NUMBER";
    private final int DEFAULT_INTERVAL = 500;
    private final int DEFAULT_NUMBER = 7;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        final EditText etInterval = getActivity().findViewById(R.id.et_interval);
        final EditText etJackpot = getActivity().findViewById(R.id.et_jackpot);

        myPreferences = getActivity().getSharedPreferences(getActivity().getApplication().toString(), MODE_PRIVATE);
        int interval = myPreferences.getInt(INTERVAL, DEFAULT_INTERVAL);
        int number = myPreferences.getInt(NUMBER, DEFAULT_NUMBER);
        etInterval.setText(String.valueOf(interval));
        etJackpot.setText(String.valueOf(number));

        etInterval.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPreferences.Editor editor = myPreferences.edit();
                int interval = Integer.parseInt(String.valueOf(etInterval.getText().toString().isEmpty() ? DEFAULT_INTERVAL : etInterval.getText().toString()));
                editor.putInt(INTERVAL, interval);
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etJackpot.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPreferences.Editor editor = myPreferences.edit();
                editor.putInt(NUMBER, Integer.parseInt(String.valueOf(etJackpot.getText().toString().isEmpty() ? 7 : etJackpot.getText().toString())));
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
