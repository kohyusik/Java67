package net.bitacademy.java67.step07;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 실습 목표: 한글 처리 필터 적용
 * - request.setCharacterEncoding() 제거.
 * 
 */

@WebServlet("/step07/change")
public class BoardChangeServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(
      HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    
    // ServletContext : 웹 애플리케이션 정보를 다루는 도구
    ServletContext ctx = this.getServletContext();
    
    BoardVo board = new BoardVo();
    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setTitle(request.getParameter("title"));
    board.setContent(request.getParameter("content"));
    
    BoardDao boardDao = new BoardDao();
    DBConnectionPool dbPool = new DBConnectionPool(
        ctx.getInitParameter("driver"),
        ctx.getInitParameter("url"),
        ctx.getInitParameter("user"),
        ctx.getInitParameter("password"));
    boardDao.setDBConnectionPool(dbPool);
    boardDao.update(board);
    
    response.sendRedirect("list");
    
  }
}













