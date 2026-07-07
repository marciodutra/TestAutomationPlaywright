package br.com.sistemaescolar.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static String get(String chave) {
        return get(chave, "config.properties");
    }

    public static String get(String chave, String arquivo) {

        Properties properties = new Properties();

        try (InputStream input = Config.class.getClassLoader()
                .getResourceAsStream(arquivo)) {

            if (input == null) {
                throw new RuntimeException(
                        "Arquivo " + arquivo + " não encontrado."
                );
            }

            properties.load(input);

            return properties.getProperty(chave);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}