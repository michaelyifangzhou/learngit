package cn.yfz.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.yfz.Impl.BusinessServiceImpl;
import cn.yfz.domain.Function;
import cn.yfz.domain.Role;
import cn.yfz.domain.User;
import cn.yfz.service.BusinessService;

/**
 * Servlet Filter implementation class PrivilegeFilter
 */
@WebFilter(filterName="PrivilegeFilter",urlPatterns="/*")
public class PrivilegeFilter implements Filter {
	private BusinessService s=new BusinessServiceImpl();
    /**
     * Default constructor. 
     */
    public PrivilegeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest request;
		HttpServletResponse response;
		try {
			request=(HttpServletRequest) req;
			response=(HttpServletResponse) resp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException("non http response or request");
		}
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		
		//判断用户是否登录
		//美欧登陆转向灯了页面
		if(user==null){
			request.getRequestDispatcher("/passport/adminlogin.jsp").forward(request, response);
			return;
		}
		//登录
		    //把当前用户能有的功能存起来
		Set<Function> funs=new HashSet<Function>();
			//查询角色
		List<Role> roles=s.findRoleByUser(user);
			//便利角色  查询工农能
		for(Role r:roles){
			List<Function> functions=s.findFunctionByRole(r);
			funs.addAll(functions);
		}
			//得到访问的URI地址
		String uri=request.getRequestURI();
		String queryString=request.getQueryString();
		if(queryString!=null){
			uri=uri+"?"+queryString;
		}
		uri.replace(request.getContextPath(), "");
		boolean hasPermission=false;
			//对比是否在权限范围之内
		for(Function f:funs){
			if(uri.equals(f.getUri())){
				hasPermission=true;
				break;
			}
		}
		if(!hasPermission){
			response.getWriter().write("您没有权限");
		}
		      //不在 提还没权限
		//放行
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
