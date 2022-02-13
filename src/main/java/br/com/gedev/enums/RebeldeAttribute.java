package br.com.gedev.enums;

public enum RebeldeAttribute {
    NOME("Nome"),
    IDADE("Idade"),
    RACA("Raça");

    private String attributeLabel;

    RebeldeAttribute(String attributeLabel) {
        this.attributeLabel = attributeLabel;
    }

    @Override
    public String toString() {
        return attributeLabel;
    }
}
