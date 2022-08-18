/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import dao.AccountDAO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Admin
 */
// lỗi ở đây ,check lại filter xem như nào ,project đi kiếm ở đâu đó :)))
@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/addtocart", "/buynow", "/checkout", "/changepass", "/profile"})
public class AuthenticationFilter implements Filter {

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        //Kiểm tra xem người dùng đã login chưa
        Account account = (Account) session.getAttribute("account");       
        
        if (account != null) {
            //đã login
            chain.doFilter(request, response);
        } else {
            //Kiểm tra trên cookie
            Cookie[] cookies = req.getCookies();
            String username = null;
            String password = null;

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
                if (username != null && password != null) {
                    break;
                }
            }
            if (username != null && password != null) {
                Account accountCookie = new AccountDAO().login(username, password);
                if (account != null) {//cookie hợp lệ
                    req.getSession().setAttribute("account", account);
                    chain.doFilter(request, response);
                    return;
                }
            }

            res.sendRedirect("login");
        }
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {

    }
}
