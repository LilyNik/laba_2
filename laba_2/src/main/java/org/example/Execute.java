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
 double execute(String postfix_str) {
        Stack<Double> stack = new Stack<>();
        char[] chars = postfix_str.toCharArray();
        Scanner scanner = new Scanner(System.in);
        int index = 0;

        while (index < chars.length) {
            char chr = chars[index];
            is_valid_char(chr);

            if (chars[index] == '.') {
                check_for_valid_decimal_point(index, chars);
            } else if (Character.isDigit(chr) || Character.isLetter(chr) || chr == '_') {
                int left_bord = left_border(index, chars);
                String var_or_num = "";
                for (int ind = index; ind <= index + left_bord; ind++) {
                    var_or_num += chars[ind];
                }
                if (Character.isLetter(chars[index])) {
                    System.out.print("Введите значение переменной " + var_or_num + ":\n");
                    var_or_num = scanner.nextLine();
                }
                index += left_bord;
                stack.push(Double.parseDouble(var_or_num));
            } else if (PRIORITY.containsKey(chr)) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();

                switch (chr) {
                    case '+':
                        stack.push(operand1 + operand2);
                        break;
                    case '-':
                        stack.push(operand1 - operand2);
                        break;
                    case '*':
                        stack.push(operand1 * operand2);
                        break;
                    case '/':
                        if (operand2 == 0) {
                            throw new ArithmeticException("Деление на ноль");
                        }
                        stack.push(operand1 / operand2);
                        break;
                    case '^':
                        stack.push(Math.pow(operand1, operand2));
                        break;
                    default:
                        throw new IllegalArgumentException("Неверный оператор: " + chr);
                }
            }

            index++;
        }

        double answer = stack.pop();

        if (!stack.isEmpty()) {
            throw new ArithmeticException("В выражении пропущен оператор");
        }

        scanner.close();

        return answer;
    }
