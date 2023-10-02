<%--
    该目录下的views/user/index.jsp页面原本是index.html页面，更改为jsp；
    而在web目录下的index.jsp页面, 则负责转发到本页面。
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%--
    注意————
    html更改为jsp页面后，若样式发生了变化，需要在jsp页面增加以下文档说明。
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
            //1.给前一页和后一页的分页导航绑定事件，防止“页码越界”
            $("#former-page").click(function () {
                if (${requestScope.page.pageNumber le 1}) {
                    return false;
                }
            })
            $("#latter-page").click(function () {
                if (${requestScope.page.pageNumber} >= ${requestScope.page.pageAmount}) {
                    return false;
                }
            })

            //2.给首页和末页的分页导航绑定事件，当没有摆设用于展示时，无法点击首页和末页
            $("#first-page").click(function () {
                if (${requestScope.page.pageAmount le 0}) {
                    return false;
                }
            })
            $("#last-page").click(function () {
                if (${requestScope.page.pageAmount le 0}) {
                    return false;
                }
            })

            //3.给"Log Out"按钮绑定事件
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
                        <a href="index.jsp"><img src="resources/images/logo/logo.png" alt="Site Logo"
                                                 width="360"
                                                 height="65"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->

                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">
                        <div class="header_account_list">
                            <a href="javascript:void(0)" class="header-action-btn search-btn"><i
                                    class="icon-magnifier"></i></a>
                            <div class="dropdown_search">
                                <form class="action-form" action="presentToUsersServlet" method="post">
                                    <input type="hidden" name="action" value="pagingByName"/>
                                    <input type="hidden" name="pageNumber" value="1"/>
                                    <input type="hidden" name="rows" value="4"/>
                                    <input class="form-control" name="name" placeholder="OP您来🌶，买点儿啥捏~" type="text">
                                    <button class="submit" type="submit"><i class="icon-magnifier"></i></button>
                                </form>
                            </div>
                        </div>
                        <!-- Single Wedge Start -->
                        <c:if test="${empty sessionScope.potUser}">
                            <div class="header-bottom-set dropdown">
                                <a href="views/user/login.jsp">Log in | Sign up</a>
                            </div>
                        </c:if>
                        <c:if test="${sessionScope.potUser ne null}">
                            <div class="header-bottom-set dropdown">
                                <%-- 注意此处的#最终会与base标签结合 --%>
                                <a>Welcome : ${sessionScope.potUser.username}</a>
                            </div>
                            <div class="header-bottom-set dropdown">
                                <a href="orderServlet?action=showMyOrders">Order Admin</a>
                            </div>
                            <div class="header-bottom-set dropdown">
                                <a href="potUserServlet?action=logout" id="logout-href">Log Out</a>
                            </div>
                        </c:if>
                        <!-- Single Wedge End -->
                        <%--
                            注意class对应样式对超链接跳转的限制！！！
                        --%>
                        <a href="shoppingCartServlet?action=showCart&uid=${sessionScope.potUser.id}" class="header-action-btn header-action-btn-cart pr-0">
                            <i class="icon-handbag"> Shopping Cart</i>
                            <span class="header-action-num">${sessionScope.cartItems.size()}</span>
                        </a>
                        <a href="#offcanvas-mobile-menu"
                           class="header-action-btn header-action-btn-menu offcanvas-toggle d-lg-none">
                            <i class="icon-menu"></i>
                        </a>
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
<br/>
<!-- Header Area End  -->

<!-- OffCanvas Cart Start 弹出cart -->
<!-- OffCanvas Cart End -->

<!-- OffCanvas Menu Start 处理手机端 -->
<!-- OffCanvas Menu End -->

<!-- Product tab Area Start -->
<div class="section product-tab-area">
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="tab-content">
                    <div class="tab-pane fade show active" id="tab-product-new-arrivals">
                        <div class="row">
                            <c:forEach items="${requestScope.page.items}" var="furnishing">
                                <div class="col-lg-3 col-md-6 col-sm-6 col-xs-6 mb-6" data-aos="fade-up"
                                     data-aos-delay="200">
                                    <div class="product">
                                        <div class="thumb">
                                            <a href="#" class="image">
                                                <img src="${furnishing.imgPath}" alt="Product"/>
                                                <img class="hover-image" src="${furnishing.imgPath}"
                                                     alt="Product"/>
                                            </a>
                                            <span class="badges">
                                                <c:if test="${furnishing.id % 2 == 0}">
                                                    <span class="sale">-10%</span>
                                                </c:if>
                                                <span class="new">New</span>
                                            </span>
                                            <div class="actions">
                                                <a href="#" class="action wishlist" data-link-action="quickview"
                                                   title="Quick view" data-bs-toggle="modal"
                                                   data-bs-target="#exampleModal"><i
                                                        class="icon-size-fullscreen"></i></a>
                                            </div>
                                            <a href="shoppingCartServlet?action=addCartItem&id=null&fid=${furnishing.id}&name=${furnishing.name}&unitPrice=${furnishing.price}&cnt=1&totalPrice=${furnishing.price * 1}&uid=${sessionScope.potUser.id}&pageNumber=${requestScope.page.pageNumber}&rows=${requestScope.page.pageSize}">
                                            <button title="Add To Cart" class="add-to-cart">
                                                Add To Cart
                                            </button>
                                            </a>
                                        </div>
                                        <div class="content">
                                            <h5 class="title">
                                                <a href="shop-left-sidebar.html">${furnishing.name}</a></h5>
                                            <span class="price">
                                                <span class="new">摆设:　${furnishing.name}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">厂家:　${furnishing.enterprise}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">单价:　${furnishing.price}摩拉</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">销量:　${furnishing.sales}</span>
                                            </span>
                                            <span class="price">
                                                <span class="new">库存:　${furnishing.stock}</span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--  Pagination Area Start -->
<div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
    <ul>
        <%--
            用户第一次登录的时候，
            Tomcat就根据URL调用了PresentToUsersServlet的pagingByName方法，
            该方法将一个page对象保存在了request域中(此处使用的page对象即这个page)。
         --%>
        <li><a href="${requestScope.page.url}&pageNumber=1&rows=${requestScope.page.pageSize}"
               id="first-page">首页</a></li>
        <li>
            <a href="${requestScope.page.url}&pageNumber=${requestScope.page.pageNumber - 1}&rows=${requestScope.page.pageSize}"
               id="former-page">上页</a></li>
        <%-- 此处利用算法，要求首页共显示5页 --%>
        <c:choose>
            <c:when test="${requestScope.page.pageAmount <= 5}">
                <c:set var="begin" value="1"></c:set>
                <c:set var="end" value="${requestScope.page.pageAmount}"></c:set>
            </c:when>
            <c:when test="${requestScope.page.pageAmount > 5}">
                <%-- JSTL中如果出现嵌套分支结构，要用c:choose来嵌套c:choose，而不是c:when --%>
                <c:choose>
                    <c:when test="${requestScope.page.pageNumber <= 3}">
                        <c:set var="begin" value="1"></c:set>
                        <c:set var="end" value="5"></c:set>
                    </c:when>
                    <c:when test="${requestScope.page.pageNumber > requestScope.page.pageAmount - 3}">
                        <c:set var="begin" value="${requestScope.page.pageAmount - 4}"></c:set>
                        <c:set var="end" value="${requestScope.page.pageAmount}"></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="begin" value="${requestScope.page.pageNumber - 2}"></c:set>
                        <c:set var="end" value="${requestScope.page.pageNumber + 2}"></c:set>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
<%--        <c:set var="begin" value="1"></c:set>--%>
<%--        <c:set var="end" value="${requestScope.page.pageAmount}"></c:set>--%>
        <c:forEach begin="${begin}" end="${end}" var="i">
            <c:if test="${requestScope.page.pageNumber == i}">
                <li><a href="${requestScope.page.url}&pageNumber=${i}&rows=${requestScope.page.pageSize}"
                       class="active">${i}</a></li>
            </c:if>
            <c:if test="${requestScope.page.pageNumber != i}">
                <li>
                    <a href="${requestScope.page.url}&pageNumber=${i}&rows=${requestScope.page.pageSize}">${i}</a>
                </li>
            </c:if>
        </c:forEach>
        <li>
            <a href="${requestScope.page.url}&pageNumber=${requestScope.page.pageNumber + 1}&rows=${requestScope.page.pageSize}"
               id="latter-page">下页</a>
        </li>
        <li>
            <a href="${requestScope.page.url}&pageNumber=${requestScope.page.pageAmount}&rows=${requestScope.page.pageSize}"
               id="last-page">末页</a>
        </li>
        <li><a>共${requestScope.page.pageAmount}页</a></li>
        <li><a>共${requestScope.page.recordSum}记录</a></li>
    </ul>
</div>
<!--  Pagination Area End -->
<!-- Product tab Area End -->

<!-- Banner Section Start -->
<div class="section pb-100px pt-100px">
    <div class="container">
        <!-- Banners Start -->
        <div class="row">
            <!-- Banner Start -->
            <div class="col-lg-6 col-12 mb-md-30px mb-lm-30px" data-aos="fade-up" data-aos-delay="200">
                <a href="https://music.163.com/#/song?id=2062727339" class="banner" target="_blank">
                    <img src="resources/images/banner/banner1.jpg" alt=""/>
                </a>
            </div>
            <!-- Banner End -->

            <!-- Banner Start -->
            <div class="col-lg-6 col-12" data-aos="fade-up" data-aos-delay="400">
                <a href="https://music.163.com/#/song?id=1459726450" class="banner" target="_blank">
                    <img src="resources/images/banner/banner2.jpg" alt=""/>
                </a>
            </div>
            <!-- Banner End -->
        </div>
        <!-- Banners End -->
    </div>
</div>
<!-- Banner Section End -->
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

<!-- Use the minified version files listed below for better performance and remove the files listed above -->
<script src="resources/js/vendor/vendor.min.js"></script>
<script src="resources/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="resources/js/main.js"></script>
</body>
</html>