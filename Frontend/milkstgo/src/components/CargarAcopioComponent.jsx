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
    }

    render() {
        return (
            <div>
                <h1>Subir Acopio</h1>
                <input type="file" name="file" onChange={this.onChangeHandler} />
                <button type="button" className="btn btn-success btn-block" onClick={this.onClickHandler}>Upload</button>
            </div>
        );
    }



}

export default CargarAcopioComponent;