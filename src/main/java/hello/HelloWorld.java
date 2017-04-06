package hello;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * Created by seven4n on 6/04/17.
 */
public class HelloWorld {

        public static void main(String[] args) throws Exception{
            System.out.println("Hello World");

            System.out.println("Cambio para el FEATUREDOS");

            //EJEMPLO 3

            Trader raoul = new Trader("Raoul", "Cambridge");
            Trader mario = new Trader("Mario","Milan");
            Trader alan = new Trader("Alan","Cambridge");
            Trader brian = new Trader("Brian","Cambridge");

            List<Transaction> transactions = Arrays.asList(
                    new Transaction(brian, 2011, 300),
                    new Transaction(raoul, 2012, 1000),
                    new Transaction(raoul, 2011, 400),
                    new Transaction(mario, 2012, 710),
                    new Transaction(mario, 2012, 700),
                    new Transaction(alan, 2012, 950)
            );

            //1
            List<Transaction> tr2011 =
                    transactions.stream()
                            .filter(transaction -> transaction.getYear() == 2011)
                            .sorted(comparing(Transaction::getValue))
                            .collect(Collectors.toList());

            //2
            List<String> cities =
                    transactions.stream()
                            .map(transaction -> transaction.getTrader().getCity())
                            .distinct()
                            .collect(Collectors.toList());

            //3
            List<Trader> traders =
                    transactions.stream()
                            .map(Transaction::getTrader)
                            .filter(trader -> trader.getCity().equals("Cambridge"))
                            .distinct()
                            .sorted(comparing(Trader::getName))
                            .collect(Collectors.toList());

            //4
            String traderStr =
                    transactions.stream()
                            .map(transaction ->transaction.getTrader().getName())
                            .distinct()
                            .sorted()
                            .reduce("", (n1, n2) -> n1 + n2);

            //5
            boolean milanBased =
                    transactions.stream()
                            .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

            //6
            transactions.stream()
                    .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                    .map(Transaction::getValue)
                    .forEach(System.out::println);

            //7
            Optional<Integer> highestValue =
                    transactions.stream()
                            .map(Transaction::getValue)
                            .reduce(Integer::max);

            //8
            Optional<Transaction> smallestTransaction =
                    transactions.stream()
                            .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

            //OTRA OPCION DEL 8
            Optional<Transaction> smallestTransaction2 =
                    transactions.stream()
                            .min(comparing(Transaction::getValue));
        }

}
