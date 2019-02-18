////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 - A Bit of Help, Inc.  All Rights Reserved.
// Use of this source code is governed by the content in the LICENSE file in the root of this repository.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package grpckotlin.simple

import io.grpc.ServerBuilder

class Server {

    companion object {

        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {

            println("Starting the grpc server...")

            val port = 5800

            val service = GisMappingServiceImpl()
            val server = ServerBuilder.forPort(port).addService(service).build()
            val instance = server.start()

            println("Server started, listening on $port")
            instance.awaitTermination()
            //server.shutdownNow()
            println("Terminating the grpc server...")
        }
    }
}