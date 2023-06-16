import axios from 'axios'

const API_URL = 'http://localhost:8080/proveedor'

class ProveedorService {

    getProveedores(){
        return axios.get(API_URL)
    }

    getProveedor(codigo){
        return axios.get(API_URL + '/' + codigo)
    }

    cargarProveedor(nombre,codigo,categoria,retencion){
        return axios.post(`${API_URL}?nombre=${nombre}&codigo=${codigo}&categoria=${categoria}&retencion=${retencion}`)
    }

}

const instance = new ProveedorService();
export default new ProveedorService()