package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
        public java.sql.Connection connection;

        public ContatoDAO() throws SQLException {
                this.connection = DB.getConnection();
        }

//METODO RESPONSAVEL POR INSERIR DADOS NA TABELA CADASTRO
        public void adiciona(CadastroPojo cadast) throws SQLException {
                // prepared statement para inserção
                PreparedStatement stmt = (PreparedStatement) ((java.sql.Connection) this.connection)
                                .prepareStatement("insert into clientes"
                                                + "(codigo, nome, cep, Logradouro , numero, complemento , bairro , cidade , uf   ) "
                                                + "values (?, ?, ?, ?  , ? , ?, ? ,? , ?  )");
            
        //System.out.println("Bairro  : "+ cadast.getBairro());
        
                stmt.setInt(1, cadast.getCodigo());
                stmt.setString(2, cadast.getNome()); 
                stmt.setString(3, cadast.getCep());
                stmt.setString(4, cadast.getLogradouro());
                stmt.setString(5, cadast.getNumero());
                stmt.setString(6, cadast.getComplemento());
                stmt.setString(7, cadast.getBairro());
                stmt.setString(8, cadast.getCidade());
                stmt.setString(9, cadast.getUF());
                stmt.execute();    
                stmt.close();
        }

        // METODO RESPONSAVEL POR ALTERAR DADOS NA TABELA CADASTRO
        public void alterar(CadastroPojo cadast) throws SQLException {
                // prepared statement para inserção
                PreparedStatement sql = this.connection.prepareStatement(
                                "update clientes set nome ='" + cadast.getNome() + "' where codigo = " + cadast.getCodigo());
                sql.executeUpdate();
        }

        // METODO RESPONSAVEL POR DELETAR DADOS NA TABELA CADASTRO
        public void deletar(CadastroPojo cadast) throws SQLException {
                PreparedStatement sql = this.connection
                                .prepareStatement("delete from clientes where codigo = " + cadast.getCodigo());
                sql.executeUpdate();
        }

        public List selecionar(CadastroPojo cadast) throws SQLException {
                PreparedStatement sql = this.connection
                                .prepareStatement("select nome from clientes where codigo = " + cadast.getCodigo());

                ResultSet rs = sql.executeQuery();

                // CRIA OBJETO LISTACONTATO DO TIPO LIST RESPONSAVEL POR ARMAZENAR TODOS OS
                // REGISTRO QUE FOREM SELECIONADOS NO CASO 1 SO
                List<CadastroPojo> listacontato = new ArrayList<CadastroPojo>();
                while (rs.next()) {
                        // cria objeto temporario cadastro
                        CadastroPojo cadastro = new CadastroPojo();
                        // instancia o objeto
                        cadastro.setNome(rs.getString("nome"));
                        // insere dados na lista
                        listacontato.add(cadastro);
                }
                // fecha recordset
                rs.close();
                // fecha statement
                sql.close();
                return listacontato;
        }


        public Integer  selCod() throws SQLException {
                PreparedStatement sql = this.connection
                                .prepareStatement("select  ( nextcod + 1 ) codigo  from ind_codCliente order by 1 ");

                ResultSet rs = sql.executeQuery();
                Integer srs = null  ; 
                while (rs.next()) {
                        srs = rs.getInt(1);

                }
                return  srs  ; 

        } 

        public void insCod(Integer codCli) throws SQLException {
                PreparedStatement sql = this.connection.prepareStatement(" update  ind_codCliente set nextcod = " + codCli);

                sql.executeUpdate();
        }






}