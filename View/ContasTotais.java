package View;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.SaidaDAO;
import DAO.ServicoDAO;
import DTO.SaidaDTO;
import DTO.ServicoDTO;

public class ContasTotais implements ActionListener{
    JFrame frame1 = new JFrame();
    String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio","Junho", "Julho", "Agosto",
    "Setembro","Outubro", "Novembro", "Dezembro"};
    JComboBox<String> caixaMeses = new JComboBox<String>(meses);
    JLabel labelRecebeu = new JLabel("Recebeu no Total: R$");
    JLabel labelGastou = new JLabel("Gastou no Total:    R$");
    JLabel labelTotal = new JLabel("Somando Tudo:     R$");
    JTextField campoRecebeu = new JTextField();
    JTextField campoGastou = new JTextField();
    JTextField campoTotal = new JTextField();
    JPanel painel1 = new JPanel();
    JPanel painelLabel = new JPanel();
    Double contaTotalRecebeu = 0.0;
    Double contaTotalGastou = 0.0;
    Double contaResultante = 0.0;
    Double contaTotalRecebeuAuxiliar = 0.0;
    Double contaTotalGastouAuxiliar = 0.0;
    Double contaResultanteAuxiliar = 0.0;
    JButton botaoVoltar = new JButton("Voltar");

    

    public void metodoGridLayout1(){
        painel1.setLayout(new GridLayout(3,1));
        painel1.add(campoRecebeu);
        painel1.add(campoGastou);
        painel1.add(campoTotal);
    }
    public void metodoGridLayoutLabel(){
        painelLabel.setLayout(new GridLayout(3,1));
        painelLabel.add(labelRecebeu);
        painelLabel.add(labelGastou);
        painelLabel.add(labelTotal);
    }
    public void frameMethodEnd(){
        frame1.setLayout(null);
        frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame1.setSize(1500,1000);
        frame1.setVisible(true);
    }
    public void metodoComponentes(){
        painel1.setBounds(800,0,300,120);
        painelLabel.setBounds(630,0,300,120);
        labelRecebeu.setFont(new Font("Comic Sans", Font.BOLD, 16));
        labelGastou.setFont(new Font("Comic Sans", Font.BOLD, 16));
        labelTotal.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoRecebeu.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoRecebeu.setEditable(false);
        campoGastou.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoGastou.setEditable(false);
        campoTotal.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoTotal.setEditable(false);
        caixaMeses.addActionListener(this);
        botaoVoltar.setBounds(0,0,300,50);
        botaoVoltar.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoVoltar.addActionListener(this);
        caixaMeses.setBounds(45,250,200,40);
    }
    public void metodoAdicionarComponentes(){
        frame1.add(painel1);
        frame1.add(painelLabel);
        frame1.add(botaoVoltar);
        frame1.add(caixaMeses);
    }
    public String funcaoFormatarData(Date data){
        String dataString1 = data.toString();
        String dataStringAno = dataString1.substring(0,4);
        String dataStringMes = dataString1.substring(5,7);
        String dataStringDia = dataString1.substring(8,10);

        String dataReformatada = dataStringDia.concat("-").concat(dataStringMes).concat("-").concat(dataStringAno);

        return dataReformatada;
    } 
    public void metodoContasRecebeu(){
        ServicoDAO servicoDAO = new ServicoDAO();
        ArrayList<ServicoDTO> listaEntrada = servicoDAO.pesquisarClientes();
        for(ServicoDTO servico:listaEntrada){
            String dataServico = servico.getDataServico().toString();
            String dataAno = dataServico.substring(0,4);
            if(dataAno.equals("2023")){
                int numeroCaixa = caixaMeses.getSelectedIndex() + 1;
                String dataString = (funcaoFormatarData(servico.getDataServico())).toString();
                String substringDataMes = dataString.substring(3,5);
                String stringCaixa = String.valueOf(numeroCaixa);
                if(substringDataMes.substring(0,1).equals("0")){
                    substringDataMes = substringDataMes.substring(1, 2);
                }
                if(substringDataMes.equals(stringCaixa)){
                    contaTotalRecebeu += servico.getValor();
                }         
            }
        }
        String totalRecebidoString = String.valueOf(contaTotalRecebeu);
        campoRecebeu.setText(totalRecebidoString);
        contaTotalRecebeuAuxiliar = contaTotalRecebeu;
        contaTotalRecebeu = 0.0;

        
    }
    public void metodoContasGastou(){
        SaidaDAO saidaDAO = new SaidaDAO();
        ArrayList<SaidaDTO> lista1 = saidaDAO.selectTudoSaida();
        for(SaidaDTO saidaDTO: lista1){
            String dataSaida = saidaDTO.getData().toString();
            String dataAno = dataSaida.substring(0,4);
            int numeroCaixa = caixaMeses.getSelectedIndex() + 1;
            String dataString = (funcaoFormatarData(saidaDTO.getData())).toString();
            String substringDataMes = dataString.substring(3,5);
            String stringCaixa = String.valueOf(numeroCaixa);
            if(substringDataMes.substring(0,1).equals("0")){
                substringDataMes = substringDataMes.substring(1, 2);
            }
            if(substringDataMes.equals(stringCaixa)){
                contaTotalGastou += saidaDTO.getValor();
            }         
        }
        String totalGastou = String.valueOf(contaTotalGastou);
        campoGastou.setText(totalGastou);
        contaTotalGastouAuxiliar = contaTotalGastou;
        contaTotalGastou = 0.0;
    }
    public void metodoResultante(){
        Double resultante = contaTotalRecebeuAuxiliar - contaTotalGastouAuxiliar;
        String stringResultante = String.valueOf(resultante);
        campoTotal.setText(stringResultante);
    }
        
            
           
    
    ContasTotais(){
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        metodoGridLayout1();
        metodoGridLayoutLabel();
        metodoComponentes();
        metodoAdicionarComponentes();
        metodoContasRecebeu();
        metodoContasGastou();
        metodoResultante();

        frameMethodEnd();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botaoVoltar){
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            frame1.dispose();
        }
        if(e.getSource() == caixaMeses){
            metodoContasRecebeu();
            metodoContasGastou();
            metodoResultante();

        }
    }
}
