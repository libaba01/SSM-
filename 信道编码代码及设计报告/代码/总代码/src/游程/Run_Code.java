package снЁл;
public class Run_Code 
{
	public int count;
	public int n = 1;

	public String codeColor;
	public String Code;
	
	public Run_Code(int count, String codeColor)
	{
		this.count = count;
		this.codeColor = codeColor;
	}
	
	public boolean equals(Run_Code code)
	{
		if((this.count == code.count) && (this.codeColor == code.codeColor))
			return true;
		else
			return false;
	}
	
	public String toString()
	{
		return "Run:" + codeColor + count + "------" + Code + "(n=" + n + ")";
	}
}
