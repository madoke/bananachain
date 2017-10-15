package org.madoke.bananachain.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Base64;

public class SignatureUtilsTest {

  private static final String B64_PRIVATE_KEY = "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCBmkC5nT758s2IH0QPPVAOyD+Ydrd9aGC92W2CUgobGug==";
  private static final String B64_PUBLIC_KEY = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE8d7abGmRR/YKYBgUf9RVh3xt6aGAM7oyO2kdzbhufE/ne/HfSGrvkVastpPtxR0v0awQAwDCrrXR5kpe8e5URQ==";
  private static final String DATA = "This is some random data";

  private PrivateKey privateKey;
  private PublicKey publicKey;

  @Before
  public void setUp() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException {
    privateKey = SignatureUtils.loadPrivateKey(B64_PRIVATE_KEY);
    publicKey = SignatureUtils.loadPublicKey(B64_PUBLIC_KEY);
  }

  @Test
  public void testValidSignature() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, UnsupportedEncodingException, InvalidKeyException, SignatureException, InvalidParameterSpecException {
    String base64Signature = SignatureUtils.sign(DATA, privateKey);
    Boolean valid = SignatureUtils.verify(DATA, base64Signature, publicKey);
    Assert.assertTrue(valid);
  }

  @Test(expected = SignatureException.class)
  public void testInvalidSignature() throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidKeyException, SignatureException {
    String invalidSignature = Base64.getEncoder().encodeToString("invalid signature".getBytes("UTF-8"));
    SignatureUtils.verify(DATA, invalidSignature, publicKey);
  }

}