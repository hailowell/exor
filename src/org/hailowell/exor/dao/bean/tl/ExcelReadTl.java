package org.hailowell.exor.dao.bean.tl;

import java.io.Serializable;

/**
 * Created by hailowell on 2018/9/4.
 */
public abstract class ExcelReadTl implements Serializable {

    private static final long serialVersionUID = 65201821160409L;

    public static class TlMode {
        public static final TlMode vertical = new TlMode();
        public static final TlMode transverse = new TlMode();
    }
    public static ExcelReadTl getInstance(TlMode mode) {
        if (mode == TlMode.transverse) {
            return new TransverseExcelReadTl();
        }else {
            return new VerticalExcelReadTl();
        }
    }
    public abstract boolean containsKey(String key);
    public abstract boolean containsValue(String value);
    public abstract boolean containsValue(String key, String value);
    public abstract int indexOf(String key, String value);
    public abstract String get(String key, int index);
    public abstract void put(int index, String key, String value);
    public abstract int length();
    public abstract boolean isEmpty();
    public abstract void remove(int index);
    public abstract void clear();
}
