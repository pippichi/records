# 配置Anaconda以及pip清华镜像源

参考：

https://blog.csdn.net/weixin_44914727/article/details/130513081（【Python】Anaconda以及Pip配置清华镜像源）

# 类方法、类实例方法和静态方法的区别

参考：

https://blog.csdn.net/qq_41586251/article/details/135068371（深入理解Python中的类方法、类实例方法和静态方法）

https://zhuanlan.zhihu.com/p/21101992（Python类的静态方法和类方法区别）



类方法使用案例：

由于python只允许一个构造函数，因此可以用类方法来模拟其他语言的多个构造函数的情况，比如：

```python
class MyClass:
    def __init__(self, x, y):
        self.x = x
        self.y = y
    
    @classmethod
    def from_string(cls, s):
        x, y = map(int, s.split(','))
        return cls(x, y)
 
obj = MyClass.from_string('5,10')
```



# 反射

## 通过类名字符串创建对象

通常使用`getattr()` 方法和 `globals()` 或 `locals()` 函数来实现

也可用importlib，例如：

```python
# 如果类的声明和创建在不同文件中：

import importlib
# handler为python程序包，内含有__init__.py文件表明其是一个程序包而非普通文件夹，其下有一个text_handler.py文件
text_handler_package = importlib.import_module("handler.text_handler")
# text_handler.py中有一个类，名叫BadSentence
BadSentence = getattr(text_handler_package, "BadSentence")
# 创建BadSentence对象
bad_sentence = BadSentence()


# 如果类的声明和创建在同一个文件中：
import importlib
import sys
# 本文件中有一个类，名叫BadSentence
BadSentence = getattr(sys.modules[__name__], "BadSentence")
# 创建BadSentence对象
bad_sentence = BadSentence()
```

## 无中生有的类对象创建

使用 `type()` 创建一个不存在的类的对象

例如：

```python
# 动态创建一个新类
DynamicClass = type('DynamicClass', (object,), {'__init__': lambda self, value: setattr(self, 'value', value), 
                                                'display': lambda self: print(f"Value is: {self.value}")})
# 普通实例化
dynamic_class = DynamicClass(42)
dynamic_class.display()

# 基于类名字符串的实例化
class_ = globals()["DynamicClass"]
instance = class_(42)
instance.display()
```



# conda虚拟环境

## 复制虚拟环境

参考：

https://blog.csdn.net/qq_37764129/article/details/102496746（复制Anaconda虚拟环境）

https://blog.csdn.net/yyywxk/article/details/140250823（conda 重命名虚拟环境）

https://blog.csdn.net/weixin_45277161/article/details/130693708（conda如何更改虚拟环境的名字）

## 依赖包导入导出

参考：

https://blog.csdn.net/qq_41185868/article/details/121346285（Python：利用pip/conda命令导出/安装当前环境所有的依赖包及其对应的版本号、在新的环境中安装导出的包之详细攻略）

# tqdm

參考：

https://blog.csdn.net/AI_dataloads/article/details/134169038（【python第三方库】tqdm——超详细解读）

# scipy

參考：

https://blog.csdn.net/fengdu78/article/details/131862801（【Python】科学计算库Scipy简易入门）

https://blog.csdn.net/molangmolang/article/details/137616110（scipy介绍）

# jupyter notebook

## 切换到conda虚拟环境

參考：

https://blog.csdn.net/u014264373/article/details/119390267（【最全指南】如何在 Jupyter Notebook 中切换/使用 conda 虚拟环境？）

## 连接远程服务器

参考：

https://blog.csdn.net/MYRLibra/article/details/109599531（使用Jupyter Notebook远程连接服务器） -> https://blog.csdn.net/qq_36603177/article/details/132117549（Jupyter Notebook 7.0 更新远程访问配置）

# pycharm

## 连接远程服务器

参考：

https://blog.csdn.net/qq_45404805/article/details/136784095（保姆级教程！！教你通过【Pycharm远程】连接服务器运行项目代码） -> https://blog.csdn.net/weixin_41012765/article/details/125816981（远程连接（ssh服务+端口开放））

# 彻底清理Conda环境

参考：

https://blog.csdn.net/Pin_BOY/article/details/120479861（Conda的清理（一下少了14G））

https://blog.csdn.net/2401_85342379/article/details/140478398（彻底清理Conda环境：使用conda remove命令的终极指南）

https://blog.csdn.net/2401_85842555/article/details/140477670（优化Conda环境：深入掌握conda clean命令的清理艺术）

https://www.cnblogs.com/yanpeng1535/p/18529787（Conda环境与包管理命令大全使用指南）

# pip忽略依赖包强制安装

参考：

https://blog.csdn.net/qq_45100200/article/details/143358599（pip忽略依赖关系强制安装）