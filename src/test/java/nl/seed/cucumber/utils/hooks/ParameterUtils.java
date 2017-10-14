package nl.seed.cucumber.utils.hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ParameterUtils {
    private static final Logger log = LogManager.getLogger(ParameterUtils.class);

    /**
     * Returns the value if nonempty, otherwise the system property, else default value.
     * @param parameter    Value that might be empty: ""
     * @param systemKey    Property passed as a flag in the command line: -Dexample=123
     * @return parameter
     */
    public static String getParameter(String parameter, String systemKey) throws Exception {
        String parameterContent = getParameter(parameter, systemKey, "");
        return getStringContentOptional(parameterContent)
                .orElseThrow(RuntimeException::new);
    }

    /**
     * Returns the value if nonempty, otherwise the system property, else default value.
     * @param parameter    Value that might be empty: ""
     * @param systemKey    Property passed as a flag in the command line: -Dexample=123
     * @param defaultValue Backup if value and system property are not present.
     * @return parameter
     */
    public static String getParameter(String parameter, String systemKey, String defaultValue) {
        return getStringContentOptional(parameter)
                .orElseGet(() ->
                        getStringContentOptional(System.getProperty(systemKey))
                                .orElse(defaultValue));
    }

    /**
     * Returns an empty observable if the String content is empty.
     * @param s String to be checked
     * @return Optional that is empty or holding a nonempty string
     */
    private static Optional<String> getStringContentOptional(String s) {
        return s.equals("") ? Optional.empty() : Optional.of(s);
    }
}
