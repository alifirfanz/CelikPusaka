package com.example.celikpusaka.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import com.example.celikpusaka.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class bardemo extends AppCompatActivity {

    PieChart piechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bardemo);

        piechart= findViewById(R.id.piechart);

        ArrayList<PieEntry> users = new ArrayList<>();
        users.add(new PieEntry(1,"UM"));
        users.add(new PieEntry(3,"USM"));
        users.add(new PieEntry(3,"UKM"));
        users.add(new PieEntry(1,"UUM"));
        users.add(new PieEntry(3,"UPSI"));
        users.add(new PieEntry(1,"UTeM"));
        users.add(new PieEntry(1,"UniMap"));

        /**
         users.add(new PieEntry(1,"UPM"));
         users.add(new PieEntry(1,"UniMAP"));
        users.add(new PieEntry(0,"UTM"));
         users.add(new PieEntry(1,"UMT"));
        users.add(new PieEntry(0,"UiTM"));
        users.add(new PieEntry(0,"UiAM"));
        users.add(new PieEntry(0,"UMS"));
        users.add(new PieEntry(0,"UNIMAS"));
        users.add(new PieEntry(0,"UTHM"));
        users.add(new PieEntry(0,"UMP"));
        users.add(new PieEntry(0,"USIM"));
        users.add(new PieEntry(0,"UniSZA"));
        users.add(new PieEntry(0,"UMK"));
        users.add(new PieEntry(0,"UPNM"));**/


        PieDataSet dataSet = new PieDataSet(users,"Jumlah Pengguna Setiap Universiti");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(18f);

        PieData pieData = new PieData(dataSet);
        piechart.setData(pieData);
        piechart.getDescription().setEnabled(false);
        piechart.setCenterText("Jumlah Pengguna Setiap Universiti Di Malaysia");
        piechart.animateXY(2000, 2000);

        /**
        ArrayList<BarEntry> pengiraan = new ArrayList<>();
        pengiraan.add(new BarEntry(1,1));
        pengiraan.add(new BarEntry(2,1));
        pengiraan.add(new BarEntry(3,3));
        pengiraan.add(new BarEntry(4,2));
        pengiraan.add(new BarEntry(5,1));
        pengiraan.add(new BarEntry(6,1));
        pengiraan.add(new BarEntry(7,1));

        BarDataSet barDataSet = new BarDataSet(pengiraan,"Jumlah Harta Pusaka Per Individu");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");;
        barChart.getDescription().setTextSize(16f);
        barChart.animateY(2000);
         VORDIPLOM_COLORS
         **/
    }
}
