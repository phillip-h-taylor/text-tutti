package com.texttutti.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class HelpService {

    public List<String> getHelpMessages() {
//        String introMessage = "Text Tutti works by interpreting comma separated text input and turning it into musical notes. Other metadata can also be set.";
//        String noteMessage = "To input a note, enter a letter name between A and G, optionally followed by S to sharpen it or T to flatten it, then a mandatory integer between 0 " +
//                "and 8 to specify the octave and an optional positive integer to specify the length of the notes in semiquavers (default is 1). For example, C54 is a crotchet middle C " +
//                "while AT43 is the A flat below middle C lasting a dotted quaver.";
//        String restMessage = "You can also add rests by inputting R followed by its length in semiquavers.";
//        String instrumentMessage = "The letter I followed by a number specifies an instrument to use. For example I40 uses the violin, I56 the trumpet";
//        String volumeMessage = "You can specify the volume of your score by using V followed by a number between 0 and 100.";
//        String titleAndFileMessage = "TI and FI can be used to set the title and filename respectively. For example FImyscore will name your file myscore.mid.";
//        String multipleMessage = "You can enter one score across multiple messages - after entering a message you have 5 minutes to enter the next message before your score gets" +
//                " deleted. To end your score you must end your last message with \"END\".";
        String partsMessage = "Multiple parts may be entered by ending a part with \"%26\" before starting the next one. Each part can have its own instrument and volume.";
        String exampleMessage = "A simple example of a message would be \"I56,T45,TITitle,FIfile,C58,G58,C616%26I58,C332END\". Good luck!";
//        return Arrays.asList(introMessage, noteMessage, restMessage, instrumentMessage, volumeMessage, titleAndFileMessage, multipleMessage, partsMessage, exampleMessage);
        return Arrays.asList(partsMessage, exampleMessage);
    }
}
