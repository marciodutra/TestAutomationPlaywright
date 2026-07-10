package br.com.sistemaescolar.models;

import java.time.LocalDateTime;

public class ResultadoTeste {

    private final String nomeTeste;
    private final String status;
    private final String screenshot;
    private final String log;
    private final LocalDateTime dataHora;
    private final long tempoExecucao;


    public ResultadoTeste(
            String nomeTeste,
            String status,
            String screenshot,
            String log,
            LocalDateTime dataHora,
            long tempoExecucao
    ) {

        this.nomeTeste = nomeTeste;
        this.status = status;
        this.screenshot = screenshot;
        this.log = log;
        this.dataHora = dataHora;
        this.tempoExecucao = tempoExecucao;

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

    public long getTempoExecucao() {
        return tempoExecucao;
    }

}