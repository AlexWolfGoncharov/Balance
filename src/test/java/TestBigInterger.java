import java.math.BigDecimal;

/**
 * Created by alexwolf on 14.02.16.
 */
public class TestBigInterger {

    public static void main(String ... agrs){

        BigDecimal test = new BigDecimal(0);


        BigDecimal two = new BigDecimal(2.00);


        BigDecimal tree = new BigDecimal(3.02);


        System.out.println(test.toString());
        System.out.println(two.toString());
        System.out.println(tree.toString());


        System.out.println(tree.add(two).add(test));

    }
}
