package org.madoke.bananachain.blockchain;

import java.util.List;
import java.util.Queue;

/**
 * Simple interface for a blockchain with read/write methods
 */
public interface Blockchain {

  /**
   * Submit an entry to the blockchain. The entry's signature is verified
   * and if it's valid, the entry is added to the mempool, where it will
   * remain until it's added to a block.
   *
   * @param entry the entry to submit
   * @return true if the signature is valid and the data is successfully queued
   */
  boolean add(Entry entry);

  /**
   * Retrieve the queue of entries that are verified and waiting to be added
   * to a block. It is called mempool in analogy with the bitcoin implementation
   *
   * @return the entries in the mempool
   */
  Queue<Entry> getMempool();

  List<Block> getBlocks();

}
