## 简介

docker compose是官方开源项目，用于同时对多个容器进行编排

**Compose核心概念：**

- 服务（service）：一个应用的容器。服务可以存在多个
- 项目（project）：由一组关联的应用容器组成的一个完整业务单元，在docker\-compose\.yml文件中定义

## 下载与卸载
**下载**

- linux

  ```
  $ sudo curl -L https://github.com/docker/compose/releases/download/1.25.5/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
  $ sudo chmod +x /usr/local/bin/docker-compose
  ```

- macos、window

  Compose可以通过pip安装，也可以直接下载编译好的二进制文件使用，甚至可以直接在Docker容器中运行。`Docker Desktop for Mac/Windows 自带 docker-compose 二进制文件，安装 Docker之后可以直接使用`

- bash命令补全

```
  $ curl -L https://raw.githubusercontent.com/docker/compose/1.25.5/contrib/completion/bash/docker-compose > /etc/bash_completion.d/docker-compose
```

**测试安装是否成功**

  ```
$ docker-compose --version
  ```

**卸载**

如果是二进制包方式安装的，删除二进制文件即可

```
$ sudo rm /user/local/bin/docker-compose
```

## 模板指令

### 1、基础模板

```yaml
# 首先指定compose的版本，可以使用`docker --version`确定docker的版本，再参照官方网址：https://docs.docker.com/engine/reference/builder/ 来确定docker-compose的版本
version: "3.0"
# 指定服务集合
services:
  # 指定某个服务的服务名（服务名唯一）
  tomcat01:
    # 指定创建当前这个服务使用的docker镜像是谁
    image: tomcat:8.0-jre8 # 镜像名:TAG；也可以只写镜像名，只写镜像名表示该镜像的最新版本；也可以写成 镜像名:@digest
    # 指定端口映射
    ports:
	  - "8080:8080"
  # 再来一个服务
  tomcat02:
    image: tomcat:8.0-jre8
	ports:
	  - "8081:8080"
```

注意：

- 端口映射有这几种写法：

```
  ports:
    - "3000"
    - "3000:3000"
    - "49100:22"
    - "127.0.0.1:8001:8001"
    - 3000
    - 3000:3000
    - 49100:22
    - 127.0.0.1:8001:8001
```

  **端口映射最好放到引号里面**，因为当使用`HOST:CONTAINER`格式来映射端口时，如果你使用的容器端口小于60并且没放到引号里，可能会得到错误结果，因为`YAML`会自动解析`xx:yy`这种数字格式为60进制。为避免出现这种问题，建议数字串都采用引号包起来的字符串的格式

- 端口映射可以有多个
- 必须保证运行命令的目录中存在docker\-compose\.yml文件
- docker\-compose启动的时候默认夯在前台



### 2、使用volumes后的模板

```yaml
# 首先指定compose的版本
version: "3.0"
# 指定服务集合
services:
  # 指定某个服务的服务名（服务名唯一）
  tomcat01:
    # 指定创建当前这个服务使用的docker镜像是谁
    image: tomcat:8.0-jre8 # 镜像名:TAG，也可以只写镜像名
    # 指定端口映射
    ports:
	  - "8080:8080"
	# 完成宿主机与容器中目录数据卷共享
	volumes:
	  # - /root/apps:/usr/local/tomcat/webapps # 使用自定义路径映射
	  # 也可以使用自动路径映射，如下：
	  # 如果使用自动路径映射，则一定需要声明一下下面这个`tomcatwebapps`，具体声明见下方volumes
	  - tomcatwebapps:/usr/local/tomcat/webapps 
  # 再来一个服务
  tomcat02:
    image: tomcat:8.0-jre8
	ports:
	  - "8081:8080"
# 声明上面服务所使用的自动创建的卷名   #对于自动创建的卷名还有补充，请看下方的`2-1.volumes补充`
volumes:
  tomcatwebapps: # 声明指令的卷名，你没看错，冒号后面就是啥也不用写。这种方式compose会自动创建该卷名，但是会在卷名之前加入项目名，比方说这里的项目名如果是myproject01，那么生成的卷名就是：myproject01_tomcatwebapps
```

**2\-1\.volumes补充：**

```
# 如果这么写：
volumes:
  tomcatwebapps: # 指定的卷名
    external: # 使用自定义卷名
      true # true表示确定使用指定的卷名，这里指定的卷名就是`tomcatwebapps`，可以使用shell命令：`docker volume ls`来查看目前外部有的数据卷，如果里面没有`tomcatwebapps`，那么这个时候如果启动该docker-compose，就会报错，此时应该先在外部创建该数据卷：`docker volume create tomcatwebapps`，此时再启动docker-compose就不会有问题了。因此要注意，如果这里指定true，则表示使用外部自定义卷名，而一旦使用外部自定义卷名启动服务之前就必须手动创建。
# 所以一般我们就直接把external置为false
```



### 3、使用networks后的模板

```yaml
# 首先指定compose的版本
version: "3.2"
# 指定服务集合
services:
  # 指定某个服务的服务名（服务名唯一）
  tomcat01:
    # 指定创建当前这个服务使用的docker镜像是谁
    image: tomcat:8.0-jre8 # 镜像名:TAG，也可以只写镜像名。 # 相当于run image
    # 指定端口映射
    ports: # 相当于run -p
	  - "8080:8080"
	# 完成宿主机与容器中目录数据卷共享
	volumes: # 相当于run -v
	  - tomcatwebapps01:/usr/local/tomcat/webapps 
	networks: # 代表当前服务使用哪个网络桥，有这还不行，我们发现启动docker-compose的时候会报错，因为需要先指定networks，具体见下方。 # 相当于run --network
	  - hello
  # 再来一个服务
  tomcat02:
    image: tomcat:8.0-jre8
	ports:
	  - "8081:8080"
	volumes:
	  - tomcatwebapps02:/usr/local/tomcat/webapps 
	networks: # 代表当前服务使用哪个网络桥，有这还不行，我们发现启动docker-compose的时候会报错，因为需要先指定networks，具体见下方
	  - hello
volumes:
  tomcatwebapps01:
    external:
      false
  tomcatwebapps02:
# 指定networks
networks: # 定义服务用到桥
  hello: # 定义上面服务用到的网桥名称 默认创建就是bridge模式。和volumes一样，这样写自动创建的网络名是：项目名_网络名，比方说项目名如果是hello01，那么自动创建的网络名就是：hello01_hello，如果要指定使用自定义网络名，也还是跟volumes一样，要声明external为true，如下：
  hello02:
    external:
      true # 使用外部指定网桥 注意：网桥必须存在。使用`docker network ls`查看网桥是否存在，使用`docker network create -d bridge xxx`创建网桥
```

此时如果启动docker\-compose，会启动两个容器，且两个容器在一个网桥（bridge模式）中。进入其中某一个容器，可以通过curl命令访问到另一个容器，比方说两个容器的ip地址是：`172.0.0.2`和`172.0.0.3`，那么进入`172.0.0.2`的容器之后就可以这么访问另一个容器：`curl http://172.0.0.3:8080`

这是通过ip来访问在同一个网桥的容器的方法，现在我们想通过容器名来访问，那么就需要用到`container_name`了。

#### network_mode

设置网络模式

```yaml
network_mode: "bridge"
network_mode: "host"
network_mode: "none"
network_mode: "service:[service name]"
network_mode: "container:[container name/id]"
```



### 4、使用container_name后的模板

```yaml
# 首先指定compose的版本
version: "3.2"
# 指定服务集合
services:
  # 指定某个服务的服务名（服务名唯一）
  tomcat01:
    container_name: tomcat01 # 相当于run --name
    # 指定创建当前这个服务使用的docker镜像是谁
    image: tomcat:8.0-jre8 # 镜像名:TAG，也可以只写镜像名。 # 相当于run image
    # 指定端口映射
    ports: # 相当于run -p
	  - "8080:8080"
	# 完成宿主机与容器中目录数据卷共享
	volumes: # 相当于run -v
	  - tomcatwebapps01:/usr/local/tomcat/webapps 
	networks: # 代表当前服务使用哪个网络桥，有这还不行，我们发现启动docker-compose的时候会报错，因为需要先指定networks，具体见下方。 # 相当于run --network
	  - hello
  # 再来一个服务
  tomcat02:
    container_name: tomcat02 # 相当于run --name
    image: tomcat:8.0-jre8
	ports:
	  - "8081:8080"
	volumes:
	  - tomcatwebapps02:/usr/local/tomcat/webapps 
	networks: # 代表当前服务使用哪个网络桥，有这还不行，我们发现启动docker-compose的时候会报错，因为需要先指定networks，具体见下方
	  - hello
volumes:
  tomcatwebapps01:
    external:
      false
  tomcatwebapps02:
# 指定networks
networks: # 定义服务用到桥
  hello: # 定义上面服务用到的网桥名称 默认创建就是bridge模式
```

此时tomcat01要访问tomcat02可以这样：

- 使用`docker container exec -it ... /bin/bash`进入tomcat01
- 使用`curl http://tomcat02:8080`（原先没使用`container_name`时我们是使用`curl http://172.0.0.3:8080`来访问的）

### 5、使用environment后的模板

```yaml
# 首先指定compose的版本
version: "3.2"
# 指定服务集合
services:
  mysql:
    image: mysql:5.7.32
    container_name: mysql
    ports:
      - "3307:3306"
    volumes:
      - mysqldata:/var/lib/mysql
      - mysqlconf:/etc/mysql
    # 传递环境变量
    environment: # 相当于run -e
      - MYSQL_ROOT_PASSWORD=root
    networks:
      - hello
volumes:
  mysqldata:
  mysqlconf:
networks:
  hello:
    external:
      true
```

**5\-1\.environment补充：**

```yaml
# environment可以这么写：
environment:
  - MYSQL_ROOT_PASSWORD=root
# environment还可以这么写：
environment:
  MYSQL_ROOT_PASSWORD: root
```

这里有个问题：

environment所指定的环境参数有时候可能是很敏感的，比方说mysql数据库的密码，直接在docker\-compose中暴露这些配置是很危险的，那么怎么来避免呢？可以用`env_file`来代替`environment`

### 6、使用`env_file`后的模板

```yaml
# 首先指定compose的版本
version: "3.2"
# 指定服务集合
services:
  mysql:
    image: mysql:5.7.32
    container_name: mysql
    ports:
      - "3307:3306"
    volumes:
      - mysqldata:/var/lib/mysql
      - mysqlconf:/etc/mysql
    # 传递环境变量
    # environment:
      # - MYSQL_ROOT_PASSWORD=root
    env_file:
      - ./mysql.env # 可以写绝对路径也可以写相对路径，这里由于我们将mysql.env文件创建在docker-compose.yml文件的同级目录下，因此可以直接写路径：./mysql.env，当然也可以直接这么写：mysql.env。mysql.env的具体内容见下方“mysql.env”
    networks:
      - hello
volumes:
  mysqldata:
  mysqlconf:
networks:
  hello:
    external:
      true
```

**`mysql.env:`**

```properties
# mysql环境参数
# 注意：必须是这种格式：key=value
MYSQL_ROOT_PASSWORD=root
```



### 7、使用command后的模板

```yaml
# 首先指定compose的版本
version: "3.2"
# 指定服务集合
services:
  redis:
    image: redis:5.0.10
    container_name: redis
    ports:
      - "6379:6379"
    volumes:
      - redisdata:/data # 因为redis的持久化数据就保存在/data中，所以数据卷映射到/data
    command:
      "redis-server --appendonly yes" # 相当于run镜像之后用来覆盖容器默认启动指令。这里为什么要这么写呢？首先解释一下redis-server和--appendonly yes，redis-server表示使用配置文件方式启动redis服务，--appendonly yes表示开启aof数据持久化。而redis容器内部默认的启动命令是“redis-server”而没有后面的“--appendonly yes”，现在我们希望加上“--appendonly yes”，也就是说要将原先的命令“redis-server”覆写成“redis-server --appendonly yes”，那么就需要使用command来进行命令覆写了
	networks:
	  - hello
volumes:
  redisdata：
networks:
  hello:
    external:
      true
```

### 8、使用`depends_on`后的模板

```yaml
version: "3.2"
services:
  tomcat01: # 服务名
    container_name: tomcat01_name # 容器名
    image: tomcat:8.0-jre8 
    ports: 
	  - "8080:8080"
	volumes:
	  - tomcatwebapps01:/usr/local/tomcat/webapps 
	networks:
	  - hello
	# 表示这个容器必须依赖哪些容器启动之后才能启动
	# 这个命令相当重要，因为它真正体现了docker-compose的编排作用
	# 注意，tomcat01服务不会等tomcat02、redis、mysql服务完全启动之后才启动，在tomcat02、redis、mysql服务启动到一定程度的时候tomcat01服务就会开始启动了
	depends_on: 
	  - tomcat02 # 注意，写的是服务名！不是容器名！
	  - redis # 注意，写的是服务名！不是容器名！
	  - mysql # 注意，写的是服务名！不是容器名！
  tomcat02: # 服务名
    container_name: tomcat02_name # 容器名
    image: tomcat:8.0-jre8
	ports:
	  - "8081:8080"
	volumes:
	  - tomcatwebapps02:/usr/local/tomcat/webapps 
	networks:
	  - hello
  redis: # 服务名
    image: redis:5.0.10
    container_name: redis # 容器名
    ports:
      - "6379:6379"
    volumes:
      - redisdata:/data
    command:
      "redis-server --appendonly yes" 
	networks:
	  - hello
  mysql: # 服务名
    image: mysql:5.7.32
    container_name: mysql # 容器名
    ports:
      - "3307:3306"
    volumes:
      - mysqldata:/var/lib/mysql
      - mysqlconf:/etc/mysql
    env_file:
      - ./mysql.env
    networks:
      - hello
volumes:
  tomcatwebapps01:
    external:
      false
  tomcatwebapps02:
  redisdata:
  mysqldata:
  mysqlconf:
networks:
  hello:
```

### 9、使用healthcheck后的模板

```yaml
# 首先指定compose的版本
version: "3.2"
# 指定服务集合
services:
  mysql:
    image: mysql:5.7.32
    container_name: mysql
    ports:
      - "3307:3306"
    volumes:
      - mysqldata:/var/lib/mysql
      - mysqlconf:/etc/mysql
    # 传递环境变量
    environment: # 相当于run -e
      - MYSQL_ROOT_PASSWORD=root
    networks:
      - hello
    # 用于心跳检测，检查容器是否健康运行
    # 注意，它在发送测试的时候底层走的是tcp，在日志中是不会对tcp的动作有所体现的，所以总的来看，healthcheck对于容器整体的服务来讲是属于锦上添花，它只起到一个检测心跳的作用，对容器的服务不会有任何的影响也不会有任何的副作用
    healthcheck:
      # 固定写法：
      test: ["CMD", "curl", "-f", "http://localhost"] # 当然，如果是要向远程docker发送测试，则需要将`localhost`替换为远程服务器的ip，不过一般情况下本机都会配备docker
      interval: 1m30s
      timeout: 10s
      retries: 3
volumes:
  mysqldata:
  mysqlconf:
networks:
  hello:
    external:
      true
```

### 10、使用sysctls后的模板

场景：linux下配置es的时候es启动时会报错，需要配置linux的内核参数之后es才能正常启动。那么现在是在容器中的es，容器它也可以想象成是一个linux系统，因此也可能需要配置内核参数之后es才能正常启动。此时就可以使用sysctls来配置内核参数

示例：

```yaml
sysctls:
  net.core.somaxconn: 1024
  net.ipv4.tcp_syncookies: 0
# 或者也可以这么写：
sysctls:
  - net.core.somaxconn=1024
  - net.ipv4.tcp_syncookies=0
```

模板示例：

```yaml
version: "3.0"
services:
  tomcat01:
    image: tomcat:8.0-jre8
    ports:
	  - "8080:8080"
	sysctls: # 用来修改容器中系统内部参数 并不是必须的 有些服务启动受容器内操作系统参数限制可能会无法启动必须通过修改容器中参数才能启动
	  - net.core.somaxconn=1024
  	  - net.ipv4.tcp_syncookies=0 
```

### 11、使用ulimits后的模板

指定容器内部的最大进程数

例如：指定最大进程数为65535，指定文件句柄数为20000（软限制，应用可以随时修改，不能超过硬限制）和40000（系统硬限制，只能root用户提高）

```yaml
ulimits:
  nproc: 65535
  nofile:
    soft: 20000
    hard: 40000
```

模板示例：

```yaml
version: "3.0"
services:
  tomcat01:
    image: tomcat:8.0-jre8
    ports:
	  - "8080:8080"
	ulimits: # 用于修改容器中系统内部的进程数限制 并不是必须的 日后使用时可根据当前容器运行服务要求进行修改
	  nproc: 65535
      nofile:
    	soft: 20000
    	hard: 40000
```

### 12、使用build后的模板

build指令作用：用来将指定DockerFile打包成对应镜像，然后再运行该镜像

示例：

```yaml
version: "3.0"
services:
  web01:
    build: # 用来指定DockerFile所在目录，启动服务时先根据build中DockerFile自动构建镜像，自动运行镜像
      context: ./web01 # 指定上下文目录DockerFile所在目录。首先，我们一般会这样放置一个DockerFile：创建一个文件夹，放置DockerFile，以及这个DockerFile用于构建镜像所需要的所有其它文件，比方说构建一个jar服务的镜像，那它可能就会依赖一个jar包，那么此时这个jar包应该放到该DockerFile同一目录的文件夹下，目的是在构建镜像的时候将DockerFile上下文一起打包发给docker-server。然后关键点来了，该文件夹一般会放到docker-compose.yml同级的目录下，而类似这样的文件夹可能会有非常多，那么docker-compose如何去锁定到底是要构建哪个DockerFile呢？显然就需要指定包含该DockerFile的文件夹的路径。这里我们用context来指定该路径，可以写绝对路径也可以写相对路径
      dockerfile: Dockerfile01 # 需要指定Dockerfile的文件名称，默认 就是Dockerfile，如果文件名就叫Dockerfile，那这个命令就不用再写了，而如果文件名不叫Dockerfile，就需要用该指令指定一下
    container_name: web01
    ports:
      - "8081:8081"
    networks:
      ...
```

### 13、`dns`与`dns_search`

`dns`：配置 dns 服务器，可以是一个值或列表

```yaml
dns: 8.8.8.8
------------
dns:
  - 8.8.8.8
  - 9.9.9.9
```

`dns_search`：配置 DNS 搜索域，可以是一个值或列表

```yaml
dns_search: example.com
------------------------
dns_search:
  - dc1.example.com
  - dc2.example.com
```

### 14、expose与ports

expose：暴露端口，只将端口暴露给连接的服务，而不暴露给主机

```yaml
expose:
  - "3000"
  - "8000"
```

ports：对外暴露的端口定义，和 expose 对应

```yaml
ports:   # 暴露端口信息  - "宿主机端口:容器暴露端口"
- "8763:8763"
- "8763:8763"
```

### 15、links

将指定容器连接到当前连接，可以设置别名，避免ip方式导致的容器重启动态改变的无法连接情况

```yaml
links: # 指定服务名称:别名 
  - docker-compose-eureka-server:compose-eureka
```

服务之间可以使用服务名称相互访问，links 允许定义一个别名，从而使用该别名访问其它服务，比如：

```yaml
version: '3'
services:
  web:
    build: .
    links:
      - "db:database"
  db:
    image: postgres
```

这样 web 服务就可以使用 db 或 database 作为 hostname 访问 db 服务了

## 指令

**compose 模板指令 和 指令 的区别**

- 模板指令

  用来书写在`docker-compose.yml`文件中的指令称之为模板指令（用来为服务进行服务的）

- 指令

  用来对整个`docker-compose.yml`对应的这个项目操作（写在`docker-compose`命令之后的命令）

### 命令对象与格式

对于Compose来说，大部分命令的对象既可以是项目本身，也可以指定为项目中的服务或者容器。如果没有特别的说明，命令对象将是项目，这意味着项目中所有的服务都会受到命令影响。

执行`docker-compose [COMMAND] --help`或者`docker-compose help [COMMAND]`可以查看具体某个命令的使用格式。

`docker-compose`命令的基本使用格式是：

```
docker-compose [-f=<arg>...] [options] [COMMAND] [ARGS...]
```

### 命令选项

- `-f, --file FILE`指定使用的Compose模板文件，默认为`docker-compose.yml`，可以多次指定

- `-p, --project-name NAME`指定项目名称，默认将使用所在目录名称作为项目名

  ```yaml
  volumes:
    mysqldata: # 比方说项目名是hello01，那么这里自动生成的卷名就是hello01_mysqldata。而如果我们使用-p指定项目名为app01，那么这里自动生成的卷名就会是app01_mysqldata
  ```

  

- `--x-networking`使用Docker的可插拔网络后端特性

- `--x-network-driver DRIVER`指定网络后端的驱动，默认为bridge

- `--verbose`输出更多调试信息

- `-v,--version`打印版本并退出

### 命令使用说明

#### up

格式为：`docker-compose up [options] [SERVICE...]`

- 该命令十分强大，它将尝试自动完成包括构建镜像，（重新）创建服务，启动服务，并关联服务相关容器的一系列操作
- 链接的服务都将会被自动启动，除非已经处于运行状态
- 可以说，大部分时候都可以直接通过该命令来启动一个项目
- 默认情况，`docker-compose up`启动的容器都在前台，控制台将会同时打印所有容器的输出信息，可以很方便进行调试
- 当通过`ctrl+c`停止命令时，所有容器将会停止
- 如果使用`docker-compose up -d`，将会在后台启动并运行所有的容器。一般推荐生产环境下使用该选项
- 默认情况，如果服务器已经存在，`docker-compose up`将会尝试停止容器，然后重新创建（保持使用`volumes-from`挂载的卷），以保证新启动的服务匹配`docker-compose.yml`文件的最新内容

```
$ docker-compose up # 全部容器都启动
$ docker-compose up redis01 # 只启动redis01容器，注意：如果redis01容器有depends_on依赖，那么依赖的容器也会被启动
$ docker-compose -f 文件名 up # 默认文件名就是docker-compose.yml，但是如果文件名不是docker-compose.yml，则需要使用-f指定具体文件名
$ docker-compose up -d # 后台启动
...
```

#### down

- 此命令将会停止up命令所启动的容器，并移除网路

  注意：

  1、只会移除它自动创建的网桥，如果是用户手动创建的网桥，比方说上面那些模板示例中的名为hello的网桥它是不会移除的，对于像hello这样的网桥它会直接跳过；

  2、数据卷不会被移除

#### exec

- 进入指定的容器

```
$ docker-compose exec mysql01 /bin/bash # 注意！exec后面跟的应该是一个服务名而不是容器ID

# 我们来看看docker的exec：
$ docker exec -it 23fdd0a692b /bin/bash # 两者的区别就在于docker需要写-it，而且docker的exec后面跟的是容器ID而不是服务名
```



#### ps

格式为`docker-compose ps [options] [SERVICE...]`

列出项目中目前的所有容器

选项：

- `-q`只打印容器的ID信息

#### restart

格式为`docker-compose restart [options] [SERVICE...]`

重启项目中的服务，可以重启项目中的全部服务，也可以重启项目中的某些服务

选项：

- `-t,--timeout TIMEOUT`指定重启前停止容器的超时（默认为10秒）

#### rm

格式为`docker-compose rm [options] [SERVICE...]`

删除所有（停止状态的）服务容器。推荐先使用`docker-compose stop`命令来停止容器

选项：

- `-f,--force`强制直接删除，包括非停止状态的容器。一般尽量不要使用该选项
- `-v`删除容器所挂载的数据卷。一般不要加`-v`

#### start

格式为`docker-compose start [SERVICE...]`

启动已经存在的服务容器

#### stop

格式为`docker-compose stop [options] [SERVICE...]`

停止已经处于运行状态的容器，但不删除它。通过`docker-compose start`可以再次启动这些容器

选项：

- `-t,--timeout TIMEOUT`停止容器时候的超时（默认为10秒）

#### top

查看各个服务器内运行的进程

```
$ docker-compose top # 查看项目中所有进程服务
$ docker-compose top redis01 # 查看项目中redis01服务
```

#### pause

格式为`docker-compose pause [SERVICE...]`

暂停服务

#### unpause

格式为`docker-compose unpause [SERVICE...]`

恢复处于暂停状态中的服务

#### logs

格式为`docker-compose logs [options] [SERVICE...]`

> Options:
>
> ```none
> --no-color          单色输出，不显示其他颜.
> -f, --follow        跟踪日志输出，就是可以实时查看日志
> -t, --timestamps    显示时间戳
> --tail="all"        从日志的结尾往上的多少行开始显示，--tail=200
> ```

查看项目所有或某一个服务的日志

#### kill

通过发送 SIGKILL 信号来停止指定服务的容器

```
$ docker-compose kill eureka
```

#### pull

下载服务镜像

#### scale

设置指定服务运气容器的个数，以 service=num 形式指定

```
$ docker-compose scale user=3 movie=3
```

#### run

在一个服务上执行一个命令

```
$ docker-compose run web bash
```



## docker可视化工具

### 安装Portainer

官方安装说明：https://www.portainer.io/installation/

```
$ docker pull portainer/portainer
$ docker volume create portainer_data
$ docker run -d -p 8000:8000 -p 9000:9000 --name=portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer
```

这里的`--restart`表示容器在什么情况下需要重启，而`--restart=always`表示只要容器关闭掉就立即重启

portainer要监测容器的状态，必须要与docker引擎建立连接，所以要告诉它docker引擎在哪（告诉它`docker.sock`文件在哪，它就可以知道docker引擎中的所有容器的状态了），在linux下`docker.sock`文件的路径一般是`/var/run/docker.sock`

同时，portainer也需要用户密码登录进去，而且设有权限访问限制（哪些角色可以管理哪些容器，哪些角色不能管理哪些容器），所以这些数据也需要用数据卷持久化到外部

同时，portainer开放了端口8000去跟`docker.sock`通信，开放了端口9000给外部

### 登录和使用

```
http://localhost:9000
```

使用方式查网络资料

