package cc.eamon.open.flow.core.error;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/16 14:33
 */
public class PackageNotFoundException extends FlowException {

    private static final long serialVersionUID = 7506050882204776407L;

    public PackageNotFoundException() {
    }

    public PackageNotFoundException(String message) {
        super(message);
    }

    public PackageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PackageNotFoundException(Throwable cause) {
        super(cause);
    }

    public PackageNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
