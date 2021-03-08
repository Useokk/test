package dog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dog.svc.DogCartQtyUpService;
import vo.ActionForward;

public class DogCartQtyUpAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String kind = request.getParameter("kind");
		DogCartQtyUpService dogCartQtyUpService = new DogCartQtyUpService();
		dogCartQtyUpService.upCartQty(kind, request);
		ActionForward forward = new ActionForward("dogShopping/dogCartList.dog", true);
		return forward;
	}

}
