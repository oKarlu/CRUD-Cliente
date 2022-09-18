package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.db;
import db.DbException;
import dao.ClienteDAO;
import java.sql.Date;
import model.Cliente;

public class ClienteDaoJDBC implements ClienteDAO {

	public Connection conn = null;

	public ClienteDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Cliente obj) {
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("INSERT INTO cliente " + "(Nome, Cpf, endereco, email, telefone, status, dataCadastro) " + "VALUES (?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					Long id = rs.getLong(1);
					obj.setIdClinte(id);
				}
			} else {
				throw new DbException("Unexpected error! no rows Affected");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			db.closeStatement(st);
		}

	}

	@Override
	public void update(Cliente obj) {
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("UPDATE department " + "SET Nome = ?  Cpf = ?  endereco = ?, email = ?, telefone = ?, status = ?, dataCadastro = ?" + "WHERE Id = ?");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getCpf());
                        st.setString(3, obj.getEndereco());
			st.setString(4, obj.getEmail());
                        st.setString(5, obj.getTelefone());
                        st.setInt(6, obj.getStatus());
                        st.setDate(7, (Date)obj.getDataCadastro());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			db.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM cliente WHERE Id = ?");
			
			st.setInt(1, id);
			
			int rows = st.executeUpdate();
			if(rows == 0) {
				throw new DbException("Trying delete inexisting id");
			}
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			db.closeStatement(st);
		}
	}

	@Override
	public Cliente findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				
				return dep;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT * FROM department ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			while(rs.next()) {
				
				Department dep = instantiateDepartment(rs);
				list.add(dep);
			}
			return list;
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

}