package ec.edu.espe.segundoparcial.examentomala.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ec.edu.espe.segundoparcial.examentomala.domain.Empresa;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {

    Empresa findByRuc(String ruc);

}
