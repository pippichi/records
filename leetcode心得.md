## 数组

### [1. Two Sum](https://leetcode-cn.com/problems/two-sum/)

- 暴力枚举

- 哈希表

  遍历一次

  用哈希表维护已经被遍历过的元素

### [14. Longest Common Prefix](https://leetcode-cn.com/problems/longest-common-prefix/)

- 横向扫描

  以字符串为单位对比

- 纵向扫描

  以字符为单位对比

- 分治

  以字符串为单位分组

- 二分查找

  以字符为单位分组

### [26. Remove Duplicates from Sorted Array](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)

- 双指针（快慢指针）

### [27. Remove Element](https://leetcode-cn.com/problems/remove-element/)

- 双指针
  - 快慢指针，慢指针可以顺便计数
  - 头尾指针，头指针可以顺便计数

### [35. Search Insert Position](https://leetcode-cn.com/problems/search-insert-position/)

- 二分查找

### [53. Maximum Subarray](https://leetcode-cn.com/problems/maximum-subarray/)

- 动态规划

  维护两个变量，一个记录局部最优，另一个记录诸多局部最优中的最大值

- 分治

  抽象来看，每一个段都可以分成左半段和右半段，而左半段和右半段又各自为一个段。假设每一个段有4个属性：`lSum（左半段部分连续元素加和能得到的最大值）`、`rSum（右半段部分连续元素加和能得到的最大值）`、`iSum（整段元素加和的值）`、`mSum（该段元素部分连续元素加和能得到的最大值）`，那么每一个段的`mSum`就是`左半段的mSum`、`右半段的mSum`、`左半段的rSum + 右半段的lSum`这三者的最大值，对于`iSum`，`iSum = 左半段iSum + 右半段iSum`，对于`lSum`，`lSum = max(左半段lSum, 左半段iSum + 右半段lSum)`，对于`rSum`，`rSum = max(右半段rSum, 右半段iSum + 左半段rSum)`。按照这个逻辑，最终求得的整个段的`mSum`即为解。

### [66. Plus One](https://leetcode-cn.com/problems/plus-one/)

- 数学

  逢十进一

### [70. Climbing Stairs](https://leetcode-cn.com/problems/climbing-stairs/)

- 动态规划

  每一阶的计算方法类似于斐波那契，是典型的动态规划

- 递归

  直接做递归其实很简单，但是某些数值会被重复计算很多次，因此难点就在于如何不重复计算，这里我们通过维护一个数组用于记录每一阶的结果，等到要调用的时候判断如果数组中已经有值了就直接从数组中取值，如果没有就去计算，这样可以将计算量降低一个数量级

- 另外还可以通过矩阵快速幂优化斐波那契的计算过程（事实上就是矩阵运算的化简），最终得到一个方程式来进行编程求解
- 另外还可以通过斐波那契的计算公式（可以通过特征值求解方程可行解得到）来进行求解

### [88. Merge Sorted Array](https://leetcode-cn.com/problems/merge-sorted-array/)

利用其中一个数组的多余空间合并两个数组

- 直接将第二个数组加到第一个数组的后面，最后调用sort()进行排序
- 双指针/从前往后
  - 需要开辟一个新数组，由于给定的两个数组已经经过排序，因此从头比较原先两个数组的元素大小，较小的那个插入新数组，等到两个数组中的某一个数组的指针走到了尾部，就将剩余没走完的数组的剩余所有元素加到新数组后面即可

- 双指针/从后往前
  - 由于给定的第一个数组有足够多的空余位置可以存放第二个数组，因此我们完全不需要开辟一个新数组，只需要从后往前比较并将较大的那个插入第一个数组最后面的空余位置即可（这需要我们将用于插入的指针从最开始就指向第一个数组最后，每次插入后就像前移动一格）
  - 这样做有一个好处就是：因为给定的两个数组都已经排好序了，如果第二个数组走完，那第一个数组的剩余元素天然的就存在第一个数组的前面，且不需要再去排序；如果第一个数组先走完，那也没关系，第二个数组的剩余元素还是可以正常的插入到第一个数组前面（那是因为我们可以保证在第二个数组走完之前第一个数组的用于插入的指针永远不会走到底，那是因为给定的第一个数组拥有足够的空间存放两个数组的所有元素）
  - 扩展：其实这么想的话就算第一个数组没给足够的空间，我们也可以resize他，让他拥有存放两个数组所有元素的足够的空间，然后用双指针从后往前的方法去做

### [118. Pascal's Triangle](https://leetcode-cn.com/problems/pascals-triangle/)

- 动态规划

  每一列都是通过上一列得来的，这是典型的动态规划

### [119. Pascal's Triangle II](https://leetcode-cn.com/problems/pascals-triangle-ii/)

- 动态规划

  总的来说就是利用杨辉三角后一行与前一行的关系
  更新过程为：从倒数第二个元素开始往前更新它等于原来这个位置的数 + 前一个位置的数
  行[i] = 行[i] + 行[i-1]

  - 方法一

    在当前数组的后面加新的数，最后把前面原来的数擦除

  - 方法二

    每一次遍历先向数组最后添加 1 ，再执行：行[i] = 行[i] + 行[i-1] 这个逻辑

### [121. Best Time to Buy and Sell Stock](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)

- 暴力法

- 一次遍历

  如果以卖出的那一天为基准，这样的话买入价格一定是越低越好，当然这个买入的日子一定是在卖出之前；如果以买入的那一天为基准，由于不知道后面哪一天价格最低，因此我们就把当天的价格当成最低的，向后计算后面每一天的收益，记录最大收益，当遇到某一天的买入价格比之前的价格低的时候（想一下后面不管价格是多少，我们都应该选择价格更低的那个价格，这样收益一定是最大的），我们应该修改买入价格为这一天的价格。这样的话到最后所记录的最大收益就是最大收益了

### [122. Best Time to Buy and Sell Stock II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)

- 暴力法
  - 把每一波都当成是最优的购入，之后去找比购入价格大的那一天卖出，再从卖出的后一天开始买入（这一天还是被当成最优）（用递归实现后续的买入和卖出）
  - 计算每次卖出后卖出价格 - 买入价格 + 后续的每一次买入和卖出的价格差（递归）
  - 保存每一天作为买入时的收益，取最大值即为最大收益
  - 该算法时间复杂度极大

- 峰谷法
  - 理论上每一次谷的时候买入，峰的时候卖出（这里的谷和峰可以类比函数一阶导为0的点），这样的收益一定比整个函数最峰 - 最谷的价格收益大
  - 因此先找到函数的波谷，再在波谷之后找波峰，再在波峰之后找波谷，再找波峰...
  - 将每一次的峰谷价格差累加，即为最优收益

- 一次遍历
  - 思路还是上面的峰谷法，但这次我们不再寻找波峰和波谷
  - 理论上如果函数一直上升的话到某一个波峰的时候他的总收益（波峰 - 波谷）是等于每一段上升的价格累计之和，因此我们只需要累加上升的价格，跳过下降的价格即可得到最优收益

### [136. Single Number](https://leetcode-cn.com/problems/single-number/)

首先明确数组中的元素只会出现两次或一次，且出现一次的元素只有一个

- 可以将数组元素放入集合中，再计算集合中元素之和乘以2，再减去数组元素之和即可得到结果

- 维护一个集合，如果集合中没有该元素就加入，如果有了就去除，最后剩下的就是结果

- 用哈希表存储每个元素及其出现的次数，最后遍历得到出现一次的元素

- 位运算

  上述方法都需要开辟额外的空间，其实本质上我们就是想得到这样的效果：如果有两个一样的元素就把他拼掉，那异或运算正好符合这个操作，因此对数组中每一个元素都做一次异或操作即可得到结果

### [167. Two Sum II - Input array is sorted](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)

- 暴力枚举法

  对每一个元素，从他后面的位置挨个遍历找另一个元素

- 二分查找法

  思路还是暴力枚举的思路，差别在于由于给定的数组有序，因此在查找另一个元素的时候可以用二分查找法

- 双指针

  由于给定数组有序，因此采用头尾指针，如果指向元素之和小于目标值，就将头指针向后移，反之尾指针向前移，直到找到元素

### [169. Majority Element](https://leetcode-cn.com/problems/majority-element/)

- 哈希表

- 排序

  首先顺序排序

  如果数组长度是奇数则取中间元素；如果长度是偶数则取中间位置右边的元素

- 随机化

  由于是选众数，因此随机选出的数很有可能就是众数

- 分治

  可以通过反证法证得：假设`a`是数组`nums`的众数，如果将`nums`分成左右两半，那么`a`一定也是左半边的众数或者右半边的众数。那么就可以先求得右半边和左半边的众数，再来确定这两个数谁才是数组真正的众数。

  我们使用经典的分治算法递归求解，直到所有的子问题都是长度为 `1 `的数组。长度为 `1` 的子数组中唯一的数显然是众数，直接返回即可。如果回溯后某区间的长度大于` 1`，我们必须将左右子区间的值合并。如果它们的众数相同，那么显然这一段区间的众数是它们相同的值。否则，我们需要比较两个众数在整个区间内出现的次数来决定该区间的众数。

- `Boyer-Moore 投票算法`

  如果我们把众数记为 `+1`，把其他数记为`-1`，将它们全部加起来，显然和大于 `0`

  那么就可以维护一个`candidate`变量，其初始值为数组第一个元素，我们假设它就是众数，随后开始遍历数组，如果遇到的数就是`candidate`，则计数器`+1`，反之`-1`，当计数器为`0`的时候，`candidate`变量在下一次遍历的时候发生改变，变为下一次遍历到的数，并且计数器`+1`，如此，当遍历到数组末尾的时候，如果存在众数，计数器必定大于`0`，并且此时`candidate`变量的值就是众数

### [198. House Robber](https://leetcode-cn.com/problems/house-robber/)

- 典型的动态规划

  最终的最大收益一定要么是上一个状态的最大收益，要么是上上个状态的最大收益加现在的收益

  - 用数组维护每一步的最大收益
  - 实际上只需要维护以现在为基准的前两步的每一步的最大收益即可，因此也可以不用数组，而是使用两个变量维护前两步的最大收益

### [217. Contains Duplicate](https://leetcode-cn.com/problems/contains-duplicate/)

- 朴素线性查找

  双重for循环查找

- 排序之后对比相邻元素

- 哈希表

  首先应该想到该题需要一个支持快速搜索和插入操作的动态数据结构，这里我们用了unordered_set

### [219. Contains Duplicate II](https://leetcode-cn.com/problems/contains-duplicate-ii/)

- 线性搜索

  维护一个长度为k的滑动窗口，在滑动窗口中比较元素（实现方法就是双重for循环）

  此外，我们来思考是否有更好用的数据接口来实现这个滑动窗口呢？考虑到上面的滑动窗口是先进先出的，因此很容易想到的一个数据结构就是队列，但是由链表实现的队列虽然可以支持在常数时间内的删除、插入，但是搜索所耗费的时间确实线性的，所以如果使用队列来实现上述的滑动窗口并不会有更大的优势

- 平衡二叉搜索树（BST）

  我们再思考一下限制队列的在此题中的效率的原因是什么？显然是搜索时的低效导致了最终的低效，那又有什么数据结构能同时兼顾删除、插入、搜索的效率呢？那就是平衡二叉搜索树（BST）了（Java中可以用TreeMap或TreeSet，c++可以用stl::set或stl::map），BST中搜索、插入、删除都可以保持O(log k)的时间复杂度

  那他该如何维护滑动窗口呢？请看如下代码：

  ```c++
  for (int i = 0; i < nums.length; ++i) {
      if (set.contains(nums[i])) return true;
      set.add(nums[i]);
      // 控制滑动窗口大小
      if (set.size() > k) {
          // 删除最老元素的方法
          set.remove(nums[i - k]);
      }
  }
  ```

- 散列表（哈希表）

  实验后我们得知虽然平衡二叉树能够完成任务，但是效率还是不够，我们需要一个删除、插入、搜索的时间复杂度更低的数据结构，那就是散列表了，在本题中，我们会做n次搜索、删除、插入操作，每次操作都耗费常数时间，因此时间复杂度为O(n)

  使用散列表实现滑动窗口思路基本跟使用平衡二叉搜索树的一样

### [228. Summary Ranges](https://leetcode-cn.com/problems/summary-ranges/)

- 双指针

  关键点：1、保留起始点；2、更新起始点

  - 用while控制终止点指针，如下：

    ```c++
    for(int i, j = 0; j < nums.size(); j++){
        ...
        // 直接更新终止点指针到目标位置
        while(j + 1 < nums.size() && nums[j + 1] == nums[j] + 1){
            j++;
        }
        ...
    }
    ```

  - 用if控制终止点指针，如下：

    ```c++
    for(int i = 0, j = 0; i < nums.size(); i++){
        ...
        // 用if配合continue的方式更新终止点指针到目标位置
        if(i + 1 < nums.size() && nums[i] + 1 == nums[i + 1]){
            continue;
        }
    	...
    }
    ```

  注意点：1、Java中可以直接用 1 + “” 的方式将int转换成string，但是c++需要借助to_string()方法；2、不要忘记更新起始点；3、c++中查找字符串是否包含子串需要借助string::size_type、find()函数以及string::npos，而Java中我们可以使用contains()函数，也可以使用indexOf()函数来实现

### 杨氏矩阵

有一个数字矩阵，矩阵的每行从左到右是递增的，矩阵从上到下是递增的，要求在时间复杂度O(N)内在这样的矩阵中找到某个数字的存在

分析：

如果暴力求解，那么最差情况下就需要遍历整个数组，时间复杂度为O(N)，不符合要求

根据矩阵的特征，右上角和左下角的数是特别的（右上角的元素是该行最大，该列最小，同理左下角），对于给定元素，要么就是在右上角元素的下面，要么就是在右上角元素的左边，同理左下角元素。所以从右上角或者左下角元素开始找能够更快地找到元素

```c
// 这里传*row和*col的原因是想要函数返回找到的元素的下标，如果我们直接在方法里面打印下标不符合函数干净简洁的原则，因此采取在外部直接传入坐标地址的方法来返回坐标值。
int findNum(int arr[3][3], int target, int* row, int* col){
    // 以右上角元素为基准，同理左下角
    int x = 0;
    int y = *col - 1;
    while(x < *row && y >= 0){
        if(arr[x][y] > target){
            y--;
        }
        else if(arr[x][y] < target){
            x++;
        }
        else{
            *row = x;
            *col = y;
            return 1;
        }
    }
    // 找不到
    *row = -1;
    *col = -1;
    return 0;
}
int main(){
    int arr[3][3] = {{1,2,3}, {4,5,6}, {7,8,9}};
    int target = 7;
    int x = 3;
    int y = 3;
    // 这种直接把参数地址传进去的叫做返回型参数
    int res = findNum(arr, 7, &x, &y);
    return 0;
}
```

### [278. First Bad Version](https://leetcode-cn.com/problems/first-bad-version/)

- 二分查找

  心得：由于整个数组左边全是false，右边全是true，我们要找的是第一个为true的元素，那么此时如果中间元素是true，则说明它有可能是我们要找的，但如果是false，说明它一定不是我们要找的，所以如果是true的话就还不能把这个元素排除掉，而如果是false，就可以直接排除掉这个元素了

### [283. Move Zeroes](https://leetcode-cn.com/problems/move-zeroes/)

- 双指针

### [303. Range Sum Query - Immutable](https://leetcode-cn.com/problems/range-sum-query-immutable/)

- 动态规划

### [338. Counting Bits](https://leetcode-cn.com/problems/counting-bits/)

- 暴力求解

- 暴力求解优化

  利用`i & (i - 1)`消除二进制最低位的1

- 动态规划

  - 最高有效位

    `10111`比`00111`多一个1

  - 最低有效位

    `10110`比`10100`多一个1

  - 最低设置位

    `111`比`011`多一个1；`110`比`011`多0个1

### [344. Reverse String](https://leetcode-cn.com/problems/reverse-string/)

双指针

### [349. Intersection of Two Arrays](https://leetcode-cn.com/problems/intersection-of-two-arrays/)

- 两个集合取交集

- 一个集合用作过滤器

  注意集合中已经比对过的元素要及时删除

- 排序之后双指针

### [350. Intersection of Two Arrays II](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/)

- 排序之后双指针
- 哈希表

### [401. Binary Watch](https://leetcode-cn.com/problems/binary-watch/)

- 枚举时分

  把所有可能的时分都枚举出来，再筛选

- 二进制枚举

  取10位二进制，前4位为时，后6位为分，从这10位二进制所能表达的所有数中去做筛选

### [414. Third Maximum Number](https://leetcode-cn.com/problems/third-maximum-number/)

- 直接排序，设置计数器，得到第三大的数
- 使用集合
  - 将数放入集合中，维护三个变量`a、b、c`放前三大的数，遍历一遍之后`c`即为第三大的数
  - 将数放入集合中，使用`reverse_iterator`得到第三大的数

### [448. Find All Numbers Disappeared in an Array](https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/)

- 维护一个数组用于记录范围内哪些数是出现过的哪些数是没有出现过的

- 上面法一的空间复杂度为`O(n)`，现在我们要将它优化为`O(1)`

  既然要将空间复杂度优化为`O(1)`，那么就不能再像法一那样维护一个额外的数组了，这里我们使用原数组本身

  核心思想：原数组长度为`n`，原数组中出现的数的范围都在`1-n`之间，那么遍历原数组，遍历到的每个数减一之后的数当作下标，该下标对应的数增加`n`，再次遍历原数组，如果遇到数小于或等于`n`，则表示该数对应的下标加一之后的数没有出现在原数组

### [453. Minimum Moves to Equal Array Elements](https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/)

- 暴力法

  开启一个最外层while循环，循环内部遍历找到数组最大最小值所对应的下标（`max_index`和`min_index`），判断这两个下标对应的数是否相等，如果相等则退出最外层循环，反之遍历数组，让不是`max_index`的下标所对应的数都加一，同时计数器加一，重复操作直到退出最外层循环，最终计数器的值就是我们要求的值

- 暴力法改进

  暴力法中我们将不是`max_index`的下标所对应的数都加一，事实上我们可以维护一个变量`diff`，它保存最大最小值之差，因为最终目的一定是让最小值加到跟最大值一样大，所以干脆直接一步到位，将不是`max_index`的下标所对应的数都加`diff`，同时计数器也加`diff`，其余操作跟暴力法一致，最终计数器的值就是我们要求的值

- 利用排序

  排序之后可以以时间复杂度`O(1)`得到最大最小值之差。因为最终目的一定是让最小值加到跟最大值一样大，所以第一次加完之后`a[0] == a[n - 1]`，此时`a[n - 2]`变最大值，而最小值仍然是`a[0]`，依次的，每加完一次，最大值就会变成`a[n - 3]、a[n - 4]、a[n - 5]、...`，而每次加的步长也很好求，就是最大最小值之差，而这些步长的累加就是我们要求的值

- 动态规划

  - 记录步长，并作累加。根据前一步累加的步长，更新自身的值

    ```c++
    int minMoves(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int i = 1;
        int ret = 0;
        int size_nums = nums.size();
        for(; i < size_nums; i++){
            int diff = ret + nums[i] - nums[i - 1];
            nums[i] += ret;
            ret += diff;
        }
        return ret;
    }
    ```

  - 记录步长，并作累加

    ```c++
    int minMoves(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int i = 1;
        int size_nums = nums.size();
        int diff = 0;
        int ret = 0;
        for(; i < size_nums; i++){
            // 步长累加
            diff = diff + nums[i] - nums[i - 1];
            ret += diff;
        }
        return ret;
    }
    ```

- 数学

  核心思想：将除了一个元素之外的全部元素`+1`，等价于将该元素`-1`

  所以只需要将所有元素减到跟最小值一样，记录步长即可

  用数学语言表述：`求得数组最小值为min_num，对于数组中的每个数i，累加(i - min_num)`

  - 可以先累加数组中所有的数，再减去最小值乘数组长度（一次for循环累加数的同时找到最小值）

    这样做可能会导致整型溢出

  - 也可以即时求出每个数与最小值之间的差值，然后做累加（一次for循环找到最小值，一次for循环做累加）

    这样做就能解决整型溢出的问题了

### [455. Assign Cookies](https://leetcode-cn.com/problems/assign-cookies/)

- 排序 + 贪心

### [463. Island Perimeter](https://leetcode-cn.com/problems/island-perimeter/)

- 迭代

  叠加岛屿格子符合题意的边

- 深度优先

  找到一个岛屿格子，从该格子开始向四周递归蔓延叠加符合题意的边

  需要注意重复遍历的问题

### [485. Max Consecutive Ones](https://leetcode-cn.com/problems/max-consecutive-ones/)

- 一次遍历，注意要考虑边界情况

### [495. Teemo Attacking](https://leetcode-cn.com/problems/teemo-attacking/)

- 法一

  计算理想情况下所有的秒数，再减去多算的

  ```c++
  int findPoisonedDuration(vector<int>& timeSeries, int duration) {
      int sTimeSeries = timeSeries.size();
      // 为了防止整型溢出，我们做了如下处理：
      unsigned long long ret = (unsigned long long)sTimeSeries * (unsigned long long)duration;
      for (int i = 1; i < sTimeSeries; i++) {
          if (timeSeries[i] - timeSeries[i - 1] < duration) {
              ret -= duration - (timeSeries[i] - timeSeries[i - 1]);
          }
      }
      return ret;
  }
  ```

- 法二

  逐步计算累加

  ```c++
  int findPoisonedDuration(vector<int>& timeSeries, int duration) {
      int ret = 0;
      for (int i = 1; i < timeSeries.size(); i++) {
          ret += min(timeSeries[i] - timeSeries[i - 1], duration);
      }
      ret += duration;
      return ret;
  }
  ```

### [496. Next Greater Element I](https://leetcode-cn.com/problems/next-greater-element-i/)

- 暴力法

- 单调栈 + hashmap

  遍历大的数组，如果栈内没有元素或者栈顶元素大于等于当前遍历到的数，则入栈，反之，栈内所有比当前遍历到的数小的元素都出栈，并将他们映射到当前遍历到的数，映射关系写到hashmap中。然后遍历小的数组，直接从hashmap取值，取不到则为`-1`，将结果放入列表中，该列表即为最终解。这里我们利用了栈内元素的单调性：**栈中的元素从栈顶到栈底是单调不降的**。

### [506. Relative Ranks](https://leetcode-cn.com/problems/relative-ranks/)

- 排序 + 哈希表记录排名
- 排序 + 二分查找法查找排名
- 利用map的自动排序将原数组的元素及其下标记录到map中并按照元素大小倒序。创建一个固定大小数组x（大小为原数组大小），遍历map，根据元素下标将排名信息逐一写回到数组x的对应下标位置中

## 树

### [94. Binary Tree Inorder Traversal](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

- 递归

  每次遍历节点都先遍历它的左孩子再遍历自己再遍历它的右孩子

- 迭代

  首先明确肯定是一层一层遍历树的左孩子的，直到遍历不下去之后再遍历自己，然后再遍历自己的右孩子的左孩子，如此往复即可

- Morris 中序遍历

  核心思想就两点：

  - 不想使用额外的栈来保存下一步应该访问什么结点，那么我们就需要手动找到并指定结点的下一个结点，比方说现在是中序遍历，那么结点的上一个结点一定是左孩子的最右的结点，那么就可以让左孩子最右结点指向当前结点。遍历的时候如果左孩子最右结点已经指向它的后一个结点（要清楚本来左孩子最右结点是空指针）说明当前结点的左子树已经遍历完，下一步就是遍历自己，再下一步就是遍历右子树；而如果左孩子最右结点是空指针，则表示已经找到了左孩子的最右结点，需要将它指向自己，并且自己要向左一步，来找到左孩子的左孩子的最右结点，如此往复。（巧妙的点在于当我们遍历完左子树时所处的结点一定是左孩子的最右结点，而此时最右结点又已经被我们指向了自己，这就是遍历的过程中能够在遍历完左子树之后直接遍历自己然后下一步能够遍历自己的右子树的原因）

  - 如何走出循环？明确每次遍历完自己之后一定就是遍历右子树了，而右子树总有一天会是空指针，当右子树为空指针时说明遍历就已经完成了

### [100. Same Tree](https://leetcode-cn.com/problems/same-tree/)

- 深度优先遍历
- 广度优先遍历

### [101. Symmetric Tree](https://leetcode-cn.com/problems/symmetric-tree/)

- 镜像
  - 难点在于构造一个函数：isSymmetric(left, right)；在这个函数中对比left和right两棵树是否对称（结构一样，同一位置的结点值一样），具体做法可以是在函数中维护一个queue，每次push到这个队列的两棵树都是同一位置的（位置对称），这样每次从队列弹出的两棵树就是位置对称的，再进行比较即可
  - 在主函数中调用上述函数：isSymmetric(root, root)；这就是镜像的技巧了，将root当作左右两棵树传入即可得知他是否对称

- 递归

  从树的根结点开始自上向下判断（位置对称的结点值是否相等）

  - 难点在于构造一个函数，该函数能比较从总体上来看的整棵树的每一个对称的部位（意思就是两个结点或者说两棵树相距甚远，但他们是在总体上来看的整棵树的对称的两个部位）具体代码：<font color="green">**return left->val == right->val && check(left->left, right->right) && check(left->right, right->left);**</font>
  - 另一个技巧是异或运算符（^）的应用，由于左右必须对称，因此左边和右边只要状态不一样就返回false，具体代码：<font color="green">**if(left == nullptr ^ right == nullptr) return false;**</font>

- 递归+镜像
  
  - 本质上就是将上述的两种方法结合的一种方法，只不过是在上述递归的基础上调用函数时由原先的function(root->left, root->right)变为function(root, root)，当然函数内部的一些细节还需要进行一些修改

### [104. Maximum Depth of Binary Tree](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)

- 深度优先

  每次递归返回左树和右树深度的较大值加1

- 广度优先

  每遍历完树的一层，层数加1

### [107. Binary Tree Level Order Traversal II](https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/)

- 迭代，可以选择对树的每一层做一个for循环遍历
- 递归，用pair结构记录每一层的结点及其高度从而判断是否要向数组头部插入新数组，之后再向数组头部的数组添加元素，然后左右子树递归

### [108. Convert Sorted Array to Binary Search Tree](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/)

- 中序遍历，选择中间位置右边或左边的数字作为根节点

### [110. Balanced Binary Tree](https://leetcode-cn.com/problems/balanced-binary-tree/)

- 自顶向下递归，关键在于写出一个计算高度的函数，利用这个函数计算左右子树的高度之后计算两树高度差从而做出判断，并递归判断子树的子树

- 自底向上递归，关键点：
  - 后序遍历的实现（也就是先来子树，后来自己）（其实主要就是代码顺序的问题）
  - 写出计算高度的函数
  - 自底向上地判断两棵子树的高度差，如果超过1，则显然不是平衡二叉树，因此返回-1（这里注意-1的判断条件，应该是子树先判断，如果子树都是-1了，那就不需要其他判断了，结果一定是-1），否则返回自身高度（子树最大高度 + 1）

### [111. Minimum Depth of Binary Tree](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)

- 深度优先
  - 难点在于维护一个全局的最小高度（其实就是每次递归下去都返回当前高度与全局最小高度的最小值即可）
- 广度优先
  - 难点在于动态保存迭代过程中每一个结点本身及其高度（这里用一个pair结构实现）
  - 维护一个queue，每次都取出队列中的结点，如果他没有子树了，就返回其高度，否则就向队列中添加pair（子树及其自身（是当前结点自身，不是当前结点的子树）的高度 + 1）

### [112. Path Sum](https://leetcode-cn.com/problems/path-sum/)

- 递归
  - 技巧在于每一次递归都将总合sum减去当前结点的值，并将减去之后的值传入函数自顶向下递归，最后如果是符合题意的一棵树，那减到最后的值必定会等于某一个叶子结点的值

- 迭代
  - 技巧在于每次迭代都保存该结点的上层结点的值
  - 维护两个队列，一个用于保存结点，另一个用于保存该结点的值
  - 最后的判定条件为计算分支走到叶子结点的时候用于保存值的队列中的相应位置的值与该叶子结点的值之和，只要有一个分支通过这样的计算之后的值等于总合sum那就是符合题意的一棵树

### [144. Binary Tree Preorder Traversal](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)

思路同[94. Binary Tree Inorder Traversal](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

Morris 中序遍历的时候不再是手动指定结点的下一个结点了，而是充分利用父节点来过渡到下一个结点（也就是父节点的右孩子）

### [145. Binary Tree Postorder Traversal](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)

思路同[94. Binary Tree Inorder Traversal](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

迭代的时候要注意从底部反上来的时候自己的右孩子的下一个访问结点一定是自己，而自己因为存在右孩子所以又重新进入右孩子访问导致死循环（所以说需要记录右孩子，在访问完右孩子之后访问下一个结点也就是访问自己的时候如果自己的右孩子已经被访问过了就不要再访问了）

另外一种迭代的方法是翻转列表（先按照`本节点 -> 右孩子 -> 左孩子`的顺序遍历，最后翻转结果列表即可）

Morris 中序遍历的解法非常有技巧也非常复杂非常极限，建议从“如何完整构造出一个走完整个后序遍历的路径”的角度去思考（心得：找到中间结点就能找到左右孩子，从而找到整棵树）

### [226. Invert Binary Tree](https://leetcode-cn.com/problems/invert-binary-tree/)

- 递归

  左右子树互换

  - 解法一

    ```c++
    TreeNode* invertTree(TreeNode* root) {
        if(!root) return root;
        TreeNode* temp = root -> left ;
        root -> left = root -> right;
        root -> right = temp;
        invertTree(root -> left);
        invertTree(root -> right);
        return root;
    }
    ```

  - 解法二

    ```c++
    TreeNode* invertTree(TreeNode* root) {
        if(!root) return root;
        TreeNode* left = invertTree(root -> left);
        TreeNode* right = invertTree(root -> right);
        root -> left = right;
        root -> right = left;
        return root;
    }
    ```



### [235. Lowest Common Ancestor of a Binary Search Tree](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)

首先要注意它是一棵二叉搜索树（意味着他有序）

- 两次遍历

  封装一个能获取从根节点到目标节点所经过的所有节点的数组的函数，获取从根节点到q和从根节点到p的两条路径，从前向后遍历两条路径找出最后一个共同节点

- 一次遍历

  假设当前节点root，存在三种情况：1、q < root < p；2、q < root && p < root；3、q > root && p > root；如果是第一种情况的，显然当前节点就是我们想要的节点，第二种的话目标节点应该在当前节点的左侧，第三种的话目标节点应该在当前节点的右侧。按照这个逻辑，我们就可以写代码了，下面是两种实现的方法：

  - 递归

    ```c++
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        return (root->val - q->val) * (root->val - p->val) <= 0 ? root : lowestCommonAncestor(root->val - q->val > 0 ? root->left: root->right, p, q);
    }
    ```

  - while循环

    ```c++
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        while(root){
            if(root->val < q->val && root->val < p->val) root = root->right;
            else if(root->val > q->val && root->val > p->val) root = root->left;
            else break;
        }
        return root;
    }
    ```

### [257. Binary Tree Paths](https://leetcode-cn.com/problems/binary-tree-paths/)

- 深度

  递归来传递当前结点形成的路径的信息

- 广度

  需要注意路径字符串是如何与每一条路径一一对应的（维护双队列）

### [404. Sum of Left Leaves](https://leetcode-cn.com/problems/sum-of-left-leaves/)

- 深度优先
  - 递归
  - 迭代
- 广度优先
  - 迭代

### rubiks_rectangle

有矩阵：

```
k1 k2 k3 k4
k8 k7 k6 k5
```

现有三种变换：

- row exchange

  ```
  k1 k2 k3 k4		->		k8 k7 k6 k5
  k8 k7 k6 k5				k1 k2 k3 k4
  ```

- right circular shift

  ```
  k1 k2 k3 k4		->		k4 k1 k2 k3
  k8 k7 k6 k5				k5 k8 k7 k6
  ```

- middle clockwise rotation

  ```
  k1 k2 k3 k4		->		k1 k7 k2 k4
  k8 k7 k6 k5				k8 k6 k3 k5
  ```

假设对于给定矩阵，一定能通过上述三种变换并且在有限步骤之内变成另一个给定矩阵，求出这个过程中的最短步长是多少？

- dfs

  ```python
  # 变换1
  def c1(arr):
      for i in range(4):
          temp = arr[0][i]
          arr[0][i] = arr[1][i]
          arr[1][i] = temp
      return arr
  # 变换2
  def c2(arr):
      for i in range(2):
          temp = arr[i][3]
          for j in range(3, 0, -1):
              arr[i][j] = arr[i][j - 1]
          arr[i][0] = temp
      return arr
  # 变换3
  def c3(arr):
      temp = arr[0][1]
      arr[0][1] = arr[1][1]
      arr[1][1] = arr[1][2]
      arr[1][2] = arr[0][2]
      arr[0][2] = temp
      return arr
  def check(arr1, arr2):
      for i in range(2):
          for j in range(4):
              if(arr1[i][j] != arr2[i][j]):
                  return False
      return True
  
  # dfs
  def dfs(start_state, final_state):
      q = [(start_state, 1)]
      while q:
          state_temp, counter = q[0]
          del q[0]
          state_temp_copy = deepcopy(state_temp)
          if(check(state_temp_copy, final_state)):
              return counter
          new_state = c1(state_temp_copy)
          q.append(tuple((new_state, counter + 1)))
          state_temp_copy = deepcopy(state_temp)
          new_state = c2(state_temp_copy)
          q.append(tuple((new_state, counter + 1)))
          state_temp_copy = deepcopy(state_temp)
          new_state = c3(state_temp_copy)
          q.append(tuple((new_state, counter + 1)))
      return -1
  ```

### [501. Find Mode in Binary Search Tree](https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/)

- 递归或迭代遍历树配合哈希表存放结点出现的频数（由于是有序二叉树，所以中序遍历的顺序是非递减的，因此还可以通过维护变量来代替哈希表，从而降低空间复杂度）
- Morris遍历树配合哈希表存放结点出现的频数（由于是有序二叉树，所以中序遍历的顺序是非递减的，因此还可以通过维护变量来代替哈希表，从而降低空间复杂度）

## 字符串

### [13. Roman to Integer](https://leetcode-cn.com/problems/roman-to-integer/)

- 模拟法

### [20. Valid Parentheses](https://leetcode-cn.com/problems/valid-parentheses/)

- 栈和哈希表

  注意括号必须以正确的顺序闭合，并且交叉闭合是不允许的，如`"([)]"`是不允许的

### [28. Implement strStr()](https://leetcode-cn.com/problems/implement-strstr/)

- 暴力匹配

  要注意在匹配过程中主串下标可能会越界

- KMP

### [58. Length of Last Word](https://leetcode-cn.com/problems/length-of-last-word/)

- 尾指针

### [67. Add Binary](https://leetcode-cn.com/problems/add-binary/)

- 先将两个数转成10进制，相加之后再转成2进制字符串

  需要注意整型溢出

  该方法事实上不健壮，设想如果给定的二进制字符串非常长，那么它肯定是会导致整型溢出的

- 模拟

  逢二进一

  ```java
  public String addBinary(String a, String b) {
      StringBuffer ans = new StringBuffer();
      int n = Math.max(a.length(), b.length()), carry = 0;
      for (int i = 0; i < n; ++i) {
          carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
          carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
          ans.append((char) (carry % 2 + '0'));
          carry /= 2;
      }
      if (carry > 0) {
          ans.append('1');
      }
      ans.reverse();
      return ans.toString();
  }
  ```

- 位运算

  首先两个二进制数相加，如果不考虑进位的话其实就是一个异或运算，如果在此基础上再考虑上进位的话就是相加的真正结果

  我们可以设计这样的算法来计算：

  ​	1、把 `a` 和 `b` 转换成整型数字 `x` 和 `y`，在接下来的过程中，`x` 保存结果，`y` 保存进位。

  ​	2、当进位不为 `0` 时：
  ​			计算当前 `x` 和`y`的无进位相加结果：`answer = x ^ y`
  ​			计算当前 `x` 和 `y` 的进位：`carry = (x & y) << 1`
  ​			完成本次循环，更新 `x = answer`，`y = carry`

  ​	3、退出循环，返回 `x` 的二进制形式

  ```python
  def addBinary(self, a, b) -> str:
      x, y = int(a, 2), int(b, 2)
      while y:
          answer = x ^ y
          carry = (x & y) << 1
          x, y = answer, carry
      return bin(x)[2:] # '0bxxxx' -> 'xxxx'
  ```



### [125. Valid Palindrome](https://leetcode-cn.com/problems/valid-palindrome/)

- 先去除非数字字母的字符，最后reverse()对比两个字符串是否相等
- 双指针
  - 先去除非数字字母的字符，然后双指针一个从前往后，一个从后往前比较字符是否相等
  - 直接比较前后对称字符是否相等，遇到非数字字母的字符就跳过

### [171. Excel Sheet Column Number](https://leetcode-cn.com/problems/excel-sheet-column-number/)

- 26进制加减乘除法

### [168. Excel Sheet Column Title](https://leetcode-cn.com/problems/excel-sheet-column-title/)

需要注意的是excel中的A-Z是1-26，不是0-25

- 26进制加减乘除法

  - 对26特殊处理

    其他数字照常取余，唯独26取余之后变0，因此26特殊处理让他直接变“Z”，此外26 / 26 = 1，这个1其实是多余的，因此在传入的数字是26的倍数的时候除了上述的特殊处理，还需要将该数减1（这对于后续是没有影响的，因为在后续做进位的时候会把上一位的剩余部分舍弃掉）

  - 直接将1-26映射到0-25

    假设传入的数为n，在运算之前直接做一步n--即可，这样还省去了对26特殊处理这一步操作

### [毕导密码破解](https://www.bilibili.com/video/BV1Wv411y7UA?t=132)

首先文本中的每一个字母都向右平移了若干个字母，已知正常情况下英文语句中 a-z 每个字母出现的频率，要求破解密文

- 暴力枚举

  遍历不同平移长度，每一次都统计字母词频，与已知字母词频做残差平方和运算，找到结果最小的那一次平移的位数，再将原文本向左平移该位数长度即可破解密文

### [205. Isomorphic Strings](https://leetcode-cn.com/problems/isomorphic-strings/)

- 哈希表

  只需第一个字符串中字符对应到第二个字符串中字符，并且由于是双向的，因此类似于镜像，需要第二个字符串中字符也对应第一个字符串的字符，操作方式：

  ```c++
  bool isSymmetric(string s, string t){...}
  bool result(string s, string t){
  	return isSymmetric(s, t) && isSymmetric(t, s);
  }
  ```

- 第三方翻译

  举个例子：法语翻译为英语，日语翻译为英语，再对照英文意思是否相同即可

  - 维护一个数组

    ```c++
    string isSymmetric(string s){
        string res;
        int temp[128]{0};
        int length = s.length();
        for(int i = 0; i < length; i++){
            if(temp[s.at(i)] == 0){
                // 这里通过i表示每个字母的顺序，当然也可以用count计数器来表示
                temp[s.at(i)] = i + 1;
            }
            res += temp[s.at(i)];
        }
        return res;
    }
    bool isIsomorphic(string s, string t) {
        return isSymmetric(s) == isSymmetric(t);
    }
    ```

  - 维护两个数组

    ```c++
    bool isIsomorphic(string s, string t) {
        int s_v[128]{0}, t_v[128]{0};
        int s1 = s.length(), s2 = t.length();
        if(s1 != s2) return false;
        if(s1 == 0) return true;
        for(int i = 0; i < s1; i++){
            // 相较于维护一个数组，这里直接比较两个数组中所保存的顺序的值，如果对不上顺序了，就返回false
            if(s_v[s.at(i)] != t_v[t.at(i)]) return false;
            else{
                if(s_v[s.at(i)] == 0){
                    // 当数组中该字母的顺序为0的时候我们才给它赋值
                    s_v[s.at(i)] = i + 1;
                    t_v[t.at(i)] = i + 1;
                }
            }
        }
        return true;
    }
    ```

### [242. Valid Anagram](https://leetcode-cn.com/problems/valid-anagram/)

c++可以通过c_str()将字符串转const char *c 指针指向型的字符数组；通过sizeof(char p[]) / sizeof(char)计算字符数组的size；通过strlen(char *p)计算指针指向型数组的size；通过strcmp(xxx, xxx)比较两个字符数组的大小（这里指针指向型和数组型的数组之间也可以相互比较。它的效果类似于减操作，返回0表示两个字符数组一样，-1表示第二个大，1表示第一个大）；通过sort函数对字符数组进行排序；由于const型数组无法改变其本身，因此无法使用sort函数，所以我们还需要通过strcpy()，将const型数组copy到一个新的数组中（注意这个新的数组需要指定大小，例如：char *c = new char[str.size()]）

- 维护一个哈希表（int table[26] = {0}）

  将第一个字符串中的字符在哈希表中对应位置都加1，遇到重复的字符就累加上去，第二个字符串中的字符在哈希表中对应位置都减1，遇到重复的字符就再减1，上述操作通过一个for循环完成，之后再通过一个for循环检验哈希表是否全为0即可得出结论

- 维护一个哈希表优化版

  两个for循环，第一次还是像上面一样去加1，只不过这次只是对其中某一个字符串进行遍历，第二个for循环对另一个字符串进行操作，也是像上面一样去减1，由于两个字符串长度必相等（不相等的直接就返回false了），因此如果是符合题意的字符串在第二次循环最后一定能将哈希表全部变为0，如果不符合则一定存在某些位置大于0某些位置小于0，且一旦小于0了就不可能再大于等于0了。利用这个特性在第二次遍历的时候一旦发现小于0的就直接返回false

- 先排序，后比较

  直接将两个字符串变成字符数组，排序之后比较两个字符数组是否相等即可得出结论

### 计算字符串中字符的个数

- 使用计数器

- 递归

- 双指针

  ```c
  // 举例双指针法
  int my_strlen(char* c){
  	char* p = c;
  	while(*p != '\0'){
  		p++;
  	}
  	return p - c;
  }
  ```

### 翻转字符串

- 双指针

  头指针和尾指针同时向中间逼近，在逼近的过程中互换它们所指向的元素，直到头指针大于等于尾指针

- 递归

  ```c
  void reverse_string(char* arr){
  	if(*arr == '\0') return;
  	int size = strlen(arr);
  	char temp = arr[0]; // 暂存首字符
  	arr[0] = arr[size - 1]; // 将尾字符移到首部
  	arr[size - 1] = '\0'; // 尾字符原先的位置变成'\0'
  	reverse_string(arr + 1); // 将中间的字符串送入下一层递归
  	arr[size - 1] = temp; // 递归结束，将原先暂存的首字符放到尾部
  	arr[size] = '\0'; // 在字符串最后放一个'\0'
  }
  ```

### 左旋字符串

- 暴力求解

  ```c
  void left_move(char* arr, int k){
      assert(arr);
      int i = 0; 
      int len = strlen(arr);
      for(i = 0; i < k; i++){
          char temp = *arr;
          int j = 0;
          for(j = 0; j < len - 1; j++){
              *(arr + j) = *(arr + j + 1);
          }
          *(arr + len - 1) = temp;
      }
  }
  ```

- 三步翻转法

  ```c
  // 假设abcde要左旋两个字符变成cdeab
  // 第一步：拆， ab cde
  // 第二步：每一部分都翻转， ba edc
  // 第三步：整体翻转，cdeab
  void reverse(char* left, char*right){
      assert(left);
      assert(right);
      while(left < right){
          char temp = *left;
          *left = *right;
          *right = temp;
          left++;
          right--;
      }
  }
  void left_move(char* arr, int k){
      assert(arr);
      int len = strlen(arr);
      assert(k <= len);
      reverse(arr, arr + k - 1);
      reverse(arr + k, arr + len - 1);
      reverse(arr, arr + len - 1);
  }
  ```

### 左旋字符串进阶（判断一个字符串是否是另一个字符串的左旋字符串）

- 暴力枚举

  首先写出一个能让字符串左旋的函数，然后每旋转一步就比较一次

- 追加字符串之后判断是否是子字符串

  ```c
  #include<stdio.h>
  #include<string.h>
  int is_left_move(char* str1, char* str2){
  	int len = strlen(str1);
      int len2 = strlen(str2);
      if(len != len2) return 0; // 需要注意的一个点是：str1和str2的长度如果不一致，那必然不是左旋字符串，需要直接判定为非左旋字符串，否则的话像"abcde"和"cde"这样的明明不是左旋字符串的也会被判定为左旋字符串
      // 1. 在str1字符串中追加一个str1字符串
      strncat(str1, str1, len); // 需要注意的是，如果是追加字符串，必须要保证该函数第一个参数（也就是源字符串的容量要大于等于两个字符串的长度之和）
      // 2. 判断str2指向的字符串是否是str1指向的字符串的子串
      char* ret = strstr(str1, str2);
      if(ret == NULL){
          printf("%s\n", "没找到");
          return 0;
      }else{
          printf("子字符串是：%s\n", ret);
          return 1;
      }
  }
  ```

### [290. Word Pattern](https://leetcode-cn.com/problems/word-pattern/)

- 维护两个map用于key和value之间互相的映射

  难点在于边界的处理

### [345. Reverse Vowels of a String](https://leetcode-cn.com/problems/reverse-vowels-of-a-string/)

- 双指针

  小心野指针

### [383. Ransom Note](https://leetcode-cn.com/problems/ransom-note/)

- 桶
  - 数组
  - 哈希表

### [387. First Unique Character in a String](https://leetcode-cn.com/problems/first-unique-character-in-a-string/)

- 使用哈希表存储每个字母的频数

  技巧：字符先后顺序的信息可以去源字符串找

- 使用哈希表存储索引

  最后求出哈希表中有效索引的最小值即可

- 使用队列维护只出现一次的字符

  最后返回队列第一个元素即可

### KMP

自己写的算法：

```java
// next数组
public static List<Integer> nextNum(String s){
    List<Integer> next = new ArrayList<>();
    next.add(0);
    int j = 0;
    int i = 1;
    while(i < s.length()){
        if(s.charAt(i) == s.charAt(j)){
            next.add(j + 1);
            j++;
        }else{
            if(j != 0){
                j = next.get(j - 1);
                while(j != 0 && s.charAt(j) != s.charAt(i)){
                    j = next.get(j - 1);
                }
            }
            if(s.charAt(j) == s.charAt(i)){
                next.add(next.get(j) + 1);
                j++;
            }else{
                next.add(0);
            }
        }
        i++;
    }
    return next;
}
// KMP
public static boolean isSubString(String pattern, String s){
    List<Integer> next = nextNum(pattern);
    int i = 0;
    int j = 0;
    while(i < pattern.length() && j < s.length()){
        if(pattern.charAt(i) == s.charAt(j)){
            i++;
        }else{
            if(i != 0){
                i = next.get(i - 1);
                while(i != 0 && pattern.charAt(i) != s.charAt(j)){
                    i = next.get(i - 1);
                }
            }
            if(pattern.charAt(i) == s.charAt(j)) {
                i++;
            }
        }
        j++;
    }
    return i == pattern.length();
}
```

### [389. Find the Difference](https://leetcode-cn.com/problems/find-the-difference/)

- 哈希表

- 计数

  先记录长度小的字符串中字符的频数，在此基础上减去另一个字符串中字符的频数，一旦出现频数为负的字符，该字符就是我们要求的字符

- 求和

  两个字符串求和之后相减即可

- 位运算

  原理跟求和一样

### [392. Is Subsequence](https://leetcode-cn.com/problems/is-subsequence/)

- 双指针

- 动态规划

  目标是记录大字符串每个字符后面所有字符出现的绝对下标位置

  核心思想：大字符串后每个字符出现的位置要么就是当前下标，要么就是以后面的字符为基准当前字符的下标（上面这句话就能构建转移方程），而且如果是这么来做的话就需要从大字符串尾部开始往前遍历做记录

  我们使用一个二维数组来维护这些下标

  此后遍历小字符串，我们能以时间复杂度`O(1)`直接找到每个字符在大字符串中的位置，如果有一个字符找不到下标就直接返回`false`

### [409. Longest Palindrome](https://leetcode-cn.com/problems/longest-palindrome/)

- 贪心

  核心思想：统计所有字符出现的频数，然后计算以每个频数本身为最大界限的最大偶数，相加，如果出现频数为奇数的情况，最终的加和结果需要再加一（回文字符串中只能出现0或1个频数为奇数的字符，如：`abba`、`abcccba`）

  - 使用hash表存储频数
  - 使用数组存储频数

### [412. Fizz Buzz](https://leetcode-cn.com/problems/fizz-buzz/)

- 模拟法

  先判断能不能被3和5整除，再判断能不能被3整除，再判断能不能被5整除

- 字符串拼接

  如果能被3整除，则拼入`Fizz`，如果能被5整除，则拼入`Buzz`

  这种做法比起上面的模拟法更加优雅，更好扩展

- 二叉树

  在字符串拼接法的基础上，将`3`对应`“Fizz”`，`5`对应`“Buzz”`这些条件放入有序map中维护，使得映射关系更加自由

- 哈希表

  核心思想：在`1 <= i <= n`的范围内，所有3的倍数的数都追加`"Fizz"`，所有5的倍数的数都直接追加`"Buzz"`，配合哈希表，我们就可以得到`1 - n`范围内所有3或5的倍数的数对应的字符串是哪个了

### [415. Add Strings](https://leetcode-cn.com/problems/add-strings/)

- 模拟法

  按照算盘加减法来写即可

### [434. Number of Segments in a String](https://leetcode-cn.com/problems/number-of-segments-in-a-string/)

- 内置函数

- 朴素遍历

  根据事物客观现象直接写出代码

### [459. Repeated Substring Pattern](https://leetcode-cn.com/problems/repeated-substring-pattern/)

- 枚举

  需要注意遍历枚举的时候起始下标应该要在`[1, n/2]`的范围之内（n为字符串长度）

  还要注意枚举的字符串m应该满足`n % m == 0`（n为字符串长度）

- 字符串匹配

  可以证明得到`s 若为 s + s 的子串（s在s + s中的起始位置不应该是下标0或n（n为s的长度）），则s满足题目要求`这个命题的充分性和必要性

  之后就可以给出代码：

  ```c++
  bool repeatedSubstringPattern(string s) {
      return (s + s).find(s, 1) != s.size(); // find(s, 1)中的1表示从字符串下标为1的地方开始匹配
  }
  ```

- kmp

  思路跟`字符串匹配`方法的一样，只不过换用了kmp去匹配

  需要注意的是不能使用一般的kmp去处理，因为`模式字符串s在s + s中的起始位置不应该是下标0或n（n为s的长度）`，言下之意还需要修改kmp算法，让模式字符串的起始位置不为0或n

- 优化的kmp

  易通过反证法证得：

  - `满足题意的字符串的next数组fail中最后一个元素fail[n - 1] != -1（言下之意最后一个字符一定存在公共前缀）`

  - `最短公共前后缀（类似于"aabaabaab"中的"aab"）的长度m一定满足：n % m == 0`。

    思考一下这里的最短公共前后缀该怎么求？其实很简单：`若是满足题意的字符串，则最短公共前后缀 = 字符串长度 - (最长公共前缀 + 1（当前字符自身长度）)，由于官解的next数组fail中每个元素的初始值为-1，因此按照-1为初始值来求的话next数组中存放的元素其实就是当前下标字符的最长公共前缀，也就是说fail[n - 1]就是字符串最后一个字符的最长公共前缀，由此可以得到：最短公共前后缀x = s.size() - (fail[n - 1] + 1)`

  经过分析可以给出代码：

  ```c++
  bool kmp(const string& pattern) {
      int n = pattern.size();
      // next数组
      vector<int> fail(n, -1);
      for (int i = 1; i < n; ++i) {
          int j = fail[i - 1];
          while (j != -1 && pattern[j + 1] != pattern[i]) {
              j = fail[j];
          }
          if (pattern[j + 1] == pattern[i]) {
              fail[i] = j + 1;
          }
      }
      // 判定条件
      return fail[n - 1] != -1 && n % (n - fail[n - 1] - 1) == 0;
  }
  
  bool repeatedSubstringPattern(string s) {
      return kmp(s);
  }
  ```

### [482. License Key Formatting](https://leetcode-cn.com/problems/license-key-formatting/)

- 倒过来遍历，遇到`'-'`就跳过，反之就将字符加进字符串尾部，直到加字符的次数等于k次就在字符串尾部加`'-'`，最后用一个while循环去掉字符串尾部所有`'-'`，再将字符串反转即可。整个过程相当于将字符串中`'-'`全部去掉，然后从尾部向前以长度k来分割字符串，分割符号为`'-'`。需要注意在访问字符串的时候不要越界。

### [500. Keyboard Row](https://leetcode-cn.com/problems/keyboard-row/)

- 暴力解法

  键盘字母与行数的对应关系可以存放在集合或者数组中

## 链表

### [21. Merge Two Sorted Lists](https://leetcode-cn.com/problems/merge-two-sorted-lists/)

- 迭代

- 递归

  - 法一

    ```c++
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode* temp = nullptr;
        if(l1 && l2){
            if(l1 -> val > l2 -> val){
                temp = l2;
                l2 = l2 -> next;
            }else{
                temp = l1;
                l1 = l1 -> next;
            }
            temp -> next = mergeTwoLists(l1, l2);
        }else if(l1){
            temp = l1;
        }else if(l2){
            temp = l2;
        }
        return temp;
    }
    ```

  - 法二

    ```c++
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        if(l1 == nullptr){
            return l2;
        }else if(l2 == nullptr){
            return l1;
        }else if(l1 -> val > l2 -> val){
            l2 -> next = mergeTwoLists(l1, l2 -> next);
            return l2;
        }else{
            l1 -> next = mergeTwoLists(l1 -> next, l2);
            return l1;
        }
    }
    ```

### [83. Remove Duplicates from Sorted List](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/)

- 单指针单次遍历

### [141. Linked List Cycle](https://leetcode-cn.com/problems/linked-list-cycle/)

- 将链表结点放入集合中，如果遍历到相同元素，则说明有环

- 快慢指针（弗洛伊德判圈算法）

  慢指针一次走一步，快指针一次走两步，如果快指针探到底了，说明不是环，如果快慢指针相遇了，则说明有环

### [160. Intersection of Two Linked Lists](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)

- 对A链表的每一个结点，遍历B链表，检查B链表是否存在与A链表中某个结点相同的结点

- 将A链表结点放入集合，遍历B链表，检查B链表是否存在已经在集合中的结点

- 双指针法

  创建pA和pB，分别指向链表A和B，让他们向后遍历，如果pA遍历完了，就让pA指向B链表，如果pB遍历完了，就让pB指向A链表，再继续向后遍历，此时如果有交点，则pA和pB一定能一起遍历到该交点。原理其实很简单，只要让两个指针在交点之前走过一样的长度即可，该长度就是两个链表交点之前的结点数量之和

### [203. Remove Linked List Elements](https://leetcode-cn.com/problems/remove-linked-list-elements/)

- 设置哨兵指针（头指针）sentinel，方便删除结点

- 使用指针的指针，代码：

  ```c++
  ListNode* removeElements(ListNode* head, int val) {
      for(ListNode **temp = &head; (*temp); ){
          if((*temp)->val != val){
              temp = &((*temp)->next);
          }else{
              ListNode *del = *temp;
              *temp = (*temp)->next;
              delete del;
          }
      }
      return head;
  }
  ```

   (\*p) 是当前节点的指针，也就是前一个节点的 next 指针，这两者是等价的，对 (*p) 赋值，就是对前一个节点的 next 指针赋值，然后，(\*p)->next 是当前节点的 next 指针的值，所以，\*temp = (\*temp)->next;的效果就是，把前一个节点的 next 指针赋值成了当前节点的 next 指针的值，也就是删除了当前节点

### [206. Reverse Linked List](https://leetcode-cn.com/problems/reverse-linked-list/)

- 迭代

  事先保存当前节点的下一个节点，之后修改当前节点的指向，再反过来处理保存的下一个节点，直到末尾，如下：

  ```c++
  ListNode* reverseList(ListNode* head) {
  	// 将反过来的链表暂时保存在这里
      ListNode *pre = nullptr;
      while(head){
          // 保存当前节点的下一个节点
          ListNode *temp = head->next;
          // 修改当前节点的指向
          head->next = pre;
          // 将反过来的链表保存到外部的变量
          pre = head;
          // 继续处理之前保存的下一个节点
          head = temp;
      }
      return pre;
  }
  ```

  迭代由于是从前往后的，因此需要保存当前节点的下一个节点，避免在修改当前节点指向的时候丢失后面的所有节点

- 递归

  从最后一个节点处开始思考，每一次返回给上一层递归的链表都是已经处理好的反过来的链表，这样到最上层的递归的时候就能形成反过来的链表了，如下：

  ```c++
  ListNode* reverseList(ListNode* head) {
      // 终止条件
      if(!head || !head->next) return head;
      // 从下一层递归中返回的已经处理好的反过来的链表（显然他是从尾部开始向前一步一步反过来的）
      ListNode *pre = reverseList(head->next);
      // 由于后面的那一串链表都已经反过来了，我们只需要在已经反过来的后面的链表前面添加经过处理的当前的节点即可
      // 下面两行代码就可以改变节点的指向
      head->next->next = head;
      // 注意，这一步是必须的，不然最终会导致链表循环
      head->next = nullptr;
      return pre;
  }
  ```

  递归由于是从后往前的，因此不再需要考虑当前节点的下一个节点，而且当前节点的下一个节点的指向的改变也不会影响当前节点指向下一个节点的这个“指向”，所以我们总能从当前节点找到后面那一段链表，不存在链表丢失的情况

### [234. Palindrome Linked List](https://leetcode-cn.com/problems/palindrome-linked-list/)

- 头指针和尾指针

  由于只是比较node的val，不需要比较node的指向，因此可以将node的val放入一个数组，维护一个头指针一个尾指针，头指针向后尾指针向前，并比较指向的node的val，停止的条件是头指针index大于等于尾指针index，如果有一次val不等的，就直接返回false，如果循环结束后都没有返回false的话就返回true

- 递归

  我们希望做到这样：有一个全局的头指针，直到递归到最后一个节点之前都不动，然后随着最后一层递归向上返回的时候他开始向后一步一步移动，如此一来就可以进行对称比较了

  想要做到这样我们只需要做到这两步：

  - 声明一个全局的头指针

  - 递归代码写完之后再写全局头指针移动的代码，如下：

    ```c++
    class Solution {
        // 全局头指针
        ListNode *frontPoint;
    public:
        bool recursivelyCheck(ListNode *head){
            if(head){
                // 先进入递归，递归到最后一层，再从最后一层开始往前比较节点的值
                if(!recursivelyCheck(head->next)) return false;
                // 比较节点的值
                if(frontPoint->val != head->val) return false;
                // 从递归的最后一层开始随着递归往前返回，全局的头指针就要跟着往后移动
                frontPoint = frontPoint->next;
            }
            // 如果要返回true，有两种情况：1、递归到最后一个node了，再次进入递归方法时传入的是一个空指针，此时不会进入上述if代码块，但是我们不能让这种情况返回false，否则整体递归就会返回false，这是不对的，因此返回true；2、递归返回到了最上层，但是上述if代码块仍旧没有返回false，说明该链表就是一个对称的回文链表，此时就是应该返回true的
            return true;
        }
        bool isPalindrome(ListNode* head) {
            // 初始化全局头指针
            frontPoint = head;
            return recursivelyCheck(head);
        }
    }
    ```

- 快慢指针

  首先通过快慢指针找到链表的中间节点，将链表分成两段，再将后半段链表反转（链表反转请看：[206. Reverse Linked List](https://leetcode-cn.com/problems/reverse-linked-list/)），之后维护两个指针，一个指向第一段头部，一个指向第二段头部，同时往后移动并比较值即可

  通过快慢指针寻找中间节点代码：
  
  ```c++
  ListNode* endOfFirstHalf(ListNode* head) {
      ListNode* fast = head;
      ListNode* slow = head;
      // 由于快指针比慢指针快一倍，因此快指针走到底的时候慢指针正好是走到了中间
      while (fast->next != nullptr && fast->next->next != nullptr) {
          fast = fast->next->next;
          slow = slow->next;
      }
      return slow;
  }
  ```
  
  注意点：1、如果最后在得出结果的同时我们还不希望原链表被修改，还可以再将第二段链表再反转一下，并将其拼接到第一段链表后面

### [237. Delete Node in a Linked List](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/)

删除当前结点可以这么删：

- 更改前结点指向为当前结点的下一个结点，并释放当前结点所占资源
- 假设操作不了前结点，若当前结点不是链表末尾结点，则可以将下一个结点`a`的内容复制到当前结点，并更改当前结点指向为下下个结点，并按需释放`a`结点所占的资源；若当前结点是链表末尾结点，由于不能操作前结点的`next`指针，因此无法做删除

## 栈

### [155. Min Stack](https://leetcode-cn.com/problems/min-stack/)

- 重点在于获取栈中最小值的算法的实现

  事实上只需要维护两个栈s1、s2，s1用于存放真正的数据，s2用于存放每次push的时候push的值与上一次push时s1中元素最小的值对比之后的较小值，相当于存放了每一层的最小值，这样就算执行pop操作，s2pop的还是这一层的最小值，这样的话在获取s2.top()的时候获取的值始终会是当前s1中元素的最小值

### [225. Implement Stack using Queues](https://leetcode-cn.com/problems/implement-stack-using-queues/)

- 两个队列

- 一个队列

  需要事先记录当前队列的长度，将目标值push到队列之后再根据之前记录的长度将原先队列中的元素再次全部push到队列后面即可

## 队列

### [232. Implement Queue using Stacks](https://leetcode-cn.com/problems/implement-queue-using-stacks/)

- 两个栈，使用一个栈为主栈，将一个栈的数据全放入另一个栈即是一个队列

- 两个栈，两个栈都是主栈，代码如下：

  ```c++
  // push的时候直接push到某个栈，并保存栈底元素
  void push(int x) {
      if(s1.empty()) front = x;
      s1.push(x);
  }
  // pop的时候先看另一个栈是否为空，如果不为空就将元素全部放入该栈，并对该栈执行pop()
  int pop() {
      if(s2.empty()){
          while(!s1.empty()){
              s2.push(s1.top()); s1.pop();
          }
      }
      int temp = s2.top();
      s2.pop();
      return temp;
  }
  // peek的时候先看另一个栈是否为空，如果不为空返回栈顶元素即可，如果为空就返回最开始放入数据的栈的栈底元素
  int peek() {
      if(!s2.empty()) return s2.top();
      return front;
  }
  // 判空的时候需要判断两个栈都为空才算为空
  bool empty() {
      return s1.empty() && s2.empty();
  }
  ```

  该方法巧妙且充分地利用了两个栈的空间，思路非常值得学习

## 数

### [7. Reverse Integer](https://leetcode-cn.com/problems/reverse-integer/)

- 数学

  需要注意的是传进来的实参就是int型，因此极限状况下最高位是不可能大于2的，这是一个关键点，因为题目不让用64位整数，所以在向左进位的时候只能提前判断数是否会整型溢出，但是在加末尾数的时候是不需要提前判断的，原因是我们已经得知在极限状况下最高位数不可能大于2，而极限状况下int型数最大值末尾数是7，而7一定是大于2的所以在加末尾数的时候是一定不会溢出的

### [9. Palindrome Number](https://leetcode-cn.com/problems/palindrome-number/)

- 将数翻转后再比较是否与原数相等

  注意整型溢出

- 将数从左向右翻转一半后与另一半比较大小

  注意奇数的处理（翻转后让较大的数除以10）

- 将数转成字符串后使用双指针

### [69. Sqrt(x)](https://leetcode-cn.com/problems/sqrtx/)

- 袖珍计算器算法

  将`sqrt`算式转化为以`e`为底的算式，再计算

  注意： 由于计算机无法存储浮点数的精确值，而指数函数和对数函数的参数和返回值均为浮点数，因此运算过程中会存在误差。例如当 `x = 2147395600`时的计算结果与正确值 `4634046340` 相差 `10^-11` ，这样在对结果取整数部分时，会得到 `4633946339` 这个错误的结果。

  因此在得到结果的整数部分`x`后，我们应当找出`x`和`x + 1`哪一个才是真正的答案。


- 二分查找

  注意整型溢出

- 牛顿迭代

  思路：可以将问题转化为求曲线`f(x) = x ^ 2 + C`在`x轴`的正交点，假设点`(xi, xi ^ xi + C)`在曲线`f(x)`上，则可以求出经过该点且斜率为在曲线`f(x)`上该点的斜率的直线方程（确定一个点，确定斜率，则一定可以求出直线的直线方程），此时根据牛顿迭代求该直线`y(x) = 0`的解即为相较于`xi`更靠近真实解的一个解`xj`，由于牛顿迭代只能无限接近真实解，因此当`xi`和`xj`之差小于`epsilon（这里我们设定为1e-7）`，即可跳出迭代，此时`xj`的整数部分即为解。根据上述逻辑我们就能写代码编程求解了。

### [172. Factorial Trailing Zeroes](https://leetcode-cn.com/problems/factorial-trailing-zeroes/)

- 暴力法

  直接将n!算出后计算0的个数

  - 时间复杂度计算

    计算阶乘是重复的乘法。通常，当我们知道乘法是固定大小的数字上（例如 32 位或 64 位整数）时，我们可以视为 O(1)运算。但是，这里要乘以的数字会随着 n 大小而增长，所以这里不能这么做。

    因此，这里的第一步是考虑乘法的成本，因为我们不能假设它是 O(1)，这里的时间复杂度计算需要记忆：O((log x) · (log y))

    接下来，我们考虑以下在计算 n!n! 时，我们做了什么乘法运算。前几个乘法如下：

    1⋅2=2
    2⋅3=6
    6⋅4=24
    24⋅5=120
    120⋅6=720
    ......

    这些乘法的成本：

    log1⋅log2
    log2⋅log3
    log6⋅log4
    log24⋅log5
    log120⋅log6
    ......

    我们可以改写为：

    log 1!⋅log 2
    log 2!⋅log 3
    log 3!⋅log 4
    log 4!⋅log 5
    log 5!⋅log 6
    ......

    每行的格式为 (log*k*!)⋅(log*k*+1)，最后一行是log((*n*−1)!)⋅log(*n*)

    我们一个接一个地做这些乘法运算，并把它们相加，得到总的时间复杂度：

    log1!⋅log2+log2!⋅log3+log3!⋅log4+⋯+log((n−2)!)⋅log(n−1)+log((n−1)!)⋅logn

    从这个公式来看，我们会发现时间复杂度比 O(n) 差。

    接下来扔掉不太重要的项：

    注意 log((n−1)!) 比 log n 大的多。因此，我们将删除这部分，留下 log((n−1)!)：

    log1!+log2!+log3!+⋯+log((n−2)!)+log((n−1)!)

    继续化简：

    记住重要公式：***O*(log *n*!)=*O*(*n*log *n*)**

    根据该公式我们可以得到：

    1⋅log1+2⋅log2+3⋅log3+⋯+(n−2)⋅log(n−2)+(n−1)⋅log(n−1)

    去掉较小项：

    1+2+3+...+(*n*−2)+(*n*−1)

    即得时间复杂度为 *O*(*n*2)

    这个复杂度太大了，而且丢弃了项以后会使我们的时间复杂度低于真实的时间复杂度

  - 空间复杂度计算

    为了存储 n!，我们需要 O(logn!) 位，而它等于 O(nlogn)，因此空间复杂度：O(logn!)=O(nlogn)


- 因子分解

  数字最后的0是由10来的，而10可以分解为2和5，而将数分解为2的概率要高于5，那是因为10以内4和8都可以分解为多个2，而只有10可以分解为多个5，因此肯定是2的数量比较多，而我们必须需要一对2和5才能凑出一个10，因此直接找分解后5的个数即可

  注意，这里我们不需要遍历全部的数，只需要遍历是5的倍数的数，因为只有是5的倍数的数才能分解出5这个因子

  - 时间复杂度

    每五个数字处理一次，因此是O(n / 5)，即O(n)

    遇到25、125这种的需要将该数字循环除以5来处理，因此是O(log5 n)，由于绝大部分数都只能分解出一个5，可以证明，因子 5 的总数小于 2 · n / 5，因此事实上他只消耗O(1)

    综合来看时间复杂度就是O(1) * O(n) = O(n)

  - 空间复杂度

    由于只用了一个整数变量，因此空间复杂度为：O(1)

- 高效计算因子5

  上述第二种方法精华在于找到阶乘起来的数能分解出多少个5，事实上，要是以这个为目的的话我们根本不需要遍历，直接从n本身就可以推断出有多少个5

  试想一下，如果n是25，那么理论上 1 - 25 这几个数里面能分解出5的有25 / 5 = 5个数，但是仅仅之这样还不够，因为25本身就能分解出两个5，也就是说会有多重因子的数字存在，那么如何解决这个问题呢？其实只需要找到所有重因子的数（包括2重、3重、...）,并加上那些没有被算上去的其他重因子5的个数即可，那么如何去找到这些重因子呢？以125为例，首先去找125中重数最小的因子，也就是二重，也就是25的倍数，分别是25、50、75、100、125，这些都是能分解出两个5的，再去找三重，也就是125的倍数，那就是125本身，再找四重就没有了，然后开始加和，首先加上一重的，也就是5的倍数，125 / 5 = 25（注意这里会把二重、三重的数加一遍，所以一会儿加二重或者三重的数的时候就不必重复加了），再加上二重的，125 / （5 * 5） = 5（同理，这里会把三重的数加一遍，一会儿加三重的数的时候也不用再重复加了），最后加三重的数，125 / （5 * 5 * 5） = 1（还是解释一下这里为什么明明125能分解为5 * 5 * 5但却记为1而不是3，因为在计算二重和一重的时候，已经把5 * 5 * 5中的前面两个5算进去了，所以就不需要重复计数了，因此记为1就可以），至此，停止结束，加和即为：25 + 5 + 1 = 31

  总结一下上述计算过程，拿25为例其实就是：

  ```python
  fives = n / 5 + n / 25
  tens = fives
  ```

  拿125为例：

  ```python
  fives = n / 5 + n / 25 + n / 125
  tens = fives
  ```

  - 时间复杂度

    在该方法中，我们将n除以5的每个幂，相当于O(log5 n)，由于乘除法在32位整数范围内，我们将这些计算视为O(1)，因此O(1) * O(log5 n) = O(log5 n) ~ O(log n)

  - 空间复杂度

    由于使用了常数空间，因此空间复杂度为：O(1)

### [202. Happy Number](https://leetcode-cn.com/problems/happy-number/)

首先分析结果的三种可能：

1、最终会得到1

2、最终会进入循环

3、值会越来越大，最后接近无穷大

第三个情况比较难以检测和处理。我们怎么知道它会继续变大，而不是最终得到 1 呢？我们可以仔细想一想，每一位数的最大数字的下一位数是多少（事实上就是每一个拆开的数都是9，这样的话乘积之后加和最大），如下：

| Digits | Largest       | Next |
| ------ | ------------- | ---- |
| 1      | 9             | 81   |
| 2      | 99            | 162  |
| 3      | 999           | 243  |
| 4      | 9999          | 324  |
| 13     | 9999999999999 | 1053 |

对于 3 位数的数字，它不可能大于 243。这意味着它要么被困在 243 以下的循环内，要么跌到 1，4位或以上的数字最终也会降到3位。所以我们会知道，最坏情况下，算法可能会在243一下的所有数字上循环，然后回到他已经到过的一个循环或者回到1。但它不可能越来越大，所以排除第三种情况

那剩下的两种情况事实上就跟问题：[141. Linked List Cycle](https://leetcode-cn.com/problems/linked-list-cycle/)（判断链表中是否有环）的思路差不多：

- 维护一个集合，如果循环的过程中出现集合中存在的数字，则说明该循环为无限循环

- 快慢指针，如果是无限循环，则肯定有一个时刻快指针和慢指针会重叠

- 数学方法

  如果多次实验（写程序让电脑去算），我们会发现如果是循环的话那它最终都会都会进入这样一个循环：4->16->37->58->89->145->42->20->4

  那就好办了，只要在循环中出现上述链中的数字，则说明就是无限循环

### [231. Power of Two](https://leetcode-cn.com/problems/power-of-two/)

- 暴力计算

  由于是2的幂的数对2取余一定为0，而不是2的幂的数一直整除2除到整除不了的时候该数对2取余一定为1。抓住这个特性做取余和整除计算即可做出判断了。

- 位运算（保留最右边的1）

  技巧：1、 x & (~x + 1) 操作能保留最右边的1 ；2、用到了2的幂的数的特性

  我们知道补码就是反码 + 1，显然 x & (~x + 1) 就可以保留最右边的1，而2的幂的数的二进制表示中，是只有1个1的，区域都是0，那么只需要判断 x & (~x + 1) 得结果是否等于 x即可做出判断

- 位运算（将最右边的1变为0）

  技巧：1、x & (x - 1) 操作能将最右边的1变为0 ；2、用到了2的幂的数的特性

  上面说了2的幂的数的二进制只有1个1，其余都是0，我们可以通过x & (x - 1) 操作将x的最右边的1去掉，通过判断x & (x - 1) 是否为0得出结论

本题中我们将int型的n转换成long型避免溢出

### 计算两个数的最大公约数

该题来自c语言教程

- 辗转相除法

  假设有两数m、n（m > n），做m%n，得到余数r，若r为0，则n即为最大公约数，若r > 0，则将m赋值为n，将n赋值为r，再次计算m%n，得到新余数r，如此循环直到r为0的时候n即为最大公约数

### 不使用第三个变量的情况下交换两个整型变量的值

假设有两个变量a、b，现在我们想要交换a和b的值

- 法一：

  ```c
  a = a + b;
  b = a - b;
  a = a - b;
  ```

  缺陷：整型变量a可能溢出

- 法二：

  ```c
  a = a^b;
  b = a^b;
  a = a^b;
  ```

  解决了法一的缺陷，因为两个数异或不会产生进位，因而不会导致溢出

  同时我们对于异或这个操作又有了新的理解：假设a和b都只能取0或1，那么有等式：a^b=c，在这个等式下只要确定了其中两个数就一定能唯一确定第三个数

  <span style="color:red;">但是！</span>异或操作可读性差而且执行效率低于使用第三个临时变量来做交换的方法

### 计算一个数的二进制形式中1的个数

- 法一

  该数对2取模，如果为1则计数器加1，否则不加1，然后将该数除以2，如此往复直到该数为0，此时计数器的数即为1的个数

  缺陷：没有考虑负数的情况，比方说-1，它在计算机中应该是以二进制补码形式存储的（1111...1111），那它显然是有很多个1，但是-1%2=-1，-1/2=0，最后计数器为0，显然是错误的

  那么怎么解决上面这个问题呢？我们可以把所有的数都当成无符号数：

  ```c
  // 假设下面这个函数用来计算数的二进制形式中1的个数
  int count_one(unsigned int x){...} // 这里形参类型是unsigned int，说明不管传过来的哪种类型的int，我都把它当成是无符号数，那么此时负数也可以进行计算了
  ```

- 法二

  让该数与1做与操作，如果为1则计数器加1，否则不加1，然后将该数右移1位，如此往复32次，此时计数器的数即为1的个数

- 法三

  假设该数为a，做操作：a = a&(a-1)，每做一次计数器都加1，直到a=0为止，此时计数器的数即为1的个数

### 打印一个32位二进制数的奇数位和偶数位

我们用 移位操作 和 按位与操作 来完成该算法

```c
void print(int m){
    int i = 0;
    printf("奇数位：\n");
    for(i = 30; i >= 0; i -= 2){
        printf("%d\n", (m>>i)&1);
    }
    printf("\n");
    printf("偶数位：\n");
    for(i = 31; i >= 1; i -= 2){
        printf("%d\n", (m>>i)&1);
    }
}
```

### 使用递归的方式求2的k次方

```c
double pow(int num, int k){ // 注意需要考虑k是负数的情况
    if(k < 0) return (1 / pow(num, -k)); // 其实k是负数的情况很简单，比方说2^-3，按照一般的数学思维，我们应该这么算：2^-3 = 1 / 2^3，因此重点就在于这个2^3。那就很巧了，2^3正好是k>0的情况
    else if(k == 0) return 1;
    else return num * pow(num, k - 1);
}
```

### 给定数组，将奇数放前面偶数放后面

从前往后找偶数，从后往前找奇数，符合条件就交换，并记录位置，下次找就从当前位置往后或者往前找，如此往复直至所有奇数在前所有偶数在后

### 寻找只出现一次的数

**给定数组，其中只有一个数只出现了一次，别的数都是成对出现的，找出只出现一次的数**

- 两两异或

**给定数组，其中有两个数只出现了一次，别的数都是成对出现的，找出只出现一次的数**

比方说：1 2 3 4 5 1 2 3 4 6，如果两两异或，最后相当于就是5和6异或，得到二进制0011，思考一下最后两个1是怎么来的，显然是那两个只出现了一次的数的二进制位中的最后两位为01或者10，那么我们就取二进制最低位为1的分为一组，为0的分为一组，由于成对出现的数肯定会被分到同一组，因此最后可以成功将两个只出现了一次的数分到不同的组：1 1 3 3 5；2 2 4 4 6，此时分别对两个组做两两异或操作即可得到这两个只出现了一次的数。

当然，如果是：1 2 3 4 8 1 2 3 4 6，所有数两两异或后得到1110，那么此时就应该取所有数二进制位从右往左数的第二位为1的分为一组，为0的分为一组。

其它数同理

**给定数组，其中有三个数只出现了一次，别的数都是成对出现的，找出只出现一次的数**

思路跟上面有两个数只出现了一次的题是一样的，这里只需要根据将所有数异或之后得到的二进制结果中出现1的二进制位的位置将所有数分成三组即可

### [258. Add Digits](https://leetcode-cn.com/problems/add-digits/)

- 递归
- 迭代
- 数学

### [263. Ugly Number](https://leetcode-cn.com/problems/ugly-number/)

- 数学

### [268. Missing Number](https://leetcode-cn.com/problems/missing-number/)

- 排序之后遍历

  注意末尾元素需要单独判断

- 哈希表

  哈希表查询操作时间复杂度O(1)

  可以先将元素放入集合中，再遍历查找缺的数

- 位运算

  利用相同的数异或运算为0的特性，让下标与数字都参与异或运算最后得到的结果就是缺的数

- 数学

  等差数列求和公式

### [292. Nim Game](https://leetcode-cn.com/problems/nim-game/)

- 数学题，找规律

### 将十进制数转成n进制数（n < 10）

自研算法：

```c++
int hir(int pow_res, int org){
	int ret = 1;
	while(org >= pow_res * (ret + 1)){
		ret++;
	}
	return ret;
}

string i2n(int n, int radix)
{
	string ret = "";
	while(n){
		int pos = 0;
		int pow_res = pow(radix, pos);
		while(n >= pow_res * radix){
			pos++;
			pow_res = pow(radix, pos);
		}
		if(ret == ""){
			if(n < radix){
				return to_string(n);
			}else{
				ret += '0' + hir(pow_res, n);
				while(pos){
					ret += '0';
					pos--;
				}
				n -= pow_res * hir(pow_res, n);
			}
		}else{
			if(n < radix){
				ret[ret.length() - 1] = '0' + n;
				return ret;
			}else{
				ret[ret.length() - pos - 1] = '0' + hir(pow_res, n);
				n -= pow_res * hir(pow_res, n);
			}
		}
	}
	return ret;
}
```

### [326. Power of Three](https://leetcode-cn.com/problems/power-of-three/)

要注意边界的处理

- 循环迭代

- 基准转换

  10进制转3进制，并判断3进制的数中是否只有一个1打头其余都是0

- 运算法

  假设n符合条件，那么以3为底，n的对数一定是整数，借助换底公式做转换并将最后的结果除以1求余数，判断余数是否为0即可

  需要注意的是该算法可能存在误差，因此需要将结果与`epsilon`进行比较：

  ```java
  return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon;
  ```

- 整数限制

  我们发现传进来的参数是int型，那么只需要找到int型最大范围内某个数n，n能被3整除，则n除以要判断的数m得到的结果一定是整数

  首先找到数n以3为底的整数幂x：

  ```c++
  int x = (int)(log(INT_MAX) / log(3));
  ```

  然后确定以3为底，x为幂的整数即为int型最大范围内的能被3整除的整数：

  ```c++
  int max_int = pow(3, x);
  ```

  最后拿这个数来作判断：

  ```c++
  return max_int % target == 0;
  ```

### [342. Power of Four](https://leetcode-cn.com/problems/power-of-four/)

注意边界的处理

- 循环迭代

- 二进制表示中1的位置

  - `n >= 1`
  - 二进制表示中只有一个1（`(n & (n - 1)) == 0`）
  - 二进制表示中的1只能出现在奇数位上

- 取模性质

  关键在于区分n是4的幂还是2的幂，如果是4的幂，除以3取余一定是1，如果是2的幂，除以3取余一定是2

### [367. Valid Perfect Square](https://leetcode-cn.com/problems/valid-perfect-square/)

- 二分查找

  注意内存溢出，这里有两种解决方法一种是使用long类型，一种是使用除法和余数而非乘法

- 数学

  使用牛顿迭代法求解

### [374. Guess Number Higher or Lower](https://leetcode-cn.com/problems/guess-number-higher-or-lower/)

- 二分查找

### [405. Convert a Number to Hexadecimal](https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal/)

- 二进制的4位能算出十六进制的1位
  - 逻辑右移
    - `c++`中需要使用无符号数，然后使用`>>`
    - `c++`中也可以不使用无符号数，限制数右移的次数也可（假设在32位机器上，每一次右移4位，则限制移8次）
    - `Java`中需要使用`>>>`
    - Java中也可以使用`>>`，限制数右移的次数即可（假设在32位机器上，每一次右移4位，则限制移8次）
  - 维护一个大小为32的int型数组用于保存数的二进制形式，再4位4位地计算出数的十六进制形式

### [441. Arranging Coins](https://leetcode-cn.com/problems/arranging-coins/)

- 数学

  根据题意结合等差数列求和公式可以得到：`(1 + k) * k / 2 <= n`，从而得到`k`的取值范围，之后可以使用：

  - 朴素遍历得到范围中符合题意的最大的`k`
  - 二分查找得到符合题意的最大的`k`

### [461. Hamming Distance](https://leetcode-cn.com/problems/hamming-distance/)

- 异或操作后使用内置位计数功能

  比如Java的`Integer.bitCount()`

- 异或操作后使用移位实现位计数

- 使用Brian Kernighan算法实现位计数

  ```
  x &= (x - 1)
  ```

### [476. Number Complement](https://leetcode-cn.com/problems/number-complement/)

- 暴力法

  求数的二进制形式，再求其补数，最后再转十进制（注意整型溢出）

- 异或运算

  求得数的二进制形式的最高位的位数，将数字1左移这些位数（注意整型溢出）后减一，再与原数做异或运算

### [492. Construct the Rectangle](https://leetcode-cn.com/problems/construct-the-rectangle/)

- 使用内置函数sqrt后再找到差值最小的两个因数

- 自己写sqrt，然后找到差值最小的两个因数

  难点在于如何编写sqrt函数，参考最终代码：

  ```python
  def constructRectangle(self, num: int) -> List[int]:
  	left, right = 1, num
      while left < right:
          mid = left + (right - left) // 2
          temp = mid * mid
          if temp <= num:
              left = mid + 1
          else: 
              right = mid - 1
      # 此时 left 或 (left - 1) 即是int(sqrt(num))
      while left >= 0:
          if num % left == 0:
              return [int(num / left), left] if int(num / left) > left else [left, int(num / left)]
          left -= 1
      return [num, 1]
  ```

  对于c++来讲，还需要注意整型溢出（`temp = mid * mid`这里），参考最终代码：

  ```c++
  vector<int> constructRectangle(int area) {
      int left = 1, right = area;
      while (left < right) {
          int mid = left + (right - left) / 2;
          if (area / mid >= mid) { // 将乘法改成了除法，防止整型溢出
              left = mid + 1;
          } else {
              right = mid - 1;
          }
      }
  	// 此时 left 或 (left - 1) 即是(int)sqrt(num)
      while (left >= 0) {
          if (area % left == 0) {
              if (area / left > left) {
                  return { area / left, left };
              } else {
                  return { left, area / left };
              }
          }
          left--;
      }
      return { area, 1 };
  }
  ```

### [504. Base 7](https://leetcode-cn.com/problems/base-7/)

- 朴素法（配合递归或迭代）（注意边界情况）

  思考如何求出当前数7进制形式最高位以及如何往后补0，补0的时候需要注意边界情况

- 递归

- 迭代

### [507. Perfect Number](https://leetcode-cn.com/problems/perfect-number/)

- 枚举

  在枚举时，我们只需要从 `1` 到 `根号n` 进行枚举。这是因为如果 `n` 有一个大于 `根号n` 的因数 `x`， 那么它一定有一个小于 `根号n` 的因数 `n/x`。因此我们可以从 `1` 到 `根号n` 枚举 `n` 的因数，当出现一个 `n` 的因数 `x` 时，我们还需要算上 `n/x`。此外还需要考虑特殊情况，即 `x=n/x`，这时我们不能重复计算。

- `欧几里得-欧拉定理；梅森素数`

  每个偶数（奇数的完全数还未被发现）如果是完全数都可以写成：`2^(p - 1) * (2^p - 1)`，其中`p和(2^p - 1)`都是素数。题目让我们计算的是`10^8`以内的所有完全数，如果套用上述公式，p是可以有限枚举的：`{2, 3, 5, 7, 13, 17, 19, 31}`，因此可以直接算出`10^8`以内的所有完全数。代码如下：

  ```c++
  // 欧几里得-欧拉定理计算完全数的公式
  long pn(long p) {
      // 注意整型溢出
      return ((long)1 << (p - 1)) * (((long)1 << p) - 1);
  }
  bool checkPerfectNumber(int num) {
      // int primes[] = {2, 3, 5, 7, 13, 17, 19, 31, 61, 89, 107}; // 梅森数
      int primes[8] = { 2, 3, 5, 7, 13, 17, 19, 31 };
      for (int& prime: primes) {
          if (pn(prime) == num) return true;
      }
      return false;
  }
  ```

### 快速幂算法、矩阵快速幂

参考：https://blog.csdn.net/qq_19782019/article/details/85621386（快速幂算法）

https://blog.csdn.net/zhangxiaoduoduo/article/details/81807338（矩阵快速幂）

### [509. Fibonacci Number](https://leetcode-cn.com/problems/fibonacci-number/)

- 动态规划
- 递归
- 迭代
- 矩阵快速幂
- 通项公式

## 思维题

### 猜凶手

四个人中有一个嫌犯，以下是4个嫌犯的供词：

A：不是我

B：是C

C：是D

D：C在胡说

已知只有一人说了假话

```c
// 反向思维，先枚举可能的结果，再验证哪个结果符合题设
int main(){
    int killer = 0;
    for(killer = 'a'; killer <= 'd'; killer++){
        if((killer != 'a') + (killer == 'c') + (killer == 'd') + (killer != 'd') == 3){
            printf("killer = %c\n", killer);
            break;
        }
    }
    return 0;
}
```

### 猜凶手变种（猜排名）

A：B第二，我第三

B：我第二，E第四

C：我第一，D第二

D：C最后，我第三

E：我第四，A第一

已知每位选手都说对了一半，请编程确定比赛的名次

```c
// 反向思维，先枚举结果，再验证那个结果符合题设
int main(){
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;
    int e = 0;
    for(a = 0; a <= 5; a++){
        for(b = 0; b <= 5; b++){
            for(c = 0; c <= 5; c++){
                for(d = 0; d <= 5; d++){
                    for(e = 0; e <= 5; e++){
                        if(((b==2)+(a==3)==1)&&((b==2)+(e==4)==1)&&((c==1)+(d==2)==1)&&((c==5)+(d==3)==1)&&((e==4)+(a==1)==1)){
                            // 防止排名重复
                            if(a*b*c*d*e==120){
                                printf("a=%d,b=%d,c=%d,d=%d,e=%d\n", a, b, c, d, e);
                            	break;
                            }
                        }
                    }
                }
            }
        }
    }
    return 0;
}
```



### 赛马问题

36匹马，6个跑道，没有计时器，问确定前三名需要至少比几次赛？

![image-20210325095240571](leetcode心得.assets/image-20210325095240571.png)

1、首先分成六组，每组六匹，比一次。此时每一组的六匹马的顺序就确定了，应该可以排除掉每一组中的最后三名；

2、再让每一组的第一名比一次，可以排除掉后三名的三个组，此时前三名要么就是第一名的那一组的前三，要么就是第二名的那一组的前二，要么就是第三名的那一组的第一；

3、让第一名的那一组的前三和第二名的那一组的前二和第三名的那一组的第一再比一次即可

### 烧香问题

有一种香材质不均，但是燃完刚好一小时，现在给两根这样的香，问如何确定一段15分钟的时间

1、其中一根香点燃两头，另一根香点燃一头；

2、当有一根香烧完的时候说明过去了半小时，此时点燃另一根香的另一头，从此刻开始计时，到燃完就是15分钟

### 1元买一瓶水，两个空瓶换一瓶水，问20元能喝多少瓶水

```c
int main(){
    int total = 0;
    int money = 0;
    int empty = 0;
    scanf("%d", &money);
    // 买回来的汽水喝掉
    total = money;
    empty = money;
    // 换回来的汽水
    while(empty >= 2){
        total += empty / 2;
        empty = empty / 2 + empty % 2;
    }
    printf("total = %d\n", total);
    return 0;
}
```

## SQL

### [175. Combine Two Tables](https://leetcode-cn.com/problems/combine-two-tables/)

- 联表查询

### [176. Second Highest Salary](https://leetcode-cn.com/problems/second-highest-salary/)

- 注意去重
- 注意有可能不存在排名第二的数值
  - 因此可以将第一层用于搜索的select作为临时表，外面再套一层select
  - 也可以在上面的基础上配合IFNULL函数来处理NULL的情况

### [181. Employees Earning More Than Their Managers](https://leetcode-cn.com/problems/employees-earning-more-than-their-managers/)

联表查询，语法可以用sql92的也可以用sql99的

### [182. Duplicate Emails](https://leetcode-cn.com/problems/duplicate-emails/)

- 使用 `GROUP BY` 和临时表
- 使用 `GROUP BY` 和 `HAVING` 条件

### [183. Customers Who Never Order](https://leetcode-cn.com/problems/customers-who-never-order/)

- 可以用NOT IN筛选Customers的id没有在Orders的外键CustomerId中的Customers的name
- 也可以用left outer join 筛选联表后CustomerId为NULL的Customers的name

### [196. Delete Duplicate Emails](https://leetcode-cn.com/problems/delete-duplicate-emails/)

- 借助子查询查找Email重复的id，需要注意的是需要在子查询的临时表外面再套一层select，否则可能会出现同时查询和更新表的错误

  - 附上代码

    ```mysql
    DELETE FROM Person 
    WHERE id in (
    	SELECT id FROM (
        	SELECT id FROM Person p1 LEFT OUTER JOIN Person p2 
            ON p1.Email=p2.Email
            WHERE p1.id > p2.id
        ) temp
    );
    ```

- 可以使用delete 要删除的表 from ... 的语法

  - 注意，这个语法sql92和sql99都有

  - 附上代码

    ```mysql
    DELETE p1 FROM Person p1, Person p2 
    WHERE p1.Email=p2.Email
    AND p1.id > p2.id;
    ```

### [197. Rising Temperature](https://leetcode-cn.com/problems/rising-temperature/)

利用DATEDIFF函数

- 可以使用子查询，通过对比今天和昨天的温度得到答案

- 可以使用join，合并的关联字段是今天的日期和昨天的日期，事实上就是这样写：

  ```mysql
  ON DATEDIFF(today.date, yesterday.date)=1
  ```

  之后再比较今日和昨日的温度得到答案

## 位操作

### [190. Reverse Bits](https://leetcode-cn.com/problems/reverse-bits/)

- 与操作配合位操作

  二进制数跟1的与操作可以知道二进制数的最右位，再将该位左移至最左位，再找到最右边第二位，左移至最左边第二位，循环此操作即可

- 带记忆化的按字节（8位）颠倒

  ![在这里插入图片描述](https://pic.leetcode-cn.com/365599a4030d26a019d37ad97c201e64e2fa3ae9fd7b43d689e8a4d7f802141e-file_1585801736122)

  在处理长字节流时，每字节（8 位的比特位）反转可能更有效，但是本题输入的是固定的 32 位整数，所以字节颠倒可能更有效

  - 附上按自己为单位反转位的算法：

    ```python
    # 这个算法是用 3 个操作反转一个字节中的位，在 Sean Eron Anderson 的在线电子书 Bit Twiddling Hacks 中可以看到更多的细节。
    def reverseByte(byte):
        return (byte * 0x0202020202 & 0x010884422010) % 1023
    ```

    上述算法完全基于算术和位操作，不涉及循环

  - 可以缓存先前计算的值，以避免重新计算

  - 步骤

    - 按字节遍历整数（总共32位，每次取最右边的8位，每次操作完后都将该整数右移8位）这里我们使用位掩码为 `11111111` 的与操作（即 `n&0xff`）来完成该操作
    - 之后使用上述的reverseByte()方法来反转字节中的位，获取每个字节（8位）反转后的字节，注意，这里我们维护一个map来保存每一次的计算，下次如果有重复的计算就直接使用map中的结果即可可以减少计算量
    - 之后将该反转后的字节左移24（32 - 8 = 24， 当然第二次的话就是 24 - 8 = 16，以此类推）
    - 等到所有位都做了上述操作之后，我们就得到反转之后的结果了。当然，这里我们选择了8位，也可以选择4位，或更小的位数，这将需要更多的计算来交换更少的缓存空间

- 分治策略颠倒位的顺序

  上面的带记忆化的按字节颠倒方法将整数按照字节分成若干份，再循环处理最后得到结果，那能不能在不使用循环的情况下反转整个32位呢？事实上我们可以将32分成16，16分成8，8分成4，...，直到最后分成块数为1位为止，并将每个块反转合并最后得到结果。通俗来讲就是把32劈成两半，反转，再把左边右边的16各自劈成两半，反转，...，最后粒度到1为止，反转，即可

  - 这里控制取到数的前一半位和后一半位的方法其实就是一个与操作

  - 将两部分数合到一起其实就是一个位操作配合一个或操作

  - 附上代码：

    ```c++
    class Solution {
    public:
        uint32_t reverseBits(uint32_t n) {
            n = (n >> 16) | (n << 16);
            n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
            n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
            n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
            n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
            return n;
        }
    };
    ```

### [191. Number of 1 Bits](https://leetcode-cn.com/problems/number-of-1-bits/)

- 让数右移并跟 1 做与运算来判断每一次右移之后最右边的是否是1，并进行计数

- 二进制数消一法

  让数减一之后与数本身做与运算，循环操作后到某一次该数变为0，记录操作的次数即为二进制数中1的个数

  - 事实上上述操作每一次都会使得二进制数消掉最靠右的那个1

### [204. Count Primes](https://leetcode-cn.com/problems/count-primes/)

- 暴力法，双层for循环遍历查找素数

- 优化的暴力法

  主要考虑两点：

  - 两层for循环中的偶数都不需要遍历，第一层for循环是被除数，偶数自然不是素数，因此不需要遍历，第二层for循环是除数，素数除以偶数本就是不可能除尽的，如果除尽了就不会是素数，因此偶数也不需要遍历，实现如下：

    ```c++
    for(...;...; i += 2){
        ...
        for(...;...; j += 2){...}
    }
    ```

  - 第二层for循环（也就是除数）应小于等于被除数自身开根号的数

    解释一下为什么是小于等于：因为对正整数 n ，如果用 2 到 √n 之间(包含边界)的所有整数去除，均无法整除，则 n 为质数。举个例子，5开根号的数是2点几的一个小数，比2点几小的整数只有2（这里需要排除整数1），但是5无法被2整除，因此5就是素数；再举个例子，9开根号的数是3，按照规则来讲需检验的除数应是2和3，9虽然没法被2整数，但是能被3整除，因此9不是素数，实现如下：

    ```c++
    for(...;...; i += 2){
        ...
        for(int j = 3; j * j <= i; j += 2){...}
    }
    ```

- **厄拉多塞筛法**

  最核心的原理就是一个整数（大于1）的倍数一定不是素数

  围绕该原理，我们只需要准备一个足够大的数组，全部初始化为0，从2开始遍历（遍历的条件是该数在该数组中对应的那个值为0而不是1（这么做的意义就是确定遍历的数既没有被遍历过而且不是非素数）），每遍历一个数，就把他的所有倍数的数在数组中的位置的那个0全部置为1，并统计遍历的数的个数即为素数个数

- **利用比特表对厄拉多塞筛法进行内存优化**

  核心原理还是某个整数的倍数一定不是素数这一点

  优化点：原先是用int来存0和1，一个1或者一个0的size是4字节，每字节有8位，这样的话一个数就是32位，这是对资源的浪费，因为事实上上述这种0和1的保存用位就可以完成

  - 这里我们需要将每一个二进制位当成是一个桶，如下：

    登记数字 1 ：0 0 0 0 0 0 0 1
    登记数字 1、3：0 0 0 0 0 1 0 1
    登记数字 1、2、3、4、5 ：0 0 0 1 1 1 1 1

  - 代码如下：

    ```c++
    int countPrimes(int n) {
        // 用于记录素数个数
        int count = 0;
        // 一个 int 变量占4字节（32位），因此用size常量保存一个int变量的位数，也就是4 * 8 = 32
        const int size = sizeof(int) * 8;
        // 一个int型数是32位，因此最多代表到32（1、2、3、。。。、30、31、32）
        // 计算需要为数n开辟的桶的个数，上面说了一个int型的数能代表到32（1、2、...、31、32），那么如果n为46，就需要46 / 32 + 1 = 2个int型的数，并且我们将这些桶都初始化为0
        vector<int> signs(n / size + 1,0);
        // 从2开始找素数
        for (int i = 2; i < n; i++){
            // 由于 i % size 效果等同 i & (size - 1)，因此if ((signs[i / size] & (1 << (i % 32))) == 0)这个操作还可以写成如下：
            if ((signs[i / size] & (1 << (i & (size - 1)))) == 0){
                // 解释一下这个if，首先找到该数所在的段，比方说n为100，那么100 / 32 + 1 = 4个段，每一段有32个桶，那比方说遍历到数46了，那46 / 32 + 1 = 2，显然他是处于第二段中的某个桶，因此找到该段的代码就是signs[i / size]，然后我们再找他是否已经被排除（在该段中该数对应的那个桶的值是否为1，如果已经为1了，说明已经被排除了），为了达到这一点，我们先将遍历到的数i变成二进制，做法就是将1左移若干位，移动的位数事实上就是i % 32（相当于i & (size - 1)）,然后再将移好的二进制数与该段的int型数做一个与运算，如果结果不为0，说明该数对应的桶的值已经是1了，说明被排除了，反之说明该数是素数，应该将计数器加1，并且所有该数的倍数所对应的桶的值置为1（操作就是一个或操作，如下）
                count++;
                for (int j = i + i; j < n; j += i){
                    //登记该数字
                	signs[j / size] |= 1 << (j & (size - 1));
                }
            }
        }
        return count;
    }
    ```

  - 使用bitset增加上述代码的可读性

    思路跟上述代码思路一致，主要就是加了一个bitset增加了可读性，代码如下：

    ````c++
    int countPrimes(int n) {
        int count = 0;
        const int size = sizeof(int) * 8;
        vector<int> res = vector<int>(n / size + 1, 0);
        for(int i = 2; i < n; i++){
            // 其实就是把每个int型数的位单独拿出来了，如下：
            bitset<size> bs = bitset<size>(res[i / size]);
            // 判断桶的值是否为0的方法也随之简化了，大大增加了可读性，如下：
            // 这里的i & (size - 1)就相当于i % size
            if(bs[i & (size - 1)] == 0){
                count++;
                for(int j = i + i; j < n; j += i){
                    res[j / size] |= 1 << (j & (size - 1));
                }
            }
        }
        return count;
    }
    ````

    

## Linux

### [193. Valid Phone Numbers](https://leetcode-cn.com/problems/valid-phone-numbers/)

- 使用grep，注意grep -P中的-P的意思是使用perl的正则表达式语法，perl的正则功能更强大

- 也可以使用awk或gawk，这是Unix上的grep

### [195. Tenth Line](https://leetcode-cn.com/problems/tenth-line/)

输出文件第十行文本方法：

- sed -n
- grep -n配合cut
- ...

统计文本行数方法：

- wc -l
- grep -nc "" file.txt
- grep -c "" file.txt
- grep -vc "^$" file.txt
- ...

## 多线程

### [1114. Print in Order](https://leetcode-cn.com/problems/print-in-order/)

- 使用原子变量
- 可重入锁 + Condition
- synchronized + 标志位 + 唤醒（synchronized 也可以加方法上）
- semaphore信号量
- CountDownLatch
- BlockingQueue阻塞队列
- LockSupport + AtomicReference（或ConcurrentHashMap）

### [1115. Print FooBar Alternately](https://leetcode-cn.com/problems/print-foobar-alternately/)

- BlockingQueue阻塞队列
- CyclicBarrier循环栅栏
- 自旋锁 + 让出CPU
- 可重入锁 + Condition
- synchronized + 标志位 + 唤醒（synchronized 也可以加方法上）
- Semaphore信号量
- LockSupport + AtomicReference（或ConcurrentHashMap）

### [1116. Print Zero Even Odd](https://leetcode-cn.com/problems/print-zero-even-odd/)

- BlockingQueue阻塞队列
- 自旋锁 + 让出CPU
- 可重入锁 + Condition
- synchronized + 标志位 + 唤醒（synchronized 也可以加方法上）
- Semaphore信号量
- CyclicBarrier循环栅栏（超时）
- 使用原子变量（超时）
- LockSupport + AtomicReference（或ConcurrentHashMap）

### [1117. Building H2O](https://leetcode-cn.com/problems/building-h2o/)

- BlockingQueue阻塞队列
- BlockingQueue + CyclicBarrier
- 可重入锁 + Condition
- synchronized + 标志位 + 唤醒（synchronized 也可以加方法上）
- Semaphore信号量
- Semaphore + CyclicBarrier
- LockSupport + AtomicReference（或ConcurrentHashMap）

### [1195. Fizz Buzz Multithreaded](https://leetcode-cn.com/problems/fizz-buzz-multithreaded/)

- synchronized + 标志位 + 唤醒（synchronized 也可以加方法上）
- 可重入锁 + Condition
- LockSupport + AtomicReference（或ConcurrentHashMap）
- BlockingQueue阻塞队列（使用多个BlockingQueue）
- Semaphore信号量（使用多个Semaphore）
- CyclicBarrier（思路很棒！）
- Semaphore信号量（使用一个Semaphore，大致思路跟CyclicBarrier一样）（思路很棒！）
- BlockingQueue阻塞队列（使用一个BlockingQueue，大致思路跟CyclicBarrier一样）（思路很棒！）
- 自旋锁 + 让出CPU

### [1226. The Dining Philosophers](https://leetcode-cn.com/problems/the-dining-philosophers/)

- 串行，不加锁，同一时间只有一个人能吃饭

- 并行

  关键点在于避免死锁，易知死锁原因是：所有人都获得了右/左叉子，都在等左/右叉子，而左/右叉子都在别人身上导致死锁

  - Semaphore或BlockingQueue限制就餐人数并使用Lock锁住资源（叉子），由于叉子是独立的，因此每一个叉子都要配备一把锁（至于如何区分叉子，可以尝试基于人的编号对叉子进行编号）

  - 设置领域（领域抽象起来其实也是一把锁）并使用Lock锁住资源（叉子），在线程抢叉子之前，必须先进入领域才行，抢到叉子之后释放领域，让别的线程也能抢叉子，吃完后释放叉子

  - 易知，可以让部分人先抢右叉子后抢左叉子，部分人先抢左叉子后抢右叉子来避免死锁

  - 上面三种方法使用了Lock，此外还能使用自旋锁 + 让出CPU的方法来代替Lock（volatile + CAS = AtomicInteger，这一点值得思考：原子变量是可以替代Lock的）

    

