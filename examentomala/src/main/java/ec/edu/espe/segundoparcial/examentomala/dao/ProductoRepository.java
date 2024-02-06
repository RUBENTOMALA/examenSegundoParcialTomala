package ec.edu.espe.segundoparcial.examentomala.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ec.edu.espe.segundoparcial.examentomala.domain.Comentario;
import ec.edu.espe.segundoparcial.examentomala.domain.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String> {
    List<Producto> findByRucEmpresaOrderByDescripcion(String rucEmpresa);
    List<Comentario> findComentarioByCodigoUnicoProducto(String codigoUnicoProducto);
    Producto findByCodigoUnicoProducto(String codigoUnicoProducto);
    

}
