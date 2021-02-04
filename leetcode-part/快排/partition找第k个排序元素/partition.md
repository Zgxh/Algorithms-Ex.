

这种算法是利用快排找中枢 pivot 的 partition 操作来进行，最坏情况下与快排相同，
如果每次选取目标数字 target 都选到了数组的左右边界，而数组又恰好是有序的，
则一次partition只能排除一个元素，因此时间复杂度最坏为 `O(n^2)`.

为了避免这种情况，选择target时采用随机数，这样出现最坏情况的概率很低，平均时间复杂度为
`O(n)`。

具体做法：
1. 利用 `random()` 选出随机位置 `picked`，注意 `random()` 返回的是`[0.0, 1.0)`之间的小数，不包括
右边界，所以区间范围要 +1 即 `right-left+1`;
2. 交换选中的元素与数组右边界上的元素，以进行正常的 `partition()`;

```java
int picked = left + (int) (Math.random() * (right - left + 1));
int target = arr[picked];
int temp = target;
arr[picked] = arr[right];
arr[right] = temp;
```