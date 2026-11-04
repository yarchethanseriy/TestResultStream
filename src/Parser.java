import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser {

    public enum Status {
        PASSED,
        FAILED,
        SKIPPED
    }

    public record TestResult(String name, Status status, long durationMs) {}

    public static void printSummary(List<TestResult> run) {
        Map<Status, List<String>> testsByStatus = run.stream()
                .collect(Collectors.groupingBy(
                        TestResult::status,
                        Collectors.mapping(
                                TestResult::name,
                                Collectors.toList()
                        )
                ));

        System.out.println("=== Сводка прогона ===");
        System.out.println("PASSED (" + testsByStatus.getOrDefault(Status.PASSED, List.of()).size() + "): "
                + testsByStatus.getOrDefault(Status.PASSED, List.of()).stream().collect(Collectors.joining(", ")));

        System.out.println("FAILED (" + testsByStatus.getOrDefault(Status.FAILED, List.of()).size() + "): "
                + testsByStatus.getOrDefault(Status.FAILED, List.of()).stream().collect(Collectors.joining(", ")));

        System.out.println("SKIPPED (" + testsByStatus.getOrDefault(Status.SKIPPED, List.of()).size() + "): "
                + testsByStatus.getOrDefault(Status.SKIPPED, List.of()).stream().collect(Collectors.joining(", ")));

        List<String> longestThree = run.stream()
                .sorted(Comparator.comparingLong(TestResult::durationMs).reversed())
                .limit(3)
                .map(TestResult::name)
                .collect(Collectors.toList());

        System.out.println("Топ-3 долгих теста: " +
                longestThree.stream().collect(Collectors.joining(", ")));

        double averageDurationMs = run.stream()
                .filter(test -> test.status() == Status.PASSED)
                .collect(Collectors.averagingLong(TestResult::durationMs));

        System.out.println("Среднее время прошедших: " + averageDurationMs + " ms");
    }

}
