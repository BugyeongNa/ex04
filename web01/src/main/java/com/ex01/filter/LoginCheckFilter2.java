package com.ex01.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ex01.dto.LoginMemberDTO;
import com.ex01.service.LoginMemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebFilter(urlPatterns = { "/loginuitest/board/*" })
public class LoginCheckFilter2 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		if (session.getAttribute("loginTest") != null) {
			chain.doFilter(request, response);

		} else {

			Cookie cookie = findCookie(req.getCookies(), "remember");

			if (cookie == null) {
				resp.sendRedirect(req.getContextPath() + "/logintest/loginTest.jsp");
			} else {
				String uuid = cookie.getValue();

				try {
					LoginMemberDTO loginMemberDTO = LoginMemberService.INSTACE.getByUUID(uuid);

					if (loginMemberDTO == null) {
						throw new Exception("Cookie value is not vaild!!!");
					}
					session.setAttribute("loginTest", loginMemberDTO);
					chain.doFilter(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					resp.sendRedirect(req.getContextPath() + "/logintest/loginTest.jsp");
				}
			}
		}
	}

	private Cookie findCookie(Cookie[] cookies, String name) {
		if (cookies == null || cookies.length == 0) {
			return null;
		}
		Optional<Cookie> result = Arrays.stream(cookies).filter(ck -> ck.getName().equals(name)).findFirst();
		return result.isPresent() ? result.get() : null;
	}
}
