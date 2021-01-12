/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class compra extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            String pasoCompra = request.getParameter("pasoCompra");
            Cookie cookies [] = request.getCookies();
            Cookie c = null ;
            if(pasoCompra==null){
                //Buscamos si existe la cookie con nombre paso
                if(cookies!=null){
                    for(int i =0; i<cookies.length;i++){
                        if(cookies[i].getName().equals("paso")){
                            c =cookies[i];//si la encuentra la guardamos en c
                        }
                    }
                }
                if(c!=null){
                    //si c no es nula, quiere decir que existia
                    pasoCompra = c.getValue();//cogemos su valor que sera el paso en el que nos quedamos
                    switch (pasoCompra){
                        case "1":
                            response.addCookie(c);
                            response.sendRedirect("paso1.html");
                            break;
                        case "2": 
                            response.addCookie(c);
                            response.sendRedirect("paso2.html");
                            break;
                        case "3": 
                            response.addCookie(c);
                            response.sendRedirect("paso3.html");
                            break;
                        case "4": 
                            response.addCookie(c);
                            response.sendRedirect("paso4.html");
                            break;
                        case "5": 
                            //si es el paso 5, antes de redireccionar borramos la cookie
                            c.setMaxAge(0);
                            response.addCookie(c);
                            response.sendRedirect("paso5.html");
                            break;
                        default:
                            
                            break;
                    }
                }else{
                    //si c era null es porque no existe, asi que la creamos y redireccionamos al paso 1
                    Cookie ca = new Cookie("paso","1");
                    ca.setMaxAge(600);
                    response.addCookie(ca);
                    response.sendRedirect("paso1.html");
                }     
            }else{
                if(cookies!=null){
                    //buscamos la cookie y le cambiamos el valor que indica el paso
                    for(int i =0; i<cookies.length;i++){
                        if(cookies[i].getName().equals("paso")){
                           c = cookies[i];
                           c.setValue(pasoCompra);
                        }
                    }
                switch (pasoCompra){
                        case "2": 
                            response.addCookie(c);
                            response.sendRedirect("paso2.html");
                            break;
                        case "3": 
                            response.addCookie(c);
                            response.sendRedirect("paso3.html");
                            break;
                        case "4": 
                            response.addCookie(c);
                            response.sendRedirect("paso4.html");
                            break;
                        case "5": 
                            c.setMaxAge(0);
                            response.addCookie(c);
                            response.sendRedirect("paso5.html");
                            break;
                        case "0": 
                            //si el valor 0 es que han cancelado el proceso por lo que borramos la cookie
                            c.setMaxAge(0);
                            response.addCookie(c);
                            response.sendRedirect("index.jsp");
                            break;
                        default: break;
                    }
                }
            }
            /* TODO output your page here. You may use following sample code. */
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
