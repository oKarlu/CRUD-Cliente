<%@ page language="java" contentType="text/html; charset=UTF-8" 
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0,
              shrink-to-fit=no">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="fonts/css/all.css" type="text/css">
        <link rel="stylesheet" href="css/menu.css" type="text/css">
        <link rel="stylesheet" href="css/styles.css" type="text/css">
        <title>Cadastro de Usuario</title>
    </head>
    <body>
        <% 
            String msg = (String) request.getAttribute("msg");
            if(msg != null){
                out.println(
                    "<script type='text/javascript'>" +
                    "alert('" + msg + "');" +
                     "</script>");    
            }
        
        %>
        <div id="container">
            
            <div id="header">
                <jsp:include page="template/banner.jsp"></jsp:include>
            </div>
            <div id="menu">
                <jsp:include page="template/menu.jsp"></jsp:include>
            </div>
            <div id="conteudo" class="bg-background">
                <form action="gerenciarCliente" method="POST" 
                      accept-charset="iso-8859-1,utf-8">
                    <h3 class="text-center mt-5">Cadastro de Cliente</h3>
                    
                    <input type="hidden" id="idcliente" name="idCliente" 
                           value="${cliente.idCliente}">
                    
                    <div class="form-group row offset-md-3 mt-4">
                        <label for="idnome" 
                               class="col-md-1 form-label btn btn-primary btn-md">Nome</label>
                        <div class="col-md-6">
                            <input type="text" name="nome" id="idnome" 
                                   class="form-control" value="${cliente.nome}">
                            
                        </div>
                    </div>
                    <div class="form-group row offset-md-2 mt-3">
                        <label for="iddata" 
                               class="col-md-2 form-label btn btn-primary btn-md">Data de Cadastro</label>
                        <div class="col-md-6">
                            <input type="date" name="dataCadastro" id="iddata" 
                                   class="form-control" value="${cliente.dataCadastro}">
                            
                        </div>
                    </div>
                    <div class="form-group row offset-md-3 mt-4">
                        <label for="idendereco" 
                            class="col-md-1 form-label btn btn-primary btn-md">Endereco</label>
                        <div class="col-md-6">
                            <input type="text" name="endereco" id="idendereco" 
                                   class="form-control" value="${cliente.endereco}">
                            
                        </div>
                    </div>
                    <div class="form-group row offset-md-3 mt-4">
                        <label for="idemail" 
                            class="col-md-1 form-label btn btn-primary btn-md">Email</label>
                        <div class="col-md-6">
                            <input type="text" name="email" id="idemail" 
                                   class="form-control" value="${cliente.email}">
                            
                        </div>
                    </div>
                    <div class="form-group row offset-md-3 mt-4">
                        <label for="idtelefone" 
                            class="col-md-1 form-label btn btn-primary btn-md">Telefone</label>
                        <div class="col-md-6">
                            <input type="text" name="telefone" id="idtelefone" 
                                   class="form-control" value="${cliente.telefone}">
                            
                        </div>
                    </div>
                    <div class="form-group row offset-md-3 mt-3">
                        <label for="idstatus" class="col-md-1 form-label btn btn-primary btn-md mt-2">Status</label>
                        <div class="col-md-6">
                            <select id="idstatus" name="status"
                                class="form-control-sm mt-2">
                                <option value="" selected>
                                    Escolha uma opção
                                </option>
                                <c:choose>
                                    <c:when test="${cliente.status == 1}">
                                        <option value="${cliente.status}" selected>
                                            Ativado
                                        </option>
                                        <option value="0">
                                            Desativado
                                        </option>
                                        
                                    </c:when>
                                    <c:otherwise>
                                        <option value="1">
                                            Ativado
                                        </option>
                                        <option value="${cliente.status == 0}" selected>
                                            Desativado
                                        </option>
                                        
                                    </c:otherwise>
                                
                                </c:choose>
                                
                                
                            </select>
                            
                        </div>
                    </div>
                    
                    <div class="d-md-flex justify-content-md-end mr-3">
                        <button  class="btn btn-primary btn-md mr-2">
                            Gravar&nbsp;
                            <i class="fa-solid fa-floppy-disk"></i>
                        </button>
                        <a href="gerenciarCliente?acao=listar"
                           class="btn btn-warning btn-md" role="button">
                            Voltar&nbsp;<i class="fa-solid fa-rotate-left"></i>
                            
                        </a>
                        
                    </div>
                    
                    
                    
                </form>
                
            </div>
            
       </div>
       
        <!--JQuery.js -->
        <script src="js/jquery.min.js"></script>
        <!--Popper.js via cdn -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha512-Ua/7Woz9L5O0cwB/aYexmgoaD7lw3dWe9FvXejVdgqu71gRog3oJgjSWQR55fwWx+WKuk8cl7UwA1RS6QCadFA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- Bootstrap.js -->
        <script src="js/bootstrap.min.js"></script>
        
    </body>
</html>
