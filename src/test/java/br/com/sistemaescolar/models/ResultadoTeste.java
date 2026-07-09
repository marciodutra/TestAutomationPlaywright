package br.com.sistemaescolar.models;

public class ResultadoTeste {

    private final String nomeTeste;
    private final String status;
    private final String screenshot;
    private final String log;

    public ResultadoTeste(
            String nomeTeste,
            String status,
            String screenshot,
            String log
    ) {

        this.nomeTeste = nomeTeste;
        this.status = status;
        this.screenshot = screenshot;
        this.log = log;

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

}