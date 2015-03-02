package com.withlovee.gridimagesearch.activities;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.withlovee.gridimagesearch.R;

import java.lang.reflect.Array;

/**
 * Created by vee on 3/1/15.
 */
public class SettingDialog extends android.support.v4.app.DialogFragment {

    private EditText etSite;
    private Spinner snSize;
    private Spinner snColor;
    private Spinner snType;
    private Button btSave;
    private String size = "none";
    private String color = "none";
    private String type = "none";
    private String site = "";

    public interface SettingDialogListener {
        void onFinishSettingDialog(String size, String color, String type, String site);
    }

    public SettingDialog(){
        // required by DialogFragment
    }

    public static SettingDialog newInstance(String size, String color, String type, String site){
        SettingDialog frag = new SettingDialog();
        frag.size = size;
        frag.color = color;
        frag.type = type;
        frag.site = site;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container);
        setupViews(view);
        getDialog().setTitle("Search Filters");
        return view;
    }

    private void setupViews(View view) {

        Context context = getActivity();

        // Size
        snSize = (Spinner) view.findViewById(R.id.snSize);
        ArrayAdapter<CharSequence> snSizeAdapter = ArrayAdapter.createFromResource(context,
                R.array.size_array, android.R.layout.simple_spinner_item);
        snSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snSize.setAdapter(snSizeAdapter);
        snSize.setSelection(snSizeAdapter.getPosition(size));

        // Color
        snColor = (Spinner) view.findViewById(R.id.snColor);
        ArrayAdapter<CharSequence> snColorAdapter = ArrayAdapter.createFromResource(context,
                R.array.color_array, android.R.layout.simple_spinner_item);
        snColorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snColor.setAdapter(snColorAdapter);
        snColor.setSelection(snColorAdapter.getPosition(color));

        // Type
        snType = (Spinner) view.findViewById(R.id.snType);
        ArrayAdapter<CharSequence> snTypeAdapter = ArrayAdapter.createFromResource(context,
                R.array.type_array, android.R.layout.simple_spinner_item);
        snTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snType.setAdapter(snTypeAdapter);
        snType.setSelection(snTypeAdapter.getPosition(type));

        // Site
        etSite = (EditText) view.findViewById(R.id.etSite);
        etSite.setText(site);

        // Button
        btSave = (Button) view.findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingDialogListener listener = (SettingDialogListener) getActivity();
                String size = snSize.getSelectedItem().toString();
                String type = snType.getSelectedItem().toString();
                String color = snColor.getSelectedItem().toString();
                String site = etSite.getText().toString();
                listener.onFinishSettingDialog(size, color, type, site);
                dismiss();
            }
        });
    }

}
