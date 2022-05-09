package br.com.desbravador.enums;

public enum Status {

    EM_ANALISE("BAIXO_RISCO"),
    ANALISE_REALIZADA("ANALISE_REALIZADA"),
    ANALISE_APROVADA("ANALISE_APROVADA"),
    INICIADO("INICIADO"),
    PLANEJADO("PLANEJADO"),
    EM_ANDAMENTO("EM_ANDAMENTO"),
    ENCERRADO("ENCERRADO"),
    CANCELADO("CANCELADO");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }

}
