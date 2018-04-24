package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/getPic")
public class PictureServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");
		String file_location = "D:\\kinoarenaMovieCovers\\"+title+"-cover";
		
		File f = new File(file_location);
		InputStream is = new FileInputStream(f);
		OutputStream os = response.getOutputStream();
		int b = is.read();
		while(b != -1) {
			os.write(b);
			b = is.read();
		}
	}

}
