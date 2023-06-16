import React from 'react'
import { BrowserRouter, Route, Switch } from 'react-router-dom'
import Navbar from './components/NavbarComponent'
import CargarAcopioComponent from './components/CargarAcopioComponent';
import ListarProveedoresComponent from './components/ListarProveedoresComponent';
import CargarProveedorComponent from './components/CargarProveedorComponent';
import InicioComponent from './components/InicioComponent';
import CargarLaboratorioComponent from './components/CargarLaboratorioComponent';


function App() {
  
  return (
    <div>
      <Navbar />
      <BrowserRouter>
          <Switch>
            <Route path="/cargarProveedor" component={CargarProveedorComponent} />
            <Route path="/proveedores" component={ListarProveedoresComponent} />
            <Route path="/acopio" component={CargarAcopioComponent} />
            <Route path="/cargarLaboratorios" component={CargarLaboratorioComponent} />
            <Route path="/" component={InicioComponent} />
          </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
