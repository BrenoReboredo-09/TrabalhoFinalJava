/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

import controle.Vendas;
import controle.Produto;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author rebor
 */
public class ManterVendas extends DAO{
  public void inserir(Vendas a) throws Exception {
    try {
    abrirBanco();
    String query = "INSERT INTO vendas(fk_for_nomep,fk_for_nomec) "
            + "values(?,?)";
    pst=(PreparedStatement) con.prepareStatement(query);
    pst.setString(1, a.getFk_for_nomep());
    pst.setString(2,a.getFk_for_nomec());
 
    pst.execute();
    fecharBanco();
    } catch (Exception e) {
        System.out.println("Erro " + e.getMessage());
    }  
}
  public ArrayList<Vendas> PesquisarTudo () throws Exception {
       ArrayList<Vendas> cliente = new ArrayList<Vendas>();
         try{
         abrirBanco();  
         String query = "select v.id_vendas, p.nome, c.nome from vendas as v join produto as p on(v.fk_for_nomep = p.nome) join cliente as c on(v.fk_for_nomec = c.nome);";
         pst = (PreparedStatement) con.prepareStatement(query);
         ResultSet tr = pst.executeQuery();
         Vendas v ;
         while (tr.next()){               
           v = new Vendas();
           v.setId_vendas(tr.getInt("id_vendas"));
           v.setFk_for_nomep(tr.getString("p.nome"));  
           v.setFk_for_nomec(tr.getString("c.nome"));           
           cliente.add(v);
         } 
         fecharBanco();
       }catch (Exception e){
           System.out.println("Erro " + e.getMessage());
     } 
       return cliente;
     }
}