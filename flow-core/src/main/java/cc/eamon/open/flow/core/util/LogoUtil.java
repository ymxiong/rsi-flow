package cc.eamon.open.flow.core.util;

import cc.eamon.open.flow.core.spring.ComponentScaner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: lucas(jvjv)
 * Email: lucas@eamon.cc
 * Time: 2021/3/17 11:24
 */
public class LogoUtil {
    private static final Logger logger = LoggerFactory.getLogger(LogoUtil.class);

    public static void print() {
        StringBuilder str = new StringBuilder("\n");
        str.append("================================================================================================\n");
        str.append("		 _____ _     _____        __\n");
        str.append("		|  ___| |   / _ \\ \\      / /\n");
        str.append("		| |_  | |  | | | \\ \\ /\\ / / \n");
        str.append("		|  _| | |__| |_| |\\ V  V /  \n");
        str.append("		|_|   |_____\\___/  \\_/\\_/   \n\n");
        str.append("		(^ v ^) Coding hard, play harder.\n");
        str.append("================================================================================================\n");
        logger.info(str.toString());
    }
}
