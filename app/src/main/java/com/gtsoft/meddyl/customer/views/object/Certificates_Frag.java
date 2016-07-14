package com.gtsoft.meddyl.customer.views.object;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Certificate;
import com.gtsoft.meddyl.customer.model.object.Deal;
import com.gtsoft.meddyl.customer.system.gtsoft.GTTextView;
import com.gtsoft.meddyl.customer.views.adapter.Certificates_Adapter;
import com.gtsoft.meddyl.customer.views.adapter.Deals_Adapter;
import com.gtsoft.meddyl.customer.views.base.Fragment_Controller;
import com.gtsoft.meddyl.customer.views.base.Tab_Controller;

import info.hoang8f.android.segmented.SegmentedGroup;

public class Certificates_Frag extends Fragment_Controller
{
    private GTTextView txvNoCertificates;
    private SegmentedGroup sgCertificates;
    private SwipeRefreshLayout srlCertificates;
    private ListView lvCertificates;

    private Certificate[] certificate_obj_array;

    private Get_Customer_Active_Certificates_Async get_customer_active_certificates_async = null;
    private Get_Customer_Certificates_Async get_customer_certificates_async = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.certificates_frag, container, false);

        ((Tab_Controller)getActivity()).getWindow().getDecorView().setBackgroundResource(R.drawable.gradient);

        system_controller = getArguments().getParcelable("system_controller");
        customer_controller = getArguments().getParcelable("customer_controller");
        deal_controller = getArguments().getParcelable("deal_controller");

        txvNoCertificates = (GTTextView) rootView.findViewById(R.id.txvNoCertificates);

        sgCertificates = (SegmentedGroup) rootView.findViewById(R.id.sgCertificates);
        sgCertificates.check(R.id.rbActive);
        sgCertificates.setOnCheckedChangeListener(new SegmentedGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbActive:
                        get_customer_active_certificates_async = new Get_Customer_Active_Certificates_Async();
                        get_customer_active_certificates_async.execute((Void) null);
                        break;
                    case R.id.rbAll:
                        get_customer_certificates_async = new Get_Customer_Certificates_Async();
                        get_customer_certificates_async.execute((Void) null);
                        break;
                    default:
                        // Nothing to do
                }
            }
        });

        srlCertificates = (SwipeRefreshLayout) rootView.findViewById(R.id.srlCertificates);
        srlCertificates.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                if(sgCertificates.getCheckedRadioButtonId() == R.id.rbActive)
                {
                    get_customer_active_certificates_async = new Get_Customer_Active_Certificates_Async();
                    get_customer_active_certificates_async.execute((Void) null);
                }
                else
                {
                    get_customer_certificates_async = new Get_Customer_Certificates_Async();
                    get_customer_certificates_async.execute((Void) null);
                }
            }
        });
        srlCertificates.setColorScheme(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light);

        lvCertificates= (ListView)rootView.findViewById(R.id.lvCertificates);
        lvCertificates.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Certificate certificate_obj = certificate_obj_array[position];
                deal_controller.setCertificateObj(certificate_obj);

                Intent intent = new Intent(getActivity(), Certificate_Detail.class);
                intent.putExtra("system_controller", system_controller);
                intent.putExtra("customer_controller", customer_controller);
                intent.putExtra("deal_controller", deal_controller);
                startActivity(intent);
            }
        });

        get_customer_active_certificates_async = new Get_Customer_Active_Certificates_Async();
        get_customer_active_certificates_async.execute((Void) null);

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();

    }



    private class Get_Customer_Active_Certificates_Async extends AsyncTask<Void, Void, Boolean>
    {
        private ProgressDialog dialog;

        public Get_Customer_Active_Certificates_Async()
        {
            dialog = new ProgressDialog(getActivity());
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Loading Certificates");
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            deal_controller.setCustomerObj(customer_controller.getCustomerObj());
            deal_controller.Get_Customer_Active_Certificates();
            certificate_obj_array = deal_controller.getCertificateObjArray();
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

                if (srlCertificates.isRefreshing())
                {
                    srlCertificates.setRefreshing(false);
                }

                if(successful)
                {
                    Certificates_Adapter certificates_adapter = new Certificates_Adapter(getActivity(), R.layout.certificates_list_view, certificate_obj_array);
                    lvCertificates.setAdapter(certificates_adapter);

                    if(certificate_obj_array.length > 0)
                    {
                        srlCertificates.setVisibility(View.VISIBLE);
                        lvCertificates.setVisibility(View.VISIBLE);
                        txvNoCertificates.setVisibility(View.GONE);
                    }
                    else
                    {
                        srlCertificates.setVisibility(View.GONE);
                        txvNoCertificates.setText("You have no Active Certificates");
                        txvNoCertificates.setVisibility(View.VISIBLE);
                    }
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
            get_customer_active_certificates_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }

    private class Get_Customer_Certificates_Async extends AsyncTask<Void, Void, Boolean>
    {
        private ProgressDialog dialog;

        public Get_Customer_Certificates_Async()
        {
            dialog = new ProgressDialog(getActivity());
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Loading Certificates");
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            deal_controller.setCustomerObj(customer_controller.getCustomerObj());
            deal_controller.Get_Customer_Certificates();
            certificate_obj_array = deal_controller.getCertificateObjArray();
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

                if (srlCertificates.isRefreshing())
                {
                    srlCertificates.setRefreshing(false);
                }

                if(successful)
                {
                    Certificates_Adapter certificates_adapter = new Certificates_Adapter(getActivity(), R.layout.certificates_list_view, certificate_obj_array);
                    lvCertificates.setAdapter(certificates_adapter);

                    if(certificate_obj_array.length > 0)
                    {
                        srlCertificates.setVisibility(View.VISIBLE);
                        lvCertificates.setVisibility(View.VISIBLE);
                        txvNoCertificates.setVisibility(View.GONE);
                    }
                    else
                    {
                        srlCertificates.setVisibility(View.GONE);
                        txvNoCertificates.setText("You have no Certificates");
                        txvNoCertificates.setVisibility(View.VISIBLE);
                    }
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
            get_customer_certificates_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }
}
