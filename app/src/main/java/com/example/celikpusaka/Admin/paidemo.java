package com.example.celikpusaka.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.celikpusaka.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class paidemo extends AppCompatActivity {

    PieChart piechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paidemo);

        piechart= findViewById(R.id.piechart);

        ArrayList<PieEntry> users = new ArrayList<>();
        users.add(new PieEntry(7,"Lelaki"));
        users.add(new PieEntry(5,"Perempuan"));

        PieDataSet dataSet = new PieDataSet(users,"Jumlah Pengguna Lelaki dan Perempuan");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(18f);

        PieData pieData = new PieData(dataSet);
        piechart.setData(pieData);
        piechart.getDescription().setEnabled(false);
        piechart.setCenterText("Jumlah pengguna lelaki dan perempuan");
        piechart.animateXY(2000, 2000);
    }
}
