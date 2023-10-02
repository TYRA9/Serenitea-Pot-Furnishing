<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
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
            //1.给删除按钮绑定事件
            $("a.icon-delete").click(function () {
                //利用了JQuery的基础过滤选择器 :eg(index)
                var fName = $(this).parent().parent().find("td:eq(1)").text();

                return confirm("你确定删除[" + fName + "]吗？");
                /*
                    PS : confirm方法会弹出一个确认窗口，点击确定返回true;点击取消返回false.
                 */
            })

            //2.给前一页和后一页的分页导航绑定事件
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
                        <div class="header_account_list">
                            <a href="javascript:void(0)" class="header-action-btn search-btn"><i
                                    class="icon-magnifier"></i></a>
                            <div class="dropdown_search">
                                <form class="action-form" action="#">
                                    <input class="form-control" placeholder="👴不是OP！" type="text">
                                    <button class="submit" type="submit"><i class="icon-magnifier"></i></button>
                                </form>
                            </div>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a>Welcome Mgr : ${sessionScope.potManager.username}</a>
                        </div>
                        <!-- Single Wedge Start -->
                        <div class="header-bottom-set dropdown">
                            <a href="potManagerServlet?action=transmit&mgrId=${sessionScope.potManager.id}">Admin</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <%--
                                Δ注意此处a标签URL的形式
                                    PS : 结合base标签，这就是一个HTTP请求，只不过不是表单action。
                            --%>
                            <a href="views/manager/furn_add.jsp?pageNumber=${requestScope.page.pageAmount}">Add Furnishing</a>
                        </div>
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
                        <a href="index.jsp"><img width="280px" src="resources/images/logo/logo.png" alt="Site Logo"/></a>
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
<div class="cart-main-area pt-100px pb-100px">
    <div class="container">
        <h3 class="cart-page-title">Furnishing Admin</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">
                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>picture</th>
                                <th>name</th>
                                <th>enterprise</th>
                                <th>price</th>
                                <th>sales</th>
                                <th>stock</th>
                                <th>operate</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--使用JSTL遍历furnishings集合，循环显示摆设--%>
                            <c:forEach items="${requestScope.page.items}" var="furnishing">
                            <tr>
                                <td class="product-thumbnail">
                                    <a href="#"><img class="img-responsive ml-3" src="${furnishing.imgPath}"
                                                     alt=""/></a>
                                </td>
                                <td class="product-name"><a href="#">${furnishing.name}</a></td>
                                <td class="product-name"><a href="#">${furnishing.enterprise}</a></td>
                                <td class="product-price-cart"><span class="amount">${furnishing.price}</span></td>
                                <td class="product-quantity">
                                    ${furnishing.sales}
                                </td>
                                <td class="product-quantity">
                                    ${furnishing.stock}
                                </td>
                                <td class="product-remove">
                                    <a href="manage/furnishingServlet?action=listSpecificOne&id=${furnishing.id}&pageNumber=${requestScope.page.pageNumber}"><i class="icon-pencil"></i></a>
                                    <a class="icon-delete" href="manage/furnishingServlet?action=delete&id=${furnishing.id}&pageNumber=${requestScope.page.pageNumber}"><i class="icon-close"></i></a>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
        <div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
            <ul>
                <li><a href="manage/furnishingServlet?action=paging&pageNumber=1&rows=${requestScope.page.pageSize}">首页</a></li>
                <li><a href="manage/furnishingServlet?action=paging&pageNumber=${requestScope.page.pageNumber - 1}&rows=${requestScope.page.pageSize}" id="former-page">上页</a></li>
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
<%--                <c:set var="begin" value="1"></c:set>--%>
<%--                <c:set var="end" value="${requestScope.page.pageAmount}"></c:set>--%>
                <c:forEach begin="${begin}" end="${end}" var="i">
                    <c:if test="${requestScope.page.pageNumber == i}">
                        <li><a href="manage/furnishingServlet?action=paging&pageNumber=${i}&rows=${requestScope.page.pageSize}" class="active">${i}</a></li>
                    </c:if>
                    <c:if test="${requestScope.page.pageNumber != i}">
                        <li><a href="manage/furnishingServlet?action=paging&pageNumber=${i}&rows=${requestScope.page.pageSize}">${i}</a></li>
                    </c:if>
                </c:forEach>
                <li><a href="manage/furnishingServlet?action=paging&pageNumber=${requestScope.page.pageNumber + 1}&rows=${requestScope.page.pageSize}" id="latter-page">下页</a></li>
                <li><a href="manage/furnishingServlet?action=paging&pageNumber=${requestScope.page.pageAmount}&rows=${requestScope.page.pageSize}">末页</a></li>
                <li><a>共${requestScope.page.pageAmount}页</a></li>
                <li><a>共${requestScope.page.recordSum}记录</a></li>
            </ul>
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