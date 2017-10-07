# Bananachain
Dummy implementation of a blockchain mostly for fun and learning. **Don't use it in Production**.

Check out the [wiki](https://github.com/madoke/bananachain/wiki) for some theory on blockchain and decentralized shenaningans.

## How to use this

### Spinning up a node

Requirements:
- java
This will start a bananachain node on your machine with an empty chain.

```bash
./gradlew bananachain-node:run
```

### Node API

#### GET /blocks
This will return the list of blocks in the blockchain.
Example request:
```bash
curl -X GET http://localhost:5050/blocks 
```
Example response:
```json
[
    {
        "timestamp": 1507402753.296,
        "hash": "000005429ff30008120cdf1d0b5fea4ce0a49cb34350fbad5b698c1c3a3e92dc",
        "previousBlockHash": "00000000000000000000000000000000",
        "data": [
            "Bananachain Genesis Block"
        ],
        "nonce": 11389
    }
]
```

#### PUT /data

This will add data to the node's mempool, which will eventually be mined into a block
Example Request:
```bash
curl -X PUT http://localhost:5050/data -H 'content-type: application/json' -d '{"data":"Test data !"}'
```
There is no response body, just a 200 OK
