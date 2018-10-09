package org.hailowell.exor.dao.bean.tl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存储数据结构
 * 特点：几乎每个字段每个索引都有值(最新的索引例外)，占用空间小，比较规则的二维数据结构
 * 优点：判断(contains)、定位索引、根据索引和字段查询简单
 * 缺点：添加逻辑麻烦、添加时间复杂度高
 * Created by hailowell on 2018/9/4.
 */
public class VerticalExcelReadTl extends ExcelReadTl {
    private Map<String, List<String>> data = new HashMap<>();

    protected VerticalExcelReadTl() {
    }

    @Override
    public boolean containsKey(String key) {
        if (null != data && !data.isEmpty()) {
            return data.containsKey(key);
        }
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        if (null != data && !data.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : data.entrySet()) {
                if (entry.getValue().contains(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(String key, String value) {
        if (null != data && !data.isEmpty()) {
            List<String> val = data.get(key);
            return val.contains(value);
        }
        return false;
    }

    @Override
    public int indexOf(String key, String value) {
        if (null != data && !data.isEmpty()) {
            List<String> val = data.get(key);
            return val.indexOf(value);
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
        if (!containsKey(key)) {
            return null;
        }
        if (index >= length()) {
            return null;
        }
        List<String> vList = data.get(key);
        if (index >= vList.size()) {
            return null;
        }
        return vList.get(index);
    }

    /**
     * 如果是新加字段，需要根据其他字段的length补null，使各字段length平均，最多不超过1
     * 如果不是新加字段，需要和其他字段length比较，如果本字段length比最小length大于等于1，其他字段需要补null，保持
     * 最大length和最小length的差距保持在1以内
     * @param index     要添加的索引
     * @param key       要添加的字段名称
     * @param value     要添加的值
     */
    @Override
    public void put(int index, String key, String value) {
        if (!data.containsKey(key)) {
            List<String> var1 = new ArrayList<String>();
            for (int i = 0, len = minLength() - 1; i < len; i++) {
                var1.add(null);
            }
            var1.add(value);
            data.put(key, var1);
        }else {
            if (index >= minLength()) {
                index = minLength();
                for (Map.Entry<String, List<String>> entry : data.entrySet()) {
                    if (!entry.getKey().equals(key)) {
                        for (int i = entry.getValue().size(); i < index; i++) {
                            entry.getValue().add(i, null);
                        }
                    }
                }
                data.get(key).add(index, value);
            }
        }
    }

    @Override
    public int length() {
        int len = 0;
        if (null != data && !data.isEmpty()) {
            for (String key : data.keySet()) {
                List<String> var1 = data.get(key);
                int size = var1.size();
                if (len < size) {
                    len = size;
                }
            }
        }
        return len;
    }

    public int minLength() {
        int len = 0;
        int index = 0;
        if (null != data && !data.isEmpty()) {
            for (String key : data.keySet()) {
                List<String> var1 = data.get(key);
                int size = var1.size();
                if (index == 0) {
                    len = size;
                    index ++;
                }
                if (len > size) {
                    len = size;
                }
            }
        }
        return len;
    }

    @Override
    public boolean isEmpty() {
        if (null != data) {
            return data.isEmpty();
        }
        return false;
    }
}
