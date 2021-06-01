# 反射

```java
首先 Class c = ArrayList.class;
```



## 判断变量是否为基础数据类型

```java
c.isPrimitive()
```



## 判断c是否是List类的父类

```java
c.isAssignableFrom(List.class)
```



## 判断c是否是List类的子类

```java
c isinstanceof List
```



## 获取包含泛型类型的类型以及泛型真正的类型

```java
Map<String,Integer> map = new HashMap<String,Integer>();
Field f = c.getDeclaredField("map");
// 获取包含泛型的类型
Type t = f.getGenericType();
/**
  * Type这个类里面没有任何的方法，所以需要调用子类的方法，那么大的类型转到小的类型，需要强转！
*/
ParameterizedType pt = (ParameterizedType)t;//强转到其子类
/**
 *  Type[] getActualTypeArguments()
    返回表示此类型实际类型参数的 Type对象的数组。
    Type getOwnerType()
    返回 Type 对象，表示此类型是其成员之一的类型。
    Type getRawType()
    返回 Type 对象，表示声明此类型的类或接口。
*/
t = pt.getRawType();//类型的类或接口
// 获取泛型真正的类型
Type[] ts = pt.getActualTypeArguments();
```

# 关键字

## strictfp

<span style="font-weight:bold">strictfp，即strict float point（精确浮点）</span>

strictfp 关键字可应用于类、接口或方法。使用 strictfp 关键字声明一个方法时，该方法中所有的float和double表达式都严格遵守FP-strict的限制,符合IEEE-754规范。当对一个类或接口 使用 strictfp 关键字时，该类中的所有代码，包括嵌套类型中的初始设定值和代码，都将严格地进行计算。严格约束意味着所有表达式的结果都必须是 IEEE 754 算法对操作数预期的结果，以单精度和双精度格式表示。
　　如果你想让你的浮点运算更加精确，而且不会因为不同的硬件平台所执行的结果不一致的话，可以用关键字strictfp.

例子如下：

不使用strictfp：

```java
public class Test {
    private static double aDouble = 0.0555500333333212d;
    private static float aFloat = 0.0333000000222f;
    public static void main(String[] args) {
        double cDouble = aDouble / aFloat;
        System.out.println("aDouble: " + aDouble);
        System.out.println("aFloat: " + aFloat);
        System.out.println("cDouble: " + cDouble);
    }
}
```

使用strictfp：

```java
public strictfp class Test {
    private static double aDouble = 0.0555500333333212d;
    private static float aFloat = 0.0333000000222f;
    public static void main(String[] args) {
        double cDouble = aDouble / aFloat;
        System.out.println("aDouble: " + aDouble);
        System.out.println("aFloat: " + aFloat);
        System.out.println("cDouble: " + cDouble);
    }
}
```

结果：

不使用strictfp：

```java
aDouble:0.0555500333333212
aFloat:3.33000016E11
cDouble:1.6681690896577544E-13
```



使用strictfp：

```java
aDouble:0.0555500333333212
aFloat:0.0333
cDouble:1.668169110346482
```

# 权限控制

| 修饰词     | 本类 | 同一个包的类 | 继承类 | 其他类 |
| ---------- | ---- | ------------ | ------ | ------ |
| private    | 1    | 0            | 0      | 0      |
| 无（默认） | 1    | 1            | 0      | 0      |
| protected  | 1    | 1            | 1      | 0      |
| public     | 1    | 1            | 1      | 1      |

# 注解

## @PostConstruct与@PreDestory

从Java EE 5规范开始，Servlet中增加了两个影响Servlet生命周期的注解（Annotation）：

@PostConstruct和@PreDestory

这两个注解被用来修饰一个非静态的void()方法，而且这个方法不能有抛出异常声明。写法有如下两种方式：

```java
@PostConstruct
public void someMethod(){}
```

或者

```java
public @PostConstruct void someMethod(){}
```

被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。@PostConstruct修饰的方法在构造函数之后执行，在init()方法之前执行。@PreDestory修饰的方法在destroy()方法执行之后执行，在Servlet被彻底卸载之前执行。

![image-20210330213805539](Java补充.assets/image-20210330213805539.png)

使用场景：

在servlet初始化加载之前可能会需要处理一些东西，像加载缓存，加载线程池等，此时@PostConstruct就能派上用场了，当然，也可以不使用该注解，如果我们要加载或处理某些东西，我们完全可以在构造器初始化的时候就直接处理掉，只不过这种方法需要自己重写构造器。



另外，spring中Constructor、@Autowired、@PostConstruct的顺序

其实从依赖注入的字面意思就可以知道，要将对象p注入到对象a，那么首先就必须得生成对象a和对象p，才能执行注入。所以，如果一个类A中有个成员变量p被@Autowried注解，那么 **@Autowired注入是发生在A的构造方法执行完之后的**。

如果想在生成对象时完成某些初始化操作，而偏偏这些初始化操作又依赖于依赖注入，那么久无法在构造函数中实现。为此，可以使用@PostConstruct注解一个方法来完成初始化，**@PostConstruct注解的方法将会在依赖注入完成后被自动调用**。

Constructor >> @Autowired >> @PostConstruct

举例： 

```java
public class AAA{
    @Autowired
    private BBB b;
    
    public AAA(){
        System.out.println("此时b还未被注入：b = " + b);
    }
    
    @PostConstruct
    private void init(){
        System.out.println("@PostConstruct将在依赖注入完成之后被自动调用：b = " + b);
    }
}
```



# 生成随机值

## UUID包的使用

```java
UUID.randomUUID();
```

# Websocket

具体看`alg-manager`

# 雪花算法

具体使用方法查看hutool源码或deya门户项目源码

# jarFile

jarFile可用于读取jar文件中的内容

应用场景：比方说有时候我们需要读取jar文件中某个class的注解，但是jar文件是被打包过的，没办法直接读取到它里面的内容，此时就可以用jarFile

参考文章：https://blog.csdn.net/qq_40951086/article/details/78580665

# RunTime

用于执行shell命令、获取计算机cpu核数等

# java中的各种命令参数

- 命令行参数

  ```
  命令行参数就是类似与c语言的命令行参数，这些参数会传给main函数，也就是java中 public static void main(String[] args) 的那个String数组。但是需要注意的是，c语言的main行数中传入的参数，arv[0]是程序本身的名字，比如program1 option1 option2,那么arv[0]就是program1本身。但是java的命令函参数确实从0开始的，也就是说，java中的第一个命令行参数是的args[0]，举个例子 java program1 option1 option2 运行一个java程序的话，args[0]获取到的是option1。
  ```

- 系统属性参数

  ```
  系统属性参数也是供应用程序使用的，并且是以key=value这样的形式提供的，在程序的任何一个地方，都可以通过System.getProperty("key")获取到对应的value值。
  
  在官方文档中对系统属性参数的描述是这样的：
  Set a system property value. If value is a string that contains spaces, you must enclose the string in double quotes:
  java -Dfoo="some string" SomeClass
  
  系统属性参数传入的时候需要带一个横杆和大写字母D，比如-Dfuck.abc="1234"这样的。在你业务代码中，你就可以使用它了：System.getProperty("fuck.abc")，获取"1234"。不过系统属性参数一般都是用来开启一些官方开关的，比如加入-Djdk.internal.lambda.dumpProxyClasses="/home/xxx"，你就可以把java8中lambda表达式的代理类字节码dump出来。
  ```

- jvm参数

  ```
  jvm参数就是和jvm相关的参数了，比如配置gc、配置堆大小、配置classpath等等
  jvm参数分为标准参数、扩展参数和不稳定参数
  标准参数是一定有效，向后兼容的，且所有的jvm都必须要实现的，比如-classpath，这类参数是横杆直接跟参数名
  扩展参数是不保证向后向后兼容，不强值要求所有jvm实现都要支持，不保证后续版本不会取消的，这类参数的形式是-Xname，横杠和一个大写的X开头
  不稳定参数就是非常不稳定，可能只是特定版本的，特点是-XXname，横杆后带两个大写X开头
  ```

如果想查看具体的参数的含义，最好的方法就是看官方文档，或者直接man java一下，也可以参考这篇文章[《Java 命令行运行参数》](https://www.cnblogs.com/jtlgb/p/8466138.html)

其实，只要java -h以下，就可以看到这些说明。java命令的语法为：

```
java [-options] class [args...]
           (to execute a class)
```

或者

```
java [-options] -jar jarfile [args...]
           (to execute a jar file)
```

所以，前面说的严格意义上全都是不对的，java中的命令行参数只有option 和 args两类。上面说的三类中第一类对应args，后面两类都是属于option的，那才是jvm的参数。

# spring boot 传递参数

spring boot 打成jar包后 通过命令行传入的参数有3种实现方式：

```
java -jar xxx.jar aaa bbb cccc
传了3个参数，分别是aaa,bbb,ccc
通过main方法的参数获取
```

```
java -jar xxx.jar -Da1=aaa -Db1=bbb -Dc1=ccc
通过  System.getProperty("aaa","1"); 方式获取，作为环境变量
注意：java -Xms10m -Xmx512m xx.jar -DconfigPath=/root 可能不生效-D配置，在程序中读取不到
最好改成：java -Xms10m -Xmx512m -DconfigPath=/root xx.jar 这样就可以读取到了
```

```
java -jar xxx.jar --a1=aaa --b1=bbb --server.port=8080
是springboot的写法，可以通过@Value("${a1}") 获取；
--server.port=8080可以直接指定spring boot的端口号
```

