import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MIDIFrontEnd {
    private ArrayList<JCheckBox> checkBoxes;
    private Sequencer sequencer;
    private Sequence sequence;
    private Track track;

    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat" ,"Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
    int[] instruments = {35,42,46,38,49,39,50,60,70, 72,64,56,58,47,67,63};

    public static void main(String[] args){

        MIDIFrontEnd midiFrontEnd = new MIDIFrontEnd();
        midiFrontEnd.createGUI();
    }

    public void createGUI(){
        JFrame window = new JFrame("MIDI Machine");
        BorderLayout borderLayout = new BorderLayout();
        JPanel backgroundPanel = new JPanel(borderLayout);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton tempoUpButton = new JButton("Tempo Up");
        JButton tempoDownButton = new JButton("Tempo Down");

        startButton.addActionListener(event -> MakeTrack());
        stopButton.addActionListener(event -> sequencer.stop());
        tempoUpButton.addActionListener(event -> changeTempo(1.03f));
        tempoDownButton.addActionListener(event -> changeTempo(0.97f));

        buttonBox.add(startButton);
        buttonBox.add(stopButton);
        buttonBox.add(tempoUpButton);
        buttonBox.add(tempoDownButton);

        Box nameBox = new Box(BoxLayout.Y_AXIS);

        for(String instrumentName : instrumentNames){
            JLabel instrumentLabel = new JLabel(instrumentName);
            instrumentLabel.setBorder(BorderFactory.createEmptyBorder(4,1,4,1));
            nameBox.add(instrumentLabel);
        }

        backgroundPanel.add(BorderLayout.EAST, buttonBox);
        backgroundPanel.add(BorderLayout.WEST, nameBox);

        window.getContentPane().add(backgroundPanel);

        GridLayout grid = new GridLayout(16,16);
        grid.setVgap(1);
        grid.setHgap(2);

        JPanel mainPanel = new JPanel(grid);
        backgroundPanel.add(BorderLayout.CENTER,mainPanel);

        checkBoxes = new ArrayList<>();
        for(int i = 0; i < 256; i++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkBoxes.add(c);
            mainPanel.add(c);
        }

        setUpMidi();

        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setBounds(50,50,300,300);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private void buildTrackAndStart(){
        int[] trackList;
        sequence.deleteTrack(track);

        track = sequence.createTrack();

        for(int i = 0; i < 16; i++){
            trackList = new int[16];
            int key = instruments[i];


        }

    }
    private void changeTempo(float tempoMultiplier){
        float tempoFactor = sequencer.getTempoFactor();
        sequencer.setTempoFactor(tempoFactor * tempoMultiplier);
    }
    private void setUpMidi(){

        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();

            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void MakeTrack(){


    }
}
