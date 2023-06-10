package cc.eamon.open.flow;

import cc.eamon.open.flow.model.enums.BasicTypeEnums;
import org.junit.Test;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2023/6/7 9:25 PM
 **/
public class BasicTypeTest {

    @Test
    public void test() {
        System.out.println(BasicTypeEnums.getTypeByReference("Object"));
    }
}
