package 香农;

import java.util.*;

import 信道编码.fetch_text;

public class ShannonEncoding {
	
    public String line ;
    public ShannonEncoding(String content) {
		// TODO Auto-generated constructor stub
    	this.line=content;
	}
	List<Bean> beans = new ArrayList<>();
    double averageCodingLength;
    double averageSelfInformation;
    //计算次数
    public void getProbability(){
        //创建HashMap集合，键是character，值是Integer
        Map<Character, Integer> hm = new HashMap();
        //double probability = 1.0/line.length();
        //循环遍历字符串，得到每个字符
        for(int i = 0; i < line.length() ; i++) {
            //通过charAt方法，将字符串的索引值，作为键添加到HashMap集合中
            char key = line.charAt(i);
            //使用HashMap集合的get方法，对集合中的键（字符）进行判断
            //创建Integer对象存储值
            Integer value = hm.get(key);
            //返回值为空，将字符作为键，1作为存储值
            if (value == null) {
                hm.put(key, 1);
            } else {
                //返回值不为空，说明此字符在集合中出现过，将字符作为键，值加一
                // 重新存储键(字符)和对应的值
                value++;
                hm.put(key, value);
            }
        }
        List<Map.Entry<Character, Integer>> infoIds = new ArrayList<Map.Entry<Character, Integer>>(hm.entrySet());
        //根据value排序
        Collections.sort(infoIds, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        //排序后
        for (int i = 0; i < infoIds.size(); i++) {
            String s = infoIds.get(i).toString();
            String[] kv = s.split("=");
            Bean bean = new Bean(kv[0],Integer.parseInt(kv[1]));
            beans.add(bean);
        }
        //计算概率
        for (int i = 0; i < beans.size();i++){
            Bean bean = beans.get(i);
            bean.setP((bean.getCount()+0.0)/line.length());
        }
        //计算自信息
        for (int i = 0; i < beans.size();i++){
            Bean bean = beans.get(i);
            bean.set_logp(-(Math.log(bean.getP())/Math.log(2)));
        }
        //对应码长
        for (int i = 0; i < beans.size();i++){
            Bean bean = beans.get(i);
            bean.setCodingLength((int)(bean.get_logp()+0.9999999999999));
        }
        //累计概率
        for (int i = 0; i < beans.size();i++){
            Bean bean = beans.get(i);
            double _p = 0;
            for (int j = 0;j < i;j++){
                Bean bean1 =beans.get(j);
                _p += bean1.getP();
            }
            bean.setCumulativeprobability(_p);
        }
        //十进制转二进制
        for (int i = 0; i < beans.size();i++){
            Bean bean = beans.get(i);
            StringBuilder sb = new StringBuilder("");
            double leijigailv = bean.getCumulativeprobability();
            for (int j = 0; j < bean.getCodingLength();j++){
                sb.append((int)(leijigailv*2));
                leijigailv=2*leijigailv-(int)(leijigailv*2);
            }
            bean.setCodeLetter(String.valueOf(sb));
        }
    }
    //平均码长
    public double averageCodingLength(){
        for (int i = 0; i < beans.size();i++){
            Bean bean = beans.get(i);
            averageCodingLength+=bean.getCodingLength()*bean.getP();
        }
        return averageCodingLength;
    }
    //编码效率
    public double codeEfficiency(){
        for (int i = 0; i < beans.size();i++){
            Bean bean = beans.get(i);
            averageSelfInformation+=bean.get_logp()*bean.getP();
        }
        return averageSelfInformation/this.averageCodingLength;
    }
    //码字
    //创建一个集合将编码过程的内容存入集合
    public HashMap codeLetter(){
    	HashMap<String,String> map = new HashMap<String,String>() ;
        for (int i = 0; i < beans.size();i++){
            Bean bean = beans.get(i);
//            System.out.println(bean.getKey() + "码字" + bean.getCodeLetter());
            map.put(bean.getKey(), bean.getCodeLetter());
        }
        
        return map;
    }
}
