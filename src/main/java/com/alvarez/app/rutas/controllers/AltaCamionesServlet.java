package com.alvarez.app.rutas.controllers;

import com.alvarez.app.rutas.models.Camion;
import com.alvarez.app.rutas.models.enums.Marcas;
import com.alvarez.app.rutas.models.enums.Tipos;
import com.alvarez.app.rutas.services.CamionesService;
import com.alvarez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet("/camiones/alta")
public class AltaCamionesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtener la lista de tipos desde el enumerador
        List<Tipos> tipos = new ArrayList<>(EnumSet.allOf(Tipos.class));
        req.setAttribute("tipos", tipos);
        List<Marcas> marcas = new ArrayList<>(EnumSet.allOf(Marcas.class));
        req.setAttribute("marcas", marcas);
        LocalDate fechaActual = LocalDate.now();
        List<Integer> modelos = IntStream.range(fechaActual.getYear() - 20,
                        fechaActual.getYear() + 2).boxed()
                .collect(Collectors.toList());
        req.setAttribute("modelos", modelos);

        Camion camion = new Camion();
        req.setAttribute("camion", camion);
        getServletContext()
                .getRequestDispatcher("/altaCamiones.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("con");
        IService<Camion> service = new CamionesService(con);
        String matricula = req.getParameter("matricula");
        String tipoCamion = req.getParameter("tipoCamion");
        Integer modelo;

        try {
            modelo = Integer.valueOf(req.getParameter("modelo"));
        } catch (NumberFormatException e) {
            modelo = 0;
        }

        String marca = req.getParameter("marca");
        System.out.println(marca);

        Integer capacidad;
        try {
            capacidad = Integer.valueOf(req.getParameter("capacidad"));
        } catch (NumberFormatException e) {
            capacidad = 0;
        }

        Double kilometraje;
        try {
            kilometraje = Double.valueOf(req.getParameter("kilometraje"));
        } catch (NumberFormatException e) {
            kilometraje = 0D;
        }

        boolean habilitar = req.getParameter("disponibilidad") != null &&
                req.getParameter("disponibilidad").equals("on");

        Map<String, String> errores = new HashMap<>();
        if (matricula == null || matricula.isBlank()) {
            errores.put("matricula", "La matriculas es requerida");
        }
        if (tipoCamion == null || tipoCamion.isBlank()) {
            errores.put("tipoCamion", "El tipo de camion es requerido");
        }
        if (marca == null || marca.isBlank()) {
            errores.put("Marca", "La marca es requerida");
        }
        if (modelo.equals(0)) {
            errores.put("modelo", "El modelo es requerido");
        }
        if (capacidad.equals(0)) {
            errores.put("capacidad", "La capacidad es requerida");
        }
        if (kilometraje.equals(0D)) {
            errores.put("kilometraje", "El kilometraje es requerido");
        }

        Camion camion = new Camion();
        camion.setId(0L);
        camion.setMatricula(matricula);
        if (tipoCamion != null && !tipoCamion.isBlank()) {
            camion.setTipoCamion(Tipos.getFromString(tipoCamion));
        }
        if (marca != null && !marca.isBlank()) {
            camion.setMarca(Marcas.getFromString(marca));
        }

        camion.setModelo(modelo);
        camion.setCapacidad(capacidad);
        camion.setKilometraje(kilometraje);
        camion.setDisponibilidad(habilitar);
        if (errores.isEmpty()) {
            service.guardar(camion);
            resp.sendRedirect(req.getContextPath() + "/camiones/listar");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("camion", camion);

            List<Tipos> tipos = new ArrayList<>(EnumSet.allOf(Tipos.class));
            req.setAttribute("tipos", tipos);
            LocalDate fechaActual = LocalDate.now();
            List<Integer> modelos = IntStream.range(fechaActual.getYear() - 20,
                            fechaActual.getYear() + 2)
                    .boxed().collect(Collectors.toList());
            req.setAttribute("modelos", modelos);
            //
            List<Marcas> marcas = new ArrayList<>(EnumSet.allOf(Marcas.class));
            req.setAttribute("marcas", marcas);
            getServletContext().getRequestDispatcher("/altaCamiones.jsp")
                    .forward(req, resp);
        }
    }
}
