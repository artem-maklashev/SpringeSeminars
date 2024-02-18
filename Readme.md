Задание: По примерам показанным на семинаре:
1) Подключить к своему проекту зависимости actuator, registry-prometheus и micrometer.
2) Установить и подключить к проекту prometheus
3) Установить и подключить Grafana. В Grafana добавить пару точеу контроля( Например: процессоное время приложения и количество запросов)
   Формат сдачи: проект с добавленными зависимостями, файл настройки prometheus и скриншот Grafana с добавленными контрольными точками.
  
Задание со звездочкой:
- Проделать, то же самое с многомодульным проектом(добавить под контроль несколько модулей)
- Добавить собственную метрику.


Запуск Prometheus:  
```docker run -d -p 9090:9090 --name=prometheus -v $PWD/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus```

Запуск Grafana:
``` docker run -d -p 3000:3000 --name=grafana grafana/grafana```

[Скриншот Grafana](grafana_screenshot.png)