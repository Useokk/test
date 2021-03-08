package dog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import dog.svc.DogCartRemoveService;
import vo.ActionForward;

public class DogCartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] kindArray = request.getParameterValues("remove");
		DogCartRemoveService dogCartRemoveService = new DogCartRemoveService();
		dogCartRemoveService.cartRemove(request, kindArray);
		ActionForward forward = new ActionForward("dogShopping/dogCartList.dog",true);
		return forward;
	}

}
