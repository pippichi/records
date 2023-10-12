# 检出指定目录或文件

使用sparse clone和sparse checkout

参考：https://www.bilibili.com/video/BV1GN411b7Ad/?vd_source=c3d9e4c3ef670596b3b0dddab637f86c（GitFAQ-如何在一个大型项目中只克隆一个目录）

# 删除提交记录中的大文件

- 本地已add未commit

- 本地已commit

- 远端已push

  使用filter-repo

- 其他人远端已bush

  方法一：1、使用rev-list、largefiles查找大文件；2、使用filter-repo
  
  方法二：使用filter-branch遍历所有的commit，删除大文件，重写历史commit

参考：https://www.bilibili.com/video/BV1C841117TD/?spm_id_from=333.788&vd_source=c3d9e4c3ef670596b3b0dddab637f86c（GitFAQ-如何删除提交记录中的大文件）、https://blog.csdn.net/dd121494648/article/details/102277068（第一次用git filter-branch 有点爽）

# Patch工作流

- A在本地修改了工程文件但未add、commit，B想看

  git diff

- A在本地修改了工程文件且已commit到本地仓但未push到远端

  git format-patch

- A在本地dev分支修改了代码，且需要将部分修改合并到master分支并push到远端

  git cherry-pick

参考：https://www.bilibili.com/video/BV14z4y1T7zN/?spm_id_from=333.788&vd_source=c3d9e4c3ef670596b3b0dddab637f86c（Git技术研究-Patch工作流）

# 大文件存储

参考：https://www.cnblogs.com/cangqinglang/p/13097777.html（详解 Git 大文件存储（Git LFS））

# git官方文档

参考：https://git-scm.com/doc、https://git-scm.com/book/zh/v2