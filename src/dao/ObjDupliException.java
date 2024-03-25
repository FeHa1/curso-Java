package dao;

@SuppressWarnings("serial")
public class ObjDupliException extends Exception {
    public ObjDupliException() {
    }

    public ObjDupliException(String message) {
        super(message);
    }

    public ObjDupliException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjDupliException(Throwable cause) {
        super(cause);
    }
}
