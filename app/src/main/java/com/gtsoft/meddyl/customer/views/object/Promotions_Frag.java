package com.gtsoft.meddyl.customer.views.object;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Certificate;
import com.gtsoft.meddyl.customer.system.gtsoft.GTButton;
import com.gtsoft.meddyl.customer.system.gtsoft.GTTextView;
import com.gtsoft.meddyl.customer.views.adapter.Deals_Adapter;
import com.gtsoft.meddyl.customer.views.base.Fragment_Controller;
import com.gtsoft.meddyl.customer.views.base.Tab_Controller;

import org.droidparts.widget.ClearableEditText;

public class Promotions_Frag extends Fragment_Controller
{
    private View rootView;
    private GTButton btnRefer;

    private Get_Valid_Promotions_Async get_valid_promotions_async = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.promotions_frag, container, false);

        ((Tab_Controller)getActivity()).getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        system_controller = getArguments().getParcelable("system_controller");
        customer_controller = getArguments().getParcelable("customer_controller");
        deal_controller = getArguments().getParcelable("deal_controller");

        btnRefer = (GTButton) rootView.findViewById(R.id.btnRefer);
        btnRefer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:"));
                intent.putExtra("sms_body",customer_controller.getCustomerObj().getPromotionObj().getLink());
                startActivity(intent);
            }
        });

        get_valid_promotions_async = new Get_Valid_Promotions_Async();
        get_valid_promotions_async.execute((Void) null);

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();

    }

    private void Load_Data()
    {
        String promotion;
        String promotion_desc;

        if(customer_controller.getPromotionActivityObjArray().length == 1)
            promotion = "You have 1 promotion";
        else
            promotion = "You have " + Integer.toString(customer_controller.getPromotionActivityObjArray().length) + " promotions";

        if(customer_controller.getPromotionActivityObjArray().length == 0)
            promotion_desc = "Refer a friend and get free deals";
        else
            promotion_desc = "Just find the deal you want and it will automatically be free during checkout";

        ((GTTextView) rootView.findViewById(R.id.txvPromotion)).setText(promotion);
        ((GTTextView) rootView.findViewById(R.id.txvPromotionDesc)).setText(promotion_desc);
        ((GTTextView) rootView.findViewById(R.id.txvPromoCode)).setText("Your promo code is " + customer_controller.getCustomerObj().getPromotionObj().getPromotionCode());

        ((View) rootView.findViewById(R.id.vw1)).setVisibility(View.VISIBLE);
        ((View) rootView.findViewById(R.id.txvDeal)).setVisibility(View.VISIBLE);
        btnRefer.setVisibility(View.VISIBLE);
    }

    private class Get_Valid_Promotions_Async extends AsyncTask<Void, Void, Boolean>
    {
        private ProgressDialog dialog;

        public Get_Valid_Promotions_Async()
        {
            dialog = new ProgressDialog(getActivity());
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
            customer_controller.Get_Valid_Promotions();
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

                if(successful)
                {
                    Load_Data();
                }
                else
                {
                    ((Tab_Controller)getActivity()).Show_Alert_Dialog("Error", system_error_obj.getMessage());
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
            get_valid_promotions_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }
}
