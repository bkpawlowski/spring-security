package pl.decerto.rekrutacja.bpawlowski;

import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPublicKeySpec;

public class HelloSigningKeyPairProvider {
    public static final KeyPair SIGNING_KEY_PAIR = retrieve();

    private HelloSigningKeyPairProvider() {
    }

    public static KeyPair retrieve() {
        return retrieveKeyPair(resource("keystore.jks"), "test", "foobar");
    }

    private static InputStream resource(String classpath) {
        try {
            return new ClassPathResource(classpath).getInputStream();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to load resource from given classpath");
        }
    }

    private static KeyStore loadKeyStore(InputStream resource, String password) {
        KeyStore store = null;
        try {
            store = KeyStore.getInstance("jks");
            store.load(resource, password.toCharArray());
            return store;
        } catch (Exception e) {
            throw new IllegalStateException("Unable to load keystore", e);
        }
    }

    private static KeyPair retrieveKeyPair(InputStream resource, String alias, String password) {
        try {
            var keyStore = loadKeyStore(resource, password);
            var key = (RSAPrivateCrtKey) keyStore.getKey(alias, password.toCharArray());
            var spec = new RSAPublicKeySpec(key.getModulus(), key.getPublicExponent());
            var publicKey = KeyFactory.getInstance("RSA").generatePublic(spec);
            return new KeyPair(publicKey, key);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot load keys from store", e);
        }
    }
}
