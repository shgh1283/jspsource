<%@page import="dto.ChangeDTO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 // 1
 ChangeDTO changeDto = new ChangeDTO();
changeDto.setUserid(request.getParameter("userid"));
changeDto.setChangePassword(request.getParameter("change_password"));
changeDto.setCurrentPassword(request.getParameter("current_password"));
 // 2 
MemberDAO dao = new MemberDAO();
int updateRow = dao.update(changeDto);
 // 4
if (updateRow ==1) {
	session.invalidate();	// session.removeAttribute("loginDto");
	response.sendRedirect("login.jsp");
} else
	response.sendRedirect("info.jsp");
%>