package model.services;

import model.entities.AluguelCarro;
import model.entities.Invoice;

import java.time.Duration;

public class AluguelServicos {

    private Double precoHora;
    private Double precoDia;

    private TaxaServicos taxaServicos;

    public AluguelServicos(Double precoHora, Double precoDia, TaxaServicos taxaServicos) {
        this.precoHora = precoHora;
        this.precoDia = precoDia;
        this.taxaServicos = taxaServicos;
    }

    public void processoLocacao(AluguelCarro aluguelCarro){
        double minutos = Duration.between(aluguelCarro.getInicio(), aluguelCarro.getFim()).toMinutes();
        double horas = minutos / 60.0;

        double pagamentoBasico;
        if(horas <= 12.0 ){
            pagamentoBasico = precoHora * Math.ceil(horas);
        } else {
            pagamentoBasico = precoDia * Math.ceil(horas / 24);
        }

        double taxa = taxaServicos.taxa(pagamentoBasico);

        aluguelCarro.setInvoice(new Invoice(pagamentoBasico,taxa));
    }
}
