package dao;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.BoardBean;

public class BoardDAO {
	DataSource ds;
	Connection conn;
	private static BoardDAO boardDAO;

	public static BoardDAO getInstance() {
		if(boardDAO==null)
		{
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}
	
	private BoardDAO() {}

	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	//글등록
	public int insertArticle(BoardBean article) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		String sql="";
		int insertCount=0;
		
		try {
			pstmt= conn.prepareStatement("select max(board_num) from board");
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}else {
				num = 1;
			}
			
			sql = "insert into board (board_num, board_name, board_pass, board_subject,";
			sql += "board_content, board_file, board_re_ref, board_re_lev, board_re_seq, board_readcount, board_date) values(?,?,?,?,?,?,?,0,0,0,now())";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getBoard_name());
			pstmt.setString(3, article.getBoard_pass());
			pstmt.setString(4, article.getBoard_subject());
			pstmt.setString(5, article.getBoard_content());
			pstmt.setString(6, article.getBoard_file());
			pstmt.setInt(7, num);
			

			insertCount = pstmt.executeUpdate();
		}catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("boardInsert 에러 : " + ex);
		}finally {
			close(rs);
			close(pstmt);
		}
		return insertCount;
		
	}

	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("getListCount 에러 : " + e);
			e.printStackTrace();
		} finally{
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	public ArrayList<BoardBean> selectArticleList (int page, int limit){
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql = "select * from board order by board_re_ref desc,board_re_seq asc limit ?,?";
		int startrow = (page-1) * limit;
		try {
			pstmt = conn.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean board = new BoardBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getDate("board_date"));
				articleList.add(board);
				
			}
		}catch(Exception e) {
			System.out.println("getBoardList 에러 : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}

	public int updateReadCount(int board_num) {
		int updateCount=0;
		PreparedStatement pstmt = null;
		String sql = "update board set board readcount = board_readcount+1 where board_num=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			updateCount = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return updateCount;
	}

	public BoardBean selectArticle(int board_num) {
		BoardBean article = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where board_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new BoardBean();
				article.setBoard_num(rs.getInt("board_num"));
				article.setBoard_name(rs.getString("board_name"));
				article.setBoard_subject(rs.getString("board_subject"));
				article.setBoard_content(rs.getString("board_content"));
				article.setBoard_file(rs.getString("board_file"));
				article.setBoard_re_ref(rs.getInt("board_re_ref"));
				article.setBoard_re_lev(rs.getInt("board_re_lev"));
				article.setBoard_re_seq(rs.getInt("board_re_seq"));
				article.setBoard_readcount(rs.getInt("board_readcount"));
				article.setBoard_date(rs.getDate("board_date"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return article;
	}

	public boolean isArticleBoardWriter(int board_num, String pass) {
		boolean isWriter = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where board_num = ? and board_pass = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isWriter = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return isWriter;
	}

	public int updateArticle(BoardBean article) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update board set board_subject=? , board_content=? where board_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getBoard_subject());
			pstmt.setString(2, article.getBoard_content());
			pstmt.setInt(3, article.getBoard_num());
			updateCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return updateCount;
	}

	public int insertReplyArticle(BoardBean article) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//관련글 seq업데이터
			String sql = "update board set board_re_seq = board_re_seq + 1 where board_re_ref = ? and board_re_seq > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article.getBoard_re_ref());
			pstmt.setInt(2, article.getBoard_re_seq());
			
			pstmt.executeUpdate();
			
			//입력할 글 번호 생성
			int num = 1;
			pstmt = conn.prepareStatement("select max(board_num) from board");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}
			
			sql = "insert into board value(?,?,?,?,?,'',?,?,?,0,now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getBoard_name());
			pstmt.setString(3, article.getBoard_pass());
			pstmt.setString(4, article.getBoard_subject());
			pstmt.setString(5, article.getBoard_content());
			pstmt.setInt(6, article.getBoard_re_ref());
			pstmt.setInt(7, article.getBoard_re_lev()+1);
			pstmt.setInt(8, article.getBoard_re_seq()+1);
			insertCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		}
		return insertCount;
	}

	public int deleteArticle(int board_num) {
		PreparedStatement pstmt = null;
		String sql = "delete from board where board_num = ?";
		int deleteCount= 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			deleteCount = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}

}
