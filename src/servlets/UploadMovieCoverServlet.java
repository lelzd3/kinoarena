package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.AdminManager;
import dao.MovieDao;
import dao.UserDao;
import exceptions.InvalidDataException;
import exceptions.NotAnAdminException;
import pojos.Movie;
import pojos.User;

@WebServlet("/upload")
@MultipartConfig
public class UploadMovieCoverServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			User admin = (User) (User) request.getSession().getAttribute("admin");
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			Double duration = Double.parseDouble(request.getParameter("duration"));
			Double rating = 0.0;
			
			String file_location = "D:\\kinoarenaMovieCovers\\"+title+"-cover";
			
			Part filePart = request.getPart("file");
			File f = new File(file_location);
			InputStream is = filePart.getInputStream();
			OutputStream os = new FileOutputStream(f);
			int b = is.read();
			while(b != -1) {
				os.write(b);
				b = is.read();
			}
			
			Movie movie = new Movie(title, description, rating, duration, file_location);
			AdminManager.getInstance().addNewMovie(movie, admin);
			request.getRequestDispatcher("adminMain.jsp").forward(request, response);
			
		} catch (InvalidDataException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (NotAnAdminException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}
	
}
