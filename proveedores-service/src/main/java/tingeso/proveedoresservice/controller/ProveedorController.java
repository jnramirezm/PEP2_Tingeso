package tingeso.proveedoresservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tingeso.proveedoresservice.entity.ProveedorEntity;
import tingeso.proveedoresservice.service.ProveedorService;

import java.util.ArrayList;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;



    @PostMapping("/cargarProveedor")
    private void proveedorUpload(@RequestParam("nombre") String nombre,
                                   @RequestParam("codigo") String codigo,
                                   @RequestParam("categoria") String categoria,
                                   @RequestParam("retencion") String retencion,
                                   RedirectAttributes redirectAttributes){
        String mensaje = proveedorService.guardarProveedor(codigo, nombre, categoria, retencion);
        redirectAttributes.addFlashAttribute("mensaje", mensaje);
    }

    @GetMapping("/listar")
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