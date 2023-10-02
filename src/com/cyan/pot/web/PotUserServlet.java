package com.cyan.pot.web;

import com.cyan.pot.domain.PotUser;
import com.cyan.pot.service.PotUserService;
import com.cyan.pot.service.impl.PotUserServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
    Web层的PotUserServlet，该servlet专用于处理与 “用户” 相关的请求，包括用户注册，用户登录等。
 */
@WebServlet(urlPatterns = {"/potUserServlet"})
public class PotUserServlet extends BasicServlet {
    //Web层 调用 Service层
    private PotUserService potUserService = new PotUserServiceImpl();

    //用于完成用户注册的方法
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取注册表单的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String registerCode = req.getParameter("registerCode");
            //获取session域中保存的正确的验证码
        String sessionKey = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
            //立刻删除session域中保存的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
            //进行验证码的校验
        if (sessionKey != null && sessionKey.equalsIgnoreCase(registerCode)) {
            //2.验证表单提交的用户名是否可用
            if (!potUserService.isExistsByUsername(username)) {
                PotUser potUser = new PotUser(null, username, password, email);
                //3.若用户名可用，验证是否注册成功
                if (potUserService.registerPotUser(potUser)) {
                    //3.1 注册成功，转发到register.ok页面
                    RequestDispatcher requestDispatcher =
                            req.getRequestDispatcher("/views/user/register_ok.jsp");
                    requestDispatcher.forward(req, resp);
                } else {
                    //3.2 注册失败，转发到register.fail页面
                    RequestDispatcher requestDispatcher =
                            req.getRequestDispatcher("/views/user/register_fail.jsp");
                    requestDispatcher.forward(req, resp);
                }
            } else {
                //4.若用户名不可用，转发到注册页面
                RequestDispatcher requestDispatcher =
                        req.getRequestDispatcher("/views/user/login.jsp");
                requestDispatcher.forward(req, resp);
            }
        } else {
            req.setAttribute("registerError", "验证码输入有误！");
            req.setAttribute("skipMsg", "验证码错误，请重新返回注册页面.");
            //将用户名和注册邮箱回显到注册表单
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
        }
    }

    //用于完成用户登录的方法
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取登录表单的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PotUser user = new PotUser(null, username, password, null);

        String loginCode = req.getParameter("loginCode");
            //获取session域中保存的正确的验证码
        String sessionKey = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
            //立刻删除session中保存的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
            //进行验证码的校验
        if (sessionKey != null && sessionKey.equalsIgnoreCase(loginCode)) {
            //2.根据“以表单数据构建的PotUser对象”，来验证用户是否登录成功
            PotUser potUser = potUserService.login(user);
            if (user != null) {
            /*
                若登录成功，将从数据库找到的PotUser对象放入到session域中(该potUserid不为NULL)，
                供login_ok.jsp和index.jsp页面使用.
             */
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("potUser", potUser);

                RequestDispatcher requestDispatcher =
                        req.getRequestDispatcher("/views/user/login_ok.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                //登录失败
                //说明该用户并不存在于pot_user表中
                //将登录错误的信息回显给浏览器(借助EL表达式)
                req.setAttribute("hint", "用户名或密码错误！");
                //将用户名回显到登录表单
                req.setAttribute("username", username);

                RequestDispatcher requestDispatcher =
                        req.getRequestDispatcher("/views/user/login.jsp");
                requestDispatcher.forward(req, resp);
            }
        } else {
            //若验证码输入错误，则重新跳转到登录页面，将验证码错误的信息回显到页面
            req.setAttribute("loginError", "验证码输入有误！");
            //将用户名回显到登录表单
            req.setAttribute("username", username);
            req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);
        }
    }

    //用于完成用户退出的方法
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        //使当前的session会话立刻无效(直接将整个session🐔了)
        httpSession.invalidate();

        //使用请求重定向，防止重复注销 (Δ href超链接一般是get请求方式提交)
            //此处也可以不拼接，默认访问index.jsp(入口)
        resp.sendRedirect(this.getServletContext().getContextPath() + "/index.jsp");
    }

    //PS : 单独定义一个用于验证用户名是否可用的方法，用于Ajax发送异步请求
    public void verifyUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        boolean verifiedResult = potUserService.isExistsByUsername(username);

        Gson gson = new Gson();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("verifiedResult", verifiedResult);
        String resultJson = gson.toJson(resultMap);

        //由于是Ajax请求访问该方法，因此此处并不会直接回显给浏览器。
        resp.getWriter().write(resultJson);
    }
}
