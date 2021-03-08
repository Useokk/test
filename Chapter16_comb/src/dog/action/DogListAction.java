package dog.action;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;
import vo.Dog;

import dog.svc.DogListService;

public class DogListAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<String> todayImageList = new ArrayList<String>();
		Cookie[] cookieArray = request.getCookies();

		if (cookieArray != null) {
			for (int i = 0; i < cookieArray.length; i++) {
				if (cookieArray[i].getName().startsWith("today")) {
					todayImageList.add(cookieArray[i].getValue());
				}
			}
		}
		DogListService dogListService = new DogListService();
		ArrayList<Dog> dogList = dogListService.getDogList();
		System.out.println(dogList);
		request.setAttribute("dogList", dogList);
		request.setAttribute("todayImageList", todayImageList);
		ActionForward forward = new ActionForward("dogShopping/dogList.jsp", false);
		return forward;
	}
}
