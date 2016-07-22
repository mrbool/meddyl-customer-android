package com.gtsoft.meddyl.customer.views.object;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Contact;
import com.gtsoft.meddyl.customer.model.object.Customer;
import com.gtsoft.meddyl.customer.model.object.Customer_Search_Location_Type;
import com.gtsoft.meddyl.customer.model.object.Zip_Code;
import com.gtsoft.meddyl.customer.system.gtsoft.Utils;
import com.gtsoft.meddyl.customer.views.base.Tab_Controller;
import com.gtsoft.meddyl.customer.views.base.View_Controller;

import org.droidparts.widget.ClearableEditText;

public class Search_Settings extends View_Controller
{
    private Customer customer_obj_update;

    private TextView txvDistance;
    private SeekBar skbDistance;
    private RadioButton rbCurrentLocation;
    private RadioButton rbZipCode;
    private ClearableEditText edtZipCode;

    private Get_Customer_Profile_Async get_customer_profile_async = null;
    private Update_Customer_Settings_Async update_customer_settings_async = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_settings);

        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        Intent intent = getIntent();
        system_controller = intent.getParcelableExtra("system_controller");
        customer_controller = intent.getParcelableExtra("customer_controller");
        deal_controller = intent.getParcelableExtra("deal_controller");

        screen_title = "SETTINGS";
        left_button = "back";
        right_button = "save";

        Set_Controller_Properties();

        txvDistance = (TextView) findViewById(R.id.txvDistance);
        skbDistance = (SeekBar) findViewById(R.id.skbDistance);
        rbCurrentLocation = (RadioButton) findViewById(R.id.rbCurrentLocation);
        rbZipCode = (RadioButton) findViewById(R.id.rbZipCode);
        edtZipCode = (ClearableEditText) findViewById(R.id.edtZipCode);

        get_customer_profile_async = new Get_Customer_Profile_Async();
        get_customer_profile_async.execute((Void) null);
    }

    private void Load_Data()
    {
        Customer customer_obj = customer_controller.getCustomerObj();

        txvDistance.setText(String.valueOf(customer_obj.getDealRange()) + " mi.");

        skbDistance.setMax(system_controller.getSystemSettingsObj().getCustomerDealRangeMax() - system_controller.getSystemSettingsObj().getCustomerDealRangeMin());
        skbDistance.setProgress(customer_obj.getDealRange() - system_controller.getSystemSettingsObj().getCustomerDealRangeMin());
        skbDistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser)
            {
                txvDistance.setText(String.valueOf(system_controller.getSystemSettingsObj().getCustomerDealRangeMin() + progressValue) + " mi.");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }
        });

        if(customer_obj.getCustomerSearchLocationTypeObj().getSearchLocationTypeId() == 1)
        {
            rbCurrentLocation.setChecked(true);
        }
        else
        {
            rbZipCode.setChecked(true);
        }

        edtZipCode.setText(customer_obj.getZipCodeObj().getZipCode());

        ((TextView) findViewById(R.id.txvDistanceLabel)).setVisibility(View.VISIBLE);
        txvDistance.setVisibility(View.VISIBLE);
        skbDistance.setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.txvSearchTypeLabel)).setVisibility(View.VISIBLE);
        rbCurrentLocation.setVisibility(View.VISIBLE);
        rbZipCode.setVisibility(View.VISIBLE);
        edtZipCode.setVisibility(View.VISIBLE);
    }

    public void Save()
    {
        int search_location_type_id=0;
        int distance = system_controller.getSystemSettingsObj().getCustomerDealRangeMin() + skbDistance.getProgress();
        String zip_code = edtZipCode.getText().toString().trim();

        if(rbCurrentLocation.isChecked())
            search_location_type_id = 1;
        else if(rbZipCode.isChecked())
            search_location_type_id = 2;

        successful = true;
        if(search_location_type_id !=1 && search_location_type_id != 2)
        {
            successful = false;
            error_title = "Search Type";
            error_message = "Please select a search type";
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

            Customer_Search_Location_Type customer_search_location_type_obj = new Customer_Search_Location_Type();
            customer_search_location_type_obj.setSearchLocationTypeId(search_location_type_id);

            customer_controller.getCustomerObj().setDealRange(distance);
            customer_controller.setZipCodeObj(zip_code_obj);
            customer_controller.setCustomerSearchLocationTypeObj(customer_search_location_type_obj);

            update_customer_settings_async = new Update_Customer_Settings_Async();
            update_customer_settings_async.execute((Void) null);
        }
    }

    public void Back()
    {
        int distance = system_controller.getSystemSettingsObj().getCustomerDealRangeMin() + skbDistance.getProgress();
        int search_location_type_id=0;

        if(rbCurrentLocation.isChecked())
            search_location_type_id = 1;
        else if(rbZipCode.isChecked())
            search_location_type_id = 2;

        edited = false;
        if(distance != customer_controller.getCustomerObj().getDealRange())
        {
            edited = true;
        }
        else if(search_location_type_id != customer_controller.getCustomerObj().getCustomerSearchLocationTypeObj().getSearchLocationTypeId())
        {
            edited = true;
        }
        else if(!edtZipCode.getText().toString().equals(customer_controller.getCustomerObj().getZipCodeObj().getZipCode()))
        {
            edited = true;
        }

        if(edited)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(Search_Settings.this);
            builder.setCancelable(false);
            builder.setTitle("Cancel");
            builder.setMessage("You have unsaved changes, are you sure you want to cancel?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            finish();
                        }
                    }).show();
        }
        else
        {
            finish();
        }
    }

    private class Get_Customer_Profile_Async extends AsyncTask<Void, Void, Boolean>
    {
        private ProgressDialog dialog;

        public Get_Customer_Profile_Async()
        {
            dialog = new ProgressDialog(Search_Settings.this);
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Loading");
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
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
                if (dialog.isShowing())
                {
                    dialog.dismiss();
                }

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

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }

    private class Update_Customer_Settings_Async extends AsyncTask<Void, Void, Boolean>
    {
        private ProgressDialog dialog;

        public Update_Customer_Settings_Async()
        {
            dialog = new ProgressDialog(Search_Settings.this);
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
            customer_controller.Update_Customer_Settings();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(Search_Settings.this);
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
            get_customer_profile_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }
}
