package com.opmrcc.cmax.app;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.opmrcc.cmax.*;
import java.util.*;
import android.animation.*;
import android.util.*;
import com.opmrcc.cmax.tool.*;
import java.io.*;
import android.view.inputmethod.*;

public class MainActivity extends Activity
{
	//代码编辑框
	EditText edit=null;
	
	//ui
	LinearLayout aideListUi=null;
	LinearLayout cmaxEditUi=null;
	
	//悬浮按钮
	ImageView menu=null;
	
	//文件列表
	FileList fileList=null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_ui);
		
		aideListUi=(LinearLayout) findViewById(R.id.ui_main_activity_aidefilelist);
		
		edit=(EditText) findViewById(R.id.id_cmax_source_edit);
		edit.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					aideListUi.setVisibility(8);
				}
			});
		
		menu=(ImageView) findViewById(R.id.id_main_activity_menu);
		menu.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					aideListUi.setVisibility(0);
					SoftInput.hideInputMethod(MainActivity.this);
				}
			});

		fileList=(FileList) findViewById(R.id.id_aide_filelist_list);
		fileList.setPath("/mnt/sdcard/AppProjects/");
		
    }
	
}
	
	
