package com.acme.appAndroid.control;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.acme.appAndroid.model.User;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Login extends Activity {

    private User user;
    private EditText txtUserId;
    private EditText txtPassword;
    private Button btnOk;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        txtUserId = (EditText) this.findViewById(R.id.txt_user_id);
        txtPassword = (EditText) this.findViewById(R.id.txt_password);
        btnOk = (Button) this.findViewById(R.id.btn_login);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {

        LoginRequest lr = new LoginRequest();
        lr.execute(this.txtUserId.getText().toString(),
                this.txtPassword.getText().toString());
        /*Intent i = new Intent(Login.this, WelcomeActivity.class);
         user = new User();
         user.setName("Juan");
         user.setLastName("Perez");
         Toast.makeText(Login.this, "Cargando bienvenida", Toast.LENGTH_LONG).show();
         i.putExtra(WelcomeActivity.USER, user);
         startActivityForResult(i, 0);*/
    }

    public class LoginRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... loginData) {
            String user = "", password = "";
            if (loginData.length == 2) {
                user = loginData[0];
                password = loginData[1];
            }
            String jsonUser = "";
            try {
                URL url = new URL("http", "192.168.0.101", 8080, "/AcmeServer/webresources/login/" + user + "/" + password);
                URLConnection conn = url.openConnection();
                conn.setDoInput(true);
                conn.setAllowUserInteraction(true);
                conn.connect();
                StringBuilder sb = new StringBuilder();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                in.close();

                jsonUser = sb.toString();
            } catch (Exception e) {
                jsonUser = e.toString();
            }
            return jsonUser;
        }

        protected void onPostExecute(String jsonUser) {
            try {
                JSONObject jsonObject = new JSONObject(jsonUser);
                user = new User();
                user.setName(jsonObject.getString("userName"));
                user.setLastName(jsonObject.getString("userApelPat"));

                Intent i = new Intent(Login.this, WelcomeActivity.class);
                i.putExtra(WelcomeActivity.USER, user);
                startActivityForResult(i, 0);
            } catch (Exception ex) {
                Toast.makeText(Login.this, "Error: " + ex.toString(), Toast.LENGTH_LONG).show();
            }
        }

    }
}
