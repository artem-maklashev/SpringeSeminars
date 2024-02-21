Задание:
1) На базе первого примера разобранного на семинаре, добавить в один из проектов разработанных ранее spring Integration. Сохранять запросы от пользователя в файл.
2) Добавить в проект один из паттернов разобранных на лекции.

Решение:
1) 
   * Создана конфигурация [IntegrationConfig](sem5/src/main/java/ru/geekbrains/sem5/configuration/IntegrationConfig.java), 
   * создан интерфейс [TaskToFileGateway](sem5/src/main/java/ru/geekbrains/sem5/service/TaskToFileGateway.java) реализация данного интерфейса происходит в контроллере
   * запись логов происходит в файл [task_log.txt](logs/task_log.txt)
2) Реализован паттерн Observer:
   * создан класс [TaskUpdateEvent](sem5/src/main/java/ru/geekbrains/sem5/service/observer/TaskUpdateEvent.java) для создания события при обновении задачи
   * создан класс [TaskUpdateListener](sem5/src/main/java/ru/geekbrains/sem5/service/observer/TaskUpdateListener.java) для прослушивания событий при обновлении задачи
   * реализована публикация события в классе TaskService с использованием интерфейса ApplicationEventPublisher
