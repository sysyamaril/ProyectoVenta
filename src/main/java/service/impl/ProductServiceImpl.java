package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MysqlConexion;
import model.Persona;
import service.ProductService;

public class ProductServiceImpl implements ProductService{

	@Override
	public int addProduct(Persona p) {
		int value = 0;
		PreparedStatement psmt = null;
		Connection cn = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "INSERT INTO persona "
					+ "(nombre, email, telefono, estado_civil, hijos, intereses) "
					+ "VALUES(?, ?, ?, ?, ?, ?)";
			psmt = cn.prepareStatement(query);
			psmt.setString(1, p.getNombre());
			psmt.setString(2, p.getEmail());
			psmt.setString(3, p.getTelefono());
			psmt.setString(4, p.getEstado_civil());
			psmt.setString(5, p.getHijos());
			psmt.setString(6, p.getIntereses());
			value = psmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return value;
	}
	
	@Override
	public List<Persona> getProducts() {
		List<Persona> products = new ArrayList<Persona>();
		PreparedStatement psmt = null;
		Connection cn = null;
		ResultSet rs = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "SELECT * FROM persona";
			psmt = cn.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Persona p = new Persona();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setEmail(rs.getString("email"));
				p.setTelefono(rs.getString("telefono"));
				p.setEstado_civil(rs.getString("estado_civil"));
				p.setHijos(rs.getString("hijos"));
				p.setIntereses(rs.getString("intereses"));
				products.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return products;
	}

	@Override
	public Persona getProduct(int id) {
		Persona product = null;
		PreparedStatement psmt = null;
		Connection cn = null;
		ResultSet rs = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "SELECT * FROM persona WHERE id = ?";
			psmt = cn.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				product = new Persona();
				product.setId(rs.getInt("id"));
				product.setNombre(rs.getString("nombre"));
				product.setEmail(rs.getString("email"));
				product.setTelefono(rs.getString("telefono"));
				product.setEstado_civil(rs.getString("estado_civil"));
				product.setHijos(rs.getString("hijos"));
				product.setIntereses(rs.getString("intereses"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return product;
	}
	
	@Override
	public int updateProduct(Persona p) {
		int value = 0;
		PreparedStatement psmt = null;
		Connection cn = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "UPDATE persona "
					+ "SET nombre=?, email=?, telefono=?, estado_civil=?, hijos=?,intereses=? "
					+ "WHERE id=?";
			psmt = cn.prepareStatement(query);
			psmt.setString(1, p.getNombre());
			psmt.setString(2, p.getEmail());
			psmt.setString(3, p.getTelefono());
			psmt.setString(4, p.getEstado_civil());
			psmt.setString(5, p.getHijos());
			psmt.setString(6, p.getIntereses());
			psmt.setInt(7, p.getId());
			value = psmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return value;
	}

	@Override
	public int deleteProduct(int id) {
		int value = 0;
		PreparedStatement psmt = null;
		Connection cn = null;
		try {
			cn = MysqlConexion.getConnection();
			String query = "DELETE FROM persona WHERE id=?";
			psmt = cn.prepareStatement(query);
			psmt.setInt(1, id);
			value = psmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(psmt != null) psmt.close();
				if(cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return value;
	}

}
