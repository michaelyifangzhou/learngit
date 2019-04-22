package cn.yfz.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;


import cn.yfz.Impl.BusinessServiceImpl;
import cn.yfz.commons.Page;
import cn.yfz.domain.Book;
import cn.yfz.domain.Category;
import cn.yfz.service.BusinessService;
import cn.yfz.utils.IdGenertor;
import cn.yfz.utils.WebUtil;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.FilenameUtils;
/**
 * Servlet implementation class ManageServlet
 */
@WebServlet("/manage/ManageServlet")
public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BusinessService s=new BusinessServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=request.getParameter("op");
		if("addCategory".equals(op)){
			addCategory(request,response);
		}else if("ShowAllCategorys".equals(op)){
			ShowAllCategorys(request,response);
		}else if("addBookUI".equals(op)){
			addBookUI(request,response);
		}else if("addBook".equals(op)){
			addBook(request,response);
		}else if("showPageBooks".equals(op)){
			showPageBooks(request,response);
		}
	}
	/**
	 * ��ѯ�鼮��ҳ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showPageBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String num=request.getParameter("num");
		Page page=s.findBookPageRecords(num);
		String url="/manage/ManageServlet?op=showPageBooks";
		page.setUrl(url);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manage/listBooks.jsp").forward(request, response);
		
	}

	/**
	 * ���ͼ���װ���ݵ�BOOK��
	 * �жϱ������Ƿ����
	 * ���������ֶ�
	 *   ��ͨ�ֶν����ݷ�װ��book������
	 *   �ϴ��ֶ�  �ϴ��ļ�
	 * @param request
	 * @param response
	 */
	private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			throw new RuntimeException("this is not a multipart/form-data form");
		}
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload sfu=new ServletFileUpload(factory);
		List<FileItem> items=new ArrayList<FileItem>();
		try {
			items=sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Book book=new Book();
		for(FileItem i:items){
			if(i.isFormField()){
				processFormField(i,book);
			}else{
				processUploadField(i,book);
			}
		}
		
		//��ͼ���������ݿ�
		s.addBook(book);
		response.sendRedirect(request.getContextPath()+"/common/message.jsp");
	}

	/**
	 * �����ļ��ϴ�
	 * @param i
	 * @param book
	 */
	private void processUploadField(FileItem i, Book book) {
		// TODO Auto-generated method stub
		/**
		 * ���·��
		 */
		String storeDirectory=getServletContext().getRealPath("/images");
		File rootDirectory=new File(storeDirectory);
		if(!rootDirectory.exists()){
			rootDirectory.mkdirs();
		}
		/**
		 * �õ��ļ���
		 */
		String filename=i.getName();
		if(filename!=null){
			filename=IdGenertor.genGUID()+"."+FilenameUtils.getExtension(filename);
			book.setFilename(filename);
		}
		/**
		 * ������Ŀ¼
		 */
		String path=getChildDirectory(storeDirectory, filename);
		book.setPath(path);
		/**
		 * �ϴ��ļ�
		 */
		try {
			i.write(new File(rootDirectory,path+"/"+filename));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��fileitem���ݷ�װ��book������
	 * @param i
	 * @param book
	 */
	private void processFormField(FileItem i, Book book) {
		// TODO Auto-generated method stub
		try {
			String fieldName=i.getFieldName();
			String fieldValue=i.getString("UTF-8");
			BeanUtils.setProperty(book, fieldName, fieldValue);
			if("categoryId".equals(fieldName)){
				Category c=s.findCategoryById(fieldValue);
				book.setCategory(c);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * ת�����ͼ��ҳ��  ��ѯ���з���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addBookUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Category> cs=s.findAllCategorys();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/addBook.jsp").forward(request, response);
	}

	private void ShowAllCategorys(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Category> cs=s.findAllCategorys();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/ListCategory.jsp").forward(request, response);
	}

	private void addCategory(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Category c=WebUtil.fillBean(request,Category.class);
		s.addCategory(c);
		try {
			response.sendRedirect(request.getContextPath()+"/common/message.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/**
	 * ��������������Ŀ¼
	 * @param realPath
	 * @return
	 */
	private String getChildDirectory(String realpath, String filename) {
		// TODO Auto-generated method stub
		int hashCode=filename.hashCode();
		int dir1=hashCode&0xf;
		int dir2=(hashCode&0xf)>>4;
		String str=dir1+File.separator+dir2;
		File file=new File(realpath,str);
		if(!file.exists()){
			file.mkdirs();
		}
		return str;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
