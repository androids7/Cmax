package com.opmrcc.cmax.app;
import android.content.*;
import java.util.*;
import android.view.*;
import android.widget.*;
import com.opmrcc.cmax.*;
import android.util.*;
import com.opmrcc.cmax.tool.*;
import java.io.*;
import android.app.*;

//文件列表适配器类
public class FileListAdapter extends BaseAdapter
{
	//应用上下文
	private Context context;
	//列表数据
	private List<String> list;
	//根目录数据
	private String path=null;
	//文件操作工具
	private FileTool fileTool=null;

	//构造方法
	public FileListAdapter(Context context, String path) 
	{
		//保存数据
		this.context = context;
		this.path=path;
		
		//获取文件目录
		fileTool=new FileTool();
		String[] pathArray=fileTool.getDirList(path);

		//数组排序
		Arrays.sort(pathArray);
		
		//拆分数组
		List<String> dirList=new ArrayList();
		List<String> fileList=new ArrayList();
		for(int i=0;i<pathArray.length; i++)
		{
			if(new File(path+pathArray[i]).isDirectory())
				dirList.add(pathArray[i]);
			else
				fileList.add(pathArray[i]);
		}
	
		//转化为List
		this.list=new ArrayList();
		//添加默认的行
		this.list.add("...");
		this.list.add("新建工程");
		this.list.add("新建文件/夹");
		//添加文件列表
		this.list.addAll(dirList);
		this.list.addAll(fileList);
	}

	@Override
	public int getCount() 
	{
		//返回列表的数量
		return  list.size();
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) 
	{
		//加载布局
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ViewGroup group = (ViewGroup) inflater.inflate(R.layout.aide_file_list_item, null);
		//设置列表内容
		TextView text = (TextView) group.findViewById(R.id.id_aide_filelistitem_title);
		text.setText(list.get(i));
		//设置列表图片
		ImageView image=(ImageView) group.findViewById(R.id.id_aide_filelistitem_image);
		switch(i)
		{
			//返回上一级
			case 0:
				image.setBackgroundResource(R.drawable.folder_open);
				break;
			//新建工程
			case 1:
				image.setBackgroundResource(R.drawable.project_new);
				break;
			//新建文件
			case 2:
				image.setBackgroundResource(R.drawable.file_new);
				break;
			//其他
			default:
				//文件夹
				if(new File(path+list.get(i)+"/").isDirectory())
				{
					image.setBackgroundResource(R.drawable.folder);
				}
				else//文件
				{
					String fileType=fileTool.getFileType(path+list.get(i));
					switch(fileType)
					{
						case "C":
						case "c":
							image.setBackgroundResource(R.drawable.file_type_c);
							break;
						
						case "CPP":
						case "cpp":
							image.setBackgroundResource(R.drawable.file_type_cpp);
							break;
							
						case "CSS":
						case "css":
							image.setBackgroundResource(R.drawable.file_type_css);
							break;
						
						case "H":
						case "h":
							image.setBackgroundResource(R.drawable.file_type_h);
							break;
							
						case "HTML":
						case "html":
							image.setBackgroundResource(R.drawable.file_type_html);
							break;
							
						case "JS":
						case "js":
							image.setBackgroundResource(R.drawable.file_type_js);
							break;
						
						case "JAVA":
						case "java":
							image.setBackgroundResource(R.drawable.file_type_java);
							break;
							
						case "TXT":
						case "txt":
							image.setBackgroundResource(R.drawable.file_type_txt);
							break;
							
						case "XML":
						case "xml":
							image.setBackgroundResource(R.drawable.file_type_xml);
							break;
							
						default:
							image.setBackgroundResource(R.drawable.file_type_unknown);
							break;
					}
				}
			break;
		}
		//设置总列表标题
		TextView title=(TextView) ((Activity)context).findViewById(R.id.id_aide_filelist_title);
		title.setText(path);
		
		return group;
	}

	@Override
	public Object getItem(int p1)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public long getItemId(int p1)
	{
		// TODO: Implement this method
		return 0;
	}
}

