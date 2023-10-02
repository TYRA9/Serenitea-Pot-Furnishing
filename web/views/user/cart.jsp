<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script>
        $(function () {
            //1.确认用户是否退出
            $("#logout-href").click(function () {
                return confirm("你确定退出🐎?");
            })

            //2.对购物车中商品数量的校验
            //Cart Plus Minus Button(From main.js)
            //需要注释掉main.js中对应的源码部分，否则会造成重复操作。
            var CartPlusMinus = $(".cart-plus-minus");
            CartPlusMinus.prepend('<div class="dec qtybutton">-</div>');
            CartPlusMinus.append('<div class="inc qtybutton">+</div>');
            $(".qtybutton").on("click", function() {
                var $button = $(this);
                var oldValue = $button.parent().find("input").val();
                if ($button.text() === "+") {
                    var newVal = parseFloat(oldValue) + 1;
                } else {
                    // Don't allow decrementing below zero
                    if (oldValue > 1) {
                        var newVal = parseFloat(oldValue) - 1;
                    } else {
                        newVal = 1;
                    }
                }
                $button.parent().find("input").val(newVal);
                //模仿前端得到实时cnt的方法，得到临时属性fid
                var $fid = $button.parent().find("input").attr("fid");

                //发出更改购物车信息的请求(newVal即是最终的cnt的实时的值)
                location.href="shoppingCartServlet?action=alterCartItem&fid=" + $fid + "&uid=${sessionScope.potUser.id}&cnt=" + newVal;
            });

            //3.对删除摆设的校验(为所有符合条件的a标签绑定该点击事件)
            $("a.remove-icon").click(function () {
                return confirm("呐~ 你确定删除该摆设🐎?");
            })

            //4.对清空购物车的操作进行校验
            $("#clear-all-a").click(function () {
                return confirm("嘤嘤嘤~ 你真的忍心清除🛒中所有的商品🐎?");
            })

            //5.对结账时购物车是否为空的校验
            $("a.checkout-a").click(function () {
                if (${sessionScope.totalPrice eq 0} || ${sessionScope.cartItems eq null}) {
                    alert("你购物车里啥jb都没有😅，结个屁账？\n op是这样的😅...");

                    return false;
                }
            })
        })
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
                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">
                        <div class="header-bottom-set dropdown">
                            <a>Welcome : ${sessionScope.potUser.username}</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="orderServlet?action=showMyOrders">Order Admin</a>
                        </div>
                        <div class="header-bottom-set dropdown" id="logout-href">
                            <a href="potUserServlet?action=logout">Log Out</a>
                        </div>
                    </div>
                </div>
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

<!-- OffCanvas Cart Start -->

<!-- OffCanvas Cart End -->

<!-- OffCanvas Menu Start -->

<!-- OffCanvas Menu End -->


<!-- breadcrumb-area start -->


<!-- breadcrumb-area end -->

<!-- Cart Area Start -->
<div class="cart-main-area pt-100px pb-100px">
    <div class="container">
        <h3 class="cart-page-title" style="font-family: consolas">Your Shopping Cart</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">
                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>picture</th>
                                <th>name</th>
                                <th>unitPrice</th>
                                <th>cnt</th>
                                <th>totalPrice</th>
                                <th>operate</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--使用JSTL遍历cartItems集合，循环显示摆设--%>
                            <c:forEach items="${sessionScope.cartItems}" var="cartItem">
                            <tr>
                                <td class="product-thumbnail">
                                    <a href="#"><img class="img-responsive ml-3"
                                                     src="resources/images/product-image/1.jpg"
                                                     alt=""/></a>
                                </td>
                                <td class="product-name"><a href="#">${cartItem.name}</a></td>
                                <td class="product-price-cart"><span class="amount">${cartItem.unitPrice}摩拉</span></td>
                                <td class="product-quantity">
                                    <div class="cart-plus-minus">
                                        <%--
                                            此处为cnt相关的input标签定义一个临时的fid属性，
                                            用于保存当前摆设的fid。供JQuery使用以发出HTTP请求。
                                         --%>
                                        <input fid="${cartItem.fid}" class="cart-plus-minus-box" type="text" name="qtybutton" value="${cartItem.cnt}"/>
                                    </div>
                                </td>
                                <td class="product-subtotal">${cartItem.totalPrice}</td>
                                <td class="product-remove">
                                    <a class="remove-icon" href="shoppingCartServlet?action=delete&fid=${cartItem.fid}&uid=${sessionScope.potUser.id}"><i class="icon-close"></i></a>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="cart-shiping-update-wrapper" style="font-family: consolas">
                                <h4>In total <span style="color: deepskyblue">${sessionScope.cartItems.size()}</span> pieces<br/>In total <span style="color: deepskyblue; font-family: consolas;">${sessionScope.totalPrice}</span> Mora</h4>
                                <div class="cart-shiping-update">
                                    <a class="checkout-a" href="orderServlet?action=saveOrder">checkout</a>
                                </div>
                                <div class="cart-clear">
                                    <button>CONTINUE SHOPPING</button>
                                    <a id="clear-all-a" href="shoppingCartServlet?action=deleteAll&uid=${sessionScope.potUser.id}">Clear All</a>
                                </div>
                            </div>
                        </div>
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