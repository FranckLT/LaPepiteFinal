/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.javabean.Bijoux;
import fr.lapepite.services.BijouxServices;
import fr.lapepite.services.PanierServices;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class productServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Bijoux bijoux = new Bijoux();
        
        bijoux = new BijouxServices().getOneBijoux(request);
        
        request.setAttribute("bijoux", bijoux);
        
        getServletContext().getRequestDispatcher("/jsp/product.jsp").forward(request, response);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        new PanierServices().addBijouxToPanier(request);
        
        int idProduct =  Integer.parseInt(request.getParameter("id"));
        
        response.sendRedirect("/LaPepite/shop/product?id="+idProduct);
        
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
