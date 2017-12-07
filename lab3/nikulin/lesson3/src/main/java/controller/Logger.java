package controller;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Logger {
    private static java.util.logging.Logger logger;
    private static SimpleFormatter formatter = new SimpleFormatter();


    public Logger() throws IOException {
    }

    static java.util.logging.Logger getInstance(){
        if (Objects.equals(logger, null)){
            logger = java.util.logging.Logger.getLogger(LoginController.class.getSimpleName());
            FileHandler fileHandler = null;
            try {
                fileHandler = new FileHandler("/home/pikachu/IdeaProjects/lesson3/log_file.log");
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(formatter);
            return logger;
        } else return logger;
    }
}
