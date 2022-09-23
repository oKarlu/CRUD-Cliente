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
    String sql;
    
    public boolean gravar(Cliente cli) throws SQLException{
        con = ConexaoFactory.conectar();
        
        if(cli.getIdClinte() == 0){
            sql = "INSERT INTO cliente "
                + "(nome, cpf, endereco, email, telefone, status, dataCadastro)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNome());
            ps.setString(2, cli.getCpf());
            ps.setString(3, cli.getEndereco());
            ps.setString(4, cli.getEmail());
            ps.setString(5, cli.getTelefone());
            ps.setInt(6, cli.getStatus());
            ps.setDate(7, (Date) cli.getDataCadastro());
                    
        } else {
            sql  = "UPDATE cliente "
                    + "SET nome = ?, cpf = ?, endereco = ?, "
                    + "email = ?, telefone = ?, status = ?, dataCadastro? = ?"
                    + "WHERE idCliente = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNome());
            ps.setString(2, cli.getCpf());
            ps.setString(3, cli.getEndereco());
            ps.setString(4, cli.getEmail());
            ps.setString(5, cli.getTelefone());
            ps.setInt(6, cli.getStatus());
            ps.setDate(7, (Date) cli.getDataCadastro());
            ps.setInt(8, cli.getIdClinte());
        }
            ps.executeQuery();
            ConexaoFactory.close(con);
            
            return true;
    }
    
    
    public ArrayList<Cliente> getAllClientes() throws SQLException {
        ArrayList<Cliente> lista = new ArrayList<>();
        sql = "SELECT * FROM cliente";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Cliente cli = new Cliente();
            cli.setIdClinte(rs.getInt("idCliente"));
            cli.setNome(rs.getString("nome"));
            cli.setCpf(rs.getString("cpf"));
            cli.setEndereco(rs.getString("endereco"));
            cli.setEmail(rs.getString("email"));
            cli.setTelefone(rs.getString("telefone"));
            cli.setStatus(rs.getInt("status"));
            cli.setDataCadastro(rs.getDate("dataCadastro"));

            lista.add(cli);
        }
        ConexaoFactory.close(con);
        return lista;
    }
    
    
    
    public Cliente getClientePorId(int idCliente) throws SQLException{
        Cliente cli = new Cliente();
        sql = "SELECT nome, cpf, endereco, email, telefone, status, dataCadastro "
            + "FROM cliente WHERE idCliente = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idCliente);
        rs = ps.executeQuery();
        
        if(rs.next()){
            cli.setNome(rs.getString("nome"));
            cli.setCpf(rs.getString("cpf"));
            cli.setEndereco(rs.getString("endereco"));
            cli.setEmail(rs.getString("email"));
            cli.setTelefone(rs.getString("telefone"));
            cli.setStatus(rs.getInt("status"));
            cli.setDataCadastro(rs.getDate("dataCadastro"));
            
        }
            ConexaoFactory.close(con);
            return cli;
        
    }
  
    
    public boolean ativar(Cliente cli) throws SQLException{
        sql = "UPDATE cliente SET status = 1 "
            + "WHERE idCliente = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, cli.getIdClinte());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }
    
    public boolean desativar(Cliente cli) throws SQLException{
        sql = "UPDATE cliente SET status = 0 "
            + "WHERE idCliente = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, cli.getIdClinte());
        ps.executeUpdate();
        
        ConexaoFactory.close(con);
        return true;
    }
}
