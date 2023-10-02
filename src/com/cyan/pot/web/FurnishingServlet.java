package com.cyan.pot.web;

import com.cyan.pot.domain.Furnishing;
import com.cyan.pot.domain.Page;
import com.cyan.pot.service.FurnishingService;
import com.cyan.pot.service.impl.FurnishingServiceImpl;
import com.cyan.pot.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

/**
 * 1.Web层的FurnishingServlet———
 * 该servlet专用于处理与摆设后台管理相关的业务，例如摆设的增删查改。
 * 也就是说，该servlet是针对于管理员的
 * 2.PS : 注意此servlet URL的配置有所不同，其目的是为了适配过滤器。
 */
@WebServlet(urlPatterns = {"/manage/furnishingServlet"})
public class FurnishingServlet extends BasicServlet {
    //Web层 调用 Service层
    private FurnishingService furnishingService = new FurnishingServiceImpl();


    /**
     (已OUT)将获取的摆设集合List放入request域对象中，并请求转发到furn_manage.jsp页面
     */
/*    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Furnishing> furnishings = furnishingService.queryFurnishings();
        //1.将集合中的元素放入到request域对象中
        req.setAttribute("furnishings", furnishings);

        //2.请求转发
        req.getRequestDispatcher("/views/manager/furn_manage.jsp").forward(req, resp);
    }*/

    /**
     * 根据获取指定的摆设，用于回显给furn_update.jsp页面
     */
    public void listSpecificOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从数据库中取出当前要修改的摆设记录
        Furnishing furnishing = furnishingService.queryFurnishingById(Integer.valueOf(req.getParameter("id")));

        //将要修改的摆设放入request域中，便于将摆设信息回显到浏览器打开的furn_update页面
        req.setAttribute("furnishing", furnishing);

        //req.setAttribute("pageNumber", req.getAttribute("pageNumber"));
        /*
            furn_manage页面中"pencil"icon 已经将pageNumber属性传了进来，
            可以利用EL表达式的内置对象param取出请求参数(因为下面使用的是请求转发，本质是一次请求)
         */

        //操作DQL语句，可以考虑使用请求转发
        req.getRequestDispatcher("/views/manager/furn_update.jsp").forward(req, resp);
    }

    /**
     * 添加摆设
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使用Apache的BeanUtils来完成数据的封装
        Furnishing furnishing = new Furnishing();

        try {
            BeanUtils.populate(furnishing, req.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        furnishingService.addFurnishing(furnishing);

        /*
            furn_manage的Add超链接将最后一页的页码传入，以实现“添加摆设”后自动跳转到最后一页
            furn_add表单中再以hidden input 的形式传到add方法。
            所以，这次取出的pageNumber，在数值上等于pageAmount.
         */
        String pageNumber = req.getParameter("pageNumber");

        /*
            此处要使用请求重定向而不是请求转发，是因为请求转发是一次请求，
            刷新页面会造成表单的重复提交；而请求重定向是两次请求。
         */
        //RequestDispatcher requestDispatcher = req.getRequestDispatcher("/manage/furnishingServlet?action=list");
        //requestDispatcher.forward(req,resp);
        resp.sendRedirect(this.getServletContext().getContextPath() + "/manage/furnishingServlet?action=paging&pageNumber=" + pageNumber + "&rows=3");
    }

    /**
     * 删除摆设
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        furnishingService.deleteFurnishing(Integer.valueOf(req.getParameter("id")));

        /*
            furn_manage页面中"x"icon 已经将pageNumber属性传了进来，
         */
        String pageNumber = req.getParameter("pageNumber");

        //涉及到数据库数据的操作，考虑使用请求重定向
        resp.sendRedirect(this.getServletContext().getContextPath() + "/manage/furnishingServlet?action=paging&pageNumber=" + pageNumber + "&rows=3");
    }

    /**
     (已OUT)更新摆设（该方法没有涉及到摆设图片的提交及修改）
     */
 /*   public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Furnishing furnishing = new Furnishing();

        try {
            BeanUtils.populate(furnishing, req.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        furnishingService.updateFurnishing(furnishing);

        //furn_update.jsp页面通过hidden input标签，将pageNumber属性的value提交过来
        String pageNumber = req.getParameter("pageNumber");
        //操作DML语句，涉及到数据库中数据的变化，考虑使用请求重定向，防止对数据的重复操作。
        resp.sendRedirect(this.getServletContext().getContextPath() + "/manage/furnishingServlet?action=paging&pageNumber=" + pageNumber + "&rows=3");
    }*/

    /**
     * 更新摆设
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //这里又用到了“取出-修改-放入-再取出”的思想！！！
        //先得到要修改的摆设的id(请求中的参数)
        Integer id = Integer.valueOf(req.getParameter("id"));
        //再通过该id得到要修改的摆设对象
        Furnishing furnishing = furnishingService.queryFurnishingById(id);

        //1.判断是否为一个文件表单
        if (JakartaServletFileUpload.isMultipartContent(req)) {
            //2.创建一个DiskFileItemFactory对象
            // (注意创建方式同Tomcat低版本不同)
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory.Builder().get();

            //3.利用得到的diskFileItemFactory对象，创建一个解析上传数据的工具对象
            JakartaServletFileUpload<DiskFileItem, DiskFileItemFactory> jakartaServletFileUpload =
                    new JakartaServletFileUpload<>(diskFileItemFactory);
            //解决乱码问题
            jakartaServletFileUpload.setHeaderCharset(StandardCharsets.UTF_8);

            //4.利用创建好的工具对象，将表单提交的数据(text or file) 封装到DiskFileItem中,
            /**返回一个由DiskFileItem组成的List集合*/
            try {
                List<DiskFileItem> diskFileItems = jakartaServletFileUpload.parseRequest(req);

                //5.遍历获取到的集合
                for (DiskFileItem diskFileItem : diskFileItems) {
                    //6.判断是否为文件类型(结果为false表示文件)
                    if (diskFileItem.isFormField()) {
                        //是input type=text类型(文本类型)

                        //判断该文本类型是什么数据(name,enterprise,price,sales,stock)
                        if ("name".equals(diskFileItem.getFieldName())) {
                            //Tomcat10----
                            //需要传入一个Charset类型
                            furnishing.setName(diskFileItem.getString(StandardCharsets.UTF_8));
                        } else if ("enterprise".equals(diskFileItem.getFieldName())) {
                            furnishing.setEnterprise(diskFileItem.getString(StandardCharsets.UTF_8));
                        } else if ("price".equals(diskFileItem.getFieldName())) {
                            furnishing.setPrice(new BigDecimal(diskFileItem.getString()));
                        } else if ("sales".equals(diskFileItem.getFieldName())) {
                            furnishing.setSales(Integer.valueOf(diskFileItem.getString()));
                        } else if ("stock".equals(diskFileItem.getFieldName())) {
                            furnishing.setStock(Integer.valueOf(diskFileItem.getString()));
                        }
                    } else {
                    //7.如果判断为是文件
                        //获取上传的文件名字
                        String uploadName = diskFileItem.getName();
                        //只有管理员上传了图片，才作处理
                        if (uploadName != null && !"".equals(uploadName)) {
                            //设置上传文件的路径
                            String filePath = "/" + WebUtils.FURN_IMG_DIRECTORY;
                            //通过ServletContext对象，得到真实的工作路径
                            String realPath = req.getServletContext().getRealPath(filePath);
                            //多级目录，分类存放图片
                            String finalPath = realPath + WebUtils.getLocalDatePath();

                            //8.由于实际工作的路径与web路径不同，所以需要创建目录
                            //创建多级目录
                            File file = new File(finalPath);
                            if (!file.exists()) {
                                file.mkdirs();
                            }

                            //9.将上传的文件拷贝到服务端的指定目录
                            /*
                                (1) 注意得到Path实例的两种方式
                                (2) 解决文件重名问题，给文件名加一个前缀，保证是唯一即可。
                             */
                            uploadName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "_" + uploadName;
                            //底层自动关闭流
                            diskFileItem.write(new File(finalPath + uploadName).toPath());

                            //修改数据库中摆设图片的路径
                            furnishing.setImgPath(WebUtils.FURN_IMG_DIRECTORY + WebUtils.getLocalDatePath() + uploadName);
                        }
                        //若发现管理员没有修改摆设的图片，就不对摆设图片做处理，默认沿用之前的图片
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("不是一个表单捏~");
        }

        furnishingService.updateFurnishing(furnishing);

        //请求转发到furn_manage页面
        //操作DML语句，涉及到数据库中数据的变化，考虑使用请求重定向，防止对数据的重复操作。
        String pageNumber = req.getParameter("pageNumber");
        resp.sendRedirect(this.getServletContext().getContextPath() + "/manage/furnishingServlet?action=paging&pageNumber=" + pageNumber + "&rows=3");
    }

    /**
     * 该方法用于支持furn_manage页面中摆设的分页展示
     * (注意区别于用户视角下的PresentToUsersServlet的pagingByName方法)
     */
    public void paging(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNumber = req.getParameter("pageNumber");
        String rows = req.getParameter("rows");

        Page<Furnishing> page = furnishingService.getPage(Integer.valueOf(pageNumber).intValue(), Integer.valueOf(rows).intValue());

        req.setAttribute("page", page);

        //请求转发到furn_manage.jsp页面
        req.getRequestDispatcher("/views/manager/furn_manage.jsp").forward(req, resp);
    }
}
