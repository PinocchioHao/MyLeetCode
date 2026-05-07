package AceCodingInterview75Qs.stack;

/*
 *
 *
 *

735. Asteroid Collision

Medium

We are given an array asteroids of integers representing asteroids in a row. The indices of the asteroid in the array represent their relative position in space.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.



Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
Example 4:

Input: asteroids = [3,5,-6,2,-1,4]​​​​​​​
Output: [-6,2,4]
Explanation: The asteroid -6 makes the asteroid 3 and 5 explode, and then continues going left. On the other side, the asteroid 2 makes the asteroid -1 explode and then continues going right, without reaching asteroid 4.


Constraints:

2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0

 *
 *
 */


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class LeetCode735 {


    public static void main(String[] args) {
        int[] arr = {10, 2, -5};
        System.out.println(asteroidCollision(arr));
    }


    // 正数向右负数向左，只有当前位置为负数，上一位置为正数，才会相撞
    // 如果相撞，则持续对栈进行操作，弹出栈顶元素直到当前被消灭或者把左边陨石消灭完
    // 注意当前元素在判断时还没入栈，需要跟栈里面的做比较
    // 当前陨石默认存活，如果发生碰撞，则根据是否存活判断是否入栈
    public static int[] asteroidCollision(int[] asteroids) {
        // 记录
        Deque<Integer> deque = new ArrayDeque<>();
        for (int a : asteroids) {
            boolean isAlive = true;
            // 判断碰撞，只有栈非空，栈顶元素>0，当前元素<0
            while (isAlive && deque.size()>0 && deque.peekLast()>0 && a < 0) {
                // 当前向左的陨石更大，向左消灭
                if (deque.peekLast() + a < 0) {
                    deque.removeLast();
                } else if (deque.peekLast() + a == 0){
                    // 同样大就同时被消灭
                    deque.removeLast();
                    isAlive = false;
                } else {
                    // 当前更小，直接被消灭
                    isAlive = false;
                }
            }
            // 经历碰撞后如果还存活，则入栈
            if (isAlive) {
                deque.addLast(a);
            }

        }

        // 直接操作效率更高
        int[] res = new int[deque.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = deque.removeFirst();
        }
        return res;

        // stream影响效率
//        return deque.stream().mapToInt(a -> a).toArray();


    }



}
