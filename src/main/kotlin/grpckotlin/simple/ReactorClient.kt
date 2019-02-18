////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 - A Bit of Help, Inc.  All Rights Reserved.
// Use of this source code is governed by the content in the LICENSE file in the root of this repository.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package grpckotlin.simple

import io.grpc.netty.NettyChannelBuilder
import reactor.core.publisher.Mono


class ReactorClient {

    companion object {

        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            println("Running the reactor grpc client...")

            // channel = InProcessChannelBuilder.forName("GradleProof").build()
            val channel = NettyChannelBuilder
                    .forAddress("localhost", 5800)
                    .usePlaintext()
                    .flowControlWindow(NettyChannelBuilder.DEFAULT_FLOW_CONTROL_WINDOW)
                    .build()

            try {
                val stub = ReactorGisMappingServiceGrpc.newReactorStub(channel)
                val enMsg = "Me?"
                val request = Mono.just(Request.newBuilder().setEnMessage(enMsg).build())

                println("Sending '$enMsg' to be translated into French...")

                stub.translateEnToFr(request)
                        .subscribe {
                            println("The translation of '$enMsg' en Francais est '$it.frMessage'.")
                        }
            } finally {
                channel.shutdownNow()
            }
        }
    }
}
