package com.example.basestation;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

import com.example.tools.Resolve;
import com.example.tools.TestSocket;

public class BaseStationActivity extends ActionBarActivity implements
		OnClickListener {

	private Button start;
	private ListView listView;
	private List<Info> infoList = new ArrayList<Info>();
	private String[] data ={"1","2","3"};

	private MyAdapter adapter;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Resolve re = new Resolve(msg.obj.toString());
				infoList = re.data();
				// 构建自定义适配器对象
				adapter = new MyAdapter(BaseStationActivity.this,
						R.layout.item, infoList);
				listView.setAdapter(adapter);
				break;

			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base_station);
		start = (Button) findViewById(R.id.Start);
		listView = (ListView) findViewById(R.id.list_view);
		start.setOnClickListener(this);
	}
	
	
	Runnable mRunnable = new Runnable() {
		
		@Override
		public void run() {
			while(true){
				TestSocket client = new TestSocket();
				try {
					Thread.sleep(5*1000);
					client.createSocket();
					System.out.println("createSoket");
					client.communicate();
					Message message = new Message();
					message.obj = client.getInformation();
					message.what = 1;
					handler.sendMessage(message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	};
	@Override
	public void onClick(View v) {
		
		new Thread(mRunnable).start();
	}

}
