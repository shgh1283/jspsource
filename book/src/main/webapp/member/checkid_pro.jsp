<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String userid = request.getParameter("userid");
	System.out.println(userid);
	
	MemberDAO dao = new MemberDAO();
	boolean dupId = dao.dupId(userid);
	
	if(dupId) {
		out.println("true");
	}else {
		out.println("false");
		
	}
%>