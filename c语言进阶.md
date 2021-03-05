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