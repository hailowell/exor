package org.hailowell.exor.common.resolve;

import org.apache.poi.ss.usermodel.Cell;
import org.hailowell.exor.dao.bean.rule.CellValueMode;
import org.hailowell.exor.dao.bean.title.ExcelReadTitle;
import org.hailowell.exor.util.office.ExcelValueReader;

/**
 * Created by hailowell on 2018/9/28.
 */
public class ExcelValueReaderResolve {
    private static ExcelValueReader excelValueReader = new ExcelValueReader();
    private ExcelValueReaderResolve() {
        getInstance();
    }
    private ExcelValueReaderResolve(int i) {
    }
    private static class LazyHolder {
        static final ExcelValueReaderResolve INSTANCE = new ExcelValueReaderResolve(1);
    }
    public static ExcelValueReaderResolve getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static void setExcelValueReader(ExcelValueReader excelValueReader) {
        ExcelValueReaderResolve.excelValueReader = excelValueReader;
    }

    public static String readCellValue(Cell cell) {
        return excelValueReader.readCellValue(cell);
    }

    public static String readCellValue(Cell cell, ExcelReadTitle title) {
        return excelValueReader.readCellValue(cell, title);
    }

    public static String readCellValue(Cell cell, CellValueMode mode) {
        return excelValueReader.readCellValue(cell, mode);
    }
}
