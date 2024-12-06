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
}
