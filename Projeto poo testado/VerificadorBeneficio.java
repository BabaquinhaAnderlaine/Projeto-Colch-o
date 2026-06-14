
public class VerificadorBeneficio {
    private static final double LIMITE_RENDA = 1621.00;
    private static final int IDADE_MINIMA = 18;
    private static final int IDADE_MAXIMA = 120;

    // Valida todos os dados do candidato
    public static void validarCandidato(Candidato c) 
            throws DadosObrigatoriosException, IdadeInvalidaException, RendaInvalidaException {
        
        // Valida campos obrigatórios
        if (c.getNome() == null || c.getNome().trim().isEmpty())
            throw new DadosObrigatoriosException("Nome é obrigatório.");
        
        if (c.getCpf() == null || !c.getCpf().matches("\\d{11}"))
            throw new DadosObrigatoriosException("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
        
        if (c.getTelefone() == null || !c.getTelefone().matches("\\d{10,11}"))
            throw new DadosObrigatoriosException("Telefone inválido. Deve conter 10 ou 11 dígitos numéricos (DDD + número).");
        
        // Valida idade
        if (c.getIdade() < IDADE_MINIMA || c.getIdade() > IDADE_MAXIMA)
            throw new IdadeInvalidaException("Idade deve estar entre " + IDADE_MINIMA + " e " + IDADE_MAXIMA + " anos.");
        
        // Valida renda
        if (c.getRendaMensal() < 0)
            throw new RendaInvalidaException("A renda mensal não pode ser negativa.");

        
    }

    // Verifica se o candidato tem direito ao benefício (já faz a validação completa)
    public static boolean temDireito(Candidato c) 
            throws DadosObrigatoriosException, IdadeInvalidaException, RendaInvalidaException {
        validarCandidato(c);
        return c.getRendaMensal() <= LIMITE_RENDA;
    }

    // Retorna mensagem amigável para exibição na interface (sem lançar exceção para o usuário)
    public static String mensagemResultado(Candidato c) {
        try {
            if (temDireito(c)) {
                return "Parabéns, " + c.getNome() + "! Você GANHOU um colchão novo.";
            } else {
                return "Infelizmente, " + c.getNome() + ", sua renda mensal (R$ " + 
                    String.format("%.2f", c.getRendaMensal()) + 
                    ") excede o limite de R$ " + String.format("%.2f", LIMITE_RENDA) + ".";
            }
        } catch (DadosObrigatoriosException | IdadeInvalidaException | RendaInvalidaException e) {
            return "Erro de validação: " + e.getMessage();
        }
    }
}