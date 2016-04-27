package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Util {

	public List<Map<String, Object>> getInformation(String jonString)
			throws Exception {

		JSONObject jsonObject = new JSONObject(jonString);
		JSONArray result = new JSONArray(jsonObject.getString("result"));
		
		JSONObject result1 = result.getJSONObject(1);
		List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(map.toString());
		map.put("cityName", result1.optString("citynm"));
		map.put("weather", result1.optString("weather"));
		all.add(map);
//		System.out.println(all.toString());
//		Log.d("map",map.toString() );
		return all;

	}

}
