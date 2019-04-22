package cn.yfz.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.impl.orbutil.closure.Constant;

import cn.yfz.Impl.BusinessServiceImpl;
import cn.yfz.commons.Page;
import cn.yfz.constant.Constants;
import cn.yfz.domain.Book;
import cn.yfz.domain.Category;
import cn.yfz.domain.Customer;
import cn.yfz.domain.Order;
import cn.yfz.domain.OrderItem;
import cn.yfz.service.BusinessService;
import cn.yfz.utils.IdGenertor;
import cn.yfz.utils.WebUtil;
import cn.yfz.web.beans.Cart;
import cn.yfz.web.beans.CartItem;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/client/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BusinessService s=new BusinessServiceImpl();    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=request.getParameter("op");
		if("showIndex".equals(op)){
			showIndex(request,response);
		}else if("showCategoryBooks".equals(op)){
			showCategoryBooks(request,response);
		}else if("showBookDetail".equals(op)){
			showBookDetail(request,response);
		}else if("buyBook".equals(op)){
			buyBook(request,response);
		}else if("changeNum".equals(op)){
			changeNum(request,response);
		}else if("delOneItem".equals(op)){
			delOneItem(request,response);
		}else if("delAllItems".equals(op)){
			delAllItems(request,response);
		}else if("registCustomer".equals(op)){
			registCustomer(request,response);
		}else if("loginCustomer".equals(op)){
			loginCustomer(request,response);
		}else if("logoutCustomer".equals(op)){
			logoutCustomer(request,response);
		}else if("genOrder".equals(op)){
			genOrder(request,response);
		}else if("showCustomerOrders".equals(op)){
			showCustomerOrders(request,response);
		}
	}
	/**
	 * 现实客户订单
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showCustomerOrders(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		Customer c=(Customer) session.getAttribute(Constants.LOGIN_FLAG);
		if(c==null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		List<Order> os=s.findCustomerOrders(c.getId());
		request.setAttribute("os", os);
		request.getRequestDispatcher("/showCustomerOrders.jsp").forward(request, response);
	}

	/**
	 * 生成订单  验证客户是否登录  没有登陆请登陆   登陆后取出购物车信息 ——》order
	 * 取出订单项信息——》orderitem
	 * 保存订单  跳转到总在线支付页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void genOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		Customer c=(Customer) session.getAttribute(Constants.LOGIN_FLAG);
		if(c==null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		Cart cart=(Cart) session.getAttribute(Constants.HttpSession_cart);
		if(cart==null){
			response.getWriter().write("会话超时");
			return;
		}
		Order order=new Order();
		order.setOrdernum(IdGenertor.genOrdernum());
		order.setQuantity(cart.getTotalQuantity());
		order.setMoney(cart.getTotalMoney());
		order.setCustomer(c);
		List<OrderItem> oitems=new ArrayList<OrderItem>();
		for(Map.Entry<String, CartItem> me:cart.getItems().entrySet()){
			CartItem citem=me.getValue();
			OrderItem oitem=new OrderItem();
			//oitem.setOrder(order);
			oitem.setId(IdGenertor.genGUID());
			oitem.setBook(citem.getBook());
			oitem.setPrice(citem.getMoney());
			oitem.setQuantity(citem.getQuantity());
			
			oitems.add(oitem);
		}
		order.setItems(oitems);
		s.getOrder(order);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/pay.jap").forward(request, response);
	}

	/**
	 * 用户注销
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void logoutCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.getSession().removeAttribute(Constants.LOGIN_FLAG);
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void loginCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Customer c=s.customerLogin(username, password);
		request.getSession().setAttribute(Constants.LOGIN_FLAG, c);
		response.sendRedirect(request.getContextPath());
		
	}

	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void registCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Customer c=WebUtil.fillBean(request, Customer.class);
		s.addCustomer(c);
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delAllItems(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.getSession().removeAttribute(Constants.HttpSession_cart);
		response.sendRedirect(request.getContextPath()+"/showCart.jsp");
	}

	/**
	 * 删除一项商品购买
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void delOneItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String bookId=request.getParameter("bookId");
		Cart cart=(Cart) request.getSession().getAttribute(Constants.HttpSession_cart);
		cart.getItems().remove(bookId);
		response.sendRedirect(request.getContextPath()+"/showCart.jsp");
	}
	/**
	 * 修改购买商品数量
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void changeNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String num=request.getParameter("num");
		String bookId=request.getParameter("bookId");
		Cart cart=(Cart) request.getSession().getAttribute(Constants.HttpSession_cart);
		CartItem item=cart.getItems().get(bookId);
		item.setQuantity(Integer.parseInt(num));
		response.sendRedirect(request.getContextPath()+"/showCart.jsp");
	}

	private void buyBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String bookId=request.getParameter("bookId");
		Book book=s.findBookById(bookId);
		HttpSession session=request.getSession();
		Cart cart=(Cart) session.getAttribute(Constants.HttpSession_cart);
		if(cart==null){
			cart=new Cart();
			session.setAttribute(Constants.HttpSession_cart, cart);
			
		}
		cart.addBook(book);
		
		response.getWriter().write("购买成功，两秒后转回主页");
		response.setHeader("Refresh", "2;URL="+request.getContextPath());
		
	}

	/**
	 * 查看某本图书详细信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showBookDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bookId=request.getParameter("bookId");
		Book book=s.findBookById(bookId);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/bookDetail.jsp").forward(request, response);
	}

	/**
	 * 按照分类查询分页信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showCategoryBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Category> cs=s.findAllCategorys();
		request.setAttribute("cs", cs);
		String num=request.getParameter("num");
		String categoryId=request.getParameter("categoryId");
		Page page=s.findBookPageRecords(num,categoryId);
		page.setUrl("/client/ClientServlet?op=showCategoryBooks&categoryId="+categoryId);
		request.setAttribute("page",page);
		request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
	}

	/**
	 * 展现主页  查询所有分类  查询所有书籍
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Category> cs=s.findAllCategorys();
		request.setAttribute("cs", cs);
		String num=request.getParameter("num");
		Page page=s.findBookPageRecords(num);
		page.setUrl("/client/ClientServlet?op=showIndex");
		request.setAttribute("page",page);
		request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
