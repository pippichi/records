## 使用mybatis_plus

首先由于mybatis_plus是在mybatis上的一层无侵入的封装，所以它是依赖mybatis的，因此需要导入所有mybatis相关的包

然后导入mybatis_plus的包

然后将mybatis的SqlSessionFactory替换成mybatis_plus的MybatisSqlSessionFactory即可（简单来讲就是替换一下工厂类即可）

之后就可以开始用了：

接口：

```java
// BaseMapper<Student>中的Student告诉mybatis_plus如果要生成基础sql查询，请参考Student类及其属性
public interface StudentMapper extends BaseMapper<Student>{}
```

测试：

```java
// 获取String容器对象
ApplicationContext ac = new ClassPathXmlApplicationContext("applicationcontext.xml");
// 获取StudentMapper接口的实例化对象
StudentMapper studentMapper = (StudentMapper) ac.getBean("studentMapper");
//// 查询
// selectList方法：查询所有的学生信息
List<Student> students = studentMapper.selectList(null);
// selectById方法：根据id查询
Student student = studentMapper.selectById(1);
// selectByMap方法：根据指定条件完成查询
Map<String, Object> map = new HashMap<>();
map.put("s_name", "张三");
map.put("sage", 20);
List<Student> stds = studentMapper.selectByMap(map);
// selectBatchIds方法：根据id集合查询数据
List<Integer> ids = new ArrayList<>();
ids.add(1);
ids.add(2);
List<Student> stds = studentMapper.selectBatchIds(ids);
// 新增学生信息
Student s = new Student();
s.setAge(1);
s.setName("qq");
int i = studentMapper.insert(s);
// 修改学生信息
Student s1 = new Student();
s1.setId(1);
s1.setName("yy");
int i = studentMapper.updateById(s1); // 只更新有值的属性，null值的属性不参与更新
//// 删除学生信息
// deleteById方法：根据ID删除学生信息
int i = studentMapper.deleteById(1);
// deleteMap方法：根据指定字段完成数据删除
// 注意：传入的map集合中存储了要删除的数据的键值对，键名为数据库中的字段的名称，而不是实体类的属性名
Map<String, Object> map = new HashMap<>();
map.put("s_name", "赵六");
map.put("sage", 40);
int i = studentMapper.deleteByMap(map);
// deleteBatchIds方法：多选删除，将符合ID要求的数据全部删除
// 参数：存储了要删除的数据的id的集合
List<Integer> ids = new ArrayList<>();
ids.add(1);
ids.add(2);
int i = studentMapper.deleteBatchIds(ids);
```



## @TableName、@TableId与@TableField

由于mybatis_plus是根据实体类来反向推测可能用到的sql查询从而免去我们手动写基本sql查询的，而有时候实体类类名或属性和数据库表名或字段名不一定对的上，这个时候就需要用@TableName("表名")和@TableField("字段名")的方式来指定了

@TableId是在实体类对应的主键属性上使用的，用来表明该属性是主键，并且需要说明主键的设置类型，比如：自增。

而且加了@TableId这个注解之后可以实现主键回填（那么为什么不加的时候新增还是可以做到数据库表中的主键自增，但是回填的时候实体类的主键还是为null呢？那是因为新增的时候mbp一看实体类有个属性跟数据库表的主键对上了，那就会判定这个属性就是主键，那就会正常生成记录并自增主键，但是回填的时候它没法判断谁是主键，因此没法回填主键）

其他注解及属性请看mybatis_plus官方文档



需要注意的是@TableField注解有一个属性叫exist，它可以用来排除实体类字段。我们知道mp默认实体类所有属性都对应到数据表的字段，但是有些属性是不存在于数据表的（或者说是我们自己加的属性，但是不希望mp把它当成数据表的某个字段），此时就可以这么用：

```java
@TableField(exist = false)
private String customid;
```





## Wrapper条件构造器

用于完成复杂sql

具体实现类：QueryWrapper

举例：

```java
/*
* 条件构造器是以Java对象的形式将数据操作的筛选条件描述出来，然后由mp将其翻译成对应的sql判断并拼接在sql语句上
*/
// 获取String容器对象
ApplicationContext ac = new ClassPathXmlApplicationContext("applicationcontext.xml");
// 获取StudentMapper接口的实例化对象
StudentMapper studentMapper = (StudentMapper) ac.getBean("studentMapper");
// 创建条件构造器对象
QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
// queryWrapper.eq("sage", 20);
// queryWrapper.or();
// queryWrapper.eq("s_name", "张三");
// 或者可以这么写：
queryWrapper.eq("sage", 29).or().eq("s_name", "张三");
// 根据条件查询数据
List<Student> stds = studentMapper.selectList(queryWrapper); // 同理增删改，具体看文档
```

## 分页器配置

可以在Spring的applicationcontext.xml配置文件中配置：

```xml
<!--配置工厂bean-->
<!--配置了这个bean意味着mp的SqlSessionFactory就会变成这个bean，后续的sql操作要用到的SqlSession就会通过它来实例化-->
<bean id="factory" class="com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean">
	<!--配置mp分页插件-->
    <!--下面的标签（property、array、bean）表示它是一个数组属性，属性名为plugins，数组里面装了bean-->
    <property name="plugins">
    	<array>
        	<!--配置分页插件：拦截对象-->
            <bean id="page" class="com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor">
                <!--value="mysql"表示指定方言为mysql，指明要进行分页操作的数据库-->
            	<property name="dialectType" value="mysql"></property>
            </bean>
        </array>
    </property>
</bean>
```

测试：

```java
// 获取String容器对象
ApplicationContext ac = new ClassPathXmlApplicationContext("applicationcontext.xml");
// 获取StudentMapper接口的实例化对象
StudentMapper studentMapper = (StudentMapper) ac.getBean("studentMapper");
// 创建Page对象封装分页查询条件
IPage<Student> page = new Page<>(1, 2);
// 查询
IPage<Student> studentIPage = studentMapper.selectPage(page, null);
// 分页查询结果
studentIPage.getRecords();
// 分页总共的数据量
studentIPage.getTotal();
// 分页总共的页码量
studentIPage.getPages();
// 其他还有很多方法...
```

