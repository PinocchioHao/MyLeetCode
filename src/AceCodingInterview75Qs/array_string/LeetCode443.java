package AceCodingInterview75Qs.array_string;

/*
 *
 *
 *

443. String Compression

Medium

Given an array of characters chars, compress it using the following algorithm:

Begin with an empty string s. For each group of consecutive repeating characters in chars:

If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.

Note: The characters in the array beyond the returned length do not matter and should be ignored.



Example 1:

Input: chars = ["a","a","b","b","c","c","c"]
Output: 6
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
Example 2:

Input: chars = ["a"]
Output: 1
Explanation: The only group is "a", which remains uncompressed since it's a single character.
Example 3:

Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: 4
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".


Constraints:

1 <= chars.length <= 2000
chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.

 *
 *
 */

public class LeetCode443 {


    public static void main(String[] args) {
        char[] arr = {'a'};
        System.out.println(compress(arr));
        System.out.println(compress1(arr));
    }


    // 题目要求不能使用StringBuilder，不能使用额外的空间，所以只能在原数组上进行修改
    public static int compress(char[] chars) {
        if (chars.length == 1) return 1;
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i-1]){
                count++;
            } else {
                sb.append(chars[i-1]);
                if (count > 1){
                    sb.append(count);
                }
                count = 1;
            }
            if (i == chars.length - 1){
                sb.append(chars[i]);
                if (count > 1){
                    sb.append(count);
                }
            }

        }
        return sb.length();
    }

    // 快慢指针（读写指针）：读指针 read 用于遍历原数组，写指针 write 用于在原数组上修改。
    // 每当读指针遇到一个新的字符时，就把这个字符写入写指针的位置，并更新写指针的位置。
    // 如果这个字符出现了多次，就把出现的次数也写入写指针的位置，并更新写指针的位置。最后返回写指针的位置就是压缩后的字符串长度。
    public static int compress1(char[] chars) {
        int len = chars.length;
        int read = 0;
        int write = 0;
        while (read < len) {
            char current = chars[read];
            int count = 0;
            while (read < len && chars[read] == current) {
                count++;
                read++;
            }
            chars[write] = current;
            write++;
            // count>1才需要写入数字，如果count=1，说明这个字符没有重复，不需要写入数字
            if (count > 1) {
                String countStr = String.valueOf(count);
                for (char c : countStr.toCharArray()) {
                    chars[write] = c;
                    write++;
                }
            }
        }
        return write;
    }

}