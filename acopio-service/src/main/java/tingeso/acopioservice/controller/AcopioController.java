package tingeso.acopioservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tingeso.acopioservice.entity.AcopioEntity;
import tingeso.acopioservice.service.AcopioService;

import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/acopio")
public class AcopioController {

    @Autowired
    private AcopioService acopioService;

    @GetMapping("/kls/{proveedor}")
    public ResponseEntity<ArrayList<Double>> obtenerKlsProveedor(@PathVariable("proveedor") String proveedor){
        return ResponseEntity.ok(acopioService.obtenerKlsProveedor(proveedor));
    }

    @GetMapping("/dias/{proveedor}/")
    public ResponseEntity<ArrayList<Date>> obtenerDiasPorProveedor(@PathVariable("proveedor") String proveedor){
        return ResponseEntity.ok(acopioService.obtenerDiasProveedor(proveedor));
    }

    @GetMapping("/turnos/{proveedor}/{turno}")
    public ResponseEntity<Integer> obtenerDiasPorTurno(@PathVariable("proveedor") String proveedor, @PathVariable("turno") String turno){
        return ResponseEntity.ok(acopioService.obtenerDiasPorTurno(proveedor, turno));
    }

    @GetMapping("/{proveedor}")
    public ResponseEntity<ArrayList<AcopioEntity>> obtenerAcopiosProveedor(@PathVariable("proveedor") String proveedor){
        return ResponseEntity.ok(acopioService.obtenerAcopiosProveedor(proveedor));
    }

    @PostMapping
    public void upload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        acopioService.guardar(file);
        String salida = acopioService.leerCsv("acopio.csv");
        redirectAttributes.addFlashAttribute("mensaje", salida );
    }
}
