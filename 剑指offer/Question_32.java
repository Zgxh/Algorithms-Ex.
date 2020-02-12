import java.util.Arrays;

/**
 * 剑指offer第32题：把数组排成最小的数
 *
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 *
 * @author Yu Yang
 * @create 2020-02-09 21:20
 */
public class Question_32 {

    /**
     * 思路1：自己定义一个比较器，对字符串进行排序，然后依次加入字符串中。
     * @param numbers
     * @return
     */
    public String PrintMinNumber(int[] numbers) {
        // 先把int[]数组转成String[]数组，为了能用自定义比较器的Arrays.sort()
        String[] strs = Arrays.stream(numbers).boxed().map(a -> Integer.toString(a)).toArray(String[]::new);
//        Arrays.sort(strs, new stringComparator());
        Arrays.sort(strs, ((o1, o2) -> {return (o1 + o2).compareTo(o2 + o1);})); // 使用lambda表达式实现字符串的Comparator
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

//    /**
//     * 自定义的字符串比较器
//     */
//    public class stringComparator implements Comparator<String> {
//        @Override
//        public int compare(String o1, String o2) {
//            int combine1 = Integer.valueOf(o1 + o2);
//            int combine2 = Integer.valueOf(o2 + o1);
//            if (combine1 == combine2) {
//                return 0;
//            }
//            return combine1 > combine2 ? 1 : -1;
//        }
//    }

    /**
     * 思路2：直接对原数组进行冒泡排序，时间消耗比思路1少
     * @param numbers
     * @return
     */
    public String PrintMinNumber2(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        // 冒泡排序：非递减序
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                // long占64位，int 32位，避免越界
                // 也可以用上面的那种字符串比较的方式，先把两个字符串拼接，然后再比较，同样能避免int越界
                long combine1 = Long.parseLong(numbers[j] + "" + numbers[j + 1]);
                long combine2 = Long.parseLong(numbers[j + 1] + "" + numbers[j]);
                if (combine1 > combine2) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int num : numbers) {
            sb.append(num);
        }
        return sb.toString();
    }
}
