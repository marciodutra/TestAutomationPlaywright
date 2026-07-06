package br.com.sistemaescolar.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties properties = new Properties();

    static {

        try (InputStream input = Config.class.getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("Arquivo config.properties não encontrado.");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o arquivo de configuração.", e);
        }
    }

    public static String get(String chave) {
        return properties.getProperty(chave);
    }
}