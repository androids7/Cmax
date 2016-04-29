package com.opmrcc.cmax.tool;
import android.app.*;
import android.view.*;
import android.widget.AdapterView.*;
import android.content.*;
import com.opmrcc.cmax.*;

public class DialogTool
{
	//应用上下文
	private Context context=null;
	//对话框工厂
	private AlertDialog.Builder builder=null;
	//对话框布局
	private ViewGroup viewGroup=null;
	//对话框实例
	private AlertDialog alertDialog=null;
	
	
	//对话框构造函数
	public DialogTool(Context context, int id)
	{
		this.context=context;
		
		//得到构建对象
		builder = new AlertDialog.Builder(context);
		
		//添加一个布局到对话框中
		viewGroup = getXmlView(id);
		builder.setView(viewGroup);

		//创建并显示对话框
		alertDialog = builder.create();
		
	}
	
	//释放对话框
	public void cancel()
	{
		alertDialog.cancel();
	}
	
	//显示对话框
	public void show()
	{
		alertDialog.show();
	}
	
	//获取对话框布局
	public ViewGroup getView()
	{
		return viewGroup;
	}
	
	//加载xml布局
	public ViewGroup getXmlView(int layout)
	{
		//加载布局
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewGroup group = (ViewGroup) inflater.inflate(layout, null);
		return group;
	}
	
	
}
