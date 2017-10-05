package org.madoke.bananachain.utils;

import org.madoke.bananachain.model.Block;

public class SerializationUtils {

    public static String serialize(Block block) {
        return new StringBuilder()
            .append(block.getData())
            .append(block.getPreviousBlockHash())
            .append(block.getNonce())
            .toString();
    }

}
