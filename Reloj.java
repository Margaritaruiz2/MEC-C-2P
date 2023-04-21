import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reloj extends JFrame implements ActionListener {
    private JLabel labelHora;
    private JButton botonIniciar, botonDetener;
    private JSlider sliderVelocidad;
    private Timer timer;
    private int delay;

    public Reloj() {
        super("Reloj");

        JPanel panelHora = new JPanel();
        labelHora = new JLabel();
        labelHora.setFont(new Font("Arial", Font.PLAIN, 48));
        panelHora.add(labelHora);

        JPanel panelBotones = new JPanel();
        botonIniciar = new JButton("Iniciar");
        botonIniciar.addActionListener(this);
        panelBotones.add(botonIniciar);

        botonDetener = new JButton("Detener");
        botonDetener.addActionListener(this);
        panelBotones.add(botonDetener);

        JPanel panelSlider = new JPanel();
        sliderVelocidad = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
        sliderVelocidad.setMajorTickSpacing(200);
        sliderVelocidad.setMinorTickSpacing(100);
        sliderVelocidad.setPaintTicks(true);
        sliderVelocidad.setPaintLabels(true);
        panelSlider.add(new JLabel("Velocidad:"));
        panelSlider.add(sliderVelocidad);

        Container container = getContentPane();
        container.add(panelHora, BorderLayout.CENTER);
        container.add(panelBotones, BorderLayout.SOUTH);
        container.add(panelSlider, BorderLayout.NORTH);

        delay = 1000;
        timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                Date fecha = new Date();
                labelHora.setText(dateFormat.format(fecha));
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonIniciar) {
            if (!timer.isRunning()) {
                delay = 1000 / sliderVelocidad.getValue();
                timer.setDelay(delay);
                timer.start();
                botonIniciar.setEnabled(false);
                botonDetener.setEnabled(true);
            }
        } else if (e.getSource() == botonDetener) {
            if (timer.isRunning()) {
                timer.stop();
                botonIniciar.setEnabled(true);
                botonDetener.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        Reloj reloj = new Reloj();
        reloj.setSize(400, 200);
        reloj.setLocationRelativeTo(null);
        reloj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reloj.setVisible(true);
    }
}
