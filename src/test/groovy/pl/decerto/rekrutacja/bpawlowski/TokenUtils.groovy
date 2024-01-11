package pl.decerto.rekrutacja.bpawlowski

import io.jsonwebtoken.Jwt
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.impl.DefaultJwtParserBuilder

import java.security.KeyPair

final class TokenUtils {
    public static final KeyPair CURRENT_KEY_PAIR = HelloSigningKeyPairProvider.SIGNING_KEY_PAIR

    public static final JwtParser JWT_PARSER = new DefaultJwtParserBuilder()
        .setSigningKey(CURRENT_KEY_PAIR.getPrivate())
        .build()
    public static final String EXPIRED_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9." +
        "eyJsYXN0TmFtZSI6IkZyb250b2ZmaWNlIiwiZ2VuZGVyIjoiTUFMRSIsInVzZXJfbmFtZSI6ImZyb250b2ZmaWNlIiwicm9sZXMiOlsiRk9fVVNFUiJdLCJiaXJ0aERhdGEiOm51bGwsImdyb3Vwc01lbWJlciI6W10sImF1dGhvcml0aWVzIjpbIlBPTElDWV9DQU5DRUxfRk8iLCJQT0xJQ1lBTk5FWF9DUkVBVEVfRk8iLCJGSUxFX0RFTEVURV9GTyIsIlBPTElDWV9OT05TVEFOREFSRF9DUkVBVEUiLCJQT0xJQ1lfU0VBUkNIX0ZPIiwiQ09NTUlTU0lPTl9SQVRFX0dFVCIsIkZJTEVfQ1JfRk8iLCJQQVlNRU5UX0RJRkZfVU5ERVJQQVlNRU5UX1JFQUQiLCJJTlNVUkFOQ0VfQVBQTElDQVRJT05fU0VBUkNIIiwiQ09NTVVOSUNBVElPTl9HRU5FUkFURSIsIk1BTkFHRV9DRVJUSUZJQ0FURVMiLCJBUFBMSUNBVElPTl9DUkVBVEVfRk8iLCJPRkZFUl9SRUFEX0ZPIiwiT0ZGRVJfTk9OU1RBTkRBUkRfU0VORF9GTyIsIlBPTElDWV9SRUFEX0ZPIiwiUE9MSUNZX0FDQ09VTlRfREVUQUlMU19SRUFEIiwiT0ZGRVJfQ1JFQVRFIiwiT0ZGRVJfU0VBUkNIX0ZPIiwiUFJPRFVDVFNfRk9SX1NBTEVTTUFOX1JFQUQiLCJQT0xJQ1lfQ1JFQVRFX0ZPIiwiR1JPVVBfUE9MSUNZX1JFQUQiLCJGT19MT0dJTiIsIkFHRU5UX1JFQUQiLCJQT0xJQ1lfRU5EX0ZPIl0sImNsaWVudF9pZCI6ImZvIiwiZmlyc3ROYW1lIjoiRnJvbnRvZmZpY2UiLCJjb21wYW5pZXMiOlsiUFRVVyIsIlBUVU5aIl0sInNhbGVzbWFucyI6W10sIm1vYmlsZVBob25lIjpudWxsLCJncm91cHNDb29yZGluYXRvciI6W10sInNjb3BlIjpbXSwiZXhwIjoxNjExMzYyNTMyLCJwZXNlbCI6IjY1MTIxMDAwMDAwIiwic2VsZWN0ZWRDb21wYW5pZXMiOm51bGwsImp0aSI6Ijg1aF9RdEJTRW85UlJWVS1WMXhMQkNGX2RxTSIsImVtYWlsIjpudWxsLCJzZWNvbmROYW1lIjpudWxsfQ.NLzuMsp4AmsV_sYnXNJGIiqNO6lAhQ_zCNB50piacfekXmaiYdPe3Yerj-6gvmSptTkmnnHwEmcCollectionfEGevpifRrmhiQI9cXfnNoyP03WmsmrVmnDUpIvDEgBG0zm9XBd-p7DkIo5umJCYldXjOQT-fj7VafybJn7G-aaOEjfSfFOxZLu8dkDHLjXSFgSkidm1ZHBJZ3yqKd04OVpne4PpUbD_agzJ9k-fbXFECg_CH7B03n9OCtE17PgXnn95ZIr5Rinz1BEvMzn_OoubdTQnQvL5TR2iB4JzNWOuOkIKLK0GPyUJt3o6O51JlmDTRCJCkdrbnZwmtx0fLl7hMIoA"

    public static final String VALID_TOKEN = token("rekrutacja@decerto.pl")

    static final Jwt parse(String token) {
        return JWT_PARSER.parse(token)
    }

    static final String token(String email) {
        return tokenBuilder()
            .claim("email", email)
            .compact()
    }

    static final JwtBuilder tokenBuilder(Collection<String> authorities = []) {
        def bldr = new HelloJwtBuilder()
            .subject("stub-subject")
            .issuer("stub-issuer")
            .authorities(authorities as Set)
            .email("rekrutacja@decerto.pl")
            .internalBuilder()

        return bldr
    }
}
