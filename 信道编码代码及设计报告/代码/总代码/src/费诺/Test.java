package 费诺;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import 香农.Bean;

public class Test {
	final static String[] p = {"","","","","","","","","","",""};
	 
	 public static String s;
       public Test(String content) {
       	this.s=content;
       }
       
     public static list test1() {
		 //static String[] p=new String[20];

	//费诺編碼程序
	        double[] a = new double[20];
	       
//	        System.out.print("请输入字符串：");
//	        Scanner in = new Scanner(System.in);		        
//	        String s = in.nextLine();
	        
	        s = s.toLowerCase();
	        HashMap<Character, Integer> result = new HashMap<Character, Integer>();

	        //在此添加代码，将程序补充完整
	        for (int i = 0; i < s.length(); i++) {
	            Character key = Character.valueOf((s.charAt(i)));
	            if (!result.containsKey(key))
	                result.put(key, 1);
	            else {
	                int val = result.get(key);
	                result.put(key, val + 1);
	            }
	        }
	        //1.
	        System.out.println("字符串长度：" + s.length());
	        int q = 0;
	        double aa;
	        //定义两个集合分别存储字符及出现的次数，还有字符的概率
            Map<Object, Object> zifu = new HashMap<>();
            List<Object> gailv = new ArrayList<>();
            
	        for (HashMap.Entry i : result.entrySet()) {
	            float b = Integer.parseInt(i.getValue().toString());
	            aa = (double) b / s.length();
	            a[q] = aa;
	            //2.

	            zifu.put(i.getKey(), i.getValue());
	            
	            String gailv1 =String.format("%.3f",a[q]);
	            gailv.add(gailv1);
	            
//	            System.out.println("字符" + i.getKey() + "出现" + i.getValue() + "次,"+"概率为：" + a[q]);
	            q++;
	        }
	        int count = 0;
	        for (int i = 0; i < 20; i++) {
	            if (a[i] == 0)
	                break;
	            else
	                count++;
	        }
	       // System.out.println(count);

	       /*for (int i = 0; i < count; i++) {
	            System.out.println(a[i]);
	        }*/
	        //double[] a= {0.2,0.2,0.2,0.2,0.2};
	        //int count = 5;

	        for(int i=0;i<count;i++){
	            for(int j=i;j<count;j++) {
	                if(a[i]<a[j]){
	                    double temp=a[i];
	                    a[i]=a[j];
	                    a[j]=temp;
	                }
	            }
	        }
	        fano(0,count-1,a);

	        //定义一个集合存储编码过程
//	        Map<Object, Object> mazi = new HashMap<>();
	        List<Object> list1=new ArrayList<Object>();
	        List<Object> list2=new ArrayList<Object>();
	        for(int j=0;j<count;j++) {
	        	//3.
	        	
	        	String convertedString =String.format("%.3f",a[j]);
	        	list1.add(convertedString);
	        	list2.add(p[j]);
//	        	mazi.put(convertedString,);
	        	
//	            System.out.printf("%.3f\t\t\t",a[j]); //编码前码字概率
//	            System.out.printf("%s\n",p[j]); //编码结果
	        }
	        //求熵
	        double sum = 0;
	        for (int i = 0; i <count; i++)
	        {
	            sum += a[i]* Math.log(a[i]) / Math.log(2.0);
	        }
	        sum = -sum;
	        //4.
	        System.out.println("熵是"+sum);
	        /*求信源码的平均长度*/
	        int len=0;
	        double aver=0;
	        double effiency=0;
	        for (int i = 0; i < count; i++) {
	            len+= p[i].length();
	            aver=(double)len/count;
	        }
	        //5.
	        System.out.println("平均码长："+aver);
	        effiency= (double) (sum/aver);
	        NumberFormat nt = NumberFormat.getPercentInstance();//获取格式化对象
	        nt.setMinimumFractionDigits(2);//设置百分数精确度2即保留两位小数
	        
	        //6.
	        System.out.println("编码效率："+ nt.format(effiency));
	        
	      //创建一个list对象将所有数据存入
	       // 长度，熵，平均码长，编码效率
	        
	        String length=""+s.length();
	        String sum1=String.format("%.6f",sum);
	        String aver1 =String.format("%.6f",aver);
	        list l=new list(length,sum1,aver1,nt.format(effiency),zifu,list1,list2,gailv);
	        //字符，出现的次数，概率存入   
	      return l;
	}
    
   public static void fano(int m,int n,double y[]) {
       int i,k;
       double sum=0.0,s=0.0,s1;
       if(m==n) {
           return;
       }
       for(i=m;i<=n;i++) {
           sum=sum+y[i];
       }
       k=m;
       do{
           s1=s;
           s=s+y[k++];
       }while(s<=sum-s);
       if((sum-2*s1)<=(2*s-sum)) {
           k--;
       }
       for(i=m;i<k;i++) {
           p[i]=p[i]+"0";
       }
       for(i=k;i<=n;i++) {
           p[i]=p[i]+"1";
       }
       fano(m,k-1,y);
       fano(k,n,y);
   }
	
}

	
	