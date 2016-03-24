package app.z0nen.slidemenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.sule.gaugelibrary.GaugeView;

/**
 * Created by britremel 2016
 */
public class menu1_Fragment extends Fragment {
    private View rootview;
    String tankLevelFromLogIn;
    String readingTimestamp;    //takes values from database

    private TextView gaugeValue;
    private TextView timestamp; //shows reading from database on app

    private GaugeView mGaugeView; //displays gauge with reading

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu1_layout, container, false);

        mGaugeView = (GaugeView) rootview.findViewById(R.id.gauge_view);

        mTimer.start();

        //create intent and bundle to call data from another activity
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        tankLevelFromLogIn = extras.getString("tankUpdate");  //tank levels
        readingTimestamp = extras.getString("readingTime"); //date and time of tank reading
        //format string to display 1 decimal place
        float number = Float.parseFloat(tankLevelFromLogIn);
        tankLevelFromLogIn = String.format("%.1f", number);

        //assign values to Text views
        gaugeValue = (TextView) rootview.findViewById(R.id.textViewGaugeLevel);
        timestamp = (TextView) rootview.findViewById(R.id.textViewUpdateDateTime);

        gaugeValue.setText(tankLevelFromLogIn);
        timestamp.setText(readingTimestamp);

        return rootview;
    }

    private final CountDownTimer mTimer = new CountDownTimer(30000, 1000) {

        @Override
        public void onTick(final long millisUntilFinished) {
            //assign oil level volume to gauge
            float tankLevel = Float.parseFloat(tankLevelFromLogIn);
            mGaugeView.setTargetValue(tankLevel);
        }

        @Override
        public void onFinish() {
        }
    };
}
