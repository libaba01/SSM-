package LZW;

import java.io.File;

public class Test {

	
	public static void main(String[] args)
	{
		Compress com = new Compress();
		Uncompress ucom = new Uncompress();
		
		File file1 = new File("data1.txt"); //带压缩的文件
		File file2 = new File("data2.txt"); //压缩生成文件
		File file3 = new File("data3.txt"); //解压目录文件
		
		com.compress(file1,file2); //文件压缩的方法
//		ucom.uncompress(file2, file3);//文件解压方法
		
	}
	
	
	
	
	
}
