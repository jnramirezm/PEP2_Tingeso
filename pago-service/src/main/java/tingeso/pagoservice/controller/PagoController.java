package tingeso.pagoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tingeso.pagoservice.entity.PagoEntity;
import tingeso.pagoservice.models.Proveedor;
import tingeso.pagoservice.service.PagoService;
import java.util.ArrayList;

@RestController
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    PagoService pagoService;

    @GetMapping("/searchProveedor")
    private String verProveedores(){
        return "searchProveedor";
    }


    @GetMapping("/viewPlanilla/{codigo}")
    private String verPlanilla(@PathVariable("codigo") String codigo, Model model){
        pagoService.calcularPlantilla(codigo);
        PagoEntity pago = pagoService.findData(codigo);
        model.addAttribute("pago", pago);
        return "viewPlanilla";
    }

    @GetMapping("/proveedores")
    public ResponseEntity<ArrayList<Proveedor>> listar(){
        return ResponseEntity.ok(pagoService.listarProveedores());
    }


}
