package cc.eamon.open.flow.spring.extra;


import cc.eamon.open.flow.core.context.pipeline.Pipeline;

public abstract class ExtraBasePipeline implements Pipeline, Comparable<ExtraBasePipeline>{
    private Pipeline pre;

    private int level;

    public ExtraBasePipeline(int level) {
        this.level = level;
    }


    public ExtraBasePipeline setPre(Pipeline pre) {
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
    public int compareTo(ExtraBasePipeline o) {
        //实现最小的元素在队头
        return level < o.level ? -1 : (level == o.level ? 0 : 1);
    }
}
