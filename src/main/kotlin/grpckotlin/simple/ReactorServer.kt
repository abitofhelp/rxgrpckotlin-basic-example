////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 - A Bit of Help, Inc.  All Rights Reserved.
// Use of this source code is governed by the content in the LICENSE file in the root of this repository.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package grpckotlin.simple

import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder

class ReactorServer {

    companion object {

        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            println("Starting the reactive grpc server...")

            val server = InProcessServerBuilder
                    .forName("ReactorServer")
                    .addService(ReactorGisMappingServiceImpl())
                    .build()

            val instance = server.start()

            val channel = InProcessChannelBuilder
                    .forName("ReactorServer")
                    .usePlaintext()
                    .build()

            val stub = ReactorGisMappingServiceGrpc.newReactorStub(channel)


            println("Reactive server started...")
            instance.awaitTermination()
            channel.shutdownNow()
            server.shutdownNow()
            println("Terminating the reactor grpc server...")
        }
    }
}