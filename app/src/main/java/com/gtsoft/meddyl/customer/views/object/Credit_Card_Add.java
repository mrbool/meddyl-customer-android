package com.gtsoft.meddyl.customer.views.object;

import android.app.Activity;
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

import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Contact;
import com.gtsoft.meddyl.customer.model.object.Credit_Card;
import com.gtsoft.meddyl.customer.model.object.Merchant_Contact;
import com.gtsoft.meddyl.customer.system.gtsoft.TextWatcherCreditCardExp;
import com.gtsoft.meddyl.customer.system.gtsoft.TextWatcherCreditCardNumber;
import com.gtsoft.meddyl.customer.system.gtsoft.TextWatcherPhone;
import com.gtsoft.meddyl.customer.system.gtsoft.Utils;
import com.gtsoft.meddyl.customer.views.base.View_Controller;

import org.droidparts.widget.ClearableEditText;

public class Credit_Card_Add extends View_Controller
{
    private ClearableEditText edtCardNumber;
    private ClearableEditText edtExpirationDate;

    private Credit_Card_Add_Async credit_card_add_async = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit_card_add);

        if(debug)
            Debug();

        Intent intent = getIntent();
        system_controller = intent.getParcelableExtra("system_controller");
        customer_controller = intent.getParcelableExtra("customer_controller");
        deal_controller = intent.getParcelableExtra("deal_controller");

        screen_title = "ADD CARD";
        left_button = "back";
        right_button = "";

        Set_Controller_Properties();

        edtCardNumber = (ClearableEditText) findViewById(R.id.edtCardNumber);
        edtCardNumber.addTextChangedListener(new TextWatcherCreditCardNumber(edtCardNumber));

        edtExpirationDate = (ClearableEditText) findViewById(R.id.edtExpirationDate);
        edtExpirationDate.addTextChangedListener(new TextWatcherCreditCardExp(edtExpirationDate));

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Add_Card();
            }
        });
    }


    public void Add_Card()
    {
        ClearableEditText edtCardHolderName = (ClearableEditText) findViewById(R.id.edtCardHolderName);
        ClearableEditText edtSecurityCode = (ClearableEditText) findViewById(R.id.edtSecurityCode);
        ClearableEditText edtZipCode = (ClearableEditText) findViewById(R.id.edtZipCode);

        String card_holder_name = edtCardHolderName.getText().toString().trim();
        String card_number = edtCardNumber.getText().toString().trim().replaceAll("[^\\d]", "");
        String expiration_date = edtExpirationDate.getText().toString().trim().replaceAll("[^\\d]", "");
        String security_code = edtSecurityCode.getText().toString().trim();
        String zip_code = edtZipCode.getText().toString().trim();

        successful = true;
        if(card_holder_name.length() == 0)
        {
            successful = false;
            error_title = "Name";
            error_message = "Card holder name cannot be blank";
            edtCardHolderName.requestFocus();
        }
        else if(card_number.length() == 0)
        {
            successful = false;
            error_title = "Card Number";
            error_message = "Card number cannot be blank";
            edtCardNumber.requestFocus();
        }
        else if(expiration_date.length() != 4)
        {
            successful = false;
            error_title = "Expiration Date";
            error_message = "Expiration date is invalid";
            edtExpirationDate.requestFocus();
        }
        else if(security_code.length() == 0)
        {
            successful = false;
            error_title = "Security Code";
            error_message = "Security code cannot be blank";
            edtSecurityCode.requestFocus();
        }
        else if(zip_code.length() != 5)
        {
            successful = false;
            error_title = "Zip Code";
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
            Credit_Card credit_card_obj = new Credit_Card();
            credit_card_obj.setCardHolderName(card_holder_name);
            credit_card_obj.setCardNumber(card_number);
            credit_card_obj.setExpirationDate(expiration_date);
            credit_card_obj.setBillingZipCode(zip_code);

            customer_controller.setCreditCardObj(credit_card_obj);

            credit_card_add_async = new Credit_Card_Add_Async();
            credit_card_add_async.execute((Void) null);
        }
    }

    @Override
    public void Back()
    {
        Intent back_intent = new Intent();
        back_intent.putExtra("deal_controller", deal_controller);
        back_intent.putExtra("customer_controller", customer_controller);
        back_intent.putExtra("system_controller", system_controller);
        setResult(1, back_intent);

        finish();
    }

    public void Debug()
    {
        ((ClearableEditText) findViewById(R.id.edtCardHolderName)).setText("George Triarhos");
        ((ClearableEditText) findViewById(R.id.edtCardNumber)).setText("4147202229308208");
        ((ClearableEditText) findViewById(R.id.edtExpirationDate)).setText("09/18");
        ((ClearableEditText) findViewById(R.id.edtSecurityCode)).setText("264");
        ((ClearableEditText) findViewById(R.id.edtZipCode)).setText("92014");
    }

    private class Credit_Card_Add_Async extends AsyncTask<Void, Void, Boolean>
    {
        private ProgressDialog dialog;

        public Credit_Card_Add_Async()
        {
            dialog = new ProgressDialog(Credit_Card_Add.this);
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Adding");
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            customer_controller.Add_Credit_Card();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(Credit_Card_Add.this);
                    builder.setCancelable(false);
                    builder.setTitle("Successful");
                    builder.setMessage(system_successful_obj.getMessage())
                            .setNeutralButton("OK", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();

                                    Intent back_intent = new Intent();
                                    back_intent.putExtra("deal_controller", deal_controller);
                                    back_intent.putExtra("customer_controller", customer_controller);
                                    back_intent.putExtra("system_controller", system_controller);

                                    setResult(1, back_intent);

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
            credit_card_add_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }
}
