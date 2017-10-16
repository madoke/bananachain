package org.madoke.bananachain.utils;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class ECDSAUtil {

  private static final String ELLIPTIC_CURVE_ALGORITHM = "EC";
  private static final String SECP256R1_CURVE = "secp256r1";
  private static final String UTF8 = "UTF-8";
  private static final String SHA256_WITH_ECDSA = "SHA256withECDSA";

  /***
   * Generates an ECDSA Key Pair, using the SECP256R1 Curve
   *
   * @return the public and private key
   */
  public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
    try {

      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ELLIPTIC_CURVE_ALGORITHM);
      ECGenParameterSpec params = new ECGenParameterSpec(SECP256R1_CURVE);
      keyPairGenerator.initialize(params, new SecureRandom());
      return keyPairGenerator.generateKeyPair();

    } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
      throw new RuntimeException("Something went wrong while generating the key pair", e);
    }
  }

  /***
   * Generates a SHA256 ECDSA signature of a given String, using a provided PrivateKey
   * @param data the data to sign
   * @param base64privateKey the base64 encoded private key
   * @return a base64 encoded signature
   * @throws InvalidKeyException if the private key is invalid
   * @throws SignatureException if it cannot sign the data
   */
  public static String sign(String data, String base64privateKey) throws InvalidKeyException, SignatureException {
    try {

      PrivateKey privateKey = loadPrivateKey(base64privateKey);
      Signature ecdsaSign = Signature.getInstance(SHA256_WITH_ECDSA);
      ecdsaSign.initSign(privateKey);
      ecdsaSign.update(data.getBytes(UTF8));
      return Base64.getEncoder().encodeToString(ecdsaSign.sign());

    } catch (NoSuchAlgorithmException | NoSuchProviderException | UnsupportedEncodingException e) {
      throw new RuntimeException("Something went wrong while signing the data", e);
    } catch (InvalidKeySpecException e) {
      throw new InvalidKeyException(e);
    }
  }

  /***
   * Verifies the signature of a given String, using the public key
   *
   * @param data the data to verify the signature
   * @param base64Signature the base64 encoded signature
   * @param base64publicKey the base64 encoded public key
   * @return true if the signature matches the provided data and public key
   * @throws InvalidKeyException if the public key is invalid
   * @throws SignatureException if the signature is invalid
   */
  public static boolean verify(String data, String base64Signature, String base64publicKey) throws InvalidKeyException, SignatureException {
    try {

      PublicKey publicKey = loadPublicKey(base64publicKey);
      Signature ecdsaSign = Signature.getInstance(SHA256_WITH_ECDSA);
      ecdsaSign.initVerify(publicKey);
      ecdsaSign.update(data.getBytes(UTF8));
      return ecdsaSign.verify(Base64.getDecoder().decode(base64Signature));

    } catch (NoSuchAlgorithmException | NoSuchProviderException | UnsupportedEncodingException e) {
      throw new RuntimeException("Something went wrong while verifying the signature", e);
    } catch (InvalidKeySpecException e) {
      throw new InvalidKeyException(e);
    }
  }

  /**
   * Load a previously generated public key which was encoded in X509 and then base64
   *
   * @param base64publicKey the base64 encoded public key
   * @return the PublicKey object
   * @throws NoSuchProviderException
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeySpecException
   */
  private static PublicKey loadPublicKey(String base64publicKey) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
    X509EncodedKeySpec formatted_public = new X509EncodedKeySpec(Base64.getDecoder().decode(base64publicKey));
    KeyFactory kf = KeyFactory.getInstance(ELLIPTIC_CURVE_ALGORITHM);
    return kf.generatePublic(formatted_public);
  }

  /**
   * Load a previously generated private key which was encoded in PCKS8 and then base64
   *
   * @param base64privateKey the base64 encoded private key
   * @return the PrivateKey object
   * @throws NoSuchProviderException
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeySpecException
   */
  private static PrivateKey loadPrivateKey(String base64privateKey) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
    PKCS8EncodedKeySpec formatted_private = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64privateKey));
    KeyFactory kf = KeyFactory.getInstance(ELLIPTIC_CURVE_ALGORITHM);
    return kf.generatePrivate(formatted_private);
  }

  /**
   * For demonstration purposes
   */
  public static void main(String[] args) throws SignatureException, InvalidKeyException {
    String B64_PRIVATE_KEY = "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCBmkC5nT758s2IH0QPPVAOyD+Ydrd9aGC92W2CUgobGug==";
    String B64_PUBLIC_KEY = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE8d7abGmRR/YKYBgUf9RVh3xt6aGAM7oyO2kdzbhufE/ne/HfSGrvkVastpPtxR0v0awQAwDCrrXR5kpe8e5URQ==";
    String DATA = "This is some random data";

    System.out.println(ECDSAUtil.sign(DATA, B64_PRIVATE_KEY));
  }

}
