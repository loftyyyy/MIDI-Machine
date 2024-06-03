import javax.sound.midi.*;

import static javax.sound.midi.ShortMessage.*;

public class SequencerClass implements ControllerEventListener {
    public static void main(String[] args){
        SequencerClass obj = new SequencerClass();
        obj.play();
    }

    public void play(){
        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            System.out.println("Scuess");

            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            ShortMessage firstMessage = new ShortMessage();
            firstMessage.setMessage(PROGRAM_CHANGE, 1, 44, 100);
            MidiEvent changeInstrument = new MidiEvent(firstMessage, 1);
            track.add(changeInstrument);

            ShortMessage piano44 = new ShortMessage();
            piano44.setMessage(NOTE_ON, 1,44,100);
            MidiEvent piano444 = new MidiEvent(piano44,1);
            track.add(piano444);

            ShortMessage piano66 = new ShortMessage();
            piano44.setMessage(NOTE_OFF, 1,44,100);
            MidiEvent piano666 = new MidiEvent(piano66,1);
            track.add(piano666);

            sequencer.setSequence(seq);
            sequencer.start();



        } catch (MidiUnavailableException e) {
            System.out.println("I fucked up");
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void controlChange(ShortMessage event) {
        System.out.println("Hi");
    }
}
