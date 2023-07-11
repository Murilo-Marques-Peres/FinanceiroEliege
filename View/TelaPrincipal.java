package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TelaPrincipal implements ActionListener{
    JFrame frame1 = new JFrame();
    JButton botaoEntrada = new JButton("Entrada");
    JButton botaoSaida = new JButton("Saída");
    JButton botaoTotal = new JButton("Soma Total");



    public void frameMethodEnd(){
        frame1.setLayout(null);
        frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame1.setSize(1500,1000);
        frame1.setVisible(true);
    }
    TelaPrincipal(){
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        botaoEntrada.setBounds(0,0,300,50);
        botaoEntrada.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoEntrada.addActionListener(this);
        botaoSaida.setBounds(0,55,300,50);
        botaoSaida.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoSaida.addActionListener(this);
        botaoTotal.setFont(new Font("Comic Sans", Font.BOLD, 24));
        botaoTotal.addActionListener(this);
        botaoTotal.setBounds(0,110,300,50);

        frame1.add(botaoEntrada);
        frame1.add(botaoSaida);
        frame1.add(botaoTotal);

        

        frameMethodEnd();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botaoEntrada){
            Interface1 interface1 = new Interface1();
            frame1.dispose();
        }
        if(e.getSource() == botaoSaida){
            Contabilidade contabilidade = new Contabilidade();
            frame1.dispose();
        }
        if(e.getSource() == botaoTotal){
            ContasTotais contasTotais = new ContasTotais();
            frame1.dispose();
        }

    }
}
