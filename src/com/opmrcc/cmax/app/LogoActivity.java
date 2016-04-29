package com.opmrcc.cmax.app;

import android.app.*;
import android.os.*;
import com.opmrcc.cmax.*;
import android.content.*;
import com.opmrcc.cmax.tool.*;

public class LogoActivity extends Activity
{
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cmax_logo);
		uigoTool.startActivity(LogoActivity.this, MainActivity.class);
		finish();
    }
}
