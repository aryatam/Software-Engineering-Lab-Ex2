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
| ---: | ---: | ---: |
| **Open–Closed Principle (OCP)** | افزودن کلاس‌های جدید `TelegramMessage` و `TelegramMessageService` بدون نیاز به تغییر در فایل‌های موجود. | — |
| **Liskov Substitution Principle (LSP)** | — | سرویس‌ها در متدهای نامرتبط `UnsupportedOperationException` پرتاب می‌کنند؛ لذا جایگزینی کامل به‌عنوان `MessageService` ممکن نیست. |
| **Interface Segregation Principle (ISP)** | — | رابط `MessageService` سرویس‌ها را وادار به پیاده‌سازی متدهای بلااستفاده می‌کند. |
| **Dependency Inversion Principle (DIP)** | متغیّر `messageService` در `Main` از نوع انتزاعی است. | `Main` به‌جای تزریق وابستگی، پیاده‌سازی‌ها را مستقیماً با `new` می‌سازد. |
