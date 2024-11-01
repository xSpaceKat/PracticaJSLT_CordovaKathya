/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import beans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "ServletUsuario", urlPatterns = {"/ServletUsuario"})
public class ServletUsuario extends HttpServlet {

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
        
        String action = request.getParameter("action");
        String url = "/index.jsp";

        if ("listUsers".equals(action)) {
            // Crear una lista de usuarios (normalmente la obtendrías de una base de datos)
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(new Usuario("Juan", "Pérez", 25));
            usuarios.add(new Usuario("Ana", "García", 30));
            usuarios.add(new Usuario("Luis", "Martínez", 28));

            // Guardar la lista de usuarios en el request
            request.setAttribute("usuarios", usuarios);

            // Enviar a la página de visualización de usuarios
            url = "/lista-usuarios.jsp";
        }

        // Redirigir a la URL correspondiente
        this.getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
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
        
        String url = "/index.jsp";
        
        //get current action
        String action = request.getParameter("action");
        if(action == null){
            action = "join"; //default action
        }
        
        //Perform action and set URL to appropiate page
        if(action.equals("join")){
            url = "/index.jsp"; 
        }else if(action.equals("add")){
            //get parameters from the request
            String name = request.getParameter("name");
            String lastName = request.getParameter("last-name");  
            int age = Integer.parseInt(request.getParameter("age"));

            //store data in an object and save in database
            Usuario user = new Usuario(name, lastName, age);

            //set User objetc in request object and set URL
            request.setAttribute("user", user);

            url="/registro-usuario.jsp";
            
           
        }
        
        //forward request and response objects to specified URL
        this.getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
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
