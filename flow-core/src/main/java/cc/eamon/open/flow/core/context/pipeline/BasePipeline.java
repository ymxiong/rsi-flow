package cc.eamon.open.flow.core.context.pipeline;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2021-05-21 19:58:55
 */
public abstract class BasePipeline implements Pipeline {

    private Pipeline pre;

    public BasePipeline(Pipeline pre) {
        this.pre = pre;
    }

    @Override
    public void initializer() {
        if (pre != null) pre.initializer();
        initializerSelf();
    }

    public abstract void initializerSelf();

}
