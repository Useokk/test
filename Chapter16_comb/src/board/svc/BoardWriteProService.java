package board.svc;

import java.sql.Connection;
import dao.BoardDAO;
import vo.BoardBean;
import static db.JdbcUtil.*;



public class BoardWriteProService {

	public Boolean registArticle(BoardBean boardBean) {
		boolean isWriteSuccess = false;
		Connection conn = null;
		try {
			conn = getConnection1();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(conn);
			int insertCount = boardDAO.insertArticle(boardBean);
			if(insertCount > 0) {
				commit(conn);
				isWriteSuccess = true;
			} else {
				rollback(conn);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return isWriteSuccess;
	}
	
}
