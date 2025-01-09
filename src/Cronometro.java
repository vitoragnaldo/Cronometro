import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cronometro extends JFrame {
    private int tempo;  // Armazena o tempo em segundos
    private Timer timer;  // Timer para atualizar o cronômetro
    private boolean emExecucao;  // Indica se o cronômetro está em execução
    private JLabel labelTempo;  // Exibe o tempo no painel

    // Construtor
    public Cronometro() {
        tempo = 0;
        emExecucao = false;

        // Configuração da janela
        setTitle("Cronômetro");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centraliza a janela

        // Painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        // Label que exibe o tempo
        labelTempo = new JLabel("00:00", SwingConstants.CENTER);
        labelTempo.setFont(new Font("Serif", Font.PLAIN, 40));
        painel.add(labelTempo, BorderLayout.CENTER);

        // Botões: Iniciar, Pausar, Reiniciar
        JPanel painelBotoes = new JPanel();
        JButton botaoIniciar = new JButton("Iniciar");
        JButton botaoPausar = new JButton("Pausar");
        JButton botaoReiniciar = new JButton("Reiniciar");

        // Botão iniciar
        botaoIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciar();
            }
        });

        // Botão pausar
        botaoPausar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pausar();
            }
        });

        // Botão reiniciar
        botaoReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciar();
            }
        });

        painelBotoes.add(botaoIniciar);
        painelBotoes.add(botaoPausar);
        painelBotoes.add(botaoReiniciar);

        painel.add(painelBotoes, BorderLayout.SOUTH);
        add(painel);

        // Timer para atualizar o cronômetro a cada 100 milissegundos
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (emExecucao) {
                    tempo++;
                    atualizarTempo();
                }
            }
        });
    }

    // Iniciar o cronômetro
    private void iniciar() {
        if (!emExecucao) {
            emExecucao = true;
            timer.start();
        }
    }

    // Pausar o cronômetro
    private void pausar() {
        if (emExecucao) {
            emExecucao = false;
            timer.stop();
        }
    }

    // Reiniciar o cronômetro
    private void reiniciar() {
        emExecucao = false;
        tempo = 0;
        timer.stop();
        atualizarTempo();
    }

    // Atualizar o tempo exibido
    private void atualizarTempo() {
        int minutos = tempo / 60;
        int segundos = tempo % 60;
        labelTempo.setText(String.format("%02d:%02d", minutos, segundos));
    }

    // Método principal para rodar a aplicação
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Cronometro().setVisible(true);
            }
        });
    }
}
