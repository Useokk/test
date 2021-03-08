package board.svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardBean;

import static db.JdbcUtil.*;

public class BoardListService {
	public int getListCount() {
		int listCount = 0;
		Connection conn = null;
		try {
			conn = getConnection1();
			BoardDAO boardDAO = BoardDAO.getInstance();
			boardDAO.setConnection(conn);
			listCount = boardDAO.selectListCount();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return listCount;
	}
	public ArrayList<BoardBean> getArticleList(int page, int limit){
		ArrayList<BoardBean> articleList = null;
		Connection conn = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(conn);
		articleList = boardDAO.selectArticleList(page, limit);
		close(conn);
		return articleList;
	}
}
