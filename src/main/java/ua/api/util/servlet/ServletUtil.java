package ua.api.util.servlet;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class ServletUtil {
    public static void writeStatus(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; UTF=8");
        response.getWriter().write(String.valueOf(response.getStatus()));
    }
    private ServletUtil() {
    }
}
