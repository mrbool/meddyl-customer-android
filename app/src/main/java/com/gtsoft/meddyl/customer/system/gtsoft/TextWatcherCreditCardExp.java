package com.gtsoft.meddyl.customer.system.gtsoft;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public class TextWatcherCreditCardExp implements TextWatcher
{

    private EditText et;
    private String cleaned_current="";
    private String current_text="";


    public TextWatcherCreditCardExp(EditText et)
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

        if(s.length() <= current_text.length())
        {
            cleaned_current = current_text.replaceAll( "[^\\d]", "" );
            cleaned_current = cleaned_current.substring(0, cleaned_current.length()-1);
        }
        else
        {
            cleaned_current = cleaned_current.replaceAll( "[^\\d]", "" );

            if(cleaned_current.length() < 4)
            {
                current_character = s.toString().substring(s.length() - 1, s.length());
                cleaned_character_added = current_character.replaceAll("[^\\d]", "");

                cleaned_current = cleaned_current + cleaned_character_added;
            }
        }

        if (cleaned_current.length() == 0)
        {
            cleaned_current = "";
        }
        else if(cleaned_current.length() == 1)
        {
            cleaned_current = cleaned_current + "";
        }
        else
        {
            cleaned_current = cleaned_current.substring(0,2) + "/" + cleaned_current.substring(2, cleaned_current.length());
        }

        et.setText(cleaned_current);
        et.setSelection(cleaned_current.length());

        et.addTextChangedListener(this);
    }
}