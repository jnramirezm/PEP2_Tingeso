import axios from 'axios'

const API_URL = 'http://127.0.0.1:64586/proveedor'

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