import com.github.alexwolfgoncharov.balance.services.BalanceService;
import com.github.alexwolfgoncharov.balance.services.BalanceServiceImpl;
import com.github.alexwolfgoncharov.balance.services.ReceiptOperContractService;
import com.github.alexwolfgoncharov.balance.services.ServiceFactory;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsContracts;

import java.util.List;


/**
 * Created by alexwolf on 05.02.16.
 */
public class TestContact {

    public static void main(String ... agrs){


        BalanceService balanceService = new BalanceServiceImpl();


//
//
//        ContrAgents agent = (ContrAgents) balanceService.getById(4, new ContrAgents());
//
//        Contracts contract = new Contracts();
//        contract.setContrAgentId(agent);
//        contract.setStartDate((java.sql.Date) new Date());
//
//        contract.setContractNumber("34567");
//        contract.setSumm(456789.88D);
//        contract.setDescription("Тестовая запись");
//
//        balanceService.add(contract);
//
//        List<Balance> contractsList = balanceService.getAll(contract);
//
//        for (Balance cont : contractsList){
//            System.out.println(cont.toString());
//        }



        Contracts contract = (Contracts) balanceService.getById(3, new Contracts());

        ReceiptOperContractService receiptOperContractService = ServiceFactory.getFactory().getReceiptOperContractService();

        List<ReceiptOperationsContracts> operationsContractses = receiptOperContractService.getAllByContract(contract);

        for (ReceiptOperationsContracts operationsContracts : operationsContractses){
            System.out.println(operationsContracts.toString());



        }


    }
}
