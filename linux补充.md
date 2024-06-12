# 部署在linux上程序的问题排查案例

## 美团调试案例

参考：https://tech.meituan.com/2019/01/03/spring-boot-native-memory-leak.html（Spring Boot引起的“堆外内存泄漏”排查及经验总结）



# 进程

## 使用ps、pstree、pidof、pgrep查看进程

参考：https://blog.csdn.net/weixin_33344629/article/details/116550719（linux 查看所有子进程id,技术|Linux 中 4 个简单的找出进程 ID（PID）的方法）



## 转入后台运行命令（可配合jobs使用）

参考：

https://www.jb51.net/server/3169882ey.htm（Linux让程序在后台运行的四种方法）

https://blog.csdn.net/weixin_45565886/article/details/137752086（Linux配置程序后台运行（前后台来回切换））

https://www.cnblogs.com/chenmaoling/articles/17342136.html（linux 后台运行的命令是什么）



# 查看文件内容

## od命令

参考：

https://blog.csdn.net/u012964600/article/details/134926222（Linux od命令教程：如何以各种格式转储文件(附案例详解和注意事项)）

## hexdump命令

参考：

https://worktile.com/kb/ask/293169.html（linux hexdump命令）

https://blog.csdn.net/qq_33471732/article/details/134982264（linux中的od命令与hexdump命令）



