package 信道编码;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import LZW.Compress;
import LZW.Uncompress;

public class Text {
	public static void main(String[] args) {
        new text1();
	}
}

class text1 extends Frame{
	public text1() {
		//Frame框架
		Frame frame=new Frame("文本压缩");
	//Panel容器
        Panel p1 = new Panel(new GridLayout(4, 2,20,10));//3行2列 水平间距20 垂直间距10
      //第一行
        Label label1=new Label("文本压缩",Label.CENTER);
		p1.add(label1);
		

		//第二行
		Label label2=new Label("请选择压缩算法",Label.CENTER);
		p1.add(label2);
		
		//第三行
		Button but1 = new Button("费诺");
        Button but2 = new Button("香农");
        but1.setBackground(Color.blue);
        
        but2.setBackground(Color.yellow);

		p1.add(but1);
		p1.add(but2);
		
		//第四行
		Button but3 = new Button("算术");
        Button but4 = new Button("LZW");
        but3.setBackground(Color.blue);
        
        but4.setBackground(Color.yellow);

		p1.add(but3);
		p1.add(but4);
		
		
		
		
		//为每个按钮添加鼠标单击左键事件
		
		//费诺
				but1.addMouseListener(new MouseListener() {
					
					@Override
					//鼠标按下
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						//鼠标左击
						if(e.getButton()==e.BUTTON1){
							//鼠标点击按钮
							new fetch_test2 ();
						}	
					}
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
		
		
		//香农
		but2.addMouseListener(new MouseListener() {
			
			@Override
			//鼠标按下
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//鼠标左击
				if(e.getButton()==e.BUTTON1){
					//鼠标点击按钮
					new fetch_test1 ();
				}	
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//算术
		but3.addMouseListener(new MouseListener() {
			
			@Override
			//鼠标按下
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//鼠标左击
				if(e.getButton()==e.BUTTON1){
					//鼠标点击按钮
					new fetch_test4 ();
				}	
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//LZW
				but4.addMouseListener(new MouseListener() {
					
					@Override
					//鼠标按下
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						//鼠标左击
						if(e.getButton()==e.BUTTON1){
							//鼠标点击按钮
							
							new LZWGUI();
							Compress com = new Compress();
							Uncompress ucom = new Uncompress();
							
							File file1 = new File("data1.txt"); //带压缩的文件
							File file2 = new File("data2.txt"); //压缩生成文件
							File file3 = new File("data3.txt"); //解压目录文件
							
							com.compress(file1,file2); //文件压缩的方法
							try {
								ucom.uncompress(file2, file3);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}//文件解压方法
						}	
					}
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				
				
				
				
		
		
		

		frame.setBounds(100, 100, 400, 300);
        frame.setLayout(new FlowLayout()); //设置布局管理器为FlowLayout
        frame.setVisible(true);
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,20,40); //对齐方式（居左，横向间隔20像素，纵向间隔40像素）
        frame.setLayout(fl);
        frame.add(p1);   
        //窗口关闭
        frame.addWindowListener(new FirstWindowListener2());
	}
	
}

//事件监听器类继承适配器类
class FirstWindowListener2 extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		Window window = (Window) e.getComponent();
		window.dispose();
	}
}
