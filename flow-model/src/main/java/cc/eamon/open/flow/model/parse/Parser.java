package cc.eamon.open.flow.model.parse;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2023/1/10 1:24 上午
 **/
public interface Parser<F, T> {

    T parse(F f);
}
