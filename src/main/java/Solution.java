import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/*

  Problem link: https://quera.org/problemset/33037

*/

public class Solution {

    public List<ExceptionProxy> transformException(List<Supplier<?>> functions) {
        //TODO
        List<ExceptionProxy> result = new ArrayList<>();

        for (Supplier<?> f : functions) {
            try {
                f.get();

                result.add(new ExceptionProxy("OK!", f));
            } catch (Exception e) {
                result.add(new ExceptionProxy(e.getMessage(), f));
            }
        }

        return result;
    }

    public static class ExceptionProxy {
        //TODO
        private final String msg;
        private final Supplier<?> function;

        public ExceptionProxy(String msg, Supplier<?> function) {
            this.msg = msg;
            this.function = function;
        }

        public String getMsg() {
            return msg;
        }

        public Supplier<?> getFunction() {
            return function;
        }
    }

    public static void main(String[] args) {
        List<Supplier<?>> functions = Arrays.asList(new Devider(1,0), new Devider(1,1));

        Solution solution = new Solution();
        List<Solution.ExceptionProxy> result = solution.transformException(functions);
        for(Solution.ExceptionProxy exceptionProxy: result) {
            System.out.println("msg: " + exceptionProxy.getMsg());
            System.out.println("function: " + exceptionProxy.getFunction());
        }
    }

    public static class Devider implements Supplier<Integer> {
        int x, y;
        public Devider(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public Integer get() {
            return x / y;
        }
        @Override
        public String toString() {
            return "Devide[" + x + "/" + y + "]";
        }
    }
}
