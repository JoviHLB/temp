package jovihlb.top.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import jovihlb.top.dao.*;

@Controller
public class UserController {
	@RequestMapping("/login.do")
	public void login(HttpSession session,
			HttpServletResponse response, String username, String password,String checkCode)
			throws Exception {

//		String code = (String) session.getAttribute("code");
//
//		if (!code.equals(checkCode)) {
//			System.out.println("µÇÂ¼Ê§°Ü");
//			response.sendRedirect("failed?errCode=1");
//			return;
//		}

		UserDao dao = new UserDao();
		boolean f = dao.login(username, password);
		if (f) {
			System.out.println("µÇÂ¼³É¹¦");
			session.setAttribute("username", username);

			List<User> users = dao.findAll();

			session.setAttribute("users", users);
			response.sendRedirect("main.jsp");
		} else {
			System.out.println("µÇÂ¼Ê§°Ü");
			response.sendRedirect("failed.jsp?errCode=2");
		}
	}
	@RequestMapping("/update.do")
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		System.out.println(username);
		UserDao dao = new UserDao();
		dao.update(id, username, password, email);

		List<User> users = dao.findAll();
		request.getSession().setAttribute("users", users);
		response.sendRedirect("main.jsp");
	}

	@RequestMapping("/delete.do")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		UserDao dao = new UserDao();
		dao.delete(id);

		List<User> users = dao.findAll();
		request.getSession().setAttribute("users", users);
		try {
			response.sendRedirect("main.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/insert.do")
	public void register(HttpServletRequest request,
			HttpServletResponse response, String username, String password1,
			String password2, String email) throws IOException {

		HttpSession session = request.getSession();
		UserDao dao = new UserDao();

		if (!password1.equals(password2)) {
			response.sendRedirect("failed.jsp?msg=mimabuyizhi");
			return;
		}

		if (!dao.checkUsername(username)) {
			response.sendRedirect("failed.jsp?msg=username chongfu");
			return;
		}
		boolean f = dao.insert(username, password1, email);
		if (f) {
			session.setAttribute("username", username);
			System.out.println("µÇÂ¼³É¹¦");
			List<User> users = dao.findAll();
			session.setAttribute("users", users);
			response.sendRedirect("main.jsp");
		} else {
			System.out.println("µÇÂ¼Ê§°Ü");
			response.sendRedirect("failed.jsp");
		}
	}
	
}
