package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.action.MemberJoinAction;
import member.action.MemberListAction;
import vo.ActionForward;

/**
 * Servlet implementation class MemberFrontController
 */
@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		// 1.요청파악
		String requestURI = request.getRequestURI();

		String contextPath = request.getContextPath();

		String command = requestURI.substring(contextPath.length());
		Action action = null;
		ActionForward forward = null;
		System.out.println(command);
		if (command.equals("/memberJoin.me")) {
			action = new MemberJoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberLogin.me")) {
			forward = new ActionForward();
			forward.setPath("/member/loginForm.jsp");
		} else if (command.equals("/memberJoin.me")){
			action = new MemberJoinAction();
			try {
				forward = action.execute(request, response);				
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberListAction.me")) {
			action = new MemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(forward);
		if (forward != null) {

			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

}
