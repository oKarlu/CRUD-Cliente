package dao;


import java.util.List;
import model.Cliente;

public interface ClienteDAO {

	void insert(Cliente obj);
	void update(Cliente obj);
	void deleteById(Integer id);
	
	Cliente findById(Integer id);
	List<Cliente> findAll();

}
