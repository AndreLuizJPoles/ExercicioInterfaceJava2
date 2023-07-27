package application;

import model.entities.Contract;
import model.services.ContractService;
import model.services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try{
            System.out.println("Entre os dados do contrato:");
            System.out.print("Numero: ");
            int contractNumber = sc.nextInt();
            System.out.print("Data (dd/MM/yyyy): ");
            LocalDate contractDate = LocalDate.parse(sc.next(), fmt);
            System.out.print("Valor do contrato: ");
            double contractValue = sc.nextDouble();
            System.out.print("Entre com o numero de parcelas: ");
            int contractNumberOfInstallments = sc.nextInt();
            Contract contract = new Contract(contractNumber, contractDate, contractValue);

            ContractService service = new ContractService(new PaypalService());
            service.processContract(contract, contractNumberOfInstallments);

            System.out.println("Parcelas:");
            contract.printInstallments();
        }
        catch(InputMismatchException e){
            System.out.println("Erro: entrada invalida");
        }
        catch(DateTimeParseException e){
            System.out.println("Erro: formatacao de data invalida");
        }
        finally {
            sc.close();
        }
    }
}