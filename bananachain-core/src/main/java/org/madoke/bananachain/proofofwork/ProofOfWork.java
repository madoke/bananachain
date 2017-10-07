package org.madoke.bananachain.proofofwork;

import org.madoke.bananachain.blockchain.Block;

public interface ProofOfWork {

    Block getProof(Block block);

}
