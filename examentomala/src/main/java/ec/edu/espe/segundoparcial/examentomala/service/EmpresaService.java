package ec.edu.espe.segundoparcial.examentomala.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import ec.edu.espe.segundoparcial.examentomala.dao.EmpresaRepository;
import ec.edu.espe.segundoparcial.examentomala.domain.Empresa;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa obtenerPorRuc(String ruc) {
        log.info("Se va a obtener la empresa con RUC : {}", ruc);
        Empresa empresa = this.empresaRepository.findByRuc(ruc);
        if (empresa != null) {
            return this.empresaRepository.findByRuc(ruc);
        } else {
            throw new RuntimeException("No existe la empresa con el RUC: " + ruc);
        }
    }

    public Empresa crear(Empresa empresa) {
        empresa.setFechaCreacion(LocalDateTime.now());

        log.info("Se va a realizar la creacion de la empresa" + empresa);

        return this.empresaRepository.save(empresa);
    }

}
