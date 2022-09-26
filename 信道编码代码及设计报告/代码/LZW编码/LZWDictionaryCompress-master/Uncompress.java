package com.myf.lzwcompress1227;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Uncompress {
	HashMap< String,Integer> map = new HashMap<>();  //存储在解压过程中生成的字典 ,字符串查编码
    HashMap<Integer, String> map1 = new HashMap<>();  //存储在解压过程中生成的字典，编码查字符串
    ArrayList<Integer> highlist = new ArrayList<>(); //存放高八位对应的数值
    ArrayList<Integer> numlist = new ArrayList<>(); //存放获得的每一个字符的数据，存放的是int值
	String pw = "";  //前缀
	String cw ="";   //后缀
	String add="";  //合成字符串
	int code=256;
	
	/**
	 * 从压缩文件中还原得到字符串和哈希表
	 * @param file2
	 */
	public byte[] getStr(File file2)
	{
		String str="";
		try
		{
		   InputStream is = new FileInputStream(file2);
		   byte[] buffer = new byte[is.available()];
		   is.read(buffer);
		   str = new String(buffer); 
		   System.out.println("获得的字符串："+str);
		   is.close();
		   return buffer;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * 通过字符串获取字典并解压，写入目标文件
	 */
	public void writeStr(File file3,byte[] buffer)
	{
		int count=0;
		for(int i=0;i<256;i++)  //添加基本ascii字符进入码表
		{
			String a = (char)i+"";
			map1.put(i, a);
			map.put(a, i); 
		}	
        for(int i=0;i<buffer.length;i+=2) //存储高八位
        {
        	int a = buffer[i];
        	highlist.add(a);
        }
		for(int i=1;i<buffer.length;i+=2) //依次取出低八位,结合高八位信息转化为对应实际字符对应的数值
		{
			if(buffer[i]==-1&&buffer[i-1]==-1)//读到清表信息
			{
				System.out.println("读到清表信息");
				numlist.add(65525);
			}
			else
			{
				if(highlist.get(count)>0)  //高八位有数据,则其为编码数据
				{
					System.out.println("高八位有数据");
					System.out.println("高八位数据："+highlist.get(count));
					System.out.println("低八位数据："+buffer[i]);
					if(buffer[i]>=0)  //字节数据为正，可以直接用，低八位加上高八位*256
					{
						System.out.println("字节数据为正");
						int a=buffer[i]+256*highlist.get(count);
						numlist.add(a);
						System.out.println("添加数据："+a);
						System.out.println("");
					}
					else      //字节数据为负，加上256转为正，再加高位数据
					{
						System.out.println("字节数据为负");
						int a=buffer[i]+256+256*highlist.get(count);
						numlist.add(a);
						System.out.println("添加数据："+a);
					}
					
					
				}
				else  //高八位全为0，则其为普通ASCII数据
				{
					System.out.println("高八位全为0");
					if(buffer[i]>0)  //字节数据为正，可以直接用
					{
						System.out.println("字节数据为正");
						int a=buffer[i];
						numlist.add(a);
						System.out.println("添加数据："+a);
					}
					else      //字节数据为负，加上256转为正
					{
						System.out.println("字节数据为负");
						int a=buffer[i]+256;
						numlist.add(a);
						System.out.println("添加数据："+a);
					}
				}			
			}
			count++;			
		}		
		try
		{
			OutputStream os = new FileOutputStream(file3);  //获取文件输出流     
			for(int i=0;i<numlist.size();i++)  //逐个取出字符数据，编码输出
			{
				int n = numlist.get(i);  //获取字符对应数值
				System.out.println("读到的字符值："+n);
				if(map.containsValue(n))  //字典中存在
				{
					System.out.println("字典中存在");
					cw=map1.get(n);   //获取对应字符串作为当前字符串
					if(pw!="")  //判断前缀不为空
					{
						System.out.println("前缀不为空");
						System.out.println("查询当前字典");
	                    
						add=pw+cw.charAt(0); //合成组合词
						System.out.println("上一个："+pw);
						System.out.println("当前："+cw);
						System.out.println("合成："+add);
						System.out.println("code:"+code);
						map1.put(code,add ); //更新词典
						map.put(add, code);
						code++;
						os.write(cw.getBytes("GBK")); //写当前读到的那个字符串
						System.out.println("写入："+cw);
	
					}
					else    //前缀为空，是第一个字符，直接写
					{
						System.out.println("前缀为空，第一个字符");
						os.write(cw.getBytes("GBK")); //把字符按照两个字节的单位写出去
						System.out.println("写入："+cw);
					} 					
				}
				else     //字典中不存在，则从前缀中获取信息得到当前数字对应的字符串，写入文件并写入字典
				{
					System.out.println("字典中不存在");
					System.out.println("pw:"+pw);
					cw=pw+pw.charAt(0); 
					map.put(cw, code);  //两表对应增加字典内容
					map1.put(code, cw);
					code++;
					os.write(cw.getBytes("GBK")); //写出当前字符串内容	
					System.out.println("写入："+cw);
				}	
				pw=cw;  //当前的字符串变为下一次循环的前缀
				
				if(map1.size()==65535||map.size()==65535) //编码溢出，重置哈希表
				{
					map1.clear();
					map.clear();
					for(int j=0;j<256;j++)
					{
						map.put((char)j+"", j);
						map1.put(j, (char)j+"");
					}
					code=256;
					pw="";
				}
			}
			os.close(); //关闭资源
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
//		pre=str.charAt(0)+"";
//		suf=str.charAt(1)+"";  //读取前两个字符
//		add=pre+suf;
//		try
//		{				
//				map1.put(code, add); //前两个字符为第一组字符串
//				map.put(add, code); 
//				System.out.println("添加第一组键值对："+"code:"+code+"  add:"+add);
//				code++;
//				dos.writeChar(pre.charAt(0));
//				System.out.println("写字符到文件："+pre.charAt(0));
//				pre=str.charAt(0)+"";
//				
//				for(int i=0;i<numlist.size();i++)   //逐个取出字符数据，编码输出
//				{
//					int n =numlist.get(i);
//					if(str.charAt(i)>255)   //大于255，则需要转化为字符串
//					{
//						System.out.println("字符大于255");
//						get=map1.get(str.charAt(i)); //字符对应的数字转为字符串
//						System.out.println("获取对应的字符串："+get);
//						char x = get.charAt(0);  //获取第一个字符
//						suf=x+"";				
//						add=pre+suf;
//							if(map1.get(add)==null) //如果map中不包含前缀加后缀第一个字符，则添加进字典
//							{
//								System.out.println("字典中不存在该字符串对应的键值对");
//								map1.put(code, add); //添加进字典
//								map.put(add, code);
//								System.out.println("添加键值对："+"code:"+code+"  add:"+add);
//								dos.writeChars(pre);   //写前缀
//								System.out.println("写前缀到文件："+pre);
//								code++;
//							}
//							else     //包含则继续下一个字符的判断
//							{
//								System.out.println("字典中有该字符串的键值对");
//								pre=add;
//							}
//					}
//					else   //读到一个普通的字符
//					{
//						System.out.println("读到一个小于255的普通字符");
//						suf=str.charAt(0)+"";
//						add=pre+suf;
//						if(map.get(add)==null)  //在字符串key中找不到相应键值对
//						{				
//							System.out.println("字典中不存在该字符串对应的键值对");
//							map1.put(code, add);
//							map.put(add, code);
//							System.out.println("添加键值对："+"code:"+code+"  add:"+add);
//							dos.writeChars(pre);
//							System.out.println("写前缀到文件："+pre);
//							code++;
//						}
//						else
//						{
//							System.out.println("字典中有该字符串的键值对");
//							pre=add;
//						}
//					}			
//				}
//			   dos.close();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
	}
	

	
	
	public void uncompress(File file2,File file3)
	{
		byte[] buffer = getStr(file2); //解压文件,获取字符串
		writeStr(file3,buffer);  //写文件	
	}
}
