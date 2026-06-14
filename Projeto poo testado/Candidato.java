

public class Candidato extends Pessoa {
    private double rendaMensal;

    public Candidato(String nome, String cpf, String telefone, int idade, double rendaMensal) {
        super(nome, cpf, telefone, idade);
        this.rendaMensal = rendaMensal;
    }

    public double getRendaMensal() { return rendaMensal; }
    public void setRendaMensal(double rendaMensal) { this.rendaMensal = rendaMensal; }

    @Override
    public String toString() {
        return String.format( "| Nome: %s \n| CPF: %s \n| Telefone: %s \n| Idade: %d \n| Renda: R$ %.2f",
                nome, cpf, telefone, idade, rendaMensal);
    }
}