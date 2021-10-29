package project.spring.nft.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	private static final Logger logger=
			LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("===== preHandle 호출");

		HttpSession session=request.getSession();
		String memberId=(String)session.getAttribute("memberId");
		
		if(memberId!=null) {
			logger.info("로그인 상태 -> controller method 실행");
			return true; //컨트롤러 메소드 실행
		} else {
			logger.info("로그아웃 상태 -> controller method 실행 안됨");
			saveDestination(request);
			response.sendRedirect("/nft/member/login");
			return false;			
		}
	} //end preHandle()
	
	private void saveDestination(HttpServletRequest request) {
		logger.info("saveDestination() 호출");
		
		//전체 요청 주소에서 쿼리 스트링을 제외한 부분
		String uri=request.getRequestURI();
		logger.info("요청 uri : "+uri);
		String contextRoot=request.getContextPath();
		logger.info("contextRoot : "+contextRoot);
		uri=uri.replace(contextRoot, "");
		
		//전체 요청 주소에서 쿼리 스트링만 추출
		String queryString=request.getQueryString();
		logger.info("쿼리 스트링 : "+queryString);
		
		String targetURL="";
		if(queryString==null) {
			targetURL=uri;
		}else {
			targetURL=uri+"?"+queryString;
		}
		request.getSession().setAttribute("targetURL", targetURL);
		
	}//end saveDestination()
}
