package com.alvarez.app.rutas.controllers;

import com.alvarez.app.rutas.models.Camion;
import com.alvarez.app.rutas.services.CamionesService;
import com.alvarez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/camiones/listar")
public class ListaCamioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperamos la conexion que provee el filtro
        Connection con = (Connection) req.getAttribute("con");
        IService<Camion> service = new CamionesService(con);

        //Declaramos un objeto de tipo Servicio
        List<Camion> camiones = service.listar();
        req.setAttribute("camiones", camiones);

        //Regresamos la vista llamada listaCamiones.jsp
        getServletContext()
                .getRequestDispatcher("/listaCamiones.jsp")
                .forward(req, resp);
    }
}
