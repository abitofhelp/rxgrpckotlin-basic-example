package grpckotlin.simple

import io.grpc.ServerBuilder

fun main(args: Array<String>) {
    println("Starting this awful piece of garbage.")

    val port = 5800

    val server = GisMappingServiceImpl()
    val s = ServerBuilder.forPort(port).addService(server).build()
    var tmp = s.start()

    println("Server started, listening on $port")
    tmp.awaitTermination()
}