package View;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Interface1 implements ActionListener{
    JFrame frame1 = new JFrame();
    JTable table1 = new JTable();
    JScrollPane scroll;
    JPanel painelGrid = new JPanel();
    JPanel painelGridEditar = new JPanel();
    JLabel labelMes = new JLabel("Mês");
    JLabel labelCliente = new JLabel("Nome Cliente:  ");
    JLabel labelServico = new JLabel("Serviço:  ");
    JLabel labelValor = new JLabel("Valor:  ");
    JLabel labelInserir = new JLabel("Inserir Cliente:");
    JTextField campoInserirCliente = new JTextField();
    JTextField campoInserirServico = new JTextField();
    JTextField campoInserirValor = new JTextField();
    JTextField campoInserirData = new JTextField();

    DefaultTableModel model = new DefaultTableModel();
    JButton botaoVer = new JButton("Visualizar");
    JButton botaoEditar = new JButton("Editar/Add/Remover");
    JButton botaoAdicionar = new JButton("+");
    JButton botaoRemover = new JButton("-");
    JButton botaoEditar2 = new JButton("Editar");


    public void gridLayoutMethod1(){
        painelGrid.setLayout(new GridLayout(1,3));
        painelGrid.add(botaoAdicionar);
        painelGrid.add(botaoRemover);
        painelGrid.add(botaoEditar2);        

    }
    public void gridLayoutMethod2(){
        painelGridEditar.setLayout(new GridLayout(3, 2));
        painelGridEditar.add(labelCliente);
        painelGridEditar.add(campoInserirCliente);
        painelGridEditar.add(labelServico);
        painelGridEditar.add(campoInserirServico);
        painelGridEditar.add(labelValor);
        painelGridEditar.add(campoInserirValor);

        
    }
    public void endFrameMethod(){
        frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame1.setSize(1500, 1000);
        frame1.setLayout(null);
        frame1.setVisible(true);

    }
    public void tableMethod(){
        Object[] colunas = {"Cliente", "Serviço", "Valor", "data"};
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

        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(250);

        scroll = new JScrollPane(table1);

    }
    
    public void componentsMethod(){
        painelGrid.setBounds(600,20,300,40);
        painelGridEditar.setBounds(350,350,600,120);

        scroll.setBounds(530,80,800,500);
        labelMes.setBounds(530,30,800,30);
        labelMes.setFont(new Font("Comic Sans", Font.BOLD, 24));   
        labelMes.setHorizontalAlignment(JLabel.CENTER);
        labelCliente.setHorizontalAlignment(JLabel.RIGHT);
        labelCliente.setFont(new Font("Comic Sans", Font.BOLD, 18));
        labelServico.setHorizontalAlignment(JLabel.RIGHT);
        labelServico.setFont(new Font("Comic Sans", Font.BOLD, 18));
        labelValor.setHorizontalAlignment(JLabel.RIGHT);
        labelValor.setFont(new Font("Comic Sans", Font.BOLD, 18));
        labelInserir.setBounds(420 ,300, 600,30);
        labelInserir.setFont(new Font("Comic Sans", Font.BOLD, 18));
        labelInserir.setHorizontalAlignment(JLabel.CENTER);
        campoInserirCliente.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoInserirServico.setFont(new Font("Comic Sans", Font.BOLD, 16));
        campoInserirValor.setFont(new Font("Comic Sans", Font.BOLD, 16));
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


        
    }
    public void positionMethod(){
        frame1.add(painelGrid);
        frame1.add(painelGridEditar);
        frame1.add(scroll);
        frame1.add(labelMes);
        frame1.add(labelMes);
        frame1.add(botaoVer);
        frame1.add(botaoEditar);
        frame1.add(labelInserir);
        

    }
    public void setarDefaultFalse(){
        scroll.setVisible(false);
        labelMes.setVisible(false);
        painelGrid.setVisible(false);
        painelGridEditar.setVisible(false);
        labelInserir.setVisible(false);
    }

    public Interface1(){
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gridLayoutMethod1();
        gridLayoutMethod2();
        tableMethod();
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

            scroll.setVisible(true);
            labelMes.setVisible(true);

        }
        if(e.getSource() == botaoEditar){
            scroll.setVisible(false);
            labelMes.setVisible(false);
            painelGridEditar.setVisible(false);
            labelInserir.setVisible(false);
            
            painelGrid.setVisible(true);


        }
        if(e.getSource() == botaoAdicionar){
            painelGrid.setVisible(true);
            painelGridEditar.setVisible(true);
            labelInserir.setVisible(true);
        }
    }
}
