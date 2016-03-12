package app.z0nen.slidemenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mel on 10/03/2016.
 */
public class menu2_Fragment extends Fragment implements OnItemSelectedListener {
    View rootview;
    Spinner spinner = null;
    Spinner spinner2= null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu2_layout, container, false);


        // Spinner element
        spinner = (Spinner) rootview.findViewById(R.id.spinner);
        spinner2 = (Spinner) rootview.findViewById(R.id.spinner2);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> products = new ArrayList<String>();
        products.add("Domestic Heating Oil");
        products.add("Agricultural Diesel");
        products.add("Commercial Diesel");

        List<String> orderBy = new ArrayList<String>();
        orderBy.add("Litre");
        orderBy.add("€");

        // Creating adapter for spinner
       ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, products);
        // Creating adapter for spinner2
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, orderBy);


        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        // attaching data adapter2 to spinner2
        spinner2.setAdapter(dataAdapter2);

        return rootview;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.spinner) {

            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        } else if (spinner.getId() == R.id.spinner2) {

            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();

            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }



}
