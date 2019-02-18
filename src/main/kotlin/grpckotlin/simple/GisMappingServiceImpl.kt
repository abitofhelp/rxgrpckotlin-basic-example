////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 - A Bit of Help, Inc.  All Rights Reserved.
// Use of this source code is governed by the content in the LICENSE file in the root of this repository.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package grpckotlin.simple

import io.grpc.stub.StreamObserver

class GisMappingServiceImpl : GisMappingServiceGrpc.GisMappingServiceImplBase() {

    override fun translateEnToFr(request: Request?, responseObserver: StreamObserver<Response>?) {
        println("Server received '$request.enMessage' to be translated into French...")

        val reply = Response.newBuilder().setFrMessage("moi?").build()
        println("Server returning '$reply.frMessage'...")

        responseObserver?.onNext(reply)
        responseObserver?.onCompleted()
    }
}

