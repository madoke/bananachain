package org.madoke.bananachain.blockchain;

/**
 * A data entry on the blockchain. Entries are tied to the identity of their
 * owner (the entity that originally submitted the data) by a signature of that
 * data, which was created with a private key, and the corresponding public key
 */
public class Entry {

  private String data;
  private String signature;
  private String publicKey;

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getSignature() {
    return signature;
  }
  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

}
