package org.hailowell.exor.dao.bean.title;

import org.hailowell.exor.dao.bean.rule.CellValueMode;

/**
 * Created by hailowell on 2018/9/27.
 */
public class ExcelReadTitle {
    private String colName;
    private int colIndex;
    private CellValueMode mode;

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public CellValueMode getMode() {
        return mode;
    }

    public void setMode(CellValueMode mode) {
        this.mode = mode;
    }
}
