package tr.gov.saglik.uets.api.convertor;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by fatihkocak on 25.04.2019.
 */

public class CustomRetrofitConverterFactory extends Converter.Factory {

    Gson gson;

    private CustomRetrofitConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("NullPointer Of gson object..");
        this.gson = gson;
    }

    public static CustomRetrofitConverterFactory create() {
        return create(new Gson());
    }

    public static CustomRetrofitConverterFactory create(Gson gson) {
        return new CustomRetrofitConverterFactory(gson);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (String.class.equals(type)) {
            return new Converter<ResponseBody, String>() {
                @Override
                public String convert(ResponseBody value) throws IOException {
                    return value.string();
                }
            };
        } else {
            TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
            return new CustomResponseBodyConverter<>(gson, adapter);
        }
    }

}