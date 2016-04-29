package com.opmrcc.cmax.app;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.view.*;
import com.opmrcc.cmax.*;
import java.io.*;
import com.opmrcc.cmax.tool.*;
import android.os.*;

//自定义ListView
public class FileList extends ListView implements AdapterView.OnItemClickListener
{
	//应用上下文
	private Context context=null;
	//列表父路径
	private String filePath=null;
	//新建工程对话框
	DialogTool newProjectDialog=null;
	//新建文件/夹对话框
	DialogTool newFileDialog=null;

	//构造函数
	public FileList(Context context)
	{
		super(context);
		this.context = context;

		this.initList();
	}

	//构造函数
	public FileList(Context context, AttributeSet attr)
	{
		super(context, attr);
		this.context = context;
		
		this.initList();
	}
	
	//初始化列表资源
	public void initList()
	{
		newProjectDialog=new DialogTool(context, R.layout.new_project_dialog);
		newFileDialog=new DialogTool(context, R.layout.new_file_dialog);

		//监听新建文件/文件夹对话框
		ViewGroup fileDialogLayout=newFileDialog.getView();
		Button fileCancelBtn=(Button) fileDialogLayout.findViewById(R.id.id_new_file_dialog_cancel);
		Button fileCreateBtn=(Button) fileDialogLayout.findViewById(R.id.id_new_file_dialog_create);
		fileCancelBtn.setOnClickListener(new viewClick());
		fileCreateBtn.setOnClickListener(new viewClick());
		
		//监听列表
		this.setOnItemClickListener(this);
	}

	//设置路径
	public void setPath(String path)
	{
		filePath = path;
		FileListAdapter fileAdapter=new FileListAdapter(context, filePath);
		this.setAdapter(fileAdapter);
	}

	//获取当前路径
	public String getPath()
	{
		return filePath;
	}

	//列表监听器
	@Override
	public void onItemClick(AdapterView<?> p1, View view, int p3, long p4)
	{
		// TODO: Implement this method
		View group=(View) view.getParent().getParent().getParent().getParent().getParent();
		TextView title=(TextView) group.findViewById(R.id.id_aide_filelist_title);
		title.setText(filePath);

		TextView textView=(TextView) view.findViewById(R.id.id_aide_filelistitem_title);
		String newPath=filePath + textView.getText().toString() + "/";

		switch (p3)
		{
			//返回上一级
			case 0:
				if(filePath.length()>1)
					this.setPath(FileTool.getFileDir(filePath.substring(0, filePath.length()-1)));
				break;
				
			//新建工程
			case 1:
				newProjectDialog.show();
				break;
			
			//新建文件
			case 2:
				newFileDialog.show();
				break;
				
			//真正列表
			default:
				if (new File(newPath).isDirectory())
				{
					this.setPath(newPath);
				}
				else
				{
					EditText editText=(EditText) group.findViewById(R.id.id_cmax_source_edit);
					String fileText=new FileTool().readFileToString(newPath, "utf-8");
					editText.setText(fileText);
				}	
				break;
		}
	}
	
	private class viewClick implements View.OnClickListener
	{
		@Override
		public void onClick(View p1)
		{
			//监听
			switch(p1.getId())
			{
				//新建文件监听
				case R.id.id_new_file_dialog_cancel:
					//释放对话框
					newFileDialog.cancel();
					break;
				case R.id.id_new_file_dialog_create:
					//创建文件/夹
					ViewGroup fileDialogLayout=newFileDialog.getView();
					CheckBox fileDir=(CheckBox) fileDialogLayout.findViewById(R.id.id_new_file_dialog_check);
					EditText pathEdit=(EditText) fileDialogLayout.findViewById(R.id.id_new_file_dialog_path);
					String newPath=filePath+pathEdit.getText().toString()+"/";
					if(fileDir.isChecked())
					{
						new FileTool().createDir(newPath);
						setPath(newPath);
						newFileDialog.cancel();
					}
					else
					{
						try
						{
							new FileTool().createFile(newPath);
						}
						catch (IOException e)
						{
							
						}
						setPath(filePath);
						newFileDialog.cancel();
					}
					
					break;
			}
			// TODO: Implement this method
		}
	}
}
