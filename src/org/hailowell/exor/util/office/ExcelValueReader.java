package org.hailowell.exor.util.office;

import org.hailowell.exor.dao.bean.rule.CellValueMode;
import org.hailowell.exor.dao.bean.title.ExcelReadTitle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hailowell on 2018/9/26.
 */
public class ExcelValueReader {

    public String readCellValue(Cell cell) {
        return readCellValue(cell, CellValueMode.DEFAULT);
    }

    public String readCellValue(Cell cell, ExcelReadTitle title) {
        return readCellValue(cell, title.getMode());
    }

    public String readCellValue(Cell cell, CellValueMode mode) {
        String cellValue = "";
        if (null == cell) {
            return cellValue;
        }
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = null;
                    int dataFormat = cell.getCellStyle().getDataFormat();
                    if (dataFormat == 14) {
                        sdf = new SimpleDateFormat("yyyy/MM/dd");
                    }else if (dataFormat == 21) {
                        sdf = new SimpleDateFormat("HH:mm:ss");
                    }else if (dataFormat == 22) {
                        sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    }
                    Date date = cell.getDateCellValue();
                    if (null != sdf) {
                        cellValue = sdf.format(date);
                    }else {
                        cellValue = String.valueOf(date);
                    }
                }else if (cell.getCellStyle().getDataFormat() == 0) {
                    double numericCellValue = cell.getNumericCellValue();
                    switch (mode) {
                        case DEFAULT:
                            cellValue = String.valueOf(numericCellValue);
                            break;
                        case INT:
                            cellValue = String.valueOf((int) numericCellValue);
                            break;
                        case FLOAT:
                            cellValue = String.valueOf((float) numericCellValue);
                            break;
                        case DOUBLE:
                            cellValue = String.valueOf(numericCellValue);
                            break;
                        default:
                            cellValue = String.valueOf(numericCellValue);
                            break;
                    }
                }
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            case BLANK:
                cellValue = null;
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}
