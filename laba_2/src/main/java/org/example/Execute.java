public class Execute {

    private static Execute instance;
    private static final HashMap<Character, Integer> PRIORITY;

    static {
        PRIORITY = new HashMap<>();
        PRIORITY.put('(', 0);
        PRIORITY.put('+', 1);
        PRIORITY.put('-', 1);
        PRIORITY.put('*', 2);
        PRIORITY.put('/', 2);
        PRIORITY.put('^', 3);
        PRIORITY.put(')', 0);
    }

    /**
     * Приватный конструктор для реализации паттерна Singleton.
     */
    private Execute() {
    }

    /**
     * Получает экземпляр класса Execute (Singleton).
     *
     * @return экземпляр Execute
     */
    public static Execute getInstance() {
        if (instance == null)
            instance = new Execute();
        return instance;
    }

    String infixToPostfix(String infixStr) {
        char[] chars = infixStr.toCharArray();
        String postfixStr = "";
        Stack<Character> stack = new Stack<>();
        int index = 0;

        while (index < chars.length) {
            char chr = chars[index];

            if (chars[index] == '.') {
                checkForValidDecimalPoint(index, chars);
            } else if (Character.isDigit(chr) || Character.isLetter(chr) || chr == '_') {
                int leftBorder = leftBorder(index, chars);
                String varOrNum = "";
                for (int ind = index; ind <= index + leftBorder; ind++) {
                    varOrNum += chars[ind];
                }
                postfixStr += varOrNum + " ";

                index += leftBorder;
            } else if (chr == '(') {
                stack.push('(');
            } else if (chr == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    postfixStr += stack.pop();
                stack.pop();
            } else if (isOperator(chr)) {
                while (!stack.isEmpty() && PRIORITY.get(stack.peek()) >= PRIORITY.get(chr))
                    postfixStr += stack.pop();
                stack.push(chr);
            }

            index++;
        }

        while (!stack.isEmpty()) {
            postfixStr += stack.pop();
        }

        return postfixStr.trim(); // Удаляем лишний пробел в конце
    }
}
