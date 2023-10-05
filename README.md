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
## 1.用户注册 : 
### 1.1 演示
  当前pot_user表中有8位用户，如下图所示 : (PS : 管理员也可以作为普通用户登录，只不过登录窗口不同。)  
    ![image](https://github.com/TYRA9/Serenitea-Pot-Furnishing/assets/99473764/d2d83317-7e0f-4a6c-b345-f78e34fbec20)
    
  启动web应用后会自动跳转到首页，在首页可以点击"Log in|Sign up"进行注册功能：
    ![image](https://github.com/TYRA9/Serenitea-Pot-Furnishing/assets/99473764/c788297c-63be-4f4b-b51f-3f83f13d335a)
    
  用户注册表单中，任何一项数据不满足格式都会提交失败。  
  提交成功后，可以在pot_user表中查看到新的用户信息，如下图所示 :   
    ![image](https://github.com/TYRA9/Serenitea-Pot-Furnishing/assets/99473764/06b34a10-4885-4621-b546-0fa80a326aa2)

### 1.2 实现思路
  **(1) 创建表和对应的JavaBean类 :**  
　　	pot_user表字段的设计可参考前端提供的界面，此处仅定义了id,username,password,email四个字段。  
  对应地，给出PotUser类，并给出无参和带参构造，以及四个字段的getter和setter方法。  
  **(2) DAO层 :**  
  　　DAO层分为DAO的接口类型和它的实现类型,这么设计是为了提高可拓展性；并且，DAO层的实现类型还要继承BasicDAO类。PotUserDAOImpl中要实现savePotUser方法，通过INSERT INTO的sql语句，将形参列表传入的PotUser对象中保存的信息写入到pot_user表中，注意密码要以MD5加密形式保存，不要明文保存。  
  　　PotUserDAOImpl中还要定义一个queryPotUserByUsername方法，即根据传入的username判断该用户是否已经存在于pot_user表中，用于后端判断用户名是否可用，实际上，前端已经可以通过Ajax实现用户名是否可用的判断。  
  **(3) Service层 :**  
  　　Service层同样分为Service的接口类型和它的实现类型(以下功能都同理，之后不再赘述)。遵循Service层调用DAO层的原则，首先要在PotUserServiceImpl类中定义一个私有的PotUserDAO对象(多态)。然后分别定义用户注册和判断用户名是否存在的方法，在方法中通过类成员位置定义好的PotUserDAO对象来调用DAO层的方法。PotUserServiceImpl中的registerPotUser方法和IsExistsByUsername方法，可利用三元运算符，将返回值类型变为boolean类型。  
	**(4) Web层 ：**  
 　　定义PotUserServlet。遵循“Web层调用Service层”的原则，首先在PotUserServlet类中定义一个私有的PotUserService类对象(多态)。PotUserServlet类不去直接继承HttpServlet类，即不直接在PotUserServlet类中重写doPost和doGet方法；而是采用**模板设计模式**，额外定义一个BasicServlet抽象类，让该类去继承HttpServlet方法，并重写doPost和doGet方法；合并doPost和doGet方法，在doPost方法中，先得到一个请求参数action，然后利用action，**反射调用**该action对应的方法。[反射调用——即基本的通过反射机制来调用方法]。对应的，在PotUserServlet类中要定义一个用户注册的方法，只有当action的值与该方法名相同时，BasicServlet中的doPost方法中的反射调用机制才能成功地调用PotUserServlet类中的用于用户注册的该方法。  
　　为防止恶意注册，使用验证码机制,此时需要导入两个jar包，kaptcha和filter。由于Tomcat10统一使用Jakarta，**因此直接使用kaptcha会提示不兼容的报错。解决之道**————在Web层下自定义一个类，eg : CyanKaptchaServlet, 然后将com.google.code.kaptcha.servlet包下的KaptchaServlet类中的代码拷贝过来，将servlet相关的javax全部替换为jakarta。最终使用的就是该自定义的CyanKaptchaServlet。PotUserServlet的register方法中，要根据KAPTCHA_SESSION_KEY来获取session域中保存的已生成的正确的验证码，获取到正确的验证码后，要立即删除session域中的**KAPTCHA_SESSION_KEY**。然后与表单提交的验证码进行比较，若不一致，不进行注册操作，并向前端页面回显相关提示信息(用户名，邮箱，错误信息)。若验证码一致，进行注册操作，首先通过potUserService对象的isExistsByUsername方法判断用户名是否可用，若用户名可用，完成注册功能并转发到注册成功的页面。    
　　PotUserServlet中还需要再单独定义一个判断用户名是否可用的方法，用以回送Ajax异步请求，该方法中要先将验证结果放入到Map集合中，再返回该Map集合的JSON字符串形式.(此处需要用到JSON相关的jar包)  
	**(5) 前端 ：**  
 　　为注册表单的提交按钮绑定点击事件，通过正则表达式对用户输入的数据进行校验，若不匹配则拒绝提交并给出提示信息。为注册表单的验证码图片绑定点击事件，使得用户每次点击该图片，都会访问"<%=request.getContextPath()%>/cyanKaptcha?date=" + new Date(),以生成一张随机的新的验证码图片.为用户注册表单的用户名输入框绑定失去焦点事件,使用Ajax发送异步请求，即时判断该用户名是否可用。
  	
## 2.用户登录 : 
### 2.1 演示
　　注册成功后，用户可以在首页点击"Log in|Sign up"进行登录操作，如下图所示 : 
		![image](https://github.com/TYRA9/Serenitea-Pot-Furnishing/assets/99473764/8ac4d40e-b01a-4036-a448-35a8502e1382)  
　　同用户注册一样，如果用户输入的用户名，密码或验证码不合法，则无法登录。若输入信息合法但用户名或密码错误，则登录失败。  
　　登录成功后，跳转到登录成功页面。如下图所示 : 
　　![image](https://github.com/TYRA9/Serenitea-Pot-Furnishing/assets/99473764/a14f4a24-c8c0-4c03-9d75-60fc8f55064c)


### 2.2 实现思路
  **(1) 创建表和对应的JavaBean类 :**  
　　	沿用pot_user和PotUser.  
	**(2) DAO层 :**  
  　　在PotUserDAOImpl类中新实现一个queryPotUserByUsernameAndPassword方法，该方法通过传入的用户名和密码，来判断数据库中是否存在该用户。注意，密码仍然要使用MD5加密模式，否则找不到。  
  **(3) Service层 :**  
  　　login方法中调用DAO层的queryPotUserByUsernameAndPassword方法，返回一个PotUser对象。  
	**(4) Web层 ：**   
 　　为防止恶意破解密码，刷票，灌水等行为，用户登录也要使用验证码机制，使用方式及原理同用户注册，此处不再赘述。
 　　定义一个login方法。首先根据用户传入的用户名和密码构建一个只有username和password不为空的PotUser对象。然后通过调用Service层中的login方法返回一个PotUser对象。判断该对象如果不为空，说明用户名密码存在且正确，登录成功，将正确的PotUser对象放入到session域中，并请求转发到登录成功页面。若判断为空，说明该用户不存在，可能是用户名或密码输入错误，因此要回显给前端页面提示信息，回显的工作可以通过放入request域 + EL表达式实现。  
 **(5) 前端 ：**  
 　　为用户登录表单的提交按钮绑定点击事件，通过正则表达式对用户输入的数据进行校验，若不匹配则拒绝提交并给出提示信息。为登录表单的验证码图片绑定点击事件，使得用户每次点击该图片，都会访问"<%=request.getContextPath()%>/cyanKaptcha?date=" + new Date(),以生成一张随机的新的验证码图片.首页需要判断当前是否有用户登录，并根据用户是否登录，给出不同的功能界面。  

## 3.管理员后台登录 : 
### 3.1 演示
　　管理员登录的页面不对外开放，用户在首页看不到管理员的登录页面，只有管理员知道该页面在哪儿，如下图所示 :   
		![image](https://github.com/TYRA9/Serenitea-Pot-Furnishing/assets/99473764/2e386192-2484-47ff-9984-6a3b3dfdbb84)
　　管理员也可以作为用户登录。但是，若在管理员登录界面登入，将直接进入摆设后台管理页面，也就是说，管理员在执行管理操作时，并不参与普通用户的功能。
### 3.2 实现思路
  **(1) 创建表和对应的JavaBean类 :**  
　　	新创建pot_manager表以及对应的PotManager类.(区别于普通用户，管理员有自己的表和对应的JavaBean,以及自己的一套DAO-Service-Web实现)  
	**(2) DAO层 :**  
  　　DAO层实现与普通用户登录时相似，定义queryPotManagerByUsernameAndPassword方法查询pot_manager表中有无对应的管理员。注意，查询密码时仍然要使用MD5加密的格式，否则查不到。  
	**(3) Service层 :**  
  　　同普通用户登录类似，login方法中调用DAO层的queryPotManagerByUsernameAndPassword方法，返回一个PotManager对象。  
	**(4) Web层 ：**   
 　　定义一个login方法。首先根据传入的用户名和密码构建一个临时的PotManager对象。调用potManagerService中的login方法，通过该临时对象得到一个从数据库中查询得到的PotManager对象。判断该对象如果不为空，说明用户名密码存在且正确，登录成功，将正确的PotManager对象放入到session域中，并请求转发到后台管理页面manage_menu.jsp。若判断为空，说明该管理员不存在，可能是用户名或密码输入错误，因此要回显给前端页面提示信息，回显的工作可以通过放入request域 + EL表达式实现。PS : 将正确的PotManager对象放入到session域中的同时，还要将该PotManager对象的id，此处key命名为mgrId，单独放入到request域中，此举的目的是为了服务于前端页面，前端页面会根据request域中是否存在该mgrId，来给出不同的服务(通过JSTL的<c:if>标签实现)。并且，由于管理员和普通用户之后会共用order.jsp以及order_detail.jsp，因此也可以用这种方法来区分当前访问页面的是管理员还是普通用户[体现出了request域的精确性]。  
 	**(5) 前端 ：**  
 　　manage_login.jsp依然是为提交按钮绑定点击事件，通过正则表达式对传入的数据进行前端的校验.

## 4.摆设管理(展示，添加，修改，删除) : 
### 4.1 演示
　　管理员登录后会进入后台管理页面，如下图所示 : 
		![image](https://github.com/TYRA9/Serenitea-Pot-Furnishing/assets/99473764/6ef4e093-d9e1-41d1-a881-93e203669a92)  
		后台管理页面会显示当前管理员的username，在后台管理页面可以进入“摆设管理”和“订单管理”两大界面。(只有这三个页面会显示管理员的username)  
		摆设管理界面如下 ：  
		![image](https://github.com/TYRA9/Serenitea-Pot-Furnishing/assets/99473764/7da9c326-34e4-4525-bb34-a5a1dd3d52e7)  
		摆设要进行分页展示。  
		在摆设管理页面furn_manage.jsp，可以进行摆设的修改，删除和添加；其中，摆设的修改会单独跳转到furn_update.jsp页面，而摆设的添加会单独跳转到furn_add.jsp页面。

### 4.2 实现思路
  **(1) 创建表和对应的JavaBean类 :**  
　　	新创建furnishing表以及对应的Furnishing类。为了实现摆设管理界面的分页显示，需要在domain包下额外定义一个Page类，Page并不与数据库中某一张真正的表相对应，而是一个抽象出来的数据模型，对furnishing表进行了处理，包含了你想要展示的furnishing表中的特定部分，也包含了分页相关的各种信息(总共的记录条数；每张分页要展示的记录条数；总共的分页数；当前分页的页码；分页要展示的所有摆设的集合；以及用于前端进行分页导航处理的url)；并且，为提高可扩展性，应该给Page数据模型一个泛型.  
	**(2) DAO层 :**  
  　　摆设展示————定义一个queryFurnishings方法用于获取所有的摆设，返回一个List集合。  
  　　摆设添加————定义一个addFurnishing方法，以对象的形式为INSERT INTO语句中的参数赋值。  
  　　摆设修改————定义一个queryFurnishingById方法，用以前端页面修改摆设时的数据回显；再定义一个updateFurnishing方法，用于提交修改后的数据到数据库中。  
  　　摆设删除————定义一个deleteFurnishing方法，根据摆设唯一对应的id，从数据库furnishing表中删除对应的记录。  
  　　分页展示的实现————1>通过统计函数COUNT(*)统计数据库中furnishing表中总共的记录条数，并以intValue返回。2>通过分页查询LIMIT start rows语句获取furnishing表中要展示的数据。  
	**(3) Service层 :**  
  　　摆设展示————Service层调用DAO层，DQL语句。  
  　　摆设添加————Service层调用DAO层，DML语句，返回受影响的行数。  
  　　摆设修改————Service层调用DAO层，DQL语句返回要修改的摆设;DML语句执行修改操作.。  
  　　摆设删除————Service层调用DAO层，DML语句，返回受影响的行数。  
  　　分页展示的实现————定义一个getPage方法,对Page抽象数据模型进行初始化；之前在DAO层定义的统计furnishing表中总的记录条数的方法和通过分页查询获取展示数据的方法都是为Service层的getPage方法进行服务的；getPage方法需要传入两个形参，pageNumber和rows，rows的作用自不必多说，pageNumber是当前分页的页码，该参数用于计算分页查询中的start, start = (pageNumber - 1) * rows, 得到start后，就可以通过调用DAO层定义的分页查询的getPageItems方法获取要展示的items集合; 分页总数pageAmount用recordSum / rows计算得出，需要注意的是，若recordSum % rows > 0，代表有记录数不足rows的一页，需要对pageAmount + 1；通过Page抽象数据模型提供的setter方法完成对Page对象的初始化，最后返回Page对象。  
	**(4) Web层 ：**   
  　　摆设展示————Web层调用Service层，定义paging方法，获取请求参数中的pageNumber和rows，然后调用Service层的getPage方法得到page对象；将page对象放入到request域中，然后请求转发到摆设管理页面furn_manage.jsp。  
  　　摆设添加————使用Apache的BeanUtils来完成数据的封装,调用Service层的addFurnishing方法完成摆设的添加；furn_manage.jsp的Add超链接将最后一页的页码传入，以实现“添加摆设”后自动跳转到最后一页(通过请求参数中的pageNumber属性，请求重定向到用于分页展示的paging方法)。  
  　　摆设修改————较为复杂。首先需要listSpecificOne方法用于给管理员回显要修改的摆设的数据，将要修改的摆设对象放入到request域中，然后请求转发到furn_update页面；其次，当管理员提交修改操作时，调用需update方法。要用户“文件上传与下载”的三个jar包。首先根据id获取到要修改的摆设对象，该对象是一个临时对象，为了达到“取出--修改--放入--再取出”的目的；通过API得到表单提交的数据的集合，然后遍历获取到的集合，如果是文本类型，就一一判断各自是什么文本类型，通过Furnishing对象的setter方法修改对应的属性；如果是文件类型，说明要修改摆设的图片，创建目录，写入文件，然后修改furnishing对象的imgPath属性。[**尤其要注意路径相关的问题**]，最后，在通过调用Service层的updateFurnishing方法，完成对数据库中数据的更改，和摆设添加同理，也要通过请求参数获取当前的pageNumber，然后请求重定向到用于分页展示的paging方法，不过同摆设添加时不一样的是，摆设修改后要停留在修改摆设的那一页。  
  　　摆设删除————通过id删除指定摆设，删除成功后，请求重定向到删除时所在分页。    
 	**(5) 前端 ：**  
  　　摆设展示————每一分页都通过JSTL的<c:foreach>标签循环展示request域中的page对象的items属性中保存的摆设；使用分页导航实现分页展示的功能，借助JSTL的<c:choose>标签，通过算法控制显示的页数。  
  　　摆设添加————furn_manage.jsp页面点击Add Furnishing, 会跳转到furn_add.jsp页面。  
  　　摆设修改————在furn_update.jsp页面中，前端要通过正则表达式对数据进行校验。注意，修改摆设数据的表单的action属性中，要通过EL的内置对象param取出pageNumber属性，并传递下去。  
  　　摆设删除————为删除按钮绑定点击事件，通过confirm方法确认管理员是否要删除摆设。.

## 5.首页展示摆设 : 
### 5.1 演示

### 5.2 实现思路  


