<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>Serenitea-Pot</title>
    <base href="<%=request.getContextPath() + "/"%>"/>
    <!-- 移动端适配 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="resources/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="resources/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="resources/css/style.min.css">
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script>
        $(function () {
            //1.给"Log Out"按钮绑定事件
            $("#logout-href").click(function () {
                return confirm("你确定退出🐎?");
            })
        })
    </script>
</head>

<body>
<!-- Header Area start  -->
<div class="header section">
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
                <div class="col align-self-center">
                    <div class="header-actions">
                        <%-- 利用两个c:if标签，分别显示管理员和普通用户访问order.jsp时的不同功能--%>
                        <c:if test="${empty requestScope.mgrId}">
                            <div class="header-bottom-set dropdown">
                                <a>Welcome : ${sessionScope.potUser.username}</a>
                            </div>
                            <div class="header-bottom-set dropdown" id="logout-href">
                                <a href="potUserServlet?action=logout">Log Out</a>
                            </div>
                        </c:if>
                        <c:if test="${not empty requestScope.mgrId}">
                            <div class="header-bottom-set dropdown">
                                <a>Welcome Mgr : ${sessionScope.potManager.username}</a>
                            </div>
                            <div class="header-bottom-set dropdown">
                                <a href="potManagerServlet?action=transmit&mgrId=${requestScope.mgrId}">Admin</a>
                            </div>
                        </c:if>
                    </div>
                </div>
                <!-- Header Action End -->
            </div>
        </div>
    </div>
    <!-- Header Bottom  End -->
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
<!-- Cart Area Start -->
<div class="cart-main-area pt-70px pb-100px">
    <div class="container">
        <h3 class="cart-page-title">Order Administration</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">
                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>number</th>
                                <th>date</th>
                                <th>sums</th>
                                <th>status</th>
                                <th>details</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%-- 此处两个if语句，体现出了request域相比session域的一个优点————精准 --%>
                            <c:if test="${not empty requestScope.mgrId}">
                                <c:forEach items="${requestScope.orders}" var="order">
                                    <tr>
                                        <td class="product-name">${order.number}</td>
                                        <td class="product-name">${order.dateTime}</td>
                                        <td class="product-price-cart"><span class="amount">${order.sums}摩拉</span></td>
                                        <c:if test="${order.status eq 0}">
                                            <td class="product-name"><a href="#">未发货</a></td>
                                        </c:if>
                                        <c:if test="${order.status eq 1}">
                                            <td class="product-name"><a href="#">已发货</a></td>
                                        </c:if>
                                        <td class="product-remove">
                                            <a href="potManagerServlet?action=transmitToDetail&mgrId=${requestScope.mgrId}&orderNumber=${order.number}"><i class="icon-eye"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty requestScope.mgrId}">
                                <c:forEach items="${requestScope.myOrders}" var="myOrder">
                                    <tr>
                                        <td class="product-name">${myOrder.number}</td>
                                        <td class="product-name">${myOrder.dateTime}</td>
                                        <td class="product-price-cart"><span class="amount">${myOrder.sums}摩拉</span></td>
                                        <c:if test="${myOrder.status eq 0}">
                                            <td class="product-name"><a href="#">未发货</a></td>
                                        </c:if>
                                        <c:if test="${myOrder.status eq 1}">
                                            <td class="product-name"><a href="#">已发货</a></td>
                                        </c:if>
                                        <td class="product-remove">
                                            <a href="orderServlet?action=showOrderItems&orderNumber=${myOrder.number}"><i class="icon-eye"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Cart Area End -->

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