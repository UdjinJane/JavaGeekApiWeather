package YandexPogodaAPI;

import okhttp3.*;

import java.io.IOException;

public class Main {
    // Немного переменных.
    // X-Yandex-API-Key: d1f7066b-1213-43ab-950b-a6be055d4972.

    private static final String X_Yandex_API_Key = "d1f7066b-1213-43ab-950b-a6be055d4972";
    private static final String BASE_HOST = "api.weather.yandex.ru";
    private static final String API_VER = "v2";
    private static final String PATH_SEGMENT = "forecast";
    private static final String LAT = "59.95";
    private static final String LON = "30.16";
    private static final String LIMIT = "5";
    private static final String LANG = "ru_RU";
    private static final String EXTRA = "true";

    public static void main(String[] args) throws IOException {
        System.out.println("Домашня работа к уроку номер 6. Достаем данные из погодного сайта. Мучать будем Яндекс.Погода.");
        OkHttpClient okHttpClient = new OkHttpClient(); // С этого всё начинается.
        // Формат.
        // GET https://api.weather.yandex.ru/v2/forecast?lat=55.75396&lon=37.620393&extra=true
        // Фоормируем запрос.

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(BASE_HOST)
                .addEncodedPathSegment(API_VER)
                .addEncodedPathSegment(PATH_SEGMENT)
                .addQueryParameter("lat", LAT).addQueryParameter("lon", LON)
                .addQueryParameter("lang", LANG).addQueryParameter("limit", LIMIT)
                .addQueryParameter("extra", EXTRA)
                .build();

        System.out.println("Посмотрим на получившийся запрос и сверим его с документацией на API:\n" + url.toString());

        // А теперь самое интересное.
        Request requestHttp = new Request.Builder()
                // .addHeader("accept", "application/json") - для яндекса это лишнее.
                .addHeader("X-Yandex-API-Key", X_Yandex_API_Key)
                .url(url)
                .build();

        //****** Работаем с сервером ******
        // Получение объекта ответа от сервера
        Response response = okHttpClient.newCall(requestHttp).execute();
        String body = response.body().string();

        // Красивое выполнение и сразу ответ в стринг.
        // String body = okHttpClient.newCall(requestHttp).execute().body().string();

        // Посмотрим что ответил сервер.
        System.out.println(response.code());
        System.out.println(response.headers());
        System.out.println("Тут кешконтрол: " + response.cacheControl());
        System.out.println("Тут как прошёл запрос: " + response.isSuccessful());
        System.out.println(response.protocol());
        //****** Работаем с сервером \ ******

        System.out.println("Вот и искомое");
        System.out.println(body);

    }


}