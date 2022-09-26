package 信道编码;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import 费诺.Test;
import 香农.ShannonEncoding;
import 费诺.list;


//1.从当前输入文本框中获取文本信息给编码
//2.将编码后的所有结果传到当前的文本域中


public class fetch_text_feinuo{
	public static void main(String[] args) {
	new fetch_test2 ();
 }
}

class fetch_test2 extends JFrame {
	
	

		public fetch_test2() {
			
			//一个输入文本框，一个文本域显示输入的信息
			JButton sendBt;
			JTextField inputField;
			JTextArea chatContent;
			this.setLayout(new BorderLayout());
			chatContent = new JTextArea(12, 34); // 创建一个文本域
			// 创建一个滚动面板，将文本域作为其显示组件
			JScrollPane showPanel = new JScrollPane(chatContent);
			chatContent.setEditable(false);    // 设置文本域不可编辑
			JPanel inputPanel = new JPanel(); // 创建一个JPanel面板
			inputField = new JTextField(20);  // 创建一个文本框
			sendBt = new JButton("提交");      // 创建一个发送按钮
			
			// 为按钮添加事件
			sendBt.addActionListener(new ActionListener() { 
				// 为按钮添加一个监听事件e
				public void actionPerformed(ActionEvent e) {
					// 重写actionPerformed方法
					// 获取输入的文本信息
					String content = inputField.getText();  
					
					//获取输入的文本信息之后，进行费诺编码
					Test t=new Test(content);
					list l1;
					l1=t.test1();					
					
	            // 判断输入的信息是否为空
					if (content != null && !content.trim().equals("")) {
	                     // 如果不为空，将输入的文本追加到到聊天窗口
						//在文本域中输出编码结果
					
						chatContent.append("字符串长度："+l1.getLength()+"\n");
						chatContent.append("熵:"+l1.getSum()+"\n"); 
						chatContent.append("平均码长:"+l1.getAver()+"\n"); 
						chatContent.append("编码效率:"+l1.getEffiency()+"\n"); 
					
						//输出字符出现的次数概率
						List<Object> gailv = new ArrayList<>();
						Map<Object, Object> zifu = new HashMap<>();
						
						gailv=l1.getGailv();
						zifu=l1.getZifu();
//						Set hashSet = new HashSet();
//						
//						List list=new ArrayList();
						
						for (int i = 0; i < gailv.size(); i++) {
							for(Entry<Object, Object> arg :zifu.entrySet()){				//遍历HashMap键值对
								String s = (String)gailv.get(i);
								chatContent.append("字符"+arg.getKey()+"出现次数为："+arg.getValue()+"\n");
	                            chatContent.append("    "+"概率为："+s+"\n");
							}
						}
//					if(hashSet.add(arg.getKey())) {
//						list.add(arg.getKey());
//						
//						list.add(arg.getKey());
//					}
//					
//					if(hashSet.add(arg.getValue())) {
//						list.add(arg.getValue());
//					}
//					if(hashSet.add(s)) {
//						list.add(s);
//					}
//								    	 
//						}
//						}
//                             
//						Iterator iterator = list.iterator();
//						while (iterator.hasNext()) {
//							chatContent.append("字符.出现次数.概率依次为："+iterator.next()+"\n");		
//						}	
//
//						for (int i = 0; i < gailv.size(); i++) {
//							chatContent.append("字符"+arg.getKey()+"出现次数为："+arg.getValue()+"\n");
//                            chatContent.append("    "+"概率为："+s+"\n");
//						}
//                                 

						
					    //输出编码过程
				        List<Object> list1=new ArrayList<Object>();
				        List<Object> list2=new ArrayList<Object>();
//						Map<Object, Object> mazi = new HashMap<>();
//						mazi=l1.getMazi();
  					    list1=l1.getList1();
  					  list2=l1.getList2();
  					  
  					Iterator it1 = list1.iterator();
  			        while(it1.hasNext()){
  			        	chatContent.append(it1.next()+"  ");
  			        }
                     chatContent.append("\n");
  			      Iterator it2 = list2.iterator();
			        while(it2.hasNext()){
			        	chatContent.append(it2.next()+"  ");
			        }
  					  
  					  
//						for(Entry<Object, Object> arg : mazi.entrySet()){	
//	
//							chatContent.append(arg.getKey()+"    "+arg.getValue()+"\n");
//							
//						}
//				
						
					    } else {
	                    // 如果为空，提示短信内容不能为空
						chatContent.append("输入内容不能为空" + "\n");
					}
					inputField.setText("");      // 将输入的文本域内容置为空
				}
			});
			Label label = new Label("输入文本信息"); // 创建一个标签
			inputPanel.add(label);                // 将标签添加到JPanel面板
			inputPanel.add(inputField);          // 将文本框添加到JPanel面板
			inputPanel.add(sendBt);              // 将按钮添加到JPanel面板
			// 将滚动面板和JPanel面板添加到JFrame窗口
			this.add(showPanel, BorderLayout.CENTER);
			this.add(inputPanel, BorderLayout.SOUTH);
			this.setTitle("提交文本信息");
			this.setSize(400, 300);
			this.addWindowListener(new FirstWindowListener2());
			this.setVisible(true);

		}
		
	}

