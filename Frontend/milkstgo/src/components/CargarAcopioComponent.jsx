import React, { Component } from 'react';
import AcopioService from '../services/AcopioService';

class CargarAcopioComponent extends Component {
    constructor() {
        super();
        this.state = {
            file : null
        }
        this.onChangeHandler = this.onChangeHandler.bind(this);
    }

    onChangeHandler(event) {
        this.setState({
            file: event.target.files[0]
        })
    }

    onClickHandler = () => {
        const data = new FormData();
        data.append('file', this.state.file);
        AcopioService.cargarAcopios(data).then(res => {
            console.log(res);
        })
        alert("Acopios cargados correctamente")
    }

    render() {
        return (
            <div className='container'>
                <h1>Subir Acopio</h1>
                <input className='archivo' type="file" name="file" onChange={this.onChangeHandler} />
                <button type="button" className="btnn btn-success" onClick={this.onClickHandler}>Cargar Acopios</button>
            </div>
        );
    }



}

export default CargarAcopioComponent;