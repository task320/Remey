package operator.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConverterJson {
    /**
     * 引数のクラスへ、Jsonを変換する
     * @param body リクエストパラメータ
     * @param target 変換先クラス
     * @param <T>　変換先クラス
     * @return
     */
    public static <T> T jsonToObjectDefault(String body, T target){
        Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
        T object = (T) gson.fromJson(body, target.getClass());

        return object;
    }

    /**
     * 対象クラスをJsonへ変換する
     * @param target 変換対象クラス
     * @param <T>　変換対象クラス
     * @return
     */
    public static <T> String objectToJson(T target){
        Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
        return new Gson().toJson(target);
    }
}
