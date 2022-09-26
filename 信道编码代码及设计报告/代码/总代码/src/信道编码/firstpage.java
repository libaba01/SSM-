package 信道编码;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import javax.swing.JFrame;

class second extends Frame implements ActionListener{
	public second() {
		//Frame框架
		Frame frame=new Frame("首页");
	//Panel容器
        Panel p1 = new Panel(new GridLayout(3, 2,20,10));//3行2列 水平间距20 垂直间距10
      //第一行
        Label label=new Label("信道编码进行图像文本压缩",Label.CENTER);
		p1.add(label);
		

		//第二行
        
        Button but1 = new Button("图像压缩");
        Button but2 = new Button("文本压缩");
        but1.setBackground(Color.blue);
        
        but2.setBackground(Color.yellow);

		p1.add(but1);
		p1.add(but2);
		
		// 为按钮1添加鼠标事件监听器
				but1.addMouseListener(new MouseListener() {
				
					@Override
					//鼠标按下
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						//鼠标左击
						if(e.getButton()==e.BUTTON1){
							//鼠标点击按钮图像压缩,跳转到图像压缩界面
							new image1();
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
		
				// 为按钮2添加鼠标事件监听器
				but2.addMouseListener(new MouseListener() {
				
					@Override
					//鼠标按下
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						//鼠标左击
						if(e.getButton()==e.BUTTON1){
							//鼠标点击按钮文本压缩，跳转到文本压缩界面
							
							new text1();
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
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER,20,40); //三个参数，对齐方式（居左，横向间隔20像素，纵向间隔40像素）
        frame.setLayout(fl);
        frame.add(p1);   
        //窗口关闭
        frame.addWindowListener(new FirstWindowListener1());
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

//事件监听器类继承适配器类
class FirstWindowListener1 extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		Window window = (Window) e.getComponent();
		window.dispose();
	}
}

public class firstpage{
	public static void main(String[] args) {
        new second();
	}
}


