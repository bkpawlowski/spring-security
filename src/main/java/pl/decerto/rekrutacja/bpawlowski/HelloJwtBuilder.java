package pl.decerto.rekrutacja.bpawlowski;

import io.jsonwebtoken.impl.DefaultJwtBuilder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static java.lang.System.currentTimeMillis;
import static java.util.Objects.requireNonNull;
import static pl.decerto.rekrutacja.bpawlowski.HelloSigningKeyPairProvider.SIGNING_KEY_PAIR;

public class HelloJwtBuilder {

    private String email;
    private String subject;
    private String issuer;

    private Set<String> authorities;

    public HelloJwtBuilder authorities(Set<String> authorities) {
        this.authorities = authorities;
        return this;
    }

    public HelloJwtBuilder authority(String authority) {
        requireNonNull(authority, "authority cannot be null");
        if (this.authorities == null) {
            this.authorities = new HashSet<>();
        }
        this.authorities.add(authority);
        return this;
    }

    public HelloJwtBuilder email(String email) {
        this.email = email;
        return this;
    }

    public HelloJwtBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    public HelloJwtBuilder issuer(String issuer) {
        this.issuer = issuer;
        return this;
    }


    public io.jsonwebtoken.JwtBuilder internalBuilder() {
        return new DefaultJwtBuilder()
                .setSubject(subject)
                .setIssuedAt(new Date(currentTimeMillis()))
                .setExpiration(new Date(currentTimeMillis() + 500_000))
                .setIssuer(issuer)
                .signWith(SIGNING_KEY_PAIR.getPrivate())
                .claim("authorities", authorities)
                .claim("email", email);
    }

    public String build() {
        return internalBuilder()
                .compact();
    }
}
