package com.seal.daemon.elasticvisualdaemon.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/9 17:45
 * @description 任务监听器
 **/
public class TplhkElasticJobListener implements ElasticJobListener {

	@Override
	public void beforeJobExecuted(ShardingContexts shardingContexts) {
		System.out.println(shardingContexts.getJobName() + " | MyElasticJobListener beforeJobExecuted");
	}

	@Override
	public void afterJobExecuted(ShardingContexts shardingContexts) {
		System.out.println(shardingContexts.getJobName() + " | MyElasticJobListener afterJobExecuted");
	}
}
