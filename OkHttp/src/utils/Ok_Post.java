package utils;

import java.io.File;
import java.io.IOException;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class Ok_Post {
	//post方式提交字符串	
	// 此种方式可以提交json数据，将postBody更改为json格式的字符串就可以
	// post提交方式构建编码格式
	private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType
			.parse("text/x-markdown; charset=utf-8");
	// 获得okHttpclient实例
	private final OkHttpClient client = new OkHttpClient();
	// 构建需要提交的数据
	String postBody = "" + "Releases\n" + "--------\n" + "\n"
			+ " * _1.0_ May 6, 2013\n" + " * _1.1_ June 15, 2013\n"
			+ " * _1.2_ August 11, 2013\n";

	public void execute() throws Exception {
		// 请求体、post方式需要一个RequestBody实例，分别由编码格式和提交的数据构成
		RequestBody body = RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody);
		// 与get方式类似，不过需要post（body）
		Request request = new Request.Builder()
				.url("https://api.github.com/markdown/raw").post(body).build();
		// 同步运行模式、也可以更改为异步运行模式
		Response response = client.newCall(request).execute();
		System.out.println("kk");
		if (response.isSuccessful()) {
			System.out.println(response.body().string());
			System.out.println("tt");
		}
	}
	
	//提交表单
	public String exe(String url) throws Exception {
		RequestBody formBody = new FormEncodingBuilder()
				.add("platform", "android")
				.add("name", "bug")
				.add("subject", "XXXXXXXXXXXXXXX").build();
		Request request = new Request.Builder().url(url).post(formBody).build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful()) {
			return response.body().string();
		} else {
			throw new IOException("Unexpected code " + response);
		}
	}
	//提交文件
	public String postFile(String url) throws Exception{
		 File file = new File("README.md");
		
		 Request request = new Request.Builder()
		 		.url(url)
		 		.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
		 		.build();
		 Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				return response.body().string();
			} else {
				throw new IOException("Unexpected code " + response);
			}

		
	}
	
	//
}


