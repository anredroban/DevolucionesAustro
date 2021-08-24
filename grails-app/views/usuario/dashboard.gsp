<!DOCTYPE html>
<%@page import="callcenter.Clientes"%>
<%@page import="com.pw.security.*"%>
<%@page import="utilitarios.Util"%>

<html>
<head>
    <meta name="layout" content="main"/>
    <title>Devoluciones - Dashboard</title>
    <asset:javascript src="usogeneral/moment.min.js" />
    <asset:javascript src="usuario/dashboard.js" />

</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                Dashboard <small></small>
            </h1>
            <ol class="breadcrumb">
                <li class="active">
                    <i class="fa fa-dashboard"></i> Dashboard
                </li>
            </ol>
        </div>
    </div>

    <div class="row">
        <a href="${createLink(uri:'/usuario/')}">
            <div class="col-lg-3 col-md-6 col-xs-12 cuadro">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-user fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${Util.getOperadoresLogueados()}</div>
                                <div>¡Agentes!</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </a>

        <a href="${createLink(uri:'#')}">
            <div class="col-lg-3 col-md-6 col-xs-12 cuadro">
                <div class="panel panel-red">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-check fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${Util.getContactados()}</div>
                                <div>¡Contactados!</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </a>

        <a href="${createLink(uri:'#')}">
            <div class="col-lg-3 col-md-6 col-xs-12 cuadro">
                <div class="panel panel-green">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-dollar fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${Util.getCantidadVentas()}</div>
                                <div>¡Exitosos CU1!</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </a>
        <a href="${createLink(uri: '#')}">
            <div class="col-lg-3 col-md-6 col-xs-12 cuadro">
                <div class="panel panel-brown">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-credit-card fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${Util.getAExitosasMes()}</div>
                                <div>¡Exitosos en el mes!</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </a>
        <a href="${createLink(uri:'#')}">
            <div class="col-lg-3 col-md-6 col-xs-12 cuadro">
                <div class="panel panel-brown">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-calendar fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${Util.getContactadosMes()}</div>
                                <div>¡Contactados en el mes!</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </a>

        <a href="${createLink(uri:'#')}">
            <div class="col-lg-3 col-md-6 col-xs-12 cuadro">
                <div class="panel panel-personalizado">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-phone fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${Util.getContactabilidadMensual()}</div>
                                <div>¡Contactabilidad Mensual!</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </a>
        <a href="${createLink(uri:'#')}">
            <div class="col-lg-3 col-md-6 col-xs-12 cuadro">
                <div class="panel panel-personalizado">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <i class="fa fa-phone fa-5x"></i>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="huge">${Util.getContactabilidadDiaria()}</div>
                                <div>¡Contactabilidad Diaria!</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </a>


    </div>

    <div class="row">
        <div class="col-lg-8 col-md-6 col-xs-12">
            <div class="panel panel-border-yellow">
                <div class="panel-heading panel-body-yellow">
                    <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i> Exitosos por Hora</h3>
                    <a class="btn btn-yellow btn-clickable pull-right" onclick="return false;" href="#">
                        <i class="fa fa-chevron-down"></i>
                    </a>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-4 col-md-6 col-xs-12">
            <div class="panel panel-border-blue-u">
                <div class="panel-heading panel-body-blue-u">
                    <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i> Sesiones</h3>
                    <a class="btn btn-blue-u btn-clickable pull-right" onclick="return false;" href="#">
                        <i class="fa fa-chevron-down"></i>
                    </a>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <div style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>Agente</th>
                                    <th>Inicio</th>
                                    <th>Fin</th>
                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${inicioSesion}">
                                    <tr>
                                        <td>${it[0]}</td><td>${it[1]}</td><td>${it[2]?:'Sin cerrar'}</td>
                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12 col-md-6 col-xs-12">
            <div class="panel panel-border-red1">
                <div class="panel-heading panel-body-red1">
                    <h3 class="panel-title"><i class="fa fa-remove fa-fw"></i> Estatus</h3>
                    <a class="btn btn-red1 btn-clickable pull-right" onclick="return false;" href="#">
                        <i class="fa fa-chevron-down"></i>
                    </a>
                </div>
                <div class="panel-body">
                    <div class="table-responsivex">
                        <div class="col-lg-4" id="containerpieCu1" style="min-width: 310px; height: 350px; max-width: 600px; margin: 0 auto"></div>
                        <div class="col-lg-4" id="containerpieCu2" style="min-width: 310px; height: 350px; max-width: 600px; margin: 0 auto"></div>
                        <div class="col-lg-4" id="containerpieCu3" style="min-width: 310px; height: 350px; max-width: 600px; margin: 0 auto"></div>
                        <div class="col-lg-4" id="containerpieCu5" style="min-width: 310px; height: 350px; max-width: 600px; margin: 0 auto"></div>
                        <div class="col-lg-4" id="containerpieCu6" style="min-width: 310px; height: 350px; max-width: 600px; margin: 0 auto"></div>
                        <div class="col-lg-4" id="containerpieNu1" style="min-width: 310px; height: 350px; max-width: 600px; margin: 0 auto"></div>
                        <div class="col-lg-12" id="containerpieNu2" style="min-width: 310px; height: 350px; max-width: 1200px; margin: 0 auto"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-4 col-md-6 col-xs-12">
            <div class="panel panel-border-cyan">
                <div class="panel-heading panel-body-cyan">
                    <h3 class="panel-title"><i class="fa fa-dollar fa-fw"></i> Exitosos por Agente</h3>
                    <a class="btn btn-cyan btn-clickable pull-right" onclick="return false;" href="#">
                        <i class="fa fa-chevron-down"></i>
                    </a>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <div style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>Agente</th>
                                    <th>Exitosas</th>
                                    <%--   <th>Ventas Cruzadas</th> --%>
                                </tr>
                                </thead>
                                <tbody>
                                <%-- <g:each in="${consolidado}">--%>
                                <g:each in="${ventasPorUsuario}">
                                    <tr>
                                        <%--<td>${it[0]}</td><td>${it[1]}</td><td>${it[2]}</td>--%>
                                        <td>${it[0]}</td><td>${it[1]?:0}</td>
                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-8 col-md-6 col-xs-12 col-xs-12">
            <div class="panel panel-border-orange">
                <div class="panel-heading panel-body-orange">
                    <h3 class="panel-title"><i class="fa fa-check fa-fw"></i> Contactados VS No Contactados</h3>
                    <a class="btn btn-orange btn-clickable pull-right" onclick="return false;" href="#">
                        <i class="fa fa-chevron-down"></i>
                    </a>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <div id="containerpiedd" style="min-width: 310px; height: 400px; max-width: 1000px; margin: 0 auto"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12 col-md-12">
            <div class="panel panel-border-black">
                <div class="panel-heading panel-body-black">
                    <h3 class="panel-title" style="color: white"><i class="fa fa-table fa-fw"></i> Detalles Gestion</h3>
                    <a class="btn btn-black btn-clickable pull-right" onclick="return false;" href="#">
                        <i class="fa fa-chevron-down"></i>
                    </a>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <div style="min-width: 310px; height: 400px; margin: 0 auto">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>AGENTE</th>
                                    <th>GESTIONADOS</th>
                                    <th>CONTACTADOS</th>
                                    <th>NO CONTACTADOS</th>
                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${tablaResult}">
                                    <tr>
                                        <td>${it[0]}</td><td>${it[1]}</td><td>${it[2]?:0}</td><td>${it[3]?:0}</td>
                                    </tr>
                                </g:each>
                                <tr style="color: blue; background-color: #D3D3D3;">
                                    <td><strong>TOTAL</strong></td><td><strong>${totalGestionados}</strong></td><td><strong>${totalContactados}</strong></td><td><strong>${totalNoContactados}</strong></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12 col-md-12">
            <div class="panel panel-border-black">
                <div class="panel-heading panel-body-black">
                    <h3 class="panel-title" style="color: white"><i class="fa fa-table fa-fw"></i> Detalles por Base</h3>
                    <a class="btn btn-black btn-clickable pull-right" onclick="return false;" href="#">
                        <i class="fa fa-chevron-down"></i>
                    </a>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <div style="min-width: 310px; height: 400px; margin: 0 auto">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>NOMBRE BASE</th>
                                    <th>CONTACTADOS</th>
                                    <th>EXITOSOS</th>
                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${tablaResult1}">
                                    <tr>
                                        <td>${it[0]}</td><td>${it[1]}</td><td>${it[2]?:0}</td>
                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12">
            <div class="panel panel-border-lila">
                <div class="panel-heading panel-body-lila">
                    <h3 class="panel-title"><i class="fa fa-money fa-fw"></i> Ventas Diarios</h3>
                    <a class="btn btn-lila btn-clickable pull-right" onclick="return false;" href="#">
                        <i class="fa fa-chevron-down"></i>
                    </a>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <div id="containerlinereport" style="min-width: 310px; height: 400px; max-width: 1000px; margin: 0 auto"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-6 col-md-6 col-xs-12">
            <div class="panel panel-border-orange">
                <div class="panel-heading panel-body-orange">
                    <%--<h3 class="panel-title" style="color: #f4f6f7"><i class="fa fa-database fa-fw"></i> Exitos por Agente/Hora</h3>--%>
                    <h3 class="panel-title" style="color: #f4f6f7"><i class="fa fa-headphones fa-fw"></i><strong> GESTIÓN PLUS WIRELESS</strong></h3>
                    <a class="btn btn-orange btn-clickable pull-right" onclick="return false;" href="#">
                        <i class="fa fa-chevron-down"></i>
                    </a>
                </div>
                <div class="panel-body">
                    <%--  <div class="table-responsive">
                          <div style="min-width: 310px; height: 400px; max-width: 1200px; margin: 0 auto">
                              <table class="table table-bordered table-hover table-striped">
                                  <thead>
                                  <tr>
                                      <th>Nombre Agente</th>
                                      <th>8:00</th>
                                      <th>9:00</th>
                                      <th>10:00</th>
                                      <th>11:00</th>
                                      <th>12:00</th>
                                      <th>13:00</th>
                                      <th>14:00</th>
                                      <th>15:00</th>
                                      <th>16:00</th>
                                      <th>17:00</th>
                                      <th>18:00</th>
                                      <th>19:00</th>
                                      <th>20:00</th>
                                      <th>META CR</th>
                                      <th>CUMP CR</th>
                                      <th>META TDC</th>
                                      <th>CUMP TDC</th>
                                  </tr>
                                  </thead>
                                  <tbody>
                                  <g:each in="${tablaResultHorasAgente}">
                                      <tr>
                                          <td>${it[0]}</td><td>${it[1]?:0}</td><td>${it[2]?:0}</td><td>${it[3]?:0}</td><td>${it[4]?:0}</td>
                                          <td>${it[5]?:0}</td><td>${it[6]?:0}</td><td>${it[7]?:0}</td><td>${it[8]?:0}</td><td>${it[9]?:0}</td>
                                          <td>${it[10]?:0}</td><td>${it[11]?:0}</td><td>${it[12]?:0}</td><td>${it[13]?:0}</td><td style="color: black;text-align: center; font-weight: bold;">12</td><td style="color: green;text-align: center; font-weight: bold">${it[14]?:0}</td>
                                          <td style="color: black;text-align: center; font-weight: bold">12</td><td style="color: #0058a2;text-align: center; font-weight: bold">${it[15]?:0}</td>
                                      </tr>
                                  </g:each>
                                  <tr style="color: blue; background-color: #D3D3D3;">
                                      <td><strong>TOTAL</strong></td><td><strong>${total8a9}</strong></td><td><strong>${total9a10}</strong></td><td><strong>${total10a11}</strong></td><td><strong>${total11a12}</strong></td><td><strong>${total12a13}</strong></td><td><strong>${total13a14}</strong></td>
                                      <td><strong>${total14a15}</strong></td><td><strong>${total15a16}</strong></td><td><strong>${total16a17}</strong></td><td><strong>${total17a18}</strong></td><td><strong>${total18a19}</strong></td><td><strong>${total19a20}</strong></td><td><strong>${total20a21}</strong></td>
                                      <td></td><td style="text-align: center"><strong>${totalCruzadasHora}</strong><td></td><td style="text-align: center"><strong>${totalExitosas}</strong>
                                  </tr>
                                  </tbody>
                              </table>
                          </div>
                      </div>--%>

                    <div class="table-responsive">
                        <div style="min-width: 310px; height: 400px; margin: 0 auto">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>NOMBRE VENDEDOR</th>
                                    <th>GESTIONADOS</th>
                                    <th>CONTACTADOS</th>
                                    <th>EXITOSOS</th>

                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${tablaResultPlusWireless}">
                                    <tr>
                                        <td>${it[0]}</td><td>${it[1]?:0}</td><td>${it[2]?:0}</td><td>${it[3]?:0}</td>
                                    </tr>
                                </g:each>
                                <tr style="color: blue; background-color: #D3D3D3;">
                                    <td><strong>TOTAL</strong></td><td><strong>${totalGestionadosPlusWireless}</strong></td><td><strong>${totalContactadosPlusWireless}</strong></td><td><strong>${totalExitososPlusWireless}</strong></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="col-lg-6 col-md-6 col-xs-12">
            <div class="panel panel-border-blue-u">
                <div class="panel-heading panel-body-blue-u">
                    <h3 class="panel-title" style="color: #f4f6f7"><i class="fa fa-cloud fa-fw"></i><strong> GESTIÓN PURE CLOUD</strong> </h3>
                    <a class="btn btn-blue-u btn-clickable pull-right" onclick="return false;" href="#">
                        <i class="fa fa-chevron-down"></i>
                    </a>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <div style="min-width: 310px; height: 400px; margin: 0 auto">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                <tr>
                                    <th>NOMBRE VENDEDOR</th>
                                    <th>GESTIONADOS</th>
                                    <th>CONTACTADOS</th>
                                    <th>EXITOSOS</th>

                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${tablaResultPureCloud}">
                                    <tr>
                                        <td>${it[0]}</td><td>${it[1]?:0}</td><td>${it[2]?:0}</td><td>${it[3]?:0}</td>
                                    </tr>
                                </g:each>
                                <tr style="color: blue; background-color: #D3D3D3;">
                                    <td><strong>TOTAL</strong></td><td><strong>${totalGestionadosPureCloud}</strong></td><td><strong>${totalContactadosPureCloud}</strong></td><td><strong>${totalExitososPureCloud}</strong></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>
</body>
</html>