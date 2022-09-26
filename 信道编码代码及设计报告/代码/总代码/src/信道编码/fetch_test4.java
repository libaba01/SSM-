package 信道编码;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.*;

import lib.ConsoleTextArea;
import 算术.ArithCode;
import 算术.ConsoleTextArea1;
import 费诺.Test;
import 香农.ShannonEncoding;
import 费诺.list;


//1.从当前输入文本框中获取文本信息给编码
//2.将编码后的所有结果传到当前的文本域中


//public class fetch_text_suanshu{
//	public static void main(String[] args) {
//	new fetch_test4 ();
// }
//}


     public   class fetch_test4 extends JFrame {

							int count = 0;
	
							String[] NQ = null;
							
							float []py = null;
								
							int mount = 0;
							
							String []F2 = null;
							
							ArithCode a=new ArithCode();
							
							
						

		public fetch_test4() {

			
			//一个输入文本框，一个文本域显示输入的信息
//			JButton sendBt;
//			JTextField inputField;
			JTextArea chatContent;
//			this.setLayout(new BorderLayout());
			chatContent = new JTextArea(12, 34); // 创建一个文本域
			// 创建一个滚动面板，将文本域作为其显示组件
			JScrollPane showPanel = new JScrollPane(chatContent);
			chatContent.setEditable(false);    // 设置文本域不可编辑
			JPanel inputPanel = new JPanel(); // 创建一个JPanel面板
			
			JTextField inputField1 = new JTextField(12);  // 创建一个文本框
			JButton sendBt1 = new JButton("符号个数");      // 创建一个发送按钮
			
			JTextField inputField2 = new JTextField(12);  // 创建一个文本框
			JButton sendBt2 = new JButton("符号");      // 创建一个发送按钮
			
			JTextField inputField3 = new JTextField(12);  // 创建一个文本框
			JButton sendBt3 = new JButton("符号概率");      // 创建一个发送按钮
			
			JTextField inputField4 = new JTextField(12);  // 创建一个文本框
			JButton sendBt4 = new JButton("编码序列长度");      // 创建一个发送按钮
			
			JTextField inputField5 = new JTextField(12);  // 创建一个文本框
			JButton sendBt5 = new JButton("消息序列");      // 创建一个发送按钮
			chatContent.append("请输入符号数：");
			
			// 为按钮1添加事件
						sendBt1.addActionListener(new ActionListener() { 
							// 为按钮添加一个监听事件e
							public void actionPerformed(ActionEvent e) {
								// 重写actionPerformed方法
								// 获取输入的文本信息 
								
			
								
								String content = inputField1.getText(); 
								int con=Integer.parseInt(content);
								count=con;
								a.setN(con);
								
								
				            // 判断输入的信息是否为空
								if (content != null && !content.trim().equals("")) {
				                     // 如果不为空，将输入的文本追加到到聊天窗口
									//在文本域中输出平均码长和编码效率															
										chatContent.append(count+"\n");																
									
								    } else {
				                    // 如果为空，提示短信内容不能为空
									chatContent.append("输入内容不能为空" + "\n");
								}
//								inputField1.setText("");      // 将输入的文本域内容置为空
							}
						});
			
						// 为按钮添加事件
						sendBt2.addActionListener(new ActionListener() { 
							// 为按钮添加一个监听事件e
							public void actionPerformed(ActionEvent e) {
								// 重写actionPerformed方法
								// 获取输入的文本信息
								// 重写actionPerformed方法
								// 获取输入的文本信息 
								
							
								
								String s3 = inputField2.getText();
		                        String [] spString = s3.split("\\s+");
		                        NQ=spString;
								a.setNQ(NQ);
								
				            // 判断输入的信息是否为空
								if (s3 != null && !s3.trim().equals("")) {
				                     // 如果不为空，将输入的文本追加到到聊天窗口
									chatContent.append("输入的字符为:");
										for(int i=0;i<NQ.length;i++) {
											chatContent.append(NQ[i]+"  ");
		
										}	
										chatContent.append("\n");
								    } else {
				                    // 如果为空，提示短信内容不能为空
									chatContent.append("输入内容不能为空" + "\n");
								}
//								inputField1.setText("");      // 将输入的文本域内容置为空
							}
						});
						
			
						// 为按钮添加事件
						sendBt3.addActionListener(new ActionListener() { 
							// 为按钮添加一个监听事件e
							public void actionPerformed(ActionEvent e) {
								// 重写actionPerformed方法
								// 获取输入的文本信息
	                           								
								String content = inputField3.getText();  
								String s4 = inputField3.getText();
		                        String [] spString1 = s4.split("\\s+");
		                        float [] sp=new float[spString1.length];
		                        for(int i=0;i<spString1.length;i++)
		                        	sp[i]=Float.parseFloat(spString1[i]);
		                        py=sp;
		                        
								a.setPy(py);
		
						
								
				            // 判断输入的信息是否为空
								if (s4 != null && !s4.trim().equals("")) {
				                     // 如果不为空，将输入的文本追加到到聊天窗口
									chatContent.append("输入字符概率为:");
										for(int i=0;i<NQ.length;i++) {
											chatContent.append(py[i]+"  ");
											
										}		
										chatContent.append("\n");
								    } else {
				                    // 如果为空，提示短信内容不能为空
									chatContent.append("输入内容不能为空" + "\n");
								}
//								inputField1.setText("");      // 将输入的文本域内容置为空
							}
						});
			
			
						// 为按钮添加事件
						sendBt4.addActionListener(new ActionListener() { 
							// 为按钮添加一个监听事件e
							public void actionPerformed(ActionEvent e) {
								// 重写actionPerformed方法
								// 获取输入的文本信息
	                   								 
								String content = inputField4.getText(); 
								int con=Integer.parseInt(content);
								mount=con;
								a.setM(mount);
		
						
								
				            // 判断输入的信息是否为空
								if (content != null && !content.trim().equals("")) {
				                     // 如果不为空，将输入的文本追加到到聊天窗口

											chatContent.append("编码序列长度为:"+mount+"  ");
											chatContent.append("\n");
								    } else {
				                    // 如果为空，提示短信内容不能为空
									chatContent.append("输入内容不能为空" + "\n");
								}
//								inputField1.setText("");      // 将输入的文本域内容置为空
							}
						});
			
			
						// 为按钮添加事件
						sendBt5.addActionListener(new ActionListener() { 
							// 为按钮添加一个监听事件e
							public void actionPerformed(ActionEvent e) {
								// 重写actionPerformed方法
								// 获取输入的文本信息
	                   
								
								String content = inputField4.getText();  
								//字符
		                        String s5 = inputField5.getText();
		                        String [] spString2 = s5.split("\\s+");
		                        F2=spString2;
		                        a.setF(F2);
						
								
				            // 判断输入的信息是否为空
								if (s5 != null && !s5.trim().equals("")) {
				                     // 如果不为空，将输入的文本追加到到聊天窗口
									chatContent.append("输入消息序列为:");
										for(int i=0;i<F2.length;i++) {
											chatContent.append(F2[i]+"  ");
										}																										
								    } else {
				                    // 如果为空，提示短信内容不能为空
									chatContent.append("输入内容不能为空" + "\n");
								}
								
								//定义一个对象,从构造方法里面传值								
								 first();
   					             
   					        //将控制台输出
   								try {
   									ConsoleTextArea1 c=new ConsoleTextArea1();
   									c.test();
   								} catch (IOException e1) {
   									// TODO Auto-generated catch block
   									e1.printStackTrace();
   								}
		
//								inputField1.setText("");      // 将输入的文本域内容置为空
							}
						});
//							
//			
//			Label label = new Label("输入文本信息"); // 创建一个标签
//			inputPanel.add(label);                // 将标签添加到JPanel面板
						
			inputPanel.add(inputField1);          // 将文本框添加到JPanel面板
			inputPanel.add(sendBt1);              // 将按钮添加到JPanel面板
			inputPanel.add(inputField2);          // 将文本框添加到JPanel面板
			inputPanel.add(sendBt2);              // 将按钮添加到JPanel面板
			inputPanel.add(inputField3);          // 将文本框添加到JPanel面板
			inputPanel.add(sendBt3);              // 将按钮添加到JPanel面板
			inputPanel.add(inputField4);          // 将文本框添加到JPanel面板
			inputPanel.add(sendBt4);              // 将按钮添加到JPanel面板
			inputPanel.add(inputField5);          // 将文本框添加到JPanel面板
			inputPanel.add(sendBt5);              // 将按钮添加到JPanel面板
			
			// 将滚动面板和JPanel面板添加到JFrame窗口
			this.add(showPanel, BorderLayout.CENTER);
			this.add(inputPanel, BorderLayout.SOUTH);
			this.setTitle("提交文本信息");
			this.setSize(1200, 600);
			this.addWindowListener(new FirstWindowListener2());
			this.setVisible(true);
		}	
		
		
		public void first() {
			a.test1();
		}
	}