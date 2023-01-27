package cc.eamon.open.flow.spring;

import org.springframework.boot.CommandLineRunner;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2023/1/27 5:26 下午
 **/
public class FlowRunner implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        Class.forName("cc.eamon.open.flow.core.FlowEngine");
    }
}
