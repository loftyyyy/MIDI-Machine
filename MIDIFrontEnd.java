import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javax.sound.midi.ShortMessage.*;

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
        JButton clearButton = new JButton("Clear Track");

        startButton.addActionListener(event -> buildTrackAndStart());
        stopButton.addActionListener(event -> sequencer.stop());
        tempoUpButton.addActionListener(event -> changeTempo(1.03f));
        tempoDownButton.addActionListener(event -> changeTempo(0.97f));
        clearButton.addActionListener(event -> clearTrack());

        buttonBox.add(startButton);
        buttonBox.add(stopButton);
        buttonBox.add(tempoUpButton);
        buttonBox.add(tempoDownButton);
        buttonBox.add(clearButton);

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

            for(int j = 0; j < 16; j++){
                JCheckBox jc = checkBoxes.get(j + 16 * i);
                if(jc.isSelected()){
                    trackList[j] = key;
                }else{
                    trackList[j] = 0;
                }
            }


            makeTrack(trackList);
            track.add(makeEvent(CONTROL_CHANGE, 1,127,0,16));


        }
        track.add(makeEvent(PROGRAM_CHANGE,9,1,0,15));

        try{
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.setTempoInBPM(120);
            sequencer.start();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static MidiEvent makeEvent(int command, int channel, int data1, int data2, int tick){
        MidiEvent event = null;

        try{

            ShortMessage msg = new ShortMessage();
            msg.setMessage(command, channel, data1, data2);
            event = new MidiEvent(msg, tick);

        }catch (Exception e){
            e.printStackTrace();

        }
        return event;
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

    public void makeTrack(int[] list){
        for(int i = 0; i < 16; i++){
            int key= list[i];

            if(key != 0){
                track.add(makeEvent(NOTE_ON, 9, key, 100, i));
                track.add(makeEvent(NOTE_OFF, 9, key, 100, i + 1));
            }
        }
    }

    public void clearTrack(){
        for(int i = 0; i < checkBoxes.size(); i++){
            checkBoxes.get(i).setSelected(false);
        }
    }
}
