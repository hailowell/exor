package org.hailowell.exor;

import org.hailowell.exor.common.office.ExcelReader;
import org.hailowell.exor.dao.bean.rule.CellValueMode;
import org.hailowell.exor.dao.bean.rule.ExcelReadRule;
import org.hailowell.exor.util.msg.LocaleMsg;

/**
 * Created by hailowell on 2018/9/26.
 */
public class T1 {
    public static void main(String[] args) {
        ExcelReadRule rule = new ExcelReadRule();
        rule.setSheetName("Sheet1");
        rule.setTitleRowIndex(1);
        rule.addTitle("序号", CellValueMode.INT);
        rule.addTitle("Value");
        ExcelReader excelReader = new ExcelReader();
        LocaleMsg msg = excelReader.read("D:\\F\\123.xlsx", rule);
        System.out.println();
    }

}
