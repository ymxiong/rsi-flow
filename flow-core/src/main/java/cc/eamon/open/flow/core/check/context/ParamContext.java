package cc.eamon.open.flow.core.check.context;

import cc.eamon.open.flow.core.check.result.CheckException;
import cc.eamon.open.flow.core.check.result.CheckResult;
import cc.eamon.open.flow.core.check.structure.BasicItem;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Lucas(jvjv)
 * Email: lucas@eamon.cc
 * Date: 2021/1/10
 * Detail: 该类用于检查过程中作为参数上下文保存参数
 * 主要提供存储参数与取出参数两个操作
 */
public class ParamContext {

    //键值对
    //最外层的键值对为：key: 域名 Value:参数Map
    //内层的键值对为：key:参数名 Value:参数类型
    private Map<String, Map<String, String>> paramsMap;


    public ParamContext() {
        this.paramsMap = new HashMap<>();
    }

    /**
     * @param scopeName   注册参数所在域名
     * @param paramName   注册参数名
     * @param paramType   注册参数类型
     * @param checkResult 检查结果
     */
    public void registerParam(BasicItem basicItem, String scopeName, String paramName, String paramType, CheckResult checkResult) {
        Map<String, String> scopeParamsMap = paramsMap.get(scopeName);
        //如果获取到的Map为空说明当前Scope尚未注册参数
        if (scopeParamsMap == null) {
            scopeParamsMap = new HashMap<>();
            paramsMap.put(scopeName, scopeParamsMap);
        }
        //检查之前是否已经在该域注册过这个参数
        String registedParamType = scopeParamsMap.get(paramName);
        if (registedParamType != null) {
            //TODO:发现重复注册参数,覆盖了东西
            //CheckError error=new CheckError();

        }
        scopeParamsMap.put(paramName, paramType);
    }


    /**
     * @param scopeName   待取参数所在域名
     * @param paramName   待取参数名
     * @param checkResult 检查结果
     * @return 若该参数在上下文存在则返回上下文中参数的类型，
     * 如果该参数在上下文不存在，则添加报错信息提示缺乏某参数
     */
    public String retrieveParam(BasicItem basicItem, String scopeName, String paramName, String paramType, CheckResult checkResult) {
        Map<String, String> scopeParamsMap = paramsMap.get(scopeName);
        if (scopeParamsMap == null) {
            //TODO:该域尚未注册异常
            CheckException scopeNotExistException = new CheckException(basicItem, "scope named " + scopeName + " have not been registered" +
                    ",you cannot retrieve param named" + paramName + " from " + scopeName);
            checkResult.addException(scopeNotExistException);
        }
        String registedParamType = scopeParamsMap.get(paramName);
        if (registedParamType != paramType) {
            if (paramType == null) {
                //TODO:发现尚未注册参数
                CheckException paramNotExistException = new CheckException(basicItem, "param named " + scopeName + " have not been registered" +
                        ",you cannot retrieve param named" + paramName + " from " + scopeName);
                checkResult.addException(paramNotExistException);
            } else {
                //TODO:发现取出取出参数与需要的参数不匹配
                CheckException paramNotMatchException = new CheckException(basicItem, "param in " + scopeName + " named " + paramName + " is " + registedParamType +
                        ", required type is " + paramType);
                checkResult.addException(paramNotMatchException);
            }
        }

        return registedParamType;
    }
}

