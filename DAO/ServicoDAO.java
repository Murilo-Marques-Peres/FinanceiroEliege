package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import DTO.ServicoDTO;

public class ServicoDAO {
    Connection conn;
    PreparedStatement pstm;

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
            JOptionPane.showMessageDialog(null, "ServicoDAO" + erro);
        }
    }   
}
