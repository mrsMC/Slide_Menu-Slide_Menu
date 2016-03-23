package app.z0nen.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import app.z0nen.slidemenu.MyActivity;
import app.z0nen.slidemenu.R;
import app.z0nen.slidemenu.RegisterActivity;

public class log_in_screen extends Activity {

    private EditText emailField, passwordField;
    private TextView oilLevel, updateDateTime;
    private Button viewGaugeButton, regDevButton;
    private ImageView logo;
    String oilLevelFromDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);

        emailField = (EditText) findViewById(R.id.editTextEmail);
        passwordField = (EditText) findViewById(R.id.editTextPassword);

        oilLevel = (TextView) findViewById(R.id.textViewLevel);
        updateDateTime = (TextView) findViewById(R.id.textViewUpdateDateTime);
        logo = (ImageView) findViewById(R.id.logo);
        viewGaugeButton = (Button) findViewById(R.id.btnViewGauge);
        regDevButton = (Button) findViewById(R.id.btnRegDevice);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                logo.setVisibility(View.GONE);
                viewGaugeButton.setVisibility(View.VISIBLE);
                regDevButton.setVisibility(View.VISIBLE);

            }
        };

        oilLevel.addTextChangedListener(watcher);

    }

    public void loginPost(View view){
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        new sign_in(this, oilLevel, 1).execute(email, password);


 //is this used? ********************
        oilLevelFromDataBase = oilLevel.getText().toString();
    }


//when btnGaugeView is clicked
    public void viewGaugePage(View view){

        Intent viewGaugeIntent = new Intent(log_in_screen.this,MyActivity.class);
        Bundle extras = new Bundle();
       // String tankLevelFromDatabase = oilLevel.getText().toString();
        String data = oilLevel.getText().toString();

        String []s = data.split(";");

        String tankLevelFromDatabase =s[0];
        String readingTimeFromDatabase =s[1];
        System.out.println(tankLevelFromDatabase);
        System.out.println(readingTimeFromDatabase);

        extras.putString("readingTime", readingTimeFromDatabase);
        extras.putString("tankUpdate", tankLevelFromDatabase);

        viewGaugeIntent.putExtras(extras);
        startActivity(viewGaugeIntent);
        log_in_screen.this.startActivity(viewGaugeIntent);
        log_in_screen.this.finish();
    }


//when btnRegisterDevice is clicked
    public void regDevice(View view){

        Intent regDev = new Intent(log_in_screen.this,RegisterActivity.class);
        startActivity(regDev);
        log_in_screen.this.startActivity(regDev);
        log_in_screen.this.finish();
    }

    //getting value back from gauge
    public interface AsyncResponse{
        void processFinish(String databaseValue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
