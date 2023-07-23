package view;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Persona;
import service.ProductService;

import java.io.IOException;
import java.util.List;

import factory.DAOFactory;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL);
	ProductService productService = daoFactory.getProduct();

	public ProductServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("type");

		switch (type) {
		case "registrar":
			String code = request.getParameter("txtId");
			if(code != null && !code.equals("0")) 
				updateProduct(request, response);
			else
				addProduct(request, response);
			break;
		case "listar":
			getProducts(request, response);
			break;
		case "editar":
			obtenerProduct(request, response);
			break;
		case "nuevo":
			cleanProduct(request, response);
			break;
		case "eliminar":
			deleteProduct(request, response);
			break;
		default:
			break;
		}
	}

	private void cleanProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		Persona p = new Persona();
		request.setAttribute("product", p);
		getProducts(request, response);		
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String id = request.getParameter("txtId");
		String nombre = request.getParameter("txtnombre");
		String email = request.getParameter("txtemail");
		String telefono = request.getParameter("txttelefono");
		String estado_civil = request.getParameter("txtestado");
		String hijos = request.getParameter("txthijos");
		String intereses = request.getParameter("txtintereses");


		Persona p = new Persona();
		p.setId(Integer.parseInt(id));
		p.setNombre(nombre);
		p.setEmail(email);
		p.setTelefono(telefono);
		p.setEstado_civil(estado_civil);
		p.setHijos(hijos);
		p.setIntereses(intereses);
	
		int value = productService.updateProduct(p);
		
		if(value == 1) {
			getProducts(request, response);			
		}else {
			request.setAttribute("mensaje", "Error al actualizar");
			request.getRequestDispatcher("persona.jsp").forward(request, response);
		}
		
	}

	private void obtenerProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String id = request.getParameter("code");
		Persona p = productService.getProduct(Integer.parseInt(id));
		
		if(p != null) {
			request.setAttribute("product", p);
			getProducts(request, response);			
		}else {
			request.setAttribute("mensaje", "Error al obtener");
			request.getRequestDispatcher("persona.jsp").forward(request, response);
		}
		
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("code");
		int value = productService.deleteProduct(Integer.parseInt(id));
		
		if(value == 1) {
			getProducts(request, response);			
		}else {
			request.setAttribute("mensaje", "Error al obtener");
			request.getRequestDispatcher("persona.jsp").forward(request, response);
		}
	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre = request.getParameter("txtnombre");
		String email = request.getParameter("txtemail");
		String telefono = request.getParameter("txttelefono");
		String estado_civil = request.getParameter("txtestado");
		String hijos = request.getParameter("txthijos");
		String intereses = request.getParameter("txtintereses");


		Persona p = new Persona();
		p.setNombre(nombre);
		p.setEmail(email);
		p.setTelefono(telefono);
		p.setEstado_civil(estado_civil);
		p.setHijos(hijos);
		p.setIntereses(intereses);

		int value = productService.addProduct(p);

		if (value == 1) {
			getProducts(request, response);
		} else {
			request.setAttribute("mensaje", "Error al listar");
			request.getRequestDispatcher("persona.jsp").forward(request, response);
		}

	}

	private void getProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Persona> products = productService.getProducts();

		if (products != null) {
			request.setAttribute("products", products);
			request.getRequestDispatcher("persona.jsp").forward(request, response);
		} else {
			request.setAttribute("mensaje", "Error al listar");
			request.getRequestDispatcher("persona.jsp").forward(request, response);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
