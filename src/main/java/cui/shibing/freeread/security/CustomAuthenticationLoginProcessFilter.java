package cui.shibing.freeread.security;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

public class CustomAuthenticationLoginProcessFilter extends UsernamePasswordAuthenticationFilter{
    private static final String USER_NAME = "username";
    private static final String USER_PASS = "userpass";

    public CustomAuthenticationLoginProcessFilter(AuthenticationFailureHandler failureHandler){
        super();
        this.setAuthenticationFailureHandler(failureHandler);
    }


    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#obtainPassword(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(USER_PASS);
    }

    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#obtainUsername(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(USER_NAME);
    }
}