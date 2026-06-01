package AceCodingInterview75Qs.trie;

/*
 *
 *
 *
1268. Search Suggestions System
Medium

You are given an array of strings products and a string searchWord.

Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

Return a list of lists of the suggested products after each character of searchWord is typed.

Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
After typing mou, mous and mouse the system suggests ["mouse","mousepad"].
Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Explanation: The only word "havana" will be always suggested while typing the search word.

Constraints:

1 <= products.length <= 1000
1 <= products[i].length <= 3000
1 <= sum(products[i].length) <= 2 * 104
All the strings of products are unique.
products[i] consists of lowercase English letters.
1 <= searchWord.length <= 1000
searchWord consists of lowercase English letters.
 *
 *
 */

import java.util.*;

public class LeetCode1268 {


    public static void main(String[] args) {

        LeetCode1268 example = new LeetCode1268();

//        System.out.println(example.singleNumber(arr1));
    }

    // 暴力法，先排序，找到searchWord每一个前缀在products里面出现的第一个位置，然后再向后找最多3个相同前缀的字符串，可以通过二分查找优化
    public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> res = new ArrayList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < searchWord.length(); i++) {
            sb.append(searchWord.charAt(i));
            String prefix = sb.toString();
            // 找到第一个前缀符合的位置，如果没找到startIdx会返回数组长度，直接跳过后面步骤添加空串进结果集
            int startIdx = findPrefixStartIndex1(products, prefix);
            List<String> suggestions = new ArrayList();
            // 往后找3个符合的
            for (int j = startIdx; j < Math.min(startIdx + 3, products.length); j++) {
                if (products[j].startsWith(prefix)) {
                    suggestions.add(products[j]);
                } else {
                    break;
                }
            }
            res.add(suggestions);

        }
        return res;
    }

    // 二分查找优化版找到第一个以该前缀开头的推荐词
    private int findPrefixStartIndex(String[] products, String prefix) {
        int left = 0;
        int right = products.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 二分查找不能使用startsWith找第一个，因为可能落在中间，需要用compareTo判断字典序大小，往字典序小的那片找
            // if (products[mid].startsWith(prefix)){
            if (products[mid].compareTo(prefix) >= 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 暴力遍历数组找到第一个以该前缀开头的推荐词
    private int findPrefixStartIndex1(String[] products, String prefix) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].startsWith(prefix)) {
                return i;
            }
        }
        // 没找到则直接返回数组长度，也可以利用这个特性方便外层调用操作
        return products.length;
    }


    // 巧妙设计双指针，效率最高也最直观，就是指针操作需要注意细节
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        // 先按照字母顺序排序，保证字典序靠前
        Arrays.sort(products);
        List<List<String>> res = new ArrayList<>();

        // 左右指针必须放外层，因为要记忆每一步的操作，不能回头，放内层会只判断当前位置的字符，忘记前面收缩的空间，出问题
        int left = 0;
        int right = products.length - 1;

        for (int i = 0; i < searchWord.length(); i++) {
            // 跑到左边第一个找到searchWork[i]的位置，如果长度比当前searchWord的遍历长度短或者不匹配则直接跳过
            while (left <= right && (products[left].length() < i + 1 || products[left].charAt(i) != searchWord.charAt(i))) {
                left++;
            }
            // 从右边找同理
            while (left <= right && (products[right].length() < i + 1 || products[right].charAt(i) != searchWord.charAt(i))) {
                right--;
            }

            // 经历过这两层while后，[left,right]区间就是符合的字符，选前3个打印
            List<String> suggest = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                // 这段代码有问题，不能操作left和right指针，因为要记忆到下一步，所以这一步的区间不能动
                // if (left <= right){
                //     suggest.add(products[left]);
                //     left++;
                // }
                // 反而用j和left+j来表示区间内的数
                if (left + j <= right) {
                    suggest.add(products[left + j]);
                }

            }
            res.add(suggest);
        }

        return res;
    }


    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        List<String> top3 = new ArrayList<>();

        // 不太需要终止flag
//        boolean isEnd = false;
        public TrieNode() {
//            isEnd = false;
            children = new TrieNode[26];
            top3 = new ArrayList<>();
        }
    }

    // 前缀树解法
    public List<List<String>> suggestedProducts1(String[] products, String searchWord) {
        // 先按字典序排序
        Arrays.sort(products);
        TrieNode root = new TrieNode();
        // 存数进前缀树
        for (String product : products) {
            TrieNode node = root;
            for (char c : product.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];

                // 每个node会有3个产品推荐，存最先遍历到node代表的字符的3个product，而product已经按照字典排序了，所以符合要求
                if (node.top3.size() < 3) {
                    node.top3.add(product);
                }
            }
        }

        List<List<String>> res = new ArrayList<>();
        // 遍历前缀树取值，取到前缀树节点的top3数组即可
        for (char c : searchWord.toCharArray()) {
            int index = c - 'a';
            root = root.children[index];
            if (root != null) {
                res.add(new ArrayList<>(root.top3));
            } else {
                break;
            }
        }

        // 手动补齐没找到的空数组
        for (int i = res.size(); i < searchWord.length(); i++) {
            res.add(new ArrayList<>());
        }

        return res;

    }


}



