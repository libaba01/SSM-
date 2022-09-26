package 游程;
import java.util.*;

public class Run_Coding {
//	
//	public static void main(String[] args)
//	{
//		new Run_Coding();
//	}
	//输出codelist，即结果
	HashSet<Run_Code> codeList1 = new HashSet<Run_Code>();
	public HashSet<Run_Code> getCodeList1() {
		return codeList1;
	}

	public void setCodeList1(HashSet<Run_Code> codeList1) {
		this.codeList1 = codeList1;
	}

	public Run_Coding(String image) {
		int[][] test_image;
		int[][] ready_matrix;
		test_image = Image_IO.inputImage(image);
		ready_matrix = Image_IO.matrixChange(test_image);
		
		//showMatrix(ready_matrix);
		
		ArrayList<ArrayList> ready_numCode = new ArrayList<ArrayList>();
		
		ready_numCode = codeToNum(ready_matrix);
		
		/*
		for(ArrayList show : ready_numCode)
		{
			System.out.println(show);
		}
		*/
		
		HashSet<Run_Code> codeList = new HashSet<Run_Code>();
		codeList = buildCodeList(ready_numCode);
		
		//
		codeList1=codeList;
		System.out.println(codeList);
		
		/*
		int Sum = 0;
		for(Run_Code get : codeList)
		{
			Sum = Sum + get.n * get.count;
		}
		
		System.out.println(Sum);
		*/
		
		ArrayList<Integer> array_n = new ArrayList<Integer>();
		
		for(Run_Code getcode : codeList)
		{
			array_n.add(getcode.n);
		}
		
		/*
		HuffmanTreeBuilder.numToTree(array_n);
		*/
	}
	
	public static void showMatrix(int[][] matrix)
	{
		for (int i=0; i<matrix.length; i++)
		{
			for (int j=0; j<matrix[i].length; j++)
			{
				
				System.out.print("[" + matrix[i][j] + "]");
			}
			System.out.println();
		}
	}
	
	public static ArrayList<ArrayList> codeToNum(int[][] matrix)
	{
		ArrayList<ArrayList> MyNumCode = new ArrayList<ArrayList>();
		
		for (int i=0; i<matrix.length; i++)
		{
			ArrayList<Integer> C = new ArrayList<Integer>();
			
			int temp = -1;
			int count = 0;
			
			for (int j=0; j<matrix[i].length; j++)
			{
				if(j == 0)
				{
					temp = matrix[i][j];
					count++;
				}
				else if((matrix[i][j] == temp) && (j != (matrix[i].length - 1)))
				{
					count++;
				}
				else if((matrix[i][j] != temp) && (j != (matrix[i].length - 1)))
				{
					C.add(count);
					count = 1;
					temp = matrix[i][j];
				}
				else if((matrix[i][j] == temp) && (j == (matrix[i].length - 1)))
				{
					count++;
					C.add(count);
					count = 1;
					temp = matrix[i][j];
				}
				else
				{
					C.add(count);
					count = 1;
					C.add(1);
				}
				
			}
			MyNumCode.add(C);
		}
		
		return MyNumCode;
	}
	
	public static HashSet<Run_Code> buildCodeList(ArrayList<ArrayList> NumCode)
	{
		HashSet<Run_Code> MyCodeList = new HashSet<Run_Code>();
		
		ArrayList<Run_Code> A_Code = new ArrayList<Run_Code>();
		
		for(ArrayList<Integer> Row : NumCode)
		{
			Iterator<Integer> row_i = Row.iterator();
			int i = 1;
			
			for(;row_i.hasNext(); i++)
			{
				if((i % 2) == 1)
				{
					Run_Code newCode = new Run_Code((row_i.next()), "White");
					A_Code.add(newCode);
				}
				else
				{
					Run_Code newCode = new Run_Code((row_i.next()), "Black");
					A_Code.add(newCode);
				}
			}
		}
		
		for(Run_Code curCode : A_Code)
		{
			for(Iterator<Run_Code> i = MyCodeList.iterator(); i.hasNext();)
			{
				Run_Code cmpCode = i.next();
				
				if(curCode.equals(cmpCode))
				{
					curCode.n = cmpCode.n + 1;
					i.remove();
					break;
				}
			}
			MyCodeList.add(curCode);
		}
		
		return MyCodeList;
	}

	
}




