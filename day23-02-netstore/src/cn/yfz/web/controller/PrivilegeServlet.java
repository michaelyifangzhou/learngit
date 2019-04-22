package cn.yfz.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yfz.Impl.BusinessServiceImpl;
import cn.yfz.domain.User;
import cn.yfz.service.BusinessService;

/**
 * Servlet implementation class PrivilegeServlet
 */
@WebServlet("/privilege/PrivilegeServlet")
public class PrivilegeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BusinessService s=new BusinessServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrivilegeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=s.login(username, password);
		if(user==null){
			response.getWriter().write("用户为空 ，2秒后跳转至登录页面");
			response.setHeader("Refresh", "2;URL="+request.getContextPath()+"/passport/adminlogin.jsp");
			return;
		}
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath()+"/manage/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
