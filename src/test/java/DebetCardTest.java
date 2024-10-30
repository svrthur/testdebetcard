import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DebetCardTest {

    @Test
    void shouldTestSuccess() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Дмитрий Логинов - Зернов");
        $("[data-test-id=phone] input").setValue("+79290234567");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestNameEnglishLetters() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Dmitry");
        $("[data-test-id=phone] input").setValue("+79290234567");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestNameNumbers() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Дмитри3");
        $("[data-test-id=phone] input").setValue("+79290134567");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldTestNameSymbols() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("=Дмитрий");
        $("[data-test-id=phone] input").setValue("+79290234567");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldTestPhoneSymbols() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Дмитрий");
        $("[data-test-id=phone] input").setValue("++7929023456");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldTestPhoneSpace() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Дмитрий");
        $("[data-test-id=phone] input").setValue("+7929 23456");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldTestPhoneLettersOInsteadOf0() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Дмитрий");
        $("[data-test-id=phone] input").setValue("+7929O23456");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldTestPhone10Digits() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Дмитрий");
        $("[data-test-id=phone] input").setValue("+7929O23456");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldTestPhoneSymbolBeforeNumber() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Дмитрий");
        $("[data-test-id=phone] input").setValue("=79290234567");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}

