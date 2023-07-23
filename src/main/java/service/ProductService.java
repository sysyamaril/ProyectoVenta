package service;

import java.util.List;

import model.Persona;

public interface ProductService {

	public int addProduct(Persona p);
	public int updateProduct(Persona p);
	public List<Persona> getProducts();
	public Persona getProduct(int id);
	public int deleteProduct(int id);
	
}
