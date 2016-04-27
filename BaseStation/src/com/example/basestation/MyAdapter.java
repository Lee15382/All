package com.example.basestation;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<Info> {

	private int resourcedId;

	public MyAdapter(Context context, int textViewResourceId,
			List<Info> objects) {
		super(context, textViewResourceId, objects);
		this.resourcedId = textViewResourceId;

	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Info in = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourcedId, null);
		TextView codeText = (TextView) view.findViewById(R.id.code);
		TextView latitudeText = (TextView) view.findViewById(R.id.latitude);
		TextView lontitudeText = (TextView) view.findViewById(R.id.longtitude);
		
		codeText.setText(in.getCode());
		latitudeText.setText(in.getLatitude());
		lontitudeText.setText(in.getLontitude());
	
		return view;

	}

}
