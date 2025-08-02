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

## مرحله چهارم: پیشنهاد راهکار برای رفع نقض‌ها

| اصل مربوطه (از SOLID)                        | علت نقض                                                                                                                                          | راه حل پیشنهادی                                                                                                                                                                         |
| ---------------------------------------------:|---------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Liskov Substitution Principle (LSP)**       | سرویس‌ها در متدهای نامرتبط با `UnsupportedOperationException` استثنا پرتاب می‌کنند؛ جایگزینی کامل را غیرممکن می‌سازد.                              | تعریف Generic Interface:<br>```java<br>interface MessageService<T extends Message> { void sendMessage(T msg); }```<br>و پیاده‌سازی هر سرویس فقط برای نوع پیام خود.                        |
| **Interface Segregation Principle (ISP)**     | اینترفیس `MessageService` همه متدهای ارسال SMS و Email و Telegram را مجبور به پیاده‌سازی می‌کند؛ سرویس‌ها متدهای بلااستفاده را دارند.            | تفکیک اینترفیس:<br>```java<br>interface SmsService { void sendSms(SmsMessage m); }<br>interface EmailService { void sendEmail(EmailMessage m); }<br>interface TelegramService { void sendTelegram(TelegramMessage m); }``` |
| **Dependency Inversion Principle (DIP)**      | کلاس `Main` مستقیماً پیاده‌سازی‌ها (`new SmsMessageService()`, ...) را می‌سازد؛ وابستگی سطح بالا به جزئیات دارد.                                 | استفاده از Dependency Injection:<br>– تزریق وابستگی سرویس‌ها از طریق سازنده یا setter<br>– یا استفاده از فکتوری/IoC کانتینر برای ساخت سرویس‌ها.                                     |

## پاسخ به سؤالات پایانی

### ۱. اگر اصول شی‌گرایی از ابتدا برقرار بود و شما سرویس جدید را به پروژه اضافه می‌کردید، چند مورد از تغییرات ثبت‌شده در جدول مرحلهٔ دوم حذف می‌شد؟ (عدد آن را حتماً اعلام کنید.)

در جدول مرحلهٔ دوم مجموعاً **۷ تغییر** ثبت شد.  
اگر از آغاز اصل **Open–Closed** و تفکیک واسط‌ها رعایت شده بود، تنها کافی بود:

1. یک فایل مدل جدید به نام `TelegramMessage` اضافه شود.  
2. یک فایل سرویس جدید به نام `TelegramMessageService` پیاده‌سازی گردد.  

بنابراین فقط **۲ تغییر** لازم بود و **۵ تغییر دیگر** (افزودن فیلدها، سازنده، متدهای اضافی و بازنویسی متدها) حذف می‌شد.

---

### ۲. با توجه به آنچه که انجام دادید، در دو خط توضیح دهید که رعایت اصول شی‌گرایی چه مزایایی را برای پروژهٔ شما فراهم می‌کند؟

- **قابلیت توسعه و نگه‌داری:** با رعایت OCP و ISP، افزودن قابلیت‌های جدید بدون دست‌زدن به کد موجود انجام می‌شود؛ در نتیجه ریسک ایجاد باگ‌های جانبی پایین می‌آید و زمان توسعه کاهش می‌یابد.  
- **کاهش وابستگی و تست‌پذیری بهتر:** تفکیک واسط‌ها و تزریق وابستگی (DIP) باعث می‌شود هر ماژول مستقلاً تست و شبیه‌سازی شود؛ کد خواناتر و پشتیبانی در بلندمدت ساده‌تر خواهد بود.
