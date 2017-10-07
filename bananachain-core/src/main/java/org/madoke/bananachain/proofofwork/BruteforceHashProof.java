package org.madoke.bananachain.proofofwork;

import org.madoke.bananachain.blockchain.Block;
import org.madoke.bananachain.utils.HashUtils;

/**
 * Very simple implementation of a proof of work algorithm that
 * tries to find a block hash starting with n zero chars ('0').
 * On each attempt, the algorithm increases the nonce by 1, starting
 * at 0
 */
public class BruteforceHashProof implements ProofOfWork {

    private String requiredChars;

    public BruteforceHashProof(int difficulty) {
        setDifficulty(difficulty);
    }

    public void setDifficulty(int difficulty) {
        requiredChars = new String(new char[difficulty]).replace('\0','0');
    }

    @Override
    public Block getProof(Block block) {
        for (int currentNonce = 0; ; currentNonce++) {
            block.setNonce(currentNonce);
            String hash = HashUtils.sha256(block.toString());
            if (hash.startsWith(requiredChars)) {
                block.setHash(hash);
                return block;
            }
        }
    }
}
