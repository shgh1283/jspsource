<%@page import="dto.BookDTO"%>
<%@page import="dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

	request.setCharacterEncoding("utf-8");

//1. 가져올 값이(= 넘어오는 값)이 있는지 확인
//시작하는 페이지에서 form 이 존재하는 경우
//a 태그? 다음에 넘어오는지

//pk(code), 수정할 컬럼(price, description) 가져오기
		BookDTO insertDto = new BookDTO();
		insertDto.setCode(Integer.parseInt(request.getParameter("code")));
		insertDto.setPrice(Integer.parseInt(request.getParameter("price")));
		insertDto.setTitle(request.getParameter("title"));
		insertDto.setWriter(request.getParameter("writer"));
		insertDto.setDescription(request.getParameter("description"));


//2. DB 작업
	BookDAO dao = new BookDAO();
	int insertRow = dao.insert(insertDto);


// 4. 페이지 이동(client 볼 페이지)
// 0 이 리턴되면 create_pro.jsp
// 1 이 리턴되면 list_pro.jsp
	
	if( insertRow == 0){
		response.sendRedirect("create.jsp");
	}else{
		response.sendRedirect("list_pro.jsp");
	}


%>