import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';


export default class inicioComponent extends Component {

    ref (dir) {
        window.location.href = dir;
    }

    render() {
        // 5 botones

        return (
            <div>
                <h1>Inicio</h1>
                <button type="button" className="btn btn-success btn-block" onClick={() => this.ref('/cargarProveedor')}>Cargar Proveedor</button>
                <button type="button" className="btn btn-success btn-block" onClick={() => this.ref('/acopio')}>Cargar Acopio</button>
                <button type="button" className="btn btn-success btn-block" onClick={() => this.ref('/proveedores')}>Listar Proveedores</button>
                <button type="button" className="btn btn-success btn-block" onClick={() => this.ref('/cargarLaboratorios')}>Cargar Laboratorio</button>
            </div>
        );
    }


}