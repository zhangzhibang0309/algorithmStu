package com.catalina.__leetcode__.栈;

import java.util.HashMap;
import java.util.Stack;

public class _20_有效的括号 {
    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 题目只包含三种括号{} [] ()
     * https://leetcode-cn.com/problems/valid-parentheses/
     *
     * @param s
     * @return
     */

    // new一个map
    private static HashMap<Character,Character> map = new HashMap<>();
    static {
        map.put('{','}');
        map.put('[',']');
        map.put('(',')');
    }


    // 做法简单 效率极低
    public boolean isValid1(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s = s.replace("{}", "");
            s = s.replace("[]", "");
            s = s.replace("()", "");
        }
        return s.isEmpty();
    }

    // 这里用栈
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) return false;

                // 这个地方不要直接用pop()去做下面的if判断 每做一次判断 pop()三下。。
                char l = stack.pop();
                if (l == '{' && s.charAt(i) != '}' || l == '[' && s.charAt(i) != ']' || l == '(' && s.charAt(i) != ')')
                    return false;
            }
        }

        return stack.isEmpty();
    }

    // 结合map
    public boolean isValid3(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) return false;

                // 这个地方不要直接用pop()去做下面的if判断 每做一次判断 pop()三下。。
                char l = stack.pop();
                if (map.get(l) != s.charAt(i))
                    return false;
            }
        }

        return stack.isEmpty();
    }

}
