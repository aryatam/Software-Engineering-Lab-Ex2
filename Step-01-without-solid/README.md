## مرحله دوم:

| ردیف | محل اعمال تغییرات                                            | نوع تغییر                    | شرح کوتاه                                              | تعداد تغییرات |
|------|-------------------------------------------------------------|------------------------------|---------------------------------------------------------|---------------|
| 1    | `src/edu/sharif/selab/models/TelegramMessage.java`          | افزودن فایل کلاس جدید       | ایجاد فایل و تعریف `public class TelegramMessage`     | 1             |
| 2    | درون `TelegramMessage`                                       | افزودن فیلدهای جدید         | اضافه کردن `private String sourceId, targetId;`         | 2             |
| 3    | درون `TelegramMessage`                                       | افزودن سازنده (constructor) | تعریف ctor با سه پارامتر و فراخوانی `super(content)`   | 1             |
| 4    | `src/edu/sharif/selab/services/TelegramMessageService.java` | افزودن فایل کلاس جدید       | ایجاد فایل و تعریف `public class TelegramMessageService` | 1           |
| 5    | درون `TelegramMessageService`                                | override متد SMS/Email       | پیاده‌سازی `sendSmsMessage(...)` و `sendEmailMessage(...)` با `UnsupportedOperationException` | 2 |
| 6    | درون `TelegramMessageService`                                | افزودن متد `sendTelegramMessage` | متدی برای اعتبارسنجی و ارسال پیام تلگرام               | 1             |
| 7    | درون `TelegramMessageService`                                | افزودن متد `validateId`      | متدی خصوصی برای چک‌کردن non-empty بودن ID              | 1             |

## مرحله سوم: تحلیل SOLID

بر اساس تغییرات مرحله قبل، موارد تحقق و نقض هر یک از اصول SOLID (به جز SRP) را در کلاس‌های بسته `services` و کلاس `Main` بررسی کنید:

| اصل مربوطه (از SOLID)                         | موارد تحقق                                                                                                                  | موارد نقض                                                                                                                           |
|-----------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| **Single Responsibility (SRP)**               | - هر سرویس (`SmsMessageService`, `EmailMessageService`, `TelegramMessageService`) فقط وظیفهٔ ارسال یک نوع پیام را دارد.  | - اینترفیس `MessageService` ترکیبی است و دو متد مختلف (`sendSmsMessage` و `sendEmailMessage`) را با هم دارد.                           |
| **Open–Closed Principle (OCP)**               | - برای افزودن Telegram نیازی به تغییر کلاس‌های `SmsMessageService` یا `EmailMessageService` نبود.                            | - در `Main` برای پشتیبانی از Telegram تغییرات جزئی (افزودن گزینه و بلوک جدید) رخ داد.                                             |
| **Liskov Substitution Principle (LSP)**       | - درک کلیِ جایگزینی سرویس‌ها به واسط `MessageService` ممکن است صادق باشد.                                                  | - `TelegramMessageService` هنگام فراخوانی غیر از `sendTelegramMessage` استثنا پرتاب می‌کند؛ پس نمی‌توان آزادانه همه‌ی متدهای `MessageService` را با آن جایگزین کرد. |
| **Interface Segregation Principle (ISP)**     | - —                                                                                                                          | - `MessageService` همه‌ی سرویس‌ها را مجبور می‌کند هر دو متد SMS و Email را پیاده‌سازی کنند (یا استثنا بدهند).                           |
| **Dependency Inversion Principle (DIP)**      | - `Main` به رابط `MessageService` وابسته‌ است، نه به پیاده‌سازی‌های جزئی.                                                   | - در `Main` مستقیماً پیاده‌سازی‌های جزئی (`new SmsMessageService()` و …) ساخته می‌شوند، به‌جای تزریق از بیرون یا یک فکتوری.           |
