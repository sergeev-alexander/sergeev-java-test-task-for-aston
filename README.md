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

- `common` — общие интерфейсы и реализации ввода/вывода
- `app1` — сравнение и арифметика двух целых чисел
- `app2` — сравнение двух строк
- `app3` — вывод чётных чисел из массива

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