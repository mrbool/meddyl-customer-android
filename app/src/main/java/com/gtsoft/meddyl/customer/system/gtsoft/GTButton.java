package com.gtsoft.meddyl.customer.system.gtsoft;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class GTButton extends Button {

    Typeface normalTypeface = FontCache.get("fonts/avenir-next-regular.ttf", getContext());
    Typeface boldTypeface = FontCache.get("fonts/avenir-next-regular.ttf", getContext());

    /**
     * @param context
     */
    public GTButton(Context context) {
        super(context);
        this.setTypeface(normalTypeface);
    }

    /**
     * @param context
     * @param attrs
     */
    public GTButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(normalTypeface);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public GTButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setTypeface(normalTypeface);
    }

}