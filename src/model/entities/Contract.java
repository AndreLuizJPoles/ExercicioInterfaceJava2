package model.entities;

import model.services.OnlinePaymentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Contract {
    private Integer number;
    private LocalDate date;
    private Double totalValue;

    List<Installment> installments = new ArrayList<>();

    public Contract() {
    }

    public Contract(Integer number, LocalDate date, Double totalValue) {
        this.number = number;
        this.date = date;
        this.totalValue = totalValue;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void addInstallment(Installment installment){
        installments.add(installment);
    }

    public void removeInstallment(Installment installment){
        installments.remove(installment);
    }

    public void printInstallments(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for(Installment installment : installments){
            System.out.println(fmt.format(installment.getDueDate()) + " - " + String.format("%.2f",installment.getAmount()));
        }
    }
}
