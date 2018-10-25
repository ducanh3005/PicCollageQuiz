package com.example.shadow.piccollagequiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.shadow.piccollagequiz.domain.model.numerals.ChineseNumeral;
import com.example.shadow.piccollagequiz.domain.model.numerals.RomanNumeral;
import com.example.shadow.piccollagequiz.domain.model.rectangle.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_quiz01_ok).setOnClickListener(v -> showNumeralsParseResult());
        findViewById(R.id.tv_quiz02_ok).setOnClickListener(v -> showRandomRectangleTest());
        findViewById(R.id.tv_quiz03_ok).setOnClickListener(v -> showPacManDevFlow());
        findViewById(R.id.tv_quiz04_ok).setOnClickListener(v -> openWebActivity());
    }

    private void showNumeralsParseResult() {
        ((TextView) findViewById(R.id.tv_notations)).setText(parseRomanToChineseNumeral(((EditText) findViewById(R.id.et_notations)).getText().toString()));
    }


    private String parseRomanToChineseNumeral(String numerals) {
        try {
            return new ChineseNumeral(new RomanNumeral(numerals).toInt()).toString();
        } catch (Exception e) {
            return "Not parsable notations";
        }
    }

    private void showRandomRectangleTest() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        @SuppressLint("InflateParams") View convertView = getLayoutInflater().inflate(R.layout.layout_list, null);
        alertDialog.setView(convertView);
        ListView lv = convertView.findViewById(R.id.lv_content);
        SimpleAdapter adapter = new SimpleAdapter(this, generateTestCases(), R.layout.layout_list_item, new String[]{"rect1", "rect2", "result"}
                , new int[]{R.id.tv_rect1, R.id.tv_rect2, R.id.tv_intersecting});
        lv.setAdapter(adapter);
        alertDialog.show();
    }

    private List<Map<String, String>> generateTestCases() {
        final int MAX_LENGTH = 1000;
        List<Map<String, String>> testCases = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> testcase = new HashMap<>(3);
            Rectangle rectangle1 = randomRectangle(MAX_LENGTH);
            Rectangle rectangle2 = randomRectangle(MAX_LENGTH);
            testcase.put("rect1", getRectangleRect(rectangle1));
            testcase.put("rect2", getRectangleRect(rectangle2));
            testcase.put("result", String.valueOf(rectangle1.intersecting(rectangle2)).toUpperCase());
            testCases.add(testcase);
        }
        return testCases;
    }

    public static Rectangle randomRectangle(int max_length) {
        int ax = (int) (Math.random() * max_length);
        int ay = (int) (Math.random() * max_length);
        return new Rectangle(ax, ay, ax + (int) (Math.random() * max_length), ay + (int) (Math.random() * max_length));
    }

    public static String getRectangleRect(Rectangle rectangle) {
        int[] rect = rectangle.getRect();
        return "[" + rect[0] + "," + rect[1] + "," + rect[2] + "," + rect[3] + ']';
    }

    private void showPacManDevFlow() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/document/d/14zS0hcBv4PFJg9irL63kEpVZE41FTI_UFWOazKu1KcQ/edit?usp=sharing"));
        startActivity(browserIntent);
    }

    private void openWebActivity() {
        startActivity(new Intent(this,WebActivity.class));
    }
}
