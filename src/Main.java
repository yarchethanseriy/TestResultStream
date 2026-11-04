import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Parser.TestResult> run = List.of(
                new Parser.TestResult("LoginTest", Parser.Status.PASSED, 120),
                new Parser.TestResult("CreateTastTest", Parser.Status.PASSED, 940),
                new Parser.TestResult("UpdateStatusTest", Parser.Status.FAILED, 2100),
                new Parser.TestResult("CommentTest", Parser.Status.PASSED, 380),
                new Parser.TestResult("DeleteTaskTest", Parser.Status.FAILED, 760),
                new Parser.TestResult("StatsTest", Parser.Status.SKIPPED, 0),
                new Parser.TestResult("LogoutTest", Parser.Status.PASSED, 90)
        );

        Parser.printSummary(run);
    }
}