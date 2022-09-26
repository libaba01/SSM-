package 信道编码;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;

import LZW.Compress;
import LZW.Uncompress;
import LZW.lzw;
import lib.ConsoleTextArea;
import 费诺.Test;
import 费诺.list;

class LZWGUI {
    private Frame f;// 定义窗体
    private MenuBar bar;// 定义菜单栏
    private TextArea ta;
    private Menu fileMenu;// 定义"文件"和"子菜单"菜单
    private MenuItem openItem, saveItem, closeItem;// 定义条目“退出”和“子条目”菜单项

    private FileDialog openDia, saveDia;// 定义“打开、保存”对话框
    private File file;//定义文件

    LZWGUI() {
        init();
    }

    /* 图形用户界面组件初始化 */
    public void init() {
        f = new Frame("LZW");// 创建窗体对象
        f.setBounds(300, 100, 650, 600);// 设置窗体位置和大小

        bar = new MenuBar();// 创建菜单栏
        ta = new TextArea();// 创建文本域

        fileMenu = new Menu("文件");// 创建“文件”菜单

        openItem = new MenuItem("打开");// 创建“打开"菜单项
        saveItem = new MenuItem("保存");// 创建“保存"菜单项
        closeItem = new MenuItem("退出");// 创建“退出"菜单项

        fileMenu.add(openItem);// 将“打开”菜单项添加到“文件”菜单上
        fileMenu.add(saveItem);// 将“保存”菜单项添加到“文件”菜单上
        fileMenu.add(closeItem);// 将“退出”菜单项添加到“文件”菜单上

        bar.add(fileMenu);// 将文件添加到菜单栏上

        f.setMenuBar(bar);// 将此窗体的菜单栏设置为指定的菜单栏。

        openDia = new FileDialog(f, "打开", FileDialog.LOAD);
        saveDia = new FileDialog(f, "保存", FileDialog.SAVE);

        f.add(ta,BorderLayout.CENTER);// 将文本域添加到窗体内
        
        Panel p = new Panel();
        //添加按钮
        JButton sendBt1 = new JButton("编码");      // 创建一个按钮

        
        JButton sendBt2 = new JButton("译码");      // 创建一个按钮

        
        p.add(sendBt1,"BorderLayout.WEST");
        p.add(sendBt2,"BorderLayout.EAST");
                f.add(p,BorderLayout.SOUTH);
        myEvent();// 加载事件处理

        

		
		// 为按钮添加事件
		sendBt1.addActionListener(new ActionListener() { 
			// 为按钮添加一个监听事件e
			public void actionPerformed(ActionEvent e) {
				Compress com = new Compress();
				//打开文件的路径和名称
				String dirpath1 = openDia.getDirectory();
				String fileName = openDia.getFile();
				
				File file1 = new File(dirpath1+fileName); //带压缩的文件
				File file2 = new File("data2.txt"); //压缩生成文件
				
				lzw l=com.compress(file1,file2);
				 //文件压缩的方法
				//将压缩的过程展示在文本域上
				
				ta.append("合成字符串："+"\n");
				Iterator<Object> iter1 = l.getList1().iterator();
			     while (iter1.hasNext()) {
			            String s = (String) iter1.next();
			            ta.append(s+"   ");
			            }
			     ta.append("\n");
			     ta.append("对应编码： "+"\n");
					Iterator<Object> iter2 = l.getList2().iterator();
				     while (iter2.hasNext()) {
				            String s = (String) iter2.next();
				            ta.append(s+"   ");
				            }
				     ta.append("\n");
				     
				     
				     ta.append("写入的前缀： "+"\n");
						Iterator<Object> iter3 = l.getList3().iterator();
					     while (iter3.hasNext()) {
					            String s = (String) iter3.next();
					            ta.append(s+"   ");
					            }  
					     ta.append("\n");
					     
					     
					  ta.append("它的编码： "+"\n");
							Iterator<Object> iter4 = l.getList4().iterator();
						     while (iter4.hasNext()) {
						            String s = (String) iter4.next();
						            ta.append(s+"   ");
						            }			     
						     ta.append("\n");
						     
						     
					  ta.append("最后的字符： "+"\n");
								Iterator<Object> iter5 = l.getList5().iterator();
							     while (iter5.hasNext()) {
							            String s = (String) iter5.next();
							            ta.append(s+"   "+"\n");
							            }
					  ta.append("写入的编码： "+"\n");
									Iterator<Object> iter6 = l.getList6().iterator();
								     while (iter6.hasNext()) {
								            String s = (String) iter6.next();
								            ta.append(s+"   "+"\n");
								            }
						     
						     
						     
//				for(Entry<Object, Object> arg : l.getFirst().entrySet()){	
//					ta.append("合成字符串："+arg.getKey()+"\n");
//					ta.append("对应编码："+arg.getValue()+"\n");
//				}
//				
//				for(Entry<Object, Object> arg : l.getSecond().entrySet()){	
//					ta.append("写入的前缀："+arg.getKey()+"\n");
//					ta.append("它的编码："+arg.getValue()+"\n");
//				}
//				
//				
//				for(Entry<Object, Object> arg : l.getSecond().entrySet()){	
//					ta.append("最后的字符："+arg.getKey()+"\n");
//					ta.append("写入的编码："+arg.getValue()+"\n");
//				}
				
				
				
				
				
//				String content = inputField.getText();

				// 判断输入的信息是否为空
//				if (content != null && !content.trim().equals("")) {
//                     // 如果不为空，将输入的文本追加到到聊天窗口
//					//在文本域中输出编码结果
////				
////					ta.append("字符串长度："+l1.getLength()+"\n");
////					ta.append("熵:"+l1.getSum()+"\n"); 
////					ta.append("平均码长:"+l1.getAver()+"\n"); 
////					ta.append("编码效率:"+l1.getEffiency()+"\n"); 
//				}
			}
		});
		
		
		sendBt2.addActionListener(new ActionListener() { 
			// 为按钮添加一个监听事件e
			public void actionPerformed(ActionEvent e) {
				Uncompress ucom = new Uncompress();
				
                String dirpath2 = openDia.getDirectory();
                String fileName = openDia.getFile();
                
				File file2 = new File(dirpath2+fileName); //要解压缩的文件
				File file3 = new File("data3.txt"); //解压到目录文件
				try {
					ucom.uncompress(file2, file3);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//文件解压方法
				
				//将解压的过程展示在一个新区域
				try {
					ConsoleTextArea c=new ConsoleTextArea();
					c.test();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
        f.setVisible(true);// 设置窗体可见

    }

    private void myEvent() {
        
        // 打开菜单项监听
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                openDia.setVisible(true);//显示打开文件对话框
                
                String dirpath = openDia.getDirectory();//获取打开文件路径并保存到字符串中。
                String fileName = openDia.getFile();//获取打开文件名称并保存到字符串中
                
                if (dirpath == null || fileName == null)//判断路径和文件是否为空
                    return;
                else
                    ta.setText(null);//文件不为空，清空原来文件内容。
                file = new File(dirpath, fileName);//创建新的路径和名称

                try {
                    BufferedReader bufr = new BufferedReader(new FileReader(file));//尝试从文件中读东西
                    String line = null;//变量字符串初始化为空
                    while ((line = bufr.readLine()) != null) {
                        ta.append(line + "\r\n");//显示每一行内容
                    }
                    bufr.close();//关闭文件
                } catch (FileNotFoundException e1) {
                    // 抛出文件路径找不到异常
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // 抛出IO异常
                    e1.printStackTrace();
                }

            }

        });
        
        // 保存菜单项监听
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (file == null) {
                    saveDia.setVisible(true);//显示保存文件对话框
                    String dirpath = saveDia.getDirectory();//获取保存文件路径并保存到字符串中。
                    String fileName = saveDia.getFile();////获取打保存文件名称并保存到字符串中
                    
                    if (dirpath == null || fileName == null)//判断路径和文件是否为空
                        return;//空操作
                    else
                        file=new File(dirpath,fileName);//文件不为空，新建一个路径和名称
                }
                    try {
                        BufferedWriter bufw = new BufferedWriter(new FileWriter(file));
                        
                        String text = ta.getText();//获取文本内容
                        bufw.write(text);//将获取文本内容写入到字符输出流
                        
                        bufw.close();//关闭文件
                    } catch (IOException e1) {
                        //抛出IO异常
                        e1.printStackTrace();
                    }
                

            }

        });
        
        // 退出菜单项监听
        closeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        
        // 窗体关闭监听
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	Window window = (Window) e.getComponent();
        		window.dispose();

            }

        });
    }

    public static void main(String[] args) {
        new LZWGUI();
    }
}