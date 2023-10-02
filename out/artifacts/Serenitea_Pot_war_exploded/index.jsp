<%--
    User : Cyan_RA9
    Version : 21.0
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
    转发到views/user/index.jsp
    PS: (1)JSP的请求转发标签中的URL————也是在服务器端解析的；所以可以不需要base标签。
        (2)Tomcat启动时,会首先在Web应用的web目录下寻找有没有index.html文件;
        如果没有，就找有没有index.htm文件; 如果还没有，就找index.jsp文件。
        这是由Tomcat的web.xml配置文件决定的！
--%>
<jsp:forward page="/presentToUsersServlet?action=pagingByName&pageNumber=1&rows=4"></jsp:forward>
