package balancefy.api.resources.enums;

public enum TypeTransaction {
    IN("Entrada"),
    OUT("Saida");

    public String type;
    TypeTransaction(String type){
        this.type = type;
    }
}
