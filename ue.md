# 安装一个全新虚幻引擎时的优化操作

- Rider内存不足

  参考：https://zhuanlan.zhihu.com/p/437617731（Rider For UE4 的内存不足问题）

- IntelliJ系列占用C盘空间

  参考：https://blog.csdn.net/weixin_44449518/article/details/103334235（解决 IntelliJ IDEA占用C盘过大空间问题）

- UE编译速度慢

  参考：https://zhuanlan.zhihu.com/p/83190575（Unreal Engine大幅提升编译速度的技巧）

- UE缓存文件占用C盘空间

  参考：https://blog.csdn.net/weixin_44753042/article/details/123093832（【虚幻】清理缓存文件（C盘占用过大））

- Epic保管库占用C盘空间

  参考：https://zhuanlan.zhihu.com/p/528351452（C盘爆满：UE（虚幻引擎）缓存，Epic保管库迁移）

# 宏

## UPARAM(ref)

b站聆枫LingFeng的使用案例：c++定义了一个蓝图中调用的函数，如果想要在蓝图节点中添加一个输入引脚（输入引脚其实就是以入参的方式传入函数），则函数指定引脚入参前要加上UPARAM(ref)

参考：https://blog.csdn.net/opk8848/article/details/104887704（ue4 关于ufunction 函数 参数 用引用 UPARAM(ref)）

# 材质节点

参考：

https://blog.csdn.net/choa12345/article/details/120075714（[浅析]UE4材质基础总结）

各种材质表达式参考：https://docs.unrealengine.com/5.2/zh-CN/unreal-engine-material-expressions-reference/（本文列出了[材质编辑器](https://docs.unrealengine.com/5.2/zh-CN/unreal-engine-material-editor-user-guide)中所有可用 **材质表达式** 节点的参考页面）

## ObjectPivotPoint

ObjectPivotPoint代表物体模型空间原点在世界空间的位置

## RotateAboutAxis与FixRotateAboutAxisNormals

参考：https://zhuanlan.zhihu.com/p/381326628（RotateAboutAxis和FixRotateAboutAxisNormals）、https://zhuanlan.zhihu.com/p/394785452（在 UE4 中使用顶点着色器旋转网格）

## 材质表达式SceneTexture

- SceneTexture:PostProcessInput0
- SceneTexture:CustomDepth
- SceneTexture:CustomStencil
- SceneTexture:WorldNormal
- ...

参考：

https://docs.unrealengine.com/5.2/zh-CN/post-process-materials-in-unreal-engine/（后期处理材质）、https://zhuanlan.zhihu.com/p/615915744（UE4技术杂谈——后处理 之 遮挡描边）

https://blog.csdn.net/ttm2d/article/details/115247252（Unreal Engine 4 使用HLSL自定义着色器（Custom Shaders）教程（上））、https://blog.csdn.net/ttm2d/article/details/115263517（Unreal Engine 4 使用HLSL自定义着色器（Custom Shaders）教程（下））

## 抗锯齿DitherTemporalAA

参考：https://blog.csdn.net/xingyali/article/details/82215662（风格化材质制作）

# 组件Component

## SceneCapture场景捕获

可以用来做远程监控、后视镜视角、传送门、Cubemap（HDR高动态范围贴图）等

参考：

https://www.gongyesheji.org/?p=1941&wd=&eqid=faa65b910005355e000000066476aafd（【unreal4】 虚幻引擎中获取虚拟摄像机图像并导入UI(UMG)实时显示（SceneCaptureComponent2D、CanvasRenderTarget2D））

https://blog.csdn.net/qq_39934403/article/details/121654665（Unreal Engine UE4虚幻引擎，生成Cubemap（HDR高动态范围贴图））、https://blog.csdn.net/qq_39934403/article/details/121656255（Unreal Engine UE4虚幻引擎，创建Sky天空球，球天材质，自制天空球（HDR高动态范围贴图））

# 创建可复用的动画通知类（Anim Notify Class）

参考：https://blog.csdn.net/ttm2d/article/details/111769249（UE4动画系统：什么是动画通知（Anim Notify））

# Data Registry

参考：

https://zhuanlan.zhihu.com/p/471631747（UE5 -- DataRegistry（数据注册表)）

https://www.bilibili.com/video/BV1qq4y1W7Ka（DataRegistry：一种统领全局的新数据配置工具）

# Gameplay Ability System（GAS）

参考：

https://blog.csdn.net/m0_38110586/category_11011758.html（虚幻四Gameplay Ability System入门1-12★）

https://www.bilibili.com/video/BV1X5411V7jh（[中文直播]第31期｜GAS插件介绍（入门篇） | 伍德 大钊）、https://www.bilibili.com/video/BV1zD4y1X77M（[UnrealOpenDay2020]深入GAS架构设计 | EpicGames 大钊）、https://www.bilibili.com/video/BV1sG4y1o7MG（[UOD2022]基于GAS的运行时编辑器框架 | 深圳元象 王杰）

https://www.zhihu.com/people/a-gun-er-58（虚幻插件GAS分析系列★）、https://space.bilibili.com/92060300/video（UE4 GAS入门系列★）

https://zhuanlan.zhihu.com/p/486808688（【Unreal】虚幻GAS系统快速入门）

# ModularGamePlay、GameFeatures

参考：

https://zhuanlan.zhihu.com/p/599593994（UE5 ModularGamePlay相关理解）

https://www.bilibili.com/video/BV1s44y1y7kY（模块化游戏功能 Modular Game Features）

# 模块化动画系统LinkAnimLayer

参考：https://zhuanlan.zhihu.com/p/599610314（UE5 Lyra项目学习（五） 模块化动画系统）、官方Lyra项目

# Quartz

参考：

https://docs.unrealengine.com/5.3/zh-CN/overview-of-quartz-in-unreal-engine/

https://www.bilibili.com/video/BV1K64y1x7b2（2021.7.23 有字幕 UE5系列之七 MetaSounds and Quartz）

# MetaSounds

参考：

https://docs.unrealengine.com/5.3/zh-CN/metasounds-in-unreal-engine/

https://www.bilibili.com/video/BV1K64y1x7b2（2021.7.23 有字幕 UE5系列之七 MetaSounds and Quartz）



# Control rig与IK rig

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

# UE多线程以及线程安全

参考：

https://zhuanlan.zhihu.com/p/133921916（UE4 C++基础 - 多线程）、https://zhuanlan.zhihu.com/p/174905748（UE4_MultiThread_多线程初体验）、https://zhuanlan.zhihu.com/p/408012121（UE4/UE5的TaskGraph）

https://zhuanlan.zhihu.com/p/403211214（UE4/UE5的LockFreeList）、https://zhuanlan.zhihu.com/p/367807315（UE4的队列TQueue）、https://zhuanlan.zhihu.com/p/362377941（UE4的TripleBuffer）



# UBT（UnrealBuildTool）、UHT（UnrealHeaderTool）

参考：https://zhuanlan.zhihu.com/p/400473355（UE -- UBT、UHT与反射基本理解）、https://www.cnblogs.com/ghl_carmack/p/5701862.html（深入研究虚幻4反射系统实现原理（一））、https://www.cnblogs.com/ghl_carmack/p/5716512.html（深入研究虚幻4反射系统实现原理（二））、https://www.cnblogs.com/ghl_carmack/p/5746921.html（深入研究虚幻4反射系统实现原理（三））

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

# Niagara

参考：https://zhuanlan.zhihu.com/p/138088668（UE4-Niagara基础解析）

## Dynamic Material Parameter

在particle update中添加Dynamic Material Parameter指定粒子的属性

材质中使用Dynamic Parameter将Niagara中的动态属性值传入材质

参考：https://blog.csdn.net/hechao3225/article/details/113532401（UE4 Niagara粒子系统基础笔记）

# 大世界

## 大世界分区（worldpartition）

参考：

https://blog.csdn.net/oFengtingwano/article/details/130871958（虚幻引擎(UE5)-大世界分区WorldPartition教程(一)）、https://cuifeng.blog.csdn.net/article/details/131432827（虚幻引擎(UE5)-大世界分区WorldPartition教程(二)）、https://cuifeng.blog.csdn.net/article/details/131433627（虚幻引擎(UE5)-大世界分区WorldPartition教程(三)）、https://cuifeng.blog.csdn.net/article/details/131433981（虚幻引擎(UE5)-大世界分区WorldPartition教程(四)）

https://www.zhihu.com/people/huang-chao-81-47/posts（World Partition浅析系列）、https://www.zhihu.com/people/kbmwooder/posts（UE5 World Partition 世界分区系列）、https://www.zhihu.com/people/yan-fei-can-yue-tian-7（UE5 WorldPartition系列）

https://blog.csdn.net/u013412391/article/details/120254269（简单尝试UE5的WorldPartition）、https://zhuanlan.zhihu.com/p/466243266（UE5 World Partition实践）

## 创建大世界

可以使用world machine（https://www.world-machine.com/）

参考：https://www.bilibili.com/video/BV14Y411b7Gn（虚幻引擎 5！如何在15分钟内创建一个巨大的开放世界地图！）

# Level Instancing

参考：https://docs.unrealengine.com/5.3/zh-CN/level-instancing-in-unreal-engine/

# MASS框架

参考：

https://zhuanlan.zhihu.com/p/441773595（UE5的ECS：MASS框架(一)）、https://zhuanlan.zhihu.com/p/446937133（UE5的ECS：MASS框架(二)）、https://zhuanlan.zhihu.com/p/477803528（UE5的ECS：MASS框架(三)）

https://www.bilibili.com/video/BV1nB4y1y7cX/（[技术演讲]在UE5中用Mass框架构建海量实体(官方字幕)）、https://www.bilibili.com/video/BV13D4y1v7xx/（[UOD2022]不Mass怎么Meta | Epic 大钊）

# 距离场（DistanceFields）

参考：

https://docs.unrealengine.com/4.26/zh-CN/BuildingWorlds/LightingAndShadows/MeshDistanceFields/（网格体距离场）

https://zhuanlan.zhihu.com/p/469205984（初探UE4 距离场系统（阴影篇））、https://zhuanlan.zhihu.com/p/29214784（【UnrealEngine4】距离场的使用技巧与应用）、https://www.zhihu.com/question/314433176?utm_id=0（有哪位大佬能和我讲讲距离场是什么东西嘛？）

https://zhuanlan.zhihu.com/p/492635939?utm_id=0（Games101 finalProject系列(一) 距离场与RayMarching）

https://zhuanlan.zhihu.com/p/487044802?utm_id=0（UE4 Signed Distance Fields：符号距离场（一））、https://zhuanlan.zhihu.com/p/492707962（UE4 Signed Distance Fields：符号距离场（二））

# DynamicMesh

参考：

https://www.bilibili.com/video/BV1iB4y1S75f/?spm_id_from=333.999.0.0（b站聆枫LingFeng视频）

https://zhuanlan.zhihu.com/p/494672575（Ue5 程序化生成： Dynamic Mesh初探）、https://zhuanlan.zhihu.com/p/497063218（UE5动态生成Mesh(GeneratedDynamicMeshActor)实践）

# PCG（程序化内容生成器）

参考：https://zhuanlan.zhihu.com/p/636291504（UE5中的PCG —— PCG in UE5）、https://zhuanlan.zhihu.com/p/638790748（UE5中的PCG进阶 —— Advanced PCG in UE5）

# Procedural Mesh 程序化模型

参考：https://zhuanlan.zhihu.com/p/346745928（UE4 Procedural Mesh 程序化模型）、https://blog.csdn.net/qq_31788759/article/details/104092692（【UE4 C++】由点面数据，批量绘制ProceduralMesh并转化为StaticMesh资产）

# Loading Screen（前端加载画面或视频，后端加载关卡）

参考：

https://zhuanlan.zhihu.com/p/608502007（虚幻杂记4 PreLoadScreen与LoadingScreen）、https://zhuanlan.zhihu.com/p/395615335（为UE4制作实时加载界面（蓝图向） —— Real-time Loading Screen for UE4（Blueprint））、https://zhuanlan.zhihu.com/p/372577094（UE的LoadingScreen加载界面及动态数据实现）

https://blog.csdn.net/sinat_27456831/article/details/49933285（虚幻4动态加载画面（后台加载关卡）的实现）

# 瞄准动画时的MeshSpace和LocalSpace

参考：https://zhuanlan.zhihu.com/p/33234659（浅谈MeshSpace和LocalSpace）

# 顶点动画

参考：

https://www.bilibili.com/video/BV13h411t7Zm/?vd_source=c3d9e4c3ef670596b3b0dddab637f86c（UE4 UE5 顶点动画）

https://blog.csdn.net/qq_29891697/article/details/133239833（UE5 官方顶点动画插件（AnimToTexture））、https://www.cnblogs.com/FZfangzheng/p/16573167.html（UE5城市样例中AnimToTexture插件的使用）

https://zhuanlan.zhihu.com/p/630381438（UE场景顶点动画效果实现）、https://blog.csdn.net/gzx88666/article/details/107714928（UE4-基于顶点偏移制作拉扯效果）、https://zhuanlan.zhihu.com/p/164352994（UE4-顶点动画）

# UMG

## EditorUtilityWidget

参考：https://blog.csdn.net/iaibeyond/article/details/117696541（简介UE4中的EditorUtilityWidget）、https://blog.csdn.net/u013412391/article/details/121844464（学习在虚幻引擎中使用UMG作为编辑器控件）、https://blog.csdn.net/Yoci98/article/details/120600645（UE使用EditorUtilityWidget完成简单的编辑器内工具）

# 蓝图中动态获取Uasset资源（GetAssetsXXX）

![image-20230830160629458](ue.assets/image-20230830160629458.png)

参考：https://blog.csdn.net/weixin_40821143/article/details/112981474（UE4 | BP | 使用蓝图获取Uasset资源）

# 多线程异步任务

参考：https://space.bilibili.com/92060300/video（【合集】UE4 C++进阶系列）、https://zhuanlan.zhihu.com/p/38881269（《Exploring in UE4》多线程机制详解[原理分析]）

# 渲染

## 渲染编程

参考：https://zhuanlan.zhihu.com/p/36675543（虚幻4渲染编程专题概述及目录★）

## G-buffer

参考：https://blog.csdn.net/weixin_44718797/article/details/125838661（延迟渲染的过程）

# module模块与plugin插件

## 创建自己的插件

参考：

https://blog.csdn.net/jxyb2012/article/details/88839224/（UE4插件研发 So Easy）、https://www.cnblogs.com/HHW-Development/p/16795797.html（UE4 UE5 虚幻引擎插件开发、创建、制作方法）

https://zhuanlan.zhihu.com/p/73068767（虚幻4笔记-插件创建、相关配置文件和加载启动源码分析）

https://zhuanlan.zhihu.com/p/484392550（UE4 基础必学系列：自定义插件）

https://www.jianshu.com/p/ec0ae889f417（Unreal插件开发入门）、https://www.jianshu.com/p/49684c1b6011（Unreal插件开发的一些技巧）

## 创建自己的模块

参考：https://blog.csdn.net/luofeixiongsix/article/details/81078232（[UE4C++程序]GameModule与Plugin）

# 编辑器界面

## c++美化属性展示

参考：https://zhuanlan.zhihu.com/p/606248942（虚幻杂记3 C++美化属性显示）

## 自定义编辑器界面

参考：

https://space.bilibili.com/92060300/video（【合集】UE4插件与Slate系列）

还有各种编辑器扩展，用到的时候再去翻阅网络资料

# 模型网站

- 动画

  mixamo

- 模型素材

  sketch fab、blender market、poly haven

# 虚幻商城资源包

horse animset（骑马）

animation movement 、rider、mount、通用飞行系统

power ik（ik系统）

oss在线子系统、steam oss、虚幻ds服务器系统（整合了epic开发经验）

game feature（知乎大钊）

动画蓝图模板（b站）

levelinstance（知乎Nero）

vdb体积云

摩卡数字（b站，他的网盘有非常多的资源）

# 推荐阅读

知乎：https://www.zhihu.com/people/a-gun-er-58（技术宅阿棍儿，写的东西很深入）、https://www.zhihu.com/people/SuperPandaGX（YivanLee，大佬，写的东西很深入）、https://www.zhihu.com/people/quabqi（quabqi，写得中肯详细易懂）

gitee：https://gitee.com/timetzhang/LECTURE.UnrealEngine（timetzhang，他整合了很多素材资源）

