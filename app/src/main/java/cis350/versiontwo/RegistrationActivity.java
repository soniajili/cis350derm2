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

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Sonia on 3/18/15.
 */
public class RegistrationActivity extends ActionBarActivity implements View.OnClickListener {
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
        final RadioGroup userkind = (RadioGroup) findViewById(R.id.user);
        final RadioButton submission = (RadioButton) findViewById(R.id.submissionUser);
        final RadioButton viewonly = (RadioButton) findViewById(R.id.viewUser);

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

                int submissionid = submission.getId();
                int viewonlyid = viewonly.getId();

                int choice = userkind.getCheckedRadioButtonId();

                if ((gender == "") || (country == "") || (setting == "") || (username ==
                        "") || (fullname == "") || (yearbirth == "") || (userpassword ==
                        "") || ((choice != submissionid) && (choice != viewonlyid))) {
                    Toast.makeText(getApplicationContext(), "Please fill out all fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ParseObject newuser = new ParseObject(username); // TODO: FIGURE OUT OBJECT NAMES

                    newuser.put("Gender", gender);
                    newuser.put("Country", country); //TODO: ASK ABOUT RETRIEVING COUNTRY INFO
                    newuser.put("Setting", setting);
                    newuser.put("Name", fullname);
                    newuser.put("Birthyear", yearbirth);
                    newuser.put("Password", userpassword);

                    if (submissionid == choice) {
                        newuser.put("ID", "submission");
                    }
                    else if (submissionid == choice) {
                        newuser.put("ID", "viewonly");
                    }

                    newuser.saveInBackground();

                    Intent intent = new Intent(getApplicationContext(), DisclaimerActivity.class);
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    public void onClick(View v) {

        // Get the spinner selection
        //int gender = genderSelection.getSelectedItemPosition();
        RadioButton viewButton = (RadioButton) findViewById(R.id.viewUser);
        RadioButton submissionButton = (RadioButton) findViewById(R.id.submissionUser);

        if (viewButton.isChecked()) {
            // TODO: EDIT THIS LATER
            viewButton.setChecked(true);
            submissionButton.setChecked(false);
        } else {
            // TODO: EDIT THIS LATER
            submissionButton.setChecked(true);
            viewButton.setChecked(false);
        }

    }


}