package com.diegogurgel.demographic;

import android.app.Activity;
import android.os.Bundle;

import com.diegogurgel.demographicchart.DemoChart;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    DemoChart mDemographicChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDemographicChart = (DemoChart) findViewById(R.id.demo);
        List<Integer> valuesWoman = new ArrayList<Integer>();
        List<Integer> valuesMan = new ArrayList<Integer>();
        List<String> labels = new ArrayList<String>();

        valuesWoman.add(830);
        valuesWoman.add(3025);
        valuesWoman.add(10740);
        valuesWoman.add(18897);
        valuesWoman.add(23155);
        valuesWoman.add(27312);
        valuesWoman.add(28706);
        valuesWoman.add(22341);
        valuesWoman.add(19243);

        valuesMan.add(530);
        valuesMan.add(1681);
        valuesMan.add(8969);
        valuesMan.add(16424);
        valuesMan.add(20532);
        valuesMan.add(25576);
        valuesMan.add(24173);
        valuesMan.add(20145);
        valuesMan.add(19973);

        labels.add("90+");
        labels.add("80-84");
        labels.add("60-64");
        labels.add("50-54");
        labels.add("40-44");
        labels.add("30-34");
        labels.add("20-24");
        labels.add("10-14");
        labels.add("0-4");
        mDemographicChart.setLabels(labels);
        mDemographicChart.setValuesWoman(valuesWoman);
        mDemographicChart.setmValuesMan(valuesMan);




    }

}
