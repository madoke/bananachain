package org.madoke.bananachain;

import org.irenical.lifecycle.LifeCycle;
import org.madoke.bananachain.blockchain.Block;
import org.madoke.bananachain.blockchain.Blockchain;
import org.madoke.bananachain.proofofwork.BruteforceHashProof;
import org.madoke.bananachain.proofofwork.ProofOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final LinkedList<Block> blocks = new LinkedList<>();
    private final LinkedBlockingQueue<String> memPool = new LinkedBlockingQueue<>();
    private final ProofOfWork proofOfWork = new BruteforceHashProof(DIFFICULTY);

    private boolean isRunning = false;
    private Thread miner;

    /**
     * Shuts down the blockchain and waits for the miner to stop
     */
    @Override
    public void stop() {
        isRunning = false;
        try {
            miner.join();
        } catch (InterruptedException e) {
            LOG.error("Miner did not terminate on time", e);
            Thread.interrupted();
        }
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
        isRunning = true;
        miner = new Thread(this::mine);
        miner.start();
    }


    /**
     * Generate the first block
     *
     * @return a Block instance
     */
    private Block genesisBlock() {
        Block genesis = new Block();
        genesis.setData(Collections.singletonList("Bananachain Genesis Block"));
        genesis.setTimestamp(ZonedDateTime.now(UTC));
        genesis.setPreviousBlockHash("00000000000000000000000000000000");
        return proofOfWork.getProof(genesis);
    }


    /**
     * Infinite loop that continuously mines blocks as soon as the
     * blockchain is ready and data is available in the memPool
     */
    private void mine() {
        while (isRunning) {
            // Get the block data from the mem pool
            LinkedList<String> blockData = new LinkedList<>();
            while(blockData.size() < MAX_BLOCK_ENTRIES) {
                try {
                    blockData.offer(memPool.take());
                } catch (InterruptedException e) {
                    LOG.warn("Attempt to interrupt miner", e);
                    Thread.interrupted();
                }
            }
            Block block = new Block();
            block.setPreviousBlockHash(blocks.getLast().getHash());
            block.setTimestamp(ZonedDateTime.now(UTC));
            block.setData(blockData);
            block = proofOfWork.getProof(block);
            blocks.add(block);
        }
    }

    @Override
    public Queue<String> getMemPool() {
        return memPool;
    }

    @Override
    public void insertData(String data) {
        memPool.add(data);
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }


}
