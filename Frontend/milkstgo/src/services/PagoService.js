import axios from 'axios'

const API_URL = 'http://127.0.0.1:64804/pago'

class PagoService {

    getPlanilla(codigo){
        return axios.get(API_URL + '/planilla/' + codigo)
    }

}

const instance = new PagoService();
export default new PagoService()