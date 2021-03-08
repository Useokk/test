package dog.svc;

import java.sql.Connection;

import static db.JdbcUtil.*;
import dao.DogDAO;
import vo.Dog;

public class DogViewService {
	public Dog getDogView(int id) {
		Dog dog = null;
		Connection conn = null;
		try {
			conn = getConnection();
			DogDAO dogDAO = DogDAO.getInstance();
			dogDAO.setConnection(conn);
			int updateCount = dogDAO.updateReadCount(id);
			if(updateCount>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			dog = dogDAO.selectDog(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return dog;
	}
}
