import React, { Component } from 'react';
import ProveedorService from '../services/ProveedorService';

class ListarProveedoresComponent extends Component {
    constructor() {
        super();
        this.state = {
            proveedores: []
        }
    }

    crearPlanilla(codigo) {
    /*    this.props.history.push(`/planilla/${codigo}`);*/
        window.location.href = `/planilla/${codigo}`;

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
                            <th>Planilla</th>
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
                                        <td> <button onClick={() => this.crearPlanilla(proveedor.codigo)}> Planilla </button> </td>
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
