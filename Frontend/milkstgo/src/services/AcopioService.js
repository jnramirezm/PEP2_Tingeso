import axios from 'axios'

const API_URL = 'http://localhost:8080/acopio'

class AcopioService {

    cargarAcopios(file){
        return axios.post(API_URL+"/guardar", file)
    }
    
    

}

const instance = new AcopioService();
export default new AcopioService()