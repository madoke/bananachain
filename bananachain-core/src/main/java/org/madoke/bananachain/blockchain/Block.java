package org.madoke.bananachain.blockchain;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * The underlying data structure in the blockchain
 */
public class Block {

  private String hash;

  private String previousBlockHash;

  private List<Entry> data;

  private Integer nonce;

  private ZonedDateTime timestamp;


  public void setHash(String hash) {
    this.hash = hash;
  }

  /**
   * The block's hash
   *
   * @return a sha256 hash string
   */
  public String getHash() {
    return hash;
  }

  public void setPreviousBlockHash(String previousBlockHash) {
    this.previousBlockHash = previousBlockHash;
  }

  /**
   * The previous block's hash
   *
   * @return a sha256 hash string
   */
  public String getPreviousBlockHash() {
    return previousBlockHash;
  }

  public void setData(List<Entry> data) {
    this.data = data;
  }

  /**
   * The list of entries contained in this block
   *
   * @return a list of Entry objects
   */
  public List<Entry> getData() {
    return data;
  }

  public void setNonce(Integer nonce) {
    this.nonce = nonce;
  }

  /**
   * The number that combined with the remaining fields resulted in producing
   * a valid proof of work for this block
   *
   * @return an integer with the proof of work number
   */
  public Integer getNonce() {
    return nonce;
  }

  public void setTimestamp(ZonedDateTime timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * The timestamp at which the data was validated and the block mined
   *
   * @return a timestamp in utc timezone
   */
  public ZonedDateTime getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Entry s : getData()) {
      sb.append(s.getData())
        .append(s.getPublicKey())
        .append(s.getSignature());
    }
    sb.append(getTimestamp().toString());
    sb.append(getPreviousBlockHash());
    sb.append(getNonce());
    return sb.toString();
  }
}
