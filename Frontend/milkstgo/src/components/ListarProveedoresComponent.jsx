import React, { Component } from 'react';
import ProveedorService from '../services/ProveedorService';

class ListarProveedoresComponent extends Component {
    constructor() {
        super();
        this.state = {
            proveedores: []
        }
    }

    

    componentDidMount() {
        ProveedorService.getProveedores().then((res) => {
            this.setState({
                proveedores: res.data
            })
        });
    }

    render() {
        return (
            <div className="container">
                <h1>Listar Proveedores</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Categoria</th>
                            <th>Retencion</th>
                            <th>Codigo</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.proveedores.map(
                                proveedor =>
                                    <tr key={proveedor.id}>
                                        <td>{proveedor.nombre}</td>
                                        <td>{proveedor.categoria}</td>
                                        <td>{proveedor.retencion}</td>
                                        <td>{proveedor.codigo}</td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        );
    }

}

export default ListarProveedoresComponent;
