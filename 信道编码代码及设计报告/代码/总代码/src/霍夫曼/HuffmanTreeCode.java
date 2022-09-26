package 霍夫曼;
import java.io.*;
import java.util.*;

//利用霍夫曼编码实现图像 解压与压缩
public class HuffmanTreeCode {
    public static void main(String[] args) {

        //对文件进行压缩操作
//        String srcFile="C:\\Users\\lenovo\\Desktop\\R-C.jpg";
//        String zipFile="C:\\Users\\lenovo\\Desktop\\fi.jpg";
//        final String s = zipFile(srcFile, zipFile);
//        System.out.println("***************************霍夫曼编码进行压缩***************************");
//        System.out.println("*****************************文件压缩成功*****************************");
//        System.out.println(s);


        //对文件进行解压操作
        String zipFile = "C:\\Users\\lenovo\\Desktop\\fi.jpg";
        String unZipFile = "C:\\Users\\lenovo\\Desktop\\fi11.jpg";
        String s = unZipFile(zipFile, unZipFile);
        System.out.println("***************************霍夫曼编码进行解压***************************");
        System.out.println("*****************************文件解压成功*****************************");
        System.out.println(s);
    }

    /**
     * 对文件进行解压操作
     *
     * @param zipFile 带解压文件的全路径
     * @param dstFile 解压后文件放到哪里（目标路径）
     */
    public static String unZipFile(String zipFile, String dstFile) {
        FileInputStream is = null;
        ObjectInputStream ois = null;
        FileOutputStream os = null;
        int length1 = 0;
        int length2 = 0;
        int i = 0;
        double j = 0.00;
        try {
            //创建输入流
            is = new FileInputStream(zipFile);
            //将获取的数据进行解压操作
            ois = new ObjectInputStream(is);

            byte[] huffmanBytes = (byte[]) ois.readObject();
            //获取解压前的文件大小
            length1 = huffmanBytes.length;

            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            byte[] srcbytes = huffmanUnZip(huffmanCodes, huffmanBytes);
            length2 = srcbytes.length;
            i = length2 -length1;
            j = length2/length1;
            //创建输出流
            os = new FileOutputStream(dstFile);
            os.write(srcbytes);
            return "***解压前文件大小："+length1+"***解压后文件大小："+length2+"***解压偏差："+i+"***解压偏差率："+j+"%***";

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return "***解压前文件大小："+length1+"***解压后文件大小："+length2+"***解压偏差："+i+"***解压偏差率："+j+"%***";

    }


    /**
     * 对文件进行压缩操作
     *
     * @param srcFile 文件的原始路径
     * @param dstFile 文件的目标路径
     */
    public static String zipFile(String srcFile, String dstFile) {
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        int length1 = 0;
        int length2 = 0;
        int i = 0;
        double j = 0.00;
        try {
            //创建输入流
            is = new FileInputStream(srcFile);
            //将输入流中的数据 存入到bytesFile中
            byte[] bytesFile = new byte[is.available()];
            //获取压缩前的文件大小
            length1 = bytesFile.length;

            is.read(bytesFile);
            byte[] bytes = huffmanZip(bytesFile);
            length2 = bytes.length;

            //创建输出流 ， 将压缩后的 文件进行输出
            os = new FileOutputStream(dstFile);
            //因为FileOutputStream中 没有把Map集合写入到文件的方法，
            // 所以需要用ObjectOutputStream 将Map集合 封装成对象，写入到文件。
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的 字节数组 压入压缩文件中
            oos.writeObject(bytes);
            //把哈夫曼编码  压入压缩文件中 (如果不压哈夫曼编码表  后期就没有办法解压文件)
            oos.writeObject(huffmanMap);

            // 压缩偏差
            i = length1 - length2;
            //压缩偏差率
            j = length1/length2;
            return "***压缩前文件大小："+length1+"***压缩后文件大小："+length2+"***压缩偏差："+i+"***压缩偏差率："+j+"%***";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return "***压缩前文件大小："+length1+"***压缩后文件大小："+length2+"***压缩率偏差："+i+"***";
    }


    /**
     * 将传进来的二进制数组进行转化
     *
     * @param haffmanMap
     * @param srcbytes   待转化的原始二进制数组
     * @return 压缩前的样子
     */

    private static byte[] huffmanUnZip(Map<Byte, String> haffmanMap, byte[] srcbytes) {

        String s = bytesToBitsting(srcbytes);

        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> maps : haffmanMap.entrySet()) {
            map.put(maps.getValue(), maps.getKey());
        }
        int count = 1;
        boolean flage = true;
        Byte b = null;
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < s.length(); ) {
            String temp = null;
            while (flage) {
                temp = s.substring(i, i + count);
                b = map.get(temp);
                if (b != null) {
                    i += count;
                    count = 1;
                    flage = false;
                } else {
                    count++;
                }
            }
            list.add(b);
            flage = true;
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }


    /**
     * 将转化后的数组 转化为二进制数字符串
     *
     * @param bytes 压缩后的数组
     * @return 该bytes数组对应的二进制字符串
     */
    private static String bytesToBitsting(byte[] bytes) {
        boolean flag = true;
        String str = "";
        for (int i = 0; i < bytes.length; i++) {
            int b = bytes[i];
            if (i != bytes.length - 1) {
                b=b|256;
            } else {
                flag = false;
            }
            String s = Integer.toBinaryString(b);
            if (flag) {
                String substring = s.substring(s.length() - 8);
                str += substring;
            } else {
                str += s;
            }
        }
        return str;
    }


    /**
     * 封装霍夫曼编码的每一步
     *
     * @param contentByte 原始的字符串对应的字节数组
     * @return 返回经过霍夫曼编码后的数组
     */
    private static byte[] huffmanZip(byte[] contentByte) {
        //第一步：将每一个字节装入到一个Node类中
        List<Node> nodes = getNodes(contentByte);
        //第二步：根据nodes  构建哈夫曼树
        Node nodeRoot = creatHuffmanTree(nodes);
        //第三步：根据哈夫曼树  生成对应的哈夫曼编码Map集合
        creatHuffmanCode(nodeRoot);
        //第四步：将生成的哈夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] zip = zip(contentByte, huffmanMap);
        return zip;
    }


    private static byte[] zip(byte[] contentByte, Map<Byte, String> huffmanMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte bytes : contentByte) {
            stringBuilder.append(huffmanMap.get(bytes));
        }

        //等价于 len=(stringBuilder.length()+7)/8
        int len;
        //len表示 有多少个byte，每个byte中含有8个位
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] huffmanByte = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {

            if (i + 8 > stringBuilder.length()) {
                huffmanByte[index] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
            } else {
                huffmanByte[index] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
            }
            index++;
        }

        return huffmanByte;
    }


    //定义一个Map集合去存储每个子结点的信息
    static Map<Byte, String> huffmanMap = new HashMap<>();
    //定义一个StringBuilder去存储某一叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    private static void creatHuffmanCode(Node node) {
        creatHuffmanCode(node, "", stringBuilder);
    }

    /**
     * 将每一个叶子结点对应的二进制编码输出出来
     *
     * @param node          根结点
     * @param track         边，左子树为0   右子树为1
     * @param stringBuilder 一个叶子节点的路径整个路径
     */
    private static void creatHuffmanCode(Node node, String track, StringBuilder stringBuilder) {
        //构造一个初始化为指定字符串内容的字符串构建器。
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(track);
        if (node != null) {
            //如果data为空，则不是叶子结点
            if (node.data == null) {
                creatHuffmanCode(node.leftNode, "0", stringBuilder2);
                creatHuffmanCode(node.rightNode, "1", stringBuilder2);
            } else {
                //如果找到叶子节点就将该结点和该系结点的值  加入到Map集合中
                huffmanMap.put(node.data, stringBuilder2.toString());
            }
        }

    }

    private static List<Node> getNodes(byte[] contentByte) {
        //用于存放 最后的哈夫曼树
        List nodes = new ArrayList<Node>();

        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : contentByte) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //构造霍夫曼树
    public static Node creatHuffmanTree(List<Node> nodes) {
        if (nodes == null) {
            return null;
        }

        while (nodes.size() > 1) {
            //将nodes按照  从小到打的顺序排列
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(null, left.weight + right.weight);
            parent.leftNode = left;
            parent.rightNode = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("为空，前序遍历失败");
        } else {
            root.preOrder();
        }
    }

}


class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node leftNode;
    Node rightNode;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public void preOrder() {
        if (this == null) {
            return;
        }
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
