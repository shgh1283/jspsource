package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.MemberDTO;
import service.MemberService;
import service.MemberServiceImpl;

public class LoginAction implements Action {
	
	private String path;

	
	
	public LoginAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		
		// service 메소드 호출 후 결과(CRUD) 받기
		MemberDTO dto = new MemberDTO();
		dto.setUserid(userid);
		dto.setPassword(password);
		
		MemberService service = new MemberServiceImpl();
		MemberDTO loginDto = service.read(dto);
		
		
		if (loginDto!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginDto", loginDto);
			
		}
		// sendRedirect 방식으로 path 보여줘
		return new ActionForward(path, true);
	}

}
