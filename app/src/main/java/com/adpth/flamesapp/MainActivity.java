package com.adpth.flamesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    EditText edit_yourName, edit_partnerName;
    Button btn_test;

    String FLAMES = "FLAMES";
    char[] arr_FLAMES = FLAMES.toCharArray();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_yourName = findViewById(R.id.input_your_name);
        edit_partnerName = findViewById(R.id.input_partner_name);

        btn_test = findViewById(R.id.flame_test);

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                assert inputMethodManager != null;
                inputMethodManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                resetValues();

                if (edit_yourName.getText().toString().trim().isEmpty()) return;
                if (edit_partnerName.getText().toString().trim().isEmpty()) return;

                start();

            }
        });
    }

    private void start() {
        Intent intent = new Intent(MainActivity.this,FlamesActivity.class);
        intent.putExtra("your_name",edit_yourName.getText().toString());
        intent.putExtra("your_partner",edit_partnerName.getText().toString());
        startActivity(intent);
    }

    public void resetValues() {
        FLAMES = getString(R.string.FLAMES);
        arr_FLAMES = FLAMES.toCharArray();
    }


}