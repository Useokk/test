package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import board.action.BoardDeleteProAction;
import board.action.BoardDetailAction;
import board.action.BoardListAction;
import board.action.BoardModifyFormAction;
import board.action.BoardModifyProAction;
import board.action.BoardReplyFormAction;
import board.action.BoardReplyProAction;
import board.action.BoardWriteProAction;
import vo.ActionForward;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String requestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=requestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;
		System.out.println(command);
		
		if(command.equals("/boardWriteForm.bo")) {
			forward=new ActionForward();
			forward.setPath("board/qna_board_write.jsp");
		} else if(command.contentEquals("/boardWritePro.bo")) {
			action = new BoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.contentEquals("/boardList.bo")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.contentEquals("/boardDetail.bo")) {
			action = new BoardDetailAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.contentEquals("/boardModifyForm.bo")) {
			action = new BoardModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.contentEquals("/boardModifyPro.bo")) {
			action = new BoardModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.contentEquals("/boardReplyForm.bo")) {
			action = new BoardReplyFormAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.contentEquals("/boardReplyPro.bo")) {
			action = new BoardReplyProAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.contentEquals("/boardDeleteForm.bo")) {
			String page = request.getParameter("page");
			request.setAttribute("page", page);
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			request.setAttribute("board_num", board_num);
			forward=new ActionForward();
			forward.setPath("/board/qna_board_delete.jsp");
		}
		else if(command.contentEquals("/boardDeletePro.bo")) {
			action = new BoardDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward!=null){
			
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
		
	}

}
