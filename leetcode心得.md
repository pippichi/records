## 数组

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



## 树

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

### [107. Binary Tree Level Order Traversal II](https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/)

- 迭代，可以选择对树的每一层做一个for循环遍历
- 递归，用pair结构记录每一层的结点及其高度从而判断是否要向数组头部插入新数组，之后再向数组头部的数组添加元素，然后左右子树递归

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

### [235. Lowest Common Ancestor of a Binary Search Tree](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)

首先要注意它是一棵二叉搜索树（意味着他有序）

- 两次遍历

  封装一个能获取从根节点到目标节点所经过的所有节点的数组的函数，获取从根节点到q和从根节点到p的两条路径，从前向后遍历两条路径找出最后一个共同节点

- 一次遍历

  假设当前节点root，存在三种情况：1、q < root < p；2、q < root && p < root；3、q > root && p > root；如果是第一种情况的，显然当前节点就是我们想要的节点，第二种的话目标节点应该在当前节点的左侧，第三种的话目标节点应该在当前节点的右侧。按照这个逻辑，我们就可以写代码了，下面是两种实现的方法：

  -  递归

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

### [94. Binary Tree Inorder Traversal](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

- 递归

  每次遍历节点都先遍历它的左孩子再遍历自己再遍历它的右孩子

- 迭代

  首先明确肯定是一层一层遍历树的左孩子的，直到遍历不下去之后再遍历自己，然后再遍历自己的右孩子的左孩子，如此往复即可

- Morris 中序遍历

  核心思想就两点：

  - 不想使用额外的栈来保存下一步应该访问什么结点，那么我们就需要手动找到并指定结点的下一个结点，比方说现在是中序遍历，那么结点的上一个结点一定是左孩子的最右的结点，那么就可以让左孩子最右结点指向当前结点。遍历的时候如果左孩子最右结点已经指向它的后一个结点（要清楚本来左孩子最右结点是空指针）说明当前结点的左子树已经遍历完，下一步就是遍历自己，再下一步就是遍历右子树；而如果左孩子最右结点是空指针，则表示已经找到了左孩子的最右结点，需要将它指向自己，并且自己要向左一步，来找到左孩子的左孩子的最右结点，如此往复。（巧妙的点在于当我们遍历完左子树时所处的结点一定是左孩子的最右结点，而此时最右结点又已经被我们指向了自己，这就是遍历的过程中能够在遍历完左子树之后直接遍历自己然后下一步能够遍历自己的右子树的原因）

  - 如何走出循环？明确每次遍历完自己之后一定就是遍历右子树了，而右子树总有一天会是空指针，当右子树为空指针时说明遍历就已经完成了

### [144. Binary Tree Preorder Traversal](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)

思路同[94. Binary Tree Inorder Traversal](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

Morris 中序遍历的时候不再是手动指定结点的下一个结点了，而是充分利用父节点来过渡到下一个结点（也就是父节点的右孩子）

### [145. Binary Tree Postorder Traversal](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)

思路同[94. Binary Tree Inorder Traversal](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

迭代的时候要注意从底部反上来的时候自己的右孩子的下一个访问结点一定是自己，而自己因为存在右孩子所以又重新进入右孩子访问导致死循环（所以说需要记录右孩子，在访问完右孩子之后访问下一个结点也就是访问自己的时候如果自己的右孩子已经被访问过了就不要再访问了）

另外一种迭代的方法是翻转列表（先按照`本节点 -> 右孩子 -> 左孩子`的顺序遍历，最后翻转结果列表即可）

Morris 中序遍历的解法非常有技巧也非常复杂非常极限，建议从“如何完整构造出一个走完整个后序遍历的路径”的角度去思考（心得：找到中间结点就能找到左右孩子，从而找到整棵树）

## 字符串

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

  

## 链表

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

### [176. Second Highest Salary](https://leetcode-cn.com/problems/second-highest-salary/)

- 注意去重
- 注意有可能不存在排名第二的数值
  - 因此可以将第一层用于搜索的select作为临时表，外面再套一层select
  - 也可以在上面的基础上配合IFNULL函数来处理NULL的情况

### [181. Employees Earning More Than Their Managers](https://leetcode-cn.com/problems/employees-earning-more-than-their-managers/)

联表查询，语法可以用sql92的也可以用sql99的

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

