package com.myf.lzwcompress1227;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Compress {

	HashMap<String, Integer> map = new HashMap<>();//压缩时的编码表
	String pre = ""; //前缀
	String suf = ""; //后缀
	String add = "";  //连接字符串，作为中间参数
	int code = 256; //从256开始编码，255~65535
	/**
	 * 读取文件数据，获取待处理字符串
	 */
	public String read(File file1)
	{
		String str="";
		try
		{
			InputStream is = new FileInputStream(file1);
			byte[] buffer = new byte[is.available()];//根据字节数建立缓冲区
			is.read(buffer); //一次性把文件读到buffer缓冲区
			str = new String(buffer); //字节数组转字符串		
			is.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return str;
	}

	/**
	 * 对字符串处理，生成LZW编码表，同时写入压缩文件
	 */
	public void write(String str,File file2)    
	{
		for(int i=0;i<256;i++)  //把256个字符放入码表
		{
			String a = (char)i+"";
			map.put(a, i);
		}
		try
		{
			OutputStream out = new FileOutputStream(file2); //向上转型	
			DataOutputStream dos = new DataOutputStream(out); //数据输出流
			pre = str.charAt(0)+"";  //把第一个字符作为前缀
			for(int i=1;i<str.length();i++)
			{
				if(code==65535)  //编码数量过多，需要清空map
				{
					System.out.println("重置");
					dos.writeInt(65535);   //写一个信号表示清零
					map.clear();  //清空
					code=256; //计数置256
					pre="";   //前缀置空
					for(int j=0;j<256;j++)  //把256个字符放入码表
					{
						String a = (char)j+"";
						map.put(a, j);
					}
				}
				suf=str.charAt(i)+""; //获取后缀字符
				add=pre+suf; //连接
				if(map.get(add)==null)   ///map在不存在该字符串，则把该字符串写入
				{
					System.out.println("合成字符串："+add);
					System.out.println("对应编码："+code);
					map.put(add,code);  //把当前字符串作为字典存入哈希表，从256开始
					add=""; //清空中间字符串
					System.out.println("写入的前缀："+pre);
					System.out.println("它的编码："+map.get(pre));	
//					Set <String> set = map.keySet();
//					Iterator<String> it = set.iterator();
//					int add=0;
//					while(it.hasNext()&&add<=255)
//					{
//						System.out.println(map.get(it.next()));
//						add++;
//					}
					if(pre.length()==1)
						dos.writeChar(pre.charAt(0));
					else
				        dos.writeChar(map.get(pre));
				    pre=suf;
				    code++;
				}
				else
				{
			        pre=add;  //已存在于map，则继续判断，把它作为前缀
				}
				if(i==str.length()-1)
				{
					System.out.println("最后的字符："+pre);
					System.out.println("写入的编码："+map.get(pre));
					if(pre.length()==1)					
						dos.writeChar(pre.charAt(0));
					else
				        dos.writeChar(map.get(pre));
				}
			}
			dos.close();
			//System.out.print("输出码表："+map.toString());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	/**
	 * 压缩文件的对外接口
	 */
	public void compress(File file1,File file2)
	{
		String str =read(file1);
		System.out.println("从文件中读取到字符串："+str);
		write(str,file2);
		
	}
	
	
	
	
	
}
