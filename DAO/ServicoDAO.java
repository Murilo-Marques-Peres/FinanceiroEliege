package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import DTO.ServicoDTO;

public class ServicoDAO {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<ServicoDTO> lista = new ArrayList<ServicoDTO>(); 

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
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ServicoDAO:" + erro);
        }
    }   
    public ArrayList<ServicoDTO> pesquisarClientes(){
        String sql = "select * from servico";
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
                lista.add(servicoDTO);

                
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ServicoDAO:" + erro);
        }
        return lista;
    }
}
