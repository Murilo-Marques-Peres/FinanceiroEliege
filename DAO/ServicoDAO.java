package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import DTO.ServicoDTO;

public class ServicoDAO {
    Date dataFormatada;
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<ServicoDTO> lista = new ArrayList<ServicoDTO>(); 
    ArrayList<ServicoDTO> lista2 = new ArrayList<ServicoDTO>(); 


    public void addClienteDB(ServicoDTO servicoDTO){
        String sql = "insert into servico (cliente, nomeservico, valor, dataservico, devendo) values (?,?,?,?,?)";
        conn = new ConexaoDAO().ConectaDB();
        java.sql.Date sqlDate = new java.sql.Date(servicoDTO.getDataServico().getTime());

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, servicoDTO.getCliente());
            pstm.setString(2, servicoDTO.getNomeServico());
            pstm.setDouble(3, servicoDTO.getValor());
            pstm.setDate(4, sqlDate);
            pstm.setString(5, servicoDTO.getDevendo());
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Cliente Adicionado Com Sucesso!");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ServicoDAO:" + erro);
        }
    }   
    public ArrayList<ServicoDTO> pesquisarClientes(){
        String sql = "select * from servico order by dataservico";
        conn = new ConexaoDAO().ConectaDB();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                ServicoDTO servicoDTO = new ServicoDTO();
                servicoDTO.setId(rs.getInt("id"));
                servicoDTO.setCliente(rs.getString("cliente"));
                servicoDTO.setNomeServico(rs.getString("nomeservico"));
                servicoDTO.setValor(rs.getDouble("valor"));
                servicoDTO.setDevendo(rs.getString("devendo"));
                servicoDTO.setDataServico(rs.getDate("dataservico"));
                

                //String dataString = (rs.getDate("dataservico")).toString();
                //SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                //try {
               // dataFormatada = formato.parse(dataString);
                
               // } catch (ParseException e1) {
               // e1.printStackTrace();
           //   }
            
                lista.add(servicoDTO);

                
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ServicoDAO:" + erro);
        }
        return lista;
    }
    public void removerServico(ServicoDTO servicoDTO){
        String sql = "delete from servico where id = ?";
        conn = new ConexaoDAO().ConectaDB();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, servicoDTO.getId());
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Cliente Removido Com Sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ServicoDAO: " + erro);
        }
    }
    /*public ArrayList<ServicoDTO> listarMes(){
        String sql = "select * from servico";
        conn = new ConexaoDAO().ConectaDB();
        String dataString;
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while(rs.next()){
                ServicoDTO servicoDTO = new ServicoDTO();
                servicoDTO.setId(rs.getInt("id"));
                servicoDTO.setCliente(rs.getString("cliente"));
                servicoDTO.setNomeServico(rs.getString("nomeservico"));
                servicoDTO.setValor(rs.getDouble("valor"));
                servicoDTO.setDevendo(rs.getString("devendo"));
                servicoDTO.setDataServico(rs.getDate("dataservico"));
                lista2.add(servicoDTO);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    return lista2;
    }*/
}
