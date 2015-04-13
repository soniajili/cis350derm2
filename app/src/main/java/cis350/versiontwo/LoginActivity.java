package cis350.versiontwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.parse.*;

import com.parse.ParseQuery;


public class LoginActivity extends ActionBarActivity {

    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        signInButton = (Button) findViewById(R.id.signInButton);
        final EditText emailText = (EditText) findViewById(R.id.emailText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: FIGURE THIS OUT

                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                //boolean userExists = userExists(email, password);

//                // If the user exists in the database, proceed into the app
//                if (userExists) {
//                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                    startActivity(intent);
//                // If the user does not exist in the database, go to the registration page
//                } else {
//                    Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
//                    startActivity(intent);
//                }

                ParseUser.logInInBackground(email, password, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                        }
                    }
                });

            }
        });
    }

//    private boolean userExists(String email, String password) {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery(email);
//        query.getInBackground("xWMyZ4YEGZ", new GetCallback<ParseObject>() {
//            public void done(ParseObject object, ParseException e) {
//                if (e == null) {
//                    String savedPassword = object.getString("Password");
//                    if (savedPassword.equals(password)) {
//                        return true;
//                    }
//                    else {
//                        return false;
//                    }
//                } else {
//                    return false;
//                }
//            }
//        });
//
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_collection, menu);
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

