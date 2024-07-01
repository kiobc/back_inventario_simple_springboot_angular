package gm.inventarios.servicio;

import gm.inventarios.modelo.producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gm.inventarios.repositorio.productoRepositorio;

import java.util.List;

@Service

public class productoServicio  implements IProductoServicio{

    @Autowired
    private productoRepositorio productoRepositorio;

    @Override
    public List<producto> listarProductos() {
     return   this.productoRepositorio.findAll();
    }

    @Override
    public producto buscarProductoPorId(Integer idProducto) {
        producto producto = this.productoRepositorio.findById(idProducto).orElse(null);
        return producto;
    }

    @Override
    public producto guardarProducto(producto producto) {
      return  this.productoRepositorio.save(producto);

    }

    @Override
    public void eliminarProductoPorId(Integer idProducto) {
this.productoRepositorio.deleteById(idProducto);
    }
}
