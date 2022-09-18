package factory;

import db.db;
import impl.ClienteDaoJDBC;

public class DaoFactory {
    
public static ClienteDaoJDBC createClienteDao() {
		return new ClienteDaoJDBC(db.getConnection());
	}

}
