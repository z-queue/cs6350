package assignment1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.util.Progressable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.io.FilenameUtils;

/**
 * Download bz2 files from remote, then upload decompressed file to HDFS. 
 * @author zeqing
 *
 */
public class Bz2FileDecompressor {
    
    static private final Logger log = LoggerFactory.getLogger(Bz2FileDecompressor.class);

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        String bz2Url = args[0];  
        String outputUri = args[1];

        URL url = new URL(bz2Url);
        String filename = FilenameUtils.getName(url.getFile());
        
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(outputUri), conf);
        
        CompressionCodecFactory factory = new CompressionCodecFactory(conf);
        CompressionCodec codec = factory.getCodec(new Path(filename));
        if (codec == null) {
          System.err.println("No codec found for " + outputUri);
          System.exit(1);
        }
        
        Path outputPath = new Path(outputUri, "assignment1");
        if (!fs.exists(outputPath)) {
            fs.mkdirs(outputPath);
        }

        String outFilename =
          CompressionCodecFactory.removeSuffix(filename, codec.getDefaultExtension());
        Path outputFilePath = new Path(outputPath, outFilename);
        log.info("File name: " + filename);
        log.info("Output path: " + outputFilePath);
        
        InputStream in = null;
        OutputStream out = null;
        try {
          in = codec.createInputStream(url.openStream());
          out = fs.create(outputFilePath, new Progressable() {
              public void progress() {
                  System.out.print(".");
                }
              });
          IOUtils.copyBytes(in, out, conf);
        } finally {
          IOUtils.closeStream(in);
          IOUtils.closeStream(out);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Program execution time: " + (endTime - startTime) + " milliseconds");
    }
}
