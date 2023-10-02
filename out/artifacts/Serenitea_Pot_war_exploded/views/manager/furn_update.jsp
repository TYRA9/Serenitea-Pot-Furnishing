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
    <style type="text/css">
        #furn-picture {
            border-radius: 50%;
        }
        #pic {
            position: relative;
        }
        input[type="file"] {
            position: absolute;
            left: 0;
            top: 0;
            height: 150px;
            opacity: 0;
            cursor: pointer
        }
        input[type="submit"] {
            width: 150px
        }
    </style>
    <script type="text/javascript" src="script/jquery-3.6.0.min.js"></script>
    <script>
        $(function () {

            //为修改摆设表单按钮绑定事件
            $("#update-btn").click(function () {
                //1.校验摆设名name
                var fNameVal = $("#fName").val();
                var fNamePattern = /^\S{1,15}$/;
                if (!fNamePattern.test(fNameVal)) {
                    $("span[class='add-errorMsg']").text("摆设名格式错误，请输入1~15位字符");

                    //需要返回一个false，表示不提交。
                    return false;
                }

                //2.校验摆设商家enterprise
                var fEnterpriseVal = $("#fEnterprise").val();
                var fEnterprisePattern = /^\S{1,15}$/;
                if (!fEnterprisePattern.test(fEnterpriseVal)) {
                    $("span[class='add-errorMsg']").text("摆设商家格式错误，请输入1~15位字符");

                    return false;
                }

                //3.校验摆设价格price
                var fPriceVal = $("#fPrice").val();
                var fPricePattern = /^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$/;
                if (!fPricePattern.test(fPriceVal)) {
                    $("span.add-errorMsg").text("摆设价格格式错误，请输入小数部分1~2的1~11位数字(包含小数部分11位)");

                    return false;
                }

                //4.校验摆设销量sales
                var fSalesVal = $("#fSales").val();
                var fSalesPattern = /^\d{1,10}$/;
                if (!fSalesPattern.test(fSalesVal)) {
                    $("span.add-errorMsg").text("摆设销量格式错误，请输入1~10位的数字");

                    return false;
                }

                //5.校验摆设库存stock
                var fStockVal = $("#fStock").val();
                var fStockPattern = /^\d{1,10}$/;
                if (!fStockPattern.test(fStockVal)) {
                    $("span[class='add-errorMsg']").text("摆设库存格式错误，请输入1~10位的数字");

                    return false;
                }

                //6.全部验证通过，则将表单提交给/manage/furnishingServlet
            });
        })

        //上传更新摆设图片绑定的事件
        function prev(event) {
            //获取展示图片的区域
            var img = document.getElementById("furn-picture");
            //获取文件对象
            var file = event.files[0];
            //获取文件阅读器(JS的一个类FileReader)
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function () {
                //给img的src设置图片url
                img.setAttribute("src", this.result);
            }
        }
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
                        <a href="index.jsp"><img src="resources/images/logo/logo.png" alt="Site Logo" width="360"
                                                  height="65"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->

                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">

                        <!-- Single Wedge Start -->
                        <div class="header-bottom-set dropdown">
                            <a href="manage/furnishingServlet?action=paging&pageNumber=${param.pageNumber}&rows=3">Furnishing Admin</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="orderServlet?action=showAllOrders&mgrId=${sessionScope.potManager.id}">Order Admin</a>
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
<div class="cart-main-area pt-100px pb-100px">
    <div class="container">
        <h3 class="cart-page-title" style="font-family: consolas">Furnishing Admin-->Updating Furnishing</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="manage/furnishingServlet?action=update&id=${requestScope.furnishing.id}&pageNumber=${param.pageNumber}" method="post" enctype="multipart/form-data">
                    <%--
                        该表单是post请求，所以此处使用hidden的input标签
                        PS: 上传更新摆设图片，不能再使用BeanUtils来封装数据，因此去除了hidden属性的input标签
                     --%>
                    <%--<input type="hidden" name="action" value="update"/>
                    <input type="hidden" name="id" value="${requestScope.furnishing.id}"/>
                    <input type="hidden" name="pageNumber" value="${param.pageNumber}"/>--%>
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
                            <tr>
                                <td class="product-thumbnail">
                                    <div id="pic">
                                        <img class="img-responsive ml-3" id="furn-picture"
                                                         src="${requestScope.furnishing.imgPath}"
                                                         alt="" style="width: 90%"/>
                                        <input type="file" name="imgPath" id="" value="${requestScope.furnishing.imgPath}" onchange="prev(this)"/>
                                    </div>
                                </td>
                                <td class="product-name"><input name="name" style="width: 75%" type="text" id="fName"
                                                                value="${requestScope.furnishing.name}"/></td>
                                <td class="product-name"><input name="enterprise" style="width: 85%" type="text" id="fEnterprise"
                                                                value="${requestScope.furnishing.enterprise}"/></td>
                                <td class="product-price-cart"><input name="price" style="width: 90%" type="text" id="fPrice"
                                                                      value="${requestScope.furnishing.price}"/></td>
                                <td class="product-quantity">
                                    <input name="sales" style="width: 90%" type="text" id="fSales"
                                           value="${requestScope.furnishing.sales}"/>
                                </td>
                                <td class="product-quantity">
                                    <input name="stock" style="width: 90%" type="text" id="fStock"
                                           value="${requestScope.furnishing.stock}"/>
                                </td>
                                <td>
                                    <input type="submit" id="update-btn"
                                           style="width: 90%;background-color: silver;border: silver;border-radius: 20%;"
                                           value="Updating Furn"/>
                                </td>
                            </tr>
                            <tr>
                                <span class="add-errorMsg"
                                      style="float: right; font-weight: bold; font-size: 20pt; margin-left: 10px;"></span>
                            </tr>
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