package com.gtsoft.meddyl.customer.views.object;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Contact;
import com.gtsoft.meddyl.customer.views.base.View_Controller;

import org.droidparts.widget.ClearableEditText;


public class Forgot_Password extends View_Controller
{
    String user_name;

    private Forgot_Password_Async forgot_password_async = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        if(debug)
            Debug();

        Intent intent = getIntent();
        system_controller = intent.getParcelableExtra("system_controller");
        customer_controller = intent.getParcelableExtra("customer_controller");
        deal_controller = intent.getParcelableExtra("deal_controller");

        screen_title = "FORGOT PASSWORD";
        left_button = "back";
        right_button = "";

        Set_Controller_Properties();

        Button btnResetPassword = (Button) findViewById(R.id.btnResetPassword);
        btnResetPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Forgot_Password();
            }
        });
    }

    private void Forgot_Password()
    {
        user_name = ((ClearableEditText) findViewById(R.id.edtEmail)).getText().toString().trim();

        successful = true;
        if(user_name.trim().length() == 0)
        {
            successful = false;
            error_message = "Please enter your email";
            error_title = "Need Email";
            ((ClearableEditText) findViewById(R.id.edtEmail)).requestFocus();
        }

        if(!successful)
        {
            system_error_obj = null;
            Show_Alert_Dialog(error_title, error_message);
        }
        else
        {
            forgot_password_async = new Forgot_Password_Async();
            forgot_password_async.execute((Void) null);
        }
    }

    public void Debug()
    {
        ((ClearableEditText) findViewById(R.id.edtEmail)).setText("gtriarhos@hotmail.com");
    }

    public class Forgot_Password_Async extends AsyncTask<Void, Void, Boolean>
    {
        private ProgressDialog dialog;

        public Forgot_Password_Async()
        {
            dialog = new ProgressDialog(Forgot_Password.this);
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Sending Email");
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            Contact contact_obj = new Contact();
            contact_obj.setUserName(user_name);

            customer_controller.setContactObj(contact_obj);
            customer_controller.Forgot_Password();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(Forgot_Password.this);
                    builder.setCancelable(false);
                    builder.setTitle("Successful");
                    builder.setMessage(system_successful_obj.getMessage())
                            .setNeutralButton("OK", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();

                                    finish();
                                }
                            }).create().show();
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
            forgot_password_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }

}
