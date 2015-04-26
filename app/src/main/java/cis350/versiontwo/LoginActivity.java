package cis350.versiontwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends ActionBarActivity {

    Button signInButton;

    /** Display page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = (Button) findViewById(R.id.signInButton);
        final EditText emailText = (EditText) findViewById(R.id.emailText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);

//        // Enable Parse
//        Parse.enableLocalDatastore(this);
//        Parse.initialize(this, "fviaFJ9B1jQdWCCnS419jkZ8dFVquHBd1lu0Y1jF",
//                "p6dYSbB0KVF7KPvstO2ui7B32RanUEj9vmS28DLi");

        signInButton.setOnClickListener(new View.OnClickListener() {
            // Sign in when sign in button is clicked
            @Override
            public void onClick(View v) {

                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                ParseUser.logInInBackground(email, password, new LogInCallback() {
                    // Handle behavior once app connects to Parse
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // User has logged in.
                            Intent intent = new Intent(getApplicationContext(),
                                    HomeActivity.class);
                            startActivity(intent);
                        } else {
                            // Signup failed
                            Toast.makeText(getApplicationContext(),
                                "Your username or password did not match.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    /** Inflate the menu; this adds items to the action bar if it is
     * present. */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collection, menu);
        return true;
    }

    /** Handle action bar item clicks here */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

