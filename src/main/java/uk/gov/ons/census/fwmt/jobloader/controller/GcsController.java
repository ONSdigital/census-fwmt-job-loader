package uk.gov.ons.census.fwmt.jobloader.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GcsController {

  @Value("gs://REPLACE_WITH_YOUR_BUCKET/my-file.txt")
  private Resource gcsFile;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String readGcsFile() throws IOException {
    return StreamUtils.copyToString(
        gcsFile.getInputStream(),
        Charset.defaultCharset()) + "\n";
  }
}



