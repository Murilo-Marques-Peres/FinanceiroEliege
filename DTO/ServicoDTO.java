package DTO;

import java.util.Date;

public class ServicoDTO {
    int id;
    String cliente, nomeServico, devendo;
    Double valor;
    Date dataServico;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public String getNomeServico() {
        return nomeServico;
    }
    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }
    public String getDevendo() {
        return devendo;
    }
    public void setDevendo(String devendo) {
        this.devendo = devendo;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Date getDataServico() {
        return dataServico;
    }
    public void setDataServico(Date dataServico) {
        this.dataServico = dataServico;
    }
}
