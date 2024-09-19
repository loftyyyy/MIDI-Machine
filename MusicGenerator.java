import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static javax.sound.midi.ShortMessage.*;

public class MusicGenerator implements Serializable {
    private JFrame window = new JFrame("Music Generator");
    private transient Sequencer sequencer;
    private transient Track track;
    private transient Sequence sequence;
    private transient ArrayList<JCheckBox> checkBoxes;
    private float tempo;

    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat" ,"Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
    int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        MusicGenerator musicGenerator = new MusicGenerator();
        musicGenerator.makeGUI();
        System.out.println("Successfully Called Main Method");
    }

    public void makeGUI() {
        BorderLayout borderLayout = new BorderLayout();
        JPanel backgroundPanel = new JPanel(borderLayout);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Box buttonsBox = new Box(BoxLayout.Y_AXIS);
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton tempoUp = new JButton("Tempo Up");
        JButton tempoDown = new JButton("Tempo Down");
        JButton clearTrack = new JButton("Clear Track");
        JButton saveTrack = new JButton("Save Track");
        JButton loadTrack = new JButton("Load Track");

        buttonsBox.add(start);
        buttonsBox.add(stop);
        buttonsBox.add(tempoUp);
        buttonsBox.add(tempoDown);
        buttonsBox.add(clearTrack);
        buttonsBox.add(saveTrack);
        buttonsBox.add(loadTrack);

        start.addActionListener(event -> buildAndStartTrack());
        stop.addActionListener(event -> sequencer.stop());
        tempoUp.addActionListener(event -> changeTempo(1.09f));
        tempoDown.addActionListener(event -> changeTempo(0.97f));
        clearTrack.addActionListener(event -> clearTrack());
        saveTrack.addActionListener(event -> saveTrack());
        loadTrack.addActionListener(event -> loadTrack());

        Box instrumentLabel = new Box(BoxLayout.Y_AXIS);
        for (String instrumentName : instrumentNames) {
            JLabel label = new JLabel(instrumentName);
            label.setBorder(BorderFactory.createEmptyBorder(4, 1, 4, 1));
            instrumentLabel.add(label);
        }

        // Updated grid layout to 16x64 (16 instruments, 64 steps per instrument)
        GridLayout grid = new GridLayout(16, 64);
        grid.setHgap(1);
        grid.setVgap(2);

        JPanel gridPanel = new JPanel(grid);
        checkBoxes = new ArrayList<>();

        // 16 instruments * 64 steps = 1024 checkboxes
        for (int i = 0; i < 1024; i++) {
            JCheckBox c = new JCheckBox();
            gridPanel.add(c);
            checkBoxes.add(c);
        }

        backgroundPanel.add(BorderLayout.CENTER, gridPanel);
        backgroundPanel.add(BorderLayout.WEST, instrumentLabel);
        backgroundPanel.add(BorderLayout.EAST, buttonsBox);

        setUpMidi();

        window.getContentPane().add(backgroundPanel);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setBounds(50, 50, 800, 400);  // Adjusted window size for 64 steps
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void changeTempo(float multiplier) {
        float tempoFactor = sequencer.getTempoFactor() * multiplier;
        tempo = tempoFactor;
        sequencer.setTempoFactor(tempoFactor);
        System.out.println(tempo);
    }

    public void setTempo(float multiplier) {
        sequencer.setTempoFactor(multiplier);
        System.out.println(multiplier);
    }

    public void buildAndStartTrack() {
        int[] trackList;

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i < 16; i++) {
            trackList = new int[64];  // Updated to 64 steps
            int key = instruments[i];

            for (int j = 0; j < 64; j++) {  // Looping through 64 steps
                if (checkBoxes.get(j + 64 * i).isSelected()) {  // Adjust for 64 steps
                    trackList[j] = key;
                } else {
                    trackList[j] = 0;
                }
            }

            makeTrack(trackList);
            track.add(makeEvent(CONTROL_CHANGE, 1, 127, 0, 64));  // Updated to 64 steps
        }
        track.add(makeEvent(PROGRAM_CHANGE, 9, 1, 0, 63));  // Updated to 64 steps

        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeTrack(int[] list) {
        for (int i = 0; i < 64; i++) {  // Updated to 64 steps
            int key = list[i];
            if (key != 0) {
                track.add(makeEvent(NOTE_ON, 9, key, 100, i));
                track.add(makeEvent(NOTE_OFF, 9, key, 100, i + 1));
            }
        }
    }

    public MidiEvent makeEvent(int command, int channel, int data1, int data2, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage msg = new ShortMessage();
            msg.setMessage(command, channel, data1, data2);
            event = new MidiEvent(msg, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    public void clearTrack() {
        for (JCheckBox checkBox : checkBoxes) {
            checkBox.setSelected(false);
        }
        sequencer.stop();
    }

    public void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTrack() {
        try {
            FileInputStream fileIn = new FileInputStream("track.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // Read the states from the file
            boolean[] trackStates = (boolean[]) in.readObject();
            in.close();
            fileIn.close();

            // Set the checkbox states
            for (int i = 0; i < checkBoxes.size(); i++) {
                checkBoxes.get(i).setSelected(trackStates[i]);
            }
            System.out.println("Track loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveTrack() {
        try {
            FileOutputStream fileOut = new FileOutputStream("track.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            // Convert checkbox states to an array
            boolean[] trackStates = new boolean[checkBoxes.size()];
            for (int i = 0; i < checkBoxes.size(); i++) {
                trackStates[i] = checkBoxes.get(i).isSelected();
            }

            // Write the states to the file
            out.writeObject(trackStates);
            out.close();
            fileOut.close();
            System.out.println("Track saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
