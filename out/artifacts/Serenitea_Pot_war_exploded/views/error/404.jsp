<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>Serenitea-Pot</title>
    <base href="<%=request.getContextPath() + "/"%>"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="resources/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="resources/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="resources/css/style.min.css"/>

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
                        <a href="index.jsp"><img src="resources/images/logo/logo.png" alt="Site Logo" width="360" height="65"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->
                <!-- Header Action Start -->
                <!-- Header Action End -->
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
                        <a class="active" href="index.jsp">
                            <h4 style="color: darkblue">404——您访问的页面不存在，点击返回首页</h4>
                        </a>
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
        <%--footer-top--%>
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