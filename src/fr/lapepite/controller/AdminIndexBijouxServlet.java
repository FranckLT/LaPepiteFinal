/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.lapepite.controller;

import fr.lapepite.db.utils.DBBijouxUtils;
import fr.lapepite.javabean.Bijoux;
import fr.lapepite.services.BijouxServices;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class AdminIndexBijouxServlet extends HttpServlet {
	
	public static final String URL_AFTER_DELETE = "/LaPepite/admin/bijoux";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        BijouxServices bijouxServices = new BijouxServices();
        
        List<Bijoux> listBijoux = new ArrayList<>();
        
        listBijoux = bijouxServices.getAll();
        
        request.setAttribute("listBijoux", listBijoux);
        
        getServletContext().getRequestDispatcher("/jsp/admin/adminIndexBijoux.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	BijouxServices services = new BijouxServices();
    	
    	services.deleteBijoux(request);
    	
    	response.sendRedirect(URL_AFTER_DELETE);
        
    }


}
