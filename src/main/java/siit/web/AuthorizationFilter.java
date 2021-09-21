package siit.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "*")
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

       if (!req.getServletPath().equals("/login")
                && !req.getServletPath().equals("/createAccount") && req.getSession(true).getAttribute("logged_user") == null) {
            ((HttpServletResponse) response).sendRedirect("/login");
          }
          if (req.getServletPath().equals("/login") && req.getSession(true).getAttribute("logged_user") != null) {
            ((HttpServletResponse) response).sendRedirect("/homePage1");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
