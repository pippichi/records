# 关键字

## const

`const`有很多用法

这里我们讨论一下`C++`在函数声明时，后面跟个`const`的作用：

其作用是限定函数类型为常成员函数, 常成员函数是指不能改变成员变量值的函数。

例如：`double d() const;`其中的`“const”`限定了`d()`函数中不能有任何改变其所属对象成员变量值的功能，如果有则会在编译阶段就报错。

它的主要作用就是能使成员函数的意义更加清楚，我们可在不改变对象的成员函数的函数原型中加上`const`说明。在需要增加可读性和减少逻辑出错的情况下，就可以用这种形式。

## constexpr

const并不能代表“常量”，它仅仅是对变量的一个修饰，告诉编译器这个变量只能被初始化，且不能被直接修改（实际上可以通过堆栈溢出等方式修改）。而这个变量的值，可以在运行时也可以在编译时指定。

constexpr可以用来修饰变量、函数、构造函数。一旦以上任何元素被constexpr修饰，那么等于说是告诉编译器 “请大胆地将我看成编译时就能得出常量值的表达式去优化我”。

如：

```c++
const int func() {
    return 10;
}
main(){
  int arr[func()];
}
// error : 函数调用在常量表达式中必须具有常量值
// 对于func() ，胆小的编译器并没有足够的胆量去做编译期优化，哪怕函数体就一句return 字面值;
```

```c++
constexpr func() {
    return 10;
}
main(){
  int arr[func()];
}
// 编译通过
// 编译器大胆地将func()做了优化，在编译期就确定了func计算出的值10而无需等到运行时再去计算。
```

constexpr还有另外一个特性，虽然它本身的作用之一就是希望程序员能给编译器做优化的信心，但它却猜到了自己可能会被程序员欺骗，而编译器并不会对此“恼羞成怒”中止编译，如：

```c++
constexpr int func(const int n){
  return 10+n;
}
main(){
 const  int i = cin.get();
 cout<<func(i);
}
//编译通过
```

程序员告诉编译器尽管信心十足地把func当做是编译期就能计算出值的程式，但却欺骗了它，程序员最终并没有传递一个常量字面值到该函数。没有被编译器中止编译并报错的原因在于编译器并没有100%相信程序员，当其检测到func的参数是一个常量字面值的时候，编译器才会去对其做优化，否则，依然会将计算任务留给运行时。

基于这个特性，constexpr还可以被用来实现编译期的type traits，比如STL中的is_const的实现。

## mutable

mutable是“可变的，异变的”的意思，跟const是反义词

在c++中，mutable是为了突破const的限制而设置的，被mutable修饰的变量，将永远处于可变的状态，即使在一个const函数中

比方说类中有一个函数getAge()是被const修饰的：

```c++
class Person{
public:
	int getAge() const;
    int getCallingTimes() const; /*获取上面的getAge()方法被调用了多少次*/
private:
    int age;
    int m_nums; // 用于统计次数
}
```

场景：我们想要测试一个方法的被调用次数，最普遍的做法就是在getAge()的方法体内对m_nums这个变量进行 +1，但是getAge()方法又是const方法，无法修改m_nums这个变量，我们又不想去掉const关键字让别人能够修改age等成员变量，这个时候mutable关键字就派上用场了：

```c++
class Person{
public:
	int getAge() const;
    int getCallingTimes() const; /*获取上面的getAge()方法被调用了多少次*/
private:
    int age;
    mutable int m_nums; // 用于统计次数
}

int Person::getAge() const
{
    std::cout << "Calling the method" << std::endl;
    m_nums++;
    // age = 4; 仍然无法修改该成员变量
    return age;
}
int Person::getCallingTimes()const
{
    return m_nums;
}
```

调用这个类：

```c++
int main()
{
    Person *person = new Person();
    for (int i = 0; i < 10; i++) {
        person->getAge();
    }
    std::cout << "getAge()方法被调用了" << person->getCallingTimes() << "次" << std::endl;
    delete person;

    getchar();
    return 0;
}
```

输出：

```c++
getAge()方法被调用了10次
```

**注意：**

mutable不能修饰const 和 static 类型的变量

## using

参考：https://blog.csdn.net/weixin_39640298/article/details/84641726

## default

可以用于默认构造函数：

```c++
class X{
public:
	X()= default; // Inline default 默认构造函数
	X(int i){
		a = i;
	}
    X(const X&);
    ~X() = default; // Inline default 析构函数
private:
	int a;
};
X::X(const X&) = default; //Out-of-line default 拷贝构造函数

// 上面的代码，编译器会自动生成默认构造函数 X::X(){}，该函数可以比用户自己定义的默认构造函数获得更高的代码效率。
// default 函数特性仅适用于类的特殊成员函数，且该特殊成员函数没有默认参数。
```

参考博客：https://blog.csdn.net/weixin_42414947/article/details/117212295

## override

参考：https://blog.csdn.net/qq_33485434/article/details/78418012

示例：

```c++
class Base ｛
	virtual void f();
};
class Derived : public Base {
	void f() override; // 表示派生类重写基类虚函数f
	void F() override; // 错误：函数F没有重写基类任何虚函数
};
```

## public、private、protected

参考：https://blog.csdn.net/wuguangbin1230/article/details/76796891

## typename

参考：https://blog.csdn.net/lyn631579741/article/details/110730145

示例1：

```c++
template<typename T>
const T& max(const T& x, const T& y) {
    if (y < x) {
        return x;
    }
    return y;
}
```

示例2：

```c++
template<typename T>
void fun(const T& proto) {
    typename T::const_iterator it(proto.begin()); // 使用typename修饰来告诉编译器 T::const_iterator 是类型而不是变量
}
```

## friend

场景：友元函数和友元类

参考：http://c.biancheng.net/view/169.html

# 左值、右值、左值引用、右值引用

参考：https://blog.csdn.net/u012198575/article/details/83142419

## std::move、std::forward

`std::move`和`std::forward`本质就是一个转换函数，`std::move`执行到右值的无条件转换，`std::forward`执行到右值的有条件转换，在参数都是右值时，二者就是等价的。

参考：https://www.jianshu.com/p/b90d1091a4ff

# C++ 值传递和地址传递和引用传递

参考：https://blog.csdn.net/qq_43587345/article/details/104395788

# 推荐使用`pass-by-reference-to-const`替代`pass-by-value`的原因以及多态与如何避免对象切割

参考：https://blog.csdn.net/cqk0100/article/details/72897504

# c++虚函数与纯虚函数

**首先：强调一个概念**

```
定义一个函数为虚函数，不代表函数为不被实现的函数。
定义他为虚函数是为了允许用基类的指针来调用子类的这个函数。
定义一个函数为纯虚函数，才代表函数没有被实现。
定义纯虚函数是为了实现一个接口，起到一个规范的作用，规范继承这个类的程序员必须实现这个函数。
```

具体介绍请参考：https://blog.csdn.net/hackbuteer1/article/details/7558868、https://blog.csdn.net/qq_36221862/article/details/61413619

C++可以用纯虚函数实现类似于JAVA中接口的功能

# c++虚基类与虚继承解决命名冲突问题以及不可避免的二义性问题

参考：http://c.biancheng.net/view/2280.html

# stl

## map

与`unordered_map`比可排序。我们来看他的构造方法：

```c++
template < class Key, // map::key_type
           class T, // map::mapped_type
           class Compare = less<Key>, // map::key_compare，自定义排序
           class Alloc = allocator<pair<const Key,T>> // map::allocator_type
           > class map;
```

使用auto来遍历map：

```c++
for (const auto &[k, v]: map) {
    ...
}
```



# 库函数

## ctype

### isalpha、isalnum、islower、isupper

参考笔记中的c文档

## functional

### std::greater

自带比较函数，用法：

```c++
// greater example
#include <functional>   // std::greater
#include <algorithm>    // std::sort
#include <map> 			// std::map

int main () {
  int numbers[]={20,40,50,10,30};
  std::sort (numbers, numbers+5, std::greater<int>()); // 排序
  map<int, int, std::greater<int>> m; // 自定义排序的map
  return 0;
}
```

### std::less

自带的比较函数，用法：

```c++
// less example
#include <functional>   // std::less
#include <algorithm>    // std::sort, std::includes

int main () {
  int foo[]={10,20,5,15,25};
  int bar[]={15,10,20};
  std::sort (foo, foo+5, std::less<int>());  // 5 10 15 20 25
  std::sort (bar, bar+3, std::less<int>());  //   10 15 20
  map<int, int, std::less<int>> m; // 自定义排序的map
  return 0;
}
```

## typeinfo

用于获取变量类型

示例：

```c++
#include <iostream>
#include <typeinfo> 
using namespace std;

int main(){
    int v1 = 1;
    char v2 = 'a';
    double v3 = 1;
    float v4 = 1.1;
    bool v5 = false; 
    cout << typeid(v1).name() << endl;
    cout << typeid(v2).name() << endl;
    cout << typeid(v3).name() << endl;
    cout << typeid(v4).name() << endl;
    cout << typeid(v5).name() << endl;
    return 0;
}
```

# 正则表达式

参考：https://blog.csdn.net/bgzclxqq/article/details/90262904

# lambda表达式

使用lambda表达式快速创建一个函数：

```c++
int main() {
    int min = INT_MAX;
    auto function = [&](int cur) {
    	if (cur < min) {
            min = cur;
        }  
    };
    function(5);
    cout << min << endl; // 5
	return 0;
}
```

# 智能指针

参考：https://blog.csdn.net/xt_xiaotian/article/details/5714477、https://blog.csdn.net/code_peak/article/details/119722167、https://blog.csdn.net/flowing_wind/article/details/81301001、https://blog.csdn.net/runner668/article/details/80539221

# 函数

## `min_element`

根据规则从容器中找到某个元素

示例：

```c++
int minSize(vector<string>& strs) {
	int ret = min_element(strs.begin(), strs.end(), [](const string* s1, const string& s2) -> bool { return s1.size() < s2.size(); }) -> size();
    return ret;
}
```

