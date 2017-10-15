package org.madoke.bananachain.utils;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class SignatureUtils {

  private static final String ELLIPTIC_CURVE_ALGORITHM = "EC";
  private static final String SECP256R1_CURVE = "secp256r1";
  private static final String UTF8 = "UTF-8";
  private static final String SHA256_WITH_ECDSA = "SHA256withECDSA";


  /***
   * Generates an ECDSA Key Pair, using the SECP256R1 Curve
   * @return a KeyPair instance with the public and private key
   * @throws NoSuchAlgorithmException
   * @throws InvalidAlgorithmParameterException
   */
  public static KeyPair generateKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ELLIPTIC_CURVE_ALGORITHM);
    ECGenParameterSpec params = new ECGenParameterSpec(SECP256R1_CURVE);
    keyPairGenerator.initialize(params, new SecureRandom());

    return keyPairGenerator.generateKeyPair();
  }

  /***
   * Generates a SHA256 ECDSA signature of a given String, using a provided PrivateKey
   * @param data the data to sign
   * @param privateKey the private key
   * @return a base64 encoded signature
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeySpecException
   * @throws InvalidKeyException
   * @throws SignatureException
   * @throws UnsupportedEncodingException
   */
  public static String sign(String data, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
    Signature ecdsaSign = Signature.getInstance(SHA256_WITH_ECDSA);
    ecdsaSign.initSign(privateKey);
    ecdsaSign.update(data.getBytes(UTF8));

    return Base64.getEncoder().encodeToString(ecdsaSign.sign());
  }

  /***
   * Verifies the signature of a given String, using the public key
   * @param data the data to verify the signature
   * @param base64Signature the base64 encoded signature
   * @param publicKey the public key
   * @return true if the signature matches the provided data and public key
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeySpecException
   * @throws InvalidKeyException
   * @throws UnsupportedEncodingException
   * @throws SignatureException
   * @throws InvalidParameterSpecException
   */
  public static boolean verify(String data, String base64Signature, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, UnsupportedEncodingException, SignatureException, InvalidParameterSpecException {
    Signature ecdsaSign = Signature.getInstance(SHA256_WITH_ECDSA);
    ecdsaSign.initVerify(publicKey);
    ecdsaSign.update(data.getBytes(UTF8));

    return ecdsaSign.verify(Base64.getDecoder().decode(base64Signature));
  }

  /**
   * Load a previously generated public key which was encoded in X509 and then base64
   * @param base64privateKey the base64 encoded public key
   * @return the PublicKey object
   * @throws NoSuchProviderException
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeySpecException
   */
  public static PublicKey loadPublicKey(String base64privateKey) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
    X509EncodedKeySpec formatted_public = new X509EncodedKeySpec(Base64.getDecoder().decode(base64privateKey));
    KeyFactory kf = KeyFactory.getInstance(ELLIPTIC_CURVE_ALGORITHM);

    return kf.generatePublic(formatted_public);
  }

  /**
   * Load a previously generated private key which was encoded in PCKS8 and then base64
   * @param base64privateKey the base64 encoded private key
   * @return the PrivateKey object
   * @throws NoSuchProviderException
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeySpecException
   */
  public static PrivateKey loadPrivateKey(String base64privateKey) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException {
    PKCS8EncodedKeySpec formatted_private = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64privateKey));
    KeyFactory kf = KeyFactory.getInstance(ELLIPTIC_CURVE_ALGORITHM);

    return kf.generatePrivate(formatted_private);
  }

}
