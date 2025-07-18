# Java高频重点总结

## 自增、计算与赋值（相关：《JVM虚拟机规范》关于指令的部分）

```java
public static void main(String[] args) {
    int i = 1;
    i = i++;
    int j = i++;
    int k = i + ++i * i++;
    System.out.println("i=" + i); // 4 
    System.out.println("j=" + j); // 1
    System.out.println("k=" + k); // 11
}
```

解释 i = i ++;

对于int i会有一个局部变量表和一个操作数栈

执行i = i ++；的时候的操作顺序：

1、把i的值压入操作数栈（操作数栈中i为1）

2、i变量自增1（局部变量表中i为1+1=2）

3、把操作数栈中的值赋值给i（局部变量表中的2被覆盖变成1）

因此i = i ++最后i还是1



解释k = i + ++i * i++;

首先i这个时候是2

| 步骤 | 局部变量表（存的int i） | 操作数栈 | 说明                                      |
| ---- | ----------------------- | -------- | ----------------------------------------- |
| 1    | 2                       | 2        |                                           |
| 2    | 3                       | 2，3     | ++i的时候先将i自增1变成3再将3压入操作数栈 |
| 3    | 4                       | 2，3，3  | i++先将i压入操作数栈，后执行自增1         |

依次出栈后k即为：2 + 3 * 3 = 11

## 单例

- 某个类只能有一个实例
  - 构造器私有化
- 它必须自行创建这个实例
  - 含有一个该类的静态变量来保存这个唯一的实例
- 它必须自行向整个系统提供这个实例
  - 对外提供获取该实例对象的方式
    - 直接暴露
    - 用静态变量的get方法获取

实现方式：

- 饿汉式

  直接创建对象，不存在线程安全问题

  ```java
  /*
  * 饿汉式：
  *   在类初始化时直接创建实例对象，不管是否需要这个对象都会创建
  */
  ```

  

  - 直接实例化饿汉式（简洁直观）

    ```java
    /*
    *   （1）构造器私有化
    *   （2）自行创建，并且用静态变量保存
    *   （3）向外提供者个实例
    *   （4）强调这是一个单例，可以用final修饰
    * */
    public class Singleton {
        // 因为是静态方法，因此在类被加载的时候就会创建
        public static final Singleton INSTANCE = new Singleton();
        private Singleton(){}
    }
    
    ```

  - 枚举式（最简洁）

    ```java
    /*
    * 枚举类型（枚举类型构造器全部都是私有的），表示该类型的对象是有限的几个
    * 我们可以限定为一个，就成了单例
    * */
    public enum Singleton {
        INSTANCE
    }
    ```

    

  - 静态代码块饿汉式（适合复杂实例化）

    ```java
    // 这么写跟上面的直接实例化没什么区别
    public class Singleton{
        public static final Singleton INSTANCE;
        static {
            INSTANCE = new Singleton();
        }
        private Singleton(){}
    }
    
    // 使用静态代码块的写法多用于需要使用配置文件中信息的情况
    public class Singleton{
        public static final Singleton INSTANCE;
        private String info;
        static {
            Properties pro = new Properties();
            try {
                // 要想读取外部配置文件需要先获取类构造器，Singleton正好有一个类构造器，那直接用Singleton的即可
                pro.load(Singleton.class.getClassLoader().getResourceAsStream("single.properties"));
                INSTANCE = new Singleton(pro.getProperty("info"));
            } catch (IOException e) {
                throw new RuntimeException(e)
            }
        }
        private Singleton(String info){
            this.info = info;
        }
    }
    ```

    

- 懒汉式

  延迟创建对象

  ```java
  /*
  * 懒汉式：
  *   延迟创建这个实例对象
  */
  ```

  

  - 线程不安全（适用于单线程）

    ```java
    /*
    *   （1）构造器私有化
    *   （2）用一个静态变量保存这个唯一的实例
    *   （3）提供一个静态方法，获取这个实例对象
    * */
    public class Singleton{
        private static Singleton instance;
        private Singleton(){}
        public static Singleton getInstance(){
            if (instance == null){
                instance = new Singleton();
            }
            return instance;
        }
    }
    ```

    此时如果存在多线程，并且我们给Singleton加一个Thread.sleep()：

    ```java
    public class Singleton{
        private static Singleton instance;
        private Singleton(){}
        public static Singleton getInstance(){
            if (instance == null){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new Singleton();
            }
            return instance;
        }
    }
    
    public class MyTest{
        Callable<Singleton> c = new Callable<Singleton>() {
            @Override
            public Singleton call() throws Exception {
                return Singleton.getInstance();
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton> f1 = es.submit(c);
        Future<Singleton> f2 = es.submit(c);
    
        Singleton s1 = f1.get();
        Singleton s2 = f2.get();
        es.shutdown();
    }
    ```

    我们发现它存在线程安全问题

    

  - 线程安全（适用于多线程）

    对于上述问题我们只需给它加一个synchronized并锁住类对象就行了

    ```java
    public class Singleton{
        private static Singleton instance;
        private Singleton(){}
        public static Singleton getInstance(){
            synchronized (Singleton.class){
                if (instance == null){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Singleton();
                }
            }
            return instance;
        }
    }
    ```

    此时存在一个待优化问题：如果这个Thread.sleep()大一点，那后面的所有线程都需要等待很长时间，但我们的目的是让他保持单例，而不是每个线程执行一遍代码逻辑，所以这个时候可以在最外面再套一层if判断逻辑，如果实例已经存在那就不要再等了，直接返回：

    ```java
    public class Singleton{
        private static Singleton instance;
        private Singleton(){}
        public static Singleton getInstance(){
            if (instance == null) {
                synchronized (Singleton.class) {
                    if (instance == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }
    ```

    

  - 静态内部类形式（适用于多线程）

    比起上面，我们可以用更简便的方式：利用类加载器的特性（线程安全，用到的时候单独加载和初始化）编写静态内部类的形式来创建懒汉式单例：

    ```java
    /*
    * 在内部类被加载和初始化时，才创建INSTANCE实例对象
    * 静态内部类不会自动随着外部类的加载和初始化而初始化，它是要单独去加载和初始化的
    * 因为是在内部类加载和初始化时创建的，因此是线程安全的
    * */
    public class Singleton{
        private Singleton(){}
        private static class Inner{
            private static final Singleton INSTANCE = new Singleton();
        }
        public static Singleton getInstance() {
            return Inner.INSTANCE;
        }
    }
    ```

    

## 类初始化过程（相关：《JVM虚拟机规范》中关于\<clinit\>和\<init\>方法的说明、invokespecial指令）

假设有父类：

```java
/*
* 父类的初始化<clinit>:
*   (1) j = method();
*   (2)父类的静态代码块
*
* 父类的实例化方法：
*   (1)super()（最前）
*   (2)i = test();
*   (3)父类的非静态代码块
*   (4)父类的无参构造（最后）
*
* 非静态方法前面其实有一个默认的对象this
* this在构造器（或<init>)中表示的是正在创建的对象，因为这里是在创建Son对象，所以test()执行的是子类重写的代码（面向对象多态）
* 因此这里i = test()执行的是子类重写的test()方法
* */
public class Father{
    private int i = test();
    private static int j = method();
    static {
        System.out.println("(1)");
    }
    Father(){
        System.out.println("(2)");
    }
    {
        System.out.println("(3)");
    }
    public int test() {
        System.out.println("(4)");
        return 1;
    }
    public static int method() {
        System.out.print("(5)");
        return 1;
    }
}
```

有子类：

```java
/*
* 子类的初始化<clinit>
*   (1) j = method()
*   (2)子类的静态代码块
*
* 先初始化父类:(5)(1)
* 再初始化子类:(10)(6)
*
* 子类的实例化方法：
*   （1）super()（最前） (9)(3)(2) ，这里为什么是子类的(9)而不是父类的(4)呢？那是因为发生了重写，<init>方法中this表示正在创建的对象，而它正是Son，因此执行的是Son的test()方法，所以输出(9)
*   （2）i = test(); (9)
*   （3）子类的非静态代码块 (8)
*   （4）子类的无参构造（最后） (7)
*
* 因为创建了两个Son对象，因此实例化方法<init>执行了两次，所以又会输出一次(9)(3)(2)(9)(8)(7)
* */
public class Son extends Father{
    private int i = test();
    private static int j = method();
    static {
        System.out.println("(6)");
    }
    Son(){
//        super(); 写或不写都在，在子类构造器中一定会调用父类的构造器
        System.out.println("(7)");
    }
    {
        System.out.println("(8)");
    }
    public int test() {
        System.out.println("(9)");
        return 1;
    }
    public static int method() {
        System.out.print("(10)");
        return 1;
    }

    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println();
        Son s2 = new Son();
    }
}
```

执行Son类后输出顺序：

```java
(5)(1)(10)(6)(9)(3)(2)(9)(8)(7)
(9)(3)(2)(9)(8)(7)
```

解释：

该问题包括一下三个考点：

- 类初始化过程

  - 一个类要创建实例需要先加载并初始化该类
    - main方法所在的类需要先加载和初始化（Son中有main，所以就算没new Son()，Son类也会被初始化）

  - 一个子类要初始化需要先初始化父类
  - 类初始化的时候都会执行\<clinit\>()方法，可以在编译后的.class文件中看到
    - \<clinit\>()方法由静态类变量显示赋值代码和静态代码块组成
    - 类变量显示赋值代码和静态代码块代码从上到下顺序执行，也就是谁先写谁先执行
    - \<clinit\>()方法只执行一次

- 实例初始化过程

  - 实例初始化就是执行\<init\>()方法
    - \<init\>()方法可能重载有多个，有几个构造器就有几个\<init\>方法
    - \<init\>()方法由非静态实例变量显示赋值代码和非静态代码块、对应构造器代码组成
    - 非静态实例变量显示赋值代码和非静态代码块代码从上到下顺序执行，而对应<font color="red">构造器的代码最后执行</font>
    - 每次创建实例对象，调用对应构造器，执行的就是对应的\<init\>方法
    - \<init\>方法的首行是super()或super(实参列表)，即对应父类的\<init\>方法（在子类构造器中不管有没有手动写都一定会有一句super()来调用父类的构造器）

- 方法的重写

  - 哪些方法不可以被重写
    - final方法
    - 静态方法
    - private等子类中不可见方法
  - 对象的多态性
    - 子类如果重写了父类的方法，通过子类对象调用的一定是子类重写过的代码
    - 非静态方法默认的调用对象是this
    - this对象在构造器或者说\<init\>方法中就是正在创建的对象

- override和overload的区别

  overload是重载的意思，它指我们可以定义若干名称相同的方法，通过传入不同的参数来选择合适的方法执行

  override是覆盖的意思，也就是重写，它是覆盖了一个方法并对其重写，以求达到不同的作用。重写表示子类中的方法可以与父类（或接口）中的某个方法的名称和参数完全相同

- invokespecial指令

  主要目的：得到对象存在堆中的地址，这样就可以用当前类、父类、父父类，即继承层的所有对象，把继承层的所有对象的数据和方法为自己当前对象使用。上面说的\<init\>方法的执行就要依据字节码中是否包含invokespecial指令

## 参数传递机制

有代码如下：

```java
public class Test{
    public static void main(String[] args) {
        int i = 1;
        String str = "hello";
        Integer num = 200;
        int[] arr = {1, 2, 3, 4, 5};
        MyData my = new MyData();

        change(i, str, num, arr, my);

        System.out.println("i = " + i);
        System.out.println("str = " + str);
        System.out.println("num = " + num);
        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("my.a = " + my.a);
    }
    public static void change(int j, String s, Integer n, int[] a, MyData m){
        j += 1;
        s += "world";
        n += 1;
        a[0] += 1;
        m.a += 1;
    }
}

class MyData {
    int a = 10;
}
```

输出结果：

```java
i = 1
str = "hello"
num = 200
arr = [2, 2, 3, 4, 5]
my.a = 11
```



考点：

- 方法的参数传递机制
  - 形参如果是<font color="red">基本数据类型</font>那就<font color="red">传递数据值</font>
  - 形参如果是<font color="red">引用数据类型</font>那就<font color="red">传递地址值</font>
    - 特殊的类型：String、包装类等对象不可变性
- String、包装类等对象的不可变性

![image-20210111160135073](Java补充.assets/image-20210111160135073.png)

注意，对于局部变量，他可能是实参也有可能是形参，因此栈中对于局部变量会有两种划分，一种为实参列表（上图蓝色框框）一种为形参列表（上图橙色框框）

过程分析：

- 首先在实参列表中：

  | 参数          | 保存形式 | 值   | 指向                | 说明                                                         |
  | ------------- | -------- | ---- | ------------------- | ------------------------------------------------------------ |
  | int i         | 值       | 1    |                     | 因为不是对象，所以直接保存数值                               |
  | String str    | 地址     |      | 常量池字符串”hello“ | 首先”hello“字符串是保存在常量池中的。因为str是对象，所以栈中保存引用地址并指向常量池中的字符串”hello“ |
  | Integer num   | 地址     |      | 堆中的200           | 首先200这个数值它不是保存在常量池中的而是保存到堆中。因为num是包装类，所以栈中保存引用地址并指向堆中的200 |
  | int[] arr     | 地址     |      | 堆中对应的数组      | 由于是数组，因此栈中保存引用地址并指向堆中数组               |
  | MyData myData | 地址     |      | 堆中的对象          | 由于是对象，因此栈中保存引用地址并指向堆中对象               |

  

- 然后在形参列表中：

  | 参数      | 保存形式 | 值   | 指向                | 说明                                        |
  | --------- | -------- | ---- | ------------------- | ------------------------------------------- |
  | int j     | 值       | 1    |                     | 因为不是对象，所以直接保存数值              |
  | String s  | 地址     |      | 常量池字符串”hello“ | 因为传参过来的是地址，所以指向同一个“hello” |
  | Integer n | 地址     |      | 堆中的200           | 同上                                        |
  | int[] a   | 地址     |      | 堆中对应的数组      | 同上                                        |
  | MyData m  | 地址     |      | 堆中的对象          | 同上                                        |



- 现在开始执行change()方法
  - j += 1，只是形参j加了1，跟实参i没关系
  - s += "world"，跟”hello“一样，常量池中会有”world“，然后执行字符串拼串变成”helloworld“，”helloworld“也在常量池中，然后形参s在栈中的地址改变并指向常量池”helloworld“，由于形参实参的地址都不一样了，因此形参s确实改变了，但对实参str没有半毛钱关系，所以实参str还是指向”hello“。这也是String对象的不可变性
  - n += 1，同String，它会在堆中新建一个201，并改变形参的地址指向201，而实参仍旧是指向堆中的200.这是包装类对象的不可变性
  - a[0] += 1，拿到的是arr的地址，直接操作arr数组本身，因此形参变了实参也得变
  - m.a += 1，同arr，拿到的是myData对象地址，直接操作对象本身，因此形参变了实参也得变

- change()方法执行完毕，所有形参全部销毁

## 就近原则、变量分类、非静态代码块的执行、方法的调用规则

代码如下：

```java
public class Exam5 {
    static int s; // 成员变量，类变量
    int i; // 成员变量，实例变量
    int j; // 成员变量，实例变量
    {
        int i = 1; // 非静态变量代码块中的局部变量i
        i++;
        j++;
        s++;
    }
    public void test(int j) { // 形参，局部变量j
        j++;
        i++;
        s++;
    }

    public static void main(String[] args) {
        Exam5 obj1 = new Exam5(); // 局部变量t1
        Exam5 obj2 = new Exam5(); // 局部变量t2
        obj1.test(10);
        obj1.test(20);
        obj2.test(30);
        System.out.println(obj1.i + ", " + obj1.j + ", " + obj1.s);
        System.out.println(obj2.i + ", " + obj2.j + ", " + obj2.s);
    }
}
```

输出结果：

```java
2,1,5
1,1,5
```

分析：

最开始会在栈中给主方法分配一块空间：

![image-20210120203851088](Java补充.assets/image-20210120203851088.png)

执行第一句：

```java
Exam5 obj1 = new Exam5();
```

首先方法区中会有一块区域：Exam5.class，静态变量s会被放进去并且初始化为0：

![image-20210120203235896](Java补充.assets/image-20210120203235896.png)

堆中会生成一个对象，并且会有实例变量i和j，都初始化为0：

![image-20210120203546025](Java补充.assets/image-20210120203546025.png)

栈中会压入一个变量叫obj1，并指向堆中的那个对象

<span style="color: red">现在还剩一个非静态代码块，非静态代码块中的变量是在实例化的过程中进行分配的，而实例化其实执行的就是\<init\>()方法，因此将该方法压入栈，并在方法内部初始化局部变量i：</span>

![image-20210120204833425](Java补充.assets/image-20210120204833425.png)

之后在{}内部遇到i++，上图的i就变成2，遇到j++，{}内部没有变量j，往{}外面找，找到了堆中的实例中的j，此时执行j++，j变成1，遇到s++，将方法区中的s变为1

此时实例obj1已经实例化完成，\<init\>()方法出栈，并且\<init\>()方法中的局部变量i也被销毁：

![image-20210120205311014](Java补充.assets/image-20210120205311014.png)



执行：

```java 
obj1.test(10)
```

test方法需要入栈，并且在方法内部有个局部变量j，将其初始化为10：

![image-20210120205423444](Java补充.assets/image-20210120205423444.png)

执行j++，test方法中的局部变量j变为11，执行i++，test方法中找不到局部变量i，往外找，找到堆中实例，并将实例中的成员变量i+1，i变为1，执行s++，方法区中的s变为2

此时方法执行完毕，test方法出栈，test方法中的变量j销毁

执行：

```java
obj1.test(20)
```

过程跟上一步差不多，结果就是test中j变为21，堆中成员变量i变为2，方法区中s变为3，然后test方法出栈，test中局部变量j销毁

执行：

```java
Exam5 obj2 = new Exam5();
```

跟上面执行过程一样，结果就是堆中新开辟一个实例，该实例成员变量i和j都初始化为0，\<init\>()方法先进栈，初始化局部变量i为1，遇到{}内i++，i变为2，遇到j++，实例成员变量j变1，遇到s++，方法区s+1变为4，\<init\>()方法执行完毕出栈，局部变量i销毁

执行：

```java
obj2.test(30)
```

跟上面一样，test方法先进栈并初始化局部变量j为30，j++，j变为31，i++，找不到局部变量i，去外面找，找到实例成员变量i，i变1，s++，方法区s变为5，test方法执行完毕，出栈，局部变量j销毁

所以最后第一个实例的i，j，s为：2，1，5；第二个实例的i，j，s为：1，1，5

考点：

- 就近原则

  从附近开始找，找不到再去父亲那边找，父亲那边如果还是找不到，那就再往上找。

  所以这种情况肯定是访问{}中的变量i：

  ```java
  public class Test {
      int i;
      {
          int i = 1;
          i++;
      }
  }
  ```

  但是如果是这种情况访问的就是{}外的成员变量i了：

  ```java
  public class Test {
      int i;
      {
          int i = 1;
          // i前面加了this.
          this.i++;
      }
  }
  ```

  

- 变量的分类

  - 成员变量：类变量、实例变量
  - 局部变量

  <span style="font-weight: bold">局部变量与成员变量的区别：</span>

  声明的位置：

  - 局部变量：方法体{}中，形参，代码块{}中
  - 成员变量：类中方法外
    - 类变量：有static修饰
    - 实例变量：没有static修饰

  修饰符:

  - 局部变量：final
  - 成员变量：public、protected、private、final、static、volatile、transient

  <font color="red">值存储的位置：</font>

  - 局部变量：栈（Stack，是指虚拟机栈。虚拟机栈用于存储局部变量表等。局部变量表存放了编译期可知长度的各种基本数据类型（boolean、byte、char、short、int、float、long、double）、对象引用（reference类型，它不等同于对象本身，是对象在堆内存的首地址）。方法执行完，自动释放。）
  - 实例变量：堆（Heap，此内存区域的唯一目的就是存放对象实例，几乎所有的对象实例都在这里分配内存。这一点在Java虚拟机规范中的描述是：所有的对象实例以及数组都要在堆上分配。）
  - 类变量：方法区（Method Area，用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。）

  作用域：

  - 局部变量：从声明处开始，到所属的}结束
  - 实例变量：在当前类中“this.”（有时this.可以缺省），在其他类中“对象名.”访问
  - 类变量：在当前类中“类名.”（有时类名.可以省略），在其他类中“类名.”或“对象名.”访问

  生命周期：

  - 局部变量：每一个线程，每一次调用执行都是新的生命周期
  - 实例变量：随着对象的创建而初始化，随着对象的被回收而消亡，每一个对象的实例变量是独立的
  - 类变量：随着类的初始化而初始化，随着类的卸载而消亡，该类的所有对象的类变量是共享的

  <font color="red">当局部变量与xx变量重名时，如何区分：</font>

  - 局部变量与实例变量重名
    - 在实例变量前面加“this.”

  - 局部变量与类变量重名
    - 在类变量前面加“类名.”

- 非静态代码块的执行：每次创建实例对象都会执行

- 方法的调用规则：调用一次执行一次



# Java对象内存大小计算

**JavaClass基本结构：**

| 名称     | 占用字节                    |
| -------- | --------------------------- |
| Class头  | 8字节                       |
| oop指针  | 4字节                       |
| 数据区域 | 不定                        |
| 对其补充 | 补充到整个大小为8字节的倍数 |

- Class头8个字节， 存储了比如这个实例目前的锁信息、目前属于的堆类型等

- oop指针，存储的是这个类的定义，比如Java反射可以拿到字段名称，方法名称这些值都是存储在这个指针所指向的定义中

- 数据区域，存放数据的区域，这里的结构区分主要是两种：数组和非数组。如果是数组，数据区域中还会包含这个数组的大小

**计算Java对象内存大小有三种方式：**

- AgentSizeOf : 使用jvm代理和Instrumentation

- UnsafeSizeOf : 使用unsafe

- ReflectionSizeOf : 通过反射出来Class的成员，通过成员类型进行计算

**实例数据：**

原生类型(primitive type)的内存占用如下：

| Primitive Type | Memory Required(bytes)                 |
| -------------- | -------------------------------------- |
| boolean        | 在数组中占1个字节，单独使用时占4个字节 |
| byte           | 1                                      |
| short          | 2                                      |
| char           | 2                                      |
| int            | 4                                      |
| float          | 4                                      |
| long           | 8                                      |
| double         | 8                                      |

reference类型在32位系统上每个占用4bytes, 在64位系统上每个占用8bytes。

关于boolean内存占用 https://www.cnblogs.com/wangtianze/p/6690665.html?utm_source=itdadao&utm_medium=referral

**对齐填充**

HotSpot的对齐方式为8字节对齐：

> （对象头 + 实例数据 + padding） % 8等于0且0 <= padding < 8

**指针压缩**

对象占用的内存大小收到VM参数UseCompressedOops的影响。32G内存以下的，默认开启对象指针压缩。

**1）对对象头的影响**

开启`（-XX:+UseCompressedOops）`对象头大小为12bytes（64位机器）。

```java
static class A {
	int a;
}
```

A对象占用内存情况：

关闭指针压缩： `16(对象头)+4(实例数据)=20不是8的倍数，因此需要对齐填充 16+4+4(padding)=24`

开启指针压缩： `12+4=16已经是8的倍数了，不需要再padding。`

**2） 对reference类型的影响**

64位机器上reference类型占用8个字节，开启指针压缩后占用4个字节。

```java
static class B2 {
    int b2a;
    Integer b2b;
}
```

B2对象占用内存情况：

关闭指针压缩： `16+4+8=28不是8的倍数，需要对齐填充 16+4+8+4(padding)=32`

开启指针压缩： `12+4+4=20不是8的倍数，需要对齐填充12+4+4+4(padding)=24`



**数组对象**

64位机器上，数组对象的对象头占用24个字节（8字节MarkWord+8字节类型指针+8字节数组长度），启用压缩之后占用16个字节（8字节MarkWord+4字节类型指针+4字节数组长度）。之所以比普通对象占用内存多是因为需要额外的对象头空间存储数组的长度。

先考虑下new Integer[0]占用的内存大小，数组长度为0，所以所占用的大小就是对象头的大小：

未开启压缩：24bytes

开启压缩后：16bytes

接着计算new Integer[1]，new Integer[2]，new Integer[3]和new Integer[4]就很容易了：

未开启压缩：

![image-20210716224521357](Java补充.assets/image-20210716224521357.png)

开启压缩：

![image-20210716224536329](Java补充.assets/image-20210716224536329.png)

拿new Integer[3]来具体解释下：

未开启压缩：`24（对象头）+ 8*3 = 48，不需要padding；`

开启压缩：`16（对象头）+ 4*3 = 28，需要对齐填充 28 + 4(padding) = 32，其他依次类推。`

自定义类的数组也是一样的，比如：

```java
static class B3 {
    int a;
    Integer b;
}
```

new B3[3]占用的内存大小：

未开启压缩：`24（对象头）+ 8*3 = 48`

开启压缩后：`16（对象头）+ 4*3 + 4(padding) = 32`



**复合对象**

计算复合对象占用内存的大小其实就是运用上面几条规则，只是麻烦点。

**1）对象本身的大小**

直接计算当前对象占用空间大小，包括当前类及超类的基本类型实例字段大小、引用类型实例字段引用大小、实例基本类型数组总占用空间、实例引用类型数组引用本身占用空间大小; 但是不包括超类继承下来的和当前类声明的实例引用字段的对象本身的大小、实例引用数组引用的对象本身的大小。

```java
static class B {
    int a;
    int b;
}
static class C {
    int ba;
    B[] as = new B[3];
    C() {
        for (int i = 0; i < as.length; i++) {
            as[i] = new B();
        }
    }
}
```

计算C对象的大小：

未开启压缩：`16（对象头）+ 4（ba）+ 8（as引用的大小）+ 4(padding) = 32`

开启压缩：`12（对象头）+ 4（ba）+4（as引用的大小）+ 4(padding) = 24`



**2)当前对象占用的空间总大小**

递归计算当前对象占用空间总大小，包括当前类和超类的实例字段大小以及实例字段引用对象大小。

递归计算复合对象占用的内存的时候需要注意的是：对齐填充是以每个对象为单位进行的，看下面这个图就很容易明白。

![image-20210716224730050](Java补充.assets/image-20210716224730050.png)

现在我们来手动计算下C对象占用的全部内存是多少，主要是三部分构成：C对象本身的大小+数组对象的大小+B对象的大小。

未开启压缩：

`(16 + 4 + 8+4(padding)) + (24+ 8*3) +(16+4+4)*3 = 152bytes`

开启压缩：

`(12 + 4 + 4 +4(padding)) + (16 + 4*3 +4(数组对象padding)) + (12+4+4+4（B对象padding)) *3= 128bytes`



**继承关系**

涉及继承关系的时候有一个最基本的规则：首先存放父类中的成员，接着才是子类中的成员, 父类也要按照 8 byte 规定。

```java
public static class D {
    byte d1;
}
public static class E extends D {
    byte e1;
}
```

计算E对象的大小：

未开启压缩：`16（对象头） + 父类(1(d1) + 7(padding)) + 1(e1) + 7(padding) = 32`

开启压缩：`12（对象头） + 父类(1(d1) + 7(padding)) + 1(e1) + 3(padding) = 24`

# Java内存模型（JMM）

参考：https://blog.csdn.net/zjcjava/article/details/78406330（JMM概述）

# GOF23种设计模式

参考：https://blog.csdn.net/pyy542718473/article/details/127248128（Java中23种设计模式）、https://zhuanlan.zhihu.com/p/128145128（快速记忆23种设计模式）

## 单例最佳方案（枚举实现）

参考：https://blog.csdn.net/LoveLion/article/details/110983839（单例模式之枚举实现）

#  序列化

## writeReplace()、writeObject()与readResolve()、readObject()

参考：https://www.codenong.com/1168348/（Java序列化：readObject()与readResolve()）、https://blog.csdn.net/Leon_cx/article/details/81517603（深入了解序列化writeObject、readObject、readResolve）、https://blog.csdn.net/qq_35410620/article/details/103007385（Java 序列化 机制writeReplace的方法探究）、https://blog.csdn.net/zero__007/article/details/109000460（利用Lambda实现通过getter/setter方法引用拿到属性名）

# 反射Reflection

```java
/// 首先 
Class c = ArrayList.class;

/// 判断变量是否为基础数据类型
c.isPrimitive();

/// 判断c是否是List类的父类
c.isAssignableFrom(List.class);

/// 判断c是否是List类的子类
c isinstanceof List;

/// 获取包含泛型类型的类型以及泛型真正的类型
Map<String,Integer> map = new HashMap<String,Integer>();
Field f = c.getDeclaredField("map");
// 获取包含泛型的类型
Type t = f.getGenericType();
/**
  * Type这个类里面没有任何的方法，所以需要调用子类的方法，那么大的类型转到小的类型，需要强转！
*/
ParameterizedType pt = (ParameterizedType)t;//强转到其子类
/**
 *  Type[] getActualTypeArguments()
    返回表示此类型实际类型参数的 Type对象的数组。
    Type getOwnerType()
    返回 Type 对象，表示此类型是其成员之一的类型。
    Type getRawType()
    返回 Type 对象，表示声明此类型的类或接口。
*/
t = pt.getRawType();//类型的类或接口
// 获取泛型真正的类型
Type[] ts = pt.getActualTypeArguments();
```

参考：https://www.jianshu.com/p/2315dda64ad2（Java Reflection(反射机制)详解）

## java.lang.reflect.Modifier

在查看反射相关的Class、Field、Constructor 等类时，看到他们都有这样一个方法：getModifiers()：返回此类或接口以整数编码的 Java 语言修饰符。如果需要知道返回的值所代表的意思，则需要用到 java.lang.reflect.Modifier 这个类，这个类提供了 static 方法和常量，可以对类和成员访问修饰符进行解码。

参考：https://blog.csdn.net/qq_39385118/article/details/83757536（Java基础 -Modifier类）

## 通过反射获取类中被指定注解标记的字段的值

参考：https://blog.csdn.net/BUGSLAYER_/article/details/107317110（JAVA工具类：获取类中被指定注解标记的字段值）

## java.beans.PropertyDescriptor

java.beans.PropertyDescriptor 类具有读取/写入对象属性值的方法，结合反射使用可以达到非常强大的效果

参考：https://blog.csdn.net/zhuqiuhui/article/details/78542049（Java中PropertyDescriptor用法）、https://blog.csdn.net/BUGSLAYER_/article/details/107317110（JAVA工具类：获取类中被指定注解标记的字段值）

## getCallerClass获取调用者的类

参考：https://blog.csdn.net/freeideas/article/details/43528571（Reflection的getCallerClass的使用）

# 字节码生成库

## ASM字节码操纵

参考：https://blog.csdn.net/zhuoxiuwu/article/details/78619645（Java ASM 技术简介）、https://blog.csdn.net/prettyboy2ge/article/details/116838199（ASM）

## javaagent（字节码插桩、attach、bTrace、Arthas、aspectjweaver）

参考：https://www.cnblogs.com/rickiyang/p/11368932.html（javaagent指南）、https://blog.csdn.net/qinhaotong/article/details/100693414（Java成神之路——javaAgent（插桩，attach））

## javassist（比asm直接操作jvm指令的方式更便捷）

参考：

https://juejin.cn/post/6952765170544279566（Java字节码编程之非常好用的javassist）、https://www.cnblogs.com/rickiyang/p/11336268.html（javassist使用全解析）、https://www.cnblogs.com/nice0e3/p/13811335.html（Java安全之Javassist动态编程）



官方文档：

http://www.javassist.org/tutorial/tutorial.html（Getting Started with Javassist）、http://www.javassist.org/html/index.html（Javassist）

`$0`、`$args`、`$class`等符号说明参考：http://www.javassist.org/tutorial/tutorial2.html



## 无中生有的类对象创建

使用字节码生成库（如 ASM、CGLIB、Javassist）来动态生成类

不同的字节码生成库在指定动态类的父类时有不同的方式。使用 ASM 时需要手动管理字节码；CGLIB 更简便，适合创建代理；而 ByteBuddy 提供更现代的 fluent API

例如（以下为chatgpt回答）：

Javassist：

```java
import javassist.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("MyDynamicClass");

        CtMethod m1 = CtNewMethod.make("public void display() { System.out.println(\"Hello from MyDynamicClass!\"); }", cc);
        cc.addMethod(m1);

        Class<?> dynamicClass = cc.toClass();
        Object instance = dynamicClass.getDeclaredConstructor().newInstance();
        dynamicClass.getMethod("display").invoke(instance);
    }
}
```

ASM：

```java
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class ASMDemo {
    public static void main(String[] args) throws Exception {
        ClassWriter cw = new ClassWriter(0);
        
        // 定义一个类，名称为 MyDynamicClass，父类为 ParentClass
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "MyDynamicClass", null, "ParentClass", null);
        
        // 你可以在这里添加字段和方法

        cw.visitEnd();

        byte[] byteCode = cw.toByteArray();

        // 你可以使用反射加载这个类
        MyDynamicClassLoader classLoader = new MyDynamicClassLoader();
        Class<?> dynamicClass = classLoader.defineClass("MyDynamicClass", byteCode);
        
        Object instance = dynamicClass.getDeclaredConstructor().newInstance();
        System.out.println(instance.getClass().getSuperclass().getName()); // 输出 ParentClass
    }
    
    static class MyDynamicClassLoader extends ClassLoader {
        public Class<?> defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }
}
```

CGLIB：

```java
import net.sf.cglib.proxy.Enhancer;

class ParentClass {
    public void greet() {
        System.out.println("Hello from ParentClass!");
    }
}

public class CGlibDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ParentClass.class); // 设置父类
        enhancer.setCallback((org.springframework.cglib.proxy.MethodInterceptor) (obj, method, args1, proxy) -> {
            System.out.println("Before method call");
            return proxy.invokeSuper(obj, args1);
        });

        ParentClass dynamicInstance = (ParentClass) enhancer.create();
        dynamicInstance.greet(); // 调用父类方法
    }
}
```

ByteBuddy：

```java
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

class ParentClass {
    public void sayHello() {
        System.out.println("Hello from ParentClass!");
    }
}

class MyInterceptor {
    public static void intercept(@SuperCall Callable<?> callable) throws Exception {
        System.out.println("Before method call");
        callable.call();
    }
}

public class ByteBuddyDemo {
    public static void main(String[] args) throws Exception {
        Class<? extends ParentClass> dynamicType = new ByteBuddy()
                .subclass(ParentClass.class)
                .method(named("sayHello"))
                .intercept(MethodDelegation.to(MyInterceptor.class))
                .make()
                .load(ByteBuddyDemo.class.getClassLoader())
                .getLoaded();

        ParentClass instance = dynamicType.getDeclaredConstructor().newInstance();
        instance.sayHello(); // 调用被拦截的方法
    }
}
```



# 函数式接口（Function、Consumer、Supplier、Predicate）

参考：https://blog.csdn.net/m0_51666430/article/details/125355782（Java四大函数式接口（Function、Consumer、Supplier、Predicate））

关于Supplier：

```java
public class CachedStoreArk<E> extends AbstractStoreArk<E> {
    private final StoreArk<E> inner;    

	public CachedStoreArk() {
        // 这里的CommonStoreArk::new就是一个Supplier
        this(CommonStoreArk::new);
    }

    private CachedStoreArk(Supplier<StoreArk<E>> sup) {
        this.inner = sup.get();
    }
}
```

# lambda表达式实现List转Map

- partitionBy分组
- groupingBy分组
- toMap自定义`<key, value>`

参考：https://blog.csdn.net/qq_23365135/article/details/123769033（Java lambda表达式实现List转Map）

# 正则表达式Pattern

参考：https://blog.csdn.net/woniu317/article/details/52186694（Pattern用法(正则表达式)）

# 表达式求值引擎

## Aviator

参考：

https://blog.csdn.net/weixin_42039228/article/details/141246508（Google Aviator: 高性能Java表达式求值引擎）

# 多线程与线程安全

## 线程池中多余的线程如何被回收？

参考：https://zhuanlan.zhihu.com/p/269145872（面试官：线程池中多余的线程是如何回收的？）、https://blog.csdn.net/xiewenfeng520/article/details/107013665（线程池的线程复用原理）、https://blog.csdn.net/sinat_36553913/article/details/114762157（Java 基础 - 线程池是如何回收空闲线程的 ？）

## ThreadFactory

参考：https://cloud.tencent.com/developer/article/1633458（Java 的线程工厂 ThreadFactory原理及源码详解）

## 原子变量

### AtomicReferenceFieldUpdater

**作用**

```
这是一个基于反射的工具类，它能对指定类的【指定的volatile引用字段】进行【原子更新】。(注意这个字段不能是private的) 
简单来讲：就是对某个类中，被volatile修饰的字段进行原子更新。 
```

**用法**

此类接收三个参数：

- 字段所在的类
- 字段的类型
- 更新字段的内容

示例：

```java
class Person {
	volatile String name="老刘";  
}
class Test{
    public static void main(String[] args){
        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(Person.class, String.class, "name");  
        Person person = new Person()；
        updater.compareAndSet(person, person.name,"老王") ;  
        System.out.println(Person.name); 
    }
}
```

## 公平锁和非公平锁

非公平锁在调用 lock 后，首先就会调用 CAS 进行一次抢锁，如果这个时候恰巧锁没有被占用，那么直接就获取到锁返回了。

非公平锁在 CAS 失败后，和公平锁一样都会进入到 tryAcquire 方法，在 tryAcquire 方法中，如果发现锁这个时候被释放了（state == 0），非公平锁会直接 CAS 抢锁，但是公平锁会判断等待队列是否有线程处于等待状态，如果有则不去抢锁，乖乖排到后面。

## 自旋锁

**自旋锁（spinlock）**：是指当一个线程在获取锁的时候，如果锁已经被其它线程获取，那么该线程将循环等待，然后不断的判断锁是否能够被成功获取，直到获取到锁才会退出循环。

获取锁的线程一直处于活跃状态，但是并没有执行任何有效的任务，使用这种锁会造成busy\-waiting。

示例：

```java
public class SpinLock {
    private AtomicReference cas = new AtomicReference();
    
    public void lock() {
        Thread current = Thread.currentThread();
        // 利用CAS
        while (!cas.compareAndSet(null, current)) {
        	// DO nothing
        }
    }

	public void unlock() {
        Thread current = Thread.currentThread();
        cas.compareAndSet(current, null);
    }
}
```



**自旋锁存在的问题：**

- 如果某个线程持有锁的时间过长，就会导致其它等待获取锁的线程进入循环等待，消耗CPU。使用不当会造成CPU使用率极高。

- 上面Java实现的自旋锁不是公平的，即无法满足等待时间最长的线程优先获取锁。不公平的锁就会存在“线程饥饿”问题。

**自旋锁的优点：**

​	自旋锁不会使线程状态发生切换，一直处于用户态，即线程一直都是active的；不会使线程进入阻塞状态，减少了不必要的上下文切换，执行速度快。

​	非自旋锁在获取不到锁的时候会进入阻塞状态，从而进入内核态，当获取到锁的时候需要从内核态恢复，需要线程上下文切换。 （线程被阻塞后便进入内核（Linux）调度状态，这个会导致系统在用户态与内核态之间来回切换，严重影响锁的性能）。

### 可重入的自旋锁和不可重入的自旋锁

上面那段Java代码，仔细分析一下就可以看出，它是不支持重入的，即当一个线程第一次已经获取到了该锁，在锁释放之前又一次重新获取该锁，第二次就不能成功获取到。由于不满足CAS，所以第二次获取会进入while循环等待，而如果是可重入锁，第二次也是应该能够成功获取到的。

而且，即使第二次能够成功获取，那么当第一次释放锁的时候，第二次获取到的锁也会被释放，而这是不合理的。

为了实现可重入锁，我们需要引入一个计数器，用来记录获取锁的线程数。

```java
public class ReentrantSpinLock {
    private AtomicReference cas = new AtomicReference();
    private int count;

    public void lock() {
        Thread current = Thread.currentThread();
        if (current == cas.get()) { // 如果当前线程已经获取到了锁，线程数增加一，然后返回
            count++;
            return;
        }
        // 如果没获取到锁，则通过CAS自旋
        while (!cas.compareAndSet(null, current)) {
        	// DO nothing
        }
    }

    public void unlock() {
    	Thread cur = Thread.currentThread();
    	if (cur == cas.get()) {
    		if (count > 0) {// 如果大于0，表示当前线程多次获取了该锁，释放锁通过count减一来模拟
    			count--;
    		} else {// 如果count==0，可以将锁释放，这样就能保证获取锁的次数与释放锁的次数是一致的了。
    			cas.compareAndSet(cur, null);
    		}
    	}
	}
}
```

### 自旋锁的其他变种

**1. TicketLock**

TicketLock主要解决的是公平性的问题。

思路：每当有线程获取锁的时候，就给该线程分配一个递增的id，我们称之为排队号，同时，锁对应一个服务号，每当有线程释放锁，服务号就会递增，此时如果服务号与某个线程排队号一致，那么该线程就获得锁，由于排队号是递增的，所以就保证了最先请求获取锁的线程可以最先获取到锁，就实现了公平性。

可以想象成银行办理业务排队，排队的每一个顾客都代表一个需要请求锁的线程，而银行服务窗口表示锁，每当有窗口服务完成就把自己的服务号加一，此时在排队的所有顾客中，只有自己的排队号与服务号一致的才可以得到服务。

实现代码：

```java
public class TicketLock {
    /**
    * 服务号
    */
    private AtomicInteger serviceNum = new AtomicInteger();

    /**
    * 排队号
    */
    private AtomicInteger ticketNum = new AtomicInteger();

    /**
    * lock:获取锁，如果获取成功，返回当前线程的排队号，获取排队号用于释放锁.
    *
    * @return
    */
    public int lock() {
        int currentTicketNum = ticketNum.incrementAndGet();
        while (currentTicketNum != serviceNum.get()) {
            // Do nothing
        }
        return currentTicketNum;
    }

    /**
    * unlock:释放锁，传入当前持有锁的线程的排队号
    *
    * @param ticketnum
    */
    public void unlock(int ticketnum) {
        serviceNum.compareAndSet(ticketnum, ticketnum + 1);
    }
}
```

上面的实现方式是，线程获取锁之后，将它的排队号返回，等该线程释放锁的时候，需要将该排队号传入。但这样是有风险的，因为这个排队号是可以被修改的，一旦排队号被不小心修改了，那么锁将不能被正确释放。一种更好的实现方式如下：

```java
public class TicketLockV2 {
    /**
    * 服务号
    */
    private AtomicInteger serviceNum = new AtomicInteger();
    /**
    * 排队号
    */
    private AtomicInteger ticketNum = new AtomicInteger();
    /**
    * 新增一个ThreadLocal，用于存储每个线程的排队号
    */
    private ThreadLocal ticketNumHolder = new ThreadLocal();

    public void lock() {
        int currentTicketNum = ticketNum.incrementAndGet();
        // 获取锁的时候，将当前线程的排队号保存起来
        ticketNumHolder.set(currentTicketNum);
        while (currentTicketNum != serviceNum.get()) {
            // Do nothing
        }
    }

    public void unlock() {
        // 释放锁，从ThreadLocal中获取当前线程的排队号
        Integer currentTickNum = ticketNumHolder.get();
        serviceNum.compareAndSet(currentTickNum, currentTickNum + 1);
    }
}
```

上面的实现方式是将每个线程的排队号放到了ThreadLocal中。

**TicketLock存在的问题**

多处理器系统上，每个进程/线程占用的处理器都在读写同一个变量serviceNum ，每次读写操作都必须在多个处理器缓存之间进行缓存同步，这会导致繁重的系统总线和内存的流量，大大降低系统整体的性能。

下面介绍的MCSLock和CLHLock就是解决这个问题的。

**2. CLHLock**

CLH锁是一种基于链表的可扩展、高性能、公平的自旋锁，申请线程只在本地变量上自旋，它不断轮询前驱的状态，如果发现前驱释放了锁就结束自旋，获得锁。

实现代码如下：

```java
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CLHLock {
    /**
    * 定义一个节点，默认的lock状态为true
    */
    public static class CLHNode {
        private volatile boolean isLocked = true;
    }
    /**
    * 尾部节点,只用一个节点即可
    */
    private volatile CLHNode tail;
    private static final ThreadLocal LOCAL = new ThreadLocal();
    private static final AtomicReferenceFieldUpdater UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLock.class, CLHNode.class, "tail");

    public void lock() {
    	// 新建节点并将节点与当前线程保存起来
    	CLHNode node = new CLHNode();
    	LOCAL.set(node);
    	// 将新建的节点设置为尾部节点，并返回旧的节点（原子操作），这里旧的节点实际上就是当前节点的前驱节点
    	CLHNode preNode = UPDATER.getAndSet(this, node);
        if (preNode != null) {
            // 前驱节点不为null表示当锁被其他线程占用，通过不断轮询判断前驱节点的锁标志位等待前驱节点释放锁
            while (preNode.isLocked) {
            }
            preNode = null;
            LOCAL.set(node);
        }
    	// 如果不存在前驱节点，表示该锁没有被其他线程占用，则当前线程获得锁
    }

    public void unlock() {
        // 获取当前线程对应的节点
        CLHNode node = LOCAL.get();
        // 如果tail节点等于node，则将tail节点更新为null，同时将node的lock状态职位false，表示当前线程释放了锁
        if (!UPDATER.compareAndSet(this, node, null)) {
        	node.isLocked = false;
        }
        node = null;
    }
}
```

**3. MCSLock**

MCSLock则是对本地变量的节点进行循环。

```java
public class MCSLock {
    /**
    * 节点，记录当前节点的锁状态以及后驱节点
    */
    public static class MCSNode {
        volatile MCSNode next;
        volatile boolean isLocked = true;
    }
	private static final ThreadLocal NODE = new ThreadLocal();

    // 队列
    @SuppressWarnings("unused")
    private volatile MCSNode queue;
    // queue更新器
    private static final AtomicReferenceFieldUpdater UPDATER = AtomicReferenceFieldUpdater.newUpdater(MCSLock.class, MCSNode.class, "queue");
    
    public void lock() {
        // 创建节点并保存到ThreadLocal中
        MCSNode currentNode = new MCSNode();
        NODE.set(currentNode);
        // 将queue设置为当前节点，并且返回之前的节点
        MCSNode preNode = UPDATER.getAndSet(this, currentNode);
        if (preNode != null) {
            // 如果之前节点不为null，表示锁已经被其他线程持有
            preNode.next = currentNode;
            // 循环判断，直到当前节点的锁标志位为false
            while (currentNode.isLocked) {
            }
        }
    }

    public void unlock() {
    	MCSNode currentNode = NODE.get();
    	// next为null表示没有正在等待获取锁的线程
    	if (currentNode.next == null) {
    		// 更新状态并设置queue为null
    		if (UPDATER.compareAndSet(this, currentNode, null)) {
    			// 如果成功了，表示queue==currentNode,即当前节点后面没有节点了
    			return;
    		} else {
    			// 如果不成功，表示queue!=currentNode,即当前节点后面多了一个节点，表示有线程在等待
    			// 如果当前节点的后续节点为null，则需要等待其不为null（参考加锁方法）
    			while (currentNode.next == null) {
    			}
    		}
    	} else {
    		// 如果不为null，表示有线程在等待获取锁，此时将等待线程对应的节点锁状态更新为false，同时将当前线程的后继节点设为null
    		currentNode.next.isLocked = false;
    		currentNode.next = null;
    	}
    }
}
```

**CLHLock 和 MCSLock**都是基于链表，不同的是CLHLock是基于隐式链表，没有真正的后续节点属性，MCSLock是显示链表，有一个指向后续节点的属性。

将获取锁的线程状态借助节点(node)保存,每个线程都有一份独立的节点，这样就解决了TicketLock多处理器缓存同步的问题。

### 自旋锁与互斥锁

自旋锁与互斥锁都是为了实现保护资源共享的机制。

无论是自旋锁还是互斥锁，在任意时刻，都最多只能有一个保持者。

获取互斥锁的线程，如果锁已经被占用，则该线程将进入睡眠状态；获取自旋锁的线程则不会睡眠，而是一直循环等待锁释放。

### 总结

**自旋锁**：线程获取锁的时候，如果锁被其他线程持有，则当前线程将循环等待，直到获取到锁。

自旋锁等待期间，线程的状态不会改变，线程一直是用户态并且是活动的(active)。

自旋锁如果持有锁的时间太长，则会导致其它等待获取锁的线程耗尽CPU。

自旋锁本身无法保证公平性，同时也无法保证可重入性。

基于自旋锁，可以实现具备公平性和可重入性质的锁。

**TicketLock：**采用类似银行排号叫好的方式实现自旋锁的公平性，但是由于不停的读取serviceNum，每次读写操作都必须在多个处理器缓存之间进行缓存同步，这会导致繁重的系统总线和内存的流量，大大降低系统整体的性能。

**CLHLock和MCSLock**通过链表的方式避免了减少了处理器缓存同步，极大的提高了性能，区别在于CLHLock是通过轮询其前驱节点的状态，而MCS则是查看当前节点的锁状态。

CLHLock在NUMA架构下使用会存在问题。在没有cache的NUMA系统架构中，由于CLHLock是在当前节点的前一个节点上自旋，NUMA架构中处理器访问本地内存的速度高于通过网络访问其他节点的内存，所以CLHLock在NUMA架构上不是最优的自旋锁。

## ReadLock和WriteLock(ReentrantReadWriteLock读写锁)

参考：https://blog.csdn.net/cdw8131197/article/details/52601559（ReadLock和WriteLock(读写锁)）

### 读锁的锁升级与锁降级

锁降级：从写锁变成读锁；锁升级：从读锁变成写锁。

参考：https://blog.csdn.net/qq_29842929/article/details/81188824（Java并发-ReentrantReadWriteLock锁降级/锁升级）、https://blog.csdn.net/qq_38737992/article/details/92796403（Java 读写锁 之 锁降级）、https://blog.csdn.net/aitangyong/article/details/38315885（JDK读写锁ReadWriteLock的升级和降级问题）

## StampedLock

StampedLock是ReentrantReadWriteLock锁的增强优化版本，可以有效防止ReentrantReadWriteLock锁多线程读的时候发生写阻塞的情况（线程饥饿问题）。StampedLock在读的时候是允许一个写线程的。

参考：https://www.jianshu.com/p/7bc040558980（Java并发编程——StampedLock）

## Lock + Condition

参考：https://zhuanlan.zhihu.com/p/333969353（并发工具（锁）：深入Lock+Condition）

## AQS(AbstractQueuedSynchronizer)

AQS是并发容器J.U.C（java.util.concurrent）下locks包内的一个类。它实现了一个**FIFO**(FirstIn、FisrtOut先进先出)的队列。底层实现的数据结构是一个**双向链表**。

参考：https://blog.csdn.net/striveb/article/details/86761900（什么是AQS及其原理）、https://www.cnblogs.com/waterystone/p/4920797.html（Java并发之AQS详解）、https://www.cnblogs.com/chengxiao/archive/2017/07/24/7141160.html（Java并发包基石-AQS详解）

## synchronized关键字

参考：https://blog.csdn.net/javazejian/article/details/72828483（深入理解Java并发之synchronized实现原理，包含JVM对synchronized的优化（偏向锁、轻量锁、自旋锁、锁消除）等）、https://blog.csdn.net/weixin_42460087/article/details/126474481（Synchronized的底层实现原理（原理解析，面试必备））

## ReentrantLock(可重入锁)

参考：https://blog.csdn.net/zxd8080666/article/details/83214089（Synchronized与ReentrantLock区别总结（简单粗暴，一目了然））、http://www.blogjava.net/zhanglongsr/articles/356782.html（ReentrantLock源码之一lock方法解析(锁的获取)）、https://blog.csdn.net/yanyan19880509/article/details/52345422（轻松学习java可重入锁(ReentrantLock)的实现原理）、https://blog.csdn.net/aitangyong/article/details/38311287（可重入锁的获取和释放的注意事项）

## CyclicBarrier

循环栅栏。它的作用就是会让所有线程都等待完成后才会继续下一步行动。举个例子：5个人（5个线程）去餐厅吃饭，规定只有五个人全部到齐了才能进去。

示例：

```java
public class CyclicBarrierDemo {

    static class TaskThread extends Thread {
        CyclicBarrier barrier;
        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + " 到达栅栏 A");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 A");
                
                Thread.sleep(2000);
                System.out.println(getName() + " 到达栅栏 B");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 B");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 完成最后任务");
            }
        });
        for(int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
    }
}
```

输出：

```
Thread-1 到达栅栏 A
Thread-3 到达栅栏 A
Thread-0 到达栅栏 A
Thread-4 到达栅栏 A
Thread-2 到达栅栏 A
Thread-2 完成最后任务
Thread-2 冲破栅栏 A
Thread-1 冲破栅栏 A
Thread-3 冲破栅栏 A
Thread-4 冲破栅栏 A
Thread-0 冲破栅栏 A
Thread-4 到达栅栏 B
Thread-0 到达栅栏 B
Thread-3 到达栅栏 B
Thread-2 到达栅栏 B
Thread-1 到达栅栏 B
Thread-1 完成最后任务
Thread-1 冲破栅栏 B
Thread-0 冲破栅栏 B
Thread-4 冲破栅栏 B
Thread-2 冲破栅栏 B
Thread-3 冲破栅栏 B
```

**CyclicBarrier 使用场景**

可以用于多线程计算数据，最后合并计算结果的场景。

**CyclicBarrier 与 CountDownLatch 区别**

- CountDownLatch 是一次性的，CyclicBarrier 是可循环利用的
- CountDownLatch 参与的线程的职责是不一样的，有的在倒计时，有的在等待倒计时结束。CyclicBarrier 参与的线程职责是一样的。

## Semaphore

Semaphore(信号量)：是一种计数器，用来保护一个或者多个共享资源的访问。如果线程要访问一个资源就必须先获得信号量。如果信号量内部计数器大于0，信号量减1，然后允许共享这个资源；否则，如果信号量的计数器等于0，信号量将会把线程置入休眠直至计数器大于0。当信号量使用完时，必须释放。

构造方法：

```java
/**
 * @permits 初始化计数器
 * @fair 是否公平
 */
Semaphore(int permits, boolean fair)

/**
 * @permits 初始化计数器
 */
Semaphore(int permits)
```

使用示例：

```java
Semaphore semaphore = new Semaphore(10, true);
semaphore.acquire();
// do something here
semaphore.release();
```

## LinkedBlockingQueue与ConcurrentLinkedQueue

参考：https://blog.csdn.net/lzxlfly/article/details/86710382（LinkedBlockingQueue与ConcurrentLinkedQueue的区别）、https://www.cnblogs.com/linjiqin/archive/2013/05/30/3108188.html（并发队列ConcurrentLinkedQueue和阻塞队列LinkedBlockingQueue用法）、https://blog.csdn.net/A1023824314/article/details/52263932（java挑战高并发（14）: LinkedBlockingQueue和ConcurrentLinkedQueue的区别及用法）、https://www.jianshu.com/p/1a49293294aa（LinkedBlockingQueue 和 ConcurrentLinkedQueue的用法及区别）

## Fork/Join与RecursiveTask和RecursiveAction

参考：https://blog.csdn.net/weixin_41404773/article/details/80733324（RecursiveTask和RecursiveAction的使用 以及java 8 并行流和顺序流）

### CountedCompleter

参考：https://segmentfault.com/a/1190000019555458（ForkJoin框架之CountedCompleter,工作线程及并行流）、https://blog.csdn.net/huitoukest/article/details/102673219（java进阶笔记线程与并发之CountedCompleter）

## ThreadLocal

### TransmittableThreadLocal

参考：https://www.cnblogs.com/intotw/p/14740215.html（TransmittableThreadLocal解决线程池变量传递以及原理解析）、https://blog.csdn.net/weixin_42260270/article/details/117699535（TransmittableThreadLocal正确使用方式）、https://zhuanlan.zhihu.com/p/146124826（全链路追踪必备组件之 TransmittableThreadLocal 详解）

## ThreadLocalRandom

參考：https://www.jianshu.com/p/89dfe990295c（多线程下ThreadLocalRandom用法）

## 线程的join()与yield()

join()表示线程要介入，看源码就会发现它就是一个简单的wait()；

yield()表示线程要让出cpu计算资源；

参考：https://blog.csdn.net/qq_18505715/article/details/79795728（Java join()方法的使用）

join()在netty中的使用案例：

```java
while (workerThread.isAlive()) {
    workerThread.interrupt();
    try {
        // 给工作线程100ms的时间等待他处理完后事
        // 如果没有这个join，则会在一小段时间内一直while循环去interrupt工作线程
        workerThread.join(100);
    } catch (InterruptedException ignored) {
        interrupted = true;
    }
}
```

## 乐观锁

参考：

https://blog.csdn.net/strawqqhat/article/details/88743519（乐观锁常见的两种实现方式和适用场景）

https://blog.csdn.net/sunwenhao_2017/article/details/81565783（乐观锁以及乐观锁的实现）

https://blog.csdn.net/weixin_43811057/article/details/130816043（基于数据库实现乐观锁）

## 锁升级、锁降级

参考：https://blog.csdn.net/sspudding/article/details/89563462（锁的升级过程）、https://zhuanlan.zhihu.com/p/139793053（锁升级 锁降级）

# 高并发

参考：

https://blog.csdn.net/m0_62051288/article/details/126624791（阿里巴巴高并发架构到底多牛逼？是如何抗住淘宝双11亿级并发量？）

https://www.bmabk.com/index.php/post/174313.html（面试官：如何解决超卖问题？）

## 高并发应对方案

参考：

https://blog.csdn.net/zhu719224032/article/details/145918265（高并发生存指南：从“系统崩溃”到“扛住双11”的骚操作）

## 防止缓存穿透的布隆过滤器

参考：

https://blog.csdn.net/Zyw907155124/article/details/135556557（布隆过滤器深度实战：详解原理、场景与SpringBoot+Redis高性能实现）

https://blog.csdn.net/qq_41125219/article/details/119982158（布隆(Bloom Filter)过滤器——全面讲解，建议收藏）

# Spliterator

Spliterator是Java 8中加入的一个新接口；这个名字代表“可拆分迭代器”（splitable iterator）。和Iterator一样，Spliterator也用于遍历数据源中的元素，但它是为了并行执行而设计的。Java 8已经为集合框架中包含的所有数据结构提供了一个默认的Spliterator实现。集合实现了Spliterator接口，接口提供了一个spliterator方法。

参考：https://blog.csdn.net/sl1992/article/details/100149187（Java8中Spliterator详解）

# 变量句柄-VarHandle

VarHandle 的出现替代了 `java.util.concurrent.atomic` 和 `sun.misc.Unsafe` 的部分操作

参考：https://blog.csdn.net/sench_z/article/details/79793741（Java 9 变量句柄-VarHandle）

# Netty

参考：

- https://www.jianshu.com/p/b9f3f6a16911（Netty入门教程1——认识Netty）、https://www.jianshu.com/p/ed0177a9b2e3（Netty入门教程2——动手搭建HttpServer）、https://www.jianshu.com/p/fd815bd437cd（Netty入门教程3——Decoder和Encoder）、https://www.jianshu.com/p/9d89b2299ce4（Netty入门教程4——实现长连接）

- https://blog.csdn.net/haoyuyang/article/details/53243785（Netty基本使用介绍）
- https://blog.csdn.net/lmdsoft/article/details/105618052（Netty介绍）

## 时间轮调度算法

参考：https://zhuanlan.zhihu.com/p/339600116（Netty时间轮调度算法原理分析，我不相信这样你还看不懂！）

# Dubbo

## 接口代理详解（@DubboReference和@Reference）

参考：https://blog.csdn.net/qq_18300037/article/details/121612458（【dubbo学习系列】dubbo消费端的代理生成详解(@DubboReference和@Reference)）

# Caffeine缓存库

参考：

https://blog.csdn.net/dgh112233/article/details/118915259（Caffeine本地缓存详解（一篇就明白））、https://blog.csdn.net/Listening_Wind/article/details/110085228（SpringBoot 集成 Caffeine（咖啡因）最优秀的本地缓存）、https://blog.csdn.net/qq_42191033/article/details/108961339（Caffeine cache实现本地缓存（简单又清楚））

https://blog.csdn.net/l_dongyang/article/details/108326755（Caffeine入门使用）、https://blog.csdn.net/l_dongyang/article/details/123294062（Caffeine基础源码解析）、https://blog.csdn.net/l_dongyang/article/details/123461686（Caffeine 驱逐算法）

# 关键字

## strictfp

<span style="font-weight:bold">strictfp，即strict float point（精确浮点）</span>

strictfp 关键字可应用于类、接口或方法。使用 strictfp 关键字声明一个方法时，该方法中所有的float和double表达式都严格遵守FP-strict的限制,符合IEEE-754规范。当对一个类或接口 使用 strictfp 关键字时，该类中的所有代码，包括嵌套类型中的初始设定值和代码，都将严格地进行计算。严格约束意味着所有表达式的结果都必须是 IEEE 754 算法对操作数预期的结果，以单精度和双精度格式表示。
　　如果你想让你的浮点运算更加精确，而且不会因为不同的硬件平台所执行的结果不一致的话，可以用关键字strictfp.

例子如下：

不使用strictfp：

```java
public class Test {
    private static double aDouble = 0.0555500333333212d;
    private static float aFloat = 0.0333000000222f;
    public static void main(String[] args) {
        double cDouble = aDouble / aFloat;
        System.out.println("aDouble: " + aDouble);
        System.out.println("aFloat: " + aFloat);
        System.out.println("cDouble: " + cDouble);
    }
}
```

使用strictfp：

```java
public strictfp class Test {
    private static double aDouble = 0.0555500333333212d;
    private static float aFloat = 0.0333000000222f;
    public static void main(String[] args) {
        double cDouble = aDouble / aFloat;
        System.out.println("aDouble: " + aDouble);
        System.out.println("aFloat: " + aFloat);
        System.out.println("cDouble: " + cDouble);
    }
}
```

结果：

不使用strictfp：

```java
aDouble:0.0555500333333212
aFloat:3.33000016E11
cDouble:1.6681690896577544E-13
```



使用strictfp：

```java
aDouble:0.0555500333333212
aFloat:0.0333
cDouble:1.668169110346482
```

# `类名.this`的用法

一般用于内部类

参考：https://blog.csdn.net/li_xunhuan/article/details/98657521（Java-“this”和“类名.this”以及“类名.class”的区分和详解）

# MapStruct

参考：https://blog.csdn.net/qq_40194399/article/details/110162124（MapStruct最详细的使用教程，别再用`BeanUtils.copyProperties()`）、https://blog.csdn.net/sunboylife/article/details/115706803（mapstruct 高级用法自定义转换规则）

# 权限控制

| 修饰词     | 本类 | 同一个包的类 | 继承类 | 其他类 |
| ---------- | ---- | ------------ | ------ | ------ |
| private    | 1    | 0            | 0      | 0      |
| 无（默认） | 1    | 1            | 0      | 0      |
| protected  | 1    | 1            | 1      | 0      |
| public     | 1    | 1            | 1      | 1      |

# 注解

## @PostConstruct与@PreDestory

从Java EE 5规范开始，Servlet中增加了两个影响Servlet生命周期的注解（Annotation）：

@PostConstruct和@PreDestory

这两个注解被用来修饰一个非静态的void()方法，而且这个方法不能有抛出异常声明。写法有如下两种方式：

```java
@PostConstruct
public void someMethod(){}
```

或者

```java
public @PostConstruct void someMethod(){}
```

被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。@PostConstruct修饰的方法在构造函数之后执行，在init()方法之前执行。@PreDestory修饰的方法在destroy()方法执行之后执行，在Servlet被彻底卸载之前执行。

![image-20210330213805539](Java补充.assets/image-20210330213805539.png)

使用场景：

在servlet初始化加载之前可能会需要处理一些东西，像加载缓存，加载线程池等，此时@PostConstruct就能派上用场了，当然，也可以不使用该注解，如果我们要加载或处理某些东西，我们完全可以在构造器初始化的时候就直接处理掉，只不过这种方法需要自己重写构造器。



另外，spring中Constructor、@Autowired、@PostConstruct的顺序

其实从依赖注入的字面意思就可以知道，要将对象p注入到对象a，那么首先就必须得生成对象a和对象p，才能执行注入。所以，如果一个类A中有个成员变量p被@Autowried注解，那么 **@Autowired注入是发生在A的构造方法执行完之后的**。

如果想在生成对象时完成某些初始化操作，而偏偏这些初始化操作又依赖于依赖注入，那么久无法在构造函数中实现。为此，可以使用@PostConstruct注解一个方法来完成初始化，**@PostConstruct注解的方法将会在依赖注入完成后被自动调用**。

Constructor >> @Autowired >> @PostConstruct

举例： 

```java
public class AAA{
    @Autowired
    private BBB b;
    
    public AAA(){
        System.out.println("此时b还未被注入：b = " + b);
    }
    
    @PostConstruct
    private void init(){
        System.out.println("@PostConstruct将在依赖注入完成之后被自动调用：b = " + b);
    }
}
```

## @Produces

作用类似springmvc中`@RequestMapping中的属性produces`

参考：https://blog.csdn.net/shadowcw/article/details/88093081

## @SafeVarargs

参考：https://www.cnblogs.com/springmorning/p/10285780.html（@SafeVarargs注解的使用）

## @FunctionInterface

参考：https://blog.csdn.net/aitangyong/article/details/54137067（JDK8新特性：函数式接口@FunctionalInterface的使用说明）

# 生成随机值

## UUID包的使用

```java
UUID.randomUUID();
```

# Websocket

具体看`alg-manager`

# 雪花算法

具体使用方法查看hutool源码或deya门户项目源码

参考：https://blog.csdn.net/Piconjo/article/details/106931062（SnowFlake雪花算法的介绍及Java实现(工具类)）

# jarFile

jarFile可用于读取jar文件中的内容

应用场景：比方说有时候我们需要读取jar文件中某个class的注解，但是jar文件是被打包过的，没办法直接读取到它里面的内容，此时就可以用jarFile

参考文章：https://blog.csdn.net/qq_40951086/article/details/78580665

# RunTime

用于执行shell命令、获取计算机cpu核数等

# java中的各种命令参数

- 命令行参数

  ```
  命令行参数就是类似与c语言的命令行参数，这些参数会传给main函数，也就是java中 public static void main(String[] args) 的那个String数组。但是需要注意的是，c语言的main行数中传入的参数，arv[0]是程序本身的名字，比如program1 option1 option2,那么arv[0]就是program1本身。但是java的命令函参数确实从0开始的，也就是说，java中的第一个命令行参数是的args[0]，举个例子 java program1 option1 option2 运行一个java程序的话，args[0]获取到的是option1。
  ```

- 系统属性参数

  ```
  系统属性参数也是供应用程序使用的，并且是以key=value这样的形式提供的，在程序的任何一个地方，都可以通过System.getProperty("key")获取到对应的value值。
  
  在官方文档中对系统属性参数的描述是这样的：
  Set a system property value. If value is a string that contains spaces, you must enclose the string in double quotes:
  java -Dfoo="some string" SomeClass
  
  系统属性参数传入的时候需要带一个横杆和大写字母D，比如-Dfuck.abc="1234"这样的。在你业务代码中，你就可以使用它了：System.getProperty("fuck.abc")，获取"1234"。不过系统属性参数一般都是用来开启一些官方开关的，比如加入-Djdk.internal.lambda.dumpProxyClasses="/home/xxx"，你就可以把java8中lambda表达式的代理类字节码dump出来。
  ```

- jvm参数

  ```
  jvm参数就是和jvm相关的参数了，比如配置gc、配置堆大小、配置classpath等等
  jvm参数分为标准参数、扩展参数和不稳定参数
  标准参数是一定有效，向后兼容的，且所有的jvm都必须要实现的，比如-classpath，这类参数是横杆直接跟参数名
  扩展参数是不保证向后向后兼容，不强值要求所有jvm实现都要支持，不保证后续版本不会取消的，这类参数的形式是-Xname，横杠和一个大写的X开头
  不稳定参数就是非常不稳定，可能只是特定版本的，特点是-XXname，横杆后带两个大写X开头
  ```

如果想查看具体的参数的含义，最好的方法就是看官方文档，或者直接man java一下，也可以参考这篇文章[《Java 命令行运行参数》](https://www.cnblogs.com/jtlgb/p/8466138.html)

其实，只要java -h以下，就可以看到这些说明。java命令的语法为：

```
java [-options] class [args...]
           (to execute a class)
```

或者

```
java [-options] -jar jarfile [args...]
           (to execute a jar file)
```

所以，前面说的严格意义上全都是不对的，java中的命令行参数只有option 和 args两类。上面说的三类中第一类对应args，后面两类都是属于option的，那才是jvm的参数。

## Java启动参数`(-, -D, -X, -XX参数)`详解

参考：https://blog.csdn.net/guyue35/article/details/107957859

## JVM内存调优

參考：

https://blog.csdn.net/sloveb123/article/details/127557816（JAVA系列之JVM内存调优）

# spring boot 传递参数

spring boot 打成jar包后 通过命令行传入的参数有3种实现方式：

```
java -jar xxx.jar aaa bbb cccc
传了3个参数，分别是aaa,bbb,ccc
通过main方法的参数获取
```

```
java -jar xxx.jar -Da1=aaa -Db1=bbb -Dc1=ccc
通过  System.getProperty("aaa","1"); 方式获取，作为环境变量
注意：java -Xms10m -Xmx512m xx.jar -DconfigPath=/root 可能不生效-D配置，在程序中读取不到
最好改成：java -Xms10m -Xmx512m -DconfigPath=/root xx.jar 这样就可以读取到了
```

```
java -jar xxx.jar --a1=aaa --b1=bbb --server.port=8080
是springboot的写法，可以通过@Value("${a1}") 获取；
--server.port=8080可以直接指定spring boot的端口号
```

# JUnit单元测试

关于注解`@ExtendWith（SpringExtension.class）`与`@ExtendWith（MockitoExtension.class）`的讨论：

```
当涉及Spring时：
如果你想在测试中使用Spring测试框架功能（例如@MockBean），则必须使用@ExtendWith(SpringExtension.class)。它取代了不推荐使用的JUnit4@RunWith(SpringJUnit4ClassRunner.class)

当不涉及Spring时：
如果你只想涉及Mockito而不必涉及Spring，比如当你只想使用@Mock/@InjectMocks注解时，你可能就会只想使用@ExtendWith(MockitoExtension.class)，因为它不会加载很多不需要的Spring的东西。它替换了不推荐使用的JUnit4 @RunWith(MockitoJUnitRunner.class)。
```

# Stream流处理

对于流其实已经了解的差不多了，包括里面的重点：平行流（`parallel()和parallelStream()`）和串行流（`sequential`），再补充几个：

具体使用参考博客：https://blog.csdn.net/qq_31865983/article/details/106443244（`Java8 Stream API` 之 `IntStream` 用法全解）、https://blog.csdn.net/a13662080711/article/details/84928181（`Arrays.stream()`）、https://www.cnblogs.com/baby123/p/12619872.html（`Arrays.asList`与`Arrays.stream`）

## Collectors.groupingBy与Collectors.mapping联用

参考：https://blog.csdn.net/qq_27607579/article/details/89499911（Stream流使用groupingBy+mapping实现对分组之后的对象集合转化为对象的某个属性的集合）

## Spliterator

参考：https://blog.csdn.net/sl1992/article/details/100149187（Java8中Spliterator详解）

## Sink（流水线节点）

参考：https://zhuanlan.zhihu.com/p/47478339（原来你是这样的 Stream —— 浅析 Java Stream 实现原理）

## boxed()

参考：

https://blog.csdn.net/weixin_37862824/article/details/112756654（Java8中Stream为什么要boxed）

# lambda表达式分组、过滤、求和、最值、排序、去重

参考：https://blog.csdn.net/gsls200808/article/details/86501905（java 8 lambda表达式list操作分组、过滤、求和、最值、排序、去重）

# SLF4J及其MDC详解

MDC 能干什么？

- 在 WEB 应用中，如果想在日志中输出请求用户 IP 地址、请求 URL、统计耗时等等，MDC 基本都能支持；
- 在 WEB 应用中，如果能画出用户的请求到响应整个过程，势必会快速定位生产问题，那么借助 MDC 来保存用户请求时产生的 reqId，当请求完成后，再将这个 reqId 进行移除，这么一来通过 grep reqId 就能轻松 get 整个请求流程的日志轨迹；
- 在微服务盛行的当下，链路跟踪是个难题，而借助 MDC 去埋点，巧妙实现链路跟踪应该不是问题；
- ...

参考：https://blog.csdn.net/weixin_42474537/article/details/114501759（java中mdc是什么_MDC是什么鬼？用法、源码一锅端）、https://blog.csdn.net/taiyangdao/article/details/82860105（SLF4J及其MDC详解）、https://www.cnblogs.com/sealedbook/p/6227452.html（SLF4J中的MDC）、https://blog.csdn.net/u012410733/article/details/109406093（Spring Boot 中使用 MDC 追踪一次请求全过程）

# Reference 、ReferenceQueue

參考：https://www.jianshu.com/p/f86d3a43eec5（Reference 、ReferenceQueue 详解）

# 在线诊断工具 Arthas

导读：虽然已经有很多分析工具 `jvisualvm,jstat,jmap,jstack,Memory Analyzer`等。但可能不是大杂烩或者线上无法分析等。所以看看`arthas`的功能，好用就用它了。

Arthas 是Alibaba开源的Java诊断工具，深受开发者喜爱。当你遇到以下类似问题而束手无策时，Arthas可以帮助你解决：

- 这个类从哪个 jar 包加载的？为什么会报各种类相关的 Exception？
- 我改的代码为什么没有执行到？难道是我没 commit？分支搞错了？
- 遇到问题无法在线上 debug，难道只能通过加日志再重新发布吗？
- 线上遇到某个用户的数据处理有问题，但线上同样无法 debug，线下无法重现！
- 是否有一个全局视角来查看系统的运行状况？
- 有什么办法可以监控到JVM的实时运行状态？
- 怎么快速定位应用的热点，生成火焰图？

参考：https://arthas.aliyun.com/doc/（Arthas官方文档）

# 内存分析工具MemoryAnalyzer

参考：https://blog.csdn.net/lyd135364/article/details/121449969（MAT（Memory Analyzer Tool）工具使用超详细版）

# BIO与NIO、AIO的区别

参考：

- https://blog.csdn.net/ty497122758/article/details/78979302?utm_source=app&app_version=5.1.1（Java BIO与NIO、AIO的区别）
- https://blog.csdn.net/m0_38109046/article/details/89449305（Java面试常考的 BIO，NIO，AIO 总结）
- https://my.oschina.net/u/3471412/blog/2966696（Java 核心 深入理解BIO、NIO、AIO）
- https://blog.csdn.net/liuningwjt/article/details/82379364（IO/NIO/AIO 基本概念）
- https://blog.csdn.net/scugxl/article/details/86742171（Java中的BIO,NIO,AIO详解以及Echo实现示例）

# 高性能网络请求开源库okhttp3

参考：https://blog.csdn.net/victor_fang/article/details/88175549（基本使用）、https://blog.csdn.net/victor_fang/article/details/88196410（常用类介绍）、https://blog.csdn.net/victor_fang/article/details/88176344（Interceptor）

# 分布式锁

有三种实现方式：基于数据库实现、基于缓存（redis等）实现、基于Zookeeper实现

参考:https://blog.csdn.net/zhaisharap/article/details/122471322、https://www.zhihu.com/question/452803310/answer/1931377239（深入探讨基于redis和基于Zookeeper实现分布式锁的优点以及存在的问题；深度解析Redlock（红锁）；引入Java工具包Redisson来方便使用基于redis的分布式锁）



关于分布式网络一致性问题可以去研究一下拜占庭将军问题，参考：https://zhuanlan.zhihu.com/p/33666461、https://learnblockchain.cn/2017/11/04/bitcoin-pow/（工作量证明 - POW : Proof of Work）



# SPI机制

 SPI全称为 (Service Provider Interface)，是JDK内置的一种服务提供发现机制。SPI是一种动态替换发现的机制，一种解耦非常优秀的思想。

SPI机制案例：

- JDBC驱动加载案例：利用Java的SPI机制，我们可以根据不同的数据库厂商来引入不同的JDBC驱动包；
- SpringBoot的SPI机制：我们可以在spring.factories中加上我们自定义的自动配置类，事件监听器或初始化器等；
- Dubbo的SPI机制：Dubbo更是把SPI机制应用的淋漓尽致，Dubbo基本上自身的每个功能点都提供了扩展点，比如提供了集群扩展，路由扩展和负载均衡扩展等差不多接近30个扩展点。如果Dubbo的某个内置实现不符合我们的需求，那么我们只要利用其SPI机制将我们的实现替换掉Dubbo的实现即可；

参考：

https://blog.csdn.net/ymb615ymb/article/details/123450610（SPI机制是什么？）

https://zhuanlan.zhihu.com/p/148144666（搞懂dubbo的SPI扩展机制）

https://zhuanlan.zhihu.com/p/609319461（源码级深度理解 Java SPI）

https://www.zhihu.com/question/584728871/answer/3167884411（java SPI( Service Provider Interface)是什么？）

https://zhuanlan.zhihu.com/p/656398645（深入浅出：SPI机制在JDK与Spring Boot中的应用）

## ServiceLoader

参考：https://zhuanlan.zhihu.com/p/212850943（Java | 带你理解 ServiceLoader 的原理与设计思想）

# java调用c/c++

有两种比较推荐的方式：JNI工具包和JNA框架

JNI的使用请参考：https://blog.csdn.net/weixin_51763233/article/details/122205288（Java调用C++的步骤详解）

JNA的使用请参考：https://blog.csdn.net/qingzhuyuxian/article/details/122997670（开源框架JNA的使用）



Idea下JNI编程，参考：

https://blog.csdn.net/weixin_43699108/article/details/124243927（熟悉JNI机制-Idea下JNI编程）

参考项目：[刘鹏`tpt-apc-backend`项目](https://github.com/pippichi/work/tree/master/zk/%E9%80%9A%E7%94%A8%E4%BC%98%E5%8C%96%E5%BC%80%E5%8F%91%E6%A1%86%E6%9E%B6/base/supcon-parent)

# OutputStream

## ObjectOutputStream

参考：https://blog.csdn.net/ifubing/article/details/115255490（对象输出流）

## ByteArrayOutputStream

ByteArrayOutputStream 对byte类型数据进行写入的类，相当于一个中间缓冲层，将类写入到文件等其他OutputStream。它是对字节进行操作，属于内存操作流。

参考：https://blog.csdn.net/qq_43597675/article/details/88829226（ByteArrayOutputStream详解）

# 枚举实现接口

枚举类型实现接口需要实现接口的抽象方法，此时有两个选择：

一、在枚举类型中重写方法一次。

二、对枚举类型中的每个对象重写一次方法，这样可以做到每个对象的方法都不相同。

参考：https://blog.csdn.net/Demon_LMMan/article/details/113655925（Java中的枚举类型与枚举实现接口的两种方式）

# 远程监控与调试

- 使用idea远程debug调试

  开启远程debug需要配置以下参数：

  `agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005`

  参考：https://blog.csdn.net/w8y56f/article/details/116493681（使用IDEA远程debug调试）

- 使用jmx进行远程监控

  在使用 JMX 对 Java 应用进行监控时，一般会在启动时添加如下参数：

  ```shell
  java \
  -Djava.rmi.server.hostname=192.168.16.237 \
  -Dcom.sun.management.jmxremote.rmi.port=2909 \
  -Dcom.sun.management.jmxremote.port=9009 \
  -Dcom.sun.management.jmxremote.authenticate=false \
  -Dcom.sun.management.jmxremote.ssl=false \
  -jar test.jar
  ```

  参考：https://www.jianshu.com/p/5884b5ecbe1a（为什么要设置`com.sun.management.jmxremote.*`？）

# 静态代理与动态代理

参考：https://cloud.tencent.com/developer/article/1429932（Java 静态代理、Java动态代理、CGLIB动态代理）、https://blog.csdn.net/weixin_36759405/article/details/82770422（动态代理的两种方式以及区别）、https://blog.csdn.net/qq_32532321/article/details/81874990（java动态代理详解）

# Map

## HashMap底层实现原理

参考：https://blog.csdn.net/kun_998/article/details/89480637（HashMap底层实现原理）

## WeakHashMap

参考：https://blog.csdn.net/u013467442/article/details/105826778（Java中的WeakHashMap）、https://zhuanlan.zhihu.com/p/80083997（Java WeakHashMap）

# 读取csv文件

- hutool读取csv

  参考：https://blog.csdn.net/prefect_start/article/details/115742170（Hutool 读取csv文件和输出csv文件）

- Java原生读取与第三方库读取

  参考：https://blog.csdn.net/liulang_ynfx/article/details/108068211（java读取csv文件的多种方式）、https://blog.csdn.net/weixin_44520739/article/details/123856068（SpringBoot读取csv文件）

# 读取配置文件

参考：https://blog.csdn.net/MyHerux/article/details/83549149（SpringBoot 获取配置文件属性（全5种，附项目Demo））、https://pippichi.github.io/blog/springboot_basic（博客笔记 Spring Boot -basic）、https://blog.csdn.net/Thinkingcao/article/details/111897862（Java中读取properties配置文件的八种方式总结）、https://blog.csdn.net/u013410747/article/details/51647535（Java读取/写入Yaml配置文件）

# 音视频传输

## RTSP

跟RTMP区别在于RTMP是基于TCP，RTSP基于UDP

參考：

https://blog.csdn.net/qq_41709801/article/details/127339210（RTSP，Java实现简单的RTSP报文交换★）

https://blog.csdn.net/qq_33210338/article/details/105083247（基于Java的RTSP服务）

https://blog.51cto.com/u_16213388/9262693（java rtsp推音频流）

https://www.5axxw.com/questions/simple/ofzppo（java接受rtsp流解码,推送给前端,实现播放实时流）

https://blog.51cto.com/u_16213608/10394489（java验证rtsp视频流地址 java rtsp服务端）

## RTMP

跟RTSP区别在于RTMP是基于TCP，RTSP基于UDP

参考：

https://blog.csdn.net/weixin_38225800/article/details/134934801（JavaCV之rtmp推流（FLV和M3U8）★）、https://blog.csdn.net/weixin_38225800/article/details/135181906（Javacv-利用Netty实现推流直播复用（flv）★）

https://blog.csdn.net/Number_oneEngineer/article/details/109196862（java抓取rtmp流的图像）

https://blog.csdn.net/Number_oneEngineer/article/details/107814586（使用javaCV录制直播流）

https://blog.csdn.net/xxxlllbbb/article/details/104819683（基于JavaCV技术实现RTMP推流和拉流功能）

https://blog.csdn.net/HelloKittyTom/article/details/113553000（java编写 rtmp协议传输视频数据）

https://blog.csdn.net/crazyzxljing0621/article/details/69568339（Red5+SpringMVC整合(RTMP+HTTP)搭建你的直播服务器）

https://www.jianshu.com/p/b2144f9bbe28（带你吃透RTMP）

## RTC

参考：

https://blog.csdn.net/King_weng/article/details/115000005（实时通信之RTC）

https://blog.csdn.net/lidecoolblue/article/details/117449804（RTC技术（Webrtc））

https://blog.csdn.net/carolzhang8406/article/details/7311413（RTC Java API 学习笔记）

https://blog.csdn.net/mengzhengjie/article/details/51460779（Java+WebSocket+WebRTC实现视频通话实例）

https://blog.csdn.net/irizhao/article/details/106640949（在Java中使用WebRTC传输视频）

https://blog.csdn.net/Melod_bc/article/details/61414980（Java使用websocket和WebRTC实现视频通话）

https://blog.csdn.net/Ares_Basic/article/details/40383191（JAVA webRtc的实现视频会议系统）

https://blog.csdn.net/zhlzdjdj/article/details/113409363（springboot+websocket+webRTC在chrome上实现web视频通话）

https://blog.csdn.net/weixin_44268792/article/details/106243014（Spring Boot WebSocket + WebRTC实现视频通话功能）

## RTC与传统RTMP对比

| 参数对比           | RTC                                                          | RTMP（CDN）                                                  |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 底层推流端传输协议 | RTP（UDP）                                                   | RTMP（TCP）                                                  |
| 质量保证Qos        | RTCP                                                         | -                                                            |
| 播放端协议         | RTP                                                          | rtmp、hls、http-flv                                          |
| 延迟               | 400ms以内                                                    | rtmp 3s+、hls 15s+、http-flv 3s+                             |
| 同步性             | 推流端与播放端基本实时，同步性非常好                         | 推流端与播放端同步性差                                       |
| 互动体验性         | 优                                                           | 差                                                           |
| 关注点             | 关注实时性                                                   | 关注质量                                                     |
| 拓扑结构           | 双向，既有推流又有拉流                                       | 单向，主播推流、观众拉流                                     |
| 技术限制           | 参与人数限制，以声网为例支持17人互动，百万观看（低延迟直播产品） | 一个主播，观众数理论无上限                                   |
| 安全性             | 所有 WebRTC 媒体数据都必须经过加密                           | 原生无加密技术，需定制开发视频加密和防盗链                   |
| 兼容性             | 为web端而生，提供Native sdk（移动端、PC端），无服务端通用方案需自行开发 | web已不支持发起rtmp直播（Adobe 2020 12弃用flash）rtmp标准协议接入，服务端由技术成熟的CDN分发 |
| 复杂性             | 非常复杂，涉及技术庞杂                                       | 比较简单清晰                                                 |
| 典型应用场景       | 推流端与播放端互动性强的场景：视频会议、连麦互动、语音/视频聊天 | 推流端与播放端同步性不是很高要求的场景：活动/赛事直播、秀场直播、游戏直播、直播带货 |
| 价格（成本）       | 高                                                           | 低                                                           |

## ffmpeg

參考：

https://blog.csdn.net/water1209/article/details/131613251（音视频领域强大的辅助工具-ffmpeg常用操作介绍）

https://zhuanlan.zhihu.com/p/673522888（FFMPEG详解(完整版））

### 命令详解

參考：

https://www.cnblogs.com/AllenChou/p/7048528.html（ffmpeg命令详解(转)）

https://blog.csdn.net/qq_40135848/article/details/132589706（音视频 ffmpeg命令参数说明）

https://blog.csdn.net/lixiaowu119/article/details/136705412（ffmpeg命令参数详解）

### 加水印

参考：

https://blog.csdn.net/weixin_43959459/article/details/122960401（java使用ffmpeg完成视频加水印功能）

https://blog.51cto.com/u_16175431/6608596（如何实现Java 视频加水印FFmpeg的具体操作步骤）、https://blog.51cto.com/u_16213310/11120526?abTest=51cto（java ffmpeg视频加水印）

## javacv

参考：

https://www.jianshu.com/p/86cc0e24e455（JavaCV入门教程和JavaCV文档手册汇总）

https://www.jianshu.com/p/3d283dac1674（JavaCV推流实战(MP4文件)★）

https://blog.csdn.net/2401_83064295/article/details/139149798（JavaCV的摄像头实战之七：推流(带声音)）

https://blog.csdn.net/Just_do_it_HZF/article/details/134293859（Java通过javacv获取视频、音频、图片等元数据信息（分辨率、大小、帧等信息）★）

https://blog.51cto.com/u_16175435/7745153（javacv将视频转发并改变分辨率★）

https://blog.csdn.net/eguid_1/article/details/117277841（JavaCV精简依赖包：如何只依赖本地平台所需的最小依赖包★）

https://blog.csdn.net/qq_39035030/article/details/107793574（JavaCV依赖精简★）

https://www.cnblogs.com/eguid/p/15636593.html（JavaCV入门指南教程目录）

https://segmentfault.com/a/1190000044568143（这可能是最详细的javaCV-FFmpeg防踩坑入门了）

https://blog.csdn.net/eguid_1/article/details/108010456（javacv教程文档手册开发指南汇总篇）

https://www.cnblogs.com/dongye95/p/17773211.html（javacv入门）

https://blog.csdn.net/ayou_llf/article/details/129222520（javacv从入门到精通——第三章：基本使用）

https://gitee.com/hjljy/javacv（海加尔金鹰javacv）

[钱依峰`rtmp-jwt-stream`项目](https://github.com/pippichi/work/tree/master/zk/%E9%80%9A%E7%94%A8%E4%BC%98%E5%8C%96%E5%BC%80%E5%8F%91%E6%A1%86%E6%9E%B6/base/supcon-parent)

### 加水印

参考：

https://blog.csdn.net/u014723137/article/details/127612657（javacv 视频添加自定义水印）

https://blog.csdn.net/u011936181/article/details/88655274（JAVACV实现视频帧添加中文水印）

https://blog.csdn.net/chuangfei7389/article/details/100871735（javaCV视频添加水印）

https://blog.csdn.net/m0_46106632/article/details/120074008（javacv给本地视频添加水印★）

https://blog.csdn.net/weixin_39310051/article/details/132232444（Java实现视频与图片添加水印★）

## x264编码器

參考：

https://blog.csdn.net/GrayOnDream/article/details/138401269（X264编码器参数）

https://blog.csdn.net/weixin_42877471/article/details/138524724（【x264】x264编码器参数配置）

### min-keyint（最小-IDR帧间隔）

參考：

https://zhuanlan.zhihu.com/p/456799518（基础不牢之x264中的min-keyint参数有什么用？）

# 图像处理

## Graphics2D

可以用来绘制水印图

參考：

https://blog.csdn.net/qq_28369007/article/details/129757451（java Graphics2D 绘图常用方法和参数介绍说明）

https://blog.csdn.net/wlddhj/article/details/134367714（Java图像编程之：Graphics2D）

https://blog.csdn.net/weixin_48419914/article/details/121080215（Java | 绘图：Graphics2D）

https://www.cnblogs.com/zhouzetian/p/7426758.html（JAVA-Graphics2D类）

# 文件处理

## Word文档处理

參考：

https://www.cnblogs.com/kakarotto-chen/p/17371043.html（SpringBoot导出Word文档的三种方式）

https://blog.csdn.net/qq_42682745/article/details/120867432（SpringBoot Poi导出word,浏览器下载）

## 大excel文件处理

如果对有一定并发的项目，大文件读最好是使用SAX模式，但它有一定的编码量；大文件的写最好基于sxssf。

定时做`System.gc()`也可一定程度上缓解内存紧张。如果gc不是cms模式要在启动项中要添加配置（`-XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled`）

参考：https://blog.csdn.net/alex_xfboy/article/details/84844667（彻底解决POI 读写excel 发生OOM问题）、https://poi.apache.org/components/spreadsheet/how-to.html#xssf_sax_api（使用SAX处理大文件读）



# Closeable、AutoCloseable与`try-with-resources`语法

参考：https://zhuanlan.zhihu.com/p/269208361（浅谈 Java 中的 AutoCloseable 接口）

# 数据结构

## PriorityQueue优先队列

PriorityQueue线程不安全，PriorityBlockingQueue线程安全

参考：https://blog.csdn.net/u010675669/article/details/86503464（Java PriorityQueue（优先队列））

# GC垃圾回收

参考：https://blog.csdn.net/imjavaxb/article/details/103805740（GC对象回收（垃圾回收））

# 强引用、软引用、弱引用、虚引用

参考：https://blog.csdn.net/qq_39192827/article/details/85611873（Java：强引用，软引用，弱引用和虚引用）

# 结构化并发编程（Structured Concurrency）

参考：https://blog.csdn.net/XiumingLee/article/details/126803317（Java 19新特性：Structured Concurrency (结构化并发编程)）

# Reactor响应式编程

参考：https://blog.csdn.net/gb4215287/article/details/120603599（使用Reactor进行反应式编程最全教程）、https://blog.csdn.net/qq_28089993/article/details/89461814（响应式编程中的Flux和Mono）、https://blog.csdn.net/qq_35067322/article/details/108301834（我对响应式编程中Mono和Flux的理解）、https://blog.csdn.net/lz710117239/article/details/93777692（Mono和Flux的用法详解）、https://www.cnblogs.com/crazymakercircle/p/16127013.html（Flux 和 Mono 、reactor实战 （史上最全））

## 背压BackPressure

参考：https://blog.csdn.net/Zong_0915/article/details/115048075（Reactor响应式编程系列（二）- 背压策略BackPressure）、https://blog.csdn.net/m0_37556444/article/details/87916093（Reactor3背压）、https://zhuanlan.zhihu.com/p/37076445（Project Reactor学习（12）--背压）、https://zhuanlan.zhihu.com/p/611064237（响应式框架Reactor中的背压实现）

