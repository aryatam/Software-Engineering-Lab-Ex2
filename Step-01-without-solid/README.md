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

| اصل مربوطه (از SOLID)                         | موارد تحقق                                                                                                        | موارد نقض                                                                                                                      |
|:----------------------------------------------|:------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------------------------------------:|
| **Open–Closed Principle (OCP)**               | افزودن پشتیبانی از Telegram بدون تغییر در `SmsMessageService` یا `EmailMessageService`.                          | در `Main` برای پشتیبانی از Telegram مجبور به تغییر و اضافه‌کردن گزینه و بلوک جدید شده‌ایم.                                    |
| **Liskov Substitution Principle (LSP)**       | پیاده‌سازی سرویس‌های مختلف با یک اینترفیس اشتراکی (`MessageService`) قابل درک است.                              | `TelegramMessageService` تنها متد `sendTelegramMessage` را دارد و متدهای دیگر را با Exception پاسخ می‌دهد؛ لذا نمی‌توان جایگزین آزادانه کرد. |
| **Interface Segregation Principle (ISP)**     | —                                                                                                                  | اینترفیس `MessageService` تمام سرویس‌ها را مجبور می‌کند متدهای `sendSmsMessage` و `sendEmailMessage` را پیاده‌سازی یا با استثنا رد کنند. |
| **Dependency Inversion Principle (DIP)**      | وابستگی `Main` به اینترفیس `MessageService` است، نه به پیاده‌سازی‌های جزئی.                                      | در `Main` مستقیماً با `new SmsMessageService()` و غیره پیاده‌سازی‌ها ساخته می‌شوند به‌جای تزریق از طریق سازنده یا فکتوری.         |
