package dog.svc;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static db.JdbcUtil.*;
import dao.DogDAO;
import vo.Cart;
import vo.Dog;

public class DogCartAddService {
	public Dog getCartDog(int id) {
		Connection conn = null;
		Dog dog = null;
		try {
			conn = getConnection();
			DogDAO dogDAO = DogDAO.getInstance();
			dogDAO.setConnection(conn);
			dog = dogDAO.selectDog(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return dog;
	}

	public void addCart(HttpServletRequest request, Dog cartDog) {
		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("cartList");
		
		if(cartList == null) {
			cartList = new ArrayList<Cart>();
			session.setAttribute("cartList",cartList);
		}
		
		boolean isNewCart = true;
		for(int i = 0; i < cartList.size(); i++) {
			if(cartDog.getKind().equals(cartList.get(i).getKind())) {
				isNewCart = false;
				cartList.get(i).setQty(cartList.get(i).getQty()+1);
				break;
			}
		}
		
		if(isNewCart) {
			Cart cart = new Cart();
			cart.setImage(cartDog.getImage());
			cart.setKind(cartDog.getKind());
			cart.setPrice(cartDog.getPrice());
			cart.setQty(1);
			cartList.add(cart);
		}
		
	}
	
}
