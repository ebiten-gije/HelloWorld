package himedia;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Logger;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger("HelloServlet");
	
	private String appName;
	private String dbUser;
	private String dbPass;
	
	//	서블릿이 처음 호출될 때,
	@Override
	public void init(ServletConfig config) throws ServletException {
		logger.info("[LifeCycle]: init");
		
		super.init(config);
		
		//	Context parameter 받아오기
		ServletContext serCon = getServletContext();
		
		appName = serCon.getInitParameter("appName");
		dbUser = serCon.getInitParameter("dbUser");
		dbPass = serCon.getInitParameter("dbPass");
		
		logger.info("DBUser: " + dbUser);
		logger.info("DBPass: " + dbPass);
		

	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("[LifeCycle]: service");
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		logger.info("[LifeCycle]: doGet");
		resp.setContentType("text/html; charset = UTF-8");
		//	사용자로부터 name 파라미터 전달 받아서 출력
		//	파라미터로 데이터가 전달되는 GET 방식의 요청을 처리하는 메서드
		String name = req.getParameter("name");
		
		if (name == null) {
			name = "적당한 이름";
		}
		
		//	Servlet Parameter 받아오시
		ServletConfig config = getServletConfig(); 
		String servletName = config.getInitParameter("servletName");
		String description = config.getInitParameter("description");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<h1>App Name: " + appName + "</h1>");
		
		
		
		out.println("<h2>" + servletName + "</h2>");
		out.println("<p>" + description + "<p>");
		out.println("<p>안녕하세요, " + name + "씨</p>");
//		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		logger.info("[LifeCycle]: doPost");
		//	폼으로부터 넘어온 error 체크박스에 따라 예외 발생
		//		web.xml error-page 테스트 위함
//		if(req.getParameter("error").equals("on")) {		//null 발생 가능성, .equals()에서 오류 나버려
		if("on".equals(req.getParameter("error"))) {
			throw new ServletException("에러 페이지 테스트");
		}
		
		//	클라이언트의 form 으로부터 전달받은 데이터를 목록 출력
		resp.setContentType("text/html; charset = UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.println("<h1>폼 데이터 처리</h1>");
		
		out.println("<p>폼으로부터 전송된 데이터<p>");
		
		//	폼으로부터 전송된 데이터 파라미터 이름 확ㅇ니
		Enumeration <String> params = req.getParameterNames();
		
		out.println("<ul>");
		while(params.hasMoreElements()) {
			String paramName = params.nextElement();
			out.printf("<li>%s = %s</li>%n", paramName, req.getParameter(paramName));
		}
		out.println("<ul>");
		//super.doPost(req, resp);
	}

	@Override
	public void destroy() {
		logger.info("[LifeCycle]: servlet destroy");
		super.destroy();
	}
	
	

	
}
