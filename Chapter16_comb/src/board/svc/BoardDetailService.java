package board.svc;

import static db.JdbcUtil.*;
import java.sql.Connection;

import dao.BoardDAO;
import vo.BoardBean;

public class BoardDetailService {

	public BoardBean getArticle(int board_num) {
		BoardBean article = null;
		Connection conn = null;
		try {
			conn = getConnection1();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(conn);
			
			int updateCount = boardDAO.updateReadCount(board_num);
			if(updateCount > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
			article = boardDAO.selectArticle(board_num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return article;
	}
	
}
