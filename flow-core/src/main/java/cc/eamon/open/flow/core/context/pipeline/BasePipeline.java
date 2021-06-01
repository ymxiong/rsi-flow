package cc.eamon.open.flow.core.context.pipeline;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 19:58:55
 */
public abstract class BasePipeline implements Pipeline, Comparable<BasePipeline>{

    private Pipeline pre;

    private int level;

    public BasePipeline(int level) {
        this.level = level;
    }


    public BasePipeline setPre(Pipeline pre) {
        this.pre = pre;
        return this;
    }

    @Override
    public void initializer() {
        if (pre != null) pre.initializer();
        initializerSelf();
    }

    public abstract void initializerSelf();

    @Override
    public int compareTo(BasePipeline o) {
        //实现最小的元素在队头
        return level < o.level ? -1 : (level == o.level ? 0 : 1);
    }

}
