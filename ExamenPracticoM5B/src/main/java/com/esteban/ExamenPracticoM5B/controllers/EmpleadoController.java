package com.esteban.ExamenPracticoM5B.controllers;

import com.esteban.ExamenPracticoM5B.models.Empleado;
import com.esteban.ExamenPracticoM5B.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
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
    public ResponseEntity<String> crear(@RequestBody Empleado c) {

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        Integer dias = c.getDias_trabajo();
        double sueldoInicial = c.getSueldo();
        double salario = 0;
        double bono = 0;
        double salarioTotal = 0;

        if (c.getNombre() == null || c.getApellido() == null || c.getTelefono() == null || c.getDireccion() == null
                || c.getFecha_nacimiento() == null || c.getObservaciones() == null || c.getDias_trabajo() == null || c.getNombre() == "" || c.getApellido() == "" || c.getTelefono() == "" || c.getDireccion() == ""
                || c.getObservaciones() == "" || c.getDias_trabajo() == 0) {
            return new ResponseEntity<>("Valores Nulos", HttpStatus.BAD_REQUEST);
        }else {

            if (c.getSueldo() < 0 || c.getDias_trabajo() < 0){
                return new ResponseEntity<>("No acepta negativos en el SUELDO o DIAS", HttpStatus.BAD_REQUEST);
            } else {
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
                empleadoService.save(c);
                return new ResponseEntity<>("Creado Exitosamente", HttpStatus.CREATED);
            }
        }

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
