package cc.eamon.open.flow.core.stereotype.execute.support;

import cc.eamon.open.flow.core.error.FlowException;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 16:23:08
 */
public enum ExecutableEnums {

    /**
     * 流程执行项
     */
    FLOW("flow", FlowExecutable.class),

    /**
     * 事件执行项
     */
    EVENT("event", EventExecutable.class),

    ;

    private String keyword;

    private Class<? extends Executable> executable;

    ExecutableEnums(String keyword, Class<? extends Executable> executable) {
        this.keyword = keyword;
        this.executable = executable;
    }

    public String getKeyword() {
        return keyword;
    }

    public Class<? extends Executable> getExecutable() {
        return executable;
    }

    public static ExecutableEnums getType(String keyword) {
        for (ExecutableEnums value : ExecutableEnums.values()) {
            if (value.getKeyword().equals(keyword)) return value;
        }
        throw new FlowException("流程类型不支持：" + keyword);
    }

}
