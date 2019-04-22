package cn.yfz.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yfz.Impl.BusinessServiceImpl;
import cn.yfz.domain.Order;
import cn.yfz.service.BusinessService;
import cn.yfz.utils.PaymentUtil;

/**
 * Servlet implementation class ResponseServlet
 */
@WebServlet("/servlet/ResponseServlet")
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BusinessService s=new BusinessServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String p1_MerId=request.getParameter("p1_MerId");
		String r0_Cmd=request.getParameter("r0_Cmd");
		String r1_Code=request.getParameter("r1_Code");//1����֧���ɹ�
		String r2_TrxId=request.getParameter("r2_TrxId");
		String r3_Amt=request.getParameter("r3_Amt");
		String r4_Cur=request.getParameter("r4_Cur");
		String r5_Pid=request.getParameter("r5_Pid");
		String r6_Order=request.getParameter("r6_Order");
		String r7_Uid=request.getParameter("r7_Uid");
		String r8_MP=request.getParameter("r8_MP");
		String r9_BType=request.getParameter("r9_BType");
		String hmac=request.getParameter("hmac");
		//��֤������ȷ��
		boolean b=PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, 
				"69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4P");
		if(b){
			//��Ϣ��ȷ
			if("1".equals(r1_Code)){
				//���׳ɹ�
				if("2".equals(r9_BType)){
					response.getWriter().write("SUCCESS");
				}
				//���Ķ���״̬
				Order o=s.findOrderByNum(r6_Order);
				o.setStatus(1);
				s.changeOrderStatus(o);
				response.getWriter().write("֧���ɹ�  succeed in paying");
			}else{
				response.getWriter().write("�����¸���");
			}
		}else{
			response.getWriter().write("������Ϣ����");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
