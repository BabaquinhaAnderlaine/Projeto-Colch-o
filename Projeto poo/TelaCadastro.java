import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame {
    private JTextField txtNome, txtCpf, txtTelefone, txtRenda;
    private JTextArea areaResultado;

    public TelaCadastro() {
        setTitle("Sistema - Ganhe um Colchão Novo");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de formulário
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelForm.add(new JLabel("Nome completo:"));
        txtNome = new JTextField();
        panelForm.add(txtNome);

        panelForm.add(new JLabel("CPF (apenas números):"));
        txtCpf = new JTextField();
        panelForm.add(txtCpf);

        panelForm.add(new JLabel("Telefone (ex: 11999999999):"));
        txtTelefone = new JTextField();
        panelForm.add(txtTelefone);

        panelForm.add(new JLabel("Renda mensal (R$):"));
        txtRenda = new JTextField();
        panelForm.add(txtRenda);

        JButton btnVerificar = new JButton("Verificar direito ao colchão");
        panelForm.add(btnVerificar);

        add(panelForm, BorderLayout.NORTH);

        // Área de resultado
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultado"));
        add(scroll, BorderLayout.CENTER);

        // Ação do botão com validação e tratamento de erros
        btnVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validação de campos vazios
                    if (txtNome.getText().trim().isEmpty() ||
                        txtCpf.getText().trim().isEmpty() ||
                        txtTelefone.getText().trim().isEmpty() ||
                        txtRenda.getText().trim().isEmpty()) {
                        throw new Exception("Todos os campos são obrigatórios.");
                    }

                    String nome = txtNome.getText().trim();
                    String cpf = txtCpf.getText().trim();
                    String telefone = txtTelefone.getText().trim();

                    // Validação simples de CPF (apenas dígitos e 11 caracteres)
                    if (!cpf.matches("\\d{11}")) {
                        throw new Exception("CPF inválido. Digite exatamente 11 números.");
                    }

                    // Validação de telefone (apenas dígitos, mínimo 10)
                    if (!telefone.matches("\\d{10,11}")) {
                        throw new Exception("Telefone inválido. Digite 10 ou 11 dígitos (DDD + número).");
                    }

                    double renda;
                    try {
                        renda = Double.parseDouble(txtRenda.getText().trim());
                        if (renda < 0) throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        throw new Exception("Renda inválida. Digite um valor numérico positivo (ex: 1250.00).");
                    }

                    // Cria o candidato usando herança
                    Candidato candidato = new Candidato(nome, cpf, telefone, renda);

                    // Verifica direito e exibe mensagem
                    String mensagem = VerificadorBeneficio.mensagemResultado(candidato);
                    areaResultado.setText(mensagem + "\n\n--- Dados cadastrados ---\n" +
                            "Nome: " + candidato.getNome() + "\n" +
                            "CPF: " + candidato.getCpf() + "\n" +
                            "Telefone: " + candidato.getTelefone() + "\n" +
                            "Renda: R$ " + String.format("%.2f", candidato.getRendaMensal()));

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(TelaCadastro.this,
                            "Erro: " + ex.getMessage(),
                            "Validação de dados",
                            JOptionPane.ERROR_MESSAGE);
                    areaResultado.setText(""); // limpa área em caso de erro
                }
            }
        });
    }
}