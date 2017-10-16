package org.madoke.bananachain.utils;

import org.junit.Assert;
import org.junit.Test;

public class ECDSAUtilTest {

  private static final String B64_PRIVATE_KEY = "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCBmkC5nT758s2IH0QPPVAOyD+Ydrd9aGC92W2CUgobGug==";
  private static final String B64_PUBLIC_KEY = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE8d7abGmRR/YKYBgUf9RVh3xt6aGAM7oyO2kdzbhufE/ne/HfSGrvkVastpPtxR0v0awQAwDCrrXR5kpe8e5URQ==";
  private static final String DATA = "This is some random data";

  @Test
  public void testValidSignature() throws Exception {
    String base64Signature = ECDSAUtil.sign(DATA, B64_PRIVATE_KEY);
    Boolean valid = ECDSAUtil.verify(DATA, base64Signature, B64_PUBLIC_KEY);
    Assert.assertTrue(valid);
  }

  @Test
  public void testInvalidSignature() throws Exception {
    String invalidSignature = ECDSAUtil.sign("funny data", B64_PRIVATE_KEY);
    boolean isValid = ECDSAUtil.verify(DATA, invalidSignature, B64_PUBLIC_KEY);
    Assert.assertFalse(isValid);
  }

}