#利用Java实现一个源程序文件的代码统计工具

### **一、项目相关要求**

wc.exe 是一个常见的工具，它能统计文本文件的字符数、单词数和行数。这个项目要求写一个命令行程序，模仿已有wc.exe 的功能，并加以扩充，给出某程序设计语言源文件的字符数、单词数和行数。

现在需要实现一个源程序文件的代码统计程序，它能正确统计源程序文件中的字符数、单词数、行数，以及还具备其他扩展功能，并能够快速地处理多个文件。

**具体功能要求：**

*1、程序处理用户需求的模式为：*

直接通过用户在图形界面上的相关操作来读取用户的需求。

*2、基本功能列表：*

- 返回单个源程序文件/多个源程序文件/文件夹中所有的源程序文件的字符数
- 返回单个源程序文件/多个源程序文件/文件夹中所有的源程序文件的词数
- 返回单个源程序文件/多个源程序文件/文件夹中所有的源程序文件的行数

*3、扩展功能：*

- 返回单个源程序文件/多个源程序文件/文件夹中所有的源程序文件的有效代码行的数目
- 返回单个源程序文件/多个源程序文件/文件夹中所有的源程序文件的空行的数目
- 返回单个源程序文件/多个源程序文件/文件夹中所有的源程序文件的注释行的数目

### **二、项目开发过程中遇到的困难及解决方法**

**# 实现图形界面的困难：**

由于并没有很经常接触到Java的GUI编程，所以不太熟悉相关的控件编写以及窗口布局。

**# 做过的尝试：**

借助MyEclipse编译器中的WindowBuilder Editor插件完成了该程序的图形界面。

**# 解决：**

最后，实现了基础功能以及拓展功能。

**# 有所收获：**

冰冻三尺,非一日之寒,路漫漫其修远兮，吾将上下而求索。

### **三、程序代码结构**

![1486866-20180910212739921-1042371181](https://ws4.sinaimg.cn/large/006tNbRwgy1fv84qklb8qj30hk0g4dlb.jpg)

1、entity包中的CodeFileInfo类是源程序文件的实体类。

2、filter包中的SourceFilenameFilter过滤器类的作用是筛选出文件夹内的源程序文件（目前实现了筛选以.c以及.java为后缀的源程序文件）。

3、function包中的Function接口是基本功能及拓展功能的抽象接口，BaseFunction类和ExtendFunction类是具体的实现类，是功能实现的关键类。

4、handle包中的CalculateHandler类是一个控制类，主要是根据用户的需求（计算单个源程序文件/多个源程序文件/文件夹中的所有源程序文件）去调用对应的计算逻辑进行计算。

5、WcMain类是程序功能的主入口（图形界面）。

### **四、程序功能截图**

1、程序图形界面

![1486866-20180910214126816-890573636](https://ws3.sinaimg.cn/large/006tNbRwgy1fv84swduvuj30m80gojtp.jpg)

2、基本功能及拓展功能

- 计算单个源程序文件
![1486866-20180910214531977-1252565121](https://ws4.sinaimg.cn/large/006tNbRwgy1fv84tnmderj31ji0ow7c0.jpg)
![1486866-20180910214618606-1300342657](https://ws4.sinaimg.cn/large/006tNbRwgy1fv84tvzjepj30m80go773.jpg)
- 计算多个源程序文件
![1486866-20180910214825906-1674590583](https://ws2.sinaimg.cn/large/006tNbRwgy1fv84vjfy6ij31i60o8dnb.jpg)
![1486866-20180910214856432-576740391](https://ws3.sinaimg.cn/large/006tNbRwgy1fv84w4xgd0j30m80godio.jpg)
- 计算文件夹中的所有源程序文件
- ![1486866-20180910215505223-505225827](https://ws4.sinaimg.cn/large/006tNbRwgy1fv84x8s283j31hm0o846v.jpg)
![1486866-20180910215558638-344785501](https://ws1.sinaimg.cn/large/006tNbRwgy1fv84xc0ngjj30m80gogog.jpg)

### **五、项目总结**
通过本项目的实践，熟悉了开发软件的相关工作流程（分析项目需求、设计项目流程、实现项目功能、进行单元测试、程序功能运行测试、项目总结）。 