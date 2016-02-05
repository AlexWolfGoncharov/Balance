import com.github.alexwolfgoncharov.balance.services.BalanceService;
import com.github.alexwolfgoncharov.balance.services.BalanceServiceImpl;
import com.github.alexwolfgoncharov.balance.structure.Balance;
import com.github.alexwolfgoncharov.balance.structure.ContrAgents;
import com.github.alexwolfgoncharov.balance.structure.Contracts;

import java.util.Date;
import java.util.List;


/**
 * Created by alexwolf on 05.02.16.
 */
public class TestContact {

    public static void main(String ... agrs){


        BalanceService balanceService = new BalanceServiceImpl();




        ContrAgents agent = (ContrAgents) balanceService.getById(4, new ContrAgents());

        Contracts contract = new Contracts();
        contract.setContrAgentId(agent);
        contract.setStartDate((java.sql.Date) new Date());

        contract.setContractNumber("34567");
        contract.setSumm(456789.88D);
        contract.setDescription("Тестовая запись");

        balanceService.add(contract);

        List<Balance> contractsList = balanceService.getAll(contract);

        for (Balance cont : contractsList){
            System.out.println(cont.toString());
        }






    }
}
