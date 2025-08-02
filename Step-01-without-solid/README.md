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

## مرحله سوم: تحلیل SOLID (بدون تغییر در Main)

| اصل مربوطه (از SOLID) | موارد تحقق | موارد نقض |
| --------------------- | ---------- | ---------- |
| **Open–Closed Principle (OCP)** | افزودن کلاس‌های جدید `TelegramMessage` و `TelegramMessageService` بدون نیاز به تغییر در کدهای موجود (`Main`, `SmsMessageService`, `EmailMessageService`). | — |
| **Liskov Substitution Principle (LSP)** | — | هر سه سرویس (`SmsMessageService`, `EmailMessageService`, `TelegramMessageService`) در متدهای نامرتبط `UnsupportedOperationException` پرتاب می‌کنند؛ بنابراین شیءهای این کلاس‌ها نمی‌توانند به‌طور کامل جایگزین نوع پایهٔ `MessageService` شوند. |
| **Interface Segregation Principle (ISP)** | — | اینترفیس `MessageService` سرویس‌ها را مجبور می‌کند متدهای ارسال SMS و Email را با هم پیاده‌سازی کنند (یا استثنا بدهند)؛ در نتیجه کلاینت‌ها متدهای بلااستفاده را می‌بینند. |
| **Dependency Inversion Principle (DIP)** | متغیر `messageService` در `Main` از نوع انتزاعی `MessageService` است. | `Main` به‌جای دریافت وابستگی از بیرون، پیاده‌سازی‌های جزئی (`new SmsMessageService()` و `new EmailMessageService()`) را مستقیماً می‌سازد؛ بنابراین به کلاس‌های سطح پایین وابسته می‌شود. |
