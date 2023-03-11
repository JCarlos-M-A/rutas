package com.alvarez.app.rutas.controllers;

import com.alvarez.app.rutas.models.Chofer;
import com.alvarez.app.rutas.services.ChoferesService;
import com.alvarez.app.rutas.services.IService;
import jakarta.json.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.*;

@WebServlet("/choferes/listar2")
public class Controlador2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Recuperamos la conexion que provee el filtro
        Connection con = (Connection) req.getAttribute("con");
        IService<Chofer> service = new ChoferesService(con);

        //Declaramos un objeto de tipo Servicio
        List<Chofer> choferes = service.listar();
        req.setAttribute("choferes", choferes);

        //Regresamos JSON <- Goood JSP <- Bad
        resp.setContentType("application/json");
        // Get the printwriter object from response to write the required json object to the output stream
        PrintWriter out = resp.getWriter();
        // Assuming your json object is **jsonObject**, perform the following, it will return your json object
        List<HashMap> listMap = new ArrayList<>();
        HashMap valor;
        for (int i = 0; i < choferes.size(); i++) {
            valor = new HashMap();
            valor.put("id", choferes.get(i).getId());
            valor.put("nombre", choferes.get(i).getNombre());
            valor.put("apMaterno", choferes.get(i).getApMaterno());
            valor.put("apPaterno", choferes.get(i).getApPaterno());
            valor.put("disponibilidad", choferes.get(i).getDisponibilidad());
            valor.put("telefono", choferes.get(i).getTelefono());

            listMap.add(valor);
        }
        System.out.println(listMap);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(listMap);

        out.print(jsonArray);
        out.flush();
    }
}
