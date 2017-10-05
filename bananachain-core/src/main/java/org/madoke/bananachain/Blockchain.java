package org.madoke.bananachain;

import org.madoke.bananachain.model.Block;

import java.util.List;

public interface Blockchain {

    List<Block> getBlocks();

    void insertData(String data);


}
