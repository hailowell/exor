package org.hailowell.exor.dao.bean.tl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存储数据结构
 * 特点：字段可以不存在，占用空间大，不规则的二维数据结构
 * 优点：添加、根据索引和字段查询方便
 * 缺点：判断(contains)、定位索引麻烦，间接导致查询困难
 * Created by hailowell on 2018/9/4.
 */
public class TransverseExcelReadTl extends ExcelReadTl {
    private List<Map<String, String>> data = new ArrayList<>();

    protected TransverseExcelReadTl() {
    }

    @Override
    public boolean containsKey(String key) {
        if (null != data && !data.isEmpty()) {
            return data.get(0).containsKey(key);
        }
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        if (null != data && !data.isEmpty()) {
            for (int i = 0, len = data.size(); i < len; i++) {
                if (data.get(i).containsValue(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(String key, String value) {
        if (null != data && !data.isEmpty()) {
            for (int i = 0, len = data.size(); i < len; i++) {
                if (data.get(i).get(key).equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int indexOf(String key, String value) {
        if (null != data && !data.isEmpty()) {
            for (int i = 0, len = data.size(); i < len; i++) {
                if (data.get(i).get(key).equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 根据字段名和索引查询值
     * @param key       要查询的字段名
     * @param index     要查询的索引
     * @return  String  要查询的值
     */
    @Override
    public String get(String key, int index) {
        if (index >= length()) {
            return null;
        }
        return data.get(index).get(key);
    }

    /**
     * 如果索引有效，根据索引获取单元，添加或更新单元的值
     * 如果索引无效，新建并更新单元的值，将单元添加到数据中
     * @param index     要添加的索引
     * @param key       要添加的字段名称
     * @param value     要添加的值
     */
    @Override
    public void put(int index, String key, String value) {
        Map map;
        if (null != data && !data.isEmpty() && index < length()) {
            map = data.get(index);
            map.put(key, value);
        }else {
            map = new HashMap();
            map.put(key, value);
            data.add(map);
        }
    }

    @Override
    public int length() {
        if (null != data) {
            return data.size();
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        if (null != data) {
            return (data.isEmpty() && data.get(0).isEmpty());
        }
        return false;
    }

    @Override
    public void remove(int index) {
        data.remove(index);
    }

    @Override
    public void clear() {
        data.clear();
    }
}
