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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Contact;
import com.gtsoft.meddyl.customer.model.object.Zip_Code;
import com.gtsoft.meddyl.customer.system.gtsoft.GTTextView;
import com.gtsoft.meddyl.customer.system.gtsoft.Utils;
import com.gtsoft.meddyl.customer.views.base.Tab_Controller;
import com.gtsoft.meddyl.customer.views.base.View_Controller;

import org.droidparts.widget.ClearableEditText;

import java.util.Arrays;
import java.util.List;

public class Register extends View_Controller
{
    private String first_name;
    private String last_name;
    private String user_name;
    private String password;
    private String zip_code;
    private String auth_token;
    private static final List<String> PERMISSIONS = Arrays.asList("public_profile", "email", "user_friends");

    private ClearableEditText edtFacebookZipCode;
    private ClearableEditText edtFirstName;
    private ClearableEditText edtLastName;
    private ClearableEditText edtEmail;
    private ClearableEditText edtPassword;
    private ClearableEditText edtZipCode;

    CallbackManager callbackManager;

    private Register_Async register_async = null;
    private Login_With_Facebook_Async login_with_facebook_async = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.register);

        if(debug)
            Debug();

        Intent intent = getIntent();
        system_controller = intent.getParcelableExtra("system_controller");
        customer_controller = intent.getParcelableExtra("customer_controller");
        deal_controller = intent.getParcelableExtra("deal_controller");

        screen_title = "REGISTER";
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
        edtFirstName = (ClearableEditText) findViewById(R.id.edtFirstName);
        edtLastName = (ClearableEditText) findViewById(R.id.edtLastName);
        edtEmail = (ClearableEditText) findViewById(R.id.edtEmail);
        edtPassword = (ClearableEditText) findViewById(R.id.edtPassword);
        edtZipCode = (ClearableEditText) findViewById(R.id.edtZipCode);

        if(customer_controller.getHasGpsService())
        {
            edtFacebookZipCode.setVisibility(View.GONE);
            edtZipCode.setVisibility(View.GONE);
        }
        else
        {
            edtFacebookZipCode.setVisibility(View.VISIBLE);
            edtZipCode.setVisibility(View.VISIBLE);
        }

        Button btnLoginFacebook = (Button) findViewById(R.id.btnLoginFacebook);
        btnLoginFacebook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Login_Facebook();
            }
        });

        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Register();
            }
        });

        GTTextView txvTerms = (GTTextView) findViewById(R.id.txvTerms);
        txvTerms.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Terms_Of_Service.class);
                intent.putExtra("system_controller", system_controller);
                intent.putExtra("customer_controller", customer_controller);
                intent.putExtra("deal_controller", deal_controller);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    public void Debug()
    {
        ((ClearableEditText) findViewById(R.id.edtFirstName)).setText("George");
        ((ClearableEditText) findViewById(R.id.edtLastName)).setText("Triarhos");
        ((ClearableEditText) findViewById(R.id.edtEmail)).setText("gtriarhos@hotmail.com");
        ((ClearableEditText) findViewById(R.id.edtPassword)).setText("test12");
    }

    private void Register()
    {
        first_name = edtFirstName.getText().toString().trim();
        last_name = edtLastName.getText().toString().trim();
        user_name = edtEmail.getText().toString().trim();
        password = edtPassword.getText().toString().trim();
        zip_code = edtZipCode.getText().toString().trim();

        successful = true;
        if(first_name.trim().length() == 0)
        {
            successful = false;
            error_message = "You must enter your first";
            error_title = "Need First Name";
            edtFirstName.requestFocus();
        }
        else if(last_name.trim().length() == 0)
        {
            successful = false;
            error_message = "You must enter your last name";
            error_title = "Need Last Name";
            edtLastName.requestFocus();
        }
        else if(!Utils.isEmailValid(user_name))
        {
            successful = false;
            error_message = "You must a valid email address";
            error_title = "Need Email";
            edtEmail.requestFocus();
        }
        else if(password.trim().length() == 0)
        {
            successful = false;
            error_message = "Your password must be at least 5 characters";
            error_title = "Password Incorrect";
            edtPassword.requestFocus();
        }
        else if(zip_code.length() != 5 && !customer_controller.getHasGpsService())
        {
            successful = false;
            error_message = "Your zip code is incorrect";
            error_title = "Zip Code";
            edtZipCode.requestFocus();
        }

        if(!successful)
        {
            system_error_obj = null;
            Show_Alert_Dialog(error_title, error_message);
        }
        else
        {
            register_async = new Register_Async();
            register_async.execute((Void) null);
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
            LoginManager.getInstance().logInWithReadPermissions(Register.this, PERMISSIONS);
        }
    }

    private class Register_Async extends AsyncTask<Void, Void, Boolean>
    {
        private ProgressDialog dialog;

        public Register_Async()
        {
            dialog = new ProgressDialog(Register.this);
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Registering");
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            Zip_Code zip_code_obj = new Zip_Code();
            zip_code_obj.setZipCode(zip_code);

            Contact contact_obj = new Contact();
            contact_obj.setFirstName(first_name);
            contact_obj.setLastName(last_name);
            contact_obj.setEmail(user_name);
            contact_obj.setPassword(password);

            customer_controller.getLoginLogObj().setAutoLogin(false);
            customer_controller.setZipCodeObj(zip_code_obj);
            customer_controller.setContactObj(contact_obj);
            customer_controller.Register();
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
            register_async = null;

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
            dialog = new ProgressDialog(Register.this);
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
