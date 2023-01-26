package com.esteban.ExamenPracticoM5B.controllers;

import com.esteban.ExamenPracticoM5B.models.Empleado;
import com.esteban.ExamenPracticoM5B.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Empleado>> obtenerLista() {
        return new ResponseEntity<>(empleadoService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Empleado> crear(@RequestBody Empleado c) {

        Integer dias = c.getDias_trabajo();
        double sueldoInicial = c.getSueldo();
        double salario = 0;
        double bono = 0;
        double salarioTotal = 0;

        if ( dias < 20) {
            salario = dias * 15;
            salarioTotal = salario + sueldoInicial;
        } else if ( dias >= 20) {
            salario = dias * 15;
            bono = salario * 0.02;
            salarioTotal = salario + bono + sueldoInicial;
        } else if (dias >= 30){
            salario = dias * 15;
            bono = salario * 0.05;
            salarioTotal = salario + bono + sueldoInicial;
        }
        c.setSueldo(salarioTotal);
        return new ResponseEntity<>(empleadoService.save(c), HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        empleadoService.delete(id);
    }


    @PutMapping("actualizar/{id}")
    public ResponseEntity<Empleado> updateUser(@PathVariable Integer id, @RequestBody Empleado c) {
        if (empleadoService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        c.setNombre(c.getNombre());
        c.setApellido(c.getApellido());
        c.setTelefono(c.getTelefono());
        c.setDireccion(c.getDireccion());
        c.setFecha_nacimiento(c.getFecha_nacimiento());
        c.setObservaciones(c.getObservaciones());
        c.setDias_trabajo(c.getDias_trabajo());
        c.setSueldo(c.getSueldo());

        Empleado newObjeto = empleadoService.save(c);
        return ResponseEntity.ok(newObjeto);
    }

}
