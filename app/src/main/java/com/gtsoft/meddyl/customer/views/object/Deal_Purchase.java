package com.gtsoft.meddyl.customer.views.object;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Certificate_Payment;
import com.gtsoft.meddyl.customer.model.object.Credit_Card;
import com.gtsoft.meddyl.customer.model.object.Deal;
import com.gtsoft.meddyl.customer.model.object.Merchant;
import com.gtsoft.meddyl.customer.model.object.Promotion;
import com.gtsoft.meddyl.customer.system.gtsoft.GTButton;
import com.gtsoft.meddyl.customer.system.gtsoft.GTTextView;
import com.gtsoft.meddyl.customer.views.base.Tab_Controller;
import com.gtsoft.meddyl.customer.views.base.View_Controller;

import org.droidparts.widget.ClearableEditText;

public class Deal_Purchase extends View_Controller
{
    private String promo_code;

    private ProgressDialog dialog;
    private ImageView imvCreditCard;
    private GTTextView txvOrderSummary;
    private GTTextView txvPaymentDetails;
    private GTTextView txvPromotion;
    private ClearableEditText edtPromotion;
    private Button btnApplyPromotion;
    private GTTextView txvCreditCardNumber;
    private GTTextView txvAddCreditCard;
    private GTTextView txvSelectCreditCard;
    private GTButton btnPurchase;

    private Apply_Promotion_Async apply_promotion_async = null;
    private Get_Payment_Async get_payment_async = null;
    private Buy_Certificate_Async buy_certificate_async = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deal_purchase);

        dialog = new ProgressDialog(Deal_Purchase.this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        Intent intent = getIntent();
        system_controller = intent.getParcelableExtra("system_controller");
        customer_controller = intent.getParcelableExtra("customer_controller");
        deal_controller = intent.getParcelableExtra("deal_controller");

        screen_title = "PURCHASE";
        left_button = "close";
        right_button = "";

        Set_Controller_Properties();

        txvOrderSummary = (GTTextView) findViewById(R.id.txvOrderSummary);
        txvPaymentDetails = (GTTextView) findViewById(R.id.txvPaymentDetails);
        txvPromotion = (GTTextView) findViewById(R.id.txvPromotion);
        edtPromotion = (ClearableEditText) findViewById(R.id.edtPromotion);
        imvCreditCard = (ImageView) findViewById(R.id.imvCreditCard);
        txvCreditCardNumber = (GTTextView) findViewById(R.id.txvCreditCardNumber);

        Typeface face=Typeface.createFromAsset(getAssets(), "fonts/avenir-next-medium.ttf");
        txvOrderSummary.setTypeface(face);
        txvPaymentDetails.setTypeface(face);

        btnApplyPromotion = (Button) findViewById(R.id.btnApplyPromotion);
        btnApplyPromotion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Apply_Promo_Code();
            }
        });

        txvAddCreditCard = (GTTextView) findViewById(R.id.txvAddCreditCard);
        txvAddCreditCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Credit_Card_Add.class);
                intent.putExtra("system_controller", system_controller);
                intent.putExtra("customer_controller", customer_controller);
                intent.putExtra("deal_controller", deal_controller);
                startActivityForResult(intent, 1);
            }
        });

        txvSelectCreditCard = (GTTextView) findViewById(R.id.txvSelectCreditCard);
        txvSelectCreditCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Credit_Cards.class);
                intent.putExtra("system_controller", system_controller);
                intent.putExtra("customer_controller", customer_controller);
                intent.putExtra("deal_controller", deal_controller);
                intent.putExtra("calling_class", "Deal_Purchase");
                startActivityForResult(intent, 1);
            }
        });

        btnPurchase = (GTButton) findViewById(R.id.btnPurchase);
        btnPurchase.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Buy();
            }
        });

        get_payment_async = new Get_Payment_Async();
        get_payment_async.execute((Void) null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        deal_controller =  data.getExtras().getParcelable("deal_controller");
        customer_controller =  data.getExtras().getParcelable("customer_controller");
        system_controller =  data.getExtras().getParcelable("system_controller");

        get_payment_async = new Get_Payment_Async();
        get_payment_async.execute((Void) null);
    }

    private void Load_Data()
    {
        Deal deal_obj = deal_controller.getDealObj();
        Merchant merchant_obj = deal_obj.getMerchantContactObj().getMerchantObj();
        Certificate_Payment certificate_payment_obj = deal_controller.getCertificatePaymentObj();
        Credit_Card credit_card_obj = certificate_payment_obj.getCreditCardObj();
        Promotion promotion_obj = certificate_payment_obj.getPromotionActivityObj().getPromotionObj();

        String total_price;
        if(certificate_payment_obj.getPaymentAmount() == null)
            total_price = "Total Price:  $0.00";
        else
            total_price = "Total Price:  $" + certificate_payment_obj.getPaymentAmount().toString();

        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int screen_width = metrics.widthPixels;

        /* customer logo image */
        ImageView imvMerchantLogo = (ImageView) findViewById(R.id.imvMerchantLogo);
        imvMerchantLogo.getLayoutParams().width = Double.valueOf(screen_width * .2).intValue();
        imvMerchantLogo.getLayoutParams().height = Double.valueOf(screen_width * .2).intValue();
        imvMerchantLogo.requestLayout();

        AQuery aq = new AQuery(getApplicationContext());
        aq.id(imvMerchantLogo).image(merchant_obj.getImage());

        /* set text views */
        ((GTTextView) findViewById(R.id.txvCompanyName)).setText(merchant_obj.getCompanyName());
        ((GTTextView) findViewById(R.id.txvDeal)).setText(deal_obj.getDeal());
        ((GTTextView) findViewById(R.id.txvPrice)).setText(total_price);

        if(promotion_obj.getPromotionId() != 0)
        {
            txvPromotion.setVisibility(View.VISIBLE);
            edtPromotion.setVisibility(View.GONE);
            btnApplyPromotion.setVisibility(View.GONE);

            txvPromotion.setText("Your promo code is:  " + promotion_obj.getPromotionCode());
        }
        else
        {
            txvPromotion.setVisibility(View.GONE);
            edtPromotion.setVisibility(View.VISIBLE);
            btnApplyPromotion.setVisibility(View.VISIBLE);
        }

        if(credit_card_obj.getCreditCardId() == 0)
        {
            imvCreditCard.setVisibility(View.GONE);
            txvCreditCardNumber.setVisibility(View.GONE);
            txvAddCreditCard.setVisibility(View.VISIBLE);
            txvSelectCreditCard.setVisibility(View.GONE);
        }
        else
        {
            imvCreditCard.setVisibility(View.VISIBLE);
            txvCreditCardNumber.setVisibility(View.VISIBLE);
            txvAddCreditCard.setVisibility(View.GONE);
            txvSelectCreditCard.setVisibility(View.VISIBLE);

            /* credit card image */
            ImageView imvCreditCard = (ImageView) findViewById(R.id.imvCreditCard);
            imvCreditCard.getLayoutParams().width = Double.valueOf(screen_width * .18).intValue();
            imvCreditCard.getLayoutParams().height = Double.valueOf(screen_width * .18).intValue();
            imvCreditCard.requestLayout();

            AQuery aq_credit_card = new AQuery(getApplicationContext());
            int resource_id = getApplicationContext().getResources().getIdentifier(credit_card_obj.getCreditCardTypeObj().getImage().toLowerCase().replace(".png","") , "drawable", getApplicationContext().getPackageName());
            aq_credit_card.id(imvCreditCard).image(resource_id);

            txvCreditCardNumber.setText(credit_card_obj.getCardNumber());
        }

        txvOrderSummary.setVisibility(View.VISIBLE);
        txvPaymentDetails.setVisibility(View.VISIBLE);
        ((View)findViewById(R.id.vw1)).setVisibility(View.VISIBLE);
        ((View)findViewById(R.id.vw2)).setVisibility(View.VISIBLE);
        btnPurchase.setVisibility(View.VISIBLE);
        imvMerchantLogo.setVisibility(View.VISIBLE);
    }

    private void Apply_Promo_Code()
    {
        promo_code = edtPromotion.getText().toString().trim();

        if(promo_code.equals(""))
        {
            edtPromotion.requestFocus();
            Show_Alert_Dialog("Promotion", "You must enter a promo code");
        }
        else
        {
            apply_promotion_async = new Apply_Promotion_Async();
            apply_promotion_async.execute((Void) null);
        }
    }

    private void Buy()
    {
        if(deal_controller.getCertificatePaymentObj().getCreditCardObj().getCreditCardId() == 0)
        {
            edtPromotion.requestFocus();
            Show_Alert_Dialog("Credit Card", "You must add a credit card");
        }
        else
        {
            buy_certificate_async = new Buy_Certificate_Async();
            buy_certificate_async.execute((Void) null);
        }
    }

    private class Get_Payment_Async extends AsyncTask<Void, Void, Boolean>
    {
        public Get_Payment_Async()
        {
        }

        @Override
        protected void onPreExecute()
        {
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            deal_controller.setCustomerObj(customer_controller.getCustomerObj());
            deal_controller.Get_Payment();
            successful = deal_controller.getSuccessful();
            system_error_obj = deal_controller.getSystemErrorObj();
            system_successful_obj = deal_controller.getSystemSuccessfulObj();

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
            get_payment_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }

    private class Apply_Promotion_Async extends AsyncTask<Void, Void, Boolean>
    {
        public Apply_Promotion_Async()
        {
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setCancelable(false);
            dialog.setMessage("Applying Promo");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            Promotion promotion_obj = new Promotion();
            promotion_obj.setPromotionCode(promo_code);

            deal_controller.setCustomerObj(customer_controller.getCustomerObj());
            deal_controller.setPromotionObj(promotion_obj);
            deal_controller.Apply_Promotion();
            successful = deal_controller.getSuccessful();
            system_error_obj = deal_controller.getSystemErrorObj();
            system_successful_obj = deal_controller.getSystemSuccessfulObj();

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

                Hide_Keyboard();

                if (successful)
                {
                    Load_Data();
                }
                else
                {
                    Show_Alert_Dialog_Error(Deal_Purchase.this);
                }
            } catch (Exception ex)
            {
                Log.i("error in async", ex.getMessage().toString());
            }
        }

        @Override
        protected void onCancelled()
        {
            apply_promotion_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }

    private class Buy_Certificate_Async extends AsyncTask<Void, Void, Boolean>
    {
        public Buy_Certificate_Async()
        {
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setCancelable(false);
            dialog.setMessage("Purchasing");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            deal_controller.Buy_Certificate();
            successful = deal_controller.getSuccessful();
            system_error_obj = deal_controller.getSystemErrorObj();
            system_successful_obj = deal_controller.getSystemSuccessfulObj();

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
                    AlertDialog.Builder builder = new AlertDialog.Builder(Deal_Purchase.this);
                    builder.setCancelable(false);
                    builder.setTitle("Successful");
                    builder.setMessage(system_successful_obj.getMessage())
                            .setNeutralButton("OK", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();

                                    Intent intent = new Intent(getApplicationContext(), Tab_Controller.class);
                                    intent.putExtra("system_controller", system_controller);
                                    intent.putExtra("customer_controller", customer_controller);
                                    intent.putExtra("deal_controller", deal_controller);
                                    intent.putExtra("selected_tab", 1);
                                    startActivity(intent);
                                }
                            }).create().show();
                }
                else
                {
                    Show_Alert_Dialog_Error(Deal_Purchase.this);
                }
            } catch (Exception ex)
            {
                Log.i("error in async", ex.getMessage().toString());
            }
        }

        @Override
        protected void onCancelled()
        {
            if (dialog.isShowing())
            {
                dialog.dismiss();
            }

            buy_certificate_async = null;
        }
    }

}
