import axios from 'axios'

const API_URL = 'http://localhost:8080/laboratorio'

class LaboratorioService {

    cargarLaboratorios(file){
        return axios.post(API_URL, file)
    }
    
}

const instance = new LaboratorioService();
export default new LaboratorioService()