package cc.eamon.open.flow.core.context;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import java.util.*;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2020-06-26 00:48:38
 */
public class FlowContext {

    // record mapper info
    private Set<String> mappers = new LinkedHashSet<>();

    // record mapper types
    private Map<String, Element> mapperElements = new HashMap<>();

    // record mapper-method relationship
    private Map<String, ExecutableElement> methodMap = new LinkedHashMap<>();

    // record mapper-field relationship
    private Map<String, VariableElement> fieldMap = new LinkedHashMap<>();

    public Set<String> getMappers() {
        return mappers;
    }

    public Map<String, Element> getMapperElements() {
        return mapperElements;
    }

    public Map<String, ExecutableElement> getMethodMap() {
        return methodMap;
    }

    public Map<String, VariableElement> getFieldMap() {
        return fieldMap;
    }


}
