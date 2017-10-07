package org.madoke.bananachain.node.services;

import com.google.inject.Inject;
import org.madoke.bananachain.BananaChain;
import ratpack.service.Service;
import ratpack.service.StartEvent;
import ratpack.service.StopEvent;

public class BananaChainService implements Service {

    private final BananaChain bananaChain;

    @Inject
    public BananaChainService(BananaChain bananaChain) {
        this.bananaChain = bananaChain;
    }

    @Override
    public void onStart(StartEvent event) throws Exception {
        bananaChain.start();
    }

    @Override
    public void onStop(StopEvent event) throws Exception {
        bananaChain.stop();
    }
}
