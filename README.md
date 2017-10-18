# Bananachain :banana: :link:
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


#### Reading the mempool (GET /mempool)

This will return the list of rows that are validated and waiting to be mined into a block.

Example Request:
```bash
curl -X GET \
  http://localhost:5050/mempool
```



#### Submitting data (PUT /mempool)

To submit new data to the bananachain, a digital signature must be generated. The algorithm used is the Elliptic Curve with secp256r1 curve. Example code on how to generate a signature and a public/private key pair can be found on ECDSAUtil.java.

Parameters:
- data: The actual data to write in the chain
- signature: A valid ECDSA Signature
- publicKey: The counterpart of the private key used to generate the signature

Example Request:
```bash
curl -X PUT \
  http://localhost:5050/mempool \
  -H 'content-type: application/json' \
  -d '{
  "data": "This is some random data",
  "signature": "MEQCIBX+KiuiyfDQYsup6WKzqoxRrGWzJcDh4pgLM6LQ3t76AiBn11C8HQfOLA8jD02V0deHrLLxsjlRfA/i6Zi+nJKTxg==",
  "publicKey": "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE8d7abGmRR/YKYBgUf9RVh3xt6aGAM7oyO2kdzbhufE/ne/HfSGrvkVastpPtxR0v0awQAwDCrrXR5kpe8e5URQ=="
}'
```

Responses:
- 201 Created: If the signature is valid
- 400 Bad request: If the signature is invalid



#### Reading the blockchain (GET /blocks)

This will return the list of blocks in the blockchain.

Example request:
```bash
curl -X GET http://localhost:5050/blocks 
```

Example response:
```json
[
    {
        "hash": "a61b2707dc1c30ab3bc28ef743778b909c6cc8f26a74dbf95b096f62ae284131",
        "previousBlockHash": "0000000000000000000000000000000000000000000000000000000000000000",
        "data": [
            {
                "data": "Genesis Block",
                "signature": null,
                "publicKey": null
            }
        ],
        "nonce": null,
        "timestamp": 1508352652.788
    },
    {
        "hash": "00000109f3ba1ca136e20f6842c92e144e2e6169a76809e9114033c2cc8b45e7",
        "previousBlockHash": "a61b2707dc1c30ab3bc28ef743778b909c6cc8f26a74dbf95b096f62ae284131",
        "data": [
            {
                "data": "This is some random data",
                "signature": "MEQCIBX+KiuiyfDQYsup6WKzqoxRrGWzJcDh4pgLM6LQ3t76AiBn11C8HQfOLA8jD02V0deHrLLxsjlRfA/i6Zi+nJKTxg==",
                "publicKey": "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE8d7abGmRR/YKYBgUf9RVh3xt6aGAM7oyO2kdzbhufE/ne/HfSGrvkVastpPtxR0v0awQAwDCrrXR5kpe8e5URQ=="
            }
        ],
        "nonce": 932407,
        "timestamp": 1508353448.014
    }
]
```
