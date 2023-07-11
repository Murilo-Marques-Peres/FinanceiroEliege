package View;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import DAO.SaidaDAO;
import DAO.ServicoDAO;
import DTO.SaidaDTO;
import DTO.ServicoDTO;

public class Contabilidade implements ActionListener{
    JFrame frame1 = new JFrame();
    JScrollPane scroll;
    JTable table1 = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio","Junho", "Julho", "Agosto",
    "Setembro","Outubro", "Novembro", "Dezembro"};
    JComboBox<String> caixaMeses = new JComboBox<String>(meses);
    JPanel painelInserirDados = new JPanel();
    JPanel painelLabel = new JPanel();
    //JPanel painelTotal = new JPanel();
    JPanel painelGridEditar = new JPanel();
    JLabel labelMarcaProduto = new JLabel("Marca do Produto:");
    JLabel labelValorProduto = new JLabel("Valor:                   R$");
    JLabel labelData = new JLabel("Data:");
    JLabel labelTotal = new JLabel("Total do Mês: R$");
    //JTextField campoTotal = new JTextField();
    JTextField campoInserirMarcaProduto = new JTextField();
    JTextField campoInserirValor = new JTextField();
    JTextField campoDataDia = new JTextField();
    JTextField campoDataMes = new JTextField();
    JTextField campoDataAno = new JTextField();
    JTextField campoRemoverId = new JTextField();
    ArrayList<SaidaDTO> lista;
    
    JButton botaoSomaTotal = new JButton("Somatório de Mês");
    JButton botaoEditar = new JButton("Add/Remover");
    JButton botaoVoltar = new JButton("Voltar");
    JButton botaoInserirInvestimento = new JButton("Inserir");
    JButton botaoAdicionar = new JButton("+");
    JButton botaoRemover = new JButton("-");

    SaidaDAO saidaDAO = new SaidaDAO();

    Date dataFormatada;

    boolean confirmacaoRepeticao = false;
    boolean confirmacaoRepeticao2 = false;
    boolean primeiraVez = true;
    int referenciaListaAnterior2;
    int referenciaListaAnterior;
    int numeroListaFiltrado = 0;


    public void mudarMes(){
        try {
            if(lista.size()>0 && primeiraVez){
                int numeroListaFiltrado = lista.size();
                for(int x3=0;x3<numeroListaFiltrado;x3++){
                    model.removeRow(0);
                }
                primeiraVez = false;
            }
            numeroListaFiltrado = 0;
            SaidaDAO DAO = new SaidaDAO();
            ArrayList<SaidaDTO> lista2 = DAO.selectTudoSaida();
            if(confirmacaoRepeticao2){
                for(int y=0; y<referenciaListaAnterior2;y++){
                    model.removeRow(0);
                }
            }
            for(int x=0; x<lista2.size(); x++){
                String dataString = (funcaoFormatarData(lista2.get(x).getData())).toString();
                String substringDataMes = dataString.substring(3,5);
                int numeroCaixa = caixaMeses.getSelectedIndex() + 1;
                String stringCaixa = String.valueOf(numeroCaixa);
                if(substringDataMes.substring(0,1).equals("0")){
                    substringDataMes = substringDataMes.substring(1, 2);
                }
                System.out.println("Substring = " + substringDataMes + " numeroCaixa = " + numeroCaixa);
                if(substringDataMes.equals(stringCaixa)){
                    numeroListaFiltrado++;
                    model.addRow(new Object[]{
                        lista2.get(x).getId(),
                        lista2.get(x).getMarcaProduto(),
                        lista2.get(x).getValor(),
                        funcaoFormatarData(lista2.get(x).getData()),
                    });
                }
                confirmacaoRepeticao2 = true;
                referenciaListaAnterior2 = numeroListaFiltrado;
                    
                }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"Listar valores: " + erro);
        }
    }



    public void setarLista(){
        try {
            SaidaDAO saidaDAO = new SaidaDAO();
            lista = saidaDAO.selectTudoSaida();
            if(confirmacaoRepeticao){
                for(int y=0; y<referenciaListaAnterior;y++){
                    model.removeRow(0);
                }
            }
            for(int x=0; x<lista.size(); x++){
                model.addRow(new Object[]{
                    lista.get(x).getId(),
                    lista.get(x).getMarcaProduto(),
                    lista.get(x).getValor(),
                    funcaoFormatarData(lista.get(x).getData()),
                });
            }
            confirmacaoRepeticao = true;
            referenciaListaAnterior = lista.size();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"Listar valores: " + erro);
        }
    }

    public String funcaoFormatarData(Date data){
        String dataString1 = data.toString();
        String dataStringAno = dataString1.substring(0,4);
        String dataStringMes = dataString1.substring(5,7);
        String dataStringDia = dataString1.substring(8,10);

        String dataReformatada = dataStringDia.concat("-").concat(dataStringMes).concat("-").concat(dataStringAno);

        return dataReformatada;
    } 
    public void gridLayoutEditar(){
        painelGridEditar.setLayout(new GridLayout(1,2));
        painelGridEditar.add(botaoAdicionar);
        painelGridEditar.add(botaoRemover);
       // painelGrid.add(botaoEditar2);        

    }

    public void tableMethod(){
        Object[] colunas = {"ID", "Marca do Produto", "Valor", "Data"};
        model.setColumnIdentifiers(colunas);
        table1.setModel(model);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);

        table1.setRowHeight(30);
        table1.getTableHeader().setFont(new Font("Comic Sans", Font.PLAIN, 18));
        table1.setFont(new Font("Comic Sans", Font.PLAIN, 16));

        TableColumnModel columnModel = table1.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(rightRenderer);
        columnModel.getColumn(1).setCellRenderer(rightRenderer);
        columnModel.getColumn(2).setCellRenderer(rightRenderer);
        columnModel.getColumn(3).setCellRenderer(rightRenderer);

        columnModel.getColumn(0).setPreferredWidth(55);
        columnModel.getColumn(1).setPreferredWidth(350);
        columnModel.getColumn(2).setPreferredWidth(80);

        scroll = new JScrollPane(table1);

    }

    public void funcaoGridInserirCampos(){
        painelInserirDados.setLayout(new GridLayout(2,1));
        painelInserirDados.add(campoInserirMarcaProduto);
        painelInserirDados.add(campoInserirValor);
    }
    public void funcaoInserirGridLabel(){
        painelLabel.setLayout(new GridLayout(2, 1));
        painelLabel.add(labelMarcaProduto);
        painelLabel.add(labelValorProduto);
    }
    public void funcaoGridTotal(){
        //painelTotal.setLayout(new GridLayout(1, 2));
        //painelTotal.add(labelTotal);
        //painelTotal.add(campoTotal);
    }
    
    
    public void funcaoFinalFrame(){
        frame1.setSize(1500,1000);
        frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame1.setLayout(null);
        frame1.setVisible(true);

    }
    public void componentsMethod(){
        painelInserirDados.setBounds(650, 350, 300, 80);
        painelLabel.setBounds(500,350,300,80);
        //painelTotal.setBounds(320,320,400,35);
        painelGridEditar.setBounds(650,150,300,80);

        scroll.setBounds(650,80,650,500);

        caixaMeses.setBounds(45,250,200,40);
        caixaMeses.addActionListener(this);

        labelMarcaProduto.setFont(new Font("Comic Sans", Font.BOLD, 16));
        labelValorProduto.setFont(new Font("Comic Sans", Font.BOLD, 16));
        labelData.setFont(new Font("Comic Sans", Font.BOLD, 16));
        labelData.setBounds(500,440,100,30);
        labelTotal.setFont(new Font("Comic Sans", Font.BOLD, 16));
        labelTotal.setHorizontalAlignment(JLabel.RIGHT);


        //campoTotal.setEditable(false);
        //campoTotal.setFont(new Font("Comic Sans", Font.BOLD, 16));
        //campoTotal.setText("N.NN");
        campoInserirMarcaProduto.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoInserirValor.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoDataDia.setBounds(650,440,30,30);
        campoDataDia.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoDataMes.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoDataMes.setBounds(685, 440,30, 30);
        campoDataAno.setBounds(720,440,60,30);
        campoDataAno.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoRemoverId.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoRemoverId.setHorizontalAlignment(JTextField.CENTER);
        campoRemoverId.setBounds(700,350,40,30);

        botaoVoltar.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoVoltar.setBounds(0,0, 300, 50);
        botaoVoltar.addActionListener(this);
        botaoInserirInvestimento.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoInserirInvestimento.setBounds(500,600, 300, 50);
        botaoInserirInvestimento.addActionListener(this);
        botaoSomaTotal.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoSomaTotal.setBounds(0,55, 300, 50);
        botaoSomaTotal.addActionListener(this);
        botaoEditar.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoEditar.setBounds(0,110, 300, 50);
        botaoEditar.addActionListener(this);
        botaoAdicionar.setFont(new Font("Comic Sans", Font.BOLD, 35));
        botaoAdicionar.addActionListener(this);
        botaoRemover.setFont(new Font("Comic Sans", Font.BOLD, 50));
        botaoRemover.addActionListener(this);
        

    }
    public void inserirComponentes(){

        frame1.add(scroll);
        frame1.add(painelInserirDados);
        frame1.add(painelLabel);
        //frame1.add(painelTotal);
        frame1.add(painelGridEditar);
        frame1.add(caixaMeses);
        frame1.add(labelData);
        frame1.add(campoDataDia);
        frame1.add(campoDataMes);
        frame1.add(campoDataAno);
        frame1.add(campoRemoverId);
        frame1.add(botaoVoltar);
        frame1.add(botaoSomaTotal);
        frame1.add(botaoEditar);
        frame1.add(botaoInserirInvestimento);
    }
    public void setarDefaultFalse(){
        painelInserirDados.setVisible(false);
        painelLabel.setVisible(false);
        //painelTotal.setVisible(false);
        labelData.setVisible(false);
        campoDataDia.setVisible(false);
        campoDataMes.setVisible(false);
        campoDataAno.setVisible(false);
        campoRemoverId.setVisible(false);
        scroll.setVisible(false);
        botaoInserirInvestimento.setVisible(false);
        painelGridEditar.setVisible(false);

    }

    public Contabilidade(){
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        funcaoGridInserirCampos();
        funcaoInserirGridLabel();
        funcaoGridTotal();
        gridLayoutEditar();
        tableMethod();
        componentsMethod();
        inserirComponentes();
        funcaoFinalFrame();
        setarDefaultFalse();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botaoVoltar){
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            frame1.dispose();
        }
        if(e.getSource() == botaoAdicionar){
            painelInserirDados.setVisible(true);
            painelLabel.setVisible(true);
            campoDataDia.setVisible(true);
            campoDataMes.setVisible(true);
            campoDataAno.setVisible(true);
            labelData.setVisible(true);
            botaoInserirInvestimento.setVisible(true);
            
            campoRemoverId.setVisible(false);
            //painelTotal.setVisible(false);
            scroll.setVisible(false);
        }
        if(e.getSource() == botaoEditar){
            painelGridEditar.setVisible(true);
            //painelTotal.setVisible(false);
            scroll.setVisible(false);
            campoRemoverId.setVisible(false);

        }
        if(e.getSource() == botaoSomaTotal){
            setarLista();



            scroll.setVisible(true);
            //ainelTotal.setVisible(true);

            painelInserirDados.setVisible(false);
            painelLabel.setVisible(false);
            labelData.setVisible(false);
            campoDataDia.setVisible(false);
            campoDataMes.setVisible(false);
            campoDataAno.setVisible(false);
            botaoInserirInvestimento.setVisible(false);
            painelGridEditar.setVisible(false);
        }
        if(e.getSource() == botaoInserirInvestimento){
            String marcaString = campoInserirMarcaProduto.getText();
            SaidaDAO saidaDAO = new SaidaDAO();
            
            String valorString = campoInserirValor.getText();
            String dataDiaString = campoDataDia.getText();
            String dataMesString = campoDataMes.getText();
            String dataAnoString = campoDataAno.getText();

            Double valorDouble = Double.valueOf(valorString);

            String dataCompleta = dataAnoString.concat("-").concat(dataMesString).concat("-").
            concat(dataDiaString);
            System.out.println(dataCompleta);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            try {
                dataFormatada = formato.parse(dataCompleta);
                
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            SaidaDTO saidaDTO = new SaidaDTO();
            saidaDTO.setMarcaProduto(marcaString);
            saidaDTO.setValor(valorDouble);
            saidaDTO.setData(dataFormatada);

            saidaDAO.addSaida(saidaDTO);
        }
        if(e.getSource() == caixaMeses){
            mudarMes();

        }
        if(e.getSource() == botaoRemover){
            campoRemoverId.setVisible(true);
        }
    }
}
