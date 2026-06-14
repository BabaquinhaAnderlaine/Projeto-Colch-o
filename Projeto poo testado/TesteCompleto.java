// TesteCompleto.java - Versão simplificada sem dependências externas
public class TesteCompleto {
    
    private static int testesPassaram = 0;
    private static int testesFalharam = 0;
    
    public static void main(String[] args) {
        System.out.println("=== INICIANDO TESTES DO SISTEMA ===\n");
        
        testarCandidatoValido();
        testarCandidatoComNomeVazio();
        testarCandidatoComNomeNull();
        testarCandidatoComCpfInvalido();
        testarCandidatoComCpfComLetras();
        testarCandidatoComTelefoneInvalido();
        testarIdadeMenorQuePermitida();
        testarIdadeMaiorQuePermitida();
        testarRendaNegativa();
        testarTemDireitoBeneficio();
        testarRendaAcimaDoLimite();
        testarRendaExatamenteNoLimite();
        testarValidacaoCompletaDadosValidos();
        testarCpfFronteira();
        testarTelefoneFronteira();
        
        System.out.println("\n=== RESULTADO FINAL ===");
        System.out.println("Testes passaram: " + testesPassaram);
        System.out.println("Testes falharam: " + testesFalharam);
        System.out.println("Total: " + (testesPassaram + testesFalharam));
        
        if (testesFalharam == 0) {
            System.out.println("\n✅ TODOS OS TESTES PASSARAM!");
        } else {
            System.out.println("\n❌ ALGUNS TESTES FALHARAM!");
        }
    }
    
    private static void assertTrue(boolean condicao, String mensagem) {
        if (condicao) {
            System.out.println("  ✅ " + mensagem);
            testesPassaram++;
        } else {
            System.out.println("  ❌ " + mensagem);
            testesFalharam++;
        }
    }
    
    private static void assertFalse(boolean condicao, String mensagem) {
        assertTrue(!condicao, mensagem);
    }
    
    private static void assertEquals(Object esperado, Object atual, String mensagem) {
        if (esperado == null && atual == null) {
            assertTrue(true, mensagem + " (esperado: null, atual: null)");
        } else if (esperado != null && esperado.equals(atual)) {
            assertTrue(true, mensagem + " (esperado: " + esperado + ", atual: " + atual + ")");
        } else {
            assertTrue(false, mensagem + " (esperado: " + esperado + ", atual: " + atual + ")");
        }
    }
    
    // Teste 1: Candidato válido
    private static void testarCandidatoValido() {
        System.out.println("\n📋 Teste 1: Criar candidato com dados válidos");
        try {
            Candidato candidato = new Candidato("João Silva", "12345678901", "11999999999", 25, 1500.00);
            VerificadorBeneficio.validarCandidato(candidato);
            assertTrue(true, "Candidato válido criado com sucesso");
            assertEquals("João Silva", candidato.getNome(), "Nome correto");
            assertEquals("12345678901", candidato.getCpf(), "CPF correto");
            assertEquals(25, candidato.getIdade(), "Idade correta");
        } catch (Exception e) {
            assertTrue(false, "Não deveria lançar exceção: " + e.getMessage());
        }
    }
    
    // Teste 2: Nome vazio
    private static void testarCandidatoComNomeVazio() {
        System.out.println("\n📋 Teste 2: Tentar criar candidato com nome vazio");
        try {
            Candidato candidato = new Candidato("", "12345678901", "11999999999", 25, 1500.00);
            VerificadorBeneficio.validarCandidato(candidato);
            assertTrue(false, "Deveria lançar exceção para nome vazio");
        } catch (DadosObrigatoriosException e) {
            assertTrue(true, "Exceção capturada corretamente: " + e.getMessage());
        } catch (Exception e) {
            assertTrue(false, "Tipo de exceção incorreta: " + e.getClass().getSimpleName());
        }
    }
    
    // Teste 3: Nome null
    private static void testarCandidatoComNomeNull() {
        System.out.println("\n📋 Teste 3: Tentar criar candidato com nome null");
        try {
            Candidato candidato = new Candidato(null, "12345678901", "11999999999", 25, 1500.00);
            VerificadorBeneficio.validarCandidato(candidato);
            assertTrue(false, "Deveria lançar exceção para nome null");
        } catch (DadosObrigatoriosException e) {
            assertTrue(true, "Exceção capturada corretamente: " + e.getMessage());
        } catch (Exception e) {
            assertTrue(false, "Tipo de exceção incorreta");
        }
    }
    
    // Teste 4: CPF inválido (menos dígitos)
    private static void testarCandidatoComCpfInvalido() {
        System.out.println("\n📋 Teste 4: Tentar criar candidato com CPF inválido (menos dígitos)");
        try {
            Candidato candidato = new Candidato("João Silva", "12345", "11999999999", 25, 1500.00);
            VerificadorBeneficio.validarCandidato(candidato);
            assertTrue(false, "Deveria lançar exceção para CPF inválido");
        } catch (DadosObrigatoriosException e) {
            assertTrue(true, "Exceção capturada corretamente: " + e.getMessage());
        } catch (Exception e) {
            assertTrue(false, "Tipo de exceção incorreta");
        }
    }
    
    // Teste 5: CPF com letras
    private static void testarCandidatoComCpfComLetras() {
        System.out.println("\n📋 Teste 5: Tentar criar candidato com CPF contendo letras");
        try {
            Candidato candidato = new Candidato("João Silva", "1234567890A", "11999999999", 25, 1500.00);
            VerificadorBeneficio.validarCandidato(candidato);
            assertTrue(false, "Deveria lançar exceção para CPF com letras");
        } catch (DadosObrigatoriosException e) {
            assertTrue(true, "Exceção capturada corretamente: " + e.getMessage());
        } catch (Exception e) {
            assertTrue(false, "Tipo de exceção incorreta");
        }
    }
    
    // Teste 6: Telefone inválido
    private static void testarCandidatoComTelefoneInvalido() {
        System.out.println("\n📋 Teste 6: Tentar criar candidato com telefone inválido");
        try {
            Candidato candidato = new Candidato("João Silva", "12345678901", "123", 25, 1500.00);
            VerificadorBeneficio.validarCandidato(candidato);
            assertTrue(false, "Deveria lançar exceção para telefone inválido");
        } catch (DadosObrigatoriosException e) {
            assertTrue(true, "Exceção capturada corretamente: " + e.getMessage());
        } catch (Exception e) {
            assertTrue(false, "Tipo de exceção incorreta");
        }
    }
    
    // Teste 7: Idade menor que 18
    private static void testarIdadeMenorQuePermitida() {
        System.out.println("\n📋 Teste 7: Tentar criar candidato com idade menor que 18");
        try {
            Candidato candidato = new Candidato("João Silva", "12345678901", "11999999999", 17, 1500.00);
            VerificadorBeneficio.validarCandidato(candidato);
            assertTrue(false, "Deveria lançar exceção para idade 17 anos");
        } catch (IdadeInvalidaException e) {
            assertTrue(true, "Exceção capturada corretamente: " + e.getMessage());
        } catch (Exception e) {
            assertTrue(false, "Tipo de exceção incorreta");
        }
    }
    
    // Teste 8: Idade maior que 120
    private static void testarIdadeMaiorQuePermitida() {
        System.out.println("\n📋 Teste 8: Tentar criar candidato com idade maior que 120");
        try {
            Candidato candidato = new Candidato("João Silva", "12345678901", "11999999999", 121, 1500.00);
            VerificadorBeneficio.validarCandidato(candidato);
            assertTrue(false, "Deveria lançar exceção para idade 121 anos");
        } catch (IdadeInvalidaException e) {
            assertTrue(true, "Exceção capturada corretamente: " + e.getMessage());
        } catch (Exception e) {
            assertTrue(false, "Tipo de exceção incorreta");
        }
    }
    
    // Teste 9: Renda negativa
    private static void testarRendaNegativa() {
        System.out.println("\n📋 Teste 9: Tentar criar candidato com renda negativa");
        try {
            Candidato candidato = new Candidato("João Silva", "12345678901", "11999999999", 25, -100.00);
            VerificadorBeneficio.validarCandidato(candidato);
            assertTrue(false, "Deveria lançar exceção para renda negativa");
        } catch (RendaInvalidaException e) {
            assertTrue(true, "Exceção capturada corretamente: " + e.getMessage());
        } catch (Exception e) {
            assertTrue(false, "Tipo de exceção incorreta");
        }
    }
    
    // Teste 10: Verificar se tem direito ao benefício
    private static void testarTemDireitoBeneficio() {
        System.out.println("\n📋 Teste 10: Verificar direito ao benefício com renda abaixo do limite");
        try {
            Candidato candidato = new Candidato("João Silva", "12345678901", "11999999999", 25, 1000.00);
            boolean temDireito = VerificadorBeneficio.temDireito(candidato);
            assertTrue(temDireito, "Candidato com renda R$ 1000,00 tem direito ao benefício");
        } catch (Exception e) {
            assertTrue(false, "Não deveria lançar exceção: " + e.getMessage());
        }
    }
    
    // Teste 11: Renda acima do limite
    private static void testarRendaAcimaDoLimite() {
        System.out.println("\n📋 Teste 11: Verificar candidato com renda acima do limite");
        try {
            Candidato candidato = new Candidato("João Silva", "12345678901", "11999999999", 25, 2000.00);
            boolean temDireito = VerificadorBeneficio.temDireito(candidato);
            assertFalse(temDireito, "Candidato com renda R$ 2000,00 NÃO tem direito ao benefício");
        } catch (Exception e) {
            assertTrue(false, "Não deveria lançar exceção: " + e.getMessage());
        }
    }
    
    // Teste 12: Renda exatamente no limite
    private static void testarRendaExatamenteNoLimite() {
        System.out.println("\n📋 Teste 12: Verificar candidato com renda exatamente no limite");
        try {
            Candidato candidato = new Candidato("João Silva", "12345678901", "11999999999", 25, 1621.00);
            boolean temDireito = VerificadorBeneficio.temDireito(candidato);
            assertTrue(temDireito, "Candidato com renda exatamente R$ 1621,00 tem direito ao benefício");
        } catch (Exception e) {
            assertTrue(false, "Não deveria lançar exceção: " + e.getMessage());
        }
    }
    
    // Teste 13: Validação completa com dados válidos
    private static void testarValidacaoCompletaDadosValidos() {
        System.out.println("\n📋 Teste 13: Validação completa com dados válidos");
        Candidato candidato = new Candidato("Maria Santos", "98765432100", "21988888888", 30, 1500.00);
        
        try {
            VerificadorBeneficio.validarCandidato(candidato);
            String mensagem = VerificadorBeneficio.mensagemResultado(candidato);
            assertTrue(mensagem.contains("GANHOU"), "Mensagem deve conter 'GANHOU'");
            assertTrue(mensagem.contains("Maria Santos"), "Mensagem deve conter o nome");
        } catch (Exception e) {
            assertTrue(false, "Não deveria lançar exceção: " + e.getMessage());
        }
    }
    
    // Teste 14: CPF no limite (11 dígitos)
    private static void testarCpfFronteira() {
        System.out.println("\n📋 Teste 14: Testar CPF com exatamente 11 dígitos");
        try {
            Candidato candidato = new Candidato("Teste", "00000000000", "11999999999", 25, 1000.00);
            VerificadorBeneficio.validarCandidato(candidato);
            assertTrue(true, "CPF com 11 dígitos (zero) é válido");
        } catch (Exception e) {
            assertTrue(false, "CPF válido não deveria lançar exceção: " + e.getMessage());
        }
    }
    
    // Teste 15: Telefone no limite (10 e 11 dígitos)
    private static void testarTelefoneFronteira() {
        System.out.println("\n📋 Teste 15: Testar telefone com 10 e 11 dígitos");
        
        // Teste com 10 dígitos
        try {
            Candidato candidato = new Candidato("Teste", "12345678901", "1199999999", 25, 1000.00);
            VerificadorBeneficio.validarCandidato(candidato);
            assertTrue(true, "Telefone com 10 dígitos é válido");
        } catch (Exception e) {
            assertTrue(false, "Telefone com 10 dígitos deveria ser válido: " + e.getMessage());
        }
        
        // Teste com 11 dígitos
        try {
            Candidato candidato = new Candidato("Teste", "12345678901", "11999999999", 25, 1000.00);
            VerificadorBeneficio.validarCandidato(candidato);
            assertTrue(true, "Telefone com 11 dígitos é válido");
        } catch (Exception e) {
            assertTrue(false, "Telefone com 11 dígitos deveria ser válido: " + e.getMessage());
        }
    }
}