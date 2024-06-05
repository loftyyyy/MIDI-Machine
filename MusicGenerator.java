import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javax.sound.midi.ShortMessage.*;

public class MusicGenerator {
    private Sequencer sequencer;
    private Track track;
    private Sequence sequence;
    private ArrayList<JCheckBox> checkBoxes;

    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat" ,"Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
    int[] instruments = {35,42,46,38,49,39,50,60,70, 72,64,56,58,47,67,63};
    public static void main(String[] args){
        MusicGenerator musicGenerator = new MusicGenerator();
        musicGenerator.makeGUI();

    }

    public void makeGUI(){
        JFrame window = new JFrame("Music Generator");
        BorderLayout borderLayout = new BorderLayout();
        JPanel backgroundPanel = new JPanel(borderLayout);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        Box buttonsBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton tempoUp = new JButton("Tempo Up");
        JButton tempoDown = new JButton("Tempo Down");
        JButton clearTrack = new JButton("Clear Track");

        buttonsBox.add(start);
        buttonsBox.add(stop);
        buttonsBox.add(tempoUp);
        buttonsBox.add(tempoDown);
        buttonsBox.add(clearTrack);


        start.addActionListener(event -> buildAndStartTrack());
        stop.addActionListener(event -> sequencer.stop());
        tempoUp.addActionListener(event -> changeTempo(1.09f));
        tempoDown.addActionListener(event -> changeTempo(0.97f));
        clearTrack.addActionListener(event -> clearTrack());

        Box instrumentLabel = new Box(BoxLayout.Y_AXIS);
        for(String instrumentName: instrumentNames){
            JLabel label = new JLabel(instrumentName);
            label.setBorder(BorderFactory.createEmptyBorder(4,1,4,1));
            instrumentLabel.add(label);
        }

        GridLayout grid = new GridLayout(16,16);
        grid.setHgap(1);
        grid.setVgap(2);

        JPanel gridPanel = new JPanel(grid);
        checkBoxes = new ArrayList<>();
        for(int i = 0; i < 256; i++){
            JCheckBox c = new JCheckBox();
            gridPanel.add(c);
            checkBoxes.add(c);
        }



        backgroundPanel.add(BorderLayout.CENTER,gridPanel);
        backgroundPanel.add(BorderLayout.WEST,instrumentLabel);
        backgroundPanel.add(BorderLayout.EAST,buttonsBox);


        setUpMidi();

        window.getContentPane().add(backgroundPanel);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setBounds(50,50,300,300);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void changeTempo(float multiplier){
        float tempoFactor = sequencer.getTempoFactor() * multiplier;

        sequencer.setTempoFactor(tempoFactor);

    }

    public void buildAndStartTrack(){
        int[] trackList;

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for(int i = 0; i < 16; i++){
            trackList = new int[16];
            int key = instruments[i];

            for(int j = 0; j < 16; j++){
                if(checkBoxes.get(j + 16 * i).isSelected()){
                    trackList[j] = key;
                }else{
                    trackList[j] = 0;
                }

            }

            makeTrack(trackList);
            track.add(makeEvent(CONTROL_CHANGE, 1, 127, 0, 16));
        }
        track.add(makeEvent(PROGRAM_CHANGE, 9, 1, 0, 15));

        try{

            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    public void makeTrack(int[] list){

        for(int i = 0; i < 16; i++){
            int key = list[i];
            if(key != 0){
                track.add(makeEvent(NOTE_ON,9,key,100, i));
                track.add(makeEvent(NOTE_OFF,9,key,100, i + 1));
            }
        }

    }

    public MidiEvent makeEvent(int command, int channel, int data1, int data2, int tick){
        MidiEvent event = null;
        try{

            ShortMessage msg = new ShortMessage();
            msg.setMessage(command,channel,data1,data2);
            event = new MidiEvent(msg,tick);

        }catch(Exception e){
            e.printStackTrace();
        }


        return event;

    }

    public void clearTrack(){
        for(JCheckBox checkBox: checkBoxes){
            checkBox.setSelected(false);
        }
        sequencer.stop();
    }


    public void setUpMidi(){
        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);

            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
