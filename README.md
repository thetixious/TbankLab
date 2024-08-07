# TbankLab

Инструкция по запуску проекта

## 1. Клонирование репозитория

Клонируйте репозиторий на ваш локальный компьютер:

```git clone https://github.com/thetixious/TbankLab.git```

## 2. Настройте соединение с базой данной

Необходимая конфигурация представлена в файле ```application.properties```

## 3. Переход в директорию проекта

Перейдите в директорию проекта:

```cd TbankLab```
## 4. Установка зависимостей и сборка проекта

### Вариант 1: Запуск через JAR файл

1. Соберите проект и создайте JAR файл:

    ```
    ./gradlew bootjar
    ```

2. Запустите приложение:

    ```
    java -jar build/libs/my-spring-boot-app.jar
    ```

### Вариант 2: Запуск через Gradle bootRun

1. Установите зависимости и соберите проект:

    ```
    ./gradlew build
    ```
2. Запустите приложение:

    ```
    ./gradlew bootRun
    ```
## 5. Как использовать 

В приложение только один endpoint ```translate```, принимающий POST запросы с JSON  body следующего формата:

```json
{
    "sourceLanguage": "en",
    "targetLanguage": "ru",
    "textForTranslation": "Hello, world!"
	
}
```
В репозитории также представлен insomnia.json 




