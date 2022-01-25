package br.com.gedev.enums;

public enum RacaRebelde {
    HUMANO("Humano"),
    GREE("Gree"),
    RAKATA("Rakata");

    private String racaLabel;

    private RacaRebelde(String racaLabel) {
        this.racaLabel = racaLabel;
    }

    @Override
    public String toString() {
        return racaLabel;
    }
}
