package com.mobileapps.training.daily2.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.mobileapps.training.daily2.R;

public class LoginCompositeView extends RelativeLayout {

    private static final String TAG = "LoginCompositeView";


    private Context context;

    public LoginCompositeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginCompositeView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.login_page_layout, this, true);
    }
}
