package core.utils;

import org.jasypt.util.text.BasicTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordDecrypt {
    private final Logger logger = LoggerFactory.getLogger(PasswordDecrypt.class);

    public String getPasswordDecoded(String encryptedPassword) {
        String encryptionKey = System.getenv("ENCRYPTION_KEY");

        if (encryptionKey == null) {
            logger.error("ENCRYPTION_KEY environment variable not found. Please set the key before running the application.");
            throw new IllegalStateException("Encryption key is missing. Set ENCRYPTION_KEY as an environment variable.");
        }

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(encryptionKey);

        return textEncryptor.decrypt(encryptedPassword);
    }
}
