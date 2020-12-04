//我们提供一个类： 
//
// 
//class FooBar {
//  public void foo() {
//    for (int i = 0; i < n; i++) {
//      print("foo");
//    }
//  }
//
//  public void bar() {
//    for (int i = 0; i < n; i++) {
//      print("bar");
//    }
//  }
//}
// 
//
// 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。 
//
// 请设计修改程序，以确保 "foobar" 被输出 n 次。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = 1
//输出: "foobar"
//解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
// 
//
// 示例 2: 
//
// 
//输入: n = 2
//输出: "foobarfoobar"
//解释: "foobar" 将被输出两次。
// 
// 👍 80 👎 0


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//leetcode submit region begin(Prohibit modification and deletion)
class FooBar {
    // // volatile + synchronized
    // private int n;
    // private volatile boolean foo;
    // private Object lock;

    // public FooBar(int n) {
    //     this.n = n;
    //     this.foo = true;
    //     this.lock = new Object();
    // }

    // public void foo(Runnable printFoo) throws InterruptedException {

    //     for (int i = 0; i < n; i++) {
    //         synchronized (lock) {
    //             while (!foo) {
    //                 lock.wait();
    //             }
    //             printFoo.run();
    //             foo = false;
    //             lock.notifyAll();
    //         }
    //     }
    // }

    // public void bar(Runnable printBar) throws InterruptedException {

    //     for (int i = 0; i < n; i++) {
    //         synchronized (lock) {
    //             while (foo) {
    //                 lock.wait();
    //             }
    //             printBar.run();
    //             foo = true;
    //             lock.notifyAll();
    //         }
    //     }
    // }

    // // Semaphore
    // private int n;
    // private Semaphore foo;
    // private Semaphore bar;

    // public FooBar(int n) {
    //     this.n = n;
    //     this.foo = new Semaphore(1);
    //     this.bar = new Semaphore(0);
    // }

    // public void foo(Runnable printFoo) throws InterruptedException {

    //     for (int i = 0; i < n; i++) {
    //         foo.acquire();
    //         printFoo.run();
    //         bar.release();
    //     }
    // }

    // public void bar(Runnable printBar) throws InterruptedException {

    //     for (int i = 0; i < n; i++) {
    //         bar.acquire();
    //         printBar.run();
    //         foo.release();
    //     }
    // }

    // ReentrantLock
    private int n;
    private ReentrantLock lock;
    private Condition fooCond;
    private Condition barCond;
    private volatile boolean fooCan;

    public FooBar(int n) {
        this.n = n;
        this.lock = new ReentrantLock();
        this.fooCond = lock.newCondition();
        this.barCond = lock.newCondition();
        this.fooCan = true;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (!fooCan) {
                    fooCond.await();
                }
                printFoo.run();
            } finally {
                fooCan = false;
                barCond.signal();
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (fooCan) {
                    barCond.await();
                }
                printBar.run();
            } finally {
                fooCan = true;
                fooCond.signal();
                lock.unlock();
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
