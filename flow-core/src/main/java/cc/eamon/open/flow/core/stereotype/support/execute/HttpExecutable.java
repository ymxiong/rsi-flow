package cc.eamon.open.flow.core.stereotype.support.execute;

import cc.eamon.open.flow.config.constants.FlowConstants;
import cc.eamon.open.flow.config.flow.FlowNodeConfig;
import cc.eamon.open.flow.config.flow.node.FlowParamConfig;
import cc.eamon.open.flow.config.flow.node.FlowResultConfig;
import cc.eamon.open.flow.core.context.FlowContextHolder;
import cc.eamon.open.flow.core.error.FlowRuntimeException;
import cc.eamon.open.flow.core.stereotype.execute.Executable;
import cc.eamon.open.flow.model.enums.TypeEnums;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-27 16:29:34
 */
public class HttpExecutable implements Executable {

    private static Logger logger = LoggerFactory.getLogger(HttpExecutable.class);

    /**
     * 流程节点的配置
     */
    private FlowNodeConfig flowNodeConfig;


    public HttpExecutable(FlowNodeConfig flowNodeConfig) {
        this.flowNodeConfig = flowNodeConfig;
    }

    @Override
    public List<TypeEnums> getParamsTypes() {
        List<TypeEnums> typeEnums = new ArrayList<>();
        for (FlowParamConfig param : flowNodeConfig.getParams()) {
            typeEnums.add(TypeEnums.OBJECT);
        }
        return typeEnums;
    }

    @Override
    public void execute() {
        StringBuilder reference = new StringBuilder(flowNodeConfig.getReference());

        if (flowNodeConfig.getParams() != null && flowNodeConfig.getParams().size() > 0) {
            reference.append(FlowConstants.QUESTION_SPLIT);
            for (FlowParamConfig param : flowNodeConfig.getParams()) {
                Object variable = FlowContextHolder.get().retrieveVariable(param.getScope(), param.getName());
                reference.append(param.getName()).append(FlowConstants.EQUAL_SPLIT).append(variable.toString()).append(FlowConstants.ET_SPLIT);
            }
            reference.deleteCharAt(reference.length() - 1);
        }


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(reference.toString())
                .build();
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            if (response.isSuccessful() && body != null) {
                JSONObject parseObject = JSONObject.parseObject(body.string());
                for (FlowResultConfig result : flowNodeConfig.getResults()) {
                    JSONObject object = parseObject;
                    String[] areas = result.getParse().split("\\.");
                    for (int i = 0; i < areas.length - 1; i++) {
                        object = object.getJSONObject(areas[i]);
                    }
                    FlowContextHolder.get().registerVariable(result.getScope(), result.getName(), object.get(areas[areas.length - 1]));
                }


            }
        } catch (IOException e) {
            throw new FlowRuntimeException(flowNodeConfig.getId(), "http远程调度过程错误：" + flowNodeConfig.getReference() + " " + e.getMessage());
        }


    }
}

