# c/c++

## 代码拦截/hook

### LD_PRELOAD

参考：

https://blog.csdn.net/itworld123/article/details/125755603（深入分析 LD_PRELOAD）

https://blog.csdn.net/Long_xu/article/details/128897509（【程序狂魔】掌握LD_PRELOAD轻松进行程序修改和优化的绝佳方法！）

### ptrace等其他方式

参考：

https://blog.csdn.net/inthat/article/details/119931358（linux系统下的各种hook方式\Linux内核hook系统调用）

https://blog.csdn.net/weixin_45030965/article/details/132547013（Linux ptrace系统调用）、https://blog.csdn.net/litost000/article/details/82813641（Ptrace--Linux中一种代码注入技术的应用）

## gcc/g++

### `-l/-L/Wl`

参考：

https://blog.csdn.net/sinat_31608641/article/details/122513674（gcc的-l参数，-L参数，-I参数）



例如`-ldl`，则表示链接`libdl.so`动态库（libxxx.so是标准写法，.so表示动态库，.a表示静态库，libdl.so表示名为dl的动态库，参考：https://blog.csdn.net/Dontla/article/details/129086709（Linux C++ g++ -ldl编译参数（链接libdl.so动态库，头文件＜dlfcn.h＞）（Dynamic Linker的缩写）（混链接、混合链接）））



**-Wl**

参考：

https://blog.csdn.net/challenglistic/article/details/129687387（【gcc编译选项】-L、-Wl 的区别（运行时无法链接到动态库：cannot open shared object file））



## dlsym/dlopen/dlclose

参考：

https://blog.csdn.net/m0_61451096/article/details/135820794（关于dlsym的总结）

# linux命令

## ldd

参考：

https://blog.csdn.net/AnChenliang_1002/article/details/131363182（Linux系统下查看动态库依赖关系指令（ldd））

# shell 脚本

## shell 脚本处理用户输入

参考：

https://blog.csdn.net/weixin_43276033/article/details/124723106（Linux shell 脚本处理用户输入（命令行选项参数、用户输入））

## shell脚本sudo自动输入密码

参考：

https://blog.csdn.net/Britripe/article/details/114081200（ubuntu/linux运行shell脚本sudo自动输入密码（亲测可以））

## 加密shell脚本

参考：

https://blog.csdn.net/rong11417/article/details/93182681（如何对shell脚本进行加密且不影响脚本运行）