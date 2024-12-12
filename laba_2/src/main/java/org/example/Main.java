package org.example;

public class Main {
    public static void main(String[] args) {
        Execute exec = Execute.get_instance();
        String prefix_str = exec.infix_to_postfix("(A + B) * C - (D / 2)");
        System.out.print(prefix_str);
        System.out.print("\n");
        System.out.println(exec.execute(prefix_str));
    }

}
