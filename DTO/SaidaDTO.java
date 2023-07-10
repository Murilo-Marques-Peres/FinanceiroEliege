package DTO;

import java.util.Date;

public class SaidaDTO {
    int id;
    String marcaProduto;
    double valor;
    Date data;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getMarcaProduto() {
        return marcaProduto;
    }
    public void setMarcaProduto(String marcaProduto) {
        this.marcaProduto = marcaProduto;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
}
