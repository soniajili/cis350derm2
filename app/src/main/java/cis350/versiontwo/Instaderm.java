package cis350.versiontwo;

import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
* Created by Sonia on 4/14/15.
*/

public class Instaderm extends android.app.Application {

    protected void onCreate(Bundle savedInstanceState) {
        ParseObject.registerSubclass(ParseUser.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "fviaFJ9B1jQdWCCnS419jkZ8dFVquHBd1lu0Y1jF",
                "p6dYSbB0KVF7KPvstO2ui7B32RanUEj9vmS28DLi");
    }

}