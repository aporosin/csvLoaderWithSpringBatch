package aporosin.csvLoaderWithSpringBatch.insurance;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*


 */
public class CsvComparer {

    @Test
    public void test() {
        try {

            ClassLoader classLoader = getClass().getClassLoader();
            File leftFile = new File(classLoader.getResource("csv/insurence_in.csv").getFile());
            File rightFile = new File(classLoader.getResource("csv/insurence_in2.csv").getFile());
            boolean result = compareCsv(leftFile.toPath(),
                    rightFile.toPath(),
                    true,
                    "IN_ID");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean compareCsv(Path left, Path right, boolean ignoreColumnOrder, String... ignoredColumns) throws IOException {

        if(!ignoreColumnOrder && ignoredColumns == null) {
         return true; // other solution
        }

        String[] leftFileLines = Files.lines(left).toArray(String[]::new);
        String[] rightFileLines = Files.lines(right).toArray(String[]::new);

        // Assume first line is header
        String[] leftHeaders = leftFileLines[0].split(";");
        String[] rightHeaders = rightFileLines[0].split(";");

        // finds first difference

        for (int i = 1; i < leftFileLines.length; i++) {

            int finalI = i;
            Set<String> leftTokens = new HashSet<>();
            String[] leftSplit = leftFileLines[i].split(";");

            for (int j = 0; j < leftHeaders.length; j++) {
                leftTokens.add(leftHeaders[j]+": " + leftSplit[j]);
            }


            Set<String> rightTokens = new HashSet<>();
            String[] rightSplit = rightFileLines[i].split(";");

            for (int j = 0; j < rightHeaders.length; j++) {
                rightTokens.add(rightHeaders[j]+": " + rightSplit[j]);
            }

            Set<String> intersection = new HashSet<>(rightTokens);
            intersection.retainAll(rightTokens);

            if(intersection.containsAll(rightTokens) && intersection.containsAll(rightTokens))
                continue;
            else
            {
                // display differences
                return false;
            }
        }

        return true;

    }
}
