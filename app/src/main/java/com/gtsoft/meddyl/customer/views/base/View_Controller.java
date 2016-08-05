package com.gtsoft.meddyl.customer.views.base;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gtsoft.meddyl.customer.R;
import com.gtsoft.meddyl.customer.controller.object.*;
import com.gtsoft.meddyl.customer.model.object.*;
import com.gtsoft.meddyl.customer.system.gtsoft.GTTextView;

public class View_Controller extends AppCompatActivity
{
    protected TextView txtHeaderTitle;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    protected int request_times=0;
    protected static boolean debug = false;
    protected boolean edited;
    protected boolean successful;
    protected System_Successful system_successful_obj;
    protected System_Error system_error_obj;
    protected String error_title;
    protected String error_message;

    protected Customer_Controller customer_controller;
    protected Deal_Controller deal_controller;
    protected System_Controller system_controller;

    protected String screen_title;
    protected String left_button;
    protected String right_button;

    protected int screen_width;
    protected int screen_height;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(system_controller == null)
        {
            system_controller = new System_Controller(getApplicationContext());
        }

        if(customer_controller == null)
        {
            customer_controller = new Customer_Controller(getApplicationContext());
        }

        if(deal_controller == null)
        {
            deal_controller = new Deal_Controller(getApplicationContext());
        }

        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        screen_width = metrics.widthPixels;
        screen_height = metrics.heightPixels;

        getWindow().getDecorView().setBackgroundResource(R.drawable.gradient);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed()
    {
        // Do Here what ever you want do on back press;
    }

    protected void Set_Controller_Properties()
    {
        final ViewGroup actionBarLayout;

        if(left_button == "cancel" || left_button == "close" || left_button == "later")
        {
            actionBarLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.action_bar_1, null);
        }
        else
        {
            actionBarLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.action_bar_2, null);
        }

        txtHeaderTitle = (TextView) actionBarLayout.findViewById(R.id.txtHeaderTitle);
        txtHeaderTitle.setText(screen_title);

        if(right_button == "")
        {
            GTTextView txtRight = (GTTextView) actionBarLayout.findViewById(R.id.txtRightButton);
            txtRight.setVisibility(TextView.INVISIBLE);
        }
        else if (right_button == "next")
        {
            GTTextView txtRightButton = (GTTextView) actionBarLayout.findViewById(R.id.txtRightButton);
            txtRightButton.setText("NEXT");
            txtRightButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Next();
                }
            });
        }
        else if(right_button == "add")
        {
            GTTextView txtRightButton = (GTTextView) actionBarLayout.findViewById(R.id.txtRightButton);
            txtRightButton.setText("ADD");
            txtRightButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Add();
                }
            });
        }
        else if(right_button == "save")
        {
            GTTextView txtRightButton = (GTTextView) actionBarLayout.findViewById(R.id.txtRightButton);
            txtRightButton.setText("SAVE");
            txtRightButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Save();
                }
            });
        }

        if(left_button == "")
        {
            ImageView txtLeftButton = (ImageView) actionBarLayout.findViewById(R.id.txtLeftButton);
            txtLeftButton.setVisibility(TextView.INVISIBLE);
        }
        else if(left_button == "back")
        {
            ImageView txtLeftButton = (ImageView) actionBarLayout.findViewById(R.id.txtLeftButton);
            txtLeftButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Back();
                }
            });
        }
        else if(left_button == "cancel")
        {
            GTTextView txtLeftButton = (GTTextView) actionBarLayout.findViewById(R.id.txtLeftButton);
            txtLeftButton.setText("CANCEL");
            txtLeftButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Cancel();
                }
            });
        }
        else if(left_button == "close")
        {
            GTTextView txtLeftButton = (GTTextView) actionBarLayout.findViewById(R.id.txtLeftButton);
            txtLeftButton.setText("CLOSE");
            txtLeftButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Close();
                }
            });
        }
        else if(left_button == "later")
        {
            GTTextView txtLeftButton = (GTTextView) actionBarLayout.findViewById(R.id.txtLeftButton);
            txtLeftButton.setText("LATER");
            txtLeftButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Later();
                }
            });
        }

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(actionBarLayout);
    }

    protected void Cancel()
    {
        finish();
    }

    protected void Close()
    {
        finish();
    }

    protected void Back()
    {
        finish();
    }

    protected void Later()
    {
        finish();
    }

    protected void Save()
    {
    }

    protected void Add()
    {
    }

    protected void Next()
    {

    }

    protected void Clear_Login()
    {
//        Session fb_session = Session.getActiveSession();
//        if(fb_session!=null)
//        {
//            fb_session.closeAndClearTokenInformation();
//        }
//
//        SharedPreferences prefs = getApplicationContext().getSharedPreferences("com.gtsoft.visopa.visopa_customer", Context.MODE_PRIVATE);
//        prefs.edit().remove("customer_id").apply();
    }

    public void Save_Login(int customer_id)
    {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("com.gtsoft.visopa.visopa_customer", Context.MODE_PRIVATE);
        prefs.edit().putInt("customer_id", customer_id).apply();
    }

    public void Show_Alert_Dialog_Error(Context context)
    {
        String error_message;
        String button;
        String title;

        if(system_error_obj.getCode() == 5001)
        {
            title = "Software Upgrade Needed";
            error_message = system_error_obj.getMessage();
            button = "Upgrade";
        }
        else
        {
            title = "Oops!";
            error_message = system_error_obj.getMessage();
            button = "OK";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage(error_message)
                .setTitle(title)
                .setPositiveButton(button, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                });
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (system_error_obj.getCode() == 5001)
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getApplicationContext().getPackageName()));

                    if (intent.resolveActivity(getPackageManager()) != null)
                    {
                        startActivity(intent);
                    }
                }
                else
                {
                    dialog.dismiss();
                }
                //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
            }
        });
    }

    public void Show_Alert_Dialog(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(title)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void Show_Alert_Dialog(String title, String message, DialogInterface.OnClickListener okListener)
    {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(title, okListener)
                .create()
                .show();
    }

    public void showPopup(View anchorView, String text)
    {
        View popupView = getLayoutInflater().inflate(R.layout.popup, null);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        final PopupWindow popupWindow = new PopupWindow(popupView, size.x, size.y);

        // Example: If you have a TextView inside `popup_layout.xml`

        FrameLayout llyMaster = (FrameLayout) popupView.findViewById(R.id.llyMaster);
        llyMaster.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                popupWindow.dismiss();
            }
        });

        GTTextView txvText = (GTTextView) popupView.findViewById(R.id.txvText);
        txvText.setText(text);

        FrameLayout rloInner = (FrameLayout) popupView.findViewById(R.id.rloInner);
        rloInner.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        ImageView imvCancel = (ImageView) popupView.findViewById(R.id.imvCancel);
        imvCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                popupWindow.dismiss();
            }
        });

        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
    }

    protected void Hide_Keyboard()
    {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void Debug()
    {

    }

    public int Check_Permission(final String permission, Boolean enforce)
    {
        int has_permission = checkSelfPermission(permission);

        if (has_permission != PackageManager.PERMISSION_GRANTED)
        {
            if (!shouldShowRequestPermissionRationale(permission) && (request_times > 0))
            {
                if(enforce)
                {
                    String title = "";

                    if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION))
                    {
                        title = "You need to allow GPS access";
                    }
                    else if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    {
                        title = "You need to allow storage access";
                    }
                    else if (permission.equals(Manifest.permission.CALL_PHONE))
                    {
                        title = "You need to allow access phone access";
                    }

                    Show_Alert_Dialog("Ok", title,
                            new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                            Uri.fromParts("package", getPackageName(), null));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);

                                    //requestPermissions(new String[] {permission}, REQUEST_CODE_ASK_PERMISSIONS);
                                }
                            });
                }
                else
                {
                    requestPermissions(new String[] {permission}, REQUEST_CODE_ASK_PERMISSIONS);
                }

                return has_permission;
            }

            requestPermissions(new String[] {permission}, REQUEST_CODE_ASK_PERMISSIONS);
            request_times++;

            return has_permission;
        }

        return has_permission;
    }

}


