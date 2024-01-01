package me.codins.utils;

import java.io.Console;
import java.io.IOException;

public class Logger {

    public void printAsciiArt(){
        String ascii = "  _____            __      _____     __         __     __          \n" +
                " / ___/______ ____/ /__   / ___/__ _/ /_____ __/ /__ _/ /____  ____\n" +
                "/ (_ / __/ _ `/ _  / -_) / /__/ _ `/ / __/ // / / _ `/ __/ _ \\/ __/\n" +
                "\\___/_/  \\_,_/\\_,_/\\__/  \\___/\\_,_/_/\\__/\\_,_/_/\\_,_/\\__/\\___/_/   \n" +
                "                                                               ";
        System.out.println(ConsoleColors.BLUE_BRIGHT + ascii + ConsoleColors.RESET);
    }

    public void success(String message){
        System.out.println(ConsoleColors.WHITE_BRIGHT + "[" + ConsoleColors.GREEN_BRIGHT + "+" + ConsoleColors.WHITE_BRIGHT+ "] " + message);
    }

    public void error(String message){
        System.out.println(ConsoleColors.WHITE_BRIGHT + "[" + ConsoleColors.RED_BRIGHT + "*" + ConsoleColors.WHITE_BRIGHT+ "] " + message);
    }

    public void log(String message){
        System.out.println(ConsoleColors.WHITE_BRIGHT + "[" + ConsoleColors.BLACK_BRIGHT + "-" + ConsoleColors.WHITE_BRIGHT+ "] " + message);
    }

    public void logQuestion(String message){
        System.out.print(ConsoleColors.WHITE_BRIGHT + "[" + ConsoleColors.YELLOW_BRIGHT + "?" + ConsoleColors.WHITE_BRIGHT+ "] " + message);
    }

    public void printSplitter(){
        System.out.println("-----------------------------------------------------------------");
    }

}
