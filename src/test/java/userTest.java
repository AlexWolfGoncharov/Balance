import com.github.alexwolfgoncharov.balance.dao.UserRolesDAO;
import com.github.alexwolfgoncharov.balance.dao.impl.UserRolesDAOImpl;
import com.github.alexwolfgoncharov.balance.security.User;
import com.github.alexwolfgoncharov.balance.security.UserRoles;
import com.github.alexwolfgoncharov.balance.services.UserServiceImpl;

import java.util.Set;

/**
 * Created by alexwolf on 01.02.16.
 */
public class userTest {


    public static void main(String ... args){

        UserServiceImpl userService = new UserServiceImpl();
        UserRolesDAO rolesService = new UserRolesDAOImpl();
        Set<UserRoles> userRoles = rolesService.getAll();

        User user =userService.getUser("alexwolf");

//        user.setLogin("alexwolf");
//        user.setFirstName("Екатерина");
//        user.setLastName("Степанова");
        user.setPassword("alex121189");
        user.setAccess(true);
        user.setEmail("alexgoncharov06@gmail.com");

//        user.setUserRoles(userRoles);
        System.out.println(user.toString());
        userService.addUser(user);

//        userService.modify(user);

    }
}
