package net.bitacademy.java67.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bitacademy.java67.dao.BoardDao;
import net.bitacademy.java67.domain.BoardVo;

/* 실습 목표: MVC 아키텍처 적용 
 * - 화면 출력 분리한다.
 */

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(
      HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int startIndex = 0;
    int pageSize = 3;
    int pageNo = 1;
        
    if (request.getParameter("pageSize") != null) {
      pageSize = Integer.parseInt(request.getParameter("pageSize"));
    }

    if (request.getParameter("pageNo") != null) {
      pageNo = Integer.parseInt(request.getParameter("pageNo"));
      startIndex = (pageNo - 1) * pageSize;
    }
    
    String word = request.getParameter("word");
    
    String order = request.getParameter("order");
    
    ServletContext ctx = this.getServletContext();
    BoardDao boardDao = (BoardDao) ctx.getAttribute("boardDao");
    
    //JSP가 화면을 준비할 때 사용할 값을 ServletRequest에 담는다.
    List<BoardVo> list = boardDao.selectList(
                              startIndex, pageSize, word, order);
    request.setAttribute("list", list);
    request.setAttribute("pageNo", pageNo);
    request.setAttribute("pageSize", pageSize);
    
    //총 페이지 수 구하기
    int countAll = boardDao.countAll(word);
    int maxPage = countAll / pageSize;
    if (countAll % pageSize > 0) {
      maxPage++;
    }
    request.setAttribute("maxPage", maxPage);
    
    
    response.setContentType("text/html;charset=UTF-8");
    RequestDispatcher rd = request.getRequestDispatcher("/board/BoardList.jsp");
    rd.include(request, response);
    
  }

}










