package com.esteban.ExamenPracticoM5B.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "nombre",length = 45)
    @Nullable
    private String nombre;

    @Column(name = "apellido",length = 45)
    @Nullable
    private String apellido;

    @Column(name = "telefono",length = 45)
    @Nullable
    private String telefono;

    @Nullable
    @Column(name = "direccion",length = 45)
    private String direccion;

    @Nullable
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;

    @Nullable
    @Column(name = "observaciones")
    private String observaciones;

    @Nullable
    @Column(name = "dias_trabajo")
    private Integer dias_trabajo;

    @Nullable
    @Column(name = "sueldo")
    private double sueldo;
}
