# Unit36_QAJA35
Testing website BOOKS.RU
# Тестирование сайта (https://www.books.ru/)
В репозитории представленны тесты для вебсайта

Произведеные тесты:

1. AddToBasketTest.java
    - Проверка, что после добавления товара, на корзине появляется значок уведомления;
    - Проверка добавления в корзину выбранного товара;
    - Проверка соответствия количества добавленного товара числу на значке уведомлений;

2. BasketPageTest.java
    - Проверка, что при нажатии на кнопку ввести Код - откроется поле для ввода кода скидки;
    - Проверка, что при нажатии на кнопку Очистить корзину - товары удаляются из корзины;
    - Проверка правильного отображения суммы в корзине при увеличении количества товаров;
    - Проверка возможности отложить товар из корзиныж;
    - Проверка, что при нажатии на кнопку крестик товары из корзины удалятся;
    - Кнопка Оформить заказ вызывает страницу оформления заказа;

3. FeedbackPageTest.java
    - Проверка наличия placeholder в полях ввода формы обратной связи;
    - Проверка? что отправка сообщения без ввода капчи невозможна;

4. GetRequestOldSiteTest.java
    - Проверка кода ответа старого сайта;

5. MainPageTest.java
    - Проверка,что при нажатии кнопки выбора региона доставки открывается всплывающее окно выбора региона;
    - Проверка, что кнопка выбора местоположения позволяет выбрать нужный город и регион доставки;
    - Проверка, что кнопка ПОМОЩЬ открывает страницу со справочными материалами;
    - Проверка, что нажатие на кнопку Вход ведет на страницу регистрации и входа;
    - Проверка, что нажатие на кнопку Корзина ведет на страницу корзины;
    - Проверка, что нажатие на кнопку Обратная связь ведет на страницу Отправить сообщение;
    - Проверка что при нажатии на кнопку Перейти на старый сайт - открывается старый сайт;

6. SearchCategoryTest.java
    - Проверка правильности поиска  товара по категориям;

7. SearchTest.java
    - Проверка поиска по валидным значениям;
    - Проверяет, что при вводе невалидного значения поиск не находит товар;
    - Проверка, что найденные товары соответствуют искомой фразе;



## Использование
Запуск SUITE тестов из Terminal:
mvn clean test
Запуск Allure отчета:
mvn allure:serve


### Требования
Для установки и запуска проекта, необходимы Java, Chrome, Selenium, Junit, Allure, Maven
### Тестирование производилось на:
- OS.version=Windows 10
- Chrome.version=116.0.5845.141
- JDK.version=jdk20.0.2
- MAVEN.version=Apache Maven 3.9.3
- junit.jupiter.version=5.10
- allure-junit4.version=2.24.0
- allure-report.version=2.24.0
- selenium.version =4.11

### Установка зависимостей
Все зависимости отображены в pom.xml файле
```


## Тестирование
Проект можно запускать потестово и поклассово из среды разработки Intelliji Idea 