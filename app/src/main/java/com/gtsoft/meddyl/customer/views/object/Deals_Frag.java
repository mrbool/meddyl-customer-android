package com.gtsoft.meddyl.customer.views.object;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Contact_GPS_Log;
import com.gtsoft.meddyl.customer.model.object.Deal;
import com.gtsoft.meddyl.customer.system.gtsoft.GTButton;
import com.gtsoft.meddyl.customer.system.gtsoft.GTTextView;
import com.gtsoft.meddyl.customer.views.adapter.Deals_Adapter;
import com.gtsoft.meddyl.customer.views.base.Fragment_Controller;
import com.gtsoft.meddyl.customer.views.base.Tab_Controller;

public class Deals_Frag extends Fragment_Controller
{
    int gps_call =0;

    View anchorView;
    private SwipeRefreshLayout srlDeal;
    private ListView lvDeals;
    private SwipeRefreshLayout srlNoDeals;

    private Deal[] deals_array;
    private Contact_GPS_Log contact_gps_log_obj;

    private Get_Deals_Async get_deals_async = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.deals_frag, container, false);
        anchorView = rootView;

        ((Tab_Controller)getActivity()).getWindow().getDecorView().setBackgroundResource(R.drawable.gradient);

        system_controller = getArguments().getParcelable("system_controller");
        customer_controller = getArguments().getParcelable("customer_controller");
        deal_controller = getArguments().getParcelable("deal_controller");

        //GTTextView txtHeaderTitle = (GTTextView) actionBarLayout.findViewById(R.id.txtHeaderTitle);

        contact_gps_log_obj = new Contact_GPS_Log();
        contact_gps_log_obj.setLatitude(0);
        contact_gps_log_obj.setLongitude(0);

        srlDeal = (SwipeRefreshLayout) rootView.findViewById(R.id.srlDeal);
        srlNoDeals = (SwipeRefreshLayout) rootView.findViewById(R.id.srlNoDeals);

        srlDeal.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                get_deals_async = new Get_Deals_Async();
                get_deals_async.execute((Void) null);
            }
        });
        srlDeal.setColorScheme(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light);

        srlNoDeals.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                get_deals_async = new Get_Deals_Async();
                get_deals_async.execute((Void) null);
            }
        });
        srlNoDeals.setColorScheme(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light);

        lvDeals= (ListView)rootView.findViewById(R.id.lvDeals);
        lvDeals.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Deal deal_obj = deals_array[position];
                deal_controller.setDealObj(deal_obj);

                Intent in = new Intent(getActivity(), Deal_Detail.class);
                in.putExtra("system_controller", system_controller);
                in.putExtra("customer_controller", customer_controller);
                in.putExtra("deal_controller", deal_controller);
                startActivity(in);
            }
        });

        if(customer_controller.getCustomerObj().getCustomerSearchLocationTypeObj().getSearchLocationTypeId() == 1)
        {
            int has_permission = 0;

            if (Build.VERSION.SDK_INT >= 23)
            {
                has_permission = Check_Permission(Manifest.permission.ACCESS_FINE_LOCATION, false, gps_call);
                gps_call++;
            }

            if(has_permission == 0)
            {
                Get_Location();

                get_deals_async = new Get_Deals_Async();
                get_deals_async.execute((Void) null);
            }
        }
        else
        {
            get_deals_async = new Get_Deals_Async();
            get_deals_async.execute((Void) null);
        }

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1)
        {
            deal_controller = data.getExtras().getParcelable("deal_controller");
            customer_controller = data.getExtras().getParcelable("customer_controller");
            system_controller = data.getExtras().getParcelable("system_controller");
        }

        if(customer_controller.getCustomerObj().getCustomerSearchLocationTypeObj().getSearchLocationTypeId() == 1)
        {
            Get_Location();
        }

        get_deals_async = new Get_Deals_Async();
        get_deals_async.execute((Void) null);
    }

    /* Callback received when a permissions request has been completed. */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == 123)
        {
            if(grantResults[0] == 0)
            {
                Get_Location();
            }

            get_deals_async = new Get_Deals_Async();
            get_deals_async.execute((Void) null);

            request_times = 0;
        }
        else
        {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void Get_Location()
    {
        customer_controller.Get_Location(getActivity());
        Boolean has_gps_service = customer_controller.getHasGpsService();

        if((has_gps_service) && !(customer_controller.getLatitude() == 0 && customer_controller.getLongitude() == 0))
        {
            contact_gps_log_obj.setLatitude(customer_controller.getLatitude());
            contact_gps_log_obj.setLongitude(customer_controller.getLongitude());
        }

        customer_controller.setContactGPSLogObj(contact_gps_log_obj);
    }


    public void Scroll_Up()
    {
        lvDeals.setSelection(0);
    }

    public void showPopup(String text)
    {
        View popupView = ((Tab_Controller)getActivity()).getLayoutInflater().inflate(R.layout.popup_location, null);

        Display display = ((Tab_Controller)getActivity()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        final PopupWindow popupWindow = new PopupWindow(popupView, size.x, size.y);

        GTTextView txvHeader = (GTTextView) popupView.findViewById(R.id.txvHeader);
        txvHeader.setText(text);

        GTTextView txvLocationServices = (GTTextView) popupView.findViewById(R.id.txvLocationServices);
        txvLocationServices.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String packageName = ((Tab_Controller)getActivity()).getPackageName();

                int has_permission = 0;

                if (Build.VERSION.SDK_INT >= 23)
                {
                    has_permission = Check_Permission(Manifest.permission.ACCESS_FINE_LOCATION, true, gps_call);
                    gps_call++;
                }

                if(has_permission == 0)
                {
                    try
                    {
                        //Open the specific App Info page:
//                    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        intent.setData(Uri.parse("package:" + packageName));
                        startActivityForResult(intent, 2);

                    } catch (ActivityNotFoundException e)
                    {
                        //Open the generic Apps page:
                        Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                        startActivityForResult(intent, 2);
                    }
                }
                else
                {
                    popupWindow.dismiss();
                }
            }
        });

        GTTextView txvSearchSettings = (GTTextView) popupView.findViewById(R.id.txvSearchSettings);
        txvSearchSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), Search_Settings.class);
                intent.putExtra("system_controller", system_controller);
                intent.putExtra("customer_controller", customer_controller);
                intent.putExtra("deal_controller", deal_controller);
                startActivityForResult(intent, 1);

                popupWindow.dismiss();
            }
        });

        GTButton btnGotIt = (GTButton) popupView.findViewById(R.id.btnGotIt);
        btnGotIt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                popupWindow.dismiss();

                Get_Location();

                get_deals_async = new Get_Deals_Async();
                get_deals_async.execute((Void) null);
            }
        });

        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
    }

    private class Get_Deals_Async extends AsyncTask<Void, Void, Boolean>
    {
        private ProgressDialog dialog;

        public Get_Deals_Async()
        {
            dialog = new ProgressDialog(getActivity());
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setMessage("Loading Deals");
            dialog.setCancelable(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            deal_controller.setCustomerId(customer_controller.getCustomerObj().getCustomerId());
            deal_controller.setLatitude((float)customer_controller.getContactGPSLogObj().getLatitude());
            deal_controller.setLongitude((float)customer_controller.getContactGPSLogObj().getLongitude());
            deal_controller.setCustomerObj(customer_controller.getCustomerObj());
            deal_controller.Get_Deals();
            deals_array = deal_controller.getDealObjArray();
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

                if (srlDeal.isRefreshing())
                {
                    srlDeal.setRefreshing(false);
                }

                if (srlNoDeals.isRefreshing())
                {
                    srlNoDeals.setRefreshing(false);
                }

                if(successful)
                {
                    Deals_Adapter deal_adapter = new Deals_Adapter(getActivity(), R.layout.deals_list_view, deals_array);
                    lvDeals.setAdapter(deal_adapter);

                    if(deals_array.length > 0)
                    {
                        srlDeal.setVisibility(View.VISIBLE);
                        srlNoDeals.setVisibility(View.GONE);
                    }
                    else
                    {
                        srlDeal.setVisibility(View.GONE);
                        srlNoDeals.setVisibility(View.VISIBLE);
                    }
                }
                else
                {
                    if(system_error_obj.getCode() == 2026)
                    {
                        showPopup(system_error_obj.getMessage());
                    }
                    else
                    {
                        ((Tab_Controller) getActivity()).Show_Alert_Dialog("Error", system_error_obj.getMessage());
                    }
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
            get_deals_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }
}
