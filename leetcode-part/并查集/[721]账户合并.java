//给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其
//余元素是 emails 表示该帐户的邮箱地址。 
//
// 现在，我们想合并这些帐户。如果两个帐户都有一些共同的邮件地址，则两个帐户必定属于同一个人。请注意，即使两个帐户具有相同的名称，它们也可能属于不同的人，因为
//人们可能具有相同的名称。一个人最初可以拥有任意数量的帐户，但其所有帐户都具有相同的名称。 
//
// 合并帐户后，按以下格式返回帐户：每个帐户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。accounts 本身可以以任意顺序返回。 
//
// 例子 1: 
//
// 
//Input: 
//accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnn
//ybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Ma
//ry", "mary@mail.com"]]
//Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.
//com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
//Explanation: 
//  第一个和第三个 John 是同一个人，因为他们有共同的电子邮件 "johnsmith@mail.com"。 
//  第二个 John 和 Mary 是不同的人，因为他们的电子邮件地址没有被其他帐户使用。
//  我们可以以任何顺序返回这些列表，例如答案[['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com'
//]，
//  ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']]仍然会被
//接受。
//
// 
//
// 注意： 
//
// 
// accounts的长度将在[1，1000]的范围内。 
// accounts[i]的长度将在[1，10]的范围内。 
// accounts[i][j]的长度将在[1，30]的范围内。 
// 
// Related Topics 深度优先搜索 并查集 
// 👍 137 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 并查集

    private int index = 0; // index 用来区分姓名相同但是不是同一个人的情况
    private Map<String, String> parent = new HashMap();

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 为所有的邮件地址建立连接关系
        for (List<String> account : accounts) {
            String name = account.get(0) + "#" + index; // name 连接 index区分同名的人
            index++;
            parent.put(name, name);
            for (int i = 1; i < account.size(); i++) {
                String curAcc = account.get(i);
                // 如果该邮件地址已经处理过了，则说明出现了同一个人，则需要处理合并的情况
                if (parent.containsKey(curAcc)) {
                    union(curAcc, name);
                } else {
                    parent.put(account.get(i), name);
                }
            }
        }
        // 找出同一个人所对应的所有邮件地址
        Map<String, LinkedList<String>> map = new HashMap();
        for (Map.Entry<String, String> pair : parent.entrySet()) {
            String child = pair.getKey(), father = pair.getValue();
            // 过滤掉单纯的姓名，因为在union时可能会把姓名变成了child
            if (child.indexOf("@") == -1) {
                continue;
            }
            String root = find(father);
            if (!map.containsKey(root)) {
                map.put(root, new LinkedList());
            }
            LinkedList<String> list = map.get(root);
            list.add(child);
        }
        // 把姓名与其对应的邮件地址合并为同一个list
        List<List<String>> result = new ArrayList();
        for (Map.Entry<String, LinkedList<String>> pair : map.entrySet()) {
            String name = pair.getKey();
            LinkedList<String> list = pair.getValue();
            // 同一个人，对应的邮件地址按照字典序排列
            Collections.sort(list);
            // 去掉姓名中 “#” 往后的部分
            int sharpIndex = name.indexOf("#");
            name = name.substring(0, sharpIndex);
            list.addFirst(name);
            result.add(list);
        }

        return result;
    }

    // 合并两个连通的子图
    private void union(String node1, String node2) {
        String root1 = find(node1), root2 = find(node2);
        if (!root1.equals(root2)) {
            parent.put(root1, root2);
        }
    }

    // 寻找某个邮件地址对应的所属人
    private String find(String node) {
        String father = parent.get(node);
        if (!node.equals(father)) {
            parent.put(node, find(father));
        }

        return parent.get(node);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
