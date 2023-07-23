package factory;


import service.ProductService;
import service.impl.ProductServiceImpl;

public class MysqlDAOFactory extends DAOFactory{

	

	@Override
	public ProductService getProduct() {
		return new ProductServiceImpl();
	}

}
