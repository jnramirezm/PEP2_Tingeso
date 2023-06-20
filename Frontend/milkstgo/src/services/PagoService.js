import axios from 'axios'

const API_URL = 'http://localhost:8080/pago'

class PagoService {

    getPlanilla(codigo){
        return axios.get(API_URL + '/planilla/' + codigo)
    }

}

const instance = new PagoService();
export default new PagoService()