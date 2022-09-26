package снЁл;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Image_IO {
	
	
	public static int[][] inputImage(String image)
	{
		int[][] matrix;
		//m*n
		try {
			
			File testImage_f = new File(image);
			BufferedImage image_ref = ImageIO.read(testImage_f); 
			int m = image_ref.getWidth();
			int n = image_ref.getHeight();
			matrix = new int[n][m]; 
			
			for (int row=0; row<n; row++)
			{
				for (int col=0; col<m; col++)
				{
					matrix[row][col] = image_ref.getRGB(col, row);
				}
			}
			
			return matrix;
			
		}catch (IOException e){
			System.out.println("error!");
			return null;
		}
	}
	
	public static int[][] matrixChange(int[][] matrix)
	{
		int[][] ready = new int[matrix.length][matrix[0].length];
		
		for (int i=0; i<matrix.length; i++)
		{	
			for (int j=0; j<matrix[i].length; j++)
			{
				if(matrix[i][j] == Color.black.getRGB())
					ready[i][j] = 1;
				if(matrix[i][j] == Color.white.getRGB())
					ready[i][j] = 0;
			}
		}
		
		return ready;
	}
}
