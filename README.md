## Elastic-Job
Elastic-Job是一个分布式调度解决方案，由两个相互独立的子项目Elastic-Job-Lite和Elastic-Job-Cloud组成。
Elastic-Job-Lite定位为轻量级无中心化解决方案，
使用jar包的形式提供分布式任务的协调服务；
Job-Cloud采用自研Mesos Framework的解决方案，额外提供资源治理、
应用分发以及进程隔离等功能（PS:我在这里只说Elastic-Job-Lite，因为Job-Cloud我没去研究）。
简单的说Elastic-Job-Lite就是一个分布式定时任务。

## 消息队列


## XXL-JOB
XXL-JOB是一个轻量级分布式任务调度平台，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。
现已开放源代码并接入多家公司线上产品线，开箱即用。