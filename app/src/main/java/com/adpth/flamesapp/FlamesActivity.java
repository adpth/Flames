package com.adpth.flamesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;

public class FlamesActivity extends AppCompatActivity {

    String FLAMES = "FLAMES";
    char[] arr_FLAMES = FLAMES.toCharArray();

    ImageView output_img;
    Button back;

    String yourName, yourNameDisplay, partnerName, partnerNameDisplay, completeName;
    String[] arr_yourName, arr_partnerName, arr_completeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flames);

        Intent intent = getIntent();
        yourNameDisplay = intent.getStringExtra("your_name");
        partnerNameDisplay =intent.getStringExtra("your_partner");

        output_img = findViewById(R.id.output_img);
        back = findViewById(R.id.back);

        processName();
        eliminateCommonLetters();
        FlamesTest();
        displayOutput();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUi();
            }
        });
    }

    public void processName() {
        yourNameDisplay =
        yourName = yourNameDisplay.toLowerCase().replaceAll("\\s+", "");
        arr_yourName = yourName.split("");

        partnerNameDisplay =
        partnerName = partnerNameDisplay.toLowerCase().replaceAll("\\s+", "");
        arr_partnerName = partnerName.split("");
    }

    public void eliminateCommonLetters() {
        for (String i : arr_yourName) {
            for (String j : arr_partnerName) {
                if ( i.equals(j) ) {
                    arr_yourName = ArrayUtils.removeElement(arr_yourName, i);
                    arr_partnerName = ArrayUtils.removeElement(arr_partnerName, j);
                    break;
                }
            }
        }

        arr_completeName = ArrayUtils.addAll(arr_yourName, arr_partnerName);
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : arr_completeName) {
            stringBuilder.append(string);
        }

        completeName = stringBuilder.toString();
    }

    public void FlamesTest() {
        int index, flamesLength = 6;

        while (FLAMES.length() != 1) {
            index = completeName.length() % flamesLength;

            if (index == 0) {
                FLAMES = FLAMES.replace(String.valueOf(arr_FLAMES[FLAMES.length() - 1]), "");
                arr_FLAMES = FLAMES.toCharArray();
            } else {
                FLAMES = FLAMES.replace(String.valueOf(arr_FLAMES[index - 1]), "");
                FLAMES = FLAMES.substring(index - 1) + FLAMES.substring(0, index - 1);
                arr_FLAMES = FLAMES.toCharArray();
            }
            flamesLength--;
        }
    }

    public void displayOutput() {

        switch (arr_FLAMES[0]) {
            case 'F':
                output_img.setImageResource(R.drawable.friends);
                break;
            case 'L':
                output_img.setImageResource(R.drawable.love);
                break;
            case 'A':
                output_img.setImageResource(R.drawable.affection);
                break;
            case 'M':
                output_img.setImageResource(R.drawable.marriage);
                break;
            case 'E':
                output_img.setImageResource(R.drawable.enemies);
                break;
            case 'S':
                output_img.setImageResource(R.drawable.sister);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateUi();
    }

    private void updateUi() {
        Intent intent1 = new Intent(FlamesActivity.this,MainActivity.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        finish();
    }
}