import axios from 'axios'

const API_URL = 'http://localhost:8080/pago'

class PagoService {

    verPlanillas(codigo){
        return axios.get(API_URL + '/' + codigo)
    }

}

const instance = new PagoService();
export default new PagoService()