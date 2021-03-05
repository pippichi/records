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