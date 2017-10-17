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

  public void setData(String data) {
    this.data = data;
  }

  /**
   * The actual data
   *
   * @return a string with the data in this entry
   */
  public String getData() {
    return data;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  /**
   * A Cryptographic signature that ties the data in this entry to a pair of
   * private/public keys
   *
   * @return a string with the signature encoded in base64
   */
  public String getSignature() {
    return signature;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  /**
   * The public key that will be used to verify the data signature
   *
   * @return a string with the public key encoded in base64
   */
  public String getPublicKey() {
    return publicKey;
  }

}
