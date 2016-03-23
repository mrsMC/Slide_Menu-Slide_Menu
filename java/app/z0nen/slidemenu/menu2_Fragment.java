package app.z0nen.slidemenu;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mel on 10/03/2016.
 */
public class menu2_Fragment extends Fragment implements OnItemSelectedListener {
    View rootview;
    Spinner spinnerFuelType = null;
    Spinner spinnerOrderType = null;
    Spinner spinnerOrderAmount = null;
    Button startBtn;
    String fuelType;
    String orderAmountInput;
    String orderType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu2_layout, container, false);


        // Spinner element
        spinnerFuelType = (Spinner) rootview.findViewById(R.id.spinnerFuelType);
        spinnerOrderType = (Spinner) rootview.findViewById(R.id.spinnerOrderType);
        spinnerOrderAmount = (Spinner) rootview.findViewById(R.id.spinnerOrderAmount);

        // Spinner click listener
        spinnerFuelType.setOnItemSelectedListener(this);
        spinnerOrderType.setOnItemSelectedListener(this);
        spinnerOrderAmount.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> products = new ArrayList<String>();
        products.add("");
        products.add("Domestic Heating Oil");
        products.add("Agricultural Diesel");
        products.add("Commercial Diesel");

        List<String> orderBy = new ArrayList<String>();
        orderBy.add("");
        orderBy.add("Litre");
        orderBy.add("€");

        List<String> orderAmount = new ArrayList<String>();
        orderAmount.add("");
        orderAmount.add("50");
        orderAmount.add("100");
        orderAmount.add("150");
        orderAmount.add("200");
        orderAmount.add("250");
        orderAmount.add("300");
        orderAmount.add("350");
        orderAmount.add("400");
        orderAmount.add("450");
        orderAmount.add("500");

        // Creating adapter for spinner for fuel type
        ArrayAdapter<String> dataAdapterFuelType = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, products);
        // Creating adapter for spinner for order type
        ArrayAdapter<String> dataAdapterOrderType = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, orderBy);
        // Creating adapter for spinner for order amount
        ArrayAdapter<String> dataAdapterOrderAmount = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, orderAmount);

        // Drop down layout style - list view with radio button
        dataAdapterFuelType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataAdapterOrderType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataAdapterOrderAmount.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // attaching data adapter to spinner Fuel Type
        spinnerFuelType.setAdapter(dataAdapterFuelType);
        // attaching data adapter2 to spinner order Type
        spinnerOrderType.setAdapter(dataAdapterOrderType);
        // attaching data adapter2 to spinner order Amount
        spinnerOrderAmount.setAdapter(dataAdapterOrderAmount);

        startBtn = (Button) rootview.findViewById(R.id.btnOrderNow);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });

        return rootview;
    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"info.fuelwatch@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        String emailSubject = "New Order";
        String emailMessage = "Fuel Order \n" + "Fuel Type: " + this.fuelType + "\n" +
                "Fuel Order Type: " + this.orderType + "\n" +
                "Order Amount: " + this.orderAmountInput + "\n";

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailMessage);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            getActivity().finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            //  Toast.makeText(menu2_Fragment.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Spinner spinner = (Spinner) parent;

        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.spinnerFuelType) {

            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();

            fuelType = item;
            System.out.println(fuelType);

        } else if (spinner.getId() == R.id.spinnerOrderType) {

            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();

            orderType = item;
            System.out.println(orderType);

        } else if (spinner.getId() == R.id.spinnerOrderAmount) {

            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();

            orderAmountInput = item;
            System.out.println(orderAmountInput);

        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}
