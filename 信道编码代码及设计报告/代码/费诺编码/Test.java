import java.text.NumberFormat;
import java.util.*;

public class Test {
    static String[] p;
    public static void main(String[] args) {

//费诺編碼程序
        //键盘输入一个字符串
       /* double []a = new double[1000];
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串");
        String line = sc.nextLine();*/
//键盘输入一个字符串
        double []a = new double[1000];
        String line = "skdheajsnfc";
        TreeMap<Character, Integer> hm = new TreeMap<>();
        //遍历字符串，得到每一个字符
        for (int i = 0; i < line.length(); i++) {
            char key = line.charAt(i);
            //拿得到的每一个字符作为键到HashMap集合种去找对应的值，看其返回值
            Integer value = hm.get(key);
            if (value == null){
                //如果返回值是null，说明该字符在HashMap种不存在，就把该字符作为键，1作为值存储
                hm.put(key,1);
            }else{
                //如果返回值不是null，说明该字符在HashMap集合中存在，把该值+1，然后从新存储该字符和对应的值
                value++;
                hm.put(key,value);
            }
        }
        List<Bean> beans = new ArrayList<>();
        for (int i = 0; i < beans.size();i++){
            Bean bean = beans.get(i);
            bean.setP((bean.getCount()+0.0)/line.length());
            a[i]= bean.getP();
        }


        for(int i=0;i<beans.size();i++){
            for(int j=i;j<beans.size();j++) {
                if(a[i]<a[j]){
                    double temp=a[i];
                    a[i]=a[j];
                    a[j]=temp;
                }
            }
        }

        fano(0,beans.size()-1,a);


        for(int i=0;i<beans.size();i++) {
            System.out.printf("%.3f\t\t\t",a[i]);
            System.out.printf("%s\n",p[i]);
        }
        //求熵
        double sum = 0;
        for (int i = 0; i <beans.size(); i++)
        {
            sum += a[i]* Math.log(a[i]) / Math.log(2.0);
        }
        sum = -sum;
        System.out.println("熵是"+sum);
        /*求信源码的平均长度*/
        int len=0;
        float aver=0;
        float effiency=0;
        for (int i = 0; i < beans.size(); i++) {
            len+= p[i].length();
            aver=(float)len/beans.size();
        }
        System.out.println("平均码长："+aver);
        effiency= (float) (sum/aver);
        NumberFormat nt = NumberFormat.getPercentInstance();//获取格式化对象
        nt.setMinimumFractionDigits(2);//设置百分数精确度2即保留两位小数
        System.out.println("编码效率："+ nt.format(effiency));

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



