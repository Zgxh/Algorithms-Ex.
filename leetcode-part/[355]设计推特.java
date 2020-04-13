//设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个
//功能： 
//
// 
// postTweet(userId, tweetId): 创建一条新的推文 
// getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
// 
// follow(followerId, followeeId): 关注一个用户 
// unfollow(followerId, followeeId): 取消关注一个用户 
// 
//
// 示例: 
//
// 
//Twitter twitter = new Twitter();
//
//// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
//twitter.postTweet(1, 5);
//
//// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
//twitter.getNewsFeed(1);
//
//// 用户1关注了用户2.
//twitter.follow(1, 2);
//
//// 用户2发送了一个新推文 (推文id = 6).
//twitter.postTweet(2, 6);
//
//// 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
//// 推文id6应当在推文id5之前，因为它是在5之后发送的.
//twitter.getNewsFeed(1);
//
//// 用户1取消关注了用户2.
//twitter.unfollow(1, 2);
//
//// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
//// 因为用户1已经不再关注用户2.
//twitter.getNewsFeed(1);
// 
// Related Topics 堆 设计 哈希表


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Twitter {

    /**
     * 每个用户维护一个数据结构，包含自己userId，关注的人Set<userId>，自己的推文列表List<tweetId>。
     * 维护一个hashMap存放所有的用户。
     * 每条推文有自己对应的index，表示发推文的时间。
     * 查询某用户的近10条动态时，维护一个大顶堆，把自己的推文和关注的人的推文都放进去，按所有文章的index排序，然后弹出最近的10条。
     */

    Map<Integer, User> users; // 存放已注册的用户Id
    int index;

    /** Initialize your data structure here. */
    public Twitter() {
        users = new HashMap<>();
        index = 0;
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        User user = users.get(userId);
        if (user == null) {
            user = new User(userId);
            users.put(userId, user);
        }
        user.tweets.add(new Tweet(tweetId, index++));
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        User user = users.get(userId);
        if (user == null) {
            return result;
        }
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((o1, o2) -> -o1.index + o2.index);
        maxHeap.addAll(user.tweets);
        for (int followeeId : user.followees) {
            maxHeap.addAll(users.get(followeeId).tweets);
        }
        int count = 0;
        while (!maxHeap.isEmpty() && count < 10) {
            result.add(maxHeap.poll().tweetId);
            count++;
        }
        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        User follower = users.get(followerId);
        if (follower == null) {
            follower = new User(followerId);
            users.put(followerId, follower);
        }
        if (followerId == followeeId) { // 避免自己关注自己
            return;
        }
        User followee = users.get(followeeId);
        if (followee == null) {
            followee = new User(followeeId);
            users.put(followeeId, followee);
        }
        follower.followees.add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        User follower = users.get(followerId);
        if (follower == null) {
            return;
        }
        follower.followees.remove(followeeId);
    }
}

class User {
    int userId;
    Set<Integer> followees; // 关注的人
    List<Tweet> tweets; // 自己发过的tweet推文

    User(int userId) {
        this.userId = userId;
        followees = new HashSet<>();
        tweets = new ArrayList<>();
    }
}

class Tweet {
    int tweetId;
    int index; // 发文时的时间

    public Tweet(int tweetId, int index) {
        this.tweetId = tweetId;
        this.index = index;
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
//leetcode submit region end(Prohibit modification and deletion)
