package SPClass;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
@MultipartConfig
public class upload {
	public String posterUpload(HttpServletRequest req) throws ServletException, IOException{
		Part Poster = req.getPart("Poster");
		Poster.write("D:/Saving/java4/Poly/PS16703_Assignment/src/main/webapp/views/material/img/"+Poster.getSubmittedFileName());
		return "views/material/img/"+Poster.getSubmittedFileName();
	}
}
