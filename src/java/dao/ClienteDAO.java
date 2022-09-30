package dao;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Cliente;
import java.sql.SQLException;

public class ClienteDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";
    
    public boolean gravar(Cliente cli) throws SQLException{
        con = ConexaoFactory.conectar();
        
        if(cli.getIdCliente() == 0){
            sql = "INSERT INTO cliente(nome, cpf, endereco, email, telefone, dataCadastro, status)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNome());
            ps.setString(2, cli.getCpf());
            ps.setString(3, cli.getEndereco());
            ps.setString(4, cli.getEmail());
            ps.setString(5, cli.getTelefone());
            ps.setDate(6, new Date(cli.getDataCadastro().getTime()));
            ps.setInt(7, cli.getStatus());
            
                    
        } else {
            sql  = "UPDATE cliente "
                    + "SET nome = ?, cpf = ?, endereco = ?, "
                    + "email = ?, telefone = ?, status = ?, dataCadastro = ?"
                    + "WHERE idCliente = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNome());
            ps.setString(2, cli.getCpf());
            ps.setString(3, cli.getEndereco());
            ps.setString(4, cli.getEmail());
            ps.setString(5, cli.getTelefone());
            ps.setInt(6, cli.getStatus());
            ps.setDate(7, (Date) cli.getDataCadastro());
            ps.setInt(8, cli.getIdCliente());
        }
            ps.executeQuery();
            ConexaoFactory.close(con);
            
            return true;
    }
    
    
    public ArrayList<Cliente> getLista() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        sql = "SELECT * FROM cliente";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Cliente cli = new Cliente();
            cli.setIdCliente(rs.getInt("idCliente"));
            cli.setNome(rs.getString("nome"));
            cli.setCpf(rs.getString("cpf"));
            cli.setEndereco(rs.getString("endereco"));
            cli.setEmail(rs.getString("email"));
            cli.setTelefone(rs.getString("telefone"));
            cli.setDataCadastro(rs.getDate("dataCadastro"));
            cli.setStatus(rs.getInt("status"));

            clientes.add(cli);
        }
        ConexaoFactory.close(con);
        return clientes;
    }
    
    
    
    public Cliente getCarregarPorId(int idCliente) throws SQLException{
        Cliente c = new Cliente();
        sql = "SELECT nome, cpf, endereco, email, telefone, status, dataCadastro "
            + "FROM cliente WHERE idCliente = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idCliente);
        rs = ps.executeQuery();
        
        if(rs.next()){
            c.setNome(rs.getString("nome"));
            c.setCpf(rs.getString("cpf"));
            c.setEndereco(rs.getString("endereco"));
            c.setEmail(rs.getString("email"));
            c.setTelefone(rs.getString("telefone"));
            c.setDataCadastro(rs.getDate("dataCadastro"));
            c.setStatus(rs.getInt("status"));
            
        }
            ConexaoFactory.close(con);
            return c;
        
    }
  
    
    public boolean ativar(Cliente cli) throws SQLException{
        sql = "UPDATE cliente SET status = 1 "
            + "WHERE idCliente = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, cli.getIdCliente());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }
    
    public boolean desativar(Cliente cli) throws SQLException{
        sql = "UPDATE cliente SET status = 0 "
            + "WHERE idCliente = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, cli.getIdCliente());
        ps.executeUpdate();
        
        ConexaoFactory.close(con);
        return true;
    }
}
