package com.example.basestation;

public class Info {
	private String Code;
	private String Latitude;
	private String Lontitude;
	
	public Info(String Code,String Latitude,String Lontitude){
		this.Code = Code;
		this.Latitude = Latitude;
		this.Lontitude = Lontitude;
	}
	
	public String getCode(){
		return Code;
	}
	
	public String getLatitude(){
		return Latitude;
	}
	
	public String getLontitude(){
		return Lontitude;
	}
}
