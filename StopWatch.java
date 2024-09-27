package StopWatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.print.attribute.standard.MediaSizeName.C;

public class StopWatch extends JFrame implements ActionListener {

    private JLabel timeLabel;
    private JPanel buttonPanel;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;

    private Timer timer;
    private long starttime;
    private boolean isRunning;


    public StopWatch() {
        setTitle("Abdullah");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Times new Roman", Font.BOLD, 37));
        add(timeLabel, BorderLayout.CENTER);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        buttonPanel = new JPanel(new GridLayout(1, 3));

        buttonPanel.setBackground(Color.LIGHT_GRAY);
        startButton = new JButton("Start");
        startButton.setBackground(Color.DARK_GRAY);
        startButton.setForeground(Color.WHITE);
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");
        stopButton.setBackground(Color.DARK_GRAY);
        stopButton.setForeground(Color.WHITE);
        resetButton.setBackground(Color.DARK_GRAY);
        resetButton.setForeground(Color.WHITE);

        ImageIcon startIcon= new ImageIcon(new ImageIcon("C:\\Users\\student\\IdeaProjects\\StopWatch\\src\\OIP.jfif").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        ImageIcon stopIcon= new ImageIcon(new ImageIcon("C:\\Users\\student\\IdeaProjects\\StopWatch\\src\\OIP(1).jfif").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        ImageIcon resetIcon= new ImageIcon(new ImageIcon("C:\\Users\\student\\IdeaProjects\\StopWatch\\src\\download.jfif").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));

        startButton.setIcon(startIcon);



        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        resetButton.addActionListener(this);


        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);


        add(buttonPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, this);
        starttime = 0;
        isRunning = false;

        stopButton.setEnabled(false);
        resetButton.setEnabled(false);


    }

    private void Start() {
        if (!isRunning) {
            starttime = System.currentTimeMillis() - starttime;
            timer.start();
            isRunning = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            resetButton.setEnabled(true);
        }
    }

    private void Stop() {
        if (isRunning) {
            timer.stop();
            isRunning = false;
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
    }

    private void Reset() {
        Stop();
        starttime = 0;
        timeLabel.setText("00:00:00");
    }

    private void Update() {
        long elapsed = System.currentTimeMillis() - starttime;
        int seconds = (int) (elapsed / 1000) % 60;
        int minutes = (int) (elapsed / (1000 * 60) )% 60;
        int hours = (int) (elapsed / (1000 * 60 * 60))%24;
        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }


    public static void main(String[] args) {

        new StopWatch().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            Start();
        } else if (e.getSource() == stopButton) {
            Stop();
        } else if (e.getSource() == timer) {
            Update();
        }


        else if(e.getSource()==resetButton)

    {
        Reset();
    }

}
}

