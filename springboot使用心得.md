# 序列化

## @Transient

数据库不存该字段的值

1、直接将@Transient注解直接加在parentName属性上，数据库不会创建parent_name字段，查询也无法获取值；

2、将@Transient注解加在parentName属性的get方法上，数据库会创建parent_name字段，查询可以获取值；

3、将@Transient注解加在parentName属性的set方法上，数据库不会创建parent_name字段，查询可以获取值；



## @JsonProperty

设置字段别名



## @JsonValue

只序列化这个字段

注意一个实体中只能出现一次



## @JsonIgnoreProperties及其属性value

@JsonIgnoreProperties(value = {"xx1", "xx2"})

则序列化的时候字段xx1和xx2会被忽略



## @JsonIgnore

不序列化这个字段



## @Column及其属性columnDefinition

```java
@Column(name = "password", length = 255, columnDefinition = "VARCHAR(255) NOT NULL DEFAULT '' COMMENT '密码'")
```



## @ManyToOne与@JoinColumn

```java
@ManyToOne(cascade = {CascadeType.REMOVE})
@JoinColumn(name = "skill_id", foreignKey = @ForeignKey(name = "FK_Reference_48"), columnDefinition = "INT NOT NULL COMMENT '外键skill_id'")
```



## @Lob与@Basic

```java
@Lob
@Basic(fetch = FetchType.LAZY) // 由于是大文本，需要配合懒加载
```



## @JsonManagedReference与@JsonBackReference

这两个注解直接加在字段属性上面，注解内没有属性

@JsonBackReference和@JsonIgnore很相似，都可用于循环序列化时解决栈溢出的问题（比方说自关联或相互关联的情况）

但是当反序列化的时候@JsonIgnore是无法给关联的对象的字段赋值的，而@JsonBackReference和@JsonManagedReference联用之后就可以使得反序列化的时候给关联的对象的字段也赋值



## @JsonIdentifyInfo

这个注解加在类上面，注解内需指定属性值

@JsonIdentityInfo也可以解决父子之间的依赖关系，但是比上面介绍的两个注解更加的灵活，在@JsonBackReference和@JsonManagedReference两个注解中，我们自己明确类之间的父子关系，但是@JsonIdentityInfo是独立的，解决的是相互之间的依赖关系，没有父子之间的上下关系。

使用方法：

```java
@JsonIdentityInfo(property = "@id",generator = ObjectIdGenerators.IntSequenceGenerator.class) // IntSequenceGenerator用于生成@id的值为1、2、...
// 其中@id是新的唯一标识
效果：
{
  "@id" : 1,
  "name" : "boss",
  "department" : "cto",
  "employees" : [ {
    "@id" : 2,
    "name" : "employee1",
    "boss" : 1
  }, {
    "@id" : 3,
    "name" : "employee2",
    "boss" : 1
  } ]
}
```

```java
@JsonIdentityInfo(property = "name",generator = ObjectIdGenerators.PropertyGenerator.class) // 上面使用@id来唯一标识，现在我们可以利用PropertyGenerator来使用类已经存在的属性名来进行唯一标识
效果：
{
  "name" : "boss",
  "department" : "cto",
  "employees" : [ {
    "name" : "employee1",
    "boss" : "boss"
  }, {
    "name" : "employee2",
    "boss" : "boss"
  } ]
}
```

## @JsonFormat与@DateTimeFormat

@JsonFormat作用域在后端接收从前端传过来的参数以及从后端返回数据给前端的时候

举例：

```java
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
private LocalDateTime time; // 返回前端的time会被格式化，从前端接收的字符串类型的time会被自动转成LocalDateTime
```

```java
@JsonFormat(shape = JsonFormat.Shape.STRING)
private Long id;
```

@DateTimeFormat作用域在后端接收从前端传过来的参数的时候

举例：

```java
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private LocalDateTime time; // 从前端接收的字符串类型的time会被自动转成LocalDateTime
```

两者之间的差别请参考：https://blog.csdn.net/zhou520yue520/article/details/81348926（@DateTimeFormat注解 和 @JsonFormat注解）

## @JsonSerialize

参考：https://mp.baomidou.com/guide/faq.html#%E5%85%B3%E4%BA%8E-long-%E5%9E%8B%E4%B8%BB%E9%94%AE%E5%A1%AB%E5%85%85%E4%B8%8D%E7%94%9F%E6%95%88%E7%9A%84%E9%97%AE%E9%A2%98（ 关于 Long 型主键填充不生效的问题）

```java
@JsonSerialize(using= ToStringSerializer.class) // 可以将返回前端的字段自动转成string类型，避免js接到long型数据时精度丢失问题
private Long indexId;
```

# 加密

## Jasypt实现配置文件中密码字符串加密配置

我们可以编写加密解密工具：

```java
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class JasyptUtil {
    /**
     * Jasypt生成加密结果
     * 
     * @param password 配置文件中设定的加密密
     * @param value 加密值
     * @return
     */
    public static String encyptPwd(String password, String value) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(cryptor(password));
        String result = encryptor.encrypt(value);
        return result;
    }

    /**
     * 解密
     * 
     * @param password 配置文件中设定的加密密码
     * @param value 解密密文
     * @return
     */
    public static String decyptPwd(String password, String value) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(cryptor(password));
        String result = encryptor.decrypt(value);
        return result;
    }

    public static SimpleStringPBEConfig cryptor(String password) {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        return config;
    }

    // public static void main(String[] args) {
    // // 加密
    // System.out.println(encyptPwd("neusoft", "root1234"));
    // // 解密
    // System.out.println(decyptPwd("neusoft",
    // "VnCioJPCXOOPIOx5Aq9XuigNH6OuaOoz"));
    // }
}
```

修改配置文件

<font color="red">- spring.datasource.password=123456</font>

<font color="green">+ spring.datasource.password=ENC(VShsidDhfoasi&@#N%#@$#@SDoOidsaDaD144sSFWSEssQD)</font>

这里这个密文就是通过上面这个加密解密工具类生成的



之后配置加密私钥

<font color="green">+ jasypt.encryptor.password=xxx</font>

私钥自己定义就行



需要使用的地方调用工具类解密

```java
String password = ""; // 配置文件中的私钥
String pwd = ""; // 加密后的密文
JasyptUtil.decyptPwd(password, pwd)
```

# @RequestMapping

url路径还能这么写：

```java
@RequestMapping("/**") // **表示 路径 / 之后的所有内容都匹配
@RequestMapping("/xx/**") // **表示 路径 /xx/ 之后的所有内容都匹配
```

## 属性produces以及consumers

参考：https://blog.csdn.net/bingguang1993/article/details/82253639（RequestMapping中produces属性作用）、https://blog.csdn.net/gyfd123/article/details/121578581（SpingMVC的@RequestMapping注解属性）、https://www.dazhuanlan.com/liufeng126/topics/1534697（RequestMapping 属性解释）

# @Produces

作用类似`@RequestMapping中的属性produces`

参考：https://blog.csdn.net/shadowcw/article/details/88093081

# @ControllerAdvice

有三种应用场景

## 配合@ExceptionHandler实现全局异常处理

```java
// 加了下面这个注解，该类中定义的函数都会变成全局的。如果不加，那这些函数就只会在当前类有效
@ControllerAdvice
public class MyGlobalExceptionHandler {
    // 注意下面这个注解也可以不指定NullpointerException.class这个参数，如果什么参数都不指定，那他将自动进行映射
    @ExceptionHandler(NullpointerException.class)
    // 全局的NullpointerException都会被下面这个函数处理
    public ModelAndView customException(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", e.getMessage());
        mv.setViewName("myerror");
        return mv;
    }
    
    @ExceptionHandler(Exception.class)
    // 如果添加下面这个注解，则会在controller中接口调用发生Exception错误之后返回下面函数中定义的东西
    @ResponseBody
    public ModelAndView customException(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", e.getMessage());
        mv.setViewName("myerror");
        return mv;
    }
}
```



## 配合@ModelAttribute实现全局数据绑定

定义全局数据：

```java 
@ControllerAdvice
public class MyGlobalExceptionHandler {
    // 定义key为md，值为下面函数返回的map
    @ModelAttribute(name = "md")
    public Map<String,Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }
}
```

取出全局数据：

```java
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model) {
        // 使用Model的asMap()取出全局的数据，这里不出意外应该会取出：md(key) --->  {"age": 99, "gender": "男"}(value)
        Map<String, Object> map = model.asMap();
        System.out.println(map);
        return "hello controller advice";
    }
}
```



## 配合@InitBinder和全局数据预处理

假设有两个实体类：

```java
public class Book {
    private String name;
    private Long price;
    //getter/setter
}
public class Author {
    private String name;
    private Integer age;
    //getter/setter
}
```

有一个controller需要同时传入上面两个实体类：

```java
@PostMapping("/book")
public void addBook(Book book, Author author) {
    ...
}
```

由于两个实体类都有name这个字段，因此需要进行区分

我们可以这么修改上面这个controller接口：

```java
@PostMapping("/book")
public void addBook(@ModelAttribute("b") Book book, @ModelAttribute("a") Author author) {
    ...
}
```

并且在@ControllerAdvice标记的类中添加如下代码：

```java
// @InitBinder("b") 注解表示该方法用来处理和Book和相关的参数,在方法中,给参数添加一个 b 前缀,即请求参数要有b前缀.
@InitBinder("b")
public void b(WebDataBinder binder) {
    binder.setFieldDefaultPrefix("b.");
}
@InitBinder("a")
public void a(WebDataBinder binder) {
    binder.setFieldDefaultPrefix("a.");
}
```

此时就需要这样发送请求了：

http://127.0.0.1:8080/book?b.name=三国演义&b.price=99&a.name=罗贯中&a.age=100

可以看到name字段非常自然的被区分开了



# 单元测试

## @Nested

测试类中嵌套测试类

## @DisplayName

更改测试的名称

## @BeforeAll

- 在当前类的所有测试方法之前执行。
- 注解在静态方法上。
- 此方法可以包含一些初始化代码。

## @AfterAll

- 在当前类中的所有测试方法之后执行。
- 注解在静态方法上。
- 此方法可以包含一些清理代码。

## @BeforeEach

- 在每个测试方法之前执行。
- 注解在非静态方法上。
- 可以重新初始化测试方法所需要使用的类的某些属性。

## @AfterEach

- 在每个测试方法之后执行。
- 注解在非静态方法上。
- 可以回滚测试方法引起的数据库修改。

# 注入以及上下文环境

## @Import注入类

原本我们在一个类中注入另一个类都是通过先new再注入的，现在可以使用该注解直接注入而不需要new。详见《Spring实战》P61。

利用这个注入的特性，我们还可以通过在某个更加高级的类上面标注@Import({xxx.class, xxx.class}) 来拼装类。详见《Spring实战》P62。

## ApplicationContextAware接口获取上下文环境

参考：https://www.cnblogs.com/loong-hon/p/10917755.html（ApplicationContextAware接口的作用）

## AutowireCapableBeanFactory对容器外的Bean进行依赖注入

参考：https://www.jianshu.com/p/14dd69b5c516、https://blog.csdn.net/qq_36838191/article/details/80702608

## AutowireCapableBeanFactory

以下是一段spring的applicationContext中的代码：

```java
/**
* Expose AutowireCapableBeanFactory functionality for this context.
* <p>This is not typically used by application code, except for the purpose
* of initializing bean instances that live outside the application context,
* applying the Spring bean lifecycle (fully or partly) to them.
* <p>Alternatively, the internal BeanFactory exposed by the
* {@link ConfigurableApplicationContext} interface offers access to the
* AutowireCapableBeanFactory interface too. The present method mainly
* serves as convenient, specific facility on the ApplicationContext
* interface itself.
* @return the AutowireCapableBeanFactory for this context
* @throws IllegalStateException if the context does not support
* the AutowireCapableBeanFactory interface or does not hold an autowire-capable
* bean factory yet (usually if {@code refresh()} has never been called)
* @see ConfigurableApplicationContext#refresh()
* @see ConfigurableApplicationContext#getBeanFactory()
*/
AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException;
```

这里说的意思就是通过getAutowireCapableBeanFactory这个方法将 AutowireCapableBeanFactory这个接口暴露给外部使用， AutowireCapableBeanFactory这个接口一般在applicationContext的内部是较少使用的，它的功能主要是为了装配applicationContext管理之外的Bean。

另一段是AutowireCapableBeanFactory接口中的源码：

```java
/**
* Autowire the bean properties of the given bean instance by name or type.
* Can also be invoked with {@code AUTOWIRE_NO} in order to just apply
* after-instantiation callbacks (e.g. for annotation-driven injection).
* <p>Does <i>not</i> apply standard {@link BeanPostProcessor BeanPostProcessors}
* callbacks or perform any further initialization of the bean. This interface
* offers distinct, fine-grained operations for those purposes, for example
* {@link #initializeBean}. However, {@link InstantiationAwareBeanPostProcessor}
* callbacks are applied, if applicable to the configuration of the instance.
* @param existingBean the existing bean instance
* @param autowireMode by name or type, using the constants in this interface
* @param dependencyCheck whether to perform a dependency check for object
* references in the bean instance
* @throws BeansException if wiring failed
* @see #AUTOWIRE_BY_NAME
* @see #AUTOWIRE_BY_TYPE
* @see #AUTOWIRE_NO
*/
void autowireBeanProperties(Object existingBean, int autowireMode, boolean dependencyCheck) throws BeansException;
```

这个方法的作用就是将传入的第一个参数按照spring中按name或者按type装备的方法将传入的Bean的各个properties给装配上。

总结来讲：

```
	对于想要拥有自动装配能力，并且想把这种能力暴露给外部应用的BeanFactory类需要实现此接口。 
	正常情况下，不要使用此接口，应该更倾向于使用BeanFactory或者ListableBeanFactory接口。此接口主要是针对框架之外，没有向Spring托管Bean的应用。通过暴露此功能，Spring框架之外的程序，具有自动装配等Spring的功能。 
	需要注意的是，ApplicationContext接口并没有实现此接口，因为应用代码很少用到此功能，如果确实需要的话，可以调用ApplicationContext的getAutowireCapableBeanFactory方法，来获取此接口的实例。 
	如果一个类实现了此接口，那么很大程度上它还需要实现BeanFactoryAware接口。它可以在应用上下文中返回BeanFactory。
```

示例：

```java
@Component
public class JobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory  capableBeanFactory;

    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        // 调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        // 进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
```

## SpringBoot使用AspectJ（CTW）下不能注入SpringIOC容器中的Bean的解决方案

参考：https://blog.csdn.net/weixin_43697006/article/details/105289423

# 静态文件存储位置

在IDEA中双击“shift”将“CLASSPATH_RESOURCE_LOCATIONS”复制进去就可以看到：

```java
private static final String[] CLASSPATH_RESOURCE_LOCATIONS = new String[]{
    "classpath:/META-INF/resources/", 
    "classpath:/resources/", 
    "classpath:/static/", 
    "classpath:/public/"
};
```

# @Lazy

@Lazy可以解决循环依赖，同时也可以实现延迟加载

用法：

- @Lazy(value = false)

  如果是false，这个注解加了跟没加一样，对象不使用延迟加载，会在Spring启动的时候，或者说初始化的时候就直接创建

- @Lazy(value = true)

  使用延迟加载，在Spring启动的时候延迟加载bean，即在调用某个bean的时候再去初始化

  降低了springIOC容器启动的加载时间，也可以解决循环依赖问题

# @Async与SpringBoot线程池配置

具体看张润华的`supcon-parent`

**@Async与线程池搭配使用：**

```java
@Async("taskExecutorName") // 注意：如果不指定要使用的线程池名，比如直接这么用：@Async，那么在执行异步任务的时候spring是直接new一个thread去执行的
```

# filter

过滤器，包括@WebFilter等，具体查网络资料

- **OncePerRequestFilter**

  每次请求的最开始都会执行这个过滤，适合做权限认证

# interceptor

拦截器，具体查网络资料

# listener

监听器，具体查网络资料

# @DependsOn

当某个Bean需要依赖其他的Bean，即需要手动让依赖的Bean先加载，之后再加载这个Bean的时候就可以使用`@DependsOn`注解

```java
@Bean("supExecutor")
@DependsOn({"threadPoolProperties", "springUtil"})
@ConditionalOnMissingBean(name = "supExecutor")
public Executor taskExecutor() {
}
```

# @CrossOrigin

跨域注解

```java
@CrossOrigin
@PostMapping(value = "/executeToHere/{socketSessionId}")
public ObpSimpleResponse executeToHere(@RequestBody ExecuteToHereParam executeToHereParam, @PathVariable @ApiParam("socketSessionId") String socketSessionId) {}
```

具体使用查看文章：https://blog.csdn.net/qq_18671415/article/details/109275495

# @SpringBootApplication

## 属性exclude

```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
```

该注解的作用是，排除自动注入数据源的配置（取消数据库配置），一般使用在客户端（消费者）服务中

当前项目其实并不需要数据源。查其根源是依赖方提供的API依赖中引用了一些多余的依赖触发了该自动化配置的加载，那么使用exclude属性将它排除即可。

# @ComponentScan

如果一个类标注了该注解，那么该类所在目录的文件夹以及该文件夹下的所有子文件夹及子文件都会被扫描

例子：

![image-20210609194713563](springboot使用心得.assets/image-20210609194713563.png)

`@SpringBootApplication`中包含了`@ComponentScan`，因此`OrderApplication80.java`所在目录的文件夹`springcloud`及其子文件与子文件夹（简单点讲就是文件夹`springcloud`下的所有文件）都会被扫描，而文件夹`myrule`不在文件夹`springcloud`下，因此`myrule`不会被扫描

# @Scope与@SessionScope、@RequestScope

`@Scope参考博客：`https://www.cnblogs.com/nemowang1996/p/11195266.html

`@Scope的属性proxyMode（代理模式）的使用场景（坑点：在代理模式为TARGET_CLASS时，可能会导致Bean的set方法失效，具体案例：在mcs项目的websocket配置中注入了Bean，使用代理模式为：TARGET_CLASS，该Bean的set方法失效）`：https://blog.csdn.net/z1353095373/article/details/108234954

`@SessionScope、@RequestScope参考博客：`https://blog.csdn.net/xyjy11/article/details/114201623

# Spring Boot使用CommandLineRunner接口完成资源初始化

参考：https://blog.csdn.net/lk142500/article/details/90270592

# 使用aop读取到项目下所有被注解标注的类或方法

具体查看网络资料

# 配置文件

## `application.yml`、`bootstrap.yml`以及`application-override.yml`

`application.yml`是用户级的资源配置项

`bootstrap.yml`是系统级的，优先级更高

Spring Cloud会创建一个`Bootstrap Context`，作为Spring应用的`Application Context`的父上下文，初始化的时候，`Bootstrap Context`负责从外部源加载配置属性并解析配置。这两个上下文共享一个从外部获取的`Encironment`。

`Bootstrap`属性有高优先级，默认情况下，它们不会被本地配置覆盖，`Bootstrap Context`和`Application Context`有着不同的约定，所以新增了一个`bootstrap.yml`文件，保证`Bootstrap Context`和`Application Context`配置的分离。

要将Client模块下的`application.yml`文件改为`bootstrap.yml`这是很关键的，因为`bootstrap.yml`是比`application.yml`先加载的，`bootstrap.yml`优先级高于`application.yml`

`application-override.yml`用于强制覆盖父工程`.yml`中的配置

## 激活其他配置文件

`spring:profiles:include的妙用`

参考博客：https://blog.csdn.net/jasnet_u/article/details/82995845

## 激活配置文件组

`spring:profiles:group配合spring:profiles:include`

示例：

```yaml
# 假设有配置文件：
	# application.yml
	# application-base-log-prod.yml
	# application-base-mp.yml
	# application-base-json.yml
	# application-base-admin.yml
	# application-base-i18n.yml
	# application-base-knif4j.yml
	# application-base-fegin.yml
	# application-base-log-dev.yml
	# application-base-log.yml

# 那么在配置文件application.yml中我们这样写就可以对配置文件进行分组并且激活base-prod组
---
spring:
  profiles:
    group:
      base-prod: base-log-prod,base-mp,base-json,base-admin,base-i18n,base-knif4j,base-fegin
---
spring:
  profiles:
    group:
      base-dev: base-log-dev,base-mp,base-json,base-admin,base-i18n,base-knif4j,base-fegin
---
spring:
  profiles:
    include: base-prod
```



# 接口文档生成与增强

使用swagger配合knife4j

# 文件系统

可以使用minio或者fdfs

# 网页报表系统

可以使用Ureport2

# 系统监控

除了使用`Spring Boot Admin`之外，还可以用`Prometheus`配合`Spring Boot Admin`来使用

参考：https://blog.csdn.net/forezp/article/details/107465307（Spring Boot集成普罗米修斯）、https://blog.csdn.net/forezp/article/details/86105850（Spring Boot Admin）、https://blog.csdn.net/feidie436/article/details/81381841（Spring Boot Admin配置属性详解）

# SpringCloud+Spring Security+OAuth2 + JWT + Gateway

参考：https://blog.csdn.net/lbjfish/article/details/109187316

# SpringBoot整合websocket

简单整合使用非常简单，直接看mcs项目

我们来看看websocket详细配置，比方说websocket拦截器、websocket监听器、websocket用户权限认证等

参考：https://blog.csdn.net/a2231476020/article/details/99465532（WebSocket拦截器和监听器）、https://blog.csdn.net/russle/article/details/86376030（WebSocket拦截器）、https://blog.csdn.net/July_whj/article/details/108947207?ivk_sa=1024320u（WebSocket增加用户权限验证）

# 循环依赖问题

使用构造器注入就算没用@Async也会有循环依赖问题，在没有使用@Async的情况下最好不要用构造器注入以免发生循环依赖：

```java
@Service
public class WarningHistoryServiceImpl implements IWarningHistoryService {
    
    // 不要用构造器注入，以免发生循环依赖
//    private final IWarningItemService warningItemService;
//    public WarningHistoryServiceImpl(IWarningItemService warningItemService) {
//    	this.warningItemService = warningItemService;
//    }
    
    // 直接这么写虽然会有警告提示，但是不会导致循环依赖
    @Autowired
    private IWarningItemService warningItemService;
}
```

对于@Async导致的循环依赖，可以使用：@Lazy懒加载或@ComponentScan(lazyInit = true)来解决，具体参考：https://www.cnblogs.com/iceggboom/p/14393725.html（Spring:解决因@Async引起的循环依赖报错）

# AutowireCapableBeanFactory（容器外的Bean使用依赖注入）

参考：https://www.jianshu.com/p/14dd69b5c516、https://blog.csdn.net/qq_36838191/article/details/80702608