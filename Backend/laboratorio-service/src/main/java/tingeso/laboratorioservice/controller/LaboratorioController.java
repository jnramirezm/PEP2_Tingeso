package tingeso.laboratorioservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tingeso.laboratorioservice.entity.LaboratorioEntity;
import tingeso.laboratorioservice.service.LaboratorioService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/laboratorio")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;


    @PostMapping
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file){
        laboratorioService.guardar(file);
        laboratorioService.leerCsv("datos.csv");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{proveedor}")
    public ResponseEntity<List<LaboratorioEntity>> buscarLaboratorioPorProveedor(@PathVariable("proveedor") String proveedor){
        return ResponseEntity.ok(laboratorioService.buscarPorProveedor(proveedor));
    }

}