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

@Controller
@RequestMapping("/laboratorio")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;


    @PostMapping
    public void upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        laboratorioService.guardar(file);
        String salida = laboratorioService.leerCsv("datos.csv");
        redirectAttributes.addFlashAttribute("mensaje", salida );
     // return "redirect:/datoUpload";
    }

    @GetMapping("/{proveedor}")
    public ResponseEntity<ArrayList<LaboratorioEntity>> buscarLaboratorioPorProveedor(@PathVariable("proveedor") String proveedor){
        return ResponseEntity.ok(laboratorioService.buscarPorProveedor(proveedor));
    }

}