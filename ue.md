# 材质节点

## ObjectPivotPoint

ObjectPivotPoint代表物体模型空间原点在世界空间的位置

## RotateAboutAxis与FixRotateAboutAxisNormals

参考：https://zhuanlan.zhihu.com/p/381326628（RotateAboutAxis和FixRotateAboutAxisNormals）、https://zhuanlan.zhihu.com/p/394785452（在 UE4 中使用顶点着色器旋转网格）



# 创建可复用的动画通知类（Anim Notify Class）

参考：https://blog.csdn.net/ttm2d/article/details/111769249（UE4动画系统：什么是动画通知（Anim Notify））



# Gameplay Ability System（GAS）

参考：https://blog.csdn.net/m0_38110586/category_11011758.html（虚幻四Gameplay Ability System入门1-12）



# control rig与IK rig

参考：https://zhuanlan.zhihu.com/p/499405167（★【游戏开发】逆向运动学（IK）详解，包括雅可比矩阵、奇异值分解（SVD）等解算方法讲解）、https://zhuanlan.zhihu.com/p/591982020（UE5 -- Control Rig与IK Rig介绍）、https://blog.csdn.net/ttm2d/article/details/112545858（虚幻引擎图文笔记：用Two Bone IK实现手扶墙）、https://blog.csdn.net/weixin_41363156/article/details/114645792（UE4之Control Rig）、https://zhuanlan.zhihu.com/p/412251528（[玩转UE4/UE5动画系统＞Control Rig篇] 之 Control Rig + Fullbody IK版的足部IK实现（附项目代码））



# virtual texture

参考：https://zhuanlan.zhihu.com/p/138484024（浅谈Virtual Texture）



# Blender Rigify

参考：https://zhuanlan.zhihu.com/p/452380549（Blender Rigify使用基础指南 （含Rigify——游戏引擎工作流））



# RPG项目

参考：

- https://www.bilibili.com/video/BV1jt4y1S7A7/?p=282&spm_id_from=333.880.my_history.page.click&vd_source=c3d9e4c3ef670596b3b0dddab637f86c（UE4 最完整的开放世界系列教程【附工程】【收藏夹必备】【422P 持续更新】）
- https://www.bilibili.com/video/BV1tg411v7L7/?spm_id_from=333.880.my_history.page.click&vd_source=c3d9e4c3ef670596b3b0dddab637f86c（【教程】全网最完整 UE5 100% 蓝图开发Steam 多人孤岛生存游戏 双语字幕 现已更新到117（20230308)）
- https://www.bilibili.com/video/BV1uS4y1872y?p=1&vd_source=c3d9e4c3ef670596b3b0dddab637f86c（【UE5】多人射击游戏开发完全教程 人工校对字幕-全网最详细56小时超长完整）



# c++ 构建蓝图多输入多输出引脚节点

参考：https://www.cnblogs.com/tanfu/p/16634394.html（构建蓝图中包含多输入多输出引脚的节点）



# 高级运动系统 (ALSV4)

参考：https://zhuanlan.zhihu.com/p/604888297（【UE5】【3C】ALSv4重构分析（一） : 更好的ALS学习体验）、https://zhuanlan.zhihu.com/p/518724305（UE4 UE5 骨骼动画 高级运动系统 (ALSV4)）、https://zhuanlan.zhihu.com/p/547321935（UE4 UE5 骨骼动画 高级运动系统 脚部IK）、https://zhuanlan.zhihu.com/p/568124406（UE4 UE5 骨骼动画 高级运动系统 手部IK 虚拟骨骼）



# AI行为树源码详解

参考：https://zhuanlan.zhihu.com/p/368889019（【图解UE4源码】AI行为树系统 目录）、https://zhuanlan.zhihu.com/p/371623309（【图解UE4源码】 其三（〇）行为树系统执行任务的流程 概述）、https://zhuanlan.zhihu.com/p/139514376（[UE4] 浅析UE4-BehaviorTree的特性）、https://zhuanlan.zhihu.com/p/143298443（UE4行为树详解（持续更新，才怪））



# FlowMap

flowMap被广泛用于制作水体或者云的“流动效果”

参考：https://zhuanlan.zhihu.com/p/222500848（[UE4] FlowMap）、https://zhuanlan.zhihu.com/p/237638786（[UE4] FlowMap Painter Tool）



# 网络复制

参考：https://zhuanlan.zhihu.com/p/593894970（关于UObject如何解决网络复制问题以及数组在Replicated网络复制的优化）



# TAttribute与Slate数据绑定

参考：https://zhuanlan.zhihu.com/p/465410846（UE4 TAttribute原理 与 Slate数据绑定）



# UE常用数据结构TArray、TMap、TSet、TDoubleLinkedList

参考：https://blog.csdn.net/ryacber/article/details/128758764（UE C++基础 | 常用数据容器 | TArray、TMap、TSet）、https://docs.unrealengine.com/5.1/en-US/API/Runtime/Core/Containers/TDoubleLinkedList/（UE官方TDoubleLinkedList）



# UE内存管理

- 不受内存管理的内存

  - malloc & free
  - new & delete

   new与malloc的区别在于，new在分配内存完成之后会调用构造函数。

- 内存管理的内存

  - 对于不是继承自UObject的Native C++类，使用TSharedPtr、TAutoPtr、TWeakPtr、TSharedRef、TScopedPointer管理

  - 对于继承自UObject的子类

    **创建**： UObject::NewObject<> 或是 UObject::ConstructObject<>，其中ConstructObject可以做更复杂的参数配置

    **销毁**：当计数为0时，自动释放；调用UObject::ConditionalBeginDestroy()手动释放。若要强制调用垃圾回收，则调用UWorld::ForceGarbageCollection(true)

  - 对于继承自AActor的子类

     **创建**： UWorld::SpawnActor<>

     **销毁**： AActor::Destroy()

  - TArray<>数组需要用UPROPERTY()修饰，否则会导致内存管理错误

  - 继承自UActorComponent的组件，使用AActor::CreateDefaultSubobject<>，同样组件的指针变量也需要用UPROPERTY()修饰



## UE中的智能指针

在游戏开发中，我们不可能完全使用UE的注解进行内存管理，特殊情况下我们需要自己开辟销毁内存，此时使用UE封装的智能指针就是一种方案。

参考：https://blog.csdn.net/github_38111866/article/details/107712692（【UE4】共享（智能）指针用法）、https://zhuanlan.zhihu.com/p/472486869（【UE4 C++ 基础知识】<15> 智能指针 TSharedPtr、UniquePtr、TWeakPtr、TSharedRef）、https://zhuanlan.zhihu.com/p/369974105（UE4的智能指针 TSharedPtr）



### FSoftClassPath 和 FSoftObjectPath

参考：https://zhuanlan.zhihu.com/p/428555822（UE4学习记录(2)FSoftClassPath 和FSoftObjectPath 区别）

### TSoftClassPtr 和 TSoftObjectPtr

参考：https://blog.csdn.net/qq_45777198/article/details/107838444（【学习笔记】UE4——`TSoftClassPtr<T> ptr`和`TSoftObjectPtr<T> ptr`）

# UE枚举迭代遍历

参考：https://zhuanlan.zhihu.com/p/492702386（UE4枚举的迭代遍历）、https://blog.csdn.net/a359877454/article/details/105262795（UE4遍历枚举）

# 坐标系

## 物体相对坐标与世界坐标互转

参考：https://blog.csdn.net/l346242498/article/details/106919703（UE4 相对坐标转世界坐标）、https://blog.csdn.net/longyanbuhui/article/details/115407458（UE4_local location（本地位置） 与 world location（世界位置） 转换）

## 屏幕坐标与世界坐标互转

参考：https://blog.csdn.net/longyanbuhui/article/details/84201864（UE4_屏幕位置与世界位置的相互转化）、https://zhuanlan.zhihu.com/p/597560776（UE4 相机屏幕坐标与世界坐标的相互转换）

# 游戏技能音效下载网站

- https://soundscrate.com/electricity.html（Electricity Sound Effects）
- https://freesfx.co.uk/Default.aspx（freeSFX）
- https://opengameart.org/art-search-advanced（OpenGameArt.ORG）
- https://www.aigei.com/sound/class/fight/（爱给）

参考：https://zhuanlan.zhihu.com/p/108442292（推荐几个免费 游戏技能音效下载网站）

# 风场（Wind）

参考：https://blog.csdn.net/shenmifangke/article/details/80281904（ue4内植物碰撞和风力设置）、https://blog.csdn.net/qq_36917144/article/details/104010429（虚幻4创建旗帜和随机风力）

# 体积烟雾

参考：https://zhuanlan.zhihu.com/p/405811376（【UE4】五种烟雾流程）、FluidNinjaLive、b站聆枫LingFeng Niagara流体模拟教程
