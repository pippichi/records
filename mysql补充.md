# mysql死锁排查及解决

参考：

https://blog.csdn.net/qq_37436172/article/details/128067682（mysql死锁介绍以及解决）、https://blog.csdn.net/weixin_56738054/article/details/128836621（【MySQL锁篇】MySQL死锁问题以及解决方案）



使用MySQL系统表`information_schema.INNODB_TRX`

参考：https://blog.csdn.net/oschina_41731918/article/details/128005386（MySQL系统表`information_schema.INNODB_TRX`详解及查看当前运行事务）



# 唯一键冲突时会加gap lock间隙锁

参考：https://www.zhihu.com/question/547447463/answer/2616628115（MySQL，insert时唯一索引冲突时会加gap lock间隙锁）

# mysql分区表分区操作

参考：

https://www.cnblogs.com/ruiati/p/6993539.html（mysql 表分区 查看表分区 修改表分区）

https://blog.51cto.com/u_16213343/7289696（mysql修改分区表分区）



注意：分区表删除某分区时，该分区的磁盘数据并不会马上删除，可能还需要执行物理删除，如`OPTIMIZE TABLE`语句或者`ALTER TABLE ... ENGINE=InnoDB`语句来完成真正的删除。而且物理数据删除可能会导致一些数据库操作的停顿，因为它涉及到大量的磁盘操作。

# MySQL事件调度器Event Scheduler

参考：https://blog.csdn.net/qq_34745941/article/details/115486804（Mysql 事件调度器详解（Event Scheduler））、https://blog.csdn.net/qq_41819893/article/details/121147980（MySQL——事件）、https://blog.csdn.net/JokerLJG/article/details/128701993（MySQL事件）



# mysql全面分析以及分析工具以及数据库日志

在数据库出现慢操作、阻塞、锁表时，可以查看该章节

参考：

https://www.cnblogs.com/cw2blog/articles/17771440.html（Mariadb数据库存储路径及日志路径★）

https://blog.csdn.net/lpfstudy/article/details/130364375（Mysql - 日志及其存放路径以及如何配置该路径）



# Canal

参考：

https://blog.csdn.net/u011066470/article/details/126734578（mysql的数据表同步工具 canal的使用）

https://blog.51cto.com/u_14014612/6027495（Canal高可用架构部署）

https://www.cnblogs.com/huangxincheng/p/7456397.html（缓存一致性和跨服务器查询的数据异构解决方案canal）

https://segmentfault.com/a/1190000023297973（阿里canal是怎么通过zookeeper实现HA机制的？）

https://blog.csdn.net/XDSXHDYY/article/details/97825508（canal介绍及HA集群模式搭建）

https://blog.csdn.net/qq_46893497/article/details/111026996（Canal--介绍及原理）
