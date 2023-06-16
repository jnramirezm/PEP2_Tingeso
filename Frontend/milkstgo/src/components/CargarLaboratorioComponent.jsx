import React, { Component } from 'react';
import LaboratorioService from '../services/LaboratorioService';

class CargarLaboratorioComponent extends Component {
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
        LaboratorioService.cargarLaboratorios(data).then(res => {
            console.log(res);
        })
    }

    render() {
        return (
            <div>
                <h1>Subir Laboratorio</h1>
                <input type="file" name="file" onChange={this.onChangeHandler} />
                <button type="button" className="btn btn-success btn-block" onClick={this.onClickHandler}>Upload</button>
            </div>
        );
    }



}

export default CargarLaboratorioComponent;