package com.cyan.pot.web;

import com.cyan.pot.domain.Furnishing;
import com.cyan.pot.domain.Page;
import com.cyan.pot.service.FurnishingService;
import com.cyan.pot.service.impl.FurnishingServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 1.Web层的PresentToUsersServlet———
     (1)该servlet与FurnishingServlet相对，前者服务于管理员，该者服务于用户,
            不过两者都调用了FurnishingService。
     (2)该servlet专用于处理首页的分页展示。
 */
@WebServlet(urlPatterns={"/presentToUsersServlet"})
public class PresentToUsersServlet extends BasicServlet{
    //Web层 调用 Service层
    private FurnishingService furnishingService = new FurnishingServiceImpl();

    /**
        (已经OUT)该方法用于支持index.jsp页面中摆设的分页展示(用户视角)
     */
    /*public void paging(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNumber = req.getParameter("pageNumber");
        String rows = req.getParameter("rows");

        Page<Furnishing> page = furnishingService.getPage(Integer.valueOf(pageNumber).intValue(), Integer.valueOf(rows).intValue());

        req.setAttribute("page", page);

        //请求转发到index.jsp页面
        req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
    }*/

    /**
         该方法用于支持index.jsp页面中(搜索后)摆设的分页展示(用户视角).
         该方法最牛逼的地方在于，通过setUrl方法使用到了Page对象的Url属性，
         而正是该属性，完成了首页分页导航的功能。
     */
    public void pagingByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNumber = req.getParameter("pageNumber");
        String rows = req.getParameter("rows");
        String name = req.getParameter("name");
        /*
            1.若传入的参数携带有name但没有值，接收到的name = "";
              若传入的参数没有携带name，接收到的name = null.
            2.利用 if 条件语句，将name = ""和name = null的两种情况合并起来，达到业务的统一
              根据DAO层sql语句中模糊查询的特点，name=""，相当于%%，会返回所有的摆设。
              即pagingByName方法可以囊括paging方法的功能。
        */
        if (null == name) {
            name = "";
        }
        //之所以此处url首部没有添加"/",是因为该url是给index.jsp首页用的，而该jsp页面添加有base标签
        StringBuilder url = new StringBuilder("presentToUsersServlet?action=pagingByName");
        if (!("".equals(name))) {
            url.append("&name=" + name);
        }

        //!!!
        /*
            注意此处url的重要性，如果走得是paging方法，request域中的page对象的url就是null，
            因此，即便是向让用户在首页添加商品后回到添加时的当前页，也必须走pagingByName方法，
            也就是必须走该方法才对。否则，分页导航(首页，下页等)会报错。
         */
        Page<Furnishing> page = furnishingService.getPageByName(Integer.valueOf(pageNumber).intValue(), Integer.valueOf(rows).intValue(), name);
        page.setUrl(url.toString());

        req.setAttribute("page", page);

        //请求转发到index.jsp页面
        req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
    }
}
