package AceCodingInterview75Qs.trie;

/*
 *
 *
 *
208. Implement Trie (Prefix Tree)
Medium
Companies
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.


Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True


Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 *
 *
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode208 {


    public static void main(String[] args) {

        LeetCode208 example = new LeetCode208();

//        System.out.println(example.singleNumber(arr1));
    }


    // 类自身包装成一个节点，孩子用Map来存，一般树的通解
    class Trie {
        // 此时 Trie 类本身就是一个节点！
        boolean isEnd;
        Map<Character, Trie> children;

        // 初始化节点
        public Trie() {
            isEnd = false;
            children = new HashMap<>();
        }

        // ==========================================
        // 插入单词
        // ==========================================
        public void insert(String word) {
            Trie node = this; // this 就是当前节点（最开始调用时就是根节点）

            for (char c : word.toCharArray()) {
                // 【极其优雅的一行代码】：如果 map 里没有这个字符，就 new 一个新的 Trie 节点放进去
                node.children.putIfAbsent(c, new Trie());

                // 指针往下跳
                node = node.children.get(c);
            }
            node.isEnd = true; // 打上结束标记
        }

        // ==========================================
        // 搜索完整单词
        // ==========================================
        public boolean search(String word) {
            Trie node = this;

            for (char c : word.toCharArray()) {
                // 如果 map 里找不着这个字符，说明路断了
                node = node.children.get(c);
                if (node == null) {
                    return false;
                }
            }
            // 走完了，看看有没有结束标记
            return node.isEnd;
        }

        // ==========================================
        // 搜索前缀
        // ==========================================
        public boolean startsWith(String prefix) {
            Trie node = this;

            for (char c : prefix.toCharArray()) {
                node = node.children.get(c);
                if (node == null) {
                    return false; // 前缀断了，直接返回 false
                }
            }
            return true; // 能顺利走完，说明前缀存在
        }
    }

    // 优化版，使用26个数组代表可能的孩子节点，比map效率高
    class Trie1 {

        // 单独抽出一层TrieNode更直观
        class TrieNode {
            boolean isEnd;
            // 只有26个小写字母，所以可以考虑长26的数组来代替Map记录孩子
            TrieNode[] children;
            public TrieNode(){
                isEnd = false;
                children = new TrieNode[26];
            }
        }

        // 根节点不存数值，只记录出发点
        TrieNode root;
        // 初始化节点
        public Trie1() {
            root = new TrieNode();
        }

        // ==========================================
        // 插入单词
        // ==========================================
        public void insert(String word) {
            TrieNode node = root; // 从根节点开始顺藤摸瓜

            for (char c : word.toCharArray()) {
                int index = c - 'a'; // 计算字母的相对索引 (0-25)
                // 如果这个字母对应的分叉还没修好，就建一个新的节点
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                // 顺着这条路继续往下走
                node = node.children[index];
            }
            // 单词的所有字母都走完了，在最后一个节点打上“单词结束”的标记
            node.isEnd = true;
        }

        // ==========================================
        // 搜索完整单词
        // ==========================================
        public boolean search(String word) {
            TrieNode node = root;

            for (char c : word.toCharArray()) {
                int index = c - 'a';
                // 如果走到一半发现路断了，说明树里根本没存过这个前缀，直接返回 false
                if (node.children[index] == null) {
                    return false;
                }
                // 路还通着，继续往下走
                node = node.children[index];
            }
            // 走完了单词的所有字符。
            // 注意：这里不能直接返回 true！
            // 比如树里存了 "apple"，你搜 "app"，路是通的，走到了第二个 'p'。
            // 但是第二个 'p' 的 isEnd 是 false，说明 "app" 只是个前缀，不是个完整的单词。
            return node.isEnd;
        }

        // ==========================================
        // 搜索前缀
        // ==========================================
        public boolean startsWith(String prefix) {
            TrieNode node = root;

            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                // 前缀的路断了，返回 false
                if (node.children[index] == null) {
                    return false;
                }
                node = node.children[index];
            }
            // 只要能顺利走完 prefix 的所有字符，说明这个前缀是存在的！
            // 根本不用管最后一个节点的 isEnd 是 true 还是 false，直接返回 true。
            return true;
        }
    }




/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

}



