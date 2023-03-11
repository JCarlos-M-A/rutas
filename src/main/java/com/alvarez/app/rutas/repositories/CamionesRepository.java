package com.alvarez.app.rutas.repositories;

import com.alvarez.app.rutas.models.Camion;
import com.alvarez.app.rutas.models.enums.Marcas;
import com.alvarez.app.rutas.models.enums.Tipos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CamionesRepository implements IRepository<Camion>{

    private Connection con;

    public CamionesRepository(Connection con) {
        this.con = con;
    }

    @Override
    public List<Camion> listar() throws SQLException {
        List<Camion> camiones = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM CAMIONES")){
            while (rs.next()){
                Camion a = this.getCamion(rs);
                camiones.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return camiones;
    }

    private Camion getCamion(ResultSet rs) throws SQLException {
        Camion a = new Camion();
        a.setId(rs.getLong("ID_CAMION"));
        a.setMatricula(rs.getString("MATRICULA"));
        a.setTipoCamion(Tipos.getFromString(rs.getString("TIPO_CAMION")));
        a.setModelo(rs.getInt("MODELO"));
        a.setMarca(Marcas.getFromString(rs.getString("MARCA")));
        a.setCapacidad(rs.getInt("CAPACIDAD"));
        a.setKilometraje(rs.getDouble("KILOMETRAJE"));
        a.setDisponibilidad(rs.getBoolean("DISPONIBILIDAD"));
        return a;
    }

    @Override
    public Camion getById(Long id) throws SQLException {
        Camion camion = new Camion();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM CAMIONES WHERE ID_CAMION = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    camion = this.getCamion(rs);
                }
            }
        }
        return camion;
    }

    @Override
    public void guardar(Camion camion) throws SQLException {
        String sql = "";
        if(camion.getId() != null && camion.getId() > 0){
            sql = "UPDATE camiones SET MATRICULA=?, TIPO_CAMION=?,"+
                    "MODELO=?, MARCA=?,CAPACIDAD=?,"+
                    "KILOMETRAJE=?, DISPONIBILIDAD=?"+
                    "WHERE ID_CAMION=?";
        }else{
            sql = "INSERT INTO CAMIONES(" +
                    "    ID_CAMION," +
                    "    MATRICULA," +
                    "    TIPO_CAMION," +
                    "    MODELO," +
                    "    MARCA," +
                    "    CAPACIDAD," +
                    "    KILOMETRAJE," +
                    "    DISPONIBILIDAD) " +
                    "    VALUES " +
                    "    (-1,?,?,?,?,?,?,?)";
        }

        try (PreparedStatement stmt = con.prepareStatement(sql)){
            if(camion.getId() != null && camion.getId() > 0){
                stmt.setString(1,camion.getMatricula());
                stmt.setString(2,camion.getTipoCamion().getDescripcion());
                stmt.setInt(3,camion.getModelo());
                stmt.setString(4,camion.getMarca().getDescripcion());
                stmt.setInt(5,camion.getCapacidad());
                stmt.setDouble(6,camion.getKilometraje());
                stmt.setInt(7, camion.getDisponibilidad() ? 1 : 0);
                stmt.setLong(8, camion.getId());
            }else{
                stmt.setString(1, camion.getMatricula().toString());
                stmt.setString(2, camion.getTipoCamion().getDescripcion());
                stmt.setInt(3, camion.getModelo());
                stmt.setString(4,camion.getMarca().getDescripcion());
                stmt.setInt(5, camion.getCapacidad());
                stmt.setFloat(6, Float.parseFloat(camion.getKilometraje().toString()));
                stmt.setInt(7, camion.getDisponibilidad() ? 1 : 0);
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM camiones WHERE id_camion=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }
}
