package dev.drzymala.certgen.generator.application;

import com.sun.jarsigner.ContentSigner;
import dev.drzymala.certgen.generator.application.port.GenerateUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.KeyPair;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class GenerateService implements GenerateUseCase {

//    public static final String TEST_KEY_FILES_PATH = ".";
//
//    private final String KEY_ALGORITHM = "RSA";
//    private final int RSA_KEY_SIZE = 2048;
//    private final String SIGNATURE_ALGORITHM = "SHA256withRSA";
//
    @Override
    public void generate() {
        return;
    }
//
//    public void createSelSignegCert() {
//
////        KeyPair keyPair = createNewKeyPair();
//
//        BigInteger serialNumber = BigInteger.valueOf(ThreadLocalRandom.current().nextLong(1000000000L));
////
////        String issuerString = "C=DE, ST=Berlin, L=Berlin, O=Zertificon Solutions GmbH, CN=Z1 Root CA"; // , emailAddress=a.radler@zertificon.com
////        X500Name issuer = new X500Name(issuerString);
////        String subjectString = "C=DE, ST=Berlin, L=Berlin, O=Zertificon Solutions GmbH, CN=Z1 Root CA"; // , emailAddress=external_edi@energy.test
////        X500Name subject = new X500Name(subjectString);
////
////        ContentSigner certContentSigner = new JcaContentSignerBuilder(SIGNATURE_ALGORITHM)
////                .setProvider(getBouncyCastleProvider()).build(keyPair.getPrivate());
////
////        Date fromDate = Date.from(Optional.ofNullable(Instant.now()).orElse(Instant.now()));
////        Date untilDate = Date.from(Optional.ofNullable(Instant.now()).orElse(Instant.now().plus(10 * 365L, ChronoUnit.DAYS)));
////        X509v3CertificateBuilder certificateBuilder = new JcaX509v3CertificateBuilder(issuer,
////                serialNumber,
////                fromDate, untilDate,
////                subject,
////                keyPair.getPublic());
////
////        // Add Extensions to mark generated Cert as CA Cert
////        JcaX509ExtensionUtils extensionUtils = new JcaX509ExtensionUtils();
////        certificateBuilder.addExtension(Extension.subjectKeyIdentifier, false,
////                extensionUtils.createSubjectKeyIdentifier(keyPair.getPublic()));
////        certificateBuilder.addExtension(Extension.authorityKeyIdentifier, false,
////                extensionUtils.createAuthorityKeyIdentifier(keyPair.getPublic()));
////        certificateBuilder.addExtension(Extension.basicConstraints, true, new BasicConstraints(true));
////
////        X509CertificateHolder certificateHolder = certificateBuilder.build(certContentSigner);
////        X509Certificate caCert = new JcaX509CertificateConverter().setProvider(getBouncyCastleProvider())
////                .getCertificate(certificateHolder);
////
////        encryptPrivateKeyAndWriteToFileBase64Encoded(keyPair.getPrivate(),
////                String.format("%s.key", caPrivateKeyFilename),
////                keyPass);
////
////        writeCsrOrCertToFileBase64Encoded(caCert, String.format("%s.crt", caPrivateKeyFilename));
//    }
//
////    /*
////    openssl req -sha256 -newkey rsa:2048 -keyout standard-test-user.key -out standard-test-user-csr.pem \
////    -subj "/C=DE/ST=Berlin/L=Berlin/O=Zertificon Solutions GmbH/CN=Z1 User1/emailAddress=standard-test-user@intern.qm"
////    */
////    public void createUserPrivateKeyAndCSR(String fileName, String userName, String userMail, String keyPass)
////            throws NoSuchProviderException, NoSuchAlgorithmException, OperatorCreationException, IOException {
////
////        KeyPair keyPair = createNewKeyPair();
////
////        X500Name subject = new X500Name(
////                String.format("C=DE,ST=Berlin,L=Berlin,O=Zertificon Solutions GmbH,CN=%s,emailAddress=%s",
////                        userName, userMail));
////
////        ContentSigner certContentSigner = new JcaContentSignerBuilder(SIGNATURE_ALGORITHM)
////                .setProvider(getBouncyCastleProvider()).build(keyPair.getPrivate());
////
////        PKCS10CertificationRequestBuilder pkcs10CertificationRequestBuilder
////                = new JcaPKCS10CertificationRequestBuilder(subject, keyPair.getPublic());
////
////        PKCS10CertificationRequest csr = pkcs10CertificationRequestBuilder.build(certContentSigner);
////
////        writeCsrOrCertToFileBase64Encoded(csr, String.format("%s-csr.pem", fileName));
////
////        encryptPrivateKeyAndWriteToFileBase64Encoded(keyPair.getPrivate(), String.format("%s.key", fileName),
////                keyPass);
////    }
////
////    /*
////            openssl x509 -req -sha256 -days 365 -in standard-test-user-csr.pem -CA root-ca.crt -CAkey root-ca.key \
////            -set_serial 1 -out standard-test-user-trust.crt \
////            -setalias "Z1 User1" -addtrust emailProtection -addreject clientAuth -addreject serverAuth -trustout
////            */
////    public void signCsrWithCa(String fileName, String userName, String csrFileName, String caFileName, String caKeyFileName,
////                              String keyPassword, Instant from, Instant until)
////            throws IOException, OperatorCreationException, PKCSException, NoSuchProviderException,
////            NoSuchAlgorithmException {
////
////        PKCS10CertificationRequest csr;
////        try (BufferedReader fileReader = newBufferedReader(Paths.get(TEST_KEY_FILES_PATH, csrFileName));
////             PEMParser pemParser = new PEMParser(fileReader)) {
////            csr = (PKCS10CertificationRequest) pemParser.readObject();
////        }
////
////        X509CertificateHolder caCertificateHolder;
////        try (BufferedReader fileReader = newBufferedReader(Paths.get(TEST_KEY_FILES_PATH, caFileName));
////             PEMParser pemParser = new PEMParser(fileReader)) {
////            caCertificateHolder = (X509CertificateHolder) pemParser.readObject();
////        }
////
////        PKCS8EncryptedPrivateKeyInfo caEncryptedPrivateKeyInfo;
////        try (BufferedReader fileReader = newBufferedReader(Paths.get(TEST_KEY_FILES_PATH, caKeyFileName));
////             PEMParser pemParser = new PEMParser(fileReader)) {
////            caEncryptedPrivateKeyInfo = (PKCS8EncryptedPrivateKeyInfo) pemParser.readObject();
////        }
////
////        InputDecryptorProvider decryptorProvider = new JceOpenSSLPKCS8DecryptorProviderBuilder()
////                .setProvider(getBouncyCastleProvider()).build(keyPassword.toCharArray());
////
////        PrivateKeyInfo privateKeyInfo = caEncryptedPrivateKeyInfo.decryptPrivateKeyInfo(decryptorProvider);
////
////        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider(getBouncyCastleProvider());
////        PrivateKey caPrivateKey = converter.getPrivateKey(privateKeyInfo);
////
////        ContentSigner csrCaSigner = new JcaContentSignerBuilder(SIGNATURE_ALGORITHM)
////                .setProvider(getBouncyCastleProvider())
////                .build(caPrivateKey);
////
////        KeyPair keyPair = createNewKeyPair();
////
////        X500Name issuer = caCertificateHolder.getIssuer();
////        BigInteger serialNumber = BigInteger.valueOf(1);
////        if (from == null) {
////            from = Instant.now();
////        }
////        if (until == null) {
////            until = from.plus(365, ChronoUnit.DAYS);
////        }
////        Date fromDate = Date.from(from);
////        Date untilDate = Date.from(until);
////        X500Name subject = csr.getSubject();
////
////        X509v3CertificateBuilder userCertificateBuilder = new JcaX509v3CertificateBuilder(issuer,
////                serialNumber,
////                fromDate, untilDate,
////                subject,
////                keyPair.getPublic());
////        X509CertificateHolder userCertHolder = userCertificateBuilder.build(csrCaSigner);
////
////        // openssl x509 -in standard-test-user-trust.crt -out standard-test-user.crt -outform PEM
////        writeCsrOrCertToFileBase64Encoded(userCertHolder, String.format("%s.crt", fileName));
////
////        Set<ASN1ObjectIdentifier> uses = new HashSet<>();
////        // E-mail Protection
////        uses.add(KeyPurposeId.id_kp_emailProtection.toOID());
////
////        Set<ASN1ObjectIdentifier> prohibitions = new HashSet<>();
////        // TLS Web Server Authentication
////        prohibitions.add(new ASN1ObjectIdentifier("1.3.6.1.5.5.7.3.1"));
////        // TLS Web Client Authentication
////        prohibitions.add(new ASN1ObjectIdentifier("1.3.6.1.5.5.7.3.2"));
////
////
////        // alias == userName
////        CertificateTrustBlock certificateTrustBlock = new CertificateTrustBlock(userName, uses, prohibitions);
////
////        X509TrustedCertificateBlock trustedCertificateBlock = new X509TrustedCertificateBlock(userCertHolder,
////                certificateTrustBlock);
////
////        writeCsrOrCertToFileBase64Encoded(trustedCertificateBlock, String.format("%s-trust.crt", userName));
////    }
////
////    /*
////    openssl pkcs12 -export -in standard-test-user.crt -inkey standard-test-user.key -out standard-test-user@intern.qm.p12
////     */
////    public void exportUserCertInNewP12KeyStore(String userName, String userCertFileName, String userPrivateKeyFileName,
////                                               String userPrivateKeyPass, String keyStoreName, String keyStorePass)
////            throws IOException, KeyStoreException, NoSuchProviderException, CertificateException,
////            NoSuchAlgorithmException, PKCSException, OperatorCreationException {
////
////        X509CertificateHolder caCertificateHolder;
////        try (BufferedReader fileReader = newBufferedReader(Paths.get(TEST_KEY_FILES_PATH, userCertFileName));
////             PEMParser pemParser = new PEMParser(fileReader)) {
////            caCertificateHolder = (X509CertificateHolder) pemParser.readObject();
////        }
////
////        PKCS8EncryptedPrivateKeyInfo userEncryptedPrivateKeyInfo;
////        try (BufferedReader fileReader = newBufferedReader(Paths.get(TEST_KEY_FILES_PATH, userPrivateKeyFileName));
////             PEMParser pemParser = new PEMParser(fileReader)) {
////            userEncryptedPrivateKeyInfo = (PKCS8EncryptedPrivateKeyInfo) pemParser.readObject();
////        }
////
////        InputDecryptorProvider inputDecryptorProvider = new JceOpenSSLPKCS8DecryptorProviderBuilder()
////                .setProvider(getBouncyCastleProvider()).build(userPrivateKeyPass.toCharArray());
////
////        PrivateKeyInfo userPrivateKeyInfo = userEncryptedPrivateKeyInfo.decryptPrivateKeyInfo(inputDecryptorProvider);
////
////        JcaPEMKeyConverter keyConverter = new JcaPEMKeyConverter().setProvider(getBouncyCastleProvider());
////
////        PrivateKey userPrivateKey = keyConverter.getPrivateKey(userPrivateKeyInfo);
////
////        KeyStore p12KeyStore = KeyStore.getInstance("PKCS12", getBouncyCastleProvider());
////        p12KeyStore.load(null, null);
////
////        X509Certificate userCertificate = new JcaX509CertificateConverter()
////                .setProvider(getBouncyCastleProvider()).getCertificate(caCertificateHolder);
////
////        // alias == userName
////        p12KeyStore.setKeyEntry(userName, userPrivateKey, keyStorePass.toCharArray(),
////                new Certificate[] {userCertificate});
////
////        try (OutputStream outputStream
////                     = newOutputStream(Paths.get(TEST_KEY_FILES_PATH, String.format("%s.p12", keyStoreName)))) {
////
////            p12KeyStore.store(outputStream, keyStorePass.toCharArray());
////        }
////    }
////
////    private KeyPair createNewKeyPair() throws NoSuchProviderException, NoSuchAlgorithmException {
////
////        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM, getBouncyCastleProvider());
////        keyPairGenerator.initialize(RSA_KEY_SIZE);
////        return keyPairGenerator.generateKeyPair();
////    }
////
////    private void writeCsrOrCertToFileBase64Encoded(Object certificateOrKey, String fileName) throws IOException {
////
////        Path newFilePath = Paths.get(TEST_KEY_FILES_PATH, fileName);
////
////        if (Files.notExists(newFilePath)) {
////            Files.createFile(newFilePath);
////        }
////
////        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(newFilePath, StandardOpenOption.CREATE);
////             JcaPEMWriter certWriter = new JcaPEMWriter(bufferedWriter)) {
////
////            certWriter.writeObject(certificateOrKey);
////        }
////    }
////
////    private void encryptPrivateKeyAndWriteToFileBase64Encoded(PrivateKey key, String fileName, String password)
////            throws IOException, OperatorCreationException {
////
////        Path newFilePath = Paths.get(TEST_KEY_FILES_PATH, fileName);
////
////        if (Files.notExists(newFilePath)) {
////            Files.createFile(newFilePath);
////        }
////
////        // encrypt RSA private key with des-ede3-cbc
////        OutputEncryptor encryptor = new JceOpenSSLPKCS8EncryptorBuilder(PKCS8Generator.DES3_CBC)
////                .setProvider(getBouncyCastleProvider())
////                .setPasssword(password.toCharArray())
////                .build();
////
////        JcaPKCS8Generator generator = new JcaPKCS8Generator(key, encryptor);
////        PemObject encryptedPrivateKey = generator.generate();
////
////        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(newFilePath, StandardOpenOption.CREATE);
////             JcaPEMWriter certWriter = new JcaPEMWriter(bufferedWriter)) {
////            certWriter.writeObject(encryptedPrivateKey);
////        }
////    }
////
////    public void createPinFile(String filenameWithoutExtension, String pin) throws IOException {
////
////        Path newFilePath = Paths.get(TEST_KEY_FILES_PATH, filenameWithoutExtension + ".pin");
////
////        if (Files.notExists(newFilePath)) {
////            Files.writeString(newFilePath, pin, StandardCharsets.UTF_8);
////        }
////    }
}
