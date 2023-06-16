package milkstgo.proveedorservice.services;

import milkstgo.proveedorservice.entities.ProveedorEntity;
import milkstgo.proveedorservice.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
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
