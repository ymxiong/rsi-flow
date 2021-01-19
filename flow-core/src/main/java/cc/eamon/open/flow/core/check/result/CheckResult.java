package cc.eamon.open.flow.core.check.result;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Lucas(jvjv)
 * Email: lucas@eamon.cc
 * Date: 2021/1/10
 * Detail: 这个类封装检查结果
 */
public class CheckResult {
    //错误列表
    private List<CheckException> exceptionList;

    //警告列表
    private List<CheckError> errorList;

    //检查结果
    private boolean isPass=false;


    public CheckResult() {
        this.exceptionList = new LinkedList<>();
        this.errorList = new LinkedList<>();
        this.isPass = true;
    }

    public void addException(CheckException checkException){
        this.exceptionList.add(checkException);
    }


    public void addError(CheckError checkError){
        this.errorList.add(checkError);

    }


  
}
