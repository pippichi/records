# 打印

%d - 整型

%c - 字符

%f - 浮点数字，小数

%lf - 双精度浮点数（double）

%p - 以地址形式打印

%x - 16进制数字

# 类型long

C语言标准规定

sizeof(long)>=sizeof(int)

所以有些编译器中long长度就是4byte，而有些地方是8byte

# 小数强制转float

float w = 95.1;

直接这么写的话默认95.1会被编译器认为是一个double类型的小数，如果把一个double类型的放到float类型里面去会发生精度丢失

因此应该这么写：

float w = 95.1f;

加一个f，明确告诉编译器这是一个float类型的小数

# scanf中蕴含的取地址操作的知识点

int n1 = 0;

int n2 = 0;

scanf("%d%d", &n1, &n2);

首先使用int在内存中申请两个地址，用于存放n1和n2，之后使用scanf的时候我们希望将数存到n1和n2，怎么做到的呢？直接告诉它n1和n2的地址在哪，找到地址就能存了，那么怎么告诉它呢？使用&即可。

# C语言如何定义变量

<span style="color:red">c语言定义变量时必须将变量定义到块中的最前面！</span>

比如：

```c
int main(){
    int a1 = 0;
    int a2 = 0;
    scanf("%d%d", &a1, &a2);
    int sum = a1 + a2;
    printf("sum = %d\n", sum);
    return 0;
}
```

这样是不行的，因为sum定义到scanf下面去了，正确的做法是将sum定义到前面：

```c
int main(){
    int a1 = 0;
    int a2 = 0;
    int sum = 0;
    scanf("%d%d", &a1, &a2);
    sum = a1 + a2;
    printf("sum = %d\n", sum);
    return 0;
}
```

# 关键字

## extern

情景：

在一个文件中声明一个全局变量，在另一个文件中如何使用这个外部定义的全局变量呢？需要这么声明一下：

```c
extern int xxx;
```

## sizeof

比方说有变量 int a

则可以这么写：

| 表达式      | 是否合法 | 值   |
| ----------- | -------- | ---- |
| sizeof(a)   | 1        | 4    |
| sizeof(int) | 1        | 4    |
| sizeof a    | 1        | 4    |
| sizeof int  | 0        |      |

再比如有数组 int arr[10] = {0}，则：

sizeof(arr) = 40

则可计算数组长度：

sizeof(arr) / sizeof(arr[0]) = 10

# VS下的一些问题

## 不安全的库函数

vs下scanf是不推荐使用的，vs编译器给我们提供了一个scanf_s来代替scanf，但是缺乏了跨平台的特性（比方说gcc他就不认识scanf_s，只认识标准C语言提供的scanf）

其他的还有例如strcpy、strlen、strcat等都是不安全的，那么VS编辑器都会为我们提供对应的安全的版本：strcpy_s、strlen_s、strcat_s等

同样，如果在VS编辑器下不使用这些_s的库函数而是使用标准C语言提供的函数，那VS编辑器都会报错（2013版本之后）

<span style="font-weight:bold">解决方法：</span>

在头文件头部加一句话

如果是在VS编辑器下可以使用 _CRT_SECURE_NO_WARNINGS

```c
#define _CRT_SECURE_NO_WARNINGS 1
```

那么有没有什么一劳永逸的方法呢？

可以在newc++file.cpp文件中加如这句话：

![image-20210130132310427](c语言.assets/image-20210130132310427.png)

![image-20210130132331165](c语言.assets/image-20210130132331165.png)

# 常量

C语言的常量分以下几种：

- 字面常量

  直接写出一个数字，这种的叫字面常量

- const修饰的常变量

- #define定义的标识符常量

- 枚举常量

  ```c
  enum Sex {
  	MALE,
  	FEMALE,
  	SECRET
  };
  int main() {
  	enum Sex x = MALE;
  	printf("%d\n", MALE); // 0
  	printf("%d\n", FEMALE); // 1
  	printf("%d\n", SECRET); // 2
  	return 0;
  }
  ```

  

# 字符串

```c
int main() {
	// "abc" -- 'a', 'b', 'c', '\0'  --  '\0'是字符串的结束标志
	char arr[] = "abc";
	// 等价于：
	char arr[] = { 'a', 'b', 'c', 0 };
	// 因为 '\0' 的ascii码值就是0，因此还等价于：
	char arr[] = { 'a', 'b', 'c', '\0' };
}
```

## strlen

用来计算字符串长度

## 字符串中的 \ 

转义符号

面试题：

```c
int main()
{
    // \t算一个字符
    // 重点解释一下\32，\32的意思是32是一个八进制数，需要先转成10进制，也就是3 * 8^1 + 3 * 8^0 = 26，再将26转成ascii码对应的字符，因此这里\32表示一个字符
    // 如果是 \32 那意思就是把32当成8进制数（所以\382这样的写法是错误的，错在中间这个8，八进制数最多到7），如果是 \x32 则表示将32当成16进制数
    printf("%d\n", strlen("c:\test\32\test.c"));
    // 答案是13
}
```

# 文件结束标志符EOF

# 二进制数的操作

## 负数

只要是整数，在内存中存储的都是二进制的补码，负数也不例外，而我们使用它的时候是使用它的原码

```
// 知识点
// 正数的原码、反码、补码三码统一（或者叫三码相同）
// 负数就不多说了
```



比方说：

```c
int main{
    int a = 0;
    int b = ~a;
    // 由于int有四字节，也就是8 * 4 = 32bit位：00000000000000000000000000000000
    // 所以b就是a的反码：11111111111111111111111111111111（此时这个就是存在内存中的b的补码）
    // 那么当我们取出b的时候是用它的原码，思考一下原码到反码是什么操作：符号位不动，其余位取反最后加1，那么反过来就是先减1，再符号位不动，其余位取反
    // 那么我们可以得到b的原码：10000000000000000000000000000001（也就是说b此时就是-1）
    printf("%d\n", b);
    // 输出为-1
    return 0;
}
```

