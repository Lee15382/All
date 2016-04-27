package com.example.tools;

import java.util.ArrayList;
import java.util.List;

import com.example.basestation.Info;

public class Resolve {
	
	private Info in;
	private String data;
	private List<Info> list = new ArrayList<Info>();
	
	public Resolve(String data){
		this.data = data;
	}
	
	public  List<Info> data(){
	String[] a= data.split(";");//使用分号将获得的字符串分隔装入数组中
	for(int i=1;i<=(a.length-17);i+=18){ //该位置注意数组越界问题
		in = new Info(a[i], a[i+8],a[i+9]); //取每行各项
		list.add(in);
		}
	
	return list;
	}	

}
