package tingeso.acopioservice.service;

import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tingeso.acopioservice.entity.AcopioEntity;
import tingeso.acopioservice.repository.AcopioRepository;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service

public class AcopioService {
    @Autowired
    private AcopioRepository acopioRepository;

    private final Logger logg = LoggerFactory.getLogger(AcopioService.class);

    @Generated
    public String guardar(MultipartFile file){
        String name = file.getOriginalFilename();
        if(name != null){
            if(!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo guardado");
                }
                catch(IOException e){
                    logg.error("ERROR", e);
                }
            }
            return "Archivo guardado con exito";
        }
        else{
            return "No se ha guardado el archivo";
        }
    }


    @Generated
    public String leerCsv(String archivo){
        BufferedReader bf = null;
        acopioRepository.deleteAll();
        try{
            bf = new BufferedReader(new FileReader(archivo));
            String temp = "";
            String bfRead;
            int count = 1;
            while((bfRead = bf.readLine()) != null){
                if(count == 1){
                    count = 0;
                }else{
                    guardarDatos(bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2], bfRead.split(";")[3]);
                    temp = temp + "\n" + bfRead;
                }
            }
            return "Acopio cargado con exito";
        }catch(Exception e){
            return "Error al cargar el acopio";
        }finally{
            if(bf != null){
                try{
                    bf.close();
                }catch(IOException e){
                    logg.error("ERROR", e);
                }
            }
        }
    }

    public void guardarDatos(String fecha, String turno, String proveedor, String kls_leche){
        AcopioEntity data = new AcopioEntity();
        data.setProveedor(proveedor);
        if(fecha != null){
            try {
                Date date  = new SimpleDateFormat("yyyy/MM/dd").parse(fecha);
                data.setFecha(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        data.setKls(Integer.valueOf(kls_leche));
        data.setTurno(turno);
        acopioRepository.save(data);
    }


    public ArrayList<Double> obtenerKlsProveedor(String proveedor){
        return acopioRepository.findAllByProveedor(proveedor);
    }

    public ArrayList<Date> obtenerDiasProveedor(String proveedor){
        return acopioRepository.findAllDays(proveedor);
    }

    public Integer obtenerDiasPorTurno(String proveedor, String turno){
        return acopioRepository.findAllDaysWhereTurno(proveedor, turno);
    }

    public ArrayList<AcopioEntity> obtenerAcopiosProveedor(String proveedor){
        return acopioRepository.findByProveedor(proveedor);
    }



}
