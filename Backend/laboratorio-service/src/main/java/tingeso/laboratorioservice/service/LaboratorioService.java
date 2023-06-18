package tingeso.laboratorioservice.service;

import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tingeso.laboratorioservice.entity.LaboratorioEntity;
import tingeso.laboratorioservice.repository.LaboratorioRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LaboratorioService {
    @Autowired
    private LaboratorioRepository laboratorioRepository;
    private final Logger logg = LoggerFactory.getLogger(LaboratorioService.class);

    @Generated
    public String guardar(MultipartFile file) {
        String name = file.getOriginalFilename();
        if (name != null) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo guardado");
                } catch (IOException e) {
                    logg.error("ERROR", e);
                }
            }
            return "Archivo guardado con exito";
        } else {
            return "No se ha guardado el archivo";
        }
    }


    @Generated
    public String leerCsv(String archivo) {
        BufferedReader bf = null;
        //laboratorioRepository.deleteAll();
        try {
            bf = new BufferedReader(new FileReader(archivo));
            String temp = "";
            String bfRead;
            int count = 1;
            while ((bfRead = bf.readLine()) != null) {
                if (count == 1) {
                    count = 0;
                } else {
                    guardarData(bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2]);
                    temp = temp + "\n" + bfRead;
                }
            }
            return "Acopio cargado con exito";
        } catch (Exception e) {
            return "Error al cargar el acopio";
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    logg.error("ERROR", e);
                }
            }
        }
    }


    public void guardarData(String proveedor, String grasa, String solido) {
        LaboratorioEntity data = new LaboratorioEntity();
        data.setProveedor(proveedor);
        data.setGrasa(Integer.valueOf(grasa));
        data.setSolido(Integer.valueOf(solido));
        data.setFecha(new Date());
        laboratorioRepository.save(data);
    }


    public List<LaboratorioEntity> buscarPorProveedor(String proveedor){
        return laboratorioRepository.findByProveedor(proveedor);
    }
}