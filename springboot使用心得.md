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

# 注入

## @Import注入类

原本我们在一个类中注入另一个类都是通过先new再注入的，现在可以使用该注解直接注入而不需要new。详见《Spring实战》P61。

利用这个注入的特性，我们还可以通过在某个更加高级的类上面标注@Import({xxx.class, xxx.class}) 来拼装类。详见《Spring实战》P62。

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

# 线程池

