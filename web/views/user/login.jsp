<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <!-- 增加base标签，注意URL开头的/被浏览器端解析的结果. -->
    <base href="<%= request.getContextPath() + "/" %>"/>
    <link rel="stylesheet" href="resources/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="resources/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="resources/css/style.min.css"/>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function () {

            //1.为用户注册的提交按钮绑定事件
            $("#sub-btn").click(function () {
                var usernameVal = $("#username").val();

                //1.1 完成对用户名格式的校验 (设置用户名的正则表达式;注意此处不能随意打空格)
                var usernamePattern = /^\w{4,10}$/;
                if (!usernamePattern.test(usernameVal)) {
                    //根据前端人员给出的errorMsg <span>标签，打印出提示的错误信息
                    $("span[class='errorMsg']").text("用户名格式错误！请输入由数字，字母，下划线组成的4~10位字符.");

                    //需要返回一个false，表示不提交。
                    return false;
                }

                //1.2 完成用户密码格式的校验
                var passwordVal = $("#password").val();
                var passwordPattern = /^\w{6,10}$/;
                if (!passwordPattern.test(passwordVal)) {
                    //此处正则表达式使用了JQuery的基本过滤器(注意这种特殊的使用格式)
                    $("span.errorMsg").text("密码格式错误！请输入由数字，字母，下划线组成的6~10位字符.");
                    return false;
                }

                //1.3 完成用户确认密码格式的校验
                var rePwdVal = $("#rePwd").val();
                if (rePwdVal != passwordVal) {
                    $("span[class='errorMsg']").text("两次输入的密码不一致!");
                    return false;
                }

                //1.4 完成用户邮箱格式的校验
                var emailVal = $("#email").val();
                var emailPattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
                if (!emailPattern.test(emailVal)) {
                    $("span.errorMsg").text("电子邮件格式不符合!");
                    return false;
                }

                //1.5 完成对验证码是否为空的校验
                var codeText = $("#register-code-input").val();

                    //"$."在此处表示调用方法
                codeText = $.trim(codeText);
                if (codeText == null || codeText == "") {
                    $("span.errorMsg").text("验证码不能为空!");
                    return false;
                }
            });

            //2.为用户登录的提交按钮绑定事件
            $("#login-btn").click(function () {
                //2.1 完成对用户登录———用户名的校验
                var loginUsernameVal = $("#login-username").val();
                var loginUsernameValPattern = /^\w{4,10}$/;
                if (!loginUsernameValPattern.test(loginUsernameVal)) {
                    $("span[class='login-errorMsg']").text("用户名格式错误！请输入由数字，字母，下划线组成的4~10位字符.");

                    //需要返回一个false，表示不提交。
                    return false;
                }

                //2.2 完成对用户登录———用户密码的校验
                var loginPasswordVal = $("#login-password").val();
                var loginPasswordPattern = /^\w{6,10}$/;
                if (!loginPasswordPattern.test(loginPasswordVal)) {
                    $("span[class='login-errorMsg']").text("密码格式错误！请输入由数字，字母，下划线组成的6~10位字符.");

                    return false;
                }

                //2.3 完成对用户登录验证码是否为空的校验
                var codeText = $("#login-code-input").val();

                    //"$."在此处表示调用方法
                codeText = $.trim(codeText);
                if (codeText == null || codeText == "") {
                    $("span.login-errorMsg").text("验证码不能为空!");
                    return false;
                }

                //2.4 若经过potUserServlet的验证后，该用户不存在于数据库中，则给出request域中的提示信息
                        //利用EL表达式在表单处给出提示

                //2.5 全部验证通过，则会将登录表单提交给potUserServlet
            });

            //3.为用户注册时的验证码绑定事件
            $("#register-code-img").click(function () {
                /*
                    很多浏览器在地址栏URL没有发生新变化时，图片不会发出新的请求。
                    因此可以给图片的url增加一个动态变化的后缀。
                 */
                this.src = "<%=request.getContextPath()%>/cyanKaptcha?date=" + new Date();
            })

            //4.为用户登录时的验证码绑定事件
            $("#login-code-img").click(function () {
                this.src = "<%=request.getContextPath()%>/cyanKaptcha?date=" + new Date();
            })

            //5.Ajax校验注册的用户名是否存在
            $("#username").blur(function () {
                //获取注册表单中用户名输入框的value
                var username = this.value;

                //使用JQuery操作Ajax的$.getJSON方法
                    //function是触发事件后调用的函数
                $.getJSON("potUserServlet", "action=verifyUsername&username=" + username, function (data) {
                    // console.log("data= ", data.verifiedResult)
                    if (data.verifiedResult) {
                        $("span.errorMsg").text("该用户名已存在！");
                    } else {
                        $("span.errorMsg").text("用户名可用");
                    }
                });
            })
        });
    </script>
</head>

<body>
<!-- Header Area start  -->
<div class="header section">
    <!-- Header Top Message Start -->
    <!-- Header Top  End -->
    <!-- Header Bottom  Start -->
    <div class="header-bottom d-none d-lg-block">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.jsp"><img src="resources/images/logo/logo.png" alt="Site Logo" width="360"
                                                  height="65"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->

            </div>
        </div>
    </div>
    <!-- Header Bottom  Start 手机端的header -->
    <div class="header-bottom d-lg-none sticky-nav bg-white">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.jsp"><img width="280px" src="resources/images/logo/logo.png"
                                                  alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->
            </div>
        </div>
    </div>
    <!-- Main Menu Start -->
    <div style="width: 100%;height: 50px;background-color: lightskyblue"></div>
    <!-- Main Menu End -->
</div>
<!-- Header Area End  -->
<!-- login area start -->
<div class="login-register-area pt-70px pb-100px">
    <div class="container">
        <div class="row">
            <div class="col-lg-7 col-md-12 ml-auto mr-auto">
                <div class="login-register-wrapper">
                    <div class="login-register-tab-list nav">
                        <a class="active" data-bs-toggle="tab" href="#lg1">
                            <h4>Log in</h4>
                        </a>
                        <a data-bs-toggle="tab" href="#lg2">
                            <h4>Sign up</h4>
                        </a>
                    </div>
                    <div class="tab-content">
                        <div id="lg1" class="tab-pane active">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <span class="login-errorMsg"
                                          style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px;">${requestScope.hint}</span>
                                    <span
                                          style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px;">${requestScope.loginError}</span>
                                    <span
                                            style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px;">${requestScope.skipMsg}</span>
                                    <form action="potUserServlet" method="post">
                                        <%-- 相当于手动给type=hidden的input标签指定了一个value --%>
                                        <input type="hidden" name="action" value="login"/>
                                        <input type="text" name="username" id="login-username" value="${requestScope.username}" placeholder="Username"/>
                                        <input type="password" name="password" id="login-password"
                                               placeholder="Password"/>
                                        <input type="text" id="login-code-input" name="loginCode" style="width: 50%"
                                               placeholder="验证码"/>　　<img id="login-code-img" style="width: 150px" height="50px" alt="" src="cyanKaptcha">
                                        <div class="button-box">
                                            <div class="login-toggle-btn">
                                                <input type="checkbox"/>
                                                <a class="flote-none" href="javascript:void(0)">Remember me</a>
                                                <a href="#">Forgot Password?</a>
                                            </div>
                                            <button type="submit" id="login-btn"><span>Login</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div id="lg2" class="tab-pane">
                            <div class="login-form-container">
                                <div class="login-register-form">
                                    <span class="errorMsg"
                                          style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px;"></span>
                                    <span
                                          style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px;">${requestScope.registerError}</span>
                                    <form action="potUserServlet" method="post">
                                        <%--注意type属性 = hidden的input标签，其使用与type=text, type=password类似--%>
                                        <input type="hidden" name="action" value="register"/>
                                        <input type="text" id="username" name="username" placeholder="Username" value="${requestScope.username}"/>
                                        <input type="password" id="password" name="password" placeholder="输入密码"/>
                                        <input type="password" id="rePwd" name="rePwd" placeholder="确认密码"/>
                                        <input name="email" id="email" placeholder="电子邮件" type="email" value="${requestScope.email}"/>
                                        <input type="text" id="register-code-input" name="registerCode" style="width: 50%"
                                               placeholder="验证码"/>　　<img id="register-code-img" style="width: 150px" height="50px" alt="" src="cyanKaptcha">
                                        <div class="button-box">
                                            <button type="submit" id="sub-btn"><span>Register</span></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- login area end -->

<!-- Footer Area Start -->
<div class="footer-area">
    <div class="footer-container">
        <div class="footer-top" style="background-color: lightseagreen; height: 250px">
            <div class="container">
                <div class="row">
                    <!-- Start single blog -->
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-sm-6 col-lg-3 mb-md-30px mb-lm-30px" data-aos="fade-up"
                         data-aos-delay="400">
                        <div class="single-wedge">
                            <h4 class="footer-herading">信息</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="https://blog.csdn.net/TYRA9?type=blog" target="_blank">关于 Cyan_RA9</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-2 col-sm-6 mb-lm-30px" data-aos="fade-up" data-aos-delay="600">
                        <div class="single-wedge">
                            <h4 class="footer-herading">我的账号</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="shoppingCartServlet?action=showCart&uid=${sessionScope.potUser.id}">我的购物车</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="800">
                    </div>
                    <!-- End single blog -->
                </div>
            </div>
        </div>
        <div class="footer-bottom" style="background-color: darkcyan">
            <div class="container">
                <div class="row flex-sm-row-reverse">
                    <div class="col-md-6 text-right">
                        <div class="payment-link">
                            <img src="#" alt="">
                        </div>
                    </div>
                    <div class="col-md-6 text-left">
                        <p class="copy-text" style="font-family: consolas">Copyright &copy; 2023 Cyan_RA9</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer Area End -->
<script src="resources/js/vendor/vendor.min.js"></script>
<script src="resources/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="resources/js/main.js"></script>
</body>
</html>