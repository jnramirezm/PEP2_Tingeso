import React, {Component} from 'react';
import ProveedorService from '../services/ProveedorService';

class CargarProveedorComponent extends Component {

    constructor() {
        super();
        this.state = {
            nombre : null,
            categoria : null,
            retencion : null,
            codigo : null
        };
    }



       onNombreChange = (event) => {
        this.setState({
            nombre: event.target.value
        })
    }
        onCategoriaChange = (event) => {
            this.setState({
                categoria: event.target.value
            })
        }

        onRetencionChange = (event) => {
            this.setState({
                retencion: event.target.value
            })
        }

        onCodigoChange = (event) => {
            this.setState({
                codigo: event.target.value
            })
        }

        onClickHandler = (e) => {
            e.preventDefault();
            ProveedorService.cargarProveedor(this.state.nombre, this.state.categoria, this.state.retencion, this.state.codigo).then(res => {
                console.log(res);
            } );
    }

    render() {
        return (
            <div className= "container">
                <h1>Cargar Proveedor</h1>
                <form>
                    <div className="form-group">
                        <label>Nombre</label>
                        <input type="text" className="form-control" placeholder="Nombre" onChange={this.onNombreChange}/>
                    </div>
                    <div className="form-group">
                        <label>Categoria</label>
                        <input type="text" className="form-control" placeholder="Categoria" onChange={this.onCategoriaChange}/>
                    </div>
                    <div className="form-group">
                        <label>Retencion</label>
                        <input type="text" className="form-control" placeholder="Retencion" onChange={this.onRetencionChange}/>
                    </div>
                    <div className="form-group">
                        <label>Codigo</label>
                        <input type="text" className="form-control" placeholder="Codigo" onChange={this.onCodigoChange}/>
                    </div>
                    <button type="submit" className="btnn btn-success btn-block" onClick={this.onClickHandler}>Cargar</button>
                </form>
            </div>
        );
    }

}
export default CargarProveedorComponent;
