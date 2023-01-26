package com.esteban.ExamenPracticoM5B.repository;

import com.esteban.ExamenPracticoM5B.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRespository extends JpaRepository<Empleado, Integer> {
}
