package com.alvarez.app.rutas.controllers;

import com.alvarez.app.rutas.models.Chofer;
import com.alvarez.app.rutas.services.ChoferesService;
import com.alvarez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/choferes/listar")
public class ListaChoferesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperamos la conexion que provee el filtro
        Connection con = (Connection) req.getAttribute("con");
        IService<Chofer> service = new ChoferesService(con);

        //Declaramos un objeto de tipo Servicio
        List<Chofer> choferes = service.listar();
        req.setAttribute("choferes", choferes);

        //Regresamos la vista llamada listaChoferes.jsp
        getServletContext()
                .getRequestDispatcher("/listaChoferes.jsp")
                .forward(req, resp);
    }

}
