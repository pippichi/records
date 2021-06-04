## maven的作用

- maven可以管理jar文件
- 自动下载jar和他的文档，源代码
- 管理jar直接的依赖， a\.jar需要b\.jar ， maven会自动下载b\.jar
- 管理你需要的jar版本
- 帮你编译程序，把java编译为class
- 帮你测试你的代码是否正确
- 帮你打包文件，形成jar文件，或者war文件
- 帮你部署项目



## maven核心概念

- POM

  pom.xml，pom翻译过来叫做项目对象模型。 
  maven把一个项目当做一个模型使用。控制maven构建项目的过程，管理jar依赖。

- 约定的目录结构

  maven项目的目录和文件的位置都是规定的：

  ```
  每一个maven项目在磁盘中都是一个文件夹（项目-Hello）
  	Hello/
  	  ---/src
  	  ------/main              #放你主程序java代码和配置文件
  	  ----------/java          #你的程序包和包中的java文件
  	  ----------/resources     #你的java程序中要使用的配置文件
  
  	  ------/test              #放测试程序代码和文件的（可以没有）
  	  ----------/java          #测试程序包和包中的java文件
  	  ----------/resources     #测试java程序中要使用的配置文件
  
  	  ---/pom.xml              #maven的核心文件（maven项目必须有）
  
  ```

  

- 坐标

  一个用来表示资源的唯一的字符串

- 依赖管理

  管理你的项目可以使用jar文件

- 仓库管理（了解）

  你的资源存放的位置

- 生命周期 (了解) 

  maven工具构建项目的过程，就是生命周期。

- 插件和目标（了解）

  执行maven构建的时候用的工具是插件

- 继承

- 聚合

## 疑问

- 第一次maven compile的时候下载的是什么?

  ```
  maven工具执行的操作需要很多插件（java类--jar文件）完成的
  所以下载的是jar文件--叫做插件--插件来完成某些功能
  ```

- 下载的东西存放到哪里了？

  ```
  默认仓库（本机仓库）：
   C:\Users\（登录操作系统的用户名）Administrator\.m2\repository
  ```

- 如何修改本机存放资源的目录位置（设置本机仓库）？

  ```
  1、修改maven的配置文件， maven安装目录/conf/settings.xml
  2、修改 <localRepository> 指定你的目录（不要使用中文目录）
  ```

- maven测试的时候测的是什么代码？

  ```
  mvn test-compile 会编译项目下的测试程序（junit）
  mvn test 会运行项目下的测试程序（junit），所以测的是我们自己编写的测试代码（通常是借助junit来编写的）
  ```

  注意：`mvn test`可以单独运行，不是非得运行完`mvn test-compile`之后才能运行的

  具体测试步骤：

  - 在pom.xml加入单元测试依赖

    ```xml
    <!-- 单元测试 -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.11</version>
        <scope>test</scope>
    </dependency>
    ```

  - 在maven项目中的src/test/java目录下，创建测试程序

    推荐的创建类和方法：
    1、测试类的名称 是Test + 你要测试的类名
    2、测试的方法名称 是：test + 方法名称

    例子：

    ```java
    // 例如你要测试HelloMaven类中的add方法
    @ExtendWith(SpringExtension.class)
    public class TestHelloMaven{
        @Test
        void testAdd(){
        }
    }
    ```

- src/main/recources中的文件在编译之后被复制到哪里去了？

  该目录中的文件会被拷贝到target/classes目录中

- main/java目录下的Java文件在编译之后被复制到哪里去了？

  执行`mvn compile`之后，将编译main/java/目录下的java文件为class文件， 同时把这些class文件拷贝到 target/classes目录下面

## 依赖管理

具体的看pdf，这里说明一下使用provided的原因：

```
有时候servlet的jar我们就会使用provided这个依赖范围
原因是虽然程序放到tomcat中运行的时候不包含servlet的jar，但是它依旧能够正常工作，那是因为tomcat已经提供了servlet的jar
对于这种情况的jar，我们就可以用provided这个依赖范围
```



## plugin中的`<executions>`

用于配置execution目标，一个插件可以有多个目标

例如：

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-checkstyle-plugin</artifactId>
    <version>3.1.1</version>
    <dependencies>
        <dependency>
            <groupId>com.puppycrawl.tools</groupId>
            <artifactId>checkstyle</artifactId>
            <version>8.39</version>
        </dependency>
    </dependencies>
    <configuration>
        <configLocation>./.checkstyle.xml</configLocation>
        <includeTestSourceDirectory>true</includeTestSourceDirectory>
        <skip>${skipTests}</skip>
    </configuration>
    <executions>
        <execution>
            <id>checkstyle</id>
            <phase>validate</phase>
            <goals>
                <goal>check</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

说明：

id：规定execution 的唯一标志
goals：表示目标
phase：表示阶段，目标将会在什么阶段执行
inherited：和上面的元素一样，设置false maven将会拒绝执行继承给子插件
configuration：表示此执行的配置属性

## plugin中的`<excludes>`

用于排除

例子1：

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <excludes>
            <exclude>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
            </exclude>
        </excludes>
        <!-- <skip>true</skip> -->
    </configuration>
</plugin>
```

例子2：

```xml
<resources>
    <!-- Filter jdbc.properties & mail.properties. NOTE: We don't filter applicationContext-infrastructure.xml, 
            let it go with spring's resource process mechanism. -->
    <resource>
        <directory>src/main/resources</directory>
        <filtering>true</filtering>
        <includes>
            <include>jdbc.properties</include>
            <include>mail.properties</include>
        </includes>
    </resource>
    <!-- Include other files as resources files. -->
    <resource>
        <directory>src/main/resources</directory>
        <filtering>false</filtering>
        <excludes>
            <exclude>jdbc.properties</exclude>
            <exclude>mail.properties</exclude>
        </excludes>
    </resource>
</resources>
```

## plugin中的`<source>`和`<target>`

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>2.0.2</version>
    <configuration>
        <!-- 编译代码使用的jdk版本 -->
        <source>1.8</source>
        <!-- 运行程序使用的jdk版本 -->
        <target>1.8</target>
    	<encoding>${project.build.sourceEncoding}</encoding>
    </configuration>
</plugin>
```

## `<distributionManagement>`

​	在使用maven过程中，我们在开发阶段经常性的会有很多公共库处于不稳定状态，随时需要修改并发布，可能一天就要发布一次，遇到bug时，甚至一天要发布N次。我们知道，maven的依赖管理是基于版本管理的，对于发布状态的artifact，如果版本号相同，即使我们内部的镜像服务器上的组件比本地新，maven也不会主动下载的。如果我们在开发阶段都是基于正式发布版本来做依赖管理，那么遇到这个问题，就需要升级组件的版本号，可这样就明显不符合要求和实际情况了。但是，如果是基于快照版本，那么问题就自热而然的解决了

​	maven中的仓库分为两种，snapshot快照仓库和release发布仓库。snapshot快照仓库用于保存开发过程中的不稳定版本，release正式仓库则是用来保存稳定的发行版本。定义一个组件/模块为快照版本，只需要在pom文件中在该模块的版本号后加上-SNAPSHOT即可(注意这里必须是大写)，如下：

```xml
<groupId>cc.mzone</groupId>  
<artifactId>m1</artifactId>  
<version>0.1-SNAPSHOT</version>  
<packaging>jar</packaging>  
```

​	maven会根据模块的版本号(pom文件中的version)中是否带有-SNAPSHOT来判断是快照版本还是正式版本。如果是快照版本，那么在mvn deploy时会自动发布到快照版本库中，而使用快照版本的模块，在不更改版本号的情况下，直接编译打包时，maven会自动从镜像服务器上下载最新的快照版本。如果是正式发布版本，那么在mvn deploy时会自动发布到正式版本库中，而使用正式版本的模块，在不更改版本号的情况下，编译打包时如果本地已经存在该版本的模块则不会主动去镜像服务器上下载。

​	所以，我们在开发阶段，可以将公用库的版本设置为快照版本，而被依赖组件则引用快照版本进行开发，在公用库的快照版本更新后，我们也不需要修改pom文件提示版本号来下载新的版本，直接mvn执行相关编译、打包命令即可重新下载最新的快照库了，从而也方便了我们进行开发。



那么如何在项目中应用snapshot和release库呢？

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">  
    <modelVersion>4.0.0</modelVersion>  
    <groupId>net.aty.mybatis</groupId>  
    <artifactId>mybatis-demo</artifactId>  
    <packaging>jar</packaging>  
    <version>${project.release.version}</version>  
    <name>mybatis-demo</name>  
    <url>http://maven.apache.org</url>  
      
    <properties>  
        <project.release.version>0.1-SNAPSHOT</project.release.version>  
    </properties>  
      
  
    <profiles>  
        <profile>  
            <id>release</id>  
            <properties>  
                <project.release.version>0.1</project.release.version>  
            </properties>  
        </profile>  
    </profiles>  
      
      
    <!--定义snapshots库和releases库的nexus地址-->  
    <distributionManagement>  
        <repository>  
            <id>nexus-releases</id>  
            <url>  
                http://172.17.103.59:8081/nexus/content/repositories/releases/  
            </url>  
        </repository>  
        <snapshotRepository>  
            <id>nexus-snapshots</id>  
            <url>  
                http://172.17.103.59:8081/nexus/content/repositories/snapshots/  
            </url>  
        </snapshotRepository>  
    </distributionManagement>  
    
</project>  
```

首先我们看到pom文件中version的定义是采用占位符的形式，这样的好处是可以根据不同的profile来替换版本信息，比如maven默认是使用0.1-SNAPSHOT作为该模块的版本。

1、如果在发布时使用mvn deploy -P release 的命令，那么会自动使用0.1作为发布版本，那么根据maven处理snapshot和release的规则，由于版本号后不带-SNAPSHOT故当成是正式发布版本，会被发布到release仓库；

2、如果发布时使用mvn deploy命令，那么就会使用默认的版本号0.1-SNAPSHOT，此时maven会认为是快照版本，会自动发布到快照版本库。

在distributionManagement段中配置的是snapshot快照库和release发布库的地址，这里是采用nexus作为镜像服务器。对于版本库主要是id和url的配置，配置完成后就可以通过mvn deploy进行发布了，当然了，如果镜像服务器需要用户名和密码，那么还需要在maven的settings.xml文件中做如下配置：

```xml
<server>  
  <id>nexus-releases</id>  
  <username>admin</username>  
  <password>admin123</password>  
</server>  
  
<server>  
  <id>nexus-snapshots</id>  
  <username>admin</username>  
  <password>admin123</password>  
</server> 
```

​	注意这里配置的server的id必须和pom文件中的distributionManagement对应仓库的id保持一致，maven在处理发布时会根据id查找用户名称和密码进行登录和文件的上传发布。

​	我们这里通过profile的定义就可以在发布灵活切换snapshot快照版本和release正式版本了，在被依赖的组件中也可以使用profile来定义在开发阶段使用快照库，在发布阶段使用正式库的功能，只需要在不同的profile中覆盖默认的properties属性值即可。

![image-20210604170119666](maven补充.assets/image-20210604170119666.png)

![image-20210604170130775](maven补充.assets/image-20210604170130775.png)

## `<profiles>`与指令中的`-P`

`<profiles>`用于声明多个环境

`<profile>`可以指定不同环境的不同变量值

指令中的`-P`用于切换环境

例子：

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">  
    <modelVersion>4.0.0</modelVersion>  
    <groupId>net.aty.mybatis</groupId>  
    <artifactId>mybatis-demo</artifactId>  
    <packaging>jar</packaging>  
    <version>${project.release.version}</version>  
    <name>mybatis-demo</name>  
    <url>http://maven.apache.org</url>  
      
    <properties>  
        <project.release.version>0.1-SNAPSHOT</project.release.version>  
    </properties>  
      
    <profiles>  
        <!-- 定义id为“release”的profile -->
        <profile>  
            <id>release</id>  
            <properties>  
                <project.release.version>0.1</project.release.version>  
            </properties>  
        </profile>  
    </profiles>  
    
</project>  
```

现在我们可以使用`-P`来切换环境：

```
$ mvn deploy  // 如果直接这么写，那project.release.version的值就是0.1-SNAPSHOT
$ mvn deploy -P release  // 如果这么写，那project.release.version的值就是0.1
```

