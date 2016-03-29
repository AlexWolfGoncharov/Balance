import com.github.alexwolfgoncharov.balance.dao.ReceiptOperDeptDAO;
import com.github.alexwolfgoncharov.balance.dao.impl.ContractDAOImpl;
import com.github.alexwolfgoncharov.balance.dao.impl.ReiceptOperDeptDAOImpl;
import com.github.alexwolfgoncharov.balance.services.BalanceService;
import com.github.alexwolfgoncharov.balance.services.BalanceServiceImpl;
import com.github.alexwolfgoncharov.balance.structure.Contracts;
import com.github.alexwolfgoncharov.balance.structure.Departments;
import com.github.alexwolfgoncharov.balance.structure.ReceiptOperationsDepartments;

import java.util.List;

/**
 * Created by alexwolf on 13.02.16.
 */
public class testRecDept {


    public static void main(String ... args){


        ReceiptOperDeptDAO receiptOperDeptDAO =new ReiceptOperDeptDAOImpl();

        BalanceService balanceService  =  new BalanceServiceImpl();


        List<ReceiptOperationsDepartments> receiptOperationsDepartmentses = null;


        Departments departments = (Departments) balanceService.getById(20, new Departments());


        receiptOperationsDepartmentses = receiptOperDeptDAO.getAll();


        System.out.println("Список всех записей: ");
        for (ReceiptOperationsDepartments receiptOperationsDep : receiptOperationsDepartmentses){


            System.out.println(receiptOperationsDep.toString());


        }




        System.out.println("Список всех по департаменту: " + departments.toString());



        receiptOperationsDepartmentses = receiptOperDeptDAO.getAllbyDept(departments);
        for (ReceiptOperationsDepartments receiptOperationsDep : receiptOperationsDepartmentses){


            System.out.println(receiptOperationsDep.toString());


        }





        Contracts contracts = new ContractDAOImpl().getById(42);




        System.out.println("Список всех по контракту: " + contracts.toString());
        receiptOperationsDepartmentses = receiptOperDeptDAO.getAllByContract(contracts);

        for (ReceiptOperationsDepartments receiptOperationsDep : receiptOperationsDepartmentses){


            System.out.println(receiptOperationsDep.toString());


        }




        System.out.println("Список всех по контракту: " + contracts.getContractNumber() + " и департаменту: " + departments.getNameOfDepartment());
        receiptOperationsDepartmentses = receiptOperDeptDAO.getAllByContractAndDep(contracts, departments);

        for (ReceiptOperationsDepartments receiptOperationsDep : receiptOperationsDepartmentses){


            System.out.println(receiptOperationsDep.toString());


        }






    }
}
