package grpckotlin.simple

import io.grpc.ManagedChannelBuilder
import java.util.logging.Logger

class Client {

    companion object {
        // private val logger = Logger.getLogger(Client::class.java.name)

        /**
         * Greet server. If provided, the first element of `args` is the name to use in the
         * greeting.
         */
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            println("Running the test client.")

            var channel = ManagedChannelBuilder.forAddress("localhost", 5800).usePlaintext().build()
            val blockingStub = GisMappingServiceGrpc.newBlockingStub(channel)

            val request = Request.newBuilder().setEnMessage("Me?").build()

            println("Sending '${request.enMessage}' to be translated to French...")
            val response = blockingStub.translateEnToFr(request)

            println("The translation of '${request.enMessage}' ${response.frMessage}.")
        }
    }
}