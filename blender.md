# 烘焙贴图

参考：https://blog.csdn.net/weixin_44568736/article/details/128119426（blender 烘焙贴图）

# 重拓扑（Polybuild Tool）

参考：https://blog.csdn.net/ttm2d/article/details/100171329/（Polybuild Tool：Blender自带的重拓扑工具）


# 动画

## 动画曲线编辑器（Graph Editor）

参考：https://blog.csdn.net/ttm2d/article/details/108614472（Blender笔记：动画曲线编辑器（Graph Editor）技巧）

# 旋转

## 四元数旋转与欧拉旋转

欧拉旋转可以轻松处理物体绕某个轴360°旋转，但四元数旋转处理起来就比较麻烦。

所以在做动画的时候，处理四元数旋转往往需要打更多的关键帧才不会出错。

在绑定骨骼的时候，默认用的是四元数旋转，那么一般会采用这种方法来处理骨骼360°公转：复制一根骨骼b，骨骼b使用欧拉旋转，然后让原骨骼复制骨骼b的旋转。从而保留原骨骼四元数旋转来做其他事情。

但是欧拉旋转有个致命的问题：“万向锁”（由于发生旋转，xyz三个轴中某两个轴重叠到一起时，原本物体能绕3个轴转，此时只能绕2个轴转）

黄金法则：当选择欧拉旋转时，其中一个轴必须锁住

![image-20230604213443445](blender.assets/image-20230604213443445.png)

![image-20230604213506915](blender.assets/image-20230604213506915.png)

![image-20230604213523673](blender.assets/image-20230604213523673.png)

## 万向锁与四元数

参考：https://blog.csdn.net/jkkk_/article/details/125398647（详解四元数）

四元数简单解释：四元数有4个维度xyzw，可以看成是一个旋转轴加一个旋转角就行，xyz归一化后是旋转轴，w是半旋转角的余弦

# 技巧

## 点对齐方法

参考：https://www.jianshu.com/p/7c1c1e738294（关于blender点对齐的方法）

## 文件格式

png格式的图片可能会存在不能正确显示颜色的问题以及他的size比jpeg要大很多。png可以转成：tif、tga，tif、tga是带alpha通道的，jpeg是不带的。
