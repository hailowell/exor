package org.hailowell.exor.dao.bean.rule;

import org.hailowell.exor.dao.bean.title.ExcelReadTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hailowell on 2018/9/26.
 */
public class ExcelReadRule {
    private String sheetName = "sheet1";
    private int titleRowIndex = -1;
    private int readLength = -1;
    private int endRowIndex = -1;
    private List<String> titleNames = new ArrayList<String>();
    private List<CellValueMode> titleMode = new ArrayList<CellValueMode>();
    private List<ExcelReadTitle> colTitles = new ArrayList<ExcelReadTitle>();

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getTitleRowIndex() {
        return titleRowIndex;
    }

    public void setTitleRowIndex(int titleRowIndex) {
        this.titleRowIndex = titleRowIndex;
        if (endRowIndex != -1) {
            readLength = endRowIndex - this.titleRowIndex;
        }
    }

    public int getReadLength() {

        return readLength;
    }

    public void setReadLength(int readLength) {
        this.readLength = readLength;
    }

    public int getEndRowIndex() {
        return endRowIndex;
    }

    public void setEndRowIndex(int endRowIndex) {
        this.endRowIndex = endRowIndex;
        if (titleRowIndex != -1) {
            readLength = this.endRowIndex - titleRowIndex;
        }
    }

    public List<String> getTitleNames() {
        return titleNames;
    }

    public void addTitle(String titleName) {
        this.titleNames.add(titleName);
        this.titleMode.add(CellValueMode.DEFAULT);
    }

    public void addTitle(String titleName, CellValueMode mode) {
        this.titleNames.add(titleName);
        if (null == mode) {
            mode = CellValueMode.DEFAULT;
        }
        this.titleMode.add(mode);
    }

    public List<ExcelReadTitle> getColTitles() {
        return colTitles;
    }

    public List<CellValueMode> getTitleMode() {
        return titleMode;
    }

    public void addColTitles(String colName, int index) {
        ExcelReadTitle title = new ExcelReadTitle();
        title.setColName(colName);
        title.setColIndex(index);
        int var1 = titleNames.indexOf(colName);
        title.setMode(titleMode.get(var1));
        colTitles.add(title);
    }
}
