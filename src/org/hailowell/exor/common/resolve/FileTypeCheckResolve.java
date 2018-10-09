package org.hailowell.exor.common.resolve;

import org.hailowell.exor.util.check.FileTypeCheck;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by hailowell on 2018/9/28.
 */
public class FileTypeCheckResolve {
    private static FileTypeCheck fileTypeChecker = new FileTypeCheck();
    private FileTypeCheckResolve() {
        getInstance();
    }
    private FileTypeCheckResolve(int i) {
    }
    private static class LazyHolder {
        static final FileTypeCheckResolve INSTANCE = new FileTypeCheckResolve(1);
    }
    public static FileTypeCheckResolve getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void setFileTypeChecker(FileTypeCheck fileTypeChecker) {
        this.fileTypeChecker = fileTypeChecker;
    }

    public static boolean checkType(String fileName, String type) {
        return fileTypeChecker.checkType(fileName, type);
    }
    public static boolean checkType(File file, String type) {
        return fileTypeChecker.checkType(file, type);
    }

    public static String getFileHeader(String filePath) {
        return fileTypeChecker.getFileHeader(filePath);
    }
    public static String getFileHeader(File file) {
        return fileTypeChecker.getFileHeader(file);
    }
    public static String getFileHeader(FileInputStream fis) {
        return fileTypeChecker.getFileHeader(fis);
    }
    public static String getFileHeader(InputStream is) {
        return fileTypeChecker.getFileHeader(is);
    }
}
