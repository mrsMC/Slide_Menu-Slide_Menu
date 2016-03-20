package app.z0nen.slidemenu;

import android.app.Fragment;
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
    private TextView gaugeValue;
    private GaugeView mGaugeView;
    //private final Random RAND = new Random();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu1_layout, container, false);



        String tankUpdate = null;
        mGaugeView = (GaugeView) rootview.findViewById(R.id.gauge_view);
                //getView().findViewById(R.id.gauge_view);
        mTimer.start();
        Bundle bundle = getActivity().getIntent().getExtras();

        gaugeValue = (TextView)rootview.findViewById(R.id.textViewGaugeLevel);

        tankLevelFromLogIn = bundle.getString(tankUpdate);

        gaugeValue.setText(tankLevelFromLogIn);

        return rootview;
    }

    private final CountDownTimer mTimer = new CountDownTimer(30000, 1000) {

        @Override
        public void onTick(final long millisUntilFinished) {
            float tankLevel = Float.parseFloat(tankLevelFromLogIn);
           // float tankLevel = 55;
            mGaugeView.setTargetValue(tankLevel);
        }

        @Override
        public void onFinish() {}
    };
}
