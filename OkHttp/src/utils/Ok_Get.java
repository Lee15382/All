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
	//同步方式
	public void execute() throws Exception {
		//创建Request对象
		Request request = new Request.Builder().url(address).build();
		//创建call对象，会取走我们的okhttpclient对象和request对象
		Call call = client.newCall(request);
		//实例化call对象后，执行execute方法，该方法会返回一个response,并抛出异常
		Response response = call.execute();
		//isSucessful检查对象是否执行成功
		if (response.isSuccessful()) {
			System.out.println(response.code());
			System.out.println(response.body().string());
		}
	}
	
	//异步方式
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
