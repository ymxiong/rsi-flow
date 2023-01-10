package cc.eamon.open.flow.model.parse;

import java.util.Date;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2023/1/10 1:24 上午
 **/
public class LongDateParser implements Parser<Long, Date> {

    @Override
    public Date parse(Long time) {
        return new Date(time);
    }
}
