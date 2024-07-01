package gm.inventarios.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import gm.inventarios.servicio.productoServicio;
import gm.inventarios.modelo.producto;

import java.util.List;
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
}
