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
	//post��ʽ�ύ�ַ���	
	// ���ַ�ʽ�����ύjson���ݣ���postBody����Ϊjson��ʽ���ַ����Ϳ���
	// post�ύ��ʽ���������ʽ
	private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType
			.parse("text/x-markdown; charset=utf-8");
	// ���okHttpclientʵ��
	private final OkHttpClient client = new OkHttpClient();
	// ������Ҫ�ύ������
	String postBody = "" + "Releases\n" + "--------\n" + "\n"
			+ " * _1.0_ May 6, 2013\n" + " * _1.1_ June 15, 2013\n"
			+ " * _1.2_ August 11, 2013\n";

	public void execute() throws Exception {
		// �����塢post��ʽ��Ҫһ��RequestBodyʵ�����ֱ��ɱ����ʽ���ύ�����ݹ���
		RequestBody body = RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody);
		// ��get��ʽ���ƣ�������Ҫpost��body��
		Request request = new Request.Builder()
				.url("https://api.github.com/markdown/raw").post(body).build();
		// ͬ������ģʽ��Ҳ���Ը���Ϊ�첽����ģʽ
		Response response = client.newCall(request).execute();
		System.out.println("kk");
		if (response.isSuccessful()) {
			System.out.println(response.body().string());
			System.out.println("tt");
		}
	}
	
	//�ύ��
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
	//�ύ�ļ�
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


