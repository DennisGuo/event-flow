package cn.geobeans.flow;

import cn.geobeans.flow.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 页面
 * Created by Scylla on 2017/1/9.
 */
public class FlowServlet extends HttpServlet {


    protected final String     resourcePath;

    protected String           remoteAddressHeader = null;

    public FlowServlet(String resourcePath){
        this.resourcePath = resourcePath;
    }

    public void init() throws ServletException {

    }


    protected String getFilePath(String fileName) {
        return resourcePath + fileName;
    }

    protected void returnResourceFile(String fileName, String uri, HttpServletResponse response)
            throws ServletException,
            IOException {

        String filePath = getFilePath(fileName);

        if (filePath.endsWith(".html")) {
            response.setContentType("text/html; charset=utf-8");
        }
        if (fileName.endsWith(".jpg")) {
            byte[] bytes = Utils.readByteArrayFromResource(filePath);
            if (bytes != null) {
                response.getOutputStream().write(bytes);
            }

            return;
        }

        String text = Utils.readFromResource(filePath);
        if (text == null) {
            response.sendRedirect(uri + "/index.html");
            return;
        }
        if (fileName.endsWith(".css")) {
            response.setContentType("text/css;charset=utf-8");
        } else if (fileName.endsWith(".js")) {
            response.setContentType("text/javascript;charset=utf-8");
        }
        response.getWriter().write(text);
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String requestURI = request.getRequestURI();

        response.setCharacterEncoding("utf-8");

        if (contextPath == null) { // root context
            contextPath = "";
        }
        String uri = contextPath + servletPath;
        String path = requestURI.substring(contextPath.length() + servletPath.length());



        if ("".equals(path)) {
            if (contextPath.equals("") || contextPath.equals("/")) {
                response.sendRedirect("/druid/index.html");
            } else {
                response.sendRedirect("druid/index.html");
            }
            return;
        }

        if ("/".equals(path)) {
            response.sendRedirect("index.html");
            return;
        }

        if (path.contains(".json")) {
            String fullUrl = path;
            if (request.getQueryString() != null && request.getQueryString().length() > 0) {
                fullUrl += "?" + request.getQueryString();
            }
            response.getWriter().print(process(fullUrl));
            return;
        }

        // find file in resources path
        returnResourceFile(path, uri, response);
    }




    protected String getRemoteAddress(HttpServletRequest request) {
        String remoteAddress = null;

        if (remoteAddressHeader != null) {
            remoteAddress = request.getHeader(remoteAddressHeader);
        }

        if (remoteAddress == null) {
            remoteAddress = request.getRemoteAddr();
        }

        return remoteAddress;
    }

    protected  String process(String url){

        return null;
    }

}
