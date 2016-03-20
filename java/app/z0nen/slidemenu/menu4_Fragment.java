package app.z0nen.slidemenu;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static app.z0nen.slidemenu.CommonUtilities.SENDER_ID;
import static app.z0nen.slidemenu.CommonUtilities.SERVER_URL;

/**
 * Created by britremel 2016
 * registration activity
 */
public class menu4_Fragment extends Fragment {

    // alert dialog manager
    AlertDialogManager alert = new AlertDialogManager();

    // Internet detector
    ConnectionDetector cd;

    // UI elements
    EditText txtName;
    EditText txtEmail;

    // Register button
    Button btnRegister;

    View rootView;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Pass RegisterActivity layout xml to the inflater and assign it to rootView.
        rootView = inflater.inflate(R.layout.activity_register, container, false);
        context = container.getContext(); // Assign your rootView to context

        cd = new ConnectionDetector(getActivity().getApplicationContext());

        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(context,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return rootView;
        }
        // Check if GCM configuration is set
        if (SERVER_URL == null || SENDER_ID == null || SERVER_URL.length() == 0
                || SENDER_ID.length() == 0) {
            // GCM sender id / server url is missing
            alert.showAlertDialog(context, "Configuration Error!",
                    "Please set your Server URL and GCM Sender ID", false);
            // stop executing code by return
            return rootView;
        }

        txtName = (EditText) rootView.findViewById(R.id.txtName);
        txtEmail = (EditText) rootView.findViewById(R.id.txtEmail);
        btnRegister= (Button) rootView.findViewById(R.id.btnRegister);

        /*
         * Click event on Register button
         * */
        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Read EditText data
                String name = txtName.getText().toString();
                String email = txtEmail.getText().toString();

                // Check if user filled the form
                if (name.trim().length() > 0 && email.trim().length() > 0) {

                //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent

                // Launch Main Activity
                    Intent i = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    // Registering user on our server
                    // Sending registration details to MainActivity
                    i.putExtra("name", name);
                    i.putExtra("email", email);
                    startActivity(i);
                    getActivity().finish();
                } else {
                    // user doesn't fill the data
                    // ask him to fill the form
                    alert.showAlertDialog(context, "Registration Error!", "Please enter your details", false);

                }
            }
        });

        return rootView;
    }
}
