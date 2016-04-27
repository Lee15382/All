package com.example.okhttp;

import utils.Ok_Get;
import utils.Ok_Post;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				Ok_Get get = new Ok_Get("http://publicobject.com/helloworld.txt");
				Ok_Post post = new Ok_Post();
				try {
					post.exe("http://publicobject.com/helloworld.txt");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("ss");
				}
//				try {
//					get.enqueue();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				
//				}
			}
		}).start();
    }
}
