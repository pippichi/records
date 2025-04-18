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



constexpr的重要性举例：

```c++
template<typename RotatorOrVector>
TArray<RotatorOrVector> UAuraAbilitySystemLibrary::TEvenlyDirectors(const FVector& Forward, const FVector& Axis, float Spread, int32 NumDirectors)
{
    if constexpr (std::is_same_v<RotatorOrVector, FVector>) { // constexpr是必须的，因为模板类型需要在编译器确定
        // ...
    }
}
```



## volatile

参考：https://blog.csdn.net/weixin_44363885/article/details/92838607（详解C/C++中volatile关键字）

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



在函数声明中使用mutable关键字：

```c++
template<typename ...U>
void Test(U... u) {
    /**int last_index = sizeof...(U) - 1;
    int i = 0;
    auto printer = [last_index, i]<typename Arg>(Arg arg) mutable { // 如果不加mutable则编译不过，因为i是外部int变量，不能直接被lambda表达式捕获
        if (last_index == i++) cout << arg << endl;
        else cout << arg << ", ";
    };*/
    
    
    // 1、上面代码中由于sizeof...是一个在编译期就能计算完成的操作符，因此last_index本质上就是一个值，可以使用const来修饰。如果使用const修饰了，那么就不需要写到lambda表达式捕获符号中了从而避免不必要的值拷贝；
    // 2、上面代码中由于lambda表达式对于i是值捕获，会发生值拷贝；
    // 因此使用引用捕获做优化：
    int const last_index = sizeof...(U) - 1;
    int i = 0;
    auto printer = [&i]<typename Arg>(Arg arg) {
        if (last_index == i++) cout << arg << endl; // 这里的i++改变的是引用i所指向内存地址中的值
        else cout << arg << ", ";
    };
    (printer(u), ...); // 折叠表达式
}
```

参考：https://blog.csdn.net/AAA123524457/article/details/80967330（深入理解C++中的mutable关键字）、https://zhuanlan.zhihu.com/p/455490651（C++知识分享：C++的mutable和volatile）、https://www.bilibili.com/video/BV1564y1h7y7/?spm_id_from=333.788&vd_source=c3d9e4c3ef670596b3b0dddab637f86c（[C++] 填上上回的坑：这次用 Lambda 试试？）

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

参考：https://blog.csdn.net/qq_33485434/article/details/78418012（C++ 中override的作用）、https://blog.csdn.net/qq_42542471/article/details/124659190（C++ [override]关键字使用详解）

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

## namespace与inline namespace（内联命名空间）

参考：https://blog.csdn.net/qq_21033779/article/details/78921997（c++ 中的 namespace 用法）、https://blog.csdn.net/craftsman1970/article/details/82872497（C++11新特性(79)-内联命名空间(inline namespace)）

## concept与requires

概念与约束

参考：https://www.apiref.com/cpp-zh/cpp/language/constraints.html

## auto

参考：https://blog.csdn.net/weixin_65743593/article/details/128885933（C++之（泛型编程基础）auto、decltype）、https://blog.csdn.net/xiaoquantouer/article/details/51647865（c++ auto类型用法总结）

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

## explicit

参考：https://blog.csdn.net/l2563898960/article/details/97769569（C++ 中explicit关键字详解）

## operator重载符

参考：https://blog.csdn.net/liitdar/article/details/80654324（C++编程语言中重载运算符（operator）介绍）、https://blog.csdn.net/jinzhu1911/article/details/101317367（C++operator()(重载小括号运算符)）、https://blog.csdn.net/xgf415/article/details/52966475（C++函数对象operator()）

### 重载文本符号

重载文本运算符时参数类型必须为`char`或`unsigned long long`！

例1：（以下为`std::chrono_literials`中看到的例子）

```c++
inline namespace literals {
    inline namespace chrono_literals {
        _NODISCARD constexpr _CHRONO milliseconds operator"" ms(unsigned long long _Val) noexcept /* strengthened */ {
            return _CHRONO milliseconds(_Val);
        }
    }
}
```

例2：

```c++
namespace qyf {
	const int operator"" _qyf(char test) {
		return 1;
	}
}
int main() {
    using namespace ::qyf;
    1_qyf; // 可以编译通过
    return 0;
}
```

### 重载隐式类型转换

```c++
class xx {
    // ...
    operator int() const {return 1;}
}
```

参考：

https://blog.csdn.net/qq_41453285/article/details/95189974（C++:28---类类型转换之类型转换运算符operator（explicit））

### 重载函数调用运算符

```c++
//类内定义:
void operator () (int n1 = 0){
}
void operator () (){ // 无参函数调用
}
```

参考：

https://blog.csdn.net/qq_42683011/article/details/102087764（C++ operator重载运算符详解）

### 类内、外重载

参考：

https://blog.csdn.net/u014583317/article/details/109217780（C++运算符重载（类内、外重载））

### 解决二义性的类型转换

```c++
//最好不要在两个类之间构建相同的类型转换
struct B;

struct A {
    A() = default;
    A(const B&); //把一个B转换成A
};
 
struct B {
    operator A()const; //也是把一个B转换成A
};
 
int main()
{
    A f(const A&);
    B b;
    A a = f(b);//二义性错误：含义是f(B::operator A())还是f(A::A(const B&))
    A a1 = f(b.operator A());  //正确，使用B的类型转换运算符
	A a2 = f(A(b));  //正确，使用A的构造函数
    return 0;
}
```

参考：

https://blog.csdn.net/qq_41453285/article/details/95189974（C++:28---类类型转换之类型转换运算符operator（explicit））

### 重载new和delete

operator new, operator new[], placement new

operator delete, operator delete[]

参考：

https://blog.csdn.net/wudaijun/article/details/9273339（C++ 内存分配(new，operator new)详解）

https://en.cppreference.com/w/cpp/memory/new/operator_new、https://en.cppreference.com/w/cpp/memory/new/operator_delete

https://blog.csdn.net/aishuirenjia/article/details/102979457（`C++ 深入解析new关键字，::new、operator new函数，placement new表达式`）

### placement new

placement new 允许在已经分配好的内存地址上构造对象，与常规的 new 操作符不同，常规的 new 会同时分配内存并调用构造函数来初始化对象。而使用 placement new 时，需要自己负责内存的分配，并且可以指定对象构造的确切位置。

使用 placement new 的一个典型场景是在实现自定义的内存管理时，比如在内存池或缓存中预先分配一大块内存，然后在这块内存上按需构造和析构对象。

placement new 的语法如下：

```c++
new (address) Type(initialization arguments); // 其中 address 是一个指向已分配内存的指针，Type 是你想要构造的对象的类型，initialization arguments 是传递给构造函数的参数。
```

在虚幻引擎中看到的案例：

```c++
template <typename ThreadSafetyMode>
struct TWriteLockedDelegateAllocation
{
	// ...
	friend void* operator new<ThreadSafetyMode>(size_t Size, const TWriteLockedDelegateAllocation<ThreadSafetyMode>& LockedAllocation); // 重载new为placement new
	// ...
};

// 使用时的写法：
void CreateCopy(TDelegateBase<FNotThreadSafeNotCheckedDelegateMode>& Base) const final
{
    new (TWriteLockedDelegateAllocation{Base}) TBaseUFunctionDelegateInstance(*this);
}
```

参考：

https://blog.csdn.net/wudaijun/article/details/9273339（C++ 内存分配(new，operator new)详解）

https://blog.csdn.net/zhangxinrun/article/details/5940019（C++中placement new操作符（经典））

### `::new`

参考：

https://blog.csdn.net/aishuirenjia/article/details/102979457（C++ 深入解析new关键字，::new、operator new函数，placement new表达式）

### operator*()

```c++
/**
 * Dereference operator returns a reference to the FUniqueNetId
 */
const FUniqueNetId& operator*() const
{
    return *GetV1();
}
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-10_创建会话第18分45秒）

### operator->()

```c++
/**
 * Arrow operator returns a pointer to this FUniqueNetId
 */
const FUniqueNetId* operator->() const
{
    return GetV1().Get();
}
```

参考：

https://www.bilibili.com/video/BV1Zr4y1G79Z（UE5_C++多人TPS完整教程(一)-10_创建会话第18分45秒）

## likely与unlikely

参考：https://zhuanlan.zhihu.com/p/357434227（C++关键字之likely和unlikely）

## `__declspec`

参考：

https://blog.csdn.net/zhangzq86/article/details/52982939（`__declspec用法详解`）

### dllexport与dllimport

参考：

https://blog.csdn.net/mrbone11/article/details/122325328（C++ dllexport与dllimport介绍和使用）

# 操作符

## `sizeof...`

参考：https://en.cppreference.com/w/cpp/language/sizeof...（sizeof... operator (since C++11)）

## `...`

用法一：

```c++
void func1(...) {
	std::cout << "1";
}
// 解释：这是一个应急函数，会匹配任意调用。但是由于它通过"..."进行匹配的，因此其它任何匹配优先级都比它高。
// 场景：比方说在函数重载中，可以利用这种应急函数来做最次级别的匹配调用。
```

用法二：

```c++
template<typename R, typename... Args>
struct DecayT<R(Args..., ...)>
{
    using Type = R(*)(Args..., ...);
};
// 暂时不明意义。大致猜测跟用法一的用法是差不多的。
```

## 四种类型转换操作符

static_cast、dynamic_cast、reinterpret_cast、const_cast

参考：

https://blog.csdn.net/new9232/article/details/143988074（C++四种类型转换操作符 static_cast、dynamic_cast、reinterpret_cast、const_cast介绍）

https://c.biancheng.net/view/410.html（C++强制类型转换运算符（static_cast、reinterpret_cast、const_cast和dynamic_cast））

https://blog.csdn.net/u014624623/article/details/79837849（static_cast和dynamic_cast详解）

# 编译预处理指令

## #pragma

参考：

https://blog.csdn.net/seanyxie/article/details/7852993（#pragma data_seg()）

https://blog.csdn.net/weixin_45983489/article/details/120873659（C/C++中关于 #pragma 的深度探究）

https://blog.csdn.net/weixin_62264287/article/details/131819001（C/C++中的#pragma预处理指令）

## #define

参考：

https://blog.csdn.net/u012611878/article/details/52534622（c/c++中define用法详解及代码示例）

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

### decay

退化类型的修饰

参考：https://blog.csdn.net/luoshabugui/article/details/109853418（C++11 decay）

### declval

参考：https://blog.csdn.net/m0_51271123/article/details/121780256（declval）、https://blog.csdn.net/fpcc/article/details/128231478（c++11中的declval和decltype）、https://blog.csdn.net/baidu_41388533/article/details/109692968（（C++模板编程）：std::declval（上））、https://blog.csdn.net/baidu_41388533/article/details/109694962（（C++模板编程）：std::declval（下））

### exchange

参考：https://blog.csdn.net/baidu_41388533/article/details/110783345（`std::exchange` 介绍及使用）

### `common_type`与`common_type_t`

取出若干形参中的公共类型。

本质上其实在做隐式转换，如果A能隐式转换成B，则A和B的公共类型就是B。

参考：https://en.cppreference.com/w/cpp/types/common_type（`std::common_type`）、https://www.apiref.com/cpp-zh/cpp/types/common_type.html（`std::common_type`）、https://www.bilibili.com/video/BV1Eb4y1U7qR/?spm_id_from=333.788&vd_source=c3d9e4c3ef670596b3b0dddab637f86c（[C++] 填上回的坑：三块钱表达式的妙用？）、https://www.bilibili.com/video/BV1bv41137Je/?spm_id_from=333.788&vd_source=c3d9e4c3ef670596b3b0dddab637f86c（[C++] common_type 是个什么东西）

### `invoke_result`与`invoke_result_t`

举例：

```c++
template<typename T>
struct Generator {
    template<typename F>
    Generator<std::invoke_result_t<F, T>> map(F f) { // 这里我们传进去的模板参数是一个function，那么这个function返回值类型是啥呢？我们可以通过std::invoke_result_t<F, T>来拿到它的返回类型，其中F表示该function，T则表示函数的参数。
        while (has_naxt()) {
            co_yield f(next());
        }
    }
}
```

参考：https://en.cppreference.com/w/cpp/types/result_of（`std::invoke_result`）

## ctype

### isalpha、isalnum、islower、isupper

参考笔记中的c文档

## functional

### function

参考：

https://blog.csdn.net/mj348940862/article/details/144449345（C++中的高阶函数：std::function）

https://blog.csdn.net/hzy925/article/details/79676085（C++ std::function的用法）

https://blog.csdn.net/qq_38410730/article/details/103637778（【C++】C++11的std::function和std::bind用法详解）

### bind

参考：

https://blog.csdn.net/jxianxu/article/details/107382049（C++11中的std::bind 简单易懂）

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

### `binary_search`

参考：http://c.biancheng.net/view/7537.html（`binary_search`）

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

# 函数指针

## 成员函数指针

参考：

https://blog.csdn.net/afei__/article/details/81985937（C++ 类成员函数的函数指针）

https://blog.csdn.net/weixin_45075170/article/details/145405382（C++中成员函数指针和普通函数指针的区别在哪）

# 内置宏

## `__VA_ARGS__`

表示是一个可变参数的宏

参考：https://blog.csdn.net/qq_33726635/article/details/115560659（可变参数列表`__VA_ARGS__`介绍）

# cmake

参考：https://www.hahack.com/codes/cmake/（CMake 入门实战）、https://zhuanlan.zhihu.com/p/500002865（CMake 良心教程，教你从入门到入魂）

## find_package、include_directories和target_link_libraries

参考：https://blog.csdn.net/haluoluo211/article/details/80559341/（cmake教程4(find_package使用)）、https://blog.csdn.net/weixin_39393741/article/details/85070299（include_directories和find_package）、https://blog.csdn.net/u012483097/article/details/109066405（target_link_libraries 和link_libraries区别）

# 模块Modules

参考：https://blog.csdn.net/drivextech/article/details/108697290（c++20模块）、https://blog.csdn.net/Jxianxu/article/details/127499762（一文读懂C++20 新特性之module（模块））、https://zhuanlan.zhihu.com/p/350136757（C++20 新特性: modules 及实现现状）

# Visual Studio开源库集成器Vcpkg

VS项目中，如果要使用第三方库，有两种解决方案：

- 不使用Vcpkg

  右键点击项目 -> 属性 -> VC++ 目录 -> 包含目录 -> 编辑 -> 新增行 -> 将第三方库的包绝对路径写进去（当然要先去官网下载第三方库的包文件） -> 确定即可正常使用第三方库

- 使用Vcpkg

  参考：https://blog.csdn.net/cjmqas/article/details/79282847（Visual Studio开源库集成器Vcpkg全教程--利用Vcpkg轻松集成开源第三方库）

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

参考：

https://blog.csdn.net/nanjono/article/details/131445237（★C++笔记四（右值引用），讲的非常通俗易懂）

https://blog.csdn.net/u012198575/article/details/83142419（c++——左值、右值、左值引用、右值引用）

https://blog.csdn.net/caojianfa969/article/details/118927852（左值、右值引用及其作用）

https://blog.csdn.net/beijixingcd/article/details/126131624（C++知识篇--右值引用）

## std::move、std::forward

`std::move`和`std::forward`本质就是一个转换函数，`std::move`执行到右值的无条件转换，`std::forward`执行到右值的有条件转换，在参数都是右值时，二者就是等价的。

参考：https://www.jianshu.com/p/b90d1091a4ff（`C++11 std::move和std::forward`）

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

参考：http://c.biancheng.net/view/2280.html（C++虚继承和虚基类详解）、https://blog.csdn.net/tmrjlu/article/details/119877557（C++ Virtual详解）

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

## `std::enable_shared_from_this`

参考：https://zhuanlan.zhihu.com/p/393571228（C++里std::enable_shared_from_this是干什么用的？）、https://www.zhihu.com/question/30957800/answer/3079826725（std::enable_shared_from_this 有什么意义？）

## `weak_ptr`

参考：https://zhuanlan.zhihu.com/p/617034450（从源码了解Weak Point）

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

# 多线程

## `std::async`与`std::thread`比较

参考：https://www.zhihu.com/question/547132461/answer/2657296340（C++ 中，std::async 可以完全替代 std::thread 来开启异步的多线程操作吗？）

# 线程安全

## 锁

参考：

https://blog.csdn.net/kangjianflying/article/details/105637015（C++学习锁）

https://blog.csdn.net/weixin_44046545/article/details/138551385（C++所有锁的讲解、使用场景、相应的C++代码示例）

## `lock_guard`与它的加强版`unique_lock`

参考：

https://blog.csdn.net/u010990478/article/details/107410841（`lock_guard`用法）、

https://blog.csdn.net/u012507022/article/details/85909567（`unique_lock`详解）

## `thread_local`

参考：

https://blog.csdn.net/fengbingchun/article/details/108691986/（C++11中thread_local的使用）

## 原子变量

参考：

https://blog.csdn.net/qq_46017342/article/details/132838649（C++ 中的原子变量（std::atomic）使用指南）

# 单例（线程安全的写法）

参考：

https://blog.csdn.net/unonoi/article/details/121138176（【C++】C++ 单例模式总结（5种单例实现方法））

https://blog.csdn.net/chenxiemin/article/details/110877406（c++: 单例模式(Singleton)的最优写法）

# 协程

参考：https://www.bennyhuo.com/book/cpp-coroutines（渡劫 C++ 协程）、https://www.bilibili.com/video/BV1vv4y1A7fX/?spm_id_from=333.788&vd_source=c3d9e4c3ef670596b3b0dddab637f86c（C++ 20 的协程其实就是一首歌？）

# ranges与views

用起来有点像Java的流式编程

参考：

https://blog.csdn.net/arong_xu/article/details/140803573（C++ ranges/view库简介）

https://blog.csdn.net/qq_42896106/article/details/128737878（C++ 20 新特性 ranges 精讲）

# 模板

参考：https://blog.csdn.net/sinat_34657451/article/details/51340160（template详解）

## 元编程

参考：https://blog.csdn.net/WHEgqing/article/details/121390133（C++模板元编程）、https://bot-man-jl.github.io/articles/?post=2017/Cpp-Metaprogramming（浅谈 C++ 元编程）

## 混合元编程

参考：https://blog.csdn.net/baidu_41388533/article/details/109806030（（C++模板编程）：混合元编程（上））、https://blog.csdn.net/baidu_41388533/article/details/109810342（（C++模板编程）：混合元编程（下））

## fold expressions

参考：https://blog.csdn.net/zwvista/article/details/53981696（C++17尝鲜：fold expression（折叠表达式））、https://www.bilibili.com/video/BV1BA411c7eA/?vd_source=c3d9e4c3ef670596b3b0dddab637f86c（[C++] fold expression? 卷起来的表达式？）

## 全特化与偏特化

参考：https://blog.csdn.net/m_buddy/article/details/72973207（C++模板全特化、偏特化）

## 萃取器

参考：

https://zhuanlan.zhihu.com/p/559936879（C++-模板-萃取的实现(一)）、https://zhuanlan.zhihu.com/p/560507157（C++-模板-萃取的实现(二)）、https://zhuanlan.zhihu.com/p/561377252（C++-模板-萃取的实现(三)）、https://zhuanlan.zhihu.com/p/562809490（C++-模板-萃取的实现(四)）、https://zhuanlan.zhihu.com/p/569142750（C++-模板-萃取的实现(五)）

https://blog.csdn.net/zxc024000/article/details/79405869（C++模板元编程type_traits）、https://blog.csdn.net/mogoweb/article/details/79264925（[C++11札记]： type traits简介）、https://zhuanlan.zhihu.com/p/547313994（C++：STL中的萃取器traits）

https://blog.csdn.net/wxj1992/article/details/122506368（C++11 类型支持之type traits）

## 模板参数类型

模板参数通常表示类型，表示类型的模板参数称为类型模板参数（type template parameter）；此外还有非类型模板参数（non-type template parameter），非类型模板参数包含以下四种类型：

- 整数及枚举类型
- 指针（对象指针或函数指针）
- 引用（对象引用或函数引用）
- 指向类对象成员函数的指针

模板的参数还可以是一个模板，叫做模板模板参数（template template parameter）。这些类型的模板参数都可以同时出现在模板参数列表中。

参考：https://blog.csdn.net/KangRoger/article/details/82833001（模板参数类型）、https://blog.csdn.net/men_wen/article/details/74033327（C++ 模板模板参数）

还有一种非类型模板参数的写法（[653. 两数之和 IV - 输入二叉搜索树](https://leetcode.cn/problems/two-sum-iv-input-is-a-bst/)）：

写法一

```c++
template<TreeNode* TreeNode::*ls = &TreeNode::left, TreeNode* TreeNode::*rs = &TreeNode::right>
void tree_iterator_init(TreeNode*& it, stack<TreeNode*>& st) {
    while (it->*ls) {
        st.push(it);
        it = it->*ls;
    }
}

template<TreeNode* TreeNode::*ls = &TreeNode::left, TreeNode* TreeNode::*rs = &TreeNode::right>
void tree_iterator_next(TreeNode*& it, stack<TreeNode*>& st) {
    if (it->*rs) {
        it = it->*rs;
        tree_iterator_init<ls, rs>(it, st);
    } else if (!st.empty()) {
        it = st.top();
        st.pop();
    } else it = nullptr;
}

class Solution {
public:
    bool findTarget(TreeNode* root, int k) {
        if (!root) return false;
        auto it1 = root;
        auto it2 = root;
        stack<TreeNode*> st1;
        stack<TreeNode*> st2;
        tree_iterator_init<&TreeNode::left, &TreeNode::right>(it1, st1);
        tree_iterator_init<&TreeNode::right, &TreeNode::left>(it2, st2);
        while (it1 != it2) {
            const int sum = it1->val + it2->val;
            if (sum == k) return true;
            if (sum < k)
                tree_iterator_next<&TreeNode::left, &TreeNode::right>(it1, st1);
            else
                tree_iterator_next<&TreeNode::right, &TreeNode::left>(it2, st2);
        }
        return false;
    }
};
```

写法二

```c++
class Solution {
    template<TreeNode* TreeNode::*lf>
    void tree_iterator_init(TreeNode*& it, stack<TreeNode*>& st) {
        while (it->*lf != nullptr) {
            st.emplace(it);
            it = it->*lf;
        }
    }

    template<TreeNode* TreeNode::*lf = &TreeNode::left, TreeNode* TreeNode::*rh = &TreeNode::right>
    void tree_iterator_next(TreeNode*& it, stack<TreeNode*>& st) {
        if (it->*rh) {
            it = it->*rh;
            tree_iterator_init<lf>(it, st);
        } else if (!st.empty()) {
            it = st.top();
            st.pop();
        } else {
            it = nullptr;
        }
    }
public:
    stack<TreeNode*> left_stack, right_stack;
    bool findTarget(TreeNode* root, int k) {
        if (root == nullptr) {
            return false;
        }
        TreeNode* lf = root, *rh = root;
        tree_iterator_init<&TreeNode::left>(lf, left_stack);
        tree_iterator_init<&TreeNode::right>(rh, right_stack);
        while (lf != rh) {
            const int sum = lf -> val + rh -> val;
            if (sum == k) {
                return true;
            }
            if (sum < k) {
                tree_iterator_next<&TreeNode::left, &TreeNode::right>(lf, left_stack);
            } else {
                tree_iterator_next<&TreeNode::right, &TreeNode::left>(rh, right_stack);
            }
        }
        return false;
    }
};
```

## 现代C++之SFINAE（模板进阶）

参考：https://blog.csdn.net/jeffasd/article/details/84667090（std::enable_if 的几种用法）、https://zhuanlan.zhihu.com/p/21314708（C++模板进阶指南：SFINAE）、https://blog.csdn.net/guangcheng0312q/article/details/103884392（现代C++之SFINAE）



SFINAE中的`type* = nullptr`是 SFINAE 的一种技巧，用于在模板中检查类型特性：

```c++
template <typename T> 
void inc_counter(
    T& counterInt,
    typename std::enable_if<
    std::is_integral<T>::value
    >::type* = nullptr );
```

参考：https://www.coder.work/article/7294607（`c++ - “type* = nullptr”是什么意思`）

## 模板构造器

举例：

```c++
template<typename T>
struct Generator {
    template<typename U>
    Generator<U> map(std::function<U(T)> f) { // 参数std::function<U(T)>当中的模板参数U(T)是个模板构造器，放到这里就表示这个函数的参数类型为T，返回值类型为U。
        while (has_next()) {
            co_yield f(next());
        } 
    }
}
```

## `::template和.template`

`::template`表示显示调用类的静态模板成员函数，例如：

```c++
class UContentBrowserAssetContextMenuContext
{
public:
    static const UContentBrowserAssetContextMenuContext* FindContextWithAssets1(const MenuOrSectionType& MenuOrSection)
    {return nullptr;}
    
    template<typename MenuOrSectionType>
	static const UContentBrowserAssetContextMenuContext* FindContextWithAssets2(const MenuOrSectionType& MenuOrSection)
    {return nullptr;}
}
int main() 
{
    // 普通静态函数写法
    const UContentBrowserAssetContextMenuContext* Context1 = UContentBrowserAssetContextMenuContext::FindContextWithAssets1(nullptr);
    // 模板静态函数写法
    const UContentBrowserAssetContextMenuContext* Context2 = UContentBrowserAssetContextMenuContext::template FindContextWithAssets2(nullptr);
    return 0;
}
```

`.template`表示显示调用实例的模板成员函数，例如：

```c++
class UContentBrowserAssetContextMenuContext
{
public:
    UContentBrowserAssetContextMenuContext* FindContextWithAssets1(const MenuOrSectionType& MenuOrSection)
    {return nullptr;}
    
    template<typename MenuOrSectionType>
	UContentBrowserAssetContextMenuContext* FindContextWithAssets2(const MenuOrSectionType& MenuOrSection)
    {return nullptr;}
}
int main() 
{
    UContentBrowserAssetContextMenuContext IContext;
    // 普通成员函数写法
    UContentBrowserAssetContextMenuContext* Context1 = IContext.FindContextWithAssets1(nullptr);
    // 模板成员函数写法
    UContentBrowserAssetContextMenuContext* Context2 = IContext.template FindContextWithAssets2(nullptr);
    return 0;
}
```

## 判断模板类关系

### 父子关系判断

```c++
template<typename GEComponentClass>
GEComponentClass& UGameplayEffect::AddComponent()
{
    // 1.
	static_assert( TIsDerivedFrom<GEComponentClass, UGameplayEffectComponent>::IsDerived, "GEComponentClass must be derived from UGameplayEffectComponent");
    // 2.
    if constexpr (TIsDerivedFrom<GEComponentClass, UGameplayEffectComponent>::IsDerived) { // constexpr是必须的，因为模板类型需要在编译器确定
        // ...
    }
	// ...
}

// TIsDerivedFrom源码如下：
/** Is type DerivedType inherited from BaseType. */
template<typename DerivedType, typename BaseType>
struct TIsDerivedFrom
{
	// Different size types so we can compare their sizes later.
	typedef char No[1];
	typedef char Yes[2];

	// Overloading Test() s.t. only calling it with something that is
	// a BaseType (or inherited from the BaseType) will return a Yes.
	static Yes& Test( BaseType* );
	static Yes& Test( const BaseType* );
	static No& Test( ... );

	// Makes a DerivedType ptr.
	static DerivedType* DerivedTypePtr(){ return nullptr ;}

	public:
	// Test the derived type pointer. If it inherits from BaseType, the Test( BaseType* ) 
	// will be chosen. If it does not, Test( ... ) will be chosen.
	static constexpr bool Value = sizeof(Test( DerivedTypePtr() )) == sizeof(Yes);

	static constexpr bool IsDerived = Value;
};
```

### 同类型判断

```c++
template<typename RotatorOrVector>
TArray<RotatorOrVector> UAuraAbilitySystemLibrary::TEvenlyDirectors(const FVector& Forward, const FVector& Axis, float Spread, int32 NumDirectors)
{
    // 1.
    static_assert( std::is_same_v<RotatorOrVector, FVector>, "...");
    // 2.
    if constexpr (std::is_same_v<RotatorOrVector, FVector>) { // constexpr是必须的，因为模板类型需要在编译器确定
        // ...
    }
}
```

### constexpr的重要性！

```c++
template<typename RotatorOrVector>
TArray<RotatorOrVector> UAuraAbilitySystemLibrary::TEvenlyDirectors(const FVector& Forward, const FVector& Axis, float Spread, int32 NumDirectors)
{
    if constexpr (std::is_same_v<RotatorOrVector, FVector>) { // constexpr是必须的，因为模板类型需要在编译器确定
        // ...
    }
}
```



# vector的reserve的作用

场景：vector如果不断的`push_back`，会进行内存的重新自动分配。

reserve的作用是更改vector的容量（capacity），使vector至少可以容纳n个元素，如果n大于vector当前的容量，reserve会对vector进行扩容，其他情况下都不会重新分配vector的存储空间。

参考：https://blog.csdn.net/hl_zzl/article/details/84944494（vector的reserve的使用（避免内存重新分配以及内存分配的方式））

# 垃圾回收算法

参考：https://blog.csdn.net/u012611878/article/details/78947267（C++中垃圾回收机制中几种经典的垃圾回收算法）

# vscode

## 配置c++运行环境

mingw最新版本下载地址：https://github.com/niXman/mingw-builds-binaries/releases



参考：https://blog.csdn.net/weixin_48468423/article/details/118950592（VsCode安装和配置c/c++环境（超完整，小白专用））、https://blog.csdn.net/Zhouzi_heng/article/details/115014059（【c++】VSCode配置 c++ 环境（小白教程））

## 配置文件预设变量

参考：https://zhuanlan.zhihu.com/p/44967536（VSCode 配置文件的变量索引）、https://blog.csdn.net/weixin_39249524/article/details/105438814（vscode中的${workspaceFolder}等变量）、https://blog.csdn.net/Hello_Ray/article/details/123522502（vscode中的类似${workspaceFolder}的变量的解释）

## 集成vcpkg

参考：https://blog.csdn.net/weixin_42703267/article/details/120603746（关于Vcpkg在VScode中的配置问题）、https://zhuanlan.zhihu.com/p/430835667（vscode + cmake + vcpkg搭建c++开发环境）、https://blog.csdn.net/weixin_45748734/article/details/128937372（vcpkg + cmake + vscode 配置教程）、https://blog.csdn.net/henry_23/article/details/121099766（Windows 下使用 vcpkg + CMake 进行开发）

# 内存、性能分析

推荐阅读：https://fangliang.blog.csdn.net/article/details/75411797（★IT项目研发过程中的利器）

## ★Address Sanitizer（Asan）

参考：https://blog.csdn.net/wads23456/article/details/105141997（Address Sanitizer（Asan）原理及实战定位）、https://blog.csdn.net/weixin_41644391/article/details/103450401（c++ Asan(address-sanitize)的配置和使用）、https://www.jianshu.com/p/3a2df9b7c353（Address Sanitizer 用法）、https://zhuanlan.zhihu.com/p/512578904（关于 ASAN）、https://zhuanlan.zhihu.com/p/550130850（内存问题难定位，那是因为你没用ASAN）、https://www.cnblogs.com/gnivor/p/16364729.html（C++笔记-Asan(address-sanitize)的使用）、https://www.jianshu.com/p/9e85345e500b（查内存泄漏试试AScan）、https://juejin.cn/post/6997201560140775461（c/c++动态检测内存错误利器 - Asan）、https://blog.csdn.net/hanlizhong85/article/details/78076668（Linux下内存检测工具：asan）

## ★gperftools（内存泄漏分析、堆状态分析、动态执行流程分析和性能瓶颈分析等）

gperftools的Heap Checker已经废弃，请使用Address Sanitizer（Asan）

参考：

https://fangliang.blog.csdn.net/article/details/81234967（内存泄漏分析的利器——gperftools的Heap Checker）、https://fangliang.blog.csdn.net/article/details/81287765（堆状态分析的利器——gperftools的Heap Profiler）、https://fangliang.blog.csdn.net/article/details/81315729（动态执行流程分析和性能瓶颈分析的利器——gperftools的Cpu Profiler）

https://www.cnblogs.com/gnivor/p/16531828.html（性能测试工具gperftools使用-内存占用分析）

## 静态分析C语言生成函数调用关系的利器

参考：https://fangliang.blog.csdn.net/article/details/75576878（静态分析C语言生成函数调用关系的利器——cflow）、https://fangliang.blog.csdn.net/article/details/75441751（静态分析C语言生成函数调用关系的利器——calltree）

## 动态执行流程分析和性能瓶颈分析

参考：https://fangliang.blog.csdn.net/article/details/79457352（动态执行流程分析和性能瓶颈分析的利器——valgrind的callgrind）

## 内存分析

参考：https://fangliang.blog.csdn.net/article/details/79445591（内存问题分析的利器——valgrind的memcheck）、https://fangliang.blog.csdn.net/article/details/79429330（内存、性能问题分析的利器——valgraind）

## 堆内存分析

参考：https://fangliang.blog.csdn.net/article/details/81202015（堆问题分析的利器——valgraind的massif）、https://fangliang.blog.csdn.net/article/details/81220457（堆状态分析的利器——valgrind的DHAT）

## 锁性能分析

参考：https://fangliang.blog.csdn.net/article/details/81360242（互斥量、读写锁长占时分析的利器——valgrind的DRD）、https://fangliang.blog.csdn.net/article/details/81365851（死锁问题分析的利器——valgrind的DRD和Helgrind）

## 多线程数据数据竞争问题分析

参考：https://fangliang.blog.csdn.net/article/details/81369152（数据竞争（data race）问题分析的利器——valgrind的Helgrind）

# 指定程序运行到某个/某些CPU上

参考：https://fangliang.blog.csdn.net/article/details/79160916（绑定CPU逻辑核心的利器——taskset）

# 使用diff、patch在不损坏源文件的前提下给程序打补丁

参考：https://fangliang.blog.csdn.net/article/details/77337889（代码打补丁的利器——diff和patch）

# 内存锁定（mlock、mlockall、munlock、munlockall）

参考：https://blog.csdn.net/qq_33526144/article/details/103360720（内存锁定）

# 内存映射文件（mmap、munmap）

参考：https://zhuanlan.zhihu.com/p/429987335（探索内存原理的内存映射文件(图文详解)）

# 原始套接字socket

参考：

https://blog.csdn.net/weixin_43288201/article/details/106266418（原始套接字（各种协议的分析））、https://blog.csdn.net/AAAA202012/article/details/127457405（原始套接字）

https://zhuanlan.zhihu.com/p/651889948（Linux原始套接字抓取底层报文★）

https://www.cnblogs.com/LyShark/p/17786325.html（18.1 Socket 原生套接字抓包★）



# 奇技淫巧

## c/c++ 劫持系统函数

可用于浏览器劫持转发实现代理服务器等

### Detours

参考：https://cloud.tencent.com/developer/article/1425861（【C/C++教学】劫持？劫持？劫持？！！！）

### 全局Hook

参考：

https://blog.csdn.net/lyshark_csdn/article/details/124938872（C/C++ HOOK 全局 API）

### DLL 注入

参考：

https://blog.csdn.net/weixin_50564032/article/details/128150174（【网络杂烩 ---＞ 网络安全】DLL 注入 --- c/c++ 代码实现（超 · 详细））

https://www.jb51.net/article/236628.htm（C++ DLL注入工具(完整源码)）
