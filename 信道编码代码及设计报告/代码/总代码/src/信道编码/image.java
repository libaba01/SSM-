package 信道编码;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class image {
	public static void main(String[] args) {
        new image1();
	}
}



class image1 extends Frame{
	public image1() {
		//Frame框架
		Frame frame=new Frame("图像压缩");
	//Panel容器
        Panel p1 = new Panel(new GridLayout(4, 2,20,10));//3行2列 水平间距20 垂直间距10
      //第一行
        Label label1=new Label("图像压缩",Label.CENTER);
		p1.add(label1);
		

		//第二行
		Label label2=new Label("请选择压缩算法",Label.CENTER);
		p1.add(label2);
		
		//第三行
		Button but1 = new Button("霍夫曼");
        Button but2 = new Button("游程");
        but1.setBackground(Color.blue);
        
        but2.setBackground(Color.yellow);

		p1.add(but1);
		p1.add(but2);
		
		//为每个按钮添加鼠标单击左键事件
		
				//霍夫曼
						but1.addMouseListener(new MouseListener() {
							
							@Override
							//鼠标按下
							public void mouseClicked(MouseEvent e) {
								// TODO Auto-generated method stub
								//鼠标左击
								if(e.getButton()==e.BUTTON1){
									//鼠标点击按钮
									new fetch_image().init();
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
						
						//游程
								but2.addMouseListener(new MouseListener() {
									
									@Override
									//鼠标按下
									public void mouseClicked(MouseEvent e) {
										// TODO Auto-generated method stub
										//鼠标左击
										if(e.getButton()==e.BUTTON1){
											//鼠标点击按钮
											new fetch_image_youcheng().init();
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
        frame.addWindowListener(new FirstWindowListener());
     
	}
	
}

//事件监听器类继承适配器类
class FirstWindowListener extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		Window window = (Window) e.getComponent();
		window.dispose();
	}
}
