package org.madoke.bananachain.blockchain;

import java.util.List;
import java.util.Queue;

/**
 * Simple interface for a blockchain with read/write methods
 */
public interface Blockchain {

  /**
   * Submit an row to the blockchain. The row's signature is verified
   * and if it's valid, the row is added to the mempool, where it will
   * remain until it's added to a block.
   *mit
   * @param row the row to sub
   * @return true if the signature is valid and the data is successfully queued
   */
  boolean insert(Row row);

  /**
   * Retrieve the queue of entries that are verified and waiting to be added
   * to a block. It is called mempool in analogy with the bitcoin implementation
   *
   * @return the entries in the mempool
   */
  Queue<Row> getMempool();

  /**
   * Retrieve the list of blocks that were previously mined
   *
   * @return the blocks that form the blockchain
   */
  List<Block> getBlocks();

}
