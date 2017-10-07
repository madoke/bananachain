package org.madoke.bananachain.node.modules;

import com.google.inject.AbstractModule;
import org.madoke.bananachain.BananaChain;
import org.madoke.bananachain.node.handlers.GetBlockchainHandler;
import org.madoke.bananachain.node.services.BananaChainService;

import static com.google.inject.Scopes.SINGLETON;

public class BananaChainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetBlockchainHandler.class);
        bind(BananaChainService.class).in(SINGLETON);
        bind(BananaChain.class).in(SINGLETON);
    }
}
