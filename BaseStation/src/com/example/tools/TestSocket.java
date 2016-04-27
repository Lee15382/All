package com.example.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.example.basestation.Info;

public class TestSocket {

	Socket socket;
	String data;
	StringBuffer s = new StringBuffer();

	public void createSocket() throws Exception {
		socket = new Socket("61.187.123.99", 6060); //地址不用加http，直接写绝对位置即可
	}
	
	public void communicate() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("GET / HTTP/1.1\r\n");   //设置请求方式为GET，并以HTTP、1.1协议，其他头部字段不用添加，因为不是POST方式
		sb.append("\r\n");				   //这一行的作用是代表发送结束，不写的话服务器不会返回

		// 发出HTTP请求
		OutputStream socketOut = socket.getOutputStream();
		socketOut.write(sb.toString().getBytes());
		socket.shutdownOutput(); // 关闭输出流
		// 接收响应结果
		System.out.println(socket);
		InputStream socketIn = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(socketIn));
		while ((data = br.readLine()) != null) {
			s.append(data);
		}
		socket.close();
	}
	
	//方法将接收到的数据发送出去
	public String getInformation() {
		return s.toString();
	}
}