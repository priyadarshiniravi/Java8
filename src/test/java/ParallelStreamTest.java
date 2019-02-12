import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class ParallelStreamTest {
    @Test
    public void shouldPrintInParallel() {
        List<Integer> list = asList(1, 2, 3, 4, 5, 6, 7);

        //very fast :D
        list.parallelStream().forEach(System.out::println);
    }

    @Test
    public void shouldFilter() {
        List<Integer> list = asList(1, 2, 3, 4, 5, 6, 7);

        List<Integer> collect = list.parallelStream().filter(val -> val % 2 == 0).collect(Collectors.toList());

        assertThat(collect).containsAll(asList(2, 4, 6));
    }

    @Test
    public void filterIsLazyEvaluationMinTimesPossibleBasedOnTerminationCondition() {
        List<Integer> list = asList(1, 2, 3, 4, 5, 6, 7);

        //got evaluated only 2 times
        list.stream().filter(val -> {
            System.out.println("Hello" + val);
            return val % 2 == 0;
        }).findFirst();
    }

    @Test
    public void filterIsLazyEvaluationMayEvaluateAllIfTerminatorIsCollect() {
        List<Integer> list = asList(1, 2, 3, 4, 5, 6, 7);

        //got evaluated only 2 times
        list.stream().filter(val -> {
            System.out.println("Hello" + val);
            return val % 2 == 0;
        }).collect(Collectors.toList());
    }
}
