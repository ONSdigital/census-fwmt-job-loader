package uk.gov.ons.census.fwmt.jobloader.service.impl;

import com.google.api.client.util.IOUtils;
import com.google.api.gax.paging.Page;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import uk.gov.ons.census.fwmt.jobloader.service.XmlDownloadService;

import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XmlDownloadServiceImpl implements XmlDownloadService {

  public void downloadXmlFile() {

    // The name of the bucket to access
     String bucketName = "my-bucket";

    // The name of the remote file to download
    // follow some sort of file format containing a time stamp?
     String srcFilename = "file.txt";

    // The path to which the file should be downloaded
     Path destFilePath = Paths.get("/local/path/to/file.txt");

    // Instantiate a Google Cloud Storage client
    Storage storage = StorageOptions.getDefaultInstance().getService();

    // Get specific file from specified bucket
    Blob blob = storage.get(BlobId.of(bucketName, srcFilename));

    // Download file to specified path
    blob.reader();

  }


  public String exampleMethod() {

    Storage storage = getStorage();

    Bucket bucket = storage.create(BucketInfo.of("testFileBucket"));

    //if we know the name of the blodId within google
//    Blob blob = storage.get(blobId);
//    String value = new String(blob.getContent());

    // if we dont hav blobId - search bucket by name]
    Page<Blob> blobs = bucket.list();
    for (Blob blob: blobs.getValues()) {
      String name = "bucketName?";
      if (name.equals(blob.getName())) {
        return new String(blob.getContent());
      }
    }

    return null;
  }

  public void simplestImplementationApparently() {

    // at this point I have not a dicky bird about blobs and buckets

    Blob blob = bucket.get("some-file");
    ReadChannel reader = blob.reader();
    InputStream inputStream = Channels.newInputStream(reader);

    // The Channels is from java.nio.
    // Furthermore you can then use commons io to easily read to InputStream into an OutputStream:
    IOUtils.copy(inputStream, outputStream);

  }

  //this will work only with files 64 * 1024 bytes on smaller
  @Nullable
  public byte[] getFileBytes(String gcsUri) throws IOException {

    Blob blob = getBlob(gcsUri);

    ReadChannel reader;
    byte[] result = null;
    if (blob != null) {
      reader = blob.reader();
      ByteBuffer bytes = ByteBuffer.allocate(64 * 1024);

      while (reader.read(bytes) > 0) {
        bytes.flip();
        result = bytes.array();
        bytes.clear();
      }
    }
    return result;
  }

  private Storage getStorage() {

    // Not entirely sure how credentials are found?
    Credentials credentials = null;
    try {
      credentials = GoogleCredentials
          .fromStream(new FileInputStream("path/to/file"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return StorageOptions.newBuilder().setCredentials(credentials)
        .setProjectId("testFiles").build().getService();
  }

  @Nullable
  private Blob getBlob(String gcsUri) {
    //gcsUri is "gs://" + blob.getBucket() + "/" + blob.getName(),
    //example "gs://myapp.appspot.com/ocr_request_images/000c121b-357d-4ac0-a3f2-24e0f6d5cea185dffb40eee-850fab211438.jpg"

    Storage storage = getStorage();

    String bucketName = parseGcsUriForBucketName(gcsUri);
    String fileName = parseGcsUriForFilename(gcsUri);

    if (bucketName != null && fileName != null) {
      return storage.get(BlobId.of(bucketName, fileName));
    } else {
      return null;
    }
  }

  @Nullable
  private String parseGcsUriForFilename(String gcsUri) {
    String fileName = null;
    String prefix = "gs://";
    if (gcsUri.startsWith(prefix)) {
      int startIndexForBucket = gcsUri.indexOf(prefix) + prefix.length() + 1;
      int startIndex = gcsUri.indexOf("/", startIndexForBucket) + 1;
      fileName = gcsUri.substring(startIndex);
    }
    return fileName;
  }

  @Nullable
  private String parseGcsUriForBucketName(String gcsUri) {
    String bucketName = null;
    String prefix = "gs://";
    if (gcsUri.startsWith(prefix)) {
      int startIndex = gcsUri.indexOf(prefix) + prefix.length();
      int endIndex = gcsUri.indexOf("/", startIndex);
      bucketName = gcsUri.substring(startIndex, endIndex);
    }
    return bucketName;
  }

}
