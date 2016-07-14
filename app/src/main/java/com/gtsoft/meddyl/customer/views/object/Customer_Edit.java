package com.gtsoft.meddyl.customer.views.object;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Contact;
import com.gtsoft.meddyl.customer.model.object.Customer;
import com.gtsoft.meddyl.customer.model.object.Zip_Code;
import com.gtsoft.meddyl.customer.views.base.View_Controller;
import com.gtsoft.meddyl.customer.system.gtsoft.Utils;

import org.droidparts.widget.ClearableEditText;

public class Customer_Edit extends View_Controller
{
    private Customer customer_obj_update;

    private ProgressDialog dialog;
    private ClearableEditText edtFirstName;
    private ClearableEditText edtLastName;
    private ClearableEditText edtEmail;
    private ClearableEditText edtZipCode;

    private Get_Customer_Profile_Async get_customer_profile_async = null;
    private Update_Customer_Async update_customer_async = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_edit);
        dialog = new ProgressDialog(Customer_Edit.this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        if(debug)
            Debug();

        Intent intent = getIntent();
        system_controller = intent.getParcelableExtra("system_controller");
        customer_controller = intent.getParcelableExtra("customer_controller");
        deal_controller = intent.getParcelableExtra("deal_controller");

        screen_title = "PROFILE";
        left_button = "back";
        right_button = "save";

        Set_Controller_Properties();

        edtFirstName = (ClearableEditText) findViewById(R.id.edtFirstName);
        edtLastName = (ClearableEditText) findViewById(R.id.edtLastName);
        edtEmail = (ClearableEditText) findViewById(R.id.edtEmail);
        edtZipCode = (ClearableEditText) findViewById(R.id.edtZipCode);

        get_customer_profile_async = new Get_Customer_Profile_Async();
        get_customer_profile_async.execute((Void) null);
    }

    private void Load_Data()
    {
        Contact contact_obj = customer_controller.getCustomerObj().getContactObj();

        edtFirstName.setText(contact_obj.getFirstName());
        edtLastName.setText(contact_obj.getLastName());
        edtEmail.setText(contact_obj.getEmail());
        edtZipCode.setText(contact_obj.getZipCodeObj().getZipCode());

        edtFirstName.setVisibility(View.VISIBLE);
        edtLastName.setVisibility(View.VISIBLE);
        edtEmail.setVisibility(View.VISIBLE);
        edtZipCode.setVisibility(View.VISIBLE);

        if (dialog.isShowing())
        {
            dialog.dismiss();
        }

        Hide_Keyboard();
    }

    public void Save()
    {
        String first_name = edtFirstName.getText().toString().trim();
        String last_name = edtLastName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String zip_code = edtZipCode.getText().toString().trim();

        successful = true;
        if(first_name.length() == 0)
        {
            successful = false;
            error_title = "Need First Name";
            error_message = "First name cannot be blank";
            edtFirstName.requestFocus();
        }
        else if(last_name.length() == 0)
        {
            successful = false;
            error_title = "Last Name";
            error_message = "Last name cannot be blank";
            edtLastName.requestFocus();
        }
        else if(!Utils.isEmailValid(email))
        {
            successful = false;
            error_title = "Email";
            error_message = "Please enter a valid email";
            edtEmail.requestFocus();
        }
        else if(zip_code.length() != 5)
        {
            successful = false;
            error_title = "Zip Code Incorrect";
            error_message = "Zip code is incorrect";
            edtZipCode.requestFocus();
        }

        if(!successful)
        {
            system_error_obj = null;
            Show_Alert_Dialog(error_title, error_message);
        }
        else
        {
            /* reset to this if it fails */
            customer_obj_update = customer_controller.getCustomerObj();

            Zip_Code zip_code_obj = new Zip_Code();
            zip_code_obj.setZipCode(zip_code);

            Contact contact_obj = new Contact();
            contact_obj.setFirstName(first_name);
            contact_obj.setLastName(last_name);
            contact_obj.setEmail(email);
            contact_obj.setZipCodeObj(zip_code_obj);

            customer_controller.setContactObj(contact_obj);

            update_customer_async = new Update_Customer_Async();
            update_customer_async.execute((Void) null);
        }
    }

    public void Back()
    {
        edited = false;

        if(!edtFirstName.getText().toString().equals(customer_controller.getCustomerObj().getContactObj().getFirstName()))
        {
            edited = true;
        }
        else if(!edtLastName.getText().toString().equals(customer_controller.getCustomerObj().getContactObj().getLastName()))
        {
            edited = true;
        }
        else if(!edtEmail.getText().toString().equals(customer_controller.getCustomerObj().getContactObj().getEmail()))
        {
            edited = true;
        }
        else if(!edtZipCode.getText().toString().equals(customer_controller.getCustomerObj().getContactObj().getZipCodeObj().getZipCode()))
        {
            edited = true;
        }

        if(edited)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(Customer_Edit.this);
            builder.setCancelable(false);
            builder.setTitle("Cancel");
            builder.setMessage("You have unsaved changes, are you sure you want to cancel?")
                    .setNegativeButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            finish();
                        }
                    })
                    .setPositiveButton("No", null).show();
        }
        else
        {
            finish();
        }
    }

    public void Debug()
    {

    }

    private class Get_Customer_Profile_Async extends AsyncTask<Void, Void, Boolean>
    {
        public Get_Customer_Profile_Async()
        {
        }

        @Override
        protected void onPreExecute()
        {
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            customer_controller.Get_Customer_Profile();
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
                if (successful)
                {
                    Load_Data();
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
            get_customer_profile_async = null;
        }
    }

    private class Update_Customer_Async extends AsyncTask<Void, Void, Boolean>
    {
        private ProgressDialog dialog;

        public Update_Customer_Async()
        {
            dialog = new ProgressDialog(Customer_Edit.this);
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Updating");
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            customer_controller.Update_Customer();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(Customer_Edit.this);
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
                    /* set data back to original */
                    customer_controller.setCustomerObj(customer_obj_update);

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
            update_customer_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }
}
