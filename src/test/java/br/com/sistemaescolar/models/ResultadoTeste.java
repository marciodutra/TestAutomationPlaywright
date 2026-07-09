package br.com.sistemaescolar.models;

import java.time.LocalDateTime;

public class ResultadoTeste {

    private final String nomeTeste;
    private final String status;
    private final String screenshot;
    private final String log;
    private final LocalDateTime dataHora;

    public ResultadoTeste(
            String nomeTeste,
            String status,
            String screenshot,
            String log,
            LocalDateTime dataHora
    ) {

        this.nomeTeste = nomeTeste;
        this.status = status;
        this.screenshot = screenshot;
        this.log = log;
        this.dataHora = dataHora;

    }

    public String getNomeTeste() {
        return nomeTeste;
    }

    public String getStatus() {
        return status;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public String getLog() {
        return log;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

}