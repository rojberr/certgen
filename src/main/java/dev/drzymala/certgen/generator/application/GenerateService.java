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
import java.math.BigDecimal;
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
    public void generate(
            String version,
            BigDecimal serialNumber,
            String signatureAlgorithm,
            int keySize,
            String signatureHashAlgorithm,
            String issuer,
            Date validFrom,
            Date validTill,
            String subject) {

        if (validFrom == null) {
            validFrom = Date.from(LocalDate.of(2000, 1, 1).atStartOfDay(ZoneOffset.UTC).toInstant());
        }
        if (validTill == null) {
            validTill = Date.from(LocalDate.of(2035, 1, 1).atStartOfDay(ZoneOffset.UTC).toInstant());
        }

        KeyPair keypair = generateRSAKeyPair(keySize);

        X509CertificateHolder holder = generateV3Certificate(
                keypair,
                serialNumber,
                signatureAlgorithm,
                signatureHashAlgorithm,
                issuer,
                validFrom,
                validTill,
                subject);

        saveToDerFile(holder);
        saveToPemFile(holder);
    }

    public X509CertificateHolder generateV3Certificate(
            KeyPair keyPair,
            BigDecimal serialNumber,
            String signatureAlgorithm,
            String signatureHashAlgorithm,
            String issuer,
            Date validFrom,
            Date validTill,
            String subject) throws IOException, OperatorCreationException {

        SecureRandom random = new SecureRandom();
        byte[] id = new byte[20];
        random.nextBytes(id);
        BigInteger serial = new BigInteger(160, random);

        X500Name x509Issuer = new X500NameBuilder(BCStyle.INSTANCE)
                .addRDN(BCStyle.CN, issuer)
                .build();

        X500Name x509Subject = new X500NameBuilder(BCStyle.INSTANCE)
                .addRDN(BCStyle.CN, subject)
                .build();

        // Fill certificate fields
        X509v3CertificateBuilder certificate = new JcaX509v3CertificateBuilder(
                x509Issuer,
                serial,
                validFrom,
                validTill,
                x509Subject,
                keyPair.getPublic());

        BasicConstraints constraints = new BasicConstraints(true);
        KeyUsage usage = new KeyUsage(KeyUsage.keyCertSign | KeyUsage.digitalSignature);
        ExtendedKeyUsage usageEx = new ExtendedKeyUsage(new KeyPurposeId[]{
                KeyPurposeId.id_kp_serverAuth,
                KeyPurposeId.id_kp_clientAuth
        });

        certificate.addExtension(Extension.basicConstraints, true, constraints.getEncoded());
        certificate.addExtension(Extension.subjectKeyIdentifier, false, id);
        certificate.addExtension(Extension.authorityKeyIdentifier, false, id);
        certificate.addExtension(Extension.keyUsage, false, usage.getEncoded());
        certificate.addExtension(Extension.extendedKeyUsage, false, usageEx.getEncoded());

        // build BouncyCastle certificate
        ContentSigner signer = new JcaContentSignerBuilder(SIGNATURE_ALGORITHM).build(keyPair.getPrivate());
        X509CertificateHolder holder = certificate.build(signer);

        return holder;
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

    public KeyPair generateRSAKeyPair(int RsaKeySize)
            throws Exception {

        KeyPairGenerator keypairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keypairGen.initialize(RsaKeySize, new SecureRandom());
        return keypairGen.generateKeyPair();
    }
}