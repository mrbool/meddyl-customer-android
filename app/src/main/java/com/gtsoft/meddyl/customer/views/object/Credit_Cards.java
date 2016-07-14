package com.gtsoft.meddyl.customer.views.object;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.model.object.Credit_Card;
import com.gtsoft.meddyl.customer.system.gtsoft.GTTextView;
import com.gtsoft.meddyl.customer.system.gtsoft.Utils;
import com.gtsoft.meddyl.customer.views.adapter.Credit_Card_Adapter;
import com.gtsoft.meddyl.customer.views.base.View_Controller;


public class Credit_Cards extends View_Controller
{
    private String calling_class;

    private ProgressDialog dialog;
    private SwipeMenuListView lvCreditCards;
    private GTTextView txvNoCards;

    private Credit_Card[] credit_cards_array;

    private Get_Credit_Cards_Async get_credit_cards_async = null;
    private Set_Default_Credit_Card_Async set_default_credit_card_async = null;
    private Delete_Credit_Card_Async delete_credit_card_async = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit_cards);

        dialog = new ProgressDialog(Credit_Cards.this);
        dialog.setCancelable(false);
        dialog.setMessage("Grabbing Cards");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        if (debug)
            Debug();

        Intent intent = getIntent();
        system_controller = intent.getParcelableExtra("system_controller");
        customer_controller = intent.getParcelableExtra("customer_controller");
        deal_controller = intent.getParcelableExtra("deal_controller");
        calling_class = intent.getStringExtra("calling_class");

        screen_title = "CREDIT CARDS";
        left_button = "back";
        right_button = "add";

        Set_Controller_Properties();

        SwipeMenuCreator creator = new SwipeMenuCreator()
        {
            @Override
            public void create(SwipeMenu menu)
            {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
                deleteItem.setWidth(Utils.dpToPx(90));
                deleteItem.setTitle("Delete");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        lvCreditCards = (SwipeMenuListView) findViewById(R.id.lvCreditCards);
        lvCreditCards.setMenuCreator(creator);
        lvCreditCards.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Credit_Card credit_card_obj = credit_cards_array[position];
                customer_controller.setCreditCardObj(credit_card_obj);

                set_default_credit_card_async = new Set_Default_Credit_Card_Async();
                set_default_credit_card_async.execute((Void) null);

            }
        });
        lvCreditCards.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index)
            {
                switch (index)
                {
                    case 0:
                        Credit_Card credit_card_obj = credit_cards_array[position];
                        customer_controller.setCreditCardObj(credit_card_obj);

                        delete_credit_card_async = new Delete_Credit_Card_Async();
                        delete_credit_card_async.execute((Void) null);

                        break;
                }
                return false;
            }
        });

        txvNoCards = (GTTextView) findViewById(R.id.txvNoCards);

        get_credit_cards_async = new Get_Credit_Cards_Async();
        get_credit_cards_async.execute((Void) null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(!calling_class.equals("Deal_Purchase"))
        {
            dialog = new ProgressDialog(Credit_Cards.this);
            dialog.setCancelable(false);
            dialog.setMessage("Grabbing Cards");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();

            get_credit_cards_async = new Get_Credit_Cards_Async();
            get_credit_cards_async.execute((Void) null);
        }
        else
        {
            Intent back_intent = new Intent();
            back_intent.putExtra("deal_controller", deal_controller);
            back_intent.putExtra("customer_controller", customer_controller);
            back_intent.putExtra("system_controller", system_controller);
            setResult(1, back_intent);

            finish();
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

    public void Add()
    {
        Intent intent = new Intent(getApplicationContext(), Credit_Card_Add.class);
        intent.putExtra("system_controller", system_controller);
        intent.putExtra("customer_controller", customer_controller);
        intent.putExtra("deal_controller", deal_controller);
        startActivityForResult(intent, 1);
    }

    public void Debug()
    {

    }

    private class Set_Default_Credit_Card_Async extends AsyncTask<Void, Void, Boolean>
    {
        public Set_Default_Credit_Card_Async()
        {
        }

        @Override
        protected void onPreExecute()
        {
            dialog.setCancelable(false);
            dialog.setMessage("Setting Default");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            customer_controller.Set_Default_Credit_Card();
            credit_cards_array = customer_controller.getCreditCardObjArray();
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
                    if(calling_class.equals("Deal_Purchase"))
                    {
                        Back();
                    }
                    else
                    {
                        Credit_Card_Adapter adapter = new Credit_Card_Adapter(Credit_Cards.this, R.layout.credit_card_list_view, credit_cards_array);
                        lvCreditCards.setAdapter(adapter);

                        get_credit_cards_async = new Get_Credit_Cards_Async();
                        get_credit_cards_async.execute((Void) null);
                    }
                }
                else
                {
                    Show_Alert_Dialog_Error(Credit_Cards.this);
                }
            } catch (Exception ex)
            {
                Log.i("error in async", ex.getMessage().toString());
            }
        }

        @Override
        protected void onCancelled()
        {
            get_credit_cards_async = null;
        }
    }

    private class Get_Credit_Cards_Async extends AsyncTask<Void, Void, Boolean>
    {

        public Get_Credit_Cards_Async()
        {

        }

        @Override
        protected void onPreExecute()
        {

        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            customer_controller.Get_Credit_Cards();
            credit_cards_array = customer_controller.getCreditCardObjArray();
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
                    Credit_Card_Adapter adapter = new Credit_Card_Adapter(Credit_Cards.this, R.layout.credit_card_list_view, credit_cards_array);
                    lvCreditCards.setAdapter(adapter);

                    if(credit_cards_array.length == 0)
                        txvNoCards.setVisibility(View.VISIBLE);
                    else
                        txvNoCards.setVisibility(View.GONE);
                }
                else
                {
                    Show_Alert_Dialog_Error(Credit_Cards.this);
                }
            } catch (Exception ex)
            {
                Log.i("error in async", ex.getMessage().toString());
            }
        }

        @Override
        protected void onCancelled()
        {
            get_credit_cards_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }

    private class Delete_Credit_Card_Async extends AsyncTask<Void, Void, Boolean>
    {

        public Delete_Credit_Card_Async()
        {

        }

        @Override
        protected void onPreExecute()
        {
            dialog = new ProgressDialog(Credit_Cards.this);
            dialog.setCancelable(false);
            dialog.setMessage("Deleting");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... login_log_data)
        {
            customer_controller.Delete_Credit_Card();
            credit_cards_array = customer_controller.getCreditCardObjArray();
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
                    get_credit_cards_async = new Get_Credit_Cards_Async();
                    get_credit_cards_async.execute((Void) null);
                }
                else
                {
                    Show_Alert_Dialog_Error(Credit_Cards.this);
                }
            } catch (Exception ex)
            {
                Log.i("error in async", ex.getMessage().toString());
            }
        }

        @Override
        protected void onCancelled()
        {
            delete_credit_card_async = null;

            if (dialog.isShowing())
            {
                dialog.dismiss();
            }
        }
    }
}
