package org.madoke.bananachain.blockchain;

import java.util.List;
import java.util.Queue;

public interface Blockchain {

    List<Block> getBlocks();

    Queue<String> getMemPool();

    void insertData(String data);

}
