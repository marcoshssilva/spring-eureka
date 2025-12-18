package br.com.marcoshssilva.springbooteureka.controller.filters;

import io.opentelemetry.api.trace.Span;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@lombok.extern.slf4j.Slf4j
@Component
public class TracingHeaderFilter extends OncePerRequestFilter {
    private static final String HEADER_TRACING = "X-Trace-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader(HEADER_TRACING, Span.current().getSpanContext().getTraceId());
        filterChain.doFilter(request, response);
    }
}
