////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 - A Bit of Help, Inc.  All Rights Reserved.
// Use of this source code is governed by the content in the LICENSE file in the root of this repository.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package grpckotlin.simple

import reactor.core.publisher.Mono

class ReactorGisMappingServiceImpl : ReactorGisMappingServiceGrpc.GisMappingServiceImplBase() {

    override fun translateEnToFr(request: Mono<Request>): Mono<Response> {
        val frMsg = "Moi?"

        return request
                .map(Request::getEnMessage)
                .map { println("Server received '$it' to be translated into French...") }
                .map { frMsg }
                .map { Response.newBuilder().setFrMessage(it).build() }
    }
}

