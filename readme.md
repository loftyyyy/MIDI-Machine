# MIDI Music Generator

## Overview

This Java application provides a simple and intuitive interface for creating rhythmic MIDI music patterns. Users can select from a variety of drum instruments and visually construct beats on a 16x16 grid. The generator then plays the created pattern in a loop, allowing for real-time experimentation and modification. This was a test project from the book Head First Java 3rd Edition.

## Features

* **Visual Grid Interface:** Easily design drum patterns using a grid layout.
* **Instrument Selection:** Choose from a variety of drum sounds (e.g., Bass Drum, Snare, Hi-Hat).
* **Tempo Control:** Adjust the playback tempo to your liking.
* **Start/Stop:** Control playback of the generated music.
* **Save/Load:** Preserve and load your custom drum patterns for later use.
* **Clear Track:** Reset the grid to create a new pattern.

## How to Use

1. **Clone or Download:** Get the project files onto your local machine.
2. **Compile and Run:** Compile the Java code and execute the `MusicGenerator` class.
3. **Interface:**
    * **Grid:** Each checkbox in the grid represents a step in the pattern. Click to activate/deactivate the sound for that step.
    * **Instruments:** Labels on the left indicate the available drum instruments.
    * **Buttons:** Use the buttons on the right to control playback, tempo, and saving/loading.
4. **Create Music:** Design your patterns by toggling checkboxes in the grid.
5. **Play:** Click "Start" to begin playback. Use "Tempo Up/Down" to adjust the speed.
6. **Save/Load:** Click "Save Track" to save your pattern, and "Load Track" to reload a saved pattern.

## Implementation Details

* **MIDI:** Uses the Java Sound API (`javax.sound.midi`) to generate and play MIDI events.
* **GUI:** Built with Swing components for the user interface.
* **Serialization:**  Leverages Java serialization to save and load drum patterns.

## Code Structure

* `MusicGenerator`: Main class containing the application logic and GUI components.
    * `makeGUI`:  Constructs the user interface elements.
    * `buildAndStartTrack`: Generates MIDI events based on the grid pattern and starts playback.
    * `saveTrack`: Serializes the pattern and checkbox states to a file.
    * `loadTrack`: Deserializes the pattern and checkbox states from a file.
    * ... (other methods for tempo control, clearing the track, etc.)

## Dependencies

* **Java Sound API:** Standard Java library for MIDI and audio handling.
* **Swing:** Java library for building graphical user interfaces.

## Note

This project assumes you have a MIDI synthesizer or software soundfont to render the generated MIDI data into audible drum sounds.
The other files are just my testing playground about the project, the main and important class is the MusicGenerator.java

## What I have learned
I have learned how to hard code GUI, introduced to streams such as the intermediate and terminal operations and chaining them. I also learned the art of serialization, saving data and loading them (deserialization).
## Potential Improvements

* **More Instruments:** Expand the instrument selection.
* **Multiple Tracks:** Allow layering multiple instrument tracks.
* **Quantization:** Add features for aligning beats to a grid.
* **Export:** Support exporting to standard MIDI file formats.
* **Sound Customization:** Allow users to load their own soundfonts.

Feel free to explore and modify the code to create your own unique MIDI music compositions!


[![Music Generator Demo]](https://github.com/loftyyyy/MIDI-Machine/assets/78846865/bafeb49f-80de-479f-9c1b-601e8780413f)




