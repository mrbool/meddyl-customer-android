package com.gtsoft.meddyl.customer.system.gtsoft;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;



public class TextWatcherPhone implements TextWatcher {

    private EditText et;
    private String cleaned_current_phone="";
    private String current_text="";


    public TextWatcherPhone(EditText et)
    {
        this.et = et;
    }


    public void afterTextChanged(Editable s)
    {

    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
        current_text = et.getText().toString();
    }

    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        String current_character;
        String cleaned_character_added;

        et.removeTextChangedListener(this);

        if(start == 0 && count == 0)
        {
            cleaned_current_phone = "";
        }
        else
        {
            if (s.length() <= current_text.length())
            {
                cleaned_current_phone = current_text.replaceAll("[^\\d]", "");
                cleaned_current_phone = cleaned_current_phone.substring(0, cleaned_current_phone.length() - 1);
            }
            else
            {
                cleaned_current_phone = cleaned_current_phone.replaceAll("[^\\d]", "");

                if (cleaned_current_phone.length() < 10)
                {
                    current_character = s.toString().substring(s.length() - 1, s.length());
                    cleaned_character_added = current_character.replaceAll("[^\\d]", "");

                    cleaned_current_phone = cleaned_current_phone + cleaned_character_added;
                }
            }

            if (cleaned_current_phone.length() == 0)
            {
                cleaned_current_phone = "";
            }
            else if ((cleaned_current_phone.length()) >= 1 && (cleaned_current_phone.length() <= 2))
            {
                cleaned_current_phone = "(" + cleaned_current_phone;
            }
            else if (cleaned_current_phone.length() == 3)
            {
                cleaned_current_phone = "(" + cleaned_current_phone + ")";
            }
            else if (cleaned_current_phone.length() < 6)
            {
                cleaned_current_phone = "(" + cleaned_current_phone.substring(0, 3) + ") " + cleaned_current_phone.substring(3, cleaned_current_phone.length());
            }
            else if (cleaned_current_phone.length() <= 10)
            {
                cleaned_current_phone = "(" + cleaned_current_phone.substring(0, 3) + ") " + cleaned_current_phone.substring(3, 6) + "-" + cleaned_current_phone.substring(6, cleaned_current_phone.length());
            }
        }

        et.setText(cleaned_current_phone);
        et.setSelection(cleaned_current_phone.length());

        et.addTextChangedListener(this);
    }
}