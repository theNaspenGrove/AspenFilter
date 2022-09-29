package net.mov51;

import net.mov51.helpers.Filter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static net.mov51.helpers.YamlHelpers.filters;
import static net.mov51.helpers.YamlHelpers.loadYaml;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

        loadYaml();
        System.out.println("Loaded filters");
        for(Filter filter: filters){
            System.out.println(filter.placeHolder);
            System.out.println(filter.filePath);
            System.out.println(filter.secret);
            System.out.println(filter.lineNumber);
            System.out.println("---");

        }
    }
    public static void ReadLine(int line, String path) throws IOException {
        try (Stream<String> all_lines = Files.lines(Paths.get(path))) {
            String input = all_lines.skip(line).findFirst().get();
            System.out.println(input);
            String output = input.replace("test","weakling");
            System.out.println(output);
            WriteLine(line,path,output);

        }catch (NoSuchElementException e){
            System.out.println("Line not found");
        }
    }

    public static void WriteLine(int line, String filePath, String newLine) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path);
        lines.set(line, newLine);
        Files.write(path, lines);
    }
}