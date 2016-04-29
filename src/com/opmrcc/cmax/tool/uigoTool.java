package com.opmrcc.cmax.tool;
import android.content.*;

public class uigoTool
{
	public static void startActivity(Context context, Class<?> cls)
	{
		Intent start=new Intent();
		start.setClass(context, cls);
		context.startActivity(start);
	}
}
