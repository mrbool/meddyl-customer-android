package com.gtsoft.meddyl.customer.views.object;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Contact;
import com.gtsoft.meddyl.customer.model.object.Customer;
import com.gtsoft.meddyl.customer.model.object.Zip_Code;
import com.gtsoft.meddyl.customer.system.gtsoft.GTTextView;
import com.gtsoft.meddyl.customer.system.gtsoft.LocationFinder;
import com.gtsoft.meddyl.customer.views.base.Tab_Controller;
import com.gtsoft.meddyl.customer.views.base.View_Controller;

import org.droidparts.widget.ClearableEditText;

import java.util.Arrays;
import java.util.List;

public class Login extends View_Controller
{
    private String user_name;
    private String password;
    private String zip_code;
    private String auth_token;
    private static final List<String> PERMISSIONS = Arrays.asList("public_profile", "email", "user_friends");

    CallbackManager callbackManager;
    ClearableEditText edtFacebookZipCode;

    private Login_Async login_async = null;
    private Login_With_Facebook_Async login_with_facebook_async = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login);

        if(debug)
            Debug();

        Intent intent = getIntent();
        system_controller = intent.getParcelableExtra("system_controller");
        customer_controller = intent.getParcelableExtra("customer_controller");
        deal_controller = intent.getParcelableExtra("deal_controller");

        screen_title = "LOGIN";
        left_button = "back";
        right_button = "";

        Set_Controller_Properties();

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult)
                    {
                        auth_token = AccessToken.getCurrentAccessToken().getToken();

                        login_with_facebook_async = new Login_With_Facebook_Async();
                        login_with_facebook_async.execute((Void) null);
                    }

                    @Override
                    public void onCancel()
                    {
                    }

                    @Override
                    public void onError(FacebookException exception)
                    {
                    }
                });

        edtFacebookZipCode = (ClearableEditText) findViewById(R.id.edtFacebookZipCode);
        if(customer_controller.getHasGpsService())
            edtFacebookZipCode.setVisibility(View.GONE);
        else
            edtFacebookZipCode.setVisibility(View.VISIBLE);

        Button btnLoginFacebook = (Button) findViewById(R.id.btnLoginFacebook);
        btnLoginFacebook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Login_Facebook();
            }
        });

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Login();
            }
        });

        GTTextView txvForgotPassword = (GTTextView) findViewById(R.id.txvForgotPassword);
        txvForgotPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Forgot_Password.class);
                intent.putExtra("system_controller", system_controller);
                intent.putExtra("customer_controller", customer_controller);
                intent.putExtra("deal_controller", deal_controller);
                startActivity(intent);
            }
        });
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
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedState)
    {
        super.onSaveInstanceState(savedState);
        //uiHelper.onSaveInstanceState(savedState);
    }

    public void Debug()
    {
        ((ClearableEditText) findViewById(R.id.edtEmail)).setText("gtriarhosx@hotmail.com");
        ((ClearableEditText) findViewById(R.id.edtPassword)).setText("test12");
    }

    private void Login()
    {
        user_name = ((ClearableEditText) findViewById(R.id.edtEmail)).getText().toString().trim();
        password = ((ClearableEditText) findViewById(R.id.edtPassword)).getText().toString().trim();

        successful = true;
        if(user_name.trim().length() == 0)
        {
            successful = false;
            error_message = "Please enter your email";
            error_title = "Need Email";
            ((ClearableEditText) findViewById(R.id.edtEmail)).requestFocus();
        }
        else if(password.trim().length() == 0)
        {
            successful = false;
            error_message = "Please enter your password";
            error_title = "Need Password";
            ((ClearableEditText) findViewById(R.id.edtPassword)).requestFocus();
        }

        if(!successful)
        {
            system_error_obj = null;
            Show_Alert_Dialog(error_title, error_message);
        }
        else
        {
            login_async = new Login_Async();
            login_async.execute((Void) null);
        }
    }

    private void Login_Facebook()
    {
        if(customer_controller.getHasGpsService())
            zip_code = "";
        else
            zip_code = edtFacebookZipCode.getText().toString().trim();

        if(zip_code.length() != 5 && !customer_controller.getHasGpsService())
        {
            Show_Alert_Dialog("Zip Code", "Your zip code is incorrect");
            edtFacebookZipCode.requestFocus();
        }
        else
        {
            LoginManager.getInstance().logInWithReadPermissions(Login.this, PERMISSIONS);
        }
    }

    private class Login_Async extends AsyncTask<Void, Void, Boolean>
    {
        private ProgressDialog dialog;

        public Login_Async()
        {
            dialog = new ProgressDialog(Login.this);
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Logging In");
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            Contact contact_obj = new Contact();
            contact_obj.setUserName(user_name);
            contact_obj.setPassword(password);

            customer_controller.getLoginLogObj().setAutoLogin(false);
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
        private ProgressDialog dialog;

        public Login_With_Facebook_Async()
        {
            dialog = new ProgressDialog(Login.this);
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Logging In");
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            Zip_Code zip_code_obj = new Zip_Code();
            zip_code_obj.setZipCode(zip_code);

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
