package balancefy.api.application.utils;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


public class FileUploadUtil {
    public static void saveFile( String fileName, MultipartFile multipartFile) throws IOException {

        AmazonS3 s3Client = AmazonS3ClientBuilder.
                standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-east-1")
                )
                .enablePathStyleAccess()
                .build();

        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(multipartFile.getContentType());
        data.setContentLength(multipartFile.getSize());

       s3Client.putObject("balancefy-d", fileName, multipartFile.getInputStream(), data);

    }
}
