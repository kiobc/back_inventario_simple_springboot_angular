package gm.inventarios.servicio;
import gm.inventarios.modelo.producto;
import java.util.List;

public interface IProductoServicio {
    public List<producto> listarProductos();

    public producto buscarProductoPorId(Integer idProducto);
    public producto guardarProducto(producto producto);

    public void eliminarProductoPorId( Integer idProducto);
}
