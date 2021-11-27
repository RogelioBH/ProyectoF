package com.findf.app.services;

import com.findf.app.repositories.*;
import com.findf.app.models.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class DonanteService {


    @Autowired
    DonanteRepository donanteRepository;

    public void guardarDonante(DonanteModel donante){
        this.donanteRepository.save(donante);
        }

        public List<DonanteModel> obtenerDonante(){
            return this.donanteRepository.findAll();
        }

        public Boolean existById(String id){
            return this.donanteRepository.existsById(id);
        }

        public Optional<DonanteModel> buscarPorId(String id){
            return this.donanteRepository.findById(id);
        }

        public void eliminarPorId(String id){
            this.donanteRepository.deleteById(id);
        }

    }
