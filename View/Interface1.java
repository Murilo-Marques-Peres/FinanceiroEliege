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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import DAO.ServicoDAO;
import DTO.ServicoDTO;

public class Interface1 implements ActionListener{
    
    
    JFrame frame1 = new JFrame();
    JTable table1 = new JTable();
    ArrayList<ServicoDTO> lista;
    String[] opcoesCaixaDevendo = {"Sim", "Não"};
    JComboBox caixaDevendo = new JComboBox<String>(opcoesCaixaDevendo);
    String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio","Junho", "Julho", "Agosto",
    "Setembro","Outubro", "Novembro", "Dezembro"};
    JComboBox<String> caixaMeses = new JComboBox<String>(meses);
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
    JLabel labelFiltro = new JLabel("Filtro:");
    JLabel labelId = new JLabel("ID");
    JTextArea areaTextoRemover = new JTextArea("Para Remover um Serviço \n   Insira o ID do Serviço");
    JTextField campoInserirCliente = new JTextField();
    JTextField campoInserirServico = new JTextField();
    JTextField campoInserirValor = new JTextField();
    JTextField campoDataDia = new JTextField();
    JTextField campoDataMes = new JTextField();
    JTextField campoDataAno = new JTextField();
    JTextField campoRemoverId = new JTextField();
    
    
    DefaultTableModel model = new DefaultTableModel();
    JButton botaoVoltar = new JButton("Voltar");
    JButton botaoVer = new JButton("Visualizar Serviços");
    JButton botaoEditar = new JButton("Editar/Add/Remover");
    JButton botaoAdicionar = new JButton("+");
    JButton botaoRemover = new JButton("-");
    JButton botaoEditar2 = new JButton("Editar");
    JButton botaoAdicionarCliente = new JButton("ADD");
    JButton botaoRemoverId = new JButton("Remover");
    
    boolean confirmacaoRepeticao = false;
    boolean confirmacaoRepeticao2 = false;
    boolean primeiraVez = true;
    boolean confirmacaoBotaoVer = false;
    int numeroListaFiltrado = 0;

    int referenciaListaAnterior;
    int referenciaListaAnterior2;
    Date dataFormatada;
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");



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
            ServicoDAO DAO = new ServicoDAO();
            ArrayList<ServicoDTO> lista2 = DAO.pesquisarClientes();
            if(confirmacaoRepeticao2){
                for(int y=0; y<referenciaListaAnterior2;y++){
                    model.removeRow(0);
                }
            }
            for(int x=0; x<lista2.size(); x++){
                String dataString = (funcaoFormatarData(lista2.get(x).getDataServico())).toString();
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
                        lista2.get(x).getCliente(),
                        lista2.get(x).getNomeServico(),
                        lista2.get(x).getValor(),
    
                        funcaoFormatarData(lista2.get(x).getDataServico()),
                        
    
                        lista2.get(x).getDevendo(),
                        
                    });
                }
                confirmacaoRepeticao2 = true;
                referenciaListaAnterior2 = numeroListaFiltrado;
                    
                }

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
    public void setarLista(){
        try {
            ServicoDAO DAO = new ServicoDAO();
            lista = DAO.pesquisarClientes();
            if(confirmacaoRepeticao){
                for(int y=0; y<referenciaListaAnterior;y++){
                    model.removeRow(0);
                }
            }
            for(int x=0; x<lista.size(); x++){
                model.addRow(new Object[]{
                    lista.get(x).getId(),
                    lista.get(x).getCliente(),
                    lista.get(x).getNomeServico(),
                    lista.get(x).getValor(),

                    funcaoFormatarData(lista.get(x).getDataServico()),
                    

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
        areaTextoRemover.setBounds(800,150,300,60);
        areaTextoRemover.setFont(new Font("Comic Sans", Font.BOLD, 18));
        labelId.setFont(new Font("Comic Sans", Font.BOLD, 16));
        labelId.setBounds(700,170,40,30);
        labelId.setHorizontalAlignment(JLabel.CENTER);
        
        
        campoInserirCliente.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoInserirServico.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoInserirValor.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoDataDia.setBounds(650,480,30,30);
        campoDataDia.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoDataMes.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoDataMes.setBounds(685, 480,30, 30);
        campoDataAno.setBounds(720,480,60,30);
        campoDataAno.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoRemoverId.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoRemoverId.setHorizontalAlignment(JTextField.CENTER);
        campoRemoverId.setBounds(700,200,40,30);
        botaoVoltar.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoVoltar.setBounds(0,0,300,50);
        botaoVoltar.addActionListener(this);
        botaoRemoverId.setBounds(620,270,200,40);
        botaoRemoverId.setFont(new Font("Comic Sans", Font.BOLD, 18));
        botaoRemoverId.addActionListener(this);
        botaoVer.setBounds(0,55,300,50);
        botaoVer.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoVer.addActionListener(this);
        botaoEditar.setBounds(0, 110,300,50);
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
        caixaMeses.setBounds(45,250,200,40);
        caixaMeses.addActionListener(this);
        labelFiltro.setFont(new Font("Comic Sans", Font.BOLD, 22));
        labelFiltro.setHorizontalAlignment(JLabel.CENTER);
        labelFiltro.setBounds(45,200,200,30);
    }
    public void positionMethod(){
        frame1.add(painelGrid);
        frame1.add(painelGridEditar);
        frame1.add(painelfundo1);
        frame1.add(painelLabel);
        frame1.add(labelData);
        frame1.add(labelId);
        frame1.add(scroll);
        frame1.add(campoDataDia);
        frame1.add(campoDataMes);
        frame1.add(campoDataAno);
        frame1.add(campoRemoverId);
        frame1.add(labelMes);
        frame1.add(labelMes);
        frame1.add(botaoVoltar);
        frame1.add(botaoVer);
        frame1.add(botaoEditar);
        frame1.add(botaoAdicionarCliente);
        frame1.add(botaoRemoverId);
        frame1.add(labelInserir);
        frame1.add(areaTextoRemover);
        frame1.add(caixaMeses);
        frame1.add(labelFiltro);
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
        botaoRemoverId.setVisible(false);
        campoRemoverId.setVisible(false);
        labelId.setVisible(false);
        areaTextoRemover.setVisible(false);
        caixaMeses.setVisible(false);
        labelFiltro.setVisible(false);
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
            if(!confirmacaoBotaoVer){
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
                campoRemoverId.setVisible(false);
                labelId.setVisible(false);
                areaTextoRemover.setVisible(false);
                
                scroll.setVisible(true);
                labelMes.setVisible(true);
                caixaMeses.setVisible(true);
                labelFiltro.setVisible(true);
    
                setarLista();
                
                confirmacaoBotaoVer = true;
            }
            if(confirmacaoBotaoVer){
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
                campoRemoverId.setVisible(false);
                labelId.setVisible(false);
                areaTextoRemover.setVisible(false);

                scroll.setVisible(true);
                labelMes.setVisible(true);
                caixaMeses.setVisible(true);
                labelFiltro.setVisible(true);
            }
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
            botaoRemoverId.setVisible(false);
            campoRemoverId.setVisible(false);
            labelId.setVisible(false);
            areaTextoRemover.setVisible(false);
            
            painelGrid.setVisible(true);
        }
        if(e.getSource() == botaoAdicionar){
            campoRemoverId.setVisible(false);
            labelId.setVisible(false);
            areaTextoRemover.setVisible(false);
            botaoRemoverId.setVisible(false);

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
            

            campoRemoverId.setVisible(false);
            labelId.setVisible(false);
            areaTextoRemover.setVisible(false);
            botaoRemoverId.setVisible(false);
        }
        if(e.getSource() == botaoRemover){

            painelGridEditar.setVisible(false);
            labelInserir.setVisible(false);
            painelLabel.setVisible(false);
            labelData.setVisible(false);
            campoDataDia.setVisible(false);
            campoDataMes.setVisible(false);
            campoDataAno.setVisible(false);
            botaoAdicionarCliente.setVisible(false);

            campoRemoverId.setVisible(true);
            labelId.setVisible(true);
            areaTextoRemover.setVisible(true);
            botaoRemoverId.setVisible(true);

        }
        if(e.getSource() == botaoRemoverId){
            String idString = campoRemoverId.getText();
            int idInt = Integer.valueOf(idString);

            ServicoDTO servicoDTO = new ServicoDTO();
            servicoDTO.setId(idInt);
            
            ServicoDAO dao = new ServicoDAO();
            dao.removerServico(servicoDTO);
        }
        if(e.getSource() == caixaMeses){
            mudarMes();
        }
        if(e.getSource() == botaoVoltar){
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            frame1.dispose();
        }
        
    }
}
