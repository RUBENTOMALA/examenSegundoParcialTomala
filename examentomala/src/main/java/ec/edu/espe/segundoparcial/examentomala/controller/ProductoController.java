package ec.edu.espe.segundoparcial.examentomala.controller;

import ec.edu.espe.segundoparcial.examentomala.domain.Comentario;
import ec.edu.espe.segundoparcial.examentomala.domain.Producto;
import ec.edu.espe.segundoparcial.examentomala.service.ProductoService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/productos")

public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Void> crearProducto(@RequestBody Producto nuevoProducto) {
        log.info("Se va a crear un nuevo producto: {}", nuevoProducto);
        try {
            this.productoService.crearProducto(nuevoProducto);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error al crear el producto", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/nuevo-comentario/{codigoUnicoProducto}")
    public ResponseEntity<Void> agregarComentario(@PathVariable(name = "codigoUnicoProducto") String codigoUnicoProducto,@RequestBody Comentario comentario) {
        log.info("Se va a agregar un comentario al producto con código único: {}", codigoUnicoProducto);
        try {
            this.productoService.agregarComentario(codigoUnicoProducto, comentario);

            return ResponseEntity.noContent().build();
        } catch (RuntimeException rte) {
            log.error("Error al agregar el comentario al producto", rte);
            return ResponseEntity.badRequest().build();
        }
    }

}
