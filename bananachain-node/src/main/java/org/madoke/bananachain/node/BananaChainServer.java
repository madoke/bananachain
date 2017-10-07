package org.madoke.bananachain.node;

import org.madoke.bananachain.node.handlers.GetBlockchainHandler;
import org.madoke.bananachain.node.modules.BananaChainModule;
import ratpack.guice.Guice;
import ratpack.server.RatpackServer;

public class BananaChainServer {
    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server
            .registry(Guice.registry(bindingsSpec ->
                bindingsSpec.module(BananaChainModule.class)
            ))
            .handlers(chain -> chain
                .get("blocks", GetBlockchainHandler.class)
            )
        );
    }
}
