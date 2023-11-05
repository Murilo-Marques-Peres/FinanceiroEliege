package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DTO.SaidaDTO;

public class SaidaDAO {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<SaidaDTO> lista = new ArrayList<SaidaDTO>();

    public void addSaida(SaidaDTO saidaDTO){
        String sql = "insert into saida (marcaproduto, valor, data) values (?,?,?)";
        conn = new ConexaoDAO().ConectaDB();
        java.sql.Date sqlDate = new java.sql.Date(saidaDTO.getData().getTime());

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, saidaDTO.getMarcaProduto());
            pstm.setDouble(2, saidaDTO.getValor());
            pstm.setDate(3, sqlDate);
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Transação Concluída Com Sucesso");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "SaidaDAO: " + erro);
        }
    }
    public void deleteSaida(SaidaDTO saidaDTO){
        String sql = "delete from saida where id = ?";
        conn = new ConexaoDAO().ConectaDB();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, saidaDTO.getId());
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Dados Removidos Com Sucesso");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "SaidaDAO: " + erro);
        }
    }
    public ArrayList<SaidaDTO> selectTudoSaida(){
        String sql = "select * from saida";
        conn = new ConexaoDAO().ConectaDB();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while(rs.next()){
                SaidaDTO saidaDTO = new SaidaDTO();
                saidaDTO.setId(rs.getInt("id"));
                saidaDTO.setMarcaProduto(rs.getString("marcaproduto"));
                saidaDTO.setValor(rs.getDouble("valor"));
                saidaDTO.setData(rs.getDate("data"));

                lista.add(saidaDTO);
            }
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "SaidaDAO:" + erro);

        }
        return lista;
    }
}
