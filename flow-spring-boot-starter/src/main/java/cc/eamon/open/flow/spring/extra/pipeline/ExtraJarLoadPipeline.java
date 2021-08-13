package cc.eamon.open.flow.spring.extra.pipeline;

import cc.eamon.open.flow.core.context.pipeline.BasePipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 20:09:01
 */
public class ExtraJarLoadPipeline extends BasePipeline {

    private static Logger logger = LoggerFactory.getLogger(ExtraJarLoadPipeline.class);

    private File extraJar;

    public ExtraJarLoadPipeline(int level, File extraJar) {
        super(level);
        this.extraJar = extraJar;
    }

    @Override
    public void initializerSelf() {
        if (extraJar.exists() == false) {
            System.out.println("jar file not found.");
            return;
        }

        //获取类加载器的addURL方法，准备动态调用
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }

        // 获取方法的访问权限，保存原始值
        boolean accessible = method.isAccessible();
        try {
            //修改访问权限为可写
            if (accessible == false) {
                method.setAccessible(true);
            }

            // 获取系统类加载器
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            //获取jar文件的url路径
            java.net.URL url = extraJar.toURI().toURL();
            //jar路径加入到系统url路径里
            method.invoke(classLoader, url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //回写访问权限
            method.setAccessible(accessible);
        }

    }
}