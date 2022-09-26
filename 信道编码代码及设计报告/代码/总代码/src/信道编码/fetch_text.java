package 信道编码;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.*;

import 香农.ShannonEncoding;



//1.从当前输入文本框中获取文本信息给编码
//2.将编码后的所有结果传到当前的文本域中


public class fetch_text{
	public static void main(String[] args) {
	new fetch_test1 ();
 }
}

class fetch_test1 extends JFrame {
	
	//一个输入文本框，一个文本域显示输入的信息
		JButton sendBt;
		JTextField inputField;
		JTextArea chatContent;

		public fetch_test1() {
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
					
					//获取输入的文本信息之后，进行香农编码
					ShannonEncoding shannonEncoding = new ShannonEncoding(content);
			        shannonEncoding.getProbability();
			        HashMap<String, String> map1=shannonEncoding.codeLetter();
			        
					
					
	            // 判断输入的信息是否为空
					if (content != null && !content.trim().equals("")) {
	                     // 如果不为空，将输入的文本追加到到聊天窗口
						//在文本域中输出平均码长和编码效率
					
						
						for(Entry<String, String> arg : map1.entrySet()){				//遍历HashMap键值对
							chatContent.append(arg.getKey()+"码字"+arg.getValue()+"\n");
							
						}
						chatContent.append("平均码长:"+shannonEncoding.averageCodingLength() + "\n"+"编码效率:"+shannonEncoding.codeEfficiency()+ "\n"); 
						
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

