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
	 * ��ʵ�ͻ�����
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
	 * ���ɶ���  ��֤�ͻ��Ƿ��¼  û�е�½���½   ��½��ȡ�����ﳵ��Ϣ ������order
	 * ȡ����������Ϣ������orderitem
	 * ���涩��  ��ת��������֧��ҳ��
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
			response.getWriter().write("�Ự��ʱ");
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
	 * �û�ע��
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
	 * �û���¼
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
	 * �û�ע��
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
	 * ��չ��ﳵ
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
	 * ɾ��һ����Ʒ����
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
	 * �޸Ĺ�����Ʒ����
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
		
		response.getWriter().write("����ɹ��������ת����ҳ");
		response.setHeader("Refresh", "2;URL="+request.getContextPath());
		
	}

	/**
	 * �鿴ĳ��ͼ����ϸ��Ϣ
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
	 * ���շ����ѯ��ҳ��Ϣ
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
	 * չ����ҳ  ��ѯ���з���  ��ѯ�����鼮
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
