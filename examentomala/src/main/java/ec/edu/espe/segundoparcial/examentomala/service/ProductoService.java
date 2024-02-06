package ec.edu.espe.segundoparcial.examentomala.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import ec.edu.espe.segundoparcial.examentomala.dao.ProductoRepository;
import ec.edu.espe.segundoparcial.examentomala.domain.Comentario;
import ec.edu.espe.segundoparcial.examentomala.domain.Producto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductosPorRuc(String rucEmpresa) {
        log.info("Se va a obtener la lista de productos con RUC : {}", rucEmpresa);
        List<Producto> productos = this.productoRepository.findByRucEmpresaOrderByDescripcion(rucEmpresa);
        if (!productos.isEmpty()) {
            log.info("Se encontró productos con el RUC : {}" + rucEmpresa);
            return productos;
        } else {
            throw new RuntimeException("No existen productos para la empresa con RUC: " + rucEmpresa);
        }
    }

    public List<Comentario> obtenerComentariosProducto(String codigoUnicoProducto) {

        log.info("Se va a obtener los comentarios del producto con código único: {}", codigoUnicoProducto);
        List<Comentario> comentarios = this.productoRepository.findComentarioByCodigoUnicoProducto(codigoUnicoProducto);

        if (!comentarios.isEmpty()) {
            log.info("Se encontró comentarios para el codigo de producto : {}" + codigoUnicoProducto);
            return comentarios;
        } else {

            throw new RuntimeException(
                    "No existen comentarios para el producto con código único: " + codigoUnicoProducto);
        }
    }

    public void crearProducto(Producto nuevoProducto) {

        try {
            nuevoProducto.setCodigoUnicoProducto(new DigestUtils("MD2").digestAsHex(nuevoProducto.toString()));
            log.info("Se va a crear un nuevo producto con código único: {}", nuevoProducto.getCodigoUnicoProducto());
            this.productoRepository.save(nuevoProducto);
            log.info("Se creó el producto" + nuevoProducto);

        } catch (Exception e) {
            throw new RuntimeException("Error al crear el producto.", e);
        }
    }

    public void agregarComentario(String codigoUnicoProducto, Comentario comentario) {
        log.info("Se va a agregar un comentario al producto con código único: {}", codigoUnicoProducto);

        Producto producto = this.productoRepository.findByCodigoUnicoProducto(codigoUnicoProducto);

        if (producto != null) {
            List<Comentario> comentarios = producto.getComentario();
            comentarios.add(comentario);
            producto.setComentario(comentarios);
            this.productoRepository.save(producto);
        } else {
            throw new RuntimeException("No existe un producto con el código único: " + codigoUnicoProducto);
        }
    }

}
