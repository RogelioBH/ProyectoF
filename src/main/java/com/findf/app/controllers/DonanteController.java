package com.findf.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.swing.text.html.ListView;
import javax.validation.Valid;

import com.findf.app.exceptions.CustomException;
import com.findf.app.models.DonanteModel;
import com.findf.app.services.DonanteService;

//import org.apache.catalina.mapper.Mapper;
//import org.apache.coyote.ErrorState;
//import org.apache.coyote.Response;
//import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Listener;
//import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.tags.form.ErrorsTag;

@RestController
@RequestMapping("/api")
public class DonanteController {

    @Autowired
    DonanteService donanteService;

    @PostMapping("/donante")
    public ResponseEntity<Map<String, String>> guardar(@Valid @RequestBody DonanteModel donante,Errors error){
        if(error.hasErrors()){
            throwError(error);
        }



        this.donanteService.guardarDonante(donante);
        Map<String, String>response= new HashMap<>();
        response.put("mensaje","El donante se agrego correctamente");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/donante")
    public List<DonanteModel> traerTodos(){
        return this.donanteService.obtenerDonante();
    }

    @GetMapping("/donante/{id}")
    public DonanteModel buscarID(@PathVariable String id){
        return this.donanteService.buscarPorId(id).get();
    }
    //Metodo HTTP y el path
    @DeleteMapping("/donante/{id}")
    public ResponseEntity<Map<String, String>> eliminarPorId(@PathVariable String id){
        Boolean existe=this.donanteService.existById(id);
        Map<String, String> respuesta=new HashMap<>();
        if(existe){
            respuesta.put("mensaje","No existe donante por este Id");
            return ResponseEntity.ok(respuesta);
        }
        donanteService.eliminarPorId(id);
        respuesta.put("mensaje","el donante se elimino correctamente");
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/donante")
    public ResponseEntity<Map<String, String>> actualizar(@RequestBody DonanteModel donante, Errors error){
        if(error.hasErrors()){
            throwError(error);
        }

        donanteService.guardarDonante(donante);
        Map<String, String> respuesta=new HashMap<>();
        respuesta.put("mensaje","el donante se actualizo correctamente");
        return ResponseEntity.ok(respuesta);
    }

    private void throwError(Errors error) {
        String message="";
        int index=0;
        for(ObjectError e: error.getAllErrors()){
            if(index>0){
                message += "|";
            }

            message += String.format("Parametro: %s - Mensaje: %s", e.getObjectName(),e.getDefaultMessage());
        }

        throw new CustomException(message);

    }
}



