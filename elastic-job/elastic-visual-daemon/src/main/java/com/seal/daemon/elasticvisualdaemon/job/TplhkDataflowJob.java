package com.seal.daemon.elasticvisualdaemon.job;


import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.List;

/**
 * @author zhangxinxin
 * @date 2018/2/8
 */
public class TplhkDataflowJob implements DataflowJob<Integer> {

    @Override
    public List<Integer> fetchData(ShardingContext shardingContext) {
        return null;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Integer> list) {

    }
}
