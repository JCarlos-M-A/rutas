package com.alvarez.app.rutas.services;

import com.alvarez.app.rutas.models.Chofer;
import com.alvarez.app.rutas.repositories.ChoferesRepository;
import com.alvarez.app.rutas.repositories.IRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ChoferesService implements IService<Chofer>{

    private IRepository<Chofer> choferesRepo;

    public ChoferesService(Connection con) {
        this.choferesRepo = new ChoferesRepository(con);
    }

    @Override
    public List<Chofer> listar() {
        try {
            return choferesRepo.listar();
        }catch (SQLException throwables){
            throw new RuntimeException(throwables.getMessage(),
                    throwables.getCause());
        }
    }

    @Override
    public Optional<Chofer> getById(Long id) {
        try {
            return Optional.ofNullable(choferesRepo.getById(id));
        }catch (SQLException throwables){
            throw new RuntimeException(throwables.getMessage(),
                    throwables.getCause());
        }
    }

    @Override
    public void guardar(Chofer chofer) {
        try {
             choferesRepo.guardar(chofer);
        }catch (SQLException throwables){
            throw new RuntimeException(throwables.getMessage(),
                    throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
             choferesRepo.eliminar(id);
        }catch (SQLException throwables){
            throw new RuntimeException(throwables.getMessage(),
                    throwables.getCause());
        }
    }
}
