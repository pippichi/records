# 类型

<span style="font-weight:bold">整型家族：</span>

- char
  - unsigned char
  - signed char

- short
  - unsigned short或unsigned short int
  - signed short或signed short int

- int
  - unsigned int
  - signed int

- long
  - unsigned long或unsigned long int
  - signed long或signed long int

<span style="font-weight:bold">浮点型家族：</span>

- float
- double

<span style="font-weight:bold">构造类型（自定义类型）：</span>

- 数组类型

  int [10]，int[5]，char [5]这些都是不同的数组类型

- 结构体类型 struct

- 枚举类型 enum

- 联合类型 union

<span style="font-weight:bold">指针类型：</span>

int* p，char* p，void* p等

<span style="font-weight:bold">空类型：</span>

void表示空类型（无类型）

通常应用于函数的返回类型、函数的参数、指针类型

举例：

```c
void test(){
    printf("ok\n");
}
int main(){
    test(100); // 正常输出ok，虽然这里传了个100过去，而test函数并没有形参去接它，但是函数依然没有报错，正常执行了。这是c语言比较模棱两可的地方。
    return 0;
}
```

那么对于上面这种函数，显然它不需要接收任何参数，那么我们可以这样写：

```c
void test(void){ // 后面那个void意思是我这个函数它是不需要参数的
    printf("ok\n");
}
int main(){
    test(100); // 这个时候如果还传个100过去，那编译器就会报警告了
    return 0;
}
```



## 类型的意义

- 使用类型开辟内存空间的大小（大小决定使用范围）
- 如何看待内存空间的视角（比方说int和float，明明都是4字节的，内存中存储的方式却不一样，那是因为一个是int一个是float）



# 大小端（解释整型数存储到内存时有时候是按字节倒着存的现象）

现象：

```c
int b = -10; // 那么b在内存中它很可能是存了个：0xf6ffffff
// 解释：b的原码、反码、补码：
// 10000000000000000000000000001010 - 原码
// 11111111111111111111111111110101 - 反码
// 11111111111111111111111111110110 - 补码
// 补码十六进制：0xfffffff6
// 而在内存中它是按照字节倒着存的，所以变成了：0xf6ffffff
```



<span style="font-weight:bold">什么是大端小端：</span>

> 大端（存储）模式（也被称为大端字节序存储模式），是指数据的低位保存在内存的高地址中，而数据的高位，保存在内存的低地址中；
>
> 小端（存储）模式（也被称为小端字节序存储模式），是指数据的低位保存在内存的低地址中，而数据的高位，保存在内存的高地址中。

```c
// 比方说有数：0x11223344这样一个数
// 那在内存中可以这样存：11 22 33 44，也可以这样存：44 33 22 11，当然也可以这样存：11 33 22 44，存的方法有千千万万种，但是最人性化的也就两种：11 22 33 44 和 44 33 22 11。这两种模式分别叫大端模式和小端模式
```

![image-20210305172029330](c语言进阶.assets/image-20210305172029330.png)

为什么说11 22 33 44是属于大端模式呢？因为在十进制中我们按照万千百十个这样的顺序的话，万是属于高位的，个是属于低位的，那么十六进制也是一样，11 22 33 44中11是属于高位的，44是属于低位的，因为高位存在低地址，11 22 33 44被称为大端模式，同理44 33 22 11则被称为小端模式

所以我们上面的0xfffffff6以f6ffffff的形式存储在内存中，那显然就是小端模式了



<span style="font-weight:bold">为什么会有大小端模式之分呢？</span>

因为在计算机系统中，我们是以字节为单位的，每个地址单元都对应着一个字节，一个字节为8bit，但是c语言中除了8bit的char之外，还有16bit的short，32bit的long（要看具体的编译器），另外，对于位数大于8位的处理器，例如16位或者32位的处理器，由于寄存器宽度大于一个字节，那么必然存在存储时如何安排多个字节的存储顺序的问题，因此就导致了大端存储模式和小端存储模式。



写程序判断当前机器是大端还是小端：

```c
int check_sys(){
    int a = 1;
    // 返回1，小端
    // 返回0，大端
    return *(char*)&a;
}
```

案例：

```c
// 已知大端存储
int main(){
    unsigned int a = 0x1234; // 由于是大端存储，因此a在内存中这么存：（假设从左到右是低地址到高地址）00 00 12 34 
    unsigned char b = *(unsigned char*)&a; // 取a的地址，强转成unsigned char*，意味着它的步长变1，再解引用拿到的应该是00这个字节，所以输出为0
    printf("%d\n", b); // 0
    return 0;
}
```



# 有符号数和无符号数所能表示的范围

拿char来举例

![image-20210307155551394](c语言进阶.assets/image-20210307155551394.png)

- 有符号数

  | 二进制补码 |                          二进制原码                          |                十进制                |
  | :--------: | :----------------------------------------------------------: | :----------------------------------: |
  |  00000000  |                           00000000                           |                  0                   |
  |  00000001  |                           00000001                           |                  1                   |
  |  00000010  |                           00000010                           |                  2                   |
  |    ...     |                             ...                              |                 ...                  |
  |  01111110  |                           01111110                           |                 126                  |
  |  01111111  |                           01111111                           |                 127                  |
  |  10000000  | 这个数比较特殊，我们在求原码的时候发现减一没地方去减了。那么在这里，我们直接将这个数定义为-128。其实定义成-128也是有一定道理的，假设使用9位二进制数来表示-128，那一定是110000000，那么它的反码是101111111，那么它的补码是110000000，我们发现补码的低8个比特位就是10000000 | <span style="color:red;">-128</span> |
  |  10000001  |                           11111111                           |                 -127                 |
  |  10000010  |                           11111110                           |                 -126                 |
  |    ...     |                             ...                              |                 ...                  |
  |  11111101  |                           10000011                           |                  -3                  |
  |  11111110  |                           10000010                           |                  -2                  |
  |  11111111  |                           10000001                           |                  -1                  |

  因此有符号的char的范围是：-128 -> 127

- 无符号数

  无符号的char那很简单，没有符号位了，因此能表示的范围是：00000000 -> 11111111，也就是0 -> 255

同理int、short、long等其他整型

案例1：

```c
int main(){
    char a = -128;
    char b = 128;
    printf("%u\n", a); // 4294967168
    printf("%u\n", b); // 4294967168
    return 0;
}
```

案例2：

```c
unsigned int a = 0; // 00000000000000000000000000000000
a -= 1; // 11111111111111111111111111111111
printf("%u\n", a); // 由于是无符号数，因此输出为4294967295（11111111111111111111111111111111）
```

案例2变种：

```c
unsigned int i;
for(i = 9; i >= 0; i--){
    printf("%u\n", i);
}
// 结果就是死循环
```

案例3：

```c
int main(){
    char a[1000];
    int i;
    for(i = 0; i < 1000; i++){
        a[i] = -1 - i; 
    }
    printf("%d\n", strlen(a)); // 128 + 127 = 255
    return 0;
}
// 解释：11111111, 11111110, 11111101, ..., 10000000, 01111111, 01111110, ..., 00000000, 11111111, 11111110, ...
// 记住一点，char型永远都是截取比特位最后8位，如果进位那也是截取比特位最后8位，如果00000000减一不够减的时候向第9位借位之后还是会截取比特位最后8位，因此00000000减一就变成11111111
```

# limits.h和float.h

limits.h用于找到整型数的取值范围

float.h用于找到浮点型数的取值范围

# 浮点数在内存中的存储

## 存进去的时候

根据国际标准IEEE（电气和电子工程协会）754，任意一个二进制浮点数V可以表示成下面的形式：

> - (-1)^S * M * 2^E
> - (-1)^S表示符号位，S=0时V为正数；S=1时V为负数
> - M表示有效数字，大于等于1，小于2.（为什么是大于等于1，小于2呢？首先科学计数法有效数字肯定是大于等于1的，然后这里是二进制所以只有1或0，因此有效数字小于2）
> - 2^E表示指数位

举例来说：

十进制的5.0，写成二进制是101.0，相当于(-1)^0 * 1.01 * 2^2。那么，按照上面V的格式，可以得出S=0，M=1.01，E=2。

十进制的-5.0，写成二进制是-101.0，相当于(-1)^1 * 1.01 * 2^2。那么，按照上面V的格式，可以得出S=1，M=1.01，E=2。



那么浮点数在内存中到底是怎么存储的呢？

![image-20210307202642547](c语言进阶.assets/image-20210307202642547.png)

![image-20210307202935460](c语言进阶.assets/image-20210307202935460.png)

<span style="font-weight:bold;">IEEE 754对有效数字M和制数E，还有一些特别规定。</span>前面说过，1≤M＜2，也就是说，M可以写成1.xxxxxxx的形式，其中xxxxxx表示小数部分。

IEEE 754规定，在计算机内部保存M时，默认这个数的第一位总是1，因此可以被舍去，只保存后面的xxxxxx部分。比如保存1.01的时候，只保存01，等到读取的时候，再把第一位的1加上去。这样做的目的，是节省1位有效数字。以32位浮点数为例，留给M只有23位，将第一位的1舍去以后，等于可以保存24位有效数字。

<span style="color:red;">注意！对于M来讲，如果M没有满23位数字，则需要向后补0直到补满23位数字为止。</span>

<span style="font-weight:bold;">至于指数E，情况就比较复杂。</span>

<span style="font-weight:bold;">首先，E为一个无符号整数（unsigned int）。</span>这意味着，如果E为8位，它的取值范围为0~255；如果E为11位，它的取值范围为0~2047.但是，我们知道，科学计数法中的E是可以出现负数的，所以IEEE 754规定，存入内存时E的真实值必须再加上一个中间数，对于8位的E，这个中间数是127；对于11位的E，这个中间数是1023.比如，2^10的E是10，所以保存成32位浮点数时，必须保存成10+127=137，即10001001。

比方说：

十进制的0.5，写成二进制是0.1（二进制中小数点后面的那个1表示2^(-1)），相当于(-1)^0 * 1.0 * 2^(-1)，我们发现，E此时是-1，是个负数，但是我们知道内存中存E的地方是需要存无符号数的，那么这里怎么办呢？这里采取的方法是加一个中间数（对于8位的E，这个中间数是127；对于11位的E，中间数是1023），加上之后，E就算原先是负数，现在也变正数了，就可以存到内存中存E的地方了

<span style="color:red;">注意！对于E来讲，不管它是正数还是负数，都要加中间数。</span>

举例：

```c
float f = 5.5;
// 5.5
// 101.1
// (-1)^0 * 1.011 * 2^2
// S = 0
// M = 1.011
// E = 2 （E此时还需要加上127，也就是说E最后存进去的是2 + 127 = 129 -> 10000001）
// 0 10000001 011 00000000000000000000 -> 0100 0000 1011 0000 0000 0000 0000 0000
// 0x40b00000
```

## 取出来的时候

还是上面的E

指数E从内存中取出还可以再分成三种情况：

<span style="font-weight:bold;">E不全为0或不全为1（常规情况）</span>

> 此时，浮点数就采用下面的规则表示，即指数E的计算值减去127（或1023）得到真实值，再在有效数字M前加上第一位的1，根据公式即可还原浮点数。如下：
>
> 0100 0000 1011 0000 0000 0000 0000 0000 -> 0 10000001 011 00000000000000000000
>
> 10000001 -> 128 -> 129 - 127 = 2 
>
> 011 -> 1.011
>
> 得：(-1)^0 * 1.011 * 2^2 = 101.1 -> 5.5

<span style="font-weight:bold;">E全为0</span>

> 此时，浮点数指数E真实值为-127（或-1023）！那么我们可以想象最终还原回来的数将是这样的：1.xxx * 2^(-127) 或 -1.xxx * 2^(-127) 或 1.xxx * 2^(-1023) 或 -1.xxx * 2^(-1023) ，已经无限接近于0了
>
> <span style="color:red;">所以假设E全为0，这里会有一种特殊的处理方式，那就是浮点数的指数E等于1-127（或者1-1023）即为真实值，有效数字M不再加上第一位的1，而是还原为0.xxxxxxx的小数。这样做是为了表示±0，以及接近于0的很小的数字。</span>如下：
>
> 0100 0000 1011 0000 0000 0000 0000 0000 -> 0 10000001 011 00000000000000000000
>
> 得：＋/－ (-1)^0 * 0.011 * 2^(-126)，这是一个无限接近于0的数字

<span style="font-weight:bold;">E全为1</span>

> 此时，浮点数指数E真实值为128（或1024）！那么我们可以想象最终还原回来的数将是这样的：1.xxx * 2^128 或 -1.xxx * 2^128 或 1.xxx * 2^1024 或 -1.xxx * 2^1024，已经是一个无穷大的数字了。
>
> 此时如果有效数字M全为0，表示±无穷大（正负取决于符号位S）

案例：

```c
int n = 9;
float* pFloat = (float*)&n;
printf("%d\n", n); // 9
printf("%f\n", *pFloat); // 0.000000

*pFloat = 9.0;
printf("%d\n", n); // 1091567616
printf("%f\n", *pFloat); // 9.0
```

# 指针的进阶

## 将数组名赋给指针

```c
int arr[10] = {1,2,3,4,5};
int* p = arr; // 数组名表示首元素地址
// *(p + 2) == p[2] == *(arr + 2) == arr[2]
// 从这里可以看出p和arr其实是等价的
```



## 字符指针

```c
// ”abcde"直接赋给指针p那是不现实的，因为指针p大小只有4个字节，而"abcde"是5个字节，根本放不下
// 实际上，字符串要赋值给指针，不是把整个字符串赋值给指针，而是把字符串首字符的地址赋值给指针
char* p = "abcde"; // "abcde"是一个常量字符串
printf("%c\n", *p); // a
printf("%s\n", p); // abcde
// 相当于发生了这些事：首先在内存中开辟空间存放常量字符串”abcde\0“，并且首字符a的地址是0x0012ff44，然后还会为指针p开辟空间并且指针p里面存放首字符a的地址，也就是0x0012ff44
```

![image-20210308100339878](c语言进阶.assets/image-20210308100339878.png)

我们刚刚说”abcde“是常量字符串，那么我们能否通过指针p来改变它呢？比方说\*p = 'W';答案是不行！如果强行执行将会报Segmentation fault - 段错误（访问非法内存时会报的错）

那么为啥会报错呢？那是因为”abcde“是常量字符串，无法被修改

那么最正确的写法是：

```c
const char* p = "abcde"; // 使用const修饰*p，那么就不怕它被非法修改了
```

案例：

```c
char arr1[] = "abc";
char arr2[] = "abc";
char* p1 = "abc"; // 最好写成 const char* p1 = "abc"
char* p2 = "abc"; // 最好写成 const char* p2 = "abc"
// 那么此时arr1 != arr2；p1 == p2
// 解释：如果”abc“是常量字符串，为了节省空间，”abc“就只需要创建并且存储一份在内存中就可以了，因此p1和p2指向的是同一块内存区域；而数组不一样，数组不管内容一不一样它都是需要重新创建并存储的
```



这里介绍一个网站：https://segmentfault.com/

## 数组指针

```c
// int *p = NULL; // p是整型指针 - 指向整型的指针 - 可以存放整型的地址
// char* pc = NULL; // pc是字符指针 - 指向字符的指针 - 可以存放字符的地址
					// 数组指针 - 指向数组的指针 - 存放数组的地址
// int arr[10] = {0};
// arr - 首元素的地址
// &arr[0] - 首元素的地址
// &arr - 数组的地址
int arr[10] = {1, 2, 3, 4};
int* p1[10] = &arr; // ❌ 由于[]的优先级高于*，因此p1先和[结合了，那么它就是一个数组而不是指针了。因此这种写法错误！
int (*p)[10] = &arr; // ✔ 相当于*先和p结合了，那么它就是一个指针，后面的[10]说明它指向的是一个存放10个元素的数组，那么这个数组存放的元素是什么类型的呢？前面的int说明存放的元素是int型的
// 那么上面的p就是数组指针
```

那么我们来辨别一下下面四行代码分别表示什么：

```c
int arr[5]; // arr是一个5个元素的整型数组
int* parr1[10]; // parr1 是一个数组，数组有10个元素，每个元素的类型是int*，parr1是指针数组
int (*parr2)[10]; // parr2 是一个指针，该指针指向了一个数组，数组有10个元素，每个元素的类型是int，parr2是数组指针
int (* parr3[10])[5]; // 首先由于[]优先级比*高，因此parr3先跟[]结合，因此parr3首先应该被定义为是一个数组。parr3是一个数组，该数组有10个元素，每个元素是一个类型为int(*)[5]的数组指针，该数组指针指向的数组有5个元素，每个元素的类型是int
// 解释一下如何得到int (* parr3[10])[5]中每一个元素的类型：首先int (* parr3[10])[5]中每一个元素应当为int (* parr3)[5]，去掉名称parr3，剩下的就是类型，因此类型是int(*)[5]；也可以简单直接一点，直接把数组名和方括号一起去了，也就是说把parr3[10]去了，那么剩下的int(*)[5]也就是元素类型
```

![image-20210308135715481](c语言进阶.assets/image-20210308135715481.png)

案例1：

```c
// 写出一个指向char* arr[5];的数组指针
char* (*parr)[5] = &arr;
```

案例2：

```c
// 使用数组指针打印下面的数组
int arr[10] = {1,2,3,4,5,6,7,8,9,10};
int (*parr)[10] = &arr;
int i = 0;
for(i = 0; i < 10 ;i++){
    // printf("%d ", (*parr)[i]);
    // 或者：
    printf("%d ", *(*parr + i));
}
```

一般情况下数组指针都是在多维数组的情况下去使用

案例3：

```c
// 有二维数组：
int arr[3][5] = {{1,2,3,4,5}, {2,3,4,5,6}, {3,4,5,6,7}};
// 有函数用于打印该二维数组：
print1(arr, 3, 5); // arr - 数组名 - 数组名就是数组首元素地址
// 那么问题来了，二维数组首元素是谁？答：不是1，而是{1,2,3,4,5}这个数组（该数组类型是int [5]）
// 注意，在说二维数组首元素的时候我们需要先将二维数组想象成一维数组，再去讨论它的首元素
// 现在我们搞清楚传入print1函数的arr是个什么类型了（其实就是一个一维数组的地址），既然是数组的地址，那么我们就应该用数组指针去接它
```

这个时候我们就可以去写这个print1函数了：

```c
void print1(int (*p)[5], int x, int y){
    int i = 0;
    int j = 0;
    for(i = 0; i < x; i++){
        for(j = 0; j < y; j++){
            // printf("%d ", *(*(p + i) + j)); 
            // 或 printf("%d ", (*(p + i))[j])
            // 或者直接：
            printf("%d", p[i][j]);
        }
        printf("\n");
    }
}
// 为了理解(*(p + i))[j]，这里做一下说明
// 假设有数组：
int arr[2] = {1,2};
int* parr = arr;
// 则可以这样打印数组：
for(int i = 0; i < 2; i++){
    printf("%d ", parr[i]);
}
```



## 指针数组

```c
int arr1[] = {1, 2, 3, 4, 5};
int arr2[] = {2, 3, 4, 5, 6};
int arr3[] = {3, 4, 5, 6, 7};
int* parr[] = {arr1, arr2, arr3};
```

![image-20210308103503153](c语言进阶.assets/image-20210308103503153.png)



## 数组传参和指针传参

一维数组传参：

```c
void test(int arr[]){} // ok
void test(int arr[10]){} // ok 这个10写跟不写没区别，就算写了写错了也没事，反正它是没用的
void test(int *arr){} // ok
void test2(int *arr[20]){} // ok 这个20写跟不写没区别，就算写了写错了也没事，反正它是没用的
void test2(int **arr){} // ok

int main(){
    int arr[10] = {0};
    int *arr2[20] = {0};
    test(arr);
    test2(arr2);
    return 0;
}
```

二维数组传参：

```c
void test(int arr[3][5]){} // ok
void test(int arr[][5]){} // ok
void test2(int *arr); // err，二维数组首元素应该是一个一维数组，而整型指针应该是用来存放整型的地址的
void test2(int **arr); // err，二维数组首元素应该是一个一维数组，而二级整型指针应该是用来存放一级整型指针的地址的
void test2(int *arr[5]); // err
void test2(int (*arr)[5]); // ok
int main(){
    int arr[3][5] = {0};
    test(arr);
    test2(arr);
    return 0;
}
```



## 函数指针

数组指针是指向数组的指针

那么函数指针其实也就是指向函数的指针 - 存放函数地址的一个指针

```c
int Add(int x, int y){}
int main(){
    // &函数名 和 函数名 都是函数的地址，它俩没有任何区别，一模一样
    printf("%p\n", &Add); // 005310E1
    printf("%p\n", Add); // 005310E1
    // 那么函数指针该怎么写呢？？、
    int (*fp)(int, int) = Add; // 解释：首先它是个指针，因此是*fp，其次它需要两个参数，分别是int和int，因此后面跟(int, int)，然后函数的返回值是int，因此前面写int
    (*fp)(3,4); // 通过函数指针调用函数
    return 0;
}
```

判断下面pfun1和pfun2哪个有能力存放函数的地址？

```c
void (*pfun1)();
void *pfun2();
// 首先，能存放存储地址，就要求pfun1或者pfun2是指针，那哪个是指针？答案是：
// pfun1可以存放。pfun1先和*结合，说明pfun1是指针，指针指向的是一个函数，指向的函数无参数，返回值类型为void
// 而第二行代码中pfun2首先跟()结合，因此这行代码仅仅只是一个函数的声明，参数无，返回类型void*，pfun2只是这个函数的名称而已
```

那么函数指针的类型是什么呢？

```c
// 还是一样，去掉函数名剩下的就是类型
// 比方说：
void test(double x, int y){}
int main(){
    void (*fp)(double, int) = test; // 那么函数指针fp的类型就是void (*)(double, int)
    return 0;
}
```

通过函数指针调用函数

```c
void test(int x){
    return x + 1;
}
int main(){
    int a = 10;
    void (*pa)(int) = test; 
    // 这里pa、*pa、**pa和***pa完全一样，没有区别，*其实是摆设，加与不加都一样。
    // 那么怎么理解呢？思考一下，直接调用test函数是怎么调用的呢：test(10)，我们把这里的test想象成地址，那么pa存的就是test的地址，那首先pa(a)就好理解了，因为pa里面存的就是test的地址，那直接调用就可以了；(*pa)(a)其实也好理解，因为pa存的是test的地址，那么我们解引用拿到test函数再去调用，也就变成了(*pa)(a)
    printf("%d\n", pa(a)); // 11
    printf("%d\n", (*pa)(a)); // 11
    printf("%d\n", (**pa)(a)); // 11 一般不这么写
    printf("%d\n", (***pa)(a)); // 11 一般不这么写
    return 0;
}
```



案例1：

```c
(*(void (*)())0)(); // 首先中间的一段”void (*)()“它表示函数指针类型，而类型包了个括号放在数字0前面，显然是在强制类型转换，那这里的意思就是把0强制类型转换成：void (*)() 函数指针类型，而0就是一个函数的地址，然后第一个*就要发挥作用了，它的意思是解引用拿到函数指针0所指向的函数，最后我们可以看到代码末尾有一对小括号，那就是在调用这个函数
```

案例2：

```c
void (*signal(int, void(*)(int)))(int); // 首先我们先思考signal(int, void(*)(int))，它的意思显然是函数声明，首先函数名是signal，然后第一个形参是int型的数，第二个形参是void(*)(int)类型的函数指针
// 然后我们来分析这个函数的返回类型是什么，思考一下，如果是int Add(int, int);这样的函数声明，显然它的返回类型是int，那我们是怎么得到它的返回类型就是int的呢？其实是去掉了Add(int, int)，剩下了int，那么这个剩下的int就说明了Add函数的返回类型是int。
// 那么这里的函数也一样，我们首先去掉signal(int, void(*)(int))，剩下了void (*)(int)，那就能说明该函数的返回类型了，就是void (*)(int)，那为什么不写成一般情况下函数声明的形式，比方说void (*)(int) signal(int, void(*)(int))呢？那是应为这样声明是非法的，如果返回类型是函数指针，那么函数名必须写到void (*)(int)中的*的右边
// 所以总结一下这行代码意思是一个名为signal的函数的声明，该函数需要一个int型的数和一个void(*)(int)型的函数指针作为参数，它的返回类型是void(*)(int)型的函数指针
```

我们发现上面这种写法及其麻烦而且可读性几乎为0，那么可不可以改进呢？

我们可以使用typedef

```c
// typedef void(*)(int) pfun_t; // ❌
typedef void(*pfun_t)(int); // ✔

// 这个时候我们就可以简化上面的代码语句了
pfun_t signal(int, pfun_t);
```

### 回调函数

> 回调函数就是一个通过函数指针调用的函数。如果你把函数的指针（地址）作为参数传递给另一个函数，当这个指针被用来调用其所指向的函数时，我们就说这是回调函数。回调函数不是由该函数的实现方直接调用，而是在特定的事件或条件发生时由另外的一方调用的，用于对该事件或条件进行响应。

那么如何将函数指针作为参数传入到函数中使用呢？

```c
int Add(int, int){}
int Sub(int, int){}
// 将函数指针作为参数传入函数
void Calc(int (*pf)(int, int)){
    printf("%d\n", pf(2, 1));
}
int main(){
    Calc(Add);
    Calc(Sub);
    return 0;
}
```

案例：

```c
// 编写通用的冒泡排序函数
void swap(char* buf1, char* buf2, int width){
    int i = 0;
    for(i = 0; i < width; i++){
        char tmp = *buf1;
        *buf1 = *buf2;
        *buf2 = temp;
        buf1++;
        buf2++;
    }
}
void bubble_sort(void* base, int sz, int width, int(*cmp)(const void* e1, const void* e2)){
    int i = 0;
    // 趟数
    for(i = 0; i < sz - 1; i++){
        // 每一趟比较的对数
        int j = 0;
        for(j = 0; j < sz - 1 - i; j++){
            if(cmp((char*)base + j * width, (char*)base + (j + 1) * width) > 0){
                // 交换
                swap((char*)base + j * width, (char*)base + (j + 1) * width, width);
            }
        }
    }
}
```



### void*

无类型的指针

可以接收任意类型变量的地址

```c
int a = 0;
char c = 'a';
void* p = &a; // ✔
p = &c; // ✔
```

案例：

```c
// qsort函数的声明
void qsort(void* base, size_t num, size_t wid, int(*cmp)(const void* e1, const void* e2));
```

我们可以看到qsort函数第4个参数是一个函数指针，那么怎么来实现这个函数呢？

首先我们要了解下面两点：

<span style="color:red;font-weight:bold;">void*类型的指针不能进行解引用操作</span>

```c
void* p = &a;
*p; // ❌
```

<span style="color:red;font-weight:bold;">void*类型的指针不能进行加减整数的操作</span>

```c
void* p = &a;
p++; // ❌
```

现在我们回到qsort函数的第4个参数，也就是那个比较函数的实现：

```c
// 如果要排序的数组里面是整型数
int cmp(const void* e1, const void* e2){
    // return *e1 - *e2; // ❌
    return *(int*)e1 - *(int*)e2; // ✔ 这里把e1和e2强制类型转换成int*型的指针
}
// 如果要排序的数组里面是浮点型数
int cmp(const void* e1, const void* e2){
    // return *(float*)e1 - *(float*)e2; // ✔ 这里把e1和e2强制类型转换成float*型的指针
    // 但是上面这种写法可能会有问题，因为函数的返回类型是int，但是两个浮点型的数做加减法得到的也是一个浮点型的数
    // 所以最好还是这么写：
    // if (*(float*)e1 == *(float*)e2) return 0;
    // else if (*(float*)e1 > *(float*)e2) return 1;
    // else return -1;
    
    // 当然也可以这么写：
    return ((int)(*(float*)e1 - *(float*)e2)); // 意思就是将两个浮点型的数做加减运算之后再将结果强制类型转换成int型
}
```

如果要排序的数组里面是结构体

```c
struct Stu{
    char name[20];
    int age;
};

// 现在有结构体数组：struct Stu s[3] = {{"zhangsan", 20}, {"lisi", 30}, {"wangwu", 10}};
// 那么怎么写比较函数来对数组s按照年龄进行排序呢？如下：
int cmp_stu_by_age(const void* e1, const void* e2){
    return ((struct Stu*)e1)->age - ((struct Stu*)e2)->age;
}
// 那么怎么写比较函数来对数组s按照名字进行排序呢？如下：
int cmp_stu_by_name(const void* e1, const void* e2){
    // 比较名字就是比较字符串
    // 字符串比较不能直接用><=来比较，应该用strcmp函数
    return strcmp(((struct Stu*)e1)->name, ((struct Stu*)e2)->name);
}
```



## 函数指针数组

```c
int Add(int x, int y){}
int Sub(int x, int y){}
int Mul(int x, int y){}
int Div(int x, int y){}
int main(){
    int (*pa)(int, int) = Add;
    int (*pa[4])(int, int) = {Add, Sub, Mul, Div}; // 函数指针数组。它所存放的元素的类型是int (*)(int, int)
    int i = 0;
    for(i = 0; i < 4; i++){
        printf("%d\n", pa[i](2,3));
    }
}
```

如何定义函数指针数组：

```c
int (*par[10])(); // ✔
int (*par[])(); // ✔
int *par[10](); // ❌
int (*)() par[10]; // ❌
```

案例1：

```c
int Add(int x, int y){}
int Sub(int x, int y){}
int Mul(int x, int y){}
int Div(int x, int y){}
int main(){
    int input = 0;
    // pf 是一个函数指针数组 - 转移表
    int (*pf[])(int, int) = {0, Add, Sub, Mul, Div};
    do{
        printf("请输入0-5之间的整数:>");
        scanf("%d", &input);
        if(input >= 1 && input <= 4){
            int res = pf[input](2, 3);
            printf("%d\n", res);
        }else if(input == 0){
            printf("退出!\n");
        }else{
            printf("错误!\n");
        }
    }while(input);
    return 0;
}
```



## 指向函数指针数组的指针

```c
int main(){
    int arr[10] = {0};
    int (*p)[10] = &arr; // 取出数组的地址
    int(*pf)(int, int); // 函数指针
    int (*pfArr[4])(int, int); // pfArr是一个数组 - 函数指针的数组
    int(*(*ppfArr)[4])(int, int); // ppfArr是一个指向[函数指针数组]的指针，指针指向的数组有4个元素，每一个元素的类型是一个函数指针，每一个函数指针的类型是int(*)(int, int)
}
```

当然我们还可以一层一层的套下去，比方说还有指向函数指针数组的指针的数组等等

## 指针和数组面试题的解析

> 1、sizeof(数组名)，这里的数组名表示整个数组，计算的是整个数组的大小；
>
> 2、&数组名，这里的数组名表示整个数组，取出的是整个数组的地址；
>
> 3、除此之外所有的数组名都表示首元素的地址

### 一维数组

```c
int main(){
    int a[] = {1, 2, 3, 4}; // 4 * 4 = 16
    printf("%d\n", sizeof(a)); // 16 sizeof(数组名) - 计算的是数组总大小 - 单位是字节
    printf("%d\n", sizeof(a + 0)); // 4/8 数组名在这里表示首元素的地址，a+0还是首元素地址，地址的大小就是4/8个字节
    printf("%d\n", sizeof(*a)); // 4 数组名表示首元素的地址，*a就是首元素，sizeof(*a)就是4
    printf("%d\n", sizeof(a + 1)); // 4/8 数组名在这里表示首元素的地址，a+1是第2个元素的地址，地址的大小就是4/8个字节
    printf("%d\n", sizeof(a[1])); // 4 第二个元素的大小
    printf("%d\n", sizeof(&a)); // 4/8 &a取出的是数组的地址，但是数组的地址那也是地址，地址的大小就是4/8个字节
    printf("%d\n", sizeof(*&a)); // 16 &a是数组的地址，数组地址解引用访问数组（相当于*和&抵消了），因此sizeof(*&a)计算的就是sizeof(a)的值
    printf("%d\n", sizeof(&a + 1)); // 4/8 &a是数组的地址，&a+1虽然地址跳过整个数组，但还是地址
    printf("%d\n", sizeof(&a[0])); // 4/8 &a[0]是第一个元素的地址
    printf("%d\n", sizeof(&a[0] + 1)); // 4/8 &a[0]+1 是第二个元素的地址
    return 0;
}
```

```c
int main(){
    int a[5] = {1,2,3,4,5};
    int* ptr = (int *)(&a + 1);
    printf("%d, %d\n", *(a + 1), *(ptr - 1)); // 2, 5
    return 0;
}
```



### 字符数组

```c
int main(){
    char arr[] = {'a', 'b', 'c', 'd', 'e', 'f'};
    printf("%d\n", sizeof(arr)); // 6 sizeof计算的是数组大小
    printf("%d\n", sizeof(arr + 0)); // 4/8 arr是首元素的地址，arr+0还是首元素的地址
    printf("%d\n", sizeof(*arr)); // 1 arr是首元素的地址，*arr就是首元素，首元素是一个字符，而字符大小是一个字节
    printf("%d\n", sizeof(arr[1])); // 1 
    printf("%d\n", sizeof(&arr)); // 4/8 &arr虽然是数组的地址，但还是地址
    printf("%d\n", sizeof(&arr + 1)); // 4/8 &arr+1是跳过整个数组后的地址
    printf("%d\n", sizeof(&arr[0] + 1)); // 4/8 是第二个元素的地址
    return 0;
}
```

```c
int main(){
    char arr[] = {'a', 'b', 'c', 'd', 'e', 'f'};
    printf("%d\n", strlen(arr)); // arr首元素地址往后找到'\0'才结束，因此是随机值
    printf("%d\n", strlen(arr + 0)); // arr+0 还是arr首元素地址，还是从arr首元素地址往后找到'\0'才结束，因此是随机值
    printf("%d\n", strlen(*arr)); // *arr是解引用arr首元素地址，这里是'a'，而strlen接收的参数是地址，因此这里传'a'相当于传了97，而访问地址97的内存是非法的，因此会报错
    printf("%d\n", strlen(arr[1])); // arr[1]是arr第二个元素地址，这里是'b'，而strlen接收的参数是地址，因此这里传'b'相当于传了98，而访问地址98的内存是非法的，因此会报错
    printf("%d\n", strlen(&arr)); // &arr是整个arr数组的地址，因此还是从arr首元素地址往后找到'\0'才结束，因此是随机值
    printf("%d\n", strlen(&arr + 1)); // &arr+1是跳过整个arr数组的地址，因此是从跳过整个arr数组的地址的那个地址往后找到'\0'才结束，因此是随机值
    printf("%d\n", strlen(&arr[0] + 1)); // &arr[0]+1是第二个元素的地址，因此是从第二个元素的地址往后找到'\0'才结束，因此是随机值
	return 0;
}
```

```c
int main(){
    char arr[] = "abcdef";
    printf("%d\n", sizeof(arr)); // 7 sizeof(arr)计算的是数组的大小
    printf("%d\n", sizeof(arr + 0)); // 4/8 计算的是地址的大小 - arr+0 是首元素的地址
    printf("%d\n", sizeof(*arr)); // 1 *arr是首元素'a'，sizeof(*arr)计算首元素'a'的大小
    printf("%d\n", sizeof(arr[1])); // 1 arr[1]是第二个元素，sizeof(arr[1])计算的是第二个元素的大小
    printf("%d\n", sizeof(&arr)); // 4/8 &arr虽然是数组的地址，但也是地址
    printf("%d\n", sizeof(&arr + 1)); // 4/8 &arr+1是跳过整个数组后的地址，但也是地址
    printf("%d\n", sizeof(&arr[0] + 1)); // 4/8 &arr[0]+1是第二个元素的地址
    return 0;
}
```

```c
int main(){
    char arr[] = "abcdef";
    printf("%d\n", strlen(arr)); // 6
    printf("%d\n", strlen(arr + 0)); // 6
    printf("%d\n", strlen(*arr)); // err
    printf("%d\n", strlen(arr[1])); // err
    printf("%d\n", strlen(&arr)); // 6 &arr是数组的地址，需要用数组指针去接：char(*p)[7] = &arr;但是strlen接收的参数实际上是一个字符指针，因此这里会报警告，但是并不影响使用，因为它会把传进去的数组指针当成字符指针去使用
    printf("%d\n", strlen(&arr + 1)); // 随机值 &arr是整个数组的地址，&arr+1还是整个数组的地址，需要用数组指针去接：char(*p)[] = &arr+1;但是strlen接收的参数实际上是一个字符指针，因此这里会报警告，但是并不影响使用，因为它会把传进去的数组指针当成字符指针去使用
    printf("%d\n", strlen(&arr[0] + 1)); // 5
    return 0;
}
```

### 字符指针

```c
int main(){
    char* p = "abcdef";
    printf("%d\n", sizeof(p)); // 4/8 计算指针变量p的大小
    printf("%d\n", sizeof(p + 1)); // 4/8 p+1得到的是字符'b'的地址
    printf("%d\n", sizeof(*p)); // 1 *p就是字符串的第一个字符'a'的大小
    printf("%d\n", sizeof(p[0])); // 1 int arr[10]; arr[0] == *(arr+0)  因此p[0] == *(p+0) == 'a'
    printf("%d\n", sizeof(&p)); // 4/8 &p取的是指针p的地址，所以计算的是地址的大小
    printf("%d\n", sizeof(&p + 1)); // 4/8 &p+1意思是先取出指针p的地址，再让它往后走一步，所以计算的仍旧是地址的大小
    printf("%d\n", sizeof(&p[0] + 1)); // 4/8 计算'b'的地址的大小
    return 0;
}
```

```c
int main(){
    char* p = "abcdef";
    printf("%d\n", strlen(p)); // 6
    printf("%d\n", strlen(p + 1)); // 5
    printf("%d\n", strlen(*p)); // err
    printf("%d\n", strlen(p[0])); // err
    printf("%d\n", strlen(&p)); // 随机值 解释：&p指取出p的地址，那么p的地址可能是0x00131214，这个时候如果是小端存储那应该是这么存：14 12 13 00，如果使用strlen显然这里会得到结果为3，因为最后一个字符是00；而如果p的地址是0x12131415，按照小端存储则是：15 14 13 12，此时使用strlen就不知道会得到什么结果了，因为不知道什么时候遇到00
    printf("%d\n", strlen(&p + 1)); // 随机值 解释：先取出p的地址，再让它往后走一步，这个时候后面什么时候遇到00仍旧是不知道的
    printf("%d\n", strlen(&p[0] + 1)); // 5
    return 0;
}
```

### 二维数组

```c
int main(){
    int a[3][4] = {0};
    printf("%d\n", sizeof(a)); // 48
    printf("%d\n", sizeof(a[0][0])); // 4
    printf("%d\n", sizeof(a[0])); // 16 a[0]相当于第一行作为一维数组的数组名，sizeof(arr[0])把数组名单独放在sizeof()内，计算的是第一行的大小
    printf("%d\n", sizeof(a[0] + 1)); // 4/8 a[0]是第一行的数组名，数组名此时是首元素的地址，a[0]其实就是第一行第一个元素的地址，所以a[0]+1就是第一行第二个元素的地址  
    printf("%d\n", sizeof(*(a[0] + 1))); // 4 *(a[0] + 1)是第一行第二个元素
    printf("%d\n", sizeof(a + 1)); // 4 a是二维数组的数组名，没有sizeof(a)，也没有&(a)，所以a是首元素地址，而把二维数组看成一维数组时，二维数组的首元素是他的第一行，a就是第一行（首元素）的地址，那么a+1就是第二行的地址
    printf("%d\n", sizeof(*(a + 1))); // 16 sizeof(a[1]) == sizeof(*(a+1))计算的是第二行的大小
    printf("%d\n", sizeof(&a[0] + 1)); // 4/8 第二行的地址
    printf("%d\n", sizeof(*(&a[0] + 1))); // 16 计算第二行的大小
    printf("%d\n", sizeof(*a)); // 16 a是首元素地址（第一行地址），那么*a就是第一行，sizeof(*a)就是计算第一行的大小
    printf("%d\n", sizeof(a[3])); // 16 虽然a[3]越界了，但是放入sizeof()中的表达式是不会真实运算的，sizeof只是根据类型来判断传入的东西占多少字节大小的内存空间而已，a[3]是一个4个整型的一维数组，所以大小是16
    return 0;
}
```

### 指针加减

```c
struct Test{
    int Num;
    char* pcName;
    short sDate;
    char cha[2];
    short sBa[4];
}* p; // 这里的p其实就是一个struct Test*类型的全局的指针
// 假设p的值为0x100000，如下表达式的值分别是多少？
// 已知，结构体Test类型的变量大小是20个字节
int main(){
    p = (struct Test*)0x100000;
    printf("%p\n", p + 0x1); // 0x100014 结构体Test类型的变量大小是20个字节
    printf("%p\n", (unsigned long)p + 0x1); // 0x100001 转化成整型了，正常计算即可
    printf("%p\n", (unsigned int*)p + 0x1); // 0x100004 int型指针长度为4个字节
    return 0;
}
```

```c
int main(){
    int a[4] = {1,2,3,4};
    int *ptr1 = (int*)(&a + 1);
    int *ptr2 = (int*)((int)a + 1);
    printf("%x,%x\n", ptr1[-1], *ptr2); // 0x4,0x02 00 00 00
    // 分析：首先ptr1应该比较好理解，&a+1之后强转成int*，指针指向数组a末尾的后面那个位置，然后ptr1[-1] == *(ptr1 - 1) 因此ptr1[-1]为数组a的最后一个元素，也就是4；然后来看ptr2，首先解释(int*)((int)a + 1); 先将a转成int型，此时a+1就是正常的加1，之后再将a+1的值转成int*，其实这就相当于指针往后走了一个字节（指针的最小步长单位是1字节），而数组a又是小端存储的，也就是说在内存中它是这么存的：01 00 00 00 02 00 00 00 03 00 00 00 04 00 00 00，那么原先ptr2解引用之后相当于拿出01 00 00 00这一段，而现在ptr2向后走了一个字节，此时再解引用就相当于拿出 00 00 00 02 这一段，由于是小端存储，所以其实就是这么一个十六进制数：02 00 00 00 。所以最后输出：0x4,0x02000000
    return 0;
}
```

```c
int main(){
    int a[3][2] = {(0, 1), (2, 3), (4, 5)};
    int *p;
    p = a[0];
    printf("%d", p[0]); // 1 请看下图
    return 0;
}
```

![image-20210322110952916](c语言进阶.assets/image-20210322110952916.png)

```c
int main(){
    int a[5][5];
    int (*p)[4];
    p = a;
    printf("%p,%d\n", &p[4][2] - &a[4][2], &p[4][2] - &a[4][2]); // 0xFF FF FF FC,-4 请看下图
    // 解释：p = a的时候首先p的类型是int(*)[4]，a是数组首元素也就是第一行，因此它的类型是int(*)[5]，然后我们来看a[4][2]和p[4][2]，a[4][2]其实很好理解，就是找到第五行的第三个元素，而p[4][2] == *(*(p + 4) + 2)，也就是说以4个为一行，找到第5行的第3个元素，其实就是a数组第4行第4个元素。此时以%d打印&p[4][2] - &a[4][2]就不难了，答案是-4，以%p打印的时候首先%p打印的是地址，地址没有负数，因此被打印的数就要当成一个无符号数，-4的补码不难得到，由于是无符号数所以打印的时候补码直接被当成源码打印出来，也就得到了0xfffffffc的结果
    return 0;
}
```

![image-20210322111830900](c语言进阶.assets/image-20210322111830900.png)

```c
int main(){
    int aa[2][5] = {1,2,3,4,5,6,7,8,9,10};
    int* ptr1 = (int*)(&aa + 1); // 跳过整个数组，指向数组末尾元素的后面那个元素
    int* ptr2 = (int*)(*(aa + 1)); // *(aa + 1)马上想到aa[1]，就是说aa是首元素地址，也就是第一行地址，+1之后就是第二行地址，因此解引用之后其实就是第二行，那么ptr2其实指向的就是第二行的首元素
    printf("%d,%d\n", *(ptr1 - 1), *(ptr2 - 1)); // 由于ptr1、ptr2都被转成int*（其实ptr2都不用转，它自己就是int*类型，因为数组名表示首元素地址），所以输出为10，5
    return 0;
}
```

```c
int main(){
    char* a[] = {"work", "at", "alibaba"};
    char** pa = a;
    pa++;
    printf("%s\n", *pa); // at
    return 0;
}
```

```c
int main(){
    char* c[] = {"ENTER", "NEW", "POINT", "FIRST"};
    char** cp[] = {c+3, c+2, c+1, c};
    char*** cpp = cp;
    printf("%s\n", **++cpp); // POINT
    printf("%s\n", *--*++cpp + 3); // ER
    printf("%s\n", *cpp[-2] + 3); // ST
    printf("%s\n", cpp[-1][-1] + 1); // EW
    
    return 0;
}
// 内存分布图见下图
```

![image-20210324145911904](c语言进阶.assets/image-20210324145911904.png)

# c语言函数

## scanf和gets()

头文件string.h

scanf读到空格就停止了

gets(char* buffer)可以读取一行

## pow()

头文件math.h

计算次方

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

## strcat()与strncat()

char\* strcat(char\* strDest, const char\* strSource);

char\* strncat(char\* strDest, const char\* strSource, size_t count);

**strcat()**

- 源字符串必须以'\\0'结束
- 目标空间必须足够的大，能容纳下源字符串的内容
- 目标空间必须可修改
- 字符串自己追加自己是不行的

- 手写strcat()

  ```c
  char* my_strcat(char* dest, const char* src){
      char* ret = dest;
      assert(dest);
      assert(src);
      // 找到目标字符串的'\0'
      while(*dest != '\0'){
          dest++;
      }
      // 追加
      while(*dest++ = *src++){
          ;
      }
      return ret;
  }
  ```

  



字符串拼接

注意：自己给自己追加的时候不能用strcat()而要用strncat()

原因是strcat()的原理是找到第一个字符串的\\0，然后用第二个字符串一个字符一个字符地从第一个字符串的\\0开始往后追加（覆盖掉第一个字符串的\\0），直到到第二个字符的\\0为止

所以这个时候如果是自己追加自己的话就会导致自己的第一个字符覆盖掉自己的\\0，导致最后找不到\\0而没办法停止而报错

## strstr()

用于判定是否是子字符串，是的话就找到并返回子字符串在字符串中的起始地址，不是的话就返回空指针

示例：

```c
#include<stdio.h>
int main(){
	str1 = "abcde";
    str2 = "bcd";
    char* ret = strstr(str1, str2);
    if(ret == NULL){
        printf("%s\n", "没找到");
    }else{
        printf("子字符串在字符串中的起始地址是：%p\n", ret);
        printf("子字符串是：%s\n", ret);
    }
    return 0;
}
```

## strlen()

要注意的是strlen返回的类型是size_t而size_t等同于unsigned int，因此下面这种歧义需要注意：

```c
if(strlen("abc") - strlen("abcde") > 0) printf("%s\n", "yes");
else printf("%s\n", "no");
// 结果打印“yes”，因为无符号数做加减操作之后类型还是无符号数
```

## strcpy()

- 源字符串必须以'\\0'结束
- 会将源字符串中的'\\0'拷贝到目标空间
- 目标空间必须足够大，以确保存放源字符串
- 目标空间必须可变（所以目标空间是常量字符串那是不行的）

手写strcpy()：

```c
char* my_strcpy(char* dest, const char* src){
    assert(dest);
    assert(src);
    char* ret = dest;
    // 拷贝src指向的字符串的dest指向的空间，包含'\0'
    // while(*src != '\0'){
    //     *dest++ = *src++;
    // }
    // *dest = *src; // 赋值最后的'\0'
    // 上面的代码可以简写成：
    while(*dest++ = *src++){
        ;
    }
    return ret;
}
```

