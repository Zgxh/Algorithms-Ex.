import java.util.ArrayList;

/**
 * @author Yu Yang
 * @create 2020-02-01 16:19
 */
public class Test {

    public static void main(String[] args) {
        Question_24 q = new Question_24();
        Question_24.TreeNode root = q.new TreeNode(10);
        root.left = q.new TreeNode(5);
        root.right = q.new TreeNode(12);
        root.left.left = q.new TreeNode(4);
        root.left.right = q.new TreeNode(7);
        ArrayList result = q.FindPath(root, 22);
        System.out.println(result);
    }
}
