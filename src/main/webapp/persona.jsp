<%@page import="model.Persona"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<div class="container">
		<h1>Nuevo Registro</h1>
		<div class="row">
			<div class="col-6 col-sm-4">
				<form action="ProductServlet?type=registrar" method="post">
					<input class="form-control" type="text" style="display: none"
						name="txtId" readonly="readonly" value="${product.id }" />
					<div class="form-group">
						<label>Nombre</label> <input class="form-control" type="text"
							name="txtnombre" value="${product.nombre }" />
					</div>
					<div class="form-group">
						<label>Email</label> <input class="form-control" type="text"
							name="txtemail" value="${product.email }" />
					</div>
					<div class="form-group">
						<label>Telefono</label> <input class="form-control" type="text"
							name="txttelefono" value="${product.telefono }" />
					</div>
					<div class="form-group">
						<label>Estado Civil</label> <select class="form-control"
							name="txtestado">
							<option value="Soltero"
								${product.estado_civil == 'Soltero' ? 'selected' : ''}>Soltero</option>
							<option value="Casado"
								${product.estado_civil == 'Casado' ? 'selected' : ''}>Casado</option>
							<option value="Viudo"
								${product.estado_civil == 'Viudo' ? 'selected' : ''}>Viudo</option>
						</select>
					</div>

					<div class="form-group">
						<label>¿Tiene Hijos?</label><br>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="txthijos"
								value="si"> <label class="form-check-label">Sí</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="txthijos"
								value="no"> <label class="form-check-label">No</label>
						</div>
					</div>


					<div class="form-group">
						<label>Intereses</label><br>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox"
								name="txtintereses" value="libros"> <label
								class="form-check-label">Libros</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox"
								name="txtintereses" value="musica"> <label
								class="form-check-label">Música</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox"
								name="txtintereses" value="deportes"> <label
								class="form-check-label">Deportes</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox"
								name="txtintereses" value="otros"> <label
								class="form-check-label">Otros</label>
						</div>
					</div>

					<br> <input class="btn btn-primary" type="submit"
						value="Guardar" /> <br> <br> <a class="btn btn-primary"
						href="ProductServlet?type=nuevo">Nuevo Registro</a>
				</form>
			</div>
			<div class="col-6 col-sm-4">
				<table class="table">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nombre</th>
							<th>Email</th>
							<th>Telefono</th>
							<th>Estado Civil</th>
							<th>Hijos</th>
							<th>Intereses</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<Persona> products = (List<Persona>) request.getAttribute("products");
						if (products != null) {
							for (Persona p : products) {
						%>
						<tr>
							<td><%=p.getId()%></td>
							<td><%=p.getNombre()%></td>
							<td><%=p.getEmail()%></td>
							<td><%=p.getTelefono()%></td>
							<td><%=p.getEstado_civil()%></td>
							<td><%=p.getHijos()%></td>
							<td><%=p.getIntereses()%></td>

							<td><a href="ProductServlet?type=editar&code=<%=p.getId()%>"
								style="margin-right: 8px"> <img width="20px"
									src="img/lapiz.png"></a> <a
								href="ProductServlet?type=eliminar&code=<%=p.getId()%>"> <img
									width="20px" src="img/boton-x.png"></a></td>
						</tr>
						<%
						}
						}
						%>
					</tbody>
				</table>
			</div>
		</div>

	</div>

</body>
</html>