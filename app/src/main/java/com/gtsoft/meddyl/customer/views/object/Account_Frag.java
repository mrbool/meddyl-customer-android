package com.gtsoft.meddyl.customer.views.object;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AbsListView.MultiChoiceModeListener;

import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.system.gtsoft.Utils;
import com.gtsoft.meddyl.customer.views.adapter.Account_Adapter;
import com.gtsoft.meddyl.customer.views.base.Fragment_Controller;
import com.gtsoft.meddyl.customer.views.base.Tab_Controller;

public class Account_Frag extends Fragment_Controller
{
    private ListView lvMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.account_frag, container, false);

        ((Tab_Controller)getActivity()).getWindow().getDecorView().setBackgroundResource(R.drawable.gradient);

        system_controller = getArguments().getParcelable("system_controller");
        customer_controller = getArguments().getParcelable("customer_controller");
        deal_controller = getArguments().getParcelable("deal_controller");

        final String[] menu_obj = new String[5];
        menu_obj[0] = "Edit Profile";
        menu_obj[1] = "Credit Cards";
        menu_obj[2] = "Search Settings";
        menu_obj[3] = "Sign Up as a Merchant";
        menu_obj[4] = "Log Out";

        lvMenu= (ListView)rootView.findViewById(R.id.lvMenu);
//        lvMenu.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String item = menu_obj[position];

                if(item == "Edit Profile")
                {
                    Intent in = new Intent(getActivity(), Customer_Edit.class);
                    in.putExtra("system_controller", system_controller);
                    in.putExtra("customer_controller", customer_controller);
                    in.putExtra("deal_controller", deal_controller);
                    startActivity(in);
                }
                else if(item == "Credit Cards")
                {
                    Intent in = new Intent(getActivity(), Credit_Cards.class);
                    in.putExtra("system_controller", system_controller);
                    in.putExtra("customer_controller", customer_controller);
                    in.putExtra("deal_controller", deal_controller);
                    in.putExtra("calling_class", "Account_Frag");
                    startActivity(in);
                }
                else if(item == "Search Settings")
                {
                    Intent in = new Intent(getActivity(), Search_Settings.class);
                    in.putExtra("system_controller", system_controller);
                    in.putExtra("customer_controller", customer_controller);
                    in.putExtra("deal_controller", deal_controller);
                    startActivity(in);
                }
                else if(item == "Sign Up as a Merchant")
                {
                    Intent in = new Intent(getActivity(), Merchant_App.class);
                    in.putExtra("system_controller", system_controller);
                    in.putExtra("customer_controller", customer_controller);
                    in.putExtra("deal_controller", deal_controller);
                    startActivity(in);
                }
                else if(item == "Log Out")
                {
                    Utils.deleteCache(getActivity().getApplicationContext());

                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("app", getActivity().MODE_PRIVATE).edit();
                    editor.clear();
                    editor.commit();

                    Intent intent = new Intent(getActivity(), Main_View.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

        Account_Adapter adapter = new Account_Adapter(getActivity(), R.layout.account_list_view, menu_obj);
        lvMenu.setAdapter(adapter);

        return rootView;
    }
}
