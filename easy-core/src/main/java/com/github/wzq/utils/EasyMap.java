package com.github.wzq.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wzq on 15/4/14.
 */
public class EasyMap extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    public EasyMap() {
        super();
    }

    public EasyMap(Map<String, Object> map) {
        super(map);
    }

    //-- new support --

    public static EasyMap call(){
        return new EasyMap();
    }

    public EasyMap chainPut(String key, Object value){
        this.put(key, value);
        return this;
    }

    //-----

    public int getInt(String key, int def) {
        Object temp = this.get(key);
        return check(temp) ? Integer.valueOf(temp.toString()) : def;
    }

    public String getString(String key) {
        Object temp = this.get(key);
        return check(temp) ? temp.toString() : "";
    }

    public EasyMap getMap(String key) {
        Object temp = this.get(key);
        return check(temp) ? JsonUtil.json2map(temp.toString()) : null;
    }

    @SuppressWarnings("unchecked")
    public List<EasyMap> getList(String key) {
        Object temp = this.get(key);
        return check(temp) ? JsonUtil.json2list(temp.toString()) : null;
    }

    public List<EasyMap> getList(String key, int from, int to) {
        List<EasyMap> temp = getList(key);
        if (check(temp) && temp.size() >= to)
            return temp.subList(from, to);
        else
            return temp;
    }

    public boolean getBoolean(String key) {
        Object temp = this.get(key);
        return check(temp) ? Boolean.parseBoolean(temp.toString()) : null;
    }

    public String getString(String key, int len) {
        String temp = this.getString(key);
        return temp.length() > len ? temp.substring(0, len - 1) + "..." : temp;
    }


    private boolean check(Object temp) {
        return temp != null && !temp.toString().equals("null");
    }


}
