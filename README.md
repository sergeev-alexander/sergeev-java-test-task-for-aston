# sergeev-java-test-task-for-aston

> Сергеев Александр
> 
> a79164220703@gmail.com

## Тестовое задание для Aston

### Стек

- Java 11
- Maven (multi-module)
- JUnit 5, AssertJ, Mockito

### Структура

- `common` - общие интерфейсы и реализации ввода/вывода
- `app1` - сравнение и арифметика двух целых чисел
- `app2` - сравнение двух строк
- `app3` - вывод чётных чисел из массива

### Сборка и запуск

```bash
mvn clean install
```

Запуск приложений:

```bash
mvn -pl app1 exec:java -Dexec.mainClass="alexander.sergeev.app1.App1"
mvn -pl app2 exec:java -Dexec.mainClass="alexander.sergeev.app2.App2"
mvn -pl app3 exec:java -Dexec.mainClass="alexander.sergeev.app3.App3"
```

### Запуск тестов

```bash
mvn test
```

---

P.S.: Я понимаю, что руководствуясь принципами KISS и YAGNI, можно было выполнить это задание тремя one-liner'ами:

```java
public class App1 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Stream.of(new long[2])
                    .peek(arr -> {
                        System.out.print("Enter integer a: ");
                        arr[0] = sc.nextLong();
                        System.out.print("Enter integer b: ");
                        arr[1] = sc.nextLong();
                        if (arr[1] == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                    })
                    .flatMap(arr -> Stream.of(
                            arr[0] > arr[1] ? "a > b" : arr[0] < arr[1] ? "a < b" : "a = b",
                            Math.addExact(arr[0], arr[1]),
                            Math.subtractExact(arr[0], arr[1]),
                            Math.multiplyExact(arr[0], arr[1]),
                            (double) arr[0] / arr[1]
                    ))
                    .forEach(System.out::println);
        } catch (InputMismatchException | ArithmeticException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

public class App2 {
    
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Stream.<String[]>of(new String[2])
                    .peek(arr -> {
                        System.out.print("Enter first string: ");
                        arr[0] = sc.nextLine();
                        System.out.print("Enter second string: ");
                        arr[1] = sc.nextLine();
                    })
                    .map(arr -> arr[0].equals(arr[1]) ? "Строки идентичны" : "Строки неидентичны")
                    .forEach(System.out::println);
        }
    }
}

public class App3 {

    public static void main(String[] args) {
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);
    }
}
```
Но так хотелось сделать красиво, что я не удержался 🙂

Прошу не расценивать мою работу как over-engineering.

Спасибо!