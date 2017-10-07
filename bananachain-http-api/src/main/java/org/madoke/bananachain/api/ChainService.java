package org.madoke.bananachain.api;

import com.google.inject.Inject;
import org.madoke.bananachain.BananaChain;
import ratpack.service.Service;
import ratpack.service.StartEvent;

public class ChainService implements Service {

    private final BananaChain bananaChain;

    @Inject
    public ChainService(BananaChain bananaChain) {
        this.bananaChain = bananaChain;
    }

    @Override
    public void onStart(StartEvent event) throws Exception {

    }
}
