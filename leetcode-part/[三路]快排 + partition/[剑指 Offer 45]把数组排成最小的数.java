//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。 
//
// 
//
// 示例 1: 
//
// 输入: [10,2]
//输出: "102" 
//
// 示例 2: 
//
// 输入: [3,30,34,5,9]
//输出: "3033459" 
//
// 
//
// 提示: 
//
// 
// 0 < nums.length <= 100 
// 
//
// 说明: 
//
// 
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数 
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0 
// 
// Related Topics 排序 
// 👍 98 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 字符串比较的快速排序方案。
     * 核心在于比较两个字符串时的比较器(o1, o2) -> (o1 + o2).compareTo(o2 + o1)。
     */
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = nums[i] + "";
        }
        // Arrays.sort(strs, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        // 字符串快速排序
        quickSort(strs, 0, nums.length - 1);

        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }

        return sb.toString();
    }

    private void quickSort(String[] strs, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition(strs, left, right);
        quickSort(strs, left, mid - 1);
        quickSort(strs, mid + 1, right);
    }

    private int partition(String[] strs, int left, int right) {
        String target = strs[right];
        while (left < right) {
            while (left < right && (strs[left] + target).compareTo(target + strs[left]) <= 0) {
                left++;
            }
            strs[right] = strs[left];
            while (left < right && (strs[right] + target).compareTo(target + strs[right]) > 0) {
                right--;
            }
            strs[left] = strs[right];
        }
        strs[left] = target;

        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
