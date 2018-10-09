package org.hailowell.exor.util.msg;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hailowell on 2018/9/27.
 */
public class LocaleMsg implements Serializable {
    private static final long serialVersionUID = 65201811290409L;

    private int state = -1;
    private Map info = new HashMap(4, 0.75f);

    public LocaleMsg() {
        init();
    }
    public void setState(int var1) {
        this.state = var1;
    }
    public int getState() {
        return state;
    }
    public void setResult(Object obj) {
        info.put("65201811290409L_LocalMsg_Result", obj);
    }
    public Object getResult() {
        return info.get("65201811290409L_LocalMsg_Result");
    }
    public void successMsg(Object var1) {
        setState(200);
        setResult(var1);
    }
    public void errMsg(Object var1) {
        setState(500);
        setResult(var1);
    }
    public void fillMsg(int code, Object var1) {
        setState(code);
        setResult(var1);
    }

    private void init() {
        info.put("65201811290409L_LocalMsg_Result", "");
    }
}
