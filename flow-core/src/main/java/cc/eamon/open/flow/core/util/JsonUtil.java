package cc.eamon.open.flow.core.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class JsonUtil {

    //把json文件转换成json字符串
    public static String getJson(String path) throws IOException {
        File file=new File(JsonUtil.class.getResource(path).getPath());
        return FileUtils.readFileToString(file,"UTF-8");
    }
}
