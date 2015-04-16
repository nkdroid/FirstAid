package com.nkdroid.firstaid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class LoginActivity extends ActionBarActivity {

    private EditText etUsername,etPassword;
    private Button btnLogin,btnNewRegistration;
    private ImageView bg1,logo;
    private ProgressDialog dialog;
    String c1,c2,resp;

    public static final String SOAP_ACTION = "http://tempuri.org/CheckUser";
    public static  final String OPERATION_NAME = "CheckUser";

    public static  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

    //public  final String SOAP_ADDRESS = "http://artist.somee.com/DPR.asmx";
    public static  final String SOAP_ADDRESS = "http://WebserviceDemo1.somee.com/WebService.asmx";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //check isLogin or not
        if(PrefUtils.isLoggedIn(LoginActivity.this)){
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
        initView();
    }

    private void initView() {
        etUsername= (EditText) findViewById(R.id.etUsername);
        etPassword= (EditText) findViewById(R.id.etPassword);
        btnLogin= (Button) findViewById(R.id.btnLogin);
        bg1= (ImageView) findViewById(R.id.bg1);
        logo= (ImageView) findViewById(R.id.logo);

        Glide.with(LoginActivity.this)
                .load(R.drawable.bg_thrid)
                .centerCrop()
                .into(bg1);

        Glide.with(LoginActivity.this)
                .load(R.drawable.firstaidlogosmall)
                .centerCrop()
                .into(logo);


        btnNewRegistration= (Button) findViewById(R.id.btnNewRegistration);
        btnNewRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent2);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmptyField(etUsername)){
                    Toast.makeText(LoginActivity.this,"Please Enter Username",Toast.LENGTH_LONG).show();
                } else if(isEmptyField(etPassword)){
                    Toast.makeText(LoginActivity.this,"Please Enter Password",Toast.LENGTH_LONG).show();
                } else {
                    // check from shared preference
//                    User user=PrefUtils.getLoggedIn(LoginActivity.this);
//                    PrefUtils.setLoggedIn(LoginActivity.this,true,user.getUsername(),user.getPassword());
//                    Log.e("username", user.getUsername()+"");
//                    if( user.getUsername().equals(etUsername.getText().toString().trim()) && user.getPassword().equals(etPassword.getText().toString().trim())){
//                        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Toast.makeText(LoginActivity.this,"Please Enter Valid Username or Password",Toast.LENGTH_LONG).show();
//                    }

                    c1=etUsername.getText().toString();
                    c2=etPassword.getText().toString();
                    new AsyncTask<Void,Void,Void>(){
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            dialog=new ProgressDialog(LoginActivity.this);
                            dialog.setMessage("Loading...");
                            dialog.show();
                        }

                        @Override
                        protected Void doInBackground(Void... params) {
                            resp=Call(c1,c2);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            //Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            //store in shared preference
                            if(resp.equalsIgnoreCase("1")) {
                                PrefUtils.setLoggedIn(LoginActivity.this, true, etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_LONG).show();
                            }
                        }
                    }.execute();
                }

            }
        });


    }


    public boolean isEmptyField(EditText param1) {

        boolean isEmpty = false;
        if (param1.getText() == null || param1.getText().toString().equalsIgnoreCase("")) {
            isEmpty = true;
        }
        return isEmpty;
    }

    public String Call(String a,String b)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);

        PropertyInfo p3=new PropertyInfo();
        p3.setName("email");
        p3.setValue(a);
        p3.setType(String.class);
        request.addProperty(p3);


        PropertyInfo p4=new PropertyInfo();
        p4.setName("password");
        p4.setValue(b);
        p4.setType(String.class);
        request.addProperty(p4);



        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;

        try
        {

            httpTransport.debug=true;
            httpTransport.call(SOAP_ACTION, envelope);

            response = envelope.getResponse();
        }
        catch (Exception ex)
        {
            // ex.printStackTrace();
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
            //displayExceptionMessage(ex.getMessage());
            //System.out.println(exception.getMessage());
        }
        return response.toString() ;
    }
}
