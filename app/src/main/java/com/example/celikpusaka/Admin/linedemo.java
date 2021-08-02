package com.example.celikpusaka.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.celikpusaka.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.KeyStore;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;

public class linedemo extends AppCompatActivity {

    LineChart lineChart;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;

    BarChart barChart;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> labelsNames;

    AlertDialog alertDialog;
    ArrayList<MonthDalesData> monthDalesDataArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linedemo);


        //hooks
        barChart = findViewById(R.id.linechart);

        //create new obj
        barEntryArrayList = new ArrayList<>();
        labelsNames = new ArrayList<>();

        barEntryArrayList.clear();
        labelsNames.clear();
        fillMonthSales();
        for(int i =0;i< monthDalesDataArrayList.size();i++){
            String month = monthDalesDataArrayList.get(i).getMonth();
            int sales =monthDalesDataArrayList.get(i).getSales();
            barEntryArrayList.add(new BarEntry(i,sales));
            labelsNames.add(month);

        }

        BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Jumlah Program Setiap Bulan(Jan-Dec)");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(12f);
        Description description = new Description();
        description.setText("Bulan");
        barChart.setDescription(description);
        BarData barData = new BarData(barDataSet);
        barChart.getDescription().setTextSize(14f);
        barChart.setData(barData);

        //x axis
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelsNames));

        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(labelsNames.size());
        xAxis.setLabelRotationAngle(270);
        barChart.animateY(2000);
        barChart.invalidate();

        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                int x = barChart.getBarData().getDataSetForEntry(e).getEntryIndex((BarEntry)e);

                String month = monthDalesDataArrayList.get(x).getMonth();
                String sales = NumberFormat.getInstance().format(monthDalesDataArrayList.get(x).getSales());
                AlertDialog.Builder builder = new AlertDialog.Builder(linedemo.this);
                builder.setCancelable(true);
                View nview = LayoutInflater.from(linedemo.this).inflate(R.layout.monthly_sales_layout,null);
                TextView month_Txt =nview.findViewById(R.id.month);
                TextView sales_Txt =nview.findViewById(R.id.sales);
                month_Txt.setText(month);
                sales_Txt.setText((sales)+" Program");
                builder.setView(nview);
                alertDialog = builder.create();
                alertDialog.show();

            }
            @Override
            public void onNothingSelected() {
            }
        });

    }

    private void fillMonthSales(){
        monthDalesDataArrayList.clear();
        monthDalesDataArrayList.add(new MonthDalesData("Jan",0));
        monthDalesDataArrayList.add(new MonthDalesData("Feb",2));
        monthDalesDataArrayList.add(new MonthDalesData("Marc",1));
        monthDalesDataArrayList.add(new MonthDalesData("Apr",0));
        monthDalesDataArrayList.add(new MonthDalesData("May",0));
        monthDalesDataArrayList.add(new MonthDalesData("Jun",0));
        monthDalesDataArrayList.add(new MonthDalesData("Jul",1));
        monthDalesDataArrayList.add(new MonthDalesData("Aug",0));
        monthDalesDataArrayList.add(new MonthDalesData("Sep",1));
        monthDalesDataArrayList.add(new MonthDalesData("Oct",0));
        monthDalesDataArrayList.add(new MonthDalesData("Nov",0));
        monthDalesDataArrayList.add(new MonthDalesData("Dec",1));



        /**

       // lineChart = findViewById(R.id.linechart);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference("pengiraan");
        BarChart barChart=(BarChart) findViewById(R.id.linechart);


        ArrayList<BarEntry> pengiraan = new ArrayList<>();
        pengiraan.add(new BarEntry(1,0));
        pengiraan.add(new BarEntry(2,2));
        pengiraan.add(new BarEntry(3,1));
        pengiraan.add(new BarEntry(4,0));
        pengiraan.add(new BarEntry(5,0));
        pengiraan.add(new BarEntry(6,0));
        pengiraan.add(new BarEntry(7,1));
        pengiraan.add(new BarEntry(8,0));
        pengiraan.add(new BarEntry(9,1));
        pengiraan.add(new BarEntry(10,0));
        pengiraan.add(new BarEntry(11,0));
        pengiraan.add(new BarEntry(12,0));

        BarDataSet barDataSet = new BarDataSet(pengiraan,"Jumlah Program Setiap Bulan(Jan-Dec)");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");;
        barChart.getDescription().setTextSize(20f);
        barChart.animateY(2000);
        **/

       /** ArrayList<Entry> program = new ArrayList<>();
        program.add(new Entry(1,0));
        program.add(new Entry(2,2));
        program.add(new Entry(3,2));
        program.add(new Entry(4,0));
        program.add(new Entry(5,1));
        program.add(new Entry(6,0));
        program.add(new Entry(7,0));
        program.add(new Entry(8,0));
        program.add(new Entry(9,0));
        program.add(new Entry(10,0));
        program.add(new Entry(11,0));
        program.add(new Entry(12,0));


        LineDataSet lineDataSet = new LineDataSet( program,"Jumlah Program Harta Pusaka(Jan-Dec)");
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(18f);

        LineData lineData = new LineData(lineDataSet);
        lineChart.getDescription().setEnabled(false);
        lineChart.setData(lineData);
        lineChart.animateY(2000);
        **/


    }
}
