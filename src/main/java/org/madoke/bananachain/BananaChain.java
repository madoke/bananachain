package org.madoke.bananachain;

import org.madoke.bananachain.model.Block;
import org.madoke.bananachain.utils.HashUtils;
import org.madoke.bananachain.utils.SerializationUtils;

import java.util.LinkedList;
import java.util.List;

public class BananaChain implements Blockchain {

    private static final int MAX_ELEMENTS = 1;

    LinkedList<Block> blocks = new LinkedList<>();
    LinkedList<String> mempool = new LinkedList<>();

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public void insertData(String data) {
        mempool.add(data);
    }

    public void mine() {
        String data = mempool.pollFirst();
        Block block = new Block();
        block.setPreviousBlockHash(blocks.size() > 0 ? blocks.getLast().getHash() : "");
        block.setData(data);
        for(int currentNonce = 0; ; currentNonce++) {
            block.setNonce(currentNonce);
            String serializedBlock = SerializationUtils.serialize(block);
            String hash = HashUtils.sha256(serializedBlock);
            if(hash.startsWith("00000")) {
                block.setHash(hash);
                blocks.add(block);
                return;
            }
        }
    }

    public static void main(String[] args) {
        BananaChain bc = new BananaChain();
        bc.insertData("theData");
        bc.mine();
        List<Block> blocks = bc.getBlocks();
        System.out.println(blocks.get(0).getData());
        System.out.println(blocks.get(0).getHash());
        System.out.println(blocks.get(0).getPreviousBlockHash());
        System.out.println(blocks.get(0).getNonce());

        bc.insertData("theData2");
        bc.mine();
        blocks = bc.getBlocks();
        System.out.println(blocks.get(0).getData());
        System.out.println(blocks.get(0).getHash());
        System.out.println(blocks.get(0).getPreviousBlockHash());
        System.out.println(blocks.get(0).getNonce());
    }

}
