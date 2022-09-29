package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import dao.ClienteDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "GerenciarCliente", urlPatterns = {"/gerenciarCliente"})
public class GerenciarCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text-html; charset=utf-8");
        String acao = request.getParameter("acao");
        String idCliente = request.getParameter("idCliente");
        String mensagem = "";

        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();

        try {
            if (acao.equals("listar")) {
                ArrayList<Cliente> clientes = new ArrayList<>();
                clientes = clienteDAO.getAllClientes();
                RequestDispatcher dispatcher
                        = getServletContext().
                                getRequestDispatcher("/listarClientes.jsp");
                request.setAttribute("clientes", clientes);
                dispatcher.forward(request, response);

            } else if (acao.equals("alterar")) {
                cliente = clienteDAO.getClientePorId(Integer.parseInt(idCliente));

                RequestDispatcher dispatcher
                        = getServletContext().
                                getRequestDispatcher("/cadastrarCliente.jsp");
                request.setAttribute("cliente", cliente);
                dispatcher.forward(request, response);
            } else if (acao.equals("ativar")) {
                cliente.setIdCliente(Integer.parseInt(idCliente));
                if (clienteDAO.ativar(cliente)) {
                    mensagem = "Cliente ativado com sucesso!";

                } else {
                    mensagem = "Falha ao ativar o cliente!";

                }

            } else if (acao.equals("desativar")) {
                cliente.setIdCliente(Integer.parseInt(idCliente));
                if (clienteDAO.desativar(cliente)) {
                    mensagem = "Cliente desativado com sucesso!";
                } else {
                    mensagem = "Falha ao desativar o cliente!";
                }
            } else {
                response.sendRedirect("index.jsp");
            }

        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();

        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='gerenciarCliente?acao=listar';"
                + "</script>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String idCliente = request.getParameter("idCliente");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String dataCadastro = request.getParameter("dataCadastro");
        String status = request.getParameter("status");
        String mensagem = "";
        System.out.println("Status: " + status);

        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (!idCliente.isEmpty()) {
            cliente.setIdCliente(Integer.parseInt(idCliente));
        }

        if (nome.equals("") || nome.isEmpty()) {
            request.setAttribute("msg", "Informe o nome do usuário!");
            despacharRequisicao(request, response);
        } else {
            cliente.setNome(nome);
        }

        if (cpf.equals("") || cpf.isEmpty()) {
            request.setAttribute("msg", "Informe o cpf do cliente!");
            despacharRequisicao(request, response);
        } else {
            cliente.setCpf(cpf);
        }

        if (endereco.equals("") || endereco.isEmpty()) {
            request.setAttribute("msg", "Informe o endereco do cliente!");
            despacharRequisicao(request, response);
        } else {
            cliente.setEndereco(endereco);
        }

        if (email.equals("") || email.isEmpty()) {
            request.setAttribute("msg", "Informe o email do cliente!");
            despacharRequisicao(request, response);
        } else {
            cliente.setEmail(email);
        }

        if (telefone.equals("") || email.isEmpty()) {
            request.setAttribute("msg", "Informe o telefone do cliente!");
            despacharRequisicao(request, response);
        } else {
            cliente.setTelefone(telefone);
        }

        try {
            if (dataCadastro.equals("") || dataCadastro.isEmpty()) {
                request.setAttribute("msg", "Informe a data de cadastro do cliente");
                despacharRequisicao(request, response);
            } else {
                cliente.setDataCadastro(df.parse(dataCadastro));
            }
        } catch (ParseException pe) {
            mensagem = "Erro: " + pe.getMessage();
        }

        if (status.equals("") || status.isEmpty()) {
            request.setAttribute("msg", "Informe o status do usuário!");
            despacharRequisicao(request, response);
        } else {
            try {
                cliente.setStatus(Integer.parseInt("status"));
            } catch (NumberFormatException e) {
                mensagem = "Erro: " + e.getMessage();
                e.printStackTrace();
            }

        }

        try {

            if (clienteDAO.gravar(cliente)) {
                mensagem = "Cliente gravado com sucesso na base de dados!";
            } else {
                mensagem = "Falha ao gravar o cliente na base de dados!";
            }

        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='gerenciarCliente?acao=listar';"
                + "</script>"
        );

    }

    private void despacharRequisicao(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().
                getRequestDispatcher("/cadastrarCliente.jsp").
                forward(request, response);

    }
}
