package com.alvarez.app.rutas.repositories;

import com.alvarez.app.rutas.models.Chofer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChoferesRepository implements IRepository<Chofer>{

    private Connection con;

    public ChoferesRepository(Connection con) {
        this.con = con;
    }

    @Override
    public List<Chofer> listar() throws SQLException {
        List<Chofer> choferes = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM CHOFERES")){
            while (rs.next()){
                Chofer a = getChofer(rs);
                choferes.add(a);
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
        System.out.println(choferes.get(0).getNombre());
        return choferes;
    }

    private Chofer getChofer(ResultSet rs) throws SQLException {
        Chofer a = new Chofer();
        a.setId(rs.getLong("ID_CHOFER"));
        a.setNombre(rs.getString("NOMBRE"));
        a.setApPaterno(rs.getString("AP_PATERNO"));
        a.setApMaterno(rs.getString("AP_MATERNO"));
        a.setLicencia(rs.getString("LICENCIA"));
        a.setTelefono(rs.getString("TELEFONO"));
        a.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO").toLocalDate());
        a.setDisponibilidad(rs.getBoolean("DISPONIBILIDAD"));
        return a;
    }

    @Override
    public Chofer getById(Long id) throws SQLException {
        Chofer chofer = new Chofer();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM choferes WHERE ID_CHOFER = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    chofer = getChofer(rs);
                }
            }
        }
        return chofer;
    }

    @Override
    public void guardar(Chofer chofer) throws SQLException {
        String sql = "";
        if(chofer.getId() != null && chofer.getId() > 0){
            sql = "UPDATE choferes SET nombre=?, ap_paterno=?,"+
                    "ap_materno=?, licencia=?,telefono=?,"+
                    "fecha_nacimiento=?, disponibilidad=?"+
                    "WHERE id_chofer=?";
        }else{
            sql = "INSERT INTO CHOFERES(" +
                    "    ID_CHOFER," +
                    "    NOMBRE," +
                    "    AP_PATERNO," +
                    "    AP_MATERNO," +
                    "    LICENCIA," +
                    "    TELEFONO," +
                    "    FECHA_NACIMIENTO," +
                    "    DISPONIBILIDAD) " +
                    "    VALUES " +
                    "    (-1,?,?,?,?,?,?,?)";
        }

        try (PreparedStatement stmt = con.prepareStatement(sql)){
            if(chofer.getId() != null && chofer.getId() > 0){
                stmt.setString(1,chofer.getNombre());
                stmt.setString(2,chofer.getApPaterno());
                stmt.setString(3,chofer.getApMaterno());
                stmt.setString(4,chofer.getLicencia());
                stmt.setString(5,chofer.getTelefono());
                stmt.setDate(6,Date.valueOf(chofer.getFechaNacimiento()));
                stmt.setInt(7, chofer.getDisponibilidad() ? 1 : 0);
                stmt.setLong(8, chofer.getId());
            }else{
                stmt.setString(1, chofer.getNombre().toString());
                stmt.setString(2, chofer.getApPaterno().toString());
                stmt.setString(3, chofer.getApMaterno().toString());
                stmt.setString(4, chofer.getLicencia().toString());
                stmt.setString(5, chofer.getTelefono().toString());
                stmt.setDate(6, Date.valueOf(chofer.getFechaNacimiento()));
                stmt.setInt(7, chofer.getDisponibilidad() ? 1 : 0);
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM choferes WHERE id_chofer=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }
}
