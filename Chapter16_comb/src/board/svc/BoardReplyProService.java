package board.svc;

import static db.JdbcUtil.*; 
import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

public class BoardReplyProService {

	public boolean replyArticle(BoardBean article) {
		boolean isReplySuccess = false;
		Connection conn = null;
		try {
			conn = getConnection1();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(conn);
			int insertCount = boardDAO.insertReplyArticle(article);
			
			if(insertCount > 0) {
				commit(conn);
				isReplySuccess = true;
			}else {
				rollback(conn);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return isReplySuccess;
	}
}
