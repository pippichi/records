# 部署在linux上程序的问题排查案例

## 美团调试案例

参考：https://tech.meituan.com/2019/01/03/spring-boot-native-memory-leak.html（Spring Boot引起的“堆外内存泄漏”排查及经验总结）



## 跟踪linux用户态、内核态的系统调用

- strace命令

  可跟踪进程和线程的系统调用

  参考：

  https://blog.csdn.net/smartvxworks/article/details/132096816（【Linux】运行程序前加上strace，可以追踪到函数库调用过程）、https://blog.csdn.net/weixin_43230594/article/details/134874408（Linux 详细介绍strace命令）



## linux查看进程运行输出

场景：linux后台运行了一个jdk，如果想要查看该jdk的控制台输出则可以使用这种方案

参考：https://blog.csdn.net/u014756245/article/details/120023188（`Linux查看进程运行输出（/proc/＜pid＞/fd)`）



# 进程

## 使用ps、pstree、pidof、pgrep查看进程

参考：https://blog.csdn.net/weixin_33344629/article/details/116550719（linux 查看所有子进程id,技术|Linux 中 4 个简单的找出进程 ID（PID）的方法）



## 转入后台运行命令（可配合jobs使用）

参考：

https://www.jb51.net/server/3169882ey.htm（Linux让程序在后台运行的四种方法）

https://blog.csdn.net/weixin_45565886/article/details/137752086（Linux配置程序后台运行（前后台来回切换））

https://www.cnblogs.com/chenmaoling/articles/17342136.html（linux 后台运行的命令是什么）



## 使用systemctl管理自己的程序

参考：

https://blog.csdn.net/qq_38844263/article/details/131858298（Linux使用systemctl添加自启动程序）、https://blog.csdn.net/sayyy/article/details/79276575（Linux使用systemctl设置程序开机自启动）、https://blog.csdn.net/tl4832194/article/details/109781230（systemctl开机启动设置）、https://cloud.tencent.com/developer/article/2296762（将服务注册为Linux Systemctl 启动项 – /usr/local/bin/ 注册为服务器的命令）

https://blog.csdn.net/bandaoyu/article/details/124358513（【linux】使用systemctl start xxx启动自己的程序|开机启动|守护进程）、https://www.liuvv.com/p/c9c96ac3.html（systemd和systemctl详解）、https://blog.csdn.net/skh2015java/article/details/94012643（linux中systemctl详细理解及常用命令）



# 查看文件内容

## od命令

参考：

https://blog.csdn.net/u012964600/article/details/134926222（Linux od命令教程：如何以各种格式转储文件(附案例详解和注意事项)）

## hexdump命令

参考：

https://worktile.com/kb/ask/293169.html（linux hexdump命令）

https://blog.csdn.net/qq_33471732/article/details/134982264（linux中的od命令与hexdump命令）



