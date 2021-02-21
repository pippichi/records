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

```c
int n1 = 0;
int n2 = 0;
scanf("%d%d", &n1, &n2); // 或者scanf("%d,%d", &n1, &n2); 这样写的话输入的时候数字之间的逗号不能丢掉！同理scanf("%d#%d", &n1, &n2);那么数字之间的#不能丢掉！
```

首先使用int在内存中申请两个地址，用于存放n1和n2，之后使用scanf的时候我们希望将数存到n1和n2，怎么做到的呢？直接告诉它n1和n2的地址在哪，找到地址就能存了，那么怎么告诉它呢？使用&即可。

那如果是需要将输入内容保存到一个字符串数组呢？可以使用&也可以不使用&：

```c
char pwd[20] = {0};
scanf("%s", pwd);
// 经过实验也可以这么写：
scanf("%s", &pwd);
```



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

- 在一个文件中声明一个全局变量，在另一个文件中如何使用这个外部定义的全局变量呢？需要这么声明一下：

  ```c
  extern int xxx;
  ```

- 如果是要引入外部的函数呢？

  t1.c文件中有函数：

  ```c
  int add(int x, int y){
      return x + y;
  }
  ```

  我们希望在main.c中引入该函数：

  ```c
  // 在main函数上方声明外部函数add
  extern int add(int, int);
  int main(){
      int a = 0;
      int b = 0;
      int sum = add(a, b);
      printf("%d\n", sum);
      return 0;
  }
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

## auto

局部变量出了作用域自动销毁，因此局部变量也叫自动变量，c语言中自动变量（局部变量）前面有一个关键字auto修饰，只不过一般情况下我们把它省略了：

```c
int main() {
	auto int a = 10; // 局部变量-自动变量
}
```

等价于：

```c
int main() {
	int a = 10; // 局部变量-自动变量
}
```

## register

寄存器关键字

我们知道计算机存储数据有四种容器：

```
按照速度排名：
寄存器 > 高速缓存 > 内存 > 硬盘 
```

```
寄存器和高速缓存存在的原因：
CPU速度越来越快导致内存读取速度跟不上CPU的处理速度，因此出现了高速缓存和寄存器
以后CPU都去寄存器读取数据，数据的流向变成了：从内存到高速缓存，再从高速缓存到寄存器，再从寄存器到CPU；CPU找数据的方式也变成从上到下：先去寄存器找，找不到再去高速缓存找，高速缓存再找不到再去内存找
```

### 使用方法

当我们频繁用到某个变量的时候就建议把它放到寄存器：

```c
int main(){
    register int a = 10; // 建议把a定义成寄存器变量
    return 0;
}
```

那么为什么是建议呢？很简单，因为寄存器造价贵，在计算机中的个数比较少可能就几十个，所以要省着用

那最终到底它会不会被定义成寄存器变量呢？这个由编译器说了算

## signed

有符号数，平时我们定义有符号数的时候是这么定义的：

```c
int a = 10;
a = -2;
```

其实它省略了一个关键字signed

也就是说它等价于：

```c
signed int a = 10;
a = -2;
```

## static ★

- 用于修饰局部变量从而延长局部变量的生命周期

  <span style="color: red">重点例子：</span>

  ```c
  void test(){
      static int a = 1; // 一个静态的局部变量
      a++;
      printf("a = %d\n", a);
  }
  int main(){
      int i = 0;
      while(i < 5){
          test();
          i++;
      }
      return 0;
  }
  ```

  ```
  答案：2 3 4 5 6
  ```

  解释：

  由于使用static修饰了a变量，在第二次以及之后几次进入test的时候static int a = 1;这句代码是会直接跳过不执行的，而且a变量也不会因为出了作用域而被销毁，也就会出现输出2 3 4 5 6的结果

- 用于修饰全局变量

  改变全局变量的作用域 - 让静态的全局变量只能在自己所在的源文件内部使用，出了源文件就没法再使用了

  比方说有一个文件t1.c中定义了static修饰的全局变量，那这个时候就不能在另一个文件t2.c中访问这个全局变量了，只能在t1.c这个文件自身内部访问到这个static修饰的全局变量

- 用于修饰函数

  比方说我在t1.c里面设置一个函数：

  ```c
  int Add(int a, int b){
      return a + b;
  }
  ```

  然后我们在main.c中引入这个函数：

  ```c
  extern int Add(int, int);
  int main(){
      int sum = Add(2, 3);
      return 0;
  }
  ```

  这个时候函数是正常可用的

  但如果我们给函数加一个static：

  ```c
  static int Add(int a, int b){
      return a + b;
  }
  ```

  然后还是一样，在main.c中引入该函数Add

  此时就会报错了：找不到外部符号Add

  > 不准确的说static改变了函数的作用域
  >
  > 准确的说static改变了函数的链接属性（main.c中使用t1.c中的函数就是一种链接）
  >
  > 当外部函数没有static修饰的时候它具有外部链接属性，而当外部函数被static修饰之后它具有内部链接属性

## #define

不是c语言关键字，是预处理指令

### 定义标识符常量

```c
#define X 10
```

### 定义宏

```c
#define MAX(A, B) (A>B?A:B)

int main(){
    int max = MAX(10, 20);
    return 0;
}
```

# c语言函数

## getchar()和putchar()

getchar()用于接收键盘的字符，putchar()用于将字符输出（相当于printf()）

```c
int ch = getchar();
putchar(ch); // 用于输出getchar()获取的字符，相当于printf("%c\n", ch)
```

案例：

```c
// 由于scanf读到空格就停止了，而我们又希望将空格后面的字符也全部都读掉以防止在scanf后面使用getchar()函数的时候getchar()误读到一些字符。那么可以怎么做呢？
char password[20] = { 0 };
scanf("%s", password);
int ch = 0;
// 我们知道用户输入字符到最后会按下回车键以结束，而这个回车键就是\n，所以我们可以通过判断getchar()是否读到\n来判断是否已经读完了用户所输入的所有字符
// 通过这个原理，我们就可以编写以下while循环了：
while ((ch = getchar()) != '\n') {
    ; // 只写一个;表示循环里面什么也不做
}
```



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

## strcpy

用于赋值字符串到变量

## strcmp

 ‘==’ 不能用来比较两个字符串是否相等，应该使用库函数strcmp

```c
#include <string.h>
strcmp(str1, str2) == 0 // 等于0说明两个字符串相等；如果str1 > str2则返回大于0的数字；如果str1 < str2则返回小于0的数字
```



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

EOF -> end of file -> -1，代表文件结束

可能在循环中使用到：

```c
int ch = 0;
// 如何让下面的循环停下来呢？ ctrl + z
while((ch=getchar()) != EOF){putchar(ch);}
```



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

# 指针

## 内存

内存中的地址编号怎么来的：

![image-20210211100637687](c语言.assets/image-20210211100637687.png)

如果是32位，则会有32根地址线，每根地址线都有正负电，代表1和0，那么就会有如上图所示的2^32种可能，从上到下排下来其实就是0、1、2、3、...这样的编号，那么每一个编号其实就是内存中的一个地址编号

### 内存一个地址占多大空间

首先我们知道计算机中有空间单位：bit byte kb mb gb tb pb

假设一个地址占1bit，那么32位的机器能有多大呢？如上图32位能表示2^32个地址，也就是2^32bit，那么除以8就是byte，再除以1024就是kb，再除以1024就是mb，再除以1024就是gb，到最后gb单位的时候我们发现才0.5g，这导致就算我们给个1g内存也是没用的，因为它最大也就能表示0.5g

> 一个内存地址空间是以一个byte来划分的

例子：

```c
int a = 10;
```

由于int是4字节的，所以会申请4字节内存空间：

![image-20210211112649231](c语言.assets/image-20210211112649231.png)

## 取地址操作符&

```c
int a = 10; // 4字节
// &a; // 取地址
printf("%p\n", &a);
```

注意打印地址用的是%p，显示的值是以16进制形式显示的

## 使用指针变量存放地址

```c
int a = 10;
int* p = &a;
```

解读：p现在是一个指针变量，他的类型是int*，p变量里面存的是a的地址

```c
printf("%p\n", &a);
printf("%p\n", p);
// 这两个东西打印出来是一样的
```

##  通过指针找到变量

那么我们存指针变量是为什么呢？

是因为有朝一日我们要用到这个变量，这个时候就需要这么用：

```c
// 假设p是一个指针变量
*p // * -- 解引用操作符
// *p能找到指针p指向的变量
```

例如：

```c
int a = 10;
int* p = &a;
*p = 20;
printf("a = %d\n", a); // 输出： a = 20
```

图解：

![image-20210211114331828](c语言.assets/image-20210211114331828.png)

## 指针占用的空间

我们知道指针是用来存放内存地址的，所以32位机上指针需要表示的就是32个bit位，也就是4字节，这就是一个指针占用的内存大小，那如果是64位机，那就是8字节

# 结构体

一种我们自己创造出来的类型，用于表达复杂对象

使用方式：

```c
struct Book{
    char name[20];
    short price;
}
int main(){
    struct Book b1 = {"C语言", 55};
    printf("书名：%s\n", b1.name);
    printf("价格：%d\n", b1.price);
    b1.price = 15;
    printf("价格：%d\n", b1.price);
    return 0;
}
```

## 结构体中字符串的赋值（strcpy）

还是上面那个结构体，我们修改price的时候一切正常，但是像修改price一样修改name字符串的时候就会有问题了，因为结构体中的字符串name本质上存的是一个地址，所以如果直接b1.name = "C++"；这样改的话肯定不行，应该使用string.h头文件的strcpy函数：

```c
// strcpy(修改目的地, 修改来源)
strcpy(b1.name, "C++");
```



## 结构体指针

比方说上面那个结构体Book，它这个类型的指针就是：

```c
struct Book* p;
// 注意这里struct Book*是一起的，表示该变量是指针变量，是struct Book类型的指针变量
```

因此我们可以：

```c
struct Book b1 = {"C语言", 55};
struct Book* p = &b1;
printf("%s\n", (*p).name);
```

### 箭头符号(->)

可以用一种更加方便的方式访问结构体指针指向的结构体中的成员变量：

```c
// 刚才我们访问成员变量name的时候是这么访问的：(*p).name
// 其实还可以这么写：
p->name // 表示p所指向的那个结构体的成员变量name
```

# 悬空else

else跟离它最近的if匹配，所以以下代码：

```c
if(2 == 1)
    if(2 == 2)
        printf("%d\n", 1);
else
    printf("%d\n", 2);
```

输出是：啥也不输出

解释：最后一个else匹配到了第二个if

# switch

注意case后面不一定需要写东西，也不一定一定要加上break，也不一定一定要加default：

```c
int x = 2;
switch (x) {
	case 0:
	case 1:
		printf("%d\n", 1);
	case 2:
		printf("%d\n", 2);
		printf("%d\n", 3);
	case 3:
		printf("%d\n", 4);
		break;
	case 4:
		printf("%d\n", 5);
    case 5:
		printf("%d\n", 6);
        break; // 注意！最后一个case最好加上break，因为下次可能还要再加case，如果下次加了新case但是忘记给这个case加上break，则可能出现bug
}
```

输出：

```
2
3
4
```

switch语句如果不加break就会一直往下走，包括如果下面有default语句也会执行里面的代码，直到走完整个switch语句，例如：

```c
int func(int a){
    int b;
    switch (a){
        case 1: b = 10;
        case 2: b = 20;
        case 3: b = 16;
        default: b = 0;
    }
    return b;
}
// 问func(1)的值是多少？ 答案：0
```



# for循环

问：下面这段代码循环几次？

```c
int i = 0;
int k = 0;
for(i=0,k=0;k=0;i++,k++){
    k++;
}
```

答：0次

解释：

因为for循环第一个分号后面（也就是第二个参数）其实是一个判断语句，我们知道判断语句0为假，1为真，那么k=0表示将k赋值为0，那么k等于0，导致该判断语句值为0，也就是说该判断语句为假，所以它一次都不会循环。当然，如果我们这里把k赋值为非0，比方说k=1，那么就会变死循环。

# Sleep

让程序暂停多少秒再执行

```c
#include <windows.h>
int main(){
    Sleep(1000); // 表示睡1000ms
    return 0;
}
```

# system

用于执行系统函数

```c
// 经过实验发现，windows系统有一个命令叫“cls”，执行这个命令会清空cmd窗口
#include <stdlib.h>
int main(){
    system("cls"); // 执行这个代码的意思就是清空cmd窗口
    return 0;
}
```

