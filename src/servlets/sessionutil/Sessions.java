package servlets.sessionutil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Sessions {

	public static void validateSession(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.isNew()) {
			session.setAttribute("ip", request.getRemoteAddr());
			session.setMaxInactiveInterval(30*60); // 30 minutes
		} else {
			if (session.getAttribute("ip") != request.getRemoteAddr()) {
				session.invalidate();
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}
	
}
