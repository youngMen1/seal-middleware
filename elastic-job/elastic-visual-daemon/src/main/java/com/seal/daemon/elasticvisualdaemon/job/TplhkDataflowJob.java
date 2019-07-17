package com.seal.daemon.elasticvisualdaemon.job;


import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.List;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/9 17:45
 * @description
 **/
public class TplhkDataflowJob implements DataflowJob<Integer> {

    @Override
    public List<Integer> fetchData(ShardingContext shardingContext) {
        return null;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Integer> list) {

    }
}
