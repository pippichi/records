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

## 使用

```
首先指定compose的版本
version: "3.0"
指定服务集合
services:
  指定某个服务的服务名（服务名唯一）
  tomcat01:
    指定创建当前这个服务使用的docker镜像是谁
    image: tomcat:8.0-jre8
    指定端口映射
    ports:
	  - "8080:8080"
  再来一个服务
  tomcat02:
    image: tomcat:8.0-jre8
	ports:
	  - "8081:8080"
```

接下来运行`docker-compose`：

```
$ docker-compose up
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

- 必须保证运行命令的目录存在docker\-compose\.yml
- docker\-compose默认启动的时候夯在前台

