package ·ÑÅµ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class list {
    private String length;
    private String sum;
    private String aver;
    private String effiency;
    
    Map<Object, Object> zifu = new HashMap<>();
    
    List<Object> list1=new ArrayList<Object>();
    List<Object> list2=new ArrayList<Object>();
    
	List<Object> gailv = new ArrayList<>();
    
//    public list(String length, String sum, String aver, String effiency, Map<Object, Object> zifu,
//			Map<Object, Object> mazi, List<Object> gailv) {
//		super();
//		this.length = length;
//		this.sum = sum;
//		this.aver = aver;
//		this.effiency = effiency;
//		this.zifu = zifu;
//		this.gailv = gailv;
//	}
	public Map<Object, Object> getZifu() {
		return zifu;
	}
	public void setZifu(Map<Object, Object> zifu) {
		this.zifu = zifu;
	}

	public List<Object> getGailv() {
		return gailv;
	}
	public void setGailv(List<Object> gailv) {
		this.gailv = gailv;
	}

    
    
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getAver() {
		return aver;
	}
	public void setAver(String aver) {
		this.aver = aver;
	}
	public String getEffiency() {
		return effiency;
	}
	public void setEffiency(String effiency) {
		this.effiency = effiency;
	}
	public List<Object> getList1() {
		return list1;
	}
	public void setList1(List<Object> list1) {
		this.list1 = list1;
	}
	public List<Object> getList2() {
		return list2;
	}
	public void setList2(List<Object> list2) {
		this.list2 = list2;
	}
	@Override
	public String toString() {
		return "list [length=" + length + ", sum=" + sum + ", aver=" + aver + ", effiency=" + effiency + ", zifu="
				+ zifu + ", list1=" + list1 + ", list2=" + list2 + ", gailv=" + gailv + "]";
	}

	public list(String length, String sum, String aver, String effiency, Map<Object, Object> zifu, List<Object> list1,
			List<Object> list2, List<Object> gailv) {
		super();
		this.length = length;
		this.sum = sum;
		this.aver = aver;
		this.effiency = effiency;
		this.zifu = zifu;
		this.list1 = list1;
		this.list2 = list2;
		this.gailv = gailv;
	}



}
