package co.com.jorge.apiservlet.webapp.headers.services;

import co.com.jorge.apiservlet.webapp.headers.models.Producto;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService{

    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L, "notebook", "computacion", 175000L),
                new Producto(2L, "mesa escritorio", "oficina", 100000L),
                new Producto(3l, "teclado mecanico", "computacion", 40000L));
    }
}
