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

