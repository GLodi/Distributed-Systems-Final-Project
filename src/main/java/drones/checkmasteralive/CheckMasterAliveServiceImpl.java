package drones.checkmasteralive;

import com.progetto.grpc.CheckMasterAliveServiceGrpc.CheckMasterAliveServiceImplBase;
import com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatRequest;
import com.progetto.grpc.CheckMasterAliveServiceOuterClass.HeartbeatResponse;
import io.grpc.stub.StreamObserver;

// as master, keep sending heartbeats to signal that you are alive
public class CheckMasterAliveServiceImpl extends CheckMasterAliveServiceImplBase {
    @Override
    public void beat(HeartbeatRequest request, StreamObserver<HeartbeatResponse> responseObserver) {
        System.out.println("CheckMasterAliveService BEING CHECKED BY " + request.getId());
    }
}
