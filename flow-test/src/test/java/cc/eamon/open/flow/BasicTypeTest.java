package cc.eamon.open.flow;

import cc.eamon.open.flow.model.enums.TypeEnums;
import org.junit.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2023/6/7 9:25 PM
 **/
public class BasicTypeTest {

    @Test
    public void testStandard() {
        Assert.assertSame(TypeEnums.STRING, TypeEnums.getTypeByKeyword("java.lang.String"));
    }

    @Test
    public void testAssemblyAndExecution() {
        Assert.assertSame(TypeEnums.getTypeByKeyword("List(String)"), TypeEnums.getTypeByClassDefaultObject(List.class));
        Assert.assertNotSame(TypeEnums.getTypeByKeyword("List(String)"), TypeEnums.getTypeByClassDefaultObject(ArrayList.class));
    }
}
