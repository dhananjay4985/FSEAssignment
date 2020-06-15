package user.library.librarymanagementusingbasicauth.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	public void commence(HttpServletRequest httpRequest,HttpServletResponse httpResponse,AuthenticationException authEception) throws IOException,ServletException{
		httpResponse.addHeader("WWW-Authenticate", "Basic realm=" +getRealmName());
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = httpResponse.getWriter();
        writer.println("HTTP Status 401 - " + authEception.getMessage());
	}
	
	
	public void afterPropertiesSet() throws Exception {
		setRealmName("DeveloperStack");
        super.afterPropertiesSet();
	}
}
