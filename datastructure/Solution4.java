package datastructure;

import com.sun.scenario.animation.shared.ClipEnvelope;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution4 {

    public boolean isValidSign_1(String s) {
        if (s == null || s.length() == 0 || s.length() % 2 != 0) return false;

        char[] chs = s.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < chs.length; i++) {
            if (map.containsKey(chs[i])) {
                if (stack.isEmpty() || stack.peek() != map.get(chs[i])) return false;
                stack.pop();
            } else {
                stack.push(chs[i]);
            }
        }
        return stack.isEmpty();

    }


    /**
     * 【辅助计数数组】
     * @param s
     * @return
     */
    public String removeXLink_1(String s) {

        Stack<Character> chStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        int LINK_REMOVE_NUM = 2;

        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (chStack.isEmpty()) {
                chStack.push(chs[i]);
                countStack.push(1);
            } else {
                if (chStack.peek() == chs[i]) {
                    countStack.push(countStack.pop()+1);
                } else {
                    chStack.push(chs[i]);
                    countStack.push(1);
                }
                if (countStack.peek() == LINK_REMOVE_NUM) {
                    chStack.pop();
                    countStack.pop();
                }
            }
        }

        Stack<Character> outputStack = new Stack<>();
        while (!countStack.isEmpty()) {
            for (int i = 0; i < countStack.peek(); i++) {
                outputStack.push(chStack.peek());
            }
            countStack.pop();
            chStack.pop();
        }
        StringBuilder sb = new StringBuilder();
        while (!outputStack.isEmpty()) {
            sb.append(outputStack.pop());
        }
        return sb.toString();
    }

    /**
     * 纯数组
     * @param s
     * @return
     */
    public String removeXLink_2(String s) {
        return s;
    }




    //【暴力解法】，思路及实现简单，时间复杂度高o(n^2)
    // 最好情况：数组正序（27，28，29，30，31），时间复杂度o(n)
    // 最坏情况：数组倒叙（31，30，29，28，27），时间复杂度o(1/2 * n^2)
    public int[] dailyTemperatures_1(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(result));
        return result;
    }


    //【单调栈】，时间复杂度o(k*n)
    // 一般的，能用单调栈解决的，暴力法都能搞定，就是时间复杂度高一点而已
    public int[] dailyTemperatures_2(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> indexStack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!indexStack.isEmpty() && temperatures[i] > temperatures[indexStack.peek()]) {
                result[indexStack.peek()] = i - indexStack.peek();
                indexStack.pop();
            }
            indexStack.push(i);
        }

        while (!indexStack.isEmpty()) {
            temperatures[indexStack.pop()] = 0;
        }

        System.out.println(Arrays.toString(result));
        return result;
    }



    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();

        char[] chs = s.toCharArray();
        int temNum = 0;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == ' ') continue;
            if (isDigit(chs[i])) {
                temNum = temNum * 10 + (chs[i] - '0');
            }
            if (isOp(chs[i])) {
                numStack.push(temNum);
                temNum = 0;
                while (!opStack.isEmpty() && !prior(chs[i], opStack.peek()))
                    fetchAndCal(numStack, opStack);
                opStack.push(chs[i]);
            }
        }
        numStack.push(temNum);
        while (!opStack.isEmpty())
            fetchAndCal(numStack, opStack);
        return numStack.pop();
    }

    private boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }
    private boolean isOp(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }
    private boolean prior(char a, char b) {
        if ((a == '*' || a == '/') && (b == '+' || b == '-')) return true;
        return false;
    }
    private int cal(char op, int num1, int num2) {
        switch (op) {
            case '+' : return num1 + num2;
            case '-' : return num1 - num2;
            case '*' : return num1 * num2;
            case '/' : return num1 / num2;
        }
        return Integer.MIN_VALUE;
    }
    private void fetchAndCal(Stack<Integer> numStack, Stack<Character> opStack) {
        // 先出来的一定是num2
        int num2 = numStack.pop();
        int num1 = numStack.pop();
        char op = opStack.pop();
        numStack.push(cal(op, num1, num2));
    }



    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> pushedStack = new Stack<>();
        Stack<Integer> poppedStack = new Stack<>();
        for (int i = popped.length - 1; i >= 0; i--) {
            poppedStack.push(popped[i]);
        }
        for (int i = 0; i < pushed.length; i++) {
            pushedStack.push(pushed[i]);
            while (!pushedStack.isEmpty() && pushedStack.peek().equals(poppedStack.peek())) {
                pushedStack.pop();
                poppedStack.pop();
            }
        }
        return pushedStack.isEmpty();
    }

    public boolean validateStackSequences_2(int[] pushed, int[] popped) {
        Stack<Integer> calStack = new Stack<>();
        int poppedIndex = 0;
        for (int i = 0; i < pushed.length; i++) {
            calStack.push(pushed[i]);
            while (!calStack.isEmpty() && popped[poppedIndex] == calStack.peek()) {
                calStack.pop();
                poppedIndex++;
            }
        }
        return calStack.isEmpty();
    }


    /**
     * 解法一：【暴力遍历】找柱状雨量
     * 时间复杂度：o(n^2)
     * 空间复杂度：o(1)
     */
    public int trap_1(int[] height) {
        int result = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int leftMax = 0;
            int rightMax = 0;
            for (int j = 0; j < i; j++) {
                if (height[j] > leftMax) leftMax = height[j];
            }
            for (int j = i+1; j < height.length; j++) {
                if (height[j] > rightMax) rightMax = height[j];
            }
            int barCarry = (leftMax < rightMax ? leftMax : rightMax) - height[i];
            result += barCarry > 0 ? barCarry : 0;
        }
        return result;
    }

    /**
     * 解法二：【前后缀统计法】，找柱状雨量
     * 时间复杂度：o(3n)，循环三次
     * 空间复杂度：o(2n)，需要两个辅助数组
     */
    public int trap_2(int[] height) {
        int n = height.length;

        // 前缀统计
        int[] leftMax = new int[n];
        for (int i = 1; i < n - 1; i++) {
            leftMax[i] = height[i-1] > leftMax[i-1] ? height[i-1] : leftMax[i-1];
        }

        // 后缀统计
        int[] rightMax = new int[n];
        for (int i = n - 2; i > 0; i--) {
            rightMax[i] = height[i+1] > rightMax[i+1] ? height[i+1] : rightMax[i+1];
        }

        // 计算每个柱子之上承载的雨量
        int result = 0;
        for (int i = 1; i < n - 1; i++) {
            int barCarry = (leftMax[i] < rightMax[i] ? leftMax[i] : rightMax[i]) - height[i];
            result += barCarry > 0 ? barCarry : 0;
        }
        return result;
    }


    /**
     * 解法三：【单调栈】，找出每层的雨量
     * 本质跟“每日温度”一样，
     * 时间复杂度：
     * 空间复杂度：
     * @param height
     * @return
     */
//    public int trap_3(int[] height) {
//
//    }

}
