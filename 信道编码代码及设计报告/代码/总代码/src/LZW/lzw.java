package LZW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lzw {
	//定义三个集合存储相关信息
	List<Object> list1=new ArrayList<Object>();
	List<Object> list2=new ArrayList<Object>();
	List<Object> list3=new ArrayList<Object>();
	List<Object> list4=new ArrayList<Object>();
	List<Object> list5=new ArrayList<Object>();
	List<Object> list6=new ArrayList<Object>();
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
	public List<Object> getList3() {
		return list3;
	}
	public void setList3(List<Object> list3) {
		this.list3 = list3;
	}
	public List<Object> getList4() {
		return list4;
	}
	public void setList4(List<Object> list4) {
		this.list4 = list4;
	}
	public List<Object> getList5() {
		return list5;
	}
	public void setList5(List<Object> list5) {
		this.list5 = list5;
	}
	public List<Object> getList6() {
		return list6;
	}
	public void setList6(List<Object> list6) {
		this.list6 = list6;
	}
	public lzw(List<Object> list1, List<Object> list2, List<Object> list3, List<Object> list4, List<Object> list5,
			List<Object> list6) {
		super();
		this.list1 = list1;
		this.list2 = list2;
		this.list3 = list3;
		this.list4 = list4;
		this.list5 = list5;
		this.list6 = list6;
	}
	public lzw() {
		// TODO Auto-generated constructor stub
	}
	
}
