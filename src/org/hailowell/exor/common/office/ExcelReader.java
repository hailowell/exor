package org.hailowell.exor.common.office;

import org.hailowell.exor.common.resolve.ExcelValueReaderResolve;
import org.hailowell.exor.common.resolve.FileTypeCheckResolve;
import org.hailowell.exor.dao.bean.rule.ExcelReadRule;
import org.hailowell.exor.dao.bean.title.ExcelReadTitle;
import org.hailowell.exor.dao.bean.tl.ExcelReadTl;
import org.hailowell.exor.dao.exception.FileTypeCheatException;
import org.hailowell.exor.util.msg.LocaleMsg;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by hailowell on 2018/9/26.
 */
public class ExcelReader {
    public LocaleMsg read(String filePath, ExcelReadRule rule) {
        LocaleMsg msg = new LocaleMsg();
        try {
            Workbook workbook = getWorkBook(filePath);
            Sheet sheet = workbook.getSheet(rule.getSheetName());
            readTitle(sheet, rule);
            ExcelReadTl readTl = ExcelReadTl.getInstance(rule.getMode());
            readContent(sheet, rule, readTl);
            msg.successMsg(readTl);
        } catch (IOException e) {
            msg.errMsg(e.getMessage());
        } catch (FileTypeCheatException e) {
            msg.errMsg(e.getMessage());
        }
        return msg;
    }

    private Workbook getWorkBook(String filePath) throws IOException, FileTypeCheatException {
        FileInputStream fis = new FileInputStream(filePath);
        return getWorkBook(filePath, fis);
    }

    private Workbook getWorkBook(File file) throws IOException, FileTypeCheatException {
        FileInputStream fis = new FileInputStream(file);
        return getWorkBook(file, fis);
    }

    private Workbook getWorkBook(String filePath, FileInputStream fis) throws IOException,
            FileTypeCheatException {
        Workbook workbook;
        InputStream is = fis;
        if (FileTypeCheckResolve.checkType(filePath, "xls")) {
            workbook = new HSSFWorkbook(is);
            return workbook;
        }
        if (FileTypeCheckResolve.checkType(filePath, "xlsx")) {
            workbook = new XSSFWorkbook(is);
            return workbook;
        }
        FileTypeCheatException ex =  new FileTypeCheatException("错误的文件类型");
        throw ex;
    }

    private Workbook getWorkBook(File file, FileInputStream fis) throws IOException,
            FileTypeCheatException {
        Workbook workbook;
        InputStream is = fis;
        if (FileTypeCheckResolve.checkType(file, "xls")) {
            workbook = new HSSFWorkbook(is);
            return workbook;
        }
        if (FileTypeCheckResolve.checkType(file, "xlsx")) {
            workbook = new XSSFWorkbook(is);
            return workbook;
        }
        FileTypeCheatException ex =  new FileTypeCheatException("错误的文件类型");
        throw ex;
    }

    private void readTitle(Sheet sheet, ExcelReadRule rule) {
        int titleIndex = rule.getTitleRowIndex();
        Row row = sheet.getRow(titleIndex);
        int firstIndex = row.getFirstCellNum();
        int lastIndex = row.getLastCellNum();
        List<String> titleNames = rule.getTitleNames();
        for (int i = firstIndex; i < lastIndex; i++) {
            Cell var1 = row.getCell(i);
            String val = ExcelValueReaderResolve.readCellValue(var1);
            if (titleNames.contains(val)) {
                rule.addColTitles(val, i);
            }
        }
    }

    private void readContent(Sheet sheet, ExcelReadRule rule, ExcelReadTl readTl) {
        int lastRowNum = sheet.getLastRowNum();
        int firstRowIndex = rule.getTitleRowIndex() + 1;
        if (lastRowNum < firstRowIndex) {
            return;
        }
        int readLength = rule.getReadLength();
        int realReadLength = lastRowNum - rule.getTitleRowIndex();
        if (readLength == -1 || realReadLength < readLength) {
            readLength = realReadLength;
        }
        List<ExcelReadTitle> colTitles = rule.getColTitles();
        for (int i = firstRowIndex, len = firstRowIndex + readLength; i < len; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0, colSize = colTitles.size(); j < colSize; j++) {
                ExcelReadTitle var1 = colTitles.get(j);
                Cell var2 = row.getCell(var1.getColIndex());
                String val = ExcelValueReaderResolve.readCellValue(var2, var1);
                readTl.put(i, var1.getColName(), val);
            }
        }
    }
}
