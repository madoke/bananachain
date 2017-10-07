package org.madoke.bananachain.node.handlers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.madoke.bananachain.BananaChain;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.Status;

import javax.inject.Inject;

import static ratpack.jackson.Jackson.fromJson;

public class PutDataHandler implements Handler {

    private BananaChain bananaChain;

    @Inject
    public PutDataHandler(BananaChain bananaChain) {
        this.bananaChain = bananaChain;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.parse(fromJson(Data.class))
            .then(data -> {
                bananaChain.insertData(data.getData());
                ctx.getResponse().status(Status.OK).send();
            });
    }

    /**
     * Bean to represent the posted data object
     */
    public static class Data {

        private final String data;

        public Data(@JsonProperty("data") String data) {
            this.data = data;
        }

        String getData() {
            return data;
        }
    }

}
