import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Main {

    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Запуск программы с аргументами: " + Arrays.toString(args));
        }

    }
}
