package com.gtsoft.meddyl.customer.views.object;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Contact;
import com.gtsoft.meddyl.customer.model.object.Contact_GPS_Log;
import com.gtsoft.meddyl.customer.model.object.Customer;
import com.gtsoft.meddyl.customer.model.object.Zip_Code;
import com.gtsoft.meddyl.customer.system.gtsoft.LocationFinder;
import com.gtsoft.meddyl.customer.views.base.Tab_Controller;
import com.gtsoft.meddyl.customer.views.base.View_Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class Main_View extends View_Controller
{
    private String action="";
    private String user_name;
    private String password;
    private String auth_token;

    private Contact_GPS_Log contact_gps_log_obj;

    private ProgressDialog dialog;

    private Login_Async login_async = null;
    private Login_With_Facebook_Async login_with_facebook_async = null;
    private Load_System_Settings_Async load_system_settings_async = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        // Add code to print out the key hash
        try
        {
            PackageInfo info = getPackageManager().getPackageInfo("com.gtsoft.meddyl.customer",PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e)
        {
        }
        catch (NoSuchAlgorithmException e)
        {
        }

        dialog = new ProgressDialog(Main_View.this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        contact_gps_log_obj = new Contact_GPS_Log();
        contact_gps_log_obj.setLatitude(0);
        contact_gps_log_obj.setLongitude(0);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                action = "login";

                Next();
            }
        });

        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                action = "register";

                Next();
            }
        });

        Auto_Login();
    }

    /* Callback received when a permissions request has been completed. */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == 123)
        {
            if(grantResults[0] == 0)
            {
                Get_Location();
            }
            else
            {
                contact_gps_log_obj.setLatitude(0);
                contact_gps_log_obj.setLongitude(0);

                customer_controller.setContactGPSLogObj(contact_gps_log_obj);
                customer_controller.setHasGpsService(false);
            }

            load_system_settings_async = new Load_System_Settings_Async();
            load_system_settings_async.execute((Void) null);

            //request_times = 0;
        }
        else
        {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //uiHelper.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        //uiHelper.onPause();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        //uiHelper.onDestroy();
    }

    @Override
    public void Next()
    {
        int has_permission = 0;

        if (Build.VERSION.SDK_INT >= 23)
        {
            has_permission = Check_Permission(Manifest.permission.ACCESS_FINE_LOCATION, false);
        }

        if(has_permission == 0)
        {
            Get_Location();

            load_system_settings_async = new Load_System_Settings_Async();
            load_system_settings_async.execute((Void) null);
        }
    }

    private void Auto_Login()
    {
        SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
        user_name = prefs.getString("user_name", null);
        password = prefs.getString("password", null);
        auth_token = prefs.getString("auth_token", null);

        if(user_name != null)
        {
            Get_Location();

            action = "auto_login";
            load_system_settings_async = new Load_System_Settings_Async();
            load_system_settings_async.execute((Void) null);
        }
        else if(auth_token != null)
        {
            Get_Location();

            action = "auto_login_facebook";
            load_system_settings_async = new Load_System_Settings_Async();
            load_system_settings_async.execute((Void) null);
        }
        else
        {
            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }

    private void Get_Location()
    {
        customer_controller.Get_Location(Main_View.this);
        Boolean has_gps_service = customer_controller.getHasGpsService();

        if((has_gps_service) && !(customer_controller.getLatitude() == 0 && customer_controller.getLongitude() == 0))
        {
            contact_gps_log_obj.setLatitude(customer_controller.getLatitude());
            contact_gps_log_obj.setLongitude(customer_controller.getLongitude());
        }

        customer_controller.setContactGPSLogObj(contact_gps_log_obj);
    }

    private class Load_System_Settings_Async extends AsyncTask<Void, Void, Boolean>
    {
        public Load_System_Settings_Async()
        {
            if(!action.equals("auto_login"))
                dialog = new ProgressDialog(Main_View.this);
        }

        @Override
        protected void onPreExecute()
        {
            if(!action.equals("auto_login"))
            {
                dialog.setMessage("Loading");
                dialog.setCancelable(false);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.show();
            }
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            system_controller.Get_System_Settings();
            successful = system_controller.getSuccessful();
            system_error_obj = system_controller.getSystemErrorObj();

            return successful;
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {
            try
            {
                if(!action.equals("auto_login"))
                {
                    if (dialog.isShowing())
                    {
                        dialog.dismiss();
                    }
                }

                if (successful)
                {
                    if(action.equals("login"))
                    {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        intent.putExtra("system_controller", system_controller);
                        intent.putExtra("customer_controller", customer_controller);
                        intent.putExtra("deal_controller", deal_controller);
                        startActivity(intent);
                    }
                    else if (action.equals("register"))
                    {
                        Intent intent = new Intent(getApplicationContext(), Register.class);
                        intent.putExtra("system_controller", system_controller);
                        intent.putExtra("customer_controller", customer_controller);
                        intent.putExtra("deal_controller", deal_controller);
                        startActivity(intent);
                    }
                    else if(action.equals("auto_login"))
                    {
                        login_async = new Login_Async();
                        login_async.execute((Void) null);
                    }
                    else if(action.equals("auto_login_facebook"))
                    {
                        login_with_facebook_async = new Login_With_Facebook_Async();
                        login_with_facebook_async.execute((Void) null);
                    }
                }
                else
                {
                    Show_Alert_Dialog_Error(Main_View.this);
                }
            }
            catch (Exception ex)
            {
                Log.i("error in async", ex.getMessage());
            }
        }

        @Override
        protected void onCancelled()
        {
            load_system_settings_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }

    private class Login_Async extends AsyncTask<Void, Void, Boolean>
    {
        public Login_Async()
        {
        }

        @Override
        protected void onPreExecute()
        {
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            Contact contact_obj = new Contact();
            contact_obj.setUserName(user_name);
            contact_obj.setPassword(password);

            customer_controller.getLoginLogObj().setAutoLogin(true);
            customer_controller.setContactObj(contact_obj);
            customer_controller.Login();
            successful = customer_controller.getSuccessful();
            system_error_obj = customer_controller.getSystemErrorObj();
            system_successful_obj = customer_controller.getSystemSuccessfulObj();

            return successful;
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {
            try
            {
                if (dialog.isShowing())
                {
                    dialog.dismiss();
                }

                if (successful)
                {
                    SharedPreferences.Editor editor = getSharedPreferences("app", MODE_PRIVATE).edit();
                    editor.putString("user_name", user_name);
                    editor.putString("password", password);
                    editor.apply();

                    system_controller.getLoginLogObj().setCustomerId(customer_controller.getCustomerObj().getCustomerId());
                    customer_controller.getLoginLogObj().setCustomerId(customer_controller.getCustomerObj().getCustomerId());
                    deal_controller.getLoginLogObj().setCustomerId(customer_controller.getCustomerObj().getCustomerId());

                    Intent intent = new Intent(getApplicationContext(), Tab_Controller.class);
                    intent.putExtra("system_controller", system_controller);
                    intent.putExtra("customer_controller", customer_controller);
                    intent.putExtra("deal_controller", deal_controller);
                    intent.putExtra("selected_tab", 0);
                    startActivity(intent);
                }
                else
                {
                    Show_Alert_Dialog("Error", system_error_obj.getMessage());
                }
            }
            catch (Exception ex)
            {
                Log.i("error in async", ex.getMessage().toString());
            }
        }

        @Override
        protected void onCancelled()
        {
            login_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }

    private class Login_With_Facebook_Async extends AsyncTask<Void, Void, Boolean>
    {
        public Login_With_Facebook_Async()
        {
        }

        @Override
        protected void onPreExecute()
        {
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            Zip_Code zip_code_obj = new Zip_Code();
            zip_code_obj.setZipCode("");

            customer_controller.setZipCodeObj(zip_code_obj);
            customer_controller.getLoginLogObj().setAutoLogin(false);
            customer_controller.getLoginLogObj().setAuthToken(auth_token);

            customer_controller.Login_With_Facebook();
            successful = customer_controller.getSuccessful();
            system_error_obj = customer_controller.getSystemErrorObj();
            system_successful_obj = customer_controller.getSystemSuccessfulObj();

            return successful;
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {
            try
            {
                if (dialog.isShowing())
                {
                    dialog.dismiss();
                }

                if (successful)
                {
                    SharedPreferences.Editor editor = getSharedPreferences("app", MODE_PRIVATE).edit();
                    editor.putString("auth_token", auth_token);
                    editor.apply();

                    system_controller.getLoginLogObj().setCustomerId(customer_controller.getCustomerObj().getCustomerId());
                    customer_controller.getLoginLogObj().setCustomerId(customer_controller.getCustomerObj().getCustomerId());
                    deal_controller.getLoginLogObj().setCustomerId(customer_controller.getCustomerObj().getCustomerId());

                    Intent intent = new Intent(getApplicationContext(), Tab_Controller.class);
                    intent.putExtra("system_controller", system_controller);
                    intent.putExtra("customer_controller", customer_controller);
                    intent.putExtra("deal_controller", deal_controller);
                    intent.putExtra("selected_tab", 0);
                    startActivity(intent);
                }
                else
                {
                    Show_Alert_Dialog("Error", system_error_obj.getMessage());
                }
            }
            catch (Exception ex)
            {
                Log.i("error in async", ex.getMessage().toString());
            }
        }

        @Override
        protected void onCancelled()
        {
            login_with_facebook_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }
}
