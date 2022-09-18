package model;

import java.util.Date;

public class Cliente {

    private Long idClinte;
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private Integer status;
    private Date dataCadastro;

    public Cliente(Long idClinte, String nome, String cpf, String endereco, String email, String telefone, Integer status, Date dataCadastro) {
        this.idClinte = idClinte;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.status = status;
        this.dataCadastro = dataCadastro;
    }

    

    public Long getIdClinte() {
        return idClinte;
    }

    public void setIdClinte(Long idClinte) {
        this.idClinte = idClinte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
