package gm.inventarios.repositorio;

import gm.inventarios.modelo.producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productoRepositorio extends JpaRepository<producto, Integer> {
}
