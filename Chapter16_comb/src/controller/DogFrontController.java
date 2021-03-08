package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dog.action.DogCartAddAction;
import dog.action.DogCartListAction;
import dog.action.DogCartQtyDownAction;
import dog.action.DogCartQtyUpAction;
import dog.action.DogCartRemoveAction;
import dog.action.DogCartSearchAction;
import dog.action.DogListAction;
import dog.action.DogRegistAction;
import dog.action.DogRegistFormAction;
import dog.action.DogViewAction;
import vo.ActionForward;

/**
 * Servlet implementation class DogFrontController
 */
@WebServlet("*.dog")
public class DogFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DogFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1.요청파악
		String requestURI = request.getRequestURI();
		
		String contextPath = request.getContextPath();
		
		String command = requestURI.substring(contextPath.length());
		Action action = null;
		ActionForward forward = null;
		if(command.equals("/dogList.dog")) {
			action = new DogListAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/dogView.dog")) {
			action = new DogViewAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartAdd.dog")) {
			action = new DogCartAddAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartList.dog")) {
			action = new DogCartListAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartSearch.dog")) {
			action = new DogCartSearchAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartRemove.dog")) {
			action = new DogCartRemoveAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartQtyUp.dog")) {
			action = new DogCartQtyUpAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/dogCartQtyDown.dog")) {
			action = new DogCartQtyDownAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/dogRegist.dog")) {
			action = new DogRegistAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/dogRegistForm.dog")) {
			action = new DogRegistFormAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	

}
