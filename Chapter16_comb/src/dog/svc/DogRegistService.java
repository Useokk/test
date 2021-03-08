package dog.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.DogDAO;
import vo.Dog;

public class DogRegistService {
	public boolean registDog(Dog dog) {
		boolean isRegistSuccess = false;
		DogDAO dogDAO = DogDAO.getInstance();
		Connection conn = null;
		try {
			conn = getConnection();
			dogDAO.setConnection(conn);
			
			int insertCount = dogDAO.insertDog(dog);
			
			if(insertCount > 0) {
				commit(conn);
				isRegistSuccess = true;
			}else {
				rollback(conn);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		
		return isRegistSuccess;
	}
}
