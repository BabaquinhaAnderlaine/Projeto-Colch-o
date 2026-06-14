
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//import model.Candidato;
//import service.VerificadorBeneficio;
//import exception.*;

public class TelaCadastro extends JFrame {
    private JTextField txtNome, txtCpf, txtTelefone, txtIdade, txtRenda;
    private JTextArea areaResultado;

    public TelaCadastro() {
        setTitle("Sistema - Ganhe um Colchão Novo");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de formulário
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelForm.add(new JLabel("Nome completo:"));
        txtNome = new JTextField();
        panelForm.add(txtNome);

        panelForm.add(new JLabel("CPF (11 números):"));
        txtCpf = new JTextField();
        panelForm.add(txtCpf);

        panelForm.add(new JLabel("Telefone (10 ou 11 dígitos):"));
        txtTelefone = new JTextField();
        panelForm.add(txtTelefone);

        panelForm.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        panelForm.add(txtIdade);

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

        // Ação do botão – agora sem regras de negócio, apenas captura exceções
        btnVerificar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // 1. Verifica campos vazios
            if (txtNome.getText().trim().isEmpty() ||
                txtCpf.getText().trim().isEmpty() ||
                txtTelefone.getText().trim().isEmpty() ||
                txtIdade.getText().trim().isEmpty() ||
                txtRenda.getText().trim().isEmpty()) {
                
                JOptionPane.showMessageDialog(TelaCadastro.this,
                        "Preencher todos os campos obrigatórios.",
                        "Campos vazios",
                        JOptionPane.WARNING_MESSAGE);
                areaResultado.setText("");
                return;
            }

            // 2. Converte idade e renda (campos preenchidos, mas podem ser inválidos)
            int idade;
            double renda;
            try {
                idade = Integer.parseInt(txtIdade.getText().trim());
        
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TelaCadastro.this,
                        "Idade deve ser um número válido.\n" +
                        "Exemplo: idade = 25",
                        "Formato inválido",
                        JOptionPane.ERROR_MESSAGE);
                areaResultado.setText("");
                return;
            }

            try {
                
                renda = Double.parseDouble(txtRenda.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TelaCadastro.this,
                        "Renda deve ser um número válido.\n" +
                        "Exemplo:renda = 1621.00 ou 1621",
                        "Formato inválido",
                        JOptionPane.ERROR_MESSAGE);
                areaResultado.setText("");
                return;
            }

            // 3. Cria o candidato
            String nome = txtNome.getText().trim();
            String cpf = txtCpf.getText().trim();
            String telefone = txtTelefone.getText().trim();
            
            Candidato candidato = new Candidato(nome, cpf, telefone, idade, renda);
            
            // 4. Delega as regras de negócio para o service
            VerificadorBeneficio.validarCandidato(candidato);
            String resultado = VerificadorBeneficio.mensagemResultado(candidato);
            areaResultado.setText(resultado + "\n\n--- Dados cadastrados ---\n" + candidato);
            
        } catch (DadosObrigatoriosException ex) {
            JOptionPane.showMessageDialog(TelaCadastro.this,
                    "Dados obrigatórios: " + ex.getMessage(),
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
            areaResultado.setText("");
        } catch (IdadeInvalidaException ex) {
            JOptionPane.showMessageDialog(TelaCadastro.this,
                    "Idade inválida: " + ex.getMessage(),
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
            areaResultado.setText("");
        } catch (RendaInvalidaException ex) {
            JOptionPane.showMessageDialog(TelaCadastro.this,
                    "Renda inválida: " + ex.getMessage(),
                    "Erro de validação", JOptionPane.ERROR_MESSAGE);
            areaResultado.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(TelaCadastro.this,
                    "Erro inesperado: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            areaResultado.setText("");
        }
    }
});
    }}