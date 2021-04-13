## mybatis的xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--开启log4j的日志打印。默认就是开启的，可以不配置-->
    <settings>
        <!--当然，这个value除了是LOG4J还可以是SLF4J-->
    	<setting name="logImpl" value="LOG4J"/>
    </settings>
    <!--配置实体类的别名。工作中一般不用，因为一旦出问题就很难搞了-->
    <typeAliases>
    	<!--给某个实体类配置别名，在Mapper文件中使用别名即可表示对应的实体类，简化代码的编写-->
        <!-- <typeAlias type="com.bjsxt.pojo.Emp" alias="emp"></typeAlias> -->
        <!-- 但是上面这种写法还是很麻烦，设想如果有一百个实体类，那么我们还是要配置一百次设置别名，因此我们换用下面这种：-->
        <package name="com.bjsxt.pojo"/> <!-- 默认该包下的所有类的类名即为别名，不区分大小写。这意味着mapper配置文件中的类似com.bjsxt.pojo.Emp的写法都可以直接写成Emp或者emp -->
    </typeAliases>
	<!--配置数据库环境，default的值为某个environment的id的值，表示当前使用的数据库环境-->
    <environments default="mysql1">
        <!--表示一个具体的数据库环境，可以配置多个-->
    	<environment id="mysql1">
            <!--表明事务的管理仍然使用原生jdbc的方式-->
        	<transactionManager type="JDBC"></transactionManager>
            <!--配置数据库的连接参数，使用数据库连接池技术-->
            <!--有POOLED、UNPOOLED和JNDI，具体介绍请看本章节的dataSource介绍-->
            <dataSource type="POOLED">
            	<property name="driver" value="com.mysql.cj.jdbc.Driver"/>
            	<property name="url" value="jdbc:mysql://localhost:3306/db_name?serverTimezone=UTC"/>
            	<property name="username" value="root"/>
            	<property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
    <!--配置Mapper文件的路径，用来告诉SqlSession mapper配置文件在哪，这样在最开始SqlSession就可以知道mapper配置文件有哪几个，就不需要在使用SqlSession做增删改查的时候再去找对应的mapper文件了-->
    <mappers>
    	<mapper resource="com/bjsxt/mapper/EmpMapper.xml"/>
    </mappers>
</configuration>
```

### transactionManager

![image-20210411185646240](mybatis.assets/image-20210411185646240.png)

在mybatis中有两种事务管理器类型（也就是type=”[JDBC|MANAGED]“）;

JDBC - 这个配置直接简单实用了JDBC的提交和回滚设置。它依赖于从数据源得到的连接来管理事务范围

MANAGED - 这个配置几乎没做什么。它从来不提交或回滚一个连接。而它会让容器来管理事务的整个生命周期（比如Spring或JEE应用服务器的上下文）

### dataSource

有三种内建的数据源类型（也就是 type=”？？？“）：

- UNPOOLED

  这个数据源的实现是每次被请求时简单打开和关闭连接。它有一点慢，这是对简单应用程序的一个很好的选择，因为它不需要及时的可用连接。UNPOOLED类型的数据源仅需配置以下5种属性：

  - driver

    这是JDBC驱动的Java类的完全限定名

  - url

    这是数据库的JDBC URL地址

  - usernama

    登录数据库的用户名

  - password

    登录数据库的密码

  - defaultTransactionIsolationLevel

    默认的连接事务隔离级别

- POOLED

  这是JDBC连接对象的数据源连接池的实现，用来避免创建新的连接实例时必要的初始连接和认证时间。一种当前Web应用程序用来快速响应请求很流行的方法。除了上述提到UNPOOLED下的属性外，还有更多属性用来配置POOLED的数据源：

  - poolMaximumActiveConnections

    在任意时间可以存在的活动（也就是正在使用）连接数量，默认值：10

  - poolMaximumIdleConnections

    任意时间可能存在的空闲连接数

  - pool MaximumCheckoutTime

    在被强制返回之前，池中连接被检出（checked out）时间，默认值：20000毫秒（即20秒）

  - poolTimeToWait

    这是一个底层设置，如果获取连接花费了相当长的时间，连接池会打印状态日志并重新尝试获取一个连接（避免在误配置的情况下一直安静的失败），默认值：20000毫秒（即20秒）

  - poolMaximumLocalBadConnectionTolerance

    这是一个关于坏连接容忍度的底层设置，作用于每一个尝试从缓存池获取连接的线程。如果这个线程获取到的是一个坏的连接，那么这个数据源允许这个线程尝试重新获取一个新连接，但这个重新尝试次数不应超过poolMaximumIdleConnections与poolMaximumLocalBadConnectionTolerance之和。默认值：3（新增于3.4.5）

  - poolPingQuery

    发送到数据库的侦测查询，用来检验连接是否正常工作并准备接受请求。默认是”NO PING QUERY SET“，这会导致多数据库驱动失败时带有一个恰当的错误信息

  - poolPingEnabled

    是否启用侦测查询。若开启，需要设置poolPingQuery属性为一个可执行的SQL语句（最好是一个速度非常快的SQL语句），默认值：false。

  - poolPingConnectionsNotUsedFor

    配置poolPingQuery的频率。可以被设置为和数据库连接超时时间一样，来避免不必要的侦测，默认值：0（即所有连接每一时刻都被侦测，当然仅当poolPingEnabled为true时适用）

- JNDI

  这个数据源实现是为了使用如Spring或应用服务器这类容器，容器可集中或在外部配置数据源，然后放置一个JNDI上下文的引用。这个数据源配置只需要两个属性。

  - initial_context

    这个属性用来在InitialContext种寻找上下文（即，initialContext.lookup(initial_context)）。这是个可选属性，如果忽略，那么将会直接从InitialContext中寻找data_source属性。

  - data_source

    这是引用数据源实例位置的上下文的路径。提供了initial_context配置时会在其返回的上下文中进行查找，没有提供则直接在InitialContext中查找。

### mappers

告诉Mybatis去哪里找映射文件

```xml
<!--使用相对于classpath的相对路径的资源，注意不是类，不使用. 而是使用/ -->
<mappers>
	<mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
	<mapper resource="org/mybatis/builder/BlogMapper.xml"/>
	<mapper resource="org/mybatis/builder/PostMapper.xml"/>
</mappers>
<!--使用全限定路径，以file开始，指定本地的绝对路径-->
<mappers>
    <!--这里的配置文件可能不在项目内，甚至是在网上的，所以这里也是以url的形式来指定的-->
	<mapper url="file:///var/mappers/AuthorMapper.xml"/>
	<mapper url="file:///var/mappers/BlogMapper.xml"/>
	<mapper url="file:///var/mappers/PostMapper.xml"/>
</mappers>
<!--使用接口的全路径名指定，是接口，所以此时使用. 而不是/ -->
<mappers>
	<mapper class="org.mybatis.builder.AuthorMapper"/>
	<mapper class="org.mybatis.builder.BlogMapper"/>
	<mapper class="org.mybatis.builder.PostMapper"/>
</mappers>

<!--★-->
<!--使用包名指定，包含指定包下所有的接口，必须有接口才行-->
<mappers>
	<package name="org.mybatis.builder"/>
</mappers>
```

推荐使用第四种方式，但是前提是一定要显示的提供响应的接口定义，且要求接口名和mapper.xml文件名必须完全相同。

### 将易变内容写到配置文件中并用${var_name}的形式引入到mybatis配置文件

首先创建db.properties：

```properties
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/db_name?serverTimezone=UTC
username=root
password=root
```

然后用\<properties\>标签将db.properties引入mybatis.xml：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--引入db.properties-->
    <properties resource="db.properties"></properties>
    <!--开启log4j的日志打印。默认就是开启的，可以不配置-->
    <settings>
        <!--当然，这个value除了是LOG4J还可以是SLF4J-->
    	<setting name="logImpl" value="LOG4J"/>
    </settings>
    ...
</configuration>
```



## Resouces（注意是mybatis的Resouces）

可以直接写文件名而不用写文件绝对路径来获取文件流

```java
// 获取mybatis配置文件的流对象
InputStream is = Resources.getResouceAsStream("file_name"); // 自动从当前项目的编译目录下获取配置文件的流对象
```



## SqlSessionFactory与SqlSession

请结合下面的mapper配置文件

```java
// 获取mybatis配置文件的流对象
InputStream is = Resources.getResourceAsStream("mybatis.xml"); // 自动从当前项目的编译目录下获取配置文件的流对象
// 获取SqlSessionFactory对象
SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
// 获取SqlSession对象
SqlSession sqlSession = factory.openSession(); // 当SqlSession对象被创建成功后，已经连接好数据库了

//// 使用SQLSession对象完成数据库的单表查询操作
// List<Object> objects = sqlSession.selectList("SELECT * FROM tbl_name"); // sql语句如果写在这里，那耦合度就太高了，应该写到mapper中，如下：
// 查询所有
List<Emp> empList = sqlSession.selectList("com.bjsxt.mapper.EmpMapper.selEmp"); // "selEmp"表示mapper文件中该条sql语句的id。前缀：com.bjsxt.mapper.EmpMapper 表示mapper文件的namespace

// 根据id查询
Emp emp = sqlSession.selectOne("com.bjsxt.mapper.EmpMapper.selEmpById", 1);

// 多条件的单表查询
// 使用Map存储查询条件
HashMap<String, Object> hashMap = new HashMap<>();
hashMap.put("a", 0);
hashMap.put("b", 1);
List<Emp> emps = sqlSession.selectList("com.bjsxt.mapper.EmpMapper.selEmpBySqlAndDeptno", hashMap);
// 使用实体类对象存储查询条件
Emp emp1 = new Emp();
emp1.setSal(0.0);
emp1.setDeptno(1);
List<Emp> emps2 = sqlSession.selectList("com.bjsxt.mapper.EmpMapper.selEmpBySqlAndDeptno2", emp1);

//// 使用SqlSession对象完成数据库的单表新增、修改、删除操作
// 新增
// 创建Emp对象存储要增加的员工信息
Emp emp = new Emp();
emp.setEname("赵六");
emp.setDeptno(2);
emp.setSal(20000.00);
emp.setJob("程序员");
emp.setMgr(1);
int i = sqlSession.insert("com.bjsxt.mapper.EmpMapper.insEmp", emp);
// 提交事务
sqlSession.commit(); // 原生jdbc是自动提交事务的，当然也可以取消自动提交：conn.setAutoCommit(false); 但是mybatis必须手动提交
// 更新
Emp emp = new Emp();
emp.setEmpid(3);
emp.setEname("王五");
int i = sqlSession.update("com.bjsxt.mapper.EmpMapper.updateEmp", emp);
sqlSession.commit();
// 删除
int i = sqlSession.delete("com.bjsxt.mapper.EmpMapper.delEmp", 4);
sqlSession.commit();

```

### 使用mapper

```java
// 假设有两个相关文件，一个是接口EmpMapper和一个是xml文件EmpMapper.xml
// 获取SqlSession对象之后，我们需要获取是哪个mapper
// 获取Mapper接口的实现类。注意，接口的实现类是在SqlSession对象被创建的时候即完成了生成，所以假设有5个接口，与之对应有5个xml文件，那么这5个接口实现会在SqlSession创建的时候就完成，到后面要用的时候直接取就行了
EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
// 调用EmpMapper接口中的方法
List<Emp> emps = empMapper.selEmpMapper();

//// 有参数查询
// 一个形参
// 参数为基本类型
Emp emp = empMapper.selEmpById(1);
// 参数为引用类型
Emp emp1 = new Emp();
emp1.setEmpid(2);
Emp emp2 = empMapper.selEmpById2(emp1);
// 多个参数
// 参数全部为基本类型
List<Emp> emps = empMapper.selEmpBySalDept(2.0, 1);
// 参数全部为引用类型
Emp e1 = new Emp();
e1.setSal(3.0);
Emp e2 = new Emp();
e2.setDeptno(1);
List<Emp> emps2 = empMapper.selEmpBySalDept2(e1, e2);
// 参数为引用类型和基本类型的混用
Emp e3 = new Emp();
e3.setSal(4.0);
List<Emp> emps3 = empMapper.selEmpBySalDept3(e3, 2);
```

而且如果使用这种一个接口对应一个xml文件的方法的话，连parameterType都不用写了，因为可以直接把查询参数传到接口中去：

```xml
<!--EmpMapper.xml文件中的sql语句-->
<!--查询所有员工信息-->
<select id="selEmpMapper" resultType="com.bjsxt.pojo.Emp">
	select empid, ename, job, mgr from emp
</select>
<!--有参数的数据库操作-->
<!--根据Id查询员工信息-->
<!--参数为一个-->
<!--类型为基本类型：使用#{0}-->
<select id="selEmpById" resultType="com.bjsxt.pojo.Emp">
    <!--下面的#{0}中的0表示接口中形参的下标-->
	select empid, ename, job, mgr from emp from emp where empid=#{0}
</select>
<!--类型为引用类型：使用#{实体类的属性名}或者#{Map集合的键名（因为形参类型可能为Map）}-->
<select id="selEmpById2" resultType="com.bjsxt.pojo.Emp">
    <!--下面的#{empid中的empid表示接口中形参Emp的属性名empid-->
	select empid, ename, job, mgr from emp from emp where empid=#{empid}
</select>

<!--参数为多个-->
<!--参数全部为基本类型：根据工资和部门编号查询员工信息，使用#{param1}，从接口方法从左至右开始，param1，param2，...-->
<select id="selEmpBySalDept" resultType="com.bjsxt.pojo.Emp">
    <!--下面的#{param2}表示形参中顺序数下去的第2个参数（注意，这里不让写#{1}了，应该写#{param2}）-->
	select empid, ename, job, mgr from emp from emp where deptno=#{param2} and sal>#{param1}
</select>
<!--参数全部为引用类型：根据工资和部门编号查询员工信息，使用#{param1.属性名}或param1为Map集合时应该使用#{param1.键名}-->
<select id="selEmpBySalDept2" resultType="com.bjsxt.pojo.Emp">
    <!--下面的#{param2.deptno}表示第二个参数Emp的deptno属性-->
	select empid, ename, job, mgr from emp from emp where deptno=#{param2.deptno} and sal>#{param1.sal}
</select>
<!--引用类型和基本类型混用：根据工资和部门编号查询员工信息-->
<select id="selEmpBySalDept3" resultType="com.bjsxt.pojo.Emp">
	select empid, ename, job, mgr from emp from emp where deptno=#{param2} and sal>#{param1.sal}
</select>
```

```java
public interface EmpMapper{
    // 查询所有的员工信息
    List<Emp> selEmpMapper();
    /**
    * 带有参数的数据库操作
    */
    //// 只有一个形参
    // 根据id查询员工信息：参数类型为基本类型
    Emp selEmpById(Integer empid);
    // 根据id查询员工信息：参数类型为引用类型
    Emp selEmpById2(Emp emp);
    
    //// 参数有多个
    // 参数全部为基本类型：根据工资和部门编号查询员工信息
    List<Emp> selEmpBySalDept(Double sql, Integer deptno);
    // 参数全部为引用类型：根据工资和部门编号查询员工信息
    List<Emp> selEmpBySalDept2(Emp e1, Emp e2);
    // 引用类型和基本类型混用：根据工资和部门编号查询员工信息
    List<Emp> selEmpBySalDept3(Emp e1, Integer deptno);
}
```



## mapper配置文件

### 查

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
		PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
		"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace就相当于mapper文件的id-->
<mapper namespace="com.bjsxt.mapper.EmpMapper">
	<!--使用Mybatis完成单表的查询-->
    <!--
		声明查询所有的员工信息
			sql语句中没有参数
				- 使用resultType属性指明返回值存储的实体类的全限定路径
				- 如果查询结果是多条数据，使用selectList(String sqlPath)方法完成查询；如果只有一条，建议使用selectOne(String sqlPath)来完成查询
	-->
    <!--resultType表示使用哪个实体类去存数据库返回的信息-->
    <select id="selEmp" resultType="com.bjxst.pojo.Emp">
    	select * from emp
    </select>
    <!--
		根据ID查询员工信息：
			SQL语句中如果只有一个参数：
				- 使用parameterType属性指明参数的类型
				- 在SQL语句中使用#{任意字符}完成参数的占位
				- 如果查询结果是多条数据，使用selectList(String sqlPath, Object Param)方法完成查询；如果只有一条，建议使用selectOne(String sqlPath, Object Param)来完成查询
	-->
    <select id="selEmpById" resultType="com.bjsxt.pojo.Emp" parameterType="int">
    	select * from emp where empid=#{myempid}
    </select>
    
    <!--
		根据部门和工资查询员工信息
			sql语句中有多个参数的单表查询：
				- 使用parameterType属性指明参数的类型
					注意：parameterType属性表明的是Mybatis接受的实参的类型，不是SQL语句中的参数类型
				- 在SQL语句中使用#{任意字符}完成参数的占位
					注意：
						因为在Mybatis底层会自动给SQL语句中的占位符进行赋值，由我们自己赋值变为自动
						但是我们需要告诉Mybatis哪个值赋值给哪个占位符。所以Mybatis中的SQL语句中使用#{键名}的方式来完成占位以及赋值
				- 如果查询结果是多条数据，使用selectList(String sqlPath, Object Param)方法完成查询；如果只有一条，建议使用selectOne(String sqlPath, Object Param)来完成查询
	-->
    <!--parameterType="map"表示使用Map集合封装实参-->
    <select id="selEmpBySqlAndDeptno" resultType="com.bjsxt.pojo.Emp" parameterType="map">
    	select * from emp where sql > #{keyA} and deptno = #{keyB}
    </select>
    
    <!--使用实体类对象封装实参，占位使用#{属性名}-->
    <select id="selEmpBySqlAndDeptno2" parameterType="com.bjsxt.pojo.Emp" resultType="com.bjsxt.pojo.Emp">
    	select * from emp where sql > #{sql} and deptno = #{deptno}
    </select>
</mapper>
```

### 增、删、改

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
		PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
		"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace就相当于mapper文件的id-->
<mapper namespace="com.bjsxt.mapper.EmpMapper">
	<!--Mybatis的增、删、改，无需使用resultType属性表明返回值类型，默认为int-->
    <!--新增-->
    <insert id="insEmp" parameterType="com.bjsxt.pojo.Emp">
    	insert into emp values(default, #{ename}, #{job}, #{mgr}, now(), #{sal}, #{comm}, #{deptno})
    </insert>
    <!--更新-->
    <update id="updateEmp" parameterType="com.bjsxt.pojo.Emp">
    	update emp set ename=#{ename}, where empid=#{empid}
    </update>
    <!--删除-->
    <delete id="delEmp" parameterType="int">
    	delete from emp where empid=#{myempid}
    </delete>
</mapper>
```



## 日志

Mybatis的内置日志工厂（LogFactory）提供日志功能，内置日志工厂将日志交给以下其中一种工具作代理：

- SLF4J
- Apache Commons Logging
- Log4j 2
- Log4j
- JDK logging
- NO_LOGGING

Mybatis内置日志工厂基于运行时自省机制选择合适的日志工具。他会使用第一个查找得到的工具（按上面列举的顺序查找）。如果一个都没找到，日志功能就会被禁用。也就是说在项目中把日志工具环境配置出来后，不用在mybatis进行配置就可以让日志生效

不少应用服务器（如Tomcat和WebShpere）的类路径种已经包含Commons Logging，所以在这种配置环境下的Mybatis会把它作为日志工具，记住这点非常重要，这意味着，在诸如WebSphere的环境中，它提供了Commons Logging的私有实现，你的Log4J配置将被忽略。mybatis将你的Log4J配置忽略掉是相当郁闷的（事实上，正是因为在这种配置环境下，Mybatis才会选择使用Commons Logging而不是Log4J）。如果你的应用部署在一个了路径已经包含Commons Logging的环境中，而你又想使用其它日志工具，可以通过在mybatis配置文件mybatis-config.xml里面添加一项setting来选择别的日志工具：

```xml
<settings>
	<setting name="logImpl" value="LOG4J"/>
</settings>
```

简单来说，要在mybatis.cfg.xml中配置mybatis所使用的具体日志实现。如果不指定将自动搜索，可能会搜到log4j，但是也有可能会优先搜索到其他的日志实现，所以还是设置一下，让它匹配到log4j：

```xml
<settings>
	<setting name="logImpl" value="LOG4J"/>
</settings>
```

无论使用哪种日志工具对于我们来说目的都是一样的：打印运行过程中日志信息。日志信息中对平时开发最重要的是运行过程中SQL的打印，这也是在开发过程中mybatis日志的重要性

### log4j配置文件

可以将全局的日志级别调高，避免大量debug信息的干扰，同时将对映射文件的操作调低，可以用来显示SQL语句的调试信息。开发阶段，建议启动控制的日志

```properties
#定义全局日志级别
log4j.rootLogger=error,stdout,logfile
#包级别日志。这句话必须有，因为我们要打印的日志是mapper包下的东西
log4j.logger.com.bjsxt.mapper=debug
#接口级别日志
#log4j.logger.com.bjsxt.mapper.EmployeeMapper=debug
#方法级别日志
#log4j.logger.com.bjsxt.mapper.EmployeeMapper.findById=debug
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.err
log4j.appender.stdout.layout=org.apache.log4j.SimpleLayout

log4j.appender.logfile=org.apache.log4j.FileAppender
log4j.appender.logfile.File=d:/bjsxt.log
log4j.appender.logfile.layout=org.apache.log4j.PatternLayout
log4j.appender.logfile.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %l %F %p %m%n
```



## mybatis依赖的包

![image-20210411182313174](mybatis.assets/image-20210411182313174.png)

## 生命周期

SqlSessionFactoryBuilder：

该类用来创建SqlSessionFactory对象，当SqlSessionFactory对象被创建后，该对象也就没有存在的必要了。

SqlSessionFactory：

该对象应该在你的应用执行期间一直存在，由于要从该对象中获取SqlSession对象，这样的操作会相当频繁，同时创建SqlSessionFactory对象是一件很耗费资源的事，因此，该对象的生命周期应该为应用返回，即与当前应用具有相同的生命周期。

SqlSession：

每个线程都应该有自己的SqlSession实例，SqlSession实力不能被共享，是线程不安全的，因此最佳的范围是请求或方法范围。

Mapper：

关闭SqlSession的时候也就关闭了由其所产生的Mapper



## 类型别名

| 别名       | 映射的类型 |
| ---------- | ---------- |
| _byte      | byte       |
| _long      | long       |
| _short     | short      |
| _int       | int        |
| _integer   | int        |
| _double    | double     |
| _float     | float      |
| _boolean   | boolean    |
| string     | String     |
| byte       | Byte       |
| long       | Long       |
| short      | Short      |
| int        | Integer    |
| integer    | Integer    |
| double     | Double     |
| float      | Float      |
| boolean    | Boolean    |
| date       | Date       |
| decimal    | BigDecimal |
| object     | Object     |
| map        | Map        |
| hashmap    | HashMap    |
| list       | List       |
| arraylist  | ArrayList  |
| collection | Collection |
| iterator   | Iterator   |

有了类型别名我们就可以直接这么写了：

```xml
<!--注意下面的parameterType-->
<delete id="delEmp" parameterType="int">
    delete from emp where empid=#{myempid}
</delete>
```

## include标签和sql标签提取公共SQL脚本片段

```xml
<!--
	问题：
		我们发现在Mapper.xml文件中不同的sql语句中会存在相同的sql片段，如果每次我们都自己声明，会造成代码维护困难
	解决：
		将相同的sql脚本片段抽取出来，在外部声明，然后在sql标签中调用即可
	实现：
		sql标签：声明公共的sql脚本片段
		include标签：引入公共的sql脚本片段
-->
<sql id="empSql">
	empid,ename,job,mgr
</sql>
<!--查询所有的员工信息-->
<select id="selEmpMapper" resultType="com.bjsxt.pojo.Emp">
	select <include refid="empSql"></include> from emp 
</select>
```

## @Param

@Param是MyBatis所提供的(org.apache.ibatis.annotations.Param)，作为Dao层的注解，作用是用于传递参数，从而可以与SQL中的的字段名相对应，一般在2=<参数数<=5时使用最佳。

@Param解决了参数可读性差的问题

先来看看原始的方法：

当只有一个参数时，没什么好说的，传进去一个值也只有一个参数可以匹配。当存在多个参数时，传进去的值就区分不开了，这时可以考虑用Map，例如接口：

```java
public List<Role> findRoleByMap(Map<String, Object> parameter);
```

此时的xml文件可以这么写：

```xml
<select id="findRoleByMap" parameterType="map" resultType="role">
    SELECT id,name FROM t_role
    WHERE roleName=#{roleName}
    AND note=#{note}
<select>
```

测试文件：

```java
RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
Map<String, Object> parameter = new HashMap<>();
parameter.put("roleName", "剑士");
parameter.put("note", "决战紫禁之巅");
List<Role> roles = roleMapper.findRolesByMap(parameter);
```

很明显上面的缺点就在于可读性差，每次必须阅读他的键，才能明白其中的作用，并且不能限定其传递的数据类型，下面是使用@Param的情况，需要将接口改为：

```java
public List<Role> findRoleByAnnotation(@Param("roleName") String roleName, @Param("note") String note);
```

这样我们就可以直接传入对应的值了。

当然也可以使用Java Bean来传递多个参数，定义一个POJO：

```java
public class RoleParam {
    private String roleName;
    private String note;
    /*getter和setter*/
}
```

此时接口就变为：

```java
public List<Role> findRoleByBean(RoleParam role);
```

这样对应的xml文件与原始的方法的区别就在于id和parameterType发生了变化，id对应的方法和parameterType对应该类的全限定名。

而使用更多的场景可能是这样的，对应多个POJO：

```java
public List<Role> findRoleByMix(@Param("roleP") RoleParam role, @Param("permissionP") PermissionParam permission);
```

这样就可以进行如下映射：

```xml
<select id="findRoleByMix" resultType="role">
    SELECT id,name FROM t_role
    WHERE roleName=#{roleP.roleName}
    AND note=#{rolep.note}
    AND level=#{permissionP.level}
<select>
```

注意：此时并不需要写出parameterType属性，Mybatis会进行自动搜索（这点在上面“使用mapper”这一章节中解释了）。

## 使用#{}占位的方式和使用${}占位的方式

现在有一个问题：

目前我们在使用mybatis完成数据库操作的时候，mybatis底层默认使用PreparedStatement对象完成数据库操作，也就是说Sql语句的赋值都是通过占位赋值的，那么如果我们想通过字符串拼接的方式赋值怎么办？也就是底层使用Statement对象完成数据库操作。

解决：

```
#{}方式占位：底层使用PreparedStatement对象完成数据库操作，sql语句本质上仍然是？占位赋值的
${}方式占位：底层使用Statement对象完成数据库操作，本质为sql语句的拼接
```

举例说明：

首先来看使用#{}占位方式：

mapper.xml文件：

```xml
<select id="selPs" resultType="com.bjsxt.pojo.Emp">
	select * from emp where ename=#{ename}
</select>
```

mapper接口中的方法：

```java
// #{}的占位
List<Emp> selPs(@Param("ename") String ename);
```

测试文件：

```java
EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
List<Emp> emps = mapper.selPs("张三"); // 此时底层使用PreparedStatement
```

使用${}占位方式：

mapper.xml文件：

```xml
<select id="selPs" resultType="com.bjsxt.pojo.Emp">
	select * from emp where ename='${ename}'
</select>
```

mapper接口中的方法：

```java
// ${}的占位
List<Emp> selPs(@Param("ename") String ename);
```

测试文件：

```java
EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
List<Emp> emps = mapper.selPs("张三"); // 此时底层使用Statement
```

## 模糊查询

在进行模糊查询时，在映射文件中可以使用concat()函数来连接参数和通配符。另外，注意对于特殊字符，比如\<，不能直接书写，应该使用字符实体（诸如\&lt;等）替换。

mapper接口方法：

```java
// 模糊查询：根据员工名查询员工信息
List<Emp> selEmpByName(@Param("ename")String ename);
```

mapper.xml文件：

```xml
<!--模糊查询：根据员工姓名获取员工信息-->
<select id="selEmpByName" resultType="com.bjsxt.pojo.Emp">
    <!--下面的这个concat其实就是MySQL自带的函数，用来拼接字符串-->
	select * from emp where ename like concat('%', #{ename}, '%')
</select>
```

测试文件：

```java
EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
List<Emp> emps = mapper.selEmpByName("三"); 
```

### 小于或者大于判断

mapper接口方法：

```java
// 小于判断
List<Emp> selEmpBySal(@Param("sal") Double sal);
```

mapper.xml文件：

```xml
<!--小于判断-->
<select id="selEmpBySal" resultType="com.bjsxt.pojo.Emp">
	select * from emp where sal &lt; #{sal}
</select>
```

测试文件：

```java
EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
List<Emp> emps = mapper.selEmpBySal(4000.0); 
```

## 分页查询

mapper接口方法：

```java
List<Emp> selEmpByPage(@Param("ename") String ename, @Param("pageStart")int pageStart, @Param("pageSize")int pageSize);
```

mapper.xml文件：

```xml
<!--分页查询-->
<select id="selEmpByPage" resultType="com.bjsxt.pojo.Emp">
	select * from emp where sal &lt; #{sal} limit #{pageStart},#{pageSize}
</select>
```

测试文件：

```java
// 分页查询
EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
List<Emp> emps = mapper.selEmpByPage(4000.0, 0, 5); 
```

## 自增主键回填

Mysql支持主键自增。有时候完成添加后需要立刻获取刚刚自增的主键，由下一个操作来使用。比如结算购物车之后，主订单的主键确定后，需要作为后续订单明细项的外键存在。对于主键的使用，我们可以用UUID（先手动生成一个UUID，再把它作为主键，为什么用UUID呢？因为UUID可以保证不重复），也可以让它自动生成，那么自动生成的时候我们如何拿到主键呢？Mybatis提供了支持，可以非常简单的获取。

**方式1：通过useGeneratedKeys属性实现**

```xml
<insert id="save" useGeneratedKeys="true" keyProperty="empno">
	insert into emp values(null,#{ename},#{job},#{mgr},#{hireDate},#{sal},#{comm},#{deptno})
</insert>
```

useGeneratedKeys：表示要使用自增的主键

keyProperty：表示把自增的主键赋给JavaBean的哪个成员变量

以添加Employee对象为例，添加前Employee对象的empno是空的，添加完毕后可以通过getEmpno()获取自增的主键。

**方式2：通过selectKey元素实现**

```xml
<insert id="save">
	<selectKey order="AFTER" keyProperty="empno" resultType="int">
		<!--SELECT LAST_INSERT_ID()-->
        <!--或者可以这么写：-->
        SELECT @@identity
    </selectKey>
    insert into emp(empno,ename,sal)values(null,#{ename},#{sal})
</insert>
```

order：取值AFTER|BEFORE，表示在新增之后|之前执行\<selectKey\>中的命令（由此可见这种方式的功能更加丰富）

keyProperty：表示将查询到的数据绑定到哪个属性上



## 动态SQL查询以及更新

### if标签

接口方法：

```java
// 根据员工的工作，部门，工资动态查询员工信息 ---if标签的使用
List<Emp> findEmp(@Param("job") String job,
                 @Param("deptno") Integer deptno,
                 @Param("sal") Double sal);
```

mapper.xml文件：

```xml
<!--
	根据员工的工作、部门、工资动态查询员工信息
	使用if标签完成逻辑判定，完成SQL语句的拼接
	使用：
		在SQL标签中声明
	属性：
		test：值为判断的逻辑条件，直接使用@Param注解中声明的名字即可获取实参。不同的条件使用 and or 关键字连接
-->
<select id="findEmp" resultType="com.bjsxt.pojo.Emp">
	select * from emp where 1=1
    <if test="job!=null and job!=''">
    	and job=#{job}
    </if>
    <if test="deptno!=null and deptno!=''">
    	and deptno=#{deptno}
    </if>
    <if test="sal!=null and sal!=''">
    	and sal > #{sal}
    </if>
</select>
```

测试文件：

```java
EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
// 动态SQL查询
List<Emp> emps = empMapper.findEmp(null, 1, null);
```

### where标签

接口方法：

```java
// 根据员工的工作，部门，工资动态查询员工信息 ---where标签的使用
List<Emp> findEmp(@Param("job") String job,
                 @Param("deptno") Integer deptno,
                 @Param("sal") Double sal);
```

mapper.xml文件：

```xml
<!--
	根据员工的工作、部门、工资动态查询员工信息
	where标签的使用：
		作用：相当于声明了where关键字
		注意：
			只有当条件成立时才会生成where关键字，并且会自动忽略第一个and关键字
			因为无法预知哪个条件会成立，所以建议每个条件前都声明SQL语句的条件链接符号（and|or）

-->
<select id="findEmp" resultType="com.bjsxt.pojo.Emp">
	select * from emp
    <where>
    	<if test="job!=null and job!=''">
    		and job=#{job}
        </if>
        <if test="deptno!=null and deptno!=''">
            and deptno=#{deptno}
        </if>
        <if test="sal!=null and sal!=''">
            and sal > #{sal}
        </if>
    </where>
</select>
```

测试文件：

```java
EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
// 动态SQL查询
List<Emp> emps = empMapper.findEmp(null, 1, null);
```

### bind标签

接口方法：

```java
// 根据员工的工作，部门，工资动态查询员工信息 ---bind标签的使用
List<Emp> findEmp(@Param("job") String job,
                 @Param("deptno") Integer deptno,
                 @Param("sal") Double sal);
```

mapper.xml文件：

```xml
<!--
	根据员工的工作、部门、工资动态查询员工信息
	bind标签的使用：
		作用：将接收到的实参进行进一步的修饰后传递给SQL语句使用
		使用：
			<bind name="新的键名" value="原始数据的名字+'拼接的新数据'"/>
			一般在模糊查询的时候使用（拼接%）
-->
<select id="findEmp" resultType="com.bjsxt.pojo.Emp">
	<bind name="job_alias" value="job+'qq1'"/>
    select * from emp where job=#{job_alias} and deptno=#{deptno} and sal=#{sal}
</select>
```

测试文件：

```java
EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
// 动态SQL查询
List<Emp> emps = empMapper.findEmp("讲师", 1, 10000.0);
```

### set标签

接口方法：

```java
// set标签的使用
int updateEmp(Emp emp);
```

mapper.xml文件：

```xml
<!--
	set标签的作用：
		set标签用在update语句中给字段赋值。借助if的配置，可以只对有具体值的字段进行更新。set元素会自动帮助添加set关键字，自动去掉最后一个if语句的多余的逗号
-->
<update id="updateEmp">
	update emp
    <set>
    	<if test="ename!=null and ename !=''">
        	ename=#{ename},
        </if>
        <if test="job!=null and job !=''">
        	job=#{job},
        </if>
        <if test="mgr!=null and mgr !=''">
        	mgr=#{mgr},
        </if>
        <if test="sal!=null and sal !=''">
        	sal=#{sal},
        </if>
        <!--写下面这一句的原因是：如果一个要更新的字段都没有，那么就会生成这样的sql语句：update emp where id=?，显然是会报错的，为了防止这种情况，更新一下empid即可（empid是一定存在的），这样的话就算一个要更新的字段都没，也会生成这样的sql语句：update emp set empid=? where empid=?，这样就不会报错了-->
        empid=#{empid},
    </set>
</update>
```

测试文件：

```java
EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
// set标签
Emp emp = new Emp();
emp.setEmpId(1);
emp.setEmpName("qyf");
int i = empMapper.updateEmp(emp);
```

### foreach标签

接口方法：

```java
List<Emp> findEmpByIds(List<Integer> ids);
```

mapper.xml文件：

```xml
<!--
	foreach标签的作用：
		foreach标签是非常强大的，它允许你指定一个集合或数组，声明集合项和索引变量，他们可以用在标签内。它也允许你指定开放和关闭的字符串，在迭代之间放置分隔符。这个元素是很智能的，它不会偶然地附加多余的分隔符
		注意：你可以传递一个List实例或者数组作为参数对象传给mybatis。当你这么做的时候，mybatis会自动将它包装在一个Map中，用名称在作为键。List实例将会以“list”作为键，而数组实例将会以“array”作为键
		特别注意：在进行sql优化时有一点就是建议少用in语句，因为对性能有影响。如果in中元素很多的话，会对性能有较大影响，此时就不建议使用foreach语句了
-->
<select id="findEmpByIds" resultType="com.bjsxt.pojo.Emp">
	select * from emp where empid in
    <foreach collection="list" open="(" close=")" separator="," item="id">
    	#{id}
    </foreach>
</select>
```

当然也可以配合@Param使用：

接口方法：

```java
List<Emp> findEmpByIds(@Param("ids") List<Integer> ids);
```

mapper.xml文件：

```xml
<select id="findEmpByIds" resultType="com.bjsxt.pojo.Emp">
	select * from emp where empid in
    <foreach collection="ids" open="(" close=")" separator="," item="id">
    	#{id}
    </foreach>
</select>
```

测试文件：

```java
EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
List<Integer> ids = new ArrayList<>();
ids.add(1);
ids.add(2);
List<Emp> emps = empMapper.findEmpByIds(ids);
```

如果有这么一个需求：需要联合ids和ename进行查询，ename使用模糊查询。那么可以这么写：

接口：

```java
List<Emp> findEmpByNameAndIds(@Param("ename")String ename, @Param("ids") List<Integer> ids);
```

mapper.xml文件：

```xml
<select id="findEmpByNameAndIds" resultType="com.bjsxt.pojo.Emp">
	select * from emp where
    ename like concat('%', #{ename}, '%')
    and
    empid in
    <foreach collection="ids" open="(" close=")" separator="," item="id">
    	#{id}
    </foreach>
</select>
```

测试文件：

```java
EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
List<Integer> ids = new ArrayList<>();
ids.add(1);
ids.add(2);
List<Emp> emps = empMapper.findEmpByNameAndIds("张三", ids);
```

## idea使用技巧

### 引入本地DTD文件

在没有联网的情况下，让dtd约束继续起作用，并且出现标签提示，可以通过引入本地dtd文件来实现。

1、下载dtd：在浏览器中输入dtd的网络地址即可实现下载。比如：http://mybatis.org/dtd/mybatis-3-config.dtd

2、将下载的dtd拷贝到本地的一个目录下

3、idea操作路径：File --- Settings --- Languages & Frameworks。其中URI复制dtd的网络地址，File选择dtd文件在本地的地址，就行了。

注意：在mybatis的核心jar包中就提供了mybatis-3-config.dtd

### 创建文件模板

idea提供了大量的内置文件模板template，可以自定义模板，避免重复，提高效率。

创建入口1：右键---new---Edit file Templates

创建入口2：File---settings---editor---File and Code Templates

使用入口：右键---new---选择模板名称

