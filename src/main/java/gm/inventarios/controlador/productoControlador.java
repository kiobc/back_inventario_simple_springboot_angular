package gm.inventarios.controlador;

import gm.inventarios.excepcion.recursoNoEncontradoExcepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gm.inventarios.servicio.productoServicio;
import gm.inventarios.modelo.producto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
//http://localhost:3035/inventario-app
@RequestMapping("inventario-app")
@CrossOrigin(value="http://localhost:4200")
public class productoControlador {
    private  static  final Logger logger =
            Logger.getLogger(productoControlador.class.getName());

    @Autowired
    private productoServicio productoServicio;

    @GetMapping("/productos")
    public List<producto> obtenerProductos(){
        List<producto> productos = this.productoServicio.listarProductos();
        logger.info("Productos obtenidos");
        productos.forEach((producto ->logger.info(producto.toString())));
        return productos;
    }

    @PostMapping("/productos")
    public producto agregarProducto(@RequestBody producto producto){
        logger.info("Producto a agregar: "+ producto);
      return this.productoServicio.guardarProducto(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<producto> obtenerProductoPorId(@PathVariable int id){
        producto producto = this.productoServicio.buscarProductoPorId(id);
        if(producto != null)
            return ResponseEntity.ok(producto);
        else
         throw   new recursoNoEncontradoExcepcion("No se encontró el id" + id);

    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<producto> actualizarProducto(@RequestBody producto productoRecibido, @PathVariable int id){
        producto producto = this.productoServicio.buscarProductoPorId(id);
        if(producto == null)
            throw new recursoNoEncontradoExcepcion("No se encontró el id" + id);
        producto.setDescripcion(productoRecibido.getDescripcion());
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setExistencia(productoRecibido.getExistencia());
        this.productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable int id){
producto producto = this.productoServicio.buscarProductoPorId(id);
if(producto == null)
    throw new recursoNoEncontradoExcepcion("No se encontró el id" + id);
this.productoServicio.eliminarProductoPorId(producto.getIdProducto());
Map<String, Boolean> respuesta= new HashMap<>();
respuesta.put("eliminado", Boolean.TRUE);
return ResponseEntity.ok(respuesta);
    }
}
