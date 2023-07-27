package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.time.LocalDate;

public class ContractService {
    private OnlinePaymentService service;

    public ContractService(OnlinePaymentService service) {
        this.service = service;
    }

    public OnlinePaymentService getService() {
        return service;
    }

    public void setService(OnlinePaymentService service){
        this.service = service;
    }

    public void processContract(Contract contract, Integer months){
        LocalDate date = contract.getDate();
        for(int i=1;i<=months;i++){
            double amount = contract.getTotalValue()/months + service.interest(contract.getTotalValue()/months, i);
            double installment = amount + service.paymentFee(amount);
            date = date.plusMonths(1);
            contract.addInstallment(new Installment(date,installment));
        }
    }
}
