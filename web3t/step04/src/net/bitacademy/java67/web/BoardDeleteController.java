package net.bitacademy.java67.web;

import javax.servlet.http.HttpServletRequest;

import net.bitacademy.java67.dao.BoardDao;

/* 실습 목표: 페이지 컨트롤러 적용
 */
public class BoardDeleteController implements Controller {
  //의존 객체 주입을 위한 변수와 셋터 선언
  BoardDao boardDao;
  
  public void setBoardDao(BoardDao boardDao) {
    this.boardDao = boardDao;
  }
  
  @Override
  public String execute(HttpServletRequest request) throws Exception {
    boardDao.delete(Integer.parseInt(request.getParameter("no")));
    
    return "redirect:list.do";
  }
}













