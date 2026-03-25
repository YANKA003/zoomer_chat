# ZChat - Стабильная версия

Мессенджер с оффлайн поддержкой и расширенными настройками.

## Что исправлено

1. **Добавлен Application класс** - ZChatApp.kt для инициализации Firebase
2. **Обработка ошибок** - try-catch во всех критических местах
3. **Null safety** - безопасная работа с nullable типами
4. **Логирование** - все ошибки логируются для отладки
5. **Безопасная навигация** - проверки перед переходами между экранами

## Как собрать APK

### Шаг 1: Создать проект на GitHub

1. Зайдите на https://github.com/new
2. Назовите репозиторий `zchat-stable`
3. Создайте репозиторий

### Шаг 2: Загрузить код

```bash
cd zchat-stable
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/ВАШ_ЮЗЕРНЕЙМ/zchat-stable.git
git push -u origin main
```

### Шаг 3: Добавить Firebase конфигурацию

**Важно!** Без этого приложение не будет работать!

1. Откройте Firebase Console: https://console.firebase.google.com/
2. Выберите ваш проект
3. Скачайте `google-services.json`:
   - Нажмите на шестерёнку → Настройки проекта
   - Внизу нажмите "Скачать google-services.json"
4. Добавьте в GitHub Secrets:
   - Откройте репозиторий → Settings → Secrets and variables → Actions
   - Нажмите "New repository secret"
   - Name: `GOOGLE_SERVICES_JSON`
   - Value: **Base64 содержимое файла**

Чтобы получить Base64 содержимое файла:
```bash
# На Linux/Mac
base64 -w 0 google-services.json

# Или на Windows (PowerShell)
[Convert]::ToBase64String([IO.File]::ReadAllBytes("google-services.json"))
```

### Шаг 4: Запустить сборку

1. Откройте репозиторий на GitHub
2. Перейдите в Actions
3. Выберите "Build ZChat APK"
4. Нажмите "Run workflow"
5. Дождитесь завершения (5-10 минут)
6. Скачайте APK из Artifacts

## Функции

- ✅ Регистрация и вход через Firebase
- ✅ Список пользователей
- ✅ Чаты с оффлайн поддержкой
- ✅ Настройки аккаунта
- ✅ Настройки чатов (фон, анимации)
- ✅ Приватность (статус онлайн, блокировка)
- ✅ Уведомления
- ✅ Режим энергосбережения
- ✅ Premium функции
- ✅ Обнаружение VPN

## Firebase настройки

Обязательно включите в Firebase Console:

1. **Authentication** → Sign-in method → Email/Password → **Включить**
2. **Realtime Database** → Создать базу → Начать в тестовом режиме

## Структура проекта

```
app/src/main/java/com/zchat/app/
├── ZChatApp.kt           # Application класс (инициализация Firebase)
├── data/
│   ├── Repository.kt     # Репозиторий данных
│   ├── local/            # Локальное хранение (Room, SharedPreferences)
│   ├── remote/           # Firebase сервисы
│   └── model/            # Модели данных
└── ui/
    ├── auth/             # Авторизация
    ├── chats/            # Чаты
    └── settings/         # Настройки
```

## Минимальные требования

- Android 7.0 (API 24)
- ~20MB свободного места

## Решение проблем

### "Operation not allowed"
Включите Email/Password в Firebase Console → Authentication

### Приложение вылетает
1. Проверьте, что google-services.json добавлен правильно
2. Проверьте логи через `adb logcat | grep ZChat`
3. Убедитесь, что Firebase правила разрешают чтение/запись

### Нет пользователей
Добавьте тестовых пользователей через Firebase Console или зарегистрируйте несколько аккаунтов
