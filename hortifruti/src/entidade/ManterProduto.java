/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

import controle.Produto;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ManterProduto extends DAO{
  public void inserir(Produto a) throws Exception {
    try {
    abrirBanco();
    String query = "INSERT INTO produto(nome,valor) "
            + "values(?,?)";
    pst=(PreparedStatement) con.prepareStatement(query);
    pst.setString(1, a.getNome());
    pst.setDouble(2,a.getValor());
    pst.execute();
    fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }  
}
  //metodo deletar aluno		
 public void deletarProduto(Produto a) throws Exception{
	 abrirBanco();
	 String query = "delete from produto where id_produto=?";
	 pst=(PreparedStatement) con.prepareStatement(query);
	 pst.setInt(1, a.getId_produto());
	 pst.execute();
        JOptionPane.showMessageDialog(null, "produto deletado com sucesso!");
	fecharBanco();
     }
  //listando Alunos
             public ArrayList<Produto> PesquisarTudo () throws Exception {
       ArrayList<Produto> Produto = new ArrayList<Produto>();
         try{
         abrirBanco();  
         String query = "select * FROM produto";
         pst = (PreparedStatement) con.prepareStatement(query);
         ResultSet tr = pst.executeQuery();
         Produto a ;
         while (tr.next()){               
           a = new Produto();
           a.setId_produto(tr.getInt("id_produto"));
           a.setNome(tr.getString("nome"));
           a.setValor(tr.getDouble("valor"));
           Produto.add(a);
         } 
         fecharBanco();
       }catch (Exception e){
           System.out.println("Erro " + e.getMessage());
     } 
       return Produto;
     }
            
            public void PesquisarRegistro(Produto a) throws Exception {
        try {
            abrirBanco();
            String query = "select * FROM produto where id_produto=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, a.getId_produto());
            ResultSet tr = pst.executeQuery();
            if (tr.next()) {
                a.setId_produto(tr.getInt("id_produto"));
                a.setNome(tr.getString("nome"));
                a.setValor(tr.getDouble("valor"));
            } else {
              //  JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado! ");
            }
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }    
            
            public void editarProduto(Produto a) throws Exception {
        abrirBanco();
        //JOptionPane.showMessageDialog(null, a.getNome()+ a.getValor());
        String query = "UPDATE produto set nome = ?,valor = ? where id_produto=?";
        pst = (PreparedStatement) con.prepareStatement(query);
        pst.setString(1, a.getNome());
        pst.setDouble(2, a.getValor());
        pst.setInt(3, a.getId_produto());
        pst.executeUpdate();
        fecharBanco();
    }
}