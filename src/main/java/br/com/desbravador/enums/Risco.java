package br.com.desbravador.enums;

public enum Risco {

    BAIXO("BAIXO"),
    MEDIO("MEDIO"),
    ALTO("ALTO");

    private final String risco;

    Risco(String risco) {
        this.risco = risco;
    }

    @Override
    public String toString() {
        return this.risco;
    }
}
