package br.com.marcoshssilva.springbooteureka.controller.filters;

import io.opentelemetry.api.trace.Span;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TracingHeaderFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TracingHeaderFilter.class);
    private static final String HEADER_TRACING = "X-Trace-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader(HEADER_TRACING, Span.current().getSpanContext().getTraceId());
        LOGGER.info("Request {}?{}", request.getPathTranslated(), request.getQueryString());
        filterChain.doFilter(request, response);
    }
}
