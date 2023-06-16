package milkstgo.proveedorservice.controllers;

import milkstgo.proveedorservice.entities.ProveedorEntity;
import milkstgo.proveedorservice.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;



    @PostMapping
    private ResponseEntity proveedorUpload(@RequestParam("nombre") String nombre,
                                           @RequestParam("codigo") String codigo,
                                           @RequestParam("categoria") String categoria,
                                           @RequestParam("retencion") String retencion){
        proveedorService.guardarProveedor(codigo, nombre, categoria, retencion);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    private ResponseEntity<ArrayList<ProveedorEntity>> listarProveedores(){
        ArrayList<ProveedorEntity> proveedores;
        proveedores = proveedorService.listarProveedores();
        return ResponseEntity.ok(proveedores);
    }


    @GetMapping("/buscar/{codigo}")
    private ResponseEntity<ProveedorEntity> buscarPorCodigo(@PathVariable("codigo") String codigo){
        return ResponseEntity.ok(proveedorService.buscarPorCodigo(codigo));
    }

}
