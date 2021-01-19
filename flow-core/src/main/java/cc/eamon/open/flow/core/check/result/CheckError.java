package cc.eamon.open.flow.core.check.result;

/**
 * Author: Lucas(jvjv)
 * Email: lucas@eamon.cc
 * Date: 2021/1/10
 * Detail: 这个类用于存储错误信息，比如参数覆盖（注入参数时之前该参数在上下文中已经存在）
 *         等不一定会引起错误的Error
 */
public class CheckError {

    //错误位置
    private String exceptionItem;

    //错误原因
    private String exceptionResource;

    public CheckError(String exceptionItem, String exceptionResource) {
        this.exceptionItem = exceptionItem;
        this.exceptionResource = exceptionResource;
    }

    public String getExceptionItem() {
        return exceptionItem;
    }

    public void setExceptionItem(String exceptionItem) {
        this.exceptionItem = exceptionItem;
    }

    public String getExceptionResource() {
        return exceptionResource;
    }

    public void setExceptionResource(String exceptionResource) {
        this.exceptionResource = exceptionResource;
    }
}
