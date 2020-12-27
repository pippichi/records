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

