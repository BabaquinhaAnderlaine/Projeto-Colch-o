public class VerificadorBeneficio {
    private static final double LIMITE_RENDA = 1621.00;

    public static boolean temDireito(Candidato candidato) {
        return candidato.getRendaMensal() <= LIMITE_RENDA;
    }

    public static String mensagemResultado(Candidato candidato) {
        if (temDireito(candidato)) {
            return "Parabéns, " + candidato.getNome() + "! Você GANHOU um colchão novo.";
        } else {
            return "Infelizmente, " + candidato.getNome() + ", você não atende ao critério de renda (até R$ 1.621,00).";
        }
    }
}