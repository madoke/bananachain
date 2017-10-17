package org.madoke.bananachain;

import org.irenical.lifecycle.LifeCycle;
import org.madoke.bananachain.blockchain.Block;
import org.madoke.bananachain.blockchain.Blockchain;
import org.madoke.bananachain.blockchain.Entry;
import org.madoke.bananachain.proofofwork.BruteforceHashProof;
import org.madoke.bananachain.proofofwork.ProofOfWork;
import org.madoke.bananachain.utils.ECDSAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.time.ZoneOffset.UTC;

public class BananaChain implements Blockchain, LifeCycle {

  private static final Logger LOG = LoggerFactory.getLogger(BananaChain.class);
  private static final int MAX_BLOCK_ENTRIES = 1;
  private static final int DIFFICULTY = 5;
  private boolean isRunning = false;
  private Thread miner;


  private final LinkedList<Block> blocks = new LinkedList<>();

  private final LinkedBlockingQueue<Entry> mempool = new LinkedBlockingQueue<>();

  private final ProofOfWork proofOfWork = new BruteforceHashProof(DIFFICULTY);


  /**
   * Shuts down the blockchain and waits for the miner to stop
   */
  @Override
  public void stop() {
    isRunning = false;
//    try {
//      miner.join();
//    } catch (InterruptedException e) {
//      LOG.error("Miner did not terminate on time", e);
//      Thread.interrupted();
//    }
  }


  /**
   * Signals that the blockchain is ready to operate (or not)
   *
   * @return true if it is ready, false if its not
   */
  @Override
  public boolean isRunning() {
    return isRunning;
  }


  /**
   * Initialize the blockchain. Sets the genesis block and
   * starts the miner
   */
  @Override
  public void start() {
    if (blocks.isEmpty()) {
      blocks.add(genesisBlock());
    }
//    miner = new Thread(this::mine);
//    miner.start();
    isRunning = true;
  }


  /**
   * Generate the first block
   *
   * @return a Block instance
   */
  private Block genesisBlock() {
    Block genesis = new Block();
    Entry entry = new Entry();
    entry.setData("Genesis Block");
    genesis.setData(Collections.singletonList(entry));
    genesis.setTimestamp(ZonedDateTime.now(UTC));
    genesis.setPreviousBlockHash("00000000000000000000000000000000");
    return genesis;
  }


  /**
   * Infinite loop that continuously mines blocks as soon as the
   * blockchain is ready and data is available in the memPool
   */
//  private void mine() {
//    while (isRunning) {
//      // Get the block data from the mem pool
//      LinkedList<Entry> blockData = new LinkedList<>();
//      while (blockData.size() < MAX_BLOCK_ENTRIES) {
//        try {
//          blockData.offer(mempool.take());
//        } catch (InterruptedException e) {
//          LOG.warn("Attempt to interrupt miner", e);
//          Thread.interrupted();
//        }
//      }
//      Block block = new Block();
//      block.setPreviousBlockHash(blocks.getLast().getHash());
//      block.setTimestamp(ZonedDateTime.now(UTC));
//      block.setData(blockData);
//      block.setHash(HashUtils.sha256(block.toString()));
//      block = proofOfWork.getProof(block);
//      blocks.add(block);
//    }
//  }
  
  @Override
  public boolean add(Entry entry) {
    boolean isValid = false;

    try {
      isValid = ECDSAUtil.verify(entry.getData(), entry.getSignature(), entry.getPublicKey());
    } catch (InvalidKeyException | SignatureException e) {
      LOG.error("Could not verify signature", e);
    }

    if (isValid) {
      mempool.add(entry);
    }

    return isValid;
  }

  @Override
  public Queue<Entry> getMempool() {
    return mempool;
  }

  @Override
  public List<Block> getBlocks() {
    return blocks;
  }


}
