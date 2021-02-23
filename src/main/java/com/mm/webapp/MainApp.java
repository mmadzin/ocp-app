/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mm.webapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mmadzin
 */
public class MainApp extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        File readiness = new File(getServletContext().getRealPath("/readiness.jsp"));
        File liveness = new File(getServletContext().getRealPath("/liveness.jsp"));

        String delete = "delete";
        String create = "create";

        if (delete.equals(request.getParameter("readiness"))) {
            readiness.delete();
        }

        if (create.equals(request.getParameter("readiness"))) {
            if (readiness.createNewFile()) {
                FileWriter fw = new FileWriter(readiness);
                fw.write(
                        "<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>"
                        + "<!DOCTYPE html>"
                        + "<html>\n"
                        + "    <head>\n"
                        + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                        + "        <title>Readiness</title>\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "        <h1>Readiness file</h1>\n"
                        + "    </body>\n"
                        + "</html>");
                fw.close();
            }
        }

        if (delete.equals(request.getParameter("liveness"))) {
            liveness.delete();
        }

        if (create.equals(request.getParameter("liveness"))) {
            if (liveness.createNewFile()) {
                FileWriter fw = new FileWriter(liveness);
                fw.write(
                        "<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>"
                        + "<!DOCTYPE html>"
                        + "<html>\n"
                        + "    <head>\n"
                        + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                        + "        <title>Liveness</title>\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "        <h1>Liveness file</h1>\n"
                        + "    </body>\n"
                        + "</html>");
                fw.close();
            }
        }

        Map<String, String[]> params = request.getParameterMap();
        PrintWriter out = response.getWriter();
        
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainApp</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Servlet which can delete/create liveness and readiness files");
            out.println("<p>Parameters: ");
            out.println("<ul>");
            out.println("<li>readiness=[" + delete + " | " + create + "]");
            out.println("<li>liveness=[" + delete + " | " + create + "]");
            out.println("</ul></p>");
            out.println("<p>Last Action: ");
            for (String param  : params.keySet()) {
                out.println(param + "=" + params.get(param)[0] + " ");
            }
            out.println("</p>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
