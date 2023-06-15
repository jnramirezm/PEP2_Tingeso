package tingeso.pagoservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tingeso.pagoservice.entity.PagoEntity;
import tingeso.pagoservice.models.Acopio;
import tingeso.pagoservice.models.Laboratorio;
import tingeso.pagoservice.models.Proveedor;
import tingeso.pagoservice.repository.PagoRepository;

import java.util.ArrayList;
import java.util.Date;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;
    @Autowired
    private RestTemplate restTemplate;

    // Funciones Acopio
    public ArrayList<Double> obtenerKlsProveedor(String proveedor){
        ArrayList<Double> kls = restTemplate.getForObject("http://acopio-service/acopio/kls/" + proveedor, ArrayList.class);
        return kls;
    }

    public ArrayList<Date> obtenerDiasDeProveedor(String proveedor){
        ArrayList<Date> totalDias = restTemplate.getForObject("http://acopio-service/acopio/dias/" + proveedor, ArrayList.class);
        return totalDias;
    }

    public Integer obtenerDiasPorTurno(String proveedor, String turno){
        Integer cantidadDias = restTemplate.getForObject("http://acopio-service/acopio/turnos/" + proveedor + "/" + turno, Integer.class);
        return cantidadDias;
    }

    public ArrayList<Acopio> obtenerAcopiosProveedor(String proveedor){
        ArrayList<Acopio> acopiosProveedor = restTemplate.getForObject("http://acopio-service/acopio/" + proveedor, ArrayList.class);
        return acopiosProveedor;
    }

   // Funciones Laboratorio

    public ArrayList<Laboratorio> obtenerLaboratoriosProveedor(String proveedor){
        ArrayList<Laboratorio> laboratoriosProveedor = restTemplate.getForObject("http://laboratorio-service/laboratorio/" + proveedor, ArrayList.class);
        return laboratoriosProveedor;
    }

    // Funciones Proveedor

    public ArrayList<Proveedor>  listarProveedores(){
        ArrayList<Proveedor> proveedores = restTemplate.getForObject("http://proveedor-service/proveedor/listar", ArrayList.class);
        return proveedores;
    }

    public Proveedor buscarProveedor(String codigo){
        Proveedor proveedor = restTemplate.getForObject("http://proveedor-service/proveedor/"+ codigo, Proveedor.class);
        return proveedor;
    }


    @Generated
    public void calcularPlantilla(String codigo){
        PagoEntity plantilla = new PagoEntity();
        String proveedor = buscarProveedor(codigo).getCodigo();
        Laboratorio laboratorio = obtenerLaboratoriosProveedor(proveedor).get(0);
        plantilla.setProveedor(proveedor);
        plantilla.setGrasa(laboratorio.getGrasa());
        plantilla.setSt(laboratorio.getSolido());
        plantilla.setQuincena(new Date());
        plantilla.setTotal_kls(totalKilos(proveedor));
        plantilla.setNro_dias_leche(nro_envios(proveedor));
        plantilla.setPromedio_leche(promedio_leche(plantilla.getTotal_kls(),plantilla.getNro_dias_leche()));
        if(pagoRepository.findByProveedor(proveedor).isEmpty()){
            plantilla.setVariacion_leche(0);
            plantilla.setVariacion_grasa(0);
            plantilla.setVariacion_st(0);
        }
        else{
            PagoEntity ultimo_pago = pagoRepository.findByProveedor(proveedor).get(0);
            plantilla.setVariacion_leche(variacion_leche(plantilla.getTotal_kls(), ultimo_pago));
            plantilla.setVariacion_grasa(variacion_grasa(plantilla.getGrasa(),ultimo_pago));
            plantilla.setVariacion_st(variacion_solidos(plantilla.getSt(), ultimo_pago));
        }
        plantilla.setPago_leche(pago_leche(proveedor,plantilla.getTotal_kls()));
        plantilla.setPago_grasa(pago_grasa(plantilla.getGrasa(), plantilla.getTotal_kls()));
        plantilla.setPago_st(pago_solidos(plantilla.getSt(), plantilla.getTotal_kls()));
        plantilla.setBonificacion_frecuencia(bonificacion_frecuencia(proveedor, plantilla.getPago_leche(), plantilla.getPago_grasa(), plantilla.getPago_st()));
        plantilla.setPago_acopio(pago_acopio(plantilla.getPago_leche(),plantilla.getPago_grasa(),plantilla.getPago_st(),plantilla.getBonificacion_frecuencia()));
        plantilla.setDcto_variacion_leche(descuento_leche(plantilla.getPago_acopio(),plantilla.getVariacion_leche()));
        plantilla.setDcto_variacion_grasa(descuento_grasa(plantilla.getPago_acopio(),plantilla.getVariacion_grasa()));
        plantilla.setDcto_variacion_st(descuento_st(plantilla.getPago_acopio(),plantilla.getVariacion_st()));
        plantilla.setDescuentos(descuento_total(plantilla.getDcto_variacion_leche(),plantilla.getDcto_variacion_grasa(), plantilla.getDcto_variacion_st()));
        plantilla.setPago_total(pago_total(plantilla.getPago_acopio(), plantilla.getDescuentos()));
        plantilla.setRetencion(retencion(plantilla.getPago_total()));
        plantilla.setPago_final(monto_final(plantilla.getPago_total(), plantilla.getRetencion()));
        pagoRepository.save(plantilla);
    }

    public double totalKilos(String proveedor){
        ArrayList<Double> kls = obtenerKlsProveedor(proveedor);
        double total_kls = 0.0;
        for(double kl : kls){
            total_kls += kl;
        }
        return total_kls;
    }

    public Double nro_envios(String proveedor){
        ArrayList<Date> fechas;
        double cantidad_entregas;
        fechas  =  obtenerDiasDeProveedor(proveedor);
        cantidad_entregas = fechas.size();
        return cantidad_entregas;
    }

    public Double promedio_leche(Double kls, Double nro_dias){
        Double prom = kls/nro_dias;
        return prom;
    }

    public Double variacion_leche(double total_kls, PagoEntity ultimo_pago){
        Double variacion = 0.0;
        variacion = ((ultimo_pago.getTotal_kls()-total_kls)/total_kls)*100;
        return variacion;
    }

    public Double variacion_grasa(Double grasa, PagoEntity ultimo_pago){
        Double variacion = 0.0;
        variacion = (ultimo_pago.getGrasa() - grasa)/grasa;
        return  variacion*100;
    }

    public Double variacion_solidos(Double St, PagoEntity ultimo_pago){
        Double variacion = 0.0;
        variacion = (ultimo_pago.getSt() - St)/St;
        return variacion*100;
    }

    public Double pago_leche(String proveedor, Double total_kls){
        String categoria = buscarProveedor(proveedor).getCategoria();
        Double pago;
        if(categoria.equals("A")){
            pago = total_kls*700;
        } else if (categoria.equals("B")) {
            pago = total_kls*550;
        } else if (categoria.equals("C")) {
            pago = total_kls*400;
        } else{
            pago = total_kls*250;
        }
        return pago;
    }

    public Double pago_grasa(Double grasa, double total_kls){
        Double pago;
        if(grasa >= 46){
            pago = total_kls*120;
        } else if (grasa >= 21) {
            pago = total_kls * 80;
        } else{
            pago = total_kls * 30;
        }
        return pago;
    }

    public Double pago_solidos(Double solidos, double total_kls){
        Double pago;
        if(solidos >= 36){
            pago = total_kls * 150;
        } else if (solidos >= 19) {
            pago = total_kls * 95;
        } else if (solidos >= 8) {
            pago = total_kls * -90;
        } else{
            pago = total_kls * -130;
        }
        return pago;
    }

    public Double bonificacion_frecuencia(String proveedor, double pago_leche, double pago_grasa, double pago_solidos){
        Double total_M = obtenerDiasPorTurno(proveedor, "M").doubleValue();
        Double total_T = obtenerDiasPorTurno(proveedor, "T").doubleValue();
        Double pago;
        if(total_T > 10 && total_M > 10){
            pago = (pago_leche + pago_grasa + pago_solidos) * .2;
        } else if (total_M > 10) {
            pago =  (pago_leche + pago_grasa + pago_solidos) * .12;
        } else {
            pago =  (pago_leche + pago_grasa + pago_solidos) * .08;
        }
        return pago;
    }

    public Double pago_acopio(double pago_leche, double pago_grasa, double pago_solidos, double bonificacion){
        return pago_leche+pago_grasa+pago_solidos+bonificacion;
    }
    public Double descuento_leche(double pago_acopio, double var_leche){
        if( var_leche >= 46){
            return pago_acopio*0.3;
        } else if (var_leche >= 26) {
            return pago_acopio*0.15;
        } else if (var_leche >= 9) {
            return pago_acopio*0.07;
        } else{
            return 0.0;
        }
    }

    public Double descuento_grasa(double pago_acopio, double var_grasa){
        if(var_grasa >= 41){
            return pago_acopio * 0.3;
        } else if (var_grasa >= 26) {
            return pago_acopio * 0.2;
        } else if (var_grasa >= 16) {
            return  pago_acopio * 0.12;
        } else {
            return 0.0;
        }
    }

    public Double descuento_st(double pago_acopio, double var_st){
        if(var_st >= 36){
            return pago_acopio * 0.45;
        } else if (var_st >= 13) {
            return pago_acopio * 0.27;
        } else if (var_st >= 7) {
            return pago_acopio * 0.18;
        } else {
            return 0.0;
        }
    }

    public Double descuento_total(double dcto_leche, double dcto_grasa, double dcto_st){
        return dcto_leche+dcto_grasa+dcto_st;
    }
    public Double pago_total(double pago_acopio, double dcto){
        return pago_acopio - dcto;
    }

    public Double retencion(double pago_total){
        if(pago_total > 950000){
            return pago_total*0.13;
        }
        return 0.0;
    }

    public Double monto_final(double pago_total, double retencion){
        return  pago_total - retencion;
    }

    public PagoEntity findData(String codigo){
        return pagoRepository.findByProveedor(codigo).get(0);
    }

}
