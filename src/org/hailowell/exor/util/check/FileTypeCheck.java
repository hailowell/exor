package org.hailowell.exor.util.check;

import org.hailowell.exor.util.convert.ByteConvert;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by hailowell on 2018/9/26.
 */
public class FileTypeCheck {
    private static final HashMap<String, String> fileTypes = new HashMap<String, String>();
    static {
        fileTypes.put("xls", "D0CF11E0");
        fileTypes.put("xlsx", "504B0304");
    }

    public boolean checkType(String fileName, String type) {
        if (!fileTypes.containsKey(type)) {
            return false;
        }
        String fileHeader = getFileHeader(fileName);
        String typeHeader = fileTypes.get(type).toUpperCase(Locale.CHINA);
        return fileHeader.equals(typeHeader);
    }
    public boolean checkType(File file, String type) {
        if (!fileTypes.containsKey(type)) {
            return false;
        }
        String fileHeader = getFileHeader(file);
        String typeHeader = fileTypes.get(type).toUpperCase(Locale.CHINA);
        return fileHeader.equals(typeHeader);
    }

    public String getFileHeader(String filePath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            return "文件查找不到";
        }
        String value = getFileHeader(fis);
        if (null != fis) {
            try {
                fis.close();
                fis = null;
            } catch (IOException e) {
            }
        }
        return value;
    }
    public String getFileHeader(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return "文件查找不到";
        }
        String value = getFileHeader(fis);
        if (null != fis) {
            try {
                fis.close();
                fis = null;
            } catch (IOException e) {
            }
        }
        if (fileTypes.containsValue(value)) {
            return "未知文件类型";
        }
        return value;
    }
    public String getFileHeader(FileInputStream fis) {
        InputStream is = fis;
        return getFileHeader(is);
    }
    public String getFileHeader(InputStream is) {
        byte[] bits = new byte[4];
        try {
            is.read(bits, 0, bits.length);
            String value = ByteConvert.bytes2HexString(bits);
            return value;
        } catch (IOException e) {
            return "读取文件失败";
        }finally {
            if (null != is) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                }
            }
        }
    }
}
