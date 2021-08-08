import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'solve' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     */

    public static int solve(int n, int k) {
        long a = 1;
        long b = -n;
        long c = (long) n * k;

        long discriminant = b * b - 4 * a * c;

        if (discriminant <= 0) {
            return n - 1;
        }

        int exclusionLower = (int) Math.floor((-b - Math.sqrt(discriminant)) / (2 * a));
        while (isTooFar(a, b, discriminant, exclusionLower)) {
            exclusionLower++;
        }

        int exclusionUpper = (int) Math.ceil((-b + Math.sqrt(discriminant)) / (2 * a));
        while (isTooFar(a, b, discriminant, exclusionUpper)) {
            exclusionUpper--;
        }

        return (n - 1) - (exclusionUpper - exclusionLower + 1);
    }

    static long square(long x) {
        return x * x;
    }

    static boolean isTooFar(long a, long b, long discriminant, int x) {
        return square((long) x * 2 * a + b) >= discriminant;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int k = Integer.parseInt(firstMultipleInput[1]);

                int result = Result.solve(n, k);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
