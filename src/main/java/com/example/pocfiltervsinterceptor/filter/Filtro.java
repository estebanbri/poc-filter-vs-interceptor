package com.example.pocfiltervsinterceptor.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component
public class Filtro extends HttpFilter {

    private static final Logger log = LoggerFactory.getLogger(Filtro.class);

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {

        if(!browserIsValid(request, "Chrome")) {
            printHtml(response, "<h1 style='color:red'>Browser not supported desde Filter</h1>");
        }

        super.doFilter(request, response, chain);
    }

    private boolean browserIsValid(HttpServletRequest request, String browserNameValid) {
        // Actual validation logic
        String browser = request.getHeader(HttpHeaders.USER_AGENT);
        boolean valid = browser.contains(browserNameValid);
        if(!valid) {
            log.error("{} ==== Browser {} is not valid desde Filter...", browser, new Date());
        }
        return valid;
    }

    private void printHtml(HttpServletResponse response, String html) {
        try (PrintWriter pw = response.getWriter()) {
            pw.write(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
