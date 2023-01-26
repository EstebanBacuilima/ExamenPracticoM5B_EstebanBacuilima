package com.esteban.ExamenPracticoM5B.services;

import com.esteban.ExamenPracticoM5B.models.Empleado;
import com.esteban.ExamenPracticoM5B.repository.EmpleadoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl extends GenericServiceImpl<Empleado, Integer> implements EmpleadoService {

    @Autowired
    EmpleadoRespository empleadoRespository;

    @Override
    public CrudRepository<Empleado, Integer> getDao() {
            return empleadoRespository;
    }


}