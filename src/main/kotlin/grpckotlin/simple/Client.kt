////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 - A Bit of Help, Inc.  All Rights Reserved.
// Use of this source code is governed by the content in the LICENSE file in the root of this repository.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package grpckotlin.simple

import io.grpc.ManagedChannelBuilder

class Client {

    companion object {

        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            println("Running the grpc client...")

            var channel = ManagedChannelBuilder.forAddress("localhost", 5800).usePlaintext().build()
            val blockingStub = GisMappingServiceGrpc.newBlockingStub(channel)

            val enMsg = "Me?"
            val request = Request.newBuilder().setEnMessage(enMsg).build()

            println("Sending '${request.enMessage}' to be translated to French...")
            val response = blockingStub.translateEnToFr(request)
            val frMsg = response.frMessage
            println("The translation of '$enMsg' en Francais est '$frMsg'")
        }
    }
}