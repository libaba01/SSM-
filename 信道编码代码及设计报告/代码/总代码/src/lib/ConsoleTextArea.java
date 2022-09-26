package lib;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

import LZW.Uncompress;
public class ConsoleTextArea extends JTextArea {
    public ConsoleTextArea(InputStream[] inStreams) {
        for(int i = 0; i < inStreams.length; ++i)
            startConsoleReaderThread(inStreams[i]);
    } // ConsoleTextArea()
    public ConsoleTextArea() throws IOException {
        final LoopedStreams ls = new LoopedStreams();
        // 重定向System.out和System.err
        PrintStream ps = new PrintStream(ls.getOutputStream());
        System.setOut(ps);
        System.setErr(ps);
        startConsoleReaderThread(ls.getInputStream());
    } // ConsoleTextArea()
    private void startConsoleReaderThread(
        InputStream inStream) {
        final BufferedReader br =
            new BufferedReader(new InputStreamReader(inStream));
        new Thread(new Runnable() {
            public void run() {
                StringBuffer sb = new StringBuffer();
                try {
                    String s;
                    Document doc = getDocument();
                    while((s = br.readLine()) != null) {
                        boolean caretAtEnd = false;
                        caretAtEnd = getCaretPosition() == doc.getLength() ?
                            true : false;
                        sb.setLength(0);
                        append(sb.append(s).append('\n').toString());
                        if(caretAtEnd)
                            setCaretPosition(doc.getLength());
                    }
                }
                catch(IOException e) {
                    JOptionPane.showMessageDialog(null,
                        "从BufferedReader读取错误：" + e);
                    System.exit(1);
                }
            }
        }).start();
    } // startConsoleReaderThread()
    // 该类剩余部分的功能是进行测试
    
    public void test() throws IOException {
    	
    	JFrame f = new JFrame("ConsoleTextArea测试");
        ConsoleTextArea consoleTextArea = null;
        try {
            consoleTextArea = new ConsoleTextArea();
        }
        catch(IOException e) {
            System.err.println(
                "不能创建LoopedStreams：" + e);
            System.exit(1);
        }
        consoleTextArea.setFont(java.awt.Font.decode("monospaced"));
        f.getContentPane().add(new JScrollPane(consoleTextArea),
            java.awt.BorderLayout.CENTER);
        f.setBounds(50, 50, 600, 500);
        f.setVisible(true);
        f.addWindowListener(new FirstWindowListener2());
        
        //
        Uncompress ucom = new Uncompress();
        File file1 = new File("data1.txt"); //带压缩的文件
		File file2 = new File("data2.txt"); //压缩生成文件
		File file3 = new File("data3.txt"); //解压目录文件
		
		ucom.uncompress(file2, file3);//文件解压方法
     
        
        
    }
    
//    public static void main(String[] args) throws IOException {
//        
//		
//        
//        
//    } // main()
    private static void startWriterTestThread(
        final String name, final PrintStream ps, 
        final int delay, final int count) {
        new Thread(new Runnable() {
            public void run() {
                for(int i = 1; i <= count; ++i) {
                    ps.println("***" + name + ", hello !, i=" + i);
                    try {
                        Thread.sleep(delay);
                    }
                    catch(InterruptedException e) {}
                }
            }
        }).start();
    } // startWriterTestThread()
} // ConsoleTextArea



//事件监听器类继承适配器类
class FirstWindowListener2 extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		Window window = (Window) e.getComponent();
		window.dispose();
	}
}