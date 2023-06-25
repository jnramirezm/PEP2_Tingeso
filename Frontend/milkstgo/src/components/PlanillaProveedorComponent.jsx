import React, {Component} from "react";
import PagoService from "../services/PagoService";

class PlanillaProveedorComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            codigo: this.props.match.params.codigo,
            pago: []
        }
    }

    componentDidMount() {
        PagoService.getPlanilla(this.state.codigo).then((res) => {
            console.log(res.data);
            if(Array.isArray(this.state.pago) && this.state.pago.length === 0) {
            this.setState({
                pago: res.data,
                
            })
        }
    });
    }

    render() {
        return (
            <div className="container">

                <h1 className="planilla">Planilla de pago</h1>
                <table className="table">
                    <thead>
                    <tr>
                        <th>Codigo</th>
                        <th></th>
                        <th>Fecha</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr >
                            <td > {this.state.pago.proveedor}</td> 
                            <td></td>
                            <td>{this.state.pago.quincena}</td>
                        </tr>

                    </tbody>
                </table>
                <h2 className="planilla2">Datos Acopio </h2>
                <table className="table">
                    <thead>
                    <tr style={{borderRight:"5px", minWidth:"100"}}>
                        <th style={{paddingLeft:"5px"}}> Total KG Leche  </th>
                        <th style={{paddingLeft:"5px"}}>  Promedio diario </th>
                        <th style={{paddingLeft:"5px"}} >  Variacion Leche </th>
                        <th style={{paddingLeft:"5px"}}>   Grasa</th>
                        <th style={{paddingLeft:"5px"}}>  Variacion Grasa</th>
                        <th style={{paddingLeft:"5px"}}>   Solidos</th>
                        <th style={{paddingLeft:"5px"}}>   Variacion Solidos</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr >
                            <td >{this.state.pago.total_kls}</td> 
                            <td>{this.state.pago.promedio_leche}</td>
                            <td>{this.state.pago.variacion_leche}</td>
                            <td>{this.state.pago.grasa}</td>
                            <td>{this.state.pago.variacion_grasa}</td>
                            <td>{this.state.pago.st}</td>
                            <td>{this.state.pago.variacion_st}</td>
                        </tr>
                    </tbody>
                </table>
                <h2 className="planilla2" >Datos Pago </h2>
                <table className="table">
                    <thead >
                    <tr style={{borderRight:"5px", minWidth:"100"}}>
                        <th style={{paddingLeft:"5px"}}> Pago Leche  </th>
                        <th style={{paddingLeft:"5px"}}> Pago Grasa </th>
                        <th style={{paddingLeft:"5px"}}> Pago Solidos </th>
                        <th style={{paddingLeft:"5px"}}> Bonificacion Frecuencia</th>
                        <th style={{paddingLeft:"5px"}}> Dcto Leche </th>
                        <th style={{paddingLeft:"5px"}}> Dcto Grasa</th>
                        <th style={{paddingLeft:"5px"}}> Dcto Solidos</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr >
                            <td >{this.state.pago.pago_leche}</td> 
                            <td>{this.state.pago.pago_grasa}</td>
                            <td>{this.state.pago.pago_st}</td>
                            <td>{this.state.pago.bonificacion_frecuencia}</td>
                            <td>{this.state.pago.dcto_variacion_leche}</td>
                            <td>{this.state.pago.dcto_variacion_grasa}</td>
                            <td>{this.state.pago.dcto_variacion_st}</td>
                        </tr>
                    </tbody>
                </table>
                <h2 className="planilla2" > Pago FINAL </h2>
                <table className="table" >
                    <thead>
                    <tr style={{borderRight:"5px", minWidth:"100"}}>
                        <th style={{paddingLeft:"5px"}}> Pago Total  </th>
                        <th style={{paddingLeft:"5px"}}> Retencion </th>
                        <th style={{paddingLeft:"5px"}}> Pago FINAL </th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr >
                            <td >{this.state.pago.pago_total}</td> 
                            <td>{this.state.pago.retencion}</td>
                            <td>{this.state.pago.pago_final}</td>
                        </tr>
                    </tbody>
                </table>
                </div>
                );
            }




}

export default PlanillaProveedorComponent;
