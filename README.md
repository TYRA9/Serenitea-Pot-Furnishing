web项目——尘歌壶摆设购买系统(Serenitea-Pot-Furnishing)，采用原生Servlet + JSP完成开发，用到了HTML+CSS+JS, JSON, Tomcat, Servlet, JSP, EL+JSTL, Session, Filter, JQuery, Ajax等内容。    

**PS1: 项目十大功能————(_功能演示及具体的实现思路见下文_)**  
**(1)用户注册; (2)用户登录; (3)管理员后台登录; (4)摆设管理[摆设展示，摆设添加，摆设修改，摆设删除]; (5)首页展示摆设[分页]**  
**(6)首页搜索; (7)用户退出; (8)购物车管理[展示，添加，修改，删除，清空]; (9)订单管理[结账，查看订单，订单详情]; (10)上传更新摆设图片**  
  
**PS2: 测试时运行环境————**  
**(1)操作系统：Windows 10**  
**(2)编译器：IDEA**  
**(3)JDK版本：JDK 17.0**  
**(4)数据库及版本：MySQL 8.0**  
**(5)Tomcat版本：Tomcat 10.0**
___

# 一、前言
## 1.软件开发的各个阶段
### 1.1 需求分析阶段
>    (1) **人员** : 需求分析师/产品经理(前者针对于客户；后者针对于自身)  
>    (2) **技能** : 技术背景 + 行业背景  
>    (3) **任务** : 完成需求分析 --> 需求分析白皮书  
>    (4) **要求** : 必须准确地分析出客户/项目需要完成的功能

### 1.2 设计阶段
>    (1) 人员 : 项目架构师/项目经理  
>    (2) 技能 : 技术全面 + 项目管理 + 人员管理  
>    (3) 任务 :   
　　　1> 设计工作(UML类图 + 时序图 + 流程图 + 数据库设计 + 项目模块划分 + 界面UI + ...) --> 设计文档  
　　　2> 组建团队(选人)  
　　　3> 技术选型([JSP + servlet], SSM, [spring boot + spring cloud + Vue], 数据库)  
>    (4) 原型开发/搭建基本界面，签署协议

### 1.3 开发阶段
>    (1) 人员 : 程序⚪/码农，软件开发工程师(前端 + 后端)  
>    (2) 技能 : 软件开发  
>    (3) 任务 : 查阅文档 + 理解需求 + 完成指定模块功能，交付小组组长(组长会review代码)  
>    (4) 自行测试代码  

### 1.4 测试阶段
>    (1) 人员 : 测试工程师  
>    (2) 技能 : 编写测试用例 + 黑盒测试 + 白盒测试 + 压力测试 + 自动化测试工具 + 测试脚本 + ...  
>    (3) 任务 : 完成单元测试，集成测试，系统测试...  
>    (4) **测试和开发阶段是交替进行的**。
  
### 1.5 实施阶段  
>    (1) 人员 : 实施工程师  
>    (2) 技能 : 了解开发，熟悉各种环境，熟悉网络及网络设备/配置，熟悉操作系统，熟悉框架软件安装  
>    (3) 任务 : 将开发好的项目部署到本地或对方公司内部的系统上，往往设计出差和用户交互用户  

### 1.6 维护阶段
>    (1) 人员 : 大公司/大项目 --> 维护部门；小公司/小项目 --> 售后对接，也可以是实施人员来维护  
>    (2) 任务 : 解决项目使用过程中的出现的错误  
>    (3) 先重启 --> 重新安装软件配置 --> 重装系统 --> 反馈给开发(项目经理)

## 2.项目架构相关
### 2.1 Java后端经典三层架构
  如下图所示 : 
    ![A_Java后端三层架构](https://github.com/TYRA9/Serenitea-Pot-Furnishing/assets/99473764/442172f0-01e8-47be-b173-173056264201)  
  项目分包情况如下图所示 : 
    ![B_项目分包方案](https://github.com/TYRA9/Serenitea-Pot-Furnishing/assets/99473764/fad21dac-2012-4e61-a7ac-415ac9a8d9a4)

### 2.2 MVC
  (1) MVC，全称Model(模型)，View(视图)，Controller(控制器)。  
  (2)　　**Model** : 将与业务逻辑相关的数据**封装**为具体的JavaBean类(JavaBean/Domain/POJO)，其中不掺杂任何与数据处理相关的代码。  
　　　**View** : 只负责数据和界面的显示，不接受任何与**显示**数据无关的代码，便于程序员和美工的分工合作。(Vue/Jsp/Thymeleaf/HTML)。   
　　　**Controller** : 只负责接收请求，调用Service层的代码处理请求，然后派发页面，起到“**调度者**”的作用。  
  (3) MVC最早出现于JavaEE三层架构中的Web层，可以指导Web层的代码实现有效分离，单独工作，其本质就是将软件代码拆分成为组件单独开发，组合使用，达到了**解耦**的目的。MVC是一种思想，体现的是数据显示，数据处理和业务调用的分离解耦。SpringMVC就是一种基于MVC思想的框架。  
    
  MVC思想在WEB层的应用如下图所示 : 
    ![C_web层MVC具体情况](https://github.com/TYRA9/Serenitea-Pot-Furnishing/assets/99473764/c95cd733-ee81-4451-9383-9e1e3c168457)

# 功能演示及具体实现
## 1.用户注册
### 1.1 演示
  当前pot_user表中有
### 1.2 实现思路

