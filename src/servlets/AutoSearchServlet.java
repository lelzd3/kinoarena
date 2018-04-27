package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.MovieDao;

@WebServlet("/searchServlet")
public class AutoSearchServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("application/json");
         try {
                 String term = request.getParameter("term");
                 System.out.println("Data from ajax call " + term);

                 ArrayList<String> allMovies = (ArrayList<String>) MovieDao.getInstance().getMoviesContains(term);
                 System.out.println(allMovies.toString());
                 String searchList = new Gson().toJson(allMovies);
                 response.getWriter().write(searchList);
         } catch (Exception e) {
                 System.err.println(e.getMessage());
         }
	}
	
}
