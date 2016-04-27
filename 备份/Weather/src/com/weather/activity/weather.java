package com.weather.activity;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Util.HttpDownloader;
import Util.Util;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weather.R;

public class weather extends Activity {
	private EditText citynameEditText;
	private Button searchWeatherButton;
	private TextView citynametTextView;
	private TextView weahterTextView;
	String jonString;
	ProgressDialog progressDialog;
	private static final int SET = 1;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			System.out.println(msg.obj.toString());
			switch (msg.what

			) {
			case SET:

				Util util = new Util();

				try {
					List<Map<String, Object>> all = util.getInformation(msg.obj
							.toString());
					System.out.println(all.toString());

					Iterator<Map<String, Object>> iterator = all.iterator();

					while (iterator.hasNext()) {

						Map<String, Object> map = iterator.next();
						Log.d("ÌìÆø", map.get("weather").toString());
						citynametTextView.setText(map.get("cityName")
								.toString());
						weahterTextView.setText(map.get("weather").toString());

					}

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("dd");
				}

			}

		}

	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		citynameEditText = (EditText) findViewById(R.id.myedit);
		searchWeatherButton = (Button) findViewById(R.id.searchweather);
		citynametTextView = (TextView) findViewById(R.id.city);
		weahterTextView = (TextView) findViewById(R.id.weather);

		searchWeatherButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// new Thread(new NewThread()).start();
				// Log.d("°´¼ü", "Success");
				System.out.println(citynameEditText.getText().toString());
				new Thread() {
					public void run() {
						String address = "http://api.k780.com:88/?app=weather.future&weaid="
								+ citynameEditText.getText().toString()
								+ "&&appkey=18548&sign=b629c3083e2ac2dcfb6833052717cda5&format=json";

						HttpDownloader httpDownloader = new HttpDownloader();
						String jonString = httpDownloader.download(address);
						Message msg = new Message();
						msg.obj = jonString;
						msg.what = weather.SET;
						handler.sendMessage(msg);
						// System.out.println(msg.obj.toString());
					};
				}.start();

			}
		});
	}

	// private class NewThread implements Runnable {
	//
	// public void run() {
	//
	// String address = "http://api.k780.com:88/?app=weather.future&weaid="
	// + citynameEditText.getText().toString()
	// + "&&appkey=18548&sign=b629c3083e2ac2dcfb6833052717cda5&format=json";
	//
	// HttpDownloader httpDownloader = new HttpDownloader();
	// String jonString = httpDownloader.download(address);
	// Message msg = new Message();
	// msg.obj = jonString;
	// msg.what = Weather.SET;
	// handler.sendMessage(msg);
	//
	// }
	// }

}
