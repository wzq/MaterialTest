package com.github.wzq.utils;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by wzq on 15/4/14.
 */
public class JsonUtil {

	private static final String TAG = "Json parse";

    public static JSONObject getObj(String str) throws Exception {
        JSONObject obj = null;
        if (!TextUtils.isEmpty(str)) {
            obj = new JSONObject(str);
        }
        return obj;
    }

	@Deprecated
	public static EasyMap getEasyMap(String str) throws Exception{
		EasyMap map = null;
		if(!TextUtils.isEmpty(str)){
			JSONObject obj = getObj(str);
			map = new EasyMap();
			Iterator<String> it = obj.keys();
			while (it.hasNext()) {
				String key = it.next();
				Object value = obj.get(key);
				if(value instanceof JSONObject){
					map.put(key, getEasyMap(value.toString()));
				}else if (value instanceof JSONArray) {
					List<EasyMap> list = new ArrayList<>();
					JSONArray v = (JSONArray) value;
					for (int i = 0; i < v.length(); i++) {
						list.add(getEasyMap(v.getString(i)));
					}
					map.put(key, list);
				}else{
					map.put(key, value);
				}
			}
		}
		return map;
	}

    public static List<EasyMap> json2list(String json){
		List<EasyMap> list = null;
		try {
			if (!TextUtils.isEmpty(json)) {
				list = new ArrayList<>();
				JSONArray v = new JSONArray(json);
				for (int i = 0; i < v.length(); i++) {
					list.add(json2map(v.getString(i)));

				}
			}
		}catch (Exception e){
			Log.e(TAG, e.getMessage());
		}
        return list;
    }

    public static EasyMap json2map(String json) {
        EasyMap map = null;
		try {
			if (!TextUtils.isEmpty(json)) {
				JSONObject obj = new JSONObject(json);
				map = new EasyMap();
				Iterator<String> it = obj.keys();
				while (it.hasNext()) {
					String key = it.next();
					Object value = obj.get(key);
					map.put(key, value);
				}
			}
		}catch (Exception e){
			Log.e(TAG, e.getMessage());
		}
        return map;
    }

    public static String map2json(Map map) {
        JSONObject obj = new JSONObject(map);
        return obj.toString().trim();
    }

//    public static <T> T json2obj(String json, Class<T> tClass) {
//        Gson gson = new Gson();
//        return gson.fromJson(json, tClass);
//    }
//
//	public static String obj2json(Object obj){
//		Gson gson = new Gson();
//		return gson.toJson(obj);
//	}
}
