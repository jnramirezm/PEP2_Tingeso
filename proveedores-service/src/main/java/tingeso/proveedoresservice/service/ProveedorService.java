package tingeso.proveedoresservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.proveedoresservice.entity.ProveedorEntity;
import tingeso.proveedoresservice.repository.ProveedorRepository;

import java.util.ArrayList;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    private final Logger logg = LoggerFactory.getLogger(ProveedorService.class);

    public String guardarProveedor(String codigo, String nombre, String categoria, String retencion){
        ProveedorEntity nProveedor = new ProveedorEntity();
        if(proveedorRepository.findByCodigo(codigo) == null){
            nProveedor.setCodigo(codigo);
            nProveedor.setNombre(nombre);
            nProveedor.setCategoria(categoria);
            nProveedor.setRetencion(retencion);
            proveedorRepository.save(nProveedor);
            return "Se cargo el proveedor con exito";
        }
        else{
            return "Este proveedor ya existe";
        }

    }

    public ArrayList<ProveedorEntity> listarProveedores(){
        return (ArrayList<ProveedorEntity>) proveedorRepository.findAll();
    }

    public ProveedorEntity buscarPorCodigo(String codigo){
        return proveedorRepository.findByCodigo(codigo);
    }

}