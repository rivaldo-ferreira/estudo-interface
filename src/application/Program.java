package application;

import model.entities.AluguelCarro;
import model.entities.Invoice;
import model.entities.Veiculo;
import model.services.AluguelServicos;
import model.services.TaxaBrasilServicos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){

        Locale.setDefault(Locale.US);
        Scanner dg = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime agora = LocalDateTime.now();

        System.out.println("***** aluguel de veículos *****".toUpperCase());
        System.out.println();

        System.out.println("Entre com os dados do aluguel");
        System.out.print("Modelo do carro: ");
        String modeloCarro = dg.nextLine();
        System.out.println("Retirada (dd/MM/yyyy HH:mm): " + agora.format(fmt));
        //LocalDateTime inicio = LocalDateTime.parse(dg.nextLine(), fmt);
        System.out.print("Devolução (dd/MM/yyyy HH:mm): ");
        LocalDateTime devolucao = LocalDateTime.parse(dg.nextLine(), fmt);

        AluguelCarro carro01 = new AluguelCarro(agora, devolucao,new Veiculo(modeloCarro));

        System.out.print("Digite o preço por hora: ");
        double precoHora = dg.nextDouble();
        System.out.print("Digite o preço por dia: ");
        double precoDia = dg.nextDouble();

        AluguelServicos aluguelServicos = new AluguelServicos(precoHora,precoDia,new TaxaBrasilServicos());
        aluguelServicos.processoLocacao(carro01);

        System.out.println("------------------------");
        System.out.println("fatura".toUpperCase());
        System.out.println("Pagamento Básico: " + String.format("%.2f",carro01.getInvoice().getPagamentoBasico()));
        System.out.println("Imposto: " + String.format("%.2f",carro01.getInvoice().getTaxa()));
        System.out.println("Pagamento Total: " + String.format("%.2f",carro01.getInvoice().getTotalPagamento()));

        dg.close();
    }
}
