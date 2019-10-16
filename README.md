## Elastic-Job
Elastic-Job是一个分布式调度解决方案，由两个相互独立的子项目Elastic-Job-Lite和Elastic-Job-Cloud组成。
Elastic-Job-Lite定位为轻量级无中心化解决方案，
使用jar包的形式提供分布式任务的协调服务；
Job-Cloud采用自研Mesos Framework的解决方案，额外提供资源治理、
应用分发以及进程隔离等功能（PS:我在这里只说Elastic-Job-Lite，因为Job-Cloud我没去研究）。
简单的说Elastic-Job-Lite就是一个分布式定时任务。
---
## 消息队列
### 一、 MQ背景&选型
消息队列作为高并发系统的核心组件之一，能够帮助业务系统解构提升开发效率和系统稳定性。主要具有以下优势：

* 削峰填谷（主要解决瞬时写压力大于应用服务能力导致消息丢失、系统奔溃等问题）
* 系统解耦（解决不同重要程度、不同能力级别系统之间依赖导致一死全死）
* 提升性能（当存在一对多调用时，可以发一条消息给消息系统，让消息系统通知相关系统）
* 蓄流压测（线上有些链路不好压测，可以通过堆积一定量消息再放开来压测）

目前主流的MQ主要是Rocketmq、kafka、Rabbitmq，Rocketmq相比于Rabbitmq、kafka具有主要优势特性有：
* 支持事务型消息（消息发送和DB操作保持两方的最终一致性，rabbitmq和kafka不支持）
* 支持结合rocketmq的多个系统之间数据最终一致性（多方事务，二方事务是前提）
* 支持18个级别的延迟消息（rabbitmq和kafka不支持）
* 支持指定次数和时间间隔的失败消息重发（kafka不支持，rabbitmq需要手动确认）
* 支持consumer端tag过滤，减少不必要的网络传输（rabbitmq和kafka不支持）
* 支持重复消费（rabbitmq不支持，kafka支持）

Kafka的架构和涉及到的名词：
* Topic：用于划分Message的逻辑概念，一个Topic可以分布在多个Broker上。
* Partition：是Kafka中横向扩展和一切并行化的基础，每个Topic都至少被切分为1个Partition。
* Offset：消息在Partition中的编号，编号顺序不跨Partition。
* Consumer：用于从Broker中取出/消费Message。
* Producer：用于往Broker中发送/生产Message。
* Replication：Kafka支持以Partition为单位对Message进行冗余备份，每个Partition都可以配置至少1个Replication(当仅1个Replication时即仅该Partition本身)。
* Leader：每个Replication集合中的Partition都会选出一个唯一的Leader，所有的读写请求都由Leader处理。其他Replicas从Leader处把数据更新同步到本地，过程类似大家熟悉的MySQL中的Binlog同步。
* Broker：Kafka中使用Broker来接受Producer和Consumer的请求，并把Message持久化到本地磁盘。每个Cluster当中会选举出一个Broker来担任Controller，负责处理Partition的Leader选举，协调Partition迁移等工作。
* ISR(In-Sync Replica)：是Replicas的一个子集，表示目前Alive且与Leader能够“Catch-up”的Replicas集合。由于读写都是首先落到Leader上，所以一般来说通过同步机制从Leader上拉取数据的Replica都会和Leader有一些延迟(包括了延迟时间和延迟条数两个维度)，任意一个超过阈值都会把该Replica踢出ISR。每个Partition都有它自己独立的ISR。
### ActiveMq
#### 1.ActiveMQ概述 
企业消息软件从80年代起就存在，它不只是一种应用间消息传递风格，也是一种集成风格。因此，消息传递可以满足应用间的通知和互相操作。但是开源的解决方案是到最近10年才出现的。
Apache ActiveMQ就是其中一种。它使应用间能以异步，松耦合方式交流。ActiveMQ 是Apache出品，最流行的，能力强劲的开源消息总线。
‍ActiveMQ是Apache软件基金下的一个开源软件，它遵循JMS规范（Java Message Service），是消息驱动中间件软件（MOM）。它为企业消息传递提供高可用，出色性能，可扩展，稳定和安全保障。ActiveMQ使用Apache许可协议。
因此，任何人都可以使用和修改它而不必反馈任何改变。这对于商业上将ActiveMQ用在重要用途的人尤为关键。MOM的工作是在分布式的各应用之间调度事件和消息，使之到达指定的接收者。所以高可用，高性能，高可扩展性尤为关键。
#### 2.ActiveMQ特性
* ⒈支持多种语言客户端,如:Java,C,C++,C#,Ruby,Perl,Python,PHP。应用协议有 OpenWire,Stomp REST,WS Notification,XMPP,AMQP。
* ⒉ 完全支持JMS1.1和J2EE1.4规范,它们包括同步和异步消息传递，一次和只有一次的消息传递，对于预订者的持久消息等等。依附于JMS规范意味着，不论JMS消息提供者是谁，同样的基本特性（持久化，XA消息，事务)都是有效的。
* ⒊ 对Spring的支持，ActiveMQ可以很容易内嵌到使用Spring的系统里面去。
* ⒋ 通过了常见J2EE服务器（如 Geronimo,JBoss 4,GlassFish,WebLogic)的测试，其中通过JCA 1.5 resource adaptors的配置，可以让ActiveMQ可以自动的部署到任何兼容J2EE 1.4 商业服务器上。
* ⒌ ActiveMQ提供各种连接选择，包括HTTP，HTTPS，IP多点传送，SSL，STOMP，TCP，UDP，XMPP等。大量的连接协议支持使之具有更好的灵活性。很多现有的系统使用一种特定协议并且不能改变，所以一个支持多种协议的消息平台降低了使用的门槛。虽然连接很重要，但是和其他容器集成也同样重要。
* 6.ActiveMQ提供多种持久性方案可供选择，也可以完全按自己需求定制验证和授权。例如，ActiveMQ通过KahaDB提供自己的超快速消息持久方案（ultra-fast message persistence），但也支持标准的JDBC方案。ActiveMQ可以通过配置文件提供简单的验证和授权，也提供标准的JAAS登陆模块。
* 7.ActiveMQ是为开发者设计的。它并不需要专门的管理工具，因为它提供各种易用且强大的管理特性。有很多方法去监控ActiveMQ的各个方面，可以通过JMX使用JConsole或ActiveMQ web console；可以运行ActiveMQ消息报告；可以用命令行脚本；可以通过日志。
* 8.代理器集群（Broker clustering）----为了利于扩展，多个ActiveMQ broker能够联合工作。这个方式就是network of brokers并且能支持多种拓扑结构;支持客户端-服务器，点对点。
* 9.支持Ajax, 支持与Axis的整合

### Kafka/Jafka
Kafka是Apache下的一个子项目，是一个高性能跨语言分布式发布/订阅消息队列系统，
而Jafka是在Kafka之上孵化而来的，即Kafka的一个升级版。
具有以下特性：快速持久化，可以在O(1)的系统开销下进行消息持久化；
高吞吐，在一台普通的服务器上既可以达到10W/s的吞吐速率；
完全的分布式系统，Broker、Producer、Consumer都原生自动支持分布式，
自动实现负载均衡；支持Hadoop数据并行加载，对于像Hadoop的一样的日志数据和离线分析系统，
但又要求实时处理的限制，这是一个可行的解决方案。Kafka通过Hadoop的并行加载机制统一了在线和离线的消息处理。
Apache Kafka相对于ActiveMQ是一个非常轻量级的消息系统，除了性能非常好之外，
还是一个工作良好的分布式系统。

### RabbitMq
RabbitMQ是使用Erlang编写的一个开源的消息队列，本身支持很多的协议：AMQP，XMPP, SMTP, STOMP，也正因如此，它非常重量级，
更适合于企业级的开发。同时实现了Broker构架，这意味着消息在发送给客户端时先在中心队列排队。
对路由，负载均衡或者数据持久化都有很好的支持。

### RocketMQ
RocketMQ 是阿里巴巴在2012年开源的分布式消息中间件，目前已经捐赠给 Apache 软件基金会，
并于2017年9月25日成为 Apache 的顶级项目。作为经历过多次阿里巴巴双十一这种“超级工程”的洗礼并有稳定出色表现的国产中间件，以其高性能、低延时和高可靠等特性近年来已经也被越来越多的国内企业使用。其主要特点有：
* 1.灵活可扩展性
RocketMQ 天然支持集群，其核心四组件（Name Server、Broker、Producer、Consumer）每一个都可以在没有单点故障的情况下进行水平扩展。
* 2.海量消息堆积能力
RocketMQ 采用零拷贝原理实现超大的消息的堆积能力，据说单机已可以支持亿级消息堆积，而且在堆积了这么多消息后依然保持写入低延迟。
* 3.支持顺序消息
可以保证消息消费者按照消息发送的顺序对消息进行消费。顺序消息分为全局有序和局部有序，一般推荐使用局部有序，即生产者通过将某一类消息按顺序发送至同一个队列来实现。
* 4.多种消息过滤方式
消息过滤分为在服务器端过滤和在消费端过滤。服务器端过滤时可以按照消息消费者的要求做过滤，优点是减少不必要消息传输，缺点是增加了消息服务器的负担，实现相对复杂。消费端过滤则完全由具体应用自定义实现，这种方式更加灵活，缺点是很多无用的消息会传输给消息消费者。
* 5.支持事务消息
RocketMQ 除了支持普通消息，顺序消息之外还支持事务消息，这个特性对于分布式事务来说提供了又一种解决思路。
* 6.回溯消费
回溯消费是指消费者已经消费成功的消息，由于业务上需求需要重新消费，RocketMQ 支持按照时间回溯消费，时间维度精确到毫秒，可以向前回溯，也可以向后回溯。

#### 基本概念
下面是一张 RocketMQ 的部署结构图，里面涉及了 RocketMQ 核心的四大组件：Name Server、Broker、Producer、Consumer ，
每个组件都可以部署成集群模式进行水平扩展。
![img](/doc/微信截图_20191016103840.png)

### ZeroMQ
ZeroMQ号称最快的消息队列系统，尤其针对大吞吐量的需求场景。
ZeroMQ能够实现RabbitMQ不擅长的高级/复杂的队列，但是开发人员需要自己组合多种技术框架，
技术上的复杂度是对这MQ能够应用成功的挑战。ZeroMQ具有一个独特的非中间件的模式，
你不需要安装和运行一个消息服务器或中间件，因为你的应用程序将扮演这个服务器角色。
你只需要简单的引用ZeroMQ程序库，可以使用NuGet安装，然后你就可以愉快的在应用程序之间发送消息了。
但是ZeroMQ仅提供非持久性的队列，也就是说如果宕机，数据将会丢失。其中，
Twitter的Storm 0.9.0以前的版本中默认使用ZeroMQ作为数据流的传输（Storm从0.9版本开始同时支持ZeroMQ和Netty作为传输模块）。

## XXL-JOB
XXL-JOB是一个轻量级分布式任务调度平台，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。
现已开放源代码并接入多家公司线上产品线，开箱即用。