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

        valuesWoman.add(620);
        valuesWoman.add(1918);
        valuesWoman.add(6284);
        valuesWoman.add(10086);
        valuesWoman.add(11029);
        valuesWoman.add(12459);
        valuesWoman.add(13399);
        valuesWoman.add(10503);
        valuesWoman.add(8764);

        valuesMan.add(1671);
        valuesMan.add(8443);
        valuesMan.add(40257);
        valuesMan.add(65941);
        valuesMan.add(78564);
        valuesMan.add(102211);
        valuesMan.add(106240);
        valuesMan.add(86338);
        valuesMan.add(68235);

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
        mDemographicChart.setValues(valuesWoman,valuesMan);




    }

}
