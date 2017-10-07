package org.madoke.bananachain.blockchain;

import java.time.ZonedDateTime;
import java.util.List;

public class Block {

    private ZonedDateTime timestamp;
    private String hash;
    private String previousBlockHash;
    private List<String> data;
    private Integer nonce;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public Integer getNonce() {
        return nonce;
    }

    public void setNonce(Integer nonce) {
        this.nonce = nonce;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Builds a string representation of this block, containing
     * all the fields
     * @return a String instance
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(String s : getData()) {
            sb.append(s);
        }
        sb.append(getTimestamp().toString());
        sb.append(getPreviousBlockHash());
        sb.append(getNonce());
        return sb.toString();
    }
}
