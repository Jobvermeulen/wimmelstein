package nl.inholland.filter;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Log
@Component
public class LargeRequetsFilter implements Filter {

    public static final int MAX_SIZE = 1000;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        int size = servletRequest.getContentLength();

        log.info("Request size: " + size);
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("Request size " + size + " bigger than " + MAX_SIZE);
        } else
            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
