package 信道编码;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

import 霍夫曼.HuffmanTreeCode;
public class fetch_image {
    private JFrame jf=new JFrame();
    private JPanel total=new JPanel(new BorderLayout());
    private JPanel pUpload=new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JLabel lFileName=new JLabel("选择要添加的路径");
    private JTextField tFileName=new JTextField(15);
    private JButton bBrowse=new JButton("浏   览");
    private JFileChooser uploadChooser=new JFileChooser();
    private JButton bUpload=new JButton("压   缩");
    private JPanel pDownLoad=new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JLabel lDownload=new JLabel("解压图片");
    private JFileChooser downloadChooser=new JFileChooser();
    private JButton bDownload=new JButton("解压");
    private JPanel pIcon=new JPanel(new FlowLayout(FlowLayout.LEFT));
    private ImageIcon icon=new ImageIcon(new ImageIcon(
            ""
    ).getImage().getScaledInstance(320,320,0));
    private JLabel lIcon=new JLabel(icon);
    Dimension faceSize=new Dimension(500,200);
    Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
    public void init(){
        pUpload.add(lFileName);
        pUpload.add(tFileName);
        pUpload.add(bBrowse);
        pUpload.add(bUpload);
        
        bBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadChooser.setCurrentDirectory(new File("."));
                int result=downloadChooser.showOpenDialog(jf);
                if (result==JFileChooser.APPROVE_OPTION){
                    String path=downloadChooser.getSelectedFile().getPath();
                    tFileName.setText(path);
                }
            }
        });
        
        //压缩
        bUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromFileName=tFileName.getText();
                String toFileName="C:/Users/lenovo/Desktop/png/"+System.currentTimeMillis()+".jpg";
                HuffmanTreeCode  ht=new HuffmanTreeCode();
                String yasuo;
                yasuo=ht.zipFile(fromFileName,toFileName);
               
                JOptionPane.showMessageDialog(jf,"图片压缩成功！"+"\n"+yasuo,"提示",JOptionPane.WARNING_MESSAGE);
            }
        });
        
        //解压
        pDownLoad.add(lDownload);
        pDownLoad.add(bDownload);
        
        bDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	String fromFileName=tFileName.getText();
            	String zipFile=fromFileName;
                String unZipFile="C:/Users/lenovo/Desktop/png"+System.currentTimeMillis()+".jpg";
                HuffmanTreeCode  ht=new HuffmanTreeCode();
                
                String jieya;
                jieya=ht.unZipFile(zipFile, unZipFile);
                
                
                JOptionPane.showMessageDialog(jf,"图片解压成功"+"\n"+jieya,"提示",JOptionPane.WARNING_MESSAGE);
                
            }
        });
        pIcon.add(lIcon);
        total.add(pUpload,BorderLayout.NORTH);
        total.add(pDownLoad,BorderLayout.CENTER);
        total.add(pIcon,BorderLayout.SOUTH);
        jf.add(total);
        jf.setSize(faceSize);
        jf.setLocation((int)(screenSize.width-faceSize.getWidth())/2,(int)(screenSize
        .height-faceSize.getHeight())/2);
        jf.setResizable(false);
        //关闭窗口
        jf.addWindowListener(new FirstWindowListener2());
        jf.setVisible(true);
    }
    public void write(String fromFile,String toFile){
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fis=new FileInputStream(fromFile);
            fos=new FileOutputStream(toFile);
            byte[] buf=new byte[1024];
            int hasRead=0;
            while ((hasRead=fis.read(buf))>0){
                fos.write(buf,0,hasRead);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fis.close();
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
 
    public static void main(String[] args) {
        new fetch_image().init();
    }
}

