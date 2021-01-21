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



