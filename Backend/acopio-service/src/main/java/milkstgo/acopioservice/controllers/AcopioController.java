package milkstgo.acopioservice.controllers;

import milkstgo.acopioservice.entities.AcopioEntity;
import milkstgo.acopioservice.services.AcopioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/acopio")
public class AcopioController {

    @Autowired
    private AcopioService acopioService;

    @GetMapping("/kls/{proveedor}")
    public ResponseEntity<List<Double>> obtenerKlsProveedor(@PathVariable("proveedor") String proveedor){
        return ResponseEntity.ok(acopioService.obtenerKlsProveedor(proveedor));
    }

    @GetMapping("/dias/{proveedor}")
    public ResponseEntity<List<Date>> obtenerDiasPorProveedor(@PathVariable("proveedor") String proveedor){
        return ResponseEntity.ok(acopioService.obtenerDiasProveedor(proveedor));
    }

    @GetMapping("/turnos/{proveedor}/{turno}")
    public ResponseEntity<Integer> obtenerDiasPorTurno(@PathVariable("proveedor") String proveedor, @PathVariable("turno") String turno){
        return ResponseEntity.ok(acopioService.obtenerDiasPorTurno(proveedor, turno));
    }

    @GetMapping("/{proveedor}")
    public ResponseEntity<List<AcopioEntity>> obtenerAcopiosProveedor(@PathVariable("proveedor") String proveedor){
        return ResponseEntity.ok(acopioService.obtenerAcopiosProveedor(proveedor));
    }

    @PostMapping("/guardar")
    public ResponseEntity<AcopioEntity> upload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        acopioService.guardar(file);
        AcopioEntity acopio = acopioService.leerCsv("acopio.csv");
        return ResponseEntity.ok(acopio);
    }
}
