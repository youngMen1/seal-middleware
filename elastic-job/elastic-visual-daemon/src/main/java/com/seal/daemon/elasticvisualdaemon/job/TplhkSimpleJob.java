package com.seal.daemon.elasticvisualdaemon.job;


import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/9 17:45
 * @description 测试Job
 **/
@Slf4j
public class TplhkSimpleJob implements SimpleJob {
	/**
	 * 业务执行逻辑
	 *
	 * @param shardingContext 分片信息
	 */
	@Override
	public void execute(ShardingContext shardingContext) {
		log.info("第一个任务执行啦:{}", shardingContext);
	}
}
