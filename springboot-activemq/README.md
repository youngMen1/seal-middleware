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

## 参考
https://www.cnblogs.com/shamo89/p/8010660.html
https://www.cnblogs.com/ruiati/p/8984303.html
https://blog.csdn.net/eumenides_/article/details/78356170
https://gitee.com/hjj520/spring-cloud-2.x/tree/master/sc-activemq