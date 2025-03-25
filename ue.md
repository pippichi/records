# 安装全新虚幻引擎时的优化配置

Rider、IntelliJ：

- Rider内存不足

  参考：https://zhuanlan.zhihu.com/p/437617731（Rider For UE4 的内存不足问题）

- IntelliJ系列占用C盘空间

  参考：https://blog.csdn.net/weixin_44449518/article/details/103334235（解决 IntelliJ IDEA占用C盘过大空间问题）

- 使用rider调试ue引擎源代码

  ![image-20250308220110814](ue.assets/image-20250308220110814.png)

  参考：https://blog.csdn.net/weixin_45685193/article/details/135372792（如何使用rider调试UE引擎的源代码）

- rider添加插件代码索引

  ![image-20250308220037265](ue.assets/image-20250308220037265.png)

  参考：https://blog.csdn.net/weixin_45197377/article/details/136174783（Rider调试UE5源码时部分源文件无法识别问题）

UE★：

- UE编译速度慢

  参考：https://zhuanlan.zhihu.com/p/83190575（Unreal Engine大幅提升编译速度的技巧）

- UE缓存文件占用C盘空间

  参考：https://blog.csdn.net/weixin_44753042/article/details/123093832（【虚幻】清理缓存文件（C盘占用过大））


Epic优化：

- Epic保管库占用C盘空间

  参考：https://zhuanlan.zhihu.com/p/528351452（C盘爆满：UE（虚幻引擎）缓存，Epic保管库迁移）

# 打包

## 项目启动器

开发完成之后用于打包、发布游戏

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/using-the-project-launcher-in-unreal-engine

## 手游

### Iphone

参考：

https://www.bilibili.com/video/BV1Nm4y1t7p9（虚幻引擎在Windows下免费打包iOS应用）

### Android

参考：

https://www.bilibili.com/video/BV1FM4m1D7Ui（【UE5教程】虚幻引擎安卓打包教程—目前B站上最全面一次成功案例教程）

https://www.bilibili.com/video/BV1uu411K73Z（坑多多的UE5.2.1安卓成功打包全过程 您能撑到哪个步骤？何勇作坊录制 虚幻引擎 Android打包apk Unreal Engine）

## 打包后无法跳转地图

编辑器中可以跳转，打包后无法执行ServerTravel、ClientTravel、OpenLevel等，多半是地图没打包进去

搜索maps to include并配置地图

![image-20250320101411514](ue.assets/image-20250320101411514.png)

参考：

https://zhuanlan.zhihu.com/p/628136103（【UE5】打包版本中 ServerTravel 函数不生效问题及解决方案）

# 模块依赖

PublicDependencyModuleNames和PrivateDependencyModuleNames有什么区别？

参考：

https://blog.csdn.net/maxiaosheng521/article/details/79174337（UE4 PublicDependencyModuleNames与PrivateDependencyModuleNames）

https://blog.csdn.net/u010087338/article/details/144711409（PublicDependencyModuleNames vs PrivateDependencyModuleNames）

# 反射系统标签

## UPARAM(ref)

b站聆枫LingFeng的使用案例：c++定义了一个蓝图中调用的函数，如果想要在蓝图节点中添加一个输入引脚（输入引脚其实就是以入参的方式传入函数），则函数指定引脚入参前要加上UPARAM(ref)

参考：https://blog.csdn.net/opk8848/article/details/104887704（ue4 关于ufunction 函数 参数 用引用 UPARAM(ref)）

## UMETA()

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/metadata-specifiers-in-unreal-engine

# 材质（Material）

参考：

非常清晰有用的教程，每个节点都有案例：https://www.bilibili.com/video/BV1LK4y177ME（Ben Cloward虚幻4材质课合集★）、https://zhuanlan.zhihu.com/p/573712342（UE4虚幻引擎材质节点以及蓝图的学习（一）★）、https://zhuanlan.zhihu.com/p/574455678（UE4虚幻引擎材质节点以及蓝图的学习（二）★）、https://zhuanlan.zhihu.com/p/575556518（UE4虚幻引擎材质节点以及蓝图的学习（三）★）

https://blog.csdn.net/choa12345/article/details/120075714（[浅析]UE4材质基础总结）

各种材质表达式参考：https://docs.unrealengine.com/5.2/zh-CN/unreal-engine-material-expressions-reference/（本文列出了[材质编辑器](https://docs.unrealengine.com/5.2/zh-CN/unreal-engine-material-editor-user-guide)中所有可用 **材质表达式** 节点的参考页面）

## Material Domain（材质域）

参考：

https://zhuanlan.zhihu.com/p/101666840（虚化4：材质域（Material Domain））

将材质给UI使用的前提，参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-15.Ghost Globe第7分20秒）

## ObjectPivotPoint

ObjectPivotPoint代表物体模型空间原点在世界空间的位置

## RotateAboutAxis与FixRotateAboutAxisNormals

参考：https://zhuanlan.zhihu.com/p/381326628（RotateAboutAxis和FixRotateAboutAxisNormals）、https://zhuanlan.zhihu.com/p/394785452（在 UE4 中使用顶点着色器旋转网格）

## DitherTemporalAA

扰乱、抗锯齿

参考：

https://blog.csdn.net/xingyali/article/details/82215662（风格化材质制作）



可以用来做淡入淡出，参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-4.Fade Actor第5分35秒）

### Responsive AA

情景：niagara中使用了该材质时，出现了锯齿状失真，此时可以勾选上材质节点中的Responsive AA以改善该情况

![image-20241223171456996](ue.assets/image-20241223171456996.png)

注意：该材质混合模式一定得设置成是透明的（Translucent）



## 对比度

CheapContrast

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/image-adjustment-material-functions-in-unreal-engine（图像调整材质函数）

## 去饱和度

Desaturation

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/color-material-expressions-in-unreal-engine（颜色材质表达式）

## ScreenAlignedPixelToPixelUVs（屏幕空间平铺uv）

参考：

https://www.bilibili.com/video/BV1Pk4y1R7MN（[中文直播]第19期 | 后期材质基础(上) | Epic 贾越-第48分30秒）

## ScaleUVsByCenter

参考：

https://www.bilibili.com/video/BV14a4y147hy（[中文直播] 第20期 | 后处理材质基础(下) | Epic贾越-第1小时42分）

## ViewProperty

用于获取视口中各种属性，例如视口大小、屏幕分辨率等

![image-20241223132109674](ue.assets\image-20241223132109674.png)

## AlignMeshToTheCamera（让材质始终面向摄像机）

参考：

https://blog.csdn.net/weixin_38527697/article/details/117989491（ue4 材质始终面向摄像机）

## AttachMeshToTheCamera（让材质贴合到摄像机）

参考：

https://www.bilibili.com/video/BV14a4y147hy（[中文直播] 第20期 | 后处理材质基础(下) | Epic贾越-第1小时55分）

## Gradient Material Functions（渐变材质函数）

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/gradient-material-functions-in-unreal-engine

## GeneratedBand（生成的色带）

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/procedurals-material-functions-in-unreal-engine

## 创建动态材质实例

```c++
UMaterialInstanceDynamic* DynamicMaterialInstace = UMaterialInstanceDynamic::Create(Material1, this);
```

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-4. Checkpoints第11分40秒）

## StaticMeshMorphTarget静态网格体变形目标节点

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/static-mesh-morph-targets-in-unreal-engine

## 案例

### 材质融合过渡

参考：

https://zhuanlan.zhihu.com/p/348060852（UE4丨材质融合过渡的应用案例）

### 环形进度条

参考：

https://blog.csdn.net/qq_41410054/article/details/114584009（UE4 UI实现环形进度条效果）

# Enhanced Input增强输入

参考：

https://www.bilibili.com/video/BV1Tr4y1b7C6（UE4/UE5实战系列：增强输入系统（Enhanced Input System）★）

https://blog.csdn.net/u011254268/article/details/131434703（用人话讲！虚幻引擎 UE5 增强输入系统（蓝图篇））

https://blog.csdn.net/weixin_55901138/article/details/130639164（UE5学习笔记|增强输入系统EnhancedInput）

https://zhuanlan.zhihu.com/p/470949422（UE5 -- EnhancedInput(增强输入系统)）



# GameMode

gamemode仅存于服务器，不存在于客户端

![image-20250319235056640](ue.assets/image-20250319235056640.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-23_Tracking Incoming Players第40秒）

## PostLogin玩家加入游戏

```c++
virtual void PostLogin(APlayerController* NewPlayer);
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-23_Tracking Incoming Players第50秒）

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-23_Tracking Incoming Players第3分10秒）

## Logout玩家离开游戏

```c++
virtual void Logout(AController* Exiting);
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-23_Tracking Incoming Players第1分10秒）

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-23_Tracking Incoming Players第3分25秒）

## OnPostLogin事件

客户端连接时的回调事件

可用于管理连接玩家

参考：

https://blog.csdn.net/zhangxiao13627093203/article/details/118385657（UE4 中GameInstance、GameMode、GameState、PlayerState和PlayerController的关系）

# GameState

![image-20250319235045844](ue.assets/image-20250319235045844.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-23_Tracking Incoming Players第1分15秒）



GameState中可以访问到PlayerArray，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-23_Tracking Incoming Players第5分30秒）



# GameInstance

参考：

https://blog.csdn.net/Highning0007/article/details/123042719（UE4使用GameInstance设置全局变量(不同关卡、类之间数据传递)）

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-2. Setting the Default Player Start第2分20秒）



设置GameInstance

![image-20250312190913824](ue.assets/image-20250312190913824.png)

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-2. Setting the Default Player Start第9分40秒）



GameInstance中可以直接访问GameState，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-23_Tracking Incoming Players第4分50秒）

# PlayerState

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-23_Tracking Incoming Players第8分20秒）



PlayerState中可以获取玩家信息，提供了GetPlayerName、GetPlayerId等方法，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-23_Tracking Incoming Players第8分50秒）

# Delegate委托

参考：

https://zhuanlan.zhihu.com/p/575671003（UE4中的委托及实现原理）

https://blog.csdn.net/q244645787/article/details/129874760（UE4/5C++：Delegate（委托or代理？）的使用）

https://cloud.tencent.com/developer/article/1889577（UE4技术总结——委托）



接口中返回委托的引用：

```c++
virtual FOnASCRegistered& GetOnASCRegisteredDelegate() = 0; // 返回引用而不是复制
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-18. Electrocute Polish第2分05秒）



委托是否已经被绑定回调函数：

```c++
GetOnDeathDelegate().IsAlreadyBound(this, &ClassName::CallbackFunc);
```



委托绑定回调函数的另一种写法：

```c++
DECLARE_MULTICAST_DELEGATE_TwoParams(FOnCreateSessionComplete, FName, bool);
typedef FOnCreateSessionComplete::FDelegate FOnCreateSessionCompleteDelegate;

FOnCreateSessionCompleteDelegate CreateSessionCompleteDelegate = FOnCreateSessionCompleteDelegate::CreateUObject(this, &ThisClass::CallbackFunc);
```

![image-20250318151938984](ue.assets/image-20250318151938984.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-10_创建会话第9分30秒）



委托中如果有参数没有托管给虚幻引擎的，例如没有打UCLASS()或USTRUCT()这些标记的，则无法使用DYNAMIC委托，因为无法在蓝图 中使用，此时只能使用普通委托，或者把参数标记起来。简单来讲，跟蓝图不兼容的都只能用普通委托

![image-20250319225917517](ue.assets/image-20250319225917517.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-21_More Subsystem Delegates第2分30秒）

# 函数

## Make Rot from ...

参考：https://www.cnblogs.com/weixiaodezhoubian/p/12844425.html（UE4 Make Rot From......函数）

## 获取各种类型数值的最大最小值

以float类型举例：

```c++
float Max = TNumericLimits<float>::Max();
float Min = TNumericLimits<float>::Min();
float Lowest = TNumericLimits<float>::Lowest()
```

## Make Transform

![image-20250315233746356](ue.assets/image-20250315233746356.png)

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-13. Loot Tiers第24分50秒）

# 组件Component

## SceneCapture场景捕获

可以用来做远程监控、后视镜视角、传送门、Cubemap（HDR高动态范围贴图）等

参考：

https://www.gongyesheji.org/?p=1941&wd=&eqid=faa65b910005355e000000066476aafd（【unreal4】 虚幻引擎中获取虚拟摄像机图像并导入UI(UMG)实时显示（SceneCaptureComponent2D、CanvasRenderTarget2D））

https://blog.csdn.net/qq_39934403/article/details/121654665（Unreal Engine UE4虚幻引擎，生成Cubemap（HDR高动态范围贴图））、https://blog.csdn.net/qq_39934403/article/details/121656255（Unreal Engine UE4虚幻引擎，创建Sky天空球，球天材质，自制天空球（HDR高动态范围贴图））

# 后处理

## 后处理材质

参考：

https://www.bilibili.com/video/BV1Pk4y1R7MN（[中文直播]第19期 | 后期材质基础(上) | Epic 贾越）

https://www.bilibili.com/video/BV14a4y147hy（[中文直播] 第20期 | 后处理材质基础(下) | Epic贾越）

https://www.bilibili.com/video/BV1552UYDEhx（后期处理材质基础教程UE5.4-讲原理的材质基础教程）



设置后处理材质起效阶段：

![image-20241223111255651](ue.assets/image-20241223111255651.png)

Epic 贾越的教程中说一般选择Before Tonemapping以避免抗锯齿等导致的抖动问题，但上图中没有“Before Tonemapping”，那么可以选择Scene Color Before Bloom或Scene Color Before DOF，参考：

https://forums.unrealengine.com/t/5-4-removed-post-process-material-settings-before-tonemapping/1865552/19（5.4 Removed Post Process Material settings (Before Tonemapping)）

## 材质表达式SceneTexture

- SceneTexture:PostProcessInput0
- SceneTexture:CustomDepth
- SceneTexture:CustomStencil
- SceneTexture:WorldNormal
- ...

参考：

https://docs.unrealengine.com/5.2/zh-CN/post-process-materials-in-unreal-engine/（后期处理材质）、https://zhuanlan.zhihu.com/p/615915744（UE4技术杂谈——后处理 之 遮挡描边）、https://www.bilibili.com/video/BV1Az42197L9（c++中获取GBuffer进行边缘检测配合后处理体积实现描边）、https://www.bilibili.com/video/BV1ki421e7QA（UE5怎样使用后期处理体积给物体描边）

https://blog.csdn.net/ttm2d/article/details/115247252（Unreal Engine 4 使用HLSL自定义着色器（Custom Shaders）教程（上））、https://blog.csdn.net/ttm2d/article/details/115263517（Unreal Engine 4 使用HLSL自定义着色器（Custom Shaders）教程（下））

## 图像模糊

参考：

https://zhuanlan.zhihu.com/p/125744132（高品质后处理：十种图像模糊算法的总结与实现）

### 径向模糊

可以做出零：濡鸦之巫女那样的效果

参考：

https://blog.csdn.net/qq_42486920/article/details/126591803（UE5 描边、径向模糊）

https://www.bilibili.com/video/BV1Pk4y1R7MN（[中文直播]第19期 | 后期材质基础(上) | Epic 贾越-第58分50秒）

### SpiralBlur-SceneTexture

可用于制作毛玻璃效果

参考：

https://blog.csdn.net/goodriver1/article/details/121712281（UE4_如果快速做出毛玻璃效果）

## Custom Depth/Custom Stencil

参考：

https://blog.csdn.net/grayrail/article/details/131173457（在UE中使用Stencil功能）

https://www.bilibili.com/video/BV1hA411n7vZ（[技巧分享]使用自定义模板缓冲创建遮罩 | Creating masks with the Custom Stencil Buffer(官方字幕)）

### 不被遮挡描边，被遮挡不描边

可以用SceneDepth和CustomDepth来实现

参考：

https://www.bilibili.com/video/BV14a4y147hy（[中文直播] 第20期 | 后处理材质基础(下) | Epic贾越-第42分）

## DDX、DDY

邻边像素对比

参考：

https://www.bilibili.com/video/BV1Pk4y1R7MN（[中文直播]第19期 | 后期材质基础(上) | Epic 贾越-第1小时13分）

## 半透明后处理材质与Scene Color节点

![image-20241223191932250](ue.assets/image-20241223191932250.png)

用于获取场景中不透明的物体所渲染出来的结果，可以利用这个信息做出很好看的效果，比如配合后处理描边：

![image-20241223192657247](ue.assets/image-20241223192657247.png)

好处：

1、只需要渲染物体内不需要渲染整个场景，消耗少；

2、利用了透明的属性；



使用场景：

1、拾取物外可以罩一个这种后处理材质，用于单独处理；

2、与AttachMeshToTheCamera材质节点结合，将赋予了该后处理材质的物体附着到相机上，根据物体的形状还可以扣掉某些不需要后处理的像素点；



参考：

https://www.bilibili.com/video/BV14a4y147hy（[中文直播] 第20期 | 后处理材质基础(下) | Epic贾越-第1小时49分20秒，第1小时54分）

## 案例

参考：

https://www.bilibili.com/video/BV1YS4y1k7Pj（(中英字幕)虚幻引擎4.27！6种不同的后处理效果调试！）

# Data Registry

参考：

https://zhuanlan.zhihu.com/p/471631747（UE5 -- DataRegistry（数据注册表)）

https://www.bilibili.com/video/BV1qq4y1W7Ka（DataRegistry：一种统领全局的新数据配置工具）

# Gameplay Ability System（GAS）

参考：

https://blog.csdn.net/m0_38110586/category_11011758.html（虚幻四Gameplay Ability System入门1-12★）、https://blog.csdn.net/m0_38110586/article/details/137971936（UE GAS进阶-深入理解GE）、https://blog.csdn.net/m0_38110586/article/details/137972247（UE5-GAS插件UE5.3改动）

https://www.bilibili.com/video/BV1X5411V7jh（[中文直播]第31期｜GAS插件介绍（入门篇） | 伍德 大钊）、https://www.bilibili.com/video/BV1zD4y1X77M（[UnrealOpenDay2020]深入GAS架构设计 | EpicGames 大钊）、https://www.bilibili.com/video/BV1sG4y1o7MG（[UOD2022]基于GAS的运行时编辑器框架 | 深圳元象 王杰）

https://www.zhihu.com/people/a-gun-er-58（虚幻插件GAS分析系列★）、https://space.bilibili.com/92060300/video（UE4 GAS入门系列★）

https://zhuanlan.zhihu.com/p/486808688（【Unreal】虚幻GAS系统快速入门）

## 网络同步

参考：

https://zhuanlan.zhihu.com/p/418085845（虚幻插件GAS分析02-0 技能网络同步之激活）、https://zhuanlan.zhihu.com/p/419741087（虚幻插件GAS分析02-1 技能的网络同步之预测与结束）

### 复制模式

![image-20250114211748120](ue.assets/image-20250114211748120.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-7.Replication Mode第1分40秒）

## 虚幻5C++教程使用GAS制作RPG游戏第一部分

参考：

https://www.bilibili.com/video/BV1JD421E7yC（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（一））、https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二））、https://www.bilibili.com/video/BV1kK421e7nw（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三））

### Owner Actor和Avatar actor

![image-20250114212748871](ue.assets/image-20250114212748871.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-8.Init Ability Actor Info第1分40秒）

### Ability初始化钩子（PossessedBy、AcknowledgePossession、OnRep_PlayerState、BeginPlay）

![image-20250114213927310](ue.assets/image-20250114213927310.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-8.Init Ability Actor Info第4分30秒）

### Ability挂件的持有Actor需要设置其Owner为Controller（Pawn/PlayerState除外，这两个自动设置掉了）

![image-20250114220330778](ue.assets/image-20250114220330778.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-8.Init Ability Actor Info第21分）

### Gameplay Effects

![image-20250115230051749](ue.assets/image-20250115230051749.png)

![image-20250115230340826](ue.assets/image-20250115230340826.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-1.Gameplay Effects第4分）

#### Periodic Gameplay Effects

![image-20250117202935400](ue.assets/image-20250117202935400.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-5.Peroidic Gameplay Effects第3分）

#### Stacking

- Aggregate by Source

​	![image-20250117211102347](ue.assets/image-20250117211102347.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-6.Effect Stacking第2分20秒）



- Aggregate by Target

​	![image-20250117211248719](ue.assets/image-20250117211248719.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-6.Effect Stacking第4分50秒）

#### PreAttributeChange

![image-20250117232827432](ue.assets/image-20250117232827432.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-10.PreAttributeChange第10分30秒）

#### Modifiers

##### Attribute Based

![image-20250120212718225](ue.assets/image-20250120212718225.png)

Coefficient、Pre Multiply Additive Val、Post Multiply Additive Val

![image-20250120212733257](ue.assets/image-20250120212733257.png)

计算方法为：

![image-20250120212812001](ue.assets/image-20250120212812001.png)

![image-20250120213042118](ue.assets/image-20250120213042118.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-5.Modifier Coefficients第1分）

##### ModMagnitudeCalculation（自定义计算）

![image-20250120232220283](ue.assets/image-20250120232220283.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-10.Modifier Magnitude Calculations）

##### Set by Caller

![image-20250204145802854](ue.assets/image-20250204145802854.png)

在c++中注册SetByCaller：

```c++
UAbilitySystemBlueprintLibrary::AssignTagSetByCallerMagnitude(SpecHandle, GameplayTags.Damage, ScaledDamage); // SetByCaller本质上是一个键值对，key为GameplayTags.Damage，value为ScaledDamage
```

他的作用之一：不需要在GE中硬编码数值，而是可以将其交由Gameplay Ability处理（GA中我们可以通过Curve Table处理数值）

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-3.Set By Caller Magnitude）

#### Execution Calculation

比ModMagnitudeCalculation（MMC）更强大，但也有不少限制

![image-20250205171015876](ue.assets/image-20250205171015876.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-11.Execution Calculations）

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-13.ExecCalcs - Capturing Attributes）



他还可以捕获到Set By Caller的数值：

![image-20250205171233899](ue.assets/image-20250205171233899.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-11.Execution Calculations第4分30秒）

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-14.Implementing Block Chance）

#### FGameplayEffectContextHandle

在GE触发时，可以通过FGameplayEffectContextHandle传递很多有用的信息

![image-20250206001926223](ue.assets/image-20250206001926223.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-1.The Gameplay Effect Context第26分）

##### 扩展FGameplayEffectContext

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-2.Custom Gameplay Effect Context）

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-7.Using a Custom Effect Context）

关于如何将自定义的FGameplayEffectContext注入UE，参考`Gameplay Ability System（GAS）-虚幻5C++教程使用GAS制作RPG游戏-Ability System Globals`章节

#### Instancing Policy

当设置为Instanced Per Actor时，需要注意局部变量初始化问题

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-2.Impact Effects第13分50秒）

#### FGameplayEffectQuery

![image-20250218112205054](ue.assets/image-20250218112205054.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-11. Cooldown Async Task第27分30秒）

#### 如何在代码中动态创建GE

```c++
UGameplayEffect* Effect = NewObject<UGameplayEffect>(GetTransientPackage(), FName(DebuffName)); // GetTransientPackage()表示临时资源，这里我们没有GE的Class，所以采用临时资源的方式创建GE
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-8. Dynamic Gameplay Effects第2分05秒）

##### 如何在代码中动态添加GE的InheritableOwnedTag

```c++
Effect->InheritableOwnedTagsContainer.AddTag(DebuffTag);
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-8. Dynamic Gameplay Effects第7分45秒）

##### 如何在代码中动态添加Modifiers

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-8. Dynamic Gameplay Effects第9分30秒）

##### 如何在代码中动态创建FGameplayEffectSpec

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-8. Dynamic Gameplay Effects第13分42秒）

### Gameplay Tags

![image-20250118163044269](ue.assets/image-20250118163044269.png)

![image-20250118163324719](ue.assets/image-20250118163324719.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-1.Gameplay Tags）

#### 在c++中初始化Tag

1、利用了自定义的UAssetManager（如何创建并使用自定义UAssetManager见“UAssetManager”章节）；

2、在UAssetManager的StartInitialLoading()初始化方法中初始化Tags：

![image-20250122184200511](ue.assets/image-20250122184200511.png)

3、之后即可创建完成：

![image-20250122184349275](ue.assets/image-20250122184349275.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-12.Aura Asset Manager）

#### 挂载/卸载标签

使用AddLooseGameplayTags()和RemoveLooseGameplayTags()挂载卸载标签

如果需要网络复制则使用AddReplicatedLooseGameplayTags()和RemoveReplicatedLooseGameplayTags()

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-20. Stun第44分15秒）

### 第一属性和第二属性的设计

![image-20250120214344565](ue.assets/image-20250120214344565.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-6.Secondary Attributes）

#### 属性面板UI设计

![image-20250121141553766](ue.assets/image-20250121141553766.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-1.Attribute Menu-Game Plan）

#### UI数值更新的MVC架构设计

![image-20250122154649389](ue.assets/image-20250122154649389.png)

![image-20250122154959274](ue.assets/image-20250122154959274.png)

![image-20250122155158552](ue.assets/image-20250122155158552.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-10.Plan for displaying Attribute Data第3分10秒）

#### 比MVC架构更新的MVVM架构设计

![image-20250311151605544](ue.assets/image-20250311151605544.png)

MVVM由数据驱动，数据和模型直接建立联系，数据变了模型就变

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-8. MVVM）

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/umg-viewmodel



启用MVVM插件

![image-20250311154517108](ue.assets/image-20250311154517108.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-10. View Model Class第7分40秒）



创建mvvm c++类（MVVMViewModelBase）

![image-20250311154650630](ue.assets/image-20250311154650630.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-10. View Model Class第8分20秒）

##### Viewmodels窗口

必须要在Designer下才可以看到这个选项

![image-20250311162739481](ue.assets/image-20250311162739481.png)

![image-20250311163209148](ue.assets/image-20250311163209148.png)

![image-20250311192840721](ue.assets/image-20250311192840721.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-11. Constructing a View Model第11分35秒）

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-12. Load Slot View Model第17分）

##### Field Notifies

这是MVVM架构的精髓

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-15. Binding Variables to ViewModels第40秒）

##### UE_MVVM_SET_PROPERTY_VALUE

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-15. Binding Variables to ViewModels第2分30秒）

##### UE_MVVM_BROADCAST_FIELD_VALUE_CHANGED

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/umg-viewmodel

##### UI属性绑定

法一：

![image-20250312003519237](ue.assets/image-20250312003519237.png)

法二：

![image-20250312003557707](ue.assets/image-20250312003557707.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-15. Binding Variables to ViewModels第4分50秒）

###### ToText(Integer)

![image-20250313162243577](ue.assets/image-20250313162243577.png)

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-9. Showing Player Level in Load Screen第4分10秒）

### Gameplay Abilities

![image-20250124101646489](ue.assets/image-20250124101646489.png)

![image-20250124101914364](ue.assets/image-20250124101914364.png)

![image-20250124101937218](ue.assets/image-20250124101937218.png)

![image-20250124102025361](ue.assets/image-20250124102025361.png)

#### Tags

![image-20250124112417533](ue.assets/image-20250124112417533.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-3.Settings on Gameplay Abilities第1分25秒）

##### Block Abilities with Tag

场景：该技能发动时阻止其他含有特定标签的技能

![image-20250306101028050](ue.assets/image-20250306101028050.png)

##### Activation Owned Tags

场景：当该技能发动时，将特定标签应用到角色身上

![image-20250306142022743](ue.assets/image-20250306142022743.png)

##### Activation Blocked Tags

场景：角色身上含有特定标签时阻止该技能发动

![image-20250306141224982](ue.assets/image-20250306141224982.png)

#### Instancing Policy

![image-20250124132345896](ue.assets/image-20250124132345896.png)

![image-20250124132359134](ue.assets/image-20250124132359134.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-3.Settings on Gameplay Abilities第9分15秒）

#### Net Execution Policy

![image-20250124132837037](ue.assets/image-20250124132837037.png)

![image-20250124132924521](ue.assets/image-20250124132924521.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-3.Settings on Gameplay Abilities第12分10秒）

#### 不推荐使用的配置项

![image-20250124135842744](ue.assets/image-20250124135842744.png)

注意：GA不在Simulated Proxies中运作，可以用GE和GC代替

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-3.Settings on Gameplay Abilities第14分55秒）

#### 将GA绑定到Enhanced Input上

![image-20250124140429661](ue.assets/image-20250124140429661.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-4.Input Config Data Asset第20秒）

#### GameplayAbilitySpec

##### FScopedAbilityListLock

一种遍历Ability时的方法域锁

```c++
/** Used to stop us from removing abilities from an ability system component while we're iterating through the abilities */
```

参考：

GameplayAbilitySpec.h

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-7. For Each Ability Delegate第4分10秒）

##### FScopedTargetListLock

```c++
/** Used to stop us from canceling or ending an ability while we're iterating through its gameplay targets */
```

参考：

GameplayAbilitySpec.h

##### 发生网络复制时的回调函数

```c++
UPROPERTY(ReplicatedUsing = OnRep_ActivateAbilities, BlueprintReadOnly, Transient, Category = "Abilities")
FGameplayAbilitySpecContainer ActivatableAbilities;
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-8. Binding Widget Events to the Ability Info Delegate第13分25秒）

##### AbilitySpecInputPressed和AbilitySpecInputReleased

![image-20250124163318770](ue.assets/image-20250124163318770.png)

方法里面会去调用Ability的InputPressed方法：

![image-20250124163847984](ue.assets/image-20250124163847984.png)

该方法在GameplayAbility中，可通过重写实现自定义逻辑：

![image-20250124164029735](ue.assets/image-20250124164029735.png)

使用案例：

![image-20250124164413647](ue.assets/image-20250124164413647.png)

它可以用于告知GA是否被按下，不管GA是否已经被Active，只要按下了就告知

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-7.Activating Abilities第19分）

##### 获取FPredictionKey

```c++
// UE5.5以前可以这么写：
// AbilitySpec.ActivationInfo.GetActivationPredictionKey()

// 经过自己实验测试发现新版本需要这样写：
AbilitySpec.GetPrimaryInstance()->GetCurrentActivationInfo().GetActivationPredictionKey(); 
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-5. Invoke Replicated Event第10分50秒）

#### 网络同步

需要借助FGameplayAbilityTargetData类来实现网络同步，服务端会有AbilityTargetDataMap来维护网络数据

![image-20250127115542777](ue.assets/image-20250127115542777.png)

![image-20250127115843681](ue.assets/image-20250127115843681.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-5.Target Data第2分55秒）

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-6.Send Mouse Cursor Data）

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-7.Receiving Target Data）

#### CheckAbilityCost、CheckAbilityCooldown

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-18. Arcane Shards Cost and Cooldown第11分15秒）

#### CommitAbility、CommitAbilityCooldown、CommitAbilityCost

CommitAbility会同时执行CommitAbilityCooldown、CommitAbilityCost

CommitAbility失败后整个Ability都会被取消，包括CommitAbility前面的执行节点也会跟没执行过一样！

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. Gameplay Ability Cost第4分10秒）

#### 使用MarkAbilitySpecDirty强制对GA进行网络复制

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-15. Update Ability Statuses第11分30秒）



#### 如何获取GA的CostGameplayEffect以及CooldownGameplayEffect

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-26. Cost and Cooldown in Spell Description第5分45秒）

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-26. Cost and Cooldown in Spell Description第20分）

#### CancelAbility

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-6. Aura Beam Spell第9分10秒）

#### EndAbility

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-2. Aura Passive Ability第5分20秒）

### Gameplay Tasks

![image-20250124101805290](ue.assets/image-20250124101805290.png)

#### 网络同步

如何正确处理Client和Server的数据同步？参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-6.Send Mouse Cursor Data）

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-7.Receiving Target Data）

### Click/Touch To Move

![image-20250125091005457](ue.assets/image-20250125091005457.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-8.Click To Move）

### NavigationSystem

在使用`UNavigationSystemV1::FindPathToActorSynchronously()`方法时，出现无法解析符号的报错，头文件也加上了，后来发现要添加NavigationSystem模块，参考：

https://blog.csdn.net/qq_42673921/article/details/89339295（`UE4 UNavigationSystemV1::FindPathToActorSynchronously`）

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-10.Setting Up Auto Running第8分15秒）



如何解决Client端导航无反应问题？答：需要开启项目设置中的Allow Client Side Navigation（但是！这并不是最省资源的方式，还有一种方式是去Server端请求导航数据）

![image-20250125111804193](ue.assets/image-20250125111804193.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-11.Implementing Auto Running第6分20秒）

### Prediction预测

利用FScopedPredictionWindow、ScopedPredictionKey完成Server域预测与Client域先执行

利用ShouldBroadcastAbilityTaskDelegates()方法判断AbilityTask当前是否允许触发广播事件

![image-20250127164846809](ue.assets/image-20250127164846809.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-6.Send Mouse Cursor Data）

![image-20250131093452037](ue.assets/image-20250131093452037.png)

![image-20250131093611460](ue.assets/image-20250131093611460.png)

![image-20250131094047334](ue.assets/image-20250131094047334.png)

![image-20250131094308205](ue.assets/image-20250131094308205.png)

![image-20250131094850841](ue.assets/image-20250131094850841.png)

![image-20250131095356814](ue.assets/image-20250131095356814.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-8.Prediction in GAS）

更多信息需要进一步参考引擎中的GameplayPrediction.h类

### Montage蒙太奇

#### Montage中可以设置特定动作时的人物朝向

此为新版本UE中用于解决根骨骼动画的方案

需要开启Root Motion

![image-20250131221114216](ue.assets/image-20250131221114216.png)

需要开启Motion Warping插件

![image-20250131221258272](ue.assets/image-20250131221258272.png)

在BP_Character中使用该插件

![image-20250131221533026](ue.assets/image-20250131221533026.png)

在Montage中设置Motion Warping

![image-20250131222316179](ue.assets/image-20250131222316179.png)

![image-20250131222401855](ue.assets/image-20250131222401855.png)

在BP_Character中新建事件用于设置上图的“FacingTarget”

![image-20250131223125096](ue.assets/image-20250131223125096.png)

最终在攻击生成火球时已知攻击点的坐标，此时调用上图事件即可完成转身

![image-20250131223341310](ue.assets/image-20250131223341310.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-10.Motion Warping第40秒）

上述操作还可以进一步优化，可以将SetFacingTarget的逻辑写到Interface接口中，参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-10.Motion Warping第9分15秒）

#### Montage中设置Blend Settings混合过渡

![image-20250303192154957](ue.assets/image-20250303192154957.png)

### RPG多种Character Classes的属性设计架构

![image-20250203003958089](ue.assets/image-20250203003958089.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-1.RPG Character Classes）

### 将CurveTable导出/导入至CSV/JSON

导出：

![image-20250204092131254](ue.assets/image-20250204092131254.png)

导入：

法一：

![image-20250204092154544](ue.assets/image-20250204092154544.png)

法二：

![image-20250204092210531](ue.assets/image-20250204092210531.png)

法三：

![image-20250204094329685](ue.assets/image-20250204094329685.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-4.Curve Tables - CSV and JSON第14分）

### Meta Attributes

![image-20250204142022865](ue.assets/image-20250204142022865.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-1.Meta Attributes）

### FScalableFloat

```c++
UPROPERTY(EditDefaultsOnly, BlueprintReadOnly, Category = "Damage")
FScalableFloat Damage;
```

可以设置Curve Table以及倍率（倍率为左侧的数字输入框），效果：

![image-20250204151104023](ue.assets/image-20250204151104023.png)

### Net Serialize序列化

![image-20250206132111490](ue.assets/image-20250206132111490.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-3.NetSerialize）

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-4.Implementing Net Serialize）

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-5.Struct Ops Type Traits）

### Ability System Globals

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-6.Aura Ability System Globals）



设置自定义Ability System Globals的法一在上述视频中

法二：

![image-20250206141542349](ue.assets/image-20250206141542349.png)

### AI

![image-20250207144418585](ue.assets/image-20250207144418585.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-1.Enemy AI Setup）

#### Behavior Tree

##### Blackboard Keys

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-4.Blackboard Keys第2分25秒）

##### Behavior Tree Decorators

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-7.Behavior Tree Decorators第1分20秒）

##### Behavior Tree Task

在蓝图中重写Receive Execute AI方法时，不要忘记最后要执行一下Finish Execute

![image-20250207231404087](ue.assets/image-20250207231404087.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-8.Attack Behavior Tree Task第6分50秒）

###### 在Behavior Tree Task中触发GA

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-1.Melee Attack Ability第19分10秒）

#### 使AI动作更加自然

##### AI移动时更加自然的转向

把Pawn的Use Controller Rotation Yaw关闭：

![image-20250207213910728](ue.assets/image-20250207213910728.png)

把Character Movement的Use Controller Desired Rotation打开：

![image-20250207213829664](ue.assets/image-20250207213829664.png)

或者直接在c++中设置：

![image-20250207214313284](ue.assets/image-20250207214313284.png)



参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-6.AI and Effect Actors第50秒）

##### AI Idle和run的切换更加自然

![image-20250207233645739](ue.assets/image-20250207233645739.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-9.Find New Location Around Target第40秒）

##### 因为EndAbility导致Montage只播放了一半的问题

把PlayMontageAndWait中的Stop when Ability Ends关闭即可

![image-20250209204055808](ue.assets/image-20250209204055808.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-12.Ghoul Attack Montages第7分45秒）

##### 弹弓手在攻击时弹弓的动画蓝图设置

举一反三，其他的比如射箭等动画蓝图也可以这样制作

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-8.Slingshot Animation Blueprint）

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-9.Slingshot Attack Montage）

##### 普通的Anim Notify

![image-20250210140920274](ue.assets/image-20250210140920274.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-9.Slingshot Attack Montage第3分）

#### 如何在Nav导航网格内获取随即半径位置

因为AI只能在Nav导航网格中移动，所以想让AI移动到某个随即半径位置时得使用这个方法Get Random Location in Navigable Radius：

![image-20250207234847451](ue.assets/image-20250207234847451.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-9.Find New Location Around Target第8分55秒）

#### EQS（Environment Query System）

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-11.Environment Queries）

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-12.EQS Tests）

##### EQS的使用场景

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-10.Environment Query System）

##### EnvQueryContext

用于筛选EQS点位

![image-20250208110248824](ue.assets/image-20250208110248824.png)

在蓝图中可通过继承EnvQueryContext_BlueprintBase实现自定义EnvQueryContext

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-12.EQS Tests）

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-13.Distance Test）



如果是Trace检测，则还应该开启遮挡物的Collision中的Visibility为Block

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-14.Using EQS Queries in Behavior Trees第8分钟）

#### AI之间互不阻挡

![image-20250210091905879](ue.assets/image-20250210091905879.png)

![image-20250210094832923](ue.assets/image-20250210094832923.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-13.Melee Polish第18分20秒）

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/using-avoidance-with-the-navigation-system-in-unreal-engine

#### SpawnDefaultController

生成AI时生成默认Controller

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-11. Spawn Volumes第10分10秒）

### 用于分类的Tag

![image-20250207205821103](ue.assets/image-20250207205821103.png)

然后在c++代码中：

```c++
APawn* Pawn = xxx;
Pawn->ActorHasTag(FName("Player"));
```

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-4.Blackboard Keys第11分25秒）



#### `UGameplayStatics::GetAllActorsWithTag`

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-4.Blackboard Keys第14分25秒）

#### `UGameplayStatics::GetAllActorsOfClassWithTag`

![image-20250207210818148](ue.assets/image-20250207210818148.png)



### 重叠检测

可以参考GameplayStatics.cpp中的ApplyRadialDamageWithFalloff方法

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-5.Get Live Players Within Radius第1分50秒）

## 虚幻5C++教程使用GAS制作RPG游戏第二部分

### 动画序列

#### 预览时显示周围环境、地板等信息

![image-20250210160500555](ue.assets/image-20250210160500555.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1.Goblin Spear - Sound Notifies第1分10秒）

### 动画蓝图

#### IsFalling()

用于判断角色是否处于滞空状态

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-13. Knockback第29分35秒）

#### 状态机

##### Add State Alias

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-13. Knockback第30分30秒）

##### Blend Settings调整状态过渡

![image-20250303191738769](ue.assets/image-20250303191738769.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-7. Electrocute Montage第11分50秒）

#### Animation Node Functions

参考：

https://dev.epicgames.com/documentation/en-us/unreal-engine/animation-blueprint-node-functions-in-unreal-engine

### Gameplay Cue

适用于处理AI的动作触发的声音、特效等及其网络复制

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-3.Melee Impact Gameplay Cue）

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-4.Montage and Socket Tags第11分30秒）

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. GameplayCue Notify Paths第30秒）

https://blog.csdn.net/xcinkey/article/details/127042540（4.8 游戏反馈 - Gameplay Cues）

#### ExecuteGameplayCueOnOwner

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. GameplayCue Notify Paths第3分10秒）

#### ExecuteGameplayCueWithParamsOnOwner

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. Gameplay Cue Notify Burst第4分）

#### 设置Gameplay Cue Manager的搜索路径

![image-20250304103110267](ue.assets/image-20250304103110267.png)

![image-20250304103149394](ue.assets/image-20250304103149394.png)

![image-20250304103239028](ue.assets/image-20250304103239028.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. GameplayCue Notify Paths第7分）

#### CheckForTooManyRPCs

Gameplay Cue的网络复制

![image-20250304095800299](ue.assets/image-20250304095800299.png)

debug后可以看到网络发送数量限制：

![image-20250304095959051](ue.assets/image-20250304095959051.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. GameplayCue Notify Paths第9分）



上图有个`net.MaxRPCPerNetUpdate`，它是ConsoleVariable，可以通过配置文件设置（调试的时候也可以直接在控制台设置），设置方法参考：`Gameplay Ability System（GAS）-虚幻5C++教程使用GAS制作RPG游戏第二部分-ConsoleVariable`章节

#### 大量gameplaycue网络复制时的优化方法

gameplaycue网络复制是有数量上线的，在`Gameplay Ability System（GAS）-虚幻5C++教程使用GAS制作RPG游戏第二部分-Gameplay Cue-CheckForTooManyRPCs`章节中我们将其设置为10。

那么当数量超过10甚至远超10的时候，此时也不说数量限制了，网络复制消耗的资源会非常多，应该避免使用gameplaycue网络复制了。

解决方案：执行本地GameplayCue

![image-20250310145151891](ue.assets/image-20250310145151891.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. Execute Local Gameplay Cues第50秒）

#### GameplayCueNotifyActor

GameplayCueNotifyStatic每次执行是不会实例化的：

![image-20250304102711363](ue.assets/image-20250304102711363.png)

![image-20250304101743269](ue.assets/image-20250304101743269.png)

GameplayCueNotifyActor会实例化：

![image-20250304102332364](ue.assets/image-20250304102332364.png)

属性设置：

![image-20250304103442455](ue.assets/image-20250304103442455.png)

重写方法中的OnActive（执行一次）和WhileActive（执行多次）：

![image-20250304103745572](ue.assets/image-20250304103745572.png)

新版本UE5.5变这样了：

![image-20250304105719755](ue.assets/image-20250304105719755.png)

![image-20250304110318663](ue.assets/image-20250304110318663.png)

以上注释为看了c++源代码之后发现的

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-10. Gameplay Cue Notify Actor第1分）

##### Add GameplayCue On Actor

![image-20250304104459047](ue.assets/image-20250304104459047.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-10. Gameplay Cue Notify Actor第6分）

##### Remove GameplayCue From Owner

![image-20250304105953656](ue.assets/image-20250304105953656.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-10. Gameplay Cue Notify Actor第7分55秒）

#### GC在客户端无法正常关闭的解决方案

让GC在只服务器端关闭即可

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-21. Stun Niagara System）

#### GameplayCueNotify_Burst

适用于一次性特效

![image-20250308110638279](ue.assets/image-20250308110638279.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. Gameplay Cue Notify Burst第1分）

##### Burst Effects

![image-20250308111410634](ue.assets/image-20250308111410634.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. Gameplay Cue Notify Burst第5分20秒）

### Niagara

#### Emit

##### Spawn Per Unit

在空间中每隔一定距离就会生成

![image-20250211210149983](ue.assets/image-20250211210149983.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-10.Ghoul - Swipe Trail第30秒）

#### 在动画通知中使用Niagara

![image-20250211210503363](ue.assets/image-20250211210503363.png)

![image-20250211210742355](ue.assets/image-20250211210742355.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-10.Ghoul - Swipe Trail第1分15秒）

#### Niagara朝向用户摄像机

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-14. Level Up Niagara System第7分50秒）

#### SpawnSystemAttached

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-10. Gameplay Cue Notify Actor第10分30秒）

#### 设置用户变量

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-10. Gameplay Cue Notify Actor第15分20秒）

### Update Redirector References

有时候资源迁移时会发生这样的问题：

![image-20250212144211142](ue.assets/image-20250212144211142.png)

或者资源诺不过去的问题

解决方法：

![image-20250212143723387](ue.assets/image-20250212143723387.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-14.Demon - Sound Notifies第4分10秒）

### RotateAngleAxis向量旋转

```c++
const FVector LeftOfSpread = Forward.RotateAngleAxis(45.f, FVector::UpVector);
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-16.Shaman Summon Locations第7分50秒）

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-13. Knockback第15分15秒）

### Vector Up

![image-20250315232855010](ue.assets/image-20250315232855010.png)

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-13. Loot Tiers第19分20秒）

### 静态资源编组

分组之后多个静态资源可以作为一个整体进行transform

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-17.Async Spawn Times第8分35秒）

### 动态生成的AI原地不动的原因

原因是动态生成的AI没有被分配Controller，所以需要手动分配一个默认的Controller给他们：

![image-20250213131939143](ue.assets/image-20250213131939143.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-19.Select Minion Class at Random第4分15秒）

### Find Look at Rotation

![image-20250213133133802](ue.assets/image-20250213133133802.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-20.Minion Summon Montage第4分）

### 炮弹刚发射出去就爆炸了的原因排查

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-22.Elementalist Behavior Tree第2分）

### UPROPERTY

#### meta参数

##### AllowPrivateAccess参数

当变量在private域中，又想在蓝图中读取的话，就需要加AllowPrivateAccess="true"：

```c++
private:
	UPROPERTY(VisibleAnywhere, BlueprintReadOnly, meta = (AllowPrivateAccess="true"))
	TObjectPtr<USphereComponent> Sphere;
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-22.Elementalist Behavior Tree第5分30秒）

#### FieldNotify、Setter、Getter

```c++
UPROPERTY(EditAnywhere, BlueprintReadWrite, FieldNotify, Setter, Getter);
FString PlayerName;
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-15. Binding Variables to ViewModels第1分30秒）

### UFUNCTION

#### meta参数

##### BlueprintInternalUseOnly参数

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-11. Cooldown Async Task第8分50秒）

##### DisplayName

节点展示名称

##### HidePin

隐藏引脚

##### DefaultToSelf

直接给节点注入self，场景：某些BlueprintPure函数不想手动传入self就可以使用这个特性

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-10. Constructing the Spell Menu Widget Controller第18分30秒）

#### BlueprintNativeEvent

接口中使用他而非virtual的好处，参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-11. Level Up Interface Function第2分20秒）



接口中使用他时不能在接口中实现它，因为虚幻的反射代码中已经定义它了，再写一遍就是重定义，参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-11. Level Up Interface Function第14分35秒）

#### CustomThunk

參考：https://www.cnblogs.com/baustein/p/15240785.html（UE4 CustomThunk笔记）

#### BlueprintPure的缺点

涉及到循环遍历时每次遍历都会重新执行一遍，比较昂贵。由于这个特性会产生一些莫名其妙的现象，解决方法就是把变量在循环前先提取出来cache住，后续使用cache值即可

![image-20250307225223936](ue.assets/image-20250307225223936.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-7. Point Collection第40分50秒）

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. Gameplay Cue Notify Burst第7分50秒）

#### 在蓝图中将普通函数转pure

![image-20250310123204338](ue.assets/image-20250310123204338.png)

### UCLASS

#### meta参数

##### ExposedAsyncProxy参数

```c++
UCLASS(BlueprintType, meta = (ExposedAsyncProxy = "AsyncTask"))
class GASGAME250107_API UWaitCooldownChange : public UBlueprintAsyncActionBase
{
	GENERATED_BODY()
    // ...
}
```

![image-20250218131536895](ue.assets/image-20250218131536895.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-12. Cooldown Tags in Ability Info第1分）

### UINTERFACE

#### BlueprintType

```c++
// 加上BlueprintType之后就可以在蓝图实现UnHighlightActor()方法了
UINTERFACE(MinimalAPI, BlueprintType)
class UHighlightInterface : public UInterface
{
	GENERATED_BODY()
};
class AURA_API IHighlightInterface
{
	GENERATED_BODY()
public:
    UFUNCTION(BlueprintNativeEvent)
	void UnHighlightActor();
};
```

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-4. Highlighting Non-Enemies第40秒）

### UENUM

#### UMETA

```c++
enum class ETurningInPlace : uint8
{
	ETIP_Left UMETA(DisplayName = "Turning Left") // DisplayName可以指定其在蓝图编辑器中展示的名称
};
```

### SpawnActor的On Destroyed监听事件

![image-20250213200647140](ue.assets/image-20250213200647140.png)

使用SpawnActor生成的Actor可以绑定其销毁事件

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-24.Decrementing Minion Count第40秒）

### 内存泄露导致FPS不断降低

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-26.Enemies Final Polish第2分30秒）

#### WaitGameplayEvent接收多次信号触发多次的问题

![image-20250213211332588](ue.assets/image-20250213211332588.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-26.Enemies Final Polish第6分50秒）

#### Niagara粒子未被销毁导致其生命周期无限长的问题

![image-20250213211629262](ue.assets/image-20250213211629262.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-26.Enemies Final Polish第9分）

### GE未捕获到Attributes导致控制台一直报错的问题

![image-20250213212226732](ue.assets/image-20250213212226732.png)

方法中把警告日志关闭即可：

![image-20250213212116630](ue.assets/image-20250213212116630.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-26.Enemies Final Polish第12分30秒）

### 灯光处理

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1.Level Lighting and Post Process第5分55秒）

#### SkyLight

##### Intensity Scale

![image-20250213215350344](ue.assets/image-20250213215350344.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1.Level Lighting and Post Process第10分40秒）

### PostProcessVolume

#### Exposure

![image-20250213223020509](ue.assets/image-20250213223020509.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1.Level Lighting and Post Process第9分55秒）

#### Temperature

![image-20250213222850276](ue.assets/image-20250213222850276.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1.Level Lighting and Post Process第21分35秒）

#### Global色调

![image-20250213223246140](ue.assets/image-20250213223246140.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1.Level Lighting and Post Process第22分45秒）

#### Shadows阴影

![image-20250213223618253](ue.assets/image-20250213223618253.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1.Level Lighting and Post Process第24分30秒）

#### Midtones中间色调

![image-20250213223820391](ue.assets/image-20250213223820391.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1.Level Lighting and Post Process第25分15秒）

#### Film Grain

![image-20250213224037444](ue.assets/image-20250213224037444.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1.Level Lighting and Post Process第26分17秒）

### 处理静态资源碰撞

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1.Level Lighting and Post Process第17分）

#### Collision Complexity

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/simple-versus-complex-collision-in-unreal-engine

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1.Level Lighting and Post Process第17分55秒）



本教程中做了一个效果，当角色被遮挡时，静态资源需要消失以避免角色被遮挡。但是出现了一个问题：当角色微微被遮挡时，静态资源消失了一下又马上出现了。这个问题的原因是静态资源的Collision Complexity被设置为“Use Complex Collision As Simple”：

![image-20250304145237928](ue.assets/image-20250304145237928.png)

需要将其设置为“Project Default”：

![image-20250304145319410](ue.assets/image-20250304145319410.png)

并给静态资源手动添加一个Box Collision：

![image-20250304145356369](ue.assets/image-20250304145356369.png)

问题就解决了！

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-12. Target Trace Channel第7分30秒）



### 设置画面质量

![image-20250213224333180](ue.assets/image-20250213224333180.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1.Level Lighting and Post Process第27分35秒）

### TEXTURE STREAMING POOL OVER xxx MiB BUDGET问题

![image-20250213232340187](ue.assets/image-20250213232340187.png)

需要压缩纹理来解决该问题

![image-20250213232540557](ue.assets/image-20250213232540557.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-2.Texture Streaming Pool Over Budget第9分15秒）



另一种方式是通过修改TEXTURE BUDGET LIMIT：

![image-20250213233955144](ue.assets/image-20250213233955144.png)

搜索rendersettings：

![image-20250213234123123](ue.assets/image-20250213234123123.png)

有则修改，无则创建：

![image-20250213234553741](ue.assets/image-20250213234553741.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-2.Texture Streaming Pool Over Budget第16分55秒）



另外，UE5.5默认存在一个RendererSettings，不知道是不是更新了，不太清楚配置是往最新的这个地方写还是上面那个地方写：

![image-20250213234918012](ue.assets/image-20250213234918012.png)

亲测UE5.5该配置会出问题（250215再次验证又没问题了，挺奇怪的）：

![image-20250215132320086](ue.assets/image-20250215132320086.png)



### Edit Selection in Property Matrix批处理资产

![image-20250213232915003](ue.assets/image-20250213232915003.png)

![image-20250213233510570](ue.assets/image-20250213233510570.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-2.Texture Streaming Pool Over Budget第11分45秒）

### 过滤资产

![image-20250213233240055](ue.assets/image-20250213233240055.png)

可以跟Edit Selection in Property Matrix批处理资产连用

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-2.Texture Streaming Pool Over Budget第12分）

### 光源的Static、Stationary、Movable

![image-20250214112700852](ue.assets/image-20250214112700852.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-3.Flame Pillar Actor第3分55秒）

### Tick优化

#### BP中Tick的开关

![image-20250214113029871](ue.assets/image-20250214113029871.png)

#### BP中设置Tick频率

尽量不要用tick，实在不行要用时可以考虑降低tick频率

![image-20250306094413646](ue.assets/image-20250306094413646.png)

#### 用自循环方式替换Tick

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-3.Flame Pillar Actor第10分45秒）

#### 用Timer替换Tick

Timer可以自定义执行频率，所以可以做到比Tick消耗更少的资源

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-13. Showing Cooldown Time in the HUD第4分40秒）

### Timeline中Use Last KeyFrame是什么意思？

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/editing-timelines-in-unreal-engine

### 场景物体遮挡视野时，物体淡入淡出解决方案

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-4.Fade Actor）

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-5.Fading Out Obstructing Geometry）

#### 物体关闭阴影

淡入时，阴影看起来比较假，不如直接关掉

![image-20250214160059125](ue.assets/image-20250214160059125.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-5.Fading Out Obstructing Geometry第8分10秒）

### 日志

#### 创建自定义日志分类集

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-5.Ability Info Data Asset第9分44秒）

#### 函数名称占位符%hs以及`__FUNCTION__`

```c++
UE_LOG(LogAura, Error, TEXT("Failed to execute delegate in %hs"), __FUNCTION__);
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-7.For Each Ability Delegate第3分50秒）



### #pragma once解决重定义问题

![image-20250215164110774](ue.assets/image-20250215164110774.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-5.Ability Info Data Asset第19分10秒）

### ASC的Broadcast和WidgetController的绑定回调函数顺序无法确定的解决方案

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-6.Initialize Overlay Startup Abilities第7分45秒）

### BlueprintAsyncActionBase执行异步任务

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-11. Cooldown Async Task第4分）

### 通过ASC获取技能的冷却剩余时间

![image-20250218112006807](ue.assets/image-20250218112006807.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-11. Cooldown Async Task第27分10秒）

### UE内置的Modeling Mode

#### Merge静态资产合并

![image-20250218142650473](ue.assets/image-20250218142650473.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-14. Modeling Mode第6分15秒）

### Experience（XP）经验值

可以手动设置每一级所需经验值，也可以通过数学公式：

![image-20250218143553812](ue.assets/image-20250218143553812.png)

![image-20250218143805148](ue.assets/image-20250218143805148.png)

![image-20250218144047933](ue.assets/image-20250218144047933.png)

![image-20250218144322325](ue.assets/image-20250218144322325.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1. Experience and Leveling Up）



![image-20250218231557954](ue.assets/image-20250218231557954.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-5. Awarding XP Game Plan）

#### 用于监听XP的GA的优化以及制作

由于该GA只需要在server运行，因此有很多东西不需要开启

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-8. Passively Listening for Events第1分50秒）

### 技能天赋树设计思路

![image-20250221163015987](ue.assets/image-20250221163015987.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1. Spell Menu Design）

### Ability Status设计思路

![image-20250223132136526](ue.assets/image-20250223132136526.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-12. Ability Status and Type）

### 字符串

#### FString、FName和FText的区别

参考：

https://zhuanlan.zhihu.com/p/65661142（UE4基础-FString,FName和FText的区别）

https://blog.csdn.net/qq_52855744/article/details/135699763（UE学习笔记--如何区分FString、FName、FText，以及使用场景）

#### `FString::Printf`中“L”的使用方法

```c++
FString::Printf(TEXT("宽字符字符串：%s"), L"很长的一段字符串");
```

这里的L会被编译器识别为宽字符类型（const wchar_t*）

宽字符和 `wchar_t` 是C++标准的一部分

宽字符花费更多的存储空间，比标准字符类型 char存储更多的字符，通常用于支持国际化和多语言字符集（例如，中文、日文等）



参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-24. Spell Descriptions第2分）

#### RemoveFromStart字符串裁切

```c++
FString WorldName = World->GetMapName();
WorldName.RemoveFromStart(World->StreamingLevelsPrefix);
```

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-13. Saving World State第2分10秒）

### 关闭UI界面（销毁UI）时，别忘了解绑所有回调函数

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-33. Unbinding Delegates）

### AddWeakLambda()

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. Debuff Niagara Component第10分）

### AddImpulse()

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-12. Handling Death Impulse第3分）

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-12. Handling Death Impulse第7分15秒）

### 击退效果

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-13. Knockback）

### FVector的IsNearlyZero()

```c++
FVector1.IsNearlyZero(10.f);
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-13. Knockback第13分05秒）

### LaunchCharacter()

```c++
TargetCharacter->LaunchCharacter(KnockbackForce, true, true);
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-13. Knockback第13分50秒）

### GetSafeNormal()

```c++
(FVector1 - FVector2).GetSafeNormal()
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-13. Knockback第24分40秒）

### 判断模板类关系

#### 父子关系判断

```c++
template<typename GEComponentClass>
GEComponentClass& UGameplayEffect::AddComponent()
{
    // 1.
	static_assert( TIsDerivedFrom<GEComponentClass, UGameplayEffectComponent>::IsDerived, "GEComponentClass must be derived from UGameplayEffectComponent");
    // 2.
    if constexpr (TIsDerivedFrom<GEComponentClass, UGameplayEffectComponent>::IsDerived) { // constexpr是必须的，因为模板类型需要在编译器确定
        // ...
    }
	// ...
}

// TIsDerivedFrom源码如下：
/** Is type DerivedType inherited from BaseType. */
template<typename DerivedType, typename BaseType>
struct TIsDerivedFrom
{
	// Different size types so we can compare their sizes later.
	typedef char No[1];
	typedef char Yes[2];

	// Overloading Test() s.t. only calling it with something that is
	// a BaseType (or inherited from the BaseType) will return a Yes.
	static Yes& Test( BaseType* );
	static Yes& Test( const BaseType* );
	static No& Test( ... );

	// Makes a DerivedType ptr.
	static DerivedType* DerivedTypePtr(){ return nullptr ;}

	public:
	// Test the derived type pointer. If it inherits from BaseType, the Test( BaseType* ) 
	// will be chosen. If it does not, Test( ... ) will be chosen.
	static constexpr bool Value = sizeof(Test( DerivedTypePtr() )) == sizeof(Yes);

	static constexpr bool IsDerived = Value;
};
```

#### 同类型判断

```c++
template<typename RotatorOrVector>
TArray<RotatorOrVector> UAuraAbilitySystemLibrary::TEvenlyDirectors(const FVector& Forward, const FVector& Axis, float Spread, int32 NumDirectors)
{
    // 1.
    static_assert( std::is_same_v<RotatorOrVector, FVector>, "...");
    // 2.
    if constexpr (std::is_same_v<RotatorOrVector, FVector>) { // constexpr是必须的，因为模板类型需要在编译器确定
        // ...
    }
}
```

#### constexpr的重要性！

```c++
template<typename RotatorOrVector>
TArray<RotatorOrVector> UAuraAbilitySystemLibrary::TEvenlyDirectors(const FVector& Forward, const FVector& Axis, float Spread, int32 NumDirectors)
{
    if constexpr (std::is_same_v<RotatorOrVector, FVector>) { // constexpr是必须的，因为模板类型需要在编译器确定
        // ...
    }
}
```

### 巡航导弹Homing Projectile制作

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-3. Homing Projectiles）

### UProjectileMovementComponent

#### 成员变量HomingTargetComponent

看了源码，他还是一个虚指针指向的对象

如果要使用HomingTargetComponent，还需要把bIsHomingProjectile置为true，还可以设置弹道弧度HomingAccelerationMagnitude等参数

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-3. Homing Projectiles第5分15秒）

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-3. Homing Projectiles第9分15秒）

#### 自定义虚指针指向对象的垃圾回收处理

虚指针指向的对象，虽然不会影响该对象的垃圾回收，但有可能变为nullptr，并且该对象还需要自行处理其垃圾回收

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-3. Homing Projectiles第7分35秒）

#### 关闭ProjectileMovement

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-3. Aura Fire Ball第11分）

### GAS通用网络复制

#### 技能同步

参考：

https://zhuanlan.zhihu.com/p/159007942（【GameplayAbility深入解析】之技能同步基础）

#### 网络复制函数InvokeReplicatedEvent()

```c++
// UE5.5以前可以这么写：
// InvokeReplicatedEvent(EAbilityGenericReplicatedEvent::InputPressed, AbilitySpec.Handle, AbilitySpec.ActivationInfo.GetActivationPredictionKey());

// 经过自己实验测试发现新版本需要这样写：
InvokeReplicatedEvent(EAbilityGenericReplicatedEvent::InputPressed, AbilitySpec.Handle, AbilitySpec.GetPrimaryInstance()->GetCurrentActivationInfo().GetActivationPredictionKey()); 
// 并且使用WaitInputRelease或WaitInputPress函数的GA的Instancing Policy必须是Instanced Per Actor
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-5. Invoke Replicated Event第9分30秒）

#### 网络复制函数ServerSetReplicatedEvent()、ClientSetReplicatedEvent()

用于服务端客户端之间触发网络事件

参考：

https://zhuanlan.zhihu.com/p/159007942（【GameplayAbility深入解析】之技能同步基础）

#### 网络复制事件EAbilityGenericReplicatedEvent

```c++
/** These are generic, nonpayload carrying events that are replicated between the client and server */
UENUM()
namespace EAbilityGenericReplicatedEvent
{
	enum Type : int
	{	
		/** A generic confirmation to commit the ability */
		GenericConfirm = 0,
		/** A generic cancellation event. Not necessarily a canellation of the ability or targeting. Could be used to cancel out of a channelling portion of ability. */
		GenericCancel,
		/** Additional input presses of the ability (Press X to activate ability, press X again while it is active to do other things within the GameplayAbility's logic) */
		InputPressed,	
		/** Input release event of the ability */
		InputReleased,
		/** A generic event from the client */
		GenericSignalFromClient,
		/** A generic event from the server */
		GenericSignalFromServer,
		/** Custom events for game use */
		GameCustom1,
		GameCustom2,
		GameCustom3,
		GameCustom4,
		GameCustom5,
		GameCustom6,
		MAX
	};
}
```

参考：

https://zhuanlan.zhihu.com/p/159007942（【GameplayAbility深入解析】之技能同步基础）

#### WaitInputPress与WaitInputRelease节点

![image-20250228153549348](ue.assets/image-20250228153549348.png)

需要搭配触发通用网络复制函数InvokeReplicatedEvent()、ServerSetReplicatedEvent()、ClientSetReplicatedEvent()等网络复制函数使用：

```c++
// UE5.5以前可以这么写：
// InvokeReplicatedEvent(EAbilityGenericReplicatedEvent::InputPressed, AbilitySpec.Handle, AbilitySpec.ActivationInfo.GetActivationPredictionKey());

// 经过自己实验测试发现新版本需要这样写：
InvokeReplicatedEvent(EAbilityGenericReplicatedEvent::InputPressed, AbilitySpec.Handle, AbilitySpec.GetPrimaryInstance()->GetCurrentActivationInfo().GetActivationPredictionKey()); 
// 并且使用WaitInputRelease或WaitInputPress函数的GA的Instancing Policy必须是Instanced Per Actor
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-5. Invoke Replicated Event第6分35秒）

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-5. Invoke Replicated Event第9分30秒）

#### WaitNetSync节点

需要搭配InvokeReplicatedEvent()、ServerSetReplicatedEvent()、ClientSetReplicatedEvent()等网络复制函数使用

参考：

https://zhuanlan.zhihu.com/p/159008831（【GameplayAbility深入解析】之WaitNetSync节点原理）

### GAS直接判断是否有Tag

![image-20250303194153324](ue.assets/image-20250303194153324.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-8. Player Block Tags第6分20秒）

### ConsoleVariable

![image-20250304100358202](ue.assets/image-20250304100358202.png)

![image-20250304100314211](ue.assets/image-20250304100314211.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. GameplayCue Notify Paths第9分45秒）

### Fade In/Fade Out

淡入淡出，让效果显得不突兀

![image-20250304142510333](ue.assets/image-20250304142510333.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-11. Electrocute Looping Sound第4分45秒）

### 射线检测

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-13. First Trace Target第5分35秒）

#### ETraceTypeQuery是什么

`UKismetSystemLibrary::SphereTraceSingle()`中有个参数是`ETraceTypeQuery::TraceTypeQuery1`

查看`UKismetSystemLibrary::SphereTraceSingle()`的源码发现，里面调用了：

```c++
ECollisionChannel CollisionChannel = UEngineTypes::ConvertToCollisionChannel(TraceChannel);
```

意思是TraceChannel可以转换为ECollisionChannel

#### 使用预设的碰撞文件Profile进行射线检测

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-7. Point Collection第20分15秒）

### 由于目标已死亡导致炮弹停滞的解决方案

可以检测相邻tick之间炮弹的位置，如果位置不变则直接爆炸

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-19. Explode Dem FireBoltz第2分45秒）

### 炮弹及其Movement需要设置为Replicate

```c++
void AAuraProjectile::BeginPlay()
{
	// ...
	SetReplicates(true);
	SetReplicateMovement(true); // 炮弹的移动需要网络复制
	// ...
}
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-21. Stun Niagara System第4分45秒）

### 持续伤害时的动画优化

持续伤害时，使用某个特定动画代替每次受伤产生的动画，可以让画面不那么鬼畜，并且声音不会重复

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-22. Shock Loop Animations）

### UDecalComponent贴花

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1. Magic Circle第5分50秒）

#### 贴花移动时变模糊问题的解决方案

降低采样质量

![image-20250307145037065](ue.assets/image-20250307145037065.png)

关闭动态模糊

![image-20250307145155141](ue.assets/image-20250307145155141.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-6. Anti Aliasing and Moving Decals）

### 子组件三维变换

![image-20250307133035914](ue.assets/image-20250307133035914.png)

可以使用子组件的AddLocalRotation等函数

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-2. Spawning Magic Circles第9分35秒）

### UKismetMathLibrary

#### MakeRotFromZ

从向量Vector解算出旋转量Rotator

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-7. Point Collection第22分45秒）

#### MakeRotFromX

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-45_Leaning and Strafing第4分20秒）

#### ComposeRotators

 ```c++
 UKismetMathLibrary::ComposeRotators(Rotation1, Rotation2);
 ```

叠加旋转

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-14. Loot Effects第9分30秒）

#### NormalizedDeltaRotator相对旋转量

场景：键盘移动角色的同时鼠标旋转对角色产生的相对旋转量，可用于BlendSpace

```c++
FRotator AimRotation = Character->GetBaseAimRotation();
FRotator MovementRotation = UKismetMathLibrary::MakeRotFromX(Character->GetVelocity());
FRotator Offset = UKismetMathLibrary::NormalizedDeltaRotator(MovementRotation, AimRotation); // 相对旋转量
float YawOffset = Offset.Yaw;
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-45_Leaning and Strafing第13分30秒）

### Billboard组件的妙用

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-7. Point Collection第26分05秒）

### 随机位置方案

- 程序化随机

  参考Shaman生成Demon

- 手动随机

  参考GA_ArcaneShards生成PointCollection

### ApplyRadialDamageWithFalloff径向伤害

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-11. Radial Damage Parameters）

### Actor

#### TakeDamage

ApplyRadialDamageWithFalloff中有调用到TakeDamage

通过重写该方法可以实现自定义伤害计算

![image-20250309154107512](ue.assets/image-20250309154107512.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-13. Radial Damage with Falloff第6分35秒）

#### SetOwner、GetOwner

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-7. FireBall Explosive Damage第13分35秒）



SetOwner是默认网络复制的，这在一些地方会有妙用，比如网络优化中GameplayCue的本地执行，参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. Execute Local Gameplay Cues第6分30秒）

#### TActorIterator

继承自TActorIteratorBase，用于遍历UWorld中某类型的所有实例

```c++
for (TActorIterator<AActor> It(World); It; ++It) {}
```

FActorIterator

用于遍历UWorld中所有AActor的实例

```c++
for (FActorIterator It(World); It; ++It) {}
```

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-13. Saving World State第9分35秒）

#### 获取Actor的唯一标识名称

```c++
Actor->GetFName()
```

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-13. Saving World State第12分）

#### FActorSpawnParameters

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-11. Spawn Volumes第6分35秒）

斯坦福教程USAction_ProjectileAttack

#### GetActorForwardVector

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-13. Loot Tiers第19分15秒）

#### SetActorScale3D

![image-20250316145623559](ue.assets/image-20250316145623559.png)

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-15. Loot Drop Curve第9分40秒）

### 方向向量别忘了归一化

```c++
DirectionVector.Normalize();
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-16. Knockback Force and Death Impulse Overrides第8分45秒）

### Save Game

![image-20250310180434534](ue.assets/image-20250310180434534.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1. Saving Progress）

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-14. Save Game Object第1分10秒）

#### 存储Actor

存储Actor及其标注了UPROPERTY(SaveGame)属性字段的方法

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-12. Data Structures for Saving Data第4分50秒）

#### UDeveloperSettings

可用于管理配置变量

参考：

斯坦福教程USSaveGameSettings

#### UGameInstanceSubsystem

可用于创建子系统，例如SaveGameSubsystem

参考：

斯坦福教程USSaveGameSubsystem

https://blog.csdn.net/Motarookie/article/details/140874558（UE5中的UGameInstanceSubsystem）

#### 获取地图名称

```c++
void AAuraGameModeBase::SaveWorldState(UWorld* World) const
{
    FString WorldName = World->GetMapName();
    // 需要去掉World->StreamingLevelsPrefix这个前缀
    // World->StreamingLevelsPrefix的意思：Prefix we used to rename streaming levels, non empty in PIE and standalone preview
    WorldName.RemoveFromStart(World->StreamingLevelsPrefix); 
}
```

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-13. Saving World State第1分40秒）

#### FMemoryWriter

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-13. Saving World State第14分05秒）

#### FObjectAndNameAsStringProxyArchive

```c++
FMemoryWriter MemoryWriter(Bytes);

FObjectAndNameAsStringProxyArchive Archive(MemoryWriter, true);
// Find only vatiables with UPROPERTY(SaveGame)
Archive.ArIsSaveGame = true;

Actor->Serialize(Archive);
```

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-13. Saving World State第16分45秒）

#### FMemoryReader

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-14. Loading World State第7分50秒）

### Open Level

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-3. Play and Quit Buttons第10分15秒）

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-12. Player Death第2分25秒）

### Travel To Map

```c++
UFUNCTION(BlueprintCallable, meta=(WorldContext="WorldContextObject", AdvancedDisplay = "2", DisplayName = "Open Level (by Object Reference)"), Category="Game")
static ENGINE_API void OpenLevelBySoftObjectPtr(const UObject* WorldContextObject, const TSoftObjectPtr<UWorld> Level, bool bAbsolute = true, FString Options = FString(TEXT("")));
```

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-23. Traveling to the Saved Map第3分30秒）

## 虚幻5C++教程使用GAS制作RPG游戏第三部分

### Player Start

#### Player Start Tag

![image-20250312181434194](ue.assets/image-20250312181434194.png)

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-1. Choosing the Player Start第1分30秒）

#### ChoosePlayerStart

![image-20250312181934810](ue.assets/image-20250312181934810.png)

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-1. Choosing the Player Start第2分10秒）

#### 继承PlayerStart，制作CheckPoints

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-4. Checkpoints第1分）

### End键

End键可以快速让物体贴附到地面

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-4. Checkpoints第18分20秒）

### Mesh

#### MarkRenderStateDirty强制更新Mesh

```
GetMesh()->MarkRenderStateDirty();
```

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-5. Set Move-To Location第6分30秒）

### Target Point

![image-20250315213236888](ue.assets/image-20250315213236888.png)

![image-20250315213209808](ue.assets/image-20250315213209808.png)

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-11. Spawn Volumes第2分45秒）

### UBoxComponent

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-11. Spawn Volumes第12分10秒）

### FTimerHandle、FTimerDelegate

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-12. Player Death第9分55秒）

### Camera

#### DetachFromComponent

```c++
CameraComp->DetachFromComponent(FDetachmentTransformRules::KeepWorldTransform); // 相机与父级解绑，并保持世界变换不动
```

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-12. Player Death第11分55秒）

### Do Once

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-16. Pickup Sounds第1分50秒）

# UI

## Image组件

Draw as：

![image-20250121150417638](ue.assets/image-20250121150417638.png)

当背景图是边框时，可以选择Draw as为Border，该选项时可以很方便调整边框Margin：

![image-20250121150611398](ue.assets/image-20250121150611398.png)

而如果Draw as为image，则无法调整边框margin

此外，Draw as为Border时，调整画布size时边框的拉伸不会太严重

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-1.Attribute Menu-Framed Value第8分钟）



## Named Slot组件（父级UI组件预留插槽）

![image-20250121155556157](ue.assets/image-20250121155556157.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-3.Attribute Menu-Text Value Row第5分40秒）



## Wrap Box组件

![image-20250121213703500](ue.assets/image-20250121213703500.png)

相当于一个辅助排版工具

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-5.Attribute Menu-Construction第2分50秒）

## Text组件

![image-20250121214455579](ue.assets/image-20250121214455579.png)

点击居中没反应？

将Fill Empty Space勾上即可：

![image-20250121214606451](ue.assets/image-20250121214606451.png)

### Rich Text Block

Rich Text Block还可以跟DataTable连用

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-23. Rich Text Blocks第1分30秒）

### EditableText

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-5. Enter Name Load Slot第1分）

### Auto Wrap Text自动换行

![image-20250224181544290](ue.assets/image-20250224181544290.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-24. Spell Descriptions第17分40秒）

### Line Height Percentage调整行间距

![image-20250312122044081](ue.assets/image-20250312122044081.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-19. Are You Sure Widget第11分15秒）

## LISTS组件

参考：

https://zhuanlan.zhihu.com/p/127184008（[UE4蓝图]UMG中新手必晕的ListView详解）

## Widget Switcher组件

可以通过Active Widget Index切换显示组件

![image-20250311131553380](ue.assets/image-20250311131553380.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-7. Load Menu第55秒）

## User Interface

### Space

![image-20250205161647062](ue.assets/image-20250205161647062.png)

有时候无法正确显示Widget，可以查看一下这里的设置是否准确，参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-10.Showing Damage Text第21分30秒）



## 播放动画时内容失真问题解决方案

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-10.Showing Damage Text第21分55秒）

## Wrap Box组件

水平对齐与垂直对齐

![image-20250221170338471](ue.assets/image-20250221170338471.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-3. Offensive Spell Tree第3分45秒）



垂直对齐妙用（同理水平对齐），参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-4. Passive Spell Tree第6分30秒）



## 根据视口大小以及视口比例调整控件位置

使用GetViewportSize、GetViewportScale和SetPositionInViewport实现

![image-20250222132852988](ue.assets/image-20250222132852988.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-8. Spell Menu Button第5分）

## Image在Scroll Box前面，导致滚动条无法点击

可以将Image的Behavior-Visibility设置为不可点击：

![image-20250224211032975](ue.assets/image-20250224211032975.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-26. Cost and Cooldown in Spell Description第31分50秒）

## Widget在编辑窗口和世界中展示不一致

试试勾选Draw at Desired Size

![image-20250310200115702](ue.assets/image-20250310200115702.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-2. Main Menu第28分25秒）

## 调整Widget形状

![image-20250310195752536](ue.assets/image-20250310195752536.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-2. Main Menu第29分10秒）

## 固定组件位置

有时候组件会被其他组件挤占空间导致显示到错误的位置，可以通过设置Size为Fill并调整相对对齐位置来解决这个问题：

![image-20250312153911493](ue.assets/image-20250312153911493.png)

但如果被挤的太厉害的话，这个方法也无能为力了，此时只能通过限制其他组件来解决

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-23. Traveling to the Saved Map第10分45秒）

## c++ UButton

```c++
UPROPERTY(meta = (BindWidget)) // meta = (BindWidget)可以将该c++属性与蓝图中的Button组件实例联系起来
UButton* HostButton; // 注意当想要将该属性与蓝图中的Button组件实例联系起来时，变量名一定要一致，如下图：
```

![image-20250319131746557](ue.assets/image-20250319131746557.png)

一旦在c++中获得了UButton的句柄，那么就可以在c++中做一些操作了，比如点击事件绑定等

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-18_Accessing our Subsystem第1分50秒）

# AI

## AI行为树源码详解

参考：https://zhuanlan.zhihu.com/p/368889019（【图解UE4源码】AI行为树系统 目录）、https://zhuanlan.zhihu.com/p/371623309（【图解UE4源码】 其三（〇）行为树系统执行任务的流程 概述）、https://zhuanlan.zhihu.com/p/139514376（[UE4] 浅析UE4-BehaviorTree的特性）、https://zhuanlan.zhihu.com/p/143298443（UE4行为树详解（持续更新，才怪））

## SmartObjects

参考：

https://dev.epicgames.com/documentation/en-us/unreal-engine/smart-objects-in-unreal-engine

https://zhuanlan.zhihu.com/p/458142070（UE5 SmartObjects（智能对象）插件）

https://blog.csdn.net/grayrail/article/details/136593620（UE5.2 SmartObject使用实践）



# 配置文件使用手册

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/configuration-files-in-unreal-engine



# ModularGamePlay、GameFeatures

参考：

https://zhuanlan.zhihu.com/p/599593994（UE5 ModularGamePlay相关理解）

https://www.bilibili.com/video/BV1s44y1y7kY（模块化游戏功能 Modular Game Features）



# 音效

## Quartz

参考：

https://docs.unrealengine.com/5.3/zh-CN/overview-of-quartz-in-unreal-engine/

https://www.bilibili.com/video/BV1K64y1x7b2（2021.7.23 有字幕 UE5系列之七 MetaSounds and Quartz）

## SoundWave

### 循环播放

![image-20250310225037750](ue.assets/image-20250310225037750.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-3. Play and Quit Buttons第1分10秒）

## MetaSounds

参考：

https://docs.unrealengine.com/5.3/zh-CN/metasounds-in-unreal-engine/

https://www.bilibili.com/video/BV1K64y1x7b2（2021.7.23 有字幕 UE5系列之七 MetaSounds and Quartz）

### 调低音量

使用场景：静步

![image-20250210161154384](ue.assets/image-20250210161154384.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-1.Goblin Spear - Sound Notifies第2分25秒）

### 让声音变得更低沉

![image-20250211203645591](ue.assets/image-20250211203645591.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9.Ghoul - Sound Notifies第1分20秒）

## orphaned sound问题

![image-20250312165918246](ue.assets/image-20250312165918246.png)

可以使用SpawnSound2D并将声音提取为变量代替PlaySound2D，防止声音被垃圾回收

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-9. Changed Needed for 5.3+第31分50秒）

# virtual texture

参考：https://zhuanlan.zhihu.com/p/138484024（浅谈Virtual Texture）



# Blender Rigify

参考：https://zhuanlan.zhihu.com/p/452380549（Blender Rigify使用基础指南 （含Rigify——游戏引擎工作流））



# RPG项目

参考：

- https://www.bilibili.com/video/BV1jt4y1S7A7/?p=282&spm_id_from=333.880.my_history.page.click&vd_source=c3d9e4c3ef670596b3b0dddab637f86c（UE4 最完整的开放世界系列教程【附工程】【收藏夹必备】【422P 持续更新】）
- https://www.bilibili.com/video/BV1tg411v7L7/?spm_id_from=333.880.my_history.page.click&vd_source=c3d9e4c3ef670596b3b0dddab637f86c（【教程】全网最完整 UE5 100% 蓝图开发Steam 多人孤岛生存游戏 双语字幕 现已更新到117（20230308)）



# c++ 构建蓝图多输入多输出引脚节点

参考：

https://www.cnblogs.com/tanfu/p/16634394.html（构建蓝图中包含多输入多输出引脚的节点）



## UPROPERTY中的meta参数中的ExposeOnSpawn

在c++中设置UPROPERTY：

```c++
UPROPERTY(BlueprintReadWrite, meta=(ExposeOnSpawn=true)) // ExposeOnSpawn=true意味着spawn时将该变量作为pin暴露出来
FGameplayEffectSpecHandle DamageEffectSpecHandle;
```

效果：

![image-20250201162129695](ue.assets/image-20250201162129695.png)



## c++函数入参中的UPARAM(ref)标记

用于解决c++函数入参中的非const参数在蓝图中为输出引脚的问题：

![image-20250206150007297](ue.assets/image-20250206150007297.png)

![image-20250206150026383](ue.assets/image-20250206150026383.png)

解决方案：入参前面加上UPARAM(ref)：

![image-20250206150242207](ue.assets/image-20250206150242207.png)

![image-20250206150257334](ue.assets/image-20250206150257334.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-7.Using a Custom Effect Context第13分）

# FlowMap

flowMap被广泛用于制作水体或者云的“流动效果”

参考：https://zhuanlan.zhihu.com/p/222500848（[UE4] FlowMap）、https://zhuanlan.zhihu.com/p/237638786（[UE4] FlowMap Painter Tool）



# ue网络复制

参考：

https://www.bilibili.com/video/BV1dT4y1N7de/（彻底掌握UE4网络）

https://zhuanlan.zhihu.com/p/593894970（关于UObject如何解决网络复制问题以及数组在Replicated网络复制的优化）

# 多人联机

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/online-subsystems-and-services-in-unreal-engine

## 在线服务

配合EOS（Epic Online Services）使用（意味着与epic强绑），EOS完全免费！不像在线子系统中steam、apply等可能会有限制或者收费

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/online-services-in-unreal-engine

## 在线子系统

与steam、apply、google等平台对接的抽象层

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/online-subsystem-in-unreal-engine

## C++多人射击游戏教程

### LAN方式联机

#### OpenLevel

在Options处可以设置参数

![image-20250317212552018](ue.assets/image-20250317212552018.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-4_测试多人游戏第7分18秒）



![image-20250317213729965](ue.assets/image-20250317213729965.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-5_局域网连接第4分30秒）

#### ExecuteConsoleCommand

![image-20250317212730122](ue.assets/image-20250317212730122.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-4_测试多人游戏第8分05秒）

### 局域网方式联机

#### ServerTravel

```c++
UWorld* World = GetWorld();
World->ServerTravel("/Game/..");
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-5_局域网连接第2分50秒）

#### ClientTravel

```c++
APlayerController* PlayerController = GetGameInstance()->GetFirstLocalPlayerController();
PlayerController->ClientTravel("Address", ETravelType::TRAVEL_Absolute);
```



参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-5_局域网连接第6分20秒）

#### 复制资源地址

![image-20250317213155220](ue.assets/image-20250317213155220.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-5_局域网连接第3分15秒）

#### /Game/路径引用前缀

Content及其之前的都可以用/Game/替代

![image-20250317213445617](ue.assets/image-20250317213445617.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-5_局域网连接第3分30秒）

### session生命周期

![image-20250318085709399](ue.assets/image-20250318085709399.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-7_在线会话控制第3分20秒）

### 联机流程

![image-20250318085824899](ue.assets/image-20250318085824899.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-7_在线会话控制第4分20秒）

### OnlineSubsystem

参考：

启用插件

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-8_配置Steam第1分15秒）

c++添加插件模块

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-8_配置Steam第2分40秒）

配置DefaultEngine.ini

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-8_配置Steam第3分50秒）

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-10_创建会话第31分）

c++操作OnlineSubsystem、IOnlineSessionPtr

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-9_访问线上服务第1分30秒）

### 创建session

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-10_创建会话第13分25秒）

#### FOnCreateSessionCompleteDelegate

![image-20250318131759752](ue.assets/image-20250318131759752.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-10_创建会话第3分）



c++中初始化该委托，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-10_创建会话第9分30秒）



将该委托添加到IOnlineSessionPtr中：

```c++
FDelegateHandle OnCreateSessionCompleteDelegateHandle = OnlineSessionPtr->AddOnCreateSessionCompleteDelegate_Handle(OnCreateSessionCompleteDelegate); // 返回值FDelegateHandle可以存储下来方便后续销毁
```

可以发现IOnlineSessionPtr的很多回调都需要以这种方式注入：

![image-20250318165639811](ue.assets/image-20250318165639811.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-10_创建会话第20分）

#### FOnlineSessionSettings

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-10_创建会话第15分30秒）

FOnlineSessionSettings属性讲解

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-10_创建会话第26分10秒）



必须要开启bUseLobbiesIfAvailable，否则Session创建失败：

```c++
TSharedPtr<FOnlineSessionSettings> SessionSettings = MakeShareable(new FOnlineSessionSettings());
// ...
// 必须要加下面这一行，否则无法创建session
SessionSettings->bUseLobbiesIfAvailable = true; // 支持 Lobbies Api，不开启可能无法找到 Session
```

参考：

https://juejin.cn/post/7142143845763907621（Unreal Engine 连接 Steam（Session 操作））

##### Set方法设置参数

```c++
SessionSettings->Set(FName("MatchType"), FString("FreeForAll"), EOnlineDataAdvertisementType::ViaOnlineServiceAndPing);
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-13_Joining the Session第4分）



在FOlineSessionSearch->SearchResults中获取Set的内容：

```c++
for (auto Result : SessionSearch->SearchResults) {

    // 获取 MatchType
    FString MatchType;
    Result.Session.SessionSettings.Get(FName("MatchType"), MatchType);
}
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-13_Joining the Session第6分10秒）

##### EOnlineDataAdvertisementType

![image-20250318214357452](ue.assets/image-20250318214357452.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-13_Joining the Session第5分15秒）

##### BuildUniqueId

```c++
TSharedPtr<FOnlineSessionSettings> LastSessionSettings;
// ...
LastSessionSettings->BuildUniqueId = 1;
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-23_Tracking Incoming Players第11分30秒）

#### 获取LocalPlayer

```c++
const ULocalPlayer* LocalPlayer = GetWorld()->GetFirstLocalPlayerFromController();
```

#### FUniqueNetId

```c++
// CreateSession其中一个重载方法的第一个参数就是FUniqueNetId
virtual bool CreateSession(const FUniqueNetId& HostingPlayerId, FName SessionName, const FOnlineSessionSettings& NewSessionSettings) = 0;
```

可以从LocalPlayer中获取：

```c++
LocalPlayer->GetPreferredUniqueNetId();
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-10_创建会话第18分）



### 查找session

#### FOnFindSessionsCompleteDelegate

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-11_设置加入游戏会话第2分）



将该委托添加到IOnlineSessionPtr中：

```c++
FDelegateHandle FindSessionsCompleteDelegateHandle = OnlineSessionPtr->AddOnFindSessionsCompleteDelegate_Handle(FindSessionsCompleteDelegate); // 返回值FDelegateHandle可以存储下来方便后续销毁
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-11_设置加入游戏会话第9分15秒）

#### FOnlineSessionSearch

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-11_设置加入游戏会话第4分50秒）

##### QuerySettings

```c++
SessionSearch->QuerySettings.Set(SEARCH_LOBBIES, true, EOnlineComparisonOp::Equals); // 只查询 lobbies 值为 true 的
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-11_设置加入游戏会话第13分55秒）

##### FOnlineSessionSearchResult

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-11_设置加入游戏会话第10分20秒）

#### TSharedPtr转Ref

```c++
TSharedPtr<FOnlineSessionSearch> SessionSearch;
SessionSearch.ToSharedRef();
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-11_设置加入游戏会话第8分15秒）

### 加入session

#### FOnJoinSessionCompleteDelegate

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-13_Joining the Session第8分35秒）



将该委托添加到IOnlineSessionPtr中：

```c++
FDelegateHandle CreateSessionCompleteDelegateHandle = OnlineSessionPtr->AddOnCreateSessionCompleteDelegate_Handle(CreateSessionCompleteDelegate); // 返回值FDelegateHandle可以存储下来方便后续销毁
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-13_Joining the Session第11分45秒）

#### EOnJoinSessionCompleteResult

![image-20250318220043701](ue.assets/image-20250318220043701.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-13_Joining the Session第9分30秒）

#### GetResolvedConnectString

连接成功后获取服务器地址

```c++
FString Address;
bool bSuccess = OnlineSessionPtr->GetResolvedConnectString(NAME_GameSession, Address);
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-13_Joining the Session第14分）

#### 从GameInstance获取LocalPlayerController

```c++
GetGameInstance()->GetFirstLocalPlayerController();
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-13_Joining the Session第16分）

### 开始session

#### FOnStartSessionCompleteDelegate

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-16_Session Interface Delegates第7分）

### 销毁session

#### FOnDestroySessionCompleteDelegate

销毁之后马上创建session会失败，应该等待销毁结束后再创建，此时就可以利用该Delegate

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-16_Session Interface Delegates第7分）

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-25_Polishing the Menu Subsystem第1分35秒）



### 清除会话过程中的Delegate

IOnlineSessionPtr中有很多清除Delegate的方法：

![image-20250319135956329](ue.assets/image-20250319135956329.png)

举个例子，清除AddOnCreateSessionCompleteDelegate_Handle所产生的Delegate：

```c++
IOnlineSessionPtr OnlineSessionPtr;
// ...
OnlineSessionPtr->ClearOnCreateSessionCompleteDelegate_Handle(OnCreateSessionCompleteDelegateHandle); // OnCreateSessionCompleteDelegateHandle为调用AddOnCreateSessionCompleteDelegate_Handle时返回的句柄
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-19_Create Session第11分15秒）

### 创建Plugin

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-14_Creating a Plugin第3分40秒）

#### uplugin配置文件

![image-20250319090908014](ue.assets/image-20250319090908014.png)

Type可以是Runtime也可以是Editor

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-14_Creating a Plugin第5分50秒）



配置文件中添加插件依赖：

![image-20250319093711585](ue.assets/image-20250319093711585.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-14_Creating a Plugin第6分20秒）



当使用UI时，需要添加UMG、Slate、SlateCore模块依赖

![image-20250319111550187](ue.assets/image-20250319111550187.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-17_The Menu Class第4分）

#### PublicDependencyModuleNames与PrivateDependencyModuleNames

![image-20250319094657892](ue.assets/image-20250319094657892.png)

PrivateDependencyModuleNames的意思是仅私有源文件可访问

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-14_Creating a Plugin第7分40秒）

#### 制作Subsystem

![image-20250319100613444](ue.assets/image-20250319100613444.png)

![image-20250319100202176](ue.assets/image-20250319100202176.png)

![image-20250319100400407](ue.assets/image-20250319100400407.png)

参考：

https://dev.epicgames.com/documentation/en-us/unreal-engine/programming-subsystems-in-unreal-engine

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-15_Creating our Own Subsystem第1分30秒）



可以通过GameInstance获取自定义的Subsystem

```c++
UMultiplayerSessionsSubsystem* MultiplayerSessionsSubsystem = GameInstance->GetSubsystem<UMultiplayerSessionsSubsystem>(); // UMultiplayerSessionsSubsystem是自定义的Subsystem
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-18_Accessing our Subsystem第13分）

#### 在插件中创建c++类

![image-20250319101348594](ue.assets/image-20250319101348594.png)

创建c++类时，右侧可以选择插件目录

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-15_Creating our Own Subsystem第5分10秒）

#### 编辑器中设置Show Plugin Content

![image-20250319102601578](ue.assets/image-20250319102601578.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-15_Creating our Own Subsystem第6分45秒）

#### UserWidget

FInputModeUIOnly中可以调用SetWidgetToFocus，可以传入TakeWidget()：

![image-20250319112223143](ue.assets/image-20250319112223143.png)

TakeWidget可以获取该Widget底层的SlateWidget（如果不存在则会创建一个）

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-17_The Menu Class第6分50秒）



FInputModeUIOnly中可以调用SetLockMouseToViewportBehavior，可以传入EMouseLockMode来控制鼠标行为：

![image-20250319113008758](ue.assets/image-20250319113008758.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-17_The Menu Class第7分10秒）

### 配置DefaultGame.ini

#### 设置MaxPlayers

![image-20250320110633057](ue.assets/image-20250320110633057.png)

这样可以有更多的玩家加入进来

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-23_Tracking Incoming Players第12分10秒）

### 配置DefaultEngine.ini

#### 设置多人射击网游的Server Net Tick Rate

多人射击网游对网络延迟要求很高，必要情况下需要调整该属性值

![image-20250322230334512](ue.assets/image-20250322230334512.png)

```c++
[/Script/OnlineSubsystemUtils.IpNetDriver]
NetServerMaxTickRate=60
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-56_Net Update Frequency第5分）

### 美术、音效资源

美术资源参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-28_Assets）

音效资源参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-59_Footstep and Jump Sounds第25秒）

### Mixamo以及动画重定向

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-29_Retargeting Animations）

#### 解决重定向导致的角色悬空问题

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-54_Turning in Place第23分40秒）

### GameFramework

![image-20250325145651554](ue.assets/image-20250325145651554.png)

![image-20250325145926310](ue.assets/image-20250325145926310.png)

![image-20250325150003629](ue.assets/image-20250325150003629.png)

![image-20250325150042521](ue.assets/image-20250325150042521.png)

Server的东西可以通过GAME STATE下发给Client

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-84_Game Framework）

### 动画资产、动画蓝图以及AnimInstance

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-33_Animation Blueprint）

斯坦福教程

#### TryGetPawnOwner

获取所有者Pawn

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-33_Animation Blueprint第5分25秒）

#### Time Remaining(ratio)

可以获取动画混合剩余比例

![image-20250320162136447](ue.assets/image-20250320162136447.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-33_Animation Blueprint第16分）

#### Blend Poses By Bool

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-41_Equipped Animation Pose第7分40秒）

#### 从已有动画资产中创建新动画资产

1、复制资产；

2、改变新资产transform；

3、添加key；

![image-20250321112641421](ue.assets/image-20250321112641421.png)

4、烘焙并创建资产（烘焙后key将消失）；

![image-20250321111557763](ue.assets/image-20250321111557763.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-44_Running Blendspace第3分40秒）

#### 在键盘鼠标同时作用下如何正确计算角色旋转量

场景：BlendSpace中需要用到正确的角色旋转量

参考：

`Gameplay Ability System（GAS）-虚幻5C++教程使用GAS制作RPG游戏第二部分-UKismetMathLibrary-NormalizedDeltaRotator相对旋转量`章节

#### 插值计算角色倾斜度

场景：BlendSpace中需要用到正确的角色倾斜度

![image-20250321134025557](ue.assets/image-20250321134025557.png)

```c++
// Delta.Yaw表示角色在每一帧的旋转量，这个值是很小的，让他除以DeltaTime的意义在于放大这个值，并让值与DeltaTime成比例
const float Target = Delta.Yaw / DeltaTime;
// FInterpTo计算插值，让变化更丝滑
const float Interp = FMath::FInterpTo(Lean, Target, DeltaTime, 6.f); // Lean为BlendSpace中用到的自定义变量；6.f表示插值速度
// Clamp防止鼠标剧烈旋转导致数值爆炸
Lean = FMath::Clamp(Interp, -90.f, 90.f);
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-45_Leaning and Strafing第19分25秒）

#### 调整过渡值让角色动画更丝滑

直接调整动画序列中的Interpolation Time可以做到Yaw变化更丝滑，但也会导致一个新的问题，即向后移动时会发生-180到180的插值，看起来会很鬼畜，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-45_Leaning and Strafing第22分15秒）



解决方案：

1、不使用动画序列的Interpolation Time；

2、对于Yaw不使用一维数值进行插值；

3、在c++中用`FMath::RInterpTo()`方法对Yaw进行插值（该方法使用最短路径插值，它其实是三维数值插值，因此不会出现-180到180这种剧烈的插值变化。将其想象成一个球体即可理解）；

![image-20250321142638017](ue.assets/image-20250321142638017.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-45_Leaning and Strafing第25分30秒）

#### 跑、跳动画状态机

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-46_Idle and Jumps）



Automatic Rule Based on Sequence Player in State状态自动过渡

![image-20250321144231379](ue.assets/image-20250321144231379.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-46_Idle and Jumps第4分20秒）



调整JumpZVelocity与GravityScale

![image-20250321144651455](ue.assets/image-20250321144651455.png)

![image-20250321144926744](ue.assets/image-20250321144926744.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-46_Idle and Jumps第7分30秒）



处理45度斜着跑时的动画，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-58_Rotating Running Animations第45秒）

#### AimOffset瞄准偏移

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-49_Aim Offsets）

##### BasePose基础姿势

AimOffset是动画叠加的，因此需要基础姿势

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-49_Aim Offsets第11分45秒）

##### Additive Anim叠加动画

除了基础姿势，其他动画都需要转为叠加动画

1、先转成Mesh Space

![image-20250321155811907](ue.assets/image-20250321155811907.png)

2、选中基础姿势

![image-20250321155948244](ue.assets/image-20250321155948244.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-49_Aim Offsets第12分45秒）



叠加动画还可以应用在开火动画上（开火动画需要在基础动画上播放，使得开火方向等看起来是正确的）

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-61_Fire Montage第4分25秒）



##### 创建AimOffset

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-49_Aim Offsets第17分50秒）

##### 制作AimOffset的状态机

![image-20250321232952536](ue.assets/image-20250321232952536.png)

![image-20250321233041538](ue.assets/image-20250321233041538.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-52_Using our Aim Offsets）

##### 使用AimOffset

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-50_ Applying Aim Offsets第3分25秒）

#### 为State Machine创建Cached Pose

![image-20250321164910829](ue.assets/image-20250321164910829.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-50_ Applying Aim Offsets第1分20秒）



可以当封装方法使用，简化动画蓝图工作量。步骤为：创建状态机->创建状态->组装动画资源->为状态机创建Cached Pose

这样在其他地方就可以直接使用Cached Pose而不需要再重新组装动画资源了

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-54_Turning in Place第18分15秒）

#### Layered blend per bone

![image-20250321165145189](ue.assets/image-20250321165145189.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-50_ Applying Aim Offsets第1分45秒）

#### Idle时的丝滑动画（类似ALSV4）

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-50_ Applying Aim Offsets第6分）

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-52_Using our Aim Offsets第3分）

##### 解决Client端Pitch异常问题

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-51_Pitch in Multiplayer）



查看Pitch网络打包源码，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-51_Pitch in Multiplayer第6分40秒）



map映射数值

![image-20250321232420441](ue.assets/image-20250321232420441.png)

```c++
AO_Pitch = GetBaseAimRotation().Pitch;
if (!IsLocallyControlled() && AO_Pitch > 90.f) {
    // map pitch from [270, 360) to [-90, 0)
    FVector2D InRange(270.f, 360.f);
    FVector2D OutRange(-90.f, 0.f);
    AO_Pitch = FMath::GetMappedRangeValueClamped(InRange, OutRange, AO_Pitch);
}
```

##### 解决Idle下还在Aimming时动画异常问题

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-52_Using our Aim Offsets第3分40秒）

##### 解决Idle下鼠标旋转超90度时角色突然转向另一侧的问题

思路：鼠标转到一定角度时直接让角色也转过去，并配合动画完成效果

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-54_Turning in Place）



定义角色旋转状态枚举，方便后续指定角色该不该转向以及该转向哪

![image-20250322110156626](ue.assets/image-20250322110156626.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-54_Turning in Place第3分）

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-54_Turning in Place第5分40秒）



在c++中定义好角色转向状态

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-54_Turning in Place第7分45秒）



在动画蓝图中使用转向状态

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-54_Turning in Place第14分10秒）

###### Rotate Root Bone

根骨骼旋转

这里想解决鼠标旋转超90度之后角色自动转过去，作者的做法是：1、开启bUseControllerRotationYaw；2、开启之后角色会随着鼠标旋转，此时使用Rotate Root Bone抵消该旋转；3、在c++代码中记录角色转过去之前的鼠标旋转初始值并插值至0度，将该插值传递给Rotate Root Bone即可实现效果；

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-55_Rotate Root Bone第1分25秒）



设置Interp Result让旋转在网络模式下更顺畅（网络动画Tick更新有延迟，可能会导致抖动等鬼畜的效果）

![image-20250322225329118](ue.assets/image-20250322225329118.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-56_Net Update Frequency第1分10秒）



RotateRootBone的缺点：虽然是每帧更新，但在网络中是根据网络更新频率去更新的，所以其他客户端看起来就会抖动，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-81_Smooth Rotation for Proxies第1分）

通过日志分析原因以及解决方案参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-81_Smooth Rotation for Proxies第14分15秒）

##### 解决Simulated角色跑动时仍在播放转向动画（滑步）问题

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-81_Smooth Rotation for Proxies第26分25秒）

#### FABRIK IK

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-53_FABRIK IK）

为FABRIK IK创建状态机，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-53_FABRIK IK第13分35秒）

使用FABRIK节点及其骨骼配置

![image-20250322102648772](ue.assets/image-20250322102648772.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-53_FABRIK IK第14分35秒）

替换掉AimOffsets状态机，使用FABRIK IK状态机

![image-20250322102856887](ue.assets/image-20250322102856887.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-53_FABRIK IK第17分45秒）

将武器替换为其他武器，并为其创建相同的socket，即可复用FABRIK IK了！参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-53_FABRIK IK第22分05秒）

##### TransformToBoneSpace

```c++
// 思路：1、左手要做解算；2、左手目标应该是武器上的socket位置；3、武器上的socket位置应该是相对右手hand_r骨骼的骨骼空间位置；

LeftHandTransform = EquippedWeapon->GetWeaponMesh()->GetSocketTransform(FName("LeftHandSocket"), ERelativeTransformSpace::RTS_World);

FVector OutPosition;
FRotator OutRotation;
Character->GetMesh()->TransformToBoneSpace(FName("hand_r"), LeftHandTransform.GetLocation(), FRotator::ZeroRotator, OutPosition, OutRotation); // TransformToBoneSpace具体什么意思参考视频讲解

// LeftHandTransform即为左手要移动的目标位置
LeftHandTransform.SetLocation(OutPosition);
LeftHandTransform.SetRotation(FQuat(OutRotation));
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-53_FABRIK IK第9分10秒）

#### 动画资产

##### 动画资产之间Notifies批量复制

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-58_Rotating Running Animations第3分35秒）

##### UAnimationAsset

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-62_Fire Weapon Effects第1分55秒）



通过USkeletalMeshComponent播放UAnimationAsset资产

```c++
USkeletalMeshComponent* WeaponMesh;
WeaponMesh->PlayAnimation(AnimationAsset1, false);// false表示不循环播放
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-62_Fire Weapon Effects第3分30秒）

#### Sync Marker

![image-20250323102750691](ue.assets/image-20250323102750691.png)

![image-20250323103321213](ue.assets/image-20250323103321213.png)

在做脚步声之前，先加一个Sync Marker，它的作用是当我们在不同的动画之间进行混合时，它会及时做同步，使得脚步动画和通知同步，例如想在右脚发出声音的同时发出通知

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-59_Footstep and Jump Sounds第10分50秒）

#### Montage蒙太奇

在c++中设置UAnimMontage

![image-20250323110024139](ue.assets/image-20250323110024139.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-61_Fire Montage第9分10秒）

##### Montage Section

![image-20250323105238480](ue.assets/image-20250323105238480.png)

![image-20250323105745342](ue.assets/image-20250323105745342.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-61_Fire Montage第6分15秒）



sections编排

![image-20250323105842083](ue.assets/image-20250323105842083.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-61_Fire Montage第8分10秒）

##### Anim Slot Manager

![image-20250323105502028](ue.assets/image-20250323105502028.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-61_Fire Montage第6分40秒）



在动画蓝图中使用蒙太奇插槽

![image-20250323111224855](ue.assets/image-20250323111224855.png)

之后将其缓存并使用

![image-20250323111353779](ue.assets/image-20250323111353779.png)

![image-20250323111417453](ue.assets/image-20250323111417453.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-61_Fire Montage第14分50秒）



##### 在c++中利用AnimInstance控制蒙太奇播放

```c++
UAnimInstance* AnimInstance = GetMesh()->GetAnimInstance();
if (AnimInstance && FireWeaponMontage) {
    AnimInstance->Montage_Play(FireWeaponMontage);
    FName SectionName = bAiming ? FName("RifleAim") : FName("RifleHip"); // Montage Section
    AnimInstance->Montage_JumpToSection(SectionName); // Montage Section跳转播放
}
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-61_Fire Montage第11分30秒）

#### 从Mesh中获取AnimInstance

```c++
UAnimInstance* AnimInstance = GetMesh()->GetAnimInstance();
```

#### 角色被击中时的蒙太奇动画

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-80_Hitting the Character）

### 音效

#### SoundCue

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-59_Footstep and Jump Sounds第3分20秒）

#### Attenuation

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-59_Footstep and Jump Sounds第6分15秒）

### Seamless Travel

NON-SEAMLESS TRAVEL

![image-20250320162702269](ue.assets/image-20250320162702269.png)

SEAMLESS TRAVEL

![image-20250320162812147](ue.assets/image-20250320162812147.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-34_Seamless Travel and Lobby）



可以通过GameMode中的bUseSeamlessTravel打开：

![image-20250320163903804](ue.assets/image-20250320163903804.png)

![image-20250320164133076](ue.assets/image-20250320164133076.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-34_Seamless Travel and Lobby第13分50秒）



ServerTravel和ClientTravel的区别：

![image-20250320163014116](ue.assets/image-20250320163014116.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-34_Seamless Travel and Lobby第3分）

#### 创建Lobby的GameMode

选择功能更多的GameMode而非GameModeBase

![image-20250320163254218](ue.assets/image-20250320163254218.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-34_Seamless Travel and Lobby第5分30秒）

#### 创建过渡用的TransitionLevel

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-34_Seamless Travel and Lobby第15分50秒）

#### 设置TransitionMap

![image-20250320164331574](ue.assets/image-20250320164331574.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-34_Seamless Travel and Lobby第16分40秒）

### Weapon武器

#### 制作武器

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-36_Weapon Class）

##### Weapon状态枚举设计

这个状态枚举后面还可以用于网络复制的信号

![image-20250320220251482](ue.assets/image-20250320220251482.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-36_Weapon Class第11分）

#### 装配武器

像装配武器这种重要的操作全部由server完成，client可以通过server RPC完成

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-39_Equipping Weapons第14分30秒）

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-39_Equipping Weapons第18分）

##### 将武器附着到Socket的另一种方法

```c++
Socket->AttachActor(WeaponActor, Character->GetMesh());
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-39_Equipping Weapons第21分10秒）

##### SetOwner很重要

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-39_Equipping Weapons第22分05秒）



SetOwner已经被网络复制了，不需要再手动做复制。而且其通知函数OnRep_Owner是用virtual标记的，意味着可以重写，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-40_Remote Procedure Calls第11分25秒）



在SpawnActor时通过FActorSpawnParameters设置武器发射出的子弹的Owner，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-65_Spawning the Projectile第11分25秒）



AttachActor后，WeaponActor和Character->GetMesh()就建立了联系，此时可以通过weapon直接获取Character->GetMesh()中的骨骼信息

```c++
FTransform RightHandTransform = EquippedWeapon->GetWeaponMesh()->GetSocketTransform(FName("hand_r"), ERelativeTransformSpace::RTS_World); // Hand_R为角色身上的骨骼，GetSocketTransform不区分大小写，因此hand_r == Hand_R，这里直接通过weapon获取到了角色的骨骼信息
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-75_Correcting the Weapon Rotation第9分30秒）

#### 从Socket处SpawnActor时X轴朝向很重要

![image-20250323230759491](ue.assets/image-20250323230759491.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-70_Bullet Shells第2分20秒）



SpawnActor时使用Socket的位置和旋转

```c++
const* Socket = Mesh->GetSocketByName(FName("AmmoEject"));
FTransform SocketTransform = Socket->GetSocketTransform(Mesh);

UWorld* World = GetWorld();
World->SpawnActor<>(
	ActorClass,
    SocketTransform.GetLocation(),
    SocketTransform.GetRotation().Rotator() // 需要转Rotator
);
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-70_Bullet Shells第11分10秒）

#### 解决当角色背靠墙体太近时屏幕被武器挡住的问题

一定距离时让武器不可见

```c++
if (IsLocallyControlled()) {
    WeaponMesh->bOwnerNoSee = true; // 只对Owner不可见
}
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-79_Extending the Trace Start第8分40秒）

#### 自动开火

在客户端使用TimerDelay实现

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-82_Automatic Fire）

### 网络

#### DOREPLIFETIME_CONDITION

![image-20250320222235825](ue.assets/image-20250320222235825.png)

```c++
DOREPLIFETIME_CONDITION(ABlasterCharacter, Variable1, COND_AutonomousOnly); // 只复制给网络角色为Autonomous的
DOREPLIFETIME_CONDITION(ABlasterCharacter, Variable1, COND_OwnerOnly); // 只复制给Owner
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-38_Variable Replication第9分50秒）

#### IsLocallyControlled

场景一：判断server中托管的pawn是否为远端本地控制

有时候使用了OnRep_Func进行网络复制，但复制只能是从Server复制到Client不能反向，所以导致远端本地触发时OnRep_Func失效

所以在只能在Server触发的函数中，可以使用IsLocallyControlled使得远端本地触发该函数

```c++
void Func() { // 假设该函数只在server端执行，不在client端执行
    if (IsLocallyControlled()) // 触发远端本地执行
}
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-38_Variable Replication第15分45秒）



场景二：筛选本地其他Character

```c++
if (!Character->HasAuthority() && !Character->IsLocallyControlled()) // ...
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-45_Leaning and Strafing第8分25秒）



场景三：筛选本地控制Character

```c++
if (!Character->HasAuthority() && Character->IsLocallyControlled()) // ...
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-51_Pitch in Multiplayer第2分40秒）



场景四：筛选在服务器上但非服务器本地控制的Character，也就是客户端控制的Character

```c++
if (Character->HasAuthority() && !Character->IsLocallyControlled()) // ...
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-51_Pitch in Multiplayer第4分）



在动画蓝图中直接调用IsLocallyControlled是线程不安全的：

![image-20250324212506215](ue.assets/image-20250324212506215.png)

正确做法是在c++的AnimInstance上新建bLocallyControlled并在线程安全的方法中设置它（这里是在AnimInstance的NativeUpdateAnimation方法中设置了该变量）：

![image-20250324212630516](ue.assets/image-20250324212630516.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-75_Correcting the Weapon Rotation第22分）

#### Overlap等事件只在server上生成

当server上开启/关闭Overlap，client也会自动跟随，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-40_Remote Procedure Calls第15分）

#### 调用Server RPC时不需要判断HasAuthority的场景

该情况下Server端调用只会在Server执行，Client端调用也只会在Server执行，那么这个情况就不需要再判断HasAuthority了

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-43_Aiming第15分）

#### bReplicates的解释

bReplicates不开启时物体拥有所有机器的权限，它将在各自的机器上生成并且独立于服务器存在；

bReplicates开启时Server生成它并传播到其他Client，Server拥有它的所有权限；

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-67_Projectile Tracer第8分15秒）

#### FVector_NetQuantize

该FVector对网络发送数据进行了优化

![image-20250323224708969](ue.assets/image-20250323224708969.png)

![image-20250323224407593](ue.assets/image-20250323224407593.png)

![image-20250323224812978](ue.assets/image-20250323224812978.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-68_Replicating the Hit Target第2分20秒）

#### OnRep_ReplicatedMovement

优化Movement的网络部分，使其更加丝滑

如果在动画蓝图nativetick中通过网络更新Simulated角色动作，可能会导致抖动。那是因为动画蓝图nativetick比网络更新频率快多了，会导致Simulated角色的nativetick中会掺插0值，这是抖动的根本原因

此时应该使用OnRep_ReplicatedMovement函数，它跟网络更新频率一致，可以杜绝0值

![image-20250325141631625](ue.assets/image-20250325141631625.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-81_Smooth Rotation for Proxies第16分20秒）

#### Character拥有比PlayerState更快的网络复制

所以血条等需要及时更新的信息可以放在Character

![image-20250325150619991](ue.assets/image-20250325150619991.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-85_Health第50秒）

#### Replicated比RPC效率要高

能用Replicated解决的不要用RPC

比如在这个案例中作者将原先用RPC的函数删除，并挪到OnRep_Func中，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-87_Damage第15分）

原因是Replicated的变量会考虑压缩再传输，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-87_Damage第27分20秒）

### ACharacter

#### 角色移动时获取正确的方向

朝前方向

```c++
void MoveForward(float UserInputMagnitude) {
    if (Controller != nullptr && UserInputMagnitude != 0.f) {
        const FRotator YawRotation(0.f, Controller->GetControlRotation().Yaw, 0.f);
        const FVector Direction(FRotationMatrix(YawRotation).GetUnitAxis(EAxis::X));
        AddMovementInput(Direction, Value);
    }
}
```

朝右方向

```c++
void MoveForward(float UserInputMagnitude) {
    if (Controller != nullptr && UserInputMagnitude != 0.f) {
        const FRotator YawRotation(0.f, Controller->GetControlRotation().Yaw, 0.f);
        const FVector Direction(FRotationMatrix(YawRotation).GetUnitAxis(EAxis::Y));
        AddMovementInput(Direction, Value);
    }
}
```



上面这些信息在矫正武器旋转时也很有用，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-75_Correcting the Weapon Rotation第3分15秒）

#### PostInitializeComponents

初始化组件函数钩子

![image-20250321094651122](ue.assets/image-20250321094651122.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-39_Equipping Weapons第10分25秒）

#### Crouch/UnCrouch处理蹲伏

需要先开启CanCrouch：

![image-20250321103818895](ue.assets/image-20250321103818895.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-42_Crouching第7分30秒）



关于CanCrouch的讲解：

![image-20250321103945517](ue.assets/image-20250321103945517.png)

从源码发现可以从c++设置CanCrouch：

![image-20250321104453717](ue.assets/image-20250321104453717.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-42_Crouching第8分10秒）



![image-20250321103549483](ue.assets/image-20250321103549483.png)

![image-20250321103640606](ue.assets/image-20250321103640606.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-42_Crouching第2分20秒）



Crouch自动处理了胶囊体大小，参考：

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-42_Crouching第11分40秒）



设置Crouch时Character的各种属性，例如移动速度等，参考：

![image-20250321111347936](ue.assets/image-20250321111347936.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-42_Crouching第12分40秒）

#### 获取角色速度

```c++
Character->GetVelocity();
```

根据角色速度获取旋转量并用于BlendSpace

```c++
FRotator MovementRotation = UKismetMathLibrary::MakeRotFromX(Character->GetVelocity());
float YawMagnitude = MovementRotation.Yaw;
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-45_Leaning and Strafing第5分）

#### 获取角色是否在加速

```c++
GetCharacterMovement()->GetCurrentAcceleration().Size() > 0.f;
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-33_Animation Blueprint第9分35秒）

#### 获取基于用户镜头控制下的旋转

```c++
// 可用于BlendSpace
// 默认网络复制
Character->GetBaseAimRotation();
```

![image-20250321113141559](ue.assets/image-20250321113141559.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-45_Leaning and Strafing第1分35秒）

#### 网络复制频率设置

![image-20250322225747126](ue.assets/image-20250322225747126.png)

通常设置为66和33，越高越流畅网络带宽要求越高

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-56_Net Update Frequency第2分10秒）



在c++ Character中设置网络复制频率

![image-20250322225913977](ue.assets/image-20250322225913977.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-56_Net Update Frequency第4分10秒）

#### Rotation Rate

可以控制角色的转向速度

使用的前提是orient character to move需要开启

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-57_Crouch Unequipped第3分05秒）



在c++中设置该属性

![image-20250322232141925](ue.assets/image-20250322232141925.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-57_Crouch Unequipped第3分40秒）

#### 重写Jump

场景：在Crouch状态下则UnCrouch，在UnCrouch状态下Jump

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-57_Crouch Unequipped第4分35秒）

#### Damage伤害

通过`UGameplayStatics::ApplyDamage`发起伤害：

![image-20250325151924834](ue.assets/image-20250325151924834.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-87_Damage第5分45秒）



绑定OnTakeAnyDamage事件来承受伤害：

![image-20250325152605751](ue.assets/image-20250325152605751.png)

还有OnTakePointDamage、OnTakeRadialDamage等伤害事件

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-87_Damage第12分30秒）

### friend友元的妙用

![image-20250321094840403](ue.assets/image-20250321094840403.png)

此时UCombatComponent拥有了访问UBlasterCharacter内容的权限，包括私有内容

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-39_Equipping Weapons第8分）

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-39_Equipping Weapons第11分10秒）

### 项目之间迁移注意资产相对路径一致

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-49_Aim Offsets第16分10秒）

### 多人射击网游的网络优化

参考：

`多人联机-C++多人射击游戏教程-配置DefaultEngine.ini-设置多人射击网游的Server Net Tick Rate`章节

`多人联机-C++多人射击游戏教程-ACharacter-网络复制频率设置`章节

### 屏幕空间到世界空间

场景：射击游戏朝屏幕准星开枪射击

#### 从c++获取屏幕大小和屏幕比例

```c++
FVector2D ViewportSize;
if (GEngine && GEngine->GameViewport) {
    GEngine->GameViewport->GetViewportSize(ViewportSize); // 获取屏幕大小
}
FVector2D CrosshairLocation(ViewportSize.X / 2.f, ViewportSize.Y / 2.f); // 获取屏幕中心位置
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-64_The Hit Target第2分30秒）

#### 屏幕空间投射到世界空间

```c++
#define TRACE_LENGTH 80000;

FVector2D ViewportSize;
if (GEngine && GEngine->GameViewport) {
    GEngine->GameViewport->GetViewportSize(ViewportSize); // 获取屏幕大小
}
FVector2D CrosshairLocation(ViewportSize.X / 2.f, ViewportSize.Y / 2.f); // 获取屏幕中心位置

FVector CrosshairWorldPosition;
FVector CrosshairWorldDirection;
bool bScreenToWorld = UGameplayStatics::DeprojectScreenToWorld(
	UGameplayStatics::GetPlayerController(this, 0),
    CrosshairLocation,
    CrosshairWorldPosition,
    CrosshairWorldDirection
); // 获取屏幕空间位置在世界空间的位置以及它所指向的方向

if (bScreenToWorld) {
    FVector Start = CrosshairWorldPosition;
    FVector End = Start + CrosshairWorldDirection * TRACE_LENGTH;
    
    // 射线检测
    FHitResult TraceHitResult;
    GetWorld()->LineTraceSingleByChannel(
    	TraceHitResult,
        Start, End,
        ECollisionChannel::ECC_Visibility
    );
    
    if (!TraceHitResult.bBlockingHit) {
        TraceHitResult.ImpactPoint = End;
    } else {
        // ...
    }
}
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-64_The Hit Target第4分05秒）

### 摄像机

#### Socket Offset

射击游戏通常需要调整摄像机往边上稍微偏一点，这样可以看到准星

![image-20250323145857918](ue.assets/image-20250323145857918.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-64_The Hit Target第15分30秒）

#### 解决当角色背靠墙体太近时屏幕被身体挡住的问题

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-79_Extending the Trace Start第5分35秒）

### ProjectileMovementComponent

```c++
ProjectileMovementComponent->bRotationFollowsVelocity = true;
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-66_Projectile Movement Component第2分50秒）

### 子弹特效

作者用的是老粒子系统

```c++
UGameplayStatics::SpawnEmitterAttached(...); // 该方法可以使得粒子效果依附在子弹上
```

![image-20250323152255017](ue.assets/image-20250323152255017.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-67_Projectile Tracer）

### 物理

#### 开启刚体碰撞通知

```c++
Mesh->SetSimulatePhysics(true);
Mesh->SetNotifyRigidBodyCollision(true); // 开启刚体碰撞通知之后Mesh才能响应OnOverlap等碰撞事件
```

蓝图版本：

![image-20250324104116060](ue.assets/image-20250324104116060.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-71_Shell Physics第12分40秒）

### HUD

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-72_Blaster HUD and Player Controller第1分）



`AHUD::DrawHUD()`，该函数会在每一帧调用

```c++
virtual void DrawHUD() override;
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-72_Blaster HUD and Player Controller第3分05秒）



`AHUD::DrawTexture()`

![image-20250324182704554](ue.assets/image-20250324182704554.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-73_Drawing the Crosshairs第5分30秒）

#### UTexture2D

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-72_Blaster HUD and Player Controller第4分15秒）



压缩优化，将资产压缩格式改为UserInterface2D(RGBA)

![image-20250324142801352](ue.assets/image-20250324142801352.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-72_Blaster HUD and Player Controller第6分40秒）



获取尺寸

```c++
Texture->GetSizeX();
Texture->GetSizeY();
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-73_Drawing the Crosshairs第4分）

#### 动态准星绘制

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-74_Crosshair Spread）



根据角色移动速度、是否在空中、是否在瞄准中等状态计算准星偏移举例，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-74_Crosshair Spread第6分50秒）

#### 优化准星绘制

只有IsLocallyControlled的玩家才需要绘制准星

```c++
if (Character->IsLocallyControlled()) {
    // 绘制准星
}
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-76_Zoom While Aiming第11分30秒）

#### 瞄准时缩小准星、开火时放大准星

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-77_Shrink Crosshairs when Aiming）

#### 射线检测到敌人时改变准星颜色

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-78_Change Crosshairs Color）

### 矫正武器旋转

开火时发现子弹是歪掉的，并没有跟枪管对齐：

```c++
// 比较枪口位置MuzzleFlash到沿MuzzleFlash向前向量*1000位置的射线 与 MuzzleFlash到屏幕中心射线检测击中目标HitTarget位置的射线的区别
FTransform MuzzleTipTransform = EquippedWeapon->GetWeaponMesh()->GetSocketTransform(FName("MuzzleFlash"), ERelativeTransformSpace::RTS_World);
FVector MuzzleX(FRotationMatrix(MuzzleTipTransform.GetRotation().Rotator()).GetUnitAxis(EAxis::X)); // 获取枪口位置MuzzleFlash的向前向量
// 通过DrawDebugLine比较（MuzzleFlash到MuzzleFlash + MuzzleX * 1000.f）和（MuzzleFlash到屏幕中心射线检测击中目标HitTarget）的区别
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-75_Correcting the Weapon Rotation第3分15秒）



需要调整右手骨骼让他朝向HitTarget

获取右手骨骼hand_r到屏幕中心射线检测击中目标HitTarget的LookAtRotation

```c++
// FRotator LookAtRotation = UKismetMathLibrary::FindLookAtRotation(RightHandTransform.GetLocation(), BlasterCharacter->GetHitTarget());
// 由于hand_r的x轴朝向是向内的，所以要这样写：
FRotator LookAtRotation = UKismetMathLibrary::FindLookAtRotation(RightHandTransform.GetLocation(), RightHandTransform.GetLocation() + (RightHandTransform.GetLocation() - BlasterCharacter->GetHitTarget())); 
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-75_Correcting the Weapon Rotation第10分10秒）



通过Transform(Modify) Bone节点修改hand_r骨骼的transform

![image-20250324194837297](ue.assets/image-20250324194837297.png)

关闭不必要的pin

![image-20250324194937393](ue.assets/image-20250324194937393.png)

将模式修改为Replace Existing以及World Space

![image-20250324195030150](ue.assets/image-20250324195030150.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-75_Correcting the Weapon Rotation第11分40秒）



矫正后别忘了要先计算正确的hand_r位置后再应用FABRIK IK，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-75_Correcting the Weapon Rotation第15分15秒）



优化点：

武器正确旋转的效果只对当前正在控制的玩家敏感，对于模拟玩家其实并不重要，所以应该跳过模拟玩家相关计算以及取消HitTarget每一帧的网络复制

![image-20250324212106626](ue.assets/image-20250324212106626.png)

在动画蓝图中也应该只对LocallyControlled的玩家进行计算，跳过其他所有计算

![image-20250324213203854](ue.assets/image-20250324213203854.png)

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-75_Correcting the Weapon Rotation第18分30秒）

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-75_Correcting the Weapon Rotation第21分40秒）



#### 解决目标突变时武器旋转突变问题

使用平滑插值即可解决，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-78_Change Crosshairs Color第11分40秒）

#### 解决目标在摄像机和角色之间时武器旋转问题

由于射线是从camera中心射出来的，因此当有物体在摄像机和角色之间时，武器就会指向错误的方向

解决方案：将射线起点推进至与角色齐平，这样射线即不会打到自己身上也不会打到背后的物体

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-79_Extending the Trace Start）

### 瞄准时调整FOV控制画面放大缩小

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-76_Zoom While Aiming）



获取相机FOV（FieldOfView）

```c++
Camera->FieldOfView;
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-76_Zoom While Aiming第7分20秒）

设置相机FOV

```c++
Camera->SetFieldOfView(CurrentFOV);
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-76_Zoom While Aiming第10分55秒）



只有IsLocallyControlled的玩家才需要调整FOV

```c++
if (Character->IsLocallyControlled()) {
    // 调整FOV
}
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-76_Zoom While Aiming第11分15秒）



调整FOV后镜头会变得模糊，此时可以通过调整Depth of Field中的Focal Distance来部分解决问题

![image-20250324215007777](ue.assets/image-20250324215007777.png)

调整后如果还是模糊，则可以调整Camera属性框的Aperture(F-stop)

![image-20250324215222387](ue.assets/image-20250324215222387.png)

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-76_Zoom While Aiming第13分30秒）

### UI

#### 下载并导入字体

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-85_Health第5分05秒）

### GameMode

#### 玩家死亡

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-88_Blaster Game Mode第3分50秒）



播放淘汰动画，参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-89_Elim Animation）

#### 玩家重生

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-90_Respawning）



重生之前需要重置并销毁Character

```c++
if (Character) {
    Character->Reset(); // Reset里面会有很多操作，例如Controller->UnPossess()等，参考视频
    Character->Destroy();
}
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-90_Respawning第6分35秒）



让Player在PlayerStart处重生

```c++
if (Controller) {
    RestartPlayerAtPlayerStart(Controller， PlayerStart); // GameMode中调用的
}
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-90_Respawning第8分30秒）



获取世界中所有APlayerStart的实例

```c++
TArray<AActor*> Actors;
UGameplayStatics::GetAllActorsOfClass(this, APlayerStart::StaticClass(), Actors);
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-90_Respawning第9分20秒）

# TAttribute与Slate数据绑定

参考：

https://zhuanlan.zhihu.com/p/465410846（UE4 TAttribute原理 与 Slate数据绑定）

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

参考：

https://zhuanlan.zhihu.com/p/428555822（UE4学习记录(2)FSoftClassPath 和FSoftObjectPath 区别）、https://zhuanlan.zhihu.com/p/351106187（虚幻4中的类型引用）

#### ToSoftObjectPath()

```c++
TSoftObjectPtr<UWorld> W1;
FString AssetName = W1.ToSoftObjectPath().GetAssetName();
```

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-7. Map Entrance第14分20秒）

### TSoftClassPtr 和 TSoftObjectPtr

参考：

https://blog.csdn.net/qq_45777198/article/details/107838444（【学习笔记】UE4——`TSoftClassPtr<T> ptr`和`TSoftObjectPtr<T> ptr`）

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-21. Map Name Field Notify第4分30秒）

## UE手动垃圾回收

对于UObject：

```c++
UMyObject* MyObject = NewObject<UMyObject>(); // 创建一个对象

// 法一：手动销毁对象
MyObject->ConditionalBeginDestroy();

// 或者用这种方法：
// 法二：标记对象为垃圾
MyObject->MarkAsGarbage();
```

对于AActor：

```c++
AMyActor* MyActor = GetWorld()->SpawnActor<AMyActor>(); // 创建一个 Actor

// 销毁 Actor
MyActor->Destroy();
```

手动触发垃圾回收：

```c++
#include "Engine/Engine.h"

// 强制触发垃圾回收
GEngine->ForceGarbageCollection(true);
```



# 强制类型转换

非UObject或Interface的转换不能使用Cast<>()，得尝试使用static_cast<>()

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-7.Using a Custom Effect Context第3分钟）

# 枚举

## 迭代遍历

参考：https://zhuanlan.zhihu.com/p/492702386（UE4枚举的迭代遍历）、https://blog.csdn.net/a359877454/article/details/105262795（UE4遍历枚举）

## TEnumAsByte

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-16. Load Slot Status第2分20秒）

## 添加名称限定符让枚举意义更明确

普通enum：

```c++
enum EnumName{
    E1
}
// ...
if (X1 == E1) // ...
```

添加名称限定符：

```c++
enum class EnumName : uint8{
    E1
}
// ...
if (X1 == EnumName::E1) // ...
```

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-3. Targeting Status第17分15秒）

# 坐标系

## 物体相对坐标与世界坐标互转

参考：https://blog.csdn.net/l346242498/article/details/106919703（UE4 相对坐标转世界坐标）、https://blog.csdn.net/longyanbuhui/article/details/115407458（UE4_local location（本地位置） 与 world location（世界位置） 转换）

## 屏幕坐标与世界坐标互转

参考：https://blog.csdn.net/longyanbuhui/article/details/84201864（UE4_屏幕位置与世界位置的相互转化）、https://zhuanlan.zhihu.com/p/597560776（UE4 相机屏幕坐标与世界坐标的相互转换）

# 游戏技能音效下载网站

https://freesound.org/（free sound）

https://soundscrate.com/electricity.html（Electricity Sound Effects）

https://freesfx.co.uk/Default.aspx（freeSFX）

https://opengameart.org/art-search-advanced（OpenGameArt.ORG）

https://www.aigei.com/sound/class/fight/（爱给）

参考：https://zhuanlan.zhihu.com/p/108442292（推荐几个免费 游戏技能音效下载网站）

# Add Force/Add Impulse

参考：

https://www.incg.com.cn/ue4_add-force_vs_add-impulse/（ue4虚幻引擎Add Force / Add Impulse力和脉冲）

# 风场（Wind）

参考：https://blog.csdn.net/shenmifangke/article/details/80281904（ue4内植物碰撞和风力设置）、https://blog.csdn.net/qq_36917144/article/details/104010429（虚幻4创建旗帜和随机风力）

# 模拟

## ue自带布料模拟

比较耗性能，不太适合做游戏，做游戏时还是推荐用风动物理骨骼

参考：

https://www.bilibili.com/video/BV1RM411Y7yB（【UE5】26 - 动画部分：给人物的衣服和头发启用布料效果）

https://www.bilibili.com/video/BV1zJ4m1A7MA（【UE5】从零开始做原神（30）添加布料模拟（Chaos Cloth））

## 风动物理骨骼（用于模拟裙摆飘动）★

参考：

https://www.bilibili.com/video/BV1QD421A7ee（【UE5】从零开始做原神（31）风动物理骨骼（SPCR Joint Dynamics））

## 头发模拟★

- kawaii插件

  参考：

  https://www.bilibili.com/video/BV1qs421N7qk（【UE5】从零开始做原神（29）添加衣服漂动（kawaii插件））

## 皮肤晃动模拟

参考：

https://www.bilibili.com/video/BV1dk4y167rx（UE4通过物理模拟自制胸部和头发摆动01）



上述视频中提到，当角色动画重定向时摆动会失效，此时使用虚幻引擎springcontroller（弹簧控制器）即可：

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/animation-blueprint-spring-controller-in-unreal-engine（弹簧控制器）

# 动画

## 重定向

参考：

https://www.bilibili.com/video/BV1HH4y1T7uN（【UE5】从零开始做原神（2）导入原神模型替换小白人（动画重定向）★）

https://www.bilibili.com/video/BV1ECmrYrEyV（【UE5】从零开始做原神（65）只狼动画重定向小白人）



mmd到ue重定向时模型飞天问题处理，参考：

https://www.bilibili.com/video/BV1Jb42187Wp（【番外教程】解决mmd骨骼太大+5.4重定向模型上天问题）

https://blog.csdn.net/ONE_SIX_MIX/article/details/119879752（mmd 到 blender 到 ue4/ue5 缩放比例相关的处理流程）



## 动画层接口

情景：在动画蓝图中方便地实现人物持枪/持刀动画切换

参考：

https://www.bilibili.com/video/BV1Qb421i7Eg（【UE5】从零开始做原神（37）动画层接口（动画基础 LinkedAnimLayer））

https://www.bilibili.com/video/BV1bh4y1t7Dz（UE4/5动画蓝图链接LinkedAnimLayer 动画层接口案例 - 基于UE4第三人称模板增加上半身持枪动画）



## 顶点动画

参考：

https://www.bilibili.com/video/BV13h411t7Zm/?vd_source=c3d9e4c3ef670596b3b0dddab637f86c（UE4 UE5 顶点动画）

https://blog.csdn.net/qq_29891697/article/details/133239833（UE5 官方顶点动画插件（AnimToTexture））、https://www.cnblogs.com/FZfangzheng/p/16573167.html（UE5城市样例中AnimToTexture插件的使用）

https://zhuanlan.zhihu.com/p/630381438（UE场景顶点动画效果实现）、https://blog.csdn.net/gzx88666/article/details/107714928（UE4-基于顶点偏移制作拉扯效果）、https://zhuanlan.zhihu.com/p/164352994（UE4-顶点动画）

## 模块化动画系统LinkAnimLayer

参考：https://zhuanlan.zhihu.com/p/599610314（UE5 Lyra项目学习（五） 模块化动画系统）、官方Lyra项目

## Control rig与IK rig

参考：

https://zhuanlan.zhihu.com/p/499405167（★【游戏开发】逆向运动学（IK）详解，包括雅可比矩阵、奇异值分解（SVD）等解算方法讲解）、https://zhuanlan.zhihu.com/p/591982020（UE5 -- Control Rig与IK Rig介绍）、https://blog.csdn.net/ttm2d/article/details/112545858（虚幻引擎图文笔记：用Two Bone IK实现手扶墙）、https://blog.csdn.net/weixin_41363156/article/details/114645792（UE4之Control Rig）、https://zhuanlan.zhihu.com/p/412251528（[玩转UE4/UE5动画系统＞Control Rig篇] 之 Control Rig + Fullbody IK版的足部IK实现（附项目代码））

https://www.bilibili.com/video/BV1Sz4y1d7bN（【动画技术教程】FullBodyIK（全身IK）原理详细解析与UE4应用实例教学）



## 使用IK重定向器修正滑步

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/fix-foot-sliding-with-ik-retargeter-in-unreal-engine

## 高级运动系统 (ALSV4)

参考：https://zhuanlan.zhihu.com/p/604888297（【UE5】【3C】ALSv4重构分析（一） : 更好的ALS学习体验）、https://zhuanlan.zhihu.com/p/518724305（UE4 UE5 骨骼动画 高级运动系统 (ALSV4)）、https://zhuanlan.zhihu.com/p/547321935（UE4 UE5 骨骼动画 高级运动系统 脚部IK）、https://zhuanlan.zhihu.com/p/568124406（UE4 UE5 骨骼动画 高级运动系统 手部IK 虚拟骨骼）

## 父类动画插槽

### BlendSpace Player（父类动画混合插槽）

![image-20250121155104004](ue.assets/image-20250121155104004.png)

子类动画继承之后即可使用父类的插槽：

![image-20250121155245136](ue.assets/image-20250121155245136.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-6.Animation Blueprints第6分钟）

### Sequence Player（父类动画播放器插槽）

![image-20250306131627160](ue.assets/image-20250306131627160.png)

子类动画继承之后即可使用父类的插槽：

![image-20250306131935645](ue.assets/image-20250306131935645.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-20. Stun第24分05秒）

## 创建可复用的动画通知类（Anim Notify Class）

参考：https://blog.csdn.net/ttm2d/article/details/111769249（UE4动画系统：什么是动画通知（Anim Notify））

## 瞄准动画时的MeshSpace和LocalSpace

参考：https://zhuanlan.zhihu.com/p/33234659（浅谈MeshSpace和LocalSpace）

## 同一个Owner不同部位动画蓝图之间如何通讯

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-8.Slingshot Animation Blueprint第3分）

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-9.Slingshot Attack Montage第4分20秒）

## 动画蓝图节点

参考：

https://zhuanlan.zhihu.com/p/141266454（AdvancedLocomotionV4学习笔记（1）——动画节点）

### Transform(Modify) Bone

![image-20250210133803882](ue.assets/image-20250210133803882.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-8.Slingshot Animation Blueprint第7分15秒）

### Blend Poses by Bool

![image-20250210134624690](ue.assets/image-20250210134624690.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-9.Slingshot Attack Montage第55秒）

### layered blend per bone

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/blend-masks-and-blend-profiles-in-unreal-engine

https://blog.csdn.net/weixin_45389639/article/details/109476448（UE4 layered blend per bone 节点详解）

https://zhuanlan.zhihu.com/p/428242048（UE4分层混合节点Layered Blend Per Bone设置）

### Modify Curve

借助该节点，可以在运行时混合、缩放并重新映射动画曲线

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/animation-blueprint-modify-curve-in-unreal-engine

## 动画序列编辑器

参考：

https://dev.epicgames.com/documentation/en-us/unreal-engine/animation-sequence-editor-in-unreal-engine

### 动画资产编辑器

参考：

https://dev.epicgames.com/documentation/en-us/unreal-engine/animation-sequence-editor-in-unreal-engine#asseteditor

#### 动画曲线

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/animation-curves-in-unreal-engine

#### Additive Layer Tracks编辑动画层

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/editing-animation-layers

https://blog.csdn.net/qq_39934403/article/details/120843542（UE4 虚幻引擎，动画篇（三）Animation动画，骨骼怎么k帧）

#### Attributes动画属性

![image-20250211224344286](ue.assets/image-20250211224344286.png)

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/fbx-attributes-in-unreal-engine

#### 裁剪动画

![image-20250306132858527](ue.assets/image-20250306132858527.png)

参考：

https://www.bilibili.com/video/BV1TH4y1L7NP（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（二）-20. Stun第29分45秒）

## Animation Composite动画合成

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/animation-composites-in-unreal-engine

## 线程安全的方式获取动画变量

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/how-to-get-animation-variables-in-animation-blueprints-in-unreal-engine

## MorphTargets网格体形变

与blender的形态键配合可以完成捏脸功能

参考：

https://blog.csdn.net/u013412391/article/details/120827050（学习在UE中导入 Morph Targets 资源）

https://www.cnblogs.com/kekec/p/18150759（SkeletalMesh之MorphTargets技术）

https://dev.epicgames.com/documentation/en-us/unreal-engine/BlueprintAPI/Animation/MorphTargets

# 体积烟雾

参考：https://zhuanlan.zhihu.com/p/405811376（【UE4】五种烟雾流程）、FluidNinjaLive、b站聆枫LingFeng Niagara流体模拟教程

# Niagara

参考：

https://www.bilibili.com/video/BV1iA41137My（虚幻引擎游戏特效制作教程 Unreal Engine - Niagara 粒子与特效教程）

https://www.bilibili.com/video/BV1kx4y1t7RC（虚幻引擎5 Niagara VFX的一站式课程）

https://zhuanlan.zhihu.com/p/138088668（UE4-Niagara基础解析）

https://www.yuque.com/unrealengine/niagara/sfq70w（Niagara详解笔记）



## Dynamic Material Parameter

在particle update中添加Dynamic Material Parameter指定粒子的属性

材质中使用Dynamic Parameter将Niagara中的动态属性值传入材质

参考：https://blog.csdn.net/hechao3225/article/details/113532401（UE4 Niagara粒子系统基础笔记）

## UAV（Unordered Access View）、Grid2DCollection、Grid3DCollection

这些都相当于高级版RenderTarget

参考：https://zhuanlan.zhihu.com/p/344575245（UE4：Niagara中的UAV）

## 粒子通信

参考：

https://zhuanlan.zhihu.com/p/427618536（UE5 GPU粒子通信_Index与ID使用详解）

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

参考：

https://docs.unrealengine.com/5.3/zh-CN/level-instancing-in-unreal-engine/

https://zhuanlan.zhihu.com/p/502068030（UE5 WorldPartition【3】 LevelInstance）

# UAssetManager

设置自己的AssetManager

1、首先创建UAssetManager子类，编写自定义逻辑；

2、在DefaultEngine.ini设置自定义AssetManager；

![image-20250122182034266](ue.assets/image-20250122182034266.png)

![image-20250122183644227](ue.assets/image-20250122183644227.png)

参考：

https://www.bilibili.com/video/BV1JD421E7yC（虚幻5C++教程使用GAS制作RPG游戏（一）-12.Aura Asset Manager第1分10秒）

https://zhuanlan.zhihu.com/p/129712105（【UE4】资源管理之UAssetManager用法★）

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

# PCG（Procedural Content Generation Framework程序化内容生成器）

参考：

https://www.bilibili.com/video/BV17z4y1P7nK（★PCG All In One Tutorial）

https://zhuanlan.zhihu.com/p/636291504（UE5中的PCG —— PCG in UE5）、https://zhuanlan.zhihu.com/p/638790748（UE5中的PCG进阶 —— Advanced PCG in UE5）

https://blog.csdn.net/ttm2d/article/details/131304084（虚幻引擎程序化资源生成框架PCG(Procedural Content Generation Framework) 之 PCG基础）

# Procedural Mesh 程序化模型

参考：https://zhuanlan.zhihu.com/p/346745928（UE4 Procedural Mesh 程序化模型）、https://blog.csdn.net/qq_31788759/article/details/104092692（【UE4 C++】由点面数据，批量绘制ProceduralMesh并转化为StaticMesh资产）

# Loading Screen（前端加载画面或视频，后端加载关卡）

参考：

https://zhuanlan.zhihu.com/p/608502007（虚幻杂记4 PreLoadScreen与LoadingScreen）、https://zhuanlan.zhihu.com/p/395615335（为UE4制作实时加载界面（蓝图向） —— Real-time Loading Screen for UE4（Blueprint））、https://zhuanlan.zhihu.com/p/372577094（UE的LoadingScreen加载界面及动态数据实现）

https://blog.csdn.net/sinat_27456831/article/details/49933285（虚幻4动态加载画面（后台加载关卡）的实现）

# 换装系统

参考：

https://zhuanlan.zhihu.com/p/58675771（UE4换装系统）、https://zhuanlan.zhihu.com/p/59600775（UE4换装系统（合并骨骼模型））

https://blog.csdn.net/ZFSR05255134/article/details/119602023（[UE4]换装功能 SetMasterPoseComponent）

https://blog.csdn.net/qq_52905520/article/details/124558629（ue4换装系统 1.换装系统的基本原理）、https://blog.csdn.net/qq_52905520/article/details/124578023（ue4 换装系统 2.换装系统的场景捕捉）、https://blog.csdn.net/qq_52905520/article/details/125081700（ue4 换装系统3.最终成果）

https://zhuanlan.zhihu.com/p/54900203（UE4[BP]角色换装系统的实现）

# UMG

## EditorUtilityWidget

参考：https://blog.csdn.net/iaibeyond/article/details/117696541（简介UE4中的EditorUtilityWidget）、https://blog.csdn.net/u013412391/article/details/121844464（学习在虚幻引擎中使用UMG作为编辑器控件）、https://blog.csdn.net/Yoci98/article/details/120600645（UE使用EditorUtilityWidget完成简单的编辑器内工具）

# 蓝图中动态获取Uasset资源（GetAssetsXXX）

![image-20230830160629458](ue.assets/image-20230830160629458.png)

参考：https://blog.csdn.net/weixin_40821143/article/details/112981474（UE4 | BP | 使用蓝图获取Uasset资源）

# 多线程异步任务

参考：https://space.bilibili.com/92060300/video（【合集】UE4 C++进阶系列）、https://zhuanlan.zhihu.com/p/38881269（《Exploring in UE4》多线程机制详解[原理分析]）

# 调试窗口

## GPU Visualizer

快捷键ctrl + shift + ,

![image-20241223095916814](ue.assets/image-20241223095916814.png)

# 日志管理

参考：

https://blog.csdn.net/zhang1461376499/article/details/113351948（虚幻引擎(UE4) 日志、打印运行时信息）

# 测试与优化

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/testing-and-optimizing-your-content

## 性能Profile的各种方式

参考：

https://blog.csdn.net/qq_29523119/article/details/123606732（UE4性能Profile的各种方式）

https://zhuanlan.zhihu.com/p/416863993（UE4 Profiler 性能分析工具原理和实现机制）

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/unreal-engine-stats-system-overview

## 常用控制台命令

参考：

https://blog.csdn.net/qq_21153225/article/details/144097338（UE5 和 UE4 中常用的控制台命令总结）

### 时间膨胀

使用控制台Slomo命令：

![image-20250316145852784](ue.assets/image-20250316145852784.png)

参考：

https://www.bilibili.com/video/BV1EwAKemEof（【AI中字】虚幻5C++教程使用GAS制作RPG游戏（三）-15. Loot Drop Curve第10分15秒）

### ShowCollision

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-42_Crouching第11分40秒）

## TRACE_BOOKMARK事件

参考：

https://dev.epicgames.com/documentation/zh-cn/unreal-engine/trace-in-unreal-engine-5

# 渲染

## 渲染编程

参考：https://zhuanlan.zhihu.com/p/36675543（虚幻4渲染编程专题概述及目录★）

## G-buffer

参考：https://blog.csdn.net/weixin_44718797/article/details/125838661（延迟渲染的过程）

## 三渲二★

参考：

https://www.bilibili.com/video/BV1az42117wz（【UE5】从零开始做原神（32）三渲二材质（兰伯特+Ramp图+边缘光+matcap+高光+水渍效果））

## matcap贴图

参考：

https://blog.csdn.net/csuyuanxing/article/details/135039939（Matcap的原理和应用）

https://www.bilibili.com/video/BV1cU411f7D1（【UE5】从零开始做原神（32）三渲二材质（matcap））

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

https://blog.csdn.net/weixin_40301728/article/details/119744451（Unreal 自定义编辑器（一））、https://blog.csdn.net/weixin_40301728/article/details/119744898（Unreal 自定义编辑器（二））、https://blog.csdn.net/weixin_40301728/article/details/119769063（Unreal 自定义编辑器（三））、https://blog.csdn.net/weixin_40301728/article/details/119814429（Unreal 自定义编辑器（四））、https://blog.csdn.net/weixin_40301728/article/details/119841875（Unreal 自定义编辑器（五））、https://blog.csdn.net/weixin_40301728/article/details/119851108（Unreal 自定义编辑器（六））



还有各种编辑器扩展，用到的时候再去翻阅网络资料

# 资源网站

- 动画

  mixamo.com

- 模型素材

  quixel.com、sketch fab、blender market、poly haven


- HDRI

  hdrihaven.com、hdriabs.com、kitbash3d.com

- 材质/纹理

  textures.com、3dtextures.me（free）、poliigon.com

# 实用工具

## 地形建模

- Gaea

  Gaea导入ue，参考：

  https://blog.csdn.net/ChaoChao66666/article/details/138505947（【Gaea+UE5】创建基本的大型世界场景）

  https://www.bilibili.com/video/BV19c411Z7GZ（Gaea地形完美导入UE最简单流程没有之一）

- WorldCreator

## 材质生成

- Substance Alchemist（初学者）
- Substance Designer（基于节点生成）
- Substance Painter（高级，是Substance Alchemist的高阶版，手绘）
- Mari

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

知乎：

https://www.zhihu.com/people/a-gun-er-58（技术宅阿棍儿，写的东西很深入）、https://www.zhihu.com/people/SuperPandaGX（YivanLee，大佬，写的东西很深入）、https://www.zhihu.com/people/quabqi（quabqi，写得中肯详细易懂）

gitee：

https://gitee.com/timetzhang/LECTURE.UnrealEngine（timetzhang，他整合了很多素材资源）

b站：

https://www.bilibili.com/video/BV12M4m1m7RS（【UE5】从零开始做原神★）

