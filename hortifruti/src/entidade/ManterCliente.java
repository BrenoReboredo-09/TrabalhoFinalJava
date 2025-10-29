/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

import controle.Cliente;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ManterCliente extends DAO{
  public void inserir(Cliente a) throws Exception {
    try {
    abrirBanco();
    String query = "INSERT INTO cliente(nome,telefone) "
            + "values(?,?)";
    pst=(PreparedStatement) con.prepareStatement(query);
    pst.setString(1, a.getNome());
    pst.setString(2,a.getTelefone());
 
    pst.execute();
    fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }  
}
   //metodo deletar aluno		
 public void deletarCliente(Cliente a) throws Exception{
	 abrirBanco();
	 String query = "delete from cliente where id_cliente=?";
	 pst=(PreparedStatement) con.prepareStatement(query);
	 pst.setInt(1, a.getId_cliente());
	 pst.execute();
        JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");
	fecharBanco();
     }
  //listando Alunos
            public ArrayList<Cliente> PesquisarTudo () throws Exception {
       ArrayList<Cliente> cliente = new ArrayList<Cliente>();
         try{
         abrirBanco();  
         String query = "select * FROM cliente";
         pst = (PreparedStatement) con.prepareStatement(query);
         ResultSet tr = pst.executeQuery();
         Cliente a ;
         while (tr.next()){               
           a = new Cliente();
           a.setId_cliente(tr.getInt("id_cliente"));
           a.setNome(tr.getString("nome"));
           a.setTelefone(tr.getString("telefone"));
           cliente.add(a);
         } 
         fecharBanco();
       }catch (Exception e){
           System.out.println("Erro " + e.getMessage());
     } 
       return cliente;
     }
            
            public void PesquisarRegistro(Cliente a) throws Exception {
        try {
            abrirBanco();
            String query = "select * FROM cliente where id_cliente=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, a.getId_cliente());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                a.setId_cliente(tr.getInt("id_cliente"));
                a.setNome(tr.getString("nome"));
                a.setTelefone(tr.getString("telefone"));
            } else {
              //  JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }    
            
            public void editarCliente(Cliente a) throws Exception {
        abrirBanco();
        //JOptionPane.showMessageDialog(null, a.getNome()+ a.getTelefone());
        String query = "UPDATE cliente set nome = ?,telefone = ? where id_cliente=?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setString(1, a.getNome());
        pst.setString(2, a.getTelefone());
        pst.setInt(3, a.getId_cliente());
        pst.executeUpdate();
        fecharBanco();
            }
}