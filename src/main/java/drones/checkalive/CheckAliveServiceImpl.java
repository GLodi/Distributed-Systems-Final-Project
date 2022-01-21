package drones.checkalive;

import com.progetto.grpc.CheckAliveServiceGrpc.CheckAliveServiceImplBase;
import com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatRequest;
import com.progetto.grpc.CheckAliveServiceOuterClass.HeartbeatResponse;
import io.grpc.stub.StreamObserver;

// Keep sending heartbeats to signal that you are alive
public class CheckAliveServiceImpl extends CheckAliveServiceImplBase {
    @Override
    public void beat(HeartbeatRequest request, StreamObserver<HeartbeatResponse> responseObserver) {
        System.out.println("CheckAliveService BEING CHECKED BY " + request.getId());
    }
}
