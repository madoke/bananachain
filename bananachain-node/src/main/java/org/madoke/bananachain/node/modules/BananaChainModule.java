package org.madoke.bananachain.node.modules;

import com.google.inject.AbstractModule;
import org.madoke.bananachain.BananaChain;
import org.madoke.bananachain.node.handlers.GetBlocksHandler;
import org.madoke.bananachain.node.handlers.PutDataHandler;
import org.madoke.bananachain.node.services.BananaChainService;

import static com.google.inject.Scopes.SINGLETON;

public class BananaChainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PutDataHandler.class);
        bind(GetBlocksHandler.class);
        bind(BananaChainService.class).in(SINGLETON);
        bind(BananaChain.class).in(SINGLETON);
    }
}
