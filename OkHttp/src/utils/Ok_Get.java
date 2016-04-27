package utils;

import java.io.IOException;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class Ok_Get {

	private String address ;
	private final OkHttpClient client = new OkHttpClient();

	public Ok_Get(String address) {
		this.address = address;
//		System.out.println(address);
	}
	//ͬ����ʽ
	public void execute() throws Exception {
		//����Request����
		Request request = new Request.Builder().url(address).build();
		//����call���󣬻�ȡ�����ǵ�okhttpclient�����request����
		Call call = client.newCall(request);
		//ʵ����call�����ִ��execute�������÷����᷵��һ��response,���׳��쳣
		Response response = call.execute();
		//isSucessful�������Ƿ�ִ�гɹ�
		if (response.isSuccessful()) {
			System.out.println(response.code());
			System.out.println(response.body().string());
		}
	}
	
	//�첽��ʽ
	public void enqueue(){
		Request request = new Request.Builder().url(address).build();
		client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Response response) throws IOException {
				// TODO Auto-generated method stub
				if(response.isSuccessful()){
					System.out.println(response.code());
                    System.out.println(response.body().string());
				}
			}
			@Override
			public void onFailure(Request arg0, IOException arg1) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}