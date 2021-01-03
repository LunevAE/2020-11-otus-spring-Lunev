package ru.otus.spring.dao;

import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppProps;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataReader {
    private final AppProps appProps;
    private final List<String> lines = new ArrayList<>();

    public DataReader(AppProps appProps) {
        this.appProps = appProps;
    }

    public List<String> readData(){
        String fileName = appProps.getQuestionsFileName() + appProps.getLocale().toString() + ".csv";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
