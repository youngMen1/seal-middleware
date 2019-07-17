package com.seal.common.jobinit;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.seal.common.properties.ElasticJobProperties;

import java.util.Map;


/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/7/9 17:45
 * @description 流式任务初始
 **/
public class DataflowJobInitialization extends AbstractJobInitialization {

	private Map<String, ElasticJobProperties.DataflowConfiguration> dataflowConfigurationMap;

	public DataflowJobInitialization(final Map<String, ElasticJobProperties.DataflowConfiguration> dataflowConfigurationMap) {
		this.dataflowConfigurationMap = dataflowConfigurationMap;
	}

	public void init() {
		for (String jobName : dataflowConfigurationMap.keySet()) {
			ElasticJobProperties.DataflowConfiguration configuration = dataflowConfigurationMap.get(jobName);
			initJob(jobName, configuration.getJobType(), configuration);
		}
	}

	@Override
	public JobTypeConfiguration getJobTypeConfiguration(String jobName, JobCoreConfiguration jobCoreConfiguration) {
		ElasticJobProperties.DataflowConfiguration configuration = dataflowConfigurationMap.get(jobName);
		return new DataflowJobConfiguration(jobCoreConfiguration, configuration.getJobClass(), configuration.isStreamingProcess());
	}
}
