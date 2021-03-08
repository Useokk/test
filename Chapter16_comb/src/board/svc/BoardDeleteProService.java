package board.svc;

import dao.BoardDAO;
import static db.JdbcUtil.*;

import java.sql.Connection;

public class BoardDeleteProService {
	
	public boolean isArticleWriter(int board_num, String pass)throws Exception {
		boolean isArticleWriter = false;
		Connection conn = null;
		try {
			conn = getConnection1();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(conn);
			isArticleWriter = boardDAO.isArticleBoardWriter(board_num, pass);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return isArticleWriter;
	}
	
	public boolean removeArticle(int board_num) {
		boolean isRemoveSuccess = false;
		Connection conn = null;
		try {
			conn = getConnection();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(conn);
			int deleteCount = boardDAO.deleteArticle(board_num);
			
			if(deleteCount > 0) {
				commit(conn);
				isRemoveSuccess = true;
			} else {
				rollback(conn);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return isRemoveSuccess;
	}
}
