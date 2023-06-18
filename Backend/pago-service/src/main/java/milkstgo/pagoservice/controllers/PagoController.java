package milkstgo.pagoservice.controllers;

import milkstgo.pagoservice.entities.PagoEntity;
import milkstgo.pagoservice.models.Proveedor;
import milkstgo.pagoservice.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pago")
public class PagoController {
    @Autowired
    PagoService pagoService;

    //@GetMapping("/searchProveedor")
    //private String verProveedores(){
    //    return "searchProveedor";
    //}


    @GetMapping("/planilla/{codigo}")
    private ResponseEntity<PagoEntity> verPlanilla(@PathVariable("codigo") String codigo){
        pagoService.calcularPlantilla(codigo);
        PagoEntity pago = pagoService.findData(codigo);
       // model.addAttribute("pago", pago);
        return ResponseEntity.ok(pago);
    }

    @GetMapping("/proveedores")
    public ResponseEntity<List<Proveedor>> listar(){
        return ResponseEntity.ok(pagoService.listarProveedores());
    }
}
