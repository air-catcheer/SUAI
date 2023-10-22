Реализовать класс MyRandomList и MyRandomListSync (первая версия без синхронизацией, вторая с синхронизацией)
с тремя функциями: 

*void addNumber() --- добавление случайного элемента*

*void removeNumber() --- удаление элемента со случайным индексом*

*int calcZero() --- подсчет числа ненулевых элементов*

Также реализовать потоки двух видов: ПОТОКИ первого вида (MyWrite Thread) добавляют или удаляют из MRandomList случайные элементы,
ПОТОК второго вида (MyReadThread) - подсчитывает число ненулевых элементов в массиве и выводят результат на экран.

Реализовать версию сначала с синхронизацией, а потом без и показать, что при использовании версии без синхронизации возникают ошибки