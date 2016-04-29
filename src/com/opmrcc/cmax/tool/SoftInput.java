package com.opmrcc.cmax.tool;
import android.app.*;
import android.view.*;
import android.view.inputmethod.*;

public class SoftInput
{
	public static void hideInputMethod(Activity activity)
	{	
		View a = activity.getCurrentFocus();	
		if(a != null){	
			InputMethodManager imm = (InputMethodManager) activity.getApplicationContext().getSystemService(activity.INPUT_METHOD_SERVICE);
			try {
				imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);	
			} 
			catch (Exception e) {
				e.printStackTrace();	
			}	
		}	
	}	

	public static void toggleSoftInput(Activity activity)
	{
		View a = activity.getCurrentFocus();	
		if(a != null){	
			InputMethodManager imm = (InputMethodManager) activity.getApplicationContext().getSystemService(activity.INPUT_METHOD_SERVICE);
			try {
				imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_FORCED);	
			} 
			catch (Exception e) {
				e.printStackTrace();	
			}	
		}	
	}	
}
