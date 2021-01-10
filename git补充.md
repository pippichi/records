# .gitgnore失效问题

有时候我们在.gitgnore编写了规则，但却不起作用，那很可能是这种情况：使用IDEA的时候开启了自动接受git管理的功能，导致新增的文件自动被add进git本地工作栈，导致该文件被git管理，这个时候就算在.gitgnore添加了该文件的排除规则也不会起作用，原因就是它已经被git管理了

那么如何解除呢？

使用git rm --cached filename，这样就可以不受git管理了

如果该文件已经committed了，那就执行git rm filename