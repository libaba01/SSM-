package 算术;
import java.util.*;
public class ArithCode {
//    public static void main(String[] args) {
//        ArithCode a=new ArithCode();
//        a.setF(args);
//        a.test1();
//    }

    public int n;
    float []py=new float[n];//原来的符号概率(10进制,下同)
    String []NQ=new String[n+1];//排好顺序前符号序列
    public int m;
    String []F=new String[10+1];//输入符号序列（符号数不超过10）

    public void setN(int n) {
        this.n = n;
    }

    public void setPy(float[] py) {
        this.py = py;
    }

    public void setNQ(String[] NQ) {
        this.NQ = NQ;
    }

    public void setM(int m) {
        this.m = m;
    }

    public void setF(String[] f) {
        F = f;
    }

 


    public ArithCode(){
//        Scanner input=new Scanner(System.in);
//        System.out.print("请输入符号数：");
//        int i,j;

//        int n=input.nextInt();



    }


    public void test1(){
    	   float []pc=new float[n];//原来的符号概率
    	    float []pp=new float[n];//排好后的符号概率

    	    float []P=new float[n];
    	    String []NH=new String[n+1];//排好顺序后符号序列

    	    float []A=new float[10];
    	    float []C=new float[10];
    	    char []N=new char[100];
    	            float v;
        for(int i=0;i<n;i++){
//            System.out.print("请输入符号：");
////            NQ[i]=input.next();
//            System.out.print("请输入此符号对应的符号概率：");
////            py[i]=input.nextFloat();
            float count=0;
            for(int j=0;j<=i;j++){
                count+=py[j];
            }
            if(py[i]>1||count>1){
                System.out.println("输入错误，请重新输入！");
                i--;
            }
        }
        for(int i=0;i<n;i++){
            pc[i]=py[i];
        }
        Arrays.sort(py);
        System.out.print("排序后的符号概率：");
        for(int i=0;i<n;i++){
            pp[i]=py[n-i-1];
            System.out.print("\t"+pp[i]);
        }
        System.out.println();
        System.out.print("排序后的符号序列：");
        for(int i=0;i<n;i++){
            out:{for( int j=0;j<n;j++){
                if(pp[i]==pc[j]){
                    NH[i]=NQ[j];
                    pc[j]=0;
                    break out;
                }
            }
            }
            System.out.print("\t"+NH[i]);
        }
        System.out.println();
        System.out.print("符号的累加概率为：");
        for (int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                P[i]+=pp[j];
            }
            System.out.print("\t"+P[i]);
        }
        System.out.println();
        System.out.print("请输入编码序列长度：");
//        int m=input.nextInt();
        System.out.print("请输入消息序列："+"\n");
        
        for (int i=0;i<m;i++){
//            F[i]=input.next();
        }
        C[0]=0;A[0]=1;
        for (int i=1;i<m+1;i++){
            for(int j=0;j<n;j++){
                if(F[i-1].equals(NH[j]))
                {
                    C[i]=C[i-1]+A[i-1]*P[j];
                    A[i]=A[i-1]*pp[j];
                    System.out.print("\t\t"+"C["+i+"]="+C[i]);
                    System.out.print("\t\t"+"A["+i+"]="+A[i]);
                    System.out.println();
                }
            }
        }
        int w=(int)Math.ceil(-Math.log((double)A[m])/Math.log((double)2));
        System.out.print("码长为："+w);
        System.out.println();
        System.out.print("码字为：            ");
        for(int i=0;i<w;i++){
            v=C[m]*2;
            if(v>=1){
                N[i]='1';
                C[m]=v-1;
            }
            else {N[i]='0';
                C[m]=v;
            }
            System.out.print("\t"+N[i]);
        }
    }

	public int getN() {
		return n;
	}

	public float[] getPy() {
		return py;
	}

	public String[] getNQ() {
		return NQ;
	}

	public int getM() {
		return m;
	}

	public String[] getF() {
		return F;
	}
}