package com.claims.demo.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

    private final S3AsyncClient s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(S3AsyncClient s3Client) {
        this.s3Client = s3Client;
    }

    public Mono<Void> uploadFile(String key, FilePart file) {
        return DataBufferUtils.join(file.content())
            .flatMap(dataBuffer -> {
                byte[] bytes = new byte[dataBuffer.readableByteCount()];
                dataBuffer.read(bytes);
                DataBufferUtils.release(dataBuffer);

                PutObjectRequest request = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .contentType("image/png")
                        .build();

                return Mono.fromFuture(() ->
                        s3Client.putObject(request, AsyncRequestBody.fromBytes(bytes))
                );
            }).then();
    }
}
