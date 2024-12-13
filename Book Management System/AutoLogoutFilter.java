
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
public class AutoLogoutFilter implements Filter {
    private static final int INACTIVITY_TIMEOUT=30;
    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       System.out.println("AutoLogout Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        HttpServletRequest httpRequest= (HttpServletRequest) request;
        HttpServletResponse httpResponse= (HttpServletResponse) response;
        HttpSession session= httpRequest.getSession(false);
        
        if(session!=null)
        {       Long lastAccessTime= (Long) session.getAttribute("lastAcessTime");
            
                if(lastAccessTime== null)
                    {
                        session.setAttribute("lastAccessTime" , System.currentTimeMillis());
                    }
        else    {
                    long currentTime= System.currentTimeMillis();
                    long inactiveDuration= (currentTime- lastAccessTime) /1000;
        
                    if(inactiveDuration>INACTIVITY_TIMEOUT)
                    {
        
                            session.invalidate();
                            httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.jsp?message=SessionExpired");
                            return;
                    }
        else {  
                        session.setAttribute("lastAccessTime", currentTime);
             }
        }
        }
        
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
