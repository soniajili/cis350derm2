package cis350.versiontwo;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.*;

/**
 * Created by Sonia on 3/18/15.
 */
public class RegistrationActivity extends ActionBarActivity {
    Spinner genderSelection;
    Spinner practiceSettingSelection;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Add gender information
        genderSelection = (Spinner) findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSelection.setAdapter(genderAdapter);

        // Add country information
        // TODO: Ensure that final doesn't limit the capabilities of textView
        final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_country);
        String[] countries = getResources().getStringArray(R.array.country_array);
        ArrayAdapter<String> countryAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(countryAdapter);

        // Add practice setting information
        practiceSettingSelection = (Spinner) findViewById(R.id.setting_spinner);
        ArrayAdapter<CharSequence> settingAdapter = ArrayAdapter.createFromResource(this,
                R.array.setting_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        practiceSettingSelection.setAdapter(settingAdapter);

        register = (Button) findViewById(R.id.registerButton);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "fviaFJ9B1jQdWCCnS419jkZ8dFVquHBd1lu0Y1jF",
                "p6dYSbB0KVF7KPvstO2ui7B32RanUEj9vmS28DLi");

        final EditText email = (EditText) findViewById(R.id.emailLabel);
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText year = (EditText) findViewById(R.id.yearBirth);
        final EditText password = (EditText) findViewById(R.id.passwordText);

        boolean allChecked = false;

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                ParseObject testObject = new ParseObject("TestObject");
//                testObject.put("foo", "bar");
//                testObject.saveInBackground();

                String gender = genderSelection.getSelectedItem().toString();
                String country = textView.getText().toString();
                String setting = practiceSettingSelection.getSelectedItem().toString();
                String username = email.getText().toString();
                String fullname = name.getText().toString();
                String yearbirth = year.getText().toString();
                String userpassword = password.getText().toString();

                if ((gender == "") || (country == "") || (setting == "") || (username ==
                        "") || (fullname == "") || (yearbirth == "") || (userpassword ==
                        "")) {
                    Toast.makeText(getApplicationContext(), "Please fill out all fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ParseUser newuser = new ParseUser(); // TODO: FIGURE OUT OBJECT NAMES

                    newuser.setUsername(username);
                    newuser.setPassword(userpassword);
                    newuser.setEmail(username);

                    newuser.put("Gender", gender);
                    newuser.put("Country", country); //TODO: ASK ABOUT RETRIEVING COUNTRY INFO
                    newuser.put("Setting", setting);
                    newuser.put("Name", fullname);
                    newuser.put("Birthyear", yearbirth);
                    //newuser.put("Password", userpassword);


                    //newuser.saveInBackground();

                    newuser.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                // Hooray! Let them use the app now.
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration failed.",
                                        Toast.LENGTH_SHORT).show();
                                // Sign up didn't succeed. Look at the ParseException
                                // to figure out what went wrong
                            }
                        }
                    });

                    if (practiceSettingSelection.getSelectedItem().toString().equals("Dermatologist")) {
                        Intent intent = new Intent(getApplicationContext(), PracticeSettingActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), ViewOnlyPracticeSettingActivity.class);
                        startActivity(intent);
                    }


                }

            }
        });

    }


}