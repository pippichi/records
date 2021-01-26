# MySQL触发器

Trigger：当一件事情发生了，会引发另外的一件事情发生，我们称之为触发器。

在MySQL里触发器的意思就是，当我们对一张表操作的时候，会触发对其他表相关的一些操作。

MySQL是关系型的数据库，表与表之间是有联系的，当我们修改一张表的时候，也希望另一张表也做相应的修改。

比如A是学员表，B是成绩表，如果A表中一个学院的名字改了，那么B表中的学员的名字也要做相应的修改。

语法：

```mysql
DELIMITER //
CREATE TRIGGER 名字 时间(AFTER|BEFORE) 动作(INSERT|UPDATE|DELETE) ON 表1
FOR EACH ROW
BEGIN
sql语句 表2;
END //
DELIMITER ;
```

解释：某个动作(insert|update|delete)，这里我们称该动作叫d1，在表1执行的时候，某些sql语句会在表2执行，执行时间(after|before)为d1的前面或者后面

<span style="font-weight:bold">那FOR EACH ROW是什么意思呢？</span>

意思是BEGIN END符中间的sql语句（按上图来讲就是“sql语句 表2;”这一块），一行一行地执行

## New和Old

New：指的是表1修改后的那行

Old：指的是表1修改之前那行的内容

例子：

```mysql
DROP TRIGGER IF EXISTS x1;
DELIMITER //
CREATE TRIGGER x1 AFTER UPDATE ON 表1
FOR EACH ROW
BEGIN
UPDATE 表2 SET money=400 WHERE id=old.id;
END //
DELIMITER ;
```

这个时候修改表1的id为1的那一行，表2的id为1的那一行也会更改

<font color="red">但是如果把上面的old换成new</font>，假设表1存在某条记录：

| id   | name |
| ---- | ---- |
| 2    | test |

我们执行语句：

```mysql
update 表1 set id=30 where name="test";
```

这个时候表1确实会被改动，因为确实存在这样一条记录。

此时old.id=2，而new.id=30

假设表2又没有id为30的，那这个时候表2就不会有数据被改动

例子2：

表1：

| id   | score |
| ---- | ----- |
| 1    | 99    |
| 2    | 59    |
| 3    | 63    |

表2：

| id   | state |
| ---- | ----- |
| 1    | pass  |
| 2    | fail  |
| 3    | pass  |



```mysql
DROP TRIGGER IF EXISTS x1;
DELIMITER //
CREATE TRIGGER x1 AFTER UPDATE ON 表1
FOR EACH ROW
BEGIN
IF new.score >= 60 THEN
UPDATE 表2 SET state="pass" WHERE id=new.id;
ELSEIF new.score < 60 then
UPDATE 表2 SET state="fail" WHERE id=new.id;
END IF;
END //
DELIMITER ;
```

上面的new.id也可以都换成old.id

这个时候如果修改表1的score，表2的state也会进行相应的修改

例子3：

```mysql
DROP TRIGGER IF EXISTS x1;
DELIMITER //
CREATE TRIGGER x1 BEFORE INSERT ON 表1
FOR EACH ROW
BEGIN
INSERT INTO 表2 VALUES("xx");
END //
DELIMITER ;
```

由于是before

因此先执行表2的插入操作，再执行表1的insert动作

## 在触发器中定义变量：

```mysql
DELIMITER $
CREATE TRIGGER user_log AFTER INSERT ON users 
FOR EACH ROW
BEGIN
DECLARE s1 VARCHAR(40) character set utf8;
DECLARE s2 VARCHAR(20) character set utf8;#后面发现中文字符编码出现乱码，这里设置字符集
SET s2 = " is created";
SET s1 = CONCAT(NEW.name,s2);     #函数CONCAT可以将字符串连接
INSERT INTO logs(log) values(s1);
END $
DELIMITER ;
```

## 查看触发器

```mysql
SHOW TRIGGERS;
```

如果SHOW TRIGGERS 语句无法查看指定的触发器，我们还可以在triggers表中查看触发器信息：

```mysql
SELECT * FROM information_schema.triggers;
```

```mysql
SELECT * FROM information_schema.triggers WHERE TRIGGER_NAME="xxx";
```

## 查看触发器创建语句

```mysql
SHOW CREATE TRIGGER "xxx";
```



## 删除触发器

```mysql
DROP TRIGGER IF EXISTS "trigger_name"
```



## 限制和注意事项

触发器会有以下两种限制：

- 触发程序不能调用将数据返回客户端的存储程序，也不能使用采用CALL语句的动态SQL语句，但是允许存储程序通过参数将数据返回触发程序，也就是存储过程或者函数通过OUT或者INOUT类型的参数将数据返回触发器是可以的，但是不能调用直接返回数据的过程。

- 不能再触发器中使用以显示或隐式方式开始或结束事务的语句，如START TRANS-ACTION,COMMIT或ROLLBACK。

注意事项：

- MySQL的触发器是按照BEFORE触发器、行操作、AFTER触发器的顺序执行的，其中任何一步发生错误都不会继续执行剩下的操作，如果对事务表进行的操作，如果出现错误，那么将会被回滚，如果是对非事务表进行操作，那么就无法回滚了，数据可能会出错。
- MySQL的触发器中不能对同一个表进行增删改操作，否则会出错。

## 总结

触发器是基于行触发的，所以删除、新增或者修改操作可能都会激活触发器，所以不要编写过于复杂的触发器，也不要增加过得的触发器，这样会对数据的插入、修改或者删除带来比较严重的影响，同时也会带来可移植性差的后果，所以在设计触发器的时候一定要有所考虑。

触发器是一种特殊的存储过程，它在插入，删除或修改特定表中的数据时触发执行，它比数据库本身标准的功能有更精细和更复杂的数据控制能力。

数据库触发器有以下的作用：

- 安全性。可以基于数据库的值使用户具有操作数据库的某种权利。

 \# 可以基于时间限制用户的操作，例如不允许下班后和节假日修改数据库数据。

 \# 可以基于数据库中的数据限制用户的操作，例如不允许股票的价格的升幅一次超过10%。

- 审计。可以跟踪用户对数据库的操作。  

 \# 审计用户操作数据库的语句。

 \# 把用户对数据库的更新写入审计表。

- 实现复杂的数据完整性规则

 \# 实现非标准的数据完整性检查和约束。触发器可产生比规则更为复杂的限制。与规则不同，触发器可以引用列或数据库对象。例如，触发器可回退任何企图吃进超过自己保证金的期货。

 \# 提供可变的缺省值。

- 实现复杂的非标准的数据库相关完整性规则。触发器可以对数据库中相关的表进行连环更新。例如，在auths表author_code列上的删除触发器可导致相应删除在其它表中的与之匹配的行。

 \# 在修改或删除时级联修改或删除其它表中的与之匹配的行。

 \# 在修改或删除时把其它表中的与之匹配的行设成NULL值。

 \# 在修改或删除时把其它表中的与之匹配的行级联设成缺省值。

 \# 触发器能够拒绝或回退那些破坏相关完整性的变化，取消试图进行数据更新的事务。当插入一个与其主健不匹配的外部键时，这种触发器会起作用。例如，可以在books.author_code 列上生成一个插入触发器，如果新值与auths.author_code列中的某值不匹配时，插入被回退。

- 同步实时地复制表中的数据。

- 自动计算数据值，如果数据的值达到了一定的要求，则进行特定的处理。例如，如果公司的帐号上的资金低于5万元则立即给财务人员发送警告数据。