package View;

import java.awt.Color;
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

import DAO.ServicoDAO;
import DTO.ServicoDTO;

public class Interface1 implements ActionListener{
    JFrame frame1 = new JFrame();
    JTable table1 = new JTable();
    String[] opcoesCaixaDevendo = {"Sim", "Não"};
    JComboBox caixaDevendo = new JComboBox<String>(opcoesCaixaDevendo);
    JScrollPane scroll;
    JPanel painelGrid = new JPanel();
    JPanel painelGridEditar = new JPanel();
    JPanel painelLabel = new JPanel();
    JPanel painelfundo1 = new JPanel();
    JLabel labelMes = new JLabel("Mês");
    JLabel labelCliente = new JLabel("Nome Cliente:  ");
    JLabel labelServico = new JLabel("Serviço:  ");
    JLabel labelValor = new JLabel("Valor:          R$");
    JLabel labelInserir = new JLabel("Inserir Cliente:");
    JLabel labelInserirDevendo = new JLabel("Devendo:  ");
    JLabel labelData = new JLabel("Data: ");
    JTextField campoInserirCliente = new JTextField();
    JTextField campoInserirServico = new JTextField();
    JTextField campoInserirValor = new JTextField();
    JTextField campoDataDia = new JTextField();
    JTextField campoDataMes = new JTextField();
    JTextField campoDataAno = new JTextField();

    Date dataFormatada;


    DefaultTableModel model = new DefaultTableModel();
    JButton botaoVer = new JButton("Visualizar");
    JButton botaoEditar = new JButton("Editar/Add/Remover");
    JButton botaoAdicionar = new JButton("+");
    JButton botaoRemover = new JButton("-");
    JButton botaoEditar2 = new JButton("Editar");
    JButton botaoAdicionarCliente = new JButton("ADD");

    boolean confirmacaoRepeticao = false;
    int referenciaListaAnterior;

    public void setarLista(){
        try {
            ServicoDAO DAO = new ServicoDAO();
            ArrayList<ServicoDTO> lista = DAO.pesquisarClientes();
            if(confirmacaoRepeticao = true){
                for(int y=0; y<referenciaListaAnterior;y++){
                    model.removeRow(1);
                }
            }
            for(int x=0; x<lista.size(); x++){
                model.addRow(new Object[]{
                    lista.get(x).getId(),
                    lista.get(x).getCliente(),
                    lista.get(x).getNomeServico(),
                    lista.get(x).getValor(),
                    lista.get(x).getDataServico(),
                    lista.get(x).getDevendo(),
                });
            }
            confirmacaoRepeticao = true;
            referenciaListaAnterior = lista.size();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"Listar valores: " + erro);
        }
    }


    public void gridLayoutMethod1(){
        painelGrid.setLayout(new GridLayout(1,3));
        painelGrid.add(botaoAdicionar);
        painelGrid.add(botaoRemover);
        painelGrid.add(botaoEditar2);        

    }
    public void gridLayoutMethod2(){
        painelGridEditar.setLayout(new GridLayout(4, 1));
        painelGridEditar.add(campoInserirCliente);
        painelGridEditar.add(campoInserirServico);
        painelGridEditar.add(campoInserirValor);
        painelGridEditar.add(caixaDevendo);

    }
    public void gridLayoutMethodLabel(){
        painelLabel.setLayout(new GridLayout(4,1));
        painelLabel.add(labelCliente);
        painelLabel.add(labelServico);
        painelLabel.add(labelValor);
        painelLabel.add(labelInserirDevendo);

        
    }
    public void endFrameMethod(){
        frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame1.setSize(1500, 1000);
        frame1.setLayout(null);
        frame1.setVisible(true);

    }
    public void tableMethod(){
        Object[] colunas = {"ID", "Cliente", "Serviço", "Valor", "data", "Devendo"};
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
        columnModel.getColumn(4).setCellRenderer(rightRenderer);
        columnModel.getColumn(5).setCellRenderer(rightRenderer);

        columnModel.getColumn(0).setPreferredWidth(45);
        columnModel.getColumn(1).setPreferredWidth(250);
        columnModel.getColumn(2).setPreferredWidth(250);

        scroll = new JScrollPane(table1);

    }
    
    public void componentsMethod(){
        painelGrid.setBounds(600,20,300,40);
        painelGridEditar.setBounds(650,350,300,120);
        painelfundo1.setBounds(350,320,650,170);
        painelLabel.setBounds(520,350,300,120);

        
        scroll.setBounds(350,80,1000,500);
        labelMes.setBounds(350,30,1000,30);
        labelMes.setFont(new Font("Comic Sans", Font.BOLD, 24));   
        labelMes.setHorizontalAlignment(JLabel.CENTER);
        labelCliente.setHorizontalAlignment(JLabel.LEFT);
        labelCliente.setFont(new Font("Comic Sans", Font.BOLD, 18));
        labelServico.setHorizontalAlignment(JLabel.LEFT);
        labelServico.setFont(new Font("Comic Sans", Font.BOLD, 18));
        labelValor.setHorizontalAlignment(JLabel.LEFT);
        labelValor.setFont(new Font("Comic Sans", Font.BOLD, 18));
        labelInserir.setBounds(330 ,282, 650,30);
        labelInserir.setFont(new Font("Comic Sans", Font.BOLD, 18));
        labelInserir.setHorizontalAlignment(JLabel.CENTER);
        labelInserirDevendo.setFont(new Font("Comic Sans", Font.BOLD, 18));
        labelInserirDevendo.setHorizontalAlignment(JLabel.LEFT);
        labelData.setBounds(520, 480,100,30);
        labelData.setFont(new Font("Comic Sans", Font.BOLD, 18));
        
        
        campoInserirCliente.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoInserirServico.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoInserirValor.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoDataDia.setBounds(650,480,30,30);
        campoDataDia.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoDataMes.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoDataMes.setBounds(685, 480,30, 30);
        campoDataAno.setBounds(720,480,60,30);
        campoDataAno.setFont(new Font("Comic Sans", Font.BOLD, 16));
        botaoVer.setBounds(0,0,300,50);
        botaoVer.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoVer.addActionListener(this);
        botaoEditar.setBounds(0,55,300,50);
        botaoEditar.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoEditar.addActionListener(this);
        botaoAdicionar.setFont(new Font("Comic Sans", Font.BOLD, 35));
        botaoAdicionar.addActionListener(this);
        botaoRemover.setFont(new Font("Comic Sans", Font.BOLD, 50));
        botaoRemover.addActionListener(this);
        botaoEditar2.setFont(new Font("Comic Sans", Font.BOLD, 20));
        botaoEditar2.addActionListener(this);
        botaoAdicionarCliente.setBounds(600, 560, 200, 35);
        botaoAdicionarCliente.setFont(new Font("Comic Sans", Font.BOLD, 18));
        botaoAdicionarCliente.addActionListener(this);
        
    }
    public void positionMethod(){
        frame1.add(painelGrid);
        frame1.add(painelGridEditar);
        frame1.add(painelfundo1);
        frame1.add(painelLabel);
        frame1.add(labelData);
        frame1.add(scroll);
        frame1.add(campoDataDia);
        frame1.add(campoDataMes);
        frame1.add(campoDataAno);
        frame1.add(labelMes);
        frame1.add(labelMes);
        frame1.add(botaoVer);
        frame1.add(botaoEditar);
        frame1.add(botaoAdicionarCliente);
        frame1.add(labelInserir);
        
    }
    public void setarDefaultFalse(){
        scroll.setVisible(false);
        labelMes.setVisible(false);
        painelGrid.setVisible(false);
        painelGridEditar.setVisible(false);
        labelInserir.setVisible(false);
        painelfundo1.setVisible(false);
        painelLabel.setVisible(false);
        labelData.setVisible(false);
        campoDataDia.setVisible(false);
        campoDataMes.setVisible(false);
        campoDataAno.setVisible(false);
        botaoAdicionarCliente.setVisible(false);
    }

    public Interface1(){
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableMethod();
        gridLayoutMethod1();
        gridLayoutMethod2();
        gridLayoutMethodLabel();
        componentsMethod();
        positionMethod();
        setarDefaultFalse();

        endFrameMethod();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botaoVer){
            painelGrid.setVisible(false);
            painelGridEditar.setVisible(false);
            labelInserir.setVisible(false);
            painelfundo1.setVisible(false);
            painelLabel.setVisible(false);
            labelData.setVisible(false);
            campoDataDia.setVisible(false);
            campoDataMes.setVisible(false);
            campoDataAno.setVisible(false);
            botaoAdicionarCliente.setVisible(false);

            scroll.setVisible(true);
            labelMes.setVisible(true);

            setarLista();

        }
        if(e.getSource() == botaoEditar){
            scroll.setVisible(false);
            labelMes.setVisible(false);
            painelGridEditar.setVisible(false);
            labelInserir.setVisible(false);
            painelfundo1.setVisible(false);
            painelLabel.setVisible(false);
            labelData.setVisible(false);
            labelData.setVisible(false);
            campoDataDia.setVisible(false);
            campoDataMes.setVisible(false);
            campoDataAno.setVisible(false);
            botaoAdicionarCliente.setVisible(false);
            
            painelGrid.setVisible(true);
        }
        if(e.getSource() == botaoAdicionar){
            painelGridEditar.setVisible(true);
            labelInserir.setVisible(true);
            painelLabel.setVisible(true);
            labelData.setVisible(true);
            campoDataDia.setVisible(true);
            campoDataMes.setVisible(true);
            campoDataAno.setVisible(true);
            botaoAdicionarCliente.setVisible(true);
            
        }
        if(e.getSource() == botaoAdicionarCliente){
            String cliente1 = campoInserirCliente.getText();
            String servico1 = campoInserirServico.getText();
            String valor1 = campoInserirValor.getText();
            String devendo = caixaDevendo.getSelectedItem().toString();

            Double valorDouble = Double.valueOf(valor1);

            String dataDia = campoDataDia.getText();
            String dataMes = campoDataMes.getText();
            String dataAno = campoDataAno.getText();
            String dataCompleta = dataAno.concat("-").concat(dataMes).concat("-").concat(dataDia);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dataFormatada = formato.parse(dataCompleta);
                
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            ServicoDTO servicoDTO = new ServicoDTO();
            servicoDTO.setCliente(cliente1);
            servicoDTO.setNomeServico(servico1);
            servicoDTO.setValor(valorDouble);
            servicoDTO.setDevendo(devendo);
            servicoDTO.setDataServico(dataFormatada);

            ServicoDAO DAO = new ServicoDAO();

            DAO.addClienteDB(servicoDTO);

        }
    }
}
