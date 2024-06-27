### Hexlet tests and linter status:
[![Actions Status](https://github.com/Grad566/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Grad566/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/a6706b3276008aef9817/maintainability)](https://codeclimate.com/github/Grad566/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/a6706b3276008aef9817/test_coverage)](https://codeclimate.com/github/Grad566/java-project-78/test_coverage)
[![myTest](https://github.com/Grad566/java-project-78/actions/workflows/myTest.yml/badge.svg)](https://github.com/Grad566/java-project-78/actions/workflows/myTest.yml)

Data-validator - библиотека, с помощью которой можно проверять корректность любых данных.

## Использование
Создать объект класса Validator, выбрать тип данных и настроить валидацию. 

Пример использования:
```
Validator v = new Validator();

// Строки
StringSchema schema = v.string().required();

schema.isValid("what does the fox say"); // true
schema.isValid(""); // false

// Числа
NumberSchema schema = v.number().required().positive();

schema.isValid(-10); // false
schema.isValid(10); // true

// Объект Map с поддержкой проверки структуры
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "");
human2.put("age", null);
schema.isValid(human2); // false
```

## Требования
 - jdk 20
 - gradle 8.5

Дополнительные команды:
```
make build 
```

CheckStyle и тесты запускаются через ./gradlew <команда>
