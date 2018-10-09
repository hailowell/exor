package org.hailowell.exor.dao.exception;

/**
 * Created by hailowell on 2018/9/26.
 */
public class FileTypeCheatException extends SecurityException {
    public FileTypeCheatException() {
    }

    public FileTypeCheatException(String s) {
        super(s);
    }

    public FileTypeCheatException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeCheatException(Throwable cause) {
        super(cause);
    }
}
