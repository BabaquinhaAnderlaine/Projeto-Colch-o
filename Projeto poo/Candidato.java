public class Candidato extends Pessoa {
    private double rendaMensal;

    public Candidato(String nome, String cpf, String telefone, double rendaMensal) {
        super(nome, cpf, telefone);
        this.rendaMensal = rendaMensal;
    }

    public double getRendaMensal() { return rendaMensal; }
    public void setRendaMensal(double rendaMensal) { this.rendaMensal = rendaMensal; }
}