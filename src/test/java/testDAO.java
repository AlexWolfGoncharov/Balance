import com.github.alexwolfgoncharov.balance.dao.BalanceDAO;
import com.github.alexwolfgoncharov.balance.dao.impl.BalanceDAOImpl;
import com.github.alexwolfgoncharov.balance.services.ReceiptOperContractService;
import com.github.alexwolfgoncharov.balance.services.ReceiptOperContractServiceImpl;
import com.github.alexwolfgoncharov.balance.services.ReceiptOperDeptService;
import com.github.alexwolfgoncharov.balance.services.ReceiptOperDeptServiceImpl;
import com.github.alexwolfgoncharov.balance.structure.*;

import java.util.List;

/**
 * Created by alexwolf on 31.01.16.
 */
public class testDAO {

    public static void main (String ... agrs){
        BalanceDAO dao = new BalanceDAOImpl();


        Departments dep = new Departments();
        dep.setId(1);
        dep.setNameOfDepartment("Test");
        dep.setDescription("Тестовый департамент");

//        dao.add(dep);

        List<Balance> departmensList = null;

        departmensList = dao.getAll(new Departments());

        for (Balance bal : departmensList){
            System.out.println(bal.toString());
            dep = (Departments) bal;
        }

        Contracts contracts = (Contracts) dao.getById(1, new Contracts());
        System.out.println(contracts.toString());



        ReceiptOperationsContracts operationsContracts = new ReceiptOperationsContracts();
        operationsContracts.setContractId(contracts);
        operationsContracts.setDescription("Тестовая запись");
        operationsContracts.setSumma(99352.87D);
        operationsContracts.setNdc((Double)operationsContracts.getSumma()*0.20);










        ReceiptOperContractService receiptOperContractsDAO = new ReceiptOperContractServiceImpl();
//        receiptOperContractsDAO.add(operationsContracts);


        ReceiptOperationsContracts newCon = new ReceiptOperationsContracts();


//        newCon.setTime(new Timestamp(new Date().getTime()));
        newCon.setContractId(contracts);
        newCon.setDescription("Тестовая запись 2");
        newCon.setSumma(55533.77D);
        newCon.setNdc((Double)newCon.getSumma()*0.20);
//        receiptOperContractsDAO.add(newCon);




        List<ReceiptOperationsContracts> receiptOperationsContractses = receiptOperContractsDAO.getAll();
        ReceiptOperationsDepartments receiptOperationsDepartments = new ReceiptOperationsDepartments();


        ReceiptOperDeptService receiptOperDeptService = new ReceiptOperDeptServiceImpl();


        for (ReceiptOperationsContracts recontracts : receiptOperationsContractses){


            for (int count = 1; count < (1 / 0.35 + 1); count++) {
                receiptOperationsDepartments.setDepartmentId(dep);
                receiptOperationsDepartments.setReceptOpContrId(recontracts.getId());
                receiptOperationsDepartments.setDescription("Тестьвая запись №" + count + " по деп-т.1");
                receiptOperationsDepartments.setSumma(recontracts.getSumma() * (count < (1 / 0.35) ? 0.35 : (1 - (0.35*count))));
                receiptOperationsDepartments.setNdc(receiptOperationsDepartments.getSumma() * 0.2);


//                receiptOperDeptService.add(receiptOperationsDepartments);

            }


        }



        System.out.println(receiptOperationsDepartments.toString());

         List<ReceiptOperationsDepartments> receiptOperationsDepartmentses = receiptOperDeptService.getAll();
        System.out.println(receiptOperationsDepartmentses.size());
        List<ReceiptOperationsDepartments> receiptOperationsDepartmentses1 = receiptOperDeptService.getAllbyDept(dep);


        for (ReceiptOperationsDepartments rec1 : receiptOperationsDepartmentses1){

            System.out.println(rec1.toString());
        }




    }
}
