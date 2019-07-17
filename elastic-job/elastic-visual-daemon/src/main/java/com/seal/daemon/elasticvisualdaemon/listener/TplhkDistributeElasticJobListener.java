package com.seal.daemon.elasticvisualdaemon.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/9 17:45
 * @description
 **/
public class TplhkDistributeElasticJobListener extends AbstractDistributeOnceElasticJobListener {

	public TplhkDistributeElasticJobListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
		super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
	}

	@Override
	public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
		System.out.println(shardingContexts.getJobName() + " | MyDistributeElasticJobListener doBeforeJobExecutedAtLastStarted...");
	}

	@Override
	public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
		System.out.println(shardingContexts.getJobName() + " | MyDistributeElasticJobListener doAfterJobExecutedAtLastCompleted...");
	}
}
