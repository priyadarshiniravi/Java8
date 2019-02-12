import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class ForEachAndLamdaTest {
    @Test
    public void forEachLoop() {
        List<Integer> list = asList(1, 2, 3, 4);

        //woh this is just a for loop inside :(
        //it's an implementation of consumer interface
        list.forEach(System.out::println);
    }


    @Test
    public void whatAreConsumers() {
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };

        consumer.accept(1);
    }

    @Test
    public void writingLamdas() {
        InterfaceForLamda interfaceForLamda = () -> "Hello Lamda";

        assertThat(interfaceForLamda.method()).isEqualTo("Hello Lamda");
    }

    @Test
    public void consumerSimplifiedToLamda() {
        Consumer<Integer> consumer = System.out::println;

        consumer.accept(1);
    }

    interface InterfaceForLamda {
        String method();
    }
}
