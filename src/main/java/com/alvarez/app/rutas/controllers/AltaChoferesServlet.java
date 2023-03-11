package com.alvarez.app.rutas.controllers;

import com.alvarez.app.rutas.models.Chofer;
import com.alvarez.app.rutas.models.enums.Marcas;
import com.alvarez.app.rutas.services.ChoferesService;
import com.alvarez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/choferes/alta")
public class AltaChoferesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Regresamos la vista llamada altaChofer.jsp
        getServletContext()
                .getRequestDispatcher("/altaChofer.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("con");
        IService<Chofer> service = new ChoferesService(con);

        String nombre = req.getParameter("nombre");
        String apPaterno = req.getParameter("apPaterno");
        String apMaterno = req.getParameter("apMaterno");
        String licencia = req.getParameter("licencia");
        String telefono = req.getParameter("telefono");
        String fechaNacimiento = req.getParameter("fechaNacimiento");

        LocalDate fecha;

        try {
            fecha = LocalDate.parse(fechaNacimiento,
                    DateTimeFormatter.ofPattern("dd/MM/yyy"));
        }catch (DateTimeParseException E){
            fecha = null;
        }

        String checkbox[];
        checkbox = req.getParameterValues("disponibilidad");
        Boolean habilitar;
        if(checkbox != null){
            habilitar = true;
        }else{
            habilitar = false;
        }
        Map<String, String> errores = new HashMap<>();
        if(nombre == null || nombre.isBlank()){
            errores.put("nombre", "el nombre es requerido");
        }
        if(apPaterno == null || apPaterno.isBlank()){
            errores.put("apPaterno", "el apPaterno es requerido");
        }
        if(apMaterno == null || apMaterno.isBlank()){
            errores.put("apMaterno", "el apMaterno es requerido");
        }
        if(licencia == null || licencia.isBlank()){
            errores.put("licencia", "el licencia es requerido");
        }
        if(telefono == null || telefono.isBlank()){
            errores.put("telefono", "el telefono es requerido");
        }
        if(telefono == null || telefono.isBlank()){
            errores.put("telefono", "el telefono es requerido");
        }
        if(fechaNacimiento == null || fechaNacimiento.isBlank()){
            errores.put("fechaNacimiento", "el fechaNacimiento es requerido");
        }

        if(errores.isEmpty()){
            Chofer chofer = new Chofer();
            chofer.setId(0L);
            chofer.setNombre(nombre);
            chofer.setApPaterno(apPaterno);
            chofer.setApMaterno(apMaterno);
            chofer.setLicencia(licencia);
            chofer.setTelefono(telefono);
            chofer.setFechaNacimiento(fecha);
            chofer.setDisponibilidad(habilitar);

            service.guardar(chofer);
            resp.sendRedirect(req.getContextPath()+"/choferes/listar");
        }else{
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/altaChofer.jsp")
                    .forward(req,resp);
        }
    }
}
