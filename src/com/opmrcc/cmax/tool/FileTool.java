package com.opmrcc.cmax.tool;
import java.io.*;
import android.os.*;
import java.util.*;

public class FileTool
{
	//SD卡路径
	public String SdPath=null;
	
	//构造函数
	public FileTool() {
		SdPath=Environment.getExternalStorageDirectory()+"/";
	}
	

	//获取文件扩展名
	public static String getFileType(String fileUri)
	{
		File file = new File(fileUri);
		String fileName = file.getName();
		String fileType = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
		return fileType;
	}
	

	//获取文件所在文件夹
	public static String getFileDir(String filePath)
	{
		String dirPath = filePath.substring(0,filePath.lastIndexOf("/"))+"/";
		return dirPath;
	}
	
	
    /**
    * 判断文件是否已经存在;
    */
	public boolean checkFileExists(String filepath) {
         File file=new File(SdPath+filepath);
         return file.exists();
	}
	
	/*
	 * 在SD卡上创建目录；
	 */
	public File createSdcardDir(String dirpath) {
		File dir=new File(SdPath+dirpath);
		dir.mkdir();
		return dir;
	}
	

	/*
	 * 创建目录；
	 */
	public File createDir(String dirpath) {
		File dir=new File(dirpath);
		dir.mkdir();
		return dir;
	}
	
	/*
	 * 在SD卡上创建文件；
	 */
	public File createSdcardFile(String filepath) throws IOException{
		File file=new File(SdPath+filepath);
		file.createNewFile();
		return file;
	}
	

	/*
	 * 创建文件；
	 */
	public File createFile(String filepath) throws IOException{
		File file=new File(filepath);
		file.createNewFile();
		return file;
	}
	
	//读取文件到String
	public static String readFileToString(String path, String encoding)
	{
		String content = null;
		content = "";
		try
		{
			File file = new File(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
														   new FileInputStream(file), encoding));
			String line = null;
			while ((line = reader.readLine()) != null) 
			{
				content += line + "\n";
        	}
        	reader.close();
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return content;
    }
	
	
	/**
	 * 将一个InputStream中的数据写入至SD卡中
	 */
	public File writeStreamToSDCard(String dirpath,String filename,InputStream input) {
		File file = null;
		OutputStream output=null;
		try {
			//创建目录；
			createDir(dirpath);
			//在创建 的目录上创建文件；
			file = createFile(dirpath+filename);
			output=new FileOutputStream(file);
			byte[]bt=new byte[4*1024];
			while (input.read(bt)!=-1) {
				output.write(bt);
			}
			//刷新缓存，
			output.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try{
				output.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
    }
	
	//获取文件夹下列表
	public String[] getDirList(String path)
	{
		File dirFile=new File(path);
		if(dirFile.isDirectory())
		{
			String fileList[]=dirFile.list();
			return fileList;
		}
		return null;
	}
	
	
}
