package com.cdkj.ride;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cdkj.ride.proxy.DispatcherImpl;
import com.cdkj.ride.proxy.IDispatcher;

/**
 * @author: xieyj 
 * @since: 2016年9月17日 下午2:22:17 
 * @history:
 */
public class APIServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(APIServlet.class);

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 6175432226630152841L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        logger.info("Successful Deployment");
        PrintWriter writer = response.getWriter();
        writer.append("Version:v2.2.6 \n");
        writer.append("Description:2nd \n");
        writer.flush();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String json = request.getParameter("json");
        IDispatcher dispatcher = new DispatcherImpl();
        String result = dispatcher.doDispatcher(code, json);
        PrintWriter writer = response.getWriter();
        writer.append(result);
        writer.flush();
    }
}
