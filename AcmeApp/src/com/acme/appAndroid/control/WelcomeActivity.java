/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.appAndroid.control;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.acme.appAndroid.model.User;

/**
 *
 * @author Fernando
 */
public class WelcomeActivity extends Activity {

    public static final String USER_NAME = "acmeApp.USER_NAME";
    public static final String USER_LAST_NAME = "acmeApp.USER_LAST_NAME";
    public static final String USER = "acmeApp.USER";
    private TextView txtUserName;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here        
        setContentView(R.layout.welcome);
        User user = (User) getIntent().getSerializableExtra(WelcomeActivity.USER);
        this.txtUserName = (TextView) this.findViewById(R.id.txt_user_name);
        this.txtUserName.setText("Bienvenido: " + user.getLastName() + " " + user.getName());
    }

}
