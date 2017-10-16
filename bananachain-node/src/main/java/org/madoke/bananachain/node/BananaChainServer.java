package org.madoke.bananachain.node;

import org.madoke.bananachain.node.handlers.GetBlocksHandler;
import org.madoke.bananachain.node.handlers.GetMemPoolHandler;
import org.madoke.bananachain.node.handlers.PutEntryHandler;
import org.madoke.bananachain.node.modules.BananaChainModule;
import org.madoke.bananachain.node.modules.JacksonModule;
import ratpack.guice.Guice;
import ratpack.server.RatpackServer;

public class BananaChainServer {
    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server
            .registry(Guice.registry(bindingsSpec ->
                bindingsSpec
                    .module(JacksonModule.class)
                    .module(BananaChainModule.class)
            ))
            .handlers(chain -> chain
                .get("mempool", GetMemPoolHandler.class)
                .get("blocks", GetBlocksHandler.class)
                .put("entry", PutEntryHandler.class)
            )
        );
    }
}
