package cc.eamon.open.flow.core.error;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-03-15 17:11:27
 */
public class RouteStrategyNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6371563182749992637L;

    public RouteStrategyNotFoundException() {
    }

    public RouteStrategyNotFoundException(String message) {
        super(message);
    }

    public RouteStrategyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RouteStrategyNotFoundException(Throwable cause) {
        super(cause);
    }

    public RouteStrategyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
