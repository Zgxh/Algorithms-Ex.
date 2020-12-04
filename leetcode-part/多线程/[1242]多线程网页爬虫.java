//给你一个初始地址 startUrl 和一个 HTML 解析器接口 HtmlParser，请你实现一个 多线程的网页爬虫，用于获取与 startUrl 有 相
//同主机名 的所有链接。 
//
// 以 任意 顺序返回爬虫获取的路径。 
//
// 爬虫应该遵循： 
//
// 
// 从 startUrl 开始 
// 调用 HtmlParser.getUrls(url) 从指定网页路径获得的所有路径。 
// 不要抓取相同的链接两次。 
// 仅浏览与 startUrl 相同主机名 的链接。 
// 
//
// 
//
// 如上图所示，主机名是 example.org 。简单起见，你可以假设所有链接都采用 http 协议，并且没有指定 端口号。举个例子，链接 http://l
//eetcode.com/problems 和链接 http://leetcode.com/contest 属于同一个 主机名， 而 http://example
//.org/test 与 http://example.com/abc 并不属于同一个 主机名。 
//
// HtmlParser 的接口定义如下： 
//
// 
//interface HtmlParser {
//  // Return a list of all urls from a webpage of given url.
//  // This is a blocking call, that means it will do HTTP request and return wh
//en this request is finished.
//  public List<String> getUrls(String url);
//} 
//
// 注意一点，getUrls(String url) 模拟执行一个HTTP的请求。 你可以将它当做一个阻塞式的方法，直到请求结束。 getUrls(Strin
//g url) 保证会在 15ms 内返回所有的路径。 单线程的方案会超过时间限制，你能用多线程方案做的更好吗？ 
//
// 对于问题所需的功能，下面提供了两个例子。为了方便自定义测试，你可以声明三个变量 urls，edges 和 startUrl。但要注意你只能在代码中访问 s
//tartUrl，并不能直接访问 urls 和 edges。 
//
// 
//
// 拓展问题： 
//
// 
// 假设我们要要抓取 10000 个节点和 10 亿个路径。并且在每个节点部署相同的的软件。软件可以发现所有的节点。我们必须尽可能减少机器之间的通讯，并确保每
//个节点负载均衡。你将如何设计这个网页爬虫？ 
// 如果有一个节点发生故障不工作该怎么办？ 
// 如何确认爬虫任务已经完成？ 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：
//urls = [
//  "http://news.yahoo.com",
//  "http://news.yahoo.com/news",
//  "http://news.yahoo.com/news/topics/",
//  "http://news.google.com",
//  "http://news.yahoo.com/us"
//]
//edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
//startUrl = "http://news.yahoo.com/news/topics/"
//输出：[
//  "http://news.yahoo.com",
//  "http://news.yahoo.com/news",
//  "http://news.yahoo.com/news/topics/",
//  "http://news.yahoo.com/us"
//]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：
//urls = [
//  "http://news.yahoo.com",
//  "http://news.yahoo.com/news",
//  "http://news.yahoo.com/news/topics/",
//  "http://news.google.com"
//]
//edges = [[0,2],[2,1],[3,2],[3,1],[3,0]]
//startUrl = "http://news.google.com"
//输出：["http://news.google.com"]
//解释：startUrl 链接与其他页面不共享一个主机名。 
//
// 
//
// 提示： 
//
// 
// 1 <= urls.length <= 1000 
// 1 <= urls[i].length <= 300 
// startUrl 是 urls 中的一个。 
// 主机名的长度必须为 1 到 63 个字符（包括点 . 在内），只能包含从 “a” 到 “z” 的 ASCII 字母和 “0” 到 “9” 的数字，以及中划
//线 “-”。 
// 主机名开头和结尾不能是中划线 “-”。 
// 参考资料：https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames 
// 你可以假设路径都是不重复的。 
// 
// Related Topics 深度优先搜索 广度优先搜索 
// 👍 19 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */
class Solution {

    /**
     * 该题对于每一个新得到的待爬取的网页，都启动一个新的线程去执行爬取操作；
     * 分别维护一个已爬取的结果集与一个待爬取的url队列，新的线程从队列中拿到
     * 一个带爬取的url，然后去爬取结果并筛选出有效的结果放入待爬取集，爬取过的
     * url则加入到已爬取的结果集里；
     * 主线程负责调度所有的子线程执行新的爬取，一旦队列为空时，判断是否所有线程都执行结束了；
     * 此时有两种情况：
     * 1.队列为空可能是子线程还未爬取和处理结束，因此队列中没有元素；
     * 2.也可能是所有待爬取的url都已经被子线程拿到了，不会有新的待爬取url出现了
     * 此时需要主线程等待一段时间再次执行判断，如果队列还是空，而且所有执行任务的子线程
     * 都结束了，则说明真的没有待爬取的url了，即情况2；否则就是情况1，还需要继续执行。
     *
     * 注：结果集resultList和待爬取集crawlList是非安全的容器，所以需要锁来限制
     * 对他们的互斥访问。
     */

    // 锁，分别控制 result 和 crawl 列表的访问权限
    private ReentrantLock resultLock = new ReentrantLock();
    private ReentrantLock crawlLock = new ReentrantLock();

    // 集合，分别维护爬取完的 result 结果和待爬取的 urls
    private volatile List<String> resultList = new ArrayList();
    private volatile Queue<String> crawlList = new LinkedList();

    // 哈希表，避免爬取重复的 url
    private ConcurrentHashMap<String, Boolean> crawledUrls = new ConcurrentHashMap();

    // 当前活跃的线程数量
    private AtomicInteger acticeThreadCount = new AtomicInteger(0);

    private String hostName;

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        this.hostName = getHostName(startUrl);
        this.crawledUrls.put(startUrl, true);
        addUrlToResult(startUrl);
        addUrlToCrawl(startUrl);

        while (true) {
            String urlToCrawl = getUrlToCrawl();
            if (urlToCrawl != null) {
                // 对每个待爬取的 url，新建一个线程来处理
                acticeThreadCount.incrementAndGet();
                new Thread(() -> {
                    // 获取所有的 urls
                    List<String> dirtyUrls = htmlParser.getUrls(urlToCrawl);
                    // 删掉爬取过的，和 hostname 不符的那部分
                    List<String> cleanUrls = filterUncrawledUrls(dirtyUrls);
                    // 遍历筛选过的 urls，分别加入到结果集与待爬取集
                    for (String cleanUrl : cleanUrls) {
                        addUrlToResult(cleanUrl);
                        addUrlToCrawl(cleanUrl);
                    }
                    // 当前 url 访问全部结束，即当前线程即将结束，活跃线程数 -1
                    acticeThreadCount.decrementAndGet();
                }).start();
            } else {
                // 如果所有活跃的线程都结束了，则跳出循环
                if (acticeThreadCount.get() == 0) {
                    break;
                }
                // LockSupport.parkNanos(1L);
                // 当进入 else 代码块时，待爬取的资源为 null，有两种情况：
                // 1. 所有资源都判断结束了
                // 2. 生产者线程比消费者线程慢，主线程拿不到待爬取的 url，所以这时让主线程睡眠一会，等待生产者多生产一些
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return resultList;
    }

    // 在 url 中截取hostname
    private String getHostName(String url) {
        String hostName = url.substring(7);
        int endIndex = hostName.indexOf('/');
        if (endIndex == -1) {
            return hostName;
        } else {
            return hostName.substring(0, endIndex);
        }
    }

    // 把 url 添加到结果列表中
    private void addUrlToResult(String url) {
        this.resultLock.lock();
        try {
            this.resultList.add(url);
        } finally {
            this.resultLock.unlock();
        }
    }

    // 把 url 加入到待爬取列表中
    private void addUrlToCrawl(String url) {
        this.crawlLock.lock();
        try {
            this.crawlList.offer(url);
        } finally {
            this.crawlLock.unlock();
        }
    }

    // 从待爬取列表中获得一个url，用于爬取
    private String getUrlToCrawl() {
        this.crawlLock.lock();
        String url = null;
        try {
            if (!crawlList.isEmpty()) {
                url = this.crawlList.poll();
            }
            return url;
        } finally {
            this.crawlLock.unlock();
        }
    }

    // 筛选出列表中还未被爬取过，并且与当前hostname相符的 urls
    private List<String> filterUncrawledUrls(List<String> urls) {
        List<String> uncrawledUrls = new ArrayList();
        for (String url : urls) {
            // 先判断有没有被爬取过
            if (this.crawledUrls.containsKey(url)) {
                continue;
            }
            crawledUrls.put(url, true);
            // 再判断hostname是否符合
            String hostName = getHostName(url);
            if (this.hostName.equals(hostName)) {
                uncrawledUrls.add(url);
            }
        }

        return uncrawledUrls;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
