package dev.drzymala.certgen.generator.application;

import dev.drzymala.certgen.generator.application.port.GenerateUseCase;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

@Service
@AllArgsConstructor
public class GenerateService implements GenerateUseCase {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static final String CERTIFICATES_FILES_PATH = "createdCerts";
    private final String KEY_ALGORITHM = "RSA";
    private final int RSA_KEY_SIZE = 2048;
    private final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    @Override
    @SneakyThrows
    public void generate() {

        KeyPair keypair = generateRSAKeyPair();
        X509CertificateHolder holder = generateV3Certificate(keypair);

        saveToDerFile(holder);
        saveToPemFile(holder);
    }

    private void saveToPemFile(X509CertificateHolder holder) throws IOException, CertificateException {

        final StringWriter writer = new StringWriter();
        final JcaPEMWriter pemWriter = new JcaPEMWriter(writer);

        pemWriter.writeObject(new JcaX509CertificateConverter().getCertificate(holder));
        pemWriter.flush();
        pemWriter.close();
        saveBytesToFile(writer.toString().getBytes(), "certificate.pem");
    }

    private void saveToDerFile(X509CertificateHolder holder) throws IOException {

        saveBytesToFile(holder.getEncoded(), "certificate.der");
    }

    private void saveBytesToFile(byte[] bytes, String filename) throws IOException {

        File outputFile = new File(CERTIFICATES_FILES_PATH + "/" + filename);
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(bytes);
        outputStream.close();
    }

    public KeyPair generateRSAKeyPair()
            throws Exception {

        KeyPairGenerator keypairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keypairGen.initialize(RSA_KEY_SIZE, new SecureRandom());
        return keypairGen.generateKeyPair();
    }

    public X509CertificateHolder generateV3Certificate(KeyPair keyPair) throws IOException, OperatorCreationException {

        SecureRandom random = new SecureRandom();
        byte[] id = new byte[20];
        random.nextBytes(id);
        BigInteger serial = new BigInteger(160, random);

        // Create subject
        X500Name subject = new X500NameBuilder(BCStyle.INSTANCE)
                .addRDN(BCStyle.CN, "stackoverflow.com")
                .build();

        // Fill certificate fields
        X509v3CertificateBuilder certificate = new JcaX509v3CertificateBuilder(
                subject,
                serial,
                Date.from(LocalDate.of(2000, 1, 1).atStartOfDay(ZoneOffset.UTC).toInstant()),
                Date.from(LocalDate.of(2035, 1, 1).atStartOfDay(ZoneOffset.UTC).toInstant()),
                subject,
                keyPair.getPublic());
        certificate.addExtension(Extension.subjectKeyIdentifier, false, id);
        certificate.addExtension(Extension.authorityKeyIdentifier, false, id);
        BasicConstraints constraints = new BasicConstraints(true);
        certificate.addExtension(
                Extension.basicConstraints,
                true,
                constraints.getEncoded());
        KeyUsage usage = new KeyUsage(KeyUsage.keyCertSign | KeyUsage.digitalSignature);
        certificate.addExtension(Extension.keyUsage, false, usage.getEncoded());
        ExtendedKeyUsage usageEx = new ExtendedKeyUsage(new KeyPurposeId[]{
                KeyPurposeId.id_kp_serverAuth,
                KeyPurposeId.id_kp_clientAuth
        });
        certificate.addExtension(
                Extension.extendedKeyUsage,
                false,
                usageEx.getEncoded());

        // build BouncyCastle certificate
        ContentSigner signer = new JcaContentSignerBuilder(SIGNATURE_ALGORITHM)
                .build(keyPair.getPrivate());
        X509CertificateHolder holder = certificate.build(signer);

        return holder;
    }
}