import javax.sound.midi.*;
import javax.swing.*;

import static javax.sound.midi.ShortMessage.*;

public class SequencerClass {
    protected Shape shape;
    public static void main(String[] args){
        SequencerClass obj = new SequencerClass();
        obj.play();
    }
    public void setGUI(){
        JFrame frame = new JFrame("Music Beat Generator");
        shape = new Shape();
        frame.setContentPane(shape);
        frame.setBounds(30,30,300,300);
        frame.setVisible(true);


    }

    public void play(){
        setGUI();

        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            System.out.println("Scuess");

            int[] eventsIWant = {127};
            sequencer.addControllerEventListener(shape, eventsIWant);

            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            for(int i = 5; i < 100; i += 1){
                track.add(makeEvent(NOTE_ON, 1, i, 100, i));
                track.add(makeEvent(CONTROL_CHANGE, 1, 127,0, i));
                track.add(makeEvent(NOTE_OFF,1, i, 100, i + 2));
            }

            sequencer.setSequence(seq);
            sequencer.start();
            sequencer.setTempoInBPM(120);



        } catch (MidiUnavailableException e) {
            System.out.println("I fucked up");
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
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


}
