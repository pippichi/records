# API Reference Document

https://www.apiref.com/

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

## delete

c++11中有“`= delete;`”的用法，表示deleted函数，对于deleted函数，编译器会对其禁用，从而避免某些非法的函数调用或者类型转换，从而提高代码的安全性。

参考：https://blog.csdn.net/fengbingchun/article/details/52475108

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

## static

C++中静态成员变量可以在类内部声明但要在类外部再定义或初始化，否则会产生错误。

原因是：

-  在类中，只是声明了静态变量，并没有定义

- 声明只是表明了变量的数据类型和属性，并不分配内存，而定义则是需要分配内存的

  注意：如果在类里面这么写：`int a;` 那么是既声明了变量，也定义了变量，两者合在一起了

- 静态成员是“类级别”的，也就是它和类的地位等同，而普通成员是“对象（实例）级别”的。 类级别的成员，先于该类任何对象的存在而存在，它被该类所有的对象共享

- 现在假定要实例化类的一个对象

  静态成员肯定要出现在这个对象里面的，那么这时候才去定义那个静态成员吗？

  这显然是不合适的，因为，比如有另外一个线程也要创建该类的对象，那么也要按照这个方式去定义那个静态成员。  

  这会产生两种可能的情况：

  - 重复定义
  - 就算不产生重复定义的情况，也会产生竞争，从而造成死锁的问题，以至于对象无法创建

  很显然，编译器不能这么干。那么合理的解决办法就是事先在类的外部把它定义好，然后再供所有的对象共享。      

  当然这样做，还是有可能产生线程安全的问题，但不管怎么说对象是创建好了，而这种线程安全问题，可以在编程中予以解决。

## namespace

参考：https://blog.csdn.net/qq_21033779/article/details/78921997

## concept与requires

概念与约束

参考：https://www.apiref.com/cpp-zh/cpp/language/constraints.html

## decltype

decltype是C++11新增的一个关键字，和auto的功能一样，用来在编译时期进行自动类型推导。引入decltype是因为auto并不适用于所有的自动类型推导场景，在某些特殊情况下auto用起来很不方便或无法使用。

```c++
auto varName=value;
decltype(exp) varName=value;
```

- auto根据=右边的初始值推导出变量的类型，decltype根据exp表达式推导出变量的类型，跟=右边的value没有关系

- auto要求变量必须初始化，这是因为auto根据变量的初始值来推导变量类型的，如果不初始化，变量的类型也就无法推导；而decltype不要求，因此可以写成如下形式

  ```c++
  decltype(exp) varName;
  ```


  原则上讲，exp只是一个普通的表达式，它可以是任意复杂的形式，但必须保证exp的结果是有类型的，不能是void；如exp为一个返回值为void的函数时，exp的结果也是void类型，此时会导致编译错误

参考：https://blog.csdn.net/qq_38196982/article/details/118578967

decltype与typeid的区别：typeid的作用与decltype相似，都可以得到一个变量或者表达式的类型，不同的是，typeid方法得到的类型不能用于定义变量，可以用来进行类型的比较

为`unordered_map`自定义哈希函数的案例（里面刚好用到了decltype）：

```c++
// 自定义对array<int, 26>类型的哈希函数
auto arrayHash = [fn = hash<int>{}](const array<int, 26>& arr) -> size_t {
    return accumulate(arr.begin(), arr.end(), 0u, [&](size_t acc, int num) {
        return (acc << 1) ^ fn(num);
    })
}
unordered_map<array<int, 26>, vector<string>, decltype(arrayHash)> mp(0, arrayHash);
```

## typeid

RTTI(`Run-TimeType Information`, 运行时类型信息)，它提供了运行时确定对象类型的方法。在C++中，为了支持RTTI提供了两个操作符：`dynamic_cast`和typeid

参考：https://blog.csdn.net/fengbingchun/article/details/51866559

# 库函数

## stl

### map

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

为`unordered_map`自定义哈希函数：

```c++
// 自定义对array<int, 26>类型的哈希函数
auto arrayHash = [fn = hash<int>{}](const array<int, 26>& arr) -> size_t {
    return accumulate(arr.begin(), arr.end(), 0u, [&](size_t acc, int num) {
        return (acc << 1) ^ fn(num);
    })
}
unordered_map<array<int, 26>, vector<string>, decltype(arrayHash)> mp(0, arrayHash);
```

### advance

迭代器无法`+1`，但可以通过`std::advance(it, 1)`实现同样的效果，也可以自己定义一个`operator+`：

```c++
#include <iostream>
#include <map>
#include <iterator>
#include <concepts>

using namespace std;

template<class It, class Distance> requires bidirectional_iterator<It>
It operator+(It lhs, Distance distance) {
    std::advance(lhs, distance);
    return lhs;
}
int main() {
    std::map<int, int> m = { {1, 11}, {2, 22} };
    auto it = m.begin();
    it = it + 1;
    cont << it -> first << "\n";
    return 0;
}
```

### valarray

valarray 是面向数值计算的数组，在C++11中才支持，他支持很多数值数组操作，如求数组总和、最大数、最小数等

参考：https://blog.csdn.net/xiamentingtao/article/details/48243389

### slice

`std::slice` 表示一个valarray切片选择器

参考：https://www.apiref.com/cpp-zh/cpp/numeric/valarray/slice.html

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

## optional（c++ 17）

优雅地返回NULL

参考：https://blog.csdn.net/janeqi1987/article/details/100567933

## algorithm

### `min_element`与`max_element`

根据规则从容器中找到某个元素

vector示例：

```c++
int minSize(vector<string>& strs) {
	int ret = min_element(strs.begin(), strs.end(), [](const string* s1, const string& s2) -> bool { return s1.size() < s2.size(); }) -> size();
    return ret;
}
```

map示例：

```c++
bool cmpValue(const pair<int, int> left, const pair<int, int> right) {
    return left.second < right.second;
}
int maxValue(unordered_map<int, int>& valueMap) {
//    ans是迭代器，返回key-value
    auto ans = max_element(valueMap.begin(), valueMap.end(), cmpValue);
    cout << ans -> first << " - " << ans -> second << endl;
    return ans -> second;
}
```

### `upper_bound`与`lower_bound`

参考：http://c.biancheng.net/view/7527.html（`upper_bound`）、http://c.biancheng.net/view/7521.html（`lower_bound`）

## ios

### `std::fixed`、`std::scientific`、`std::hexfloat`、`std::defaultfloat`

用于输入输出格式化

参考：https://www.apiref.com/cpp-zh/cpp/io/manip/fixed.html

## tuple

### tie

类似于js中的解构

可用于交换变量值，举例：

```c++
int i, j, k;
// i -> j ; j -> k ; k -> i
// 原先是这样做的：
int temp = k;
k = j;
j = i;
i = temp;
// 使用tie之后就不必这样麻烦了：
tie(j, k, i) = make_tuple(i, j, k);
```

参考：http://www.cplusplus.com/reference/tuple/tie/

# 构造器的几种写法

案例：

```c++
class Name {
private:
    string name; 
    static string gen() {
        return "generating...";
    }
public:
    // 写法一：
    Name(const string& name) {
        this -> name = name;
    }
    // 写法二：
    Name(const string& name): name(name) {
    }
    // 写法三：
    Name(const string& name): name{ name } {
    }
    // 写法四：
    Name(): name(Name::gen()) {
    }
    // 写法五：
    Name(): name{ Name::gen() } {
    }
}
```

# 运算符重载

参考：https://blog.csdn.net/lishuzhai/article/details/50781753

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

# c++函数返回NULL的解决方案

假设有某一个函数，返回类型为`std::string&`，此时我想返回NULL

注意C++引用不能为空！

解决方案：

- 如果返回是一个对象的引用，这个对象的生存期与函数调用的范围没有关系，比如数据成员，则可以安全地返回一个原始指针（推荐指向const的指针）

  ```c++
  std::string const* foo::bar() const { 
      if (condition) {
      	return &some_data_member; 
      } else {
          return nullptr; 
      }
  }
  ```

- 如果不是，最佳解决方案是C++ 17[`std::optional`](http://en.cppreference.com/w/cpp/experimental/optional)或使用包装类型等（[`boost::optional`](http://www.boost.org/doc/libs/release/libs/optional/doc/html/index.html)）。这可以通过值返回一个可选对象（性能好），但它也是[self-documenting](https://en.wikipedia.org/wiki/Self-documenting_code)

  ```c++
  std::optional<std::string> foo::bar() const { 
      if (condition) { 
       	return "hello, world"; 
      } else { 
       	return std::nullopt; 
      } 
  } 
  ```

- 或者，可以返回一个指针，该指针可以为null。但是，返回一个原始指针会引发谁负责删除动态分配的字符串的问题。在这种情况下，返回[`std::unique_ptr`](http://en.cppreference.com/w/cpp/memory/unique_ptr)将是最好的选择，因为所有权显式传递给调用者

  ```c++
  std::unique_ptr<std::string> foo::bar() const { 
      if (condition) { 
       	return std::make_unique<std::string>("hello, world"); 
      } else { 
       	return nullptr; 
      } 
  } 
  ```

- 或者甚至更简单，直接返回一个空字符串

  ```c++
  std::string foo::bar() const { 
      if (condition) { 
       	return "hello, world"; 
      } else { 
       	return ""; 
      } 
  }
  ```

# 正则表达式

参考：https://blog.csdn.net/bgzclxqq/article/details/90262904

# lambda表达式

参考：https://blog.csdn.net/u010984552/article/details/53634513

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

自定义对 `array<int, 26>`类型的哈希函数：

```c++
auto arrayHash = [fn = hash<int>{}](const array<int, 26>& arr) -> size_t {
    return accumulate(arr.begin(), arr.end(), 0u, [&](size_t acc, int num) {
        return (acc << 1) ^ fn(num);
    })
}
```



# 智能指针

参考：https://blog.csdn.net/xt_xiaotian/article/details/5714477、https://blog.csdn.net/code_peak/article/details/119722167、https://blog.csdn.net/flowing_wind/article/details/81301001、https://blog.csdn.net/runner668/article/details/80539221

# 浅拷贝（值拷贝）和深拷贝（位拷贝）

参考：https://blog.csdn.net/haoaoweitt/article/details/81204336

# 对自定义类型做hash操作

参考：https://blog.csdn.net/qq_45311905/article/details/121488048

hash是非常重要的概念，诸如哈希map、哈希set这些容器中都有用到hash操作

为`unordered_map`自定义哈希函数：

```c++
// 自定义对array<int, 26>类型的哈希函数
auto arrayHash = [fn = hash<int>{}](const array<int, 26>& arr) -> size_t {
    return accumulate(arr.begin(), arr.end(), 0u, [&](size_t acc, int num) {
        return (acc << 1) ^ fn(num);
    })
}
unordered_map<array<int, 26>, vector<string>, decltype(arrayHash)> mp(0, arrayHash);
```

# 线程安全

## 锁

参考：https://blog.csdn.net/kangjianflying/article/details/105637015

## `lock_guard`与它的加强版`unique_lock`

参考：https://blog.csdn.net/u010990478/article/details/107410841（`lock_guard`用法）、

https://blog.csdn.net/u012507022/article/details/85909567（`unique_lock`详解）

## `thread_local`

参考：https://blog.csdn.net/fengbingchun/article/details/108691986/

# 模板元编程与type_traits

参考：https://blog.csdn.net/WHEgqing/article/details/121390133（C++模板元编程）、https://blog.csdn.net/zxc024000/article/details/79405869（C++模板元编程type_traits）、https://blog.csdn.net/mogoweb/article/details/79264925（[C++11札记]： type traits简介）

# 现代C++之SFINAE（模板进阶）

参考：https://blog.csdn.net/jeffasd/article/details/84667090（std::enable_if 的几种用法）、https://zhuanlan.zhihu.com/p/21314708（C++模板进阶指南：SFINAE）、https://blog.csdn.net/guangcheng0312q/article/details/103884392（现代C++之SFINAE）

