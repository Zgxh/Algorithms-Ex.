package DataStructureAndAlgorithm.Graph;

/**
 * 算法流程：
 * 1. 给定一个起点start，从他的邻接顶点中选一个权值最小的点minNode，此时start与minNode的边权值一定就是start到minNode的最短距离，
 *      标记minNode已访问；
 * 2. 然后以minNode为中转点，更新start到所有未访问结点之间的最短距离
 *      weight[start][i]=min(weight[start][i], weight[start][minNode] + weight[minNode][i]);
 * 3. 更新完之后，再次从未访问结点中选择权值最小的minNode，此时start到minNode的权值即为二者间最短距离，标记它已访问，然后进行步骤2；
 * 4. 重复步骤2、3，直到所有节点都已访问，即start到所有节点的最小距离都求得。
 *
 * @author Yu Yang
 * @create 2021-02-28 22:59
 */
public class Dijkstra {

    /**
     * dijkstra 求单源最短路径：start 到其他所有点的最短路径
     * @param weight 带权邻接矩阵
     * @param start 给定的结点
     * @return
     */
    public int[] dijkstra(int[][]weight, int start){
        int length = weight.length;
        int[] shortPath = new int[length]; // 存放从start到各个点的最短距离
        shortPath[start] = 0;
        // String path[] = new String[length];//存放从start点到各点的最短路径的字符串表示
        // for(int i=0;i<length;i++){
        //     path[i] = start+"->"+i;
        // }
        int visited[] = new int[length]; //标记当前该顶点的最短路径是否已经求出，1表示已经求出
        visited[start] = 1; //start点的最短距离已经求出
        for(int count = 1;count<length;count++){
            int k=-1;
            int dmin = Integer.MAX_VALUE;
            for(int i=0;i<length;i++){
                if(visited[i]==0 && weight[start][i]<dmin){
                    dmin = weight[start][i];
                    k=i;
                }
            }
            //选出一个距离start最近的未标记的顶点，作为中转点，更新start到其他未访问结点之间的权值
            shortPath[k] = dmin;
            visited[k] = 1;
            for(int i=0;i<length;i++){
                if(visited[i]==0 && weight[start][k]+weight[k][i]<weight[start][i]){
                    weight[start][i] = weight[start][k]+weight[k][i];
                    // path[i] = path[k]+"->"+i;
                }
            }
        }
        // for(int i=0;i<length;i++){
        //     System.out.println("从"+start+"出发到"+i+"的最短路径为："+path[i]+"="+shortPath[i]);
        // }

        return shortPath;
    }
}
