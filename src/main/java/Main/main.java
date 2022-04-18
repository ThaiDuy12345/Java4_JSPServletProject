package Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Class.Favorite;
import Class.Share;
import Class.User;
import Class.Video;
import DAO.FavoriteDAO;
import DAO.ShareDAO;
import DAO.UserDAO;
import DAO.VideoDAO;
import SPClass.CookieUtil;
import SPClass.dateHelper;
import SPClass.jdbc;
import SPClass.sendMail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet({"/TrangChu","/TrangDayDu","/TrangYeuThich","/TrangChiTiet","/DangNhap","/QuenMatKhau","/DangKy","/ThayDoiMatKhau","/QuanLyNoiDung"})
public class main extends HttpServlet {
    public static int focusID = 0;
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("utf-8");	
    	String url = req.getRequestURI();
        if(url.contains("TrangChu")){
            TrangChuURL(url, req, resp);
        }else if(url.contains("TrangDayDu")){
            TrangDayDuURL(url, req, resp);
        }else if(url.contains("TrangYeuThich")) {
        	TrangYeuThichURL(req, resp);
        }else if(url.contains("TrangChiTiet")) {
        	TrangChiTietURL(req, resp);
        }else if(url.contains("DangNhap")){
        	DangNhapURL(req, resp);
        }else if(url.contains("QuenMatKhau")){
        	QuenMatKhauURL(req, resp);
        }else if(url.contains("DangKy")) {
        	DangKyURL(req, resp);
        }else if(url.contains("ThayDoiMatKhau")) {
        	ThayDoiMatKhauURL(req, resp);
        }else if(url.contains("QuanLyNoiDung")) {
        	QuanLyNoiDungURL(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("utf-8");
    	String url = req.getRequestURI();
    	if(req.getParameter("dangXuatButton") != null) {
    		xoaCookieTruyVan(req, resp);
    		resp.sendRedirect("TrangChu");
    	}else if(req.getParameter("dangNhapButton") != null){
    		DangNhap(req, resp);
    	}else if(req.getParameter("dangKyButton") != null){
    		DangKy(req, resp);
    	}else if(req.getParameter("khoiPhucMatKhauButton") != null ) {
    		KhoiPhucMatKhau(req, resp);
    	}else if(req.getParameter("thayDoiMatKhauButton") != null ){
    		ThayDoiMatKhau(req, resp);
    	}else if(req.getParameter("videoSearchDayDu") != null){
    		String keyWord = PhanBietButton(req);
        	if(keyWord.equals("")) {
        		TrangDayDuURL(url, req, resp);
        	}else {
        		TrangSearchDayDuURL(keyWord,req, resp);
        	}
    	}else if(req.getParameter("likeIDButton") != null) {
    		if(LayDuLieuCookie(req, resp) != null) {
    			String VideoID = "";
    			try {
    				VideoID = req.getParameterValues("likeIDButton")[0];
    				focusID = Integer.parseInt(VideoID);
    			}catch(Exception ex) {
    				ex.printStackTrace();
    			}
        		if(kiemTraLike(req, resp) == true) {
        			dislikeVideo(req, resp);
        		}else { 
        			likeVideo(req,resp);
        		}
        		TrangChiTietReloadURL(req, resp);
    		}else {
    			resp.sendRedirect("DangNhap");
    		}
    	}else if(req.getParameter("shareIDButton") != null){
    		if(LayDuLieuCookie(req, resp) != null) {
    			String VideoID = "";
    			try {
    				VideoID = req.getParameterValues("shareIDButton")[0];
    				focusID = Integer.parseInt(VideoID);
    			}catch(Exception ex) {
    				ex.printStackTrace();
    			}
        		TrangShareURL(req, resp);
    		}else {
    			resp.sendRedirect("DangNhap");
    		}
    	}else if(req.getParameter("sendEmailButton") != null){
    		String sEmail = req.getParameterValues("sEmail")[0];
    		String sPassword = req.getParameterValues("sPassword")[0];
    		String rEmail = req.getParameterValues("rEmail")[0];
    		String sTitle = req.getParameterValues("sTitle")[0];
    		String sDescription = req.getParameterValues("sDescription")[0];
    		try {
    			sendMail.send(sEmail,sPassword,rEmail,sTitle,sDescription);
    			req.setAttribute("ketQua","Thành công");
    			TangShareVideo(req, resp, rEmail);
    			TrangChiTietReloadURL(req, resp);
    		}catch(Exception ex) {
    			ex.printStackTrace();
    			if(LayDuLieuCookie(req, resp) != null) {
            		TrangShareURL(req, resp);
            		req.setAttribute("ketQua","Đã có lỗi xảy ra, xin vui lòng thử lại");
        		}else {
        			resp.sendRedirect("DangNhap");
        		}
    		}
    	}else if(req.getParameter("restartQuanLyNoiDung")!=null) {
        	QuanLyNoiDungURL(req, resp);
        }else{
    		if(url.contains("TrangChu")) {
    			resp.sendRedirect("TrangChu");
    		}else if(url.contains("TrangChiTiet")) {
        		TrangChiTietURL(req, resp);
    		}else if(url.contains("TrangYeuThich")) {
    			TrangYeuThichURL(req, resp);
    		}else if(url.contains("QuenMatKhau")) {
    			QuenMatKhauURL(req, resp);
    		}else if(url.contains("ThayDoiMatKhau")) {
    			ThayDoiMatKhauURL(req, resp);
    		}else if(url.contains("QuanLyNoiDung")) {
    			QuanLyNoiDungURL(req, resp);
    		}
    	}
    }
    public String randomPass() {
    	String random = "";
    	for(int i = 0; i < 6 ;i++) {
    		Random rand = new Random();
            int ranNum = rand.nextInt(9)+0;
    		random = random + String.valueOf(ranNum);
    	}
    	return random;
    }
    public void KhoiPhucMatKhau(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	if(!req.getParameterValues("emailInput")[0].equals("")){
    		String emailInput = req.getParameterValues("emailInput")[0];
    		if(TruyXuatTonTai(emailInput,req, resp) == true) {
    			String randomNumber = randomPass();
    			String sEmail = "thaiduynguyen.nt@gmail.com";
    			String sPassword = "sirikakire12345";
    			String rEmail = emailInput;
    			String sTitle = "Xác nhận khôi phục mật khẩu từ hệ thống OECompany";
    			String sDesciption = "Lưu ý: Không được chia sẻ cho bất kỳ ai, mật khẩu khôi phục là: "+randomNumber;
    			try{
    				sendMail.send(sEmail, sPassword, rEmail, sTitle, sDesciption);
    				User u = UserDAO.sp_SearchUserWithEmail(emailInput);
    				u.setPassword(randomNumber);
    				if(UserDAO.update(u) != 0) {
    					req.setAttribute("ketQua", "Khôi phục thành công, mật khẩu mới đã được gửi vào mail của người dùng");
    				}else {
    					req.setAttribute("ketQua", "Khôi phục thất bại, đã có lỗi xảy ra, xin vui lòng thử lại");
    				}
    			}
    			catch(Exception ex) {
    				ex.printStackTrace();
    				req.setAttribute("ketQua", "Khôi phục thất bại, đã có lỗi xảy ra, xin vui lòng thử lại");
    			}
    			QuenMatKhauURL(req, resp);
    		}else {
    			req.setAttribute("ketQua", "Khôi phục mật khẩu thất bại, email không tồn tại");
        		QuenMatKhauURL(req, resp);
    		}
    	}else {
    		req.setAttribute("ketQua", "Khôi phục mật khẩu thất bại, email không được để trống");
    		QuenMatKhauURL(req, resp);
    	}
    }
    public void likeVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	int button = focusID;
    	Video v = new Video();
    	User u = new User();
    	v.setID(button);
    	try {
    		v = VideoDAO.searchByID(v);
    		u = LayDuLieuCookie(req, resp);
    		Favorite f = new Favorite(0,u.getID(),v.getID(),dateHelper.getDate());
    		FavoriteDAO.add(f);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    } 
    public void dislikeVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	int button = focusID;
    	Video v = new Video();
    	User u = new User();
    	v.setID(button);
    	try {
    		v = VideoDAO.searchByID(v);
    		u = LayDuLieuCookie(req, resp);
    		Favorite f = FavoriteDAO.sp_SearchFavoriteByUserAndVideo(u, v);
    		FavoriteDAO.delete(f);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public boolean kiemTraLike(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	int button = focusID;
    	Video v = new Video();
    	User u = new User();
    	v.setID(button);
    	try {
    		v = VideoDAO.searchByID(v);
    		u = LayDuLieuCookie(req, resp);
    		ArrayList<Favorite> list = FavoriteDAO.searchAll();
    		for(Favorite i : list) {
    			if(i.getUserID() == u.getID() && i.getVideoID() == v.getID()) return true;
    		}
    		return false;
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		return false;
    	}
    	
    }
    public void ThayDoiMatKhau(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	String oldPasswordInput = req.getParameterValues("oldPasswordInput")[0];
    	String newPasswordInput = req.getParameterValues("newPasswordInput")[0];
    	String newPasswordConfirmInput = req.getParameterValues("newPasswordConfirmInput")[0];
    	if(!(oldPasswordInput.equals("") || newPasswordInput.equals("") || newPasswordConfirmInput.equals(""))) {
    		try {
        		User u = LayDuLieuCookie(req, resp);
        		if(u.getPassword().equals(oldPasswordInput)) {
        			if(newPasswordInput.equals(newPasswordConfirmInput)) {
        				u.setPassword(newPasswordInput);
        				UserDAO.update(u);
        				CapNhatCookie("Password",u.getPassword(),req,resp);
        				req.setAttribute("ketQua", "Thay đổi mật khẩu thành công, mật khẩu mới sẽ được áp dụng kể từ lần đăng nhập tiếp theo");
        	    		ThayDoiMatKhauURL(req, resp);
        			}else {
        				req.setAttribute("ketQua", "Thay đổi mật khẩu thất bại, mật khẩu mới xác nhận không đúng với mật khẩu mới hiện tại");
        	    		ThayDoiMatKhauURL(req, resp);
        			}
        		}else {
        			req.setAttribute("ketQua", "Thay đổi mật khẩu thất bại, mật khẩu cũ của bạn không trùng khớp");
            		ThayDoiMatKhauURL(req, resp);
        		}
        	}catch(Exception ex) {
        		ex.printStackTrace();
        	}
    	}else {
    		req.setAttribute("ketQua", "Thay đổi mật khẩu thất bại, các trường không được để trống");
    		ThayDoiMatKhauURL(req, resp);
    	}
    	
    }
    public void xoaCookieTruyVan(HttpServletRequest req, HttpServletResponse resp) {
    	CookieUtil.deleteCookie(req, resp, "Email");
    	CookieUtil.deleteCookie(req, resp, "Password");
    	CookieUtil.deleteCookie(req, resp, "Admin");
    }
    public User LayDuLieuCookie(HttpServletRequest req, HttpServletResponse resp) {
    	String email = CookieUtil.getCookie(req, "Email");
    	String password = CookieUtil.getCookie(req, "Password");
    	if(email.equals("") || email == null || password.equals("") || password == null) {
    		return null;
    	}else {
    		try {
    			return UserDAO.searchByEmailAndPassword(password, email);
    		}catch(Exception ex) {
    			ex.printStackTrace();
    			return null;
    		}
    	}
    	
    }
    public void TangShareVideo(HttpServletRequest req, HttpServletResponse resp, String rEmail) throws ServletException, IOException, SQLException {
    	Video v = new Video();
    	v.setID(focusID);
    	v = VideoDAO.searchByID(v);
    	User u = LayDuLieuCookie(req, resp);
    	Share s = new Share(0, u.getID(), v.getID(), rEmail, dateHelper.getDate());
    	ShareDAO.add(s);
    }
    public void TruyVan(String email, String password, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	try {
    		User u = UserDAO.searchByEmailAndPassword(password, email);
    		if(u == null) {
    			req.setAttribute("ketQuaDangNhap", "Email hoặc mật khẩu sai, xin vui lòng nhập lại");
    			DangNhapURL(req, resp);
    		}else {
    			LuuCookie(u, req, resp);
        		resp.sendRedirect("TrangChu");
    		}
    		
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public void CapNhatCookie(String name,String value, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	CookieUtil.updateCookie(resp,req, name, value, 5000*5000);
    }
    public void LuuCookie(User u, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	CookieUtil.addCookie(resp, "Email", u.getEmail(), 5000*5000);
    	CookieUtil.addCookie(resp, "Password", u.getPassword(), 5000*5000);
    	CookieUtil.addCookie(resp, "Admin", String.valueOf(u.isAdmin()), 5000*5000);
    }
    public void DangNhap(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String emailInput = req.getParameterValues("emailInput")[0];
    	String passwordInput = req.getParameterValues("passwordInput")[0];
    	if(!(emailInput.equals("") || passwordInput.equals(""))) {
    		TruyVan(emailInput, passwordInput, req, resp);
    	}else {
    		req.setAttribute("ketQuaDangNhap", "Đăng nhập thất bại, các trường không được để trống");
    		DangNhapURL(req, resp);
    	}
    }
    public void DangKy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String emailInput = req.getParameterValues("emailInput")[0];
    	String fullNameInput = req.getParameterValues("fullNameInput")[0];
    	String passwordInput = req.getParameterValues("passwordInput")[0];
    	String confirmPasswordInput = req.getParameterValues("confirmPasswordInput")[0];
    	if(!(emailInput.equals("") || fullNameInput.equals("") || passwordInput.equals("") || confirmPasswordInput.equals(""))) {
    		if(!(passwordInput.equals(confirmPasswordInput))){
    			req.setAttribute("ketQua", "Đăng ký thất bại, mật khẩu xác nhận không đúng với mật khẩu đã nhập");
        		DangKyURL(req, resp);
    		}else {
    			if(TruyXuatTonTai(emailInput, req, resp) == false) {
        			DangKyTaiKhoan(emailInput, fullNameInput, passwordInput, req, resp);
        			DangKyURL(req, resp);
        		}else{
        			req.setAttribute("ketQua", "Đăng ký thất bại, Email này đã được đăng ký, xin vui lòng chọn Email khác để đăng ký");
            		DangKyURL(req, resp);
        		}
    		}
    	}else {
    		req.setAttribute("ketQua", "Đăng ký thất bại, các trường không được để trống");
    		DangKyURL(req, resp);
    	}
    }
    public void DangKyTaiKhoan(String email, String fullName, String password, HttpServletRequest req, HttpServletResponse resp) {
    	try {
    		User u = new User(0, password,  email, fullName, false);
    		int a = UserDAO.add(u);
    		if(a != 0) {
    			req.setAttribute("ketQua","Đăng ký thành công, bạn đã có thể tiến hành đăng nhập");
    		}else {
    			req.setAttribute("ketQua","Đăng ký thất bại, đã xảy ra lỗi, xin vui lòng thử lại");
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public boolean TruyXuatTonTai(String emailInput, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	try{
    		ArrayList<User> list = UserDAO.searchAll();
    		for(User i : list) {
    			if(i.getEmail().equals(emailInput)) return true;
    		}
    		return false;
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		return false;
    	}
    }
    public String PhanBietButton(HttpServletRequest req)throws ServletException, IOException {
    	String searchKeyWord[] = req.getParameterValues("searchBox");
    	return searchKeyWord[0];
    }
    public void TangViewVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	String button[] = req.getParameterValues("videoClick");
    	Video object = new Video();
    	try {
    		object.setID(Integer.parseInt(button[0]));
    		int a = VideoDAO.sp_IncreaseViews(object);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public void ReloadTrangChiTiet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	try {
    		int ID = focusID;
    		Video object = new Video();
    		object.setID(ID);
    		object = VideoDAO.searchByID(object);
    		req.setAttribute("Video", object);
    		req.setAttribute("LikeNumber", VideoDAO.sp_GetVideoLike(object));
    		req.setAttribute("ShareNumber", VideoDAO.sp_GetVideoShare(object));
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public void TrangShareURL(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	if(LayDuLieuCookie(req, resp) == null) {
    		resp.sendRedirect("TrangChu");
    	}else {
    		if(LayDuLieuCookie(req, resp).isAdmin() == true) {
    			setDuLieuShare(req, resp);
    			req.getRequestDispatcher("/views/Trang Share/trangShareAdmin.jsp").forward(req, resp);
    		}else {
    			setDuLieuShare(req, resp);
    			req.getRequestDispatcher("/views/Trang Share/trangShareCustomer.jsp").forward(req, resp);
    		}
    	}
    }
    public void setDuLieuShare(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	try {
    		User u = LayDuLieuCookie(req, resp);
    		Video v = new Video();
    		v.setID(focusID);
    		v = VideoDAO.searchByID(v);
    		req.setAttribute("Video",v);
    		req.setAttribute("User", u);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public void LoadTrangChiTiet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	String button[] = req.getParameterValues("videoClick");
    	Video object = new Video();
    	try {
    		object.setID(Integer.parseInt(button[0]));
    		object = VideoDAO.searchByID(object);
    		req.setAttribute("Video", object);
    		req.setAttribute("LikeNumber", VideoDAO.sp_GetVideoLike(object));
    		req.setAttribute("ShareNumber", VideoDAO.sp_GetVideoShare(object));
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public void TrangYeuThichURL(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
    	if(LayDuLieuCookie(req, resp) == null) {
    		resp.sendRedirect("TrangChu");
    	}else {
    		if(LayDuLieuCookie(req, resp).isAdmin() == true) {
    			setDuLieuTrangYeuThich(req, resp);
        		req.getRequestDispatcher("/views/Trang Yeu Thich/trangYeuThichAdmin.jsp").forward(req, resp);
        	}else{
        		setDuLieuTrangYeuThich(req, resp);
        		req.getRequestDispatcher("/views/Trang Yeu Thich/trangYeuThichCustomer.jsp").forward(req, resp);
        	}
    	}
    }
    public void TrangChuURL(String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if(LayDuLieuCookie(req, resp) == null) {
    		set6DuLieuTrangChu(req);
    		req.getRequestDispatcher("/views/Trang Chu/index.jsp").forward(req, resp);
    	}else {
    		if(LayDuLieuCookie(req, resp).isAdmin() == true) {
        		set6DuLieuTrangChu(req);
        		req.getRequestDispatcher("/views/Trang Chu/indexAdmin.jsp").forward(req, resp);
        	}else if(LayDuLieuCookie(req, resp).isAdmin() == false) {
        		set6DuLieuTrangChu(req);
        		req.getRequestDispatcher("/views/Trang Chu/indexCustomer.jsp").forward(req, resp);
        	}
    	}
    }
    public void setDuLieuSearchTrangChu(String keyWord, HttpServletRequest req) {
    	try {
        	ArrayList<Video> list = VideoDAO.sp_SearchByTitle(keyWord);
        	req.setAttribute("Videos", list);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public void setDuLieuQuanLyNoiDung(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	if(req.getParameterValues("clickOnID_main")!=null) {
    		String button = req.getParameterValues("clickOnID_main")[0];
    		Video v = new Video();
    		try{
    			v.setID(Integer.parseInt(button));
    			v = VideoDAO.searchByID(v);
    			req.setAttribute("like", VideoDAO.sp_GetVideoLike(v));
    			req.setAttribute("share", VideoDAO.sp_GetVideoShare(v));
    			req.setAttribute("view", v.getViews());
    			ArrayList<Video> list = VideoDAO.searchAll();
        		ArrayList<User> list1 = UserDAO.searchAll();
        		req.setAttribute("list", list);
        		req.setAttribute("list1", list1);
    		}catch(Exception ex) {
    			ex.printStackTrace();
    		}
    	}else if(req.getParameterValues("clickOnID_user")!=null) {
    		String button = req.getParameterValues("clickOnID_user")[0];
    		User u = new User();
    		try{
    			u.setID(Integer.parseInt(button));
    			u = UserDAO.searchByID(u);
    			req.setAttribute("User", u);
    			ArrayList<Video> list = VideoDAO.searchAll();
        		ArrayList<User> list1 = UserDAO.searchAll();
        		req.setAttribute("list", list);
        		req.setAttribute("list1", list1);
        		req.setAttribute("number", 1);
    		}catch(Exception ex) {
    			ex.printStackTrace();
    		}
    	}else{
    		try{
        		ArrayList<Video> list = VideoDAO.searchAll();
        		ArrayList<User> list1 = UserDAO.searchAll();
        		req.setAttribute("list", list);
        		req.setAttribute("list1", list1);
        		req.setAttribute("number", 1);
        	}catch(Exception ex) {
        		ex.printStackTrace();
        	}
    	}
    }
    public void TrangSearchDayDuURL(String keyWord, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if(LayDuLieuCookie(req, resp) == null) {
    		setDuLieuSearchTrangChu(keyWord, req);
    		req.getRequestDispatcher("/views/Trang Day Du/trangDayDu.jsp").forward(req, resp);
    		
    	}else if(LayDuLieuCookie(req, resp).isAdmin() == true) {
    		setDuLieuSearchTrangChu(keyWord, req);
    		req.getRequestDispatcher("/views/Trang Day Du/trangDayDuAdmin.jsp").forward(req, resp);
    	}else {
    		setDuLieuSearchTrangChu(keyWord, req);
    		req.getRequestDispatcher("/views/Trang Day Du/trangDayDuCustomer.jsp").forward(req, resp);
    		
    	}
    }
    public void TrangDayDuURL(String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if(LayDuLieuCookie(req, resp) == null) {
    		setDuLieuTrangChu(req);
    		req.getRequestDispatcher("/views/Trang Day Du/trangDayDu.jsp").forward(req, resp);
    	}else {
    		if(LayDuLieuCookie(req, resp).isAdmin() == true) {
    			setDuLieuTrangChu(req);
        		req.getRequestDispatcher("/views/Trang Day Du/trangDayDuAdmin.jsp").forward(req, resp);
        	}else if(LayDuLieuCookie(req, resp).isAdmin() == false) {
        		setDuLieuTrangChu(req);
        		req.getRequestDispatcher("/views/Trang Day Du/trangDayDuCustomer.jsp").forward(req, resp);
        	}
    	}
    }
    public void QuanLyNoiDungURL(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	if(LayDuLieuCookie(req, resp) == null) {
    		resp.sendRedirect("TrangChu");
    	}else {
    		if(LayDuLieuCookie(req, resp).isAdmin() == true) {
    			setDuLieuQuanLyNoiDung(req, resp);
    			req.getRequestDispatcher("/views/Trang Quan Ly/trangQuanLy.jsp").forward(req, resp);
    		}else {
    			resp.sendRedirect("TrangChu");
    		}
    	}
    }
    public void TrangChiTietReloadURL(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	if(LayDuLieuCookie(req, resp) == null) {
    		ResetDuLieuInteractTrangChiTiet(req, resp);
    		ReloadTrangChiTiet(req, resp);
    		req.getRequestDispatcher("/views/Trang Chi Tiet/trangChiTiet.jsp").forward(req, resp);
    	}else if(LayDuLieuCookie(req, resp).isAdmin() == true) {
    		ResetDuLieuInteractTrangChiTiet(req, resp);
    		ReloadTrangChiTiet(req, resp);
    		req.getRequestDispatcher("/views/Trang Chi Tiet/trangChiTietAdmin.jsp").forward(req, resp);
    	}else if(LayDuLieuCookie(req, resp).isAdmin() == false){
    		ResetDuLieuInteractTrangChiTiet(req, resp);
    		ReloadTrangChiTiet(req, resp);
    		req.getRequestDispatcher("/views/Trang Chi Tiet/trangChiTietCustomer.jsp").forward(req, resp);
    	}
    	
    }
    public void TrangChiTietURL(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	if(LayDuLieuCookie(req, resp) == null) {
    		TangViewVideo(req, resp);
    		setDuLieuInteractTrangChiTiet(req, resp);
    		LoadTrangChiTiet(req, resp);
    		req.getRequestDispatcher("/views/Trang Chi Tiet/trangChiTiet.jsp").forward(req, resp);
    	}else if(LayDuLieuCookie(req, resp).isAdmin() == true) {
    		TangViewVideo(req, resp);
    		setDuLieuInteractTrangChiTiet(req, resp);
    		LoadTrangChiTiet(req, resp);
    		req.getRequestDispatcher("/views/Trang Chi Tiet/trangChiTietAdmin.jsp").forward(req, resp);
    	}else if(LayDuLieuCookie(req, resp).isAdmin() == false){
    		TangViewVideo(req, resp);
    		setDuLieuInteractTrangChiTiet(req, resp);
    		LoadTrangChiTiet(req, resp);
    		req.getRequestDispatcher("/views/Trang Chi Tiet/trangChiTietCustomer.jsp").forward(req, resp);
    	}
    }
    public void DangNhapURL(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	req.getRequestDispatcher("/views/Login/DangNhap.jsp").forward(req, resp);
    }
    public void QuenMatKhauURL(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	req.getRequestDispatcher("/views/Login/QuenMatKhau.jsp").forward(req, resp);
    }
    public void DangKyURL(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	req.getRequestDispatcher("/views/Login/DangKy.jsp").forward(req, resp);
    }
    public void ThayDoiMatKhauURL(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	if(LayDuLieuCookie(req, resp) == null) {
    		resp.sendRedirect("TrangChu");
    	}else{
    		if(LayDuLieuCookie(req, resp).isAdmin() == false) {
    			req.getRequestDispatcher("/views/Login/ThayDoiMatKhau.jsp").forward(req, resp);
    		}else {
    			resp.sendRedirect("TrangChu");
    		}
    	}
    }
    public void setDuLieuTrangChu(HttpServletRequest req)throws ServletException, IOException {
    	try {
        	ArrayList<Video> list = VideoDAO.searchAll();
        	req.setAttribute("Videos", list);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public void set6DuLieuTrangChu(HttpServletRequest req)throws ServletException, IOException {
    	try {
        	ArrayList<Video> list = VideoDAO.sp_SearchAllWithOrder();
        	ArrayList<Video> trueList = new ArrayList<Video>();
        	for(int i = 0; i < list.size() ; i++) {
        		trueList.add(list.get(i));
        		if(i == 5) {
        			break;
        		}
        	}
        	req.setAttribute("Videos", trueList);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public void setDuLieuTrangYeuThich(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	try {
    		User u = LayDuLieuCookie(req, resp);
    		ArrayList<Video> list = VideoDAO.sp_SearchAllWithFavorite(u.getID());
        	req.setAttribute("Videos", list);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public void setDuLieuInteractTrangChiTiet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	Video v = new Video();
    	String button[] = req.getParameterValues("videoClick");
    	try {
    		v.setID(Integer.parseInt(button[0]));
    		v = VideoDAO.searchByID(v);
    		User u = LayDuLieuCookie(req, resp);
    		if(u != null) {
    			ArrayList<Favorite> list = FavoriteDAO.searchAll();
        		for(Favorite f : list) {
        			if(f.getUserID() == u.getID() && f.getVideoID() == v.getID()) {
        				req.setAttribute("LikeButton", "views/material/icon/heart.png");
        				break;
        			}else {
        				req.setAttribute("LikeButton", "views/material/icon/unheart.png");
        			}
        		}
    		}else {
    			req.setAttribute("LikeButton", "views/material/icon/unheart.png");
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
    public void ResetDuLieuInteractTrangChiTiet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	try {
    		Video v = new Video();
    		v.setID(focusID);
    		v = VideoDAO.searchByID(v);
    		User u = LayDuLieuCookie(req, resp);
    		ArrayList<Favorite> list = FavoriteDAO.searchAll();
    		for(Favorite f : list) {
    			if(f.getUserID() == u.getID() && f.getVideoID() == v.getID()) {
    				req.setAttribute("LikeButton", "views/material/icon/heart.png");
    				break;
    			}else {
    				req.setAttribute("LikeButton", "views/material/icon/unheart.png");
    			}
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }
}
