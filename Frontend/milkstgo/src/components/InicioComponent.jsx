import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './Inicio.css';


export default class inicioComponent extends Component {

    ref (dir) {
        window.location.href = dir;
    }


    render() {
        return (
            <div className="container">
            <h1 className="title">MilkStgo</h1>
                <a href="/cargarProveedor" className="btn">Cargar Proveedor</a>
                <a href="/acopio" className="btn">Cargar Acopio</a>
                <a href="/proveedores" className="btn">Listar Proveedores</a>
                <a href="/cargarLaboratorios" className="btn">Cargar Laboratorio</a>
        </div>
        );
    }


}

