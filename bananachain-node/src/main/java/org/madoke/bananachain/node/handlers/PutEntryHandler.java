package org.madoke.bananachain.node.handlers;

import org.madoke.bananachain.BananaChain;
import org.madoke.bananachain.blockchain.Row;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.Status;

import javax.inject.Inject;

import static ratpack.jackson.Jackson.fromJson;

public class PutEntryHandler implements Handler {

  private BananaChain bananaChain;

  @Inject
  public PutEntryHandler(BananaChain bananaChain) {
    this.bananaChain = bananaChain;
  }

  @Override
  public void handle(Context ctx) throws Exception {
    ctx.parse(fromJson(Row.class))
      .then(data -> {
        boolean added = bananaChain.insert(data);
        if (added) {
          ctx.getResponse().status(Status.of(201)).send();
        } else {
          ctx.getResponse().status(Status.of(400));
          ctx.render("{\"error\":\"Invalid Signature\"}");
        }
      });
  }


}
