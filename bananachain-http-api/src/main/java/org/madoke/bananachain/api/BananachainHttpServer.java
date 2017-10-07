package org.madoke.bananachain.api;

import org.madoke.bananachain.BananaChain;
import org.madoke.bananachain.blockchain.Block;
import ratpack.server.RatpackServer;

import java.util.List;

import static ratpack.jackson.Jackson.json;

public class BananachainHttpServer {
    public static void main(String[] args) throws Exception {

        BananaChain bc = new BananaChain();
        bc.insertData("theData");
        bc.mine();
        bc.insertData("theData2");
        bc.mine();

        List<Block> blocks = bc.getBlocks();

        RatpackServer.start(server -> server
            .handlers(chain -> chain
                .get(ctx -> ctx.render(json(blocks)))
            )
        );

    }
}
