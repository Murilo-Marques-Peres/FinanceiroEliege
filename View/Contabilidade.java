package View;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
    JPanel painelTotal = new JPanel();
    JLabel labelMarcaProduto = new JLabel("Marca do Produto:");
    JLabel labelValorProduto = new JLabel("Valor:                   R$");
    JLabel labelData = new JLabel("Data:");
    JLabel labelTotal = new JLabel("Total do Mês: R$");
    JTextField campoTotal = new JTextField();
    JTextField campoInserirMarcaProduto = new JTextField();
    JTextField campoInserirValor = new JTextField();
    JTextField campoDataDia = new JTextField();
    JTextField campoDataMes = new JTextField();
    JTextField campoDataAno = new JTextField();
    
    JButton botaoSomaTotal = new JButton("Somatório de Mês");
    JButton botaoInserirDados = new JButton("Inserir Investimento");
    JButton botaoVoltar = new JButton("Voltar");
    JButton botaoInserirInvestimento = new JButton("Inserir");

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
        painelTotal.setLayout(new GridLayout(1, 2));
        painelTotal.add(labelTotal);
        painelTotal.add(campoTotal);
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
        painelTotal.setBounds(320,320,400,35);
        scroll.setBounds(650,80,650,500);

        caixaMeses.setBounds(45,250,200,40);

        labelMarcaProduto.setFont(new Font("Comic Sans", Font.BOLD, 16));
        labelValorProduto.setFont(new Font("Comic Sans", Font.BOLD, 16));
        labelData.setFont(new Font("Comic Sans", Font.BOLD, 16));
        labelData.setBounds(500,440,100,30);
        labelTotal.setFont(new Font("Comic Sans", Font.BOLD, 16));
        labelTotal.setHorizontalAlignment(JLabel.RIGHT);


        campoTotal.setEditable(false);
        campoTotal.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoTotal.setText("N.NN");
        campoInserirMarcaProduto.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoInserirValor.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoDataDia.setBounds(650,440,30,30);
        campoDataDia.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoDataMes.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoDataMes.setBounds(685, 440,30, 30);
        campoDataAno.setBounds(720,440,60,30);
        campoDataAno.setFont(new Font("Comic Sans", Font.BOLD, 16));

        botaoVoltar.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoVoltar.setBounds(0,0, 300, 50);
        botaoVoltar.addActionListener(this);

        botaoInserirInvestimento.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoInserirInvestimento.setBounds(500,600, 300, 50);
        botaoInserirInvestimento.addActionListener(this);

        botaoSomaTotal.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoSomaTotal.setBounds(0,55, 300, 50);
        botaoSomaTotal.addActionListener(this);
        
        botaoInserirDados.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoInserirDados.setBounds(0,110, 300, 50);
        botaoInserirDados.addActionListener(this);
        

    }
    public void inserirComponentes(){

        frame1.add(scroll);
        frame1.add(painelInserirDados);
        frame1.add(painelLabel);
        frame1.add(painelTotal);
        frame1.add(caixaMeses);
        frame1.add(labelData);
        frame1.add(campoDataDia);
        frame1.add(campoDataMes);
        frame1.add(campoDataAno);
        frame1.add(botaoVoltar);
        frame1.add(botaoSomaTotal);
        frame1.add(botaoInserirDados);
        frame1.add(botaoInserirInvestimento);
    }
    public void setarDefaultFalse(){
        painelInserirDados.setVisible(false);
        painelLabel.setVisible(false);
        painelTotal.setVisible(false);
        labelData.setVisible(false);
        campoDataDia.setVisible(false);
        campoDataMes.setVisible(false);
        campoDataAno.setVisible(false);
        scroll.setVisible(false);
        botaoInserirInvestimento.setVisible(false);
    }

    public Contabilidade(){
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        funcaoGridInserirCampos();
        funcaoInserirGridLabel();
        funcaoGridTotal();
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
        if(e.getSource() == botaoInserirDados){
            painelInserirDados.setVisible(true);
            painelLabel.setVisible(true);
            campoDataDia.setVisible(true);
            campoDataMes.setVisible(true);
            campoDataAno.setVisible(true);
            labelData.setVisible(true);
            botaoInserirInvestimento.setVisible(true);

            painelTotal.setVisible(false);
            scroll.setVisible(false);
        }
        if(e.getSource() == botaoSomaTotal){
            scroll.setVisible(true);
            painelTotal.setVisible(true);

            painelInserirDados.setVisible(false);
            painelLabel.setVisible(false);
            labelData.setVisible(false);
            campoDataDia.setVisible(false);
            campoDataMes.setVisible(false);
            campoDataAno.setVisible(false);
            botaoInserirInvestimento.setVisible(false);
        }
    }
}
