package com.example.shadow.piccollagequiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shadow.piccollagequiz.domain.model.numerals.ChineseNumeral;
import com.example.shadow.piccollagequiz.domain.model.numerals.RomanNumeral;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_ok).setOnClickListener(v -> showNumeralsParseResult());
    }

    private void showNumeralsParseResult() {
        ((TextView) findViewById(R.id.tv_notations)).setText(parseRomanToChineseNumeral(((EditText) findViewById(R.id.et_notations)).getText().toString()));
    }


    private String parseRomanToChineseNumeral(String numerals) {
        try {
            return new ChineseNumeral(new RomanNumeral(numerals).toInt()).toString();
        }catch (Exception e){
            return "Not parsable notations";
        }

    }
}
