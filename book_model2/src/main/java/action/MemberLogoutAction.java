package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.BookDTO;
import dto.MemberDTO;
import lombok.AllArgsConstructor;
import serivce.BookService;
import serivce.BookServiceImpl;
import serivce.MemberService;
import serivce.MemberServiceImpl;

@AllArgsConstructor
public class MemberLogoutAction implements Action {
	
	private String path;
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginDto");
		
		
		return new ActionForward(path, true);
	}
}
